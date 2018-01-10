/**
 * 
 */
package com.bos.conPrint;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.example.excel.ChangeUtil;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;

/**
 * @author 钟辉
 * @date 2015-10-01 17:59:34
 *
 */
@Bizlet("")
public class convertPrintData {

	@Bizlet("")
	public DataObject changeDYData(DataObject obj) {
		changeCommonData(obj);
		String productType=obj.getString("PRODUCT_TYPE");
		DataObject con  =null;
		if(null!=productType){
			String entityName="com.bos.pub.product.TbSysProduct";
			con  = DataObjectUtil.createDataObject(entityName);
			con.set("productId", productType);
			DatabaseUtil.expandEntity("default", con);
			obj.set("PRODUCT_TYPE", con.getString("productName"));
		}
		
		String entityName2="com.bos.dataset.grt.TbGrtSortarguments";
		DataObject con2  = DataObjectUtil.createDataObject(entityName2);
		con2.set("sortType", obj.getString("SORT_TYPE"));
		DatabaseUtil.expandEntityByTemplate("default", con2, con2);
		
		
		String arbitrateType=obj.getString("ARBITRATE_TYPE");
		if("01".equals(arbitrateType)){
			arbitrateType="(一)";
		}else if("02".equals(arbitrateType)){
			arbitrateType="(二)";
		}else if("03".equals(arbitrateType)){
			arbitrateType="(三)";
		}
		
		String MARGIN_SORT=obj.getString("MARGIN_SORT");
		if("01".equals(MARGIN_SORT)){
			MARGIN_SORT="(一)";
		}else if("02".equals(MARGIN_SORT)){
			MARGIN_SORT="(二)";
		}else if("03".equals(MARGIN_SORT)){
			MARGIN_SORT="(三)";
		}
		
		obj.set("MARGIN_SORT",MARGIN_SORT);
		
		BigDecimal zgbjxe=obj.getBigDecimal("ZGBJXE");
		String bjxe="";
		if(null!=zgbjxe){
			bjxe=changeMoney(zgbjxe);
		}
		
		BigDecimal amt=obj.getBigDecimal("CONTRACT_AMT");
		String conAmt="";
		if(null!=amt){
			conAmt=changeMoney(amt);
			obj.set("DCONTRACT_AMT",conAmt);
		}
		
		BigDecimal bzjje=obj.getBigDecimal("BZJJE");
		String bzj="";
		if(null!=bzjje){
			bzj=changeMoney(bzjje);
			obj.set("DBZJJE",bzj);
			obj.set("XBZJJE",bzjje);
		}
		
		//转换合同份数
		String totalCount = obj.getString("TOTAL_COUNT");
		if(null!=totalCount&&!"".equals(totalCount)){
			obj.set("TOTAL_COUNT", ChangeUtil.getBigNum(Long.parseLong(totalCount)));
		}
		
		obj.set("SORT_TYPE",con2.getString("sortName"));
		obj.set("ARBITRATE_TYPE",arbitrateType); 
		obj.set("ZGBJXE",bjxe); 
		return obj;
	}
	
	//日期转换  原格式yyyy-MM-dd转yyyy年MM月dd日
	@Bizlet("")
	public String changeDate(String date) {
		String[] array;
		String conDate;
		if ("" != date && null != date) {
			array = date.split("-");
			conDate = array[0] + "年" + array[1] + "月" + array[2] + "日";
			return conDate;
		}
		return null;
	}
	
	//小写货币转大写汉字
	@Bizlet("")
	public String changeMoney(BigDecimal money) {
		return NumberToCN.number2CNMontrayUnit(money);
	}
	
	public String changeSmailAmt(Double amt){
		DecimalFormat df=new DecimalFormat("￥###,###.##");
		String smailAmt=df.format(amt);
		return smailAmt;
	}
	
