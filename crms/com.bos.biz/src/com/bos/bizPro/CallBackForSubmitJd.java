package com.bos.bizPro;

import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.engine.component.ILogicComponent;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
/**
 * 回调逻辑：提交流程，更新业务表数据
 *  01-未提交;
	02-审批中;
	03-结束;
	04-已删除
 * 
 * */
public class CallBackForSubmitJd implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitJd.class);

	//(用于撤销流程后业务逻辑)
	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根
		String[] xpath={"bizId"};//获取相关数据的数组
		//String conclusion = (String) workitem.get("conclusion");//结论
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String amountId=(String)list.get(0);
			if(null==amountId||"".equals(amountId)){
				logger.info("解冻撤销申请ID为空！");
				throw new EOSException("解冻申请ID为空");
			}
			logger.info("------3231------>解冻撤销流程流结束节点，开始更新业务状态------amountId="+amountId+"-------开始！！！！！！！-->");
			ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.irm.irmApply.irmApply");
			try {
				//提交流程
				logicComponent.invoke("saveIrmDeleteJdCoreImpl", new Object[] { amountId });
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException("------3231------>解冻流程结束时，更新业务数据报错！");
			}
			logger.info("------3231------>解冻撤销流程流结束节点，开始更新业务状态------amountId="+amountId+"-------结束！！！！！！！");
		}catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>解冻流程结束时，更新业务数据报错！");
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
		// TODO 自动生成方法存根
	}
	/**
	 * (用于普通流程结束前业务逻辑)
	 */
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根
		//提交流程  业务状态更新为03已生效
		String[] xpath={"bizId"};//获取相关数据的数组
		//String conclusion = (String) workitem.get("conclusion");//结论
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String amountId=(String)list.get(0);
			if(null==amountId||"".equals(amountId)){
				logger.info("解冻申请ID为空！");
				throw new EOSException("解冻申请ID为空");
			}
			logger.info("------3231------>解冻流程流结束节点，开始更新业务状态------amountId="+amountId+"-------开始！！！！！！！-->");
			ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.irm.irmApply.irmApply");
			try {
				//提交流程
				logicComponent.invoke("saveIrmSubmitJdCoreImpl", new Object[] { amountId });
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException("------3231------>解冻流程结束时，更新业务数据报错！");
			}
			logger.info("------3231------>解冻流程流结束节点，开始更新业务状态------amountId="+amountId+"-------结束！！！！！！！");
		}catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>解冻流程结束时，更新业务数据报错！");
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
