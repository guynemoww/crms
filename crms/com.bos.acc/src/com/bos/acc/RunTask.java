package com.bos.acc;

import java.util.Date;
import java.util.HashMap;

import com.bos.mq.rq.BaseMQRequest;
import com.bos.mq.server.BaseWorkTask;
import com.bos.mq.util.DateHelper;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;
import com.primeton.ext.engine.component.LogicComponentFactory;
@Bizlet("执行任务")
public class RunTask extends BaseWorkTask implements WorkTask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根

	}
	
	public HashMap pMap;
	public RunTask(HashMap pMap) {
		this.pMap = pMap;
	}
	
	public void execute() throws Exception {
		// TODO 自动生成方法存根

//		 逻辑构件名称
		String componentName = "com.bos.acc.accnfdsheet";
		// 逻辑流名称
		String operationName = "runAllIndexbiz";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		// 逻辑流的输入参数
		Object[] params = new Object[1];
		BaseMQRequest bmr = new BaseMQRequest();
		try {
			//params[0] = DateHelper.getDate("2013-12-31");
			params[0] = pMap;
			logicComponent.invoke(operationName, params);
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
