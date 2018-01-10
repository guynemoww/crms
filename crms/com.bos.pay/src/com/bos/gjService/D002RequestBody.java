package com.bos.gjService;

import java.io.Serializable;
/**
 * 
 * @author ww
 *
 */
public class D002RequestBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1261014058005632819L;
	
	private String ecifPartyNum;//客户编号        
	private String source;//渠道来源              
	private String productType;//产品类型         
	private String userNum;//经办人             
	private String orgNum;//经办人机构              
	private String guarantyType;//担保方式        
	private String amt;//业务金额                 
	private String loanUse;//贷款用途           
	private String repaymentType;//还款方式       
	private String payment;//还款来源             
	private String applyXwTerm; //申请期限        
	private String applyRate;  //申请利率         
	private String applyDate;   //申请日期        
	private String signPlace;   //签约地点        
	private String loanTurn;  //行业投向          
	private String ajustType; //产业结构调整类型          
	private String upgradeType;//工业转型升级标识         
	private String newProductType;//战略新兴产业类型      
	private String payway; //资金支付方式             
	private String prepaymentPenalty;//提前还款是否收违约金   
	private String wybcbl;//提前还款补偿率(%)              
	private String zhmc;//放款账户名称                
	private String zhzh;//放款账户账号                
	private String zhkhjg;//开户行              
	private String zhzt;//账户状态                
	private String firstPayBackZhmc;//第一还款账户名称    
	private String firstPayBackZhzh;//第一还款账户账号    
	private String firstPayBackZhkhjg;//第一还款账户开户行  
	private String firstPayBackZhstatus;//第一还款账户状态
	private String secondPayBackZhmc;//第二还款账户名称   
	private String secondPayBackZhzh;//第二还款账户账号   
	private String secondPayBackZhkhjg;//第二还款账户开户行 
	private String secondPayBackZhstatus;//第二还款账户状态
	private String thirdPayBackZhmc; //第三还款账户名称   
	private String thirdPayBackZhzh;//第三还款账户账号    
	private String thirdPayBackZhkhjg; //第三还款账户开户行 
	private String thirdPayBackZhstatus;//第三还款账户状态
	
	
	public D002RequestBody() {
	}


	public String getEcifPartyNum() {
		return ecifPartyNum;
	}


	public void setEcifPartyNum(String ecifPartyNum) {
		this.ecifPartyNum = ecifPartyNum;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getUserNum() {
		return userNum;
	}


	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}


	public String getOrgNum() {
		return orgNum;
	}


	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}


	public String getGuarantyType() {
		return guarantyType;
	}


	public void setGuarantyType(String guarantyType) {
		this.guarantyType = guarantyType;
	}


	public String getAmt() {
		return amt;
	}


	public void setAmt(String amt) {
		this.amt = amt;
	}


	public String getLoanUse() {
		return loanUse;
	}


	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}


	public String getRepaymentType() {
		return repaymentType;
	}


	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getApplyXwTerm() {
		return applyXwTerm;
	}


	public void setApplyXwTerm(String applyXwTerm) {
		this.applyXwTerm = applyXwTerm;
	}


	public String getApplyRate() {
		return applyRate;
	}


	public void setApplyRate(String applyRate) {
		this.applyRate = applyRate;
	}


	public String getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}


	public String getSignPlace() {
		return signPlace;
	}


	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}


	public String getLoanTurn() {
		return loanTurn;
	}


	public void setLoanTurn(String loanTurn) {
		this.loanTurn = loanTurn;
	}


	public String getAjustType() {
		return ajustType;
	}


	public void setAjustType(String ajustType) {
		this.ajustType = ajustType;
	}


	public String getUpgradeType() {
		return upgradeType;
	}


	public void setUpgradeType(String upgradeType) {
		this.upgradeType = upgradeType;
	}


	public String getNewProductType() {
		return newProductType;
	}


	public void setNewProductType(String newProductType) {
		this.newProductType = newProductType;
	}


	public String getPayway() {
		return payway;
	}


	public void setPayway(String payway) {
		this.payway = payway;
	}


	public String getPrepaymentPenalty() {
		return prepaymentPenalty;
	}


	public void setPrepaymentPenalty(String prepaymentPenalty) {
		this.prepaymentPenalty = prepaymentPenalty;
	}


	public String getWybcbl() {
		return wybcbl;
	}


	public void setWybcbl(String wybcbl) {
		this.wybcbl = wybcbl;
	}


	public String getZhmc() {
		return zhmc;
	}


	public void setZhmc(String zhmc) {
		this.zhmc = zhmc;
	}


	public String getZhzh() {
		return zhzh;
	}


	public void setZhzh(String zhzh) {
		this.zhzh = zhzh;
	}


	public String getZhkhjg() {
		return zhkhjg;
	}


	public void setZhkhjg(String zhkhjg) {
		this.zhkhjg = zhkhjg;
	}


	public String getZhzt() {
		return zhzt;
	}


	public void setZhzt(String zhzt) {
		this.zhzt = zhzt;
	}


	public String getFirstPayBackZhmc() {
		return firstPayBackZhmc;
	}


	public void setFirstPayBackZhmc(String firstPayBackZhmc) {
		this.firstPayBackZhmc = firstPayBackZhmc;
	}


	public String getFirstPayBackZhzh() {
		return firstPayBackZhzh;
	}


	public void setFirstPayBackZhzh(String firstPayBackZhzh) {
		this.firstPayBackZhzh = firstPayBackZhzh;
	}


	public String getFirstPayBackZhkhjg() {
		return firstPayBackZhkhjg;
	}


	public void setFirstPayBackZhkhjg(String firstPayBackZhkhjg) {
		this.firstPayBackZhkhjg = firstPayBackZhkhjg;
	}


	public String getFirstPayBackZhstatus() {
		return firstPayBackZhstatus;
	}


	public void setFirstPayBackZhstatus(String firstPayBackZhstatus) {
		this.firstPayBackZhstatus = firstPayBackZhstatus;
	}


	public String getSecondPayBackZhmc() {
		return secondPayBackZhmc;
	}


	public void setSecondPayBackZhmc(String secondPayBackZhmc) {
		this.secondPayBackZhmc = secondPayBackZhmc;
	}


	public String getSecondPayBackZhzh() {
		return secondPayBackZhzh;
	}


	public void setSecondPayBackZhzh(String secondPayBackZhzh) {
		this.secondPayBackZhzh = secondPayBackZhzh;
	}


	public String getSecondPayBackZhkhjg() {
		return secondPayBackZhkhjg;
	}


	public void setSecondPayBackZhkhjg(String secondPayBackZhkhjg) {
		this.secondPayBackZhkhjg = secondPayBackZhkhjg;
	}


	public String getSecondPayBackZhstatus() {
		return secondPayBackZhstatus;
	}


	public void setSecondPayBackZhstatus(String secondPayBackZhstatus) {
		this.secondPayBackZhstatus = secondPayBackZhstatus;
	}


	public String getThirdPayBackZhmc() {
		return thirdPayBackZhmc;
	}


	public void setThirdPayBackZhmc(String thirdPayBackZhmc) {
		this.thirdPayBackZhmc = thirdPayBackZhmc;
	}


	public String getThirdPayBackZhzh() {
		return thirdPayBackZhzh;
	}


	public void setThirdPayBackZhzh(String thirdPayBackZhzh) {
		this.thirdPayBackZhzh = thirdPayBackZhzh;
	}


	public String getThirdPayBackZhkhjg() {
		return thirdPayBackZhkhjg;
	}


	public void setThirdPayBackZhkhjg(String thirdPayBackZhkhjg) {
		this.thirdPayBackZhkhjg = thirdPayBackZhkhjg;
	}


	public String getThirdPayBackZhstatus() {
		return thirdPayBackZhstatus;
	}


	public void setThirdPayBackZhstatus(String thirdPayBackZhstatus) {
		this.thirdPayBackZhstatus = thirdPayBackZhstatus;
	}
   

}
