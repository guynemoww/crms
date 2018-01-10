package com.primeton.mgrcore.client;

import com.primeton.mgrcore.IXD15AccountInfo;
import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012_AccoutingRes;
import com.primeton.mgrcore.OXD041_SuccessFlagChkReq;
import com.primeton.mgrcore.OXD042_SuccessFlagChkRes;
import com.primeton.mgrcore.OXD051_AccInfoQryReq;
import com.primeton.mgrcore.OXD052_AccInfoQryRes;
import com.primeton.mgrcore.OXD061_OutFlushesReq;
import com.primeton.mgrcore.OXD06_OutFlushesRes;
import com.primeton.mgrcore.OXD071_AccControlReq;
import com.primeton.mgrcore.OXD072_AccControlRes;
import com.primeton.mgrcore.OXD081_CustAccInfoQryReq;
import com.primeton.mgrcore.OXD082_CustAccInfoQryRes;
import com.primeton.mgrcore.OXD091_PawnInOutReq;
import com.primeton.mgrcore.OXD092_PawnInOutRes;
import com.primeton.mgrcore.OXD11_CdzykhReq;
import com.primeton.mgrcore.OXD11_CdzykhRes;
import com.primeton.mgrcore.OXD15AccountInfo;

/**
 * 信贷管理与核心接口
 * 
 * @author MJF
 */
public interface CrmsMgrCallCoreProxy {
	/** 【XD01】通用记账(8661) 输出接口OXD012 */
	OXD012_AccoutingRes executeXD01(OXD011_AccoutingReq requestBody) throws Exception;
	
	/** 【XD04】交易状态成功性检查接口OXD041 */
	OXD042_SuccessFlagChkRes executeXD04(OXD041_SuccessFlagChkReq requestBody)  throws Exception;
	
	/** 【XD05】账户信息查询(1224) 输出接口OXD052 FXD051 */
	OXD052_AccInfoQryRes executeXD05(OXD051_AccInfoQryReq requestBody) throws Exception;

	/** 【XD06】外围冲正(对应撤销) 输出接口OOTHER */
	OXD06_OutFlushesRes executeXD06(OXD061_OutFlushesReq requestBody)throws Exception;
	
	/** 【XD07】账户控制及维护(止付/解止付)(1204) 输出接口OXD072 */
	OXD072_AccControlRes executeXD07(OXD071_AccControlReq requestBody) throws Exception;

	/** 【XD08】客户账户信息查询(1232) 输出接口OXD082 */
	OXD082_CustAccInfoQryRes executeXD08(OXD081_CustAccInfoQryReq requestBody) throws Exception;

	/** 【XD09】信贷抵质押物表外记账 输出接口OXD092 */
	OXD092_PawnInOutRes executeXD09(OXD091_PawnInOutReq requestBody) throws Exception;
	/** 【XD15】内部账号查询 输出接口OXD15 */
	OXD15AccountInfo executeXD15(IXD15AccountInfo requestBody) throws Exception; 
	
	/** 【XD11】质押扣划OXD11 */
	OXD11_CdzykhRes executeXD11(OXD11_CdzykhReq requestBody) throws Exception; 
}