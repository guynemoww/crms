package com.bos.asset.handle.plan;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionDefinition;

import com.bos.asset.AssetsUtil;
import com.bos.asset.AssetsUtil.ASSETS_STATUS;
import com.bos.asset.AssetsUtil.INTERFACE_CODE;
import com.bos.asset.AssetsUtil.INTERFACE_STATUS;
import com.bos.asset.handle.IAssetHandlePlanService;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.bos.pub.entity.name.LoanTableName;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.git.easyrule.service.RuleService;
import com.primeton.mgrcore.FXD011;
import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012_AccoutingRes;
import com.primeton.mgrcore.client.CrmsMgrCallCoreProxy;
import com.primeton.plus.AssetOffControlRq;
import com.primeton.plus.AssetOffRq;
import com.primeton.plus.RepayControlCancel;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.TransferDataUtil;
import commonj.sdo.DataObject;

public class WriteOffServiceImp implements IAssetHandlePlanService {

	public void createValidate(Map<String, Object> data) {
	}

	public void create(Map<String, Object> param) {
	}

	public void save(DataObject writeOff, DataObject[] writeOffLoans) throws Throwable {
		AssetsUtil.editValidate(getDBName(), writeOff);
		if (EntityUtil.exists(getDBName(), AssetsTableName.TB_ASSET_WRITE_OFF, new String[] { "id", writeOff.getString("id") })) {
			DatabaseUtil.updateEntity(getDBName(), writeOff);
		} else {
			DatabaseUtil.insertEntity(getDBName(), writeOff);
		}
		for (DataObject wol : writeOffLoans) {
			DatabaseUtil.updateEntity(getDBName(), wol);
		}
	}

