package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.eos.foundation.database.DatabaseExt;
import com.sun.star.uno.RuntimeException;

import commonj.sdo.DataObject;

public class PayBack extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);
		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.pay.TbLoanInfo", "loanId", bizId);
		
		String product_type = entity.getString("productType");
		DataObject productentity = EntityUtil.getEntityById("com.bos.pub.product.TbSysProduct", "productId", product_type);
		String productDepartment = productentity.getString("productDepartment");
		
		String loanId = entity.getString("loanId");
		String contractId = entity.getString("contractId");
		param.setPartyId(entity.getString("partyId"));
		String legorg = GitUtil.getLegorg();
		
		if("4".equals(productDepartment)){//4 国际业务部
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("repayback_gj_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("repayback_biz_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("repayback_biz_pwfm");
			}
		}else if("5".equals(productDepartment)){//5 个人贷款中心
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("repayback_person_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("repayback_biz_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("repayback_biz_pwfm");
			}
		}else{//1 公司业务部，2 小企业信贷中心 ，3 金融市场部
			if(DictContents.ORG_MCCB.equals(legorg)){
				String bizType = null;
				Object[] bizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.product.queryBiztypeChange", contractId);
				if (null != bizDataObject && bizDataObject.length > 0) {
					DataObject bizData = (DataObject) bizDataObject[0];
					bizType = bizData.getString("BIZ_TYPE");
				}
				if(bizType == null || "".equals(bizType)){
					throw new RuntimeException("未获取到业务性质，请联系管理员！出账编号："+loanId);
				}else if("01".equals(bizType)){
					param.setTemplateName("repayback_xqy_mccb");
				}else{
					param.setTemplateName("repayback_biz_mccb");
				}
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("repayback_biz_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("repayback_biz_pwfm");
			}
		}
		param.setModelType("pay");
		return param;
	}

}
