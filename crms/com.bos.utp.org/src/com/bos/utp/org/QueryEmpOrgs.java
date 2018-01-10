/**
 * 
 */
package com.bos.utp.org;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bos.bps.util.CommonUtil;
import com.eos.data.datacontext.IUserObject;
import com.eos.data.datacontext.UserObject;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author guyan
 * @date 2014-04-23 11:26:42
 *
 */
@Bizlet("QueryEmpOrgs")
public class QueryEmpOrgs {
    @Bizlet("setEmpOrgs")
    public DataObject[] setEmpOrgs(DataObject[] datas){
        if(null != datas && datas.length > 0){
            for (int i = 0; i < datas.length; i++) {
                Map<String,Object> param = new HashMap<String, Object>(); 
                param.put("empid", datas[i].get("empid"));
                Object[] dos = DatabaseExt.queryByNamedSql("default", "com.bos.utp.org.empQuery.queryEmpOrgsByEmpid", param);
                String orgids = "";
                String orgnames = "";
                for(int j = 0; j < dos.length;j++){
                    DataObject doi = (DataObject)dos[j];
                    if(j != dos.length-1){
                        orgids += (doi.get("ORGID")+",");
                        orgnames += (doi.get("ORGNAME")+",");
                    }else{
                        orgids += doi.get("ORGID");
                        orgnames += doi.get("ORGNAME");
                    }
                }
                datas[i].set("orgids", orgids);
                datas[i].set("orgnames", orgnames);
            }
        }
        return datas;
    }

    /**
     * 根据用户ID查询机构信息
     * @param empId 用户ID
     * @param inorgId 主机构ID
     */
    @Bizlet("queryOrgsByEmpId")
    public DataObject[] queryOrgsByEmpId(String empId, String inorgId, DataObject page) {
        DataObject[] orgs = new DataObject[]{};
    	Map<String,Object> param = new HashMap<String, Object>(); 
        param.put("empid", empId);
        Object[] dos = DatabaseExt.queryByNamedSqlWithPage("default", "com.bos.utp.org.organization.queryOrgsByEmpId", page, param);
        if (null != dos) {
        	orgs = new DataObject[dos.length];
        	for(int j = 0; j < dos.length;j++){
        		DataObject doi = (DataObject)dos[j];
        		if (StringUtils.isNotBlank(inorgId)){//主机构存在
        			if (inorgId.equals(String.valueOf(doi.getBigDecimal("ORGID")))) {
        				doi.setString("MAINFLAG", "是");
        			}
        		}
        		orgs[j] = doi;
        	}
        }
        return orgs;
    }
    
    /**
     * 根据机构ID查询角色信息
     * @param orgId 机构ID
     */
    @Bizlet("queryRolesByorgId")
    public DataObject[] queryRolesByorgId(String orgId, DataObject page) {
        DataObject[] orgs = new DataObject[]{};
    	Map<String,Object> param = new HashMap<String, Object>(); 
        param.put("orgid", orgId);
        page.set("isCount", true);
        IUserObject user = CommonUtil.getIUserObject();
		String userid = user.getUserId();
        if(!"sysadmin".equals(userid)){//是否超级系统管理员,如果不是需过滤机构角色中的超级管理员角色
        	 param.put("isAdmin", "1");
        }
       
        Object[] dos = DatabaseExt.queryByNamedSqlWithPage("default", "com.bos.utp.org.organization.queryRolesByorgId", page, param);
        if (null != dos) {
        	orgs = new DataObject[dos.length];
        	for(int j = 0; j < dos.length;j++){
        		DataObject doi = (DataObject)dos[j];
        		orgs[j] = doi;
        	}
        }
        return orgs;
    }
}
