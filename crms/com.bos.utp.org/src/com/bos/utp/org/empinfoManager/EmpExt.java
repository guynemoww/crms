/**
 * @author guyan
 * @date 2014-04-28 09:19:41
 *
 */
package com.bos.utp.org.empinfoManager;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmEmporg;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.impl.OmEmployeeImpl;
import com.bos.utp.dataset.organization.impl.OmEmporgImpl;
import com.bos.utp.dataset.organization.impl.OmOrganizationImpl;
import com.bos.utp.dataset.privilege.AcOperator;
import com.bos.utp.dataset.privilege.AcOperatorrole;
import com.bos.utp.dataset.privilege.AcRole;
import com.bos.utp.dataset.privilege.impl.AcOperatorImpl;
import com.bos.utp.dataset.privilege.impl.AcOperatorroleImpl;
import com.bos.utp.dataset.privilege.impl.AcRoleImpl;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.data.sdo.impl.DataObjectImpl;
import commonj.sdo.DataObject;

@Bizlet("EmpExt")
public class EmpExt {

    /**
     * 插入更新人员角色及人员机构
     * @param addroleids
     * @param addorgjson
     * @param delroleids
     * @param delorgjson
     * @param operatorid 
     * @param empid 
     * @param inorgid 主机构
     * @return
     */
    @Bizlet("saveEmpRoleAndOrg")
    public int saveEmpRoleAndOrg(String addroleids, String addorgjson, String delroleids, 
            String delorgjson,String operatorid, String empid, String inorgid){
    	
    	Map<String, List<String>> orgrolemap = new HashMap<String, List<String>>();//界面已经展开的机构及该机构拥有的角色列表
    	Map<String, List<String>> addorgrolemap = new HashMap<String, List<String>>();//界面已经选中的机构及该机构拥有的角色列表
    	String[] addorgarray = null;
    	if (StringUtils.isNotBlank(addorgjson)) {
    		addorgarray = addorgjson.split(",");
    		for (String orgid : addorgarray) {
				Object[] tempres = DatabaseExt.queryByNamedSql("default", "com.bos.utp.org.organization.queryRoleIdsByOrg", orgid);
				List<String> roleList = new ArrayList<String>();
				for (Object object : tempres) {
					DataObjectImpl dd = (DataObjectImpl)object;
					roleList.add(dd.getString("ROLEID"));
				}
				orgrolemap.put(orgid, roleList);
				addorgrolemap.put(orgid, roleList);
    		}
    	}
    	String[] delorgarray = null;
    	if (StringUtils.isNotBlank(delorgjson)) {
    		delorgarray = delorgjson.split(",");
    		for (String orgid : delorgarray) {
				Object[] tempres = DatabaseExt.queryByNamedSql("default", "com.bos.utp.org.organization.queryRoleIdsByOrg", orgid);
				List<String> roleList = new ArrayList<String>();
				for (Object object : tempres) {
					DataObjectImpl dd = (DataObjectImpl)object;
					roleList.add(dd.getString("ROLEID"));
				}
				orgrolemap.put(orgid, roleList);
    		}
    	}
    	
    	
        //删除人员角色
        if(delroleids!=null && !"".equals(delroleids)){
            String[] roleidarray = delroleids.split(",");
            List<AcOperatorrole> addlist = new ArrayList<AcOperatorrole>();
            //遍历待删除的角色
            for (int i = 0; i < roleidarray.length; i++) {
//                acopeRole.setOrgid(0);
                Set<String> key = orgrolemap.keySet();
                //遍历界面展开的机构ID
                for (Iterator it = key.iterator(); it.hasNext();) {
                    String s = (String) it.next();
                    List<String> roleList = orgrolemap.get(s);
                    //判断此机构是否含有该角色，若含有则判断用户是否含有该机构-角色信息，有则删除
                    if (roleList.contains(roleidarray[i])) {
                    	Map<String, Object> params = new HashMap<String, Object>();
                    	params.put("operatorid", operatorid);
                    	params.put("roleId", roleidarray[i]);
                    	params.put("orgId", s);
                    	Object[] a = DatabaseExt.queryByNamedSql("default", "com.bos.utp.org.organization.countoperole", params);
                    	if (a.length > 0) {
                            AcOperatorrole acopeRole = new AcOperatorroleImpl();
                            AcOperator acoperator = new AcOperatorImpl();
                            acoperator.setOperatorid(new BigDecimal(operatorid));
                            acopeRole.setAcOperator(acoperator);
                            AcRole acRole = new AcRoleImpl();
                            acopeRole.setOrgid(Integer.parseInt(s));
                            acRole.setRoleid(roleidarray[i]);
                            acopeRole.setAcRole(acRole);
                            addlist.add(acopeRole);
                    	}
                    }
                }
            }
            AcOperatorrole[] acoperoles = new AcOperatorrole[addlist.size()];
            for (int i = 0 ;i < addlist.size(); i++) {
                acoperoles[i] = addlist.get(i);
			}
            DatabaseUtil.deleteEntityBatch("default", acoperoles);
        }
        
        
        //删除人员机构
        if(null!=delorgjson && !"".equals(delorgjson)){
            String[] orgarray = delorgjson.split(",");
            OmEmporg[] omemporgs = new OmEmporgImpl[orgarray.length];
            for (int i = 0; i < orgarray.length; i++) {
                OmEmporg omemporg = new OmEmporgImpl();
                OmOrganization org = new OmOrganizationImpl();
                org.setOrgid(new BigDecimal(orgarray[i]));
                omemporg.setOmOrganization(org);
                OmEmployee emp = new OmEmployeeImpl();
                emp.setEmpid(new BigDecimal(empid));
                omemporg.setOmEmployee(emp);
                if(inorgid.equals(orgarray[i])){
                    omemporg.setIsmain("1");
                }else{
                    omemporg.setIsmain("2");
                }
                omemporgs[i] = omemporg;
            }
            DatabaseUtil.deleteEntityBatch("default", omemporgs);
        }
        
        //更新人员角色
        if(null!=addroleids && !"".equals(addroleids)){
            String[] roleidarray = addroleids.split(",");
            List<AcOperatorrole> oprolelist = new ArrayList<AcOperatorrole>();
            //遍历待新增的角色
            for (int i = 0;i < roleidarray.length; i++) {
                Set<String> key = addorgrolemap.keySet();
                //遍历界面展开且选中的机构ID
                for (Iterator it = key.iterator(); it.hasNext();) {
                    String s = (String) it.next();
                    List<String> roleList = addorgrolemap.get(s);
                    //若机构的角色列表中含有该角色ID则新增用户-角色信息
                    if (roleList.contains(roleidarray[i])) {
                    	if (StringUtils.isNotBlank(s)) {
                            AcOperatorrole acopeRole = new AcOperatorroleImpl();
                            AcOperator acoperator = new AcOperatorImpl();
                            acoperator.setOperatorid(new BigDecimal(operatorid));
                            acopeRole.setAcOperator(acoperator);
                            AcRole acRole = new AcRoleImpl();
                            acRole.setRoleid(roleidarray[i]);
                            acopeRole.setAcRole(acRole);
                    		acopeRole.setOrgid(Integer.parseInt(s));
                    		oprolelist.add(acopeRole);
                    	}
                    }
                }
            }
            AcOperatorrole[] acoperoles = new AcOperatorrole[oprolelist.size()];
            for (int i = 0; i < oprolelist.size(); i++) {
            	acoperoles[i] = oprolelist.get(i);
            }
            DatabaseUtil.saveEntities("default", acoperoles);
        }
        
        //更新人员机构
        if(null!=addorgjson && !"".equals(addorgjson)){
            String[] orgarray = addorgjson.split(",");
            OmEmporg[] omemporgs = new OmEmporgImpl[orgarray.length];
            for (int i = 0; i < orgarray.length; i++) {
                OmEmporg omemporg = new OmEmporgImpl();
                OmOrganization org = new OmOrganizationImpl();
                org.setOrgid(new BigDecimal(orgarray[i]));
                omemporg.setOmOrganization(org);
                OmEmployee emp = new OmEmployeeImpl();
                emp.setEmpid(new BigDecimal(empid));
                omemporg.setOmEmployee(emp);
                if(inorgid.equals(orgarray[i])){
                    omemporg.setIsmain("1");
                }else{
                    omemporg.setIsmain("2");
                }
                omemporgs[i] = omemporg;
            }
            DatabaseUtil.saveEntities("default", omemporgs);
        }
        
        //删除不属于该用户所属机构下的角色
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("empid", empid);
        param.put("operatorid", operatorid);
        DatabaseExt.executeNamedSql("default", "com.bos.utp.org.omemployee.delrolewithoutinemp", param);
        
        return 1;
    }

