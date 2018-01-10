package com.bos.asset.handle.plan;

import java.util.HashMap;
import java.util.Map;

import com.bos.asset.AssetsUtil;
import com.bos.asset.AssetsUtil.ASSETS_STATUS;
import com.bos.asset.AssetsUtil.INTERFACE_CODE;
import com.bos.asset.AssetsUtil.INTERFACE_STATUS;
import com.bos.asset.handle.IAssetHandlePlanService;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.bos.pub.entity.name.ConTableName;
import com.bos.pub.entity.name.LoanTableName;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.plus.PayVerifControlRq;
import com.primeton.plus.VerificationRq;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;

import commonj.sdo.DataObject;

public class VerifyOffServiceImp implements IAssetHandlePlanService {

	@SuppressWarnings("unchecked")
	public void createValidate(Map<String, Object> data) {
		AssetsUtil.paramValidate(data, "summaryId");
		Object[] temps = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.asset.handle.plan.VerifyOffSql.createVerifyOffValid", data);
		if (temps.length != 0) {
			for (Object obj : temps) {
				if (((Map<String, String>) obj).get("VID") != null) {
					throw new RuntimeException("该借据已存在核销业务");
				}
			}
			throw new RuntimeException("该客户存在在途业务，请先完成");
		}
	}

	@SuppressWarnings("unchecked")
	public void create(Map<String, Object> param) throws Throwable {
		AssetsUtil.paramValidate(param, "planId", "summaryId");
		DataObject summary = EntityUtil.getEntityById(LoanTableName.TB_LOAN_SUMMARY, "summaryId", (String) param.get("summaryId"));
		String summaryId = summary.getString("summaryId");
		DataObject loaninfo = EntityUtil.getEntityById(LoanTableName.TB_LOAN_INFO, "loanId", (String) param.get("loanId"));
		//
		Object[] datas = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.asset.handle.plan.VerifyOffSql.createVerifyOffInfo", summaryId);
		if (datas == null || datas.length == 0) {
			throw new RuntimeException("错误的借据编号，无法查询相关数据[summaryId=" + summaryId + "]");
		}
		Map<String, Object> dataMap = (Map<String, Object>) datas[0];
		if (!summaryId.equals(dataMap.get("SUMMARY_ID"))) {
			throw new RuntimeException("查询到的借据信息错误，请核实");
		}
		DataObject verifyOff = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_VERIFY_OFF);
		// 调用核算接口 ，控制信息以及第一步信息
		executeT1207(loaninfo,summary);
		VerificationRq v_1107 = executeT1107("1", loaninfo, null,summary);
		verifyOff.set("cancelAmt", v_1107.getTotPrnItr());
		verifyOff.set("cancelCapitalAmt", v_1107.getRcvPrn());
		verifyOff.set("cancelNormalItr", v_1107.getRcvNorItrIn());
		verifyOff.set("cancelArrearItr", v_1107.getRcvDftItrIn());
		verifyOff.set("cancelPunishItr", v_1107.getRcvPnsItrIn());
		verifyOff.set("rcnStan", String.valueOf(v_1107.getBaseVO().getRcnStan()));
		//
		verifyOff.set("id", param.get("planId"));
		verifyOff.set("summaryId", summaryId);
		verifyOff.set("summaryAmt", dataMap.get("SUMMARY_AMT"));
		verifyOff.set("sumNormalItr", dataMap.get("NORMAL_ITR"));
		verifyOff.set("sumArrearItr", dataMap.get("ARREAR_ITR"));
		verifyOff.set("sumPunishItr", dataMap.get("PUNISH_ITR"));
		verifyOff.set("summaryBal", dataMap.get("JJYE"));
		verifyOff.set("sumBeginDate", dataMap.get("BEGIN_DATE"));
		verifyOff.set("sumEndDate", dataMap.get("END_DATE"));
		verifyOff.set("repaymentType", dataMap.get("REPAYMENT_TYPE"));
		verifyOff.set("interestCollectType", dataMap.get("INTEREST_COLLECT_TYPE"));
		verifyOff.set("yearRate", dataMap.get("YEAR_RATE"));
		verifyOff.set("repayNum", dataMap.get("ZH"));
		verifyOff.set("repayName", dataMap.get("ZHMC"));
		//
		verifyOff.set("pursue", "1");
		verifyOff.set("settle", "1");
		verifyOff.set("status", INTERFACE_STATUS.UNACTION);

