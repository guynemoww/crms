package com.primeton.crmsgj.client;

import com.primeton.crmsgj.GJS01501030000001Req;
import com.primeton.crmsgj.GJS01501030000001Res;
import com.primeton.crmsgj.GJS01501110000002Req;
import com.primeton.crmsgj.GJS01501110000002Res;
import com.primeton.crmsgj.GJS01501030000003Req;
import com.primeton.crmsgj.GJS01501030000003Res;
import com.primeton.crmsgj.GJS01501110000004Req;
import com.primeton.crmsgj.GJS01501110000004Res;
import com.primeton.crmsgj.GJS01501030000005Req;
import com.primeton.crmsgj.GJS01501030000005Res;
import com.primeton.crmsgj.GJS01501030000006Req;
import com.primeton.crmsgj.GJS01501030000006Res;
import com.primeton.crmsgj.GJS01501070000007Req;
import com.primeton.crmsgj.GJS01501070000007Res;
import com.primeton.crmsgj.GJS01501030000008Req;
import com.primeton.crmsgj.GJS01501030000008Res;
import com.primeton.crmsgj.GJS01501030000009Req;
import com.primeton.crmsgj.GJS01501030000009Res;
import com.primeton.crmsgj.GJS01501010000010Req;
import com.primeton.crmsgj.GJS01501010000010Res;
import com.primeton.crmsgj.GJS01501010000011Req;
import com.primeton.crmsgj.GJS01501010000011Res;
/**
 * 
 * @author shendl
 *
 */
public interface CrmsMgrCallGjProxy {
	//表内融资业务放款交易接口
	GJS01501030000001Res executeS01501030000001(GJS01501030000001Req gjS01501030000001Req);
	//进口信用证开证接口
	GJS01501110000002Res executeS01501110000002(GJS01501110000002Req gjS01501110000002Req);
	//进口信用证开证修改接口
	GJS01501030000003Res executeS01501030000003(GJS01501030000003Req gjS01501030000003Req);
	//进口保函开立接口
	GJS01501110000004Res executeS01501110000004(GJS01501110000004Req gjS01501110000004Req);
	//进口保函修改接口
	GJS01501030000005Res executeS01501030000005(GJS01501030000005Req gjS01501030000005Req);
	//提货担保接口
	GJS01501030000006Res executeS01501030000006(GJS01501030000006Req gjS01501030000006Req);
	//放款撤销接口
	GJS01501070000007Res executeS01501070000007(GJS01501070000007Req gjS01501070000007Req);
	//编号校验接口
	GJS01501030000008Res executeS01501030000008(GJS01501030000008Req gjS01501030000008Req);
	//融资展期接口---暂时不做
	GJS01501030000009Res executeS01501030000009(GJS01501030000009Req gjS01501030000009Req);
	//牌价查询接口---暂时不做
	GJS01501010000010Res executeS01501010000010(GJS01501010000010Req gjS01501010000010Req);
	//国结业务表外业务放款状态查询接口---暂时不做
	GJS01501010000011Res executeS01501010000011(GJS01501010000011Req gjS01501010000011Req);
}
