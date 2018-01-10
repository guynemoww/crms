package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02003000004A04
 * @Description: 02003000004信贷信息查询 04业务申请查询
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02003000004A04 extends EsbBody implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cstMgrNo;// 客户经理 String(8) Y
	private String ittbrId;// 起始机构号 String(10) Y
	private String cstNm;// 客户名称 String(50) N 支持模糊查询
	private String idntTp;// 证件类型 String(10) N
	private String identNo;// 证件号码 String(20) N 当证件类型有值时，证件号码必须有值
	private String aplySt;// 申请状态 String(4) Y "01:未提交，02：审批中，03：生效多个用“,”隔开"
	private String pgRcrdNum;// 每页记录数 String(10) Y 默认10条
	private String crnPgNo;// 当前页码 String(10) Y
	
	public EsbBodyMtmqRq02003000004A04() {
		
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

	public String getAplySt() {
		return aplySt;
	}
	@XmlElement(name = "AplySt")
	public void setAplySt(String aplySt) {
		this.aplySt = aplySt;
	}

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
}
