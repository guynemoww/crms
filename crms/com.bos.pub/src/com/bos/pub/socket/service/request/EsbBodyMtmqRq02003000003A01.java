package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02003000003A01 
 * @Description: 02003000003客户授信信息查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02003000003A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cstMgrNo;	//客户经理	String(8)	Y	
	private String cstMgrInstNo;	//客户经理机构号	String(10)	Y	
	private String idntTp;	//证件类型	String(10)	Y	"101 身份证 102 临时身份证 110 户口簿 121 士兵证 122 军官证 123 解放军文职干部证 132 警官证 140 护照 150 港澳居民来往内地通行证 151 台湾同胞来往内地通行证 153 外国人居留证 199 其他证件 201 营业执照 202 组织机构代码证"
	private String identNo;	//证件号码	String(20)	Y	
	
	
	
	public EsbBodyMtmqRq02003000003A01() {
		
	}

	public String getCstMgrNo() {
		return cstMgrNo;
	}
	@XmlElement(name = "CstMgrNo")
	public void setCstMgrNo(String cstMgrNo) {
		this.cstMgrNo = cstMgrNo;
	}
	
	public String getCstMgrInstNo() {
		return cstMgrInstNo;
	}
	@XmlElement(name = "CstMgrInstNo")
	public void setCstMgrInstNo(String cstMgrInstNo) {
		this.cstMgrInstNo = cstMgrInstNo;
	}

	public String getIdntTp() {
		return idntTp;
	}
	@XmlElement(name = "IdntTp")
	public void setIdntTp(String idntTp) {
		this.idntTp = idntTp;
	}

	public String getIdentNo() {
		return identNo;
	}
	@XmlElement(name = "IdentNo")
	public void setIdentNo(String identNo) {
		this.identNo = identNo;
	}
}
