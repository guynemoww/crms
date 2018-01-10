package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02002000004A01
 * @Description: 02002000004信贷信息维护 01项目信息建立
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02002000004A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cstMgrNo;// 客户经理 String(8)
	private String ittbrId;// 起始机构号 String(10)
	private String crCstNo;// 客户代号 String(10)
	private String prjNm;// 项目名称 String(100)
	private String prjTp;// 项目类别 String(4)
	private String prjLvl;// 项目级别 String(4)
	private String prjAdr;// 项目地址 String(300)
	private String ccy;// 币种 String(10)
	private double prjTotIvsAmt;// 项目总投资金额 Double(20,2)
	private String prjFileNo;// 批准立项文件文号 String(20)
	private String prjReplyOffcNm;// 批准立项批复机关名称 String(100)
	private String prjStrtDt;// 项目开工日 String(8)
	private String prjEndDt;// 项目竣工日 String(8)
	private String statOwnLandUseRghtCtfNo;// 国有土地使用权证号 String(20)
	private String consLandUsePrmtNo;// 建设用地规划许可证号 String(20)
	private String consEngnPlnPrmtNo;// 建设工程规划许可证号 String(20)
	private String consEngnWrkPrmtNo;// 建设工程施工许可证号 String(20)
	
	private String imgTplCd;  //影像模板代码
	private String imgBsnNo;  //影像业务编号
	
	private EsbBodyMtmqRqEsttInf  esbBodyMtmqRqEsttInf;//房地产附属信息
	private EsbBodyMtmqRqLandBnkgInf  esbBodyMtmqRqLandBnkgInf;//土地储备附属信息
	
	public EsbBodyMtmqRq02002000004A01() {
		
	}
	

	public String getImgTplCd() {
		return imgTplCd;
	}
	@XmlElement(name = "ImgTplCd")
	public void setImgTplCd(String imgTplCd) {
		this.imgTplCd = imgTplCd;
	}

	public String getImgBsnNo() {
		return imgBsnNo;
	}
	@XmlElement(name = "ImgBsnNo")
	public void setImgBsnNo(String imgBsnNo) {
		this.imgBsnNo = imgBsnNo;
	}
	
	public EsbBodyMtmqRqEsttInf getEsbBodyMtmqRqEsttInf() {
		return esbBodyMtmqRqEsttInf;
	}
	@XmlElement(name = "EsttInf")
	public void setEsbBodyMtmqRqEsttInf(EsbBodyMtmqRqEsttInf esbBodyMtmqRqEsttInf) {
		this.esbBodyMtmqRqEsttInf = esbBodyMtmqRqEsttInf;
	}

	public EsbBodyMtmqRqLandBnkgInf getEsbBodyMtmqRqLandBnkgInf() {
		return esbBodyMtmqRqLandBnkgInf;
	}
	@XmlElement(name = "LandBnkgInf")
	public void setEsbBodyMtmqRqLandBnkgInf(EsbBodyMtmqRqLandBnkgInf esbBodyMtmqRqLandBnkgInf) {
		this.esbBodyMtmqRqLandBnkgInf = esbBodyMtmqRqLandBnkgInf;
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

	public String getPrjNm() {
		return prjNm;
	}
	@XmlElement(name = "PrjNm")
	public void setPrjNm(String prjNm) {
		this.prjNm = prjNm;
	}

	public String getPrjTp() {
		return prjTp;
	}
	@XmlElement(name = "PrjTp")
	public void setPrjTp(String prjTp) {
		this.prjTp = prjTp;
	}

	public String getPrjLvl() {
		return prjLvl;
	}
	@XmlElement(name = "PrjLvl")
	public void setPrjLvl(String prjLvl) {
		this.prjLvl = prjLvl;
	}

	public String getPrjAdr() {
		return prjAdr;
	}
	@XmlElement(name = "PrjAdr")
	public void setPrjAdr(String prjAdr) {
		this.prjAdr = prjAdr;
	}

	public String getCcy() {
		return ccy;
	}
	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public double getPrjTotIvsAmt() {
		return prjTotIvsAmt;
	}
	@XmlElement(name = "PrjTotIvsAmt")
	public void setPrjTotIvsAmt(double prjTotIvsAmt) {
		this.prjTotIvsAmt = prjTotIvsAmt;
	}

	public String getPrjFileNo() {
		return prjFileNo;
	}
	@XmlElement(name = "PrjFileNo")
	public void setPrjFileNo(String prjFileNo) {
		this.prjFileNo = prjFileNo;
	}

	public String getPrjReplyOffcNm() {
		return prjReplyOffcNm;
	}
	@XmlElement(name = "PrjReplyOffcNm")
	public void setPrjReplyOffcNm(String prjReplyOffcNm) {
		this.prjReplyOffcNm = prjReplyOffcNm;
	}

	public String getPrjStrtDt() {
		return prjStrtDt;
	}
	@XmlElement(name = "PrjStrtDt")
	public void setPrjStrtDt(String prjStrtDt) {
		this.prjStrtDt = prjStrtDt;
	}

	public String getPrjEndDt() {
		return prjEndDt;
	}
	@XmlElement(name = "PrjEndDt")
	public void setPrjEndDt(String prjEndDt) {
		this.prjEndDt = prjEndDt;
	}

	public String getStatOwnLandUseRghtCtfNo() {
		return statOwnLandUseRghtCtfNo;
	}
	@XmlElement(name = "StatOwnLandUseRghtCtfNo")
	public void setStatOwnLandUseRghtCtfNo(String statOwnLandUseRghtCtfNo) {
		this.statOwnLandUseRghtCtfNo = statOwnLandUseRghtCtfNo;
	}

	public String getConsLandUsePrmtNo() {
		return consLandUsePrmtNo;
	}
	@XmlElement(name = "ConsLandUsePrmtNo")
	public void setConsLandUsePrmtNo(String consLandUsePrmtNo) {
		this.consLandUsePrmtNo = consLandUsePrmtNo;
	}

	public String getConsEngnPlnPrmtNo() {
		return consEngnPlnPrmtNo;
	}
	@XmlElement(name = "ConsEngnPlnPrmtNo")
	public void setConsEngnPlnPrmtNo(String consEngnPlnPrmtNo) {
		this.consEngnPlnPrmtNo = consEngnPlnPrmtNo;
	}

	public String getConsEngnWrkPrmtNo() {
		return consEngnWrkPrmtNo;
	}
	@XmlElement(name = "ConsEngnWrkPrmtNo")
	public void setConsEngnWrkPrmtNo(String consEngnWrkPrmtNo) {
		this.consEngnWrkPrmtNo = consEngnWrkPrmtNo;
	}
	
}
