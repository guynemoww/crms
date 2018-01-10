package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyGjRq05001000001A01 
 * @Description: 05001000001信用证维护	01信用证开立
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyGjRq05001000001A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ctrNo;			//合同号
	private String dbtNo;			//借据号
	private String eCIFCstNo;		//ECIF客户代号
	private String ccyTp;			//货币种类
	private double aplyAmt;			//申请金额
	private String ittDt;			//起始日
	private String expDt;			//到期日
	private String mainGryTy;		//主要担保方式
	private String rmtInstNo;		//汇款机构号
	private String spotFwdInd;		//即期远期标志
	private String upPct;			//上浮比例
	private String dwPct;			//下浮比例
	private String benfNm;			//受益人名称
	private String lCEfftDt;		//信用证效期
	private String trdCtrNo;		//贸易合同编号
	private double trdCtrTotAmt;	//贸易合同总金额
	private double mrgnPct;			//保证金比例
	private String mrgnNum;			//保证金个数
	private List<EsbBodyGjRqMrgnArray> esbBodyGjRqMrgnArrays;//保证金数组

	public EsbBodyGjRq05001000001A01(){

	}

	public String getCtrNo() {
		return ctrNo;
	}

	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}

	public String getDbtNo() {
		return dbtNo;
	}

	@XmlElement(name = "DbtNo")
	public void setDbtNo(String dbtNo) {
		this.dbtNo = dbtNo;
	}

	public String geteCIFCstNo() {
		return eCIFCstNo;
	}

	@XmlElement(name = "ECIFCstNo")
	public void seteCIFCstNo(String eCIFCstNo) {
		this.eCIFCstNo = eCIFCstNo;
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public double getAplyAmt() {
		return aplyAmt;
	}

	@XmlElement(name = "AplyAmt")
	public void setAplyAmt(double aplyAmt) {
		this.aplyAmt = aplyAmt;
	}

	public String getIttDt() {
		return ittDt;
	}

	@XmlElement(name = "IttDt")
	public void setIttDt(String ittDt) {
		this.ittDt = ittDt;
	}

	public String getExpDt() {
		return expDt;
	}

	@XmlElement(name = "ExpDt")
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}

	public String getMainGryTy() {
		return mainGryTy;
	}

	@XmlElement(name = "MainGryTy")
	public void setMainGryTy(String mainGryTy) {
		this.mainGryTy = mainGryTy;
	}

	public String getRmtInstNo() {
		return rmtInstNo;
	}

	@XmlElement(name = "RmtInstNo")
	public void setRmtInstNo(String rmtInstNo) {
		this.rmtInstNo = rmtInstNo;
	}

	public String getSpotFwdInd() {
		return spotFwdInd;
	}

	@XmlElement(name = "SpotFwdInd")
	public void setSpotFwdInd(String spotFwdInd) {
		this.spotFwdInd = spotFwdInd;
	}

	public String getUpPct() {
		return upPct;
	}

	@XmlElement(name = "UpPct")
	public void setUpPct(String upPct) {
		this.upPct = upPct;
	}

	public String getDwPct() {
		return dwPct;
	}

	@XmlElement(name = "DwPct")
	public void setDwPct(String dwPct) {
		this.dwPct = dwPct;
	}

	public String getBenfNm() {
		return benfNm;
	}

	@XmlElement(name = "BenfNm")
	public void setBenfNm(String benfNm) {
		this.benfNm = benfNm;
	}

	public String getlCEfftDt() {
		return lCEfftDt;
	}

	@XmlElement(name = "LCEfftDt")
	public void setlCEfftDt(String lCEfftDt) {
		this.lCEfftDt = lCEfftDt;
	}

	public String getTrdCtrNo() {
		return trdCtrNo;
	}

	@XmlElement(name = "TrdCtrNo")
	public void setTrdCtrNo(String trdCtrNo) {
		this.trdCtrNo = trdCtrNo;
	}

	public double getTrdCtrTotAmt() {
		return trdCtrTotAmt;
	}

	@XmlElement(name = "TrdCtrTotAmt")
	public void setTrdCtrTotAmt(double trdCtrTotAmt) {
		this.trdCtrTotAmt = trdCtrTotAmt;
	}

	public double getMrgnPct() {
		return mrgnPct;
	}

	@XmlElement(name = "MrgnPct")
	public void setMrgnPct(double mrgnPct) {
		this.mrgnPct = mrgnPct;
	}

	public String getMrgnNum() {
		return mrgnNum;
	}

	@XmlElement(name = "MrgnNum")
	public void setMrgnNum(String mrgnNum) {
		this.mrgnNum = mrgnNum;
	}

	public List<EsbBodyGjRqMrgnArray> getEsbBodyGjRqMrgnArrays() {
		return esbBodyGjRqMrgnArrays;
	}

	@XmlElement(name = "MrgnArray")
	public void setEsbBodyGjRqMrgnArrays(List<EsbBodyGjRqMrgnArray> esbBodyGjRqMrgnArrays) {
		this.esbBodyGjRqMrgnArrays = esbBodyGjRqMrgnArrays;
	}
}
