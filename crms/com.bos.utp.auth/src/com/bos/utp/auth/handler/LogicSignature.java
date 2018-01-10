package com.bos.utp.auth.handler;

/**
 * 
 * 逻辑流对象签名
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: LogicSignature.java,v $
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
 */
public class LogicSignature {
	
	/**
	 * 逻辑流显示名称
	 */
	private String logicLabel;
	
	/**
	 * 流程名称
	 */
	private String logicName;
	
	/**
	 * 输入参数数组
	 */
	private String[] parameterNames;
	
	/**
	 * 返回参数数组
	 */
	private String[] returnNames;

	public String getLogicLabel() {
		return logicLabel;
	}

	public void setLogicLabel(String logicLabel) {
		this.logicLabel = logicLabel;
	}

	public String getLogicName() {
		return logicName;
	}

	public void setLogicName(String logicName) {
		this.logicName = logicName;
	}
	

	public String[] getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(String[] parameterNames) {
		this.parameterNames = parameterNames;
	}

	public String[] getReturnNames() {
		return returnNames;
	}

	public void setReturnNames(String[] returnNames) {
		this.returnNames = returnNames;
	}


}
