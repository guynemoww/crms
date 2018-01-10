package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqFrstRghtInfArray   林权
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqFrstRghtInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String ctrMgtPrsnNm;          			//承包经营权人名称             String(80)       Y
	private  String frstRghtCtfNo;					//林权证号       	    String(20)       Y
	private  String ccy;							//币种                                        String(10)       Y
	private  double yrCtrCostAmt;					//年承包费用                           Double(26,8)     Y
	private  String ctrStrtDt;						//承包起始日期                       String(8)        Y
	private  String ctrEndDt;						//承包终止日期                       String(8)        Y
	private  String ptyOwnNm;						//产权人名称                            String(80)       Y
	
	public EsbBodyWmaRqFrstRghtInfArray(){
		
	}

	public String getCtrMgtPrsnNm() {
		return ctrMgtPrsnNm;
	}

	@XmlElement(name = "CtrMgtPrsnNm")
	public void setCtrMgtPrsnNm(String ctrMgtPrsnNm) {
		this.ctrMgtPrsnNm = ctrMgtPrsnNm;
	}

	public String getFrstRghtCtfNo() {
		return frstRghtCtfNo;
	}

	@XmlElement(name = "FrstRghtCtfNo")
	public void setFrstRghtCtfNo(String frstRghtCtfNo) {
		this.frstRghtCtfNo = frstRghtCtfNo;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getYrCtrCostAmt() {
		return yrCtrCostAmt;
	}

	@XmlElement(name = "YrCtrCostAmt")
	public void setYrCtrCostAmt(double yrCtrCostAmt) {
		this.yrCtrCostAmt = yrCtrCostAmt;
	}

	public String getCtrStrtDt() {
		return ctrStrtDt;
	}

	@XmlElement(name = "CtrStrtDt")
	public void setCtrStrtDt(String ctrStrtDt) {
		this.ctrStrtDt = ctrStrtDt;
	}

	public String getCtrEndDt() {
		return ctrEndDt;
	}

	@XmlElement(name = "CtrEndDt")
	public void setCtrEndDt(String ctrEndDt) {
		this.ctrEndDt = ctrEndDt;
	}

	public String getPtyOwnNm() {
		return ptyOwnNm;
	}

	@XmlElement(name = "PtyOwnNm")
	public void setPtyOwnNm(String ptyOwnNm) {
		this.ptyOwnNm = ptyOwnNm;
	}

	
}
