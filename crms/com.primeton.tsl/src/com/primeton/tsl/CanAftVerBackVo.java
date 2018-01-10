package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A02;

/**
 * 
* @ClassName: CanAftVerBackVo 
* @Description: 核销收回
* @author GIT-git
* @date 2015-5-12 下午08:43:14 
*
 */
public class CanAftVerBackVo extends SuperBosfxRq implements Serializable{

	private EsbBodyHxRq01001000002A02 msgCore3181In;
	private String telNo;//核销收回通知书编号
	private String dueNum;//借据编号
	private String brwName;//客户名称
	private String begDate;//贷款起期      
	private String endDate;//贷款止期    
	private String rcvDate;//核销日期
	private BigDecimal oftRes;//已核销本金
	private BigDecimal oftItr;//已核销利息
	private BigDecimal oftSum;//核销金额合计
	private BigDecimal oftBackSum;//核销收回金额
	private String payPrimName;//还款账户名称
	private String payPrimAcct;//还款账号
	
	public CanAftVerBackVo(){
		setBaseVO(new BaseVO());
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


	public String getTelNo() {
		return telNo;
	}
	
	@XmlElement(name = "TelNo")
	public void setTelNo(String telNo) {
		this.telNo = telNo;
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
	 * @return rcvDate 
	 */
	public String getRcvDate() {
		return rcvDate;
	}

	/** 
	 * @param rcvDate 要设置的 rcvDate 
	 */
	@XmlElement(name = "RcvDate")
	public void setRcvDate(String rcvDate) {
		this.rcvDate = rcvDate;
	}

	/** 
	 * @return oftRes 
	 */
	public BigDecimal getOftRes() {
		return oftRes;
	}

	/** 
	 * @param oftRes 要设置的 oftRes 
	 */
	@XmlElement(name = "OftRes")
	public void setOftRes(BigDecimal oftRes) {
		this.oftRes = oftRes;
	}

	/** 
	 * @return oftItr 
	 */
	public BigDecimal getOftItr() {
		return oftItr;
	}

	/** 
	 * @param oftItr 要设置的 oftItr 
	 */
	@XmlElement(name = "OftItr")
	public void setOftItr(BigDecimal oftItr) {
		this.oftItr = oftItr;
	}

	/** 
	 * @return oftSum 
	 */
	public BigDecimal getOftSum() {
		return oftSum;
	}

	/** 
	 * @param oftSum 要设置的 oftSum 
	 */
	@XmlElement(name = "OftSum")
	public void setOftSum(BigDecimal oftSum) {
		this.oftSum = oftSum;
	}

	/** 
	 * @return oftBackSum 
	 */
	public BigDecimal getOftBackSum() {
		return oftBackSum;
	}

	/** 
	 * @param oftBackSum 要设置的 oftBackSum 
	 */
	@XmlElement(name = "OftBackSum")
	public void setOftBackSum(BigDecimal oftBackSum) {
		this.oftBackSum = oftBackSum;
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
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}

	@Override
	public String toString() {
		return "CanAftVerBackVo [baseVO=" + getBaseVO() + ", begDate=" + begDate
				+ ", brwName=" + brwName + ", dueNum=" + dueNum + ", endDate="
				+ endDate + ", oftBackSum=" + oftBackSum + ", oftItr=" + oftItr
				+ ", oftRes=" + oftRes + ", oftSum=" + oftSum
				+ ", payPrimAcct=" + payPrimAcct + ", payPrimName="
				+ payPrimName + ", rcvDate=" + rcvDate + ", telNo=" + telNo
				+ ", msgCore3181In=" + msgCore3181In + "]";
	}
	
}
