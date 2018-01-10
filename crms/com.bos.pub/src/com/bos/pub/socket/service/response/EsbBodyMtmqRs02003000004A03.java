/**
 * 
 */
package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRs02003000004A03
 * @Description: 02003000004信贷信息查询 03押品信息查询
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRs02003000004A03 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String plgNo;// 押品编号 String(32)
	private String plgTp;// 押品类型 String(4)
	private String plgSt;// 押品状态 String(4)
	private String crtrNm;// 创建人名称 String(32)

	public EsbBodyMtmqRs02003000004A03() {

	}

	public String getPlgNo() {
		return plgNo;
	}

	@XmlElement(name = "PlgNo")
	public void setPlgNo(String plgNo) {
		this.plgNo = plgNo;
	}

	public String getPlgTp() {
		return plgTp;
	}

	@XmlElement(name = "PlgTp")
	public void setPlgTp(String plgTp) {
		this.plgTp = plgTp;
	}

	public String getPlgSt() {
		return plgSt;
	}

	@XmlElement(name = "PlgSt")
	public void setPlgSt(String plgSt) {
		this.plgSt = plgSt;
	}

	public String getCrtrNm() {
		return crtrNm;
	}

	@XmlElement(name = "CrtrNm")
	public void setCrtrNm(String crtrNm) {
		this.crtrNm = crtrNm;
	}

}
