package com.primeton.plusclient;

import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012_AccoutingRes;
import com.primeton.plus.AssetOffControlRq;
import com.primeton.plus.AssetOffRq;
import com.primeton.plus.BusiOrgSplitRq;
import com.primeton.plus.ChangeCredTimeRq;
import com.primeton.plus.ChangeInterControlRq;
import com.primeton.plus.ChangeIntrRq;
import com.primeton.plus.CrePayQueryRq;
import com.primeton.plus.CredMenu;
import com.primeton.plus.CredPeriodChangeRq;
import com.primeton.plus.DelayTime;
import com.primeton.plus.DiscountBackRq;
import com.primeton.plus.DiscountStopRq;
import com.primeton.plus.DuePlanChangeRq;
import com.primeton.plus.ExtendTimeAppRq;
import com.primeton.plus.FirstAjustPeriodRq;
import com.primeton.plus.ImpproviMenu;
import com.primeton.plus.InterSettMenu;
import com.primeton.plus.LoanCancelControlRq;
import com.primeton.plus.LoanCancelRq;
import com.primeton.plus.MoneyPayOffRq;
import com.primeton.plus.OffCaculate;
import com.primeton.plus.PayInterMenu;
import com.primeton.plus.PayOutCheckRq;
import com.primeton.plus.PayOutRq;
import com.primeton.plus.PayVerifBackControlRq;
import com.primeton.plus.PayVerifControlRq;
import com.primeton.plus.QueryCredPayPlanRq;
import com.primeton.plus.RepayAccChangeRq;
import com.primeton.plus.RepayCancel;
import com.primeton.plus.RepayControlCancel;
import com.primeton.plus.RepayControlInfRq;
import com.primeton.plus.RepayMenu;
import com.primeton.plus.RepayOldNewRq;
import com.primeton.plus.RepayPlanChangeRq;
import com.primeton.plus.RepayWayChangeRq;
import com.primeton.plus.RepaymentRq;
import com.primeton.plus.SettleMenu;
import com.primeton.plus.StopControlRq;
import com.primeton.plus.StopItrRq;
import com.primeton.plus.StopStopControlRq;
import com.primeton.plus.StopStopItrRq;
import com.primeton.plus.VerificationBackRq;
import com.primeton.plus.VerificationRq;
import com.primeton.plus.VouComMenu;


