package com.bos.asset.handle;

import java.util.HashMap;
import java.util.Map;

import com.bos.asset.AssetsUtil;
import com.bos.asset.AssetsUtil.ASSETS_STATUS;
import com.bos.bizApply.BizProcess;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.sun.star.uno.RuntimeException;
import commonj.sdo.DataObject;

public class HandlePlanServiceImp implements IAssetHandlePlanService {

	public void createValidate(Map<String, Object> data) {
		throw new RuntimeException("接口都调错了，在搞啥?");
	}

	public void create(Map<String, Object> param) throws Throwable {
		throw new RuntimeException("接口都调错了，在搞啥?");
	}

	public Map<String, Object> createAssetHandlePlan(String planType, String cleanTakeType, Map<String, Object> data) {
		if (planType == null || (planType = planType.trim()).isEmpty()) {
			throw new RuntimeException("没有获取到[处置方式]");
		}

		IAssetHandlePlanService service = AssetsUtil.getService(planType, cleanTakeType);
		service.createValidate(data);

		DataObject handle = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_HANDLE_PLAN);
		handle.set("planType", planType);
		handle.set("cleanTakeType", cleanTakeType);
		handle.set("planNum", GitUtil.getSeqNo(AssetsUtil.PLAN_SEQ_NAME, GitUtil.getCurrentOrgCd()));
		handle.set("partyId", data.get("partyId"));
		handle.set("regUserId", data.get("userId"));
		handle.set("regOrgId", data.get("orgId"));
		handle.set("regDate", GitUtil.getBusiDate());
		handle.set("status", ASSETS_STATUS.START);
		Map<String, Object> param = new HashMap<String, Object>();
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
		try {
			//
			DatabaseUtil.insertEntity(getDBName(), handle);
			String planId = handle.getString("id");
			data.put("planId", planId);
			//
			service.create(data);
			//
			if (service.inFlowPath()) {
				String processId = new BizProcess().createBpsProcessThrowError(planId, "asset");
				param.put("processInstId", processId);
			}
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			throw new RuntimeException(e.getMessage());
		}
		param.put("plan", handle);
		return param;
	}

	public void save(DataObject data) {
		AssetsUtil.editValidate(getDBName(), data);
		data.set("updateDate", GitUtil.getBusiDate());
		data.set("updateUserId", GitUtil.getCurrentUserId());
		DatabaseUtil.updateEntity(getDBName(), data);
	}

	public void remove(String planId) {
		DataObject plan = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_HANDLE_PLAN, "id", planId);
		AssetsUtil.editValidate(AssetsUtil.getDBName(), plan);
		String planType = plan.getString("planType");
		String cleanTakeType = plan.getString("cleanTakeType");
		String[] temps = AssetsUtil.getPlanEntity(planType, cleanTakeType);
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		try {
			tm.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
			DatabaseUtil.deleteEntity(AssetsUtil.getDBName(), plan);
			if (temps != null) {
				for (String tabelName : temps) {
					if (tabelName == null || (tabelName = tabelName.trim()).isEmpty()) {
						throw new RuntimeException("错误的处置方案实体配置信息");
					}
					DataObject temp = DataObjectUtil.createDataObject(tabelName);
					temp.set("id", planId);
					DatabaseUtil.deleteByTemplate(AssetsUtil.getDBName(), temp);
				}
			}
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			throw new RuntimeException(e.getMessage());
		}
	}

	public String getDBName() {
		return AssetsUtil.getDBName();
	}

	public String getServiceName() {
		return this.getClass().getSimpleName();
	}

	public Object submitToInterface(String planId, Map<String, Object> param) {
		throw new RuntimeException("接口都调错了，在搞啥?");
	}

	public boolean inFlowPath() {
		throw new RuntimeException("接口都调错了，在搞啥?");
	}

	public void processSubmitValid(String planId) {
		throw new RuntimeException("接口都调错了，在搞啥?");
	}
}