package com.bos.utp.auth.handler;

/**
 *
 * 业务日志信息JavaBean
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: LogConfig.java,v $
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
 * Revision 1.1  2008/11/25 06:23:10  wengzr
 * Update:修改业务日志实现方式
 * Update:修改业务日志表和日志配置表,增加日志类型字段
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/09/26 15:24:51  wengzr
 * refactor:重构权限缓存数据的获取方式,增加permission_data_provider配置
 *
 */
public class LogConfig {

	public static final String LOG_EVENT_BEFORE="before";

	public static final String LOG_EVENT_AFTER="after";

	public static final String LOG_EVENT_EXCEPTION="exception";


	//功能代码
	private String funccode;

	//功能入口
	private String funcaction;

	//日志输出类型
	private String outputtype;

	//日志触发时机
	private String logevent;
	
	//日志类型
	private String logtype;

	public String getFuncaction() {
		return funcaction;
	}

	public void setFuncaction(String funcaction) {
		this.funcaction = funcaction;
	}

	public String getFunccode() {
		return funccode;
	}

	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}

	public String getLogevent() {
		return logevent;
	}

	public void setLogevent(String logevent) {
		this.logevent = logevent;
	}

	public String getOutputtype() {
		return outputtype;
	}

	public void setOutputtype(String outputtype) {
		this.outputtype = outputtype;
	}

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}


}
