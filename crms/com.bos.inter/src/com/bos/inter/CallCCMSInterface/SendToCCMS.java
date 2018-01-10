package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.JAXBException;
import com.eos.system.annotation.Bizlet;
import com.ibm.mq.MQException;
import commonj.sdo.DataObject;


/**
 * 向押品子系统发送报文，并且接受响应信息
 * @author menglei 2014-5-21
 *
 */
public class SendToCCMS {
	

	@Bizlet(value="")
	public Object sendBasicToCCMS(DataObject object) throws JAXBException, MQException{
		CCMSCollReVerifyRq basic = new CCMSCollReVerifyRq();
		basic.setSuretyId(object.getString("suretyId"));
		basic.setSortType(object.getString("sortType"));
		basic.setCardType(object.getString("cardType"));
		basic.setCardNum(object.getString("cardNum"));
		basic.setOrgNum(object.getString("orgNum"));
		basic.setUserNum(object.getString("userNum"));
		basic.setChannelId(object.getString("channelId"));
		//basic.setCommonRsHdr("");
		
		
		return basic;
	}

}
