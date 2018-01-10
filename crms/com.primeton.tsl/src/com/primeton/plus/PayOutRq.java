package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;

/**
 * 出账放款输入
 * @author CHENPAN
 *
 */
public class PayOutRq extends SuperBosfxRq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7516671815444825126L;
	private String telNo;//通知书编号
	private String dueNum;//借据编号
	private String brwName;//客户名称      
	private String conNo;//合同编号      
	private String prodCod;//业务品种      
	private String primAcct;//放款账号      
	private String primAcctName;//放款账户名称  
	private String payPrimAcct;//还款账号      
	private String payPrimName;//还款账户名称  
	private String currCod;//币种          
	private BigDecimal padUpAmt;//放款金额      
	private BigDecimal norItrRate;//正常利率      
	private BigDecimal delItrRate;//罚息利率      
	private String begDate;//放款日期      
	private String endDate;//到期日期      
	private String curPrmPayTyp;//主还款方式    
	private String curAstPayTyp;//子还款方式    
	private String caspan;//结息周期      
	private String payDate;//指定还款日    
	private String nextProvDate;//首次结息日    
	private Long stgFirstMon;//阶段性还本期数
	private String discType;//贴息方式      
	private String dueNumSun;//原借据编号    
	private String agyBusAccName;//原借款人名称  
	private BigDecimal rcvPrn;//原借据本金    
	private BigDecimal rcvNorItrIn;//原借据正常利息
	private BigDecimal rcvDftItrIn;//原借据拖欠利息
	private BigDecimal rcvPnsItrIn;//原借据罚息 
	private String accEntJson; //核心记账接口结构
	private String isDk; //是否垫款   1 是    0 否
	private String trustFlg;//受托支付标志
	public String getIsDk() {
		return isDk;
	}

	public void setIsDk(String isDk) {
		this.isDk = isDk;
	}

	public PayOutRq() {
	}
	
	public String getAccEntJson() {
		return accEntJson;
	}

	public void setAccEntJson(String accEntJson) {
		this.accEntJson = accEntJson;
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
	public String getBrwName() {
		return brwName;
	}
	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	public String getProdCod() {
		return prodCod;
	}
	public void setProdCod(String prodCod) {
		this.prodCod = prodCod;
	}
	public String getPrimAcct() {
		return primAcct;
	}
	public void setPrimAcct(String primAcct) {
		this.primAcct = primAcct;
	}
	public String getPrimAcctName() {
		return primAcctName;
	}
	public void setPrimAcctName(String primAcctName) {
		this.primAcctName = primAcctName;
	}
	public String getPayPrimAcct() {
		return payPrimAcct;
	}
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}
	public String getPayPrimName() {
		return payPrimName;
	}
	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
	}
	public String getCurrCod() {
		return currCod;
	}
	public void setCurrCod(String currCod) {
		this.currCod = currCod;
	}
	public BigDecimal getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(BigDecimal padUpAmt) {
		this.padUpAmt = padUpAmt;
	}
	public BigDecimal getNorItrRate() {
		return norItrRate;
	}
	public void setNorItrRate(BigDecimal norItrRate) {
		this.norItrRate = norItrRate;
	}
	public BigDecimal getDelItrRate() {
		return delItrRate;
	}
	public void setDelItrRate(BigDecimal delItrRate) {
		this.delItrRate = delItrRate;
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
	public String getNextProvDate() {
		return nextProvDate;
	}
	public void setNextProvDate(String nextProvDate) {
		this.nextProvDate = nextProvDate;
	}
	public Long getStgFirstMon() {
		return stgFirstMon;
	}
	public void setStgFirstMon(Long stgFirstMon) {
		this.stgFirstMon = stgFirstMon;
	}
	public String getDiscType() {
		return discType;
	}
	public void setDiscType(String discType) {
		this.discType = discType;
	}
	public String getDueNumSun() {
		return dueNumSun;
	}
	public void setDueNumSun(String dueNumSun) {
		this.dueNumSun = dueNumSun;
	}
	public String getAgyBusAccName() {
		return agyBusAccName;
	}
	public void setAgyBusAccName(String agyBusAccName) {
		this.agyBusAccName = agyBusAccName;
	}
	public BigDecimal getRcvPrn() {
		return rcvPrn;
	}
	public void setRcvPrn(BigDecimal rcvPrn) {
		this.rcvPrn = rcvPrn;
	}
	public BigDecimal getRcvNorItrIn() {
		return rcvNorItrIn;
	}
	public void setRcvNorItrIn(BigDecimal rcvNorItrIn) {
		this.rcvNorItrIn = rcvNorItrIn;
	}
	public BigDecimal getRcvDftItrIn() {
		return rcvDftItrIn;
	}
	public void setRcvDftItrIn(BigDecimal rcvDftItrIn) {
		this.rcvDftItrIn = rcvDftItrIn;
	}
	public BigDecimal getRcvPnsItrIn() {
		return rcvPnsItrIn;
	}
	public void setRcvPnsItrIn(BigDecimal rcvPnsItrIn) {
		this.rcvPnsItrIn = rcvPnsItrIn;
	}

	public String getTrustFlg() {
		return trustFlg;
	}

	public void setTrustFlg(String trustFlg) {
		this.trustFlg = trustFlg;
	}
	
}
