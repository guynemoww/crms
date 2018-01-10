package com.bos.aft;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.biz.MathHelper;
import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import com.git.easyrule.util.DateHelper;

import commonj.sdo.DataObject;

@Bizlet("InitRepayPlanXwAft")
public class InitRepayPlanXwAft {
	
	private static Logger log = GitUtil.getLogger(DecisionUtil.class);
	
	/**
	 * @param cycleunit 
	 * @param startDate 
	 * @param yearrate 
	 * @param term 
	 * @param amt 
	 * 保存列表信息
	 * amt:贷款金额
	 * term:期次
	 * rate:结息周期对应利率
	 * startDate:贷款起期
	 * cycleunit:结息周期
	 * delqc:删除掉的期次
	 * 
	 **/
	@Bizlet("")
	public static Map saveList(String amountDetailId, BigDecimal amt, int term,
			BigDecimal yearrate, Date startDate, String cycleunit, int delqc, String changeId) {
		Map resultMap = new HashMap();
		BigDecimal restAmt = (BigDecimal) getRestAmtAndTerm(amountDetailId, amt, changeId)
				.get("restAmt");
		//按结息周期算对应利率
		BigDecimal rate = culrate(cycleunit, yearrate);
		int term1 = (Integer) getRestAmtAndTerm(amountDetailId, amt, changeId)
				.get("term");
		if (term1 == 0)
			term1 = term;
		//每期还款额
		BigDecimal repayAmtEvery = calRepayAmtEvery(restAmt, term1, rate);

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("changeId", changeId);
		Object[] hkjhs = DatabaseExt.queryByNamedSql("default",
				"com.bos.aft.conLoanChange.getHkjhsByAdid", paramMap);

		if (hkjhs.length == 0) {
			DataObject hkjh = DataObjectUtil
					.createDataObject("com.bos.dataset.con_cha.TbConRepayplanChange");
			for (int i = 1; i <= term; i++) {				
				BigDecimal b = new BigDecimal(i);
				hkjh.set("newPeriodsNum", b);
				hkjh.set("xgbz1", "0");
				hkjh.set("xgbz2", "0");
				hkjh.set("xgbz3", "0");
				hkjh.set("newOrOld", "2");
				hkjh.set("changeId", changeId);
				hkjh.set("repayplanChangeId", null);
				DatabaseUtil.saveEntity("default", hkjh);
			}
			/*DataObject tp = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizXwHkjh");
			tp.set("amountDetailId", amountDetailId);
			hkjhs = DatabaseUtil.queryEntitiesByTemplate("default", tp);*/

			hkjhs = DatabaseExt.queryByNamedSql("default",
					"com.bos.aft.conLoanChange.getHkjhsByAdid", paramMap);
		}
		resultMap = saveDetails(amountDetailId, amt, term, yearrate, startDate,
				cycleunit, repayAmtEvery, hkjhs, delqc, changeId);
		return resultMap;
	}

