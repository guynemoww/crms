package com.bos.csm.inteface.ecifJava;

import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * ECIF系统查看客户信息---地址信息2
 * 请求
 * @author git
 *
 */
public class AddressInfo2 {
	
	//其他地址操作类型（C/U/D 创建/更新/删除）
	@XmlElement(name="OpTypeAddn")
	public String OpTypeAddn;

	//其他地址物理编号
	@XmlElement(name="NetType")
	public String NetType;

	//其他地址类型
	@XmlElement(name="NetAddrNo")
	public String NetAddrNo = "201";

	//其他地址渠道类型
	@XmlElement(name="NetChannelType")
	final public String NetChannelType = "010201";
	
	//其它地址信息
	@XmlElement(name="NetAddr")
	public String NetAddr;

	
	
	public void setNetAddr(String netAddr) {
		NetAddr = netAddr;
	}


	public void setNetType(String netType) {
		NetType = netType;
	}


	public void setOpTypeAddn(String opTypeAddn) {
		OpTypeAddn = opTypeAddn;
	}


	public void setNetAddrNo(String netAddrNo) {
		NetAddrNo = netAddrNo;
	}


	@Override
	public String toString() {
		return "AddressInfo2 [OpTypeAddn=" + OpTypeAddn+",NetType="+NetType
		+",NetAddrNo="+NetAddrNo+",NetChannelType"+NetChannelType+",NetAddr"+NetAddr ;
	}

}
