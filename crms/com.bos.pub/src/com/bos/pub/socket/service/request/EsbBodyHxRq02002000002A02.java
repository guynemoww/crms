package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq02002000002A02 
 * @Description: 02002000002信贷下柜登记撤销		02担保业务下柜登记撤销
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq02002000002A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String fileInd;		//文件标志		String(1)	Y	"1-是0-否默认为0"
	private String fileNm;		//文件名		String(100)	N
	private String acctRgonCd;	//开户地区代号	String(2)	Y
	private String acctBrId;	//开户机构代号	String(6)	Y
	private String trdTp;		//交易种类		String(10)	Y	B-担保业务下柜
	private String oprTp;		//操作类型		String(3)		"0-下柜登记1-撤销下柜登记"
	private String complNo;		//下柜编号		String(14)
	private String ctrNo;		//合同号		String(20)
	private String cstNo;		//客户代号		String(10)
	private String acctCd;		//科目代码		String(6)
	private String gryTp;		//担保类型		String(6)
	private String ccyTp;		//货币种类		String(3)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String stopDt;		//截止日期		String(8)
	private double txnAmt;		//交易金额		Double(20,2)
	
	public EsbBodyHxRq02002000002A02(){
		
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

	public String getGryTp() {
		return gryTp;
	}

	@XmlElement(name = "GryTp")
	public void setGryTp(String gryTp) {
		this.gryTp = gryTp;
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public String getStopDt() {
		return stopDt;
	}

	@XmlElement(name = "StopDt")
	public void setStopDt(String stopDt) {
		this.stopDt = stopDt;
	}

	public double getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}
}