	/**
	 * 保存明细
	 * 
	 **/
	public static Map saveDetails(String amountDetailId, BigDecimal amt,
			int term, BigDecimal yearrate, Date startDate, String cycleunit,
			BigDecimal repayAmtEvery, Object[] hkjhs, int delqc, String changeId) {
		DataObject[] resultObjects = new DataObject[hkjhs.length];
		Map resultMap = new HashMap();
		String resultMsg = "1";
		//用来保存上期止期，方便计算每期天数
		Date lastEndDate = startDate;
		//保存上期的余额，计算利息
		BigDecimal lastRestAmt = amt;
		//总还款额
		BigDecimal totalAmt = new BigDecimal("0");
		//如果改了最后一期，改过后的最后一期本息和
		BigDecimal oldLastAmt = new BigDecimal("0");

		//最后一期本息和
		BigDecimal lastAmt = new BigDecimal("0");
		//更改过的期次总期数
		int changeItems = 0;
		for (int i = 0; i < hkjhs.length; i++) {
			DataObject hkjh = (DataObject) hkjhs[i];
			//给期次赋值
			BigDecimal b = new BigDecimal(i+1);
			hkjh.set("newPeriodsNum", b);
			//给”止期是否修改标志“赋值
			if (hkjh.get("xgbz1") == null) {
				hkjh.set("xgbz1", "0");
			}
			//如果删除了期次,删除期次以后的期次止期不变
			if (((BigDecimal)hkjh.get("newPeriodsNum")).intValue() - delqc > 0) {
				hkjh.set("xgbz1", "1");
			}

			//-----------------------给止期赋值-----------start-----------
			Date endDate = new Date();
			//只有止期修改标志为0的才给止期赋值
			if (hkjh.get("xgbz1").equals("0")) {
				endDate = calEndDate(startDate, ((BigDecimal)hkjh.get("newPeriodsNum")).intValue() - 1,
						cycleunit, hkjh.get("xgbz1").toString(),
						(Date) hkjh.get("newRepayDate"));
				hkjh.set("newRepayDate", endDate);
			}
			if (DateHelper.compare((Date) hkjh.get("newRepayDate"), lastEndDate) <= 0) {
				resultMsg = "第"+(i+1)+"期还款日期不合法";
				resultMap.put("resultMsg", resultMsg);
				return resultMap;
			}

			//-----------------------给止期赋值-----------end---------------

			//---------------------------------本期期天数-----------start-----------
			Integer days = 0;
			days = DateHelper.getDiffDays(lastEndDate,
					(Date) hkjh.get("newRepayDate"));
			hkjh.set("newDays", days);
			
			//第一期与起息日之间间隔小于15天报错
			if (i==0 && days < 15) {
				resultMsg = "第一期与起息日之间间隔不能小于15天";
				resultMap.put("resultMsg", resultMsg);
				return resultMap;
			}
			//-----------------------------本期天数-------------end------------

			//------------------------------本期还款额-----------start---------
			//给”还款金额是否修改标志“赋值
			if (hkjh.get("xgbz2") == null) {
				hkjh.set("xgbz2", "0");
			}
			if (hkjh.get("xgbz3") == null) {
				hkjh.set("xgbz3", "0");
			}
			//只有"还款金额是否修改标志"为0且本金修改标志为0的才给还款金额赋值
			if (hkjh.get("xgbz2").equals("0") && hkjh.get("xgbz3").equals("0")) {
				hkjh.set("newRepayAmt", MathHelper.round(repayAmtEvery, 2));
			}
			//
			//--------------------------------本期还款额-------------end------------

			//-------------------------------本期利息----------------start-------------
			BigDecimal lx = calCurRate(lastRestAmt, days, yearrate);
			hkjh.set("newLx", MathHelper.round(lx, 2));
			//---------------------------------期利息----------------end----------------

			//-------------------------------本期本金start---------------------

			//只有"本金是否修改标志"为0的才给本金赋值
			if (hkjh.get("xgbz3").equals("0")) {
				if (i != hkjhs.length - 1) {//不是最后一期
					Map<String, Object> params1 = new HashMap<String, Object>();
					params1.put("amt", (BigDecimal) hkjh.get("newRepayAmt"));
					params1.put("lx", (BigDecimal) hkjh.get("newLx"));
					BigDecimal bj = MathHelper.expressionBigDecimal("amt-lx",
							params1);
					if (bj.compareTo(new BigDecimal("0")) == -1) {
						resultMsg = "输入值不合法";
						resultMap.put("resultMsg", resultMsg);
						return resultMap;
					}
					hkjh.set("newBj", MathHelper.round(bj, 2));
				}
			} else {//如果修改了本金，本息和也得变
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("bj", (BigDecimal) hkjh.get("newBj"));
				params1.put("lx", (BigDecimal) hkjh.get("newLx"));
				BigDecimal bxh = MathHelper.expressionBigDecimal("bj+lx",
						params1);
				hkjh.set("newRepayAmt", MathHelper.round(bxh, 2));
			}
			//-------------------------------本期本金end---------------------

			//-------------------------------本期扣掉本金剩余start---------------------
			if (i == hkjhs.length - 1) {

				oldLastAmt = (BigDecimal) hkjh.get("newRepayAmt");
				hkjh.set("newSybj", new BigDecimal("0"));
				hkjh.set("newBj", MathHelper.round(lastRestAmt, 2));
				Map<String, Object> params1 = new HashMap<String, Object>();
				params1.put("bj", (BigDecimal) hkjh.get("newBj"));
				params1.put("lx", (BigDecimal) hkjh.get("newLx"));
				lastAmt = MathHelper.expressionBigDecimal("bj+lx", params1);
				hkjh.set("newRepayAmt", lastAmt);
			} else {
				Map<String, Object> params2 = new HashMap<String, Object>();
				params2.put("lastRestAmt", lastRestAmt);
				params2.put("bj", (BigDecimal) hkjh.get("newBj"));
				BigDecimal restBj = MathHelper.expressionBigDecimal(
						"lastRestAmt-bj", params2);

				if (restBj.compareTo(new BigDecimal("0")) == -1) {
					resultMsg = "输入值不合法";
					resultMap.put("resultMsg", resultMsg);
					return resultMap;
				}

				hkjh.set("newSybj", MathHelper.round(restBj, 2));
			}
			if (hkjh.get("xgbz2").equals("1") || hkjh.get("xgbz3").equals("1")) {
				changeItems++;
			}
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("totalAmt", totalAmt);
			params1.put("amt", (BigDecimal)hkjh.get("newRepayAmt"));
			totalAmt = MathHelper.expressionBigDecimal("totalAmt+amt", params1);
			//-------------------------------本期扣掉本金剩余end---------------------
			//DatabaseUtil.saveEntity("default", hkjh);
			//将本期剩余本金和止期保存下来留给下期计算利息和天数使用
			lastRestAmt = (BigDecimal) hkjh.get("newSybj");
			lastEndDate = (Date) hkjh.get("newRepayDate");
			//hkjh.set("changeId", changeId);
			//hkjh.set("newOrOld", "2");
			resultObjects[i] = hkjh;
			log.info("---" + resultObjects[i]);
		}

		resultMap.put("totalAmt", totalAmt);//还款总金额
		Map<String, Object> params5 = new HashMap<String, Object>();
		params5.put("totalAmt", totalAmt);
		params5.put("amt", amt);
		BigDecimal totalLx = MathHelper.expressionBigDecimal("totalAmt-amt",
				params5);
		resultMap.put("totalLx", totalLx);
		resultMap.put("resultMsg", resultMsg);
		resultMap.put("totalTerm", hkjhs.length);
		//最后一期
		DataObject lastterm = (DataObject) hkjhs[hkjhs.length - 1];
		//如果最后一期与每期还款额相差太大，重新计算
		//本金和本息和都没有手动改
		if (lastterm.get("xgbz2").equals("0") && lastterm.get("xgbz3").equals("0")) {
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("repayAmtEvery", repayAmtEvery);
			params1.put("amt", lastAmt);
			BigDecimal ce = MathHelper.expressionBigDecimal(
					"repayAmtEvery-amt", params1);
			if (ce.abs().compareTo(new BigDecimal("1")) == 1) {
				//平均算一下每期应还额
				Map<String, Object> params2 = new HashMap<String, Object>();
				params2.put("repayAmtEvery", repayAmtEvery);
				params2.put("changeItems", changeItems);
				params2.put("term", hkjhs.length);
				params2.put("amt", lastAmt);
				repayAmtEvery = MathHelper
						.expressionBigDecimal(
								"(repayAmtEvery*(term-changeItems-1)+amt)/(term-changeItems)",
								params2);
				resultMap = saveDetails(amountDetailId, amt, term, yearrate, startDate,
						cycleunit, repayAmtEvery, hkjhs, delqc, changeId);
			}

		}
		//如果修改了最后一期本息和，尽量向修改后的金额靠近
		if (lastterm.get("xgbz2").equals("1")) {
			DataObject hkjh = DataObjectUtil
					.createDataObject("com.bos.dataset.con_cha.TbConRepayplanChange");
			hkjh.set("newPeriodsNum", (BigDecimal) lastterm.get("newPeriodsNum"));
			hkjh.set("newOrOld", "2");
			hkjh.set("changeId", changeId);
			DatabaseUtil.expandEntityByTemplate("default", hkjh, hkjh);
			oldLastAmt = (BigDecimal) hkjh.get("newRepayAmt");

			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("oldLastAmt", oldLastAmt);
			params2.put("amt", lastAmt);
			BigDecimal ce2 = MathHelper.expressionBigDecimal("oldLastAmt-amt",
					params2);
			if (ce2.abs().compareTo(new BigDecimal("1")) == 1) {
				//平均算一下每期应还额
				Map<String, Object> params3 = new HashMap<String, Object>();
				params3.put("repayAmtEvery", repayAmtEvery);
				params3.put("changeItems", changeItems);
				params3.put("term", hkjhs.length);
				params3.put("amt", lastAmt);
				params3.put("oldamt", oldLastAmt);
				repayAmtEvery = MathHelper
						.expressionBigDecimal(
								"(repayAmtEvery*(term-changeItems-1)+amt-oldamt)/(term-changeItems-1)",
								params3);
				resultMap = saveDetails(amountDetailId, amt, term, yearrate, startDate,
						cycleunit, repayAmtEvery, hkjhs, delqc, changeId);
			}
		}
		//如果修改了最后一期本金，尽量向修改后的金额靠近
		if (lastterm.get("xgbz3").equals("1")) {
			DataObject hkjh = DataObjectUtil
					.createDataObject("com.bos.dataset.con_cha.TbConRepayplanChange");
			hkjh.set("newPeriodsNum", (BigDecimal) lastterm.get("newPeriodsNum"));
			hkjh.set("changeId", changeId);
			hkjh.set("newOrOld", "2"); 
			DatabaseUtil.expandEntityByTemplate("default", hkjh, hkjh);
			BigDecimal oldBj = (BigDecimal) hkjh.get("newBj");
			BigDecimal bj = (BigDecimal) lastterm.get("newBj");
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("oldBj", oldBj);
			params2.put("bj", bj);
			BigDecimal ce2 = MathHelper.expressionBigDecimal("oldBj-bj",
					params2);
			if (ce2.abs().compareTo(new BigDecimal("1")) == 1) {
				//平均算一下每期应还额
				Map<String, Object> params3 = new HashMap<String, Object>();
				params3.put("repayAmtEvery", repayAmtEvery);
				params3.put("changeItems", changeItems);
				params3.put("term", hkjhs.length);
				params3.put("bj", bj);
				params3.put("oldBj", oldBj);
				repayAmtEvery = MathHelper
						.expressionBigDecimal(
								"(repayAmtEvery*(term-changeItems-1)+bj-oldBj)/(term-changeItems-1)",
								params3);
				resultMap = saveDetails(amountDetailId, amt, term, yearrate, startDate,
						cycleunit, repayAmtEvery, hkjhs, delqc, changeId);
			}
		}
		DatabaseUtil.saveEntities("default", resultObjects);
		log.info("---" + resultObjects);
		return resultMap;
	}

