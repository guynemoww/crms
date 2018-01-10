package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;

/**
 * 借新还旧
 * @author CHENPAN
 *
 */
public class RepayOldNewRq  extends SuperBosfxRq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7513615743985506783L;
	private String telNo;             //通知书编号
	private String dueNum;            //借据编号                  
	private String brwName;           //客户名称                  
	private String prodCod;           //业务品种                  
	private String primAcct;          //放款账号                  
	private String primAcctName;      //放款账户名称              
	private String payPrimAcct;       //还款账号                  
	private String payPrimName;       //还款账户名称              
	private String currCod;           //币种                      
	private BigDecimal padUpAmt;          //放款金额                  
	private BigDecimal norItrRate;        //正常利率                  
	private BigDecimal delItrRate;        //罚息利率                  
	private String begDate;           //放款日期                  
	private String endDate;           //到期日期                  
	private String curPrmPayTyp;      //主还款方式                
	private String curAstPayTyp;      //子还款方式                
	private String caspan;            //结息周期                  
	private String payDate;           //指定还款日                
	private String nextProvDate;      //首次结息日                
	private Long stgFirstMon;     //阶段性还本期数            
	private String discType;          //贴息方式                  
	private String conNo;             //客户编号                  
	private String dueNumSun;         //原借据编号                
	private String payOpenDep;        //原借款人名称              
	private String PayPrimAcctTyp;    //原借款人账号              
	private String payOrder;          //还款顺序                  
	private BigDecimal rcvPrn;            //原借据本金                
	private BigDecimal rcvNorItrIn;       //原借据正常利息            
	private BigDecimal rcvDftItrIn;       //原借据拖欠利息            
	private BigDecimal rcvPnsItrIn;       //原借据罚息                
	private BigDecimal rcvCpdItrIn;       //原借据应收表内复利金额    
	private BigDecimal padUpPrn;          //原借据实收本金金额        
	private BigDecimal padUpNorItrIn;     //原借据实收表内正常利息金额
	private BigDecimal padUpDftItrIn;     //原借据实收表内拖欠利息金额
	private BigDecimal padUpPnsItrIn;     //原借据实收表内罚息金额    
	private BigDecimal padUpCpdItrIn;     //原借据实收表内复利金额    
	private BigDecimal padUpPentIcm;      //原借据违约金              
	private String prvCod;            //原借据通知书编号          
	private String rlsDep;            //原借据合同编号            
	private String begItrDate;        //原借据发放日期            
	private String forwProvDate;      //原借据到期日期            
	private String talDep;            //原借据借款人名称          
	private String accEntJson; //核心记账接口结构
	private String payOutItrFlg;//是否还利息标志
	public RepayOldNewRq() {
	}
	
	public String getPayOutItrFlg() {
		return payOutItrFlg;
	}

	public void setPayOutItrFlg(String payOutItrFlg) {
		this.payOutItrFlg = payOutItrFlg;
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
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	public String getDueNumSun() {
		return dueNumSun;
	}
	public void setDueNumSun(String dueNumSun) {
		this.dueNumSun = dueNumSun;
	}
	public String getPayOpenDep() {
		return payOpenDep;
	}
	public void setPayOpenDep(String payOpenDep) {
		this.payOpenDep = payOpenDep;
	}
	public String getPayPrimAcctTyp() {
		return PayPrimAcctTyp;
	}
	public void setPayPrimAcctTyp(String payPrimAcctTyp) {
		PayPrimAcctTyp = payPrimAcctTyp;
	}
	public String getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
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
	public BigDecimal getRcvCpdItrIn() {
		return rcvCpdItrIn;
	}
	public void setRcvCpdItrIn(BigDecimal rcvCpdItrIn) {
		this.rcvCpdItrIn = rcvCpdItrIn;
	}
	public BigDecimal getPadUpPrn() {
		return padUpPrn;
	}
	public void setPadUpPrn(BigDecimal padUpPrn) {
		this.padUpPrn = padUpPrn;
	}
	public BigDecimal getPadUpNorItrIn() {
		return padUpNorItrIn;
	}
	public void setPadUpNorItrIn(BigDecimal padUpNorItrIn) {
		this.padUpNorItrIn = padUpNorItrIn;
	}
	public BigDecimal getPadUpDftItrIn() {
		return padUpDftItrIn;
	}
	public void setPadUpDftItrIn(BigDecimal padUpDftItrIn) {
		this.padUpDftItrIn = padUpDftItrIn;
	}
	public BigDecimal getPadUpPnsItrIn() {
		return padUpPnsItrIn;
	}
	public void setPadUpPnsItrIn(BigDecimal padUpPnsItrIn) {
		this.padUpPnsItrIn = padUpPnsItrIn;
	}
	public BigDecimal getPadUpCpdItrIn() {
		return padUpCpdItrIn;
	}
	public void setPadUpCpdItrIn(BigDecimal padUpCpdItrIn) {
		this.padUpCpdItrIn = padUpCpdItrIn;
	}
	public BigDecimal getPadUpPentIcm() {
		return padUpPentIcm;
	}
	public void setPadUpPentIcm(BigDecimal padUpPentIcm) {
		this.padUpPentIcm = padUpPentIcm;
	}
	public String getPrvCod() {
		return prvCod;
	}
	public void setPrvCod(String prvCod) {
		this.prvCod = prvCod;
	}
	public String getRlsDep() {
		return rlsDep;
	}
	public void setRlsDep(String rlsDep) {
		this.rlsDep = rlsDep;
	}
	public String getBegItrDate() {
		return begItrDate;
	}
	public void setBegItrDate(String begItrDate) {
		this.begItrDate = begItrDate;
	}
	public String getForwProvDate() {
		return forwProvDate;
	}
	public void setForwProvDate(String forwProvDate) {
		this.forwProvDate = forwProvDate;
	}
	public String getTalDep() {
		return talDep;
	}
	public void setTalDep(String talDep) {
		this.talDep = talDep;
	}


}
