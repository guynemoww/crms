package com.bos.pub.socket.service.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs02003000004A07
 * @Description: 02003000004信贷信息查询  07逾期借据信息查询
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02003000004A07 extends EsbBody implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String totRcrdNum;
	private List<EsbBodyMtmqRsyqSummaryArray> esbBodyMtmqRsyqSummaryArrays;

	
	public EsbBodyMtmqRs02003000004A07() {
		
	}


	public String getTotRcrdNum() {
		return totRcrdNum;
	}

	@XmlElement(name = "TotRcrdNum")

	public void setTotRcrdNum(String totRcrdNum) {
		this.totRcrdNum = totRcrdNum;
	}


	public List<EsbBodyMtmqRsyqSummaryArray> getEsbBodyMtmqRsyqSummaryArrays() {
		return esbBodyMtmqRsyqSummaryArrays;
	}

	@XmlElement(name = "DbtInfArray")

	public void setEsbBodyMtmqRsyqSummaryArrays(
			List<EsbBodyMtmqRsyqSummaryArray> esbBodyMtmqRsyqSummaryArrays) {
		this.esbBodyMtmqRsyqSummaryArrays = esbBodyMtmqRsyqSummaryArrays;
	}
	

	
	
}
