/**
 * 
 */
package com.bos.accInfo;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.EosUtil;
import com.bos.pub.GitUtil;
import com.eos.data.datacontext.UserObject;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.sun.star.uno.RuntimeException;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-10-31 10:54:18
 *
 */
@Bizlet("")
public class AccountIdeaBook {
	
	@Bizlet("")
	public HashMap getConInfoBook(String indexid,String applyId,String changeId) {
		if (null == applyId || "".equals(applyId)) {
			throw new EOSException("业务ID为空");
		}
		HashMap map = new HashMap();
		HashMap mapbz = new HashMap();
		mapbz.put("applyId", applyId);
		// 业务合同业务信息
		/*if(applyId == null || "".equals(applyId)){
			Object[] bizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getBookApplyID", contractId);
			if (null != bizDataObject && bizDataObject.length > 0) {
				DataObject bizData = (DataObject) bizDataObject[0];
				applyId = bizData.getString("APPLY_ID");
			}
		}*/
		if("0".equals(indexid)){
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
			}
			daik = allamt-cdhp-myrz;
			map.put("PARTY_NAME", party_name);
			map.put("ORGNAME", orgname);
			map.put("allamt", allamt);
			map.put("cdhp", cdhp);
			map.put("myrz", myrz);
			map.put("daik", daik);
		}else if("1".equals(indexid)){//查询具体明细
			Object[] info1 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getApproveBookInfo", applyId);
			DataObject[] obj2 = new DataObject[info1.length];
			for (int i = 0; i < info1.length; i++) {
				DataObject detailObj = (DataObject) info1[i];
				
				String CYCLE_UNIT = detailObj.getString("CYCLE_UNIT");
				if(CYCLE_UNIT != null && !"".equals(CYCLE_UNIT)){
					CYCLE_UNIT = BusinessDictUtil.getDictName("XD_GGCD6009",CYCLE_UNIT);
					detailObj.set("CREDIT_TERM", detailObj.get("CREDIT_TERM")+CYCLE_UNIT);
				}
				info1[i] = detailObj;
			}
			map.put("info1", info1);
		}else if("2".equals(indexid)){//抵质押明细
			Object[] info2 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getSXMortgageBasic", mapbz);
			map.put("info2", info2);
		}else if("3".equals(indexid)){//保证人明细
			Object[] info3 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getSXGuaranteeBasic", mapbz);
			map.put("info3", info3);
		}else if("4".equals(indexid)){//保证金明细
			Object[] info4 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getCashDepositList", applyId);
			map.put("info4", info4);
		}else if("5".equals(indexid)){//历史审批意见
			String processId = null;
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
//			Object[] bizDataObject = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getBookWorkprocessid", applyId);
//			if (null != bizDataObject && bizDataObject.length > 0) {
//				DataObject bizData = (DataObject) bizDataObject[0];
//				processId = bizData.getString("PROCESS_ID");
//			}
			Map map2 = new HashMap();
			map2.put("processInstId", processId);
			Object[] info5 = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.conApply.accountIdeaBook.getBookWorkInfo", map2);
			if (null != info5 && info5.length > 1) {
				Object[] info6 = new Object[2];
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
				map.put("info5", info6);
			}else{
				map.put("info5", info5);
			}
			
		}
		
		map.put("applyId", applyId);
		return map;
	}
}
