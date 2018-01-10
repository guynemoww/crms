/**
 * 
 */
package com.bos.pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.plus.QueryCredPayPlanRq;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.FileManager;
import com.primeton.tsl.PayConInfo;

import commonj.sdo.DataObject;

/**
 * @author chenchuan
 * @date 2016-05-13 16:02:49
 * 
 */
@Bizlet("")
public class AccountChangeToJL {

	@Bizlet("账务调整调用计量接口")
	public String accountChangeToJL(DataObject obj) {
		try {
			String bus_date = GitUtil.getBusiDateYYYYMMDD();// 当前营业日期
//			String bus_date = "20160711";// 当前营业日期

			//int lcsStan = Integer.parseInt(BizNumGenerator.getLcsStan());// 交易流水号
			Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());// 交易流水号
			DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryNum", obj.getString("summaryNum"));
			DatabaseUtil.expandEntityByTemplate("default", summary, summary);

			DataObject loan = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loan.set("loanId", summary.getString("loanId"));
			DatabaseUtil.expandEntity("default", loan);

			PayConInfo vo = new PayConInfo();
			vo.getBaseVO().setTranCod("MA1_1508");// 交易代码
			vo.getBaseVO().setOpr("HX001");// 操作员
			vo.getBaseVO().setAut("HX001");// 授权员
			vo.getBaseVO().setAcsMethStan(lcsStan);// 接入系统流水号
			vo.getBaseVO().setRcnStan(lcsStan);// 对账流水号
			vo.getBaseVO().setSupStan(lcsStan);// 自动生成9位放款流水号
			vo.getBaseVO().setTranFrom("001");// 业务渠道来源 001-信贷系统
			vo.getBaseVO().setAccSysDate(bus_date);// 营业日期 检查该机构在机构表中是否存在
			vo.getBaseVO().setTranDate(bus_date);// 接入系统营业日期
			vo.getBaseVO().setTranTimes("1");// 交易次数标志 1次交易后填2，二次交易后填3
			vo.getBaseVO().setToCoreSys("0");// 交易是否转发核心系统标志 0=不转发；1=向核心系统转发
			vo.getBaseVO().setDepCod(loan.getString("loanOrg"));// 交易机构，会校验
			vo.setDueNum(summary.getString("summaryNum"));// //借据编号
			vo.setTelNo(BizNumGenerator.getLcsStan());// 通知书编号
			vo.setPayAmt(obj.getBigDecimal("amt"));// 退还多收客户利息金额
			vo.setRcvItrType("01");// 调账类型：自营填01，委托填02（01-退还多收客户利息02-多退委托贷款手续费）
			vo.setSpecCode("0008");// 事项代码，传0008
			vo.setOpnDep(loan.getString("loanOrg"));// 出账机构
			// 账务调整第一次交易
			Object[] params1 = new Object[2];
			params1[0] = "MA1_1508";
			params1[1] = vo;
			ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] objs = logicComponent.invoke("newDataInsertCheck", params1);
			DataObject vo1 = (DataObject) objs[0];
			BaseVO baseVO = (BaseVO) vo1.get("baseVO");
			String returnCode = (String) baseVO.getErrCod();
			if (!"200".equals(returnCode)) {
				return baseVO.getErrMsg();
			}

			// 账务调整，第二次交易
//			Object[] params2 = new Object[2];
//			params2[0] = "MDA_2003";
//			params2[1] = vo;
//			ILogicComponent logicComponent2 = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
//			Object[] objs2 = logicComponent2.invoke("newDataInsertCheck", params2);
//			DataObject vo2 = (DataObject) objs2[0];
//			BaseVO baseVO2 = (BaseVO) vo2.get("baseVO");
//			String returnCode2 = (String) baseVO2.getRpsCod();
//			if (!"200".equals(returnCode2)) {
//				return baseVO2.getRpsMsg();
//			}

