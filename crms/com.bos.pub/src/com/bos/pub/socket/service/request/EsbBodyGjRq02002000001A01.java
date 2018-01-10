package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyGjRq02002000001A01 
 * @Description: 02002000001贷款业务撤销		01信贷交易取消
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyGjRq02002000001A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String bsnTp;		//业务类型
	private String dbtNo;		//借据号
	private String ctrNo;		//合同号
	
	public EsbBodyGjRq02002000001A01(){
		
	}
	
	public String getBsnTp() {
		return bsnTp;
	}
	
	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
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