	/**
	 * 计算本期止期
	 * startDate:贷款起期
	 * i：期次
	 * cycleunit:结息周期
	 * endDate：数据库中已存本期止期
	 * 
	 **/
	public static Date calEndDate(Date startDate, int i, String cycleunit,
			String bz1, Date endDate) {
		Date retDate = new Date();
		if (bz1.equals("1")) {//如果修改标志是1，说明数据库已有本条数据的止期且不能修改
			retDate = endDate;
		} else {//止期没有修改，重新计算止期
			if (cycleunit.equals("4")) {//结息周期：年
				retDate = DateHelper.calculateDate(startDate, i + 1, 0, 0);
			} else if (cycleunit.equals("3")) {//半年
				retDate = DateHelper
						.calculateDate(startDate, 0, 6 * (i + 1), 0);
			} else if (cycleunit.equals("2")) {//季
				retDate = DateHelper
						.calculateDate(startDate, 0, 3 * (i + 1), 0);
			} else if (cycleunit.equals("1")) {//月
				retDate = DateHelper.calculateDate(startDate, 0, i + 1, 0);
			} else {//后加的两种结息周期按月
				retDate = DateHelper.calculateDate(startDate, 0, i + 1, 0);
			}
		}
		return retDate;
	}

	/**
	 * 按结息周期算出对应的利率 年-年率除1  ；半年-年利率除2； 季-年利率除4； 月-年利率除12
	 * 
	 **/
	public static BigDecimal culrate(String cycleunit, BigDecimal yearrate) {
		BigDecimal rate = new BigDecimal(0);
		if (cycleunit.equals("4")) {//结息周期：年
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("yearrate", yearrate);
			params1.put("k", new BigDecimal(1));
			rate = MathHelper.expressionBigDecimal("yearrate/k", params1);
		} else if (cycleunit.equals("3")) {//半年
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("yearrate", yearrate);
			params1.put("k", new BigDecimal(2));
			rate = MathHelper.expressionBigDecimal("yearrate/k", params1);
		} else if (cycleunit.equals("2")) {//季
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("yearrate", yearrate);
			params1.put("k", new BigDecimal(4));
			rate = MathHelper.expressionBigDecimal("yearrate/k", params1);
		} else if (cycleunit.equals("1")) {//月
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("yearrate", yearrate);
			params1.put("k", new BigDecimal(12));
			rate = MathHelper.expressionBigDecimal("yearrate/k", params1);
		}else {//后加的两种结息周期按月
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("yearrate", yearrate);
			params1.put("k", new BigDecimal(12));
			rate = MathHelper.expressionBigDecimal("yearrate/k", params1);
		}
		return rate;
	}

