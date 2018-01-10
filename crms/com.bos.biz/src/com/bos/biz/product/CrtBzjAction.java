package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.eos.foundation.database.DatabaseExt;

import commonj.sdo.DataObject;

/**
 * 补足保证金
 * 
 * @author Administrator
 * 
 */
public class CrtBzjAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.crt.TbConContractInfo", "contractId", bizId);

		/*String amountDetailId = entity.getString("amountDetailId");
		String bizType = null;
		if(amountDetailId != null && amountDetailId != ""){
			Object[] bizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.bizApply.bizApply.queryBiztypeConContract", amountDetailId);
			if (null != bizDataObject && bizDataObject.length > 0) {
				DataObject bizData = (DataObject) bizDataObject[0];
				bizType = bizData.getString("BIZ_TYPE");
			}
		}*/
		param.setPartyId(entity.getString("partyId"));
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			/*if("01".equals(bizType)){//01  单笔单批
				param.setTemplateName("contract_bcbzj_tzxqy_mccb");
			}else if("02".equals(bizType)){//02	综合授信
				param.setTemplateName("contract_bcbzj_tzall_mccb");
			}else if("04".equals(bizType)){// 04自然人
				param.setTemplateName("contract_bcbzj_tzperson_mccb");
			}else{
				param.setTemplateName("contract_bcbzj_tz_mccb");
			}*/
			param.setTemplateName("contract_bcbzj_tzxqy_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("contract_bcbzj_tz_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("contract_bcbzj_tz_pwfm");
		}
		
		param.setModelType("crt");

		return param;
	}
}
