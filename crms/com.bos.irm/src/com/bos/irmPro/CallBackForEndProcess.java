package com.bos.irmPro;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.util.DateHelper;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

public class CallBackForEndProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);

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
		// TODO 自动生成的方法存根
		//提交流程  业务状态更新为03
		String[] xpath={"bizId"};//获取相关数据的数组
		String conclusion = (String) workitem.get("conclusion");//结论
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String iraApplyId=(String)list.get(0);
				if(null==iraApplyId||"".equals(iraApplyId)){
					logger.info("评级申请ID为空！");
					throw new EOSException("评级申请ID为空");
				}
		logger.info("------3231------>评级流程流结束节点，开始更新业务状态------bizId="+iraApplyId+"-------评级流程意见结论为------>"+conclusion+"------>");
			if("1".equals(conclusion) || "3".equals(conclusion)){
		logger.info("------3231------>评级流程流结束节点，开始更新业务状态------bizId="+iraApplyId+"------->开始!");
				//查询本次评级信息
				DataObject irmApply = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingApply");
				irmApply.set("iraApplyId", iraApplyId);
				DatabaseUtil.expandEntity("default", irmApply);
				//参与人ID
				String partyId = (String)irmApply.get("partyId");
				String ratingtype=(String)irmApply.get("ratingType");
				//查询该客户下所有生效的评级信息
			logger.info("------3231------>该客户下所有评级结果置为失效------bizId="+iraApplyId+"------>");
				DataObject irmApplyA = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingApply");
				irmApplyA.set("partyId", partyId);
				irmApplyA.set("ratingType", ratingtype);
				irmApplyA.set("ratingState", "03");
				DataObject[] irmApplyAs = DatabaseUtil.queryEntitiesByTemplate("default", irmApplyA);
				
				DataObject ratingResult = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingResult");
				String creditRatingCdOld = null;
				String oldIrmApplyId = null;
				//所有生效的评级置为失效
				for(int i=0;i<irmApplyAs.length;i++){
					irmApplyAs[i].set("ratingState", "04");
					DatabaseUtil.updateEntity("default", irmApplyAs[i]);
					creditRatingCdOld =(String)irmApplyAs[i].get("generalAdjustRatingCd");
					oldIrmApplyId = (String)irmApplyAs[i].get("iraApplyId");
					ratingResult.set("iraApplyId", irmApplyAs[i].get("iraApplyId"));
					DatabaseUtil.expandEntityByTemplate("default", ratingResult, ratingResult);
					if(null != ratingResult.get("irrResultId")){
						ratingResult.set("ratingState", "04");
						DatabaseUtil.updateEntity("default", ratingResult);
					}
				}
			logger.info("------3231------>本笔评级结果置为生效------bizId="+iraApplyId+"------>");
				DataObject irmApplyB = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingApply");
				irmApplyB.set("iraApplyId", iraApplyId);
				irmApplyB.set("ratingState", "03");
				DatabaseUtil.updateEntity("default", irmApplyB);
				
			logger.info("------3231------>评级信息移到评级结果------bizId="+iraApplyId+"------>");	
				//评级结果表 	tb_irm_internal_rating_result
				Date date = GitUtil.getBusiDate();
				DataObject ratingResultN = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingResult");
				ratingResultN.set("iraApplyId", irmApply.get("iraApplyId"));
				ratingResultN.set("partyId", irmApply.get("partyId"));
				DatabaseUtil.expandEntityByTemplate("default", ratingResultN, ratingResultN);
				ratingResultN.set("ratingDt", irmApply.get("applyDate"));
				ratingResultN.set("creditRatingCd", irmApply.get("generalAdjustRatingCd"));
				ratingResultN.set("effectiveStartDt",date);
				ratingResultN.set("effectiveEndDt", DateHelper.calculateDate(date, 1, 0, 0));
				ratingResultN.set("ratingState", "03");
				ratingResultN.set("orgNum", irmApply.get("orgNum"));
				ratingResultN.set("userNum", irmApply.get("userNum"));
				ratingResultN.set("creditRatingCdOld", creditRatingCdOld);
				ratingResultN.set("projectId", oldIrmApplyId);
				DatabaseUtil.updateEntity("default", ratingResultN);
			}else{
			logger.info("------3231------>本笔评级结果置为失效------bizId="+iraApplyId+"------>");
				DataObject irmApplyB = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingApply");
				irmApplyB.set("iraApplyId", iraApplyId);
				irmApplyB.set("ratingState", "04");
				DatabaseUtil.updateEntity("default", irmApplyB);
			logger.info("------3231------>评级信息移到评级失效------bizId="+iraApplyId+"------>");	
			}
			
	logger.info("------3231------>评级流程流结束节点，开始更新业务状态------bizId="+iraApplyId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>评级流程结束时，更新业务数据报错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>评级流程结束时，更新业务数据报错！");
		}
		
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		logger.info("不同意走executeBeforeReject，不同意走executeBeforeReject");	
		String[] xpath={"bizId"};//获取相关数据的数组

		List<Object> list;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String iraApplyId=(String)list.get(0);
			logger.info("------3231------>本笔评级结果置为失效------bizId="+iraApplyId+"------>");
			DataObject irmApplyB = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingApply");
			irmApplyB.set("iraApplyId", iraApplyId);
			irmApplyB.set("ratingState", "04");
			DatabaseUtil.updateEntity("default", irmApplyB);
			logger.info("------3231------>评级信息移到评级失效------bizId="+iraApplyId+"------>");	
		} catch (WFServiceException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
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
