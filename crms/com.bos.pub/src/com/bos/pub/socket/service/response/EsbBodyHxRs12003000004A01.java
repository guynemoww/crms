package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRs12003000004A01 
 * @Description: 12003000004账户信息查询		01根据账号查询账户信息
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRs12003000004A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String repeatTms;	//重复次数		String(4)		成功返回字段
	private String tellerSeqNo;	//柜员流水号	String(8)		主机产生的柜员流水号，成功返回字段
	private String acctInd;		//账户代号		String(18)
	private String ccyTp;		//货币种类		String(3)
	private String smlNo;		//小序号		String(3)
	private String bsnTp;		//业务类型		String(10)
	private String bnkNtRmtInd;	//钞汇标志		String(2)
	private String cstNm;		//客户名称		String(50)
	private double acctBal;		//账户余额		Double(20,2)
	private double cntlBal;		//控制余额		Double(20,2)
	private double frzBal;		//冻结余额		Double(20,2)
	private double avlBal;		//可用余额		Double(20,2)
	private double txnAmt;		//交易金额		Double(20,2)
	private String identTp;		//证件种类		String(1)
	private String identNo;		//证件号码		String(20)
	private String ctcAdr;		//联系地址		String(30)
	private String ntwAdr;		//网络地址		String(30)
	private String tel1;		//电话号码1	String(50)
	private String pstCd;		//邮政编码		String(6)
	private String acctRgonCd;	//开户地区代号	String(2)
	private String acctBrId;	//开户机构代号	String(6)
	private String acctTp;		//账户类型		String(1)
	private String cstNo;		//客户代号		String(10)
	private String lcsNo;		//执照号码		String(20)
	private String entpCd;		//企业代号		String(20)
	private String rmk;			//备注		String(100)
	
	public EsbBodyHxRs12003000004A01(){
		
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

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public String getSmlNo() {
		return smlNo;
	}

	@XmlElement(name = "SmlNo")
	public void setSmlNo(String smlNo) {
		this.smlNo = smlNo;
	}

	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getBnkNtRmtInd() {
		return bnkNtRmtInd;
	}

	@XmlElement(name = "BnkNtRmtInd")
	public void setBnkNtRmtInd(String bnkNtRmtInd) {
		this.bnkNtRmtInd = bnkNtRmtInd;
	}

	public String getCstNm() {
		return cstNm;
	}

	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public double getAcctBal() {
		return acctBal;
	}

	@XmlElement(name = "AcctBal")
	public void setAcctBal(double acctBal) {
		this.acctBal = acctBal;
	}

	public double getCntlBal() {
		return cntlBal;
	}

	@XmlElement(name = "CntlBal")
	public void setCntlBal(double cntlBal) {
		this.cntlBal = cntlBal;
	}

	public double getFrzBal() {
		return frzBal;
	}

	@XmlElement(name = "FrzBal")
	public void setFrzBal(double frzBal) {
		this.frzBal = frzBal;
	}

	public double getAvlBal() {
		return avlBal;
	}

	@XmlElement(name = "AvlBal")
	public void setAvlBal(double avlBal) {
		this.avlBal = avlBal;
	}

	public double getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getIdentTp() {
		return identTp;
	}

	@XmlElement(name = "IdentTp")
	public void setIdentTp(String identTp) {
		this.identTp = identTp;
	}

	public String getIdentNo() {
		return identNo;
	}

	@XmlElement(name = "IdentNo")
	public void setIdentNo(String identNo) {
		this.identNo = identNo;
	}

	public String getCtcAdr() {
		return ctcAdr;
	}

	@XmlElement(name = "CtcAdr")
	public void setCtcAdr(String ctcAdr) {
		this.ctcAdr = ctcAdr;
	}

	public String getNtwAdr() {
		return ntwAdr;
	}

	@XmlElement(name = "NtwAdr")
	public void setNtwAdr(String ntwAdr) {
		this.ntwAdr = ntwAdr;
	}

	public String getTel1() {
		return tel1;
	}

	@XmlElement(name = "Tel1")
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getPstCd() {
		return pstCd;
	}

	@XmlElement(name = "PstCd")
	public void setPstCd(String pstCd) {
		this.pstCd = pstCd;
	}

	public String getAcctRgonCd() {
		return acctRgonCd;
	}

	@XmlElement(name = "AcctRgonCd")
	public void setAcctRgonCd(String acctRgonCd) {
		this.acctRgonCd = acctRgonCd;
	}

	public String getAcctBrId() {
		return acctBrId;
	}

	@XmlElement(name = "AcctBrId")
	public void setAcctBrId(String acctBrId) {
		this.acctBrId = acctBrId;
	}

	public String getAcctTp() {
		return acctTp;
	}

	@XmlElement(name = "AcctTp")
	public void setAcctTp(String acctTp) {
		this.acctTp = acctTp;
	}

	public String getCstNo() {
		return cstNo;
	}

	@XmlElement(name = "CstNo")
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public String getLcsNo() {
		return lcsNo;
	}

	@XmlElement(name = "LcsNo")
	public void setLcsNo(String lcsNo) {
		this.lcsNo = lcsNo;
	}

	public String getEntpCd() {
		return entpCd;
	}

	@XmlElement(name = "EntpCd")
	public void setEntpCd(String entpCd) {
		this.entpCd = entpCd;
	}

	public String getRmk() {
		return rmk;
	}

	@XmlElement(name = "Rmk")
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
}
