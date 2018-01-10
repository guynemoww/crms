package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 还款方式变更输入
 * @author CHENPAN
 *
 */
public class RepayWayChangeRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8357274543888912824L;
	private String dueNum;           //借据编号            
	private String caspan;           //结息周期            
	private String payDate;          //指定还款日          
	private String curPrmPayTyp;     //新还款方式-主       
	private String curAstPayTyp;     //新还款方式-子       
	private String itrRateWay;       //利率依据方式        
	private Integer stgFirstMon;      //阶段性首次还本期数  --03，04还款方式必输
	private String ballMthEndPerd;   //气球贷止期  --0210还款方式必输
	private BigDecimal frePayMethPayAmt; //自由还款法月均还款额 --0220还款方式必输
	private Integer frePayMethDay;    //自由还款法失效天数  --0220还款方式必输
	private Integer calDays;          //自定义结息天数      --结息周期为自定义必输
	private String cusPayPlanType;   //客户指定还息计划标志
	private String prinPlanFlg;      //下发还本计划标志    
	private String payItrPlanFlg;    //下发还息计划标志
	public RepayWayChangeRq() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getCaspan() {
		return caspan;
	}
	public void setCaspan(String caspan) {
		this.caspan = caspan;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getCurPrmPayTyp() {
		return curPrmPayTyp;
	}
	public void setCurPrmPayTyp(String curPrmPayTyp) {
		this.curPrmPayTyp = curPrmPayTyp;
	}
	public String getCurAstPayTyp() {
		return curAstPayTyp;
	}
	public void setCurAstPayTyp(String curAstPayTyp) {
		this.curAstPayTyp = curAstPayTyp;
	}
	public String getItrRateWay() {
		return itrRateWay;
	}
	public void setItrRateWay(String itrRateWay) {
		this.itrRateWay = itrRateWay;
	}
	public Integer getStgFirstMon() {
		return stgFirstMon;
	}
	public void setStgFirstMon(Integer stgFirstMon) {
		this.stgFirstMon = stgFirstMon;
	}
	public String getBallMthEndPerd() {
		return ballMthEndPerd;
	}
	public void setBallMthEndPerd(String ballMthEndPerd) {
		this.ballMthEndPerd = ballMthEndPerd;
	}
	public Integer getFrePayMethDay() {
		return frePayMethDay;
	}
	public void setFrePayMethDay(Integer frePayMethDay) {
		this.frePayMethDay = frePayMethDay;
	}
	public Integer getCalDays() {
		return calDays;
	}
	public void setCalDays(Integer calDays) {
		this.calDays = calDays;
	}
	public String getCusPayPlanType() {
		return cusPayPlanType;
	}
	public void setCusPayPlanType(String cusPayPlanType) {
		this.cusPayPlanType = cusPayPlanType;
	}
	public String getPrinPlanFlg() {
		return prinPlanFlg;
	}
	public void setPrinPlanFlg(String prinPlanFlg) {
		this.prinPlanFlg = prinPlanFlg;
	}
	public String getPayItrPlanFlg() {
		return payItrPlanFlg;
	}
	public void setPayItrPlanFlg(String payItrPlanFlg) {
		this.payItrPlanFlg = payItrPlanFlg;
	}
	public BigDecimal getFrePayMethPayAmt() {
		return frePayMethPayAmt;
	}
	public void setFrePayMethPayAmt(BigDecimal frePayMethPayAmt) {
		this.frePayMethPayAmt = frePayMethPayAmt;
	}
	
	
}
