package com.bos.pub.socket.service.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @ClassName: EsbBodyEcifRqLoAdrArray 
 * @Description: 客户信息开户维护	所在地址数组
 *
 */
public class EsbBodyEcifRqLoAdrArray implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String adrCd;		//地址编号		String(16)		地址标识ID，后台返回，地址修改时，原值上传；新增为空
	private String adrTp;		//地址类型		String(5)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String ctyCd;		//国家编码		String(3)		
	private String admnRgonCd;	//行政区域代码	String(19)		
	private String pstCd;		//邮政编码		String(6)		
	private String provCd;		//省份代码		String(10)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String dstcCd;		//区县代码		String(10)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String cityCd;		//城市代码		String(10)		见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String strAdr;		//街道地址		String(40)		
	private String rgonNo;		//地区编号		String(19)		
	private String ctcAdr;		//联系地址		String(30)
	private String dfltAdrFlg;	//默认地址标志	String(3)		"1是0否-1未知"
	
	public EsbBodyEcifRqLoAdrArray(){
		
	}

	public String getAdrCd() {
		return adrCd;
	}

	@XmlElement(name = "AdrCd")
	public void setAdrCd(String adrCd) {
		this.adrCd = adrCd;
	}

	public String getAdrTp() {
		return adrTp;
	}

	@XmlElement(name = "AdrTp")
	public void setAdrTp(String adrTp) {
		this.adrTp = adrTp;
	}

	public String getCtyCd() {
		return ctyCd;
	}

	@XmlElement(name = "CtyCd")
	public void setCtyCd(String ctyCd) {
		this.ctyCd = ctyCd;
	}

	public String getAdmnRgonCd() {
		return admnRgonCd;
	}

	@XmlElement(name = "AdmnRgonCd")
	public void setAdmnRgonCd(String admnRgonCd) {
		this.admnRgonCd = admnRgonCd;
	}

	public String getPstCd() {
		return pstCd;
	}

	@XmlElement(name = "PstCd")
	public void setPstCd(String pstCd) {
		this.pstCd = pstCd;
	}

	public String getProvCd() {
		return provCd;
	}

	@XmlElement(name = "ProvCd")
	public void setProvCd(String provCd) {
		this.provCd = provCd;
	}

	public String getDstcCd() {
		return dstcCd;
	}

	@XmlElement(name = "DstcCd")
	public void setDstcCd(String dstcCd) {
		this.dstcCd = dstcCd;
	}

	public String getCityCd() {
		return cityCd;
	}

	@XmlElement(name = "CityCd")
	public void setCityCd(String cityCd) {
		this.cityCd = cityCd;
	}

	public String getStrAdr() {
		return strAdr;
	}

	@XmlElement(name = "StrAdr")
	public void setStrAdr(String strAdr) {
		this.strAdr = strAdr;
	}

	public String getRgonNo() {
		return rgonNo;
	}

	@XmlElement(name = "RgonNo")
	public void setRgonNo(String rgonNo) {
		this.rgonNo = rgonNo;
	}

	public String getCtcAdr() {
		return ctcAdr;
	}

	@XmlElement(name = "CtcAdr")
	public void setCtcAdr(String ctcAdr) {
		this.ctcAdr = ctcAdr;
	}

	public String getDfltAdrFlg() {
		return dfltAdrFlg;
	}

	@XmlElement(name = "DfltAdrFlg")
	public void setDfltAdrFlg(String dfltAdrFlg) {
		this.dfltAdrFlg = dfltAdrFlg;
	}
}
