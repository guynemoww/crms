package com.bos.utp.auth.permission;

import java.util.HashMap;

import com.eos.foundation.eoscommon.ServiceUtil;

/**
 *
 * 基于WebService实现权限数据的获取<BR>
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: WebServicePermissionDataAccessor.java,v $
 * Revision 1.6  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.5  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.4  2009/05/19 05:30:50  caisy
 * Update:Functionaction为空导致无法构件权限缓存
 *
 * Revision 1.3  2009/05/14 11:18:59  caisy
 * Update:Portal缓存判断错误，会导致所有的功能被人为portal资源
 *
 * Revision 1.2  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/05 02:34:50  caisy
 *
 * Revision 1.1  2008/10/07 09:25:50  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 */
public class WebServicePermissionDataAccessor implements PermissionDataAccessor {

	private static final String  wsdlEndpoint="http://com.bos.utp.auth/com/bos/utp/auth/PermissionManager#wsdl.port(PermissionManager/PermissionManagerSOAP)";

	public Object[] loadOperatorFunctionAll()throws Exception {
		return ServiceUtil.callWebService(wsdlEndpoint, "getOperatorFunctionAll", new Object[0]);
	}

	public Object[] loadOperatorFunctionAll(String operatorid) throws Exception{

		return ServiceUtil.callWebService(wsdlEndpoint, "getOperatorFunctionAll", new Object[]{operatorid});
	}

	public Object[] loadPortalResourceAll()throws Exception {

		return ServiceUtil.callWebService(wsdlEndpoint, "getPortalResourceAll", new Object[0]);
	}
	public Object[] loadPortalResourceAll(String portalid)throws Exception {
		HashMap<String ,String> param = new HashMap<String,String>();
		param.put("id", portalid);
		return ServiceUtil.callWebService(wsdlEndpoint, "getPortalResourceAll", param);
	}
	public Object[] loadRolePermissionAll()throws Exception{
		return ServiceUtil.callWebService(wsdlEndpoint, "getRolePermissionAll", new Object[0]);
	}

	public Object[] loadRolePermissionAll(String roleId)throws Exception {
		return ServiceUtil.callWebService(wsdlEndpoint, "getRolePermissionAll", new Object[]{roleId});
	}

}
