package com.bos.utp.auth.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import com.bos.utp.auth.bizlet.PermissionUtil;

import com.eos.access.authorization.CheckedResult;
import com.eos.data.datacontext.IUserObject;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;


/*
 * 修改历史
 * $Log: PermissionTag.java,v $
 * Revision 1.10  2010/12/09 06:29:33  caisy
 * 重构haspermission返回值
 *
 * Revision 1.9  2010/12/02 06:48:44  caisy
 * 增加异常日志输出
 *
 * Revision 1.8  2010/12/02 05:53:48  caisy
 * 增加initAttributes方法
 *
 * Revision 1.7  2010/12/01 10:01:05  caisy
 * 权限校验抽取为公用方法
 *
 * Revision 1.6  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.5  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.4  2009/04/14 03:54:32  caisy
 * 资源权限控制
 *
 * Revision 1.3  2009/03/30 05:39:39  caisy
 * 代码规范
 *
 * Revision 1.2  2009/01/21 05:24:54  caisy
 * 消除编译警告
 *
 * Revision 1.1  2009/01/05 02:34:57  caisy
 *
 */
public class PermissionTag extends BaseIteratorTagSupport{
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 8121152106347375612L;

	@Override public int doStartTag() {
		try{
		initAttributes();
		Object obj = this.getOutput();
        String url =(obj==null)?null:obj.toString();	
        HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		IUserObject userObject=(IUserObject)request.getSession().getAttribute(IUserObject.KEY_IN_CONTEXT);
		if ( CheckedResult.THREAD_ACCESSED_PASS.equals(PermissionUtil.hasPermission(url,userObject))) {
			return EVAL_BODY_INCLUDE;			
		} else {
			return SKIP_BODY;
		}		
		}catch(Exception e){
			LogUtil.logError("权限标签执行出错!", e, (Object)null);
			return SKIP_BODY;
		}
	}
	@Override  public void initAttributes() throws JspException {
	         super.initAttributes();
	}
	/**
	 * 获得输出的对象
	 *
	 * @return
	 */
	private String getOutput() {
	   Object	propertyObject = this.getPropertyValue();
		// 如果proprety取值为null,取value属性的值
		if (propertyObject != null) {
			return propertyObject.toString();
		} else {
			return this.getValue();
		}
	}
}
