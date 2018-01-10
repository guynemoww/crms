package com.bos.batch;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import commonj.sdo.DataObject;

@Bizlet("MonitorUtil")
public class MonitorUtil {
	static Logger logger = TraceLoggerFactory.getLogger("com.bos.batch.acbatchconsolebiz.BatchMonitorConsole");
	DateUtil dateUtil = new DateUtil();
	boolean isdone = false;
	private String date = new DateUtil().getCurDate("yyyy-MM-dd");
	int sleeptime = 1000 * 60 * 5;

	// private String date="2014-11-30";
	// int sleeptime=1000*60*1;

	@Bizlet("监控当天批量是否开始")
	public void MonitorTaskStart() throws Exception {
		if (date == null || date.equals(""))
			throw new Exception("当天日期为空！");
		java.util.Map<String, String> map = new HashMap<String, String>();
		map.put("batchDate", date);
		Object[] res_1 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countConfigTasks", map);
		Object[] res_2 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countConfigCriticalTasks", map);
		logger.info(date + " need to execute " + res_1 == null ? "empty" : (Integer) res_1[0] + " tasks");
		logger.info(date + " need to execute " + res_2 == null ? "empty" : (Integer) res_2[0] + " critical tasks");
		while (true) {
			// 检查是否有该日期任务
			Object[] res1 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countTaskByDate", map);
			if (res1 != null && res1.length > 0) {
				int count = (Integer) res1[0];
				if (count == 0) {
					logger.info(date + "批量未开始。。。waiting for 5 minutes...");
					if (islaterthan(20, 50)) {
						String info = date + " CRMS non-critical batchTask not Begin!";
						logger.error(info);
					}
					// 等待5分钟
					Thread.sleep(sleeptime);
				} else {
					// 非关键任务开始
					String info = date + " CRMS non-critical batchTask Begin!";
					logger.info(info);
					return;
				}
			} else {
				throw new Exception("批量监控失败！");
			}
		}

	}

	@Bizlet("监控当天关键批量是否开始")
	public void MonitorCriticalTaskStart() throws Exception {
		if (date == null || date.equals(""))
			throw new Exception("当天日期为空！");
		java.util.Map<String, String> map = new HashMap<String, String>();
		map.put("batchDate", date);
		while (true) {
			// 检查是否有该日期任务
			Object[] res1 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.MonitorCriticalTaskisStart", map);
			if (res1 != null && res1.length > 0) {
				int count = (Integer) res1[0];
				if (count == 0) {
					logger.info(date + "需要监控的批量任务仍未开始！请等待5分钟。。。");
					Thread.sleep(sleeptime);// 等待5分钟
				} else {
					// 关键任务开始
					logger.info(date + " 需要监控的批量任务已经开始!");
					return;
				}
			} else {
				logger.info(date + " 没有需要监控的批量任务!");
			}
		}
	}

	@Bizlet("列出当天执行失败的批量")
	public void MonitorUnsuccessedTask() throws Exception {
		if (date == null || date.equals(""))
			throw new Exception("当天日期为空！");
		java.util.Map<String, String> map = new HashMap<String, String>();
		map.put("batchDate", date);
		// 监控关键批量是否结束
		while (!isMonitorTaskEnd_critical()) {
			logErrTask();
		}
		// 监控所有批量是否结束
		while (!isMonitorTaskEnd()) {
			logErrTask();
		}
		logger.info(date + "批量结束 goodbye");
	}

	private void logErrTask() {
		java.util.Map<String, String> map = new HashMap<String, String>();
		map.put("batchDate", date);
		// 获得执行失败的批量列表
		Object[] res1 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.getUnSuccessedTasks", map);
		if (res1 != null && res1.length > 0) {
			// 有出错的批量
			logger.error("The ERROR Batch List is :");
			for (int i = 0; i < res1.length; i++) {
				DataObject unsuccessedtask = (DataObject) res1[i];
				String batch_id = unsuccessedtask.getString("batch_id");
				String batch_group = unsuccessedtask.getString("batch_group");
				String starttime = unsuccessedtask.getString("starttime");
				String batch_date = unsuccessedtask.getString("batch_date");
				String batch_name = unsuccessedtask.getString("batch_name");
				String batch_bizname = unsuccessedtask.getString("batch_bizname");
				String BATCH_TEMP1 = unsuccessedtask.getString("BATCH_TEMP1");
				String iscritical = "";
				if (BATCH_TEMP1 != null) {
					if (BATCH_TEMP1.equals("1")) {
						iscritical = "critical";
					} else {
						iscritical = "non-critical";
					}
				}
				String info = "CRMS " + iscritical + " batchTask ERROR! batch_id:" + batch_id + " batch_group:" + batch_group + " batch_date:" + batch_date + " starttime:" + starttime + " 批量名称："
						+ batch_name + " 对应逻辑流：" + batch_bizname;
				logger.error(info);
			}
		}
	}

