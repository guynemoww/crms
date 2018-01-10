package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyRzRq12005000003A01 
 * @Description: 12005000003批量账户开户		01批量机构账户开户
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq12005000003A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
//	private String bsnTp;		//业务类型		String(10)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String sysTrcNo;	//系统跟踪号	String(8)	Y
	private String mdlTxnDt;	//中间交易日期	String(8)	Y	YYYYMMDD
	private String rcrdNum;		//记录数		String(10)	Y
	private String fileNm;		//文件名		String(100)	Y
	
	public EsbBodyHxRq12005000003A01(){
		
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

/*	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}*/

	public String getSysTrcNo() {
		return sysTrcNo;
	}

	@XmlElement(name = "SysTrcNo")
	public void setSysTrcNo(String sysTrcNo) {
		this.sysTrcNo = sysTrcNo;
	}

	public String getMdlTxnDt() {
		return mdlTxnDt;
	}

	@XmlElement(name = "MdlTxnDt")
	public void setMdlTxnDt(String mdlTxnDt) {
		this.mdlTxnDt = mdlTxnDt;
	}

	public String getRcrdNum() {
		return rcrdNum;
	}

	@XmlElement(name = "RcrdNum")
	public void setRcrdNum(String rcrdNum) {
		this.rcrdNum = rcrdNum;
	}

	public String getFileNm() {
		return fileNm;
	}

	@XmlElement(name = "FileNm")
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
}
