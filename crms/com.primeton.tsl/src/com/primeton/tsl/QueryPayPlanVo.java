package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
* @ClassName: QueryPayPlan 
* @Description: 查询还款计划 
* @author GIT-dengchao
* @date 2015-6-11 下午05:23:54 
*
 */
public class QueryPayPlanVo extends SuperBosfxRq implements Serializable{
	private static final long serialVersionUID = -6896917124251755876L;
	
	private String dueNum;//借据编号
	private String telNo;//通知书编号
	private String begDate;//发放日期
	private String endDate;//到期日期
	private String prmPayTyp;//主还款方式
	private String astPayTyp;//子还款方式
	private String itrCyl;//结息周期
	private String itrRateWay;//利率依据方式
	private String itrCalRule;//下次结息日计算规则
	private BigDecimal baseAmt;//放款金额
	private BigDecimal norItrRate;//正常利率
	private String specPaymentDate;//指定还款日
	private int iStgFirstMon;//阶段性首次还本期次
	private int betweenDays;//间隔天数
	private String currCode;//币种
	private int numBasePeri;//基准期次
	private int currPeri;//当前期次
	private List<TbSupPrinPlanNTmpVO> tbSupPrinPlanNList;//还款计划查询链表
	private List<MsgQueryPayPlan> msgQueryPayPlanList;//返还管理的LIst
	
	public QueryPayPlanVo(){
		this.setBaseVO(new BaseVO());
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

	public String getBegDate() {
		return begDate;
	}

	@XmlElement(name="BegDate")
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	public String getEndDate() {
		return endDate;
	}

	@XmlElement(name="EndDate")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPrmPayTyp() {
		return prmPayTyp;
	}

	@XmlElement(name="PrmPayTyp")
	public void setPrmPayTyp(String prmPayTyp) {
		this.prmPayTyp = prmPayTyp;
	}

	public String getAstPayTyp() {
		return astPayTyp;
	}

	@XmlElement(name="AstPayTyp")
	public void setAstPayTyp(String astPayTyp) {
		this.astPayTyp = astPayTyp;
	}

	public String getItrCyl() {
		return itrCyl;
	}

	@XmlElement(name="ItrCyl")
	public void setItrCyl(String itrCyl) {
		this.itrCyl = itrCyl;
	}

	public String getItrRateWay() {
		return itrRateWay;
	}

	@XmlElement(name="ItrRateWay")
	public void setItrRateWay(String itrRateWay) {
		this.itrRateWay = itrRateWay;
	}

	public String getItrCalRule() {
		return itrCalRule;
	}

	@XmlElement(name="ItrCalRule")
	public void setItrCalRule(String itrCalRule) {
		this.itrCalRule = itrCalRule;
	}

	public BigDecimal getBaseAmt() {
		return baseAmt;
	}

	@XmlElement(name="BaseAmt")
	public void setBaseAmt(BigDecimal baseAmt) {
		this.baseAmt = baseAmt;
	}

	public BigDecimal getNorItrRate() {
		return norItrRate;
	}

	@XmlElement(name="NorItrRate")
	public void setNorItrRate(BigDecimal norItrRate) {
		this.norItrRate = norItrRate;
	}

	public String getSpecPaymentDate() {
		return specPaymentDate;
	}

	@XmlElement(name="SpecPaymentDate")
	public void setSpecPaymentDate(String specPaymentDate) {
		this.specPaymentDate = specPaymentDate;
	}

	public int getIStgFirstMon() {
		return iStgFirstMon;
	}

	@XmlElement(name="IStgFirstMon")
	public void setIStgFirstMon(int iStgFirstMon) {
		this.iStgFirstMon = iStgFirstMon;
	}

	public int getBetweenDays() {
		return betweenDays;
	}

	@XmlElement(name="BetweenDays")
	public void setBetweenDays(int betweenDays) {
		this.betweenDays = betweenDays;
	}
	
	public int getNumBasePeri() {
		return numBasePeri;
	}

	@XmlElement(name="NumBasePeri")
	public void setNumBasePeri(int numBasePeri) {
		this.numBasePeri = numBasePeri;
	}

	public int getCurrPeri() {
		return currPeri;
	}

	@XmlElement(name="CurrPeri")
	public void setCurrPeri(int currPeri) {
		this.currPeri = currPeri;
	}
	
	public String getCurrCode() {
		return currCode;
	}

	@XmlElement(name="CurrCode")
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	
	public List<TbSupPrinPlanNTmpVO> getTbSupPrinPlanNList() {
		return tbSupPrinPlanNList;
	}

	@XmlElement(name="TbSupPrinPlanNList")
	public void setTbSupPrinPlanNList(List<TbSupPrinPlanNTmpVO> tbSupPrinPlanNList) {
		this.tbSupPrinPlanNList = tbSupPrinPlanNList;
	}

	public List<MsgQueryPayPlan> getMsgQueryPayPlanList() {
		return msgQueryPayPlanList;
	}

	@XmlElement(name="MsgQueryPayPlanList")
	public void setMsgQueryPayPlanList(List<MsgQueryPayPlan> msgQueryPayPlanList) {
		this.msgQueryPayPlanList = msgQueryPayPlanList;
	}

	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "QueryPayPlanVo [astPayTyp=" + astPayTyp + ", baseAmt="
				+ baseAmt + ", begDate=" + begDate + ", betweenDays="
				+ betweenDays + ", currCode=" + currCode + ", currPeri="
				+ currPeri + ", dueNum=" + dueNum + ", endDate=" + endDate
				+ ", iStgFirstMon=" + iStgFirstMon + ", itrCalRule="
				+ itrCalRule + ", itrCyl=" + itrCyl + ", itrRateWay="
				+ itrRateWay + ", msgQueryPayPlanList=" + msgQueryPayPlanList
				+ ", norItrRate=" + norItrRate + ", numBasePeri=" + numBasePeri
				+ ", prmPayTyp=" + prmPayTyp + ", specPaymentDate="
				+ specPaymentDate + ", tbSupPrinPlanNList="
				+ tbSupPrinPlanNList + ", telNo=" + telNo + "]";
	}

	

}
