package com.bos.utp.auth.ldap;

import javax.naming.ldap.Control;

/**
 * 
 * 对LDAP验证结果进行封装的JAVABEAN
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: LDAPAuthResult.java,v $
 * Revision 1.4  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.3  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.2  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/05 02:34:57  caisy
 * 二期初始版本
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/08/21 09:42:24  wengzr
 * Refactor:重构sso包分opensso,simple以及cas的集成
 *
 * Revision 1.1  2008/08/18 14:19:49  wengzr
 * 提交CVS
 *
 */
public class LDAPAuthResult {
	
	private boolean _authenticated;
	private String _errorMessage;
	private String _responseControl = "";
	
	public boolean isAuthenticated() {
		return _authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		_authenticated = authenticated;
	}

	public String getErrorMessage() {
		return _errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	public String getResponseControl() {
		return _responseControl;
	}

	public void setResponseControl(Control[] response) {
		if ((response != null) && (response.length > 0)) {
			_responseControl = response[0].getID();
		}
	}


}
