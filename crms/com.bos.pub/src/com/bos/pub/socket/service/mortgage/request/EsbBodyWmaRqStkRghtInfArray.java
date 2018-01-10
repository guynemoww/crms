package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @ClassName: EsbBodyWmaRqStkRghtInfArray   股权
 * @Description: 02002000004信贷信息维护		02押品信息建立	
 *
 */
@XmlRootElement(name = "PlgDtlInf")
public class EsbBodyWmaRqStkRghtInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private  String stkRghtCtfNo;          			//股权证号码               		String(20)      Y
	private  String stkRghtTp;						//股权类型       	  		String(4)       Y
	private  String ccy;							//币种                                                      String(10)      Y
	private  double plgStkRghtAmt;					//质押股权金额                                    Double(26,8)    Y
	private  double plgStkRghtPrcnt;				//质押股权占股比例                           Double(16,8)    Y
	private  String stkRghtKnd;						//股权性质                        		String(4)       Y
	private  String issuEntpNm;						//发行企业名称                                    String(100)     Y
	private  String bnkOrListCoFlg;					//银行或上市公司股权标志             String(4)       Y
	private  String pblsDt;							//发行日期                                             String(8)       Y
	private  String expDt;							//到期日                                                  String(8)       Y
	
	public EsbBodyWmaRqStkRghtInfArray(){
		
	}

	public String getStkRghtCtfNo() {
		return stkRghtCtfNo;
	}

	@XmlElement(name = "StkRghtCtfNo")
	public void setStkRghtCtfNo(String stkRghtCtfNo) {
		this.stkRghtCtfNo = stkRghtCtfNo;
	}

	public String getStkRghtTp() {
		return stkRghtTp;
	}

	@XmlElement(name = "StkRghtTp")
	public void setStkRghtTp(String stkRghtTp) {
		this.stkRghtTp = stkRghtTp;
	}

	public String getCcy() {
		return ccy;
	}

	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getPlgStkRghtAmt() {
		return plgStkRghtAmt;
	}

	@XmlElement(name = "PlgStkRghtAmt")
	public void setPlgStkRghtAmt(double plgStkRghtAmt) {
		this.plgStkRghtAmt = plgStkRghtAmt;
	}

	public double getPlgStkRghtPrcnt() {
		return plgStkRghtPrcnt;
	}

	@XmlElement(name = "PlgStkRghtPrcnt")
	public void setPlgStkRghtPrcnt(double plgStkRghtPrcnt) {
		this.plgStkRghtPrcnt = plgStkRghtPrcnt;
	}

	public String getStkRghtKnd() {
		return stkRghtKnd;
	}

	@XmlElement(name = "StkRghtKnd")
	public void setStkRghtKnd(String stkRghtKnd) {
		this.stkRghtKnd = stkRghtKnd;
	}

	public String getIssuEntpNm() {
		return issuEntpNm;
	}

	@XmlElement(name = "IssuEntpNm")
	public void setIssuEntpNm(String issuEntpNm) {
		this.issuEntpNm = issuEntpNm;
	}

	public String getBnkOrListCoFlg() {
		return bnkOrListCoFlg;
	}

	@XmlElement(name = "BnkOrListCoFlg")
	public void setBnkOrListCoFlg(String bnkOrListCoFlg) {
		this.bnkOrListCoFlg = bnkOrListCoFlg;
	}

	public String getPblsDt() {
		return pblsDt;
	}

	@XmlElement(name = "PblsDt")
	public void setPblsDt(String pblsDt) {
		this.pblsDt = pblsDt;
	}

	public String getExpDt() {
		return expDt;
	}

	@XmlElement(name = "ExpDt")
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}

	
}
