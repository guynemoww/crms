package com.bos.repayback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.plus.LoanCancelRq;
import com.primeton.tsl.BaseVO;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

public class CallBackForEndProcess implements IBIZProcess {
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {

		//审批通过
		String[] xpath = { "bizId" };// 获取相关数据的数组
		List<Object> list = null;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
		} catch (WFServiceException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}

			logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");
			
			DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			change.set("changeId", applyId);
			DatabaseUtil.expandEntity("default", change);
			String summaryId = change.getString("summaryId");//借据ID
			DataObject loanSummary = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			loanSummary.set("summaryId", summaryId);
			DatabaseUtil.expandEntity("default", loanSummary);
			String telNo = loanSummary.getString("nftNo");
			String summaryNum = loanSummary.getString("summaryNum");
			String rcnStan = loanSummary.getString("rcnStan");
			String loanId = loanSummary.getString("loanId");
			//String date = loanSummary.getString("beginDate");
			Date date = (Date)change.get("changeDate");
			SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
			String beginDate = formater.format(date);
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			String loanorg = loanInfo.getString("loanOrg");
			Map maps = new HashMap();
			maps.put("summaryNum", summaryNum);
			Object[] orgArea = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryLoanOpr", maps);
			Map mapo = (Map) orgArea[0];
			String opr = (String) mapo.get("USERNUM");//操作员和出账保持一致
			try {
				//调用撤销接口
				LoanCancelRq rq = new LoanCancelRq();
				rq.setDueNum(summaryNum);
				rq.setTelNo(telNo);
				rq.setRevStan(Long.valueOf(rcnStan));
				BaseVO bvo = new BaseVO();
				bvo.setTranCod("T1110");
				bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
				bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
				bvo.setOpnDep(loanorg);
				bvo.setTrnDep(loanorg);
				bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
				bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
				bvo.setOpr(opr);
				bvo.setOrigFrom("11000");
				bvo.setLegPerCod("9999");
				bvo.setTranFrom("47");
				bvo.setOrigStan(Long.valueOf(rcnStan));
				rq.setBaseVO(bvo);
				Object[] params1 = new Object[2];
				params1[0]=rq;
				params1[1]=beginDate;
				ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.pay.LoanSummary");
				Object[] objs;
				objs = logicComponent.invoke("allBussBackout", params1);
				DataObject vo1 = (DataObject)objs[0];
				BaseVO baseVO = (BaseVO)vo1.get("baseVO");
				String returnCode = (String)baseVO.getErrCod();
				if(!"00000".equals(returnCode)){
					throw new EOSException("撤销交易失败");
				}
				loanSummary.set("summaryStatusCd", "02");
				loanSummary.set("backCd", "02");
				DatabaseUtil.updateEntity("default", loanSummary);
				loanInfo.set("loanStatus", "03");
				DatabaseUtil.updateEntity("default", loanInfo);
				Map map2 = new HashMap();
				map2.put("partyId", loanInfo.get("partyId"));
				DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
				// 更新变更表状态
				change.set("changeStatus", "04");
				DatabaseUtil.updateEntity("default", change);
				// 更新首次检查表状态
				DataObject first = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftFirstCheck");
				first.set("firstCheckId", applyId);
				first.set("checkStatus", "03");
				DatabaseUtil.updateEntity("default", first);
				
				// 更新日常检查表状态
				DataObject normal = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalCheck");
				normal.set("normalCheckId", applyId);
				normal.set("checkStatus", "03");
				DatabaseUtil.updateEntity("default", normal);
				
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
				
			}catch (Exception e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException("流程提交还款撤销业务出错！");
			}

	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		//审批不通过
				String[] xpath = { "bizId" };// 获取相关数据的数组
				try {
					List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
					// 获取主键id
					String applyId = (String) list.get(0);
					if (null == applyId || "".equals(applyId)) {
						logger.info("流程返回的申请ID为空！");
						throw new EOSException("流程返回的申请ID为空");
					}

					logger.info("流程结束，开始处理业务数据------bizId=" + applyId + "------->开始!");

					// 更新合同借据变更表状态
					DataObject change = DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
					change.set("changeId", applyId);
					DatabaseUtil.expandEntity("default", change);
					change.set("changeStatus", "03");
					DatabaseUtil.updateEntity("default", change);
					String summaryId = change.getString("summaryId");//借据ID
					DataObject loanSummary = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					loanSummary.set("summaryId", summaryId);
					DatabaseUtil.expandEntity("default", loanSummary);
					loanSummary.set("summaryStatusCd", "02");
					loanSummary.set("backCd", "02");
					DatabaseUtil.updateEntity("default", loanSummary);
					// 更新首次检查表状态
					DataObject first = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftFirstCheck");
					first.set("firstCheckId", applyId);
					first.set("checkStatus", "03");
					DatabaseUtil.updateEntity("default", first);
					
					// 更新日常检查表状态
					DataObject normal = DataObjectUtil.createDataObject("com.bos.dataset.aft.TbAftNormalCheck");
					normal.set("normalCheckId", applyId);
					normal.set("checkStatus", "03");
					DatabaseUtil.updateEntity("default", normal);
					
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

					logger.info("流程提交，开始处理业务数据------bizId=" + applyId + "------>结束!");
				} catch (Exception e) {
					e.printStackTrace();
					throw new EOSException("流程提交更新业务状态出错！");
				} catch (Throwable e) {
					e.printStackTrace();
					throw new EOSException("流程提交更新业务状态出错！");
				}
				
	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

}
