package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;

import commonj.sdo.DataObject;

/**
 * 贷后检查
 * 
 * @author Administrator
 * 
 */
public class AftCAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		String[][][] entityNames = new String[][][] {//
		{ { "com.bos.dataset.aft.TbAftFirstCheck", "01" }, { "firstCheckId", bizId } }//
				, { { "com.bos.dataset.aft.TbAftNormalCheck", "02" }, { "normalCheckId", bizId } }//
				, { { "com.bos.dataset.aft.TbAftPointCheck", "p" }, { "checkId", bizId } }//
				, { { "com.bos.dataset.aft.TbAftExpireCheck", "e" }, { "checkId", bizId } } //
		};
		for (String[][] configs : entityNames) {
			DataObject entity = EntityUtil.getEntityById(configs[0][0], false, configs[1]);
			if (entity != null) {
				param.setPartyId(entity.getString("partyId"));
				param.putRelaMap("bizType", configs[0][1]);
				break;
			}
		}
		if (param.getPartyId() == null) {

		}
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("aft_day_check_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("aft_day_check_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("aft_day_check_pwfm");
		}

		return param;
	}
}
