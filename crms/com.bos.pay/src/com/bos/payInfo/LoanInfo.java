/**
 * 
 */
package com.bos.payInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;

import com.bos.biz.MathHelper;
import com.bos.inter.LoanToLcs;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.BeanToMapUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.git.easyrule.util.DateHelper;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.plus.QueryCredPayPlanRq;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.FileManager;
import com.primeton.tsl.MsgQueryPayPlan;
import com.primeton.tsl.QueryPayPlanVo;
import com.primeton.tsl.TbSupPrinPlanNTmpVO;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-05-15 18:27:44
 *
 */
@Bizlet("")
public class LoanInfo {
 
	public static TraceLogger logger = new TraceLogger(LoanInfo.class);

	/**
	 * @param loanInfo 
	 * @param args
	 * @author 3231
	 * 点击放款按钮调用，生成借据信息
	 * saveLoanInfoToSummary
	 */
	@Bizlet("")
	public void createSummary(DataObject loanInfo) {
		if (null == loanInfo.get("loanId") || "".equals(loanInfo.get("loanId"))) {
			throw new EOSException("放款ID为空");
		}
		String loanId = (String) loanInfo.get("loanId");
		logger.info("生成借据------bizId=" + loanId + "------->开始!");

		//获取时间com.bos.pub.GitUtil.getBusiDate
		Date date = GitUtil.getBusiDate();

		String productType = (String) loanInfo.get("productType");
		if ("01008001".equals(productType) || "01008002".equals(productType) || "01008010".equals(productType)) {// 银行承兑汇票
			DataObject hpje = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanHpAmt");
			hpje.set("loanId", loanId);
			DataObject[] hpjes = DatabaseUtil.queryEntitiesByTemplate("default", hpje);
			for (int i = 0; i < hpjes.length; i++) {
				DataObject hpjetp = hpjes[i];
				DataObject loanSummary = DataObjectUtil.convertDataObject(loanInfo, "com.bos.dataset.pay.TbLoanSummary", true);
				loanSummary.set("createTime", date);
				loanSummary.set("summaryStatusCd", "02");
				loanSummary.set("summaryTerm", loanInfo.getString("term"));
				loanSummary.set("cycleUnit", loanInfo.getString("unit"));
				loanSummary.set("summaryAmt", hpjetp.getBigDecimal("loanAmt"));
				loanSummary.set("jjye", hpjetp.getBigDecimal("loanAmt"));
				loanSummary.set("summaryCurrencyCd", loanInfo.getString("currencyCd"));
				loanSummary.set("tingxiStatus", "02");// 贴息状态(01-贴息 02-正常)
				loanSummary.set("tiexiStatus", "02");// 停息状态(01-停息 02-正常)
				loanSummary.set("nftNo", hpjetp.getString("summaryNum"));
				loanSummary.set("summaryNum", hpjetp.getString("summaryNum"));
				// 银承rmbamt需要重新计算
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("amt", (BigDecimal) loanSummary.get("summaryAmt"));
				params1.put("ll", (BigDecimal) loanSummary.get("exchangeRate"));
				BigDecimal rmbAmt = MathHelper.expressionBigDecimal("amt*ll", params1);
				loanSummary.set("rmbAmt", rmbAmt);
				DatabaseUtil.saveEntity("default", loanSummary);
			}
		} else if ("01006001".equals(productType) || "01006002".equals(productType) || "01006010".equals(productType)) {// 贴现
			DataObject tbLoanHpAmt = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanHpAmt");
			tbLoanHpAmt.set("loanId", loanId);
			DataObject[] hpjes = DatabaseUtil.queryEntitiesByTemplate("default", tbLoanHpAmt);
			for (int i = 0; i < hpjes.length; i++) {
				DataObject tempData = hpjes[i];
				DataObject loanSummary = DataObjectUtil.convertDataObject(loanInfo, "com.bos.dataset.pay.TbLoanSummary", true);
				loanSummary.set("createTime", date);
				loanSummary.set("summaryStatusCd", "02");
				loanSummary.set("summaryTerm", loanInfo.getString("term"));
				loanSummary.set("cycleUnit", loanInfo.getString("unit"));
				loanSummary.set("summaryAmt", tempData.getBigDecimal("loanAmt"));
				loanSummary.set("jjye", tempData.getBigDecimal("loanAmt"));
				loanSummary.set("summaryCurrencyCd", loanInfo.getString("currencyCd"));
				loanSummary.set("tingxiStatus", "02");// 贴息状态(01-贴息 02-正常)
				loanSummary.set("tiexiStatus", "02");// 停息状态(01-停息 02-正常)
				loanSummary.set("nftNo", tempData.getString("summaryNum"));
				loanSummary.set("summaryNum", tempData.getString("summaryNum")); // 借据编号
				// 银承rmbamt需要重新计算
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("amt", (BigDecimal) loanSummary.get("summaryAmt"));
				params1.put("ll", (BigDecimal) loanSummary.get("exchangeRate"));
				BigDecimal rmbAmt = MathHelper.expressionBigDecimal("amt*ll", params1);
				loanSummary.set("rmbAmt", rmbAmt);
				DatabaseUtil.saveEntity("default", loanSummary);
			}
		} else {
			//保存借据信息
			DataObject loanSummary = DataObjectUtil.convertDataObject(loanInfo,
					"com.bos.dataset.pay.TbLoanSummary", true);
			/*loanSummary.set("applyOrg", loanInfo.get("orgNum"));
			loanSummary.set("userNum", "");
			loanSummary.set("orgNum", "");*/
			loanSummary.set("createTime", date);
			if("01007001".equals(productType)||"01007002".equals(productType)||"01007003".equals(productType)||
					"01007004".equals(productType)||"01007005".equals(productType)||"01007006".equals(productType)||
					"01007007".equals(productType)||"01007008".equals(productType)||productType.equals("01007009")
					||productType.equals("01007010")||productType.equals("01007011")
					||productType.equals("01007012")||productType.equals("01007013")||productType.equals("01007014")){
				loanSummary.set("summaryStatusCd", "01");
				loanSummary.set("jjye", new BigDecimal(0));//国结产品借据生成的时候余额---0 余额：相当于已用金额
			}else{
				loanSummary.set("summaryStatusCd", "02");
				loanSummary.set("jjye", (BigDecimal) loanInfo.get("loanAmt"));
			}
			loanSummary.set("summaryTerm", loanInfo.getString("term"));
			loanSummary.set("cycleUnit", loanInfo.getString("unit"));
			loanSummary.set("summaryAmt", (BigDecimal) loanInfo.get("loanAmt"));
			loanSummary.set("summaryCurrencyCd",
					(String) loanInfo.get("currencyCd"));
			loanSummary.set("tingxiStatus", "02");
			String nftNo = BizNumGenerator.getBizNum("SEQ_PAY_JJ");
			nftNo = "JJ" + nftNo;
			loanSummary.set("nftNo", nftNo);
			
			//是否贴息判断
			//根据loanId取applyId
			Map<String,String> map = new HashMap<String, String>();
			map.put("loanId", loanId);
			Object[] applyIds = DatabaseExt.queryByNamedSql("default", "com.bos.pay.LoanSummary.getApplyIdByLoanId", map);
			if(null ==applyIds || applyIds.length==0){
				throw new EOSException("-------------未查询到applyId------------------");
			}
			String applyId = (String)((DataObject)applyIds[0]).get("APPLY_ID");
			map.put("applyId", applyId);
			//查询贴息主体
			Object[] txzts = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTxzt", map);
			if(null ==txzts || txzts.length==0){//非贴息贷款
				loanSummary.set("tiexiStatus", "02");
			}else{
				loanSummary.set("tiexiStatus", "01");
			}
			//委托贷款账户
			if("01013010".equals(productType)||"02005010".equals(productType)|| "01013001".equals(productType)||"02005001".equals(productType)||"01005002".equals(productType)||"01005003".equals(productType)){
				Object[] tcSupLoanInfoEntrInfos = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.getJlData.getTcSupLoanInfoEntrInfo", map);
				if(null ==tcSupLoanInfoEntrInfos || tcSupLoanInfoEntrInfos.length==0){
					throw new EOSException("-------------未查询到委托人相关账户------------------");
				}else{
					DataObject tcSupLoanInfoEntrInfo = (DataObject)tcSupLoanInfoEntrInfos[0];
					loanSummary.set("entrustReturnPrincipalAcc", tcSupLoanInfoEntrInfo.getString("prnSettAcc"));
					loanSummary.set("entrustReturnInterestAcc", tcSupLoanInfoEntrInfo.getString("itrSettAcc"));
				}
			}
			DatabaseUtil.saveEntity("default", loanSummary);
		}

		loanInfo.set("loanId", loanId);
		loanInfo.set("loanStatus", "03");
		DatabaseUtil.updateEntity("default", loanInfo);
		logger.info("生成借据------bizId=" + loanId + "------->结束!");
	}

