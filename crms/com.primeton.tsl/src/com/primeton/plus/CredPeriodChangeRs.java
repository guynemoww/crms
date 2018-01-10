package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 贷款期限变更输出
 * @author CHENPAN
 *
 */
public class CredPeriodChangeRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9053956540435890430L;
	private String dueNum;         //借据编号    
	private String endDate;        //贷款新到期日
	private String ballMthEndPerd; //气球贷止期  
	private String busCod;         //贷款新业务别
	private String discEndDate;    //新贴息止期  
	public CredPeriodChangeRs() {
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
