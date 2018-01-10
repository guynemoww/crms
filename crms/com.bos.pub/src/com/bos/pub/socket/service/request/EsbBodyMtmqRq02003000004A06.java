package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02003000004A06
 * @Description: 02003000004信贷信息查询    06日常检查信息列表查询
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02003000004A06 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cstMgrNo;// 客户经理 String(8)
	private String ittbrId;// 起始机构号 String(10)
	private String cstNm;// 客户名称	String(50)
	private String idntTp;//证件类型	String(10)
	private String identNo;// 证件号码	String(20)
	private String pgRcrdNum;// 每页记录数 String(10) Y 默认10条
	private String crnPgNo;// 当前页码 String(10) Y
	
	
	public String getPgRcrdNum() {
		return pgRcrdNum;
	}
	@XmlElement(name = "PgRcrdNum")
	public void setPgRcrdNum(String pgRcrdNum) {
		this.pgRcrdNum = pgRcrdNum;
	}

	public String getCrnPgNo() {
		return crnPgNo;
	}
	@XmlElement(name = "CrnPgNo")
	public void setCrnPgNo(String crnPgNo) {
		this.crnPgNo = crnPgNo;
	}

	public EsbBodyMtmqRq02003000004A06() {
		
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



	public String getCstNm() {
		return cstNm;
	}


	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
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