		DatabaseUtil.insertEntity(getDBName(), verifyOff);
	}

	public void save(DataObject data) throws Throwable {
		AssetsUtil.editValidate(getDBName(), data);
		data.set("updateDate", GitUtil.getBusiDate());
		data.set("updateUserId", GitUtil.getCurrentUserId());
		DatabaseUtil.updateEntity(getDBName(), data);
	}

	public Object submitToInterface(String planId, Map<String, Object> param) {
		DataObject verifyOff = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_VERIFY_OFF, "id", planId);
		if (INTERFACE_STATUS.SUCCESS == INTERFACE_STATUS.get(verifyOff.getString("status"))) {
			return null;
		}
		DataObject summary = EntityUtil.getEntityById(LoanTableName.TB_LOAN_SUMMARY, "summaryId", verifyOff.getString("summaryId"));
		DataObject con = EntityUtil.getEntityById(ConTableName.TB_CON_CONTRACT_INFO, "contractId", summary.getString("contractId"));
		DataObject loaninfo = EntityUtil.getEntityById(LoanTableName.TB_LOAN_INFO, "loanId", summary.getString("loanId"));
		if (!"0501".equals(con.get("clsResult"))) {
			throw new RuntimeException("非[损失]状态的合同无法核销");
		}
		VerificationRq v_1107 = null;
		try {
			v_1107 = executeT1107("2", loaninfo, verifyOff.getBigDecimal("rcnStan").longValue(),summary);
		} catch (Exception e) {
			String msg = e.getMessage();
			throw new RuntimeException(msg == null || msg.isEmpty() ? "接口通讯失败" : msg);
		}
		//第二次传过来的数据居然是0
//		// 再次刷新核销数据，以免两次数据不符
//		verifyOff.set("cancelAmt", v_1107.getTotPrnItr());
//		verifyOff.set("cancelCapitalAmt", v_1107.getRcvPrn());
//		verifyOff.set("cancelNormalItr", v_1107.getRcvNorItrIn());
//		verifyOff.set("cancelArrearItr", v_1107.getRcvDftItrIn());
//		verifyOff.set("cancelPunishItr", v_1107.getRcvPnsItrIn());
		//
		verifyOff.set("status", INTERFACE_STATUS.SUCCESS);
		summary.set("summaryStatusCd", "07");
		DatabaseUtil.updateEntity(getDBName(), summary);
		DatabaseUtil.updateEntity(getDBName(), verifyOff);

		return null;
	}

	private void error(BaseVO baseVO) {
		if (!DictContents.APLUS_RECODE.equals(baseVO.getErrCod())) {
			throw new RuntimeException("核算接口调用失败：" + baseVO.getErrCod() + "->" + baseVO.getErrMsg());
		}
	}
	/**
	 * 
	 * @param loaninfo放款信息
	 * @param loansummary借据信息
	 * @return
	 * @throws Throwable
	 */
	private PayVerifControlRq executeT1207(DataObject loaninfo, DataObject loansummary) throws Throwable {
		CrmsCallPlusProxy aplusImp = AssetsUtil.getAplusInterface();
		PayVerifControlRq v_1207 = new PayVerifControlRq();
		v_1207.setBaseVO(AssetsUtil.createBaseVO(INTERFACE_CODE.T1207, loaninfo.getString("loanOrg")));
		v_1207.setTelNo(loansummary.getString("summaryNum"));
		v_1207.setDueNum(loansummary.getString("summaryNum"));
		// ------------
		v_1207 = aplusImp.executeT1207(v_1207);
		error(v_1207.getBaseVO());
		return v_1207;
	}
	/**
	 * 
	 * @param times
	 * @param loaninfo放款信息
	 * @param rcnStan
	 * @param loansummary借据信息
	 * @return
	 * @throws Exception
	 */
	private VerificationRq executeT1107(String times, DataObject loaninfo, Long rcnStan,DataObject loansummary) throws Exception {
		CrmsCallPlusProxy crmsCallPlus = AssetsUtil.getAplusInterface();
		VerificationRq v_1107 = new VerificationRq();
		v_1107.setBaseVO(AssetsUtil.createBaseVO(INTERFACE_CODE.T1107, loaninfo.getString("loanOrg"), rcnStan));
		rcnStan = v_1107.getBaseVO().getRcnStan();
		v_1107.setTelNo(loansummary.getString("summaryNum"));
		v_1107.setDueNum(loansummary.getString("summaryNum"));
		v_1107.getBaseVO().setTranTimes(times);
		// ------------------------
		v_1107 = crmsCallPlus.executeT1107(v_1107);
		if (v_1107.getBaseVO().getRcnStan() == null) {
			v_1107.getBaseVO().setRcnStan(rcnStan);
		}
		error(v_1107.getBaseVO());
		return v_1107;
	}

	public String actionVerifyOff(String planId, HashMap<String, Object> dataMap) throws Throwable {
		if (ASSETS_STATUS.PASS != AssetsUtil.getPlanStatus(getDBName(), planId)) {
			throw new Throwable("只有审核通过的抵债资产冲销数据可以重新发起冲销交易");
		}
		return (String) submitToInterface(planId, dataMap);
	}

	public void processSubmitValid(String string) {
		// TODO 自动生成的方法存根

	}

	public String getServiceName() {
		return this.getClass().getSimpleName();
	}

	public String getDBName() {
		return AssetsUtil.getDBName();
	}

	public boolean inFlowPath() {
		return true;
	}
}