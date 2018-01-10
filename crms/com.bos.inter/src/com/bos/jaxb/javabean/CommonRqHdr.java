/**
 * BOSFXⅡ请求报文头
 */
package com.bos.jaxb.javabean;

import javax.xml.bind.annotation.XmlElement;
import com.bos.mq.util.KeyGenerator;


/**
 * @author Sunny
 * 
 */
public class CommonRqHdr {
	//服务提供者名称
	@XmlElement(name="SPName")
	public String sPName = "";
	//请求流水号
	@XmlElement(name="RqUID")
	public String rqUID = "";
	//数字交易码
	@XmlElement(name="NumTranCode")
	public String numTranCode = "";
	//清算日期
	@XmlElement(name="ClearDate")
	public String clearDate = "";
	//交易日期
	@XmlElement(name="TranDate")
	public String tranDate = "";
	//交易时间
	@XmlElement(name="TranTime")
	public String tranTime = "";
	//透明传输标志
	@XmlElement(name="DirectSendFlag")
	public String directSendFlag;
	//发起渠道号
	@XmlElement(name="ChannelId")
	public String channelId;
	//报文版本号
	@XmlElement(name="Version")
	public String version;
	//柜员号
	@XmlElement(name="CntId")
	public String cntId;
	//网点号
	@XmlElement(name="CompanyCode")
	public String companyCode;

//	public CommonRqHdr() {
//		this.rqUID = KeyGenerator.getUUID();
//		this.channelId = "S64";
//		this.cntId = "408008";
//		this.companyCode = "CN0019009";
//		this.clearDate = "20140425";
//		this.tranDate = "20140425";
//		this.tranTime = "185453";
////		this.sPName = "T24";
//		this.numTranCode="123123";
//	}
	

	/**
	 * @param channelId 要设置的 channelId
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}


	/**
	 * @param clearDate 要设置的 clearDate
	 */
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}


	/**
	 * @param cntId 要设置的 cntId
	 */
	public void setCntId(String cntId) {
		this.cntId = cntId;
	}


	/**
	 * @param companyCode 要设置的 companyCode
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}


	/**
	 * @param directSendFlag 要设置的 directSendFlag
	 */
	public void setDirectSendFlag(String directSendFlag) {
		this.directSendFlag = directSendFlag;
	}


	/**
	 * @param numTranCode 要设置的 numTranCode
	 */
	public void setNumTranCode(String numTranCode) {
		this.numTranCode = numTranCode;
	}


	/**
	 * @param rqUID 要设置的 rqUID
	 */
	public void setRqUID(String rqUID) {
		this.rqUID = rqUID;
	}


	/**
	 * @param name 要设置的 sPName
	 */
	public void setSPName(String name) {
		sPName = name;
	}


	/**
	 * @param tranDate 要设置的 tranDate
	 */
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}


	/**
	 * @param tranTime 要设置的 tranTime
	 */
	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}


	/**
	 * @param version 要设置的 version
	 */
	public void setVersion(String version) {
		this.version = version;
	}


	@Override
	public String toString() {
		return "CommonRqHdr [SPName=" + sPName + ", RqUID=" + rqUID
				+ ", NumTranCode=" + numTranCode + ", ClearDate=" + clearDate
				+ ", TranDate=" + tranDate + ", TranTime=" + tranTime
				+ ", DirectSendFlag=" + directSendFlag + ", ChannelId="
				+ channelId + ", Version=" + version + ", CntId=" + cntId
				+ ", CompanyCode=" + companyCode + "]";
	}
}
