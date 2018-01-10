package com.bos.biz.product;

import java.util.HashMap;
import java.util.Map;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.eos.foundation.database.DatabaseExt;

import commonj.sdo.DataObject;

/**
 * 贷后变更
 * 
 * @author Administrator
 * 
 */
public class AftBAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.con_cha.TbConLoanChange", "changeId", bizId);

		//boolean isManager = isManager();
		String changeType = entity.getString("loanChangeType");

		param.setPartyId(entity.getString("partyId"));
		param.putRelaMap("loanChangeType", changeType);
		String legorg = GitUtil.getLegorg();
		
		String contractId = entity.getString("contractId");
		String bizType = null;
		String applyModeType = null;
		String productDepartment = null;
		int YQTS = 0;//逾期天数
		if(contractId != null && ("06".equals(changeType)||"01".equals(changeType)||"19".equals(changeType))){//利率、展期、期限变更
			Object[] bizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.bizApply.bizApply.queryBiztypeChange", contractId);
			if (null != bizDataObject && bizDataObject.length > 0) {
				DataObject bizData = (DataObject) bizDataObject[0];
				bizType = bizData.getString("BIZ_TYPE");
				applyModeType = bizData.getString("APPLY_MODE_TYPE");
				productDepartment = bizData.getString("PRODUCT_DEPARTMENT");
				String strYQTS = bizData.getString("YQTS");
				if(strYQTS != null && !"".equals(strYQTS)){
					YQTS = Integer.parseInt(strYQTS);
				}
			}
		}
		if(applyModeType == null){
			applyModeType = "01";
		}
		if("02".equals(changeType)){//还款方式及结息周期变更
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("fjq_week_loan_fkfs_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else if("03".equals(changeType)){//约定还款日变更
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("change_after_loan_ydfkr_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else if("04".equals(changeType)){//还款账号变更
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("change_after_loan_fkzh_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("change_after_loan_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("change_after_loan_pwfm");
			}
		}else if("10".equals(changeType)){//还本计划表变更
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("change_after_loan_fbjhbg_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else if("11".equals(changeType)){//提前/手工还款
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("change_after_loan_tqfk_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("change_after_loan_rate_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("change_after_loan_rate_pwfm");
			}
		}else if("14".equals(changeType)){//停息、终止停息
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("fjq_week_loan_tx_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else if("18".equals(changeType)){//指定内部账号还款
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("change_after_loan_zdzffk_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("change_after_loan_rate_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("change_after_loan_rate_pwfm");
			}
		}else if("19".equals(changeType)){//期限调整
			if(DictContents.ORG_MCCB.equals(legorg)){
				if(YQTS >= 90){
					if("02".equals(applyModeType)){//	低风险业务
						if("2".equals(productDepartment)){//01  单笔单批
							param.setTemplateName("change_after_loan_zqdfxone_mccb");
						}else if("5".equals(productDepartment)){// 自然人
							param.setTemplateName("change_after_loan_zqdfxperson_mccb");
						}else{
							param.setTemplateName("change_after_loan_zqdfx_mccb");
						}
					}else if("01".equals(bizType)&&"01".equals(applyModeType)){//01  单笔单批
						param.setTemplateName("change_after_loan_zqdb_mccb");
					}else if("02".equals(bizType)){//02	综合授信
						param.setTemplateName("change_after_loan_zqzh_mccb");
					}else if("04".equals(bizType)&&"01".equals(applyModeType)){// 04自然人
						param.setTemplateName("change_after_loan_zqgr_mccb");
					}else{
						param.setTemplateName("change_after_loan_zqbg_mccb");
					}
				}else{
					param.setTemplateName("fjq_week_loan_qxbg_mccb");
				}
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else if("16".equals(changeType)){//16利息调整
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("change_after_loan_lxtz_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else if("17".equals(changeType)){//17委托人收本收息账户变更
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("change_after_loan_wtrsbsx_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else if("08".equals(changeType)||"09".equals(changeType)){//08贴息主体变更,09贴息、暂停贴息
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("change_after_loan_txbg_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else if("01".equals(changeType)){//01利率变更
			if(DictContents.ORG_MCCB.equals(legorg)){
				if("02".equals(applyModeType)){//	低风险业务
					if("2".equals(productDepartment)){//01  单笔单批
						param.setTemplateName("change_after_loan_zqdfxone_mccb");
					}else if("5".equals(productDepartment)){// 自然人
						param.setTemplateName("change_after_loan_zqdfxperson_mccb");
					}else{
						param.setTemplateName("change_after_loan_zqdfx_mccb");
					}
				}else if("01".equals(bizType)&&"01".equals(applyModeType)){//01  单笔单批
					param.setTemplateName("change_after_loan_zqdb_mccb");
				}else if("02".equals(bizType)){//02	综合授信
					param.setTemplateName("change_after_loan_zqzh_mccb");
				}else if("04".equals(bizType)&&"01".equals(applyModeType)){// 04自然人
					param.setTemplateName("change_after_loan_zqgr_mccb");
				}else{
					param.setTemplateName("change_after_loan_llbg_mccb");
				}
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else if("06".equals(changeType)){//06展期
			if(DictContents.ORG_MCCB.equals(legorg)){
				if("02".equals(applyModeType)){//	低风险业务
					if("2".equals(productDepartment)){//01  单笔单批
						param.setTemplateName("change_after_loan_zqdfxone_mccb");
					}else if("5".equals(productDepartment)){// 自然人
						param.setTemplateName("change_after_loan_zqdfxperson_mccb");
					}else{
						param.setTemplateName("change_after_loan_zqdfx_mccb");
					}
				}else if("01".equals(bizType)&&"01".equals(applyModeType)){//01  单笔单批
					param.setTemplateName("change_after_loan_zqdb_mccb");
				}else if("02".equals(bizType)){//02	综合授信
					param.setTemplateName("change_after_loan_zqzh_mccb");
				}else if("04".equals(bizType)&&"01".equals(applyModeType)){// 04自然人
					param.setTemplateName("change_after_loan_zqgr_mccb");
				}else{
					param.setTemplateName("change_after_loan_zqbg_mccb");
				}
				
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}else{//12信用证修改,13保函修改,15合作商代偿
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("change_after_loan_rate_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("fjq_week_manager_pwfm");
			}
		}
		String isAftb = "0";
		if("2".equals(productDepartment)){//单笔单批
			isAftb = "1";
		}else if("5".equals(productDepartment)){//自然人
			isAftb = "2";
		}
		param.putRelaMap("isAftb", isAftb);
		
		return param;
	}
}
