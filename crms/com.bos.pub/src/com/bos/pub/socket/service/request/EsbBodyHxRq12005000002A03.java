package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyRzRq12005000002A03 
 * @Description: 12005000002批量账务处理		03批量入账请求
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq12005000002A03 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
//	private String bsnTp;		//业务类型		String(10)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String sysTrcNo;	//系统跟踪号	String(8)	Y
	private String rcrdNum;		//记录数		String(10)	Y	文件汇总笔数
	private String fileNm;		//文件名		String(100)	Y	输入文件名称
	
	public EsbBodyHxRq12005000002A03(){
		
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

	public String getSysTrcNo() {
		return sysTrcNo;
	}
	
/*	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}
*/
	@XmlElement(name = "SysTrcNo")
	public void setSysTrcNo(String sysTrcNo) {
		this.sysTrcNo = sysTrcNo;
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
