package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq12005000002A02 
 * @Description: 12005000002批量处理文件传送	02批量运行结果查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq12005000002A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y	2385
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String bsnTp;		//业务类型		String(10)	Y
	private String pfwDt;		//提出日期		String(8)	Y		
	private String sysTrcNo;	//系统跟踪号	String(8)	Y
	private String btchNo;		//批次号		String(4)	Y	原交易批次号
	private String otptFile;	//输出文件		String(100)	Y	输出文件名称
	
	public EsbBodyHxRq12005000002A02(){
		
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

	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getPfwDt() {
		return pfwDt;
	}

	@XmlElement(name = "PfwDt")
	public void setPfwDt(String pfwDt) {
		this.pfwDt = pfwDt;
	}

	public String getBtchNo() {
		return btchNo;
	}

	@XmlElement(name = "BtchNo")
	public void setBtchNo(String btchNo) {
		this.btchNo = btchNo;
	}

	public String getSysTrcNo() {
		return sysTrcNo;
	}

	@XmlElement(name = "SysTrcNo")
	public void setSysTrcNo(String sysTrcNo) {
		this.sysTrcNo = sysTrcNo;
	}

	public String getOtptFile() {
		return otptFile;
	}

	@XmlElement(name = "OtptFile")
	public void setOtptFile(String otptFile) {
		this.otptFile = otptFile;
	}
}
