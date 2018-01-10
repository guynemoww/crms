package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* @ClassName: PayTypVo 
* @Description: 还款方式变更
* @author GIT-ABC
* @date 2015-6-4 上午09:13:32 
*
 */
public class PayTypVo extends SuperBosfxRq implements Serializable{

	private BaseVO baseVO;
	private String dueNum;//借据编号
	private String chgPrmPayTyp;//变更后主还款方式
	private String chgAstPayTyp;//变更后子还款方式
	private String chgCaspan;//变更后计息周期
	private String chgSpecPaymentDate;//修改后指定还款日
	private String chgItrRateWay;//修改后利率依据方式
	private int chgStgFirstMon;//阶段性首次还本期数
	private int chgPrinPlanTerm;//还本总期数
	private String chgPrinPlanFlag;//下发新的还本计划标志
	private String telNo;//通知书编号
	
	public PayTypVo(){
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
	
	public String getChgPrmPayTyp() {
		return chgPrmPayTyp;
	}

	public void setChgPrmPayTyp(String chgPrmPayTyp) {
		this.chgPrmPayTyp = chgPrmPayTyp;
	}

	public String getChgAstPayTyp() {
		return chgAstPayTyp;
	}

	public void setChgAstPayTyp(String chgAstPayTyp) {
		this.chgAstPayTyp = chgAstPayTyp;
	}

	public String getChgCaspan() {
		return chgCaspan;
	}

	public void setChgCaspan(String chgCaspan) {
		this.chgCaspan = chgCaspan;
	}

	public String getChgSpecPaymentDate() {
		return chgSpecPaymentDate;
	}

	public void setChgSpecPaymentDate(String chgSpecPaymentDate) {
		this.chgSpecPaymentDate = chgSpecPaymentDate;
	}

	public String getChgItrRateWay() {
		return chgItrRateWay;
	}

	public void setChgItrRateWay(String chgItrRateWay) {
		this.chgItrRateWay = chgItrRateWay;
	}

	public int getChgStgFirstMon() {
		return chgStgFirstMon;
	}

	public void setChgStgFirstMon(int chgStgFirstMon) {
		this.chgStgFirstMon = chgStgFirstMon;
	}

	public int getChgPrinPlanTerm() {
		return chgPrinPlanTerm;
	}

	public void setChgPrinPlanTerm(int chgPrinPlanTerm) {
		this.chgPrinPlanTerm = chgPrinPlanTerm;
	}

	public String getChgPrinPlanFlag() {
		return chgPrinPlanFlag;
	}

	public void setChgPrinPlanFlag(String chgPrinPlanFlag) {
		this.chgPrinPlanFlag = chgPrinPlanFlag;
	}

	public String getTelNo() {
		return telNo;
	}

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
		return "PayTypVo [baseVO=" + baseVO + ", dueNum=" + dueNum
				+ ", chgPrmPayTyp=" + chgPrmPayTyp + ", chgCaspan=" + chgCaspan
				+ ", chgSpecPaymentDate=" + chgSpecPaymentDate + ", chgItrRateWay="
				+ chgItrRateWay + ", chgStgFirstMon=" + chgStgFirstMon + ",chgPrinPlanTerm="
				+ chgPrinPlanTerm + ", chgPrinPlanFlag=" + chgPrinPlanFlag + ", telNo=" + telNo+ "]";
	}
	
	
	
}