	@Bizlet("监控当天批量是否结束")
	public boolean isMonitorTaskEnd_critical() throws Exception {
		if (date == null || date.equals(""))
			throw new Exception("当天日期为空！");
		
		java.util.Map<String, String> map = new HashMap<String, String>();
		map.put("batchDate", date);

		// 应执行的批量任务
		Object[] res1 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countConfigCriticalTasks", map);
		// 实际完成批量任务
		Object[] res2 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countSucceedCriticalTasks", map);
		
		if (res1 != null && res1.length > 0) {
			int count_config_tasks = (Integer) res1[0];
			int count_succeed_tasks = (Integer) res2[0];
			if (count_config_tasks == count_succeed_tasks) {
				logger.info(date + " 所监控的批量任务已经全部完成!");
				return true;
			} else {
				logger.debug(date + " 应执行任务数量： " + count_config_tasks + "\r\n已完成任务数量： " + count_succeed_tasks);
				Thread.sleep(sleeptime);
				return false;
			}
		} else {
			logger.info(date + " 没有需要监控的批量任务!");
			return false;
		}
	}

	@Bizlet("监控当天批量是否结束")
	// 监控所有批量
	public boolean isMonitorTaskEnd() throws Exception {
		if (date == null || date.equals(""))
			throw new Exception("当天日期为空！");
		java.util.Map<String, String> map = new HashMap<String, String>();
		map.put("batchDate", date);
		String date1 = dateUtil.getCurDate("yyyy-MM-dd");
		// 获取当前执行未成功的批量数
		Object[] res1 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countConfigTasks", map);
		Object[] res2 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countSucceedTasks", map);
		if (res1 != null && res1.length > 0) {
			int count_config_tasks = (Integer) res1[0];
			int count_succeed_tasks = (Integer) res2[0];
			if (count_config_tasks == count_succeed_tasks) {
				// config表数量和console表数量相同,检查是否需要跑月报
				if (date1.substring(date1.length() - 2, date1.length()).equals("01")) {
					// 每月01号检查月报批量是否执行
					Object[] resB3 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countB3task", map);
					if (resB3 != null && resB3.length > 0) {
						int countB3 = (Integer) resB3[0];
						if (countB3 == 0) {

							logger.info(date + "月报未执行");
							return false;
						}
					}
				}
				// 检查是否需要执行季报任务
				if (date1.substring(date1.length() - 5, date1.length()).equals("01-01") || date1.substring(date.length() - 5, date1.length()).equals("04-01")
						|| date1.substring(date1.length() - 5, date1.length()).equals("07-01") || date1.substring(date1.length() - 5, date1.length()).equals("10-01")) {
					// 每3月的01号检查季报批量是否执行
					Object[] resB4 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countB4task", map);
					if (resB4 != null && resB4.length > 0) {
						int countB4 = (Integer) resB4[0];
						if (countB4 == 0) {
							logger.info(date + "季报未执行");
							return false;
						}
					}
				}

				String info = date + " CRMS non-critical batchTask END!";
				logger.info(info);
				return true;
			} else {
				if (islaterthan(10, 0)) {
					String info = date + " CRMS non-critical batchTask not END!";
					logger.error(info);
				}
				String test = date + " config taskcount is " + count_config_tasks + "; succeed taskcount is " + count_succeed_tasks;
				logger.debug(test);
				Thread.sleep(sleeptime);
				return false;
			}
		} else {
			throw new Exception("批量监控失败！");
		}
	}

	// 判断时间是否晚于某个点
	public boolean islaterthan(int hour, int minute) {
		boolean flag = false;
		Date curdate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		flag = cal.getTime().before(curdate);
		return flag;
	}

	@Bizlet("数仓所有文件导入完成")
	public static void EDWDone() {
		// logger.info("EDW Done");
	}
}
