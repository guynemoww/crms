package com.bos.inter.CallBmsInterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 额度查询 响应报文体
 * @author shendl
 */
public class CRMSCrdQueryRsBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6127534986782726465L;
	public String custNo;//客户编号
	public String custType;//客户类型
	public String custName;//客户姓名
	public BigDecimal crdTotalAmount;//授信总金额
	public BigDecimal crdUsedAmount;//授信已用金额
	public BigDecimal crdAviAmount;//授信可用金额
	public Date beginDate;//授信起始日
	public Date endDate;//授信到期日
	public String productNo;//产品编号
	public String productName;//产品名称
	public BigDecimal crdDetailAmount;//分项金额
	public BigDecimal crdDetailAviAmount;//分项可用金额
	public BigDecimal crdDetailUsedAmount;//分项已用金额
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public BigDecimal getCrdTotalAmount() {
		return crdTotalAmount;
	}
	public void setCrdTotalAmount(BigDecimal crdTotalAmount) {
		this.crdTotalAmount = crdTotalAmount;
	}
	public BigDecimal getCrdUsedAmount() {
		return crdUsedAmount;
	}
	public void setCrdUsedAmount(BigDecimal crdUsedAmount) {
		this.crdUsedAmount = crdUsedAmount;
	}
	public BigDecimal getCrdAviAmount() {
		return crdAviAmount;
	}
	public void setCrdAviAmount(BigDecimal crdAviAmount) {
		this.crdAviAmount = crdAviAmount;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getCrdDetailAmount() {
		return crdDetailAmount;
	}
	public void setCrdDetailAmount(BigDecimal crdDetailAmount) {
		this.crdDetailAmount = crdDetailAmount;
	}
	public BigDecimal getCrdDetailAviAmount() {
		return crdDetailAviAmount;
	}
	public void setCrdDetailAviAmount(BigDecimal crdDetailAviAmount) {
		this.crdDetailAviAmount = crdDetailAviAmount;
	}
	public BigDecimal getCrdDetailUsedAmount() {
		return crdDetailUsedAmount;
	}
	public void setCrdDetailUsedAmount(BigDecimal crdDetailUsedAmount) {
		this.crdDetailUsedAmount = crdDetailUsedAmount;
	}
}
