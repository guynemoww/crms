/**
 * 
 */
package com.bos.accInfo;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-11-02 15:36:58
 *
 */
@Bizlet("")
public class PrintAccountIdeaBook {


	@Bizlet("")
	public HashMap getConInfoBook(DataObject reqApply) {
		String applyId = (String) reqApply.get("applyId");
		if (null == applyId || "".equals(applyId)) {
			throw new EOSException("业务ID为空");
		}
		/*if (null == contractId || "".equals(contractId)) {
			throw new EOSException("业务ID为空");
		}
		// 业务合同业务信息
		String applyId = contractId;*/
		/*Object[] bizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getBookApplyID", contractId);
		if (null != bizDataObject && bizDataObject.length > 0) {
			DataObject bizData = (DataObject) bizDataObject[0];
			applyId = bizData.getString("APPLY_ID");
		}
		if (null == applyId || "".equals(applyId)) {
			throw new EOSException("业务ID为空");
		}*/
		HashMap map = new HashMap();
		HashMap mapbz = new HashMap();
		mapbz.put("applyId", applyId);
		//明细信息
		Object[] info1 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getApproveBookInfo", applyId);
		String party_name = "";
		String orgname = "";
		if (null != info1 && info1.length > 0) {
			DataObject detailObj = (DataObject) info1[0];
			party_name = (String) detailObj.get("PARTY_NAME");
			orgname = (String) detailObj.get("ORGNAME");
		}
		int allamt = 0;
		int cdhp = 0;//银行承兑汇票
		int myrz = 0;//贸易融资
		int daik = 0;//贷款
		DataObject[] info11 = null;
		if(info1 !=null && info1.length>0){
			info11 = new DataObject[info1.length];
			for (int i = 0; i < info1.length; i++) {
				DataObject detailObj1 = (DataObject) info1[i];
				int detail_amt = Integer.parseInt(detailObj1.getString("DETAIL_AMT"));
				allamt += detail_amt;
				String superior_id = detailObj1.getString("SUPERIOR_ID");
				if("01008".equals(superior_id)){
					cdhp += detail_amt;
				}else if("01007".equals(superior_id)){
					myrz += detail_amt;
				}
				daik = allamt-cdhp-myrz;
				
				String CYCLE_UNIT = detailObj1.getString("CYCLE_UNIT");
				if(CYCLE_UNIT != null && !"".equals(CYCLE_UNIT)){
					CYCLE_UNIT = BusinessDictUtil.getDictName("XD_GGCD6009",CYCLE_UNIT);
					detailObj1.set("CREDIT_TERM", detailObj1.get("CREDIT_TERM")+CYCLE_UNIT);
				}
				
				
				String GUARANTY_TYPE = detailObj1.getString("GUARANTY_TYPE");
				if(GUARANTY_TYPE != null && !"".equals(GUARANTY_TYPE)){
					if(GUARANTY_TYPE.indexOf(",") != -1){
						String GUARANTY[] = GUARANTY_TYPE.split(",");
						String GUARANTY_TYPE2 = "";
						for (int j = 0; j < GUARANTY.length; j++) {
							String GUARANTY1 = GUARANTY[j];
							GUARANTY_TYPE2 += BusinessDictUtil.getDictName("CDZC0005",GUARANTY1)+"，";
						}
						GUARANTY_TYPE = GUARANTY_TYPE2.substring(0, GUARANTY_TYPE2.length()-1);
					}else{
						GUARANTY_TYPE = BusinessDictUtil.getDictName("CDZC0005",GUARANTY_TYPE);
					}
					detailObj1.set("GUARANTY_TYPE", GUARANTY_TYPE);
				}
				
				String REPAYMENT_TYPE = detailObj1.getString("REPAYMENT_TYPE");
				if(REPAYMENT_TYPE != null && !"".equals(REPAYMENT_TYPE)){
					REPAYMENT_TYPE = BusinessDictUtil.getDictName("XD_SXCD1162",REPAYMENT_TYPE);
					detailObj1.set("REPAYMENT_TYPE", REPAYMENT_TYPE);
				}
				
				
				DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
				detail.set("APPROVAL_NUM", detailObj1.get("APPROVAL_NUM"));
				detail.set("PRODUCT_NAME", detailObj1.get("PRODUCT_NAME"));
				detail.set("DETAIL_AMT", detailObj1.get("DETAIL_AMT"));
				detail.set("CREDIT_TERM", detailObj1.get("CREDIT_TERM"));
				detail.set("YEAR_RATE", detailObj1.get("YEAR_RATE"));
				detail.set("GUARANTY_TYPE", detailObj1.get("GUARANTY_TYPE"));
				detail.set("REPAYMENT_TYPE", detailObj1.get("REPAYMENT_TYPE"));
				info11[i] = detail;
			}
		}else{
			info11 = new DataObject[1];
			info11[0] = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
		}
		map.put("info1", info11);
		DataObject mapobj = (DataObject) info1[0];
		mapobj.set("PARTY_NAME",party_name);
		mapobj.set("ORGNAME",orgname);
		mapobj.set("ALLAMT",allamt);
		mapobj.set("CDHP",cdhp);
		mapobj.set("MYRZ",myrz);
		mapobj.set("DAIK",daik);
		map.put("mapdata", mapobj);
		
		//抵质押明细
		Object[] info2 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getSXMortgageBasic", mapbz);
		DataObject[] info22 = null;
		if(info2.length>0){
			info22 = new DataObject[info2.length];
			for (int a = 0; a < info2.length; a++) {
				DataObject detailObj1 = (DataObject) info2[a];
				
				String SORT_TYPE = detailObj1.getString("SORT_TYPE");
				if(SORT_TYPE != null && !"".equals(SORT_TYPE)){
					SORT_TYPE = BusinessDictUtil.getDictName("XD_YWDB02",SORT_TYPE);
					detailObj1.set("SORT_TYPE", SORT_TYPE);
				}
				
				DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
				detail.set("APPROVAL_NUM", detailObj1.get("APPROVAL_NUM"));
				detail.set("SURETY_NO", detailObj1.get("SURETY_NO"));
				detail.set("PARTY_NAME", detailObj1.get("PARTY_NAME"));
				detail.set("SORT_TYPE", detailObj1.get("SORT_TYPE"));
				detail.set("ASSESS_VALUE", detailObj1.get("ASSESS_VALUE"));
				detail.set("MORTGAGE_VALUE", detailObj1.get("MORTGAGE_VALUE"));
				info22[a] = detail;
			}
		}else{
			info22 = new DataObject[1];
			info22[0] = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
		}
		map.put("info2", info22);
		
		//保证人明细
		Object[] info3 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getSXGuaranteeBasic", mapbz);
		DataObject[] info33 = null;
		if(info3.length>0){
			info33 = new DataObject[info3.length];
			for (int a = 0; a < info3.length; a++) {
				DataObject detailObj1 = (DataObject) info3[a];
				DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
				detail.set("APPROVAL_NUM", detailObj1.get("APPROVAL_NUM"));
				detail.set("PARTY_NAME", detailObj1.get("PARTY_NAME"));
				detail.set("SURETY_AMT", detailObj1.get("SURETY_AMT"));
				info33[a] = detail;
			}
		}else{
			info33 = new DataObject[1];
			info33[0] = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
		}
		map.put("info3", info33);
		
		//保证金明细
		Object[] info4 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getCashDepositList", applyId);
		DataObject[] info44= null;
		if(info4.length>0){
			info44 = new DataObject[info4.length];
			for (int a = 0; a < info4.length; a++) {
				DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
				DataObject detailObj1 = (DataObject) info4[a];
				detail.set("APPROVAL_NUM", detailObj1.get("APPROVAL_NUM"));
				detail.set("ACCT_NAME", detailObj1.get("ACCT_NAME"));
				detail.set("MARGIN_ACCOUNT", detailObj1.get("MARGIN_ACCOUNT"));
				detail.set("ACC_BALANCE", detailObj1.get("ACC_BALANCE"));
				info44[a] = detail;
			}
		}else{
			info44 = new DataObject[1];
			info44[0] = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
		}
		map.put("info4", info44);
		
		//历史审批意见
		String processId = null;
		String changeId = (String) reqApply.get("changeId");//贷后变更使用
		if(changeId != null && changeId != "" && !"null".equals(changeId)){
			Object[] changeProduct = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getBookProductDepartment", changeId);
			if (null != changeProduct && changeProduct.length > 0) {
				DataObject changeData = (DataObject) changeProduct[0];
				String loan_change_type = changeData.getString("LOAN_CHANGE_TYPE");
				String productDepartment = changeData.getString("PRODUCT_DEPARTMENT");
				if("06".equals(loan_change_type)&&"1".equals(productDepartment)){
					Object[] changeProcessData = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getBookProductProcessid", changeId);
					if (null != changeProcessData && changeProcessData.length > 0) {
						DataObject processData = (DataObject) changeProcessData[0];
						processId = processData.getString("PROCESS_ID");
					}
				}
			}
		}
		if(processId == null || "".equals(processId)){
			Object[] bizDataObject2 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getBookWorkprocessid", applyId);
			if (null != bizDataObject2 && bizDataObject2.length > 0) {
				DataObject bizData = (DataObject) bizDataObject2[0];
				processId = bizData.getString("PROCESS_ID");
			}
		}
		
		Map map2 = new HashMap();
		map2.put("processInstId", processId);
		Object[] info5 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getBookWorkInfo", map2);
		Object[] info6 = new Object[2];
		if (null != info5 && info5.length > 1) {
			info6[0] = info5[0];
			info6[1] = info5[1];
			for (int j = 0; j < info5.length; j++) {
				DataObject infoData = (DataObject) info5[j];
				String post_cd = infoData.getString("postCd");
				String conclusion = infoData.getString("conclusion");
				if("P1225".equals(post_cd) && "1".equals(conclusion)){
					info6[0] = info5[j];
				}
				if("P1245".equals(post_cd) && "1".equals(conclusion)){
					info6[1] = info5[j];
				}
			}
		}else{
			info6 = info5;
		}
		SimpleDateFormat perdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataObject[] info55= null;
		if(null != info6 && info6.length > 0){
			info55 = new DataObject[info6.length];
			for (int i = 0; i < info6.length; i++) {
				DataObject detailObj6 = (DataObject) info6[i];
				detailObj6.set("performtime", perdate.format(detailObj6.get("performtime"))+" ");
				DataObject detail = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
				detail.set("PERFORMTIME", detailObj6.get("performtime"));
				detail.set("ORG_NAME", detailObj6.get("orgName"));
				detail.set("USER_NAME", detailObj6.get("userName"));
				detail.set("ACTIVITY_NAME", detailObj6.get("activityName"));
				detail.set("OPINION", detailObj6.get("opinion"));
				
				info55[i] = detail;
			}
		}else{
			info55 = new DataObject[1];
			info55[0] = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1316Entry");
		}
		map.put("info5", info55);
		
		return map;
	}
}
