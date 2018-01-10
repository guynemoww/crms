package com.bos.utp.auth.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eos.access.http.IWebInterceptor;
import com.eos.access.http.IWebInterceptorChain;

/**
 *
 * IP安全访问拦截器
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: IPSecurityInterceptor.java,v $
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
 * Revision 1.1  2008/10/07 09:25:50  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/09/25 16:35:51  wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包
 * 并修改缓存模型删除模型中的名称XXXname字段
 *
 * Revision 1.1  2008/09/21 15:51:45  wengzr
 * Update:重构AT模块的数据实体
 *
 */
public class IPSecurityInterceptor implements IWebInterceptor{

	public void doIntercept(HttpServletRequest request, HttpServletResponse response, IWebInterceptorChain chain) throws IOException, ServletException {

	}

}
