package com.bos.risk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.utp.tools.ChangeUtil;
import com.eos.foundation.common.utils.DateUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

@Bizlet("分类报表值")
public class FinanceDataUtil {

	private String partyId;
	private String customerTypeCd;

	@Bizlet("分类报表值")
	@SuppressWarnings("unchecked")
	public List<Map> getItemValue(String partyId) {
		/**
		 * 
		 007 证券公司类 008 保险公司类 009 银行类 011 其他非银行金融机构 --
		 */
		// 002 企业类
		String[] itemName002 = { "报告日期", "一、营业收入", "三、利润总额（亏损总额以“-”号填列）", "四、净利润（净亏损以“-”号填列） ", "净现金流量", "货币资金", "应收票据", "应收账款", "其他应收款", "存货",
				"流动资产合计", "固定资产", "在建工程", "无形资产", "非流动资产合计", "资产总计", "短期借款", "应付票据", "应付账款", "预收款项", "其他应付款", "流动负债合计", "长期借款", "非流动负债合计", "负债合计",
				"所有者权益合计", "负债及所有者权益总计", "资产负债率", "流动比率", "速动比率", "应收账款周转率", "存货周转率", "销售收入增长率", "销售净利率", "净利润增长率", "对外担保金额" };

		// 004 一般事业单位类
		String[] itemName004 = { "报告日期", "（一）事业类收入", "六、本年非财政补助结余", "七、转入事业基金", "净现金流量", "货币资金", "应收票据", "应收账款", "其他应收款", "存货", "流动资产合计", "固定资产", "在建工程",
				"无形资产", "非流动资产合计", "资产总计", "短期借款", "应付票据", "应付账款", "预收账款", "其他应付款", "流动负债合计", "长期借款", "非流动负债合计", "负债合计", "净资产合计", "负债和净资产合计",
				"经费自给率", "资产负债率", "收入总额/平均借入款项", "收支结余与流动负债比率", "总收入增长率", "现金比率", "经营及事业结余率", "收支结余率", "总收入" };

		// 006 担保公司
		String[] itemName006 = { "报告日期", "担保业务收入", "十、扣除资产损失后利润总额", "净利润", "净现金流量", "货币资金", "应收票据", "应收担保费", "其他应收款", "流动资产合计", "长期投资合计", "固定资产合计",
				"无形资产及其他资产合计", "资产总计", "短期借款", "预收担保费", "其他应付款", "流动负债合计", "长期借款", "长期负债合计", "负债合计", "所有者权益合计", "负债及所有者权益总计", "净资产增长率", "代偿保障率",
				"准备金充足率", "拨备覆盖率", "担保业务放大倍数", "担保代偿回收率" };

		// 012 事业单位医院类
		String[] itemName012 = { "报告日期", "一、医疗收入", "四、结转入结余分配", "转入事业基金", "净现金流量", "货币资金", "应收医疗款", "其他应收款", "存货", "流动资产合计", "固定资产", "在建工程", "无形资产",
				"非流动资产合计", "资产总计", "短期借款", "应付票据", "应付账款", "其他应付款", "流动负债合计", "长期借款", "非流动负债合计", "负债合计", "净资产合计", "负债和净资产总计", "经费自给率", "资产负债率",
				"收入总额/平均借入款项", "收支结余与流动负债比率", "总收入增长率", "现金比率", "经营及事业结余率", "收支结余率", "总收入" };

		// 013 事业单位高校类
		String[] itemName013 = { "报告日期", "一、本期收入", "六、本年非财政补助结余", "七、转入事业基金", "净现金流量", "货币资金", "应收票据", "应收账款", "其他应收款", "存货", "流动资产合计", "固定资产", "在建工程",
				"无形资产", "非流动资产合计", "资产总计", "短期借款", "应付票据", "应付账款", "预收账款", "其他应付款", "流动负债合计", "长期借款", "非流动负债合计", "负债合计", "净资产合计", "负债和净资产总计",
				"经费自给率", "资产负债率", "收入总额/平均借入款项", "收支结余与流动负债比率", "总收入增长率", "现金比率", "经营及事业结余率", "收支结余率", "总收入" };

		// 014 事业单位中小学类
		String[] itemName014 = { "报告日期", "收入总计", "本年非财政补助结余", "转入事业基金", "净现金流量", "货币资金", "应收票据", "应收账款", "其他应收款", "存货", "流动资产合计", "固定资产", "在建工程",
				"无形资产", "非流动资产合计", "资产总计", "短期借款", "应付账款", "其他应付款", "流动负债合计", "长期借款", "非流动负债合计", "负债合计", "净资产合计", "负债和净资产总计", "经费自给率", "资产负债率",
				"收入总额/平均借入款项", "收支结余与流动负债比率", "总收入增长率", "现金比率", "经营及事业结余率", "收支结余率", "总收入" };

		String[] itemNames = {};

		ArrayList<Map> sheetResult = new ArrayList<Map>();

		Object[] result = DatabaseExt.queryByNamedSql("default", "com.bos.risk.sort.queryCurrFinanceData", partyId);
		if (result.length == 0) {
			return sheetResult;
		}
		String customerTypeCd = (String) ((Map) result[0]).get("customerTypeCd");
		Date currFinanceDate = (Date) ((Map) result[0]).get("financeDeadline");
		this.partyId = partyId;
		this.customerTypeCd = customerTypeCd;
		if ("002".equals(customerTypeCd)) {
			itemNames = itemName002;
		} else if ("004".equals(customerTypeCd)) {
			itemNames = itemName004;
		} else if ("006".equals(customerTypeCd)) {
			itemNames = itemName006;
		} else if ("012".equals(customerTypeCd)) {
			itemNames = itemName012;
		} else if ("013".equals(customerTypeCd)) {
			itemNames = itemName013;
		} else if ("014".equals(customerTypeCd)) {
			itemNames = itemName014;
		}

		if (itemNames.length == 0) {
			return sheetResult;
		}

		BigDecimal currValue = new BigDecimal(0);
		BigDecimal lastOneValue = new BigDecimal(0);
		BigDecimal lastTwoValue = new BigDecimal(0);
		BigDecimal lastThreeValue = new BigDecimal(0);

		Date lastOneFinanceDate = getEndDateOfYear(-1);// 去年
		Date lastTwoFinanceDate = getEndDateOfYear(-2);// 前年
		Date lastThreeFinanceDate = getEndDateOfYear(-3);// 大前年

		// 结果集第一行
		HashMap map = new HashMap();

		map.put("itemName", "");// 项目名：报表日期
		map.put("currValue", "近期财务状况");// 当前值
		map.put("lastOneValue", ChangeUtil.formatDate(lastOneFinanceDate, "yyyy") + "年末财务状况");// 前一年年末值
		map.put("lastTwoValue", ChangeUtil.formatDate(lastTwoFinanceDate, "yyyy") + "年末财务状况");// 前二年年末值
		map.put("lastThreeValue", ChangeUtil.formatDate(lastThreeFinanceDate, "yyyy") + "年末财务状况");// 前三年年末值
		sheetResult.add(map);

		map = new HashMap();
		map.put("itemName", itemNames[0]);// 项目名：报表日期
		if (currFinanceDate.compareTo(lastOneFinanceDate) != 0) {
			map.put("currValue", ChangeUtil.formatDate(currFinanceDate, "yyyy年MM月dd日"));// 当前值
		}
		map.put("lastOneValue", ChangeUtil.formatDate(lastOneFinanceDate, "yyyy年MM月dd日"));// 前一年年末值
		map.put("lastTwoValue", ChangeUtil.formatDate(lastTwoFinanceDate, "yyyy年MM月dd日"));// 前二年年末值
		map.put("lastThreeValue", ChangeUtil.formatDate(lastThreeFinanceDate, "yyyy年MM月dd日"));// 前三年年末值
		sheetResult.add(map);

		for (int i = 1; i < itemNames.length; i++) {

			// 赋值放入结果集
			map = new HashMap();
			// 货币资金年末数-货币资金年初数=净现金流量
			if ("净现金流量".equals(itemNames[i])) {
				currValue = getValue(null, null, "货币资金");
				lastOneValue = getValue("1", lastOneFinanceDate, "货币资金");
				lastTwoValue = getValue("1", lastTwoFinanceDate, "货币资金");
				lastThreeValue = getValue("1", lastThreeFinanceDate, "货币资金");
				BigDecimal lastFourValue = getValue("1", lastThreeFinanceDate, "货币资金");
				map.put("itemName", itemNames[i]);// 项目名
				if (currFinanceDate.compareTo(lastOneFinanceDate) != 0) {
					map.put("currValue", mySubtractMath(currValue, lastOneValue));// 当前值
				}
				map.put("lastOneValue", mySubtractMath(lastOneValue, lastTwoValue));// 前一年年末值
				map.put("lastTwoValue", mySubtractMath(lastTwoValue, lastThreeValue));// 前二年年末值
				map.put("lastThreeValue", mySubtractMath(lastThreeValue, lastFourValue));// 前三年年末值
			} else {
				String name = formatName(itemNames[i]);
				currValue = getValue(null, null, name);
				lastOneValue = getValue("1", lastOneFinanceDate,name);
				lastTwoValue = getValue("1", lastTwoFinanceDate, name);
				lastThreeValue = getValue("1", lastThreeFinanceDate, name);

				map.put("itemName",itemNames[i]);// 项目名
				if (currFinanceDate.compareTo(lastOneFinanceDate) != 0) {
					map.put("currValue", currValue);// 当前值
				}
				map.put("lastOneValue", lastOneValue);// 前一年年末值
				map.put("lastTwoValue", lastTwoValue);// 前二年年末值
				map.put("lastThreeValue", lastThreeValue);// 前三年年末值
			}
			sheetResult.add(map);

		}

		return sheetResult;
	}

