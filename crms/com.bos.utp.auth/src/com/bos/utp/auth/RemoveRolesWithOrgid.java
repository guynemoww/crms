/**
 * 
 */
package com.bos.utp.auth;


import java.util.HashMap;
import java.util.Map;

import com.bos.utp.dataset.privilege.OmPartyrole;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author guyan
 * @date 2014-04-23 15:38:06
 *
 */
@Bizlet("RemoveRolesWithOrgid")
public class RemoveRolesWithOrgid {
    
    @Bizlet("removeOrgRole")
    public void removeOrgRole(OmPartyrole orgRole){
        //删除本机构角色
        //DatabaseUtil.deleteEntity("default", orgRole);
    	 Map<String,Object> param = new HashMap<String, Object>();
//        String orgid = orgRole.getString("partyid");
//        Map<String,Object> param = new HashMap<String, Object>();
//        param.put("parentid", orgid);
//        //查询seq及level
//        param.put("orgid", orgid);
//        Object[] seqlevel = DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.organization.getorgseqlevelbypid", param);
//        String orgseq = (String)((DataObject)seqlevel[0]).get("ORGSEQ");
//        Object orglevel = ((DataObject)seqlevel[0]).get("ORGLEVEL");
//        //查询子机构id
//        param.put("orgseq", orgseq);
//        Object[] orgos = DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.organization.getorgidbypseq", param);
//        
//        for (int i = 0; i < orgos.length; i++) {
//            DataObject doi = (DataObject)orgos[i];
//            String subOrgid = doi.getString("ORGID");
//
//            //删除机构角色
//            orgRole.set("partyid", subOrgid);
//            DatabaseUtil.deleteEntity("default", orgRole);
//            
//            
//            //查询机构下人员
//            param.put("orgid", subOrgid);
//            Object[] empos = DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.organization.getEmpOpeIdByOrgId", param);
//            if(null != empos && empos.length>0){
//                for (Object object2 : empos) {
//                    DataObject doi2 = (DataObject)object2;
//                    String empid = doi2.getString("EMPID");
//                    String operatorid = doi2.getString("OPERATORID");
//                    
//                    
//                    param.put("empid", empid);
//                    param.put("roleid", orgRole.getAcRole().getRoleid());
//                    param.put("orglevel", orglevel);
//                    param.put("operatorid", operatorid);
//                    //当机构id为orgid时判断该人员所属上级及平行机构是否存在该角色
//                    Object[] res = DatabaseExt.queryByNamedSql("default", "com.bos.utp.auth.organization.judgeemprole", param);
//                    if(res.length>=1){
//                        continue;
//                    }else{
//                        //删除人员角色
//                        DatabaseExt.executeNamedSql("default", "com.bos.utp.auth.organization.deleteOperatorRole", param);
//                    }
//                }
//            }
//        }
    	  String orgid = orgRole.getString("partyid");
    	  param.put("orgid", orgid);
    	  param.put("roleid", orgRole.getAcRole().getRoleid());
    	  DatabaseExt.executeNamedSql("default", "com.bos.utp.auth.organization.deleteOmPartyRole", param);
    	  DatabaseExt.executeNamedSql("default", "com.bos.utp.auth.organization.deleteOperatorRole", param);
    	 
    }
    
}