	/**
	 * 根据等额本息计算每期应还金额
	 * 
	 * amt:剩余本金
	 * days:当期天数
	 * rate:每期对应利率
	 **/
	public static BigDecimal calRepayAmtEvery(BigDecimal amt, int term,
			BigDecimal rate) {
		//4.本金+利息=Y*i*(1+i)~n/((1+i)~n-1)*/
		BigDecimal dec_t1 = new BigDecimal("0.00");//(1+i)~(k-1)的值
		BigDecimal dec_t2 = new BigDecimal("0.00");//(1+i)~n的值
		BigDecimal dec_one = new BigDecimal("1.00");//临时变量1
		//1.(1+i)~n的值
		dec_t1 = MathHelper.powBigDecimal(dec_one.add(rate), term);

		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("Y", amt);//贷款总额
		params1.put("dec_t1", dec_t1);
		params1.put("i", rate);//贷款利率
		return MathHelper
				.expressionBigDecimal("Y*i*dec_t1/(dec_t1-1)", params1);
	}

	/**
	 * @param yearrate 
	 * @param days 
	 * @param amt 
	 * 计算当期利息
	 * 
	 * amt:剩余本金
	 * days:当期天数
	 * dayrate:日利率=年利率/360
	 **/
	@Bizlet("")
	public static BigDecimal calCurRate(BigDecimal amt, int days,
			BigDecimal yearrate) {
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("Y", amt);//贷款总额
		params1.put("days", days);//当期天数
		params1.put("i", yearrate);//日利率
		params1.put("t", new BigDecimal("360"));//利率依据方式：360
		return MathHelper.expressionBigDecimal("Y*days*i/t", params1);
	}

