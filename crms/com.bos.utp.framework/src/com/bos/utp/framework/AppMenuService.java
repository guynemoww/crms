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
package com.bos.utp.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bos.utp.framework.application.AcMenu;
import com.bos.utp.framework.constants.IAppConstants;
import com.bos.utp.tools.IconCls;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.spring.DASDaoSupport;
import com.eos.system.logging.Logger;
import commonj.sdo.DataObject;

/**
 * TODO 此处填写 class 信息
 *
 * @author fangwl (mailto:fangwl@primeton.com)
 */
public class AppMenuService extends DASDaoSupport implements IAppMenuService{
	private Logger log = TraceLoggerFactory.getLogger(AppMenuService.class);
	public void getPrimaryKey(AcMenu appMenu){
		getDASTemplate().getPrimaryKey(appMenu);
	}
	public void addAppMenu(AcMenu appMenu){
		//appMenu.setTenant_id(TenantManager.getCurrentTenantID());
		try{
			if (appMenu.getAppid() < 1)
				appMenu.setAppid(1);
			getDASTemplate().insertEntity(appMenu);
		} catch (Throwable t) {
			log.error("Insert menu [menuid=" + appMenu.getMenuid() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
	}

	public void deleteAppMenu(AcMenu[] appMenus ){
		for(DataObject appMenu:appMenus){
			try{
				getDASTemplate().deleteEntityCascade(appMenu);
			} catch (Throwable t) {
				log.error("Delete menu [menuid=" + appMenu.get("menuid") + "] failure, please do the operation again or contact the sysadmin.", t);
			}
		}
	}


	public void getAppMenu(AcMenu appMenu){
		getDASTemplate().expandEntity(appMenu);
	}


	public AcMenu[]  queryAppMenus(CriteriaType criteriaType,PageCond pageCond){
		criteriaType.set_entity(AcMenu.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(AcMenu.class, dasCriteria, pageCond);
	}


    public void updateAppMenu(AcMenu appMenu){
    	try{
		    getDASTemplate().updateEntity(appMenu);
	    } catch (Throwable t) {
			log.error("Update menu [menuid=" + appMenu.getMenuid() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
    }

	public AcMenu[] queryAppMenus(CriteriaType criteriaType) {
		criteriaType.set_entity(AcMenu.QNAME);
		criteriaType.set("_orderby[1]/_property", "displayorder");
		criteriaType.set("_orderby[1]/_sort","asc");
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		return getDASTemplate().queryEntitiesByCriteriaEntity(AcMenu.class, dasCriteria);
	}

	@SuppressWarnings("unchecked")
	public List<Map> getMenuNode(DataObject[] menus) {
		List<Map> nodeList = new ArrayList<Map>();
		//构造应用List
		for(DataObject dataObject : menus){
			Map map = new HashMap();
			map.put("id", dataObject.get("menuid"));
			map.put("text", dataObject.get("menulabel"));
			//if(dataObject.get("isleaf").equals("1")){//为Abframe改造，Abrame中为字典类型为ABF_YESORNO y/n
			if(dataObject.get("isleaf").equals("1")){
				map.put("type", IAppConstants.TYPE_MENU);
				map.put("iconCls", IconCls.MENU);
				map.put("isLeaf", true);
				map.put("expanded", false);
			}else{
				map.put("iconCls", IconCls.MENU_ROOT);
				map.put("type", IAppConstants.TYPE_MENUGROUP);
				map.put("isLeaf", false);
				map.put("expanded", false);
			}
			//如果parent为null，则父节点为root
			if(dataObject.getDataObject("appMenu") == null){
				map.put("pid", "root");
			}else{
				if("".equals(dataObject.getDataObject("appMenu").get("menuid")) || dataObject.getDataObject("appMenu").get("menuid")==null){
					map.put("pid", "root");
				}else{
					map.put("pid", dataObject.getDataObject("appMenu").get("menuid"));
				}
			}
			nodeList.add(map);
		}
		return nodeList;
	}

	@SuppressWarnings("unchecked")
	public List<Map> getMenuRoot(DataObject[] menus) {
		List<Map> rootList = new ArrayList<Map>();
		Map rootMap = new HashMap();
		rootMap.put("id", "root");
		rootMap.put("text", IAppConstants.MENU_TREE_ROOT);
		rootMap.put("type", "root");
		rootMap.put("iconCls", IconCls.APPLICATION_HOME);
		rootMap.put("isLeaf", false);
		rootMap.put("expanded", true);
		rootList.add(rootMap);
		//构造应用List
		for(DataObject dataObject : menus){
			Map map = new HashMap();
			map.put("id", dataObject.get("menuid"));
			map.put("text", dataObject.get("menulabel"));
			
			if(dataObject.get("isleaf").equals("1")){
				map.put("type", IAppConstants.TYPE_MENU);
				map.put("iconCls", IconCls.MENU);
				map.put("isLeaf", true);
				map.put("expanded", false);
			}else{
				map.put("type", IAppConstants.TYPE_MENUGROUP);
				map.put("iconCls", IconCls.MENU_ROOT);
				map.put("isLeaf", false);
				map.put("expanded", false);
			}
			map.put("pid", "root");
			rootList.add(map);
		}
		return rootList;
	}

	public int countAppMenus(CriteriaType criteria) {
		criteria.set_entity(AcMenu.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}
	
	public void deleteMenuById(String id) {
		AcMenu appMenu = AcMenu.FACTORY.create();
		appMenu.setMenuid(id);
		try{
			getDASTemplate().deleteEntityCascade(appMenu);
		} catch (Throwable t) {
			log.error("Delete menu [menuid=" + appMenu.getMenuid() + "] failure, please do the operation again or contact the sysadmin.", t);
		}
	}
	
	private AcMenu[] queryChildMenu(AcMenu appMenu){
		CriteriaType criteria = CriteriaType.FACTORY.create();
		criteria.set_entity(AcMenu.QNAME);
		criteria.set("_expr[1]/menuseq", appMenu.getMenuseq());
		criteria.set("_expr[1]/_op","like");
		criteria.set("_expr[1]/_likeRule","end");
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		AcMenu[] results = getDASTemplate().queryEntitiesByCriteriaEntity(AcMenu.class, dasCriteria);
		return results;
	}
	
	public void modifyMenuRelation(String menuId, String targetMenuId) {
		//查找currentMenu
		AcMenu currentMenu = AcMenu.FACTORY.create();
		currentMenu.setMenuid(menuId);
		getDASTemplate().expandEntity(currentMenu);
		
		//查找tagertmenu
		AcMenu targetMenu = AcMenu.FACTORY.create();
		targetMenu.setMenuid(targetMenuId);
		getDASTemplate().expandEntity(targetMenu);
		
		//查找currentMenu下所有的子孙
		AcMenu[] appMenus = queryChildMenu(currentMenu);
		
		List<AcMenu> updateList = new ArrayList<AcMenu>();
		currentMenu.setAcMenu(targetMenu);
		for(AcMenu appmenu : appMenus){
			//修改currentmenu的parent，修改currentmenu及子孙的seq
			if(StringUtils.equals(appmenu.getMenuid(), menuId)){
				appmenu.setAcMenu(targetMenu);
				appmenu.setMenuseq(targetMenu.getMenuseq() + menuId + ".");
			}else{
				appmenu.setMenuseq(StringUtils.replace(appmenu.getMenuseq(), currentMenu.getMenuseq(), targetMenu.getMenuseq() + menuId + "."));
			}
			appmenu.setMenulevel((short)((int)appmenu.getAcMenu().getMenulevel()+1));
			updateList.add(appmenu);
		}
		//修改tagertmenu的subcount
		targetMenu.setSubcount(targetMenu.getSubcount());
		updateList.add(targetMenu);
		//批量更新menu
		try{
			getDASTemplate().updateEntityBatch(updateList.toArray(new AcMenu[updateList.size()]));
	    } catch (Throwable t) {
			log.error("Update menus failure, please do the operation again or contact the sysadmin.", t);
		}
	}
	public int validateAppMenu(AcMenu appMenu) {
		AcMenu[] menus = getDASTemplate().queryEntitiesByTemplate(AcMenu.class, appMenu);
		if(menus.length > 0){
			return 1;
		}
		return 0;
	}
    
}
