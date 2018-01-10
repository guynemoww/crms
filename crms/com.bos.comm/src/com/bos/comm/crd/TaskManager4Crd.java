/**
 * 
 */
package com.bos.comm.crd;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.mq.util.KeyGenerator;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.common.TaskBean;
import com.git.easyetl.threadpool.task.TaskManager;
import com.git.easyetl.threadpool.task.WorkTask;
import com.git.easyetl.threadpool.thread.ThreadPool;
import commonj.sdo.DataObject;

/**
 * @author houjiaxin
 * @date 2014-10-22 18:29:01
 *
 */
@Bizlet("额度计算任务管理")
public class TaskManager4Crd {

	/**
	 * @param cusList 需要计算的客户信息
	 * @author houjiaxin
	 */
	@Bizlet("新增额度任务")
	public static void addCrdTask(DataObject[] cusList, String taskType) {
		//初始化额度计算总数及已计算数量-begin
		if("crd".equals(taskType)){
			Global.crdTotal = cusList.length;
			Global.crdCount = 0;
		}else if("guatee".equals(taskType)){
			Global.guateeTotal = cusList.length;
			Global.guateeCount = 0;
		}else if("platform".equals(taskType)){
			Global.platformTotal = cusList.length;
			Global.platformCount = 0;
		}else if("peer".equals(taskType)){
			Global.peerTotal = cusList.length;
			Global.peerCount = 0;
		}else if("single".equals(taskType)){
			Global.singleTotal = cusList.length;
			Global.singleCount = 0;
		}
		//初始化额度计算总数及已计算数量-end
		for (DataObject cusObj : cusList) {
			Map<String, String> custMap = new HashMap<String, String>();
			if ("single".equals(taskType)) {
				custMap.put("partyId", cusObj.getString("approveId"));
			} else {
				custMap.put("partyId", cusObj.getString("partyId"));
			}
			custMap.put("taskType", taskType);
			System.out.println(custMap.get("taskType") + "-"
					+ custMap.get("partyId"));
			WorkTask crdTask = new BatchCrdCalculatorTask(custMap);
			try {
				TaskManager tm = ThreadPool.getInstance().getTaskManager();
				TaskBean taskBean = new TaskBean();
				taskBean.setTaskId(custMap.get("partyId"));
				taskBean.setUuid(KeyGenerator.getUUID());
				taskBean.setTaskType("BATCHCRD");
				taskBean.setTaskName(custMap.get("partyId"));
				crdTask.setTaskBean(taskBean);
				crdTask.setTaskThreadKey("DEFAULT");
				tm.addWorkTask(crdTask);
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				//增加添加任务失败后记录日志的内容
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return
	 * @throws Exception 
	 */
	@Bizlet("")
	public static boolean checkCrdStatus() throws Exception {
		// TODO 自动生成方法存根
		TaskManager tm = ThreadPool.getInstance().getTaskManager();
		boolean flag = false;
		while(true)
		{
		List queueList = tm.getQueue();
		if(queueList.size()==0)
		{
			//检查异常表是否存在记录
			DataObject dataObj = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdLimitException");
			int temp=DatabaseUtil.countByTemplate("default", dataObj);
			if(temp>0)
			{
			//存在记录，批量执行结果失败
			flag=false;
			}
			else
			{
				//任务执行完毕且无异常记录，返回批量成功
				flag=true;
			}
			break;
		}
		else
		{
			//线程休眠30秒
			Thread.sleep(30000);
		}
		}
		return flag;
	}

}
