/**
 * 
 */
package com.bos.csm.pub;

import com.eos.system.annotation.Bizlet;
import com.primeton.tsl.ecif.S0110102000B014ServiceStub.FMT_CRMS_SVR_S0110102000B014_IN;
import com.primeton.tsl.ecif.S0110102000B014ServiceStub.FMT_CRMS_SVR_S0110102000B014_OUT;
import com.primeton.tsl.ecif.S0110102000B014ServiceStub.S0110102000B014Response;
import com.primeton.tsl.ecif.port.IcustEcif;
import com.primeton.tsl.ecif.port.impl.CustEcifImpl;

/**
 * @author zhouxu
 * @date 2017-07-21 10:11:00
 *
 */
@Bizlet("客户业务开通情况登记")
public class PubBussOpen {

	@Bizlet("公共业务  客户业务开通情况登记查询")
	public String CustBussOpenMaint(String ecifPartyNum) throws Exception {

		IcustEcif ecif = new CustEcifImpl();
		FMT_CRMS_SVR_S0110102000B014_IN requestB014 = new FMT_CRMS_SVR_S0110102000B014_IN();
		requestB014.setECIF_CUST_NO(ecifPartyNum);
		requestB014.setSYSTEM_CODE("公共.系统编号");
		//		requestB014.setOTHER_DESC("");
		S0110102000B014Response responseB014 = ecif.CCustBussOpenMaint(requestB014);
		String errorCode=responseB014.getResTranHeader().getHRetNo();
		String errorMsg=responseB014.getResTranHeader().getHRetMsg();
		FMT_CRMS_SVR_S0110102000B014_OUT resBody = responseB014.getResponseBody();
		
		return resBody.getSYS_SEQ_ID();

	}

}