	private BigDecimal getValue(String financeTypeCd, Date financeDeadline, String itemName) {
		HashMap params = new HashMap();
		params.put("partyId", partyId);
		params.put("customerTypeCd", customerTypeCd);
		params.put("financeTypeCd", financeTypeCd);
		params.put("itemName", itemName);
		params.put("financeDeadline", financeDeadline);
		Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.risk.financeUtil.queryFinanceValue", params);
		BigDecimal currValue = (BigDecimal) objs[0];
		return currValue;
	}

	private Date getEndDateOfYear(int diff) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, diff);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DATE, 31);
		String strDate = DateUtil.format(calendar.getTime(), "yyyy-MM-dd");
		Date date = DateUtil.parse(strDate, "yyyy-MM-dd");
		return date;
	}

	private BigDecimal mySubtractMath(BigDecimal a, BigDecimal b) {
		if (a == null || b == null) {
			return null;
		} else {
			return a.subtract(b);
		}
	}

	//去除括号和标题号
	private static String formatName(String str) {
		// 一次
		int beg = str.indexOf("、");
		if (beg != -1) {
			str = str.substring(beg + 1);
		}
		// 二次
		int a = str.indexOf("（");
		int b = str.indexOf("）");
		if (a != -1 && b != -1) {
			String temp = str.substring(a, b + 1);
			str = str.replace(temp, "");
		}
		//System.out.println(str);
		return str;
	}
}
