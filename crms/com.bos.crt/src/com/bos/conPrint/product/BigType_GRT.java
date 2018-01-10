/**
 * 
 */
package com.bos.conPrint.product;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.bos.conPrint.ConInfoLoadService;
import com.bos.conPrint.ConInfoLoadUtil;
import com.bos.csm.pub.CsmUtil;
import com.bos.grt.services.GrtInterfaceService;
import com.bos.grt.services.GrtSortTypeUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.SysTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;

import commonj.sdo.DataObject;

/**
 * <pre>
 * Title: 抵质押，保证 合同打印信息
 * Description: 程序功能的描述
 * </pre>
 * 
 * @author Administrator
 * @version 1.00.00
 * 
 */

public class BigType_GRT extends ConInfoLoad {

	private GrtSortTypeUtil sortTypeUtil;
	private ConInfoLoadService conInfoLoadService;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> loadCon(Map<String, String> param) {
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getBigType_GRT", param);
		Map<String, Object> conMap = datas == null || datas.length == 0 ? null : (Map<String, Object>) datas[0];
		if (conMap == null) {
			return null;
		}
		String top = (String) conMap.get("IF_TOP_SUBCON");
		BigDecimal subAmt = (BigDecimal) ("1".equals(top) ? conMap.get("ZGBJXE") : conMap.get("SUBCONTRACT_AMT"));
		conMap.put("SUB_AMT", subAmt);
		conMap.put("SUB_SCOPE", subAmt.compareTo((BigDecimal) conMap.get("CONTRACT_AMT")) == 0 ? "1" : "2");
		if (getLoadConSql() != null && !getLoadConSql().isEmpty()) {
			setConParam(conMap, param);
			datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, getLoadConSql(), param);
			if (datas != null && datas.length > 0) {
				conMap.putAll((Map<String, Object>) datas[0]);
			}
		}
		if (conInfoLoadService != null) {
			String conReportName = conInfoLoadService.getReportNameCN((String) conMap.get("PRODUCT_TYPE"));
			conMap.put("CON_REPORT_NAME", conReportName);
		}
		return conMap;
	}

	protected void setConParam(Map<String, Object> conMap, Map<String, String> param) {
		param.put("suretyId", (String) getNeedValue(conMap, "SURETY_ID"));
	}

	@Override
	public void loadOther(Map<String, Object> reportMap) {
		super.loadOther(reportMap);
		loadSub(reportMap);
	}

	protected void loadSub(Map<String, Object> reportMap) {

	}

	@Override
	protected Map<String, Object> loadAddr(Map<String, Object> conMap) {
		Map<String, Object> addMap = loadParty((String) getNeedValue(conMap, "A_ID"));
		String[] keys = { "A_PHONE", "PHONE", "A_EMAIL", "EMAIL", "A_POSTCODE", "ZIP_NUM", "A_SEND_ADDR", "ADDRESS", "A_RECEIVER", "PARTY_NAME" };
		for (int i = 0; i < keys.length - 1; i = i + 2) {
			addMap.put(keys[i], addMap.get(keys[i + 1]));
			addMap.remove(keys[i + 1]);
		}
		Map<String, Object> tempMap = loadOrg((String) getNeedValue(conMap, "B_ID"));
		keys = new String[] { "B_PHONE", "PHONE", "B_EMAIL", "EMAIL", "B_POSTCODE", "ZIP_NUM", "B_SEND_ADDR", "ADDRESS", "B_RECEIVER", "ORG_NAME" };
		for (int i = 0; i < keys.length - 1; i = i + 2) {
			addMap.put(keys[i], tempMap.get(keys[i + 1]));
		}
		return addMap;
	}

	protected Map<String, String> getYpMap(String suretyNo, String sortType) {
		Map<String, String> ypMap = GrtInterfaceService.YP1113_UNERR(suretyNo);
		if (ypMap != null) {
			Map<String, String> aliasMap = getSortTypeUtil().getAlias(sortType);
			for (String key : aliasMap.keySet()) {
				// 通过接口获取的数据存在Object类型结果
				Object value = ypMap.get(key);
				if (value != null) {
					ypMap.put(aliasMap.get(key), String.valueOf(value));
				}
			}
			return ypMap;
		}
		return new HashMap<String, String>();
	}

	/**
	 * 查询该抵押物是否给他人抵质押
	 * 
	 * @param param
	 * @return
	 */
	protected Object[] getWTrdzy(Map<String, String> param) {
		return DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.conApply.ContractPrintSQL.getGrt_wtrdzy", param);
	}

	@SuppressWarnings("unchecked")
	protected String isWTrdzy(Object[] maps, String surety_id) {
		if (maps == null || maps.length == 0) {
			return "否";
		}
		Map<String, Object> map;
		for (Object obj : maps) {
			map = (Map<String, Object>) obj;
			if (surety_id.equals(map.get("SURETY_ID"))) {
				return (String) map.get("SURETY_AMT");
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getReportName(Map<String, Object> reportMap) {
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		Map<String, Object> aMap = (Map<String, Object>) reportMap.get(REPORT_A);
		String name = getReportName();
		String partyType = (String) getNeedValue(aMap, "PARTY_TYPE");
		name += "_" + (CsmUtil.isNatural(partyType) ? "2" : "1");
		name += "_" + (String) getNeedValue(conMap, "SUBCONTRACT_TYPE");
		name += "_" + (String) getNeedValue(conMap, "IF_TOP_SUBCON");
		return "grt" + File.separator + name + ".docx";
	}

	@Override
	public String getReportNameCN(String... params) {
		String temp = super.getReportNameCN();
		if (temp != null) {
			String[] temps = temp.split(",");
			if (temps.length == 3) {
				return (CsmUtil.isNatural(params[0]) ? temps[0] : "") + ("1".equals(params[1]) ? temps[1] : "") + temps[2];
			}
		}
		return null;
	}

	@Override
	public void convertCon(Map<String, Object> conMap) {
		// // 数字转换为带括号中文数字
		// ConInfoLoadUtil.setNumToCNKH(conMap, new String[] { "ARBITRATE_TYPE" }, new String[] { "MARGIN_SORT" });
		//
		// // 数字转换为带圆圈数字
		// ConInfoLoadUtil.setNumToYQ(conMap, new String[] { "HOLIDAY_INT_FLG" });

		ConInfoLoadUtil.setNumToCN(conMap, new String[] { "SUB_SCOPE" });
		// 金额转换为中文
		ConInfoLoadUtil.setMoneyToCN(conMap, new String[] { "SUB_AMT", "SUB_AMT_CN" }, new String[] { "CONTRACT_AMT", "CONTRACT_AMT_CN" });

		// 时间数据转换
		ConInfoLoadUtil.setDateFormat(conMap, new String[] { "SUBBEGIN_DATE" }, new String[] { "SUBEND_DATE" });
		// 转换产品代码
		String temp = (String) conMap.get("PRODUCT_TYPE");
		if (null != temp) {
			DataObject product = EntityUtil.getEntityById(SysTableName.TB_SYS_PRODUCT, "productId", temp);
			// 产品代码必须存在于系统中
			ConInfoLoadUtil.convertValue(conMap, "PRODUCT_TYPE_CN", product.getString("productName"));
		}
		// // 转换押品类型
		// temp = (String) conMap.get("SORT_TYPE");
		// if (temp != null) {
		// DataObject sortarguments = EntityUtil.searchEntity(GrtTableName.TB_GRT_SORTARGUMENTS, "sortType", temp);
		// if (sortarguments != null) {
		// ConInfoLoadUtil.convertValue(conMap, "SORT_TYPE", sortarguments.getString("sortName"));
		// }
		// }
		// 字典数据转换
		ConInfoLoadUtil.setCodeByDict(conMap, new String[] { "BZ", "CD000001", "BZ_CN" }, new String[] { "CURRENCY_CD", "CD000001" });
	}

	public GrtSortTypeUtil getSortTypeUtil() {
		return sortTypeUtil;
	}

	public void setSortTypeUtil(GrtSortTypeUtil sortTypeUtil) {
		this.sortTypeUtil = sortTypeUtil;
	}

	public ConInfoLoadService getConInfoLoadService() {
		return conInfoLoadService;
	}

	public void setConInfoLoadService(ConInfoLoadService conInfoLoadService) {
		this.conInfoLoadService = conInfoLoadService;
	}

}
