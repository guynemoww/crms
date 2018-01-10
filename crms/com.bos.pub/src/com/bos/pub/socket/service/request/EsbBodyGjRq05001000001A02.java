package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyGjRq05001000001A02 
 * @Description: 05001000001信用证维护	02信用证修改
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyGjRq05001000001A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ctrNo;	//合同号
	private String dbtNo;	//借据号
	private String lCNo;	//ECIF客户代号
	private String rmtInstNo;	//货币种类
	private String cstNo;	//申请金额
	private double afAmdtAmt;	//起始日
	private String newUpPct;	//到期日
	private String newDwPct;	//主要担保方式
	private String newEfftDt;	//汇款机构号
	private String newSpotFwdInd;	//即期远期标志
	private double newMrgnPct;	//上浮比例
	private String mrgnNum;	//保证金个数
	private List<EsbBodyGjRqMrgnArray> esbBodyGjRqMrgnArrays;//保证金数组

	public EsbBodyGjRq05001000001A02(){
		
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

	public String getlCNo() {
		return lCNo;
	}

	@XmlElement(name = "LCNo")
	public void setlCNo(String lCNo) {
		this.lCNo = lCNo;
	}

	public String getRmtInstNo() {
		return rmtInstNo;
	}

	@XmlElement(name = "RmtInstNo")
	public void setRmtInstNo(String rmtInstNo) {
		this.rmtInstNo = rmtInstNo;
	}

	public String getCstNo() {
		return cstNo;
	}

	@XmlElement(name = "CstNo")
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public double getAfAmdtAmt() {
		return afAmdtAmt;
	}

	@XmlElement(name = "AfAmdtAmt")
	public void setAfAmdtAmt(double afAmdtAmt) {
		this.afAmdtAmt = afAmdtAmt;
	}

	public String getNewUpPct() {
		return newUpPct;
	}

	@XmlElement(name = "NewUpPct")
	public void setNewUpPct(String newUpPct) {
		this.newUpPct = newUpPct;
	}

	public String getNewDwPct() {
		return newDwPct;
	}

	@XmlElement(name = "NewDwPct")
	public void setNewDwPct(String newDwPct) {
		this.newDwPct = newDwPct;
	}

	public String getNewEfftDt() {
		return newEfftDt;
	}

	@XmlElement(name = "NewEfftDt")
	public void setNewEfftDt(String newEfftDt) {
		this.newEfftDt = newEfftDt;
	}

	public String getNewSpotFwdInd() {
		return newSpotFwdInd;
	}

	@XmlElement(name = "NewSpotFwdInd")
	public void setNewSpotFwdInd(String newSpotFwdInd) {
		this.newSpotFwdInd = newSpotFwdInd;
	}

	public double getNewMrgnPct() {
		return newMrgnPct;
	}

	@XmlElement(name = "NewMrgnPct")
	public void setNewMrgnPct(double newMrgnPct) {
		this.newMrgnPct = newMrgnPct;
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
	public void setEsbBodyGjRqMrgnArrays(
			List<EsbBodyGjRqMrgnArray> esbBodyGjRqMrgnArrays) {
		this.esbBodyGjRqMrgnArrays = esbBodyGjRqMrgnArrays;
	}
}
