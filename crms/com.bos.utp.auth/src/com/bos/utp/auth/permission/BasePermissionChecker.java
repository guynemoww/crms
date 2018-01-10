package com.bos.utp.auth.permission;

import com.eos.data.datacontext.IUserObject;
import commonj.sdo.DataObject;


/**
 *
 * 功能权限校验模板实现<BR>
 * 实现初始化用户对象(UserObject)获取操作员角色集合，以及实现hasAccessPermission方法校验权限.<BR>
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: BasePermissionChecker.java,v $
 * Revision 1.5  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.4  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.3  2009/04/14 03:54:32  caisy
 * 资源权限控制
 *
 * Revision 1.2  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/05 02:34:50  caisy
 *
 * Revision 1.1  2008/10/07 09:25:50  wengzr
 * *** empty log message ***
 *
 * Revision 1.8  2008/09/11 13:55:57  wengzr
 * Update:修改登录实现方式和权限校验BUG
 *
 * Revision 1.7  2008/09/07 07:40:10  wengzr
 * Update:修改权限校验接口
 *
 * Revision 1.2  2008/08/14 15:46:45  wengzr
 * 提交CVS
 *
 * Revision 1.1  2008/08/13 15:55:59  wengzr
 * 提交CVS
 *
 */
public abstract class BasePermissionChecker implements PermissionChecker{

	protected String[] roles;

	protected String operatorid;

	protected boolean checkGuest;

	public void init(IUserObject userObject,boolean checkGuest){

		if(userObject!=null){
			DataObject[] acRoles=(DataObject[])userObject.getAttributes().get("roles");
			if(acRoles!=null){
				roles=new String[acRoles.length];
				for(int i=0;i<acRoles.length;i++){
					roles[i]=acRoles[i].getString("roleid");
				}
			}
			operatorid=userObject.getAttributes().get("operatorid")+"";
		}

		this.checkGuest=checkGuest;
	}


	/**
	 * 判断指定的角色是否拥有某一功能的权限
	 * @param roleid 角色ID
	 * @param action 功能入口
	 * @param checkResource 是否检查资源
	 * @return true-有权限,false-无权限
	 */
	public abstract boolean checkRolePermission(String roleid, String action,boolean isResource);

	/**
	 * 校验附加功能权限(不通过角色授权)
	 *
	 * @param operatorid 操作员ID
	 * @param action 功能入口
	 * @param checkResource 是否检查资源
	 * @return
	 */
	public abstract int checkAdditionalPermission(String operatorid,String action,boolean isResource);


	public boolean getCheckGuest(){
		return checkGuest;
	}


	public String[] getOperatorRoles() {
		return roles;
	}

	public String getOperatorId(){
		return operatorid;
	}
	/**
	 * 判断当前action是否具有可访问权限
	 * @param isResource 是否资源类型
	 * @return
	 */
	public boolean hasAccessPermission(String action,boolean isResource){
		//不检查guest用户权限
		if(!checkGuest)
			return true;

		int checkResult=checkAdditionalPermission(operatorid,action,isResource);
		if(checkResult==AUTH_TYPE_RESULT_ON)
			return true;
		if(checkResult==AUTH_TYPE_RESULT_OFF)
			return false;
         
		if(roles!=null){
			for(int i=0;i<roles.length;i++){
				if(this.checkRolePermission(roles[i], action,isResource)){
					return true;
				}

			}
		}
		return false;
	}

	public void setCheckGuest(boolean checkGuest) {
		this.checkGuest=checkGuest;

	}

	public void recycle() {
		roles = new String[0];
		operatorid =null;
		checkGuest = false;
	}
}
