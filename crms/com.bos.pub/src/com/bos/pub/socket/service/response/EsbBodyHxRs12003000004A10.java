package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRs12003000004A10
 * @Description: 12003000004账户信息查询		10账户业务数据信息查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRs12003000004A10 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String repeatTms;	//重复次数		String(4)		成功返回字段
	private String tellerSeqNo;	//柜员流水号	String(8)		主机产生的柜员流水号，成功返回字段
	private String tranDate;	//交易日期	String(8)		 
	private String tranTime;	//交易时间	String(6)		 
	private String cstAcctNo;	//账户代号		String(18)
	private String rgonCd;		//开户地区代号	String(2)
	private String branchId;	//开户机构代号	String(6)
	private String ccyTp	;		//货币种类		String(10)
	private String acctCd;		//科目代码		String(6)
	private String bsnTp;		//业务类型		String(10)
	private String bnkNtRmtInd;	//钞汇标志		String(2)
	private String cstNm;		//客户名称		String(50)
	private double acctBal;		//账户余额		Double(20,2)
	private double cntlBal;		//控制余额		Double(20,2)
	private double frzBal;		//冻结余额		Double(20,2)
	private double odLmt;		//透支额度		Double(20,2)
	private double avlBal;		//可用余额		Double(20,2)
	private String storeCd;		//科目存储		String(1) 0-对公活期1-对私活期2-定期3-贷款4-内部 5-表外
	private String rcrdSt;		//账户状态		String(1)  0－删除  1－有效 2－销户 3－挂失 4－冻结 5－不动户		

	private String intRatCd;//利率代号	String(8)
	private String intRatMd;//利率方式	String(1)
	private String fltIntRat;//浮动利率	String(11)
	private String acctDt;//开户日期	String(8)		YYYYMMDD
	private String expDt	;//到期日	String(8)		YYYYMMDD

	public EsbBodyHxRs12003000004A10(){
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
		return this.acctBal-this.frzBal-this.cntlBal;
	}

	@XmlElement(name = "AvlBal")
	public void setAvlBal(double avlBal) {
		this.avlBal = avlBal;
	}

	public String getTranDate() {
		return tranDate;
	}

	@XmlElement(name = "TranDate")
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	@XmlElement(name = "TranTime")
	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getCstAcctNo() {
		return cstAcctNo;
	}

	@XmlElement(name = "CstAcctNo")
	public void setCstAcctNo(String cstAcctNo) {
		this.cstAcctNo = cstAcctNo;
	}

	public String getRgonCd() {
		return rgonCd;
	}

	@XmlElement(name = "RgonCd")
	public void setRgonCd(String rgonCd) {
		this.rgonCd = rgonCd;
	}

	public String getBranchId() {
		return branchId;
	}

	@XmlElement(name = "BranchId")
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "Ccy")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public String getAcctCd() {
		return acctCd;
	}

	@XmlElement(name = "AcctCd")
	public void setAcctCd(String acctCd) {
		this.acctCd = acctCd;
	}

	public double getOdLmt() {
		return odLmt;
	}

	@XmlElement(name = "OdLmt")
	public void setOdLmt(double odLmt) {
		this.odLmt = odLmt;
	}

	public String getStoreCd() {
		return storeCd;
	}

	@XmlElement(name = "StoreCd")
	public void setStoreCd(String storeCd) {
		this.storeCd = storeCd;
	}

	public String getRcrdSt() {
		return rcrdSt;
	}
	
	@XmlElement(name = "RcrdSt")
	public void setRcrdSt(String rcrdSt) {
		this.rcrdSt = rcrdSt;
	}

	public String getAcctDt() {
		return acctDt;
	}
	@XmlElement(name = "AcctDt")
	public void setAcctDt(String acctDt) {
		this.acctDt = acctDt;
	}

	public String getExpDt() {
		return expDt;
	}
	@XmlElement(name = "ExpDt")
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}
	
	public String getIntRatCd() {
		return intRatCd;
	}
	@XmlElement(name = "IntRatCd")
	public void setIntRatCd(String intRatCd) {
		this.intRatCd = intRatCd;
	}

	public String getIntRatMd() {
		return intRatMd;
	}
	@XmlElement(name = "IntRatMd")
	public void setIntRatMd(String intRatMd) {
		this.intRatMd = intRatMd;
	}

	public String getFltIntRat() {
		return fltIntRat;
	}
	@XmlElement(name = "FltIntRat")
	public void setFltIntRat(String fltIntRat) {
		this.fltIntRat = fltIntRat;
	}
}
