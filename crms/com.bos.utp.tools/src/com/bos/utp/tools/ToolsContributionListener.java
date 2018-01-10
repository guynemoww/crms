package com.bos.utp.tools;

import com.eos.runtime.resource.IContributionEvent;
import com.eos.runtime.resource.IContributionListener;

public class ToolsContributionListener implements IContributionListener {
	public void load(IContributionEvent event) {

	}

	public void loadFinished(IContributionEvent event) {
		//初始化日志工厂
		ABFLogFactory.getInstance().register();
	}

	public void unLoad(IContributionEvent event) {
		//关闭日志工厂
		ABFLogFactory.getInstance().unRegister();
	}

}
