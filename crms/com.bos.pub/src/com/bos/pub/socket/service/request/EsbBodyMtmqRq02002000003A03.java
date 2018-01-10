package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyMtmqRq02002000003A03
 * @Description: 02002000003移动信贷公共管理 03催收登记信息提交
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyMtmqRq02002000003A03 extends EsbBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cstMgrNo; // String(8) Y 客户经理
	private String ittbrId; // String(10) Y 起始机构号
	private String crCstNo; // String(10) Y 客户代号
	private String collDt; // String(8) Y 催收日期
	private String collMth; // String(4) Y 催收方式
	private String collObjDsc; // String(200) Y催收对象
	private String tel; // String(50) N 电话号码
	private String collAdr; // String(120) N催收地址
	private String anlOpnnDsc; // String(500) Y分析意见
	private String collRcrdDsc; // String(500) Y催收记录
	private String imgBsnNo; // String(100) Y影像业务编号
	private List<EsbBodyMtmqRqCtrDbtInfArray> esbBodyMtmqRqCtrDbtInfArrays;

	public EsbBodyMtmqRq02002000003A03() {

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

	public String getCollDt() {
		return collDt;
	}

	@XmlElement(name = "CollDt")
	public void setCollDt(String collDt) {
		this.collDt = collDt;
	}

	public String getCollMth() {
		return collMth;
	}

	@XmlElement(name = "CollMth")
	public void setCollMth(String collMth) {
		this.collMth = collMth;
	}

	public String getCollObjDsc() {
		return collObjDsc;
	}

	@XmlElement(name = "CollObjDsc")
	public void setCollObjDsc(String collObjDsc) {
		this.collObjDsc = collObjDsc;
	}

	public String getTel() {
		return tel;
	}

	@XmlElement(name = "Tel")
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCollAdr() {
		return collAdr;
	}

	@XmlElement(name = "CollAdr")
	public void setCollAdr(String collAdr) {
		this.collAdr = collAdr;
	}

	public String getAnlOpnnDsc() {
		return anlOpnnDsc;
	}

	@XmlElement(name = "AnlOpnnDsc")
	public void setAnlOpnnDsc(String anlOpnnDsc) {
		this.anlOpnnDsc = anlOpnnDsc;
	}

	public String getCollRcrdDsc() {
		return collRcrdDsc;
	}

	@XmlElement(name = "CollRcrdDsc")
	public void setCollRcrdDsc(String collRcrdDsc) {
		this.collRcrdDsc = collRcrdDsc;
	}

	public String getImgBsnNo() {
		return imgBsnNo;
	}

	@XmlElement(name = "ImgBsnNo")
	public void setImgBsnNo(String imgBsnNo) {
		this.imgBsnNo = imgBsnNo;
	}

	public List<EsbBodyMtmqRqCtrDbtInfArray> getEsbBodyMtmqRqCtrDbtInfArrays() {
		return esbBodyMtmqRqCtrDbtInfArrays;
	}

	@XmlElement(name = "CtrDbtInfArray")
	public void setEsbBodyMtmqRqCtrDbtInfArrays(List<EsbBodyMtmqRqCtrDbtInfArray> esbBodyMtmqRqCtrDbtInfArrays) {
		this.esbBodyMtmqRqCtrDbtInfArrays = esbBodyMtmqRqCtrDbtInfArrays;
	}

}
