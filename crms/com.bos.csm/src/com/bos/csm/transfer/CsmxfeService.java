/**
 * 
 */
package com.bos.csm.transfer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.bos.grt.collService.CollServiceImplServiceServiceStub.CollServiceCommInter;
import com.bos.grt.collService.CollServiceImplServiceServiceStub.CollServiceCommInterResponse;
import com.bos.pub.DateStyle;
import com.bos.pub.DateUtil;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.CsmTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.plus.BusiOrgSplitRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-11-30 14:18:05
 * 
 */
@Bizlet("")
public class CsmxfeService {
	@Bizlet
	public void actionCsmxfe(String transferId) throws RemoteException {
		DataObject csmxfe = EntityUtil.getEntityById(CsmTableName.TB_CSMXFE_TRANSFER, "transferId", transferId);
		if (!"10".equals(csmxfe.getString("status"))) {
			throw new RuntimeException("非[审批中]状态数据无法执行转移");
		}
		csmxfe.set("status", "90");
		// 记录发给核算的日期时间
		csmxfe.set("updateTime", GitUtil.getBusiDate());
		// 提前更新主表状态，以防重复执行
		DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, csmxfe);

		boolean isXfeBiz = "1".equals(csmxfe.getString("xfeBiz"));
		// 押品信息先移交，核算在日终处理
		if (isXfeBiz) {
			xfeSurety(csmxfe);
		}
		// 需要移交账务信息
		if ("1".equals(csmxfe.getString("xfeAcct"))) {
			xfeAcct(csmxfe);
		}
		if (isXfeBiz) {
			// 移交客户权限
			xfeManagementTeam(csmxfe);
			// 移交业务
			xfeBiz(csmxfe);
		}
		csmxfe.set("status", "95");
		DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, csmxfe);
	}

	@SuppressWarnings("unchecked")
	@Bizlet
	public void passCsmxfe(String transferId) {
		DataObject csmxfe = EntityUtil.getEntityById(CsmTableName.TB_CSMXFE_TRANSFER, "transferId", transferId);
		if (!"95".equals(csmxfe.getString("status"))) {
			throw new RuntimeException("非[待确认]状态数据无法处理");
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("transferId", transferId);

		final String rcvDate = DateUtil.DateToString(csmxfe.getDate("updateTime"), DateStyle.YYYY_MM_DD_8L);
		param.put("rcvDate", rcvDate);
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_APLUS, "com.bos.csm.transfer.transfer.getAplusSplitDue", param);
		if (datas == null || datas.length == 0) {
			return;
		}
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin();
		try {
			for (int i = 0; i < datas.length; i++) {
				param.put("summaryNum", ((Map<String, String>) datas[i]).get("DUE_NUM"));
				DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.activeCsmxfeSummary", param);
			}
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.activeCsmxfeContract", param);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.activeCsmxfeSubcontract", param);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.activeCsmxfeApprove", param);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.activeCsmxfeParty", param);
			csmxfe.set("status", "99");
			// 提前更新主表状态，以防重复执行
			DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, csmxfe);
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private void xfeBiz(DataObject csmxfe) {
		final String transferId = csmxfe.getString("transferId");
		Map<String, String> param = new HashMap<String, String>();
		param.put("transferId", transferId);
		param.put("status", "0");
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getApproveId", param);
		if (datas == null || datas.length == 0) {
			return;
		}
		for (int i = 0; i < datas.length; i++) {
			Map<String, String> map = (Map<String, String>) datas[i];
			map.put("TRANSFER_ID", transferId);
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.callXfeTransfer", map);
		}
	}

	@SuppressWarnings("unchecked")
	private void xfeAcct(DataObject csmxfe) {
		if (!"1".equals(csmxfe.getString("xfeAcct"))) {
			return;
		} else if (csmxfe.getString("newLoanOrg") == null) {
			throw new RuntimeException("需要移交账务的数据，不能没有新会计机构");
		}
		Map<String, String> param = new HashMap<String, String>();
		param.put("transferId", csmxfe.getString("transferId"));
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getSummaryToSubmit", param);
		if (datas == null || datas.length == 0) {
			return;
		}
		final String rcvDate = DateUtil.DateToString(csmxfe.getDate("updateTime"), DateStyle.YYYY_MM_DD_8L);
		final String newLoanOrg = csmxfe.getString("newLoanOrg");
		for (Object obj : datas) {
			Map<String, String> map = (Map<String, String>) obj;
			if (map.get("OLD_LOAN_ORG") == null) {
				// throw new RuntimeException("借据[]缺失原会计机构数据");
				continue;// 机构为空的数据无法插入
			}
			String oldLoanOrg = map.get("OLD_LOAN_ORG");
			map.put("RCV_DATE", rcvDate);
			map.put("LEG_PER_COD", "9999");
			map.put("ORG_DEP_COD", oldLoanOrg);// 原开户机构
			map.put("ORG_DEP", oldLoanOrg);// 原开户机构
			map.put("ORG_DEP_SPR", oldLoanOrg);// 原开户机构
			map.put("DUE_NUM", map.get("SUMMARY_NUM"));// 借据编号
			map.put("NOW_DEP_COD", newLoanOrg);// 目的机构号
			map.put("NOW_DEP_OBJ", newLoanOrg);// 目的机构号
			map.put("NOW_DEP_OBJ_SPR", newLoanOrg);// 目的机构号
			map.put("FLAG", "0");
			Object[] temps = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_APLUS, "com.bos.csm.transfer.transfer.validAplusSplitDue", map);
			if (temps != null && temps.length > 0) {
				String status = ((Map<String, String>) temps[0]).get("FLAG");
				if (!"0".equals(status)) {
					throw new RuntimeException("[" + rcvDate + "]借据[" + map.get("SUMMARY_NUM") + "]在核算移交表中状态为[" + status + "],无法操作");
				}
				DatabaseExt.executeNamedSql(DBUtil.DB_NAME_APLUS, "com.bos.csm.transfer.transfer.removeAplusSplitDue", map);
			}
			DatabaseExt.executeNamedSql(DBUtil.DB_NAME_APLUS, "com.bos.csm.transfer.transfer.insertAplusSplitDue", map);
		}
		param.put("RCV_DATE", rcvDate);
		if (DatabaseExt.countByNamedSql(DBUtil.DB_NAME_APLUS, "com.bos.csm.transfer.transfer.validAplusSplitReq", param) == 0) {
			BusiOrgSplitRq splitRq = new BusiOrgSplitRq();
			splitRq.setBaseVO(CrmsCallPlusImpl.createAplusBaseVO("T1411", csmxfe.getString("oldOrgId"), null));
			splitRq.setExiFlg("1");
			splitRq.setExdBegDate(rcvDate);
			CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
			try {
				BusiOrgSplitRq rs = proxy.executeT1411(splitRq);
				if (!DictContents.APLUS_RECODE.equals(rs.getBaseVO().getErrCod())) {
					throw new RuntimeException(rs.getBaseVO().getErrMsg());
				}
			} catch (Throwable e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void xfeSurety(DataObject csmxfe) throws RemoteException {
		Map<String, String> param = new HashMap<String, String>();
		param.put("transferId", csmxfe.getString("transferId"));
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getSuretyToSubmit", param);
		if (datas == null || datas.length == 0) {
			return;
		}
		CollServiceImplServiceServiceStub services = new CollServiceImplServiceServiceStub(GitUtil.getWebServiceConfig());
		for (Object obj : datas) {
			Map<String, String> map = (Map<String, String>) obj;
			map.put("trans_code", "1110");
			map.put("trans_desc", "业务移交通知");
			map.put("ori_userid", map.get("OLD_USER_ID"));
			map.put("ori_orgid", map.get("OLD_ORG_ID"));
			map.put("new_userid", map.get("NEW_USER_ID"));
			map.put("new_orgid", map.get("NEW_ORG_ID"));
			map.put("clt_no", map.get("SURETY_NO"));
			String msg = GitUtil.mapToJson(map);
			CollServiceCommInter rq = new CollServiceCommInter();
			rq.setIn0(msg);
			CollServiceCommInterResponse rs = services.collServiceCommInter(rq);
			msg = rs.getOut1();
			// 返回信息需要校验
			if ("业务移交成功".equals(msg)) {
				param.put("suretyId", map.get("SURETY_ID"));
				param.put("subcontractId", map.get("SUBCONTRACT_ID"));
				DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.updateCsmxfeSubcontract", param);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void xfeManagementTeam(DataObject csmxfe) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("transferId", csmxfe.getString("transferId"));
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getUserToSubmit", param);
		if (datas == null || datas.length == 0) {
			return;
		}
		final String userNum = csmxfe.getString("newUserId");
		final String orgNum = csmxfe.getString("newOrgId");
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		List<String> list = new ArrayList<String>();
		for (Object obj : datas) {
			Map<String, String> map = (Map<String, String>) obj;
			map.put("newUserNum", userNum == null ? map.get("USER_NUM") : userNum);
			map.put("newOrgNum", orgNum == null ? map.get("ORG_NUM") : orgNum);
			// 当机构拆并或者客户移交时，对方存在客户权，需要删除
			tm.begin();
			try {
				if (DatabaseExt.countByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.validManagement", map) > 0) {
					map.put("updateManagerMode", "new");
					DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.updateManagement", map);
				} else {
					DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.insertManagement", map);
				}
				if ("1".equals(csmxfe.get("keepCsm"))) {
					map.put("USER_PLACING_CD", "02");
					map.put("updateManagerMode", "old");
					DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.updateManagement", map);
				} else {
					DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.removeManagement", map);
				}
				param.put("partyId", map.get("PARTY_ID"));
				DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.updateCsmxfeParty", param);
				String key = map.get("USER_NUM") + "_" + map.get("ORG_NUM");
				if ("4".equals(csmxfe.getString("transferType")) && !list.contains(key)) {
					list.add(key);
					moveUserToNewOrg(map);
				}
				tm.commit();
			} catch (Throwable e) {
				tm.rollback();
				throw new RuntimeException(e);
			}
		}
	}

	private void moveUserToNewOrg(Map<String, String> map) throws Throwable {
		// 把原有客户经理转移到新机构下面
		Map<String, Object> eoMap = getEmpOrgId(map.get("USER_NUM"), map.get("ORG_NUM"));
		Map<String, Object> newEoMap = getEmpOrgId(map.get("newUserNum"), map.get("newOrgNum"));
		eoMap.put("newEmpId", newEoMap.get("EMPID"));
		eoMap.put("newOrgId", newEoMap.get("ORGID"));
		DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.updateEmpOrg", eoMap);
		DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.updateEmpPosition", eoMap);
		DatabaseExt.executeNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.updateOperatorRole", eoMap);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getEmpOrgId(String userNum, String orgNum) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("USER_NUM", userNum);
		map.put("ORG_NUM", orgNum);
		Object[] eos = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.transfer.transfer.getEmpOrgId", map);
		if (eos == null || eos.length != 1) {
			throw new RuntimeException("获取用户[" + userNum + "]机构[" + orgNum + "]信息错误");
		}
		Map<String, Object> eoMap = (Map<String, Object>) eos[0];
		if (eoMap.get("EMPID") == null) {
			throw new RuntimeException("获取用户[" + userNum + "]员工数据错误");
		} else if (eoMap.get("ORGID") == null) {
			throw new RuntimeException("获取机构[" + userNum + "]数据错误");
		}
		return eoMap;
	}

	@SuppressWarnings("unchecked")
	private String getSuretyNo(Object[] datas) {
		StringBuilder sb = new StringBuilder(datas.length * 20);
		sb.append(((Map<String, String>) datas[0]).get("SURETY_NO"));
		for (int i = 1; i < datas.length; i++) {
			sb.append(((Map<String, String>) datas[i]).get("SURETY_NO"));
		}
		return sb.toString();
	}
}
