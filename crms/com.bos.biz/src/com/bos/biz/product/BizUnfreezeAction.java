package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;

import commonj.sdo.DataObject;

/**
 * 解冻
 * 
 * @author Administrator
 * 
 */
public class BizUnfreezeAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		//DataObject entity = EntityUtil.getEntityById("com.bos.dataset.biz.TbBizApply", "applyId", bizId);
		DataObject entity = EntityUtil.getEntityById(BizTableName.TB_BIZ_AMOUNT_APPROVE, "amountId", bizId);
		
		param.setPartyId(entity.getString("partyId"));
		param.setModelType("irm");
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("cust_irm_jd_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("cust_irm_jd_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("cust_irm_jd_pwfm");
		}
		return param;
	}

}
