package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* @ClassName: RcvItrVo 
* @Description: 终止停息
* @author GIT-git
* @date 2015-5-12 下午08:44:22 
*
 */
public class RcvItrVo extends SuperBosfxRq implements Serializable{

	private BaseVO baseVO;
	private String brwName;//客户名称
	private BigDecimal resCapital;//贷款剩余本金
	private BigDecimal ceasNorItr;//停息间利息
	private BigDecimal ceasDftItr;//停息间拖欠利息
	private BigDecimal ceasOtdNorItr;//停息间未结计利息
	private BigDecimal ceasPns;//停息前罚息
	private String ceasDate;//停息日
	private String padUpItrFlg;//补计利息标识
	private BigDecimal padUpItr;//补计利息
	private BigDecimal padUpDel;//补计罚息
	private BigDecimal padUpItrSum;//补计利息合计
	private String telNo;//终止停息通知书编号
	private String dueNum;//借据编号
	private String conNo;//合同编号
	private String begDate;//贷款起期      
	private String endDate;//贷款止期    
	
	
	public RcvItrVo(){
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
	 * @return resCapital 
	 */
	public BigDecimal getResCapital() {
		return resCapital;
	}


	/** 
	 * @param resCapital 要设置的 resCapital 
	 */
	public void setResCapital(BigDecimal resCapital) {
		this.resCapital = resCapital;
	}

	
	public BigDecimal getCeasNorItr() {
		return ceasNorItr;
	}


	public void setCeasNorItr(BigDecimal ceasNorItr) {
		this.ceasNorItr = ceasNorItr;
	}


	public BigDecimal getCeasDftItr() {
		return ceasDftItr;
	}


	public void setCeasDftItr(BigDecimal ceasDftItr) {
		this.ceasDftItr = ceasDftItr;
	}
	

	public BigDecimal getCeasOtdNorItr() {
		return ceasOtdNorItr;
	}


	public void setCeasOtdNorItr(BigDecimal ceasOtdNorItr) {
		this.ceasOtdNorItr = ceasOtdNorItr;
	}


	public BigDecimal getCeasPns() {
		return ceasPns;
	}


	public void setCeasPns(BigDecimal ceasPns) {
		this.ceasPns = ceasPns;
	}


	/** 
	 * @return ceasDate 
	 */
	public String getCeasDate() {
		return ceasDate;
	}


	/** 
	 * @param ceasDate 要设置的 ceasDate 
	 */
	public void setCeasDate(String ceasDate) {
		this.ceasDate = ceasDate;
	}


	/** 
	 * @return padUpItrFlg 
	 */
	public String getPadUpItrFlg() {
		return padUpItrFlg;
	}


	/** 
	 * @param padUpItrFlg 要设置的 padUpItrFlg 
	 */
	public void setPadUpItrFlg(String padUpItrFlg) {
		this.padUpItrFlg = padUpItrFlg;
	}


	/** 
	 * @return padUpItr 
	 */
	public BigDecimal getPadUpItr() {
		return padUpItr;
	}


	/** 
	 * @param padUpItr 要设置的 padUpItr 
	 */
	public void setPadUpItr(BigDecimal padUpItr) {
		this.padUpItr = padUpItr;
	}


	/** 
	 * @return padUpDel 
	 */
	public BigDecimal getPadUpDel() {
		return padUpDel;
	}


	/** 
	 * @param padUpDel 要设置的 padUpDel 
	 */
	public void setPadUpDel(BigDecimal padUpDel) {
		this.padUpDel = padUpDel;
	}


	/** 
	 * @return padUpItrSum 
	 */
	public BigDecimal getPadUpItrSum() {
		return padUpItrSum;
	}


	/** 
	 * @param padUpItrSum 要设置的 padUpItrSum 
	 */
	public void setPadUpItrSum(BigDecimal padUpItrSum) {
		this.padUpItrSum = padUpItrSum;
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
		return "RcvItrVo [baseVO=" + baseVO + ", begDate=" + begDate
				+ ", brwName=" + brwName + ", ceasDate=" + ceasDate
				+ ", ceasNorItr=" + ceasNorItr+",ceasDftItr="+ceasDftItr+",ceasOtdNorItr="+ceasOtdNorItr + ", ceasPns=" + ceasPns + ", conNo="
				+ conNo + ", dueNum=" + dueNum + ", endDate=" + endDate
				+ ", padUpDel=" + padUpDel + ", padUpItr=" + padUpItr
				+ ", padUpItrFlg=" + padUpItrFlg + ", padUpItrSum="
				+ padUpItrSum + ", resCapital=" + resCapital + ", telNo="
				+ telNo + "]";
	}
	
}
