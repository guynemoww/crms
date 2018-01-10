/**
 * 
 */
package com.bos.csm.mtmq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.grt.manage.CheckManage;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.EsbSocketService;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 钟辉
 * @date 2016-03-03 09:45:07
 * 
 */
@Bizlet("")
public class addMortgage {

	/**
	 * 根据接受到的报文信息 新增押品主表及从表数据(暂未对从表数据有效性做校验)
	 * 
	 * @param object
	 *            报文数据
	 * @return 返回数据处理结果 0成功 1失败
	 */
	@Bizlet("")
	public String addData(DataObject object) {
		String msg = "";
		String getStr = addTbGrtMortgage(object);
		
		if("1".equals(getStr)){
			return "交易成功，抵质押类型与押品类型不匹配！";
		}
		if("2".equals(getStr)){
			return "交易成功，没有找到对应客户信息！";
		}
		
		String suretyId = getStr;
		String sortType = object.getString("plgTp");
		// 获取押品详细信息
		String serviceCodeScene = object.getString("ServiceCodeScene");
		String plgDtlInfStr = object.getString("plgDtlInf");
		
		if (sortType.startsWith("01")) { // 房地产
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "01");

			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtHouse");
			con.set("_entity", "com.bos.dataset.grt.TbGrtHouse");
			con.set("rMsg", "交易成功，房地产信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("housePropStatus", obj.getString("hsProprtCtfSt"));
			con.set("housePropNo", obj.getString("hsOwnCtfNo"));
			con.set("registerDate", GitUtil.toDate(obj.getString("rgstDt"), "yyyyMMdd"));
			con.set("country", obj.getString("ctyNm"));
			con.set("province", obj.getString("provNm"));
			con.set("city", obj.getString("cityNm"));
			con.set("town", obj.getString("cntyNm"));
			con.set("houseLocation", obj.getString("rgstAdr"));
			con.set("houseArea", new BigDecimal(obj.getString("stcArea")));
			con.set("houseStructure", obj.getString("hsStcCd"));
			con.set("landUseNo", obj.getString("landUseRghtCtfNo"));
			con.set("landQuale", obj.getString("landKnd"));
			con.set("landGainWay", obj.getString("landGainMth"));
			
			msg = getMsg(con);
		} else if (sortType.startsWith("02")) { // 在建工程
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "02");

			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtBuilding");
			con.set("_entity", "com.bos.dataset.grt.TbGrtBuilding");
			con.set("rMsg", "交易成功，在建工程信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("approvalNo", obj.getString("prjFileNo"));
			con.set("landPlanPerNo", obj.getString("landPlnPrmtNo"));
			con.set("buildPlanPerNo", obj.getString("ConsEngnPlnPrmtNo"));
			con.set("constPlanPerNo", obj.getString("consEngnWrkPrmtNo"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("buildBudgetCost", new BigDecimal(obj.getDouble("consBdgtCostAmt")));
			con.set("buildBeginDate", GitUtil.toDate(obj.getString("prjStrtDt"), "yyyyMMdd"));
			con.set("exceptEndDate", GitUtil.toDate(obj.getString("expcEndDt"), "yyyyMMdd"));
			con.set("exceptLandAcreage", new BigDecimal(obj.getDouble("expcLandArea")));
			con.set("country", obj.getString("ctyNm"));
			con.set("province", obj.getString("provNm"));
			con.set("city", obj.getString("cityNm"));
			con.set("town", obj.getString("cntyNm"));
			con.set("buildLocation", obj.getString("prjAdr"));
			con.set("landUseNo", obj.getString("statOwnLandUseRghtCtfNo"));
			con.set("landAcreage", obj.getString("landUseArea"));
			con.set("landQuale", obj.getString("landKnd"));
			con.set("landGainWay", obj.getString("landGainMth"));
			con.set("useEndDate", GitUtil.toDate(obj.getString("landUseEndDt"), "yyyyMMdd"));
			con.set("buildUnit", obj.getString("consUnitNm"));

			msg = getMsg(con);
		} else if (sortType.startsWith("03")) { // 土地使用权
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "03");

			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtLanduse");
			con.set("_entity", "com.bos.dataset.grt.TbGrtLanduse");
			con.set("rMsg", "交易成功，土地使用权信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("landUseNo", obj.getString("landUseRghtCtfNo"));
			con.set("certiIssue", obj.getString("landUseRghtCtfIssuOfficNm"));
			con.set("landQuale", obj.getString("landKnd"));
			con.set("landGainWay", obj.getString("landGainMth"));
			con.set("landArea", obj.getString("landUseArea"));
			con.set("useEndDate", GitUtil.toDate(obj.getString("landUseEndDt"), "yyyyMMdd"));
			con.set("country", obj.getString("ctyNm"));
			con.set("province", obj.getString("provNm"));
			con.set("city", obj.getString("cityNm"));
			con.set("town", obj.getString("cntyNm"));
			con.set("landLocation", obj.getString("landAdr"));
			con.set("landGainDate", GitUtil.toDate(obj.getString("landGainDt"), "yyyyMMdd"));
			con.set("usePowerLimit", obj.getString("useRghtAgeLmt"));

			msg = getMsg(con);
		} else if (sortType.startsWith("04")) { // 交通工具
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "04");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtVehicle");
			con.set("_entity", "com.bos.dataset.grt.TbGrtVehicle");
			con.set("rMsg", "交易成功，交通工具信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("buyContractNo", obj.getString("purchsCtrNo"));
			con.set("invoiceNo", obj.getString("invNo"));
			con.set("certificateNo", obj.getString("complnDocNo"));
			con.set("carRunNo", obj.getString("engNo"));
			con.set("carNo", obj.getString("licPltNo"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("buyPrice", new BigDecimal(obj.getDouble("buyCostAmt")));
			con.set("operationNo", obj.getString("oprCtfNo"));
			con.set("certiIssue", obj.getString("oprCtfIssuOfficNm"));
			con.set("weightCapacity", new BigDecimal(obj.getDouble("cryCpyNum")));

			msg = getMsg(con);
		} else if (sortType.startsWith("05")) { // 机械设备
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "05");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtMachine");
			con.set("_entity", "com.bos.dataset.grt.TbGrtMachine");
			con.set("rMsg", "交易成功，机械设备信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("machineNo", obj.getString("mchnNo"));
			con.set("machineType", obj.getString("mchnTp"));
			con.set("factoryDate", GitUtil.toDate(obj.getString("pdnDt"), "yyyyMMdd"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("buyPrice", new BigDecimal(obj.getDouble("buyCostAmt")));

			msg = getMsg(con);
		} else if (sortType.startsWith("06")) { // 存货
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "06");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtGoods");
			con.set("_entity", "com.bos.dataset.grt.TbGrtGoods");
			con.set("rMsg", "交易成功，存货信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("inventoryNo", obj.getString("invtListNo"));
			con.set("inventoryNum", new BigDecimal(obj.getDouble("invtNum")));
			con.set("inventoryUnits", obj.getString("invtUnitNm"));
			con.set("buyTime", GitUtil.toDate(obj.getString("buyDt"), "yyyyMMdd"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("buyTotalPrice", new BigDecimal(obj.getDouble("buyCostAmt")));
			con.set("superviseUnit", obj.getString("regInstNm"));

			msg = getMsg(con);
		} else if (sortType.startsWith("07")) { // 其他抵押资产
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "07");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtOtherProperty");
			con.set("_entity", "com.bos.dataset.grt.TbGrtOtherProperty");
			con.set("rMsg", "交易成功，其他抵押资产信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("assetNum", new BigDecimal(obj.getDouble("astNum")));
			con.set("assetNumUnits", obj.getString("astNumUnitNm"));
			con.set("assetAddress", obj.getString("astAdr"));

			msg = getMsg(con);
		} else if (sortType.startsWith("08")) { // 存单
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "08");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtDeposit");
			con.set("_entity", "com.bos.dataset.grt.TbGrtDeposit");
			con.set("rMsg", "交易成功，存单信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("depositType", obj.getString("depRecptTp"));
			con.set("depositAcc", obj.getString("depRecptAcctNo"));
			con.set("depositNo", obj.getString("depRecptNo"));
			con.set("openBank", obj.getString("openAcctBnkNm"));
			con.set("beginDate", GitUtil.toDate(obj.getString("depDt"), "yyyyMMdd"));
			con.set("endDate", GitUtil.toDate(obj.getString("expDt"), "yyyyMMdd"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("depositAmt", new BigDecimal(obj.getDouble("depRecpAmt")));
			con.set("depositRate", new BigDecimal(obj.getDouble("depRecpRate")));

			msg = getMsg(con);
		} else if (sortType.startsWith("09")) { // 债券
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "09");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtBond");
			con.set("_entity", "com.bos.dataset.grt.TbGrtBond");
			con.set("rMsg", "交易成功，债券信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("singedType", obj.getString("issurTp"));
			con.set("singedUnit", obj.getString("issurNm"));
			con.set("bondNo", obj.getString("bondNo"));
			con.set("nationalSaveOrg", obj.getString("bookEntrTBondAgntNm"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("bondValue", new BigDecimal(obj.getDouble("bondValAmt")));
			con.set("bondRate", new BigDecimal(obj.getDouble("bondRate")));
			con.set("beginDate", GitUtil.toDate(obj.getString("valDt"), "yyyyMMdd"));
			con.set("endDate", GitUtil.toDate(obj.getString("expDt"), "yyyyMMdd"));
			con.set("repayType", obj.getString("pnpRepyMth"));
			con.set("intrWay", obj.getString("pyIntMth"));
			con.set("isTransfer", obj.getString("tfrblFlg"));

			msg = getMsg(con);
		} else if (sortType.startsWith("10")) { // 股权
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "10");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtInmarketstock");
			con.set("_entity", "com.bos.dataset.grt.TbGrtInmarketstock");
			con.set("rMsg", "交易成功，股权信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("stockCode", obj.getString("stkRghtCtfNo"));
			con.set("stockType", obj.getString("stkRghtTp"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("stockValue", new BigDecimal(obj.getDouble("plgStkRghtAmt")));
			con.set("stockPercent", new BigDecimal(obj.getDouble("plgStkRghtPrcnt")));
			con.set("stockQuality", obj.getString("stkRghtKnd"));
			con.set("issueName", obj.getString("issuEntpNm"));
			con.set("isPublicCompany", obj.getString("bnkOrListCoFlg"));
			con.set("issuingDate", GitUtil.toDate(obj.getString("pblsDt"), "yyyyMMdd"));
			con.set("endDate", GitUtil.toDate(obj.getString("expDt"), "yyyyMMdd"));

			msg = getMsg(con);
		} else if (sortType.startsWith("11")) { // 股票
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "11");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtStock");
			con.set("_entity", "com.bos.dataset.grt.TbGrtStock");
			con.set("rMsg", "交易成功，股票信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("stockName", obj.getString("stkNm"));
			con.set("stockCode", obj.getString("stkCd"));
			con.set("issuedUnit", obj.getString("issuEntpNm"));
			con.set("issuedDate", GitUtil.toDate(obj.getString("pblsDt"), "yyyyMMdd"));
			con.set("stockType", obj.getString("stkTp"));
			con.set("stockSort", obj.getString("stkClCd"));
			con.set("circulateCase", obj.getString("stkRghtCrclCd"));
			con.set("stockCount", new BigDecimal(obj.getDouble("shrhldNum")));
			con.set("currencyCd", obj.getString("ccy"));

			msg = getMsg(con);
		} else if (sortType.startsWith("12")) { // 基金
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "12");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtFund");
			con.set("_entity", "com.bos.dataset.grt.TbGrtFund");
			con.set("rMsg", "交易成功，基金信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("fundName", obj.getString("fndNm"));
			con.set("fundCode", obj.getString("fndCd"));
			con.set("issuedUnit", obj.getString("issuEntpNm"));
			con.set("issuedDate", GitUtil.toDate(obj.getString("pblsDt"), "yyyyMMdd"));
			con.set("fundSort", obj.getString("fndClCd"));
			con.set("fundType", obj.getString("fndTp"));
			con.set("isCirculation", obj.getString("crclFlag"));
			con.set("currencyCd", obj.getString("ccy"));

			msg = getMsg(con);
		} else if (sortType.startsWith("13")) { // 理财产品
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "13");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtManagemoney");
			con.set("_entity", "com.bos.dataset.grt.TbGrtManagemoney");
			con.set("rMsg", "交易成功，理财产品信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("manageMoneyName", obj.getString("pdNm"));
			con.set("treatyNo", obj.getString("fncAgrmNo"));
			con.set("beginDate", GitUtil.toDate(obj.getString("ittDt"), "yyyyMMdd"));
			con.set("issuedDate", GitUtil.toDate(obj.getString("expDt"), "yyyyMMdd"));
			con.set("yieldRate", new BigDecimal(obj.getDouble("expcPftRate")));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("buyPrice", new BigDecimal(obj.getDouble("buyAmt")));
			con.set("earningsType", obj.getString("incmTp"));
			con.set("accountNo", obj.getString("fncAcctNo"));
			con.set("dangerLevel", obj.getString("pdRskLvl"));

			msg = getMsg(con);
		} else if (sortType.startsWith("14")) { // 票据
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "14");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtBill");
			con.set("_entity", "com.bos.dataset.grt.TbGrtBill");
			con.set("rMsg", "交易成功，票据信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("billNo", obj.getString("billNo"));
			con.set("remitter", obj.getString("drwrAcctNm"));
			con.set("billAccount", obj.getString("drwrAcctNo"));
			con.set("billBank", obj.getString("drwrOpenAcctBnkNm"));
			con.set("beginDate", GitUtil.toDate(obj.getString("issuDt"), "yyyyMMdd"));
			con.set("endDate", GitUtil.toDate(obj.getString("expDt"), "yyyyMMdd"));
			con.set("payeeAccount", obj.getString("pyeAcctNo"));
			con.set("payeeBank", obj.getString("pyeOpenAcctBnkNm"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("isLxbs", obj.getString("unintruptEndrsmFlg"));
			con.set("billValue", new BigDecimal(obj.getDouble("billAmt")));

			msg = getMsg(con);
		} else if (sortType.startsWith("15")) { // 应收类
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "15");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtReceivables");
			con.set("_entity", "com.bos.dataset.grt.TbGrtReceivables");
			con.set("rMsg", "交易成功，应收类信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("payeeName", obj.getString("pyeNm"));
			con.set("payeeOpenbankName", obj.getString("pyeOpenAcctBnkNm"));
			con.set("payeeNum", obj.getString("pyeAcctNo"));
			con.set("payerName", obj.getString("pyrNm"));
			con.set("payerEnterprisePro", obj.getString("pyrEntpKnd"));
			con.set("contractNo", obj.getString("ctrNo"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("accountsDueBalance", new BigDecimal(obj.getDouble("rcvbBal")));
			con.set("aging", obj.getString("agngMoNum"));
			con.set("beginDate", GitUtil.toDate(obj.getString("sellOnCrDt"), "yyyyMMdd"));
			con.set("endDate", GitUtil.toDate(obj.getString("rcvbEndDt"), "yyyyMMdd"));

			msg = getMsg(con);
		} else if (sortType.startsWith("16")) { // 出口退税
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "16");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtCkts");
			con.set("_entity", "com.bos.dataset.grt.TbGrtCkts");
			con.set("rMsg", "交易成功，出口退税信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("assetName", obj.getString("astNm"));
			con.set("cktszh", obj.getString("exprtRbtAcctNo"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("tsje", new BigDecimal(obj.getDouble("rbtAmt")));
			con.set("ckhwbgd", obj.getString("exprtGdsDclrtnNo"));

			msg = getMsg(con);
		} else if (sortType.startsWith("17")&&!sortType.startsWith("1703")) { // 货权类
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "17");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtDepot");
			con.set("_entity", "com.bos.dataset.grt.TbGrtDepot");
			con.set("rMsg", "交易成功，货权类信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("storageOrg", obj.getString("strgInstNm"));
			con.set("depotNo", obj.getString("wrntNo"));
			con.set("storageName", obj.getString("strgNm"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("depotMoney", new BigDecimal(obj.getDouble("wrntAmt")));
			con.set("cargoSort", obj.getString("gdsTp"));
			con.set("cargoUnits", obj.getString("gdsUnitTp"));
			con.set("cargoNum", obj.getString("gdsNum"));
			con.set("instorageDate", GitUtil.toDate(obj.getString("inStrgDt"), "yyyyMMdd"));

			msg = getMsg(con);
		} else if (sortType.startsWith("1703")) { // 提单
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "1703");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtLanbill");
			con.set("_entity", "com.bos.dataset.grt.TbGrtLanbill");
			con.set("rMsg", "交易成功，提单信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("lanBillNo", obj.getString("ladBillNo"));
			con.set("cargoName", obj.getString("gdsNm"));
			con.set("cargoUnits", obj.getString("gdsUnitTp"));
			con.set("cargoNum", obj.getString("gdsNum"));
			con.set("cargoNum", obj.getString("ccy"));
			con.set("sumMoney", new BigDecimal(obj.getDouble("ladBillAmt")));
			con.set("sendDate", GitUtil.toDate(obj.getString("dlvDt"), "yyyyMMdd"));
			con.set("deliveryDate", GitUtil.toDate(obj.getString("pckUpDt"), "yyyyMMdd"));
			con.set("forwarder", obj.getString("fwdrNm"));
			con.set("address", obj.getString("ivntAdr"));

			msg = getMsg(con);
		} else if (sortType.startsWith("18")) { // 知识产权
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "18");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtLicense");
			con.set("_entity", "com.bos.dataset.grt.TbGrtLicense");
			con.set("rMsg", "交易成功，知识产权信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("licenseNo", obj.getString("intPtyCtfNo"));
			con.set("licenseName", obj.getString("intPtyCtfIssuOfficNm"));
			con.set("endDate", GitUtil.toDate(obj.getString("intPtyProtEndDt"), "yyyyMMdd"));
			con.set("years", obj.getString("intPtyAgLmt"));

			msg = getMsg(con);
		} else if (sortType.startsWith("19")&&!sortType.startsWith("1902")&&!sortType.startsWith("1903")&&!sortType.startsWith("1904")) { // 承包经营权
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "19");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtRestProfit");
			con.set("_entity", "com.bos.dataset.grt.TbGrtRestProfit");
			con.set("rMsg", "交易成功，承包经营权信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("cbjyqr", obj.getString("ctrMgtPrsnNm"));
			con.set("landUseNo", obj.getString("landCtrMgtRghtCtfNo"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("yearJobCost", new BigDecimal(obj.getDouble("yrCtrCostAmt")));
			con.set("beginDate", GitUtil.toDate(obj.getString("ctrStrtDt"), "yyyyMMdd"));
			con.set("endDate", GitUtil.toDate(obj.getString("ctrEndDt"), "yyyyMMdd"));
			con.set("licenseOwner", obj.getString("ptyOwnNm"));
			con.set("landPaymentState", obj.getString("landTfrFeePymtSt"));

			msg = getMsg(con);
		} else if (sortType.startsWith("1902")) { // 特许经营权
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "1902");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtTxjyq");
			con.set("_entity", "com.bos.dataset.grt.TbGrtTxjyq");
			con.set("rMsg", "交易成功，特许经营权信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("txjyqsr", GitUtil.toDate(obj.getString("frchsStrtDt"), "yyyyMMdd"));
			con.set("txjyzzr", GitUtil.toDate(obj.getString("frchsEndDt"), "yyyyMMdd"));
			con.set("czcjyxkz", obj.getString("taxiBsnCtfNo"));
			con.set("glkyxljyxkz", obj.getString("hghwyPsngrTransptnCtfNo"));
			con.set("jyqxxms", obj.getString("frchsRghtDsc"));

			msg = getMsg(con);
		} else if (sortType.startsWith("1903")) { // 其他收益权
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "1903");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtQtsyq");
			con.set("_entity", "com.bos.dataset.grt.TbGrtQtsyq");
			con.set("rMsg", "交易成功，其他收益权信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("syqqsr", GitUtil.toDate(obj.getString("rghtStrtDt"), "yyyyMMdd"));
			con.set("syqzzr", GitUtil.toDate(obj.getString("rghtEndDt"), "yyyyMMdd"));
			msg = getMsg(con);
		} else if (sortType.startsWith("1904")) { // 林权
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "1904");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtResourceprofit");
			con.set("_entity", "com.bos.dataset.grt.TbGrtResourceprofit");
			con.set("rMsg", "交易成功，林权信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("cbjyqr", obj.getString("ctrMgtPrsnNm"));
			con.set("forestNo", obj.getString("frstRghtCtfNo"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("yearJobCost", new BigDecimal(obj.getDouble("yrCtrCostAmt")));
			con.set("beginDate", GitUtil.toDate(obj.getString("ctrStrtDt"), "yyyyMMdd"));
			con.set("endDate", GitUtil.toDate(obj.getString("ctrEndDt"), "yyyyMMdd"));
			con.set("licenseOwner", obj.getString("ptyOwnNm"));

			msg = getMsg(con);
		} else if (sortType.startsWith("21")) { // 路桥等收费权
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "21");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtRoadBridge");
			con.set("_entity", "com.bos.dataset.grt.TbGrtRoadBridge");
			con.set("rMsg", "交易成功，路桥等收费权信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("chargingUnitName", obj.getString("chrgInstNm"));
			con.set("chargingUnitEnNature", obj.getString("chrgInstEntpKnd"));
			con.set("chargingContractNo", obj.getString("ctrNo"));
			con.set("beginDate", obj.getString("rghtStrtDt"));
			con.set("endDate", obj.getString("rghtEndDt"));

			msg = getMsg(con);
		} else if (sortType.startsWith("22")) { // 其他质押资产
			DataObject obj = EsbSocketService.getMapByClob("plgDtlInf",plgDtlInfStr, serviceCodeScene, "22");
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtQtzyzc");
			con.set("_entity", "com.bos.dataset.grt.TbGrtQtzyzc");
			con.set("rMsg", "交易成功，其他质押资产信息唯一性校验不通过！");
			con.set("sortType", sortType);
			con.set("suretyId", suretyId);
			con.set("assetName", obj.getString("astNm"));
			con.set("currencyCd", obj.getString("ccy"));
			con.set("assetValue", new BigDecimal(obj.getDouble("astValAmt")));
			con.set("assetUnit", obj.getString("astNumUnitNm"));
			con.set("assetNum", new BigDecimal(obj.getDouble("astNum")));

			msg = getMsg(con);
		}
		return msg;
	}

	public String getMsg(DataObject con) {
		String msg = new CheckManage().checkGrt(con);
		if ("0".equals(msg)) {
			DatabaseUtil.insertEntity("default", con);
			return con.getString("suretyId");
		} else {
			return con.getString("rMsg");
		}
	}

	/**
	 * 查询相应的partyId
	 * 这里根据用户号、机构号、和客户编号三个条件去匹配客户
	 * @param partyNum
	 * @param usrNum
	 * @param orgNum
	 * @return
	 */
	private String queryPartyId(String partyNum,String userNum,String orgNum) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("partyNum", partyNum);
		map.put("userNum", userNum);
		map.put("orgNum", orgNum);
		
		Object[] result = DatabaseExt.queryByNamedSql("default", "com.bos.csm.mtmq.toMtmq.findPartyId", map);
		if(result.length == 0||result ==null){
			return null;
		}
		return ((DataObject)result[0]).getString("PARTY_ID");
	}

	/**
	 * 新增押品主表信息
	 * 
	 * @param object
	 *            参数信息
	 * @return 返回押品主键
	 */
	private String addTbGrtMortgage(DataObject object) {
		String userNum = object.getString("cstMgrNo");
		String orgNum = object.getString("ittbrId");
		String partyNum = object.getString("crCstNo");
		String s = queryPartyId(partyNum,userNum,orgNum);
		if(s == null){
			return "2";
		}
		String partyId = s;
		String sortType = object.getString("plgTp");
		String collType = object.getString("cltzTp"); // 暂定colltype
		double mrtgValAmt = object.getDouble("mrtgValAmt");
		String suretyNo = getSuretyNo(sortType, collType);
		String time = object.getString("time");
		
		//判断抵质押类型与押品类型是否匹配
		DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtSortarguments");
		obj.set("sortType", sortType);
		Object[] result = DatabaseUtil.queryEntitiesByTemplate("default", obj);
		String getCollType = ((DataObject)result[0]).getString("collType");
		if(!("01".equals(collType)||"02".equals(collType))){
			return "1";
		}
		
		if(collType.equals(getCollType)||"00".equals(getCollType)){
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtMortgageBasic");
			con.set("userNum", userNum);
			con.set("orgNum", orgNum);
			con.set("partyId", partyId);
			con.set("mortgageValue", new BigDecimal(mrtgValAmt));
			con.set("sortType", sortType);
			con.set("collType", collType);
			con.set("suretyNo", suretyNo);
			con.set("ifDataMove", "3");
			con.set("mortgageStatus", "03");
			con.set("createTime", GitUtil.toDate(time, "yyyyMMdd"));
			con.set("updateTime", GitUtil.toDate(time, "yyyyMMdd"));
			DatabaseUtil.insertEntity("default", con);
			return con.getString("suretyId");
		}else{
			return "1";
		}
	}

	/**
	 * 获取抵质押物编号
	 * 
	 * @param sortType
	 * @param collType
	 * @return
	 */
	private String getSuretyNo(String sortType, String collType) {
		String bizNum = BizNumGenerator.getBizNum("SEQ_GRT_DZ");
		bizNum += sortType;
		if ("01".equals(collType)) {
			return "D" + bizNum;
		} else if ("02".equals(collType)) {
			return "Z" + bizNum;
		}
		return null;
	}
}
