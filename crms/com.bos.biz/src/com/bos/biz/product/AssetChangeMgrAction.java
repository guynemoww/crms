package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.bos.pub.entity.name.ConTableName;

import commonj.sdo.DataObject;

/**
 * 不良资产管户权变更
 * 
 * @author Administrator
 * 
 */
public class AssetChangeMgrAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_CHANGE_MGR, "id", bizId);
		DataObject tas = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_TRANSFER, "id", entity.getString("transferId"));
		DataObject con = EntityUtil.getEntityById(ConTableName.TB_CON_CONTRACT_INFO, "contractId", tas.getString("contractId"));

		param.setPartyId(con.getString("partyId"));
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("asset_change_manager_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("asset_change_manager_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("asset_change_manager_pwfm");
		}
		param.setModelType("asset");

		return param;
	}
}
