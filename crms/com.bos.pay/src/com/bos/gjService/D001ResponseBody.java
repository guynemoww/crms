package com.bos.gjService;

import java.math.BigDecimal;

/**
 * 
 * @author ww
 *
 */
public class D001ResponseBody {
	
	private BigDecimal rate;//利率
	private BigDecimal totalLimit;//总额度
	private BigDecimal usedLimit;//已用额度
	private String userId;//管户经理
	private String orgCode;//管户机构
	private BigDecimal aviLimit;//可用额度
	private String ecifPartyNum;//客户编号
	private String cusName;//客户名称
	private String cerNum;//证件号码
	private String cerType;//证件类型
	
	
	
	public D001ResponseBody() {
	}



	public BigDecimal getRate() {
		return rate;
	}



	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}



	public BigDecimal getTotalLimit() {
		return totalLimit;
	}



	public void setTotalLimit(BigDecimal totalLimit) {
		this.totalLimit = totalLimit;
	}



	public BigDecimal getUsedLimit() {
		return usedLimit;
	}



	public void setUsedLimit(BigDecimal usedLimit) {
		this.usedLimit = usedLimit;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getOrgCode() {
		return orgCode;
	}



	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}



	public BigDecimal getAviLimit() {
		return aviLimit;
	}



	public void setAviLimit(BigDecimal aviLimit) {
		this.aviLimit = aviLimit;
	}



	public String getEcifPartyNum() {
		return ecifPartyNum;
	}



	public void setEcifPartyNum(String ecifPartyNum) {
		this.ecifPartyNum = ecifPartyNum;
	}


	public String getCusName() {
		return cusName;
	}



	public void setCusName(String cusName) {
		this.cusName = cusName;
	}



	public String getCerNum() {
		return cerNum;
	}



	public void setCerNum(String cerNum) {
		this.cerNum = cerNum;
	}



	public String getCerType() {
		return cerType;
	}



	public void setCerType(String cerType) {
		this.cerType = cerType;
	}

	
}

















