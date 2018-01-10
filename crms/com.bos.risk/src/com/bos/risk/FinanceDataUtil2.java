package com.bos.risk;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.DictContents;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AccTableName;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

@Bizlet("分类报表值")
public class FinanceDataUtil2 {
	// 002 企业类
	private static final String[][] item002 = { { "报告日期", "xx_reportDate" }, { "营业收入", "00202001" },//
			{ "利润总额（亏损总额以“-”号填列）", "00202015" },//
			{ "净利润（净亏损以“-”号填列） ", "00202017" },//
			{ "净现金流量", "00201003" },//
			{ "货币资金", "00201003" }, //
			{ "应收票据", "00201007" },//
			{ "应收账款", "00201009" }, //
			{ "其他应收款", "00201017" },//
			{ "存货", "00201019" },//
			{ "流动资产合计", "00201025" },//
			{ "固定资产", "00201039" }, //
			{ "在建工程", "00201041" }, //
			{ "无形资产", "00201051" },//
			{ "非流动资产合计", "00201063", }, //
			{ "资产总计", "00201067" },//
			{ "短期借款", "00201004" }, //
			{ "应付票据", "00201008" }, //
			{ "应付账款", "00201010" }, //
			{ "预收款项", "00201012" },//
			{ "其他应付款", "00201022" },//
			{ "流动负债合计", "00201028" },//
			{ "长期借款", "00201032" },//
			{ "非流动负债合计", "00201046" }, //
			{ "负债合计", "00201048" },//
			{ "所有者权益合计", "00201066" },//
			{ "负债及所有者权益总计", "00201068" }, //
			{ "资产负债率", "ZCFZL" },//
			{ "流动比率", "LDBL" }, //
			{ "速动比率", "L3" }, //
			{ "应收账款周转率", "YSZKZZL" }, //
			{ "存货周转率", "CHZZL" }, //
			{ "销售收入增长率", "G1" }, //
			{ "销售净利率", "XSJLL" },//
			{ "净利润增长率", "JLRZZL" },//
			{ "对外担保金额", "00204051" } //
	};

	// 004 一般事业单位类
	private static final String[][] item004 = { { "报告日期", "xx_reportDate" }, { "事业类收入", "00402005" },//
			{ "本年非财政补助结余", "00402022" },//
			{ "转入事业基金", "00402025" },//
			{ "净现金流量", "00401003" },//
			{ "货币资金", "00401003" }, //
			{ "应收票据", "00401009" },//
			{ "应收账款", "00401011" }, //
			{ "其他应收款", "00401015" },//
			{ "存货", "00401017" },//
			{ "流动资产合计", "00401021" },//
			{ "固定资产", "00401027" }, //
			{ "在建工程", "00401033" }, //
			{ "无形资产", "00401035" },//
			{ "非流动资产合计", "00401045", }, //
			{ "资产总计", "00401057" },//
			{ "短期借款", "00401004" }, //
			{ "应付票据", "00401014" }, //
			{ "应付账款", "00401016" }, //
			{ "预收款项", "00401018" },//
			{ "其他应付款", "00401020" },//
			{ "流动负债合计", "00401024" },//
			{ "长期借款", "00401028" },//
			{ "非流动负债合计", "00401032" }, //
			{ "负债合计", "00401034" },//
			{ "净资产合计", "00401056" },//
			{ "负债和净资产合计", "00401058" }, //
			{ "经费自给率", "A17" },//
			{ "资产负债率", "C12" }, //
			{ "收入总额/平均借入款项", "D11" }, //
			{ "收支结余与流动负债比率", "D12" }, //
			{ "总收入增长率", "G9" }, //
			{ "现金比率", "L10" }, //
			{ "经营及事业结余率", "P18" },//
			{ "收支结余率", "P19" },//
			{ "总收入", "S5" } //
	};

