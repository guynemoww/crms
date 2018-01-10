package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;

/**
 * 机构拆并
 * 
 * @author Administrator
 * 
 */
public class CsmxfeAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		// DataObject entity = EntityUtil.getEntityById(CsmTableName.TB_CSMXFE_TRANSFER, "transferId", bizId);
		// 机构拆并
		// 测试用，待调整
		param.setModelType("biz");
		param.setTemplateName("com.bos.bps.biz.biz_apply_bmdzr_mccb");
		param.setAbsenceParty(true);
		param.setPartyId("没有客户信息");
		return param;
	}
}
