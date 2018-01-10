package com.bos.csm.inteface.ecifJava;

import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * ECIF系统查看客户信息---地址信息
 * 响应
 * @author git
 *
 */
public class AddressInfo {
//	地址类型代码
	@XmlElement(name="OpTypeAddr")
	public String OpTypeAddr;
	
	//地址类型代码
	@XmlElement(name="AddrType")
	public String AddrType = "101";
	
	
	//地址编号
	@XmlElement(name="AdderNum")
	public String AdderNum;
	
	//渠道类型ChannelType
	@XmlElement(name="ChannelType")
	public String ChannelType = "010100";
	
	//地址应用系统AddrAppSystem
	//@XmlElement(name="AddrAppSystem")
	//public String AddrAppSystem = "CBM";
	
	//详细地址
	//@XmlElement(name="Usage")
	//public String Usage ;
	
	//洲别
	@XmlElement(name="Continent")
	public String Continent;
	
	// 国家代码
	@XmlElement(name="NationalCode")
	public String NationalCode;

	//省份代码
	@XmlElement(name="CustProvince")
	public String CustProvince;

	// 区县
	@XmlElement(name="DistNo")
	public String DistNo;
	
	//城市代码
	@XmlElement(name="City")
	public String City;
	
	//街道
	@XmlElement(name="ProvinceCode")
	public String ProvinceCode;

	//街道地址，具体到门牌号码
	@XmlElement(name="AgtNation")
	public String AgtNation;
	
	//邮政编码
	@XmlElement(name="PayerPostCode")
	public String PayerPostCode;
	
	//电话号码
	@XmlElement(name="TePhone")
	public String TePhone;

	//传真号码
	@XmlElement(name="FaxPhone")
	public String FaxPhone;

	//地址类型：注册地址、工作地址等
	@XmlElement(name="AddrUsageType")
	public String AddrUsageType;

	
	

	





	public void setAdderNum(String adderNum) {
		AdderNum = adderNum;
	}










	public void setAddrType(String addrType) {
		AddrType = addrType;
	}










	public void setAddrUsageType(String addrUsageType) {
		AddrUsageType = addrUsageType;
	}










	public void setAgtNation(String agtNation) {
		AgtNation = agtNation;
	}










	public void setChannelType(String channelType) {
		ChannelType = channelType;
	}










	public void setCity(String city) {
		City = city;
	}










	public void setContinent(String continent) {
		Continent = continent;
	}










	public void setCustProvince(String custProvince) {
		CustProvince = custProvince;
	}










	public void setDistNo(String distNo) {
		DistNo = distNo;
	}










	public void setFaxPhone(String faxPhone) {
		FaxPhone = faxPhone;
	}










	public void setNationalCode(String nationalCode) {
		NationalCode = nationalCode;
	}










	public void setOpTypeAddr(String opTypeAddr) {
		OpTypeAddr = opTypeAddr;
	}










	public void setPayerPostCode(String payerPostCode) {
		PayerPostCode = payerPostCode;
	}










	public void setProvinceCode(String provinceCode) {
		ProvinceCode = provinceCode;
	}










	public void setTePhone(String tePhone) {
		TePhone = tePhone;
	}










	@Override
	public String toString() {
		return "AddressInfo [AddrType=" + AddrType+",NationalCode="+NationalCode
		+",AgtNation="+AgtNation ;
	}

}
