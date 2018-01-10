package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A02;

/**
 * 
* @ClassName: RefundVo 
* @Description: 还款信息返显
* @author LSY
* @date 2015-5-12 下午03:54:53 
*
 */
public class RetPaymentInfoVo extends SuperBosfxRq implements Serializable{
	private static final long serialVersionUID = 632604114127842981L;
	
	private EsbBodyHxRq01001000002A02 msgCore3181In;
	private String payPrimName = "";//还款账户名称
	private String payPrimAcct ="";//还款账号
	private BigDecimal payPrimAmt = new BigDecimal("0.00");//还款金额
	//private BigDecimal capital = new BigDecimal("0.00");//本金
	private BigDecimal interest = new BigDecimal("0.00");//正常利息
	private BigDecimal dftItr = new BigDecimal("0.00");//拖欠利息
	private BigDecimal pnsItr = new BigDecimal("0.00");//罚息
	private BigDecimal penalty = new BigDecimal("0.00");//违约金
	private String dueNum = "";//借据编号
	private String payPrimTelNo = "";//还款通知书编号
	private String conNo = "";//合同编号
	private String begDate = "";//贷款起期      
	private String endDate = "";//贷款止期   
	private String brwName = "";//客户名称
	private String sts = "";//贷款状态
	private BigDecimal allPrnItrAmt = new BigDecimal("0.00");//结清合计金额
	private BigDecimal dftPrn = new BigDecimal("0.00");//拖欠期本金
	private BigDecimal curPrn = new BigDecimal("0.00");//当前期本金
	private BigDecimal aheadPrn = new BigDecimal("0.00");//提前还本金额
	
	public RetPaymentInfoVo(){
		this.setBaseVO(new BaseVO());
		this.msgCore3181In = new EsbBodyHxRq01001000002A02();
	}

	/** 
	 * @return tranMsgVO 
	 */
	public EsbBodyHxRq01001000002A02 getMsgCore3181In() {
		return msgCore3181In;
	}


	/** 
	 * @param tranMsgVO 要设置的 tranMsgVO 
	 */
	@XmlElement(name = "EsbBodyHxRq01001000002A02")
	public void setMsgCore3181In(EsbBodyHxRq01001000002A02 msgCore3181In) {
		this.msgCore3181In = msgCore3181In;
	}


	/** 
	 * @return payPrimName 
	 */
	public String getPayPrimName() {
		return payPrimName;
	}


