/**
 * 
 */
package com.bos.asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-07-10 19:19:57
 * 
 */
@Bizlet("")
public class AssetsDownload {

	private String[][] getTitle(String title) {
		if ("handleAcc".equals(title)) {
			return new String[][] { { "PLAN_NUM", "处置方案编号", null }, //
					{ "CONTRACT_NUM", "合同编号", null }, //
					{ "SUMMARY_NUM", "借据编号", null }, //
					{ "PARTY_NAME", "客户名称", null }, //
					{ "CERT_TYPE", "证件类型", "CDKH0002" }, //
					{ "CERT_NUM", "证件号码", null }, //
					{ "PRODUCT_TYPE", "业务品种", "product" }, //
					{ "SUMMARY_AMT", "借据金额", null }, //
					{ "SUMMARY_BAL", "借据余额", null }, //
					{ "SUM_BEGIN_DATE", "借据起期", null }, //
					{ "SUM_END_DATE", "借据止期", null }, //
					{ "PLAN_TYPE", "方案处置方式", "XD_ASSET001" }, //
					{ "REG_ORG_ID", "经办机构", "org" },//
					{ "REG_USER_ID", "经办人员", "user" },//
					{ "REG_DATE", "经办日期", null } };
		} else if ("verifyAcc".equals(title)) {
			return new String[][] { { "PLAN_NUM", "处置方案编号", null }, //
					{ "CONTRACT_NUM", "合同编号", null }, //
					{ "SUMMARY_NUM", "借据编号", null }, //
					{ "PARTY_NAME", "客户名称", null }, //
					{ "CERT_TYPE", "证件类型", "CDKH0002" }, //
					{ "CERT_NUM", "证件号码", null }, //
					{ "PRODUCT_TYPE", "业务品种", "product" }, //
					{ "SUMMARY_AMT", "借据金额", null }, //
					{ "SUMMARY_BAL", "借据余额", null }, //
					{ "SUM_BEGIN_DATE", "借据起期", null }, //
					{ "SUM_END_DATE", "借据止期", null }, //
					{ "PLAN_TYPE", "方案处置方式", "XD_ASSET001", null }, //
					{ "CANCEL_CAPITAL_AMT", "核销本金", null }, //
					{ "CANCEL_NORMAL_ITR", "核销正常利息", null }, //
					{ "CANCEL_ARREAR_ITR", "核销拖欠利息", null },//
					{ "CANCEL_PUNISH_ITR", "核销罚息", null }, //
					{ "CANCEL_AMT", "核销总额", null },//
					{ "PURSUE", "是否保留追索权", "XD_0002" },//
					{ "REG_ORG_ID", "经办机构", "org" },//
					{ "REG_USER_ID", "经办人员", "user" },//
					{ "REG_DATE", "经办日期", null } };
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Bizlet
	public List<Object> getDataxls(String title, Object[] datas) {
		List<Object> dataList = new ArrayList<Object>();
		String[][] titles = getTitle(title);
		if (titles == null) {
			return dataList;
		}
		String[] temp = new String[titles.length];
		for (int i = 0; i < titles.length; i++) {
			temp[i] = titles[i][1];
		}
		dataList.add(temp);
		Map<String, Object> map;
		for (Object obj : datas) {
			map = (Map<String, Object>) obj;
			String[] row = new String[titles.length];
			for (int i = 0; i < titles.length; i++) {
				row[i] = titles[i][2] != null ? getValue((String) titles[i][2], (String) map.get(titles[i][0])) : getValue(map.get(titles[i][0]));
			}
			dataList.add(row);
		}

		return dataList;
	}

	private String getValue(Object value) {
		if (value == null) {
			return "";
		}
		return String.valueOf(value);
	}

	private String getValue(String type, String value) {
		if (value == null) {
			return "";
		}
		if ("product".equals(type)) {
			DataObject[] temps = GitUtil.getProductInfo(value);
			if (temps != null && temps.length > 0) {
				return temps[0].getString("productName");
			}
		} else if ("org".equals(type)) {
			DataObject[] temps = GitUtil.getOrgInfo(value);
			if (temps != null && temps.length > 0) {
				return temps[0].getString("orgname");
			}
		} else if ("user".equals(type)) {
			DataObject[] temps = GitUtil.getUserInfo(value);
			if (temps != null && temps.length > 0) {
				return temps[0].getString("empname");
			}
		} else {
			return mappingData.tomapping(type, value);
		}
		return value;
	}
}
