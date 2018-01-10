package com.bos.pub.socket.service.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs02003000004A05
 * @Description: 02003000004信贷信息查询 05合同信息查询
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02003000004A05 extends EsbBody implements Serializable {

	private static final long serialVersionUID = 1L;
	private String returnCode;
	private String returnMsg;
	private String totRcrdNum;// 总记录数 String(10)
	private List<EsbBodyMtmqRsCtrInfArray> esbBodyMtmqRsCtrInfArrays;
	
	public EsbBodyMtmqRs02003000004A05() {
		
	}
	
	public String getTotRcrdNum() {
		return totRcrdNum;
	}
	@XmlElement(name = "TotRcrdNum")
	public void setTotRcrdNum(String totRcrdNum) {
		this.totRcrdNum = totRcrdNum;
	}
	
	public List<EsbBodyMtmqRsCtrInfArray> getEsbBodyMtmqRsCtrInfArrays() {
		return esbBodyMtmqRsCtrInfArrays;
	}
	@XmlElement(name = "CtrInfArray")
	public void setEsbBodyMtmqRsCtrInfArrays(List<EsbBodyMtmqRsCtrInfArray> esbBodyMtmqRsCtrInfArrays) {
		this.esbBodyMtmqRsCtrInfArrays = esbBodyMtmqRsCtrInfArrays;
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
}
