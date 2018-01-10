/**
 * 
 */
package com.bos.pub.socket.service.response;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs02003000004A02
 * @Description: 02003000004信贷信息查询 02项目信息查询
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02003000004A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String totRcrdNum;// 总记录数String(10)
	private List<EsbBodyMtmqRsPrjArray> esbBodyMtmqRsPrjArrays;

	public EsbBodyMtmqRs02003000004A02() {

	}



	public String getTotRcrdNum() {
		return totRcrdNum;
	}

	@XmlElement(name = "TotRcrdNum")
	public void setTotRcrdNum(String totRcrdNum) {
		this.totRcrdNum = totRcrdNum;
	}

	public List<EsbBodyMtmqRsPrjArray> getEsbBodyMtmqRsPrjArrays() {
		return esbBodyMtmqRsPrjArrays;
	}

	@XmlElement(name = "PrjInfArray")
	public void setEsbBodyMtmqRsPrjArrays(List<EsbBodyMtmqRsPrjArray> esbBodyMtmqRsPrjArrays) {
		this.esbBodyMtmqRsPrjArrays = esbBodyMtmqRsPrjArrays;
	}

}