	// 006 担保公司
	private static final String[][] item006 = { { "报告日期", "xx_reportDate" }, { "担保业务收入", "00602001" },//
			{ "扣除资产损失后利润总额", "00602035" },//
			{ "净利润", "00603042" },//
			{ "净现金流量", "00601003" },//
			{ "货币资金", "00601003" }, //
			{ "应收票据", "00601009" },//
			{ "应收担保费", "00601015" }, //
			{ "其他应收款", "00601021" },//
			{ "流动资产合计", "00601045" },//
			{ "长期投资合计", "00601057" },//
			{ "固定资产合计", "00601077" }, //
			{ "无形资产及其他资产合计", "00601093" },//
			{ "资产总计", "00601095", }, //
			{ "短期借款", "00601004" },//
			{ "预收担保费", "00601010" }, //
			{ "其他应付款", "00601020" }, //
			{ "流动负债合计", "00601034" }, //
			{ "长期借款", "00601040" },//
			{ "长期负债合计", "00601052" },//
			{ "负债合计", "00601054" },//
			{ "所有者权益合计", "00601088" },//
			{ "负债及所有者权益总计", "00601096" }, //
			{ "净资产增长率", "G10" },//
			{ "代偿保障率", "L15" },//
			{ "准备金充足率", null },//
			{ "拨备覆盖率", "Y3" }, //
			{ "担保业务放大倍数", "Y4" },//
			{ "担保代偿回收率", "A18" } //
	};

	// 012 事业单位医院类
	private static final String[][] item012 = { { "报告日期", "xx_reportDate" }, { "医疗收入", "01202001" },//
			{ "结转入结余分配", "01202010" },//
			{ "转入事业基金", "01201003" },//
			{ "净现金流量", "01201003" },//
			{ "货币资金", "01201003" }, //
			{ "应收医疗款", "01201011" },//
			{ "其他应收款", "01201013" },//
			{ "存货", "01201019" },//
			{ "流动资产合计", "01201025" },//
			{ "固定资产", "01201031" },//
			{ "在建工程", "01201037" },//
			{ "无形资产", "01201041" },//
			{ "非流动资产合计", "01201051" },//
			{ "资产总计", "01201057" },//
			{ "短期借款", "01201004" },//
			{ "应付票据", "01201008" },//
			{ "应付账款", "01201010" },//
			{ "其他应付款", "01201022" },//
			{ "流动负债合计", null },//
			{ "长期借款", "01201032" },//
			{ "非流动负债合计", "01201028" },//
			{ "负债合计", "01201038" },//
			{ "净资产合计", "01201056" },//
			{ "负债和净资产总计", "01201058" },//
			{ "经费自给率", "A17" },//
			{ "资产负债率", "C12" },//
			{ "收入总额/平均借入款项", "D11" },//
			{ "收支结余与流动负债比率", "D12" },//
			{ "总收入增长率", "G9" },//
			{ "现金比率", "L10" },//
			{ "经营及事业结余率", "P18" },//
			{ "收支结余率", "P19" },//
			{ "总收入", "S5" } //
	};

	// 013 事业单位高校类
	private static final String[][] item013 = { { "报告日期", "xx_reportDate" }, { "本期收入", "01302001" },//
			{ "本年非财政补助结余", "01302041" },//
			{ "转入事业基金", "01302044" },//
			{ "净现金流量", "01301003" },//
			{ "货币资金", "01301003" },//
			{ "应收票据", "01301009" },//
			{ "应收账款", "01301011" },//
			{ "其他应收款", "01301015" },//
			{ "存货", null },//
			{ "流动资产合计", "01301021" },//
			{ "固定资产", "01301027" },//
			{ "在建工程", "01301033" },//
			{ "无形资产", "01301035" },//
			{ "非流动资产合计", "01301043" },//
			{ "资产总计", "01301059" },//
			{ "短期借款", "01301004" },//
			{ "应付票据", "01301014" },//
			{ "应付账款", "01301016" },//
			{ "预收账款", "01301018" },//
			{ "其他应付款", "01301020" },//
			{ "流动负债合计", "01301024" },//
			{ "长期借款", "01301028" },//
			{ "非流动负债合计", "01301034" },//
			{ "负债合计", "01301036" },//
			{ "净资产合计", "01301058" },//
			{ "负债和净资产总计", "01301060" },//
			{ "经费自给率", "A17" },//
			{ "资产负债率", "C12" },//
			{ "收入总额/平均借入款项", "D11" },//
			{ "收支结余与流动负债比率", "D12" },//
			{ "总收入增长率", "G9" },//
			{ "现金比率", "L10" },//
			{ "经营及事业结余率", "P18" },//
			{ "收支结余率", "P19" },//
			{ "总收入", "S5" } //
	};

