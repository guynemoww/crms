package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyEcifRqIdentInfArray 
 * @Description: 客户信息开户维护	证件信息数组
 *
 */
public class EsbBodyEcifRqIdentInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String idntCd;			//证件编号			String(16)		证件标识ID，后台返回，证件修改时，原值上传；新增为空
	private String idntTp;			//证件类型			String(10)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String identNo;			//证件号码			String(20)	N
	private String issuOffc;		//签发机关			String(100)	N
	private String sgnDt;			//签发日期			String(8)	N	YYYYMMDD
	private String identEfftEndDt;	//证件有效截止日期	String(8)	N	YYYYMMDD
	private String dfltIdentFlg;	//默认证件标志		String(3)	N	"1是0否-1未知"
	
	public EsbBodyEcifRqIdentInfArray(){
		
	}

	public String getIdntCd() {
		return idntCd;
	}

	@XmlElement(name = "IdntCd")
	public void setIdntCd(String idntCd) {
		this.idntCd = idntCd;
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

	public String getIssuOffc() {
		return issuOffc;
	}

	@XmlElement(name = "IssuOffc")
	public void setIssuOffc(String issuOffc) {
		this.issuOffc = issuOffc;
	}

	public String getSgnDt() {
		return sgnDt;
	}

	@XmlElement(name = "SgnDt")
	public void setSgnDt(String sgnDt) {
		this.sgnDt = sgnDt;
	}

	public String getIdentEfftEndDt() {
		return identEfftEndDt;
	}

	@XmlElement(name = "IdentEfftEndDt")
	public void setIdentEfftEndDt(String identEfftEndDt) {
		this.identEfftEndDt = identEfftEndDt;
	}

	public String getDfltIdentFlg() {
		return dfltIdentFlg;
	}

	@XmlElement(name = "DfltIdentFlg")
	public void setDfltIdentFlg(String dfltIdentFlg) {
		this.dfltIdentFlg = dfltIdentFlg;
	}
}
