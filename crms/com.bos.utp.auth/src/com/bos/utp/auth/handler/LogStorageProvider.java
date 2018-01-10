package com.bos.utp.auth.handler;


import com.eos.data.datacontext.IUserObject;
import com.primeton.ext.engine.core.IRuntimeContext;

/**
 *
 * 日志存储提供者接口
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: LogStorageProvider.java,v $
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
 * Revision 1.2  2008/11/25 06:23:10  wengzr
 * Update:修改业务日志实现方式
 * Update:修改业务日志表和日志配置表,增加日志类型字段
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.2  2008/10/05 14:11:25  wengzr
 * Added:增加业务日志功能
 *
 * Revision 1.1  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 */
public interface LogStorageProvider {
	
	/**
	 * 记录业务操作日志方法
	 * @param user 用户对象
	 * @param logConfig 操作日志配置
	 * @param runtimeContext 运行期上下文对象，通过get("参数名")方法可以获取对应的参数值
	 * @param logicSignature 逻辑流方法签名
	 * @param t Throwable实例,有异常触发则不为null
	 */
	public void log(IUserObject user,LogConfig logConfig,IRuntimeContext runtimeContext,LogicSignature logicSignature,Throwable t);

}
