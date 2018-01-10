package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;

import commonj.sdo.DataObject;

/**
 * 额度申请
 * 
 * @author Administrator
 * 
 */
public class IrmAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.crd.TbCrdThirdPartyLimit", "limitId", bizId);

		boolean isManager = isManager();
		param.setPartyId(entity.getString("partyId"));
		String limitType = entity.getString("limitType");
		String legorg = GitUtil.getLegorg();
		// XD_SXYW0228
		if ("08".equals(limitType)) {// 担保额度
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("third_cust_grante_apply_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("third_cust_grante_apply_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("third_cust_grante_apply_pwfm");
			}
		} else if ("09".equals(limitType)) {// 项目额度
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("third_cust_project_apply_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("third_cust_project_apply_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("third_cust_project_apply_pwfm");
			}
		} else {
			// 同业额度
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("fina_cust_irm_apply_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fina_cust_irm_apply_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fina_cust_irm_apply_pwfm");
			}
		}
		return param;
	}
}
