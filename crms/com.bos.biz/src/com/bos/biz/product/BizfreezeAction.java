package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;

import commonj.sdo.DataObject;

/**
 * 冻结
 * 
 * @author Administrator
 * 
 */
public class BizfreezeAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		//DataObject entity = EntityUtil.getEntityById(BizTableName.TB_BIZ_JD_DJ_FLOW, "frzNum", bizId);
		DataObject amountApprove = EntityUtil.getEntityById(BizTableName.TB_BIZ_AMOUNT_APPROVE, "amountId", bizId);
		
		param.setPartyId(amountApprove.getString("partyId"));
		//流程待定
		param.setModelType("irm");
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("fina_cust_irm_control_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("fina_cust_irm_control_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("fina_cust_irm_control_pwfm");
		}
		return param;
	}

}
