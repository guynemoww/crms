package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.ConTableName;
import com.bos.pub.entity.name.AssetsTableName;

import commonj.sdo.DataObject;

/**
 * 不良资产逆移交
 * 
 * @author Administrator
 * 
 */
public class AssetRetransferAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_RETRANSFER, "id", bizId);
		DataObject con = EntityUtil.getEntityById(ConTableName.TB_CON_CONTRACT_INFO, "contractId", entity.getString("contractId"));
		DataObject org = EntityUtil.searchEntity("com.bos.utp.dataset.organization.OmOrganization", "orgcode", con.getString("orgNum"));
		if (org == null) {
			throw new RuntimeException("获取合同机构信息错误[" + con.getString("orgNum") + "]");
		}
		param.setPartyId(con.getString("partyId"));
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("asset_reverseTran_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("asset_reverseTran_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("asset_reverseTran_pwfm");
		}
		param.setModelType("asset");
		param.putRelaMap("belongOrg", org.get("orglevel"));
		param.putRelaMap("sureOrgCd", org.get("orgcode"));
		return param;
	}
}
