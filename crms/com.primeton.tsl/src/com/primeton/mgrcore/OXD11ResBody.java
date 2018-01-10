package com.primeton.mgrcore;

import java.io.Serializable;
/**
 * 核心质押扣划---响应对象
 * @author lenovo
 *
 */
public class OXD11ResBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9157217864231071529L;
	
	private String frzNo;//冻结编号
	private String custAcctNo;//客户账户
	private String deductedAmt;//扣划金额
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
	
	
}
