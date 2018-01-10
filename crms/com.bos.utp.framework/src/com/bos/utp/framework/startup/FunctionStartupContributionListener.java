/*
 * Copyright 2013 Primeton Technologies Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bos.utp.framework.startup;


import com.bos.utp.framework.IAppFunctionService;
import com.bos.utp.tools.tab.TabPageManager;
import com.eos.runtime.resource.IContributionEvent;
import com.eos.runtime.resource.IContributionListener;
import com.eos.spring.BeanFactory;

/**
 * TODO 此处填写 class 信息
 * 
 * @author fangwl (mailto:fangwl@primeton.com)
 */
public class FunctionStartupContributionListener implements
		IContributionListener {

	public void load(IContributionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void loadFinished(IContributionEvent event) {
		// TODO Auto-generated method stub
		BeanFactory beanFactory = BeanFactory.newInstance();
		IAppFunctionService functionService = beanFactory
				.getBean("AppFunctionBean");
		functionService.initFunctions();
		TabPageManager.INSTANCE.loadTabPage("AuthTab", event
				.getContributionConfiguration());
	}

	public void unLoad(IContributionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
