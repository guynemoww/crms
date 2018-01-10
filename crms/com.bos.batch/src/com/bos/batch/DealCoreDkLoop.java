/**
 * 
 */
package com.bos.batch;

import java.util.Calendar;

import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;

/**
 * @author kf_xdxt11
 * @date 2016-03-16 10:53:44
 * 
 */
@Bizlet("处理核心贷款")
public class DealCoreDkLoop {

	@Bizlet("批量处理")
	public void loadDBFileLoop() {
		while (true) {
			try {
				// 下载垫款文件
				DBFileUtil dbUtil = new DBFileUtil();
				String res = dbUtil.loadDBFile("core_down_diankuan");

				// 如果存在文件，则执行垫款出账交易
				if ("1".equals(res)) {
					ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.batch.batchtask");
					Object[] params = new Object[0];
					Object[] result = logicComponent.invoke("btDealBizCoreDK", params);
				}

				// 挂起线程等待
				int waitTime_dk = Integer.parseInt(CommonUtil.getDBConfigVal("ftp_core", "waitTime_dk"));
				Thread.sleep(waitTime_dk * 1000);
				LogUtil.logInfo("正在轮询核心垫款文件，等待" + waitTime_dk + "秒...", null);
				System.out.println("正在轮询核心垫款文件，等待" + waitTime_dk + "秒...");

			} catch (InterruptedException e) {
				System.out.println("垫款出账失败！");
				e.printStackTrace();
				Thread.currentThread().interrupt();
			} catch (Throwable e1) {
				System.out.println("轮询时，垫款文件装载失败！");
				e1.printStackTrace();
			}
			// 定时关闭
			String stopLoop_dk = CommonUtil.getDBConfigVal("ftp_core", "stopLoop_dk");
			int stop_hour = Integer.parseInt(stopLoop_dk.split(":")[0]);
			int stop_minute = Integer.parseInt(stopLoop_dk.split(":")[1]);
			Calendar cal = Calendar.getInstance();
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minute = cal.get(Calendar.MINUTE);
			if (hour >= stop_hour && minute >= stop_minute) {
				System.out.println("当前时间【" + hour + "时" + minute + "分】，超过预定时间【" + stopLoop_dk + "】,垫款轮询结束！");
				break;
			}
		}
	}

}
