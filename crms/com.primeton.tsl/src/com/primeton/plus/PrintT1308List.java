package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 贴息明细
 * 
 * @author MJF
 * 
 */
public class PrintT1308List implements Serializable {
	private static final long serialVersionUID = -6979113824227747862L;
	private String trnDep;// 放款机构
	private String dueNum;// 借据编号
	private BigDecimal amt;// 放款金额
	private String begDate;// 贷款起期
	private String endDate;// 贷款止期
	private String clsFlg;// 五级分类
	private String discProcType;// 贴息类型
	private String discType;// 贴息方式
	private String protNum;// 协议号
	private String discAccTyp;// 贴息账号类型
	private String discAccOpnDep;// 贴息主体账号开户机构
	private String discAcc;// 贴息账号
	private String discAccNm;// 贴息账户名称
	private BigDecimal discRate;// 贴息率
	private BigDecimal totAmtItr;// 正常利息
	private BigDecimal brwItrPay;// 借款人应付利息
	private BigDecimal rcvDiscAmt;// 应还贴息

	public PrintT1308List() {

	}

	public String getTrnDep() {
		return trnDep;
	}

	public void setTrnDep(String trnDep) {
		this.trnDep = trnDep;
	}

	public String getDueNum() {
		return dueNum;
	}

	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getBegDate() {
		return begDate;
	}

	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getClsFlg() {
		return clsFlg;
	}

	public void setClsFlg(String clsFlg) {
		this.clsFlg = clsFlg;
	}

	public String getDiscProcType() {
		return discProcType;
	}

	public void setDiscProcType(String discProcType) {
		this.discProcType = discProcType;
	}

	public String getDiscType() {
		return discType;
	}

	public void setDiscType(String discType) {
		this.discType = discType;
	}

	public String getProtNum() {
		return protNum;
	}

	public void setProtNum(String protNum) {
		this.protNum = protNum;
	}

	public String getDiscAccTyp() {
		return discAccTyp;
	}

	public void setDiscAccTyp(String discAccTyp) {
		this.discAccTyp = discAccTyp;
	}

	public String getDiscAccOpnDep() {
		return discAccOpnDep;
	}

	public void setDiscAccOpnDep(String discAccOpnDep) {
		this.discAccOpnDep = discAccOpnDep;
	}

	public String getDiscAcc() {
		return discAcc;
	}

	public void setDiscAcc(String discAcc) {
		this.discAcc = discAcc;
	}

	public String getDiscAccNm() {
		return discAccNm;
	}

	public void setDiscAccNm(String discAccNm) {
		this.discAccNm = discAccNm;
	}

	public BigDecimal getDiscRate() {
		return discRate;
	}

	public void setDiscRate(BigDecimal discRate) {
		this.discRate = discRate;
	}

	public BigDecimal getTotAmtItr() {
		return totAmtItr;
	}

	public void setTotAmtItr(BigDecimal totAmtItr) {
		this.totAmtItr = totAmtItr;
	}

	public BigDecimal getBrwItrPay() {
		return brwItrPay;
	}

	public void setBrwItrPay(BigDecimal brwItrPay) {
		this.brwItrPay = brwItrPay;
	}

	public BigDecimal getRcvDiscAmt() {
		return rcvDiscAmt;
	}

	public void setRcvDiscAmt(BigDecimal rcvDiscAmt) {
		this.rcvDiscAmt = rcvDiscAmt;
	}
}
