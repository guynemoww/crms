/**
 * 
 */
package com.bos.csm.transfer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.bizApply.BizProcess;
import com.bos.pub.GitUtil;
import com.bos.pub.StringUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.CsmTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-11-27 16:59:52
 * 
 */
@Bizlet("")
public class CsmxfeDao {

	@Bizlet
	public DataObject createCsmxfe(String transferType, boolean havWorkFlow) throws Throwable {
		if (StringUtil.isNull(transferType)) {
			throw new RuntimeException("请选择业务类型");
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("userNum", GitUtil.getCurrentUserId());
		param.put("orgNum", GitUtil.getCurrentOrgCd());
		param.put("status", "00");
		// 测试用 为方便测试暂时屏蔽
		/*
		 * if (DatabaseExt.countByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.searchCsmxfe", param) != 0) { throw new RuntimeException("已经创建有新的转移申请，请先完成后在创建"); }
		 */
		DataObject csmxfe = DataObjectUtil.createDataObject(CsmTableName.TB_CSMXFE_TRANSFER);
		csmxfe.set("userId", GitUtil.getCurrentUserId());
		csmxfe.set("orgId", GitUtil.getCurrentOrgCd());
		csmxfe.set("createTime", new Date());
		csmxfe.set("transferType", transferType);
		csmxfe.set("xfeBiz", "1");// 默认都需要移交业务，机构拆并在页面上没有该数据录入
		csmxfe.set("status", "00");
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin();
		try {
			DatabaseUtil.insertEntity(DBUtil.DB_NAME_DEF, csmxfe);
			if (havWorkFlow) {
				String processId = new BizProcess().createBpsProcessThrowError(csmxfe.getString("transferId"), "csmxfe");
				csmxfe.set("processId", processId);
			}
			DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, csmxfe);
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			throw new RuntimeException(e);
		}
		return csmxfe;
	}