	/**
	 * @param restAmt 
	 * @param cycleunit 
	 * @param startDate 
	 * @param yearrate 
	 * @param term 
	 * @param amt 
	 * @param a 
	 * @param hkjh 
	 * 页面数据变更时调用方法
	 * 
	 * hkjh:页面修改的那条记录对应对象
	 * a: 1-止期修改  2-本息和修改  3-本金修改
	 **/
	@Bizlet("")
	public Map dataChang(String amountDetailId, DataObject hkjh, String a,
			BigDecimal amt, int term, BigDecimal yearrate, Date startDate,
			String cycleunit, int delqc, String changeId) {
		Map resultMap = new HashMap();
		log.info("changeId---" + changeId);
		DataObject tmp = DataObjectUtil
				.createDataObject("com.bos.dataset.con_cha.TbConRepayplanChange");
		tmp.set("newPeriodsNum", (BigDecimal) hkjh.get("newPeriodsNum"));
		tmp.set("newOrOld", "2");
		tmp.set("changeId", changeId);
		DatabaseUtil.expandEntityByTemplate("default", tmp, tmp);
		log.info("newRepayAmt---" + tmp.getBigDecimal("newRepayAmt"));

		//第一期首次还本
		log.info("repayplanChangeId---" + (String) tmp.get("repayplanChangeId"));
		//传进来的实体uuid为空，不知为何
		hkjh.set("repayplanChangeId", (String) tmp.get("repayplanChangeId"));
		log.info("---" + (Date) tmp.get("newRepayDate")+"---" + (BigDecimal) tmp.get("newBj")+"---" + (BigDecimal) tmp.get("newRepayAmt"));
		Date oldDate = (Date) tmp.get("newRepayDate");
		BigDecimal oldBj = (BigDecimal) tmp.get("newBj");
		BigDecimal oldAmt = (BigDecimal) tmp.get("newRepayAmt");
		String oldBz1 = (String) tmp.get("xgbz1");
		String oldBz2 = (String) tmp.get("xgbz2");
		String oldBz3 = (String) tmp.get("xgbz3");

		if (a.equals("1")) {//日期变，本金也变
			hkjh.set("xgbz1", "1");
		} else if (a.equals("2")) {//本息和和本金不能同时自定义
			hkjh.set("xgbz2", "1");
			hkjh.set("xgbz3", "0");
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("amt", (BigDecimal) hkjh.get("newRepayAmt"));
			params1.put("lx", (BigDecimal) hkjh.get("newLx"));
			BigDecimal bj = MathHelper.expressionBigDecimal("amt-lx", params1);
			hkjh.set("newBj", bj);
		} else if (a.equals("3")) {
			hkjh.set("xgbz3", "1");
			hkjh.set("xgbz2", "0");
		}
		DatabaseUtil.updateEntity("default", hkjh);
		resultMap = saveList(amountDetailId, amt, term, yearrate, startDate,
				cycleunit, delqc, changeId);
		String resultMsg = (String) resultMap.get("resultMsg");
		if (!"1".equals(resultMsg)) {
			hkjh.set("xgbz1", oldBz1);
			hkjh.set("xgbz2", oldBz2);
			hkjh.set("xgbz3", oldBz3);
			hkjh.set("newBj", oldBj);
			hkjh.set("newRepayAmt", oldAmt);
			hkjh.set("newRepayDate", oldDate);
			DatabaseUtil.updateEntity("default", hkjh);
		}
		return resultMap;
	}

