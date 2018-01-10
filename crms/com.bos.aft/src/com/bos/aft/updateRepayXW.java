package com.bos.aft;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import oracle.sql.TIMESTAMP;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

@Bizlet("更新小贷还款计划")
public class updateRepayXW {
	
	private static Logger log = GitUtil.getLogger(DecisionUtil.class);
	
	@Bizlet("更新小贷还款计划表")
	public static String updateRepay(DataObject[] repayPlans2, Date endDate) throws EOSException {
		String flag = "";
		try {
			HashMap map = new HashMap();
			Date currentDate = GitUtil.getBusiDate();
			log.info("====///====/////======--------------" + endDate);
//			Double amt = new Double(0);
//			Double bj = new Double(0);
//			Double lx = new Double(0);
			if(null != repayPlans2 && repayPlans2.length > 0) {
				log.info("====///====/////======--------------" + repayPlans2.length);
				for(int i=0;i<repayPlans2.length;i++) {
					
					DataObject obj = (DataObject)repayPlans2[i];
					log.info(obj.getString("REPAYPLAN_CHANGE_ID") + "---" + obj.getDate("NEW_REPAY_DATE") + "---" + obj.getBigDecimal("NEW_REPAY_AMT"));
					
//					amt = obj.getBigDecimal("NEW_REPAY_AMT").doubleValue();
//					bj = obj.getBigDecimal("NEW_BJ").doubleValue();
					
					map.put("repayplanChangeId", obj.getString("REPAYPLAN_CHANGE_ID"));
					map.put("newRepayDate", obj.getDate("NEW_REPAY_DATE"));
					map.put("newRepayAmt", obj.getBigDecimal("NEW_REPAY_AMT"));
					map.put("newBj", obj.getBigDecimal("NEW_BJ"));
					map.put("newLx", obj.getBigDecimal("NEW_REPAY_AMT").doubleValue() - obj.getBigDecimal("NEW_BJ").doubleValue());
					
//					amt += amt;
//					bj += bj;
					
					int comp = endDate.compareTo(obj.getDate("NEW_REPAY_DATE"));
					if(comp < 0) {
						flag = "1";
						break;
					}
					
					int comp2 = currentDate.compareTo(obj.getDate("NEW_REPAY_DATE"));
					if(comp2 >= 0) {
						flag = "2";
						break;
					}
					
					DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateRepayXW", map);
				}

//				for(int i=0;i<repayPlans2.length;i++) {
//					
//					DataObject obj = (DataObject)repayPlans2[i];
//					log.info(obj.getString("REPAYPLAN_CHANGE_ID") + "---" + obj.getDate("NEW_REPAY_DATE") + "---" + obj.getBigDecimal("NEW_REPAY_AMT"));
//					
//					map.put("repayplanChangeId", obj.getString("REPAYPLAN_CHANGE_ID"));
//					map.put("newSybj", obj.getBigDecimal("LAST_SYBJ").doubleValue() - obj.getBigDecimal("NEW_BJ").doubleValue());
//					
//					if(i==0 || i==repayPlans2.length - 1) {
//						continue;
//					}else {
//						DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.updateRepaySYBJ", map);
//					}
//					
//				}
				
			}else {
				log.info("====///====/////======");
			}
				
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		return flag;
	}
	
	@Bizlet("查询小贷原还款计划")
	public static Object[] findRepay(String amountDetailId) throws EOSException {
		DataObject[] repays = null;
		Object[] object = null;
		try {
			HashMap map = new HashMap();
			if(null != amountDetailId && !"".equals(amountDetailId)) {
				
				map.put("amountDetailId", amountDetailId);
				object = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findOldPlan", map);
				
				if(null != object && object.length > 0) {
					log.info("------" + object.length);
					for(int i=0;i<object.length;i++) {
						DataObject obj = (DataObject)object[i];
						log.info(obj.getInt("QC") + "---" + obj.getDate("END_DATE") + "---" + obj.getBigDecimal("AMT"));
					}
				}
				
				
			}else {
				log.info("====///====/////======");
			}
				
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		return object;
	}
	
	@Bizlet("插入小贷还款计划")
	public static void insertRepay(Object[] repayPlans2,String changeId,String amountDetailId) throws EOSException {
		try {
			HashMap map = new HashMap();
			
			map.put("amountDetailId", amountDetailId);
			map.put("busDate", GitUtil.getBusiDate());
//			Object[] object1 = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findRestAmt", map);
//			DataObject obj1 = (DataObject) object1[0];
//			log.info(obj1.getBigDecimal("NEW_AMT") + "---" + obj1.getInt("NEW_QC"));
//			
//			Object[] object2 = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findStartDate", map);
//			DataObject obj2 = (DataObject) object2[0];
//			log.info("---" + obj2.getDataObject("START_DATE"));
			
			if(null != repayPlans2 && repayPlans2.length > 0) {
				log.info("====///====/////======--------------" + repayPlans2.length);
				for(int i=0;i<repayPlans2.length;i++) {
					
					DataObject obj = (DataObject)repayPlans2[i];
					log.info(obj.getInt("QC") + "---" + obj.getDate("END_DATE") + "---" + obj.getBigDecimal("AMT"));
					//map.put("UUID", GitUtil.genUUIDString());
					map.put("repayQc", obj.getInt("QC"));
					map.put("repayDate", obj.getDate("END_DATE"));
					map.put("repayAmt", obj.getBigDecimal("AMT"));
					map.put("changeId", changeId);
					map.put("repayTypeOld", "1");
//					map.put("repayTypeNew", "2");
					map.put("bj", obj.getBigDecimal("BJ"));
					map.put("lx", obj.getBigDecimal("LX"));
					map.put("days", obj.getInt("DAYS"));
					map.put("sybj", obj.getBigDecimal("SYBJ"));
					
					DatabaseExt.executeNamedSql("default", "com.bos.aft.conLoanChange.insertRepayXWOld", map);
//					DatabaseExt.executeNamedSql("default", "com.bos.aft.conLoanChange.insertRepayXWNew", map);
				}
			}else {
				log.info("====///====/////======");
			}
			
//			Object[] object3 = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findOldNotRepay", map);
//			if(null != object3 && object3.length > 0) {
//				log.info("------" + object3.length);
//				for(int i=0;i<object3.length;i++) {
//					DataObject obj3 = (DataObject)object3[i];
//					log.info(obj3.getInt("QC") + "---" + obj3.getDate("END_DATE") + "---" + obj3.getBigDecimal("AMT"));
//					map.put("repayQc", i+1);
//					map.put("repayDate", obj3.getDate("END_DATE"));
//					map.put("repayAmt", obj3.getBigDecimal("AMT"));
//					map.put("changeId", changeId);
//					map.put("repayTypeNew", "2");
//					map.put("bj", obj3.getBigDecimal("BJ"));
//					map.put("lx", obj3.getBigDecimal("LX"));
//					map.put("days", obj3.getInt("DAYS"));
//					map.put("sybj", obj3.getBigDecimal("SYBJ"));
//					
//					DatabaseExt.executeNamedSql("default", "com.bos.aft.conLoanChange.insertRepayXWNew", map);
//				}
//			}
				
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
	
	@Bizlet("查询公贷原还款计划")
	public static Object[] findRepayCor(String loanId) throws EOSException {
		DataObject[] repays = null;
		Object[] object = null;
		try {
			HashMap map = new HashMap();
			if(null != loanId && !"".equals(loanId)) {
				
				map.put("loanId", loanId);
				object = DatabaseExt.queryByNamedSql("default", "com.bos.aft.conLoanChange.findOldPlanCor", map);
				
				if(null != object && object.length > 0) {
					log.info("------" + object.length);
					for(int i=0;i<object.length;i++) {
						DataObject obj = (DataObject)object[i];
						log.info(obj.getBigDecimal("PERIODS_NUMBER") + "---" + obj.getDate("REPAY_DATE") + "---" + obj.getBigDecimal("REPAY_AMT"));
					}
				}
				
				
			}else {
				log.info("====///====/////======");
			}
				
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		return object;
	}
	
	@Bizlet("插入公贷还款计划")
	public static void insertRepayCor(Object[] repayPlans2,String changeId) throws EOSException {
		try {
			HashMap map = new HashMap();
			
			map.put("busDate", GitUtil.getBusiDate());
			
			if(null != repayPlans2 && repayPlans2.length > 0) {
				log.info("====///====/////======--------------" + repayPlans2.length);
				for(int i=0;i<repayPlans2.length;i++) {
					
					DataObject obj = (DataObject)repayPlans2[i];
					log.info(obj.getBigDecimal("PERIODS_NUMBER") + "---" + obj.getDate("REPAY_DATE") + "---" + obj.getBigDecimal("REPAY_AMT"));
					map.put("repayQc", obj.getBigDecimal("PERIODS_NUMBER"));
					map.put("repayDate", obj.getDate("REPAY_DATE"));
					map.put("repayAmt", obj.getBigDecimal("REPAY_AMT"));
					map.put("changeId", changeId);
					
					DatabaseExt.executeNamedSql("default", "com.bos.aft.conLoanChange.insertRepayCorOld", map);
				}
			}else {
				log.info("====///====/////======");
			}
	
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

}
