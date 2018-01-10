package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRq;


/**
 * 抵押物多头校验JavaBen
 * CRMS系统向押品子系统发送报文
 * @author menglei 2014-5-21
 *
 */
public class CCMSCollReVerifyRq  extends SuperBosfxRq {
	@XmlElement(name="SuretyId")
	public String SuretyId;//押品唯一编号，主键
	@XmlElement(name="SortType")
	public String SortType;//押品类型
	@XmlElement(name="CardType")
	public String CardType;//权利证明类型
	@XmlElement(name="CardNum")
	public String CardNum;//权利证明编号
	@XmlElement(name="UserNum")
	public String UserNum;//创建人
	@XmlElement(name="OrgNum")
	public String OrgNum;//创建号
	@XmlElement(name="ChannelId")
	public String ChannelId;//渠道号，CRMS系统为 “1”
	
	public void setCardNum(String cardNum) {
		CardNum = cardNum;
	}

	public void setCardType(String cardType) {
		CardType = cardType;
	}

	public void setChannelId(String channelId) {
		ChannelId = channelId;
	}

	public void setOrgNum(String orgNum) {
		OrgNum = orgNum;
	}

	public void setSortType(String sortType) {
		SortType = sortType;
	}

	public void setSuretyId(String suretyId) {
		SuretyId = suretyId;
	}

	public void setUserNum(String userNum) {
		UserNum = userNum;
	}

	@Override
	public String toString() {
		return "TbGrtGuarantybasicRq [SuretyId=" + SuretyId + ", SortType="
				+ SortType + ", CardType=" + CardType
				+ ", CardNum=" + CardNum + ", UserNum=" + UserNum + ", OrgNum="
				+ OrgNum + ", ChannelId=" + ChannelId  + "]";
	}

}
