package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyXdRq05002000001A01 
 * @Description: 05002000001国际结算业务维护		01国际业务撤销/闭卷/取消/冲账
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyXdRq05002000001A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 4686936419980938801L;
	
	private String ctrNo;		//合同号
	private String dbtNo;		//借据号
	private String bsnFlg;		//业务标志
	private double ovrdAmt;		//撤销金额
	private String oprInd;		//操作标志
	private String tlrID;		//柜员ID
	private String bsnTp;		//业务类型
	
	public EsbBodyXdRq05002000001A01(){
		
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

	public String getBsnFlg() {
		return bsnFlg;
	}

	@XmlElement(name = "BsnFlg")
	public void setBsnFlg(String bsnFlg) {
		this.bsnFlg = bsnFlg;
	}

	public double getOvrdAmt() {
		return ovrdAmt;
	}

	@XmlElement(name = "OvrdAmt")
	public void setOvrdAmt(double ovrdAmt) {
		this.ovrdAmt = ovrdAmt;
	}

	public String getOprInd() {
		return oprInd;
	}

	@XmlElement(name = "OprInd")
	public void setOprInd(String oprInd) {
		this.oprInd = oprInd;
	}

	public String getTlrID() {
		return tlrID;
	}

	@XmlElement(name = "TlrID")
	public void setTlrID(String tlrID) {
		this.tlrID = tlrID;
	}

	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	@Override
	public String toString() {
		return "EsbBodyXdRq05002000001A01 [ctrNo=" + ctrNo + ",dbtNo=" + dbtNo
				+ ",bsnFlg=" + bsnFlg + ",ovrdAmt=" + ovrdAmt + ",oprInd="
				+ oprInd + ",tlrID=" + tlrID + ",bsnTp=" + bsnTp + "]";
	}
}
