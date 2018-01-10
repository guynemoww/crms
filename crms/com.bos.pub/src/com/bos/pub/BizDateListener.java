package com.bos.pub;

import java.sql.Timestamp;
import com.eos.runtime.core.IRuntimeListener;
import com.eos.runtime.core.RuntimeEvent;

public class BizDateListener implements IRuntimeListener {
	public void start(RuntimeEvent arg0) {
		GitUtil gu = new GitUtil();
		System.out.println("开始初始化营业日期。。。");
		gu.initBusiDate();
		System.out.println("初始化营业日期成功，当前营业日期【"+new Timestamp(gu.previousDate.getTimeInMillis())+"】！");
	}

	public void stop(RuntimeEvent arg0) {
	}
}