	// 014 事业单位中小学类
	private static final String[][] item014 = { { "报告日期", "xx_reportDate" }, { "收入总计", "01402009" },//
			{ "本年非财政补助结余", "01402026" },//
			{ "转入事业基金", "01402029" },//
			{ "净现金流量", "01401003" },//
			{ "货币资金", "01401003" },//
			{ "应收票据", "01401009" },//
			{ "应收账款", "01401011" },//
			{ "其他应收款", "01401013" },//
			{ "存货", null },//
			{ "流动资产合计", "01401019" },//
			{ "固定资产", "01401025" },//
			{ "在建工程", "01401027" },//
			{ "无形资产", "01401029" },//
			{ "非流动资产合计", "01401033" },//
			{ "资产总计", "01401055" },//
			{ "短期借款", "01401004" },//
			{ "应付账款", "01401014" },//
			{ "其他应付款", "01401016" },//
			{ "流动负债合计", "01401020" },//
			{ "长期借款", "01401024" },//
			{ "非流动负债合计", "01401030" },//
			{ "负债合计", "01401032" },//
			{ "净资产合计", "01401054" },//
			{ "负债和净资产总计", "01401056" },//
			{ "经费自给率", "A17" },//
			{ "资产负债率", "C12" },//
			{ "收入总额/平均借入款项", "D11" },//
			{ "收支结余与流动负债比率", "D12" },//
			{ "总收入增长率", "G9" },//
			{ "现金比率", "L10" },//
			{ "经营及事业结余率", "P18" },//
			{ "收支结余率", "P19" },//
			{ "总收入", "S5" } //
	};

	public String[][] getItems(String type) {
		if ("002".equals(type)) {
			return item002;
		} else if ("004".equals(type)) {
			return item004;
		} else if ("006".equals(type)) {
			return item006;
		} else if ("012".equals(type)) {
			return item012;
		} else if ("013".equals(type)) {
			return item013;
		} else if ("014".equals(type)) {
			return item014;
		}
		return null;
	}

