package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq02002000002A01 
 * @Description: 02002000002信贷下柜登记撤销		01信贷业务下柜登记撤销
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq02002000002A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String fileInd;		//文件标志		String(1)	Y	"1-是0-否默认为0"
	private String fileNm;		//文件名		String(100)	N
	private String acctRgonCd;	//开户地区代号	String(2)	Y
	private String acctBrId;	//开户机构代号	String(6)	Y
	private String trdTp;		//交易种类		String(10)	Y	A-信贷业务下柜			
	private String oprTp;		//操作类型		String(3)	Y	"0-下柜登记1-撤销下柜登记"
	private String complNo;		//下柜编号		String(14)	Y
	private String ctrNo;		//合同号		String(20)	Y
	private String cstNo;		//客户代号		String(10)	Y
	private String acctCd;		//科目代码		String(6)	Y
	private String txnTp;		//交易类型		String(10)	Y
	private String ccyTp;		//货币种类		String(3)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private double ctrAmt;		//合同金额		Double(20,2)	Y
	private double txnAmt;		//交易金额		Double(20,2)	Y
	private String stopDt;		//截止日期		String(8)	Y
	private String ctrIttDt;	//合同起始日期	String(8)	Y
	private String ctrExpDt;	//合同到期日	String(8)	Y
	private String intRatCd;	//利率代号		String(8)	Y
	private String ratEfftDt;	//利率生效日	String(8)	Y
	private String intRatMd;	//利率方式		String(1)	Y
	private String fltIntRat;	//浮动利率		String(11)	Y
	private String moRat;		//月利率		String(11)	Y
	private String intPnyRat;	//罚息率		String(11)	Y
	private String intInd;		//计息标志		String(1)	Y	"0 计息标志：不计息1 计息标志：不分段计息 2 计息标志：分段计息3 计息标志：按旬计息4 (旧)计息标志：按月计息5 (旧)计息标志：按季计息6 (旧)计息标志：按半年浮动 
								//							7 (旧)计息标志：按年浮动 8 计息标志：特殊计息 9 计息标志:固定时点用于按揭类贷款(cgq) A 计息标志:按周分段计息"
	private String intPrd;		//计息周期		String(1)	Y
	private String moTrm;		//期限月		String(8)	Y
	private String dayTrm;		//期限日		String(8)	Y
	private String entpCd;		//企业代号		String(20)	Y
	private String blgAcctNo;	//结算账号		String(50)	Y
	
	public EsbBodyHxRq02002000002A01(){
		
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

	public String getCstNo() {
		return cstNo;
	}

	@XmlElement(name = "CstNo")
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public String getAcctCd() {
		return acctCd;
	}

	@XmlElement(name = "AcctCd")
	public void setAcctCd(String acctCd) {
		this.acctCd = acctCd;
	}

	public String getTxnTp() {
		return txnTp;
	}

	@XmlElement(name = "TxnTp")
	public void setTxnTp(String txnTp) {
		this.txnTp = txnTp;
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

	public String getStopDt() {
		return stopDt;
	}

	@XmlElement(name = "StopDt")
	public void setStopDt(String stopDt) {
		this.stopDt = stopDt;
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

	public String getIntRatCd() {
		return intRatCd;
	}

	@XmlElement(name = "IntRatCd")
	public void setIntRatCd(String intRatCd) {
		this.intRatCd = intRatCd;
	}

	public String getRatEfftDt() {
		return ratEfftDt;
	}

	@XmlElement(name = "RatEfftDt")
	public void setRatEfftDt(String ratEfftDt) {
		this.ratEfftDt = ratEfftDt;
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

	public String getMoRat() {
		return moRat;
	}

	@XmlElement(name = "MoRat")
	public void setMoRat(String moRat) {
		this.moRat = moRat;
	}

	public String getIntPnyRat() {
		return intPnyRat;
	}

	@XmlElement(name = "IntPnyRat")
	public void setIntPnyRat(String intPnyRat) {
		this.intPnyRat = intPnyRat;
	}

	public String getIntInd() {
		return intInd;
	}

	@XmlElement(name = "IntInd")
	public void setIntInd(String intInd) {
		this.intInd = intInd;
	}

	public String getIntPrd() {
		return intPrd;
	}

	@XmlElement(name = "IntPrd")
	public void setIntPrd(String intPrd) {
		this.intPrd = intPrd;
	}

	public String getMoTrm() {
		return moTrm;
	}

	@XmlElement(name = "MoTrm")
	public void setMoTrm(String moTrm) {
		this.moTrm = moTrm;
	}

	public String getDayTrm() {
		return dayTrm;
	}

	@XmlElement(name = "DayTrm")
	public void setDayTrm(String dayTrm) {
		this.dayTrm = dayTrm;
	}

	public String getEntpCd() {
		return entpCd;
	}

	@XmlElement(name = "EntpCd")
	public void setEntpCd(String entpCd) {
		this.entpCd = entpCd;
	}

	public String getBlgAcctNo() {
		return blgAcctNo;
	}

	@XmlElement(name = "BlgAcctNo")
	public void setBlgAcctNo(String blgAcctNo) {
		this.blgAcctNo = blgAcctNo;
	}
}
