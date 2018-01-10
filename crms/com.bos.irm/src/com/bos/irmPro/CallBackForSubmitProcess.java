package com.bos.irmPro;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.util.DateHelper;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.data.sdo.impl.DataObjectImpl;
import com.primeton.data.sdo.impl.types.DataObjectType;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

public class CallBackForSubmitProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		//提交流程  业务状态更新为02
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String iraApplyId=(String)list.get(0);
				if(null==iraApplyId||"".equals(iraApplyId)){
					logger.info("流程返回的bizID为空！");
					throw new EOSException("流程返回的bizID为空");
				}
				
	logger.info("------3231------>评级流程客户经理提交，开始业务状态为02------bizId="+iraApplyId+"------->开始!");
		DataObject irmApplyB = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingApply");
		irmApplyB.set("iraApplyId", iraApplyId);
		irmApplyB.set("ratingState", "02");
		DatabaseUtil.updateEntity("default", irmApplyB);
	logger.info("------3231------>评级流程客户经理提交，开始业务状态为02------bizId="+iraApplyId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>客户评级流程提交状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>流程提交更新业务状态出错！");
		}

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
				irmApplyA.set("ratingState", "03");
				irmApplyA.set("ratingType", ratingtype);
				DataObject[] irmApplyAs = DatabaseUtil.queryEntitiesByTemplate("default", irmApplyA);
				
				DataObject ratingResult = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingResult");
				String creditRatingCdOld = null;
				//所有生效的评级置为失效
				for(int i=0;i<irmApplyAs.length;i++){
					irmApplyAs[i].set("ratingState", "04");
					DatabaseUtil.updateEntity("default", irmApplyAs[i]);
					creditRatingCdOld =(String)irmApplyAs[i].get("generalAdjustRatingCd");
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
		// TODO 自动生成的方法存根

	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		//撤销流程  业务状态更新为06
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String iraApplyId=(String)list.get(0);
				if(null==iraApplyId||"".equals(iraApplyId)){
					logger.info("评级撤销流程时，bizID为空！");
					throw new EOSException("评级撤销流程时，bizID为空！");
				}
				
	logger.info("------3231------>评级流程撤销，开始业务状态为06------bizId="+iraApplyId+"------->开始!");
		DataObject irmApplyB = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingApply");
		irmApplyB.set("iraApplyId", iraApplyId);
		irmApplyB.set("ratingState", "06");
		DatabaseUtil.updateEntity("default", irmApplyB);
	logger.info("------3231------>评级流程撤销，开始业务状态为06------bizId="+iraApplyId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>客户评级流程提交状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>流程提交更新业务状态出错！");
		}

	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		DataObject[] arr = new DataObject[0];
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> mapB = new HashMap<String, String>();
		String[] xpath={"bizId"};//获取相关数据的数组
	 
			List<Object> list;
			try {
				list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				String iraApplyId=(String)list.get(0);
				if(null==iraApplyId||"".equals(iraApplyId)){
					logger.info("流程返回的iraApplyId为空！");
					throw new EOSException("流程返回的iraApplyId为空");
				}
				map.put("iraApplyId", iraApplyId);
				Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
						"com.bos.irm.modelCalc.model.getRatingCd", map);
				arr = new DataObject[tmp.length];
				
				for (int i = 0; i < tmp.length; i++) {
					String dat = (String) tmp[i];
					logger.info("!!!!!!!!!!!!!!!"+dat);
					if(dat==null){
						mapB.put("errorNum", "2");
						mapB.put("errorCode", "2");
						mapB.put("errorContent", "请将评级内容填写完整！");
						
						return mapB;
					}
						mapB.put("errorNum", "1");
						mapB.put("errorCode", "");
						mapB.put("errorContent", "");
						
						 
					
 				}
			
			} catch (WFServiceException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return mapB;
		
		
		
		

		
	 
	}

}
