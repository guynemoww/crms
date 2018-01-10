package com.bos.biz.product;

import com.bos.bizApply.AProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;
import com.sun.star.uno.RuntimeException;

import commonj.sdo.DataObject;

/**
 * 出账放款
 * 
 * @author Administrator
 * 
 */
public class PayAction extends AProcessAction {

	public ProcessParam action(String bizId) {
		ProcessParam param = new ProcessParam(bizId);

		DataObject entity = EntityUtil.getEntityById("com.bos.dataset.pay.TbLoanInfo", "loanId", bizId);
		String product_type = entity.getString("productType");
		DataObject productentity = EntityUtil.getEntityById("com.bos.pub.product.TbSysProduct", "productId", product_type);
		String productDepartment = productentity.getString("productDepartment");
		String loanId = entity.getString("loanId");
		
		String contractId = entity.getString("contractId");
		DataObject contract_info = EntityUtil.getEntityById("com.bos.dataset.crt.TbConContractInfo", "contractId", contractId);
		String agricul_loans = contract_info.getString("agriculLoans");
		
		param.setPartyId(entity.getString("partyId"));
		String legorg = GitUtil.getLegorg();
		if("4".equals(productDepartment)){//4 国际业务部
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("payBiz_gj_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("payBiz_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("payBiz_pwfm");
			}
		}else if("5".equals(productDepartment)){//5 个人贷款中心
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("payBiz_person_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("payBiz_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("payBiz_pwfm");
			}
		}else if("3".equals(productDepartment)){//金融市场部
			if(DictContents.ORG_MCCB.equals(legorg)){
				param.setTemplateName("payBiz_tx_mccb");
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("payBiz_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("payBiz_pwfm");
			}
		}else{//1 公司业务部，2 小企业信贷中心 
			if(DictContents.ORG_MCCB.equals(legorg)){
				String bizType = null;
				Object[] bizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.product.queryBiztypeChange", contractId);
				if (null != bizDataObject && bizDataObject.length > 0) {
					DataObject bizData = (DataObject) bizDataObject[0];
					bizType = bizData.getString("BIZ_TYPE");
					String biz_happen_type = bizData.getString("BIZ_HAPPEN_TYPE");
					if("06".equals(biz_happen_type)){// 借新还旧走的是原有产品的流程，但是数据可能与原有数据不同
						String applyId = bizData.getString("APPLY_ID");
						Object[] objs = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.bizApply.bizApply.getOldAppIdForDetailApp", applyId);
						if (objs == null || objs.length == 0) {
							throw new RuntimeException("没有获取到原有合同的业务申请信息[" + applyId + "]");
						}
						DataObject oldApp = EntityUtil.getEntityById(BizTableName.TB_BIZ_APPLY, "applyId", (String) objs[0]);
						bizType = oldApp.getString("bizType");
						/*contractId = bizData.getString("OLD_CONTRACT_ID");
						Object[] oldBizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.product.queryBiztypeChange", contractId);
						if (null != oldBizDataObject && oldBizDataObject.length > 0) {
							DataObject oldBizData = (DataObject) oldBizDataObject[0];
							bizType = oldBizData.getString("BIZ_TYPE");
						}*/
					}
				}
				if(bizType == null || "".equals(bizType)){
					throw new RuntimeException("未获取到业务性质，请联系管理员！出账编号："+loanId);
				}else if("01".equals(bizType)){
					param.setTemplateName("payBiz_xqy_mccb");
				}else{
					param.setTemplateName("payBiz_mccb");
				}
			}else if(DictContents.ORG_BCFM.equals(legorg)){
				param.setTemplateName("payBiz_bcfm");
			}else if(DictContents.ORG_PWFM.equals(legorg)){
				param.setTemplateName("payBiz_pwfm");
			}
		}
		
		String isPiao = "0";
		if("01006002".equals(product_type)||"01006001".equals(product_type)||"01008001".equals(product_type)){
			isPiao = "1";//贴现、票据
		}
		if("4".equals(productDepartment)){//4 国际业务部
			if("01007014".equals(product_type)||"01007013".equals(product_type)||
			   "01007012".equals(product_type)||"01007010".equals(product_type)){
				isPiao = "1";
			}
		}
		String isLoans = "0";//是否是融单0：否 1：是
		if("1".equals(agricul_loans)){
			isPiao = "1";
			isLoans = "1";
		}
		// 授权参数
		param.putRelaMap("authAmt", entity.getBigDecimal("loanAmt"));
		param.putRelaMap("productType", entity.getString("productType"));
		param.putRelaMap("isLow", "0");
		param.putRelaMap("isPiao", isPiao);
		param.putRelaMap("isLoans", isLoans);
		return param;
	}
}