public interface CrmsCallPlusProxy {
		/*
		 * 结清试算 
		 */
		 OffCaculate executeT1100(OffCaculate offCaculate)   throws Exception;
		/*
		 * 放款 
		 */
		PayOutRq executeT1101(PayOutRq payOutRq) throws Exception;
		/*
		 * 还款 
		 */
		RepaymentRq executeT1102(RepaymentRq repaymentRq) throws Exception;
		/*
		 * 停息 
		 */
		StopItrRq executeT1103(StopItrRq stopItrRq)   throws Exception;
		/*
		 * 终止停息 
		 */
		StopStopItrRq executeT1104(StopStopItrRq stopStopItrRq)   throws Exception;
		/*
		 * 调整利息 
		 */
		ChangeIntrRq executeT1105(ChangeIntrRq changeIntrRq)   throws Exception;
		/*
		 * 核销 
		 */
		VerificationRq executeT1107(VerificationRq verificationRq)   throws Exception;
		/*
		 * 核销收回 
		 */
		VerificationBackRq executeT1108(VerificationBackRq verificationBackRq)   throws Exception;
		/*
		 * 撤销 
		 */
		LoanCancelRq executeT1110(LoanCancelRq loanCancelRq)   throws Exception;
		/*
		 * 还款控制信息 
		 */
		RepayControlInfRq executeT1202(RepayControlInfRq repayControlInfRq)   throws Exception;
		/*
		 * 停息控制信息 
		 */
		StopControlRq executeT1203(StopControlRq stopControlRq)   throws Exception;
		/*
		 * 终止停息控制信息 
		 */
		StopStopControlRq executeT1204(StopStopControlRq stopStopControlRr)   throws Exception;
		/*
		 * 调整利息控制信息 
		 */
		ChangeInterControlRq executeT1205(ChangeInterControlRq changeInterControlRq)   throws Exception;
		/*
		 * 核销控制信息
		 */
		PayVerifControlRq executeT1207(PayVerifControlRq payVerifControlRq)   throws Exception;
		/*
		 * 核销收回控制信息 
		 */
		PayVerifBackControlRq executeT1208(PayVerifBackControlRq payVerifBackControlRq)   throws Exception;
		/*
		 * 抵债资产冲销贷款控制信息 
		 */
		AssetOffControlRq executeT1206(AssetOffControlRq assetOffControlRq)   throws Exception;
		/*
		 * 撤销控制信息 
		 */
		LoanCancelControlRq executeT1210(LoanCancelControlRq loanCancelControlRq)   throws Exception;
		/*
		 * 贷款出账检查 
		 */
		PayOutCheckRq executeT1401(PayOutCheckRq payOutCheckRq)   throws Exception;
		/*
		 * 还款账号变更 
		 */
		RepayAccChangeRq executeT1402(RepayAccChangeRq repayAccChangeRq)   throws Exception;
		/*
		 * 暂停贴息 
		 */
		DiscountStopRq executeT1403(DiscountStopRq discountStopRq)   throws Exception;
		/*
		 * 恢复贴息 
		 */
		DiscountBackRq executeT1404(DiscountBackRq discountBackRs)   throws Exception;
		/*
		 * 还款方式变更 
		 */
		RepayWayChangeRq executeT1405(RepayWayChangeRq repayWayChangeRq)   throws Exception;
		/*
		 * 贷款期限变更 
		 */
		CredPeriodChangeRq executeT1406(CredPeriodChangeRq credPeriodChangeRq)   throws Exception;
		/*
		 * 调整阶段性首次还本期数
		 */
		FirstAjustPeriodRq executeT1407(FirstAjustPeriodRq firstAjustPeriodRq)   throws Exception;
		/*
		 * 调整贴息到期日 
		 */
		ChangeCredTimeRq executeT1408(ChangeCredTimeRq changeCredTimeRq)   throws Exception;
		/*
		 * 贷款本息查询 
		 */
		CrePayQueryRq executeT1410(CrePayQueryRq crePayQueryRq)   throws Exception;
		/*
		 * 业务拆分申请及机构拆分申请 
		 */
		BusiOrgSplitRq executeT1411(BusiOrgSplitRq busiOrgSplitRq)   throws Exception;
		/*
		 * 展期申请控制信息
		 */
		ExtendTimeAppRq executeT1412(ExtendTimeAppRq extendTimeAppRq)   throws Exception;
		/*
		 * 查询贷款还款计划表 
		 */
		QueryCredPayPlanRq executeT1413(QueryCredPayPlanRq queryCredPayPlanRq)   throws Exception;
		/*
		 * 还本计划变更控制信息 
		 */
		RepayPlanChangeRq executeT1415(RepayPlanChangeRq repayPlanChangeRq)   throws Exception;
		/*
		 * 还息计划变更控制信息 
		 */
		DuePlanChangeRq executeT1417(DuePlanChangeRq duePlanChangeRq)   throws Exception;
		/*
		 * 本金归还完成后调整累计利息标志 
		 */
		MoneyPayOffRq executeT1418(MoneyPayOffRq moneyPayOffRq)   throws Exception;
		/*
		 * 借新还旧
		 */
		RepayOldNewRq executeT1115(RepayOldNewRq repayOldNewRq)   throws Exception;
		/*
		 * 逾期展期
		 */
		DelayTime executeT1419(DelayTime delayTime)   throws Exception;
		/*
		 * 放款清单
		 */
		CredMenu executeT1302(CredMenu credMenu) throws Exception;
		/*
		 * 还款清单
		 */
		RepayMenu executeT1303(RepayMenu repayMenu) throws Exception;
		/*
		 * 结息清单
		 */
		InterSettMenu executeT1304(InterSettMenu interSettMenu) throws Exception;
		/*
		 * 结清清单
		 */
		SettleMenu executeT1305(SettleMenu settleMenu) throws Exception;
		/*
		 * 计提减值清单
		 */
		ImpproviMenu executeT1306(ImpproviMenu impproviMenu) throws Exception;
		/*
		 * 贴息清单
		 */
		PayInterMenu executeT1308(PayInterMenu payInterMenu) throws Exception;
		/*
		 * 凭证补丁
		 */
		VouComMenu executeT1301(VouComMenu vouComMenu) throws Exception;
		/*
		 * 通用记账
		 */
		OXD012_AccoutingRes executeXD01(OXD011_AccoutingReq requestBody) throws Exception;
		/*
		 *放款撤销 
		 */
		RepayCancel executeB1101(RepayCancel repayCancel) throws Exception;
		/*
		 * 抵债资产冲销
		 */
		AssetOffRq executeT1106(AssetOffRq assetOffRq) throws Exception; 
		/*
		 * 还款控制信息撤销
		 */
		public RepayControlCancel executeB1102(RepayControlCancel repayControlCancel) throws Exception; 
}
