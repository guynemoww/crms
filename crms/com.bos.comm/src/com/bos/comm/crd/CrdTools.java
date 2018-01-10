/**
 * 
 */
package com.bos.comm.crd;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author houjiaxin
 * @date 2014-05-23 16:21:08
 *
 */
@Bizlet("额度工具类")
public class CrdTools {

	static TraceLogger log = new TraceLogger(CrdTools.class);

	/**
	 * @param conEndDate 合同结束日期
	 * @param grace 宽限期
	 * @param unit 宽限期单位
	 * @param crdDate 额度开始日期
	 * @return 若为true，则合同到期日期超过额度到期日期
	 * @author houjiaxin
	 * description:判断合同结束日期是否在额度开始日期+期限之内,若在之内返回false,否则返回true
	 */
	@Bizlet("")
	public static boolean conEndDateInspect(Date conEndDate, int grace,
			String unit, Date crdDate) {
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(crdDate);
		if (conEndDate != null && unit != null && crdDate != null) {
			if (unit.equals("01")) {
				//				如果期限单位为年
				calendar.add(calendar.YEAR, grace);
			} else if (unit.equals("02")) {
				//				如果期限单位为半年
				int mon = grace * 6;
				calendar.add(calendar.MONTH, mon);
			} else if (unit.equals("03")) {
				//				如果期限单位为季
				int mon = grace * 3;
				calendar.add(calendar.MONTH, mon);
			} else if (unit.equals("04")) {
				//				如果期限单位为月
				calendar.add(calendar.MONTH, grace);
			} else if (unit.equals("05")) {
				//				如果期限单位为日
				calendar.add(calendar.DATE, grace);
			} else {
				return false;
			}
		}
		//		else {
		//			return false;
		//		}
		flag = calendar.getTime().before(conEndDate);//判断额度结束日期是否在合同之前（若额度结束日期在合同结束日期之前返回true）
		return flag;
	}

