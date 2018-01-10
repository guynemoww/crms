package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRs01001000002A05 
 * @Description: 01001000002通用核心记账		05中间业务多笔单边记账
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRs01001000002A05 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String repeatTms;	//重复次数		String(4)		成功返回字段	
	private String tellerSeqNo;	//柜员流水号	String(8)		主机产生的柜员流水号，成功返回字段	
	private String acctInd;		//账户代号		String(18)			
	private double txnAmt;		//交易金额		Double(20,2)			
	private double acctBal;		//账户余额		Double(20,2)			
	private String cstNm;		//客户名称		String(50)			
	private double oppAcctBal;	//对方账户余额	Double(20,2)			
	private String coNm;		//单位名称		String(100)			
	
	public EsbBodyHxRs01001000002A05(){
		
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

	public String getAcctInd() {
		return acctInd;
	}

	@XmlElement(name = "AcctInd")
	public void setAcctInd(String acctInd) {
		this.acctInd = acctInd;
	}

	public double getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}

	public double getAcctBal() {
		return acctBal;
	}

	@XmlElement(name = "AcctBal")
	public void setAcctBal(double acctBal) {
		this.acctBal = acctBal;
	}

	public String getCstNm() {
		return cstNm;
	}

	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public double getOppAcctBal() {
		return oppAcctBal;
	}

	@XmlElement(name = "OppAcctBal")
	public void setOppAcctBal(double oppAcctBal) {
		this.oppAcctBal = oppAcctBal;
	}

	public String getCoNm() {
		return coNm;
	}

	@XmlElement(name = "CoNm")
	public void setCoNm(String coNm) {
		this.coNm = coNm;
	}
}
