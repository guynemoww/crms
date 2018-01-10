package com.bos.crdPro;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.DateHelper;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

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
			String limitId=(String)list.get(0);
				if(null==limitId||"".equals(limitId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				
				logger.info("流程提交，开始更新业务状态------bizId="+limitId+"------->开始!");
				DataObject partyLimit = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");
				partyLimit.set("limitId", limitId);
				DatabaseUtil.expandEntity("default", partyLimit);
				partyLimit.set("statusCd", "02");
				DatabaseUtil.updateEntity("default", partyLimit);
				logger.info("流程提交，开始更新业务状态------bizId="+limitId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
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
		String eosEx = null;
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String limitId=(String)list.get(0);
				if(null==limitId||"".equals(limitId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
			DataObject partyLimit = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");
			partyLimit.set("limitId", limitId);
			DatabaseUtil.expandEntity("default", partyLimit);
			if(null == partyLimit.get("partyId")){
				logger.info("------3231------>额度流程提交，第三方客户开始更新业务状态------bizId="+limitId+"------->开始!");	
				//第三方额度	com.bos.crd.CreditLimit.saveThirdPartyLimit
				ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.crd.CreditLimit");
				Object[] params = new Object[1];
				params[0] = limitId;
				Object[] objs=logicComponent.invoke("saveThirdPartyLimit", params);
				Object obj = objs[0];
				logger.info("计算客户额度结果为："+obj.toString());
				if(!"1".equals(obj)){
					eosEx=obj.toString();
					throw new EOSException(obj.toString());
				}
				logger.info("------3231------>额度流程提交，第三方客户开始更新业务状态------bizId="+limitId+"------>结束!");
				return;
			}
			
			
			logger.info("------3231------>额度流程提交，非第三方客户开始更新业务状态------bizId="+limitId+"------->开始!");	
			ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.crd.CreditLimit");
			Object[] params = new Object[1];
			params[0] = limitId;
			Object[] objs=logicComponent.invoke("savePartyLimit", params);
			Object obj = objs[0];
			logger.info("计算客户额度结果为："+obj);
			if(!"1".equals(obj)){
				eosEx=obj.toString();
				throw new EOSException(obj.toString());
			}
	
	
			//计算额度起止期

			DatabaseUtil.expandEntity("default", partyLimit);
			int creditTerm = (Integer)partyLimit.get("creditTerm");
			Date date = GitUtil.getBusiDate();
			partyLimit.set("startDate", date);
			partyLimit.set("endDate", DateHelper.calculateDate(date, 0, creditTerm, 0));
			partyLimit.set("updateTime", date);
			DatabaseUtil.updateEntity("default", partyLimit);
			

	logger.info("------3231------>额度流程提交，非第三方客户开始更新业务状态------bizId="+limitId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(eosEx);
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(eosEx);
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
		//撤销  业务状态更新为06
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String limitId=(String)list.get(0);
				if(null==limitId||"".equals(limitId)){
					logger.info("客户额度撤销流程时，返回的申请ID为空！");
					throw new EOSException("客户额度撤销流程时，返回的申请ID为空！");
				}
				
	logger.info("客户额度流程撤销，开始更新业务状态------bizId="+limitId+"------->开始!");
				DataObject partyLimit = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");
				partyLimit.set("limitId", limitId);
				DatabaseUtil.expandEntity("default", partyLimit);
				partyLimit.set("statusCd", "06");
				DatabaseUtil.updateEntity("default", partyLimit);
	logger.info("客户额度流程撤销，开始更新业务状态------bizId="+limitId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("客户额度撤销流程时，更新业务状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("客户额度撤销流程时，更新业务状态出错！");
		}


	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String[] xpath={"bizId"};//获取相关数据的数组
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String limitId=(String)list.get(0);
			String partyLimitType = "01";
			
			DataObject partyLimit = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");
			partyLimit.set("limitId", limitId);
			DatabaseUtil.expandEntity("default", partyLimit);
			partyLimitType = partyLimit.getString("limitType");
			
			//规则校验
			RuleService rs = new RuleService();
			Map paramMap = new HashMap();
			paramMap.put("limitId", limitId);
			if("01".equals(partyLimitType) ||"05".equals(partyLimitType) ){
				//额度申请信息保存校验
				List<MessageObj> msgList = rs.runRule("RCRD_0001", paramMap);
		        String msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
			}else if("08".equals(partyLimitType)){
				//额度申请信息保存校验
				List<MessageObj> msgList = rs.runRule("RCRD_0002", paramMap);
		        String msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
			}else if("09".equals(partyLimitType)){
				//额度申请信息保存校验
				List<MessageObj> msgList = rs.runRule("RCRD_0003", paramMap);
		        String msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
			}
			resultMap.put("errorNum", "1");
        	resultMap.put("errorCode", "");
        	resultMap.put("errorContent", "");
		} catch (Exception e) {
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
