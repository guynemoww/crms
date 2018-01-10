package com.bos.pub;

import java.util.HashMap;
import java.util.Map;

import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;

@Bizlet("多线程工具类")
public class MultiThreadUtil implements Runnable {

	/**
	 * 1-逻辑流；2-命名SQL
	 */
	private int handleType;
	private String handleName;// 逻辑流名称或命名sql名称
	private Object[] handleParams;// 传入参数
	private String logicPackage;// 逻辑流包名
	private String logicName;// 逻辑流名

	public MultiThreadUtil(int handleType, String handleName, Object[] handleParams) {
		this.handleType = handleType;
		this.handleName = handleName;
		this.handleParams = handleParams;
		if (handleType == 1) {
			logicPackage = handleName.substring(0, handleName.lastIndexOf("."));
			logicName = handleName.substring(handleName.lastIndexOf(".") + 1);
		}
	}

	public void run() {
		String startTime = DateUtil.getDate();
		System.out.println("批次开始：" + Thread.currentThread().getName() + "，开始时间：" + startTime);
		for (int j = 0; j < handleParams.length; j++) {
			Object params = null;
			try {
				params = handleParams[j];
				if (this.handleType == 1) {
					ILogicComponent logicComponent = LogicComponentFactory.create(logicPackage);
					Object[] objs = { params };
					logicComponent.invoke(logicName, objs);
				} else if (this.handleType == 2) {
					Map<String, String> recountParameters = new HashMap<String, String>();
					recountParameters.put("partyId", (String) params);
					DatabaseExt.executeNamedSql("default", handleName, recountParameters);
				}
			} catch (Throwable e) {
				e.printStackTrace();
				System.out.println("多线程任务执行失败！当前批次：" + Thread.currentThread().getName() + "。调用类型：【" + handleType + "】，调用名称：【" + handleName
						+ "】，传入参数：【" + params + "】");
				continue;
			}
		}
		String endTime = DateUtil.getDate();
		double time = DateUtil.getIntervalMinutes(DateUtil.StringToDate(startTime), DateUtil.StringToDate(endTime));
		System.out.println("批次结束：" + Thread.currentThread().getName() + "，结束时间：" + endTime + "，本批次耗时：" + time + "分");

	}

}
