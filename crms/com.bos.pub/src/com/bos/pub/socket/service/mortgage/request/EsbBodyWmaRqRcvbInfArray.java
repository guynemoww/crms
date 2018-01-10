package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqRcvbInfArray   应收类
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqRcvbInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String pyeNm;          			//票据号码               		String(100)      Y
	private  String pyeOpenAcctBnkNm;			//出票人户名       	    String(100)      Y
	private  String pyeAcctNo;					//出票人账号                           String(35)       Y
	private  String pyrNm;						//出票人开户行行名              String(100)      Y
	private  String pyrEntpKnd;					//出票日期                                String(4)        Y
	private  String ctrNo;						//到期日                                    String(20)       Y
	private  String ccy;						//收款人账号                           String(10)       Y
	private  double rcvbBal;				    //票据金额                  	Double(26,8)     Y
	private  String agngMoNum;					//收款人开户行行名             String(4)        Y
	private  String sellOnCrDt;					//币种                                        String(8)        Y
	private  String rcvbEndDt;					//连续背书标志                      String(8)        Y
	
	public EsbBodyWmaRqRcvbInfArray(){
		
	}

	public String getPyeNm() {
		return pyeNm;
	}

	@XmlElement(name = "PyeNm")
	public void setPyeNm(String pyeNm) {
		this.pyeNm = pyeNm;
	}

	public String getPyeOpenAcctBnkNm() {
		return pyeOpenAcctBnkNm;
	}
	
	@XmlElement(name = "PyeOpenAcctBnkNm")
	public void setPyeOpenAcctBnkNm(String pyeOpenAcctBnkNm) {
		this.pyeOpenAcctBnkNm = pyeOpenAcctBnkNm;
	}

	public String getPyeAcctNo() {
		return pyeAcctNo;
	}

	@XmlElement(name = "PyeAcctNo")
	public void setPyeAcctNo(String pyeAcctNo) {
		this.pyeAcctNo = pyeAcctNo;
	}

	public String getPyrNm() {
		return pyrNm;
	}

	@XmlElement(name = "PyrNm")
	public void setPyrNm(String pyrNm) {
		this.pyrNm = pyrNm;
	}

	public String getPyrEntpKnd() {
		return pyrEntpKnd;
	}

	@XmlElement(name = "PyrEntpKnd")
	public void setPyrEntpKnd(String pyrEntpKnd) {
		this.pyrEntpKnd = pyrEntpKnd;
	}

	public String getCtrNo() {
		return ctrNo;
	}

	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getRcvbBal() {
		return rcvbBal;
	}

	@XmlElement(name = "RcvbBal")
	public void setRcvbBal(double rcvbBal) {
		this.rcvbBal = rcvbBal;
	}

	public String getAgngMoNum() {
		return agngMoNum;
	}

	@XmlElement(name = "AgngMoNum")
	public void setAgngMoNum(String agngMoNum) {
		this.agngMoNum = agngMoNum;
	}

	public String getSellOnCrDt() {
		return sellOnCrDt;
	}

	@XmlElement(name = "SellOnCrDt")
	public void setSellOnCrDt(String sellOnCrDt) {
		this.sellOnCrDt = sellOnCrDt;
	}

	public String getRcvbEndDt() {
		return rcvbEndDt;
	}

	@XmlElement(name = "RcvbEndDt")
	public void setRcvbEndDt(String rcvbEndDt) {
		this.rcvbEndDt = rcvbEndDt;
	}

	
}
