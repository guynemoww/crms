package com.bos.riskPro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.EosUtil;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;
import com.primeton.bfs.tp.common.exception.EOSException;

public class CallBackForSubmitProcess implements IBIZProcess {

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		// 流程提交时，更新最新审批结果到审批表
		String acApplyId = EosUtil.getBizId(processInstId);
		HashMap parameter = new HashMap();
		parameter.put("acApplyId", acApplyId);
		if (DatabaseExt.countByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.risk.sort.validClsCanSubmit", parameter) < 1) {
			throw new RuntimeException("没有修改明细或者明细没有变化，不需要处理");
		}
		DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateApproveResult", parameter);
	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		try {
			// 流程结束
			String[] xpath = { "bizId" };
			List ls = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			HashMap parameter = new HashMap();
			parameter.put("acApplyId", ls.get(0));
			parameter.put("status", "2");// 审批同意完成
			// 更新审批结果
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateApproveResult", parameter);
			// 更新审批状态
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateClaApplyStatus", parameter);
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateClaDebtStatus", parameter);

			// 将审批后的数据新增到tb_cla_debt_detailinfo表
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.insertApproveResult", parameter);

			// 同步更新合同表的审批结果
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateConClsResult", parameter);

			// 同步更新借据表的审批结果
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateLoanClsResult", parameter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException();
		}

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		try {
			// 流程退回
			// 删除改节点的审批意见
			String[] xpath = { "bizId", "workItemId" };
			List ls = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			HashMap params = new HashMap();
			params.put("acApplyId", ls.get(0));
			params.put("posicode", workitem.get("workItemId"));
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.deleteClaApprove", params);

			// 恢复上次审批结果
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateApproveBeforeResult", params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException();
		}

	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		try {
			// 流程拒绝
			String[] xpath = { "bizId" };
			List ls = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			HashMap parameter = new HashMap();
			parameter.put("acApplyId", ls.get(0));
			parameter.put("status", "3");// 审批拒绝完成
			// 更新审批结果
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateApproveResult", parameter);
			// 更新审批状态
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateClaApplyStatus", parameter);
			parameter.put("status", "0");// 恢复初始
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateClaDebtStatus", parameter);

			// 将审批后的数据新增到tb_cla_debt_detailinfo表
			// DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.insertApproveResult", parameter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		// 流程撤销
		try {
			String[] xpath = { "bizId" };
			List ls = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			HashMap parameter = new HashMap();
			parameter.put("acApplyId", ls.get(0));
			parameter.put("status", "0");// 初始录入
			// 恢复待分类表tb_cla_debt_detailinfo为初始状态0
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateClaDebtStatus", parameter);

			// 删除分类业务数据
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.cleanClaApproveDetail", parameter);
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.cleanClaDeltInfo", parameter);
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.cleanClaCustInfo", parameter);
			DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.cleanClaApply", parameter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		String[] xpath = { "bizType", "bizId", "custFlag" };
		try {
			List ls = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 如果是季度调整,并且是大客户
			if ("quar".equalsIgnoreCase((String) ls.get(0)) && "big".equals(ls.get(2))) {
				// 流程提交时，更新最新审批结果到审批表
				HashMap parameter = new HashMap();
				parameter.put("acApplyId", ls.get(1));
				int objs = DatabaseExt.countByNamedSql("default", "com.bos.risk.startProcessLogic.checkDraftReport", parameter);
				if (objs == 0) {
					// errorNum=1通过errorNum=2不通过errorNum=3询问是否通过，errorCode提示码，errorContent提示内容
					Map<String, String> msg = new HashMap<String, String>();
					msg.put("errorNum", "2");
					msg.put("errorCode", "claReprotIsNull");
					msg.put("errorContent", "请上传分类报告！");
					return msg;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		return null;

	}

}
