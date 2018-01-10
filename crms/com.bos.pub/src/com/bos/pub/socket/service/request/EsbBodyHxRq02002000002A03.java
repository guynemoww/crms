package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq02002000002A03 
 * @Description: 02002000002信贷下柜登记撤销		03个人授信卡登记撤销
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq02002000002A03 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String fileInd;		//文件标志		String(1)	Y	"1-是0-否默认为0"
	private String fileNm;		//文件名		String(100)	N
	private String acctRgonCd;	//开户地区代号	String(2)	Y
	private String acctBrId;	//开户机构代号	String(6)	Y
	private String trdTp;		//交易种类		String(10)	Y	Z-个人授信卡下柜
	private String oprTp;		//操作类型		String(3)	Y	"0-下柜登记1-撤销下柜登记"
	private String loanTp;		//贷款种类		String(6)	Y
	private String complNo;		//下柜编号		String(14)	Y
	private String ctrNo;		//合同号		String(20)	Y
	private String cardNo;		//卡号		String(19)	Y
	private String ccyTp;		//货币种类		String(3)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private double ctrAmt;		//合同金额		Double(20,2)	Y
	private double txnAmt;		//交易金额		Double(20,2)	Y
	private String crExnTrm;	//授信期限		String(8)	Y
	private String ctrIttDt;	//合同起始日期	String(8)	Y
	private String ctrExpDt;	//合同到期日	String(8)	Y
	private String bsnExpDt;	//业务到期日	String(8)	Y
	private String intRatMd;	//利率方式		String(1)	Y
	private String fltIntRat;	//浮动利率		String(11)	Y
	private String intPnyRat;	//罚息率		String(11)	Y
	private String cardTp;		//卡类型		String(8)	Y
	
	public EsbBodyHxRq02002000002A03(){
		
	}

	public String getTxnCd() {
		return txnCd;
	}

	@XmlElement(name = "TxnCd")
	public void setTxnCd(String txnCd) {
		this.txnCd = txnCd;
	}

	public String getRgonCd() {
		return rgonCd;
	}

	@XmlElement(name = "RgonCd")
	public void setRgonCd(String rgonCd) {
		this.rgonCd = rgonCd;
	}

	public String getFileInd() {
		return fileInd;
	}

	@XmlElement(name = "FileInd")
	public void setFileInd(String fileInd) {
		this.fileInd = fileInd;
	}

	public String getFileNm() {
		return fileNm;
	}

	@XmlElement(name = "FileNm")
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
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

	public String getTrdTp() {
		return trdTp;
	}

	@XmlElement(name = "TrdTp")
	public void setTrdTp(String trdTp) {
		this.trdTp = trdTp;
	}

	public String getOprTp() {
		return oprTp;
	}

	@XmlElement(name = "OprTp")
	public void setOprTp(String oprTp) {
		this.oprTp = oprTp;
	}

	public String getLoanTp() {
		return loanTp;
	}

	@XmlElement(name = "LoanTp")
	public void setLoanTp(String loanTp) {
		this.loanTp = loanTp;
	}

	public String getComplNo() {
		return complNo;
	}

	@XmlElement(name = "ComplNo")
	public void setComplNo(String complNo) {
		this.complNo = complNo;
	}

	public String getCtrNo() {
		return ctrNo;
	}

	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	@XmlElement(name = "CardNo")
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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

	public double getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getCrExnTrm() {
		return crExnTrm;
	}

	@XmlElement(name = "CrExnTrm")
	public void setCrExnTrm(String crExnTrm) {
		this.crExnTrm = crExnTrm;
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

	public String getBsnExpDt() {
		return bsnExpDt;
	}

	@XmlElement(name = "BsnExpDt")
	public void setBsnExpDt(String bsnExpDt) {
		this.bsnExpDt = bsnExpDt;
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

	public String getIntPnyRat() {
		return intPnyRat;
	}

	@XmlElement(name = "IntPnyRat")
	public void setIntPnyRat(String intPnyRat) {
		this.intPnyRat = intPnyRat;
	}

	public String getCardTp() {
		return cardTp;
	}

	@XmlElement(name = "CardTp")
	public void setCardTp(String cardTp) {
		this.cardTp = cardTp;
	}
}
