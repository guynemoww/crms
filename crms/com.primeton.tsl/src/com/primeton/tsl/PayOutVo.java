package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A02;
import com.bos.pub.socket.service.request.EsbBodyHxRq01001000002A05;

public class PayOutVo extends SuperBosfxRq implements Serializable {
	private EsbBodyHxRq01001000002A02 msgCore3181In;//放款核心需要的报文
	private EsbBodyHxRq01001000002A05 msgCore7130In;//放款核心需要的报文,受托支付
   //	private TranMsgVO tranMsgVO;
	private String dueNum;			//借据编号    
	private String brwName;			//客户名称
	private String conNo;			//合同编号
	private String busCod;			//业务品种    
	private String primAcct;		//放款账号  
	private String primAcctName;	//放款账户名称 
	private String payPrimAcct;		//还款账号
	private String payPrimName;		//还款账户名称  
	private String currCod;			//币种          
	private BigDecimal amt;			//放款金额      
	private BigDecimal norItrRate;	//正常利率      
	private BigDecimal delItrRate;	//罚息利率      
	private String begDate;			//放款日期      
	private String endDate;			//到期日期      
	private String prmPayTyp;		//主还款方式    
	private String astPayTyp;		//子还款方式    
	private String caspan;			//结息周期      
	private String specPaymentDate;	//指定还款日    
	private String firstCasDate;	//首次结息日    
	private int stgFirstMon;		//阶段性还本期数
	private String discType;		//贴息方式      
	private String cusNo;			//客户编号      
	private String telNo;			//放款通知书编号
	private String trusToPayFlg;			//受托支付标志0=非受托支付 1=受托支付

	public PayOutVo(){
		this.setBaseVO(new BaseVO());
		this.msgCore3181In = new EsbBodyHxRq01001000002A02();
		this.msgCore7130In = new EsbBodyHxRq01001000002A05();
	//	this.tranMsgVO = new TranMsgVO();
	}

	public EsbBodyHxRq01001000002A02 getMsgCore3181In() {
		return msgCore3181In;
	}

	@XmlElement(name = "EsbBodyHxRq01001000002A02")
	public void setMsgCore3181In(EsbBodyHxRq01001000002A02 msgCore3181In) {
		this.msgCore3181In = msgCore3181In;
	}

	public EsbBodyHxRq01001000002A05 getMsgCore7130In() {
		return msgCore7130In;
	}
	
	@XmlElement(name = "EsbBodyHxRq01001000002A05")
	public void setMsgCore7130In(EsbBodyHxRq01001000002A05 msgCore7130In) {
		this.msgCore7130In = msgCore7130In;
	}
/*	public TranMsgVO getTranMsgVO() {
		return tranMsgVO;
	}

	@XmlElement(name = "TranMsgVO")
	public void setTranMsgVO(TranMsgVO tranMsgVO) {
		this.tranMsgVO = tranMsgVO;
	}*/

	public String getDueNum() {
		return dueNum;
	}

