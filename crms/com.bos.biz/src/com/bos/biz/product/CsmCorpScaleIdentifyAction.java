package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;

import commonj.sdo.DataObject;

/**
 * 企业规模认定
 * 
 * @author Administrator
 * 
 */
public class CsmCorpScaleIdentifyAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.csm.TbCsmCorpScaleIdentify", "id", bizId);
		String legorg = GitUtil.getLegorg();
		param.setPartyId(entity.getString("partyId"));
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("crm_corp_scale_identify_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("crm_corp_scale_identify_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("crm_corp_scale_identify_pwfm");
		}
		
		param.setModelType("csm");

		return param;
	}
}
