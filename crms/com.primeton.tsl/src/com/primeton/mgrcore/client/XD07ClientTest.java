package com.primeton.mgrcore.client;

import com.primeton.mgrcore.OXD071_AccControlReq;
import com.primeton.mgrcore.OXD072_AccControlRes;
import com.primeton.mgrcore.OXD081_CustAccInfoQryReq;
import com.primeton.mgrcore.OXD082_CustAccInfoQryRes;

public class XD07ClientTest {
	public static void main(String[] args) throws Exception {
		CrmsMgrCallCoreImpl impl = new CrmsMgrCallCoreImpl();

		OXD071_AccControlReq oxd071ReqBody = new OXD071_AccControlReq();
		OXD072_AccControlRes oxd072Res = new OXD072_AccControlRes();
		
		OXD081_CustAccInfoQryReq oxd081ReqBody = new OXD081_CustAccInfoQryReq();
		OXD082_CustAccInfoQryRes oxd082Res = new OXD082_CustAccInfoQryRes();
		oxd081ReqBody.setCustNo("6223670000000078841");
		oxd082Res = impl.executeXD08(oxd081ReqBody);

		oxd071ReqBody.setOperFlag(""); 
		oxd071ReqBody.setFreezeOperFlag("5"); 
		oxd071ReqBody.setFrzNum(""); 
		oxd071ReqBody.setFreezeType("11"); 
		oxd071ReqBody.setCustNo("6223670000000078841"); 
		oxd071ReqBody.setAcctname(oxd082Res.getOxd082ResBody().getCustName());
		oxd071ReqBody.setSubAcctSeri(oxd082Res.getOxd082ResBody().getSubAcctSeri());
		oxd071ReqBody.setCurrCode(oxd082Res.getOxd082ResBody().getCurrCode());
		oxd071ReqBody.setCashFlag(oxd082Res.getOxd082ResBody().getCashFlag());
		oxd071ReqBody.setLabtAcctNum(oxd082Res.getOxd082ResBody().getLabtAcctNum());
		oxd071ReqBody.setFreezeBal("");
		oxd071ReqBody.setFreezeAmt("10.0"); 
		oxd071ReqBody.setAmt(""); 
		oxd071ReqBody.setFrzCase("TEST"); 
		oxd071ReqBody.setFreezeEnsureFileType(""); 
		oxd071ReqBody.setFreezeNotifyNo(""); 
		oxd071ReqBody.setApprover(""); 
		oxd071ReqBody.setFlgValue(""); 
		oxd071ReqBody.setVchKind(oxd082Res.getOxd082ResBody().getVchKind());
		oxd071ReqBody.setVchBatNo(oxd082Res.getOxd082ResBody().getVchBatNo());
		oxd071ReqBody.setVchSerialNo(oxd082Res.getOxd082ResBody().getVchSerialNo());
		oxd071ReqBody.setOperFlag1("1"); 
		oxd071ReqBody.setFreezeEndDate("20170330"); 
		
		oxd072Res = impl.executeXD07(oxd071ReqBody);

		System.out.println(oxd072Res.getResHeader().toString());
		System.out.println(oxd072Res.getResTranHeader().toString());
		System.out.println(oxd072Res.getOxd072ResBody().toString());
	}
}
