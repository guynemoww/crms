package com.bos.utp.auth.permission.model;

/**
 *TODO 建议改为接口
 * 基本功能信息JavaBean，与命名sql permission.namingsqlx返回的字段一一对应.
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: BaseFunction.java,v $
 * Revision 1.7  2010/12/03 09:46:10  caisy
 * 删除无用代码
 *
 * Revision 1.6  2010/12/01 03:23:14  caisy
 * 配置文件读取方式修改
 *
 * Revision 1.5  2010/11/30 16:12:23  caisy
 * 编码改为UTF-8
 *
 * Revision 1.4  2010/10/18 14:03:49  caisy
 * 取消java中直接调用DatabaseExt
 *
 * Revision 1.3  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.2  2009/01/21 05:24:54  caisy
 * 消除编译警告
 *
 * Revision 1.1  2009/01/05 02:34:57  caisy
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.3  2008/09/25 16:35:51  wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包
 * 并修改缓存模型删除模型中的名称XXXname字段
 *
 * Revision 1.2  2008/08/18 14:19:49  wengzr
 * 提交CVS
 *
 * Revision 1.1  2008/08/13 15:55:59  wengzr
 * 提交CVS
 *
 */
public class BaseFunction implements java.io.Serializable{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 9039671607903238125L;

	protected String funccode;

	protected String appid;

	protected String funcgroupid;

	protected String funcaction;

	protected String ischeck;

	protected String functype;

	protected String ismenu;

	protected String respath;

	protected String restype;

	protected String resid;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

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

	public String getFuncgroupid() {
		return funcgroupid;
	}

	public void setFuncgroupid(String funcgroupid) {
		this.funcgroupid = funcgroupid;
	}

	public String getFunctype() {
		return functype;
	}

	public void setFunctype(String functype) {
		this.functype = functype;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public String getIsmenu() {
		return ismenu;
	}

	public void setIsmenu(String ismenu) {
		this.ismenu = ismenu;
	}

	public String getResid() {
		return resid;
	}

	public void setResid(String resid) {
		this.resid = resid;
	}


	public String getRespath() {
		return respath;
	}

	public void setRespath(String respath) {
		this.respath = respath;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}


}
