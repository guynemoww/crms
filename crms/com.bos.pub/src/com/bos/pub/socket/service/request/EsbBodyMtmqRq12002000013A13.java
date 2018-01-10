package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq12002000013A13 
 * @Description: 12002000013客户信息开户维护     13对公客户财报信息维护				
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq12002000013A13 extends EsbBody implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cstMgrNo	 		;//	客户经理	String(8)	Y	
	private String ittbrId			;//	起始机构号	String(10)	Y	
	private String crCstNo				;//	客户代号	String(10)	Y	
	private String fncRptDt			;//	财务报表日期	String(8)	Y	YYYYMMDD
	private String fncRptTp			;//	财务报表类型	String(4)	Y	"1 年报 2 半年报 3 季报 4 月报"
	private String fncRptClbrCd	;//	财务报表口径	String(4)	Y	"1 合并 2 单一"
	private String fncRptCstTp	;//	财务报表客户类别	String(4)	Y	"2 企业类（新会计准则） 4 一般事业单位类（新会计准则） 6 担保公司（新会计准则） 7 证券公司类（新会计准则）8 保险公司类（新会计准则） 9 银行类（新会计准则）11 其他非银行金融机构（新会计准则）12 事业单位医院类（新会计准则）13 事业单位高校类（新会计准则）14 事业单位中小学类（新会计准则）"
	private String ccy					;//	币种	String(10)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String fncRptAdjFlg	;//	财报调整标识	String(4)	Y	"1 是 0 否"
	private String adjRsn				;//	调整原因	String(4000)	N	当是否经过调整为“是”时，则必输
	private String audtFlg			;//	审计标识	String(4)	Y	"1 是 0 否"
	private String audtFrmNm		;//	审计事务所名称	String(100)	N	当是否经过审计为“是”时，则必输
	
	private String imgTplCd;  //影像模板代码
	private String imgBsnNo;  //影像业务编号
	
	private List<EsbBodyMtmqRqPftStmtArray> esbBodyMtmqRsPftStmtArrays;
	private List<EsbBodyMtmqRqCashFlowStmtArray> esbBodyMtmqRsCashFlowStmtArrays;
	private List<EsbBodyMtmqRqBalShetArray> esbBodyMtmqRqBalShetArrays;
	
	
	
	public EsbBodyMtmqRq12002000013A13() {
		
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
	
	public List<EsbBodyMtmqRqBalShetArray> getEsbBodyMtmqRqBalShetArrays() {
		return esbBodyMtmqRqBalShetArrays;
	}
	@XmlElement(name = "BalShetArray")
	public void setEsbBodyMtmqRqBalShetArrays(List<EsbBodyMtmqRqBalShetArray> esbBodyMtmqRqBalShetArrays) {
		this.esbBodyMtmqRqBalShetArrays = esbBodyMtmqRqBalShetArrays;
	}

	public List<EsbBodyMtmqRqCashFlowStmtArray> getEsbBodyMtmqRsCashFlowStmtArrays() {
		return esbBodyMtmqRsCashFlowStmtArrays;
	}
	@XmlElement(name = "CashFlowStmtArray")
	public void setEsbBodyMtmqRsCashFlowStmtArrays(
			List<EsbBodyMtmqRqCashFlowStmtArray> esbBodyMtmqRsCashFlowStmtArrays) {
		this.esbBodyMtmqRsCashFlowStmtArrays = esbBodyMtmqRsCashFlowStmtArrays;
	}
	public List<EsbBodyMtmqRqPftStmtArray> getEsbBodyMtmqRsPftStmtArrays() {
		return esbBodyMtmqRsPftStmtArrays;
	}
	@XmlElement(name = "PftStmtArray")
	public void setEsbBodyMtmqRsPftStmtArrays(
			List<EsbBodyMtmqRqPftStmtArray> esbBodyMtmqRsPftStmtArrays) {
		this.esbBodyMtmqRsPftStmtArrays = esbBodyMtmqRsPftStmtArrays;
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
	
	public String getFncRptDt() {
		return fncRptDt;
	}
	@XmlElement(name = "FncRptDt")
	public void setFncRptDt(String fncRptDt) {
		this.fncRptDt = fncRptDt;
	}
	
	public String getFncRptTp() {
		return fncRptTp;
	}
	@XmlElement(name = "FncRptTp")
	public void setFncRptTp(String fncRptTp) {
		this.fncRptTp = fncRptTp;
	}
	
	public String getFncRptClbrCd() {
		return fncRptClbrCd;
	}
	@XmlElement(name = "FncRptClbrCd")
	public void setFncRptClbrCd(String fncRptClbrCd) {
		this.fncRptClbrCd = fncRptClbrCd;
	}
	
	public String getFncRptCstTp() {
		return fncRptCstTp;
	}
	@XmlElement(name = "FncRptCstTp")
	public void setFncRptCstTp(String fncRptCstTp) {
		this.fncRptCstTp = fncRptCstTp;
	}
	
	public String getCcy() {
		return ccy;
	}
	@XmlElement(name = "Ccy")
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	
	public String getFncRptAdjFlg() {
		return fncRptAdjFlg;
	}
	@XmlElement(name = "FncRptAdjFlg")
	public void setFncRptAdjFlg(String fncRptAdjFlg) {
		this.fncRptAdjFlg = fncRptAdjFlg;
	}
	
	public String getAdjRsn() {
		return adjRsn;
	}
	@XmlElement(name = "AdjRsn")
	public void setAdjRsn(String adjRsn) {
		this.adjRsn = adjRsn;
	}
	
	public String getAudtFlg() {
		return audtFlg;
	}
	@XmlElement(name = "AudtFlg")
	public void setAudtFlg(String audtFlg) {
		this.audtFlg = audtFlg;
	}
	
	public String getAudtFrmNm() {
		return audtFrmNm;
	}
	@XmlElement(name = "AudtFrmNm")
	public void setAudtFrmNm(String audtFrmNm) {
		this.audtFrmNm = audtFrmNm;
	}
}
