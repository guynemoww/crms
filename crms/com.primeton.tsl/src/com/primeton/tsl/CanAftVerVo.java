package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* @ClassName: CanAftVerVo 
* @Description: 核销
* @author GIT-git
* @date 2015-5-12 下午08:44:12 
*
 */
public class CanAftVerVo extends SuperBosfxRq implements Serializable{

	private String telNo;//核销通知书编号
	private String dueNum;//借据编号
	private String brwName;//客户名称
	private String begDate;//贷款起期      
	private String endDate;//贷款止期    
	private BigDecimal capital;//本金
	private BigDecimal interest;//利息
	private BigDecimal pnsItr;//罚息
	private BigDecimal oftSum;//核销金额合计
	
	public CanAftVerVo(){
		this.setBaseVO(new BaseVO());
	}

	public String getTelNo() {
		return telNo;
	}

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
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/** 
	 * @return capital 
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/** 
	 * @param capital 要设置的 capital 
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	/** 
	 * @return interest 
	 */
	public BigDecimal getInterest() {
		return interest;
	}

	/** 
	 * @param interest 要设置的 interest 
	 */
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
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
	public void setPnsItr(BigDecimal pnsItr) {
		this.pnsItr = pnsItr;
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
	public void setOftSum(BigDecimal oftSum) {
		this.oftSum = oftSum;
	}

	@Override
	public String toString() {
		return "CanAftVerVo [begDate=" + begDate
				+ ", brwName=" + brwName + ", capital=" + capital + ", dueNum="
				+ dueNum + ", endDate=" + endDate + ", interest=" + interest
				+ ", oftSum=" + oftSum + ", pnsItr=" + pnsItr + ", telNo="
				+ telNo + "]";
	}
	
}
