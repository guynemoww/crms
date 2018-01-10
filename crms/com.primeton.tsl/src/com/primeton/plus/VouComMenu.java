package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 凭证补打清单
 * @author CHENPAN
 *
 */
public class VouComMenu  extends SuperBosfxRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3326099004534640900L;
	private String dueNum;   //借据编号
	private String begDate;  //起始日期
	private String endDate;  //终止日期
	private String prnTyp;   //打印类型--00：放款 01：还款 02：核销收回 03：结清证明 04：结息 05：核销  06=调整贷款利息 07：停息  08：终止停息   09：抵债资产冲销贷款 1A:日终扣款

	public VouComMenu() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
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
	public String getPrnTyp() {
		return prnTyp;
	}
	public void setPrnTyp(String prnTyp) {
		this.prnTyp = prnTyp;
	}

}