			// 账务调整，第三次交易
			Object[] params3 = new Object[2];
			vo.getBaseVO().setTranTimes("2");// 交易次数标志 1次交易后填2，二次交易后填3
			params3[0] = "MDA_2003";
			params3[1] = vo;
			ILogicComponent logicComponent3 = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] objs3 = logicComponent3.invoke("newDataInsertCheck", params3);
			DataObject vo3 = (DataObject) objs3[0];
			BaseVO baseVO3 = (BaseVO) vo3.get("baseVO");
			String returnCode3 = (String) baseVO3.getErrCod();
			if (!"200".equals(returnCode3)) {
				return baseVO3.getErrMsg();
			}
		} catch (Throwable e) {
			return "调用计量接口异常";
		}
		return null;

	}
	// 查询贷款还款计划表-T1413
	@Bizlet("查询贷款还款计划表-T1413")
	public DataObject[] aftTo1413(DataObject obj) throws EOSException {
		DataObject[] da=null;
			try {
				String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
				//int lcsStan = Integer.parseInt(BizNumGenerator.getLcsStan());//交易流水号
				Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());// 交易流水号
				DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
				DataObject repayment = DataObjectUtil.createDataObject("com.bos.dataset.pub.repayment");
				
				summary.set("summaryNum", obj.getString("dueNum"));
				DatabaseUtil.expandEntityByTemplate("default", summary, summary);
			    String dueNumId=summary.getString("summaryId");
				if(!"".equals(dueNumId)&&dueNumId!=null){
			
				DataObject loan = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
				loan.set("loanId", summary.getString("loanId"));
				DatabaseUtil.expandEntity("default", loan);
				
				Object[] params1 = new Object[2];
				params1[0] = "T1413";
				
				QueryCredPayPlanRq vo = new QueryCredPayPlanRq();
				BaseVO bvo = new BaseVO();
				bvo.setTranCod("T1413");//交易代码
				bvo.setOpr(GitUtil.getCurrentUserId());//操作员
				bvo.setAut(GitUtil.getCurrentUserId());//授权员
				bvo.setAcsMethStan(lcsStan);//接入系统流水号
				bvo.setRcnStan(lcsStan);//对账流水号
				bvo.setSupStan(lcsStan);///自动生成9位放款流水号
				bvo.setTrnDep(loan.getString("loanOrg"));//交易机构，会校验
				bvo.setTranFrom("47");//业务渠道来源 001-信贷系统
				bvo.setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
				bvo.setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
				bvo.setOpnDep(loan.getString("loanOrg"));//贷款开户机构
				bvo.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
				bvo.setTranDate(bus_date);//接入系统营业日期 
				bvo.setOrigFrom("11000");
				bvo.setLegPerCod("9999");
				vo.setDueNum(summary.getString("summaryNum"));//借据编号
				String amt=obj.getString("amt");
				if(!"".equals(amt)&&amt!=null){
				BigDecimal n = new BigDecimal(amt);
				vo.setAmt(n);
				}
				String begDate=obj.getString("begDate");
				if(!"".equals(begDate)&&begDate!=null&&begDate.length()>10){
					String yy=begDate.substring(0, 4);
					String mm=begDate.substring(5, 7);
					String dd=begDate.substring(8, 10);
					String yymmdd=yy+mm+dd;
				vo.setBegDate(yymmdd);
				}
				String endDate=obj.getString("endDate");
				if(!"".equals(endDate)&&endDate!=null&&endDate.length()>10){
					String yy=endDate.substring(0, 4);
					String mm=endDate.substring(5, 7);
					String dd=endDate.substring(8, 10);
					String yymmdd=yy+mm+dd;
				vo.setEndDate(yymmdd);
				}
				String payDate=obj.getString("payDate");
				if(!"".equals(payDate)&&payDate!=null&&payDate.length()>10){
					String yy=payDate.substring(0, 4);
					String mm=payDate.substring(5, 7);
					String dd=payDate.substring(8, 10);
					String yymmdd=yy+mm+dd;
				vo.setPayDate(yymmdd);
				}
				String paytype=obj.getString("curPrmPayTyp");
				if(!"".equals(paytype)&&paytype!=null&&paytype.length()==4){
				vo.setCurAstPayTyp(paytype.substring(2, 4));
				vo.setCurPrmPayTyp(paytype.substring(0, 2));
				}
				vo.setExiFlg(obj.getString("exiFlg"));
				vo.setItrRateWay(obj.getString("itrRateWay"));
				vo.setNorItrRate(obj.getBigDecimal("norItrRate"));
				String stgFirstMon=obj.getString("stgFirstMon");
				if(!"".equals(stgFirstMon)&&!stgFirstMon.isEmpty()){
				vo.setStgFirstMon(Integer.parseInt(stgFirstMon));
				}
				vo.setBaseVO(bvo);
				params1[1] = vo;
				
				ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
				Object[] objs = null;
				objs = logicComponent.invoke("newDataInsertCheck", params1);
				DataObject vo1 = (DataObject) objs[0];
				BaseVO baseVO = (BaseVO) vo1.get("baseVO");
				String returnCode = (String) baseVO.getErrCod();
				String remountefilePath = (String) vo1.get("rltFileDir");
				String loacalFileName = (String) vo1.get("rltFile");
				System.out.println("aplus路径："+remountefilePath.substring(remountefilePath.indexOf("discFiles"))+loacalFileName);
				System.out.println("文件名："+loacalFileName);
				FileManager fileManager = new FileManager();
				fileManager.fileDown(remountefilePath.substring(remountefilePath.indexOf("discFiles"))+loacalFileName, "aplus/"+loacalFileName);
				String loanFilePath = "/home/weblogic/tft_data/server_data/diankuan/aplus/"+loacalFileName;
				System.out.println("crms路径："+loanFilePath);
				 ArrayList<String[]> arry = readTxtFile(loanFilePath);
				  da=getDataObject(arry);
				if (!"00000".equals(returnCode)) {
					throw new EOSException(baseVO.getErrMsg());
				}
				}else{
					throw new EOSException("该借据不存在");
				}

			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
			return  da;
		}
    public ArrayList<String[]> readTxtFile(String filePath){
        ArrayList<String[]> array=new ArrayList<String[]>();
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    int len=0;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    String[] aa=lineTxt.split(String.valueOf((char)0x01));
                    	array.add(aa);
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return array;
     
    }
    
    public DataObject[] getDataObject(ArrayList<String[]>  array){
    	DataObject[] obj=new DataObject[array.size()];
            for(int i=0;i<array.size();i++){
            	String[] line=array.get(i);
            	DataObject repayment = DataObjectUtil.createDataObject("com.bos.dataset.pub.repayment");
            	repayment.set("currPeri", line[0]);
            	repayment.set("forwProvDate", line[1]);
            	repayment.set("nextProvDate", line[2]);
            	repayment.set("dCurPrin", line[3]);
            	repayment.set("dCurItr", line[4]);
            	repayment.set("dTotalAmt", line[5]);
            	repayment.set("dTotalPrin", line[6]);
            	repayment.set("dTotalItr", line[7]);
            	obj[i]=repayment;
            	}
            
        return obj;
     
    }
}
