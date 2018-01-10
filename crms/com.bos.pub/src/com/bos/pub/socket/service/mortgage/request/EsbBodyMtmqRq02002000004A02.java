package com.bos.pub.socket.service.mortgage.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02002000004A02 
 * @Description: 02002000004信贷信息维护
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02002000004A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cstMgrNo;     					//客户经理                String(8)     Y
	private String ittbrId;							//起始机构号           String(10)    Y
	private String crCstNo;							//客户代号               String(10)    Y
	private String cltzTp;							//抵质押类型
	private String plgTp;							//押品类型               String(4)     Y
	private double mrtgValAmt;						//权利价值               Double(26,8)  Y
	private String plgDtlInf;						//押品详细信息        String(16000) Y
	
	private String imgTplCd;  //影像模板代码
	private String imgBsnNo;  //影像业务编号
	
//	private List<EsbBodyWmaRqEsttInfArray> esbBodyWmaRqEsttInfArrays;
//	private List<EsbBodyWmaRqConsInPrgsInfArray> esbBodyWmaRqConsInPrgsInfArrays;
//	private List<EsbBodyWmaRqLandUseRghtInfArray> esbBodyWmaRqLandUseRghtInfArrays;
//	private List<EsbBodyWmaRqVhclInfArray> esbBodyWmaRqVhclInfArrays;
//	private List<EsbBodyWmaRqMchnInfArray> esbBodyWmaRqMchnInfArrays;
//	private List<EsbBodyWmaRqInvtInfArray> esbBodyWmaRqInvtInfArrays;
//	private List<EsbBodyWmaRqOthrMrtgAstInfArray> esbBodyWmaRqOthrMrtgAstInfArrays;
//	private List<EsbBodyWmaRqOthrPlgAstInfArray> esbBodyWmaRqOthrPlgAstInfArrays;
//	private List<EsbBodyWmaRqDepRecptInfArray> esbBodyWmaRqDepRecptInfArrays;
//	private List<EsbBodyWmaRqBondInfArray> esbBodyWmaRqBondInfArrays;
//	private List<EsbBodyWmaRqStkRghtInfArray> esbBodyWmaRqStkRghtInfArrays;
//	private List<EsbBodyWmaRqStkInfArray> esbBodyWmaRqStkInfArrays;
//	private List<EsbBodyWmaRqFndInfArray> esbBodyWmaRqFndInfArrays;
//	private List<EsbBodyWmaRqFncPdInfArray> esbBodyWmaRqFncPdInfArrays;
//	private List<EsbBodyWmaRqBillInfArray> esbBodyWmaRqBillInfArrays;
//	private List<EsbBodyWmaRqRcvbInfArray> esbBodyWmaRqRcvbInfArrays;
//	private List<EsbBodyWmaRqExprtRbtInfArray> esbBodyWmaRqExprtRbtInfArrays;
//	private List<EsbBodyWmaRqCrgWrntInfArray> esbBodyWmaRqCrgWrntInfArrays;
//	private List<EsbBodyWmaRqCrgLadBillInfArray> esbBodyWmaRqCrgLadBillInfArrays;
//	private List<EsbBodyWmaRqIntPtyInfArray> esbBodyWmaRqIntPtyInfArrays;
//	private List<EsbBodyWmaRqLandCtrMgtRghtInfArray> esbBodyWmaRqLandCtrMgtRghtInfArrays;
//	private List<EsbBodyWmaRqFrchsRghtInfArray> esbBodyWmaRqFrchsRghtInfArrays;
//	private List<EsbBodyWmaRqOthrPftRghtInfArray> esbBodyWmaRqOthrPftRghtInfArrays;
//	private List<EsbBodyWmaRqFrstRghtInfArray> esbBodyWmaRqFrstRghtInfArrays;
//	private List<EsbBodyWmaRqRoadBrdgChrgRghtInfArray> esbBodyWmaRqRoadBrdgChrgRghtInfArrays;
	

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
	
	public String getPlgDtlInf(){
		return plgDtlInf;
	}
	@XmlElement(name = "PlgDtlInf")
	public void setPlgDtlInf(String plgDtlInf){
		this.plgDtlInf = plgDtlInf;
	}
	public String getCltzTp() {
		return cltzTp;
	}
	@XmlElement(name = "CltzTp")
	public void setCltzTp(String cltzTp) {
		this.cltzTp = cltzTp;
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
	
	public String getPlgTp() {
		return plgTp;
	}
	@XmlElement(name = "PlgTp")
	public void setPlgTp(String plgTp) {
		this.plgTp = plgTp;
	}
	public double getMrtgValAmt() {
		return mrtgValAmt;
	}
	@XmlElement(name = "MrtgValAmt")
	public void setMrtgValAmt(double mrtgValAmt) {
		this.mrtgValAmt = mrtgValAmt;
	}
}
