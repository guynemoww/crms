package com.bos.pub.socket.service.response.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
* @ClassName: EsbAppHead 
* @Description: 应用头说明
*
 */
public class EsbAppHeadRs implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tranDate;		//交易日期			String	8	必输	YYYYMMDD
	private String tranTime;		//交易时间			String	6	必输	hhmmss
	private String backendSeqNo;	//服务提供方流水号	String	42	必输	6位系统ID+6位日期+16位序号
	private String backendSysId;	//服务提供方系统ID	String	10	必输	6位系统ID
	private String globalSeqNo;		//全局流水号		String	42	可选	6位系统ID+6位日期+16位序号
	private String returnCode;		//服务返回代码		String	12	必输	正常：000000000000异常：编码规则参见4.9章节
	private String returnMsg;		//服务返回信息		String	255	必输	正常：交易成功异常：提示信息
	private String langCode;		//用户语言			String	10	可选	CHINESE-中文（默认）
	
	public EsbAppHeadRs(){
		
	}

	public String getTranDate() {
		return tranDate;
	}

	@XmlElement(name = "TranDate")
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranTime() {
		return tranTime;
	}

	@XmlElement(name = "TranTime")
	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getBackendSeqNo() {
		return backendSeqNo;
	}

	@XmlElement(name = "BackendSeqNo")
	public void setBackendSeqNo(String backendSeqNo) {
		this.backendSeqNo = backendSeqNo;
	}

	public String getBackendSysId() {
		return backendSysId;
	}

	@XmlElement(name = "BackendSysId")
	public void setBackendSysId(String backendSysId) {
		this.backendSysId = backendSysId;
	}

	public String getGlobalSeqNo() {
		return globalSeqNo;
	}

	@XmlElement(name = "GlobalSeqNo")
	public void setGlobalSeqNo(String globalSeqNo) {
		this.globalSeqNo = globalSeqNo;
	}

	public String getReturnCode() {
		return returnCode;
	}

	@XmlElement(name = "ReturnCode")
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	@XmlElement(name = "ReturnMsg")
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	public String getLangCode() {
		return langCode;
	}

	@XmlElement(name = "LangCode")
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
}
