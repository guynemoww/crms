/**
 * 
 */
package com.bos.batch;

import java.math.BigDecimal;
import java.util.Date;

import com.bos.pub.DateUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author kf_xdxt11
 * @date 2016-07-12 08:57:45
 * 
 */
@Bizlet("处理计量账务")
public class DealAccount {

	@Bizlet("全部同步")
	public static void allSynch() throws Exception {
		DataObject params = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
		Object[] res = DatabaseExt.queryByNamedSql("default", "com.bos.batch.dealAccount.queryAllSummary", params);
		System.out.println("-----------------开始 >> 同步全部计量账务：共计：【" + res.length + "】笔！-----------------" + DateUtil.getDate());

		for (int i = 0; i < res.length; i++) {
			System.out.println("正在同步第【" + String.valueOf(i + 1) + "】笔，共计【" + String.valueOf(res.length) + "】笔，还剩余【"
					+ String.valueOf(res.length - i + 1) + "】笔...");
			String summary_num = ((DataObject) res[i]).getString("SUMMARY_NUM");
			singleSynch(summary_num);
		}
		System.out.println("-----------------结束 >> 同步全部计量账务：共计：【" + res.length + "】笔！-----------------" + DateUtil.getDate());

	}

	/**
	 * @param summary_num
	 * @author kf_xdxt11
	 * @throws Exception
	 */
	@Bizlet("单笔同步")
	public static void singleSynch(String dueNum) throws Exception {

		System.out.println("-------开始 >> 同步计量单笔账务：借据号：【" + dueNum + "】-------");

		Object[] res = DatabaseExt.queryByNamedSql("aplus", "com.bos.batch.dealAccount.queryLoanInfo", dueNum);

		if (res != null && res.length == 1) {

			Date workdate = GitUtil.getBusiDate();
			Date worktime = GitUtil.getBusiTimestamp();

			BigDecimal v_normal_itr = new BigDecimal(0); // --正常利息(已结计)
			BigDecimal v_arrear_itr = new BigDecimal(0); // --拖欠利息(已结计)
			BigDecimal v_punish_itr = new BigDecimal(0);// --罚息(未停息:已结计；停息:包括未结计)
			BigDecimal v_dft_itr_in = new BigDecimal(0);
			BigDecimal v_dft_itr_out = new BigDecimal(0);
			BigDecimal v_ljfx_in = new BigDecimal(0);
			BigDecimal v_ljfx_out = new BigDecimal(0);

			DataObject loanInfo = (DataObject) res[0];

			String due_num = loanInfo.getString("DUE_NUM");
			String ceas_flg = loanInfo.getString("CEAS_FLG");
			String prm_cls = loanInfo.getString("PRM_CLS");
			String ast_cls = loanInfo.getString("AST_CLS");
			String deva_sts = loanInfo.getString("DEVA_STS");
			String sts = loanInfo.getString("STS");
			// 非停息
			if ("0".equals(ceas_flg)) {
				v_normal_itr = loanInfo.getBigDecimal("IN_NOR_BAL_06").add(loanInfo.getBigDecimal("OUT_NOR_BAL_09"));
				v_arrear_itr = loanInfo.getBigDecimal("IN_DFT_BAL_07").add(loanInfo.getBigDecimal("OUT_DFT_BAL_10"));
				v_punish_itr = loanInfo.getBigDecimal("IN_PNS_BAL_08").add(loanInfo.getBigDecimal("OUT_PNS_BAL_11"))
						.add(loanInfo.getBigDecimal("DFT_PRN_OTD_ITR")).add(loanInfo.getBigDecimal("DFT_ITR_IN_OTD_ITR"))
						.add(loanInfo.getBigDecimal("DFT_ITR_OUT_OTD_ITR")).add(loanInfo.getBigDecimal("NOR_ITR_IN_OTD_ITR"))
						.add(loanInfo.getBigDecimal("NOR_ITR_OUT_OTD_ITR")); // --(欠款表中的未结计罚息)

				// --自营性贷款
				if ("01".equals(prm_cls)) {
					// --资产证券化:利息归属表外
					if ("A00".equals(ast_cls)) {
						// --表外欠息累加和
						v_dft_itr_out = loanInfo.getBigDecimal("OUT_NOR_BAL_09").add(loanInfo.getBigDecimal("OUT_DFT_BAL_10"))
								.add(loanInfo.getBigDecimal("OUT_PNS_BAL_11")).add(loanInfo.getBigDecimal("DFT_PRN_OTD_ITR"))
								.add(loanInfo.getBigDecimal("DFT_ITR_OUT_OTD_ITR")).add(loanInfo.getBigDecimal("NOR_ITR_OUT_OTD_ITR"));
					} // --非资产证券化
					else {
						// --正常状态-利息归属表内
						if ("0".equals(deva_sts)) {
							// --表内欠息累加和
							v_dft_itr_in = loanInfo.getBigDecimal("IN_NOR_BAL_06").add(loanInfo.getBigDecimal("IN_DFT_BAL_07"))
									.add(loanInfo.getBigDecimal("IN_PNS_BAL_08")).add(loanInfo.getBigDecimal("DFT_PRN_OTD_ITR"))
									.add(loanInfo.getBigDecimal("DFT_ITR_IN_OTD_ITR")).add(loanInfo.getBigDecimal("NOR_ITR_IN_OTD_ITR"));
						} // --减值状态:利息归属到表外
						else if ("1".equals(deva_sts)) {
							// --表外欠息累加和
							v_dft_itr_out = loanInfo.getBigDecimal("OUT_NOR_BAL_09").add(loanInfo.getBigDecimal("OUT_DFT_BAL_10"))
									.add(loanInfo.getBigDecimal("OUT_PNS_BAL_11")).add(loanInfo.getBigDecimal("DFT_PRN_OTD_ITR"))
									.add(loanInfo.getBigDecimal("DFT_ITR_OUT_OTD_ITR")).add(loanInfo.getBigDecimal("NOR_ITR_OUT_OTD_ITR"));
						}
					}

					// --委托贷款:利息归属表外
				} else if ("02".equals(prm_cls)) {
					// --表外欠息累加和
					v_dft_itr_out = loanInfo.getBigDecimal("OUT_NOR_BAL_09").add(loanInfo.getBigDecimal("OUT_DFT_BAL_10"))
							.add(loanInfo.getBigDecimal("OUT_PNS_BAL_11")).add(loanInfo.getBigDecimal("DFT_PRN_OTD_ITR"))
							.add(loanInfo.getBigDecimal("DFT_ITR_OUT_OTD_ITR")).add(loanInfo.getBigDecimal("NOR_ITR_OUT_OTD_ITR"));
				}
				// 停息
			} else if ("1".equals(ceas_flg)) {
				v_normal_itr = loanInfo.getBigDecimal("IN_NOR_BAL_06").add(loanInfo.getBigDecimal("OUT_NOR_BAL_09"));
				v_arrear_itr = loanInfo.getBigDecimal("IN_DFT_BAL_07").add(loanInfo.getBigDecimal("OUT_DFT_BAL_10"));
				v_punish_itr = loanInfo.getBigDecimal("IN_PNS_BAL_08").add(loanInfo.getBigDecimal("OUT_PNS_BAL_11")); // --已结罚息

				// --自营性贷款
				if ("01".equals(prm_cls)) {
					// --资产证券化:利息归属表外
					if ("A00".equals(ast_cls)) {
						// --表外欠息累加和
						v_dft_itr_out = loanInfo.getBigDecimal("OUT_NOR_BAL_09").add(loanInfo.getBigDecimal("OUT_DFT_BAL_10"))
								.add(loanInfo.getBigDecimal("OUT_PNS_BAL_11"));
					} // --非资产证券化
					else {
						// --正常状态-利息归属表内
						if ("0".equals(deva_sts)) {
							// --表内欠息累加和
							v_dft_itr_in = loanInfo.getBigDecimal("IN_NOR_BAL_06").add(loanInfo.getBigDecimal("IN_DFT_BAL_07"))
									.add(loanInfo.getBigDecimal("IN_PNS_BAL_08"));
						}// --减值状态-利息归属到表外
						else if ("1".equals(deva_sts)) {
							// --表外欠息累加和
							v_dft_itr_out = loanInfo.getBigDecimal("OUT_NOR_BAL_09").add(loanInfo.getBigDecimal("OUT_DFT_BAL_10"))
									.add(loanInfo.getBigDecimal("OUT_PNS_BAL_11"));
						}
					}
				}// --委托贷款-利息归属表外
				else if ("02".equals(prm_cls)) {
					// --表外欠息累加和
					v_dft_itr_out = loanInfo.getBigDecimal("OUT_NOR_BAL_09").add(loanInfo.getBigDecimal("OUT_DFT_BAL_10"))
							.add(loanInfo.getBigDecimal("OUT_PNS_BAL_11"));
				}
			}

			// System.out.println("----------------------计算累计罚息------------------------");

			if ("01".equals(prm_cls)) {

				v_ljfx_in = loanInfo.getBigDecimal("DFT_PRN_OTD_ITR").add(loanInfo.getBigDecimal("DFT_ITR_IN_OTD_ITR"))
						.add(loanInfo.getBigDecimal("NOR_ITR_IN_OTD_ITR")); // --表内累计罚息
				v_ljfx_out = loanInfo.getBigDecimal("DFT_ITR_OUT_OTD_ITR").add(loanInfo.getBigDecimal("NOR_ITR_OUT_OTD_ITR")); // --表外累计罚息

			} else if ("1".equals(deva_sts) || "02".equals(prm_cls) || ("01".equals(prm_cls) && "A00".equals(ast_cls))) {

				v_ljfx_in = loanInfo.getBigDecimal("DFT_ITR_IN_OTD_ITR").add(loanInfo.getBigDecimal("NOR_ITR_IN_OTD_ITR"));// --表内累计罚息
				v_ljfx_out = loanInfo.getBigDecimal("DFT_PRN_OTD_ITR").add(loanInfo.getBigDecimal("DFT_ITR_OUT_OTD_ITR"))
						.add(loanInfo.getBigDecimal("NOR_ITR_OUT_OTD_ITR"));// --表外累计罚息

			}

			// System.out.println("----------------------开始：同步分期违约记录表------------------------");

			Object[] debtInfos = DatabaseExt.queryByNamedSql("aplus", "com.bos.batch.dealAccount.queryDebtInfo", due_num);

			for (int i = 0; i < debtInfos.length; i++) {
				DataObject debtInfo = (DataObject) debtInfos[i];
				
				//逾期本金
				BigDecimal v_peri_overdue_capi = debtInfo.getBigDecimal("RCV_PRN").subtract(debtInfo.getBigDecimal("PAD_UP_PRN"));
				
				//逾期利息（正常+拖欠）
				BigDecimal v_peri_overdue_def_itr = debtInfo.getBigDecimal("RCV_DFT_ITR").subtract(debtInfo.getBigDecimal("PAD_UP_DFT_ITR"));
				BigDecimal v_peri_overdue_nor_itr = debtInfo.getBigDecimal("RCV_NOR_ITR").subtract(debtInfo.getBigDecimal("PAD_UP_NOR_ITR"));
				BigDecimal v_peri_overdue_itr = v_peri_overdue_def_itr.add(v_peri_overdue_nor_itr);
				
				//罚息
				BigDecimal v_peri_punish_itr = debtInfo.getBigDecimal("RCV_PNS_ITR").subtract(debtInfo.getBigDecimal("PAD_UP_PNS_ITR"));

				// --非停息-0: 判断  应收-实收 + 未结计罚息 > 0
				// --停息-1：判断 应收-实收 > 0
				BigDecimal judgeOverdueAmt = v_peri_overdue_capi.add(v_peri_overdue_itr).add(v_peri_punish_itr);

				if ("0".equals(ceas_flg)) {
					judgeOverdueAmt = judgeOverdueAmt.add(debtInfo.getBigDecimal("DFT_OTD_ITR"));
				}

				// --逾期
				Date end_date = DateUtil.StringToDate(debtInfo.getString("END_DATE"), "yyyyMMdd");
				Date pay_date = DateUtil.StringToDate(debtInfo.getString("PAY_DATE"), "yyyyMMdd");
				Date beg_date = DateUtil.StringToDate(debtInfo.getString("BEG_DATE"), "yyyyMMdd");
				
				String overdueStatus = null;
				String overdueType = null;
				//拖欠金额>0则逾期
				if (judgeOverdueAmt.compareTo(new BigDecimal(0)) == 1) {

					overdueStatus = "03"; // --逾期状态

					if (v_peri_overdue_capi.compareTo(new BigDecimal(0)) == 1
							&& v_peri_overdue_itr.add(v_peri_punish_itr).compareTo(new BigDecimal(0)) == 1) {
						overdueType = "本息逾期";
					} else if (v_peri_overdue_capi.compareTo(new BigDecimal(0)) == 1) {
						overdueType = "本金逾期";
					} else if (v_peri_overdue_itr.add(v_peri_punish_itr).compareTo(new BigDecimal(0)) == 1) {
						overdueType = "利息逾期";
					}
				}

				// 拖欠金额=0
				else if (judgeOverdueAmt.compareTo(new BigDecimal(0)) == 0) {
					// 超过应还日期，曾经逾期，后又结清
					if (pay_date != null && end_date.compareTo(pay_date)== -1) {
						overdueStatus = "04"; // --结清状态
						overdueType = "逾期结清";
					}

				}
				
				
				DataObject illegalRecord = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanIllegalRecord");
				illegalRecord.set("summaryNum", due_num);
				illegalRecord.set("currPeri", debtInfo.getString("CURR_PERI"));
				int count = DatabaseUtil.countByTemplate("default", illegalRecord);
				// 存在非逾期记录，则删除
				if (overdueStatus == null){
					if(count > 0){
						DatabaseUtil.deleteByTemplate("default", illegalRecord);
					}
					continue;
				}
				// 不存在则新增
				if (count == 0) {
					DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					obj.setString("summaryNum", due_num);
					DatabaseUtil.expandEntityByTemplate("default", obj, obj);
					illegalRecord.set("partyId", obj.getString("partyId"));
					illegalRecord.set("userNum", obj.getString("userNum"));
					illegalRecord.set("orgNum", obj.getString("orgNum"));
					illegalRecord.set("createTime", worktime);
				}
				illegalRecord.set("beginDate", beg_date);
				illegalRecord.set("endDate", end_date);
				illegalRecord.set("status", overdueStatus);
				illegalRecord.set("overdueDate", end_date);
				illegalRecord.set("repayDate", pay_date);
				illegalRecord.set("rcvPrn", debtInfo.getBigDecimal("RCV_PRN"));
				illegalRecord.set("padUpPrn", debtInfo.getBigDecimal("PAD_UP_PRN"));
				illegalRecord.set("rcvNorItr", debtInfo.getBigDecimal("RCV_NOR_ITR"));
				illegalRecord.set("padUpNorItr", debtInfo.getBigDecimal("PAD_UP_NOR_ITR"));
				illegalRecord.set("rcvDftItr", debtInfo.getBigDecimal("RCV_DFT_ITR"));
				illegalRecord.set("padUpDftItr", debtInfo.getBigDecimal("PAD_UP_DFT_ITR"));
				illegalRecord.set("rcvPnsItr", debtInfo.getBigDecimal("RCV_PNS_ITR"));
				illegalRecord.set("padUpPnsItr", debtInfo.getBigDecimal("PAD_UP_PNS_ITR"));
				illegalRecord.set("dftOtdItr", debtInfo.getBigDecimal("DFT_OTD_ITR"));
				illegalRecord.set("overdueCapi", v_peri_overdue_capi);
				illegalRecord.set("overdueItr", v_peri_overdue_itr);
				illegalRecord.set("overduePnsItr", v_peri_punish_itr);
				illegalRecord.set("updateTime", worktime);
				illegalRecord.set("remark", overdueType);

				DatabaseUtil.saveEntity("default", illegalRecord);
			}

			// System.out.println("----------------------结束：同步分期违约记录表------------------------");

			// --累计逾期次数
			DataObject illegalRecord = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanIllegalRecord");
			illegalRecord.set("summaryNum", due_num);
			long v_ljyqcs = DatabaseUtil.countByTemplate("default", illegalRecord);

			// --连续逾期次数
			illegalRecord.set("status", "03");
			long v_lxyqcs = DatabaseUtil.countByTemplate("default", illegalRecord);

			String v_summary_status = null;
			if ("A".equals(sts) || "9".equals(sts)) {
				v_summary_status = "04"; // --结清
			} else if ("7".equals(sts)) {
				v_summary_status = "07"; // --核销
			} else if (v_lxyqcs > 0) {
				v_summary_status = "03"; // --逾期
			} else {
				v_summary_status = "02"; // --正常
			}
			
			// 逾期天数
			int v_yqts = 0;
			Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.batch.dealAccount.getOverdueDate", illegalRecord);
			if (objs != null && objs.length == 1 && v_summary_status.equals("03")) {
				Date overdueDate = (Date) objs[0];
				v_yqts = (int) (DateUtil.getIntervalDays(overdueDate, workdate) + 1);
			}

			// --更新借据表
			DataObject summaryData = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summaryData.setString("summaryNum", due_num);
			summaryData.setString("summaryStatusCd", v_summary_status);
			summaryData.setString("deavSts", deva_sts); // 减值状态
			summaryData.set("jjye", loanInfo.getBigDecimal("V_JJYE")); // 借据余额
			summaryData.set("dftItrIn", v_dft_itr_in); // 累计表内欠息
			summaryData.set("dftItrOut", v_dft_itr_out); // 累计表外欠息
			summaryData.set("dftItr", v_dft_itr_in.add(v_dft_itr_out)); // 累计欠息
			summaryData.set("ljfx", v_ljfx_in.add(v_ljfx_out)); // 累计罚息
			summaryData.set("normalItr", v_normal_itr); // 正常利息
			summaryData.set("arrearItr", v_arrear_itr); // 拖欠利息
			summaryData.set("punishItr", v_punish_itr); // 罚息
			summaryData.set("yqts", v_yqts); // 逾期天数
			summaryData.set("jjyqbj", loanInfo.getBigDecimal("OVERDUECAPITAL")); // 逾期本金
			summaryData.set("lxyqcs", v_lxyqcs); // 连续逾期次数
			summaryData.set("ljyqcs", v_ljyqcs); // 累计逾期次数
			summaryData.set("updateTime", worktime);

			DataObject template = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			template.setString("summaryNum", due_num);

			DatabaseUtil.updateEntityByTemplate("default", summaryData, template);
		} else {
			System.out.println("--------借据号：【" + dueNum + "】在计量没有找到！----------");
		}
		System.out.println("-------结束 >> 同步计量单笔账务：借据号：【" + dueNum + "】-------");

	}

}
