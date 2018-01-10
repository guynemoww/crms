package com.primeton.mgrcore.client;

import com.primeton.mgrcore.OXD051_AccInfoQryReq;
import com.primeton.mgrcore.OXD052_AccInfoQryRes;

public class XD05ClientTest {
	public static void main(String[] args) throws Exception {
		CrmsMgrCallCoreImpl impl = new CrmsMgrCallCoreImpl();

		OXD051_AccInfoQryReq oxd051ReqBody = new OXD051_AccInfoQryReq();
		OXD052_AccInfoQryRes oxd052Res = new OXD052_AccInfoQryRes();

		oxd051ReqBody.setQryType("1");
		oxd051ReqBody.setCustAcctNo("6223670000000078841");
		oxd051ReqBody.setSubAcctSeri("");
		oxd051ReqBody.setGroupAcctSeri("");
		oxd051ReqBody.setCurrCode("01");
		oxd051ReqBody.setCashFlag("0");
		oxd051ReqBody.setQryPwd("");
		
		oxd052Res = impl.executeXD05(oxd051ReqBody);

		System.out.println(oxd052Res.getResHeader().toString());
		System.out.println(oxd052Res.getResTranHeader().toString());
		System.out.println(oxd052Res.getOxd052ResBody().toString());
	}
}
