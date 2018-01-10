package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* @ClassName: AdjItrVo 
* @Description: 调整利息
* @author GIT-git
* @date 2015-5-12 下午05:33:26 
*
 */
public class AdjItrVo  extends SuperBosfxRq implements Serializable{

	private String telNo;//调整贷款利息通知书编号
	private String dueNum;//借据编号
	private String brwName;//客户名称
	private BigDecimal norItr;//正常利息
	private BigDecimal dftItr;//拖欠利息
	private BigDecimal pnsItr;//罚息
	private String adjType;//调整类型
	
	
	public AdjItrVo(){
		setBaseVO(new BaseVO());
	}



	/** 
	 * @return adjItrTelNo 
	 */
	public String getTelNo() {
		return telNo;
	}




	/** 
	 * @param adjItrTelNo 要设置的 adjItrTelNo 
	 */
	public void setTelNo(String adjItrTelNo) {
		this.telNo = adjItrTelNo;
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
	 * @return norItr 
	 */
	public BigDecimal getNorItr() {
		return norItr;
	}




	/** 
	 * @param norItr 要设置的 norItr 
	 */
	public void setNorItr(BigDecimal norItr) {
		this.norItr = norItr;
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
	public void setPnsItr(BigDecimal pnsItr) {
		this.pnsItr = pnsItr;
	}




	/** 
	 * @return adjType 
	 */
	public String getAdjType() {
		return adjType;
	}




	/** 
	 * @param adjType 要设置的 adjType 
	 */
	public void setAdjType(String adjType) {
		this.adjType = adjType;
	}

	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "AdjItrVo [TelNo=" + telNo + ", adjType=" + adjType
				 + ", brwName=" + brwName + ", dftItr="
				+ dftItr + ", dueNum=" + dueNum + ", norItr=" + norItr
				+ ", pnsItr=" + pnsItr + "]";
	}

	
}
