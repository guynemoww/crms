/**
 * BOSFXII 响应报文头信息
 */
package com.bos.jaxb.javabean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.bos.mq.util.KeyGenerator;

public class CommonRsHdr {
	//返回结果码
	@XmlElement(name="StatusCode")
	public String StatusCode;
	//返回结果信息
	@XmlElement(name="ServerStatusCode")
	public String ServerStatusCode;
	//渠道流水号
	@XmlElement(name="RqUID")
	public String RqUID;
	//主机流水号
	@XmlElement(name="SPRsUID")
	public String SPRsUID;
	//数字交易码
	@XmlElement(name="NumTranCode")
	public String NumTranCode;
	//网点号
	@XmlElement(name="CompanyCode")
	public String CompanyCode;
	//柜员号
	@XmlElement(name="CntId")
	public String CntId;
	
	/**
	 * @param cntId 要设置的 cntId
	 */
	public void setCntId(String cntId) {
		CntId = cntId;
	}

	/**
	 * @param companyCode 要设置的 companyCode
	 */
	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	/**
	 * @param numTranCode 要设置的 numTranCode
	 */
	public void setNumTranCode(String numTranCode) {
		NumTranCode = numTranCode;
	}

	/**
	 * @param rqUID 要设置的 rqUID
	 */
	public void setRqUID(String rqUID) {
		RqUID = rqUID;
	}

	/**
	 * @param serverStatusCode 要设置的 serverStatusCode
	 */
	public void setServerStatusCode(String serverStatusCode) {
		ServerStatusCode = serverStatusCode;
	}

	/**
	 * @param rsUID 要设置的 sPRsUID
	 */
	public void setSPRsUID(String rsUID) {
		SPRsUID = rsUID;
	}

	/**
	 * @param statusCode 要设置的 statusCode
	 */
	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}
	
//	public CommonRsHdr(){
//		this.StatusCode = "000000";
//		this.RqUID = KeyGenerator.getUUID();
//		this.ServerStatusCode = "SUCCESS";
//		this.CntId = "408008";
//		this.CompanyCode = "CN0019009";
//		this.NumTranCode="123123";
//	}

	@Override
	public String toString() {
		return "CommonLoanRs [StatusCode=" + StatusCode + ", ServerStatusCode="
				+ ServerStatusCode + ", RqUID=" + RqUID + ", SPRsUID="
				+ SPRsUID + ", NumTranCode=" + NumTranCode + ", CompanyCode="
				+ CompanyCode + ", CntId=" + CntId + "]";
	}
	
}