	/** 
	 * @param payPrimName 要设置的 payPrimName 
	 */
	@XmlElement(name = "PayPrimName")
	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
	}


	/** 
	 * @return payPrimAcct 
	 */
	public String getPayPrimAcct() {
		return payPrimAcct;
	}


	/** 
	 * @param payPrimAcct 要设置的 payPrimAcct 
	 */
	@XmlElement(name = "PayPrimAcct")
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}


	/** 
	 * @return payPrimAmt 
	 */
	public BigDecimal getPayPrimAmt() {
		return payPrimAmt;
	}


	/** 
	 * @param payPrimAmt 要设置的 payPrimAmt 
	 */
	@XmlElement(name = "PayPrimAmt")
	public void setPayPrimAmt(BigDecimal payPrimAmt) {
		this.payPrimAmt = payPrimAmt;
	}


	/** 
	 * @return capital 
	 */
	/*public BigDecimal getCapital() {
		return capital;
	}*/


	/** 
	 * @param capital 要设置的 capital 
	 */
	/*@XmlElement(name = "Capital")
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}*/


	/** 
	 * @return interest 
	 */
	public BigDecimal getInterest() {
		return interest;
	}


	/** 
	 * @param interest 要设置的 interest 
	 */
	@XmlElement(name = "Interest")
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	/** 
	 * @return dftItr 
	 */
	public BigDecimal getDftItr() {
		return dftItr;
	}

	/** 
	 * @param dftItr 要设置的 dftItr 
	 */
	@XmlElement(name = "DftItr")
	public void setDftItr(BigDecimal dftItr) {
		this.dftItr = dftItr;
	}
	
	/** 
	 * @return pnsItr 
	 */
	public BigDecimal getPnsItr() {
		return pnsItr;
	}


	/** 
	 * @param pnsItr 要设置的 pnsItr 
	 */
	@XmlElement(name = "PnsItr")
	public void setPnsItr(BigDecimal pnsItr) {
		this.pnsItr = pnsItr;
	}


	/** 
	 * @return penalty 
	 */
	public BigDecimal getPenalty() {
		return penalty;
	}


	/** 
	 * @param penalty 要设置的 penalty 
	 */
	@XmlElement(name = "Penalty")
	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}


	/** 
	 * @return dueNum 
	 */
	public String getDueNum() {
		return dueNum;
	}


	/** 
	 * @param dueNum 要设置的 dueNum 
	 */
	@XmlElement(name = "DueNum")
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}


	/** 
	 * @return payPrimTelNo 
	 */
	public String getPayPrimTelNo() {
		return payPrimTelNo;
	}


	/** 
	 * @param payPrimTelNo 要设置的 payPrimTelNo 
	 */
	@XmlElement(name = "PayPrimTelNo")
	public void setPayPrimTelNo(String payPrimTelNo) {
		this.payPrimTelNo = payPrimTelNo;
	}


	/** 
	 * @return conNo 
	 */
	public String getConNo() {
		return conNo;
	}


	/** 
	 * @param conNo 要设置的 conNo 
	 */
	@XmlElement(name = "ConNo")
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}


	/** 
	 * @return begDate 
	 */
	public String getBegDate() {
		return begDate;
	}


	/** 
	 * @param begDate 要设置的 begDate 
	 */
	@XmlElement(name = "BegDate")
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}


	/** 
	 * @return endDate 
	 */
	public String getEndDate() {
		return endDate;
	}


	/** 
	 * @param endDate 要设置的 endDate 
	 */
	@XmlElement(name = "EndDate")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	/** 
	 * @return brwName 
	 */
	public String getBrwName() {
		return brwName;
	}


	/** 
	 * @param brwName 要设置的 brwName 
	 */
	@XmlElement(name = "BrwName")
	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}


	/** 
	 * @return sts 
	 */
	public String getSts() {
		return sts;
	}


	/** 
	 * @param sts 要设置的 sts 
	 */
	@XmlElement(name = "Sts")
	public void setSts(String sts) {
		this.sts = sts;
	}

	public BigDecimal getAllPrnItrAmt() {
		return allPrnItrAmt;
	}
	
	@XmlElement(name = "AllPrnItrAmt")
	public void setAllPrnItrAmt(BigDecimal allPrnItrAmt) {
		this.allPrnItrAmt = allPrnItrAmt;
	}
	
	public BigDecimal getDftPrn() {
		return dftPrn;
	}
	
	@XmlElement(name = "DftPrn")
	public void setDftPrn(BigDecimal dftPrn) {
		this.dftPrn = dftPrn;
	}

	public BigDecimal getCurPrn() {
		return curPrn;
	}
	
	@XmlElement(name = "CurPrn")
	public void setCurPrn(BigDecimal curPrn) {
		this.curPrn = curPrn;
	}

	public BigDecimal getAheadPrn() {
		return aheadPrn;
	}
	
	@XmlElement(name = "AheadPrn")
	public void setAheadPrn(BigDecimal aheadPrn) {
		this.aheadPrn = aheadPrn;
	}

	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "RefundVo [baseVO=" + this.getBaseVO() + ", begDate=" + begDate
				+ ", brwName=" + brwName + ",conNo="
				+ conNo + ", dueNum=" + dueNum + ", endDate=" + endDate
				+ ", interest=" + interest + ", payPrimAcct=" + payPrimAcct
				+ ", payPrimAmt=" + payPrimAmt + ", payPrimName=" + payPrimName
				+ ", payPrimTelNo=" + payPrimTelNo + ", penalty=" + penalty
				+ ", pnsItr=" + pnsItr + ", sts=" + sts +",dftPrn="+dftPrn+",curPrn="+curPrn+",aheadPrn="+aheadPrn+",msgCore3181In="
				+ msgCore3181In + "]";
	}


}
