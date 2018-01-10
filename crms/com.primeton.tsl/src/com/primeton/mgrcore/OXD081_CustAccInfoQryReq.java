package com.primeton.mgrcore;

import java.io.Serializable;

/**
 * 【XD08】客户账户信息查询(1232) Y：必输 N：非必输
 * 
 * @author MJF
 * 
 *         输入
 */
public class OXD081_CustAccInfoQryReq implements Serializable {
	private static final long serialVersionUID = -7990470221015280365L;

	private String custNo; // 客户账号 // Y
	private String dealMode; // 处理方式 // N
	private String orgNum;//核心记账机构--必输
	public OXD081_CustAccInfoQryReq() {
	}

	public String getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	@Override
	public String toString() {
		return "OXD081_CustAccInfoQryReq [custNo=" + custNo + ", dealMode="
				+ dealMode + "]";
	}

}
