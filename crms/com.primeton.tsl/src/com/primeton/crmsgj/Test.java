package com.primeton.crmsgj;

import org.apache.axis2.AxisFault;

import com.primeton.crmsgj.client.CrmsMgrCallGjImpl;
import com.primeton.crmsgj.client.CrmsMgrCallGjProxy;

public class Test {

	public static void main(String[] args) throws AxisFault {
		//S01501030000001
		CrmsMgrCallGjProxy crmsMgrCallGjImpl = new CrmsMgrCallGjImpl();
		GJS01501030000001Req gjS01501030000001Req = new GJS01501030000001Req();
		gjS01501030000001Req.setAcctBrch("");//出账机构
		gjS01501030000001Req.setAgreeAmt("");//合同金额
		gjS01501030000001Req.setAgreeSeqNo("");//合同流水号
		gjS01501030000001Req.setBusiCode("");//业务编号
		gjS01501030000001Req.setContractNo("JK150116000864");//合同编号
		gjS01501030000001Req.setCurrency("");//借据币种
		gjS01501030000001Req.setCustNo("");//客户编号
		gjS01501030000001Req.setDateOfValue("");//日期
		gjS01501030000001Req.setDealRate("");//执行利率
		gjS01501030000001Req.setDebAcct("");//入账账户
		gjS01501030000001Req.setDebAmt("");//借据金额
		gjS01501030000001Req.setDebitNo("");//借据编号
		gjS01501030000001Req.setMatuDat("");//到期日期
		gjS01501030000001Req.setOverRate("");//逾期利率
		gjS01501030000001Req.setPaySeqnNo("");//出账流水号
		gjS01501030000001Req.setPrdCode("");//产品类型
		gjS01501030000001Req.setProSubTp("");//产品子类型
		GJS01501030000001Res s01501030000001Res = crmsMgrCallGjImpl.executeS01501030000001(gjS01501030000001Req);
		System.out.println(s01501030000001Res.toString());
	}

}
