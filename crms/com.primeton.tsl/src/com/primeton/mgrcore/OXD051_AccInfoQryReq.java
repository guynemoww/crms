package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * 【XD05】账户信息查询(1224) Y：必输 N：非必输
 * 
 * @author MJF
 * 
 *         输入
 */
public class OXD051_AccInfoQryReq implements Serializable {
	private static final long serialVersionUID = 3407930803871448938L;

	private String qryType; // 查询类型 // Y // 0-客户 1-非客户
	private String custAcctNo; // 客户账号 // Y
	private String subAcctSeri; // 子账户序号 // N
	private String groupAcctSeri; // 组合账户序号 // N
	/**
	 * 01-人民币 12-英镑 13-港币 14-美元 15-瑞士法郎 27-日元 28-加拿大元 29-澳大利亚元 32-新加坡元 38-欧元
	 * 81-澳门元
	 **/
	private String currCode; // 货币代号 // Y
	private String cashFlag; // 钞汇标志 // Y // 0-钞户1-汇户
	private String qryPwd; // 查询密码 // Y // 查询密码由信贷管理系统加密过后，经ESB系统转加密成核心系统密码密文
	private String orgNum;//核心记账机构--必输
	public OXD051_AccInfoQryReq() {
	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	public String getQryType() {
		return qryType;
	}

	public void setQryType(String qryType) {
		this.qryType = qryType;
	}

	public String getCustAcctNo() {
		return custAcctNo;
	}

	public void setCustAcctNo(String custAcctNo) {
		this.custAcctNo = custAcctNo;
	}

	public String getSubAcctSeri() {
		return subAcctSeri;
	}

	public void setSubAcctSeri(String subAcctSeri) {
		this.subAcctSeri = subAcctSeri;
	}

	public String getGroupAcctSeri() {
		return groupAcctSeri;
	}

	public void setGroupAcctSeri(String groupAcctSeri) {
		this.groupAcctSeri = groupAcctSeri;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getCashFlag() {
		return cashFlag;
	}

	public void setCashFlag(String cashFlag) {
		this.cashFlag = cashFlag;
	}

	public String getQryPwd() {
		return qryPwd;
	}

	public void setQryPwd(String qryPwd) {
		this.qryPwd = qryPwd;
	}

}
