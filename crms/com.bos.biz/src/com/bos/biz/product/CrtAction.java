package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;

import commonj.sdo.DataObject;

/**
 * 合同签约
 * 
 * @author Administrator
 * 
 */
public class CrtAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.crt.TbConCreditInfo", false, "contractId", bizId);
		if (entity == null) {
			entity = EntityUtil.getEntityById("com.bos.dataset.crt.TbConContractInfo", "contractId", bizId);
		}
		param.setPartyId(entity.getString("partyId"));
		String oldConId = entity.getString("oldContractId");
		String legorg = GitUtil.getLegorg();
		// 合同申请
		if (null == oldConId || "".equals(oldConId)) {
			if (DictContents.ORG_MCCB.equals(legorg)) {
				param.setTemplateName("crt_sign_mccb");
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				param.setTemplateName("crt_sign_bcfm");
			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("crt_sign_pwfm");
			}
		} else {// 合同调整
			if (DictContents.ORG_MCCB.equals(legorg)) {
				param.setTemplateName("contract_tz_mccb");
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				param.setTemplateName("contract_tz_bcfm");
			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("contract_tz_pwfm");
			}
		}
		return param;
	}

}
