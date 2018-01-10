/**
 * 
 */
package com.bos.bizApply;

import java.math.BigDecimal;
import java.util.HashMap;

import com.bos.biz.MathHelper;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-09-21 18:00:41
 *
 */
@Bizlet("")
public class SQPartyLimit {
	public static TraceLogger logger = new TraceLogger(SQPartyLimit.class);

	/**
	 * @param map 
	 * @param args
	 * @author 3231
	 * 查询客户所有已生效业务敞口：
		1，综合授信不存在历史生效单笔
		2，取所有已生效的非银团非低的已生效的单笔批复额度
	 */
	@Bizlet("")
	public BigDecimal getBizAmtCkToProParty03(HashMap map) {
		String partyId = (String) map.get("partyId");
		String baseApplyId = (String) map.get("applyId");
		BigDecimal partyCkAmt = new BigDecimal(0);
		if (null == partyId) {
			logger.info("业务申请流程结束ID为空！");
			return new BigDecimal(0);
		}
		DataObject bizApprove = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizApprove");
		bizApprove.set("partyId", partyId);
		bizApprove.set("becomeEffectiveMark", "03");
		DataObject[] bizApproves = DatabaseUtil.queryEntitiesByTemplate(
				"default", bizApprove);
		for (int i = 0; i < bizApproves.length; i++) {
			//非银团，非低
			if (null == bizApproves[i].get("lowRiskBizType") && ("0".equals(bizApproves[i].get("isBankTeamLoan")) || null == bizApproves[i].get("isBankTeamLoan")) &&
				(!"2".equals(bizApproves[i].get("approveConclusion"))) ) {
				String applyId = null;
				applyId = (String) bizApproves[i].get("applyId");
				if(applyId.equals(baseApplyId)){
					
				}else{
					HashMap mapA = new HashMap();
					mapA.put("applyId", applyId);
					logger.info("------3231------>业务申请授权------获取客户历史敞口------>历史生效applyId="
							+ applyId);
					Object[] objs = DatabaseExt.queryByNamedSql("default",
							"com.bos.bizInfo.bizInfo.getAmountAmt", mapA);
					BigDecimal amt = (BigDecimal) objs[0];
					logger.info("------3231------>业务申请授权------获取客户历史敞口------>历史生效applyId="
							+ applyId + "------>额度批复=" + amt);
					Object[] kjs = DatabaseExt.queryByNamedSql("default",
							"com.bos.bizInfo.bizInfo.getKjAmt", mapA);
					BigDecimal kjAmt = (BigDecimal) kjs[0];
					logger.info("------3231------>业务申请授权------获取客户历史敞口------>历史生效applyId="
							+ applyId + "------>额度批复敞口需扣减=" + amt);
					String gsKj = "amt-kjAmt";
					mapA.put("amt", amt);
					mapA.put("kjAmt", kjAmt);
					BigDecimal ckAmt = MathHelper.expressionBigDecimal(gsKj, mapA);
					logger.info("------3231------>业务申请授权------获取客户历史敞口------>历史生效applyId="
							+ applyId + "------>额度批复敞口额度=" + ckAmt);
					String gsLj = "partyCkAmt+ckAmt";
					mapA.put("ckAmt", ckAmt);
					mapA.put("partyCkAmt", partyCkAmt);
					partyCkAmt = MathHelper.expressionBigDecimal(gsLj, mapA);
					logger.info("------3231------>业务申请授权------获取客户历史敞口------>历史生效applyId="
							+ applyId + "------>额度批复累计敞口额度=" + partyCkAmt);
				}
			}
		}
		return partyCkAmt;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
