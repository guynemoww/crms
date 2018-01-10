/**
 * 
 */
package com.bos.batch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.DateStyle;
import com.bos.pub.DateUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author kf_xdxt11
 * @date 2016-07-14 16:18:36
 * 
 */
@Bizlet("同步记录基准利率")
public class SynchBaseRate {

	/**
	 * @author kf_xdxt11
	 * 
	 */
	@Bizlet("同步")
	public static void synch(String summaryNum) {
		HashMap<String, String> para = new HashMap<String, String>();
		para.put("summaryNum", summaryNum);
		Object[] baseRateInfos = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.synchBaseRate.queryBaseRateInfo", para);

		if (baseRateInfos != null && baseRateInfos.length > 0) {
			for (int i = 0; i < baseRateInfos.length; i++) {
				DataObject baseRateInfo = (DataObject) baseRateInfos[i];

				Date workdate = GitUtil.getBusiDate();// 工作日期
				Date worktime = GitUtil.getBusiTimestamp();// 工作时间

				// --计算调整日期
				String yearStr = DateUtil.DateToString(workdate, "yyyy"); // 年份
				int monthNum = DateUtil.getMonth(workdate);// 月份
				String itrDate = null; // --利率调整日期
				String ir_update_frequency = baseRateInfo.getString("IR_UPDATE_FREQUENCY");

				if ("01".equals(ir_update_frequency)) {// 按月调整:1.1/2.1/3.1...
					itrDate = DateUtil.DateToString(DateUtil.addDay(DateUtil.getLastDateOfMonth(workdate), 1), DateStyle.YYYY_MM_DD_8L);
				} else if ("02".equals(ir_update_frequency)) {// 按季调整1.1/4.1/7.1/10.1
					int quar = DateUtil.getSeason(workdate);// 当前季度，下季度初调整
					if (1 == quar) {
						itrDate = yearStr + "0401";
					} else if (2 == quar) {
						itrDate = yearStr + "0701";
					} else if (3 == quar) {
						itrDate = yearStr + "1001";
					} else if (4 == quar) {
						itrDate = (Integer.valueOf(yearStr) + 1) + "0101";
					}
				} else if ("03".equals(ir_update_frequency)) { // 按半年调整1.1/6.1
					if (monthNum > 6) {
						itrDate = (Integer.valueOf(yearStr) + 1) + "0101";// 下半年
					} else {
						itrDate = yearStr + "0701";
					}
				} else if ("04".equals(ir_update_frequency)) {// 按年调整 1.1
					itrDate = (Integer.valueOf(yearStr) + 1) + "0101";
				}
				if (itrDate == null) {
					System.out.println("基准利率调整，【利率调整日期】计算失败！");
				}

				// 执行利率计算
				BigDecimal yearRate = null;// 计算后的执行利率
				BigDecimal int_rate = baseRateInfo.getBigDecimal("INT_RATE");// 新的基准利率
				String float_way = baseRateInfo.getString("FLOAT_WAY");// 浮动方式
				BigDecimal rate_float_proportion = baseRateInfo.getBigDecimal("RATE_FLOAT_PROPORTION");// 浮动比例

				if ("1".equals(float_way)) {
					yearRate = int_rate.multiply(BigDecimal.valueOf(1).add(rate_float_proportion.divide(BigDecimal.valueOf(100)))).setScale(6,
							RoundingMode.HALF_EVEN);// 上浮
				} else if ("2".equals(float_way)) {
					yearRate = int_rate.multiply(BigDecimal.valueOf(1).subtract(rate_float_proportion.divide(BigDecimal.valueOf(100)))).setScale(6,
							RoundingMode.HALF_EVEN);// 下浮
				}
				if (yearRate == null) {
					System.out.println("基准利率调整，【执行利率计算】计算失败！");
				}

				// 罚息利率计算
				BigDecimal overdue_rate_up_proportion = baseRateInfo.getBigDecimal("OVERDUE_RATE_UP_PROPORTION");// 罚息利率上浮比率
				BigDecimal overdueRate = yearRate.multiply(BigDecimal.valueOf(1).add(overdue_rate_up_proportion.divide(BigDecimal.valueOf(100))))
						.setScale(6, RoundingMode.HALF_EVEN);// 罚息利率

				// 更新借据利率信息的基准利率和执行利率（申请利率）
				DataObject template = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
				template.setString("loanId", baseRateInfo.getString("LOAN_ID"));

				DataObject loanLoanrate = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanLoanrate");
				loanLoanrate.setBigDecimal("baseRateValue", int_rate);
				loanLoanrate.setBigDecimal("yearRate", yearRate);
				loanLoanrate.setDate("updateTime", worktime);
				DatabaseUtil.updateEntityByTemplate("default", loanLoanrate, template);

				// 将需要利率调整的数据插入计量数据库
				Map<String, Object> parameterObject = new HashMap<String, Object>();
				// parameterObject.put("uuid", "");//主键
				parameterObject.put("due_num", baseRateInfo.getString("SUMMARY_NUM")); // 借据编号
				parameterObject.put("rcv_date", DateUtil.DateToString(workdate, DateStyle.YYYY_MM_DD_8L)); // 数据上传日期
				parameterObject.put("itr_date", itrDate); // 利率调整日期
				parameterObject.put("fin_flg", "0"); // 完成标志:初始状态
				// 判断唯一索引
				int count = DatabaseExt.countByNamedSql("sdp", "com.bos.batch.synchBaseRate.countBaseRateInfo", parameterObject);
				if (count == 0) {
					parameterObject.put("leg_per_cod", "3600"); // 法人代码
					parameterObject.put("prv_cod", baseRateInfo.getString("PRV_COD")); // 区域代码
					parameterObject.put("opn_dep", baseRateInfo.getString("ORG_NUM")); // 开户机构
					parameterObject.put("tal_dep", baseRateInfo.getString("ORG_NUM")); // 核算机构
					parameterObject.put("prm_cls", baseRateInfo.getString("PRM_CLS")); // 贷款主类别
					parameterObject.put("ast_cls", baseRateInfo.getString("AST_CLS")); // 贷款子类别
					parameterObject.put("tran_from", "CRMS"); // 业务渠道来源
					parameterObject.put("nor_itr_rate", yearRate); // 正常利率
					parameterObject.put("del_itr_rate", overdueRate); // 罚息利率
					parameterObject.put("cpd_itr_rate", overdueRate); // 复利利率
					parameterObject.put("fin_date", ""); // 完成日期
					parameterObject.put("rmk1", "batch"); // 保留1
					parameterObject.put("rmk2", baseRateInfo.getString("FREQUENCY_NAME")); // 保留2 //调整频率（01按月02按季03按半年04按年）
					parameterObject.put("create_time", worktime); // 创建时间
					parameterObject.put("update_time", worktime); // 更新时间
					parameterObject.put("trunc_no", 1);// 乐观锁

					DatabaseExt.executeNamedSql("aplus", "com.bos.batch.synchBaseRate.insertRateInfo", parameterObject);
				}
			}
		}
	}

}