	@XmlElement(name = "DueNum")
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}

	public String getBrwName() {
		return brwName;
	}

	@XmlElement(name = "BrwName")
	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}

	public String getConNo() {
		return conNo;
	}

	@XmlElement(name = "ConNo")
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	public String getBusCod() {
		return busCod;
	}

	@XmlElement(name = "BusCod")
	public void setBusCod(String busCod) {
		this.busCod = busCod;
	}

	public String getPrimAcct() {
		return primAcct;
	}

	@XmlElement(name = "PrimAcct")
	public void setPrimAcct(String primAcct) {
		this.primAcct = primAcct;
	}

	public String getPrimAcctName() {
		return primAcctName;
	}

	@XmlElement(name = "PrimAcctName")
	public void setPrimAcctName(String primAcctName) {
		this.primAcctName = primAcctName;
	}

	public String getPayPrimAcct() {
		return payPrimAcct;
	}

	@XmlElement(name = "PayPrimAcct")
	public void setPayPrimAcct(String payPrimAcct) {
		this.payPrimAcct = payPrimAcct;
	}

	public String getPayPrimName() {
		return payPrimName;
	}

	@XmlElement(name = "PayPrimName")
	public void setPayPrimName(String payPrimName) {
		this.payPrimName = payPrimName;
	}

	public String getCurrCod() {
		return currCod;
	}

	@XmlElement(name = "CurrCod")
	public void setCurrCod(String currCod) {
		this.currCod = currCod;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	@XmlElement(name = "Amt")
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getNorItrRate() {
		return norItrRate;
	}

	@XmlElement(name = "NorItrRate")
	public void setNorItrRate(BigDecimal norItrRate) {
		this.norItrRate = norItrRate;
	}

	public BigDecimal getDelItrRate() {
		return delItrRate;
	}

	@XmlElement(name = "DelItrRate")
	public void setDelItrRate(BigDecimal delItrRate) {
		this.delItrRate = delItrRate;
	}

	public String getBegDate() {
		return begDate;
	}

	@XmlElement(name = "BegDate")
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	public String getEndDate() {
		return endDate;
	}

	@XmlElement(name = "EndDate")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPrmPayTyp() {
		return prmPayTyp;
	}

	@XmlElement(name = "PrmPayTyp")
	public void setPrmPayTyp(String prmPayTyp) {
		this.prmPayTyp = prmPayTyp;
	}

	public String getAstPayTyp() {
		return astPayTyp;
	}

	@XmlElement(name = "AstPayTyp")
	public void setAstPayTyp(String astPayTyp) {
		this.astPayTyp = astPayTyp;
	}

	public String getCaspan() {
		return caspan;
	}

	@XmlElement(name = "Caspan")
	public void setCaspan(String caspan) {
		this.caspan = caspan;
	}

	public String getSpecPaymentDate() {
		return specPaymentDate;
	}

	@XmlElement(name = "SpecPaymentDate")
	public void setSpecPaymentDate(String specPaymentDate) {
		this.specPaymentDate = specPaymentDate;
	}

	public String getFirstCasDate() {
		return firstCasDate;
	}

	@XmlElement(name = "FirstCasDate")
	public void setFirstCasDate(String firstCasDate) {
		this.firstCasDate = firstCasDate;
	}

	public int getStgFirstMon() {
		return stgFirstMon;
	}

	@XmlElement(name = "StgFirstMon")
	public void setStgFirstMon(int stgFirstMon) {
		this.stgFirstMon = stgFirstMon;
	}

	public String getDiscType() {
		return discType;
	}

	@XmlElement(name = "DiscType")
	public void setDiscType(String discType) {
		this.discType = discType;
	}

	public String getCusNo() {
		return cusNo;
	}

	@XmlElement(name = "CusNo")
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}

	public String getTelNo() {
		return telNo;
	}

	@XmlElement(name = "TelNo")
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getTrusToPayFlg() {
		return trusToPayFlg;
	}
	
	@XmlElement(name = "TrusToPayFlg")
	public void setTrusToPayFlg(String trusToPayFlg) {
		this.trusToPayFlg = trusToPayFlg;
	}

	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "PayOutVo [amt=" + amt + ", astPayTyp=" + astPayTyp
				+ ", baseVO=" + this.getBaseVO() + ", begDate=" + begDate + ", brwName="
				+ brwName + ", busCod=" + busCod + ", caspan=" + caspan
				+ ", conNo=" + conNo + ", currCod=" + currCod + ", cusNo="
				+ cusNo + ", delItrRate=" + delItrRate + ", discType="
				+ discType + ", dueNum=" + dueNum + ", endDate=" + endDate
				+ ", firstCasDate=" + firstCasDate + ", norItrRate="
				+ norItrRate + ", payPrimAcct=" + payPrimAcct
				+ ", payPrimName=" + payPrimName + ", primAcct=" + primAcct+",trusToPayFlg="+trusToPayFlg
				+ ", primAcctName=" + primAcctName + ", prmPayTyp=" + prmPayTyp
				+ ", specPaymentDate=" + specPaymentDate + ", stgFirstMon="
				+ stgFirstMon + ", telNo=" + telNo + ", msgCore3181In=" + msgCore3181In+ ",msgCore7130In="+msgCore7130In
				+ "]";
	}
