/**
 * 
 */
package com.bos.acc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bos.mq.util.KeyGenerator;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.common.TaskBean;
import com.git.easyetl.threadpool.task.TaskManager;
import com.git.easyetl.threadpool.task.WorkTask;
import com.git.easyetl.threadpool.thread.ThreadPool;

/**
 * @author C_ture
 * @date 2014-09-20 10:50:06
 *
 */
@Bizlet("线程池添加任务")
public class RunAccFinance {

	/**
	 * @author C_ture
	 * 
	 */
	@Bizlet("添加任务")
	public List<WorkTask> getTaskList() {
		List<WorkTask> taskList = new ArrayList<WorkTask>();
		try {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				Object[] pramDatas = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME,
						"com.bos.acc.exportExcel.selectTaskParm", paramMap);
				
				for (int i = 0; i < pramDatas.length; i++) {
					HashMap pram = (HashMap)pramDatas[i];

					TaskBean taskBean = new TaskBean();
					WorkTask task = null;
					task = new RunTask(pram);
					taskBean.setTaskId(KeyGenerator.getUUID());
					taskBean.setUuid(KeyGenerator.getUUID());
					taskBean.setTaskType("ACC");
					task.setTaskBean(taskBean);
					task.setTaskThreadKey("DEFAULT");
					if(task != null){
						taskList.add(task);
					}	
				}
				
				TaskManager tm = ThreadPool.getInstance().getTaskManager();
				tm.getTaskQueue(taskList);
				
//			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return taskList;
	}


}
