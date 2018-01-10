package com.bos.csm.inteface.ecifJava;

import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * ECIF系统查看客户信息---地址信息2
 * 请求
 * @author git
 *
 */
public class AddressInfoForECIF {
	
	//地址类型代码
	@XmlElement(name="OpTypeAddr")
	public String OpTypeAddr;
	
	//地址类型代码
	@XmlElement(name="AddressType")
	public String AddressType = "101";
	
	
	//地址编号
	@XmlElement(name="Vaddr")
	public String Vaddr;
	
	//渠道类型ChannelType
	@XmlElement(name="ChannelType")
	public String ChannelType = "010100";
	
	//地址应用系统AddrAppSystem
	@XmlElement(name="AddrAppSystem")
	public String AddrAppSystem = "CBM";
	
	//详细地址
	@XmlElement(name="Usage")
	public String Usage ;
	
	//洲别
	@XmlElement(name="Continent")
	public String Continent;
	
	// 国家代码
	@XmlElement(name="AddCountry")
	public String AddCountry;

	//省份代码
	@XmlElement(name="Province")
	public String Province;

	// 区县
	@XmlElement(name="Dist")
	public String Dist;
	
	//城市代码
	@XmlElement(name="City")
	public String City;
	
	//街道
	@XmlElement(name="Strent")
	public String Strent;

	//街道地址，具体到门牌号码
	@XmlElement(name="StrentDoor")
	public String StrentDoor;
	
	//电话号码
	@XmlElement(name="TePhone")
	public String TePhone;

	//传真号码
	@XmlElement(name="FaxPhone")
	public String FaxPhone;

	//地址类型：注册地址、工作地址等
	@XmlElement(name="AddrUsageType")
	public String AddrUsageType;

	
	public void setAddCountry(String addCountry) {
		AddCountry = addCountry;
	}

	public void setAddrAppSystem(String addrAppSystem) {
		AddrAppSystem = addrAppSystem;
	}

	public void setAddressType(String addressType) {
		AddressType = addressType;
	}

	public void setAddrUsageType(String addrUsageType) {
		AddrUsageType = addrUsageType;
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

	public void setDist(String dist) {
		Dist = dist;
	}

	public void setFaxPhone(String faxPhone) {
		FaxPhone = faxPhone;
	}

	public void setOpTypeAddr(String opTypeAddr) {
		OpTypeAddr = opTypeAddr;
	}


	public void setProvince(String province) {
		Province = province;
	}

	public void setStrent(String strent) {
		Strent = strent;
	}

	public void setTePhone(String tePhone) {
		TePhone = tePhone;
	}

	public void setUsage(String usage) {
		Usage = usage;
	}

	public void setVaddr(String vaddr) {
		Vaddr = vaddr;
	}

	public void setStrentDoor(String strentDoor) {
		StrentDoor = strentDoor;
	}
	
	
	
	







//	@Override
//	public String toString() {
//		return "AddressInfo [AddrType=" + AddrType+",NationalCode="+NationalCode
//		+",AgtNation="+AgtNation ;
//	}

}
