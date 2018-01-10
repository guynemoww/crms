package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyEcifRs12002000013A02 
 * @Description: 12002000013客户信息开户维护	02个人客户关键信息维护
 * 				 12002000013客户信息开户维护	03个人客户基本信息维护
 * 				 12002000013客户信息开户维护	05公司客户关键信息维护
 * 				 12002000013客户信息开户维护	06公司客户基本信息维护
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyEcifRs12002000013A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cstNo;		//客户代号		String(10)	

	public EsbBodyEcifRs12002000013A02(){
		
	}

	public String getCstNo() {
		return cstNo;
	}

	@XmlElement(name = "CstNo")
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}
}
