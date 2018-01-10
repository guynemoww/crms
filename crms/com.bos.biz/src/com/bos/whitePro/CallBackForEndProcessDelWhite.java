package com.bos.whitePro;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.biz.SaveBizInfo;
import com.bos.bizApply.GroupInfo;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;
/**
 * 回调逻辑：结束流程，更新业务表数据
 *  01-未提交;
	02-审批中;
	03-结束;
	04-已删除
 * 
 * */
public class CallBackForEndProcessDelWhite implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndProcessDelWhite.class);
	
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	/**
	* 
	* @Title: executeBeforeSubmit
	* @Description: TODO(用于审批同意流程提交前业务逻辑)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	*/
	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	/**
	 * 同意方法
	 * */
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		String conclusion = (String) workitem.get("conclusion");//结论
		try {
			
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String applyId=(String)list.get(0);
			if(null==applyId||"".equals(applyId)){
				logger.info("业务申请流程结束ID为空！");
				throw new EOSException("业务申请流程结束ID为空");
			}
			
		logger.info("业务申请流程结束，回掉逻辑开始------bizId="+applyId+"------->开始!");
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApply);
			String partyId = bizApply.getString("partyId");
			String bizType = bizApply.getString("bizType");
			//更新白名单
			if(applyId!=null&&!"".equals(applyId)){
				DataObject white = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmWhiteCustomer");
				white.set("delWhite", applyId);
				DataObject[] whites= DatabaseUtil.queryEntitiesByTemplate("default", white);
				if(whites!=null){
					for (DataObject dataObject : whites) {
						dataObject.set("cusStatus", "04");
						DatabaseUtil.updateEntity("default", dataObject);
					}
				}
				
			}
		
			
			
			
			
		logger.info("业务申请流程结束，回掉逻辑结束------bizId="+applyId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	/**
	 * 否决
	 * */
	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		logger.info("------3231------>业务申请撤销流程------>begin！");
		String[] xpath={"bizId"};//获取相关数据的数组
		List<Object> list;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String applyId=(String)list.get(0);
				if(null==applyId||"".equals(applyId)){
					logger.info("业务申请撤销流程ID为空！");
					throw new EOSException("业务申请撤销流程ID为空！");
				}
	logger.info("------3231------>业务申请撤销流程------>bizId="+applyId);
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			bizApply.set("oldApplyId",null);
			bizApply.set("statusType", "06");
			DatabaseUtil.updateEntity("default", bizApply);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("applyId",  applyId);

			//删除白名单
			if(applyId!=null&&!"".equals(applyId)){
				DataObject white = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmWhiteCustomer");
				white.set("delWhite", applyId);
				DataObject[] whites= DatabaseUtil.queryEntitiesByTemplate("default", white);
				if(whites!=null){
					for (DataObject dataObject : whites) {
						dataObject.set("cusStatus", "05");
						DatabaseUtil.updateEntity("default", dataObject);
					}
				}
				
			}
	logger.info("------3231------>业务申请撤销流程------>end！");		
		} catch (WFServiceException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		return null;
	}
	
}