	//主合同判断个人单词、个人循环、流动单词、流动循环
	@Bizlet("")
	public String getDocx(DataObject obj) {
		
		String productType=obj.getString("PRODUCT_TYPE");
		String cycle=obj.getString("CYCLE_IND_CON");
		
		/*//个人
		if(productType.startsWith("02")){			//个人暂时没上
			if(cycle.equals("0")){  //否
				obj.set("CONTRACT_NAME", "借款合同");
				return "/contract/JKGRDC.docx";
			}else if(cycle.equals("1")){ //是
				obj.set("CONTRACT_NAME", "借款合同");
				return "/contract/JKGRXH.docx";
			}
		}*/
		
		//小贷
		if(productType.startsWith("03")){	
			Map map=new HashMap();
			
			//小贷共同借款人信息
			map.put("contractId", obj.getString("CONTRACT_ID"));
			Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getXDPARTY", map);
			if(objs.length>0){
				DataObject con = (DataObject) objs[0];
				
				obj.set("COMPARTY_NAME", con.getString("COMPARTY_NAME"));
				
				String CERT_TYPE=con.getString("COMCERT_TYPE");
				if(null!=CERT_TYPE){
					CERT_TYPE = BusinessDictUtil.getDictName("CDKH0002",CERT_TYPE);
				}
				
				obj.set("COMCERT_TYPE", CERT_TYPE);
				obj.set("COMCERT_NUM", con.getString("COMCERT_NUM"));
				
				String entityName3="com.bos.dataset.csm.TbCsmNaturalPerson";
				DataObject con3  = DataObjectUtil.createDataObject(entityName3);
				con3.set("partyId", con.getString("PARTY_ID"));
				DatabaseUtil.expandEntity("default", con3);
				
				obj.set("COMADDRESS", con3.getString("familyAddress"));
				obj.set("COMPHONE", con3.getString("phoneNumber"));
			}
			
			//小贷借款人信息
			String partyId=obj.getString("PARTY_ID");
			String entityName3="com.bos.dataset.csm.TbCsmNaturalPerson";
			DataObject con3  = DataObjectUtil.createDataObject(entityName3);
			con3.set("partyId", partyId);
			DatabaseUtil.expandEntity("default", con3);
			
			String CERT_TYPE=obj.getString("CERT_TYPE");
			if(null!=CERT_TYPE){
				CERT_TYPE = BusinessDictUtil.getDictName("CDKH0002",CERT_TYPE);
			}
			
			obj.set("CERT_TYPE", CERT_TYPE);
			obj.set("ADDRESS", con3.getString("familyAddress"));
			obj.set("PHONE", con3.getString("phoneNumber"));
			
			BigDecimal amt=obj.getBigDecimal("CONTRACT_AMT");
			String DCONTRACT_AMT=changeMoney(amt);
			
			obj.set("DCONTRACT_AMT", DCONTRACT_AMT);
			obj.set("SCONTRACT_AMT", changeSmailAmt(obj.getDouble("CONTRACT_AMT")));
			
			/*后面有统一处理过程
			 * String entityName="com.bos.pub.product.TbSysProduct";
			DataObject con  = DataObjectUtil.createDataObject(entityName);
			con.set("productId", productType);
			DatabaseUtil.expandEntity("default", con);
			obj.set("PRODUCT_TYPE", con.getString("productName"));*/
			
			Object[] objZH = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getBHZH", map);
			if(objZH.length!=0){
				DataObject conZH = (DataObject) objZH[0];
				obj.set("ZH", conZH.getString("ZH"));
			}
			
			Object[] objInfo = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getXDInfo", map);
			if(null == ((DataObject)objInfo[0]).get("PAY_WAY")){
				objInfo = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getGDInfo", map);
			}
			if(objInfo.length!=0){
				DataObject conInfo = (DataObject) objInfo[0];
				obj.set("PAY_WAY", conInfo.getString("PAY_WAY"));
				obj.set("CAPITAL_PAY_CONDITION1", conInfo.getString("CAPITAL_PAY_CONDITION1"));
				obj.set("CAPITAL_PAY_CONDITION2", conInfo.getString("CAPITAL_PAY_CONDITION2"));
				obj.set("CAPITAL_PAY_CONDITION3", conInfo.getString("CAPITAL_PAY_CONDITION3"));
				obj.set("HOLIDAY_INT_FLG", conInfo.getString("HOLIDAY_INT_FLG"));
			}
			
			
			String FLOAT_WAY=obj.getString("FLOAT_WAY");
			if(("2").equals(FLOAT_WAY)){
				String downFloat=obj.getString("RATE_FLOAT_PROPORTION");
				obj.set("downFloat", downFloat);
			}else if(("1").equals(FLOAT_WAY)){
				String upFloat=obj.getString("RATE_FLOAT_PROPORTION");
				obj.set("upFloat", upFloat);
			}
			
			if(cycle.equals("0")){  //否
				obj.set("CONTRACT_NAME", "借款合同");
				return "/contract/XDGRDC.docx";
			}else if(cycle.equals("1")){ //是
				obj.set("CONTRACT_NAME", "借款合同");
				return "/contract/XDGRXH.docx";
			}
		}
		//流动资金
		if(productType.startsWith("01001")){
			Map map=new HashMap();
			map.put("contractId", obj.getString("CONTRACT_ID"));
			Object[] objsFDLL = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getGDZCFDLL", map);
			DataObject conFDLL = (DataObject) objsFDLL[0];
			
			String FLOAT_WAY=conFDLL.getString("FLOAT_WAY");
			if(("2").equals(FLOAT_WAY)){
				String downFloat=conFDLL.getString("RATE_FLOAT_PROPORTION");
				obj.set("downFloat", downFloat);
			}else if(("1").equals(FLOAT_WAY)){
				String upFloat=conFDLL.getString("RATE_FLOAT_PROPORTION");
				obj.set("upFloat", upFloat);
			}
			
			Object[] objZH = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getBHZH", map);
			if(objZH.length!=0){
				DataObject conZH = (DataObject) objZH[0];
				obj.set("ZH", conZH.getString("ZH"));
			}
			
			
			if(cycle.equals("0")){  //否
				obj.set("CONTRACT_NAME", "借款合同");
				return "/contract/JKLDDC.docx";
			}else if(cycle.equals("1")){ //是
				obj.set("CONTRACT_NAME", "借款合同");
				return "/contract/JKLDXH.docx";
			}
		}
		//固定资产贷款
		if(productType.startsWith("01003")||productType.startsWith("01002")||productType.startsWith("01005")){
			Map map=new HashMap();
			map.put("contractId", obj.getString("CONTRACT_ID"));
			Object[] objsFDLL = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getGDZCFDLL", map);
			DataObject conFDLL = (DataObject) objsFDLL[0];
			String FLOAT_WAY=conFDLL.getString("FLOAT_WAY");
			if(("2").equals(FLOAT_WAY)){
				String downFloat=conFDLL.getString("RATE_FLOAT_PROPORTION");
				obj.set("downFloat", downFloat);
			}else if(("1").equals(FLOAT_WAY)){
				String upFloat=conFDLL.getString("RATE_FLOAT_PROPORTION");
				obj.set("upFloat", upFloat);
			}
			
			map.put("subContractNum", obj.getString("CONTRACT_NUM"));
			Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getGDZC", map);
			if(objs==null || objs.length==0){
				return "/contract/GDZCJKHT.docx";
			}
			DataObject con = (DataObject) objs[0];
			obj.set("PAY_WAY", con.getString("PAY_WAY"));
			obj.set("WYBCBL", con.getString("WYBCBL"));
			obj.set("FINANCIAL_LIMIT", con.getString("FINANCIAL_LIMIT"));
			obj.set("OTHER_CONDITION1", con.getString("OTHER_CONDITION1"));
			obj.set("OTHER_CONDITION2", con.getString("OTHER_CONDITION2"));
			obj.set("OTHER_CONDITION3", con.getString("OTHER_CONDITION3"));
			obj.set("OTHER_CONDITION4", con.getString("OTHER_CONDITION4"));
			obj.set("PAY_DEPEND1", con.getString("PAY_DEPEND1"));
			obj.set("PAY_DEPEND2", con.getString("PAY_DEPEND2"));
			obj.set("PAY_DEPEND3", con.getString("PAY_DEPEND3"));
			obj.set("TOTAL_DEBT", con.getString("TOTAL_DEBT"));
			obj.set("THIRD_GUARANT", con.getString("THIRD_GUARANT"));
			obj.set("THIRD_LOAN_AMOUNT", con.getString("THIRD_LOAN_AMOUNT"));
			obj.set("THIRD_BORROW_AMOUNT", con.getString("THIRD_BORROW_AMOUNT"));
			obj.set("STOCK_CHANGE", con.getString("STOCK_CHANGE"));
			obj.set("CONTRACT_NAME", "固定资产借款合同");
			return "/contract/GDZCJKHT.docx";
		}
		
		//开立保函
		if(productType.startsWith("01009")){
			Map map=new HashMap();
			map.put("subContractNum", obj.getString("CONTRACT_NUM"));
			Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getBHXY", map);
			DataObject con = (DataObject) objs[0];
			obj.set("SYR", con.getString("SYR"));
			obj.set("WYJBL", con.getString("WYJBL"));
			
			map.put("contractId", obj.getString("CONTRACT_ID"));
			Object[] objCHT = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getBHCHT", map);
			DataObject conCHT = (DataObject) objCHT[0];
			obj.set("SUBCONTRACT_NUM", conCHT.getString("SUBCONTRACT_NUM"));
			
			Object[] objFL = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getBHFL", map);
			if(objFL.length!=0){
				DataObject conFL = (DataObject) objFL[0];
				obj.set("SFBL", conFL.getBigDecimal("SFBL"));
			}
			
			Object[] objZH = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getBHZH", map);
			DataObject conZH = (DataObject) objZH[0];
			obj.set("ZH", conZH.getString("ZH"));
			
			BigDecimal BZJJE=con.getBigDecimal("BZJJE");
			String bzjje=changeMoney(BZJJE);
			obj.set("BZJJE", bzjje);
			
			String bhBeginDate=obj.getString("BHBEGIN_DATE");
			bhBeginDate=changeDate(bhBeginDate);
			
			String bhEndDate=obj.getString("BHEND_DATE");
			bhEndDate=changeDate(bhEndDate);
			
			obj.set("BHBEGIN_DATE", bhBeginDate);
			obj.set("BHEND_DATE", bhEndDate);
			obj.set("DKLL", con.getString("DKLL"));
			obj.set("CONTRACT_NAME", "开立保函协议");
			return "/contract/KLBHXY.docx";
		}
		//商业汇票贴现 
		if(productType.startsWith("01006")){
			Map map=new HashMap();
			map.put("subContractNum", obj.getString("CONTRACT_NUM"));
			map.put("contractId", obj.getString("CONTRACT_ID"));
			Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getSYHP", map);
			DataObject con = (DataObject) objs[0];
			
			BigDecimal amt=con.getBigDecimal("HP_TOTAL_AMT");
			if(null!=amt){
				String totalAmt=changeMoney(amt);
				obj.set("HP_TOTAL_AMT", totalAmt);
			}
			
			String txqq=con.getString("TXQQ");
			if(null!=txqq){
				String qq=changeDate(txqq);
				obj.set("TXQQ", qq);
			}
			
			Object[] objsHPZH = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getZHFK", map);
			DataObject conHPZH = (DataObject) objsHPZH[0];
			if(null!=conHPZH.getString("ZH")){
				obj.set("ZH", conHPZH.getString("ZH"));
				
				String orgCode=conHPZH.getString("ZHKHJG");
				Map map1=new HashMap();
				map1.put("orgCode",orgCode);
				Object[] orgS = DatabaseExt.queryByNamedSql("default","com.bos.pay.getLoanNoticeInfo.getOrg", map1);
				DataObject con4 = (DataObject) orgS[0];
				obj.set("ZHKHJG", con4.getString("ORGNAME"));
			}
			
			obj.set("HPZS", con.getString("HPZS"));
			obj.set("HPBH", con.getString("HPBH"));
			obj.set("TXYLL", con.getString("TXYLL"));
			obj.set("DKLL", con.getString("DKLL"));
			obj.set("TXZJYT", con.getString("TXZJYT"));
			obj.set("CONTRACT_NAME", "商业汇票贴现合同");
			return "/contract/SYHPTXHT.docx";
		}
		//国内信用证
		if(productType.equals("01010001")){
			Map map=new HashMap();
			map.put("subContractNum", obj.getString("CONTRACT_NUM"));
			Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getXYZ", map);
			DataObject con = (DataObject) objs[0];
			
			map.put("contractId", obj.getString("CONTRACT_ID"));
			Object[] objsYC = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getBHCHT", map);
			DataObject conYC = (DataObject) objsYC[0];
			if(null!=conYC.getString("SUBCONTRACT_NUM")){
				obj.set("SUBCONTRACT_NUM", conYC.getString("SUBCONTRACT_NUM"));
			}
			
			Object[] objsYCNum = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getZHTType", map);
			DataObject conYCNum = (DataObject) objsYCNum[0];
			if(null!=conYCNum.getString("SUBCONTRACT_TYPE")){
				obj.set("SUBCONTRACT_TYPE", conYCNum.getString("SUBCONTRACT_TYPE"));
			}
			
			obj.set("BZJBLBDY", con.getString("BZJBLBDY"));
			obj.set("DKLL", con.getString("DKLL"));
			obj.set("BZJBL", con.getString("BZJBL"));
			obj.set("BZJ", con.getString("BZJ"));
			obj.set("CONTRACT_NAME", "国内信用证开证合同");
			return "/contract/GNXYZKZHT.docx";
		}
		//银承
		if(productType.equals("01008001")||productType.equals("01008002") ||productType.equals("01008010")){
			Map map=new HashMap();
			map.put("subContractNum", obj.getString("CONTRACT_NUM"));
			Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getYC", map);
			DataObject con = (DataObject) objs[0];
			
			
			map.put("contractId", obj.getString("CONTRACT_ID"));
			Object[] objsYC = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getBHCHT", map);
			DataObject conYC = (DataObject) objsYC[0];
			if(null!=conYC.getString("SUBCONTRACT_NUM")){
				obj.set("SUBCONTRACT_NUM", conYC.getString("SUBCONTRACT_NUM"));
			}
			
			Object[] objsYCNum = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getZHTType", map);
			DataObject conYCNum = (DataObject) objsYCNum[0];
			if(null!=conYCNum.getString("SUBCONTRACT_TYPE")){
				obj.set("SUBCONTRACT_TYPE", conYCNum.getString("SUBCONTRACT_TYPE"));
			}
			
			Object[] objsYCZH = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getZHJS", map);
			DataObject conYCZH = (DataObject) objsYCZH[0];
			if(null!=conYCZH.getString("ZH")){
				obj.set("ZH", conYCZH.getString("ZH"));
				
				String orgCode=conYCZH.getString("ZHKHJG");
				System.out.println(orgCode);
				if(null!=orgCode){
					Map map1=new HashMap();
					map1.put("orgCode",orgCode);
					Object[] orgS = DatabaseExt.queryByNamedSql("default","com.bos.pay.getLoanNoticeInfo.getOrg", map1);
					DataObject con4 = (DataObject) orgS[0];
					obj.set("ZHKHJG", con4.getString("ORGNAME"));
				}
			}
			
			
			obj.set("TGFPDCLQX", con.getString("TGFPDCLQX"));
			obj.set("BZJBL", con.getString("BZJBL"));
			obj.set("SFBZ", con.getString("SFBZ"));
			obj.set("DKLL", con.getString("DKLL"));
			
			BigDecimal BZJJE=con.getBigDecimal("BZJJE");
			if(BZJJE!=null){
				String bzjje=changeMoney(BZJJE);
				obj.set("BZJJE", bzjje);
			}
			if(cycle.equals("0")){  //否
				obj.set("CONTRACT_NAME", "银行承兑协议");
				return "/contract/YHCDXYDC.docx";
			}else if(cycle.equals("1")){ //是
				obj.set("CONTRACT_NAME", "银行承兑协议");
				return "/contract/YHCDXYXH.docx";
			}
		}
		return null;
	}
	
	
	//转换综合授信协议数据
		@Bizlet("")
		public String getZHSXDataInfo(Object[] obj){
			
			Object[] dbs=obj;
			
			for(int i=0;i<dbs.length;i++){
				DataObject db=(DataObject)dbs[i];
				String productType=db.getString("PRODUCT_TYPE");
				
				String entityName="com.bos.pub.product.TbSysProduct";
				DataObject con  = DataObjectUtil.createDataObject(entityName);
				con.set("productId", productType);
				DatabaseUtil.expandEntity("default", con);
				db.set("PRODUCT_TYPE", con.getString("productName"));
				
				String CURRENCY_CD=db.getString("CURRENCY_CD");
				if(null!=CURRENCY_CD){
					CURRENCY_CD = BusinessDictUtil.getDictName("CD000001",CURRENCY_CD);
				}
				db.set("CURRENCY_CD", CURRENCY_CD);
			}
			
			return "";
		}
	
	
	//转换综合授信协议数据
	@Bizlet("")
	public String getZHSXData(DataObject obj){
		changeCommonData(obj);
		
		
		String partyId=obj.getString("PARTY_ID");
		//获取抵质押人法定代表人、联系电话、传真、通讯地址
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.csm.corporation.corporation");
		Object[] params = new Object[1];
		params[0] = partyId;
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("getCorpration", params);
		} catch (Throwable e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		DataObject TbCsmCorporation=  (DataObject) objs[0];
		String LEGAL_NAME=TbCsmCorporation.getString("legalName");
		
		String entityName3="com.bos.dataset.csm.TbCsmAttachedInfo";
		DataObject con3  = DataObjectUtil.createDataObject(entityName3);
		con3.set("partyId", partyId);
		DatabaseUtil.expandEntityByTemplate("default", con3,con3);

		
		BigDecimal amt=obj.getBigDecimal("CONTRACT_AMT");
		if(null!=amt){
			String contractAmt=changeMoney(amt);
			obj.set("CONTRACT_AMT",contractAmt);
		}
		
		String rateType=obj.getString("RATE_TYPE");
		if(null!=rateType){
			if(rateType.equals("1")){
				rateType="(一)";
			}else if(rateType.equals("2")){
				rateType="(二)";
			}
		}
		
		String ir=obj.getString("IR_UPDATE_FREQUENCY");
		if(null!=ir){
			if(ir.equals("01")){
				ir="1";
			}else if(ir.equals("02")){
				ir="2";
			}else if(ir.equals("03")){
				ir="3";
			}else if(ir.equals("04")){
				ir="4";
			}
		}
		
		String in=obj.getString("INTEREST_COLLECT_TYPE");
		if(null!=in){
			if(in.equals("1")){
				in="(一)";
			}else if(in.equals("2")){
				in="(二)";
			}else if(in.equals("3")){
				in="(三)";
			}else if(in.equals("4")){
				in="(四)";
			}
		}
		
		String arbitrateType=obj.getString("ARBITRATE_TYPE");
		if(null!=arbitrateType){
			if(arbitrateType.equals("01")){
				arbitrateType="(一)";
			}else if(arbitrateType.equals("02")){
				arbitrateType="(二)";
			}else if(arbitrateType.equals("03")){
				arbitrateType="(三)";
			}
		}
		
		//支付方式
		String repayment=obj.getString("REPAYMENT_TYPE");
		if(null!=repayment){
			repayment = BusinessDictUtil.getDictName("XD_SXCD1162",repayment);
		}
		
		String holiday=obj.getString("HOLIDAY_INT_FLG");
		if(null!=holiday){
			if(holiday.equals("1")){
				holiday="①";
			}else if(holiday.equals("2")){
				holiday="②";
			}
		}
		//转换合同份数
		String totalCount = obj.getString("TOTAL_COUNT");
		if(null!=totalCount&&!"".equals(totalCount)){
			obj.set("TOTAL_COUNT", ChangeUtil.getBigNum(Long.parseLong(totalCount)));
		}
		
		String beginDate=obj.getString("BEGIN_DATE");
		beginDate=changeDate(beginDate);
		
		String endDate=obj.getString("END_DATE");
		endDate=changeDate(endDate);
		
		Map map=new HashMap();
		map.put("subContractNum", obj.getString("CONTRACT_NUM"));
		Object[] orgS = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.printZHSXJE", map);
		DataObject con4 = (DataObject) orgS[0];
		
		String currencyCd=con4.getString("CURRENCY_CD");
		
		if(null!=currencyCd){
			currencyCd = BusinessDictUtil.getDictName("CD000001",currencyCd);
		}
		
		
		BigDecimal amount=con4.getBigDecimal("CREDIT_AMOUNT");
		String DCREDIT_AMOUNT=changeMoney(amount);
	/*	
		DecimalFormat df=new DecimalFormat("￥###,###.##");
		String smailAmt=df.format(con4.getBigDecimal("CREDIT_AMOUNT"));
		*/
		obj.set("CURRENCY_CD",currencyCd);
		obj.set("DCREDIT_AMOUNT",DCREDIT_AMOUNT);
		obj.set("XCREDIT_AMOUNT",con4.getBigDecimal("CREDIT_AMOUNT"));
		
		
		obj.set("BEGIN_DATE",beginDate);
		obj.set("END_DATE",endDate);
		obj.set("JLEGAL_NAME",LEGAL_NAME);
		obj.set("JPHONE",con3.getString("accountContactsPhone"));
		obj.set("JTELEPHONE",con3.getString("telephone"));
		obj.set("JEMAIL",con3.getString("email"));
		obj.set("JFAX",con3.getString("fax"));
		obj.set("JZIP_NUM",con3.getString("zipNum"));
		obj.set("JADDRESS",con3.getString("addressValue"));
		obj.set("RATE_TYPE",rateType);
		obj.set("IR_UPDATE_FREQUENCY",ir);
		obj.set("INTEREST_COLLECT_TYPE",in);
		obj.set("ARBITRATE_TYPE",arbitrateType);
		obj.set("REPAYMENT_TYPE",repayment);
		obj.set("HOLIDAY_INT_FLG",holiday);
		
		return "/contract/ZHSXHT.docx";
	}
	
