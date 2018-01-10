package com.bos.asset.handle.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.asset.AssetsUtil;
import com.bos.asset.AssetsUtil.INTERFACE_CODE;
import com.bos.asset.handle.IAssetHandlePlanService;
import com.bos.pub.DictContents;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.bos.pub.entity.name.LoanTableName;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.git.easyrule.service.RuleService;
import com.primeton.plus.CrePayQueryRq;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;

import commonj.sdo.DataObject;

public class CleanTakeMoneyServiceImp implements IAssetHandlePlanService {

	public void createValidate(Map<String, Object> data) {
		AssetsUtil.paramValidate(data, "summaryId");
		Object[] temps = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.asset.handle.HandleSql.createHandleValid", data);
		if (temps.length != 0) {
			throw new RuntimeException("该客户存在在途业务，请先完成");
		}
	}

	@SuppressWarnings("unchecked")
	public void create(Map<String, Object> param) throws Throwable {
		AssetsUtil.paramValidate(param, "planId", "summaryId");
		DataObject summary = EntityUtil.getEntityById(LoanTableName.TB_LOAN_SUMMARY, "summaryId", (String) param.get("summaryId"));
		String summaryId = summary.getString("summaryId");
		//
		Object[] datas = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.asset.handle.plan.CleanTakeSql.createCleanTakeMoneyInfo", summaryId);
		if (datas == null || datas.length == 0) {
			throw new RuntimeException("错误的借据编号，无法查询相关数据[summaryId=" + summaryId + "]");
		}
		Map<String, Object> dataMap = (Map<String, Object>) datas[0];
		if (!summaryId.equals(dataMap.get("SUMMARY_ID"))) {
			throw new RuntimeException("查询到的借据信息错误，请核实");
		}
		DataObject cleanTakeMoney = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_CLEAN_TAKE_MONEY);
		cleanTakeMoney.set("id", param.get("planId"));
		cleanTakeMoney.set("summaryId", summaryId);
		cleanTakeMoney.set("tingxiStatus", "01".equals(dataMap.get("TINGXI_STATUS")) ? "1" : "0");
		cleanTakeMoney.set("summaryAmt", dataMap.get("SUMMARY_AMT"));
		cleanTakeMoney.set("sumNormalItr", dataMap.get("NORMAL_ITR"));
		cleanTakeMoney.set("sumArrearItr", dataMap.get("ARREAR_ITR"));
		cleanTakeMoney.set("sumPunishItr", dataMap.get("PUNISH_ITR"));
		cleanTakeMoney.set("summaryBal", dataMap.get("JJYE"));
		cleanTakeMoney.set("sumBeginDate", dataMap.get("BEGIN_DATE"));
		cleanTakeMoney.set("sumEndDate", dataMap.get("END_DATE"));
		cleanTakeMoney.set("sumOverdueDays", dataMap.get("YQTS"));
		cleanTakeMoney.set("conVerify", "0".equals(dataMap.get("CON_VERIFY")) ? "0" : "1");
		//
		CrePayQueryRq rq = executeT1410(summary);
		cleanTakeMoney.set("supResNor", rq.getResNor());// 剩余正常本金（含当前期本金）
		cleanTakeMoney.set("supDftPrnBal", rq.getDftPrnBal()); // 贷款拖欠本金
		cleanTakeMoney.set("supTotPrnItr", rq.getTotPrnItr()); // 结清金额
		if (rq.getResNor() != null && rq.getDftPrnBal() != null) {
			cleanTakeMoney.set("supSurplusAmt", rq.getResNor().add(rq.getDftPrnBal()));// 贷款剩余本金
		}
		//
		DatabaseUtil.insertEntity(getDBName(), cleanTakeMoney);
	}

	public void save(DataObject data) throws Throwable {
		AssetsUtil.editValidate(getDBName(), data);
		DatabaseUtil.updateEntity(getDBName(), data);
	}

	public void processSubmitValid(String planId) throws Throwable {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", planId);
		List<com.git.easyrule.models.MessageObj> msgList = new RuleService().runRule("RNPL_0006", paramMap);
		if (msgList != null && msgList.size() != 0) {
			throw new RuntimeException(msgList.get(0).getMessageInfo());
		}
	}

	private CrePayQueryRq executeT1410(DataObject summary) throws Throwable {
		CrmsCallPlusProxy aplusImp = AssetsUtil.getAplusInterface();
		CrePayQueryRq rq = new CrePayQueryRq();
		rq.setBaseVO(AssetsUtil.createBaseVO(INTERFACE_CODE.T1410, summary.getString("orgNum")));
		rq.setDueNum(summary.getString("summaryNum"));
		rq = aplusImp.executeT1410(rq);
		error(rq.getBaseVO());
		return rq;
	}

	private void error(BaseVO baseVO) {
		if (!DictContents.APLUS_RECODE.equals(baseVO.getErrCod())) {
			throw new RuntimeException("核算接口调用失败：" + baseVO.getErrCod() + "->" + baseVO.getErrMsg());
		}
	}

	public Object submitToInterface(String planId, Map<String, Object> param) {
		return null;
	}

	public String getServiceName() {
		return this.getClass().getSimpleName();
	}

	public String getDBName() {
		return AssetsUtil.getDBName();
	}

	public boolean inFlowPath() {
		return false;
	}

}