	@SuppressWarnings("unchecked")
	public void createWriteOffLoan(String planId, Map<String, Object>[] dataMap) throws Throwable {
		if (dataMap == null || dataMap.length < 1) {
			throw new Throwable("没有获取到需要保存的数据");
		}
		AssetsUtil.editValidate(getDBName(), planId);
		List<DataObject> saveDatas = new ArrayList<DataObject>(dataMap.length);
		for (int i = 0; i < dataMap.length; i++) {
			Object[] temps = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.asset.handle.plan.WriteOffSql.createWriteOffLoanInfo", dataMap[i].get("summaryId"));
			if (temps == null || temps.length != 1) {
				throw new Throwable("获取借据相关数据失败");
			}
			Map<String, Object> tempMap = (Map<String, Object>) temps[0];
			if (EntityUtil.exists(getDBName(), AssetsTableName.TB_ASSET_WRITE_OFF_LOAN, new String[] { "id", planId, "summaryId", (String) tempMap.get("SUMMARY_ID") })) {
				continue;
			}
			DataObject writeOffLoan = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_WRITE_OFF_LOAN);
			writeOffLoan.set("id", planId);
			writeOffLoan.set("summaryId", tempMap.get("SUMMARY_ID"));
			writeOffLoan.set("contractNum", tempMap.get("CONTRACT_NUM"));
			writeOffLoan.set("talOrgNum", tempMap.get("ORG_NUM"));
			writeOffLoan.set("summaryNum", tempMap.get("SUMMARY_NUM"));
			writeOffLoan.set("summaryAmt", tempMap.get("SUMMARY_AMT"));
			writeOffLoan.set("summaryBal", tempMap.get("JJYE"));
			writeOffLoan.set("sumNormalItr", tempMap.get("NORMAL_ITR"));
			writeOffLoan.set("sumArrearItr", tempMap.get("ARREAR_ITR"));
			writeOffLoan.set("sumPunishItr", tempMap.get("PUNISH_ITR"));
			writeOffLoan.set("sumBeginDate", tempMap.get("BEGIN_DATE"));
			writeOffLoan.set("sumEndDate", tempMap.get("END_DATE"));
			// --------------
			writeOffLoan.set("repaySort", "20");
			writeOffLoan.set("status", INTERFACE_STATUS.UNACTION);
			saveDatas.add(writeOffLoan);
		}
		DatabaseUtil.insertEntityBatch(getDBName(), saveDatas.toArray(new DataObject[] {}));
	}

	public void updateWriteOffLoan(String planId, DataObject[] datas) throws Throwable {
		if (datas.length < 1) {
			throw new Throwable("没有获取到需要保存的数据");
		}
		AssetsUtil.editValidate(getDBName(), planId);
		DataObject[] saveDatas = new DataObject[datas.length];
		for (int i = 0; i < datas.length; i++) {
			saveDatas[i] = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_WRITE_OFF_LOAN);
			saveDatas[i].set("id", planId);
			saveDatas[i].set("summaryId", datas[i].getString("summaryId"));
			int num = DatabaseUtil.expandEntity(getDBName(), saveDatas[i]);
			if (num != 1) {
				throw new Throwable("抵债资产冲销贷款_关联借据信息错误，無法更新");
			}
			saveDatas[i].set("contractNum", datas[0].getString("CONTRACT_NUM"));
			saveDatas[i].set("summaryNum", datas[0].getString("SUMMARY_NUM"));
			saveDatas[i].set("summaryAmt", datas[0].getString("SUMMARY_AMT"));
			saveDatas[i].set("jjye", datas[0].getString("JJYE"));
		}
		DatabaseUtil.updateEntityBatch(getDBName(), saveDatas);
	}

	public void removeWriteOffLoan(String planId, HashMap<String, Object>[] dataMap) throws Throwable {
		if (dataMap == null || dataMap.length < 1) {
			throw new Throwable("没有获取到需要保存的数据");
		}
		AssetsUtil.editValidate(getDBName(), planId);
		List<DataObject> removeDatas = new ArrayList<DataObject>(dataMap.length);
		for (int i = 0; i < dataMap.length; i++) {
			String summaryId = (String) dataMap[i].get("summaryId");
			if (summaryId == null) {
				continue;
			}
			DataObject writeOffLoan = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_WRITE_OFF_LOAN);
			writeOffLoan.set("id", planId);
			writeOffLoan.set("summaryId", summaryId);
			removeDatas.add(writeOffLoan);
		}
		DatabaseUtil.deleteEntityBatch(getDBName(), removeDatas.toArray(new DataObject[] {}));
	}

	@SuppressWarnings("unchecked")
	public String[] submitToInterface(String planId, Map<String, Object> param) {
		DataObject writeOff = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_WRITE_OFF, "id", planId);
		DataObject[] writeOffLoans = EntityUtil.searchEntitys(AssetsTableName.TB_ASSET_WRITE_OFF_LOAN, "id", planId);
		String acctNo = writeOff.getString("acctNo");
		List<String> warnList = new ArrayList<String>(writeOffLoans.length);
		List<String> actionList = (List<String>) param.get("actionLoan");
		for (DataObject writeOffLoan : writeOffLoans) {
			if (actionList != null && !actionList.contains(writeOffLoan.get("summaryId"))) {
				continue;
			}
			String t = _submitToInterface(acctNo, writeOffLoan);
			if (t != null) {
				warnList.add(t);
			}
		}
		return warnList.size() == 0 ? null : warnList.toArray(new String[] {});
	}

	private String _submitToInterface(String acctNo, DataObject writeOffLoan) {
		try {
			if (INTERFACE_STATUS.SUCCESS == INTERFACE_STATUS.get(writeOffLoan.getString("status"))) {
				return null;// 冲销成功的数据不允许再次冲销
			}
			DataObject summary = EntityUtil.getEntityById(LoanTableName.TB_LOAN_SUMMARY, "summaryId", writeOffLoan.getString("summaryId"));
			DataObject loanInfo = EntityUtil.getEntityById(LoanTableName.TB_LOAN_INFO, "loanId", summary.getString("loanId"));
			// 第一次调用核算接口
			AssetOffRq v_1106 = executeT1106("1", acctNo, summary, writeOffLoan, null, null);
			Long rcnStan = v_1106.getBaseVO().getRcnStan();
			// 第二次调用核算接口
			v_1106 = executeT1106("2", acctNo, summary, writeOffLoan, rcnStan, null);
			// 调用核心接口，失败情况下需要调用撤销交易
			try {
				executeXD01(v_1106, writeOffLoan, loanInfo);
			} catch (Throwable e) {
				e.printStackTrace();
				executeB1102(summary, writeOffLoan, rcnStan);
				throw new RuntimeException(e.getMessage());
			}
			// 第三次调用核算接口
			v_1106 = executeT1106("3", acctNo, summary, writeOffLoan, rcnStan, null);// oxd12
			writeOffLoan.set("status", INTERFACE_STATUS.SUCCESS);
			DatabaseUtil.updateEntity(getDBName(), writeOffLoan);
		} catch (Throwable e) {
			e.printStackTrace();
			writeOffLoan.set("status", INTERFACE_STATUS.FAIL);
			DatabaseUtil.updateEntity(getDBName(), writeOffLoan);
			return e.getMessage();
		}
		return "抵债资产冲销成功";
	}

	@SuppressWarnings("unchecked")
	private void insertSupDebtAsset(String planId, DataObject summary) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", planId);
		map.put("summaryId", summary.get("summaryId"));
		Object[] datas = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.asset.handle.plan.WriteOffSql.getAplusWriteOffInfoByCrms", map);
		if (datas.length != 1) {
			throw new RuntimeException("信息错误，没有获取到填入冲销临时表的信息");
		}
		map = (Map<String, Object>) datas[0];
		if (!summary.getString("summaryId").equals(map.get("SUMMARY_ID"))) {
			throw new RuntimeException("信息错误，查询到的冲销信息与实际需要的数据不吻合");
		}
		String temp = GitUtil.getBusiDateYYYYMMDD();
		map.put("RCV_DATE", temp);
		map.put("TEL_NO", summary.get("summaryNum"));
		map.put("DEP_COD", summary.get("orgNum"));
		map.put("RCV_DATE", temp);
		Object[] objs = DatabaseExt.queryByNamedSql("aplus", "com.bos.asset.handle.plan.WriteOffSql.getAplusWriteOffInfo", map);
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		try {// 脱离原有事物，不然核算无法获取插入数据
			tm.begin(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			if (objs.length > 0) {
				DatabaseExt.executeNamedSql("aplus", "com.bos.asset.handle.plan.WriteOffSql.deleteAplusWriteOff", map);
			}
			String seq = GitUtil.getSeqNo(AssetsUtil.PLAN_SEQ_NAME, GitUtil.getCurrentOrgCd());
			seq = temp.substring(1, 4) + seq.substring(seq.length() - 6);
			map.put("SEQ_NO", seq);
			DatabaseExt.executeNamedSql("aplus", "com.bos.asset.handle.plan.WriteOffSql.insertAplusWriteOff", map);
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
		}
	}

	private AssetOffControlRq executeT1206(String acctNo, DataObject summary, DataObject writeOffLoan) throws Throwable {
		CrmsCallPlusProxy aplus = AssetsUtil.getAplusInterface();
		AssetOffControlRq v_1206 = new AssetOffControlRq();
		v_1206.setBaseVO(AssetsUtil.createBaseVO(INTERFACE_CODE.T1206, summary.getString("orgNum")));
		v_1206.setTelNo(summary.getString("summaryNum"));
		v_1206.setPayPrimAcct(acctNo);
		v_1206.setPadUpAmt(writeOffLoan.getBigDecimal("offAmt"));
		// -----------------------
		v_1206 = aplus.executeT1206(v_1206);
		aplusError("T1206", v_1206.getBaseVO(), writeOffLoan);
		return v_1206;
	}

	private void aplusError(String code, BaseVO baseVO, DataObject writeOffLoan) {
		if (!DictContents.APLUS_RECODE.equals(baseVO.getErrCod())) {
			throw new RuntimeException("借据号[" + writeOffLoan.getString("summaryNum") + "]冲销失败[" + code + "]:" + baseVO.getErrMsg());
		}
	}

	private AssetOffRq executeT1106(String times, String acctNo, DataObject summary, DataObject writeOffLoan, Long rcnStan, OXD012_AccoutingRes oxd12) throws Exception {
		CrmsCallPlusProxy aplus = AssetsUtil.getAplusInterface();
		AssetOffRq v_1106 = new AssetOffRq();
		v_1106.setBaseVO(AssetsUtil.createBaseVO(INTERFACE_CODE.T1106, summary.getString("orgNum"), rcnStan));
		v_1106.getBaseVO().setTranTimes(times);
		rcnStan = v_1106.getBaseVO().getRcnStan();
		v_1106.setTelNo(summary.getString("summaryNum"));
		v_1106.setPayPrimAcct(acctNo);
		if (oxd12 != null) {

		}
		// ----------------------
		v_1106 = aplus.executeT1106(v_1106);
		if (v_1106.getBaseVO().getRcnStan() == null) {
			v_1106.getBaseVO().setRcnStan(rcnStan);
		}
		aplusError("T1106_" + times, v_1106.getBaseVO(), writeOffLoan);
		return v_1106;
	}

	private OXD011_AccoutingReq executeXD01(AssetOffRq v_1106, DataObject writeOffLoan, DataObject loanInfo) throws Exception {
		CrmsMgrCallCoreProxy coreImpl = AssetsUtil.getCoreInterface();
		TransferDataUtil util = new TransferDataUtil();
		writeOffLoan.set("accJson", v_1106.getAccJson());
		String accJson = writeOffLoan.getString("accJson");
		FXD011[] fxd011 = util.plusToHx(accJson);
		OXD011_AccoutingReq oxd011 = new OXD011_AccoutingReq();
		oxd011.setChargeSeq(String.valueOf(v_1106.getBaseVO().getRcnStan()));
		writeOffLoan.setString("rcnStan", oxd011.getChargeSeq());
		oxd011.setOutSystemDate(GitUtil.getBusiDateYYYYMMDD());
		oxd011.setRecNum(BigInteger.valueOf(fxd011.length));
		oxd011.setRemarkInfo("B");
		oxd011.setSummaryCode("B00155");//摘要代码
		oxd011.setSummaryDescription("抵债资产冲销");//摘要描述
		oxd011.setHxorg(loanInfo.getString("loanOrg"));
		oxd011.setFxd011(fxd011);
		oxd011.setAmount(fxd011[0].getTransAmt());
		OXD012_AccoutingRes oxd12 = coreImpl.executeXD01(oxd011);
		if (!DictContents.CORE_SUCCESS.equals(oxd12.getResTranHeader().getHRetCode())) {
			throw new RuntimeException("借据号[" + writeOffLoan.getString("summaryNum") + "]冲销失败[XD01]:" + oxd12.getResTranHeader().getHRetCode() + "->" + oxd12.getResTranHeader().getHRetMsg());
		}
		return oxd011;
	}

	private void executeB1102(DataObject summary, DataObject writeOffLoan, Long rcnStan) throws Exception {
		RepayControlCancel repay = new RepayControlCancel();
		repay.setBaseVO(AssetsUtil.createBaseVO(INTERFACE_CODE.B1102, summary.getString("orgNum"), rcnStan));
		repay.setTelNo(summary.getString("summaryNum"));
		repay.setDueNum(summary.getString("summaryNum"));
		CrmsCallPlusProxy aplus = AssetsUtil.getAplusInterface();
		repay = aplus.executeB1102(repay);
		aplusError("B1102", repay.getBaseVO(), writeOffLoan);
	}

	public String getDBName() {
		return AssetsUtil.getDBName();
	}

	public String getServiceName() {
		return this.getClass().getSimpleName();
	}

	public boolean inFlowPath() {
		return true;
	}

	public String[] actionWriteOffLoan(String planId, HashMap<String, Object>[] dataMap) throws Throwable {
		if (dataMap == null || dataMap.length < 1) {
			throw new Throwable("没有获取到需要保存的数据");
		}
		if (ASSETS_STATUS.PASS != AssetsUtil.getPlanStatus(getDBName(), planId)) {
			throw new Throwable("只有审核通过的抵债资产冲销数据可以重新发起冲销交易");
		}
		List<String> actionList = new ArrayList<String>();
		for (int i = 0; i < dataMap.length; i++) {
			String summaryId = (String) dataMap[i].get("summaryId");
			if (summaryId == null) {
				continue;
			}
			actionList.add(summaryId);
		}
		if (actionList.isEmpty()) {
			throw new Throwable("没有获取到需要提交的数据");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("actionLoan", actionList);
		return submitToInterface(planId, param);
	}

	public void processSubmitValid(String planId) throws Throwable {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", planId);
		List<com.git.easyrule.models.MessageObj> msgList = new RuleService().runRule("RNPL_0003", paramMap);
		if (msgList != null && msgList.size() != 0) {
			throw new RuntimeException(msgList.get(0).getMessageInfo());
		}
		// 核算数据校验
		DataObject writeOff = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_WRITE_OFF, "id", planId);
		DataObject[] writeOffLoans = EntityUtil.searchEntitys(AssetsTableName.TB_ASSET_WRITE_OFF_LOAN, "id", planId);
		String acctNo = writeOff.getString("acctNo");
		for (DataObject writeOffLoan : writeOffLoans) {
			DataObject summary = EntityUtil.getEntityById(LoanTableName.TB_LOAN_SUMMARY, "summaryId", writeOffLoan.getString("summaryId"));
			insertSupDebtAsset(planId, summary);
			executeT1206(acctNo, summary, writeOffLoan);
		}
	}
}