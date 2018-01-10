package com.bos.crtPro;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.accInfo.ContractSub;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.spring.support.DataObjectUtil;

import commonj.sdo.DataObject;
/**
 * 回调逻辑：提交流程，更新业务表数据
 *  01-未提交;
	02-审批中;
	03-结束;
	04-已删除
 * 
 * */
public class CallBackForSubmitProcessDbjg implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcessDbjg.class);

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		//撤销流程  业务状态更新为06
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String contractId=(String)list.get(0);
			if(null==contractId||"".equals(contractId)){
				logger.info("专业担保机构合作协议流程撤销时，bizID为空！");
				throw new EOSException("专业担保机构合作协议流程撤销时，bizID为空！");
			}
			//将主表状态置为已删除
			DataObject con  = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGuarantOrgInfo");
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			con.set("contractId", contractId);
			con.set("statusCd", "06");
			Date date = GitUtil.getBusiDate();
			con.set("updateTime", date);
			DatabaseUtil.updateEntity("default", con);
			
			//删除账户信息
			DataObject zh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConZh");
			zh.set("contractId", contractId);
			DataObject[] tbConZhs = DatabaseUtil.queryEntitiesByTemplate("default", zh);
			if(tbConZhs==null || tbConZhs.length==0){
				//无账户信息
			}else{
				DatabaseUtil.deleteEntityBatch("default", tbConZhs);
			}
			
	logger.info("------3231------>专业担保机构合作协议流程撤销------bizId="+contractId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>专业担保机构合作协议流程撤销出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>流程提交更新业务状态出错！");
		}
	}

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

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
		//合同调整流程提交  业务状态更新为02
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String contractId=(String)list.get(0);
			if(null==contractId||"".equals(contractId)){
				logger.info("------3231------>专业担保机构合作协议申请ID为空！");
				throw new EOSException("------3231------>专业担保机构合作协议ID为空！");
			}
			Date date = GitUtil.getBusiDate();
			DataObject con  = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGuarantOrgInfo");
			con.set("contractId", contractId);
			con.set("updateTime", date);
			con.set("statusCd", "02");
			DatabaseUtil.updateEntity("default", con);
	logger.info("------3231------>专业担保机构合作协议流程提交，开始业务状态为02------bizId="+contractId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("专业担保机构合作协议流程提交时出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("专业担保机构合作协议流程提交时出错");
		}
	}
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}
	/**
	 * 客户经理提交流程前，进行数据完整性校验（只校验申请数据）
	 * */

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {

		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String[] xpath={"bizId"};//获取相关数据的数组
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String contractId=(String)list.get(0);
			if(null==contractId||"".equals(contractId)){
				logger.info("------3231------>专业担保机构合作协议申请ID为空！");
				throw new EOSException("------3231------>专业担保机构合作协议ID为空！");
			}
			
			String entityName="com.bos.dataset.crt.TbConCreditInfo";
			DataObject con  = DataObjectUtil.createDataObject(entityName);
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			
			RuleService rs = new RuleService();
			Map<String,String> paramMap = new HashMap<String, String>();
			paramMap.put("contractId", contractId);
			
			//基本信息保存校验
			List<MessageObj> msgList = rs.runRule("RCONDB_0001", paramMap);
	        String msg = convertMsg(msgList);
	        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	        	resultMap.put("errorNum", "2");
	        	resultMap.put("errorCode", "2");
	        	resultMap.put("errorContent", msg);
	        	return resultMap;
	        }
	      //担保基金专用账户校验
	        msgList = rs.runRule("RCONDB_0002", paramMap);
	        msg = convertMsg(msgList);
	        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	        	resultMap.put("errorNum", "2");
	        	resultMap.put("errorCode", "2");
	        	resultMap.put("errorContent", msg);
	        	return resultMap;
	        }
			resultMap.put("errorNum", "1");
        	resultMap.put("errorCode", "");
        	resultMap.put("errorContent", "");
		}catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorNum", "2");
        	resultMap.put("errorCode", "2");
        	resultMap.put("errorContent", "执行规则校验出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			resultMap.put("errorNum", "2");
        	resultMap.put("errorCode", "2");
        	resultMap.put("errorContent", "执行规则校验出错！");
		}
		
		
		
		return resultMap;
	
	}

	private String convertMsg(List<MessageObj> msgList){
        StringBuffer sf = new StringBuffer();
        if(msgList != null && !msgList.isEmpty()){
            for (int i = 0; i < msgList.size(); i++) {
                MessageObj t = msgList.get(i);
                if(EngineConstants.RULE_LEVEL_ERROR.equals(t.getMessageType())){
                    sf.append("[(" + (i+1) + "):" + t.getCode() + "," + t.getMessageInfo() + "]");
                }
            }
        }
        if (sf.length() > 0) {
            return sf.toString();
        }
        return "true";
    }
}
