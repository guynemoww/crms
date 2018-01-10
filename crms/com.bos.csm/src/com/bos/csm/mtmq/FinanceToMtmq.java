/**
 * 
 */
package com.bos.csm.mtmq;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.service.request.EsbBodyMtmqRqBalShetArray;
import com.bos.pub.socket.service.request.EsbBodyMtmqRqCashFlowStmtArray;
import com.bos.pub.socket.service.request.EsbBodyMtmqRqPftStmtArray;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author kf_xdxt22
 * @date 2016-02-29 09:15:45
 *
 */
@Bizlet("")
public class FinanceToMtmq {
	@Bizlet("")
	public void setvalueFinance(String Financeid,
			List<EsbBodyMtmqRqBalShetArray> list,
			String financialStatementSortCd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Financeid", Financeid);
		map.put("financialStatementSortCd", financialStatementSortCd);
		Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.csm.mtmq.toMtmq.findfinancialStatementId", map);
		if (tmp == null || tmp.length == 0 || list==null||list.size()==0) {
			return;
		}
		DataObject StatementData = DataObjectUtil
				.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");
		Date date = GitUtil.getBusiDate();
		String findfinancialStatementId = ((DataObject) tmp[0])
				.getString("FINANCIAL_STATEMENT_ID");
		for (int i = 0; i < list.size(); i++) {
			String AcctCd = list.get(i).getAcctCd();
			String PrdBegNum = list.get(i).getPrdBegNum();
			String PrdEndNum = list.get(i).getPrdEndNum();
			StatementData.set("financeId", Financeid);
			StatementData.set("financialStatementId", findfinancialStatementId);
			StatementData.set("projectCd", AcctCd);
			StatementData.set("projectValue", PrdEndNum);
			StatementData.set("preTotalValue", PrdBegNum);
			StatementData.set("createTime", date);
			StatementData.set("updateTime", date);
			DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, StatementData);
		}

	}
	@Bizlet("")
	public void setvalueFinance2(String Financeid,
			List<EsbBodyMtmqRqCashFlowStmtArray> list,
				 
			String financialStatementSortCd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Financeid", Financeid);
		map.put("financialStatementSortCd", financialStatementSortCd);
		Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.csm.mtmq.toMtmq.findfinancialStatementId", map);
		if (tmp == null || tmp.length == 0 || list==null||list.size()==0) {
			return;
		}
		DataObject StatementData = DataObjectUtil
				.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");
		Date date = GitUtil.getBusiDate();
		String findfinancialStatementId = ((DataObject) tmp[0])
				.getString("FINANCIAL_STATEMENT_ID");
		for (int i = 0; i < list.size(); i++) {
			String AcctCd = list.get(i).getAcctCd();
			String PrdBegNum = list.get(i).getPrdBegNum();
			String PrdEndNum = list.get(i).getPrdEndNum();
			StatementData.set("financeId", Financeid);
			StatementData.set("financialStatementId", findfinancialStatementId);
			StatementData.set("projectCd", AcctCd);
			StatementData.set("preTotalValue", PrdEndNum);
			StatementData.set("projectValue", PrdBegNum);
			StatementData.set("createTime", date);
			StatementData.set("updateTime", date);
			DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, StatementData);
		}

	}
	@Bizlet("")
	public void setvalueFinance3(String Financeid,
			List<EsbBodyMtmqRqPftStmtArray> list,
			String financialStatementSortCd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Financeid", Financeid);
		map.put("financialStatementSortCd", financialStatementSortCd);
		Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.csm.mtmq.toMtmq.findfinancialStatementId", map);
		if (tmp == null || tmp.length == 0 || list==null||list.size()==0) {
			return;
		}
		DataObject StatementData = DataObjectUtil
				.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");
		Date date = GitUtil.getBusiDate();
		String findfinancialStatementId = ((DataObject) tmp[0])
				.getString("FINANCIAL_STATEMENT_ID");
		for (int i = 0; i < list.size(); i++) {
			String AcctCd = list.get(i).getAcctCd();
			String PrdBegNum = list.get(i).getPrdBegNum();
			String PrdEndNum = list.get(i).getPrdEndNum();
			StatementData.set("financeId", Financeid);
			StatementData.set("financialStatementId", findfinancialStatementId);
			StatementData.set("projectCd", AcctCd);
			StatementData.set("preTotalValue", PrdEndNum);
			StatementData.set("projectValue", PrdBegNum);
			StatementData.set("createTime", date);
			StatementData.set("updateTime", date);
			DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, StatementData);
		}

	}
}
