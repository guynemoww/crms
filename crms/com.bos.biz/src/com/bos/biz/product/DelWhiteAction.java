package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;

import commonj.sdo.DataObject;

/**
 * 添加白名单流程
 * 
 * @author Administrator
 * 
 */
public class DelWhiteAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);
		param.setModelType("biz");
		String legorg = GitUtil.getLegorg();
		if(DictContents.ORG_MCCB.equals(legorg)){
			param.setTemplateName("biz_apply_bmdyc_mccb");
		}else if(DictContents.ORG_BCFM.equals(legorg)){
			param.setTemplateName("biz_apply_bmdyc_bcfm");
		}else if(DictContents.ORG_PWFM.equals(legorg)){
			param.setTemplateName("biz_apply_bmdyc_pwfm");
		}
		return param;
	}

}
