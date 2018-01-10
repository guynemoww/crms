/**
 * 
 */
package com.bos.utp.org;


import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.primeton.data.sdo.impl.DataObjectImpl;

import commonj.sdo.DataObject;

/**
 * @author guyan
 * @date 2014-04-27 18:19:22
 *
 */
@Bizlet("AcRoleExt")
public class AcRoleExt {

    /**
     * 根据角色id查询角色
     * @param roleids
     * @return
     */
    @Bizlet("queryRolesByids")
    public DataObject[] queryRolesByids(DataObject[] roleids){
        String s = "";
        if(null!=roleids && roleids.length>0){
            for (int i = 0; i < roleids.length; i++) {
                s += ("'"+roleids[i].getString("ROLEID")+"',");
            }
            s = s.substring(0, s.length()-1);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("roleids", s);
            Object[] roles = DatabaseExt.queryByNamedSql("default", "com.bos.utp.org.organization.queryRoleByids", param);
            DataObject[] res = new DataObject[roles.length];
            for(int i = 0; i < roles.length; i++){
                res[i] = (DataObject)roles[i];
            }
            return res;
        }else{
            return null;
        }
    }
}
