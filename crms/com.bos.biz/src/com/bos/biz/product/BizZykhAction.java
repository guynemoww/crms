package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;
import commonj.sdo.DataObject;

public class BizZykhAction extends AProcessAction{

	@Override
	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject tbCdZykhApply = EntityUtil.getEntityById(BizTableName.TB_CD_ZYKH_APPRLY, "zykhId", bizId);
		
		param.setPartyId(tbCdZykhApply.getString("partyId"));
		//流程待定
		param.setModelType("biz");
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("com.bos.bps.biz.biz_apply_cdkh_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("com.bos.bps.biz.biz_apply_cdkh_mccb");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("com.bos.bps.biz.biz_apply_cdkh_mccb");
		}
		return param;
	}

}
