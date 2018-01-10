package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyRzRq12002000012A01 
 * @Description: 12002000012员工信息验证		01员工编号信息查询验证
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyRzRq12002000012A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String empeNo;	//员工号
	
	public EsbBodyRzRq12002000012A01(){
		
	}

	public String getEmpeNo() {
		return empeNo;
	}

	@XmlElement(name = "EmpeNo")
	public void setEmpeNo(String empeNo) {
		this.empeNo = empeNo;
	}
}