	/**
	 * 计算剩余本金
	 * @param amt
	 */
	public static Map getRestAmtAndTerm(String amountDetailId, BigDecimal amt, String changeId) {
		Map<String, Object> retmap = new HashMap<String, Object>();
		int term = 0;
		BigDecimal restAmt = amt;
		DataObject tmpl = DataObjectUtil
				.createDataObject("com.bos.dataset.con_cha.TbConRepayplanChange");
		tmpl.set("changeId", changeId);
		tmpl.set("newOrOld", "2");
		DataObject[] hkjhs = DatabaseUtil.queryEntitiesByTemplate("default",
				tmpl);
		if (hkjhs != null) {
			term = hkjhs.length;
			for (DataObject hkjh : hkjhs) {
				String bz1 = hkjh.get("xgbz1").toString();//日期改变标志
				String bz2 = hkjh.get("xgbz2").toString();//本息和改变标志
				String bz3 = hkjh.get("xgbz3").toString();//本金改变标志
				if (bz2.equals("1") || bz3.equals("1")) {
					Map<String, Object> params1 = new HashMap<String, Object>();
					params1.put("restAmt", restAmt);//剩余本金
					params1.put("bj", (BigDecimal) hkjh.get("newBj"));//本金
					restAmt = MathHelper.expressionBigDecimal("restAmt-bj",
							params1);
					term -= 1;
				}
			}
		}
		retmap.put("restAmt", restAmt);
		retmap.put("term", term);
		return retmap;
	}

