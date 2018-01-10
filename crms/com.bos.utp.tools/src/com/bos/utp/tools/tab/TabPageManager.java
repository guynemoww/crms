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
package com.bos.utp.tools.tab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import com.eos.infra.config.Configuration;
import com.eos.infra.config.Configuration.Group;
import com.eos.infra.config.Configuration.Module;

/**
 * Tab页管理
 * 
 * @author guwei
 * 
 */
public class TabPageManager {

	public static final TabPageManager INSTANCE = new TabPageManager();

	private Map<String, Map<String, TabPage>> tabPageMap = new HashMap<String, Map<String, TabPage>>();

	/**
	 * 
	 */
	private TabPageManager() {
	}

	/**
	 * 根据类型获取tab页模型
	 * 
	 * @param type
	 * @return
	 */
	public List<TabPage> getTabPageList(String type) {
		Map<String, TabPage> pageMap = tabPageMap.get(type);
		if (pageMap != null) {

			List<TabPage> pages = new ArrayList<TabPage>();
			for (TabPage page : pageMap.values()) {
				pages.add(page);
			}
			Collections.sort(pages, new Comparator<TabPage>() {

				public int compare(TabPage page1, TabPage page2) {
					return page1.getOrder() - page2.getOrder();
				}
			});
			return pages;
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * 添加tab页模型
	 * 
	 * @param type
	 * @param page
	 */
	public void addTabPage(String type, TabPage page) {
		Map<String, TabPage> pageMap = tabPageMap.get(type);
		if (pageMap == null) {
			pageMap = new HashMap<String, TabPage>();
			tabPageMap.put(type, pageMap);
		}
		pageMap.put(page.getId(), page);

	}

	/**
	 * <module name="AuthTab"> <group name="Form"> <configValue key="title">表单</configValue>
	 * <configValue key="url">/coframe/resource/form/form_auth.jsp</configValue>
	 * <configValue key="order">20</configValue> </group> <group name="View">
	 * <configValue key="title">视图</configValue> <configValue
	 * key="url">/coframe/resource/view/view_auth.jsp</configValue>
	 * <configValue key="order">30</configValue> </group> </module>
	 */
	public void loadTabPage(String type, Configuration contributionConfig) {
		Module module = contributionConfig.getModule(type);
		if(module!=null){			
			for (Group group : module.getGroups().values()) {
				String title = group.getConfigValue("title");
				String url = group.getConfigValue("url");
				String order = group.getConfigValue("order");
				TabPage page = new TabPage();
				page.setTitle(title);
				page.setUrl(url);
				page.setOrder(NumberUtils.toInt(order));
				page.setId(group.getName());
				addTabPage(type, page);
			}
		}
	}
	
	public void unloadTabPage(String type, String groupName, Configuration contributionConfig) {
			Map<String, TabPage> pageMap = tabPageMap.get(type);
			if(pageMap != null && pageMap.containsKey(groupName) == true) {
				pageMap.remove(groupName);
			}
	}

}