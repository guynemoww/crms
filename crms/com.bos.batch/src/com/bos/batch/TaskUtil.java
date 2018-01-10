/**
 * 
 */
package com.bos.batch;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 李磊BOS
 * @date 2014-07-24 14:18:48
 *
 */
@Bizlet("任务处理")
public class TaskUtil {

	/**
	 * @param args
	 * @author 李磊BOS
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(checkFileExist("E:"+"/"+"20141120"));

	}

	/**
	 * @param date
	 * @author 李磊BOS
	 * @throws Exception 
	 */
	@Bizlet("检查任务状态")
	public static void checkTaskFinish(String date) throws Exception {
		if (date == null || date.equals(""))
			throw new Exception("日期为空！");
		java.util.Map<String, String> map = new HashMap<String, String>();
		map.put("batchDate", date);
		// 检查是否有该日期任务
		Object[] res1 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countTaskByDate", map);
		if (res1 != null && res1.length > 0) {
			int count = (Integer) res1[0];
			if (count == 0) {
				LogUtil.logDebug("{0}没有执行任务痕迹！等待执行。。。", null, date);
			} else {
				LogUtil.logDebug("{0}有任务执行痕迹", null, date);
				while (true) {
					// 检查该日期的批量任务是否全都完成
					Object[] results = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.task.countUnSuccessedTask", map);
					if (results != null && results.length > 0) {
						count = (Integer) results[0];
						if (count > 0) {
							LogUtil.logDebug("{0}批量任务未完成！等待。。。", null, date);
							// 等待1分钟
							Thread.sleep(1000 * 60);
						} else {
							LogUtil.logDebug("{0}批量任务已完成！", null, date);
							return;
						}
					} else {
						throw new Exception("查询失败！");
					}
					
				}
			}
		}
	}
	
	@Bizlet("检查第三、四批的依赖任务（spMQ25BillRpt）是否完成")
	public static void checkQ25RptTaskFinish(String date) throws Exception {
		if(date==null||date.equals(""))throw new Exception("日期为空！");
		java.util.Map<String, String> map = new HashMap<String, String>();
		map.put("batchDate", date);
		while(true){
			// 检查该日期的批量任务是否全都完成
			Object[] results =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
					"com.bos.batch.task.checkQ25RptTaskFinish",map);
			if(results!=null && results.length>0){
				int count= (Integer)results[0];
				if(count>0){
					LogUtil.logDebug("{0}第三、四批的依赖任务已完成！", null, date);
					return;
				}else{
					LogUtil.logDebug("{0}第三、四批的依赖任务未完成！等待。。。", null, date);
					// 等待5分钟
					Thread.sleep(1000*60*5);
				}
				
			}else{
				throw new Exception("查询失败！");
			}
			
		}
	}
	
	@Bizlet("检查导入借据表tb_batch_business_duebill")
	public static String  checkbd(String date,int checkNum) throws Exception {
		if(date==null||date.equals(""))throw new Exception("日期为空！");
		java.util.Map<String, String> map = new HashMap<String, String>();
		map.put("batchDate", date);
		
			// 检查 数仓给过来的借据，余额大于零的是否超过2W条
			Object[] results =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
					"com.bos.batch.task.checkbd",map);
			if(results!=null && results.length>0){
				int count= (Integer)results[0];
				LogUtil.logDebug("检查结束,数仓给过来的借据,日期{0},余额大于零的有{1}条", null, date,count);
				if(count>checkNum){
					LogUtil.logDebug("检查成功", null, date);
					return "1";
				}else{
					LogUtil.logError("检查失败！", null, date);
					
				}
			}	
			// 失败
				return "2";
	}
		
	@Bizlet("检查卸数是否产生跑批日期文件夹")
	public static String checkFileExist(String path) throws Exception {
		LogUtil.logDebug("checkFileExist  begin {0}", null, path);
		//1存在，2不存在
		String isexist="2";
		File file = new File(path);
		if (!file.exists()) {
			isexist = "2";
		} else {
			isexist="1";}
		
		LogUtil.logDebug("checkFileExist  end {0} {1}", null, path,isexist);
		return isexist;
	}
	@Bizlet("检查借据表是否有重复借据号")
	public static String  checkDupBill() throws Exception {
		java.util.Map<String, String> map = new HashMap<String, String>();
		
			// 检查借据表是否有重复借据号
			Object[] results =  DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
					"com.bos.batch.task.checkDupBill",map);
			if(results!=null && results.length>0){
				LogUtil.logError("检查结束,借据表有{0}条重复借据号", null, results.length);
				for(int i=0;i<results.length;i++)
				{
					DataObject duebills=(DataObject)results[i];
					String billnum=duebills.getString("business_num");
					LogUtil.logError("借据号{0}重复！",  null,billnum);
				}	
				return "2";
			}	
		return "1";		
	}
	
	@Bizlet("任务排序")
	public DataObject[] taskOrder(DataObject[] taskList) {
		DataObject temp = null;
		int size = taskList.length;
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				if (taskList[i].getInt("batchOrder") > taskList[j].getInt("batchOrder")) {
					temp = taskList[i];
					taskList[i] = taskList[j];
					taskList[j] = temp;
				}
			}
		}
		return taskList;
	}

	@Bizlet("等待执行结果")
	public boolean waitResult(String nameSqlId, long time) {
		//使用sdp的数据源直接查计量，避开sdp
		int count = DatabaseExt.countByNamedSql("sdp", nameSqlId, null);
		while (count == 0) {
			LogUtil.logInfo("验证SQL【" + nameSqlId + "】执行结果记录为空，继续等待【" + time + "】秒...", null);
			try {
				Thread.sleep(time * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.interrupted();
			}
			count = DatabaseExt.countByNamedSql("default", nameSqlId, null);
		}
		LogUtil.logInfo("验证SQL【" + nameSqlId + "】执行结果记录存在！", null);
		return true;
	}
	
	
}
