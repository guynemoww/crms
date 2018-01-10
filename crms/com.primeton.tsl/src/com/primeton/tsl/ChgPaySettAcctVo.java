package com.primeton.tsl;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
* @ClassName: ChgPayAcctVo 
* @Description: 变更还款账号
* @author GIT-ABC
* @date 2015-6-4 上午09:13:32 
*
 */
public class ChgPaySettAcctVo extends SuperBosfxRq implements Serializable{	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -2318092005904640523L;
	private BaseVO baseVO;
	private String dueNum;//借据编号
	private String telNo;//通知书编号
	private String prnSettAcct;//委托人帐号（本金）
	private String prnSettAcctName;//委托人名称（本金）
	private String prnSettAcctTyp;//委托人类型（本金）
	private String itrSettAcct;//委托人账号（利息）	
	private String itrSettAcctName;//委托人名称（利息）
	private String itrSettAcctTyp;//委托人类型（利息）	
	public ChgPaySettAcctVo(){
		this.baseVO = new BaseVO();
	}



	public BaseVO getBaseVO() {
		return baseVO;
	}



	public void setBaseVO(BaseVO baseVO) {
		this.baseVO = baseVO;
	}


	public String getDueNum() {
		return dueNum;
	}


	@XmlElement(name="DueNum")
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}



	public String getTelNo() {
		return telNo;
	}


	@XmlElement(name="TelNo")
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}



	public String getPrnSettAcct() {
		return prnSettAcct;
	}


	@XmlElement(name="PrnSettAcct")
	public void setPrnSettAcct(String prnSettAcct) {
		this.prnSettAcct = prnSettAcct;
	}



	public String getItrSettAcct() {
		return itrSettAcct;
	}


	@XmlElement(name="ItrSettAcct")
	public void setItrSettAcct(String itrSettAcct) {
		this.itrSettAcct = itrSettAcct;
	}



	

	public String getPrnSettAcctName() {
		return prnSettAcctName;
	}


	@XmlElement(name="PrnSettAcctName")
	public void setPrnSettAcctName(String prnSettAcctName) {
		this.prnSettAcctName = prnSettAcctName;
	}



	public String getItrSettAcctName() {
		return itrSettAcctName;
	}


	@XmlElement(name="ItrSettAcctName")
	public void setItrSettAcctName(String itrSettAcctName) {
		this.itrSettAcctName = itrSettAcctName;
	}



	public String getPrnSettAcctTyp() {
		return prnSettAcctTyp;
	}


	@XmlElement(name="PrnSettAcctTyp")
	public void setPrnSettAcctTyp(String prnSettAcctTyp) {
		this.prnSettAcctTyp = prnSettAcctTyp;
	}



	public String getItrSettAcctTyp() {
		return itrSettAcctTyp;
	}


	@XmlElement(name="ItrSettAcctTyp")
	public void setItrSettAcctTyp(String itrSettAcctTyp) {
		this.itrSettAcctTyp = itrSettAcctTyp;
	}



	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "ChgPaySettAcctVo [baseVO=" + baseVO + ", dueNum=" + dueNum
				+ ", itrSettAcct=" + itrSettAcct + ", prnSettAcct="
				+ prnSettAcct + ", telNo=" + telNo + "]";
	}





}
