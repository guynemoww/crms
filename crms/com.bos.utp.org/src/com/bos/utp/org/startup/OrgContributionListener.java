package com.bos.utp.org.startup;


import com.bos.utp.tools.tab.TabPageManager;
import com.eos.runtime.resource.IContributionEvent;
import com.eos.runtime.resource.IContributionListener;
/**
 * 加载资源的监听器
 * @author caozw@primeton.com
 *
 */
public class OrgContributionListener implements IContributionListener {

	public void load(IContributionEvent arg0) {

	}

	public void loadFinished(IContributionEvent event) {
		TabPageManager.INSTANCE.loadTabPage("AuthTab", event
				.getContributionConfiguration());

	}

	public void unLoad(IContributionEvent arg0) {
	}

}
