package com.bos.acc;

import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author jiangzhan
 * @date 2016-05-26 09:52:14
 *
 */
@Bizlet("")
public class AccFinanceChange {
	public static TraceLogger logger = new TraceLogger(AccFinanceChange.class);
	
	@Bizlet("根据业务主键删除财报信息")
	public static String delFinancesByBizId(String bizId) throws Throwable{
		
		String msg = "";
		//根据业务主键去查财报
		DataObject accFinance = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccCustomerFinance");
		accFinance.setString("partyId", bizId);
		DataObject[] financeObj = DatabaseUtil.queryEntitiesByTemplate("default", accFinance);
		if (null == financeObj || financeObj.length == 0) {
			msg = "没有查到财务报表信息！";
			logger.info("------根据业务主键删除财报信息------>bizId=" + bizId);
			return msg;
		}
		
		for (int i = 0; i < financeObj.length; i++) {
			String financeId = financeObj[i].getString("financeId");
			msg = delFinanceInfo(financeId);
			if(!"".equals(msg)&&null!=msg){
				return msg;
			}
		}
		return msg;
	}
	
	
	@Bizlet("根据业务ID复制财报信息并修改业务ID")
	public static String copyFinancesByBizId(String oldBizId, String newBizId) throws Exception{
		String msg = "";
		//根据业务主键去查财报
		DataObject accFinance = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccCustomerFinance");
		accFinance.setString("partyId", oldBizId);
		DataObject[] financeObj = DatabaseUtil.queryEntitiesByTemplate("default", accFinance);
		if(null == financeObj||financeObj.length == 0){
			msg = "没有查到财务报表信息！";
			logger.info("------根据业务ID复制财报信息并修改业务ID------>oldBizId="+oldBizId);
			return msg;
		}
		
		for (int i = 0; i < financeObj.length; i++) {
			String newFinanceId = copyFinanceInfo(financeObj[i].getString("financeId"));
			//修改每个关联财报的业务ID
			accFinance.setString("financeId", newFinanceId);
			accFinance.setString("partyId", newBizId);
			DatabaseUtil.updateEntity("default", accFinance);
		}
		return msg;
	}
	
	@Bizlet("复制财报信息")
	public static String copyFinanceInfo(String financeId) throws Exception{
		
		if("".equals(financeId)||null==financeId){
			logger.info("------复制财报信息------>financeId="+financeId);
			return null;
		}
		//备份财报主表信息
		DataObject accFinance = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccCustomerFinance");
		accFinance.setString("financeId", financeId);
		DatabaseUtil.expandEntity("default", accFinance);
		accFinance.setString("financeId", null);
		DatabaseUtil.insertEntity("default", accFinance);
		//获取新的财报主键
		String newfinanceId = accFinance.getString("financeId");
		
		
		//备份财报子表信息
		DataObject accFinanceStatement = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinancialStatement");
		accFinanceStatement.setString("financeId", financeId);
		DataObject[] stateresult = DatabaseUtil.queryEntitiesByTemplate("default", accFinanceStatement);
		for (int i = 0; i < stateresult.length; i++) {
			String financialStatementId = stateresult[i].getString("financialStatementId");
			stateresult[i].setString("financialStatementId", null);
			stateresult[i].setString("financeId", newfinanceId);
			DatabaseUtil.insertEntity("default", stateresult[i]);
			String newfinancialStatementId = stateresult[i].getString("financialStatementId");
			
			Map<String ,String>map = new HashMap<String, String>();
			map.put("financeId", financeId);
			map.put("newfinanceId", newfinanceId);
			map.put("financialStatementId", financialStatementId);
			map.put("newfinancialStatementId", newfinancialStatementId);
			
			//备份财报科目值
			DatabaseExt.executeNamedSql("default", "com.bos.acc.getAccCopfinanceData.insertAccFinanceStatementDataInfo", map);
		}
		Map<String ,String>map = new HashMap<String, String>();
		map.put("financeId", financeId);
		map.put("newfinanceId", newfinanceId);
		
		Object[] result = null;
		DataObject profitDetail = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceNprofitData");
		profitDetail.setString("financeId", financeId);
		result = DatabaseUtil.queryEntitiesByTemplate("default", profitDetail);
		if(result.length>0){
			//如果是小贷业务品种，备份利润明细表数据
			DatabaseExt.executeNamedSql("default", "com.bos.acc.getAccCopfinanceData.insertAccFinanceProfitDataInfo", map);
		}
		DataObject billData = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceBillData");
		billData.setString("financeId", financeId);
		result = DatabaseUtil.queryEntitiesByTemplate("default", billData);
		if(result.length>0){
			//如果是小贷业务品种，备份利对账单汇总表数据
			DatabaseExt.executeNamedSql("default", "com.bos.acc.getAccCopfinanceData.insertAccFinanceBillDataInfo", map);
		}
		DataObject billAccount = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceBillAccount");
		billAccount.setString("financeId", financeId);
		result = DatabaseUtil.queryEntitiesByTemplate("default", billAccount);
		if(result.length>0){
			//如果是小贷业务品种，备份对账单汇总表银行账户信息
			DatabaseExt.executeNamedSql("default", "com.bos.acc.getAccCopfinanceData.insertAccFinanceBillAccountDataInfo", map);
		}
		return newfinanceId;
	}
	
	@Bizlet("删除财报信息")
	public static String delFinanceInfo(String financeId) throws Exception{
		String msg = "";
		if("".equals(financeId)||null==financeId){
			msg = "财报主键为空！";
			logger.info("------删除财报信息------>financeId="+financeId);
			return msg;
		}
		//删除对账单汇总表银行账户信息
		DataObject billAccount = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceBillAccount");
		billAccount.setString("financeId", financeId);
		DatabaseUtil.deleteByTemplate("default", billAccount);
		
		//删除对账单汇总表数据
		DataObject billData = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceBillData");
		billData.setString("financeId", financeId);
		DatabaseUtil.deleteByTemplate("default", billData);
		
		//删除利润明细表数据
		DataObject profitDetail = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceNprofitData");
		profitDetail.setString("financeId", financeId);
		DatabaseUtil.deleteByTemplate("default", profitDetail);
		
		//删除财报子表科目数据信息
		DataObject accFinanceStatementData = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinanceStatementData");
		accFinanceStatementData.setString("financeId", financeId);
		DatabaseUtil.deleteByTemplate("default", accFinanceStatementData);
		
		//删除财报子表信息
		DataObject accFinanceStatement = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccFinancialStatement");
		accFinanceStatement.setString("financeId", financeId);
		DatabaseUtil.deleteByTemplate("default", accFinanceStatement);
		
		//删除财报主表信息
		DataObject accFinance = DataObjectUtil.createDataObject("com.bos.dataset.acc.TbAccCustomerFinance");
		accFinance.setString("financeId", financeId);
		DatabaseUtil.deleteByTemplate("default", accFinance);
		
		return msg;
	}

}