	/**
	 * SELECT * FROM (<br/>
	 * SELECT 'index',index_code,index_name FROM TB_ACC_NFD_INDEX WHERE sheet_id IN (SELECT sheet_id FROM TB_ACC_NFD_SHEET WHERE report_id='002')<br/>
	 * UNION ALL <br/>
	 * SELECT 'item',item_code,item_name FROM TB_ACC_NFD_ITEM WHERE sheet_id IN (SELECT sheet_id FROM TB_ACC_NFD_SHEET WHERE report_id='002')<br/>
	 * ) WHERE INDEX_NAME LIKE '%应收票据%'
	 * 
	 * @param partyId
	 * @return
	 */
	@Bizlet
	public ArrayList<Map> getItemValue(String partyId) {
		ArrayList<Map> sheetResult = new ArrayList<Map>();
		DataObject[] temps = EntityUtil.searchEntitys(AccTableName.TB_ACC_CUSTOMER_FINANCE, new String[] { "partyId", partyId, "financeStatusCd", "02" }, new String[] { "financeDeadline", "DESC", "updateTime", "DESC" });
		if (temps == null || temps.length == 0) {
			return sheetResult;
		}
		Calendar cld = Calendar.getInstance();
		Object[][] finances = new Object[4][];
		cld.setTime(temps[0].getDate("financeDeadline"));
		finances[0] = new Object[] { temps[0].getString("financeId"), cld.get(Calendar.YEAR), temps[0].getDate("financeDeadline"), temps[0].get("customerTypeCd") };
		String[][] items = getItems((String) finances[0][3]);
		if (items == null) {
			return sheetResult;
		}
		for (int i = 1, j = 1; i < temps.length && j < 4; i++) {
			cld.setTime(temps[i].getDate("financeDeadline"));
			if ((Integer) finances[i - 1][1] == cld.get(Calendar.YEAR)) {
				continue;// 同年的报表只取一份
			}
			if (!finances[0][3].equals(temps[i].get("customerTypeCd"))) {// 只取同类型的报表
				continue;
			}
			finances[j++] = new Object[] { temps[i].getString("financeId"), cld.get(Calendar.YEAR), temps[i].getDate("financeDeadline") };
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Map<String, Object>[] maps = new Map[finances.length];
		for (int i = 0; i < finances.length; i++) {
			if (finances[i] == null || finances[i][0] == null) {
				continue;
			}
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("financeId", finances[i][0]);
			Object[] datas = DatabaseExt.queryByNamedSql(DictContents.DB_NAME_CRMS, "com.bos.risk.financeUtil.getFinanceData", m);
			maps[i] = new HashMap<String, Object>();
			maps[i].put("xx_reportDate", sdf.format(finances[i][2]));
			for (Object obj : datas) {
				maps[i].put((String) ((Map) obj).get("CODE"), (BigDecimal) ((Map) obj).get("VALUE"));
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemName", "");// 项目名
		map.put("currValue", "近期财务状况");// 当前值
		map.put("lastOneValue", finances[1] == null || finances[1][1] == null ? "" : (finances[1][1] + "年末财务状况"));// 前一年年末值
		map.put("lastTwoValue", finances[2] == null || finances[2][1] == null ? "" : (finances[2][1] + "年末财务状况"));// 前二年年末值
		map.put("lastThreeValue", finances[3] == null || finances[3][1] == null ? "" : (finances[3][1] + "年末财务状况"));// 前三年年末值
		sheetResult.add(map);
		for (String[] item : items) {
			map = new HashMap<String, Object>();
			map.put("itemName", item[0]);// 项目名
			map.put("currValue", getValue(item, maps[0]));// 当前值
			map.put("lastOneValue", getValue(item, maps[1]));// 前一年年末值
			map.put("lastTwoValue", getValue(item, maps[2]));// 前二年年末值
			map.put("lastThreeValue", getValue(item, maps[3]));// 前三年年末值
			if ("净现金流量".equals(item[0])) {
				BigDecimal num = (BigDecimal) map.get("currValue");
				BigDecimal num1 = (BigDecimal) map.get("lastOneValue");
				BigDecimal num2 = (BigDecimal) map.get("lastTwoValue");
				BigDecimal num3 = (BigDecimal) map.get("lastThreeValue");
				map.put("currValue", null);
				map.put("lastOneValue", null);
				map.put("lastTwoValue", null);
				map.put("lastThreeValue", null);
				if (num != null && num1 != null) {
					map.put("currValue", num.subtract(num1));
				}
				if (num1 != null && num2 != null) {
					map.put("lastOneValue", num1.subtract(num2));
				}
				if (num2 != null && num3 != null) {
					map.put("lastTwoValue", num2.subtract(num3));
				}
			}
			sheetResult.add(map);
		}
		return sheetResult;
	}

	private Object getValue(String[] item, Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		if (item.length == 2) {
			return item[1] == null ? null : map.get(item[1]);
		}
		for (int i = 1; i < item.length; i++) {
			BigDecimal num = (BigDecimal) map.get(item[i]);
			if (num != null || num.compareTo(BigDecimal.ZERO) != 0) {
				return num;
			}
		}
		return null;
	}
}
