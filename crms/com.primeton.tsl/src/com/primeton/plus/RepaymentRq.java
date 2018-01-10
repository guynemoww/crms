package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 还款输入
 * @author CHENPAN
 *
 */
public class RepaymentRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5884137208735458269L;
	private String payOutItrFlg; //归还未结计利息标志
	private String payPrimName;   //还款账户名称
	private String payPrimAcct;   //还款账号    
	private BigDecimal padUpAmt = new BigDecimal("0.00");  //还款金额    
	private String payOrder;      //还款顺序    
	private BigDecimal rcvPrn = new BigDecimal("0.00"); ;        //应还本金    
	private BigDecimal rcvNorItrIn = new BigDecimal("0.00"); ;   //应还正常利息
	private BigDecimal rcvDftItrIn = new BigDecimal("0.00"); ;   //应还拖欠利息
	private BigDecimal rcvPnsItrIn = new BigDecimal("0.00"); ;   //应还罚息    
	private BigDecimal rcvCpdItrIn = new BigDecimal("0.00"); ;   //应还复利    
	private BigDecimal padUpPrn = new BigDecimal("0.00"); ;      //实还本金    
	private BigDecimal padUpNorItrIn = new BigDecimal("0.00"); ; //实还正常利息
	private BigDecimal padUpDftItrIn = new BigDecimal("0.00"); ; //实还拖欠利息
	private BigDecimal padUpPnsItrIn = new BigDecimal("0.00"); ; //实还罚息    
	private BigDecimal padUpCpdItrIn = new BigDecimal("0.00"); ; //实还复利    
	private BigDecimal padUpPentIcm = new BigDecimal("0.00"); ;  //违约金      
	private String dueNum;        //借据编号    
	private String telNo;         //通知书编号  
	private String conNo;         //合同编号    
	private String begDate;       //贷款起期    
	private String endDate;       //贷款止期    
	private String brwName;       //客户名称    
	private String sts;           //贷款状态   
	private String accEntJson; //核心记账接口结构
	private String productType;//产品类型
	private String assetTransferFlg;        //资产转让标识  1 资产转让    0 非资产转让
	private String pledgeDeductFlg ;  //抵质押扣划标志 1 代表是抵质押扣划  0 非质押扣划

	public RepaymentRq() {
	}
	
	public String getAssetTransferFlg() {
		return assetTransferFlg;
	}

	public void setAssetTransferFlg(String assetTransferFlg) {
		this.assetTransferFlg = assetTransferFlg;
	}

	public String getPledgeDeductFlg() {
		return pledgeDeductFlg;
	}

	public void setPledgeDeductFlg(String pledgeDeductFlg) {
		this.pledgeDeductFlg = pledgeDeductFlg;
	}

	public String getAccEntJson() {
		return accEntJson;
	}

	public void setAccEntJson(String accEntJson) {
		this.accEntJson = accEntJson;
	}

	public String getPayOutItrFlg() {
		return payOutItrFlg;
	}
	public void setPayOutItrFlg(String payOutItrFlg) {
		this.payOutItrFlg = payOutItrFlg;
	}
	public String getPayPrimName() {
		return payPrimName;
	}
	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
	}
	public String getPayPrimAcct() {
		return payPrimAcct;
	}
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}
	public BigDecimal getPadUpAmt() {
		return padUpAmt;
	}
	public void setPadUpAmt(BigDecimal padUpAmt) {
		this.padUpAmt = padUpAmt;
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
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
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
	public String getBrwName() {
		return brwName;
	}
	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
}
