package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyGJ12002000008A01 
 * @Description: 12002000008汇率牌价维护	01汇率获取
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyRs extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dbtNo;		//借据号
	private String ctrNo;		//合同号

	public EsbBodyRs(){
		
	}

	public String getDbtNo() {
		return dbtNo;
	}

	@XmlElement(name = "DbtNo")
	public void setDbtNo(String dbtNo) {
		this.dbtNo = dbtNo;
	}

	public String getCtrNo() {
		return ctrNo;
	}

	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}
}
