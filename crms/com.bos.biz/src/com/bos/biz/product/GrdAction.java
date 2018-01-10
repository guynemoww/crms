package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;

import commonj.sdo.DataObject;

/**
 * 评级申请
 * 
 * @author Administrator
 * 
 */
public class GrdAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.irm.TbIrmInternalRatingApply", "iraApplyId", bizId);

		param.setPartyId(entity.getString("partyId"));
		if(DictContents.ORG_MCCB.equals(GitUtil.getLegorg())){
			param.setTemplateName("Simple_cust_grade_mccb");
		}else if(DictContents.ORG_BCFM.equals(GitUtil.getLegorg())){
			param.setTemplateName("Simple_cust_grade_bcfm");
		}else if(DictContents.ORG_PWFM.equals(GitUtil.getLegorg())){
			param.setTemplateName("Simple_cust_grade_pwfm");
		}

		return param;
	}

}
