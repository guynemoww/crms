package com.primeton.mgrcore.client;

import com.primeton.mgrcore.OXD061_OutFlushesReq;
import com.primeton.mgrcore.OXD06_OutFlushesRes;


public class XD06ClientTest {
	public static void main(String[] args) throws Exception {
		CrmsMgrCallCoreImpl impl = new CrmsMgrCallCoreImpl();

		OXD061_OutFlushesReq oxd061ReqBody = new OXD061_OutFlushesReq();
		OXD06_OutFlushesRes oxd06Res = new OXD06_OutFlushesRes();

		oxd061ReqBody.setFrontSeqNo("2017050288880012");//前台流水  前台流水号
		oxd061ReqBody.setNextDaySignFlg("");//隔日抹账允许标志  不填
		oxd061ReqBody.setOldFrontTransDate("20170422");//原前台交易日期  需要冲正流水的前台交易日期
		oxd061ReqBody.setTransCode("");//交易码  若交易码有输入，则需核对交易码
		oxd061ReqBody.setYnPeriCan("");//是否外围冲正  不填
		oxd06Res = impl.executeXD06(oxd061ReqBody);

		System.out.println(oxd06Res.getResHeader().toString());
		System.out.println(oxd06Res.getResTranHeader().toString());
		System.out.println(oxd06Res.getXd06ResBody().toString());
	}
}
