package com.bos.utp.auth.ldap;

import com.eos.system.exception.EOSRuntimeException;

/**
 * 
 * LDAP密码过期异常
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: PasswordExpiredException.java,v $
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
public class PasswordExpiredException extends EOSRuntimeException{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -9028293160390977308L;

	public PasswordExpiredException(){
		super("PasswordExpired");
	}
}
