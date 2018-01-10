package com.bos.comm.crd;

import java.util.Date;
import java.util.Map;

import com.bos.mq.server.BaseWorkTask;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;

public class BatchCrdCalculatorTask extends BaseWorkTask implements WorkTask {
	
	public   Object obj;
	static TraceLogger log = new TraceLogger(BatchCrdCalculatorTask.class);
	public BatchCrdCalculatorTask(Object obj) {
		this.obj = obj;
	}
	
	@Bizlet(value = "")
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		String partyId = ((Map<String,String>) obj).get("partyId");
		String taskType = ((Map<String,String>) obj).get("taskType");
		// 逻辑构件名称
		String componentName = "com.bos.comm.crd.CrdMaintain";
		// 逻辑流名称
		String limitType = "";
		String operationName = "";
		if("crd".equals(taskType)){
			operationName = "crdCalculates";
			limitType = "综合授信";
		}else if("guatee".equals(taskType)){
			operationName = "guateeCalculates";
			limitType = "担保额度";
		}else if("platform".equals(taskType)){
			operationName = "platformCalculates";
			limitType = "平台额度";
		}else if("peer".equals(taskType)){
			operationName = "peersCalculates";
			limitType = "同业额度";
		}else if("single".equals(taskType)){
			operationName = "singleCalculates";
			limitType = "单笔单批";
		}else{
			//增加异常记录日志
			throw new Exception("额度类型错误");
		}
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		// 逻辑流的输入参数
		Object[] params = new Object[1];
		params[0] = partyId;
		try {
			log.info(limitType+"重计算开始,Key:"+partyId);
			Object[] result = logicComponent.invoke(operationName, params);
			log.info(limitType+"重计算完毕,Key:"+partyId);
			//记录执行日志
		} catch (Throwable e) {
			//记录异常日志
			DataObject dataObj = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdLimitException");
			dataObj.setString("partyId", partyId);
			dataObj.setString("exceptionDesc", e.getMessage().toString());
			dataObj.setDate("dateTime", GitUtil.currDateTime());
			DatabaseUtil.insertEntity("default", dataObj);
			e.printStackTrace();
			throw new EOSException(e);
		}
	}

}
