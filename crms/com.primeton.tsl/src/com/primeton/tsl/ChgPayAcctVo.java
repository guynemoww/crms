package com.primeton.tsl;

import java.io.Serializable;

/**
 * 
* @ClassName: ChgPayAcctVo 
* @Description: 变更还款账号
* @author GIT-ABC
* @date 2015-6-4 上午09:13:32 
*
 */
public class ChgPayAcctVo extends SuperBosfxRq implements Serializable{

	private BaseVO baseVO;
	private String dueNum;//借据编号
	private String telNo;//通知书编号
	private String payPrimAcct;//还款帐号
	private String payPrimName;//还款账户名称
	private String payOpenDep;//还款账号开户机构
	
	
	public ChgPayAcctVo(){
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


	/** 
	 * @return payPrimName 
	 */
	public String getPayPrimName() {
		return payPrimName;
	}


	/** 
	 * @param payPrimName 要设置的 payPrimName 
	 */
	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
	}


	/** 
	 * @return payOpenDep 
	 */
	public String getPayOpenDep() {
		return payOpenDep;
	}


	/** 
	 * @param payOpenDep 要设置的 payOpenDep 
	 */
	public void setPayOpenDep(String payOpenDep) {
		this.payOpenDep = payOpenDep;
	}


	/** 
	 * @return telNo 
	 */
	public String getTelNo() {
		return telNo;
	}


	/** 
	 * @param telNo 要设置的 telNo 
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "ChgPayAcctVo [baseVO=" + baseVO + ", dueNum=" + dueNum
				+ ", payOpenDep=" + payOpenDep + ", payPrimAcct=" + payPrimAcct
				+ ", payPrimName=" + payPrimName + ", telNo=" + telNo + "]";
	}

	
}
