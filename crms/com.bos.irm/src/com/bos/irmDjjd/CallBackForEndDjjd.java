package com.bos.irmDjjd;

import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.engine.component.ILogicComponent;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
//额度冻结流程回调函数
public class CallBackForEndDjjd implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndDjjd.class);

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
		//提交流程  业务状态更新为07冻结
		String[] xpath={"bizId"};//获取相关数据的数组
		//String conclusion = (String) workitem.get("conclusion");//结论
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String amountId=(String)list.get(0);
			if(null==amountId||"".equals(amountId)){
				logger.info("冻结申请ID为空！");
				throw new EOSException("冻结申请ID为空");
			}
//			logger.info("------3231------>冻结流程流结束节点，开始更新业务状态------amountId="+amountId+"-------开始！！！！！！！-->");
//			ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.irm.irmApply.irmApply");
//			try {
//				//提交流程
//				logicComponent.invoke("saveIrmSubmitDjCoreImpl", new Object[] { amountId });
//			} catch (Throwable e) {
//				e.printStackTrace();
//				throw new EOSException("------3231------>冻结流程结束时，更新业务数据报错！");
//			}
//			logger.info("------3231------>冻结流程流结束节点，开始更新业务状态------amountId="+amountId+"-------结束！！！！！！！");
		}catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>冻结流程结束时，更新业务数据报错！");
		}
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
	}
	/**
	 * 
	* @Title: executeBeforeReject
	* @Description: TODO(用于审批否决前业务逻辑)
	* @param processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	 */
	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		//String conclusion = (String) workitem.get("conclusion");//结论
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String amountId=(String)list.get(0);
			if(null==amountId||"".equals(amountId)){
				logger.info("冻结撤销申请ID为空！");
				throw new EOSException("冻结申请ID为空");
			}
			logger.info("------3231------>冻结撤销流程流结束节点，开始更新业务状态------amountId="+amountId+"-------开始！！！！！！！-->");
			ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.irm.irmApply.irmApply");
			try {
				//提交流程
				logicComponent.invoke("saveIrmDeleteDjCoreImpl", new Object[] { amountId });
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException("------3231------>冻结流程结束时，更新业务数据报错！");
			}
			logger.info("------3231------>冻结撤销流程流结束节点，开始更新业务状态------amountId="+amountId+"-------结束！！！！！！！");
		}catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>冻结流程结束时，更新业务数据报错！");
		}
		/*String[] xpath={"bizId"};//获取相关数据的数组

		List<Object> list;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String amountId=(String)list.get(0);
			logger.info("------3231------>本笔评级结果置为失效------bizId="+amountId+"------>");
			DataObject irmApplyB = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingApply");
			irmApplyB.set("iraApplyId", amountId);
			irmApplyB.set("ratingState", "04");
			DatabaseUtil.updateEntity("default", irmApplyB);
			logger.info("------3231------>评级信息移到评级失效------bizId="+amountId+"------>结束！！！！！！！");	
		} catch (WFServiceException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/

		
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
