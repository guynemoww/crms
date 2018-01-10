package com.bos.pub.credit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bos.pub.GitUtil;
import com.bos.pub.ServiceModuleName;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.BizTableName;
import com.bos.pub.entity.name.CsmTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

@Bizlet
public class CreditAmtValid {

	public static void validPartyAmt(String partyId) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("partyId", partyId);
		validOccupyAmt(param, getPartyMaxAmt(partyId), "该客户已超我行最高额度，无法继续申请贷款业务");
	}

	@Bizlet
	public static String validZhsxAmtEos(DataObject bizApply) {
		try {
			validZhsxAmt(bizApply);
			return null;
		} catch (Throwable e) {
			e.printStackTrace();
			String msg = e.getMessage();
			return msg == null || msg.isEmpty() ? "综合授信额度计算失败" : msg;
		}
	}

	public static void validZhsxAmt(DataObject bizApply) {
		// 低风险业务不提示，但是数据需要统计
		if (!"01".equals(bizApply.get("applyModeType"))) {
			return;
		}
		// 只判断业务发生方式为正常的数据
		// String bizHappenType = bizApply.getString("bizHappenType");
		// if (bizHappenType == null || !"01".equals(bizHappenType)) {
		// return;
		// }
		String bizType = bizApply.getString("bizType");
		if (!"02".equals(bizType)) {
			return;
		}
		DataObject party = EntityUtil.getEntityById(CsmTableName.TB_CSM_PARTY, "partyId", bizApply.getString("partyId"));
		// 只有公司客户验证
		if (!"01".equals(party.getString("partyTypeCd"))) {
			return;
		}
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("partyId", bizApply.getString("partyId"));
		param.put("bizHappenType", "01");
		param.put("bizType", "zhsx");
		validOccupyAmt(param, getZhsxMaxAmt(bizApply.getString("applyId")), "该客户已超我行最高综合授信额，是否确定继续做业务？");
	}

	public static void validDbdpAmt(DataObject bizApply) {
		// 低风险业务不提示，但是数据需要统计
		if (!"01".equals(bizApply.get("applyModeType"))) {
			return;
		}
		String prodect_type = bizApply.getString("productType");
		// 贴现业务不控制
		if (GitUtil.contain(prodect_type, new String[] { "01006001", "01006002", "01006010" })) { // 村镇银行贴现产品
			return;
		}
		// 只判断业务发生方式为正常的数据
		String bizHappenType = bizApply.getString("bizHappenType");
		if (bizHappenType == null || !"01".equals(bizHappenType)) {
			return;
		}
		String bizType = bizApply.getString("bizType");
		if (!"01".equals(bizType) && !"04".equals(bizType)) {
			return;
		}
		DataObject party = EntityUtil.getEntityById(CsmTableName.TB_CSM_PARTY, "partyId", bizApply.getString("partyId"));
		// 只有对公客户验证
		if (!"01".equals(party.getString("partyTypeCd"))) {
			return;
		}
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("partyId", bizApply.getString("partyId"));
		param.put("bizHappenType", "01");
		param.put("bizType", "dbdp");
		validOccupyAmt(param, getDbdpMaxAmt(bizApply.getString("applyId")), "超审批权限，无法继续做单笔单批业务");
	}

	@Bizlet("批复可用额度计算")
	public static void validCanUseApproveAmt(String amountDetailId, String conId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchApply", "unsearch");
		param.put("amountDetailId", amountDetailId);
		validCanUseAmt(param, conId, "此次申请合同金额不能超过[#maxAmt#]");
	}

	@SuppressWarnings("unchecked")
	public static Map<String, List<Map<String, Object>>> getAppCreditInfo(Map<String, Object> param) {
		Object[] objs = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.pub.credit.CreditAmtValidSql.getCreditInfoExposure", param);
		if (objs == null || objs.length == 0) {
			return null;
		}
		Map<String, List<Map<String, Object>>> appMap = new HashMap<String, List<Map<String, Object>>>();
		Map<String, String> oldConIdMap = new HashMap<String, String>();
		for (Object obj : objs) {
			Map<String, Object> map = (Map<String, Object>) obj;
			String id = (String) map.get("AMOUNT_DETAIL_ID");
			List<Map<String, Object>> tempList = appMap.get(id);
			if (tempList == null) {
				tempList = new ArrayList<Map<String, Object>>();
				appMap.put(id, tempList);
			}
			String oldConId = (String) map.get("OLD_CONTRACT_ID");
			if (oldConId != null) {
				oldConIdMap.put(oldConId, id);
			}
			tempList.add(map);
		}
		// 合同调整时，原有合同信息状态为正常，需要过滤
		for (Map.Entry<String, String> entry : oldConIdMap.entrySet()) {
			List<Map<String, Object>> tempList = appMap.get(entry.getValue());
			if (tempList == null || tempList.isEmpty()) {
				continue;
			}
			for (int i = 0; i < tempList.size(); i++) {
				if (entry.getKey().equals(tempList.get(i).get("CONTRACT_ID"))) {
					tempList.remove(i);
					break;
				}
			}
		}
		return appMap;
	}

	public static void validOccupyAmt(Map<String, Object> param, final BigDecimal maxAmt, String error) {
		Map<String, List<Map<String, Object>>> appMap = getAppCreditInfo(param);
		if (appMap == null) {
			throw new RuntimeException("占用额度校验时，没有获取到任何检查数据！");
		} else if (maxAmt == null) {
			throw new RuntimeException("占用额度校验时，没有获取到最高可占用额度信息！");
		}
		BigDecimal amt = BigDecimal.ZERO;
		// 申请是不可能有负数的，所以最大值如果小于零，就不需要计算了
		if (maxAmt.compareTo(BigDecimal.ZERO) > 0) {
			// 贴现不纳入占用额度
			param.put("filtProductType", new String[] { "01006001", "01006002", "01006010" });
			for (List<Map<String, Object>> appList : appMap.values()) {
				amt = amt.add(CreditAmtReckon.getOccupyAppAmt(appList));
			}
		}
		if (amt.compareTo(maxAmt) > 0) {
			param.put("maxAmt", maxAmt.toString());
			param.put("amt", amt.toString());
			throw new RuntimeException(getMsg(error, param));
		}
	}

	public static void validCanUseAmt(Map<String, Object> param, String conId, String error) {
		Map<String, List<Map<String, Object>>> appMap = getAppCreditInfo(param);
		if (appMap == null) {
			throw new RuntimeException("可用额度校验时，没有获取到任何检查数据！");
		} else if (conId == null) {
			throw new RuntimeException("可用额度校验时，没有获取到预使用额度信息！");
		}
		BigDecimal canUseAmt = BigDecimal.ZERO;
		BigDecimal appAmt = null;
		BigDecimal conAmt = null;
		BigDecimal conOccupy = null;
		BigDecimal conBzjbl = null;
		for (List<Map<String, Object>> appList : appMap.values()) {
			if (conId != null) {
				for (Map<String, Object> map : appList) {
					if (conId.equals(map.get("CONTRACT_ID"))) {
						conOccupy = CreditAmtReckon.getOccupyConAmt(map, new String[] { "06" }, false);
						appAmt = (BigDecimal) map.get("APP_AMT");
						conAmt = (BigDecimal) map.get("CON_AMT");
						conBzjbl = (BigDecimal) map.get("CON_BZJBL");
						conId = null;
						break;
					}
				}
			}
			canUseAmt = canUseAmt.add(CreditAmtReckon.getCanUseAppAmt(appList));
		}
		conBzjbl = CreditAmtReckon.quotiety(conBzjbl);
		if (appAmt == null) {
			throw new RuntimeException("可用额度校验时传入错误合同编号，无法获取合同相应信息");
		} else if (BigDecimal.ZERO.compareTo(canUseAmt) > 0) {
			param.put("maxAmt", canUseAmt.add(conOccupy).divide(conBzjbl, 2, BigDecimal.ROUND_HALF_UP).toString());
			param.put("amt", conAmt.setScale(2).toString());
			throw new RuntimeException(getMsg(error, param));
		} else if (conBzjbl.compareTo(BigDecimal.ZERO) == 0 && conAmt.compareTo(appAmt) > 0) {
			throw new RuntimeException("100%保证金比例下合同金额不可大于[¥" + appAmt + "]");
		}
	}

	private static final String getMsg(String msg, Map<String, Object> param) {
		if (param == null || param.isEmpty() || msg == null || msg.trim().isEmpty()) {
			return msg;
		}
		Matcher m = Pattern.compile("#(.*?)#").matcher(msg);
		while (m.find()) {
			String key = m.group();
			if (key.length() < 3) {
				continue;
			}
			key = key.substring(1, key.length() - 1);
			String value = (String) param.get(key);
			msg = m.replaceAll(value == null ? "" : value);
		}
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			Object v = entry.getValue();
			if (v instanceof BigDecimal) {
				v = ((BigDecimal) v).setScale(2);
			}
			msg = msg.replaceAll("#" + entry.getKey() + "#", v.toString());
		}
		return msg;
	}

	public static BigDecimal getMax(BigDecimal... nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		BigDecimal max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i].compareTo(max) > 0) {
				max = nums[i];
			}
		}
		return max;
	}

	public static boolean isZero(BigDecimal num) {
		return num == null || BigDecimal.ZERO.compareTo(num) == 0;
	}

	public static BigDecimal getPartyMaxAmt(String partyId) {
		DataObject csmObj = EntityUtil.getEntityById(CsmTableName.TB_CSM_PARTY, "partyId", partyId);
		String partyType = csmObj.getString("partyTypeCd");
		partyType = partyType == null ? "def_amt" : (partyType + "_amt");
		String maxAmt = ConfigurationUtil.getContributionConfig(ServiceModuleName.PUB, "risk_limit_config", "party_limit", partyType);
		if (maxAmt == null || (maxAmt = maxAmt.trim()).isEmpty()) {
			return null;
		}
		return new BigDecimal(maxAmt);
	}

	public static BigDecimal getZhsxMaxAmt(String applyId) {
		DataObject obj = EntityUtil.searchEntity(BizTableName.TB_BIZ_CREDIT_LINE_MEASURE, "id", applyId);
		return obj != null ? obj.getBigDecimal("b25") : BigDecimal.ZERO;
	}

	public static BigDecimal getDbdpMaxAmt(String applyId) {
		String maxAmt = ConfigurationUtil.getContributionConfig(ServiceModuleName.PUB, "risk_limit_config", "dbdp_limit", "dbdp_limit_amt");
		if (maxAmt == null || (maxAmt = maxAmt.trim()).isEmpty()) {
			return null;
		}
		return new BigDecimal(maxAmt);
	}
}
