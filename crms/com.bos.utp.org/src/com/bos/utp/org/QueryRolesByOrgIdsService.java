/**
 * 
 */
package com.bos.utp.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.utp.dataset.organization.OmOrganization;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.DASDaoSupport;
import com.primeton.data.sdo.impl.DataObjectImpl;
import commonj.sdo.DataObject;

/**
 * <pre>
 * Title: 程序的中文名称
 * Description: 程序功能的描述
 * </pre>
 * @author guyan
 * @version 1.00.00
 * 
 */
/*
 * 修改历史
 * $log$
 */
public class QueryRolesByOrgIdsService  extends DASDaoSupport{ 
    

    /**
     * 根据机构id集合获取角色集合
     * @param orgids
     * @return
     */
    public DataObject[] queryRolesByOrgIds(String orgids, String loginPOrgid){
    	try {
    		if(null!=orgids && !"".equals(orgids)){
                if(orgids.indexOf("[")>=0){
                    orgids = orgids.replaceAll("\"", "");
                    orgids = orgids.substring(1,orgids.length()-1);
                }else{
                    orgids = orgids.replaceAll("\"", "");
                }
                
                if (loginPOrgid != null && loginPOrgid.length() > 0) {
            		List<String> list = new ArrayList<String>();
            		String[] tempArray = orgids.split(",");
            		for (int i = 0; i < tempArray.length; i++) {
            			list.add(tempArray[i]);
            		}
            		String orgid = loginPOrgid;
            		while(orgid != null && orgid.length() > 0) {
            			Object object = getOrgInfo(orgid);
            			if (object != null) {
            				DataObject omOrg = (DataObject) object;
    						String parentOrgid = omOrg.getString("PARENTORGID");
    						if (parentOrgid != null && parentOrgid.length() > 0) {
    							if (!loginPOrgid.equals(orgid)) {
    								if (list.contains(orgid)) {
    									list.remove(orgid);
    								}
    							}
    							orgid = parentOrgid;
    						} else {
    							if (!loginPOrgid.equals(orgid)) {
    								if (list.contains(orgid)) {
    									list.remove(orgid);
    								}
    							}
    							orgid = null;
    						}
    					} else {
    						orgid = null;
    					}
            		}
            		if (list != null && list.size() > 0) {
            			orgids = "";
            			for (String temp : list) {
            				orgids += temp + ",";
            			}
            			if (orgids != null && orgids.length() > 0) {
            				orgids = orgids.substring(0, orgids.length() - 1);
            			}
            		} else {
            			orgids = "";
            		}
            	}
            }
            
            if (orgids != null && orgids.length() > 0) {
            	System.out.println("orgids="+orgids);
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("orgids", orgids);
                DataObjectImpl[] roles = getDASTemplate().queryByNamedSql(DataObjectImpl.class, "com.bos.utp.org.organization.queryRolesByOrgIds", param);
                return roles;
            } else {
                return null;
            }
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    private Object getOrgInfo(String orgid) throws Exception {
		Object orgInfo = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("orgid", orgid);
			Object[] object = DatabaseExt.queryByNamedSql("default", "com.bos.utp.org.position.queryOrgInfo", map);
			if (object != null && object.length > 0) {
				orgInfo = object[0];
			}
		} catch (Exception e) {
			throw e;
		}
		return orgInfo;
	}

}