//	private String telNo;//通知书编号
//	private String dueNum;//借据编号
//	private String brwName;//客户名称      
//	private String conNo;//合同编号      
//	private String prodCod;//业务品种      
//	private String primAcct;//放款账号      
//	private String primAcctName;//放款账户名称  
//	private String payPrimAcct;//还款账号      
//	private String payPrimName;//还款账户名称  
//	private String currCod;//币种          
//	private BigDecimal padUpAmt;//放款金额      
//	private BigDecimal norItrRate;//正常利率      
//	private BigDecimal delItrRate;//罚息利率      
//	private String begDate;//放款日期      
//	private String endDate;//到期日期      
//	private String curPrmPayTyp;//主还款方式    
//	private String curAstPayTyp;//子还款方式    
//	private String caspan;//结息周期      
//	private String payDate;//指定还款日    
//	private String nextProvDate;//首次结息日    
//	private String stgFirstMon;//阶段性还本期数
//	private String discType;//贴息方式      
//	private String dueNumSun;//原借据编号    
//	private String agyBusAccName;//原借款人名称  
//	private BigDecimal rcvPrn;//原借据本金    
//	private BigDecimal rcvNorItrIn;//原借据正常利息
//	private BigDecimal rcvDftItrIn;//原借据拖欠利息
//	private BigDecimal rcvPnsItrIn;//原借据罚息   
//	
//	
//	public String getTelNo() {
//		return telNo;
//	}
//	@XmlElement(name = "TelNo")
//	public void setTelNo(String telNo) {
//		this.telNo = telNo;
//	}
//	public String getDueNum() {
//		return dueNum;
//	}
//	@XmlElement(name = "DueNum")
//	public void setDueNum(String dueNum) {
//		this.dueNum = dueNum;
//	}
//	public String getBrwName() {
//		return brwName;
//	}
//	@XmlElement(name = "BrwName")
//	public void setBrwName(String brwName) {
//		this.brwName = brwName;
//	}
//	public String getConNo() {
//		return conNo;
//	}
//	@XmlElement(name = "ConNo")
//	public void setConNo(String conNo) {
//		this.conNo = conNo;
//	}
//	public String getProdCod() {
//		return prodCod;
//	}
//	@XmlElement(name = "ProdCod")
//	public void setProdCod(String prodCod) {
//		this.prodCod = prodCod;
//	}
//	public String getPrimAcct() {
//		return primAcct;
//	}
//	@XmlElement(name = "PrimAcct")
//	public void setPrimAcct(String primAcct) {
//		this.primAcct = primAcct;
//	}
//	public String getPrimAcctName() {
//		return primAcctName;
//	}
//	@XmlElement(name = "PrimAcctName")
//	public void setPrimAcctName(String primAcctName) {
//		this.primAcctName = primAcctName;
//	}
//	public String getPayPrimAcct() {
//		return payPrimAcct;
//	}
//	@XmlElement(name = "PayPrimAcct")
//	public void setPayPrimAcct(String payPrimAcct) {
//		this.payPrimAcct = payPrimAcct;
//	}
//	public String getPayPrimName() {
//		return payPrimName;
//	}
//	@XmlElement(name = "PayPrimName")
//	public void setPayPrimName(String payPrimName) {
//		this.payPrimName = payPrimName;
//	}
//	public String getCurrCod() {
//		return currCod;
//	}
//	@XmlElement(name = "CurrCod")
//	public void setCurrCod(String currCod) {
//		this.currCod = currCod;
//	}
//	public BigDecimal getPadUpAmt() {
//		return padUpAmt;
//	}
//	@XmlElement(name = "PadUpAmt")
//	public void setPadUpAmt(BigDecimal padUpAmt) {
//		this.padUpAmt = padUpAmt;
//	}
//	public BigDecimal getNorItrRate() {
//		return norItrRate;
//	}
//	@XmlElement(name = "NorItrRate")
//	public void setNorItrRate(BigDecimal norItrRate) {
//		this.norItrRate = norItrRate;
//	}
//	public BigDecimal getDelItrRate() {
//		return delItrRate;
//	}
//	@XmlElement(name = "DelItrRate")
//	public void setDelItrRate(BigDecimal delItrRate) {
//		this.delItrRate = delItrRate;
//	}
//	public String getBegDate() {
//		return begDate;
//	}
//	@XmlElement(name = "BegDate")
//	public void setBegDate(String begDate) {
//		this.begDate = begDate;
//	}
//	public String getEndDate() {
//		return endDate;
//	}
//	@XmlElement(name = "EndDate")
//	public void setEndDate(String endDate) {
//		this.endDate = endDate;
//	}
//	public String getCurPrmPayTyp() {
//		return curPrmPayTyp;
//	}
//	@XmlElement(name = "CurPrmPayTyp")
//	public void setCurPrmPayTyp(String curPrmPayTyp) {
//		this.curPrmPayTyp = curPrmPayTyp;
//	}
//	public String getCurAstPayTyp() {
//		return curAstPayTyp;
//	}
//	@XmlElement(name = "CurAstPayTyp")
//	public void setCurAstPayTyp(String curAstPayTyp) {
//		this.curAstPayTyp = curAstPayTyp;
//	}
//	public String getCaspan() {
//		return caspan;
//	}
//	@XmlElement(name = "Caspan")
//	public void setCaspan(String caspan) {
//		this.caspan = caspan;
//	}
//	public String getPayDate() {
//		return payDate;
//	}
//	@XmlElement(name = "PayDate")
//	public void setPayDate(String payDate) {
//		this.payDate = payDate;
//	}
//	public String getNextProvDate() {
//		return nextProvDate;
//	}
//	@XmlElement(name = "NextProvDate")
//	public void setNextProvDate(String nextProvDate) {
//		this.nextProvDate = nextProvDate;
//	}
//	public String getStgFirstMon() {
//		return stgFirstMon;
//	}
//	@XmlElement(name = "StgFirstMon")
//	public void setStgFirstMon(String stgFirstMon) {
//		this.stgFirstMon = stgFirstMon;
//	}
//	public String getDiscType() {
//		return discType;
//	}
//	@XmlElement(name = "DiscType")
//	public void setDiscType(String discType) {
//		this.discType = discType;
//	}
//	public String getDueNumSun() {
//		return dueNumSun;
//	}
//	@XmlElement(name = "DueNumSun")
//	public void setDueNumSun(String dueNumSun) {
//		this.dueNumSun = dueNumSun;
//	}
//	public String getAgyBusAccName() {
//		return agyBusAccName;
//	}
//	@XmlElement(name = "AgyBusAccName")
//	public void setAgyBusAccName(String agyBusAccName) {
//		this.agyBusAccName = agyBusAccName;
//	}
//	public BigDecimal getRcvPrn() {
//		return rcvPrn;
//	}
//	@XmlElement(name = "RcvPrn")
//	public void setRcvPrn(BigDecimal rcvPrn) {
//		this.rcvPrn = rcvPrn;
//	}
//	public BigDecimal getRcvNorItrIn() {
//		return rcvNorItrIn;
//	}
//	@XmlElement(name = "RcvNorItrIn")
//	public void setRcvNorItrIn(BigDecimal rcvNorItrIn) {
//		this.rcvNorItrIn = rcvNorItrIn;
//	}
//	public BigDecimal getRcvDftItrIn() {
//		return rcvDftItrIn;
//	}
//	@XmlElement(name = "RcvDftItrIn")
//	public void setRcvDftItrIn(BigDecimal rcvDftItrIn) {
//		this.rcvDftItrIn = rcvDftItrIn;
//	}
//	public BigDecimal getRcvPnsItrIn() {
//		return rcvPnsItrIn;
//	}
//	@XmlElement(name = "RcvPnsItrIn")
//	public void setRcvPnsItrIn(BigDecimal rcvPnsItrIn) {
//		this.rcvPnsItrIn = rcvPnsItrIn;
//	}
//	@Override
//	public String toString() {
//		return "PayOutVo [telNo=" + telNo + ", dueNum=" + dueNum + ", brwName="
//				+ brwName + ", conNo=" + conNo + ", prodCod=" + prodCod
//				+ ", primAcct=" + primAcct + ", primAcctName=" + primAcctName
//				+ ", payPrimAcct=" + payPrimAcct + ", payPrimName="
//				+ payPrimName + ", currCod=" + currCod + ", padUpAmt="
//				+ padUpAmt + ", norItrRate=" + norItrRate + ", delItrRate="
//				+ delItrRate + ", begDate=" + begDate + ", endDate=" + endDate
//				+ ", curPrmPayTyp=" + curPrmPayTyp + ", curAstPayTyp="
//				+ curAstPayTyp + ", caspan=" + caspan + ", payDate=" + payDate
//				+ ", nextProvDate=" + nextProvDate + ", stgFirstMon="
//				+ stgFirstMon + ", discType=" + discType + ", dueNumSun="
//				+ dueNumSun + ", agyBusAccName=" + agyBusAccName + ", rcvPrn="
//				+ rcvPrn + ", rcvNorItrIn=" + rcvNorItrIn + ", rcvDftItrIn="
//				+ rcvDftItrIn + ", rcvPnsItrIn=" + rcvPnsItrIn + "]";
//	}
	
}
