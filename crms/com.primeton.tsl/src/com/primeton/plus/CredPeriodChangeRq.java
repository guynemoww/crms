package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 贷款期限变更输入
 * @author CHENPAN
 *
 */
public class CredPeriodChangeRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3848675630052666943L;
	private String dueNum;         //借据编号    
	private String endDate;        //贷款新到期日
	private String ballMthEndPerd; //气球贷止期  
	private String busCod;         //贷款新业务别
	private String discEndDate;    //新贴息止期  
	public CredPeriodChangeRq() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBallMthEndPerd() {
		return ballMthEndPerd;
	}
	public void setBallMthEndPerd(String ballMthEndPerd) {
		this.ballMthEndPerd = ballMthEndPerd;
	}
	public String getBusCod() {
		return busCod;
	}
	public void setBusCod(String busCod) {
		this.busCod = busCod;
	}
	public String getDiscEndDate() {
		return discEndDate;
	}
	public void setDiscEndDate(String discEndDate) {
		this.discEndDate = discEndDate;
	}
	

}
