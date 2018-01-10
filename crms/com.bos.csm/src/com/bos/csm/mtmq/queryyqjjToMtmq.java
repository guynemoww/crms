/**
 * 
 */
package com.bos.csm.mtmq;

import java.util.HashMap;

import com.bos.pub.socket.service.response.EsbBodyMtmqRsdaycheckArray;
import com.bos.pub.socket.service.response.EsbBodyMtmqRsyqSummaryArray;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author chenchuan
 * @date 2016-02-23 10:29:17
 * 
 */
@Bizlet("queryyqjjMtmq")
public class queryyqjjToMtmq {
	/**
	 * 
	 * @param obj
	 * @return
	 */
	@Bizlet("逾期借据信息查询")
	public DataObject findyqjjMsg(DataObject obj) {
		DataObject ret = DataObjectUtil.createDataObject("com.bos.pub.sys.MtmqPubData");

		String userNum = obj.getString("cstMgrNo");// 客户经理
		String orgNum = obj.getString("ittbrId");// 客户经理机构号
		String csmName = obj.getString("cstNm");// 客户名称
		String certType = obj.getString("idntTp");// 证件类型
		String certNum = obj.getString("identNo");// 证件号码
		int pgRcrdNum = Integer.parseInt(obj.getString("pgRcrdNum"));// 每页记录数
		int crnPgNo = Integer.parseInt(obj.getString("crnPgNo"));// 当前页码
		
		if (userNum == null || userNum == "" || orgNum == null || orgNum == "") {
			ret.set(EsbSocketConstant.RETURN_CODE, "00000000000002");
			ret.set(EsbSocketConstant.RETURN_MSG, "交易成功，客户信息要素不完整");
			return ret;
		}
		
		//查询参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userNum", userNum);
		map.put("orgNum", orgNum);
		if (csmName != null && !"".equals(csmName)) {
			map.put("csmName", csmName);
		}
		if (certType != null && !"".equals(certType)) {
			map.put("certType", certType);
		}
		if (certNum != null && !"".equals(certNum)) {
			map.put("certNum", certNum);
		}

		//分页参数
		DataObject pageCond = DataObjectUtil.createDataObject("com.eos.foundation.PageCond");
		pageCond.set("isCount", true);
		pageCond.set("count", -1);
		pageCond.set("begin", (crnPgNo-1)*pgRcrdNum);
		pageCond.set("length", pgRcrdNum);
		
		Object[] result = DatabaseExt.queryByNamedSqlWithPage("default", "com.bos.csm.mtmq.toMtmq.yqsummaryMsg",pageCond, map);

		if (result == null || result.length == 0) {
			ret.set("ReturnCode", "00000000000001");
			ret.set("ReturnMsg", "交易成功，没有找到对应的信息");
			return ret;
		}

		EsbBodyMtmqRsyqSummaryArray[] yqSummaryArrays = new EsbBodyMtmqRsyqSummaryArray[result.length];
		for (int i = 0; i < result.length; i++) {
			DataObject resultDataObject = (DataObject) result[i];
			EsbBodyMtmqRsyqSummaryArray yqSummaryArray = new EsbBodyMtmqRsyqSummaryArray();
			yqSummaryArray.setCrCstNo(resultDataObject.getString("PARTY_NUM"));
			yqSummaryArray.setCstNm(resultDataObject.getString("PARTY_NAME"));
			yqSummaryArray.setBsnTp(resultDataObject.getString("PRODUCT_TYPE"));
			yqSummaryArray.setCtrDbtNo(resultDataObject.getString("SUMMARY_NUM"));
			yqSummaryArray.setCtrDbtAmt(resultDataObject.getString("SUMMARY_AMT"));
			yqSummaryArray.setOduePrePaidPnpAmt(resultDataObject.getString("JJYQBJ"));
			yqSummaryArray.setOdueIntAmt(resultDataObject.getString("ARREAR_ITR"));
			yqSummaryArray.setIntPnyAmt(resultDataObject.getString("PUNISH_ITR"));
			yqSummaryArray.setOduePrePaidDayNum(resultDataObject.getString("YQTS"));
			yqSummaryArray.setImgBsnNo(resultDataObject.getString("BAR_CODE"));
			yqSummaryArrays[i] = yqSummaryArray;

		}
		ret.set("esbBodyMtmqRsyqSummaryArrays", yqSummaryArrays);
		ret.set("ReturnCode", "00000000000000");
		ret.set("ReturnMsg", "交易成功，逾期借据信息已返回");
		ret.set("totRcrdNum", pageCond.getInt("count"));
		return ret;
	}
}
