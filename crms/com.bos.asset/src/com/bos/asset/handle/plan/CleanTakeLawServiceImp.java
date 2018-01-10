package com.bos.asset.handle.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.asset.AssetsUtil;
import com.bos.asset.handle.IAssetHandlePlanService;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.git.easyrule.service.RuleService;
import commonj.sdo.DataObject;

public class CleanTakeLawServiceImp implements IAssetHandlePlanService {

	public void createValidate(Map<String, Object> data) {

	}

	public void create(Map<String, Object> param) {

	}

	public void save(DataObject cleanTakLaw, DataObject[] cleanTakLawCon) throws Throwable {
		AssetsUtil.editValidate(getDBName(), cleanTakLaw);
		if (EntityUtil.exists(getDBName(), AssetsTableName.TB_ASSET_CLEAN_TAKE_LAW, new String[] { "id", cleanTakLaw.getString("id") })) {
			DatabaseUtil.updateEntity(getDBName(), cleanTakLaw);
		} else {
			DatabaseUtil.insertEntity(getDBName(), cleanTakLaw);
		}
		for (DataObject clc : cleanTakLawCon) {
			DatabaseUtil.updateEntity(getDBName(), clc);
		}
		DatabaseExt.executeNamedSql(getDBName(), "com.bos.asset.handle.plan.CleanTakeSql.updateCleanTakeLawAmtAndBal", cleanTakLaw.getString("id"));
	}

	@SuppressWarnings("unchecked")
	public void createCleanTakLawCon(String planId, HashMap<String, Object>[] dataMap) throws Throwable {
		if (dataMap == null || dataMap.length < 1) {
			throw new Throwable("没有获取到需要保存的数据");
		}
		AssetsUtil.editValidate(getDBName(), planId);
		List<DataObject> saveDatas = new ArrayList<DataObject>(dataMap.length);
		for (int i = 0; i < dataMap.length; i++) {
			Object[] temps = DatabaseExt.queryByNamedSql(getDBName(), "com.bos.asset.handle.HandleSql.handleListByCon", dataMap[i]);
			if (temps == null || temps.length != 1) {
				throw new Throwable("获取合同相关数据失败");
			}
			Map<String, Object> tempMap = (Map<String, Object>) temps[0];
			if (EntityUtil.exists(getDBName(), AssetsTableName.TB_ASSET_CLEAN_TAKE_LAW_CON, new String[] { "id", planId, "contractId", (String) tempMap.get("CONTRACT_ID") })) {
				continue;
			}
			DataObject cleanTakeLawCon = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_CLEAN_TAKE_LAW_CON);
			cleanTakeLawCon.set("id", planId);
			cleanTakeLawCon.set("contractId", tempMap.get("CONTRACT_ID"));
			cleanTakeLawCon.set("contractNum", tempMap.get("CONTRACT_NUM"));
			cleanTakeLawCon.set("conProductType", tempMap.get("PRODUCT_TYPE"));
			cleanTakeLawCon.set("conCurrencyCd", tempMap.get("CURRENCY_CD"));
			cleanTakeLawCon.set("contractAmt", tempMap.get("CONTRACT_AMT"));
			cleanTakeLawCon.set("contractBal", tempMap.get("CON_BALANCE"));
			cleanTakeLawCon.set("conBeginDate", tempMap.get("BEGIN_DATE"));
			cleanTakeLawCon.set("conEndDate", tempMap.get("END_DATE"));
			cleanTakeLawCon.set("conOrgNum", tempMap.get("ORG_NUM"));
			cleanTakeLawCon.set("conUserNum", tempMap.get("USER_NUM"));
			cleanTakeLawCon.set("conOverdueDays", tempMap.get("YQTS"));
			cleanTakeLawCon.set("conOverdueCapital", tempMap.get("JJYQBJ"));
			cleanTakeLawCon.set("conNormalItr", tempMap.get("NORMAL_ITR"));
			cleanTakeLawCon.set("conArrearItr", tempMap.get("ARREAR_ITR"));
			cleanTakeLawCon.set("conPunishItr", tempMap.get("PUNISH_ITR"));
			cleanTakeLawCon.set("conFiveClassify", tempMap.get("CLS_RESULT"));
			// --------------
			saveDatas.add(cleanTakeLawCon);
		}
		DatabaseUtil.insertEntityBatch(getDBName(), saveDatas.toArray(new DataObject[] {}));
		DatabaseExt.executeNamedSql(getDBName(), "com.bos.asset.handle.plan.CleanTakeSql.updateCleanTakeLawAmtAndBal", planId);
	}

	public void removeCleanTakLawCon(String planId, HashMap<String, Object>[] dataMap) throws Throwable {
		if (dataMap == null || dataMap.length < 1) {
			throw new Throwable("没有获取到需要保存的数据");
		}
		AssetsUtil.editValidate(getDBName(), planId);
		List<DataObject> removeDatas = new ArrayList<DataObject>(dataMap.length);
		for (int i = 0; i < dataMap.length; i++) {
			String contractId = (String) dataMap[i].get("contractId");
			if (contractId == null) {
				continue;
			}
			DataObject cleanTakeLawCon = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_CLEAN_TAKE_LAW_CON);
			cleanTakeLawCon.set("id", planId);
			cleanTakeLawCon.set("contractId", contractId);
			removeDatas.add(cleanTakeLawCon);
		}
		DatabaseUtil.deleteEntityBatch(getDBName(), removeDatas.toArray(new DataObject[] {}));
		DatabaseExt.executeNamedSql(getDBName(), "com.bos.asset.handle.plan.CleanTakeSql.updateCleanTakeLawAmtAndBal", planId);
	}

	public void processSubmitValid(String planId) throws Throwable {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id", planId);
		List<com.git.easyrule.models.MessageObj> msgList = new RuleService().runRule("RNPL_0005", paramMap);
		if (msgList != null && msgList.size() != 0) {
			throw new RuntimeException(msgList.get(0).getMessageInfo());
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