	/**
	 * @param loanId 
	 * 取消出账流程
	 * */
	@Bizlet("")
	public void cancelPayOut(String loanId) {

		if (null == loanId || "".equals(loanId)) {
			throw new EOSException("放款ID为空");
		}

		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);

		if ("01".equals(loanInfo.get("loanStatus"))
				&& (null != loanInfo.get("loanId"))) {

			DataObject productInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
			productInfo.set("productCd", loanInfo.getString("productType"));
			DatabaseUtil.expandEntityByTemplate("default", productInfo,
					productInfo);
			String entityName = productInfo.getString("entityName");
			//com.bos.dataset.biz.TbBizLdzjApply
			//com.bos.dataset.pay.TbLoanLdzj
			entityName = entityName.replace("biz", "pay");
			entityName = entityName.replace("Biz", "Loan");
			entityName = entityName.replace("Apply", "");

			DataObject productDetail = DataObjectUtil
					.createDataObject(entityName);
			productDetail.set("loanId", loanId);
			DatabaseUtil.deleteEntity("default", productDetail);
			DatabaseUtil.deleteEntity("default", loanInfo);
			return;
		}
		//获取时间com.bos.pub.GitUtil.getBusiDate
		Date date = GitUtil.getBusiDate();

		//保存借据信息
		loanInfo.set("loanStatus", "04");
		loanInfo.set("updateTime", date);
		DatabaseUtil.saveEntity("default", loanInfo);
	}

	//从计量查询还款计划
	@Bizlet("从计量查询还款计划")
	public DataObject[] getHkjhsFromJl(String dueNum, String loanId) throws Throwable {
		String bus_date = GitUtil.getBusiDateYYYYMMDD();//当前营业日期
		DataObject[] resultObjects = null;
		DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);

		Object[] params1 = new Object[2];
		params1[0] = "T1413";
		QueryCredPayPlanRq vo = new QueryCredPayPlanRq();
		BaseVO bvo = new BaseVO();
		bvo.setTranCod("T1413");//交易代码
		bvo.setOpr(GitUtil.getCurrentUserId());//操作员
		bvo.setAut(GitUtil.getCurrentUserId());//授权员
		bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));//接入系统流水号
		bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));//对账流水号
		bvo.setTrnDep(loanInfo.getString("loanOrg"));//交易机构，会校验
		bvo.setTranFrom("47");//业务渠道来源 001-信贷系统
		bvo.setTranTimes("1");//交易次数标志 1次交易后填2，二次交易后填3
		bvo.setToCoreSys("0");//交易是否转发核心系统标志 0=不转发；1=向核心系统转发
		bvo.setOpnDep(loanInfo.getString("loanOrg"));//贷款开户机构
		bvo.setAccSysDate(bus_date);//营业日期 检查该机构在机构表中是否存在
		bvo.setTranDate(bus_date);//接入系统营业日期 
		bvo.setOrigFrom("11000");
		bvo.setLegPerCod("9999");
		vo.setDueNum(loanInfo.getString("summaryNum"));//借据编号
		vo.setExiFlg("1");
		vo.setBaseVO(bvo);
		params1[1] = vo;
		ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
		Object[] objs = logicComponent.invoke("newDataInsertCheck", params1);
		DataObject vo1 = (DataObject) objs[0];

		BaseVO obj = (BaseVO) vo1.get("baseVO");
		String rpsCod = obj.getErrCod();
		String rpsMsg = obj.getErrMsg();
		if (!"00000".equals(rpsCod)) {
			throw new EOSException(rpsMsg);
		}
		String remountefilePath = (String) vo1.get("rltFileDir");
		String loacalFileName = (String) vo1.get("rltFile");
		System.out.println("aplus路径："+remountefilePath.substring(remountefilePath.indexOf("discFiles"))+loacalFileName);
		System.out.println("文件名："+loacalFileName);
		FileManager fileManager = new FileManager();
		fileManager.fileDown(remountefilePath.substring(remountefilePath.indexOf("discFiles"))+loacalFileName, "aplus/"+loacalFileName);
		String loanFilePath = "/home/weblogic/tft_data/server_data/diankuan/aplus/"+loacalFileName;
		System.out.println("crms路径："+loanFilePath);
		 ArrayList<String[]> arry = readTxtFile(loanFilePath);
		List<MsgQueryPayPlan> returnList = new ArrayList<MsgQueryPayPlan>();
		MsgQueryPayPlan payPlan = null;
		for(int i=0; i<arry.size(); i++){
			String[] arrs = arry.get(i);
			payPlan = new MsgQueryPayPlan();
		    payPlan.setCurrPeri(arrs[0]);
			payPlan.setForwProvDate(arrs[1]);
			payPlan.setNextProvDate(arrs[2]);
			payPlan.setDCurPrin(new BigDecimal(arrs[3]));
			payPlan.setDCurItr(new BigDecimal(arrs[4]));
			payPlan.setDTotalAmt(new BigDecimal(arrs[5]));
			returnList.add(payPlan);
		}
		if (null == returnList || 0 == returnList.size()) {
			System.out.println(dueNum+":未查询到还款计划");
		}
		resultObjects = new DataObject[returnList.size()];
		for (int i = 0; i < returnList.size(); i++) {
			MsgQueryPayPlan mpp = (MsgQueryPayPlan) returnList.get(i);
			DataObject db = BeanToMapUtil.convertBean(mpp);
			db.set("qc", (i+1));
			resultObjects[i] = db;
		}
		
		return resultObjects;
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
	    
	//从计量查询还款计划
	@Bizlet("从计量查询还款计划试算")
	public DataObject[] getHkssFromJl(Map map,DataObject[] obj) {

		DataObject[] resultObjects = null;
		try {
			BigDecimal bz=new BigDecimal((String)map.get("bz")); //本金
			String startDate = DateHelper.foramteDateYYYYMMDD((String) map.get("startDate"));//起息日
			String endDate =DateHelper.foramteDateYYYYMMDD((String) map.get("endDate")) ;//到期日
			String hkfs = (String) map.get("hkfs");//还款方式
			int jgtq = Integer.parseInt((String) map.get("jgtq")) ;//间隔天数
			String jxzq = (String) map.get("jxzq");//结息周期
			BigDecimal nll = new BigDecimal( (String) map.get("nll")); //年利率
			int schbqc = map.get("schbqc")==null?0:Integer.parseInt((String) map.get("schbqc")) ;//首次还本期次
			String zdhkr = (String) map.get("zdhkr"); //指定还款日

			Object[] params1 = new Object[2];
			params1[0] = "MA1_1600";

			QueryPayPlanVo vo = new QueryPayPlanVo();
			vo.getBaseVO().setTranCod("MA1_1600");
			vo.getBaseVO().setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
			vo.getBaseVO().setTranDate(GitUtil.getBusiDateYYYYMMDD());
			vo.getBaseVO().setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
		

			vo.setBaseAmt(bz);
			vo.setBegDate(startDate);
			vo.setEndDate(endDate);
			vo.setPrmPayTyp(hkfs.substring(0, 2));
			vo.setAstPayTyp(hkfs.substring(2, 4));
			vo.setBetweenDays(jgtq);
			vo.setItrCyl(jxzq);
			vo.setNorItrRate(nll);
			if(schbqc!=0){
			vo.setIStgFirstMon(schbqc);
			}
			
			vo.setSpecPaymentDate(zdhkr);
			vo.setItrRateWay("1");
			vo.setItrCalRule("1");
			vo.setCurrCode("CNY");
			String tmpdate="";
			String begDate="";
			List<TbSupPrinPlanNTmpVO> PlanNTmpVO=new ArrayList<TbSupPrinPlanNTmpVO>();
			if("1400".equals(hkfs) ){
				for (int i = 0; i < obj.length; i++) {
				int currPeri=Integer.parseInt((String) obj[i].get("periodsNumber"));
				String hbDate=(((String) obj[i].get("hbDate")).replace("-", "")).substring(0, 8);
				BigDecimal hbDCurPrin=new BigDecimal( (String)obj[i].get("hbDCurPrin"));
					 if(currPeri==1){
						  begDate=startDate;
						tmpdate=hbDate;
						 
					 }else{
						 begDate=tmpdate;
						 tmpdate=hbDate;
						 
					 }
						TbSupPrinPlanNTmpVO planvo = new TbSupPrinPlanNTmpVO();

					 planvo.setLegPerCod("kh01");
					 planvo.setDueNumTmp("01");
					 planvo.setCurrPeri(currPeri);
					 planvo.setBegDate(begDate);
					 planvo.setEndDate(hbDate);
					 planvo.setRcvPrn(hbDCurPrin);
					 planvo.setPrnProcFlg("1");
					 PlanNTmpVO.add(planvo) ;
					 
 					
					
				}
				vo.setTbSupPrinPlanNList(PlanNTmpVO);
			}else {
			vo.setTbSupPrinPlanNList(null);
			}
			params1[1] = vo;
			ILogicComponent logicComponent = LogicComponentFactory
					.create("com.primeton.tsl.TransferDataManager");
			Object[] objs = logicComponent
					.invoke("newDataInsertCheck", params1);
			DataObject vo1 = (DataObject) objs[0];
			BaseVO baseVO1= (BaseVO) vo1.get("baseVO");
			String RpsMsg=baseVO1.getErrMsg();
			String Rpscode=baseVO1.getErrCod();
			if(RpsMsg!=null&& !"200".equals(Rpscode)){
				resultObjects = new DataObject[1];
				DataObject d1=DataObjectUtil.createDataObject("com.bos.pub.meta.TbPubDate");
				d1.set("RpsMsg", RpsMsg);
				resultObjects[0]=d1;
				return resultObjects;
			}
			List<MsgQueryPayPlan> returnList = vo1
					.getList("msgQueryPayPlanList");
			if (null == returnList || 0 == returnList.size()) {
				throw new EOSException("未查询到还款计划");
			}
			resultObjects = new DataObject[returnList.size()];
			for (int i = 0; i < returnList.size(); i++) {
				MsgQueryPayPlan mpp = (MsgQueryPayPlan) returnList.get(i);
				DataObject db = BeanToMapUtil.convertBean(mpp);
				
				
			
				
 				Date sd= DateHelper.getDateYYYYMMDD(db.get("forwProvDate").toString());
 				Date ed= DateHelper.getDateYYYYMMDD(db.get("nextProvDate").toString());
				
 				int ts=DateHelper.getDiffDays(sd, ed);
  				db.set("ts", ts);
				db.set("sybj", bz);
				bz =bz.subtract(db.getBigDecimal("DCurPrin"));
				resultObjects[i] = db;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		return resultObjects;
	}

	/**
	 * 
	 * @Title: dkcz 
	 * @Description:垫款出账
	 * @param contractNum
	 * @param pjhm
	 * @param orgCode
	 * @param dkAmt 垫款金额
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author GIT-LPC
	 * @date 2015年10月28日 下午6:30:08 
	 * @version V1.0
	 */
	@Bizlet("垫款出账")
	public String dkcz(String contractNum, String pjhm, String orgCode,
			Double dkAmt) {
		String result = "出账成功";
		//contractNum = "JK150717000426";//test-------------
		//dueNum = "JJ150718000643";//test--------------
		try {
			/*//合同编号
			DataObject ht = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			ht.set("contractNum", contractNum);
			ht.set("conStatus", "03");
			DatabaseUtil.expandEntityByTemplate("default", ht, ht);
			if(null == ht.get("contractId")){
				throw new EOSException("未找到合同编号："+contractNum+"对应的合同信息");
			}
			String contractId = ht.getString("contractId");*/

			//根据票据号码取借据id
			DataObject pj = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizPjxxApply");
			pj.set("pjhm", pjhm);
			DatabaseUtil.expandEntityByTemplate("default", pj, pj);
			if (null == pj.get("applyDetailId")) {
				throw new EOSException("未找到票据号码：" + pjhm + "对应的票据信息");
			}

			String summaryId = pj.getString("amountDetailId");//票据信息里amountDetailId存的是借据id

			//借据信息
			DataObject summaryInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summaryInfo.set("summaryId", summaryId);
			DatabaseUtil.expandEntity("default", summaryInfo);
			if (null == summaryInfo.get("loanId")) {
				throw new EOSException("未找到票据号码：" + pjhm + "对应的借据信息");
			}
			String loanId = summaryInfo.getString("loanId");
			String summaryNum = summaryInfo.getString("summaryNum");

			/*//出账信息
			DataObject loanInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("contractId", contractId);
			loanInfo.set("loanStatus", "03");
			DatabaseUtil.expandEntityByTemplate("default", loanInfo, loanInfo);
			if(null == loanInfo.get("loanId")){
				throw new EOSException("未找到合同编号："+contractNum+"对应的放款信息");
			}
			String loanId = loanInfo.getString("loanId");*/

			/*//更新借据
			DataObject loanSummary = DataObjectUtil.convertDataObject(loanInfo,
					"com.bos.dataset.pay.TbLoanSummary", true);
			loanSummary.set("applyOrg", loanInfo.get("orgNum"));
			loanSummary.set("userNum", "");
			loanSummary.set("orgNum", "");
			loanSummary.set("createTime", date);
			loanSummary.set("summaryStatusCd", "03");
			loanSummary.set("summaryTerm", (Integer)loanInfo.get("loanTerm"));
			loanSummary.set("summaryAmt", (BigDecimal)loanInfo.get("loanAmt"));
			loanSummary.set("jjye", (BigDecimal)loanInfo.get("loanAmt"));
			loanSummary.set("summaryCurrencyCd", (String)loanInfo.get("currencyCd"));
			loanSummary.set("tingxiStatus","02");
			String nftNo=BizNumGenerator.getBizNum("SEQ_CON_JK");
			nftNo = "JJ"+nftNo;
			loanSummary.set("nftNo", nftNo);
			loanSummary.set("summaryNum", nftNo);
			loanSummary.set("tiexiStatus","02");
			DatabaseUtil.saveEntity("default", loanSummary);*/
			//向计量表插入数据
			//nftNo = "JJ1507170015";//-----------test-------------
			LoanToLcs ll = new LoanToLcs();
			ll.dataInsertCheckForDk(loanId, summaryNum, orgCode, dkAmt);
			//垫款检查
			ll.loanToLcs1ForDk(loanId, summaryNum);
			//二次交易
			ll.loanToLcs2ForDk(loanId, summaryNum);

			/*	//成功后将对应原借据失效
				DataObject loanSummary2 = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
				loanSummary2.set("summaryNum", dueNum);
				DatabaseUtil.expandEntityByTemplate("default", loanSummary2, loanSummary2);
				loanSummary2.set("summaryStatusCd", "06");
				loanSummary2.set("jjye", new BigDecimal("0"));
				DatabaseUtil.updateEntity("default", loanSummary2);*/
		} catch (Exception e) {
			e.printStackTrace();
			result = "出账失败";
		}
		return result;
	}

	@Bizlet("重新排序")
	public String aftDeleteRepayPlan(String loanId) {
		String result = "1";
		try {
			Map argMap = new HashMap();
			argMap.put("loanId", loanId);
			DataObject repayPlan = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanRepayPlan");
			// repayPlan.set("contractId", contractId);
			Object[] repayPlans = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.LoanSubject.getHkjhsByLoanId", argMap);
			for (int i = 0; i < repayPlans.length; i++) {
				repayPlan = (DataObject) repayPlans[i];
				repayPlan.set("periodsNumber", new BigDecimal(i + 1));
				DatabaseUtil.updateEntity("default", repayPlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "2";
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
