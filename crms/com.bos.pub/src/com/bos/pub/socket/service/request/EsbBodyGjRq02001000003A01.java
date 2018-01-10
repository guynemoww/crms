package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyGjRq02001000003A01 
 * @Description: 02001000003贷款信息登记		01贷款展期信息登记
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyGjRq02001000003A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ctrNo;			//合同号
	private String dbtNo;			//借据号
	private String aplyInstID;		//申请机构号
	private String rmtInstNo;		//汇款机构号
	private String cstNo;			//客户代号
	private String bsnTp;			//业务类型
	private String ccyTp;			//货币种类
	private double ctrAmt;			//合同金额
	private String ctrIttDt;		//合同起始日期
	private String ctrExpDt;		//合同到期日
	private String relCtrNo;		//关联合同号
	private String relDbtNo;		//关联借据号
	private double newIntRate;		//新利率
	private double newOdueIntRate;	//保新逾期利率
	
	public EsbBodyGjRq02001000003A01(){
		
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

	public String getAplyInstID() {
		return aplyInstID;
	}

	@XmlElement(name = "AplyInstID")
	public void setAplyInstID(String aplyInstID) {
		this.aplyInstID = aplyInstID;
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

	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public double getCtrAmt() {
		return ctrAmt;
	}

	@XmlElement(name = "CtrAmt")
	public void setCtrAmt(double ctrAmt) {
		this.ctrAmt = ctrAmt;
	}

	public String getCtrIttDt() {
		return ctrIttDt;
	}

	@XmlElement(name = "CtrIttDt")
	public void setCtrIttDt(String ctrIttDt) {
		this.ctrIttDt = ctrIttDt;
	}

	public String getCtrExpDt() {
		return ctrExpDt;
	}

	@XmlElement(name = "CtrExpDt")
	public void setCtrExpDt(String ctrExpDt) {
		this.ctrExpDt = ctrExpDt;
	}

	public String getRelCtrNo() {
		return relCtrNo;
	}

	@XmlElement(name = "RelCtrNo")
	public void setRelCtrNo(String relCtrNo) {
		this.relCtrNo = relCtrNo;
	}

	public String getRelDbtNo() {
		return relDbtNo;
	}

	@XmlElement(name = "RelDbtNo")
	public void setRelDbtNo(String relDbtNo) {
		this.relDbtNo = relDbtNo;
	}

	public double getNewIntRate() {
		return newIntRate;
	}

	@XmlElement(name = "NewIntRate")
	public void setNewIntRate(double newIntRate) {
		this.newIntRate = newIntRate;
	}

	public double getNewOdueIntRate() {
		return newOdueIntRate;
	}

	@XmlElement(name = "NewOdueIntRate")
	public void setNewOdueIntRate(double newOdueIntRate) {
		this.newOdueIntRate = newOdueIntRate;
	}
}