    /**
     * 删除用户在机构下的角色关系
     * @param empId
     * @param orgId
     */
    @Bizlet("delemporgroles")
    public void delemporgroles(String empId, String orgId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("empid", empId);
        param.put("orgid", orgId);
        DatabaseExt.executeNamedSql("default", "com.bos.utp.org.organization.delemporgroles", param);
    }
    
    /**
     * 处理获取用户角色关系数组
     * @param empId
     * @param orgId
     * @param rolesjson
     * @return
     */
    @Bizlet("getacroles")
    public DataObject[] getacroles(String empId, String orgId, String rolesjson) {
    	AcOperatorrole[] res = new AcOperatorrole[]{};
    	if(StringUtils.isNotBlank(rolesjson)){
            String[] roleidarray = rolesjson.split(",");
            res = new AcOperatorrole[roleidarray.length];
            for (int i = 0; i < roleidarray.length; i++) {
                AcOperatorrole acopeRole = new AcOperatorroleImpl();
                AcOperator acoperator = new AcOperatorImpl();
                acoperator.setOperatorid(new BigDecimal(empId));
                acopeRole.setAcOperator(acoperator);
                AcRole acRole = new AcRoleImpl();
                acRole.setRoleid(roleidarray[i]);
                acopeRole.setAcRole(acRole);
        		acopeRole.setOrgid(Integer.parseInt(orgId));
        		res[i] = acopeRole;
			}
    	}
    	return res;
    }
    
