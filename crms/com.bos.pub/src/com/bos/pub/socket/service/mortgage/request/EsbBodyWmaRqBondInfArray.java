package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqBondInfArray   债券
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqBondInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String issurTp;          			//发行机构类型               		String(4)      Y
	private  String issurNm;					//发行机构名称       	  		String(100)    Y
	private  String bondNo;						//债券号码                        		String(20)     Y
	private  String bookEntrTBondAgntNm;		//记账式国债代理机构名称              String(20)     N
	private  String ccy;						//币种                                                       String(10)     Y
	private  double bondValAmt;					//债券面额                                              Double(26,8)   Y
	private  double bondRate;					//债券年利率                              	Double(16,8)   Y
	private  String valDt;						//起息日期                  	    String(8)      Y
	private  String expDt;						//到期日                                                  String(8)      Y
	private  String pnpRepyMth;					//本金偿还方式                                     String(4)      Y
	private  String pyIntMth;					//付息方式                                              String(4)      Y
	private  String tfrblFlg;					//可转让标志                                         String(4)      Y
	
	public EsbBodyWmaRqBondInfArray(){
		
	}

	public String getIssurTp() {
		return issurTp;
	}

	@XmlElement(name = "IssurTp")
	public void setIssurTp(String issurTp) {
		this.issurTp = issurTp;
	}

	public String getIssurNm() {
		return issurNm;
	}

	@XmlElement(name = "IssurNm")
	public void setIssurNm(String issurNm) {
		this.issurNm = issurNm;
	}

	public String getBondNo() {
		return bondNo;
	}

	@XmlElement(name = "BondNo")
	public void setBondNo(String bondNo) {
		this.bondNo = bondNo;
	}

	public String getBookEntrTBondAgntNm() {
		return bookEntrTBondAgntNm;
	}

	@XmlElement(name = "BookEntrTBondAgntNm")
	public void setBookEntrTBondAgntNm(String bookEntrTBondAgntNm) {
		this.bookEntrTBondAgntNm = bookEntrTBondAgntNm;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getBondValAmt() {
		return bondValAmt;
	}

	@XmlElement(name = "BondValAmt")
	public void setBondValAmt(double bondValAmt) {
		this.bondValAmt = bondValAmt;
	}

	public double getBondRate() {
		return bondRate;
	}

	@XmlElement(name = "BondRate")
	public void setBondRate(double bondRate) {
		this.bondRate = bondRate;
	}

	public String getValDt() {
		return valDt;
	}

	@XmlElement(name = "ValDt")
	public void setValDt(String valDt) {
		this.valDt = valDt;
	}

	public String getExpDt() {
		return expDt;
	}

	@XmlElement(name = "ExpDt")
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}

	public String getPnpRepyMth() {
		return pnpRepyMth;
	}

	@XmlElement(name = "PnpRepyMth")
	public void setPnpRepyMth(String pnpRepyMth) {
		this.pnpRepyMth = pnpRepyMth;
	}

	public String getPyIntMth() {
		return pyIntMth;
	}

	@XmlElement(name = "PyIntMth")
	public void setPyIntMth(String pyIntMth) {
		this.pyIntMth = pyIntMth;
	}

	public String getTfrblFlg() {
		return tfrblFlg;
	}

	@XmlElement(name = "TfrblFlg")
	public void setTfrblFlg(String tfrblFlg) {
		this.tfrblFlg = tfrblFlg;
	}

	
}
