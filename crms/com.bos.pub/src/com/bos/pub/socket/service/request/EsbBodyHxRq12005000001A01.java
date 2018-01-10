package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq12005000001A01 
 * @Description: 12005000001对账文件传送		01日终对账
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq12005000001A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;			//交易码	String(6)	Y	"3184该交易直接从中间业务流水表里取记录生成"
	private String rgonCd;			//地区代码	String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String bsnTp;			//业务类型	String(10)	Y
	private String otptFile;		//输出文件	String(100)	Y	输出文件名称
	private String rgonStrg;		//地区串	String(100)	Y	一般业务直接赋值地区代号
	private String rcnclDt;			//对账日期	String(8)	Y
	private String oprInd;			//操作标志	String(4)	Y	"0,1-根据交易日期,分地区对账2-根据交易日不分地区对账3-根据提出日期分地区对账4-根据提出日期不分地区对账"
	
	public EsbBodyHxRq12005000001A01(){
		
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

	public String getOtptFile() {
		return otptFile;
	}

	@XmlElement(name = "OtptFile")
	public void setOtptFile(String otptFile) {
		this.otptFile = otptFile;
	}

	public String getRgonStrg() {
		return rgonStrg;
	}

	@XmlElement(name = "RgonStrg")
	public void setRgonStrg(String rgonStrg) {
		this.rgonStrg = rgonStrg;
	}

	public String getRcnclDt() {
		return rcnclDt;
	}

	@XmlElement(name = "RcnclDt")
	public void setRcnclDt(String rcnclDt) {
		this.rcnclDt = rcnclDt;
	}

	public String getOprInd() {
		return oprInd;
	}

	@XmlElement(name = "OprInd")
	public void setOprInd(String oprInd) {
		this.oprInd = oprInd;
	}
}
