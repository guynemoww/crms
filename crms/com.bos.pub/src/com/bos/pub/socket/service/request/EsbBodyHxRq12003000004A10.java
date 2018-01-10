package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq12003000004A10 
 * @Description: 12003000004账户信息查询		10账户业务数据信息查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq12003000004A10 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;		//交易码			String(6)	Y	7190
	private String rgonCd;		//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String branchId;	//机构代码		String(6)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String tranTellerNo;//交易柜员		String(30)	Y	
	private String terminalCode;//终端号			String(20)  Y
	private String cstAcctNo;	//客户账号		String(35)  Y

	public EsbBodyHxRq12003000004A10(){
		
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

	public String getBranchId() {
		return branchId;
	}

	@XmlElement(name = "BranchId")
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getTranTellerNo() {
		return tranTellerNo;
	}

	@XmlElement(name = "TranTellerNo")
	public void setTranTellerNo(String tranTellerNo) {
		this.tranTellerNo = tranTellerNo;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	@XmlElement(name = "TerminalCode")
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getCstAcctNo() {
		return cstAcctNo;
	}

	@XmlElement(name = "CstAcctNo")
	public void setCstAcctNo(String cstAcctNo) {
		this.cstAcctNo = cstAcctNo;
	}
}
