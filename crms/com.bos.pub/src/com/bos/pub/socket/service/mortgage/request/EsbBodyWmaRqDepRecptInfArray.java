package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqDepRecptInfArray   存单
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqDepRecptInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String depRecptTp;						//存单类型                       String(4)        Y
	private  String depRecptAcctNo;					//存单账号                       String(35)       N
	private  String depRecptNo;				    	//存单号                            String(20)       N
	private  String openAcctBnkNm;					//开户行行名                   String(100)      Y
	private  String depDt;							//存入日期              	  String(8)        N
	private  String expDt;				    		//到期日                             String(8)        N
	private  String ccy;				    		//币种                                 String(10)       Y
	private  double depRecpAmt;				    	//存单面额                        Double(26,8)     Y
	private  double depRecpRate;				    //存单利率                        Double(16,8)     Y
	
	public EsbBodyWmaRqDepRecptInfArray(){
		
	}

	public String getDepRecptTp() {
		return depRecptTp;
	}

	@XmlElement(name = "DepRecptTp")
	public void setDepRecptTp(String depRecptTp) {
		this.depRecptTp = depRecptTp;
	}

	public String getDepRecptAcctNo() {
		return depRecptAcctNo;
	}

	@XmlElement(name = "DepRecptAcctNo")
	public void setDepRecptAcctNo(String depRecptAcctNo) {
		this.depRecptAcctNo = depRecptAcctNo;
	}

	public String getDepRecptNo() {
		return depRecptNo;
	}

	@XmlElement(name = "DepRecptNo")
	public void setDepRecptNo(String depRecptNo) {
		this.depRecptNo = depRecptNo;
	}

	public String getOpenAcctBnkNm() {
		return openAcctBnkNm;
	}

	@XmlElement(name = "OpenAcctBnkNm")
	public void setOpenAcctBnkNm(String openAcctBnkNm) {
		this.openAcctBnkNm = openAcctBnkNm;
	}

	public String getDepDt() {
		return depDt;
	}

	@XmlElement(name = "DepDt")
	public void setDepDt(String depDt) {
		this.depDt = depDt;
	}

	public String getExpDt() {
		return expDt;
	}

	@XmlElement(name = "ExpDt")
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getDepRecpAmt() {
		return depRecpAmt;
	}

	@XmlElement(name = "DepRecpAmt")
	public void setDepRecpAmt(double depRecpAmt) {
		this.depRecpAmt = depRecpAmt;
	}

	public double getDepRecpRate() {
		return depRecpRate;
	}

	@XmlElement(name = "DepRecpRate")
	public void setDepRecpRate(double depRecpRate) {
		this.depRecpRate = depRecpRate;
	}
	
}
