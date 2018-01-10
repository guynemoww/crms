package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 国结调用信贷出账接口---响应对象体
 * 
 */
public class G001ResponseBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5932619964929154918L;
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
	private String cstNo;//客户编号
	private String telNo;//放款通知书编号
	private String discType;//贴息方式      
	private String dueNumSun;//原借据编号    
	private String agyBusAccName;//原借款人名称  
	private BigDecimal rcvPrn;//原借据本金    
	private BigDecimal rcvNorItrIn;//原借据正常利息
	private BigDecimal rcvDftItrIn;//原借据拖欠利息
	private BigDecimal rcvPnsItrIn;//原借据罚息   
	private String fieldRs1;//备用字段1
	private String fieldRs2;//备用字段2
	private String fieldRs3;//备用字段3
	private String fieldRs4;//备用字段4
	private String fieldRs5;//备用字段5
	
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
	public String getFieldRs1() {
		return fieldRs1;
	}
	public void setFieldRs1(String fieldRs1) {
		this.fieldRs1 = fieldRs1;
	}
	public String getFieldRs2() {
		return fieldRs2;
	}
	public void setFieldRs2(String fieldRs2) {
		this.fieldRs2 = fieldRs2;
	}
	public String getFieldRs3() {
		return fieldRs3;
	}
	public void setFieldRs3(String fieldRs3) {
		this.fieldRs3 = fieldRs3;
	}
	public String getFieldRs4() {
		return fieldRs4;
	}
	public void setFieldRs4(String fieldRs4) {
		this.fieldRs4 = fieldRs4;
	}
	public String getFieldRs5() {
		return fieldRs5;
	}
	public void setFieldRs5(String fieldRs5) {
		this.fieldRs5 = fieldRs5;
	}
	public String getCstNo() {
		return cstNo;
	}
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
}
