package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 还息计划变更控制信息输出
 * @author CHENPAN
 *
 */
public class DuePlanChangeRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7401495567943916293L;
	private String telNo;       //通知书编号      
	private String dueNum;      //借据编号        
	private String prinPlanFlg; //下发还息计划标志
	public DuePlanChangeRs() {
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getPrinPlanFlg() {
		return prinPlanFlg;
	}
	public void setPrinPlanFlg(String prinPlanFlg) {
		this.prinPlanFlg = prinPlanFlg;
	}
	
}
