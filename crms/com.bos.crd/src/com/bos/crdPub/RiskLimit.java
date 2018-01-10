/**
 * 
 */
package com.bos.crdPub;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.bos.biz.MathHelper;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-08-01 13:39:05
 * 限额计算：
 * 出账时校验限额，限额不足时提示不能处长
 *
 */
@Bizlet("")
public class RiskLimit {
	public static TraceLogger logger = new TraceLogger(RiskLimit.class);

	/**
	 * @param args
	 * @author 3231
	 * 放款流程扣除综合授信额度前，校验限额
	 * 1，筛选出业务品种所在组
	 * 2，依次判断余额是否满足 并扣除 相应额度
	 * 传入参数  HashMap map 内参数包括orgNum，productCd，loanAmt
	 * 传出参数  HashMap map map内groupName为空时，表示余额足值且已全部扣除。groupName存在时，表示改groupName可用额度不足
	 */
	public HashMap delRiskLimit(HashMap map) {

		if (null == map.get("orgNum") || "".equals(map.get("orgNum"))) {
			logger.info("限额限额机构为空！");
			throw new EOSException("限额限额机构为空");
		}
		if (null == map.get("productCd") || "".equals(map.get("productCd"))) {
			logger.info("限额限额品种为空！");
			throw new EOSException("限额限额品种为空");
		}
		if (null == map.get("loanAmt") || "".equals(map.get("loanAmt"))) {
			logger.info("本次出账金额为空！");
			throw new EOSException("本次出账金额为空");
		}
		BigDecimal loanAmt = (BigDecimal) map.get("loanAmt");
		Date date = GitUtil.getBusiDate();
		/**
		 * 判断限额组校验
		 * 1，判断总行是否有生效的 有效期内的  含该品种的限额组
		 * 2，循环判断，该机构是否含该限额组。
		 * 3，如果该机构不含该限额组，判断上级机构是否含限额组
		 * */
		//1，判断总行是否有生效的 有效期内的  含该品种的限额组
		map.put("date", date);
		Object[] riskGroups = DatabaseExt.queryByNamedSql("default",
				"com.bos.crdPub.riskLimit.getRiskGroup", map);
		//判断是否满足该限额组
		for (int i = 0; i < riskGroups.length; i++) {
			String limitGroup = (String) ((DataObject) riskGroups[i]).get("LIMIT_GROUP");
			String orgNum = (String) map.get("orgNum");
			RiskLimit rl = new RiskLimit();
			BigDecimal avi = rl.getRiskLimitAvi(orgNum, limitGroup,date);
			if (avi.compareTo(loanAmt) >= 0) {
			} else {
				map.put("groupName",
						(String) ((DataObject) riskGroups[i]).get("GROUP_NAME"));
				return map;
			}
		}

		return map;
	}

	//查询限额可用   查询某个组,某个机构限额可用额度
	/**
	 * @param limitGroup 
	 * @param orgNum 
	 * 查询某机构所在限额组的可用额度
	 * 1，判断当前机构A是否配置了该限额组
	 * 2，如果未配置该限额组，判断上级机构是否配置该限额，直到向上有机构B 配置该限额组
	 * 3，查询机构B向下所有机构的该限额组的配置额度，+  机构B向下的该限额组的结局余额   =   已用额度
	 * 4，机构B的限额 - 已用 =  可用额度
	 * 
	 * */
	@Bizlet("")
	public BigDecimal getRiskLimitAvi(String orgNum, String limitGroup,Date date) {

		if (null == orgNum || "".equals(orgNum)) {
			logger.info("限额限额机构为空！");
			throw new EOSException("限额限额机构为空");
		}
		if (null == limitGroup || "".equals(limitGroup)) {
			logger.info("限额限额品种为空！");
			throw new EOSException("限额限额品种为空");
		}
		//查询该机构A是否配置该限额组
		HashMap map = new HashMap();
		map.put("date", date);
		map.put("orgNum", orgNum);
		map.put("limitGroup", limitGroup);
		DataObject org = DataObjectUtil.createDataObject("com.bos.pub.userMove.userMove.OmOrganization");
		org.set("orgcode", orgNum);
		DatabaseUtil.expandEntityByTemplate("default", org, org);
		Object[] risks = DatabaseExt.queryByNamedSql("default",
				"com.bos.crdPub.riskLimit.getRiskLimit", map);
		if (0 == risks.length) {
			//查询上级机构是否含该限额组
			int parentorgId = (Integer) org.get("parentorgid");
			org = null;
			org = DataObjectUtil.createDataObject("com.bos.pub.userMove.userMove.OmOrganization");
			org.set("orgid", parentorgId);
			DatabaseUtil.expandEntityByTemplate("default", org, org);
			map.put("orgNum", org.get("orgcode"));
			risks = DatabaseExt.queryByNamedSql("default",
					"com.bos.crdPub.riskLimit.getRiskLimit", map);
			if (0 == risks.length) {
				//查询上级机构是否含该限额组
				parentorgId = (Integer) org.get("parentorgid");
				org = null;
				org = DataObjectUtil
						.createDataObject("com.bos.pub.userMove.userMove.OmOrganization");
				org.set("orgid", parentorgId);
				DatabaseUtil.expandEntityByTemplate("default", org, org);
				map.put("orgNum", org.get("orgcode"));
				risks = DatabaseExt.queryByNamedSql("default",
						"com.bos.crdPub.riskLimit.getRiskLimit", map);
				if (0 == risks.length) {
					//查询上级机构是否含该限额组
					parentorgId = (Integer) org.get("parentorgid");
					org = null;
					org = DataObjectUtil
							.createDataObject("com.bos.pub.userMove.userMove.OmOrganization");
					org.set("orgid", parentorgId);
					DatabaseUtil.expandEntityByTemplate("default", org, org);
					map.put("orgNum", org.get("orgcode"));
					risks = DatabaseExt.queryByNamedSql("default",
							"com.bos.crdPub.riskLimit.getRiskLimit", map);
				}
			}
		}
		//机构B的限额组信息
		int orgId = (Integer) org.get("orgid");
		map.put("orgId", orgId);
		map.put("groupId", limitGroup);
		//查询机构B向下的所有已分配额度
		Object[] fps = DatabaseExt.queryByNamedSql("default",
				"com.bos.crdPub.riskLimit.getLimitUsed", map);
		BigDecimal fp = ((DataObject) fps[0]).getBigDecimal("LIMIT_AMT");
		//查询机构B向下的所有未分配机构  的  借据余额之和
		Object[] jjs = DatabaseExt.queryByNamedSql("default",
				"com.bos.crdPub.riskLimit.getLimitJjye", map);
		BigDecimal jjye = ((DataObject) jjs[0]).getBigDecimal("JJYE");
		//已用额度 = 已分配额度+借据余额之和
		String gs = "fp+jjye";
		map.put("fp", fp);
		map.put("jjye", jjye);
		BigDecimal usedLimit = MathHelper.expressionBigDecimal(gs, map);

		//查询B的可用额度
		DataObject limit = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdRiskLimit");
		limit.set("limitOrg",org.get("orgcode"));
		limit.set("limitGroup", limitGroup);
		DatabaseUtil.expandEntityByTemplate("default", limit, limit);

		BigDecimal limitAmt = limit.getBigDecimal("limitAmt");
		gs = "limitAmt - usedLimit";
		map.put("limitAmt", limitAmt);
		map.put("usedLimit", usedLimit);
		BigDecimal aviLimit = MathHelper.expressionBigDecimal(gs, map);

		return aviLimit;

	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}
}
