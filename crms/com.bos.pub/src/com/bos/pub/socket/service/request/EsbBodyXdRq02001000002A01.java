package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyXdRq02001000002A01
 * @Description: 02001000002贷款还款 01贸易融资还款
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyXdRq02001000002A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 5286588106549084922L;

	private String ctrNo; // 合同号
	private String dbtNo; // 借据号
	private String bsnTp; // 业务类型
	private String clrCtrInd; // 结清合同标志
	private String ccyTp; // 货币种类
	private double pymtAmt; // 还款金额
	private double dbtBal; // 借据余额
	private String instID; // 机构ID
	private String tlrID; // 柜员ID
	private String prePaidInd; // 垫款标志
	private String prePaidAcctNo; // 垫款账号
	private String prePaidCcy; // 垫款币种
	private double prePaidAmt; // 垫款金额

	public EsbBodyXdRq02001000002A01() {

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

	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getClrCtrInd() {
		return clrCtrInd;
	}

	@XmlElement(name = "ClrCtrInd")
	public void setClrCtrInd(String clrCtrInd) {
		this.clrCtrInd = clrCtrInd;
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public double getPymtAmt() {
		return pymtAmt;
	}

	@XmlElement(name = "PymtAmt")
	public void setPymtAmt(double pymtAmt) {
		this.pymtAmt = pymtAmt;
	}

	public double getDbtBal() {
		return dbtBal;
	}

	@XmlElement(name = "DbtBal")
	public void setDbtBal(double dbtBal) {
		this.dbtBal = dbtBal;
	}

	public String getInstID() {
		return instID;
	}

	@XmlElement(name = "InstID")
	public void setInstID(String instID) {
		this.instID = instID;
	}

	public String getTlrID() {
		return tlrID;
	}

	@XmlElement(name = "TlrID")
	public void setTlrID(String tlrID) {
		this.tlrID = tlrID;
	}

	public String getPrePaidInd() {
		return prePaidInd;
	}

	@XmlElement(name = "PrePaidInd")
	public void setPrePaidInd(String prePaidInd) {
		this.prePaidInd = prePaidInd;
	}

	public String getPrePaidAcctNo() {
		return prePaidAcctNo;
	}

	@XmlElement(name = "PrePaidAcctNo")
	public void setPrePaidAcctNo(String prePaidAcctNo) {
		this.prePaidAcctNo = prePaidAcctNo;
	}

	public String getPrePaidCcy() {
		return prePaidCcy;
	}

	@XmlElement(name = "PrePaidCcy")
	public void setPrePaidCcy(String prePaidCcy) {
		this.prePaidCcy = prePaidCcy;
	}

	public double getPrePaidAmt() {
		return prePaidAmt;
	}

	@XmlElement(name = "PrePaidAmt")
	public void setPrePaidAmt(double prePaidAmt) {
		this.prePaidAmt = prePaidAmt;
	}

	@Override
	public String toString() {
		return "EsbBodyXdRq02001000002A01 [ctrNo=" + ctrNo + ",dbtNo=" + dbtNo
				+ ",bsnTp=" + bsnTp + ",clrCtrInd="
				+ clrCtrInd + ",ccyTp=" + ccyTp + ",pymtAmt=" + pymtAmt
				+ ",dbtBal=" + dbtBal + ",instID=" + instID + ",tlrID=" + tlrID
				+ ",prePaidInd=" + prePaidInd + ",prePaidAcctNo="
				+ prePaidAcctNo + ",prePaidCcy=" + prePaidCcy + ",prePaidAmt="
				+ prePaidAmt + "]";
	}
}
