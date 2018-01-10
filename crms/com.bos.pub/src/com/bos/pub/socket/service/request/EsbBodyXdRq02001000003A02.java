package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyXdRq02001000003A02
 * @Description: 02001000003贷款信息登记 02贷款放款通知
 * 
 */
@XmlRootElement(name = "BODY")
public class EsbBodyXdRq02001000003A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = -2721642574834526336L;

	private String ctrNo; // 合同号
	private String dbtNo; // 借据号
	private String bsnTp; // 业务类型
	private String rmtInstNo; // 汇款机构号
	private double txnAmt; // 交易金额
	private double dbtBal; // 借据余额
	private String lndAcctNo; // 放款账号
	private String pyeAcctNo; // 收款人账号

	public EsbBodyXdRq02001000003A02() {

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

	public String getRmtInstNo() {
		return rmtInstNo;
	}

	@XmlElement(name = "RmtInstNo")
	public void setRmtInstNo(String rmtInstNo) {
		this.rmtInstNo = rmtInstNo;
	}

	public double getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}

	public double getDbtBal() {
		return dbtBal;
	}

	@XmlElement(name = "DbtBal")
	public void setDbtBal(double dbtBal) {
		this.dbtBal = dbtBal;
	}

	public String getLndAcctNo() {
		return lndAcctNo;
	}

	@XmlElement(name = "LndAcctNo")
	public void setLndAcctNo(String lndAcctNo) {
		this.lndAcctNo = lndAcctNo;
	}

	public String getPyeAcctNo() {
		return pyeAcctNo;
	}

	@XmlElement(name = "PyeAcctNo")
	public void setPyeAcctNo(String pyeAcctNo) {
		this.pyeAcctNo = pyeAcctNo;
	}

	@Override
	public String toString() {
		return "EsbBodyXdRq02001000003A02 [ctrNo=" + ctrNo + ",dbtNo=" + dbtNo
				+ ",bsnTp=" + bsnTp + ",rmtInstNo=" + rmtInstNo + ",txnAmt="
				+ txnAmt + ",dbtBal=" + dbtBal + ",lndAcctNo=" + lndAcctNo
				+ ",pyeAcctNo=" + pyeAcctNo + "]";
	}
}
