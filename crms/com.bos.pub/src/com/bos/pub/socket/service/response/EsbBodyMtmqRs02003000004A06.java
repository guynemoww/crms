package com.bos.pub.socket.service.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.EsbBodyMtmqRqPftStmtArray;
import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs02003000004A06
 * @Description: 02003000004信贷信息查询 06日常检查信息列表查询
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02003000004A06 extends EsbBody implements Serializable {

	private static final long serialVersionUID = 1L;
	private String totRcrdNum;// 总记录数
	private List<EsbBodyMtmqRsdaycheckArray> esbBodyMtmqRsdaycheckArrays;

	public EsbBodyMtmqRs02003000004A06() {

	}

	public String getTotRcrdNum() {
		return totRcrdNum;
	}

	@XmlElement(name = "TotRcrdNum")
	public void setTotRcrdNum(String totRcrdNum) {
		this.totRcrdNum = totRcrdNum;
	}

	public List<EsbBodyMtmqRsdaycheckArray> getEsbBodyMtmqRsdaycheckArrays() {
		return esbBodyMtmqRsdaycheckArrays;
	}

	@XmlElement(name = "TskArray")
	public void setEsbBodyMtmqRsdaycheckArrays(List<EsbBodyMtmqRsdaycheckArray> esbBodyMtmqRsdaycheckArrays) {
		this.esbBodyMtmqRsdaycheckArrays = esbBodyMtmqRsdaycheckArrays;
	}

}
