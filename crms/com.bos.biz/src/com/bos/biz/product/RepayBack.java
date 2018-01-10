package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;

import commonj.sdo.DataObject;

public class RepayBack  extends AProcessAction{

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);
		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.con_cha.TbConLoanChange", "changeId", bizId);
		String changeType = entity.getString("loanChangeType");
		param.setPartyId(entity.getString("partyId"));
		param.putRelaMap("loanChangeType", changeType);
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("change_after_reback_loan_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("change_after_reback_loan_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("change_after_reback_loan_pwfm");
		}
		
		param.setModelType("aftb");
		return param;
	}

}
