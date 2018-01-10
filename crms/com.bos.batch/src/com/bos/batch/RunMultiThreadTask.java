/**
 * 
 */
package com.bos.batch;

import java.util.HashMap;

import com.bos.pub.GitUtil;
import com.bos.pub.MultiThreadUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

/**
 * @author kf_xdxt11
 * @date 2016-04-25 16:16:16
 * 
 */
@Bizlet("CredRecount")
public class RunMultiThreadTask {

	@Bizlet("批量计量同步")
	public void runDealAccount() {
		run("批量计量同步", "com.bos.batch.loadData.querySummaryTotalPage", "com.bos.batch.loadData.querySummaryNums", 1,
				"com.bos.batch.batchtask.singleDealAccount");
	}

	@Bizlet("批量额度重算")
	public void runCredRecount() {
		run("批量额度重算", "com.bos.batch.loadData.queryBizTotalPage", "com.bos.batch.loadData.queryBizPartyIds", 1,
				"com.bos.batch.batchtask.singleCredRecount");
	}

	@Bizlet("执行")
	public static void run(String batchName, String totalSQL, String parmasSQL, int handleType, String handleName) {
		String times = CommonUtil.getDBConfigVal("night_batch", "group_total");
		System.out.println(">>>获取配置：多线程每组运行次数【" + times + "】，默认500个");
		if (times == null || "".equals(times)) {
			times = "500";
		}
		int groupTotal = Integer.parseInt(times);

		HashMap<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("groupTotal", groupTotal);
		Object[] pages = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, totalSQL, parameter);
		int totalPage = (Integer) pages[0];

		ThreadGroup threadGroup = new ThreadGroup(batchName);

		for (int i = 0; i < totalPage; i++) {
			parameter.put("frequency", i);
			Object[] handleParams = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, parmasSQL, parameter);

			System.out.println(batchName + "：批次开始【" + (i + 1) + "】；批次内共计 >> " + handleParams.length + "个");

			MultiThreadUtil mm = new MultiThreadUtil(handleType, handleName, handleParams);

			Thread t2 = new Thread(threadGroup, mm, batchName + "批次【" + (i + 1) + "】");
			t2.start();

		}

		int total = threadGroup.activeCount();
		int active = 0;
		while ((active = threadGroup.activeCount()) > 0) {
			System.out.println("处理线程共有【" + total + "】个，目前还有线程【" + active + "】个，阻塞 10 秒 等待线程全部结束...");
			try {
				Thread.sleep(1000 * 10);
			} catch (Exception e) {
				e.printStackTrace();
				Thread.interrupted();
			}
		}
		System.out.println("线程已全部结束...");
	}

}
