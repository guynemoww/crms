package com.bos.utp.auth.permission.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 权限的角色模型JavaBean
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: RoleModel.java,v $
 * Revision 1.5  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.4  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.3  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.2  2009/01/21 05:24:54  caisy
 * 消除编译警告
 *
 * Revision 1.1  2009/01/05 02:34:57  caisy
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.4  2008/09/25 16:35:51  wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包
 * 并修改缓存模型删除模型中的名称XXXname字段
 *
 * Revision 1.3  2008/09/07 07:40:10  wengzr
 * Update:修改权限校验接口
 *
 * Revision 1.2  2008/08/18 14:19:49  wengzr
 * 提交CVS
 *
 * Revision 1.1  2008/08/13 15:55:59  wengzr
 * 提交CVS
 *
 */
public class RoleModel implements java.io.Serializable{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2570853045962157454L;

	//角色ID
	private String roleId;

	//以FuncAction为key,FunctionModel为value
	private Map<String,FunctionModel> functions=new HashMap<String, FunctionModel>();

	public RoleModel(String roleId){
		this.roleId=roleId;
	}

	public Map<String, FunctionModel> getFunctions() {
		return functions;
	}

	public void setFunctions(Map<String, FunctionModel> functions) {
		this.functions = functions;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
