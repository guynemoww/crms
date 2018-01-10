package com.bos.bizPro;

import java.math.BigDecimal;
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
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.spring.support.DataObjectUtil;

import commonj.sdo.DataObject;
/**
 * 小贷拒贷流程
 * 回调逻辑：提交流程，更新业务表数据
 *  01-未提交;
	02-审批中;
	03-结束;
	04-已删除
 * 
 * */
public class CallBackForEndJuDai implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndJuDai.class);

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

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
	}
	/**
	 * 小贷拒贷流程
	 * 生效后，如果结论为同意，将本笔业务置为失效
	 * 将被拒贷的业务审批结论置为2 不同意
	 * 将业务的批复额度置为失效
	 * 
	 * */
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根
		String[] xpath={"bizId"};//获取相关数据的数组
		String conclusion = (String) workitem.get("conclusion");//结论
		String eosEx="拒贷流程更新业务状态出错！";
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String applyId=(String)list.get(0);
				if(null==applyId||"".equals(applyId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				
	logger.info("------3231------>拒贷结束流程------bizId="+applyId+"------->开始!");
				//查询被拒贷业务申请信息
				DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
				bizApply.set("applyId", applyId);
				DatabaseUtil.expandEntity("default", bizApply);
				String oldApplyId=(String)bizApply.get("oldApplyId");
				
				/*//查询被拒贷业务额度信息
				DataObject creditLimit = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdCreditLimit");
				creditLimit.set("applyId", oldApplyId);
				creditLimit.set("statusCd", "03");
				DatabaseUtil.expandEntityByTemplate("default", creditLimit, creditLimit);
				
				if((creditLimit.get("creditAmt")).equals((creditLimit.get("availableAmt")))){*/
					//本笔业务置为失效
					bizApply.set("statusType", "04");
					//获取时间com.bos.pub.GitUtil.getBusiDate
					Date date = GitUtil.getBusiDate();
					bizApply.set("updateTime", date);
					DatabaseUtil.updateEntity("default", bizApply);
					//被拒贷业务批复审批结论置为不同意
					DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
					bizApprove.set("applyId", oldApplyId);
					DatabaseUtil.expandEntityByTemplate("default", bizApprove, bizApprove);
					bizApprove.set("approveConclusion", "2");
					DatabaseUtil.updateEntity("default", bizApprove);
					/*//被拒贷业务额度置为失效
					creditLimit.set("statusCd", "04");
					DatabaseUtil.updateEntity("default", creditLimit);*/
					//被拒贷的业务  合同置为失效
					DataObject amountApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
					amountApprove.set("approveId", bizApprove.get("approveId"));
					DatabaseUtil.expandEntityByTemplate("default", amountApprove, amountApprove);
					DataObject amountDetail = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
					amountDetail.set("amountId", amountApprove.get("amountId"));
					DatabaseUtil.expandEntityByTemplate("default", amountDetail, amountDetail);
					DataObject conInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
					conInfo.set("amountDetailId", amountDetail.get("amountDetailId"));
					conInfo.set("conStatus", "03");
					DatabaseUtil.expandEntityByTemplate("default", conInfo, conInfo);
					if(null == conInfo.get("contractId")){
						
					}else{
						conInfo.set("conStatus", "04");
						DatabaseUtil.updateEntity("default", conInfo);
						//从合同状态更新 20151222--BUG #8416 已经拒绝了的抵押贷款，额度没有释放-------begin-----
						Map map = new HashMap();
						map.put("contractId", conInfo.get("contractId"));
						DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateConInfoRelStatus", map);
						//释放额度
						map.put("partyId", conInfo.get("partyId"));
						DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map);
						//从合同状态更新 20151222--BUG #8416 已经拒绝了的抵押贷款，额度没有释放-------end--------
					}
					
					
					/*}else{
					eosEx="被拒贷业务已放款！";
					throw new EOSException(eosEx);
				}*/
	logger.info("------3231------>拒贷结束流程------bizId="+applyId+"------>结束!");
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
		// TODO 自动生成方法存根

	}
	/**
	 * 客户经理提交流程前，进行数据完整性校验（只校验申请数据）
	 * */

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		return null;
	}

}
