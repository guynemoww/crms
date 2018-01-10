/**
 *
 * 可访问资源模型<BR>
 * @author charles
 * caisy (mailto:caisy@primeton.com)
 */
package com.bos.utp.auth.permission.model;

import java.io.Serializable;
import java.util.Map;

public interface IAccessResource extends Serializable {
	
	/**
	 * 资源编号
	 * <pre>
	 * 资源编号与资源类型应能唯一标识一个资源
	 * </pre>
	 * @return 资源编号
	 */
	public String getResourceId();
	/**
	 * 资源类型
	 * 	 <pre>
	 * 资源类型与资源编号应能唯一标识一个资源
	 * </pre>
	 * @return 资源类型
	 */
	public String getResourceType();
	/**
	 * 资源的URI标识
	 * 如：页面流的全名
	 *        逻辑流的全名等
	 * @return URI标识
	 */
	public String getResourceUri();

	/**
	 * 资源的附加信息
	 * @return
	 */
	public Map getResouceAttributes();
}
