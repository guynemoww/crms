package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02002000003A01 
 * @Description: 02002000003移动信贷公共管理
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02002000003A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cstMgrNo;		//客户经理
	private String ittbrId;			//起始机构号
	private String crCstNo;			//客户代号
	private String bsnNo;			//业务号码
	private String aplyBsnTp;		//申请业务类型
	private String bsnKnd;			//业务性质
	private String bsnAplyImgNo;	//业务申请影像编号
	private String ctrAplyImgNo;	//合同申请影像编号
	
	
	
	public EsbBodyMtmqRq02002000003A01() {
		
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
	
	public String getCrCstNo() {
		return crCstNo;
	}
	@XmlElement(name = "CrCstNo")
	public void setCrCstNo(String crCstNo) {
		this.crCstNo = crCstNo;
	}

	public String getBsnNo() {
		return bsnNo;
	}

	@XmlElement(name = "BsnNo")
	public void setBsnNo(String bsnNo) {
		this.bsnNo = bsnNo;
	}

	public String getAplyBsnTp() {
		return aplyBsnTp;
	}

	@XmlElement(name = "AplyBsnTp")
	public void setAplyBsnTp(String aplyBsnTp) {
		this.aplyBsnTp = aplyBsnTp;
	}

	

	public String getBsnKnd() {
		return bsnKnd;
	}

	@XmlElement(name = "BsnKnd")
	public void setBsnKnd(String bsnKnd) {
		this.bsnKnd = bsnKnd;
	}

	public String getBsnAplyImgNo() {
		return bsnAplyImgNo;
	}

	@XmlElement(name = "BsnAplyImgNo")
	public void setBsnAplyImgNo(String bsnAplyImgNo) {
		this.bsnAplyImgNo = bsnAplyImgNo;
	}

	public String getCtrAplyImgNo() {
		return ctrAplyImgNo;
	}

	@XmlElement(name = "CtrAplyImgNo")
	public void setCtrAplyImgNo(String ctrAplyImgNo) {
		this.ctrAplyImgNo = ctrAplyImgNo;
	}
}