	//转换主合同数据
	@Bizlet("")
	public DataObject changeZHTData(DataObject obj,String docxTemplate) {
		
		
		getAddrs(obj);   //设置送达地址
		
		String partyId=obj.getString("PARTY_ID");
		//获取抵质押人法定代表人、联系电话、传真、通讯地址
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.csm.corporation.corporation");
		Object[] params = new Object[1];
		params[0] = partyId;
		Object[] objs = null;
		try {
			objs = logicComponent.invoke("getCorpration", params);
		} catch (Throwable e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		DataObject TbCsmCorporation=  (DataObject) objs[0];
		String LEGAL_NAME=TbCsmCorporation.getString("legalName");
		
		String entityName3="com.bos.dataset.csm.TbCsmAttachedInfo";
		DataObject con3  = DataObjectUtil.createDataObject(entityName3);
		con3.set("partyId", partyId);
		DatabaseUtil.expandEntityByTemplate("default", con3,con3);
		
		Map map=new HashMap();
		map.put("orgCode", obj.getString("ORG_NUM"));
		Object[] orgS = DatabaseExt.queryByNamedSql("default","com.bos.pay.getLoanNoticeInfo.getOrg", map);
		DataObject con4 = (DataObject) orgS[0];
		
		BigDecimal amt=obj.getBigDecimal("CONTRACT_AMT");
		if(null!=amt){
			String contractAmt=changeMoney(amt);
			obj.set("CONTRACT_AMT",contractAmt);
		}
		
		String rateType=obj.getString("RATE_TYPE");
		if(null!=rateType){
			if(rateType.equals("1")){
				rateType="(一)";
			}else if(rateType.equals("2")){
				rateType="(二)";
				obj.set("YEAR_RATE", "");
			}
		}
		
		String ir=obj.getString("IR_UPDATE_FREQUENCY");
		if(null!=ir){
			if(ir.equals("01")){
				ir="1";
			}else if(ir.equals("02")){
				ir="2";
			}else if(ir.equals("03")){
				ir="3";
			}else if(ir.equals("04")){
				ir="4";
			}
		}
		
		String in=obj.getString("INTEREST_COLLECT_TYPE");
		if(null!=in){
			if(in.equals("1")){
				in="(一)";
				obj.set("MSPEC_PAYMENT_DATE",obj.getString("SPEC_PAYMENT_DATE"));
			}else if(in.equals("2")){
				in="(二)";
				obj.set("JSPEC_PAYMENT_DATE",obj.getString("SPEC_PAYMENT_DATE"));
			}else if(in.equals("3")){
				in="(三)";
			}else if(in.equals("4")){
				in="(四)";
			}else if(in.equals("5")){
				if(docxTemplate.equals("/contract/GDZCJKHT.docx")){
					in="(五)";
				}else{
					in="(三)";
				}
			}else if(in.equals("6")){
				if(docxTemplate.equals("/contract/GDZCJKHT.docx")){
					in="(六)";
				}else{
					in="(四)";
				}
			}
		}
		
		String arbitrateType=obj.getString("ARBITRATE_TYPE");
		if(null!=arbitrateType){
			if(arbitrateType.equals("01")){
				arbitrateType="(一)";
			}else if(arbitrateType.equals("02")){
				arbitrateType="(二)";
			}else if(arbitrateType.equals("03")){
				arbitrateType="(三)";
			}
		}
		
		//支付方式
		String repayment=obj.getString("REPAYMENT_TYPE");
		if(null!=repayment){
			repayment = BusinessDictUtil.getDictName("XD_SXCD1162",repayment);
		}
		
		//转换合同份数
		String totalCount = obj.getString("TOTAL_COUNT");
		if(null!=totalCount&&!"".equals(totalCount)){
			obj.set("TOTAL_COUNT", ChangeUtil.getBigNum(Long.parseLong(totalCount)));
		}
		
		String holiday=obj.getString("HOLIDAY_INT_FLG");
		if(null!=holiday){
			if(holiday.equals("1")){
				holiday="①";
			}else if(holiday.equals("2")){
				holiday="②";
			}
		}
		
		String beginDate=obj.getString("BEGIN_DATE");
		beginDate=changeDate(beginDate);
		
		String endDate=obj.getString("END_DATE");
		endDate=changeDate(endDate);
		
		String productType=obj.getString("PRODUCT_TYPE");
		DataObject con  =null;
		if(null!=productType){
			String entityName="com.bos.pub.product.TbSysProduct";
			con  = DataObjectUtil.createDataObject(entityName);
			con.set("productId", productType);
			DatabaseUtil.expandEntity("default", con);
			obj.set("PRODUCT_TYPE", con.getString("productName"));
		}
		
		obj.set("BEGIN_DATE",beginDate);
		obj.set("END_DATE",endDate);
		obj.set("JLEGAL_NAME",LEGAL_NAME);
		obj.set("JPHONE",con3.getString("accountContactsPhone"));
		obj.set("JTELEPHONE",con3.getString("telephone"));
		obj.set("JEMAIL",con3.getString("email"));
		obj.set("JFAX",con3.getString("fax"));
		obj.set("JZIP_NUM",con3.getString("zipNum"));
		obj.set("JADDRESS",con3.getString("addressValue"));
		obj.set("ORG_NUM", con4.getString("ORGNAME"));
		obj.set("ORGMANAGER",con4.getString("ORGMANAGER"));
		obj.set("ORGADDR",con4.getString("ORGADDR"));
		obj.set("ZIPCODE",con4.getString("ZIPCODE"));
		obj.set("LINKTEL",con4.getString("LINKTEL"));
		obj.set("RATE_TYPE",rateType);
		obj.set("IR_UPDATE_FREQUENCY",ir);
		obj.set("INTEREST_COLLECT_TYPE",in);
		obj.set("ARBITRATE_TYPE",arbitrateType);
		obj.set("REPAYMENT_TYPE",repayment);
		obj.set("HOLIDAY_INT_FLG",holiday);
		
		return obj;
	}
	
	
	//转换保证合同数据
	@Bizlet("")
	public DataObject changeBZHTData(DataObject obj) {
		changeCommonData(obj);
		
		DataObject con =null;
		if(null!=obj.getString("PRODUCT_TYPE")){
			String entityName="com.bos.pub.product.TbSysProduct";
			con  = DataObjectUtil.createDataObject(entityName);
			con.set("productId", obj.getString("PRODUCT_TYPE"));
			DatabaseUtil.expandEntity("default", con);
			obj.set("PRODUCT_TYPE", con.getString("productName"));
			
			
				
			String productName=con.getString("productName");
			
			if(productName.contains("银行承兑汇票")){
				productName="银行承兑协议";
			}else if(productName.contains("固定资产")){
				productName="固定资产借款合同";
			}else if(productName.contains("流动资金")){
				productName="借款合同";
			}else if(productName.contains("汇票贴现")){
				productName="商业汇票贴现合同";
			}else if(productName.contains("保函")){
				productName="开立保函协议";
			}else if(productName.contains("信用证")){
				productName="国内信用证开证合同";
			}else{
				productName="借款合同";
			}
			obj.set("CONTRACT_NAME", productName);
			
		}
		
		String beginDate=obj.getString("BEGIN_DATE");
		beginDate=changeDate(beginDate);
		
		String endDate=obj.getString("END_DATE");
		endDate=changeDate(endDate);
		
		BigDecimal zgbjxe=obj.getBigDecimal("ZGBJXE");
		String bjxe="";
		if(null!=zgbjxe){
			bjxe=changeMoney(zgbjxe);
		}
		
		String arbitrateType=obj.getString("ARBITRATE_TYPE");
		if(arbitrateType!=null){
			if(arbitrateType.equals("01")){
				arbitrateType="(一)";
			}else if(arbitrateType.equals("02")){
				arbitrateType="(二)";
			}else if(arbitrateType.equals("03")){
				arbitrateType="(三)";
			}
		}
		//转换合同份数
		String totalCount = obj.getString("TOTAL_COUNT");
		if(null!=totalCount&&!"".equals(totalCount)){
			obj.set("TOTAL_COUNT", ChangeUtil.getBigNum(Long.parseLong(totalCount)));
		}
		
		obj.set("BEGIN_DATE",beginDate);
		obj.set("END_DATE",endDate);
		obj.set("ZGBJXE",bjxe); 
		obj.set("ARBITRATE_TYPE",arbitrateType);
		return obj;
	}
	
	public String getUnit(int index,int len){
		String unit = "";
		switch(len){
			case 2:switch(index){case 1:unit="十";}break;
			case 3:switch(index){case 1:unit="百";case 2:unit="十";}break;
			case 4:switch(index){case 1:unit="千";case 2:unit="百";case 3:unit="十";}break;
		}
		return unit;
		
	}
	
	public String getBigNum(String numChar){
		String reNumChar = "";
		if("0".equals(numChar)){
			reNumChar = "零";
		}
		if("1".equals(numChar)){
			reNumChar = "壹";	
		}
		if("2".equals(numChar)){
			reNumChar = "贰";
		}
		if("3".equals(numChar)){
			reNumChar = "叁";
		}
		if("4".equals(numChar)){
			reNumChar = "肆";
		}
		if("5".equals(numChar)){
			reNumChar = "伍";
		}
		if("6".equals(numChar)){
			reNumChar = "陆";
		}
		if("7".equals(numChar)){
			reNumChar = "柒";
		}
		if("8".equals(numChar)){
			reNumChar = "捌";
		}
		if("9".equals(numChar)){
			reNumChar = "玖";
		}
		return reNumChar;
	}
	
	//初始化从合同抵押、质押、保证、保证金、的借款人、债权人信息
	@Bizlet("")
	public DataObject changeCommonData(DataObject obj) {
		if(null!=obj){
			String CERT_TYPE=obj.getString("CERT_TYPE");
			String certType="";
			String currencyCd="";
			
			if(null!=CERT_TYPE){
				certType = BusinessDictUtil.getDictName("CDKH0002",CERT_TYPE);
			}
			
			String CURRENCY_CD=obj.getString("CURRENCY_CD");
			if(null!=CURRENCY_CD){
				currencyCd = BusinessDictUtil.getDictName("CD000001",CURRENCY_CD);
			}
			
			//获取抵质押人法定代表人、联系电话、传真、通讯地址
			String partyId=obj.getString("PARTY_ID");
			ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.csm.corporation.corporation");
			Object[] params = new Object[1];
			params[0] = partyId;
			Object[] objs = null;
			try {
				objs = logicComponent.invoke("getCorpration", params);
			} catch (Throwable e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			DataObject TbCsmCorporation=  (DataObject) objs[0];
			
			String LEGAL_NAME=TbCsmCorporation.getString("legalName");
			
			String entityName3="com.bos.dataset.csm.TbCsmAttachedInfo";
			DataObject con3  = DataObjectUtil.createDataObject(entityName3);
			con3.set("partyId", partyId);
			DatabaseUtil.expandEntityByTemplate("default", con3,con3);
			
			
			Map map=new HashMap();
			map.put("orgCode", obj.getString("ORG_NUM"));
			Object[] orgS = DatabaseExt.queryByNamedSql("default","com.bos.pay.getLoanNoticeInfo.getOrg", map);
			DataObject con4 = (DataObject) orgS[0];
			
			String subBeginDate=obj.getString("SUBBEGIN_DATE");
			subBeginDate=changeDate(subBeginDate);
			
			String subEndDate=obj.getString("SUBEND_DATE");
			subEndDate=changeDate(subEndDate);
			
			obj.set("LEGAL_NAME",LEGAL_NAME);							//对公法人代表                                                                       --------------
			obj.set("PHONE",con3.getString("accountContactsPhone"));	//联系电话                                                                       --------------
			obj.set("TELEPHONE",con3.getString("telephone"));			//其他联系方式                                                                       --------------
			obj.set("ZIP_NUM",con3.getString("zipNum"));			    //邮编                                                                       --------------
			obj.set("EMAIL",con3.getString("email"));					//邮箱                                                                       --------------
			obj.set("FAX",con3.getString("fax"));						//传真
			obj.set("ADDRESS",con3.getString("addressValue"));			//地址                                                                       --------------
			
			obj.set("ORG_NUM", con4.getString("ORGNAME"));				//                      --------------
			obj.set("JORGMANAGER",con4.getString("ORGMANAGER"));		
			obj.set("JORGADDR",con4.getString("ORGADDR"));				//                      --------------
			obj.set("JZIPCODE",con4.getString("ZIPCODE"));				//                      --------------
			obj.set("JLINKTEL",con4.getString("LINKTEL"));				//                      --------------
			
			obj.set("SUBBEGIN_DATE",subBeginDate);
			obj.set("SUBEND_DATE",subEndDate);
			
			obj.set("CERT_TYPE", certType);				//证件类型
			obj.set("CURRENCY_CD", currencyCd);
			
			//如果是个人客户，替换送达地址和电话
			DataObject grObj  = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmNaturalPerson");
			grObj.set("partyId",partyId);
			DatabaseUtil.expandEntity("default", grObj);
			if(null != grObj.get("partyName")){
				obj.set("PHONE",grObj.getString("phoneNumber"));
				obj.set("ADDRESS",grObj.getString("familyAddress"));
			}
			return obj;
		}
		return null;
	}
	
	
	private DataObject getAddrs(DataObject obj){
		//而且合同打印新增甲乙地址信息,需要查出该信息 重新给合同模板赋值
		Map map=new HashMap();
		String contractNum=obj.getString("CONTRACT_NUM");
		
		if(null!=contractNum||"null".equals(contractNum)){
			map.put("contractNum", contractNum);
		}else{			//因为借款合同存在的情况下,担保合同编号没有必要去查 只需要用借款合同编号查询就可以了
			map.put("subContractNum", obj.getString("SUBCONTRACT_NUM"));
		}
		Object[] objAddrs = DatabaseExt.queryByNamedSql("default","com.bos.conApply.contractPrint.getAddrs", map);
		if(objAddrs.length==0){
			return obj;
		}
		DataObject con5 = (DataObject) objAddrs[0];
		obj.set("A_SEND_ADDR",con5.getString("A_SEND_ADDR"));		//甲方送达地址
		obj.set("A_POSTCODE",con5.getString("A_POSTCODE"));		//甲方邮编
		obj.set("A_RECEIVER",con5.getString("A_RECEIVER"));		//甲方收件人
		obj.set("A_PHONE",con5.getString("A_PHONE"));		//甲方电话
		obj.set("A_EMAIL",con5.getString("A_EMAIL"));		//甲方电子邮箱
		obj.set("A_OTHER",con5.getString("A_OTHER"));		//甲方其他方式
		obj.set("B_SEND_ADDR",con5.getString("B_SEND_ADDR"));		//乙方送达地址
		obj.set("B_POSTCODE",con5.getString("B_POSTCODE"));		//乙方邮编
		obj.set("B_RECEIVER",con5.getString("B_RECEIVER"));		//乙方收件人
		obj.set("B_PHONE",con5.getString("B_PHONE"));		//乙方电话
		
		return obj;
	}
}
