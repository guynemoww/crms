package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;

import commonj.sdo.DataObject;

/**
 * 专业担保协议
 * 
 * @author Administrator
 * 
 */
public class DbjgAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.crt.TbConGuarantOrgInfo", "contractId", bizId);

		param.setPartyId(entity.getString("partyId"));
		
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("crt_sign_zydbjghz_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("crt_sign_zydbjghz_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("crt_sign_zydbjghz_pwfm");
		}
		param.setModelType("crt");

		return param;
	}

}
