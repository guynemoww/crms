/**
 * 
 */
package com.bos.pub.socket.service.response;

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
public class EsbBodyMtmqRs02003000003A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String crCstNo; // 客户代号 String(10)
	private String cstNm; // 客户名称 String(50)
	private String idntTp; // 证件类型 String(10)
	private String identNo; // 证件号码 String(20)
	private String cstTp; // 客户类型 String(2) 01对公，02自然人
	private String blcklistFlg; // 黑名单标识 String(4) 1是，0否
	private String crExnCstFlg; // 授信客户标识 String(4) 1是，0否
	private String blngCstMgrNo;// 管户客户经理 String(8)
	private String blngInstNo; // 管户机构号 String(10)
	private String blngInstNm; // 管户机构名称 String(100)
	private String dealPrsnPrvgCd; // 经办人员权限 String(4) 01管户权，02业务权，00无权限
	private String imgBsnNo; // 影像业务编号 String(100)

	public EsbBodyMtmqRs02003000003A01() {

	}

	public String getCrCstNo() {
		return crCstNo;
	}

	@XmlElement(name = "CrCstNo")
	public void setCrCstNo(String crCstNo) {
		this.crCstNo = crCstNo;
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

	public String getCstTp() {
		return cstTp;
	}

	@XmlElement(name = "CstTp")
	public void setCstTp(String cstTp) {
		this.cstTp = cstTp;
	}

	public String getBlcklistFlg() {
		return blcklistFlg;
	}

	@XmlElement(name = "BlcklistFlg")
	public void setBlcklistFlg(String blcklistFlg) {
		this.blcklistFlg = blcklistFlg;
	}

	public String getCrExnCstFlg() {
		return crExnCstFlg;
	}

	@XmlElement(name = "CrExnCstFlg")
	public void setCrExnCstFlg(String crExnCstFlg) {
		this.crExnCstFlg = crExnCstFlg;
	}

	public String getBlngCstMgrNo() {
		return blngCstMgrNo;
	}

	@XmlElement(name = "BlngCstMgrNo")
	public void setBlngCstMgrNo(String blngCstMgrNo) {
		this.blngCstMgrNo = blngCstMgrNo;
	}

	public String getBlngInstNo() {
		return blngInstNo;
	}

	@XmlElement(name = "BlngInstNo")
	public void setBlngInstNo(String blngInstNo) {
		this.blngInstNo = blngInstNo;
	}

	public String getBlngInstNm() {
		return blngInstNm;
	}

	@XmlElement(name = "BlngInstNm")
	public void setBlngInstNm(String blngInstNm) {
		this.blngInstNm = blngInstNm;
	}

	public String getDealPrsnPrvgCd() {
		return dealPrsnPrvgCd;
	}

	@XmlElement(name = "DealPrsnPrvgCd")
	public void setDealPrsnPrvgCd(String dealPrsnPrvgCd) {
		this.dealPrsnPrvgCd = dealPrsnPrvgCd;
	}

	public String getImgBsnNo() {
		return imgBsnNo;
	}

	@XmlElement(name = "ImgBsnNo")
	public void setImgBsnNo(String imgBsnNo) {
		this.imgBsnNo = imgBsnNo;
	}
}