	/**
	 * @param pOrgSeq 父级机构seq
	 * @param orgSeq 子级机构seq
	 * @return
	 */
	@Bizlet("判断是否父级")
	public static boolean isParentOrg(String pOrgSeq, String orgSeq) {
		boolean flag = false;
		if (orgSeq != null && pOrgSeq != null) {
			if (orgSeq.indexOf(pOrgSeq) != -1) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 额度日终重计算数量统计log打印
	 * @param taskType 额度类型
	 */
	@Bizlet("")
	public static void outLog(String taskType) {
		String logStr = "";
		if ("crd".equals(taskType)) {
			Global.crdCount = Global.crdCount + 1;
			logStr = "综合授信总数为：" + Global.crdTotal + ",已执行完毕数量为:"
					+ Global.crdCount;
		} else if ("guatee".equals(taskType)) {
			Global.guateeCount = Global.guateeCount + 1;
			logStr = "担保额度总数为：" + Global.guateeTotal + ",已执行完毕数量为:"
					+ Global.guateeCount;
		} else if ("platform".equals(taskType)) {
			Global.platformCount = Global.platformCount + 1;
			logStr = "平台额度总数为：" + Global.platformTotal + ",已执行完毕数量为:"
					+ Global.platformCount;
		} else if ("peer".equals(taskType)) {
			Global.peerCount = Global.peerCount + 1;
			logStr = "同业额度总数为：" + Global.peerTotal + ",已执行完毕数量为:"
					+ Global.peerCount;
		} else if ("single".equals(taskType)) {
			Global.singleCount = Global.singleCount + 1;
			logStr = "单笔单批总数为：" + Global.singleTotal + ",已执行完毕数量为:"
					+ Global.singleCount;
		}
		log.info(logStr);
	}

	/**
	 * @param limitType 额度类型
	 * @param limitId 额度ID
	 * @param partyId 客户ID
	 * @param limit 额度对象
	 * @param detail 分项额度对象
	 * @param product 授信品种
	 * @param logType log类型(分项or总额度)
	 * @param occAmt 本次占用统计
	 * @param occFinalAmt 本次全额占用统计
	 */
	@Bizlet("额度计算记录log")
	public static void crdCalculateLog(String limitType, String limitId,
			String partyId, DataObject limit, DataObject detail,
			String product, String logType, BigDecimal occAmt,
			BigDecimal occFinalAmt) {
		String limitTypeStr = "";
		String logTypeStr = "";
		String currTakeAmt = "";
		String currTakeFinalAmt = "";
		String limitInfo = "";
		String logString = "";
		if (limitType != null && limitType != "") {
			if (limitId == null || partyId == null) {
				return;
			}
			if ("limit".equals(limitType)) {
				limitTypeStr = "综合授信";
				if (logType != null && logType != "") {
					if ("limit".equals(logType)) {
						if (limit != null) {
							logTypeStr = "总额度";
							logString = "limitId:" + limitId + ",partyId:"
									+ partyId + ";";
							logString = logString + limitTypeStr + logTypeStr;
							if (occAmt != null && occFinalAmt != null) {
								String dedType = limit
										.getString("limitDedType");
								String exp = String.valueOf(limit
										.getBigDecimal("creditExpsure"));
								String amt = String.valueOf(limit
										.getBigDecimal("creditAmt"));
								String avaExp = String.valueOf(limit
										.getBigDecimal("availableExpsure"));
								String avaAmt = String.valueOf(limit
										.getBigDecimal("availableAmt"));
								logString = logString + "，本次统计占用金额：" + occAmt;
								logString = logString + "，本次统计占用全额:"
										+ occFinalAmt + ";";
								logString = logString + "计算后总额度信息：敞口总额度为 "
										+ exp + " ,";
								logString = logString + "闭口总额度为 " + amt + " ,";
								logString = logString + "敞口可用额度为 " + avaExp
										+ " ,";
								logString = logString + "闭口可用额度为 " + avaAmt
										+ " ;";
							} else {
								return;
							}
						} else {
							logString = logString + "额度信息为空";
						}
					} else if ("detail".equals(logType)) {
						logTypeStr = "分项额度";
						if (detail != null) {
							logString = "limitId:" + limitId + ",partyId:"
									+ partyId + ";";
							logString = logString + limitTypeStr + logTypeStr;
							if (occAmt != null && occFinalAmt != null) {
								String dedType = limit
										.getString("limitDedType");
								String exp = String.valueOf(limit
										.getBigDecimal("creditExpsure"));
								String amt = String.valueOf(limit
										.getBigDecimal("creditAmt"));
								String avaExp = String.valueOf(limit
										.getBigDecimal("availableExpsure"));
								String avaAmt = String.valueOf(limit
										.getBigDecimal("availableAmt"));
								logString = logString + "，本次统计占用金额：" + occAmt;
								if ("1".equals(dedType)) {
									logString = logString + "计算后分项额度信息：敞口额度为 "
											+ exp + " ,";
									logString = logString + "敞口可用额度为 " + avaExp
											+ " ,";
								} else {
									logString = logString + "计算后分项额度信息：闭口额度为 "
											+ amt + " ,";
									logString = logString + "闭口可用额度为 " + avaAmt
											+ " ;";
								}
							} else {
								return;
							}
						} else {
							logString = logString + "分项额度信息为空";
						}
					}
				} else {
					return;
				}
			} else if ("single".equals(limitType)) {
				limitTypeStr = "单笔单批";
				logTypeStr = "额度";
				if (detail != null) {
					logString = "limitId:" + limitId + ",partyId:" + partyId
							+ ";";
					logString = logString + limitTypeStr + logTypeStr;
					if (occAmt != null && occFinalAmt != null) {
						String exp = String.valueOf(detail
								.getBigDecimal("creditExpsure"));
						String amt = String.valueOf(detail
								.getBigDecimal("creditAmt"));
						String avaExp = String.valueOf(detail
								.getBigDecimal("availableExpsure"));
						String avaAmt = String.valueOf(detail
								.getBigDecimal("availableAmt"));
						logString = logString + "，本次统计占用金额：" + occAmt;
						logString = logString + "，本次统计占用全额:" + occFinalAmt
								+ ";";
						logString = logString + "计算后总额度信息：敞口总额度为 " + exp + " ,";
						logString = logString + "闭口总额度为 " + amt + " ,";
						logString = logString + "敞口可用额度为 " + avaExp + " ,";
						logString = logString + "闭口可用额度为 " + avaAmt + " ;";
					} else {
						return;
					}
				} else {
					logString = logString + "额度信息为空";
				}
			}
		} else {
			return;
		}
		log.info(logString);
	}
}
