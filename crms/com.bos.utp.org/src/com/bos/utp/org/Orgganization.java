package com.bos.utp.org;

import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

public class Orgganization {

	/**
	 * 根据机构类型更新机构排序
	 * 
	 * @param oOrg
	 * @return
	 */
	@Bizlet("根据机构类型更新机构排序")
	public void updateOrgSortNo(String orgcode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgcode", orgcode);
		// 根据机构类型更新机构排序
		try {
			DatabaseExt.executeNamedSql("default",
					"com.bos.utp.org.organization.updateOrgSortNo", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除人员机构及关联信息
	 * @param orgid   机构id
	 */
	@Bizlet("删除人员机构及关联信息")
	public static String deleteOrg(String orgid){
		
		String flag = "-1";
		
		if(orgid == null || "".equals(orgid)) return flag;
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("orgid", orgid);
		
		try {
			//1.删除机构人员关联表信息
			DatabaseExt.executeNamedSql("default", "com.bos.utp.org.util.orgUtilns.deleteEmporg", map);
			//2.删除操作者权限关联表信息
			DatabaseExt.executeNamedSql("default", "com.bos.utp.org.util.orgUtilns.deleteOperatorrole", map);
			//3.删除操作者
			DatabaseExt.executeNamedSql("default", "com.bos.utp.org.util.orgUtilns.deleteOperator", map);
			//4.删除员工信息
			DatabaseExt.executeNamedSql("default", "com.bos.utp.org.util.orgUtilns.deleteEmployee", map);
			//5.删除机构信息
			DatabaseExt.executeNamedSql("default", "com.bos.utp.org.util.orgUtilns.deleteOrganizaiton", map);
			
			flag = "1";
		} catch (Exception e) {
			return flag;
		}
		return flag;
		
	}
	
	/**
	 * 转换机构地址，由地址code翻译成中文地址
	 * @return
	 */
	@Bizlet("转换机构地址，由地址code翻译成中文地址")
	public static String trunAddrCodeToString(String addr){
		
		StringBuffer  orgaddr =new StringBuffer();
		
		if(null != addr && !"".equals(addr)){
			
			String[] addrs = addr.split("\\.");
			for (int i = 0; i < addrs.length; i++) {
				String string = addrs[i];
				if(i == addrs.length-1){
					orgaddr.append(string);
					continue;
				}else{
					
					if(null!=string && !"null".equals(string)){
						
						Object [] objs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.utp.org.organization.queryAddrName", string);
						if(null!=objs && objs.length>0){
							orgaddr.append(objs[0]);
						}
					}
					
				}
			}
			
		}
		return orgaddr.toString();
	}

}
