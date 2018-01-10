package com.primeton.mgrcore.client;

import java.math.BigInteger;

import com.primeton.mgrcore.FXD091;
import com.primeton.mgrcore.OXD091_PawnInOutReq;
import com.primeton.mgrcore.OXD092_PawnInOutRes;

public class XD09ClientTest {
	public static void main(String[] args) throws Exception {
		CrmsMgrCallCoreImpl impl = new CrmsMgrCallCoreImpl();

		OXD091_PawnInOutReq oxd091ReqBody = new OXD091_PawnInOutReq();
		OXD092_PawnInOutRes oxd092Res = new OXD092_PawnInOutRes();
		
		oxd091ReqBody.setOperFlag("0");
		oxd091ReqBody.setChargeBrch("1061");
		oxd091ReqBody.setYnFlag("");
		oxd091ReqBody.setPrdCode("");
		oxd091ReqBody.setCollateralWay("1");
		oxd091ReqBody.setActualValue("20.0");
		oxd091ReqBody.setCurrCode("01");
		oxd091ReqBody.setSummaryCode("");
		oxd091ReqBody.setSummary("");
		oxd091ReqBody.setCustNo("66666666");
		oxd091ReqBody.setBackup2("");
		oxd091ReqBody.setBackup1("");
		oxd091ReqBody.setReserveMark1("");
		oxd091ReqBody.setReserveMark2("");
		oxd091ReqBody.setBackupAmt("");
		oxd091ReqBody.setRecNum(new BigInteger("1"));
		FXD091[] fxd091Array = new FXD091[(oxd091ReqBody.getRecNum().intValue())];
		for (int i = 0; i < fxd091Array.length; i++) {
			FXD091 fxd091 = new FXD091();
			fxd091.setYnFlag1("0");
			fxd091.setFrzNum("");
			fxd091.setCustAcct("6223670000000078841");
			fxd091.setAcctname("");
			fxd091.setSubAcctSeri("");
			fxd091.setCurrCode("01");
			fxd091.setCashFlag("");
			fxd091.setFreezeType("");
			fxd091.setFreezeEndDate("");
			fxd091.setFreezeAmt("10.0");
			fxd091.setFrzCase("");
			fxd091.setFreezeEnsureFileType("");
			fxd091.setFreezeNotifyNo("");
			fxd091.setYnFlag2("0");
			fxd091.setBackup2("");
			fxd091.setBackup1("");
			fxd091.setReserveMark1("");
			fxd091.setReserveMark2("");
			fxd091.setBackupAmt("");
			fxd091Array[i] = fxd091 ;
		}
		oxd091ReqBody.setFxd091(fxd091Array);
		
		oxd092Res = impl.executeXD09(oxd091ReqBody);

		System.out.println(oxd092Res.getResHeader().toString());
		System.out.println(oxd092Res.getResTranHeader().toString());
		System.out.println(oxd092Res.getOxd092ResBody().toString());
	}
}
