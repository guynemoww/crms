package com.bos.pub.socket.service.request.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbAppHead
 * @Description: 应用头说明
 * 
 */
public class EsbAppHeadRq implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tranDate; // 交易日期 String 8 必输 YYYYMMDD
	private String tranTime; // 交易时间 String 6 必输 hhmmss
	private String tranTellerNo; // 交易柜员 String 30 必输 有柜员号则填柜员号/无柜员号则填用户ID
	private String branchId; // 机构代码 String 6 可选
	private String terminalCode; // 终端号 String 20 可选
	private String cityCode; // 城市代码 String 6 可选
	private String tranSeqNo; // 交易流水号 String 42 必输 6位系统ID+6位日期+16位序号
	private String globalSeqNo; // 全局流水号 String 42 可选
								// 6位系统ID+6位日期+16位业务流程初始发起系统全局流水号与流水号一致，非初始发起系统则填原全局流水号
	private String authrTellerNo; // 授权柜员 String 30 可选
	private String authrPwd; // 授权密码 String 16 可选 16进制编码
	private String authrCardFlag; // 授权柜员有无卡标志 String 1 可选 0-无卡 1-有卡
	private String authrCardNo; // 授权柜员卡序号 String 2 可选
	private String langCode; // 用户语言 String 10 可选 CHINESE-中文（默认）

	public EsbAppHeadRq() {

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

	public String getCityCode() {
		return cityCode;
	}

	@XmlElement(name = "CityCode")
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getBranchId() {
		return branchId;
	}

	@XmlElement(name = "BranchId")
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getTranSeqNo() {
		return tranSeqNo;
	}

	@XmlElement(name = "TranSeqNo")
	public void setTranSeqNo(String tranSeqNo) {
		this.tranSeqNo = tranSeqNo;
	}

	public String getGlobalSeqNo() {
		return globalSeqNo;
	}

	@XmlElement(name = "GlobalSeqNo")
	public void setGlobalSeqNo(String globalSeqNo) {
		this.globalSeqNo = globalSeqNo;
	}

	public String getAuthrTellerNo() {
		return authrTellerNo;
	}

	@XmlElement(name = "AuthrTellerNo")
	public void setAuthrTellerNo(String authrTellerNo) {
		this.authrTellerNo = authrTellerNo;
	}

	public String getAuthrPwd() {
		return authrPwd;
	}

	@XmlElement(name = "AuthrPwd")
	public void setAuthrPwd(String authrPwd) {
		this.authrPwd = authrPwd;
	}

	public String getAuthrCardFlag() {
		return authrCardFlag;
	}

	@XmlElement(name = "AuthrCardFlag")
	public void setAuthrCardFlag(String authrCardFlag) {
		this.authrCardFlag = authrCardFlag;
	}

	public String getAuthrCardNo() {
		return authrCardNo;
	}

	@XmlElement(name = "AuthrCardNo")
	public void setAuthrCardNo(String authrCardNo) {
		this.authrCardNo = authrCardNo;
	}

	public String getLangCode() {
		return langCode;
	}

	@XmlElement(name = "LangCode")
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	@Override
	public String toString() {
		return "EsbAppHeadRq [tranDate=" + tranDate + ",tranTime=" + tranTime
				+ ",tranTellerNo=" + tranTellerNo + ",branchId=" + branchId
				+ ",terminalCode=" + terminalCode + ",cityCode=" + cityCode
				+ ",tranSeqNo=" + tranSeqNo + ",globalSeqNo=" + globalSeqNo
				+ ",authrTellerNo=" + authrTellerNo + ",authrPwd=" + authrPwd
				+ ",authrCardFlag=" + authrCardFlag + ",authrCardNo="
				+ authrCardNo + ",langCode=" + langCode + "]";
	}
}