    /**
     * 查询用户角色并拼装字符串
     * @param empId
     * @param orgId
     * @return
     */
    @Bizlet("queryEmpRoles")
    public String queryEmpRoles(String empId, String orgId){
    	String rs = "";
    	Map<String, Object> param = new HashMap<String, Object>();
        param.put("empid", empId);
        param.put("orgid", orgId);
        Object[] roles = DatabaseExt.queryByNamedSql("default", "com.bos.utp.org.organization.queryEmpRoles", param);
        StringBuilder sb = new StringBuilder();
        if (roles.length > 0) {
            for (Object object : roles) {
    			DataObject dob = (DataObjectImpl)object;
    			sb.append(dob.get("ROLEID")).append(",");
    		}
            rs = sb.toString();
        }
        return rs;
    }
    
    
    /**
     * 根据empId查询userId
     * @param empId
     * @param userId
     * @return
     */
    @Bizlet("queryUserIdByEmpId")
    public String queryUserIdByEmpId(String empId){
    	String userId = "";
    	//int empid = Integer.parseInt(empId);
    	Map<String, Object> param = new HashMap<String, Object>();
        param.put("empid", empId);
        Object[] userIdObj = DatabaseExt.queryByNamedSql("default", "com.bos.utp.org.organization.queryUserIdByEmpId", param);
        if (userIdObj.length > 0) {
            Object object = userIdObj[0];
            DataObject dob = (DataObjectImpl)object;
            userId = (String)dob.get("USERID");
        }
        return userId;
    }
    
}
