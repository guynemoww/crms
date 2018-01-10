package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* @ClassName: CeaseVo 
* @Description: 停息
* @author LSY
* @date 2015-5-12 下午04:18:45 
*
 */
public class CeaseVo extends SuperBosfxRq implements Serializable{

	private BaseVO baseVO;
	private String brwName;//客户名称
	private BigDecimal capital;//本金
	private BigDecimal interest;//利息
	private BigDecimal pnsItr;//罚息
	private String telNo;//停息通知书编号
	private String dueNum;//借据编号
	private String conNo;//合同编号
	private String begDate;//贷款起期      
	private String endDate;//贷款止期   
	
	
	public CeaseVo(){
		this.baseVO = new BaseVO();
	}


	/** 
	 * @return baseVO 
	 */
	public BaseVO getBaseVO() {
		return baseVO;
	}


	/** 
	 * @param baseVO 要设置的 baseVO 
	 */
	public void setBaseVO(BaseVO baseVO) {
		this.baseVO = baseVO;
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
	 * @return conNo 
	 */
	public String getConNo() {
		return conNo;
	}


	/** 
	 * @param conNo 要设置的 conNo 
	 */
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


	@Override
	public String toString() {
		return "CeaseVo [baseVO=" + baseVO + ", begDate=" + begDate
				+ ", brwName=" + brwName + ", capital=" + capital + ", conNo="
				+ conNo + ", dueNum=" + dueNum + ", endDate=" + endDate
				+ ", interest=" + interest + ", pnsItr=" + pnsItr + ", telNo="
				+ telNo + "]";
	}

}