	@Bizlet
	public void saveCsmxfe(DataObject csmxfe) {
		if (!DataObjectUtil.checkEntityName(csmxfe, CsmTableName.TB_CSMXFE_TRANSFER)) {
			throw new RuntimeException("非移交数据，无法保存");
		}
		String[][] strs = { { "transferId", "转移业务ID" }, { "transferType", "业务类型" }, { "xfeAcct", "是否移交账务" }, { "oldOrgId", "原机构信息" }, { "newOrgId", "新机构信息" } };
		for (String[] s : strs) {
			String temp = csmxfe.getString(s[0]);
			if (StringUtil.isNull(temp)) {
				throw new RuntimeException("[" + s[1] + "]为必填内容");
			}
		}
		String temp = csmxfe.getString("newLoanOrg");
		if ("1".equals(csmxfe.get("xfeAcct")) && StringUtil.isNull(temp)) {
			throw new RuntimeException("需要移交账务时，必须填入[新会计机构]");
		} else if (StringUtil.isNotNull(csmxfe.getString("newUserId")) && csmxfe.getString("newUserId").equals(csmxfe.getString("oldUserId"))) {
			throw new RuntimeException("[新客户经理]与[原客户经理]不能相同");
		} else if (csmxfe.getString("oldOrgId").equals(csmxfe.getString("newOrgId")) && StringUtil.isNull(csmxfe.getString("newUserId"))) {
			throw new RuntimeException("[新机构]与[原机构]不能相同");
		} else if ("0".equals(csmxfe.getString("xfeBiz")) && "0".equals(csmxfe.getString("xfeAcct"))) {
			throw new RuntimeException("[是否移交业务]与[是否移交账务]不能同时选择[否]");
		}
		editValid(csmxfe.getString("transferId"));
		Map<String, String> param = new HashMap<String, String>();
		param.put("transferId", csmxfe.getString("transferId"));
		if (DatabaseExt.countByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getApproveId", param) != 0) {
			throw new RuntimeException("保存失败:存在明细数据，无法修改申请信息");
		}
		DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, csmxfe);
	}

	@SuppressWarnings("unchecked")
	@Bizlet
	public void removeCsmxfe(String transferId) {
		DataObject csmxfe = EntityUtil.getEntityById(CsmTableName.TB_CSMXFE_TRANSFER, "transferId", transferId);
		editValid(csmxfe);
		Map<String, String> param = new HashMap<String, String>();
		param.put("transferId", csmxfe.getString("transferId"));
		param.put("status", "0");
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getApproveId", param);
		if (datas != null && datas.length > 0) {
			for (int i = 0; i < datas.length; i++) {
				removeDetail(transferId, ((Map<String, String>) datas[i]).get("APPROVE_ID"), false);
			}
		}
		DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.removeCsmxfeParty", param);
		DatabaseUtil.deleteEntity(DBUtil.DB_NAME_DEF, csmxfe);
	}

	@Bizlet
	public void approveCsmxfeEos(DataObject transfer) {
		approveCsmxfe(transfer, false);
	}

	public void approveCsmxfe(DataObject transfer, boolean useWorkFlow) {
		DataObject csmxfe = EntityUtil.getEntityById(CsmTableName.TB_CSMXFE_TRANSFER, "transferId", transfer.getString("transferId"));
		// 90是为了移交中途报错可以再次移交留的后门
		if (!GitUtil.contain(csmxfe.getString("status"), new String[] { "00", "90" })) {
			throw new RuntimeException("非[创建]状态的申请无法操作");
		} else if (!csmxfe.getString("status").equals(csmxfe.getString("status"))) {
			throw new RuntimeException("当前数据已改变，请刷新后再试");
		} else if (!useWorkFlow && csmxfe.get("processId") != null) {
			throw new RuntimeException("进入流程的业务无法操作");
		}
		csmxfe.set("status", "10");
		DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, csmxfe);
	}

	@Bizlet
	public void createDetail(final String transferId, DataObject[] params) {
		for (DataObject param : params) {
			createDetail(transferId, param.getString("approveId"), true);
		}
	}

	@SuppressWarnings("unchecked")
	@Bizlet
	public void createDetailByParty(final String transferId, final String orgNum, DataObject[] params) {
		// 不强制传userNum 是为了支持同一机构下不同经理名下客户信息转入同一经理名下
		if (params == null || params.length == 0) {
			return;
		}
		editValid(transferId);
		String partyId;
		for (DataObject param : params) {
			partyId = param.getString("partyId");
			if (partyId == null || partyId.trim().isEmpty()) {
				throw new RuntimeException("没有获取到[客户编号]信息");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("transferId", transferId);
			map.put("partyId", partyId);
			map.put("orgNum", orgNum);
			map.put("userNum", param.getString("userNum"));
			Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getApproveIdToInsert", map);
			if (datas != null && datas.length > 0) {
				// 规则检查的sql太多，如果没有数据情况下可以不处理
				createDetailByPartyValid(param);
				for (int i = 0; i < datas.length; i++) {
					createDetail(transferId, ((Map<String, String>) datas[i]).get("APPROVE_ID"), false);
				}
			}
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.insertCsmxfeParty", map);
		}
	}

	@SuppressWarnings("unchecked")
	@Bizlet
	public void createDetailByUser(DataObject param) {
		final String transferId = param.getString("transferId");
		editValid(transferId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transferId", transferId);
		map.put("userNum", param.getString("userNum"));
		map.put("orgNum", param.getString("orgNum"));
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getApproveIdToInsert", map);
		if (datas != null && datas.length >= 0) {
			// 只有客户没有明细的数据不需要验证
			createDetailByUserValid(param);
			for (int i = 0; i < datas.length; i++) {
				createDetail(transferId, (String) ((Map<String, Object>) datas[i]).get("APPROVE_ID"), false);
			}
		}
		DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.insertCsmxfePartyByUser", map);
	}

	@Bizlet
	public void removeDetail(final String transferId, DataObject[] params) {
		for (DataObject param : params) {
			removeDetail(transferId, param.getString("approveId"), true);
		}
	}

	@SuppressWarnings("unchecked")
	@Bizlet
	public void removeDetailByParty(DataObject param) {
		final String transferId = param.getString("transferId");
		final String partyId = param.getString("partyId");
		if (partyId == null) {
			throw new RuntimeException("没有获取到[客户编号]信息");
		}
		editValid(transferId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transferId", transferId);
		map.put("partyId", partyId);
		map.put("status", "0");
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getApproveId", map);
		if (datas != null && datas.length > 0) {
			for (int i = 0; i < datas.length; i++) {
				removeDetail(transferId, ((Map<String, String>) datas[i]).get("APPROVE_ID"), false);
			}
		}
		DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.removeCsmxfeParty", map);
	}

	@SuppressWarnings("unchecked")
	@Bizlet
	public void removeDetailByUser(DataObject param) {
		final String transferId = param.getString("transferId");
		editValid(transferId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transferId", transferId);
		map.put("userNum", param.getString("userNum"));
		map.put("status", "0");
		DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.removeCsmxfeParty", map);
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getApproveId", map);
		if (datas == null || datas.length == 0) {
			return;
		}
		for (int i = 0; i < datas.length; i++) {
			Map<String, Object> dataMap = (Map<String, Object>) datas[i];
			removeDetail(transferId, (String) dataMap.get("APPROVE_ID"), false);
		}
	}

	public void editValid(String csmxfeId) {
		editValid(EntityUtil.getEntityById(CsmTableName.TB_CSMXFE_TRANSFER, "transferId", csmxfeId));
	}

	public void editValid(DataObject csmxfe) {
		if (!"00".equals(csmxfe.getString("status"))) {
			throw new RuntimeException("非[创建]状态的移交申请不允许编辑");
		} else if (!GitUtil.getCurrentUserId().equals(csmxfe.get("userId"))) {
			throw new RuntimeException("非本人提交的申请无法操作");
		}
	}

	private void createDetailByUserValid(DataObject obj) {
		String[][] rules = { { "com.bos.csm.transfer.transfer.validApply", "该客户经理名下存在在途的申请信息：" }, //
				{ "com.bos.csm.transfer.transfer.validApprove", "该客户经理名下存在在途的批复信息:" }, //
				{ "com.bos.csm.transfer.transfer.validContract", "该客户经理名下存在在途的合同信息:" }, //
				{ "com.bos.csm.transfer.transfer.validCredit", "该客户经理名下存在在途的综合授信协议信息:" },//
				{ "com.bos.csm.transfer.transfer.validLoan", "该客户经理名下存在在途的放款信息:" },//
				{ "com.bos.csm.transfer.transfer.validChange", "该客户经理名下存在在途的贷后调整信息:" }, //
				{ "com.bos.csm.transfer.transfer.validAftExpire", "该客户经理名下存在在途的贷后期满检查信息:" } };
		Map<String, String> param = new HashMap<String, String>();
		param.put("userNum", obj.getString("userNum"));
		createDetailValid(rules, param);
	}

	private void createDetailByPartyValid(DataObject obj) {
		String[][] rules = { { "com.bos.csm.transfer.transfer.validApprove", "该客户名下存在在途的批复信息:" }, //
				{ "com.bos.csm.transfer.transfer.validContract", "该客户名下存在在途的合同信息:" }, //
				{ "com.bos.csm.transfer.transfer.validCredit", "该客户名下存在在途的综合授信协议信息:" },//
				{ "com.bos.csm.transfer.transfer.validLoan", "该客户名下存在在途的放款信息:" },//
				{ "com.bos.csm.transfer.transfer.validChange", "该客户名下存在在途的贷后调整信息:" },//
				{ "com.bos.csm.transfer.transfer.validAftExpire", "该客户名下存在在途的贷后期满检查信息:" } };
		Map<String, String> param = new HashMap<String, String>();
		param.put("partyId", obj.getString("partyId"));
		createDetailValid(rules, param);
	}

	private void createDetailValid(String approveId) {
		// String[] rules = { "PUB_CON_BIZ", "PUB_LOAN_CHANGE", "PUB_LOAN_BIZ", "PUB_FIRST_CHECK", "PUB_POINT_CHECK", "PUB_CLASS_BIZ" };
		String[][] rules = { { "com.bos.csm.transfer.transfer.validContract", "批复下存在在途的合同信息:" }, //
				{ "com.bos.csm.transfer.transfer.validCredit", "批复下存在在途的综合授信协议信息:" },//
				{ "com.bos.csm.transfer.transfer.validLoan", "批复下存在在途的放款信息:" },//
				{ "com.bos.csm.transfer.transfer.validChange", "批复下存在在途的贷后调整信息:" }, //
				{ "com.bos.csm.transfer.transfer.validAftExpire", "批复下存在在途的贷后期满检查信息:" } };
		Map<String, String> param = new HashMap<String, String>();
		param.put("approveId", approveId);
		createDetailValid(rules, param);
	}

	@SuppressWarnings("unchecked")
	private void createDetailValid(String[][] rules, Map<String, String> param) {
		for (String[] r : rules) {
			Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, r[0], param);
			if (datas == null || datas.length == 0) {
				return;
			}
			String msg = r[1] + "<br/>";
			int i = 1;
			for (Object obj : datas) {
				String num = ((Map<String, String>) obj).get("NUM");
				if (num == null || num.isEmpty()) {
					continue;
				}
				if (i % 5 == 0) {
					msg += "<br/>";
				}
				msg += num + ",";
				i++;
			}
			throw new RuntimeException(msg);
		}
	}

	private void createDetail(final String transferId, final String approveId, boolean validate) {
		if (transferId == null || transferId.isEmpty()) {
			throw new RuntimeException("无法获取转移信息编号");
		} else if (approveId == null || approveId.isEmpty()) {
			throw new RuntimeException("无法获取批复信息编号");
		}
		if (validate) {
			editValid(transferId);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transferId", transferId);
		map.put("approveId", approveId);
		// 当批复已经存在于该移交表中不在处理
		if (DatabaseExt.countByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getApproveId", map) > 0) {
			return;
		}
		if (validate) {
			createDetailValid(approveId);
		}
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin();
		try {
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.insertCsmxfeApprove", map);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.insertCsmxfeContract", map);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.insertCsmxfeSummary", map);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.insertCsmxfeSubcontract", map);
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			throw new RuntimeException(e);
		}
	}

	private void removeDetail(final String transferId, final String approveId, boolean validate) {
		if (transferId == null || transferId.isEmpty()) {
			throw new RuntimeException("无法获取转移信息编号");
		} else if (approveId == null || approveId.isEmpty()) {
			throw new RuntimeException("无法获取批复信息编号");
		}
		if (validate) {
			editValid(transferId);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transferId", transferId);
		map.put("approveId", approveId);
		// 当批复不存在于该移交表中不在处理
		if (DatabaseExt.countByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getApproveId", map) < 1) {
			return;
		}
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin();
		try {
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.removeCsmxfeApprove", map);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.removeCsmxfeContract", map);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.removeCsmxfeSummary", map);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.removeCsmxfeSubcontract", map);
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			e.printStackTrace();
		}
	}
}
