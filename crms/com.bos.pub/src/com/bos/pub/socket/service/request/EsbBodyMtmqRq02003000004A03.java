package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02003000004A03
 * @Description: 02003000004信贷信息查询 03押品信息查询
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02003000004A03 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cstMgrNo;// 客户经理 String(8) Y
	private String ittbrId;// 起始机构号 String(10) Y
	private String plgTp;// 押品类型 String(4) Y
	private String plgNo;// 押品编号 String(32) Y

	public EsbBodyMtmqRq02003000004A03() {

	}

	public String getCstMgrNo() {
		return cstMgrNo;
	}

	@XmlElement(name = "CstMgrNo")
	public void setCstMgrNo(String cstMgrNo) {
		this.cstMgrNo = cstMgrNo;
	}

	public String getIttbrId() {
		return ittbrId;
	}

	@XmlElement(name = "IttbrId")
	public void setIttbrId(String ittbrId) {
		this.ittbrId = ittbrId;
	}

	public String getPlgTp() {
		return plgTp;
	}

	@XmlElement(name = "PlgTp")
	public void setPlgTp(String plgTp) {
		this.plgTp = plgTp;
	}

	public String getPlgNo() {
		return plgNo;
	}

	@XmlElement(name = "PlgNo")
	public void setPlgNo(String plgNo) {
		this.plgNo = plgNo;
	}

}
