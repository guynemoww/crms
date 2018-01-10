package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;
import com.bos.pub.entity.name.SysTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;
import com.sun.star.uno.RuntimeException;

import commonj.sdo.DataObject;

/**
 * 业务申请
 * 
 * @author Administrator
 * 
 */
public class BizAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject bizApply = EntityUtil.getEntityById(BizTableName.TB_BIZ_APPLY, "applyId", bizId);
		String bizType = bizApply.getString("bizType");
		String applyModeType = bizApply.getString("applyModeType");
		if (applyModeType == null) {
			applyModeType = "01";
		}
		DataObject product = EntityUtil.searchEntity(SysTableName.TB_SYS_PRODUCT, "productCd", bizApply.getString("productType"));
		String superior_id = product.getString("superiorId");
		String product_cd = product.getString("productCd");
		String productDepartment = product.getString("productDepartment");
		param.setPartyId(bizApply.getString("partyId"));

		String legorg = GitUtil.getLegorgByParty(param.getPartyId());
		if (param.getPartyId() == null) {
			legorg = GitUtil.getLegorg();
		}
		// 借新还旧走的是原有产品的流程，但是数据可能与原有数据不同
		String isold = "0";
		if ("06".equals(bizApply.getString("bizHappenType"))) {
			Object[] objs = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.bizApply.bizApply.getOldAppIdForDetailApp", bizId);
			if (objs == null || objs.length == 0) {
				throw new RuntimeException("没有获取到原有合同的业务申请信息[" + bizId + "]");
			}
			DataObject oldApp = EntityUtil.getEntityById(BizTableName.TB_BIZ_APPLY, "applyId", (String) objs[0]);
			bizType = oldApp.getString("bizType");
			applyModeType = oldApp.getString("applyModeType");
			isold = "1";
			if (applyModeType == null) {
				applyModeType = "01";
			}
		}

		if ("01009001".equals(product_cd) || "01009002".equals(product_cd)) {// 绵商行的保函业务走综合授信流程
			if (DictContents.ORG_MCCB.equals(legorg)) {
				param.setTemplateName("single_biz_apply_allbh_mccb");
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_all_bcfm");
			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_person_pwfm");
			}
		} else if ("01".equals(bizType) && "01".equals(applyModeType)) {// 01 单笔单批
			if (DictContents.ORG_MCCB.equals(legorg)) {
				if("1".equals(isold)){
					param.setTemplateName("single_biz_apply_oneold_mccb");
				}else{
					param.setTemplateName("single_biz_apply_one_mccb");
				}
				
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_one_bcfm");
			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_person_pwfm");
			}
		} else if ("04".equals(bizType) && "01".equals(applyModeType)) {// 自然人
			if (DictContents.ORG_MCCB.equals(legorg)) {
				if("1".equals(isold)){
					param.setTemplateName("single_biz_apply_personold_mccb");
				}else{
					param.setTemplateName("single_biz_apply_person_mccb");
				}
				
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				if (DictContents.PRODUCT_CD_02001058.equals(bizApply.getString("productType"))) {// 微小贷独立流程
					param.setTemplateName("single_biz_apply_small_bcfm");
				} else {
					param.setTemplateName("single_biz_apply_person_bcfm");
				}

			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_person_pwfm");
			}

		} else if ("02".equals(applyModeType)) {// 07 低风险业务
			if (DictContents.ORG_MCCB.equals(legorg)) {
				if ("01006".equals(superior_id)) {// 贴现
					param.setTemplateName("Single_biz_apply_discount_mccb");
				} else if ("2".equals(productDepartment)) {// 01 单笔单批
					param.setTemplateName("Single_biz_apply_lowone_mccb");
				} else if ("5".equals(productDepartment)) {// 自然人
					param.setTemplateName("Single_biz_apply_lowperson_mccb");
				} else {
					param.setTemplateName("Single_biz_apply_lowrisk_mccb");
				}
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				param.setTemplateName("Single_biz_apply_lowrisk_bcfm");
			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_person_pwfm");
			}
		} else if ("02".equals(bizType)) {// 02 综合授信
			if (DictContents.ORG_MCCB.equals(legorg)) {
				if("1".equals(isold)){
					param.setTemplateName("single_biz_apply_allold_mccb");
				}else{
					param.setTemplateName("single_biz_apply_all_mccb");
				}
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_all_bcfm");
			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_person_pwfm");
			}
		} else if ("03".equals(bizType)) {// 集团授信
			if (DictContents.ORG_MCCB.equals(legorg)) {
				param.setTemplateName("group_cust_biz_apply_mccb");
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				param.setTemplateName("group_cust_biz_apply_bcfm");
			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("group_cust_biz_apply_pwfm");
			}

		} else if ("05".equals(bizType) || "06".equals(bizType)) {// 集团成员授信
			if (DictContents.ORG_MCCB.equals(legorg)) {
				param.setTemplateName("biz_apply_member_mccb");
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				param.setTemplateName("biz_apply_member_bcfm");
			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("biz_apply_member_pwfm");
			}

			param.setBatch(true);
			String userCode = (String) bizApply.get("userNum");
			param.setUserCode(userCode);
			param.setUserName(GitUtil.getUserInfo(userCode)[0].getString("empName"));

			String orgCode = (String) bizApply.get("orgNum");
			param.setOrgCode(orgCode);
			param.setOrgName(GitUtil.getOrgInfo(orgCode)[0].getString("orgname"));
		} else {
			if (DictContents.ORG_MCCB.equals(legorg)) {
				if("1".equals(isold)){
					param.setTemplateName("single_biz_apply_oneold_mccb");
				}else{
					param.setTemplateName("single_biz_apply_one_mccb");
				}
			} else if (DictContents.ORG_BCFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_manager_bcfm");
			} else if (DictContents.ORG_PWFM.equals(legorg)) {
				param.setTemplateName("single_biz_apply_manager_pwfm");
			}
		}
		return param;
	}
}
