package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 编号校验接口---响应对象体
 * @author shendl
 *
 */
public class GJS01501030000008ResBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 910986538128241004L;
	private String knotBusiNo;//国结业务编号
	private String transStat;//交易状态
	private String errMsg;//错误消息
	private String loanCur;//放款币种
	private String loanAmt;//放款金额
	private String loanDay;//放款期限(天数)
	private String knotMsg;//国际业务部意见
	
	
	public String getKnotBusiNo() {
		return knotBusiNo;
	}
	public void setKnotBusiNo(String knotBusiNo) {
		this.knotBusiNo = knotBusiNo;
	}
	public String getTransStat() {
		return transStat;
	}
	public void setTransStat(String transStat) {
		this.transStat = transStat;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getLoanCur() {
		return loanCur;
	}
	public void setLoanCur(String loanCur) {
		this.loanCur = loanCur;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getLoanDay() {
		return loanDay;
	}
	public void setLoanDay(String loanDay) {
		this.loanDay = loanDay;
	}
	public String getKnotMsg() {
		return knotMsg;
	}
	public void setKnotMsg(String knotMsg) {
		this.knotMsg = knotMsg;
	}
	@Override
	public String toString() {
		return "GJS01501030000008ResBody [knotBusiNo=" + knotBusiNo
				+ ", transStat=" + transStat + ", errMsg=" + errMsg
				+ ", loanCur=" + loanCur + ", loanAmt=" + loanAmt
				+ ", loanDay=" + loanDay + ", knotMsg=" + knotMsg + "]";
	}
	
}
