package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq12005000002A01 
 * @Description: 12005000002批量处理文件传送		01批量账务处理
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq12005000002A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码		String(6)	Y	3187
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String bsnTp;		//业务类型		String(10)	Y
	private String oprInd;		//操作标志		String(4)	Y	"0-全额扣款;2-适用理财"
	private String svcMth;		//服务方式		String(1)	Y	"0-处理完成后应答--同步模式1-先应答后处理这种情况当文件处理完成后会发起一个交易请求到前置--异步模式2-先应答后处理后可通过2385交易取回处理结果文件--异步模式"
	private String pfwDt;		//提出日期		String(8)	Y
	private String btchNo;		//批次号		String(4)	Y	每个业务类型当日唯一
	private String sysTrcNo;	//系统跟踪号	String(8)	Y
	private String tskNum;		//任务个数		String(10)	Y	文件汇总笔数
	private double txnAmt;		//交易金额		Double(20,2)	Y	文件汇总金额
	private String inptFile;	//输入文件		String(100)	Y	输入文件名称
	private String otptFile;	//输出文件		String(100)	Y	输出文件名称
	
	public EsbBodyHxRq12005000002A01(){
		
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

	public String getOprInd() {
		return oprInd;
	}

	@XmlElement(name = "OprInd")
	public void setOprInd(String oprInd) {
		this.oprInd = oprInd;
	}

	public String getSvcMth() {
		return svcMth;
	}

	@XmlElement(name = "SvcMth")
	public void setSvcMth(String svcMth) {
		this.svcMth = svcMth;
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

	public String getTskNum() {
		return tskNum;
	}

	@XmlElement(name = "TskNum")
	public void setTskNum(String tskNum) {
		this.tskNum = tskNum;
	}

	public double getTxnAmt() {
		return txnAmt;
	}

	@XmlElement(name = "TxnAmt")
	public void setTxnAmt(double txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getInptFile() {
		return inptFile;
	}

	@XmlElement(name = "InptFile")
	public void setInptFile(String inptFile) {
		this.inptFile = inptFile;
	}

	public String getOtptFile() {
		return otptFile;
	}

	@XmlElement(name = "OtptFile")
	public void setOtptFile(String otptFile) {
		this.otptFile = otptFile;
	}
}
