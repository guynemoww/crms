package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;

import commonj.sdo.DataObject;

/**
 * 处置方案
 * 
 * @author Administrator
 * 
 */
public class AssetAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_HANDLE_PLAN, "id", bizId);

		param.setPartyId(entity.getString("partyId"));
		param.setAbsenceParty(param.getPartyId() == null);
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("asset_verif_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("asset_verif_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("asset_verif_pwfm");
		}

		return param;
	}

}
