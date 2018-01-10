package com.primeton.mgrcore.client;

import java.math.BigInteger;

import com.primeton.mgrcore.FXD011;
import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012_AccoutingRes;

public class XD01ClientTest {
	public static void main(String[] args) throws Exception {
		CrmsMgrCallCoreImpl impl = new CrmsMgrCallCoreImpl();
		OXD011_AccoutingReq oxd011ReqBody = new OXD011_AccoutingReq();
		OXD012_AccoutingRes xd012Res = new OXD012_AccoutingRes();
		
		oxd011ReqBody.setChargeSeq("");
		oxd011ReqBody.setOutSystemDate("");
		oxd011ReqBody.setBusiType1("");
		oxd011ReqBody.setUnitNo("");
		oxd011ReqBody.setLotNum("");
		oxd011ReqBody.setAmount("");
		oxd011ReqBody.setThridTransCode("");
		oxd011ReqBody.setRecNum(new BigInteger("1"));
		oxd011ReqBody.setSummaryCode("");
		oxd011ReqBody.setSummaryDescription("");
		oxd011ReqBody.setRemarkInfo("");
		FXD011[] fxd011Array =  new FXD011[(oxd011ReqBody.getRecNum()).intValue()];
		for (int i = 0; i < fxd011Array.length; i++) {
			FXD011 fxd011 = new FXD011();
			fxd011.setDealType("");
			fxd011.setDrCrFlag("");
			fxd011.setCurrCode("");
			fxd011.setCashFlag("");
			fxd011.setTransAmt("");
			fxd011.setAcctFromGo("");
			fxd011.setAcct("");
			fxd011.setAcctName("");
			fxd011.setAcctSeq("");
			fxd011.setChargeBrch("");
			fxd011.setChargeBusiCode("");
			fxd011.setChargeBusiType("");
			fxd011.setRolloutWriteoffSeq("");
			fxd011.setCshProCode("");
			fxd011.setPwdKind("");
			fxd011.setTransPassWord("");
			fxd011.setVchKind("");
			fxd011.setVchBatNo("");
			fxd011.setVchSerialNo("");
			fxd011.setPayPwd("");
			fxd011.setDrawDate("");
			fxd011.setIssueBankNo("");
			fxd011.setSndTrak("");
			fxd011.setThrTrak("");
			fxd011.setSignPassFlag("");
			fxd011.setCertType("");
			fxd011.setCertNo("");
			fxd011.setVertLastboxSignFlag("");
			fxd011.setVertSignBelongOper("");
			fxd011.setVchKind("");
			fxd011.setVchNo("");
			fxd011.setVchBatNo("");
			fxd011.setVchSerialNo("");
			fxd011.setOtherAcct("");
			fxd011.setOtherNam("");
			fxd011.setOrganNam("");
			fxd011.setOthBankBrchType("");
			fxd011.setOthBankBrchCode("");
			fxd011.setAgentName("");
			fxd011.setAgentPaperType("");
			fxd011.setAgntCertNum("");
			fxd011.setFeePayType("");
			fxd011.setOrigBusiNo("");
			fxd011.setOrigTxDate("");
			fxd011.setOldOperSeq("");
			fxd011.setPoundageAmtFrom("");
			fxd011.setFeeAcct("");
			fxd011.setChargeAcctSeq("");
			fxd011.setFeeEveNo("");
			fxd011.setChargeAmt("");
			fxd011.setEnoughFlag("");
			fxd011Array[i] = fxd011 ;
		}
		oxd011ReqBody.setFxd011(fxd011Array);
		
		xd012Res = impl.executeXD01(oxd011ReqBody);

		System.out.println(xd012Res.getResHeader());
		System.out.println(xd012Res.getResTranHeader());
		System.out.println(xd012Res.getOxd012ResBody());
	}
}