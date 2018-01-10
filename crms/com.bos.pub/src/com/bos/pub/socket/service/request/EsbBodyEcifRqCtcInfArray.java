package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyEcifRqCtcInfArray 
 * @Description: 客户信息开户维护	联系信息数组
 *
 */
public class EsbBodyEcifRqCtcInfArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ctcCd;		//联系编号		String(16)		联系序号ID，后台返回，联系信息修改时，原值上传；新增为空
	private String ctcMth;		//联系方式		String(2)	N	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String ctcInf;		//联系信息		String(42)
	private String dfltCtcFlg;	//默认联系标志	String(3)	N	"1是0否-1未知"
	
	public EsbBodyEcifRqCtcInfArray(){
		
	}

	public String getCtcCd() {
		return ctcCd;
	}

	@XmlElement(name = "CtcCd")
	public void setCtcCd(String ctcCd) {
		this.ctcCd = ctcCd;
	}

	public String getCtcMth() {
		return ctcMth;
	}

	@XmlElement(name = "CtcMth")
	public void setCtcMth(String ctcMth) {
		this.ctcMth = ctcMth;
	}

	public String getCtcInf() {
		return ctcInf;
	}

	@XmlElement(name = "CtcInf")
	public void setCtcInf(String ctcInf) {
		this.ctcInf = ctcInf;
	}

	public String getDfltCtcFlg() {
		return dfltCtcFlg;
	}

	@XmlElement(name = "DfltCtcFlg")
	public void setDfltCtcFlg(String dfltCtcFlg) {
		this.dfltCtcFlg = dfltCtcFlg;
	}
}
