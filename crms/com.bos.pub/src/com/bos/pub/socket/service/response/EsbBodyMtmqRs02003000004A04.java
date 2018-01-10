package com.bos.pub.socket.service.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;
/**
 * 
 * @ClassName: EsbBodyMtmqRs02003000004A04
 * @Description: 02003000004信贷信息查询 04业务申请查询
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02003000004A04 extends EsbBody implements Serializable {

	private static final long serialVersionUID = 1L;
	private String returnCode;
	private String returnMsg;
	private String totRcrdNum;// 总记录数 String(10)
	private List<EsbBodyMtmqRsBsnInfArray> esbBodyMtmqRsBsnInfArrays;
	
	public EsbBodyMtmqRs02003000004A04() {
		
	}
	
	public List<EsbBodyMtmqRsBsnInfArray> getEsbBodyMtmqRsBsnInfArrays() {
		return esbBodyMtmqRsBsnInfArrays;
	}
	@XmlElement(name = "BsnInfArray")
	public void setEsbBodyMtmqRsBsnInfArrays(List<EsbBodyMtmqRsBsnInfArray> EsbBodyMtmqRsBsnInfArrays) {
		this.esbBodyMtmqRsBsnInfArrays = EsbBodyMtmqRsBsnInfArrays;
	}
	
	public String getReturnCode() {
		return returnCode;
	}
	@XmlElement(name = "ReturnCode")
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	public String getReturnMsg() {
		return returnMsg;
	}
	@XmlElement(name = "ReturnMsg")
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	public String getTotRcrdNum() {
		return totRcrdNum;
	}
	@XmlElement(name = "TotRcrdNum")
	public void setTotRcrdNum(String totRcrdNum) {
		this.totRcrdNum = totRcrdNum;
	}

}
