package com.primeton.mgrcore.client;

import com.primeton.mgrcore.OXD081_CustAccInfoQryReq;
import com.primeton.mgrcore.OXD082_CustAccInfoQryRes;

public class XD08ClientTest {
	public static void main(String[] args) throws Exception {
		CrmsMgrCallCoreImpl impl = new CrmsMgrCallCoreImpl();

		OXD081_CustAccInfoQryReq oxd081ReqBody = new OXD081_CustAccInfoQryReq();
		OXD082_CustAccInfoQryRes oxd082Res = new OXD082_CustAccInfoQryRes();

		oxd081ReqBody.setCustNo("6223670000000078841");
		oxd082Res = impl.executeXD08(oxd081ReqBody);

		System.out.println(oxd082Res.getResHeader().toString());
		System.out.println(oxd082Res.getResTranHeader().toString());
		System.out.println(oxd082Res.getOxd082ResBody().toString());
	}
}
