package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq12005000002A04 
 * @Description: 12005000002批量账务处理		04批量入账结果查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq12005000002A04 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String sysTrcNo;	//系统跟踪号	String(8)	Y
	private String fileNm;		//文件名		String(100)	Y	"信贷：XD+8位日期+X+PLRZ+文件标识3位+5位序号.i	直销银行：ZX++8位日期+X+PLRZ+ 5位序号.i"
	private String sysDt;		//系统日期		String(8)	Y	YYYYMMDD

	public EsbBodyHxRq12005000002A04(){
		
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

	@XmlElement(name = "SysTrcNo")
	public void setSysTrcNo(String sysTrcNo) {
		this.sysTrcNo = sysTrcNo;
	}

	public String getFileNm() {
		return fileNm;
	}

	@XmlElement(name = "FileNm")
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getSysDt() {
		return sysDt;
	}

	@XmlElement(name = "SysDt")
	public void setSysDt(String sysDt) {
		this.sysDt = sysDt;
	}
}
