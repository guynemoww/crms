package com.bos.repayback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.plus.LoanCancelControlRq;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.tsl.BaseVO;

import commonj.sdo.DataObject;

public class CallBackForSubmitProcess implements IBIZProcess {
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {

		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			
			logger.info("流程提交，开始更新还款撤销业务状态------bizId=" + applyId + "------->开始!");
			
			
			DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			change.set("changeId", applyId);
			DatabaseUtil.expandEntity("default", change);
			String summaryId = change.getString("summaryId");//借据ID
			DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			loanSummary.set("summaryId", summaryId);
			DatabaseUtil.expandEntity("default", loanSummary);
			String telNo = loanSummary.getString("nftNo");
			String summaryNum = loanSummary.getString("summaryNum");
			String rcnStan = loanSummary.getString("rcnStan");
			String loanId = loanSummary.getString("loanId");
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			String loanorg = loanInfo.getString("loanOrg");
			//调用核算撤销控制信息接口
			LoanCancelControlRq rq = new LoanCancelControlRq();
			BaseVO bvo = new BaseVO();
			rq.setTelNo(telNo);
			rq.setDueNum(summaryNum);
			rq.setRevStan(Long.valueOf(rcnStan));
			bvo.setTranCod("T1210");
			bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setOpnDep(loanorg);
			bvo.setTrnDep(loanorg);
			bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
			bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
			bvo.setOpr(GitUtil.getCurrentUserId());
			bvo.setOrigFrom("11000");
			bvo.setLegPerCod("9999");
			bvo.setTranFrom("47");
			bvo.setOrigStan(Long.valueOf(rcnStan));
			rq.setBaseVO(bvo);
			Object[] params1 = new Object[1];
			params1[0] = rq;
			ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.pay.LoanSummary");
			Object[] objs = null;
			objs = logicComponent.invoke("allBussBackoutCheck", params1);
			DataObject vo1 = (DataObject)objs[0];
			BaseVO baseVO = (BaseVO)vo1.get("baseVO");
			String returnCode = (String)baseVO.getErrCod();
			if(!"00000".equals(returnCode)){
				throw new EOSException(baseVO.getErrMsg());
			}
			logger.info("------>放款流程撤销提交后,修改状态------bizId="+loanId+"------->begin!");
			Map<String,String> map = new HashMap<String,String>();
			map.put("backCd", "08");//审批中
			DatabaseExt.executeNamedSql("default", "com.bos.pay.LoanSummary.updateStatus", map);
			logger.info("------>修改借据撤销状态结束----->");
			// 更新变更表状态
			change.set("changeStatus", "02");
			DatabaseUtil.updateEntity("default", change);
			
			// 更新首次检查表状态
			DataObject first = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftFirstCheck");
			first.set("firstCheckId", applyId);
			first.set("checkStatus", "02");
			DatabaseUtil.updateEntity("default", first);
			
			// 更新日常检查表状态
			DataObject normal = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalCheck");
			normal.set("normalCheckId", applyId);
			DatabaseUtil.expandEntity("default", normal);
			if(null != normal && null != normal.getString("isFinish")) {
				if("1".equals(normal.getString("isFinish"))) {
					normal.set("checkStatus", "02");
					DatabaseUtil.updateEntity("default", normal);
				}else {
					throw new EOSException("请填写日常检查信息！");
				}
			}
			
			// 更新重点检查表状态
			DataObject point = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftPointCheck");
			point.set("checkId", applyId);
			point.set("checkStatus", "02");
			DatabaseUtil.updateEntity("default", point);
			
			// 更新授信到期前检查表状态
			DataObject expire = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftExpireCheck");
			expire.set("checkId", applyId);
			expire.set("checkStatus", "02");
			DatabaseUtil.updateEntity("default", expire);

			logger.info("流程提交，开始更新还款撤销业务状态------bizId=" + applyId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新还款撤销业务状态出错！");
		}

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {


	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {

		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");
			
			HashMap map = new HashMap();
			map.put("applyId", applyId);
			
			DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			change.set("changeId", applyId);
			DatabaseUtil.expandEntity("default", change);
			
			DataObject loanInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			loanInfo.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", loanInfo);
			String summaryNum = loanInfo.getString("summaryNum");//借据编号
			logger.info("------>放款流程撤销拒绝,修改状态------bizId="+applyId+"------->begin!");
			map.put("backCd", "02");//正常
			map.put("summaryNum", summaryNum);//借据编号
			DatabaseExt.executeNamedSql("default", "com.bos.pay.LoanSummary.updateStatus", map);
			logger.info("------>修改借据撤销状态结束----->");
			// 更新变更表状态
			change.set("changeStatus", "02");
			DatabaseUtil.updateEntity("default", change);
			
			// 更新首次检查表状态
			DataObject first = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftFirstCheck");
			first.set("firstCheckId", applyId);
			first.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", first);
			
			// 更新日常检查表状态
			DataObject normal = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalCheck");
			normal.set("normalCheckId", applyId);
			DatabaseUtil.expandEntity("default", normal);
			if(null != normal && null != normal.getString("isFinish")) {
				if("1".equals(normal.getString("isFinish"))) {
					normal.set("checkStatus", "03");
					DatabaseUtil.updateEntity("default", normal);
				}else {
					throw new EOSException("请填写日常检查信息！");
				}
			}
			
			// 更新重点检查表状态
			DataObject point = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftPointCheck");
			point.set("checkId", applyId);
			point.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", point);
			
			// 更新授信到期前检查表状态
			DataObject expire = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftExpireCheck");
			expire.set("checkId", applyId);
			expire.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", expire);
			logger.info("流程提交，开始更新还款撤销拒绝修改状态------bizId=" + applyId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程撤销拒绝修改状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程撤销拒绝修改状态出错！");
		}


	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {

		try {
			String[] xpath = { "bizId" };// 获取相关数据的数组
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");
			
			HashMap map = new HashMap();
			map.put("applyId", applyId);
			
			DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			change.set("changeId", applyId);
			DatabaseUtil.expandEntity("default", change);
			logger.info("------>放款流程撤销拒绝,修改状态------bizId="+applyId+"------->begin!");
			DataObject summary = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summary.set("summaryId", change.getString("summaryId"));
			DatabaseUtil.expandEntity("default", summary);
			String summaryNum = summary.getString("summaryNum");//借据编号
			map.put("backCd", "02");//正常
			map.put("summaryNum", summaryNum);
			DatabaseExt.executeNamedSql("default", "com.bos.pay.LoanSummary.updateStatus", map);
			logger.info("------>修改借据撤销状态结束----->");
			// 更新变更表状态
			change.set("changeStatus", "02");
			DatabaseUtil.updateEntity("default", change);
			
			// 更新首次检查表状态
			DataObject first = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftFirstCheck");
			first.set("firstCheckId", applyId);
			first.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", first);
			
			// 更新日常检查表状态
			DataObject normal = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalCheck");
			normal.set("normalCheckId", applyId);
			DatabaseUtil.expandEntity("default", normal);
			if(null != normal && null != normal.getString("isFinish")) {
				if("1".equals(normal.getString("isFinish"))) {
					normal.set("checkStatus", "03");
					DatabaseUtil.updateEntity("default", normal);
				}else {
					throw new EOSException("请填写日常检查信息！");
				}
			}
			
			// 更新重点检查表状态
			DataObject point = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftPointCheck");
			point.set("checkId", applyId);
			point.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", point);
			
			// 更新授信到期前检查表状态
			DataObject expire = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftExpireCheck");
			expire.set("checkId", applyId);
			expire.set("checkStatus", "03");
			DatabaseUtil.updateEntity("default", expire);
			logger.info("流程提交，开始更新还款撤销拒绝修改状态------bizId=" + applyId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程撤销拒绝修改状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程撤销拒绝修改状态出错！");
		}



	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

}
