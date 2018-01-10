package com.bos.irmDjjd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.workflow.api.WFServiceException;
import commonj.sdo.DataObject;
//额度冻结流程回调函数
public class CallBackForSubmitDjjd implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitDjjd.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	//(用于审批同意流程提交前业务逻辑)
	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		//提交流程  业务状态更新为07
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

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	//(用于普通流程结束前业务逻辑)
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		//提交流程  业务状态更新为07
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

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	//(用于撤销流程后业务逻辑)
	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		//撤销流程  业务状态更新为03
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
	}
	//验证数据是否填写完整
	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
//		DataObject[] arr = new DataObject[0];
//		Map<String, String> map = new HashMap<String, String>();
//		Map<String, String> mapB = new HashMap<String, String>();
//		String[] xpath={"bizId"};//获取相关数据的数组
//			List<Object> list;
//			try {
//				list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
//				String amountId=(String)list.get(0);
//				if(null==amountId||"".equals(amountId)){
//					logger.info("流程返回的amountId为空！");
//					throw new EOSException("流程返回的amountId为空");
//				}
//				map.put("amountId", amountId);
//				Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
//						"com.bos.irm.modelCalc.model.getRatingCdDJ", map);
//				arr = new DataObject[tmp.length];
//				for (int i = 0; i < tmp.length; i++) {
//					String dat = (String) tmp[i];
//					logger.info("!!!!!!!!!!!!!!!"+dat);
//					if(dat==null){
//						mapB.put("errorNum", "2");
//						mapB.put("errorCode", "2");
//						mapB.put("errorContent", "请将冻结内容填写完整！");
//						return mapB;
//					}
//						mapB.put("errorNum", "1");
//						mapB.put("errorCode", "");
//						mapB.put("errorContent", "");
// 				}
//			} catch (WFServiceException e) {
//				e.printStackTrace();
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			}
//			return mapB;
			return null;
	}
				
}
