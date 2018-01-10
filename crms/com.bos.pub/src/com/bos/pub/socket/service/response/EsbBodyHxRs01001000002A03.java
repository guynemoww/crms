package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRs01001000002A03 
 * @Description: 01001000002通用核心记账		03表外账记账
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRs01001000002A03 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String repeatTms;	//重复次数		String(4)		前补空格
	private String tellerSeqNo;	//柜员流水号	String(8)		主机产生的柜员流水号，成功返回字段
	private String s9;			//串9		String(10)
	private String s10;			//串10		String(10)
	private String relAcctNo;	//关联账号		String(35)
	private String gdsNm;		//物品名称		String(12)
	private String ccyTp;		//货币种类		String(3)
	private double txnAmt;		//交易金额		Double(20,2)
	private String rmk;			//备注		String(100)
	private EsbBodyHxRsTskArray[] esbBodyHxRsTskArrays;//任务数组
	
	public EsbBodyHxRs01001000002A03(){
		
	}

	public String getRepeatTms() {
		return repeatTms;
	}

	@XmlElement(name = "RepeatTms")
	public void setRepeatTms(String repeatTms) {
		this.repeatTms = repeatTms;
	}

	public String getTellerSeqNo() {
		return tellerSeqNo;
	}

	@XmlElement(name = "TellerSeqNo")
	public void setTellerSeqNo(String tellerSeqNo) {
		this.tellerSeqNo = tellerSeqNo;
	}

	public String getS9() {
		return s9;
	}

	@XmlElement(name = "S9")
	public void setS9(String s9) {
		this.s9 = s9;
	}

	public String getS10() {
		return s10;
	}

	@XmlElement(name = "S10")
	public void setS10(String s10) {
		this.s10 = s10;
	}

	public String getRelAcctNo() {
		return relAcctNo;
	}

	@XmlElement(name = "RelAcctNo")
	public void setRelAcctNo(String relAcctNo) {
		this.relAcctNo = relAcctNo;
	}

	public String getGdsNm() {
		return gdsNm;
	}

	@XmlElement(name = "GdsNm")
	public void setGdsNm(String gdsNm) {
		this.gdsNm = gdsNm;
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public double getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getRmk() {
		return rmk;
	}

	@XmlElement(name = "Rmk")
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public EsbBodyHxRsTskArray[] getEsbBodyHxRsTskArrays() {
		return esbBodyHxRsTskArrays;
	}

	@XmlElement(name = "TskArray")
	public void setEsbBodyHxRsTskArrays(EsbBodyHxRsTskArray[] esbBodyHxRsTskArrays) {
		this.esbBodyHxRsTskArrays = esbBodyHxRsTskArrays;
	}
}
