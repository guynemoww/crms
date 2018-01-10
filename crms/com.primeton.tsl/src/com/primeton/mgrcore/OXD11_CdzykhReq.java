package com.primeton.mgrcore;

import java.io.Serializable;
/**
 * 核心质押扣划请求对象
 * @author lenovo
 *
 */

public class OXD11_CdzykhReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3813100675400683933L;
	private String frzNo;//冻结编号
	private String custAcctNo;//客户账户
	private String deductedAmt;//扣划金额
	private String trsfInAcctNo;//转入账号
	private String approvalDepart;//审批部门
	private String approver;//审批人
	private String summaryCode;//摘要代码
	private String summaryDesc;//摘要描述
	private String hxorg;//核心记账机构--调用此接口必传
	private String hxOrgNum;//经办机构--调用此接口必传
	private String flowNo;//核心交易流水---要与给到核算的交易流水保持一致  才能用来满足对账需求
	public String getFrzNo() {
		return frzNo;
	}
	public void setFrzNo(String frzNo) {
		this.frzNo = frzNo;
	}
	public String getCustAcctNo() {
		return custAcctNo;
	}
	public void setCustAcctNo(String custAcctNo) {
		this.custAcctNo = custAcctNo;
	}
	public String getDeductedAmt() {
		return deductedAmt;
	}
	public void setDeductedAmt(String deductedAmt) {
		this.deductedAmt = deductedAmt;
	}
	public String getTrsfInAcctNo() {
		return trsfInAcctNo;
	}
	public void setTrsfInAcctNo(String trsfInAcctNo) {
		this.trsfInAcctNo = trsfInAcctNo;
	}
	public String getApprovalDepart() {
		return approvalDepart;
	}
	public void setApprovalDepart(String approvalDepart) {
		this.approvalDepart = approvalDepart;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getSummaryCode() {
		return summaryCode;
	}
	public void setSummaryCode(String summaryCode) {
		this.summaryCode = summaryCode;
	}
	public String getSummaryDesc() {
		return summaryDesc;
	}
	public void setSummaryDesc(String summaryDesc) {
		this.summaryDesc = summaryDesc;
	}
	public String getHxorg() {
		return hxorg;
	}
	public void setHxorg(String hxorg) {
		this.hxorg = hxorg;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public String getHxOrgNum() {
		return hxOrgNum;
	}
	public void setHxOrgNum(String hxOrgNum) {
		this.hxOrgNum = hxOrgNum;
	}
	
}