	/**
	 * 
	* @Title: changeFirstRepayDay 
	* @Description: 首次还款日变化时触发
	* @param amountDetailId
	* @param hkjh
	* @param a
	* @param am
	* @param term
	* @param yearrate
	* @param startDate
	* @param cycleunit
	* @param delqc
	* @return    设定文件 
	* @return Map    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年8月19日 上午12:05:50 
	* @version V1.0
	 */
	@Bizlet("")
	public Map changeFirstRepayDay(String amountDetailId, Date schkq,
			BigDecimal amt, int term, BigDecimal yearrate,
			Date startDate, String cycleunit, String changeId) {
		Map resultMap = new HashMap();

		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("newOrOld", "2");
			paramMap.put("changeId", changeId);
			Object[] hkjhs = DatabaseExt.queryByNamedSql("default",
					"com.bos.aft.conLoanChange.getHkjhsByAdid", paramMap);
			DataObject hkjh = DataObjectUtil
					.createDataObject("com.bos.dataset.con_cha.TbConRepayplanChange");
			for (int i = 0; i < hkjhs.length; i++) {
				hkjh = (DataObject)hkjhs[i];
				//置换日期
				hkjh.set("xgbz1", "0");
				if(i==0){
					hkjh.set("newRepayDate", schkq);
				}else{
					Date endDate = calEndDate(schkq, ((BigDecimal)hkjh.get("newPeriodsNum")).intValue() - 2,
							cycleunit, hkjh.get("xgbz1").toString(),
							(Date) hkjh.get("newRepayDate"));
					hkjh.set("newRepayDate",endDate);
				}
				hkjh.set("xgbz1", "1");
				hkjh.set("xgbz2", "0");
				hkjh.set("xgbz3", "0");
				hkjhs[i] = hkjh;
			}
			//按结息周期算对应利率
			BigDecimal rate = culrate(cycleunit, yearrate);
			BigDecimal repayAmtEvery = calRepayAmtEvery(amt, term, rate);
			resultMap = saveDetails(amountDetailId, amt, term, yearrate, startDate,
					cycleunit, repayAmtEvery, hkjhs, 10000, changeId);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultMsg", e.getMessage());
		}
		return resultMap;
	}

	public static void main(String[] args) {

		//BigDecimal amt , int term , BigDecimal yearrate,Date startDate,String cycleunit
		Date bed = new Date();
		Date end = new Date();
		String year = "";
		String month = "";
		String day = "";
		String nString = "";
		try {
			/* day = DateHelper.getDay(DateHelper.formatDate(bed));
			 month = DateHelper.getMonth(DateHelper.formatDate(bed));
			 year = DateHelper.getYear(DateHelper.formatDate(bed));
			 nString = year+"-"+month+"-"+day;
			// end = DateHelper.getDate(nString);
			 end = DateHelper.getDateYYYYMMDD(nString);*/
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//System.out.println(DateHelper.calculateDate(bed, 0, 0, 15));
		System.out.println((int)Math.ceil(7.0/3));
	}

}
