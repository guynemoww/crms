/**
 * 
 */
package com.primeton.tsl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import oracle.sql.TIMESTAMP;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.BeanToMapUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.primeton.mgrcore.FXD011;
import com.primeton.plus.BusiOrgSplitRq;
import com.primeton.plus.ChangeInterControlRq;
import com.primeton.plus.ChangeIntrRq;
import com.primeton.plus.CrePayQueryRq;
import com.primeton.plus.CredMenu;
import com.primeton.plus.CredPeriodChangeRq;
import com.primeton.plus.DelayTime;
import com.primeton.plus.DiscountBackRq;
import com.primeton.plus.DiscountStopRq;
import com.primeton.plus.ExtendTimeAppRq;
import com.primeton.plus.FirstAjustPeriodRq;
import com.primeton.plus.ImpproviMenu;
import com.primeton.plus.InterSettMenu;
import com.primeton.plus.LoanCancelControlRq;
import com.primeton.plus.LoanCancelRq;
import com.primeton.plus.OffCaculate;
import com.primeton.plus.PayInterMenu;
import com.primeton.plus.PayOutRq;
import com.primeton.plus.PayVerifBackControlRq;
import com.primeton.plus.PayVerifControlRq;
import com.primeton.plus.QueryCredPayPlanRq;
import com.primeton.plus.RepayAccChangeRq;
import com.primeton.plus.RepayCancel;
import com.primeton.plus.RepayControlCancel;
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
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.xfire.XFireRuntimeException;

import commonj.sdo.DataObject;

/**
 * @author git
 * @date 2015-05-28 15:35:03
 * 
 */
@Bizlet("")
public class TransferDataUtil {
	private String MA1_1101 = "MA1_1101";
	private String MA1_1400 = "MA1_1400";
	private String MA1_1401 = "MA1_1401";
	private String MA1_1405 = "MA1_1405";
	private String MA1_1414 = "MA1_1414";
	private String MA1_1500 = "MA1_1500";
	private String DBMChgPlan = "DBMChgPlan";
	private String MA1_5101 = "MA1_5101";
	private String MA1_5102 = "MA1_5102";
	private String MA1_5103 = "MA1_5103";
	private String MA1_5104 = "MA1_5104";
	private String MA1_5105 = "MA1_5105";
	private String MA1_5106 = "MA1_5106";
	private String MA1_5107 = "MA1_5107";
	private String MA1_5108 = "MA1_5108";
	private String MA1_5109 = "MA1_5109";
	private String MA1_5110 = "MA1_5110";
	private String MA1_5111 = "MA1_5111";
	private String MA1_5112 = "MA1_5112";
	private String MA1_5113 = "MA1_5113";
	private String MA1_5114 = "MA1_5114";
	private String MA1_5115 = "MA1_5115";
	private String MA1_5116 = "MA1_5116";
	private String MA1_5117 = "MA1_5117";
	private String MA1_5118 = "MA1_5118";
	private String T1413 = "T1413";
	private String MA1_5119 = "MA1_5119";
	private String MA1_5120 = "MA1_5120";
	private String MA1_5121 = "MA1_5121";
	private String MA1_5122 = "MA1_5122";
	private String MA1_5123 = "MA1_5123";
	private String MA1_5124 = "MA1_5124";
	private String MA1_5125 = "MA1_5125";
	private String MA1_5126 = "MA1_5126";
	private String MA1_5127 = "MA1_5127";
	private String MA1_5128 = "MA1_5128";
	private String MA1_5129 = "MA1_5129";//业务拆分
	private String MA1_5130 = "MA1_5130";
	private static TraceLogger log = new TraceLogger(TransferDataUtil.class);


	@Bizlet("")
	public boolean transferData(String dueNum) {
		boolean retBl = true;
		try {
			if (dueNum == null) {
				throw new Exception("借据号不能为空！");
			}
			log.info("出账：从管理库导入数据到计量库；dueNum==" + dueNum);
			String CRMS_DS_NAME = "crms";
//			String SDP_DS_NAME = "sdp";
			String SDP_DS_NAME = "aplus";

			HashMap<String, Object> paramHM = new HashMap<String, Object>();
			paramHM.put("dueNum", dueNum);

			// 贷款主协议表
			Object[] TcSupLoanInfoCrms = DatabaseExt.queryByNamedSql(CRMS_DS_NAME, "com.primeton.tsl.transferData.queryTcSupLoanInfoAplus", paramHM);
			if (TcSupLoanInfoCrms != null && TcSupLoanInfoCrms.length > 0) {
				
				DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME, "com.primeton.tsl.transferData.insertTcSupLoanInfoAplus", new Object[]{TcSupLoanInfoCrms[0]});

				// 放款、还款账户协议
				Object[] TcSupLoanInfoAcctCrms = DatabaseExt.queryByNamedSql(CRMS_DS_NAME,
						"com.primeton.tsl.transferData.queryTcSupLoanInfoAcctAplus", paramHM);
				if (TcSupLoanInfoAcctCrms != null && TcSupLoanInfoAcctCrms.length > 0) {
					
					DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME, "com.primeton.tsl.transferData.insertTcSupLoanInfoAcctAplus", TcSupLoanInfoAcctCrms);
				}
				// 分期贷款计算还款计划协议
				Object[] TcSupLoanInfoCalPayPlanCrms = DatabaseExt.queryByNamedSql(CRMS_DS_NAME,
						"com.primeton.tsl.transferData.queryTcSupLoanInfoCalPayPlanAplus", paramHM);
				if (TcSupLoanInfoCalPayPlanCrms != null && TcSupLoanInfoCalPayPlanCrms.length > 0) {
					DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME, "com.primeton.tsl.transferData.insertTcSupLoanInfoCalPayPlanAplus",
							TcSupLoanInfoCalPayPlanCrms);
				}
				// 贴息协议
				Object[] TcSupLoanInfoDiscInfoCrms = DatabaseExt.queryByNamedSql(CRMS_DS_NAME,
						"com.primeton.tsl.transferData.queryTcSupLoanInfoDiscInfoAplus", paramHM);
				if (TcSupLoanInfoDiscInfoCrms != null && TcSupLoanInfoDiscInfoCrms.length > 0) {
					DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME, "com.primeton.tsl.transferData.insertTcSupLoanInfoDiscInfoAplus",
							TcSupLoanInfoDiscInfoCrms);
				}
				// 委托贷款协议
				Object[] TcSupLoanInfoEntrInfoCrms = DatabaseExt.queryByNamedSql(CRMS_DS_NAME,
						"com.primeton.tsl.transferData.queryTcSupLoanInfoEntrInfoAplus", paramHM);
				if (TcSupLoanInfoEntrInfoCrms != null && TcSupLoanInfoEntrInfoCrms.length > 0) {
					DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME, "com.primeton.tsl.transferData.insertTcSupLoanInfoEntrInfoAplus",
							TcSupLoanInfoEntrInfoCrms);
				}
				// 还本计划协议
				Object[] TcSupPrinPlanNCrms = DatabaseExt.queryByNamedSql(CRMS_DS_NAME, "com.primeton.tsl.transferData.queryTcSupPrinPlanNAplus",
						paramHM);
				if (TcSupPrinPlanNCrms != null && TcSupPrinPlanNCrms.length > 0) {
					DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME, "com.primeton.tsl.transferData.insertTcSupPrinPlanNAplus", TcSupPrinPlanNCrms);
				}
				// 贴现协议
				Object[] TcSupLoanInfoDiscNoteCrms = DatabaseExt.queryByNamedSql(CRMS_DS_NAME,
						"com.primeton.tsl.transferData.queryTcSupLoanInfoDiscNoteAplus", paramHM);
				if (TcSupLoanInfoDiscNoteCrms != null && TcSupLoanInfoDiscNoteCrms.length > 0) {
					DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME, "com.primeton.tsl.transferData.insertTcSupLoanInfoDiscNoteAplus",
							TcSupLoanInfoDiscNoteCrms);
				}
				// 受托支付账户
				Object[] tcSupTrustPayAcctCrms = DatabaseExt.queryByNamedSql(CRMS_DS_NAME,
						"com.primeton.tsl.transferData.queryTcSupTrustPayAcctAplus", paramHM);
				if (tcSupTrustPayAcctCrms != null && tcSupTrustPayAcctCrms.length > 0) {
					for (Object obj : tcSupTrustPayAcctCrms) {
						HashMap hm = (HashMap) obj;
						Object ct = hm.get("CREATE_TIME");
						if (ct != null) {
							TIMESTAMP tct = (TIMESTAMP) ct;
							Timestamp timestampValueC = tct.timestampValue();
							hm.put("CREATE_TIME", timestampValueC);
						} else {
							hm.put("CREATE_TIME", GitUtil.currDateTime());
						}
					}
					DatabaseExt.executeNamedSqlBatch(SDP_DS_NAME, "com.primeton.tsl.transferData.insertTcSupTrustPayAcctAplus",
							tcSupTrustPayAcctCrms);
				}
			} else {
				retBl = false;
			}
		} catch (Exception e) {
			retBl = false;
			e.printStackTrace();
			log.error("出账：管理到计量导数据错误；" + e.getMessage());
		}

		return retBl;
	}

	@Bizlet("")
	public BOSFXII initBOSFXII(String trnCod, Object iObjectVo) throws Exception {
		BOSFXII bosfxii = new BOSFXII();

		try {
			if (MA1_1400.equals(trnCod)) {
				PayOutRq payOutRq = (PayOutRq) iObjectVo;
				bosfxii.bosfxRq = payOutRq;
			} else if (MA1_1101.equals(trnCod)) {
				PayOutRq payOutRq = (PayOutRq) iObjectVo;
				bosfxii.bosfxRq = payOutRq;
			}else if (MA1_1500.equals(trnCod)) {
				OffCaculate offCaculate = (OffCaculate) iObjectVo;
				bosfxii.bosfxRq = offCaculate;
			}else if (MA1_1401.equals(trnCod)) {
				RepayAccChangeRq repayAccChangeRq = (RepayAccChangeRq) iObjectVo;
				bosfxii.bosfxRq = repayAccChangeRq;
			}else if(MA1_5101.equals(trnCod)){
				RepaymentRq repaymentRq = (RepaymentRq) iObjectVo;
				bosfxii.bosfxRq = repaymentRq;
			}else if(MA1_5102.equals(trnCod)){
				CrePayQueryRq repaymentRq = (CrePayQueryRq) iObjectVo;
				bosfxii.bosfxRq = repaymentRq;
			} else if(DBMChgPlan.equals(trnCod)){
				  RepayPlanChangeRq repayPlanChangeRq = (RepayPlanChangeRq) iObjectVo;
					bosfxii.bosfxRq = repayPlanChangeRq;
			}else if(MA1_5103.equals(trnCod)){
				DiscountStopRq discountStopRq = (DiscountStopRq) iObjectVo;
				bosfxii.bosfxRq = discountStopRq;
			}else if(MA1_5104.equals(trnCod)){
				DiscountBackRq discountBackRq = (DiscountBackRq) iObjectVo;
				bosfxii.bosfxRq = discountBackRq;
			}else if(MA1_5105.equals(trnCod)){
				StopControlRq stopControlRq = (StopControlRq) iObjectVo;
				bosfxii.bosfxRq = stopControlRq;
			}else if(MA1_5106.equals(trnCod)){
				StopItrRq stopItrRq = (StopItrRq) iObjectVo;
				bosfxii.bosfxRq = stopItrRq;
			}else if(MA1_5107.equals(trnCod)){
				StopStopControlRq stopStopControlRq = (StopStopControlRq) iObjectVo;
				bosfxii.bosfxRq = stopStopControlRq;
			}else if(MA1_5108.equals(trnCod)){
				StopStopItrRq stopStopItrRq = (StopStopItrRq) iObjectVo;
				bosfxii.bosfxRq = stopStopItrRq;
			}else if (MA1_1405.equals(trnCod)) {
				CredPeriodChangeRq credPeriodChangeRq = (CredPeriodChangeRq) iObjectVo;
				bosfxii.bosfxRq = credPeriodChangeRq;
			}else if(MA1_5109.equals(trnCod)){
				ChangeInterControlRq  changeInterControlRq = (ChangeInterControlRq) iObjectVo;
				bosfxii.bosfxRq = changeInterControlRq;
			}else if(MA1_5110.equals(trnCod)){
				RepayWayChangeRq  repayWayChangeRq = (RepayWayChangeRq) iObjectVo;
				bosfxii.bosfxRq = repayWayChangeRq;
			}else if (MA1_1414.equals(trnCod)) {
				ExtendTimeAppRq extendTimeAppRq = (ExtendTimeAppRq) iObjectVo;
				bosfxii.bosfxRq = extendTimeAppRq;
			}else if(MA1_5111.equals(trnCod)){
				PayVerifControlRq  payVerifControlRq = (PayVerifControlRq) iObjectVo;
				bosfxii.bosfxRq = payVerifControlRq;
			}else if(MA1_5112.equals(trnCod)){
				VerificationRq  verificationRq = (VerificationRq) iObjectVo;
				bosfxii.bosfxRq = verificationRq;
			}else if(MA1_5113.equals(trnCod)){
				PayVerifBackControlRq  payVerifBackControlRq = (PayVerifBackControlRq) iObjectVo;
				bosfxii.bosfxRq = payVerifBackControlRq;
			}else if(MA1_5114.equals(trnCod)){
				VerificationBackRq  verificationBackRq = (VerificationBackRq) iObjectVo;
				bosfxii.bosfxRq = verificationBackRq;
			}else if(MA1_5115.equals(trnCod)){
				FirstAjustPeriodRq  firstAjustPeriodRq = (FirstAjustPeriodRq) iObjectVo;
				bosfxii.bosfxRq = firstAjustPeriodRq;
			}else if(MA1_5116.equals(trnCod)){
				ChangeIntrRq  changeIntrRq = (ChangeIntrRq) iObjectVo;
				bosfxii.bosfxRq = changeIntrRq;
			}else if(MA1_5117.equals(trnCod)){
				LoanCancelControlRq  loanCancelControlRq = (LoanCancelControlRq) iObjectVo;
				bosfxii.bosfxRq = loanCancelControlRq;
			}else if(MA1_5118.equals(trnCod)){
				LoanCancelRq  loanCancelRq = (LoanCancelRq) iObjectVo;
				bosfxii.bosfxRq = loanCancelRq;
			}else if(T1413.equals(trnCod)){
				QueryCredPayPlanRq  queryCredPayPlanRq = (QueryCredPayPlanRq) iObjectVo;
				bosfxii.bosfxRq = queryCredPayPlanRq;
			}else if(MA1_5119.equals(trnCod)){
				RepayOldNewRq  repayOldNewRq = (RepayOldNewRq) iObjectVo;
				bosfxii.bosfxRq = repayOldNewRq;
			}else if(MA1_5120.equals(trnCod)){
				CredMenu  credMenu = (CredMenu) iObjectVo;
				bosfxii.bosfxRq = credMenu;
			}else if(MA1_5121.equals(trnCod)){
				RepayMenu  repayMenu = (RepayMenu) iObjectVo;
				bosfxii.bosfxRq = repayMenu;
			}else if(MA1_5122.equals(trnCod)){
				InterSettMenu  repayOldNewRq = (InterSettMenu) iObjectVo;
				bosfxii.bosfxRq = repayOldNewRq;
			}else if(MA1_5123.equals(trnCod)){
				SettleMenu  settleMenu = (SettleMenu) iObjectVo;
				bosfxii.bosfxRq = settleMenu;
			}else if(MA1_5124.equals(trnCod)){
				ImpproviMenu  impproviMenu = (ImpproviMenu) iObjectVo;
				bosfxii.bosfxRq = impproviMenu;
			}else if(MA1_5125.equals(trnCod)){
				PayInterMenu  payInterMenu = (PayInterMenu) iObjectVo;
				bosfxii.bosfxRq = payInterMenu;
			}else if(MA1_5126.equals(trnCod)){
				VouComMenu  repayOldNewRq = (VouComMenu) iObjectVo;
				bosfxii.bosfxRq = repayOldNewRq;
			}else if(MA1_5127.equals(trnCod)){
				RepayCancel  repayCancel = (RepayCancel) iObjectVo;
				bosfxii.bosfxRq = repayCancel;
			}else if(MA1_5128.equals(trnCod)){
				RepayControlCancel  repayControlCancel = (RepayControlCancel) iObjectVo;
				bosfxii.bosfxRq = repayControlCancel;
			} else if(MA1_5129.equals(trnCod)){
				BusiOrgSplitRq  busiOrgSplitRq = (BusiOrgSplitRq) iObjectVo;
				bosfxii.bosfxRq = busiOrgSplitRq;
			}else if(MA1_5130.equals(trnCod)){
				DelayTime  busiOrgSplitRq = (DelayTime) iObjectVo;
				bosfxii.bosfxRq = busiOrgSplitRq;
			}           
			      
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("访问计量输入对象转换报文错误:" + e.getMessage());
			throw new Exception("访问计量输入对象转换报文错误:" + e.getMessage());
		}
		if (bosfxii.bosfxRq == null) {
			throw new Exception("输入报文体为空或没有找到此交易：" + trnCod);
		}
		return bosfxii;
	}
	@Bizlet("")
	public BOSFXII crmsWebService(String trnCod, BOSFXII bosfxii) throws Exception {
		BOSFXII obosfxii = new BOSFXII();
		try {
			CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
			if (MA1_1400.equals(trnCod)|| MA1_1101.equals(trnCod)){
				PayOutRq rq = (PayOutRq)bosfxii.bosfxRq;
				PayOutRq rs = proxy.executeT1101(rq);
				obosfxii.bosfxRq = rs;
			}else if (MA1_1500.equals(trnCod)) {
				OffCaculate rq = (OffCaculate)bosfxii.bosfxRq;
				OffCaculate rs = proxy.executeT1100(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5101.equals(trnCod)){
				RepaymentRq repaymentRq = (RepaymentRq) bosfxii.bosfxRq;
				RepaymentRq rs = proxy.executeT1102(repaymentRq);
				obosfxii.bosfxRq = rs;
			}else if (MA1_1401.equals(trnCod)) {
				RepayAccChangeRq rq=(RepayAccChangeRq)bosfxii.bosfxRq;
				RepayAccChangeRq rs = proxy.executeT1402(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5102.equals(trnCod)){
				CrePayQueryRq rq=(CrePayQueryRq)bosfxii.bosfxRq;
				CrePayQueryRq rs = proxy.executeT1410(rq);
				obosfxii.bosfxRq = rs;
			} else if(DBMChgPlan.equals(trnCod)){
			   RepayPlanChangeRq rq = (RepayPlanChangeRq)bosfxii.bosfxRq;
			   RepayPlanChangeRq rs = proxy.executeT1415(rq);
			   obosfxii.bosfxRq = rs;
			}else if(MA1_5103.equals(trnCod)){
				DiscountStopRq rq=(DiscountStopRq)bosfxii.bosfxRq;
				DiscountStopRq rs = proxy.executeT1403(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5104.equals(trnCod)){
				DiscountBackRq rq=(DiscountBackRq)bosfxii.bosfxRq;
				DiscountBackRq rs = proxy.executeT1404(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5105.equals(trnCod)){
				StopControlRq rq=(StopControlRq)bosfxii.bosfxRq;
				StopControlRq rs = proxy.executeT1203(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5106.equals(trnCod)){
				StopItrRq rq=(StopItrRq)bosfxii.bosfxRq;
				StopItrRq rs = proxy.executeT1103(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5107.equals(trnCod)){
				StopStopControlRq rq=(StopStopControlRq)bosfxii.bosfxRq;
				StopStopControlRq rs = proxy.executeT1204(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5108.equals(trnCod)){
				StopStopItrRq rq=(StopStopItrRq)bosfxii.bosfxRq;
				StopStopItrRq rs = proxy.executeT1104(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_1405.equals(trnCod)){
				CredPeriodChangeRq rq=(CredPeriodChangeRq)bosfxii.bosfxRq;
				CredPeriodChangeRq rs = proxy.executeT1406(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5109.equals(trnCod)){
				ChangeInterControlRq rq=(ChangeInterControlRq)bosfxii.bosfxRq;
				ChangeInterControlRq rs = proxy.executeT1205(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5110.equals(trnCod)){
				RepayWayChangeRq rq=(RepayWayChangeRq)bosfxii.bosfxRq;
				RepayWayChangeRq rs = proxy.executeT1405(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_1414.equals(trnCod)){
				ExtendTimeAppRq rq=(ExtendTimeAppRq)bosfxii.bosfxRq;
				ExtendTimeAppRq rs = proxy.executeT1412(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5111.equals(trnCod)){
				PayVerifControlRq rq=(PayVerifControlRq)bosfxii.bosfxRq;
				PayVerifControlRq rs = proxy.executeT1207(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5112.equals(trnCod)){
				VerificationRq rq=(VerificationRq)bosfxii.bosfxRq;
				VerificationRq rs = proxy.executeT1107(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5113.equals(trnCod)){
				PayVerifBackControlRq rq=(PayVerifBackControlRq)bosfxii.bosfxRq;
				PayVerifBackControlRq rs = proxy.executeT1208(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5114.equals(trnCod)){
				VerificationBackRq rq=(VerificationBackRq)bosfxii.bosfxRq;
				VerificationBackRq rs = proxy.executeT1108(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5115.equals(trnCod)){
				FirstAjustPeriodRq rq=(FirstAjustPeriodRq)bosfxii.bosfxRq;
				FirstAjustPeriodRq rs = proxy.executeT1407(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5116.equals(trnCod)){
				ChangeIntrRq rq=(ChangeIntrRq)bosfxii.bosfxRq;
				ChangeIntrRq rs = proxy.executeT1105(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5117.equals(trnCod)){
				LoanCancelControlRq rq=(LoanCancelControlRq)bosfxii.bosfxRq;
				LoanCancelControlRq rs = proxy.executeT1210(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5118.equals(trnCod)){
				LoanCancelRq rq=(LoanCancelRq)bosfxii.bosfxRq;
				LoanCancelRq rs = proxy.executeT1110(rq);
				obosfxii.bosfxRq = rs;
			}else if(T1413.equals(trnCod)){
				QueryCredPayPlanRq rq=(QueryCredPayPlanRq)bosfxii.bosfxRq;
				QueryCredPayPlanRq rs = proxy.executeT1413(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5119.equals(trnCod)){
				RepayOldNewRq rq=(RepayOldNewRq)bosfxii.bosfxRq;
				RepayOldNewRq rs = proxy.executeT1115(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5120.equals(trnCod)){
				CredMenu rq=(CredMenu)bosfxii.bosfxRq;
				CredMenu rs = proxy.executeT1302(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5121.equals(trnCod)){
				RepayMenu rq=(RepayMenu)bosfxii.bosfxRq;
				RepayMenu rs = proxy.executeT1303(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5122.equals(trnCod)){
				InterSettMenu rq=(InterSettMenu)bosfxii.bosfxRq;
				InterSettMenu rs = proxy.executeT1304(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5123.equals(trnCod)){
				SettleMenu rq=(SettleMenu)bosfxii.bosfxRq;
				SettleMenu rs = proxy.executeT1305(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5124.equals(trnCod)){
				ImpproviMenu rq=(ImpproviMenu)bosfxii.bosfxRq;
				ImpproviMenu rs = proxy.executeT1306(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5125.equals(trnCod)){
				PayInterMenu rq=(PayInterMenu)bosfxii.bosfxRq;
				PayInterMenu rs = proxy.executeT1308(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5126.equals(trnCod)){
				VouComMenu rq=(VouComMenu)bosfxii.bosfxRq;
				VouComMenu rs = proxy.executeT1301(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5127.equals(trnCod)){
				RepayCancel rq=(RepayCancel)bosfxii.bosfxRq;
				RepayCancel rs = proxy.executeB1101(rq);
				obosfxii.bosfxRq = rs;
			}else if(MA1_5128.equals(trnCod)){
				RepayControlCancel rq=(RepayControlCancel)bosfxii.bosfxRq;
				RepayControlCancel rs = proxy.executeB1102(rq);
				obosfxii.bosfxRq = rs;
			} else if(MA1_5129.equals(trnCod)){
				BusiOrgSplitRq busSplit=(BusiOrgSplitRq)bosfxii.bosfxRq;
				BusiOrgSplitRq rs = proxy.executeT1411(busSplit);
				obosfxii.bosfxRq = rs;
			} else if(MA1_5130.equals(trnCod)){
				DelayTime busSplit=(DelayTime)bosfxii.bosfxRq;
				DelayTime rs = proxy.executeT1419(busSplit);
				obosfxii.bosfxRq = rs;
			}                                 
			              
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (XFireRuntimeException e) {
			e.printStackTrace();
			obosfxii = new BOSFXII();
			obosfxii.setStatusCode(GitUtil.getSourceSysId());
			obosfxii.setMessage("捕获异常：访问计量WebService服务出现问题！");
			log.error("捕获异常：访问计量WebService服务出现问题！");
			// throw new Exception("捕获异常：访问计量WebService服务出现问题！");
		} catch (Exception e) {
			e.printStackTrace();
			obosfxii.setStatusCode(GitUtil.getSourceSysId());
			obosfxii.setStatusCode("捕获异常：" + e.getMessage());
			log.error("捕获异常：" + e.getMessage());
			// throw new Exception(e);
		}
		return obosfxii;
	}

	private byte[] marshal(Object obj) throws Exception {
		JAXBContext context = JAXBCache.instance().getJAXBContext(obj.getClass());
		Marshaller m = context.createMarshaller();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		m.marshal(obj, outputStream);
		byte[] result = outputStream.toByteArray();
		log.info(new String(result, "UTF-8"));
		System.out.println(new String(result, "UTF-8"));
		return result;
	}

	private Object unmarshal(byte[] data, Class<?> classe) throws Exception {
		JAXBContext context = JAXBCache.instance().getJAXBContext(classe);
		Unmarshaller m = context.createUnmarshaller();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		Object obj = m.unmarshal(inputStream);
		log.info(new String(data, "UTF-8"));
		System.out.println(new String(data, "UTF-8"));
		return obj;
	}

	@Bizlet("")
	public PayOutRq getBOSFXIIRsPayOutVo(String trnCod, BOSFXII bosfxii) {
		PayOutRq payOutRq = (PayOutRq) bosfxii.bosfxRq;
		if (payOutRq == null) {
			payOutRq = new PayOutRq();
		}
		return payOutRq;
	}
	@Bizlet("调用核心失败撤销核算")
	public RepayCancel getBOSFXIIRsRepayCancel(String trnCod, BOSFXII bosfxii) {
		RepayCancel repayCancel = (RepayCancel) bosfxii.bosfxRq;
		if (repayCancel == null) {
			repayCancel = new RepayCancel();
		}
		return repayCancel;
	}
	@Bizlet("")
	public RepaymentRq getBOSFXIIRsRetPaymentInfoVo(String trnCod, BOSFXII bosfxii) {
		RepaymentRq retPaymentInfoVo = (RepaymentRq) bosfxii.bosfxRq;
		if (retPaymentInfoVo == null) {
			retPaymentInfoVo = new RepaymentRq();
		}
//		retPaymentInfoVo.getBaseVO().setErrCod(bosfxii.getStatusCode());
//		retPaymentInfoVo.getBaseVO().setErrMsg(bosfxii.getMessage());
		return retPaymentInfoVo;
	}

	@Bizlet("")
	public PayConInfo getBOSFXIIRsPayConInfo(String trnCod, BOSFXII bosfxii) {
		PayConInfo payConInfo = (PayConInfo) bosfxii.bosfxRq;
		if (payConInfo == null) {
			payConInfo = new PayConInfo();
		}
		payConInfo.getBaseVO().setErrCod(bosfxii.getStatusCode());
		payConInfo.getBaseVO().setErrMsg(bosfxii.getMessage());

		return payConInfo;
	}

	@Bizlet("")
	public int getSequence() {
		return Integer.parseInt(BizNumGenerator.getLcsStan());
	}
	@Bizlet("还款控制信息撤销B1102")
	public RepayControlCancel getBOSFXIIRsRepayControlCancel(String trnCod, BOSFXII bosfxii) {
		RepayControlCancel repayControlCancel = (RepayControlCancel) bosfxii.bosfxRq;
		if (repayControlCancel == null) {
			repayControlCancel = new RepayControlCancel();
		}
		return repayControlCancel;
	}
	@Bizlet("")
	public DataObject getBOSFXIIRsDataObject(String trnCod, BOSFXII bosfxii) throws Exception {
		DataObject retDataObject = null;
		try {
			if (MA1_1400.equals(trnCod) || MA1_1101.equals(trnCod)) {
				PayOutVo payOutVo = (PayOutVo) bosfxii.bosfxRq;

				if (payOutVo == null) {
					payOutVo = new PayOutVo();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(payOutVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (MA1_1401.equals(trnCod)){
				RepayAccChangeRq repayAccChangeRq= (RepayAccChangeRq) bosfxii.bosfxRq;
				if(repayAccChangeRq == null){
					repayAccChangeRq = new RepayAccChangeRq();
				}
				retDataObject = BeanToMapUtil.convertBean(repayAccChangeRq);
			}
			else if (MA1_1500.equals(trnCod)) {
				OffCaculate caluate = (OffCaculate)bosfxii.bosfxRq;
				if(caluate == null){
					caluate = new OffCaculate();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(caluate);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5101.equals(trnCod)){
				RepaymentRq repaymentRq = (RepaymentRq)bosfxii.bosfxRq;
				if(repaymentRq == null){
					repaymentRq = new RepaymentRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(repaymentRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5102.equals(trnCod)){
				CrePayQueryRq crePayQueryRq = (CrePayQueryRq)bosfxii.bosfxRq;
				if(crePayQueryRq == null){
					crePayQueryRq = new CrePayQueryRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(crePayQueryRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(DBMChgPlan.equals(trnCod)){
				RepayPlanChangeRq repayPlanChangeRq = (RepayPlanChangeRq)bosfxii.bosfxRq;
				if(repayPlanChangeRq == null){
					repayPlanChangeRq = new RepayPlanChangeRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(repayPlanChangeRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5103.equals(trnCod)){
				DiscountStopRq repayPlanChangeRq = (DiscountStopRq)bosfxii.bosfxRq;
				if(repayPlanChangeRq == null){
					repayPlanChangeRq = new DiscountStopRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(repayPlanChangeRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5104.equals(trnCod)){
				DiscountBackRq repayPlanChangeRq = (DiscountBackRq)bosfxii.bosfxRq;
				if(repayPlanChangeRq == null){
					repayPlanChangeRq = new DiscountBackRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(repayPlanChangeRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5105.equals(trnCod)){
				StopControlRq stopControlRq = (StopControlRq)bosfxii.bosfxRq;
				if(stopControlRq == null){
					stopControlRq = new StopControlRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(stopControlRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5106.equals(trnCod)){
				StopItrRq stopItrRq = (StopItrRq)bosfxii.bosfxRq;
				if(stopItrRq == null){
					stopItrRq = new StopItrRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(stopItrRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5107.equals(trnCod)){
				StopStopControlRq stopStopControlRq = (StopStopControlRq)bosfxii.bosfxRq;
				if(stopStopControlRq == null){
					stopStopControlRq = new StopStopControlRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(stopStopControlRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5108.equals(trnCod)){
				StopStopItrRq stopStopItrRq = (StopStopItrRq)bosfxii.bosfxRq;
				if(stopStopItrRq == null){
					stopStopItrRq = new StopStopItrRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(stopStopItrRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_1405.equals(trnCod)){
				CredPeriodChangeRq credPeriodChangeRq = (CredPeriodChangeRq)bosfxii.bosfxRq;
				if(credPeriodChangeRq == null){
					credPeriodChangeRq = new CredPeriodChangeRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(credPeriodChangeRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5109.equals(trnCod)){
				ChangeInterControlRq changeInterControlRq = (ChangeInterControlRq)bosfxii.bosfxRq;
				if(changeInterControlRq == null){
					changeInterControlRq = new ChangeInterControlRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(changeInterControlRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5110.equals(trnCod)){
				RepayWayChangeRq repayWayChangeRq = (RepayWayChangeRq)bosfxii.bosfxRq;
				if(repayWayChangeRq == null){
					repayWayChangeRq = new RepayWayChangeRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(repayWayChangeRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_1414.equals(trnCod)){
				ExtendTimeAppRq extendTimeAppRq = (ExtendTimeAppRq)bosfxii.bosfxRq;
				if(extendTimeAppRq == null){
					extendTimeAppRq = new ExtendTimeAppRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(extendTimeAppRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5111.equals(trnCod)){
				PayVerifControlRq payVerifControlRq = (PayVerifControlRq)bosfxii.bosfxRq;
				if(payVerifControlRq == null){
					payVerifControlRq = new PayVerifControlRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(payVerifControlRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5112.equals(trnCod)){
				VerificationRq verificationRq = (VerificationRq)bosfxii.bosfxRq;
				if(verificationRq == null){
					verificationRq = new VerificationRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(verificationRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5113.equals(trnCod)){
				PayVerifBackControlRq payVerifBackControlRq = (PayVerifBackControlRq)bosfxii.bosfxRq;
				if(payVerifBackControlRq == null){
					payVerifBackControlRq = new PayVerifBackControlRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(payVerifBackControlRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5114.equals(trnCod)){
				VerificationBackRq verificationBackRq = (VerificationBackRq)bosfxii.bosfxRq;
				if(verificationBackRq == null){
					verificationBackRq = new VerificationBackRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(verificationBackRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5115.equals(trnCod)){
				FirstAjustPeriodRq firstAjustPeriodRq = (FirstAjustPeriodRq)bosfxii.bosfxRq;
				if(firstAjustPeriodRq == null){
					firstAjustPeriodRq = new FirstAjustPeriodRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(firstAjustPeriodRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5116.equals(trnCod)){
				ChangeIntrRq changeIntrRq = (ChangeIntrRq)bosfxii.bosfxRq;
				if(changeIntrRq == null){
					changeIntrRq = new ChangeIntrRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(changeIntrRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5117.equals(trnCod)){
				LoanCancelControlRq loanCancelControlRq = (LoanCancelControlRq)bosfxii.bosfxRq;
				if(loanCancelControlRq == null){
					loanCancelControlRq = new LoanCancelControlRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(loanCancelControlRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5118.equals(trnCod)){
				LoanCancelRq loanCancelRq = (LoanCancelRq)bosfxii.bosfxRq;
				if(loanCancelRq == null){
					loanCancelRq = new LoanCancelRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(loanCancelRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(T1413.equals(trnCod)){
				QueryCredPayPlanRq queryCredPayPlanRq = (QueryCredPayPlanRq)bosfxii.bosfxRq;
				if(queryCredPayPlanRq == null){
					queryCredPayPlanRq = new QueryCredPayPlanRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(queryCredPayPlanRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5119.equals(trnCod)){
				RepayOldNewRq repayOldNewRq = (RepayOldNewRq)bosfxii.bosfxRq;
				if(repayOldNewRq == null){
					repayOldNewRq = new RepayOldNewRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(repayOldNewRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5120.equals(trnCod)){
				CredMenu credMenu = (CredMenu)bosfxii.bosfxRq;
				if(credMenu == null){
					credMenu = new CredMenu();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(credMenu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5121.equals(trnCod)){
				RepayMenu repayMenu = (RepayMenu)bosfxii.bosfxRq;
				if(repayMenu == null){
					repayMenu = new RepayMenu();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(repayMenu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5122.equals(trnCod)){
				InterSettMenu interSettMenu = (InterSettMenu)bosfxii.bosfxRq;
				if(interSettMenu == null){
					interSettMenu = new InterSettMenu();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(interSettMenu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5123.equals(trnCod)){
				SettleMenu settleMenu = (SettleMenu)bosfxii.bosfxRq;
				if(settleMenu == null){
					settleMenu = new SettleMenu();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(settleMenu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5124.equals(trnCod)){
				ImpproviMenu impproviMenu = (ImpproviMenu)bosfxii.bosfxRq;
				if(impproviMenu == null){
					impproviMenu = new ImpproviMenu();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(impproviMenu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5125.equals(trnCod)){
				PayInterMenu payInterMenu = (PayInterMenu)bosfxii.bosfxRq;
				if(payInterMenu == null){
					payInterMenu = new PayInterMenu();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(payInterMenu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(MA1_5126.equals(trnCod)){
				VouComMenu vouComMenu = (VouComMenu)bosfxii.bosfxRq;
				if(vouComMenu == null){
					vouComMenu = new VouComMenu();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(vouComMenu);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(MA1_5129.equals(trnCod)){
				BusiOrgSplitRq busiOrgSplitRq = (BusiOrgSplitRq)bosfxii.bosfxRq;
				if(busiOrgSplitRq == null){
					busiOrgSplitRq = new BusiOrgSplitRq();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(busiOrgSplitRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}  else if(MA1_5130.equals(trnCod)){
				DelayTime busiOrgSplitRq = (DelayTime)bosfxii.bosfxRq;
				if(busiOrgSplitRq == null){
					busiOrgSplitRq = new DelayTime();
				}
				try {
					retDataObject = BeanToMapUtil.convertBean(busiOrgSplitRq);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}                                             
			    

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getBOSFXIIRsDataObject计量返回报文错误：" + e.getMessage());
			throw new Exception("getBOSFXIIRsDataObject计量返回报文错误：" + e.getMessage());
		}
		if (retDataObject == null) {
			throw new Exception("返回报文没有找到交易码：" + trnCod);
		}
		return retDataObject;
	}

	@Bizlet("")
	public String getStrByPayOutVo(PayOutVo obj) throws JAXBException {
		String result = "";
		BOSFXII bosfxii = new BOSFXII();
		bosfxii.bosfxRq = (PayOutVo) obj;

		JAXBContext context = JAXBCache.instance().getJAXBContext(bosfxii.getClass());
		Marshaller m = context.createMarshaller();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		m.marshal(bosfxii, outputStream);
		result = outputStream.toString();
		return result;
	}
	@Bizlet("将核算json转化为核心字符串")
	public FXD011[] plusToHx(String accEntJson) throws Exception{
		try {
			JSONArray obj = new JSONArray(accEntJson);
			FXD011[] req = new FXD011[obj.length()];
			for(int i=0;i<obj.length();i++){
				JSONArray obj1 = new JSONArray(obj.get(i).toString());
				FXD011 msg = new FXD011();
				msg.setDealType("0");
				msg.setDrCrFlag(obj1.get(0).toString());
				msg.setCurrCode(obj1.get(1).toString());
				if("01".equals(obj1.get(1).toString())){
					msg.setCashFlag("0");
				}else{
					msg.setCashFlag("1");
				}
				msg.setTransAmt(obj1.get(2).toString());
				msg.setAcctFromGo(obj1.get(3).toString());
				msg.setAcct(obj1.get(4).toString());
				msg.setAcctSeq(obj1.get(5).toString());
				msg.setPwdKind("00");
				msg.setSignPassFlag("1");
				msg.setVertLastboxSignFlag("0");
				msg.setFeePayType("0");
				req[i]= msg;
				}
			return req;
		} catch (JSONException e) {
			throw new Exception("转换json字符串出错：" + e.getMessage());
		}
	}
	@Bizlet("转换发生额")
	public String getAllMoney(FXD011[] fxd011s) throws Exception{
		String transAmt = "";
		//一般放款处理
		if(fxd011s.length ==2){
			transAmt = fxd011s[0].getTransAmt();
		}else if(fxd011s.length ==4){//针对借新还旧的处理
			String transAmt1 = fxd011s[0].getTransAmt();
			String tannsAmt2 = fxd011s[2].getTransAmt();
			BigDecimal b1 = new BigDecimal(transAmt1);
			BigDecimal b2 = new BigDecimal(tannsAmt2);
			transAmt = b1.add(b2).toString();
		}else{
			throw new Exception("核算传递的分录错误");
		}
		return transAmt;
	}
	@Bizlet("转换发生额")
	public String getAllMoney1(FXD011[] fxd011s) throws Exception{
		String transAmt = "";
		//受托支付--特殊处理
		if(fxd011s.length >=4){
			transAmt = fxd011s[0].getTransAmt();
		}else{
			throw new Exception("核算传递的分录错误");
		}
		return transAmt;
	}
	@Bizlet("转换发生额")
	public String getAllMoney2(FXD011[] fxd011s) throws Exception{
		String transAmt = "";
		//委托贷款
		if(fxd011s.length <9){
			transAmt = fxd011s[0].getTransAmt();
		}else{
			throw new Exception("核算传递的分录错误");
		}
		return transAmt;
	}
	@Bizlet("判断是否核心异常")
	public String getReturnCode(String returnCod) {
		// 0不是核心异常 1是核心异常
		String ret = "0";

		/*
		 * if(returnCod == null) { ret = "0"; } else if ("".equals(returnCod)) {
		 * ret = "0"; } else { int len = 0; len = returnCod.length(); if(len >=
		 * 6) { String sysId = returnCod.substring(0, 6);
		 * if(GitUtil.getSourceSysId().equals(sysId) || "400010".equals(sysId)
		 * || "999999".equals(sysId)) { ret = "0"; } } }
		 */

		HashMap<String, String> hashMap = new HashMap<String, String>();

		hashMap.put("1001", "1001");
		hashMap.put("1002", "1002");
		hashMap.put("1003", "1003");
		hashMap.put("1004", "1004");
		hashMap.put("1005", "1005");
		hashMap.put("1006", "1006");
		hashMap.put("1007", "1007");
		hashMap.put("1008", "1008");
		hashMap.put("1009", "1009");
		hashMap.put("1010", "1010");
		hashMap.put("1011", "1011");
		hashMap.put("1012", "1012");
		hashMap.put("1013", "1013");
		hashMap.put("1014", "1014");
		hashMap.put("1015", "1015");
		hashMap.put("1016", "1016");
		hashMap.put("1017", "1017");
		hashMap.put("1018", "1018");
		hashMap.put("1019", "1019");
		hashMap.put("1020", "1020");
		hashMap.put("1021", "1021");
		hashMap.put("1022", "1022");
		hashMap.put("1023", "1023");
		hashMap.put("1024", "1024");
		hashMap.put("1025", "1025");
		hashMap.put("1026", "1026");
		hashMap.put("1400", "1400");
		hashMap.put("1401", "1401");
		hashMap.put("1402", "1402");
		hashMap.put("1403", "1403");
		hashMap.put("1404", "1404");
		hashMap.put("1405", "1405");
		hashMap.put("1406", "1406");
		hashMap.put("1410", "1410");
		hashMap.put("1411", "1411");
		hashMap.put("1420", "1420");
		hashMap.put("1421", "1421");
		hashMap.put("1422", "1422");
		hashMap.put("1423", "1423");
		hashMap.put("1430", "1430");
		hashMap.put("1440", "1440");
		hashMap.put("1441", "1441");
		hashMap.put("1442", "1442");
		hashMap.put("1443", "1443");
		hashMap.put("1470", "1470");
		hashMap.put("1481", "1481");
		hashMap.put("1501", "1501");
		hashMap.put("1502", "1502");
		hashMap.put("1503", "1503");
		hashMap.put("1520", "1520");
		hashMap.put("1521", "1521");
		hashMap.put("1522", "1522");
		hashMap.put("1523", "1523");
		hashMap.put("1524", "1524");
		hashMap.put("1530", "1530");
		hashMap.put("1531", "1531");
		hashMap.put("1532", "1532");
		hashMap.put("1533", "1533");
		hashMap.put("1540", "1540");
		hashMap.put("1541", "1541");
		hashMap.put("1542", "1542");
		hashMap.put("1544", "1544");
		hashMap.put("1590", "1590");
		hashMap.put("1593", "1593");
		hashMap.put("1597", "1597");
		hashMap.put("1598", "1598");
		hashMap.put("1600", "1600");
		hashMap.put("1601", "1601");
		hashMap.put("1602", "1602");
		hashMap.put("1603", "1603");
		hashMap.put("1604", "1604");
		hashMap.put("1605", "1605");
		hashMap.put("1606", "1606");
		hashMap.put("1607", "1607");
		hashMap.put("1608", "1608");
		hashMap.put("1609", "1609");
		hashMap.put("1610", "1610");
		hashMap.put("1611", "1611");
		hashMap.put("1612", "1612");
		hashMap.put("1613", "1613");
		hashMap.put("1614", "1614");
		hashMap.put("1615", "1615");
		hashMap.put("1616", "1616");
		hashMap.put("1618", "1618");
		hashMap.put("1619", "1619");
		hashMap.put("1620", "1620");
		hashMap.put("1621", "1621");
		hashMap.put("1622", "1622");
		hashMap.put("1623", "1623");
		hashMap.put("1624", "1624");
		hashMap.put("1625", "1625");
		hashMap.put("1626", "1626");
		hashMap.put("1627", "1627");
		hashMap.put("1629", "1629");
		hashMap.put("1630", "1630");
		hashMap.put("1631", "1631");
		hashMap.put("1632", "1632");
		hashMap.put("1633", "1633");
		hashMap.put("1634", "1634");
		hashMap.put("1635", "1635");
		hashMap.put("1636", "1636");
		hashMap.put("1637", "1637");
		hashMap.put("1638", "1638");
		hashMap.put("1639", "1639");
		hashMap.put("1640", "1640");
		hashMap.put("1641", "1641");
		hashMap.put("1642", "1642");
		hashMap.put("1643", "1643");
		hashMap.put("1644", "1644");
		hashMap.put("1645", "1645");
		hashMap.put("1646", "1646");
		hashMap.put("1647", "1647");
		hashMap.put("1648", "1648");
		hashMap.put("1649", "1649");
		hashMap.put("1650", "1650");
		hashMap.put("1651", "1651");
		hashMap.put("1652", "1652");
		hashMap.put("1653", "1653");
		hashMap.put("1654", "1654");
		hashMap.put("1655", "1655");
		hashMap.put("1656", "1656");
		hashMap.put("1657", "1657");
		hashMap.put("1658", "1658");
		hashMap.put("1659", "1659");
		hashMap.put("1660", "1660");
		hashMap.put("1661", "1661");
		hashMap.put("1662", "1662");
		hashMap.put("1663", "1663");
		hashMap.put("1664", "1664");
		hashMap.put("1665", "1665");
		hashMap.put("1666", "1666");
		hashMap.put("1667", "1667");
		hashMap.put("1668", "1668");
		hashMap.put("1669", "1669");
		hashMap.put("1670", "1670");
		hashMap.put("1671", "1671");
		hashMap.put("1672", "1672");
		hashMap.put("1673", "1673");
		hashMap.put("1675", "1675");
		hashMap.put("1676", "1676");
		hashMap.put("1677", "1677");
		hashMap.put("1678", "1678");
		hashMap.put("1679", "1679");
		hashMap.put("1680", "1680");
		hashMap.put("1681", "1681");
		hashMap.put("1682", "1682");
		hashMap.put("1683", "1683");
		hashMap.put("1684", "1684");
		hashMap.put("1685", "1685");
		hashMap.put("1686", "1686");
		hashMap.put("1687", "1687");
		hashMap.put("1688", "1688");
		hashMap.put("1689", "1689");
		hashMap.put("1690", "1690");
		hashMap.put("1691", "1691");
		hashMap.put("1693", "1693");
		hashMap.put("1694", "1694");
		hashMap.put("1695", "1695");
		hashMap.put("1696", "1696");
		hashMap.put("1697", "1697");
		hashMap.put("1698", "1698");
		hashMap.put("1699", "1699");
		hashMap.put("1701", "1701");
		hashMap.put("1702", "1702");
		hashMap.put("1703", "1703");
		hashMap.put("1704", "1704");
		hashMap.put("1705", "1705");
		hashMap.put("1706", "1706");
		hashMap.put("1707", "1707");
		hashMap.put("1708", "1708");
		hashMap.put("1709", "1709");
		hashMap.put("1710", "1710");
		hashMap.put("1711", "1711");
		hashMap.put("1712", "1712");
		hashMap.put("1713", "1713");
		hashMap.put("1714", "1714");
		hashMap.put("1715", "1715");
		hashMap.put("1716", "1716");
		hashMap.put("1717", "1717");
		hashMap.put("1718", "1718");
		hashMap.put("1719", "1719");
		hashMap.put("1720", "1720");
		hashMap.put("1721", "1721");
		hashMap.put("1722", "1722");
		hashMap.put("1723", "1723");
		hashMap.put("1724", "1724");
		hashMap.put("1725", "1725");
		hashMap.put("1726", "1726");
		hashMap.put("1727", "1727");
		hashMap.put("1728", "1728");
		hashMap.put("1729", "1729");
		hashMap.put("1730", "1730");
		hashMap.put("1731", "1731");
		hashMap.put("1732", "1732");
		hashMap.put("1733", "1733");
		hashMap.put("1734", "1734");
		hashMap.put("1735", "1735");
		hashMap.put("1736", "1736");
		hashMap.put("1737", "1737");
		hashMap.put("1738", "1738");
		hashMap.put("1739", "1739");
		hashMap.put("1740", "1740");
		hashMap.put("1741", "1741");
		hashMap.put("1742", "1742");
		hashMap.put("1750", "1750");
		hashMap.put("1752", "1752");
		hashMap.put("1754", "1754");
		hashMap.put("1755", "1755");
		hashMap.put("1756", "1756");
		hashMap.put("1757", "1757");
		hashMap.put("1758", "1758");
		hashMap.put("1759", "1759");
		hashMap.put("1760", "1760");
		hashMap.put("1761", "1761");
		hashMap.put("1762", "1762");
		hashMap.put("1763", "1763");
		hashMap.put("1764", "1764");
		hashMap.put("1765", "1765");
		hashMap.put("1766", "1766");
		hashMap.put("1767", "1767");
		hashMap.put("1770", "1770");
		hashMap.put("1771", "1771");
		hashMap.put("1772", "1772");
		hashMap.put("1773", "1773");
		hashMap.put("1774", "1774");
		hashMap.put("1775", "1775");
		hashMap.put("1776", "1776");
		hashMap.put("1777", "1777");
		hashMap.put("1778", "1778");
		hashMap.put("1779", "1779");
		hashMap.put("1780", "1780");
		hashMap.put("1781", "1781");
		hashMap.put("1782", "1782");
		hashMap.put("8888", "8888");
		hashMap.put("1800", "1800");
		hashMap.put("1801", "1801");
		hashMap.put("1802", "1802");
		hashMap.put("1803", "1803");
		hashMap.put("1804", "1804");
		hashMap.put("1805", "1805");
		hashMap.put("1806", "1806");
		hashMap.put("1807", "1807");
		hashMap.put("1808", "1808");
		hashMap.put("1809", "1809");
		hashMap.put("1810", "1810");
		hashMap.put("1811", "1811");
		hashMap.put("1813", "1813");
		hashMap.put("1814", "1814");
		hashMap.put("1815", "1815");
		hashMap.put("1816", "1816");
		hashMap.put("1817", "1817");
		hashMap.put("1818", "1818");
		hashMap.put("1819", "1819");
		hashMap.put("1820", "1820");
		hashMap.put("1821", "1821");
		hashMap.put("1822", "1822");
		hashMap.put("1823", "1823");
		hashMap.put("1824", "1824");
		hashMap.put("1825", "1825");
		hashMap.put("1826", "1826");
		hashMap.put("1827", "1827");
		hashMap.put("1828", "1828");
		hashMap.put("1830", "1830");
		hashMap.put("0|成", "0|成");
		hashMap.put("2100", "2100");
		hashMap.put("2001", "2001");
		hashMap.put("2002", "2002");
		hashMap.put("2003", "2003");
		hashMap.put("2103", "2103");
		hashMap.put("2005", "2005");
		hashMap.put("2006", "2006");
		hashMap.put("2007", "2007");
		hashMap.put("2008", "2008");
		hashMap.put("2009", "2009");
		hashMap.put("2010", "2010");
		hashMap.put("2011", "2011");
		hashMap.put("2012", "2012");
		hashMap.put("2014", "2014");
		hashMap.put("2015", "2015");
		hashMap.put("2016", "2016");
		hashMap.put("2110", "2110");
		hashMap.put("2111", "2111");
		hashMap.put("2112", "2112");
		hashMap.put("2113", "2113");
		hashMap.put("2114", "2114");
		hashMap.put("2115", "2115");
		hashMap.put("2118", "2118");
		hashMap.put("2119", "2119");
		hashMap.put("2121", "2121");
		hashMap.put("2122", "2122");
		hashMap.put("2123", "2123");
		hashMap.put("2124", "2124");
		hashMap.put("2125", "2125");
		hashMap.put("2126", "2126");
		hashMap.put("2127", "2127");
		hashMap.put("2128", "2128");
		hashMap.put("2129", "2129");
		hashMap.put("2130", "2130");
		hashMap.put("2017", "2017");
		hashMap.put("2020", "2020");
		hashMap.put("2021", "2021");
		hashMap.put("2024", "2024");
		hashMap.put("2025", "2025");
		hashMap.put("2026", "2026");
		hashMap.put("2027", "2027");
		hashMap.put("2028", "2028");
		hashMap.put("2029", "2029");
		hashMap.put("2030", "2030");
		hashMap.put("2031", "2031");
		hashMap.put("2032", "2032");
		hashMap.put("2033", "2033");
		hashMap.put("2034", "2034");
		hashMap.put("2035", "2035");
		hashMap.put("2036", "2036");
		hashMap.put("2037", "2037");
		hashMap.put("2038", "2038");
		hashMap.put("2039", "2039");
		hashMap.put("2040", "2040");
		hashMap.put("2043", "2043");
		hashMap.put("2131", "2131");
		hashMap.put("2132", "2132");
		hashMap.put("2133", "2133");
		hashMap.put("2134", "2134");
		hashMap.put("2135", "2135");
		hashMap.put("2136", "2136");
		hashMap.put("2137", "2137");
		hashMap.put("2138", "2138");
		hashMap.put("2139", "2139");
		hashMap.put("2140", "2140");
		hashMap.put("2141", "2141");
		hashMap.put("2142", "2142");
		hashMap.put("2143", "2143");
		hashMap.put("2144", "2144");
		hashMap.put("2145", "2145");
		hashMap.put("2146", "2146");
		hashMap.put("2041", "2041");
		hashMap.put("2042", "2042");
		hashMap.put("2045", "2045");
		hashMap.put("2046", "2046");
		hashMap.put("2047", "2047");
		hashMap.put("2050", "2050");
		hashMap.put("2051", "2051");
		hashMap.put("2055", "2055");
		hashMap.put("2056", "2056");
		hashMap.put("2060", "2060");
		hashMap.put("2061", "2061");
		hashMap.put("2062", "2062");
		hashMap.put("2063", "2063");
		hashMap.put("2064", "2064");
		hashMap.put("2065", "2065");
		hashMap.put("2168", "2168");
		hashMap.put("2169", "2169");
		hashMap.put("2170", "2170");
		hashMap.put("2171", "2171");
		hashMap.put("2172", "2172");
		hashMap.put("2173", "2173");
		hashMap.put("2174", "2174");
		hashMap.put("2068", "2068");
		hashMap.put("2069", "2069");
		hashMap.put("2070", "2070");
		hashMap.put("2071", "2071");
		hashMap.put("2072", "2072");
		hashMap.put("2073", "2073");
		hashMap.put("2074", "2074");
		hashMap.put("2075", "2075");
		hashMap.put("2076", "2076");
		hashMap.put("2077", "2077");
		hashMap.put("2078", "2078");
		hashMap.put("2079", "2079");
		hashMap.put("2080", "2080");
		hashMap.put("2085", "2085");
		hashMap.put("2086", "2086");
		hashMap.put("2087", "2087");
		hashMap.put("2088", "2088");
		hashMap.put("2089", "2089");
		hashMap.put("2095", "2095");
		hashMap.put("2096", "2096");
		hashMap.put("2081", "2081");
		hashMap.put("2082", "2082");
		hashMap.put("2083", "2083");
		hashMap.put("2084", "2084");
		hashMap.put("2181", "2181");
		hashMap.put("2182", "2182");
		hashMap.put("2183", "2183");
		hashMap.put("2184", "2184");
		hashMap.put("2185", "2185");
		hashMap.put("2186", "2186");
		hashMap.put("2187", "2187");
		hashMap.put("2188", "2188");
		hashMap.put("2106", "2106");
		hashMap.put("2107", "2107");
		hashMap.put("2108", "2108");
		hashMap.put("2109", "2109");
		hashMap.put("2116", "2116");
		hashMap.put("2117", "2117");
		hashMap.put("2120", "2120");
		hashMap.put("2200", "2200");
		hashMap.put("2201", "2201");
		hashMap.put("2202", "2202");
		hashMap.put("2203", "2203");
		hashMap.put("2204", "2204");
		hashMap.put("2205", "2205");
		hashMap.put("2206", "2206");
		hashMap.put("2207", "2207");
		hashMap.put("2208", "2208");
		hashMap.put("2209", "2209");
		hashMap.put("2210", "2210");
		hashMap.put("2211", "2211");
		hashMap.put("2212", "2212");
		hashMap.put("2213", "2213");
		hashMap.put("2214", "2214");
		hashMap.put("2215", "2215");
		hashMap.put("2216", "2216");
		hashMap.put("2217", "2217");
		hashMap.put("2218", "2218");
		hashMap.put("2219", "2219");
		hashMap.put("2220", "2220");
		hashMap.put("2221", "2221");
		hashMap.put("2222", "2222");
		hashMap.put("2223", "2223");
		hashMap.put("2224", "2224");
		hashMap.put("2225", "2225");
		hashMap.put("2226", "2226");
		hashMap.put("2227", "2227");
		hashMap.put("2228", "2228");
		hashMap.put("2229", "2229");
		hashMap.put("2230", "2230");
		hashMap.put("2231", "2231");
		hashMap.put("2232", "2232");
		hashMap.put("2233", "2233");
		hashMap.put("2234", "2234");
		hashMap.put("2235", "2235");
		hashMap.put("2236", "2236");
		hashMap.put("2237", "2237");
		hashMap.put("2238", "2238");
		hashMap.put("2239", "2239");
		hashMap.put("2401", "2401");
		hashMap.put("2403", "2403");
		hashMap.put("2404", "2404");
		hashMap.put("2405", "2405");
		hashMap.put("2406", "2406");
		hashMap.put("2407", "2407");
		hashMap.put("2408", "2408");
		hashMap.put("2409", "2409");
		hashMap.put("2410", "2410");
		hashMap.put("2411", "2411");
		hashMap.put("2412", "2412");
		hashMap.put("2413", "2413");
		hashMap.put("2414", "2414");
		hashMap.put("2415", "2415");
		hashMap.put("2416", "2416");
		hashMap.put("2417", "2417");
		hashMap.put("2418", "2418");
		hashMap.put("2420", "2420");
		hashMap.put("2421", "2421");
		hashMap.put("2422", "2422");
		hashMap.put("2423", "2423");
		hashMap.put("2424", "2424");
		hashMap.put("2425", "2425");
		hashMap.put("2427", "2427");
		hashMap.put("2428", "2428");
		hashMap.put("2429", "2429");
		hashMap.put("2430", "2430");
		hashMap.put("2431", "2431");
		hashMap.put("2432", "2432");
		hashMap.put("2433", "2433");
		hashMap.put("2434", "2434");
		hashMap.put("2435", "2435");
		hashMap.put("2436", "2436");
		hashMap.put("2437", "2437");
		hashMap.put("2438", "2438");
		hashMap.put("2439", "2439");
		hashMap.put("2440", "2440");
		hashMap.put("2441", "2441");
		hashMap.put("2442", "2442");
		hashMap.put("2443", "2443");
		hashMap.put("2444", "2444");
		hashMap.put("2445", "2445");
		hashMap.put("2446", "2446");
		hashMap.put("2447", "2447");
		hashMap.put("2448", "2448");
		hashMap.put("2449", "2449");
		hashMap.put("2450", "2450");
		hashMap.put("2452", "2452");
		hashMap.put("2453", "2453");
		hashMap.put("2455", "2455");
		hashMap.put("2456", "2456");
		hashMap.put("2457", "2457");
		hashMap.put("2458", "2458");
		hashMap.put("2459", "2459");
		hashMap.put("2460", "2460");
		hashMap.put("2461", "2461");
		hashMap.put("2462", "2462");
		hashMap.put("2463", "2463");
		hashMap.put("2464", "2464");
		hashMap.put("2465", "2465");
		hashMap.put("2466", "2466");
		hashMap.put("2467", "2467");
		hashMap.put("2468", "2468");
		hashMap.put("2469", "2469");
		hashMap.put("2470", "2470");
		hashMap.put("2472", "2472");
		hashMap.put("2473", "2473");
		hashMap.put("2474", "2474");
		hashMap.put("2475", "2475");
		hashMap.put("2476", "2476");
		hashMap.put("2477", "2477");
		hashMap.put("2478", "2478");
		hashMap.put("2479", "2479");
		hashMap.put("2514", "2514");
		hashMap.put("2515", "2515");
		hashMap.put("2801", "2801");
		hashMap.put("2802", "2802");
		hashMap.put("2803", "2803");
		hashMap.put("2804", "2804");
		hashMap.put("2805", "2805");
		hashMap.put("2806", "2806");
		hashMap.put("2807", "2807");
		hashMap.put("2808", "2808");
		hashMap.put("2809", "2809");
		hashMap.put("2810", "2810");
		hashMap.put("2811", "2811");
		hashMap.put("2812", "2812");
		hashMap.put("2813", "2813");
		hashMap.put("2814", "2814");
		hashMap.put("2815", "2815");
		hashMap.put("2816", "2816");
		hashMap.put("2817", "2817");
		hashMap.put("2818", "2818");
		hashMap.put("2819", "2819");
		hashMap.put("2820", "2820");
		hashMap.put("2821", "2821");
		hashMap.put("2822", "2822");
		hashMap.put("2823", "2823");
		hashMap.put("2824", "2824");
		hashMap.put("2825", "2825");
		hashMap.put("2826", "2826");
		hashMap.put("2827", "2827");
		hashMap.put("2828", "2828");
		hashMap.put("2830", "2830");
		hashMap.put("2831", "2831");
		hashMap.put("3000", "3000");
		hashMap.put("3001", "3001");
		hashMap.put("3002", "3002");
		hashMap.put("3003", "3003");
		hashMap.put("3004", "3004");
		hashMap.put("3005", "3005");
		hashMap.put("3007", "3007");
		hashMap.put("3008", "3008");
		hashMap.put("3010", "3010");
		hashMap.put("3011", "3011");
		hashMap.put("3012", "3012");
		hashMap.put("3013", "3013");
		hashMap.put("3014", "3014");
		hashMap.put("3015", "3015");
		hashMap.put("3016", "3016");
		hashMap.put("3017", "3017");
		hashMap.put("3018", "3018");
		hashMap.put("3019", "3019");
		hashMap.put("3020", "3020");
		hashMap.put("3021", "3021");
		hashMap.put("3023", "3023");
		hashMap.put("3024", "3024");
		hashMap.put("3025", "3025");
		hashMap.put("3026", "3026");
		hashMap.put("3027", "3027");
		hashMap.put("3028", "3028");
		hashMap.put("3029", "3029");
		hashMap.put("3030", "3030");
		hashMap.put("3031", "3031");
		hashMap.put("3032", "3032");
		hashMap.put("3033", "3033");
		hashMap.put("3034", "3034");
		hashMap.put("3035", "3035");
		hashMap.put("3036", "3036");
		hashMap.put("3037", "3037");
		hashMap.put("3038", "3038");
		hashMap.put("3039", "3039");
		hashMap.put("3040", "3040");
		hashMap.put("3041", "3041");
		hashMap.put("3042", "3042");
		hashMap.put("3043", "3043");
		hashMap.put("3044", "3044");
		hashMap.put("3050", "3050");
		hashMap.put("3051", "3051");
		hashMap.put("3052", "3052");
		hashMap.put("3053", "3053");
		hashMap.put("3198", "3198");
		hashMap.put("3200", "3200");
		hashMap.put("3201", "3201");
		hashMap.put("3202", "3202");
		hashMap.put("3206", "3206");
		hashMap.put("3211", "3211");
		hashMap.put("3212", "3212");
		hashMap.put("3213", "3213");
		hashMap.put("3214", "3214");
		hashMap.put("3215", "3215");
		hashMap.put("3252", "3252");
		hashMap.put("3253", "3253");
		hashMap.put("3254", "3254");
		hashMap.put("3255", "3255");
		hashMap.put("3256", "3256");
		hashMap.put("3257", "3257");
		hashMap.put("3258", "3258");
		hashMap.put("3259", "3259");
		hashMap.put("3260", "3260");
		hashMap.put("3261", "3261");
		hashMap.put("3262", "3262");
		hashMap.put("3263", "3263");
		hashMap.put("3264", "3264");
		hashMap.put("3265", "3265");
		hashMap.put("3266", "3266");
		hashMap.put("3267", "3267");
		hashMap.put("3268", "3268");
		hashMap.put("3269", "3269");
		hashMap.put("3270", "3270");
		hashMap.put("3271", "3271");
		hashMap.put("3272", "3272");
		hashMap.put("3273", "3273");
		hashMap.put("3274", "3274");
		hashMap.put("3275", "3275");
		hashMap.put("3276", "3276");
		hashMap.put("3277", "3277");
		hashMap.put("3278", "3278");
		hashMap.put("3279", "3279");
		hashMap.put("3280", "3280");
		hashMap.put("3281", "3281");
		hashMap.put("3282", "3282");
		hashMap.put("3283", "3283");
		hashMap.put("3284", "3284");
		hashMap.put("3300", "3300");
		hashMap.put("3301", "3301");
		hashMap.put("3302", "3302");
		hashMap.put("3303", "3303");
		hashMap.put("3304", "3304");
		hashMap.put("3310", "3310");
		hashMap.put("3311", "3311");
		hashMap.put("3312", "3312");
		hashMap.put("3313", "3313");
		hashMap.put("3314", "3314");
		hashMap.put("3321", "3321");
		hashMap.put("3341", "3341");
		hashMap.put("3342", "3342");
		hashMap.put("3343", "3343");
		hashMap.put("3350", "3350");
		hashMap.put("3360", "3360");
		hashMap.put("3361", "3361");
		hashMap.put("3371", "3371");
		hashMap.put("3390", "3390");
		hashMap.put("3391", "3391");
		hashMap.put("3392", "3392");
		hashMap.put("3393", "3393");
		hashMap.put("3394", "3394");
		hashMap.put("3395", "3395");
		hashMap.put("3396", "3396");
		hashMap.put("3398", "3398");
		hashMap.put("3399", "3399");
		hashMap.put("3500", "3500");
		hashMap.put("3501", "3501");
		hashMap.put("3502", "3502");
		hashMap.put("3503", "3503");
		hashMap.put("3504", "3504");
		hashMap.put("3505", "3505");
		hashMap.put("3506", "3506");
		hashMap.put("3507", "3507");
		hashMap.put("3508", "3508");
		hashMap.put("3540", "3540");
		hashMap.put("3541", "3541");
		hashMap.put("3542", "3542");
		hashMap.put("3512", "3512");
		hashMap.put("3513", "3513");
		hashMap.put("3514", "3514");
		hashMap.put("3515", "3515");
		hashMap.put("3516", "3516");
		hashMap.put("3517", "3517");
		hashMap.put("3518", "3518");
		hashMap.put("3519", "3519");
		hashMap.put("3520", "3520");
		hashMap.put("3521", "3521");
		hashMap.put("3522", "3522");
		hashMap.put("3523", "3523");
		hashMap.put("3524", "3524");
		hashMap.put("3525", "3525");
		hashMap.put("3526", "3526");
		hashMap.put("3527", "3527");
		hashMap.put("3528", "3528");
		hashMap.put("3529", "3529");
		hashMap.put("3530", "3530");
		hashMap.put("3531", "3531");
		hashMap.put("3532", "3532");
		hashMap.put("3533", "3533");
		hashMap.put("3534", "3534");
		hashMap.put("3535", "3535");
		hashMap.put("3536", "3536");
		hashMap.put("3538", "3538");
		hashMap.put("3539", "3539");
		hashMap.put("3800", "3800");
		hashMap.put("3803", "3803");
		hashMap.put("3804", "3804");
		hashMap.put("3805", "3805");
		hashMap.put("3806", "3806");
		hashMap.put("3807", "3807");
		hashMap.put("3808", "3808");
		hashMap.put("3809", "3809");
		hashMap.put("3810", "3810");
		hashMap.put("3811", "3811");
		hashMap.put("3812", "3812");
		hashMap.put("3813", "3813");
		hashMap.put("3814", "3814");
		hashMap.put("3815", "3815");
		hashMap.put("3816", "3816");
		hashMap.put("3817", "3817");
		hashMap.put("3818", "3818");
		hashMap.put("3819", "3819");
		hashMap.put("3820", "3820");
		hashMap.put("3821", "3821");
		hashMap.put("3822", "3822");
		hashMap.put("3823", "3823");
		hashMap.put("3824", "3824");
		hashMap.put("3825", "3825");
		hashMap.put("3826", "3826");
		hashMap.put("3827", "3827");
		hashMap.put("3828", "3828");
		hashMap.put("3829", "3829");
		hashMap.put("3830", "3830");
		hashMap.put("3831", "3831");
		hashMap.put("3832", "3832");
		hashMap.put("3833", "3833");
		hashMap.put("3834", "3834");
		hashMap.put("3835", "3835");
		hashMap.put("3836", "3836");
		hashMap.put("3837", "3837");
		hashMap.put("3838", "3838");
		hashMap.put("3839", "3839");
		hashMap.put("3840", "3840");
		hashMap.put("3841", "3841");
		hashMap.put("3842", "3842");
		hashMap.put("3843", "3843");
		hashMap.put("3844", "3844");
		hashMap.put("3847", "3847");
		hashMap.put("3848", "3848");
		hashMap.put("3849", "3849");
		hashMap.put("3850", "3850");
		hashMap.put("3851", "3851");
		hashMap.put("3852", "3852");
		hashMap.put("3853", "3853");
		hashMap.put("3854", "3854");
		hashMap.put("3855", "3855");
		hashMap.put("3856", "3856");
		hashMap.put("3857", "3857");
		hashMap.put("3858", "3858");
		hashMap.put("3859", "3859");
		hashMap.put("3860", "3860");
		hashMap.put("3861", "3861");
		hashMap.put("3862", "3862");
		hashMap.put("3863", "3863");
		hashMap.put("3864", "3864");
		hashMap.put("3865", "3865");
		hashMap.put("3866", "3866");
		hashMap.put("3867", "3867");
		hashMap.put("3868", "3868");
		hashMap.put("3869", "3869");
		hashMap.put("3870", "3870");
		hashMap.put("3871", "3871");
		hashMap.put("3872", "3872");
		hashMap.put("3873", "3873");
		hashMap.put("3874", "3874");
		hashMap.put("3875", "3875");
		hashMap.put("3876", "3876");
		hashMap.put("3877", "3877");
		hashMap.put("3878", "3878");
		hashMap.put("3885", "3885");
		hashMap.put("3886", "3886");
		hashMap.put("3887", "3887");
		hashMap.put("3889", "3889");
		hashMap.put("3989", "3989");
		hashMap.put("3990", "3990");
		hashMap.put("3991", "3991");
		hashMap.put("3992", "3992");
		hashMap.put("3993", "3993");
		hashMap.put("3994", "3994");
		hashMap.put("3995", "3995");
		hashMap.put("3996", "3996");
		hashMap.put("3997", "3997");
		hashMap.put("3998", "3998");
		hashMap.put("3999", "3999");
		hashMap.put("4001", "4001");
		hashMap.put("4002", "4002");
		hashMap.put("4003", "4003");
		hashMap.put("4004", "4004");
		hashMap.put("4005", "4005");
		hashMap.put("4007", "4007");
		hashMap.put("4008", "4008");
		hashMap.put("4009", "4009");
		hashMap.put("4010", "4010");
		hashMap.put("4011", "4011");
		hashMap.put("4012", "4012");
		hashMap.put("4013", "4013");
		hashMap.put("4014", "4014");
		hashMap.put("4015", "4015");
		hashMap.put("4016", "4016");
		hashMap.put("4202", "4202");
		hashMap.put("4203", "4203");
		hashMap.put("4204", "4204");
		hashMap.put("4205", "4205");
		hashMap.put("4206", "4206");
		hashMap.put("4208", "4208");
		hashMap.put("4209", "4209");
		hashMap.put("4210", "4210");
		hashMap.put("4211", "4211");
		hashMap.put("4212", "4212");
		hashMap.put("4213", "4213");
		hashMap.put("4214", "4214");
		hashMap.put("4215", "4215");
		hashMap.put("4216", "4216");
		hashMap.put("4217", "4217");
		hashMap.put("4218", "4218");
		hashMap.put("4219", "4219");
		hashMap.put("4220", "4220");
		hashMap.put("4221", "4221");
		hashMap.put("4222", "4222");
		hashMap.put("4223", "4223");
		hashMap.put("4224", "4224");
		hashMap.put("4225", "4225");
		hashMap.put("4227", "4227");
		hashMap.put("4229", "4229");
		hashMap.put("4230", "4230");
		hashMap.put("4231", "4231");
		hashMap.put("4236", "4236");
		hashMap.put("4237", "4237");
		hashMap.put("4238", "4238");
		hashMap.put("4239", "4239");
		hashMap.put("4243", "4243");
		hashMap.put("4244", "4244");
		hashMap.put("4245", "4245");
		hashMap.put("4246", "4246");
		hashMap.put("4248", "4248");
		hashMap.put("4249", "4249");
		hashMap.put("4250", "4250");
		hashMap.put("4251", "4251");
		hashMap.put("4252", "4252");
		hashMap.put("4253", "4253");
		hashMap.put("4254", "4254");
		hashMap.put("4255", "4255");
		hashMap.put("4256", "4256");
		hashMap.put("4257", "4257");
		hashMap.put("4258", "4258");
		hashMap.put("4259", "4259");
		hashMap.put("4260", "4260");
		hashMap.put("4261", "4261");
		hashMap.put("4262", "4262");
		hashMap.put("4263", "4263");
		hashMap.put("4600", "4600");
		hashMap.put("4601", "4601");
		hashMap.put("4602", "4602");
		hashMap.put("4603", "4603");
		hashMap.put("4604", "4604");
		hashMap.put("4605", "4605");
		hashMap.put("4606", "4606");
		hashMap.put("4607", "4607");
		hashMap.put("4608", "4608");
		hashMap.put("4609", "4609");
		hashMap.put("4610", "4610");
		hashMap.put("4611", "4611");
		hashMap.put("4612", "4612");
		hashMap.put("4613", "4613");
		hashMap.put("4614", "4614");
		hashMap.put("4615", "4615");
		hashMap.put("4616", "4616");
		hashMap.put("4617", "4617");
		hashMap.put("4618", "4618");
		hashMap.put("4619", "4619");
		hashMap.put("4621", "4621");
		hashMap.put("4622", "4622");
		hashMap.put("4623", "4623");
		hashMap.put("4624", "4624");
		hashMap.put("4627", "4627");
		hashMap.put("4628", "4628");
		hashMap.put("4629", "4629");
		hashMap.put("4630", "4630");
		hashMap.put("4631", "4631");
		hashMap.put("4632", "4632");
		hashMap.put("4633", "4633");
		hashMap.put("4634", "4634");
		hashMap.put("4635", "4635");
		hashMap.put("4636", "4636");
		hashMap.put("4637", "4637");
		hashMap.put("4638", "4638");
		hashMap.put("4639", "4639");
		hashMap.put("4640", "4640");
		hashMap.put("4641", "4641");
		hashMap.put("4642", "4642");
		hashMap.put("4800", "4800");
		hashMap.put("4801", "4801");
		hashMap.put("4802", "4802");
		hashMap.put("4803", "4803");
		hashMap.put("4804", "4804");
		hashMap.put("4805", "4805");
		hashMap.put("4806", "4806");
		hashMap.put("4807", "4807");
		hashMap.put("4808", "4808");
		hashMap.put("4810", "4810");
		hashMap.put("4811", "4811");
		hashMap.put("4812", "4812");
		hashMap.put("4814", "4814");
		hashMap.put("4815", "4815");
		hashMap.put("4816", "4816");
		hashMap.put("4817", "4817");
		hashMap.put("4818", "4818");
		hashMap.put("4819", "4819");
		hashMap.put("4820", "4820");
		hashMap.put("4821", "4821");
		hashMap.put("4822", "4822");
		hashMap.put("4823", "4823");
		hashMap.put("4824", "4824");
		hashMap.put("4825", "4825");
		hashMap.put("4826", "4826");
		hashMap.put("4827", "4827");
		hashMap.put("4829", "4829");
		hashMap.put("4830", "4830");
		hashMap.put("4831", "4831");
		hashMap.put("4832", "4832");
		hashMap.put("4833", "4833");
		hashMap.put("4835", "4835");
		hashMap.put("4836", "4836");
		hashMap.put("4837", "4837");
		hashMap.put("4839", "4839");
		hashMap.put("4840", "4840");
		hashMap.put("4842", "4842");
		hashMap.put("4843", "4843");
		hashMap.put("4844", "4844");
		hashMap.put("4845", "4845");
		hashMap.put("4846", "4846");
		hashMap.put("4847", "4847");
		hashMap.put("4848", "4848");
		hashMap.put("4849", "4849");
		hashMap.put("4853", "4853");
		hashMap.put("4854", "4854");
		hashMap.put("4855", "4855");
		hashMap.put("4856", "4856");
		hashMap.put("4857", "4857");
		hashMap.put("4858", "4858");
		hashMap.put("4859", "4859");
		hashMap.put("4860", "4860");
		hashMap.put("4861", "4861");
		hashMap.put("4862", "4862");
		hashMap.put("4863", "4863");
		hashMap.put("4864", "4864");
		hashMap.put("4865", "4865");
		hashMap.put("4866", "4866");
		hashMap.put("4867", "4867");
		hashMap.put("4868", "4868");
		hashMap.put("4869", "4869");
		hashMap.put("4870", "4870");
		hashMap.put("4871", "4871");
		hashMap.put("4872", "4872");
		hashMap.put("4873", "4873");
		hashMap.put("4874", "4874");
		hashMap.put("4875", "4875");
		hashMap.put("4876", "4876");
		hashMap.put("4877", "4877");
		hashMap.put("4879", "4879");
		hashMap.put("4880", "4880");
		hashMap.put("4881", "4881");
		hashMap.put("4882", "4882");
		hashMap.put("4883", "4883");
		hashMap.put("4884", "4884");
		hashMap.put("4885", "4885");
		hashMap.put("4886", "4886");
		hashMap.put("4887", "4887");
		hashMap.put("4888", "4888");
		hashMap.put("4889", "4889");
		hashMap.put("4890", "4890");
		hashMap.put("4891", "4891");
		hashMap.put("4892", "4892");
		hashMap.put("4893", "4893");
		hashMap.put("4894", "4894");
		hashMap.put("4895", "4895");
		hashMap.put("4896", "4896");
		hashMap.put("4897", "4897");
		hashMap.put("4898", "4898");
		hashMap.put("4899", "4899");
		hashMap.put("4900", "4900");
		hashMap.put("4901", "4901");
		hashMap.put("4903", "4903");
		hashMap.put("4904", "4904");
		hashMap.put("4905", "4905");
		hashMap.put("4906", "4906");
		hashMap.put("4907", "4907");
		hashMap.put("4908", "4908");
		hashMap.put("4909", "4909");
		hashMap.put("4910", "4910");
		hashMap.put("4911", "4911");
		hashMap.put("4912", "4912");
		hashMap.put("4913", "4913");
		hashMap.put("4914", "4914");
		hashMap.put("4915", "4915");
		hashMap.put("4916", "4916");
		hashMap.put("4917", "4917");
		hashMap.put("4918", "4918");
		hashMap.put("4919", "4919");
		hashMap.put("4920", "4920");
		hashMap.put("4921", "4921");
		hashMap.put("4922", "4922");
		hashMap.put("4923", "4923");
		hashMap.put("4850", "4850");
		hashMap.put("4851", "4851");
		hashMap.put("4852", "4852");
		hashMap.put("4924", "4924");
		hashMap.put("4925", "4925");
		hashMap.put("4930", "4930");
		hashMap.put("4931", "4931");
		hashMap.put("4932", "4932");
		hashMap.put("4933", "4933");
		hashMap.put("4934", "4934");
		hashMap.put("4935", "4935");
		hashMap.put("4936", "4936");
		hashMap.put("4937", "4937");
		hashMap.put("4938", "4938");
		hashMap.put("4939", "4939");
		hashMap.put("2300", "2300");
		hashMap.put("2301", "2301");
		hashMap.put("2302", "2302");
		hashMap.put("2303", "2303");
		hashMap.put("2304", "2304");
		hashMap.put("2305", "2305");
		hashMap.put("2306", "2306");
		hashMap.put("2307", "2307");
		hashMap.put("2308", "2308");
		hashMap.put("2309", "2309");
		hashMap.put("2310", "2310");
		hashMap.put("2311", "2311");
		hashMap.put("2312", "2312");
		hashMap.put("7001", "7001");
		hashMap.put("7002", "7002");
		hashMap.put("7003", "7003");
		hashMap.put("7004", "7004");
		hashMap.put("7005", "7005");
		hashMap.put("7006", "7006");
		hashMap.put("7007", "7007");
		hashMap.put("7008", "7008");
		hashMap.put("7009", "7009");
		hashMap.put("7010", "7010");
		hashMap.put("7011", "7011");
		hashMap.put("7012", "7012");
		hashMap.put("7013", "7013");
		hashMap.put("7014", "7014");
		hashMap.put("7015", "7015");
		hashMap.put("7016", "7016");
		hashMap.put("7017", "7017");
		hashMap.put("7018", "7018");
		hashMap.put("7019", "7019");
		hashMap.put("7020", "7020");
		hashMap.put("7021", "7021");
		hashMap.put("7022", "7022");
		hashMap.put("7023", "7023");
		hashMap.put("7024", "7024");
		hashMap.put("7025", "7025");
		hashMap.put("7026", "7026");
		hashMap.put("7027", "7027");
		hashMap.put("7028", "7028");
		hashMap.put("7029", "7029");
		hashMap.put("7030", "7030");
		hashMap.put("7031", "7031");
		hashMap.put("7032", "7032");
		hashMap.put("7033", "7033");
		hashMap.put("7034", "7034");
		hashMap.put("7035", "7035");
		hashMap.put("7036", "7036");
		hashMap.put("7037", "7037");
		hashMap.put("7038", "7038");
		hashMap.put("7039", "7039");
		hashMap.put("7040", "7040");
		hashMap.put("7041", "7041");
		hashMap.put("4400", "4400");
		hashMap.put("4401", "4401");
		hashMap.put("4402", "4402");
		hashMap.put("4403", "4403");
		hashMap.put("4404", "4404");
		hashMap.put("4405", "4405");
		hashMap.put("4406", "4406");
		hashMap.put("4407", "4407");
		hashMap.put("4408", "4408");
		hashMap.put("4409", "4409");
		hashMap.put("4410", "4410");
		hashMap.put("4411", "4411");
		hashMap.put("4412", "4412");
		hashMap.put("4413", "4413");
		hashMap.put("4414", "4414");
		hashMap.put("4415", "4415");
		hashMap.put("4416", "4416");
		hashMap.put("4417", "4417");
		hashMap.put("4418", "4418");
		hashMap.put("4419", "4419");
		hashMap.put("4420", "4420");
		hashMap.put("4421", "4421");
		hashMap.put("4422", "4422");
		hashMap.put("4423", "4423");
		hashMap.put("4424", "4424");
		hashMap.put("4425", "4425");
		hashMap.put("4426", "4426");
		hashMap.put("4450", "4450");
		hashMap.put("4451", "4451");
		hashMap.put("4452", "4452");
		hashMap.put("4453", "4453");
		hashMap.put("4454", "4454");
		hashMap.put("4455", "4455");
		hashMap.put("4456", "4456");
		hashMap.put("4457", "4457");
		hashMap.put("4458", "4458");
		hashMap.put("2601", "2601");
		hashMap.put("2603", "2603");
		hashMap.put("2604", "2604");
		hashMap.put("2605", "2605");
		hashMap.put("2606", "2606");
		hashMap.put("2607", "2607");
		hashMap.put("2608", "2608");
		hashMap.put("2609", "2609");
		hashMap.put("2610", "2610");
		hashMap.put("2632", "2632");
		hashMap.put("2633", "2633");
		hashMap.put("2636", "2636");
		hashMap.put("2611", "2611");
		hashMap.put("2612", "2612");
		hashMap.put("2613", "2613");
		hashMap.put("2614", "2614");
		hashMap.put("2615", "2615");
		hashMap.put("2616", "2616");
		hashMap.put("2617", "2617");
		hashMap.put("2618", "2618");
		hashMap.put("2619", "2619");
		hashMap.put("2620", "2620");
		hashMap.put("2621", "2621");
		hashMap.put("2631", "2631");
		hashMap.put("2622", "2622");
		hashMap.put("2623", "2623");
		hashMap.put("2624", "2624");
		hashMap.put("2625", "2625");
		hashMap.put("2626", "2626");
		hashMap.put("2627", "2627");
		hashMap.put("2628", "2628");
		hashMap.put("2629", "2629");
		hashMap.put("2630", "2630");
		hashMap.put("2634", "2634");
		hashMap.put("2635", "2635");
		hashMap.put("2637", "2637");
		hashMap.put("2638", "2638");
		hashMap.put("2639", "2639");
		hashMap.put("2640", "2640");
		hashMap.put("2641", "2641");
		hashMap.put("2642", "2642");
		hashMap.put("2643", "2643");
		hashMap.put("2644", "2644");
		hashMap.put("2645", "2645");
		hashMap.put("2646", "2646");
		hashMap.put("2647", "2647");
		hashMap.put("2648", "2648");
		hashMap.put("2649", "2649");
		hashMap.put("2650", "2650");
		hashMap.put("2651", "2651");
		hashMap.put("2652", "2652");
		hashMap.put("2653", "2653");
		hashMap.put("2655", "2655");
		hashMap.put("2661", "2661");
		hashMap.put("2662", "2662");
		hashMap.put("2663", "2663");
		hashMap.put("2671", "2671");
		hashMap.put("2672", "2672");
		hashMap.put("2673", "2673");
		hashMap.put("2681", "2681");
		hashMap.put("2682", "2682");
		hashMap.put("2684", "2684");
		hashMap.put("2685", "2685");
		hashMap.put("2683", "2683");
		hashMap.put("2691", "2691");
		hashMap.put("2701", "2701");
		hashMap.put("2702", "2702");
		hashMap.put("2703", "2703");
		hashMap.put("2704", "2704");
		hashMap.put("2711", "2711");
		hashMap.put("2712", "2712");
		hashMap.put("2713", "2713");
		hashMap.put("2714", "2714");
		hashMap.put("2715", "2715");
		hashMap.put("2722", "2722");
		hashMap.put("2730", "2730");
		hashMap.put("2731", "2731");
		hashMap.put("2732", "2732");
		hashMap.put("2733", "2733");
		hashMap.put("2734", "2734");
		hashMap.put("2735", "2735");
		hashMap.put("2736", "2736");
		hashMap.put("2737", "2737");
		hashMap.put("2738", "2738");
		hashMap.put("2739", "2739");
		hashMap.put("2740", "2740");
		hashMap.put("2741", "2741");
		hashMap.put("2742", "2742");
		hashMap.put("2743", "2743");
		hashMap.put("2744", "2744");
		hashMap.put("2745", "2745");
		hashMap.put("2746", "2746");
		hashMap.put("2747", "2747");
		hashMap.put("2748", "2748");
		hashMap.put("2749", "2749");
		hashMap.put("2750", "2750");
		hashMap.put("2751", "2751");
		hashMap.put("2752", "2752");
		hashMap.put("2753", "2753");
		hashMap.put("2755", "2755");
		hashMap.put("2756", "2756");
		hashMap.put("2757", "2757");
		hashMap.put("2758", "2758");
		hashMap.put("2759", "2759");
		hashMap.put("2770", "2770");
		hashMap.put("2771", "2771");
		hashMap.put("2772", "2772");
		hashMap.put("2773", "2773");
		hashMap.put("2776", "2776");
		hashMap.put("2754", "2754");
		hashMap.put("2760", "2760");
		hashMap.put("2761", "2761");
		hashMap.put("2762", "2762");
		hashMap.put("2763", "2763");
		hashMap.put("2764", "2764");
		hashMap.put("2765", "2765");
		hashMap.put("2766", "2766");
		hashMap.put("2767", "2767");
		hashMap.put("2768", "2768");
		hashMap.put("2769", "2769");
		hashMap.put("2774", "2774");
		hashMap.put("2775", "2775");
		hashMap.put("2777", "2777");
		hashMap.put("2778", "2778");
		hashMap.put("2779", "2779");
		hashMap.put("2780", "2780");
		hashMap.put("2781", "2781");
		hashMap.put("2782", "2782");
		hashMap.put("2783", "2783");
		hashMap.put("2784", "2784");
		hashMap.put("2785", "2785");
		hashMap.put("2786", "2786");
		hashMap.put("2787", "2787");
		hashMap.put("2788", "2788");
		hashMap.put("2789", "2789");
		hashMap.put("2790", "2790");
		hashMap.put("2791", "2791");
		hashMap.put("2792", "2792");
		hashMap.put("2793", "2793");
		hashMap.put("2794", "2794");
		hashMap.put("2795", "2795");
		hashMap.put("2796", "2796");
		hashMap.put("1300", "1300");
		hashMap.put("1301", "1301");
		hashMap.put("1302", "1302");
		hashMap.put("1303", "1303");
		hashMap.put("1304", "1304");
		hashMap.put("1305", "1305");
		hashMap.put("1306", "1306");
		hashMap.put("1350", "1350");
		hashMap.put("1351", "1351");
		hashMap.put("1352", "1352");
		hashMap.put("1353", "1353");
		hashMap.put("1354", "1354");
		hashMap.put("1355", "1355");
		hashMap.put("1356", "1356");
		hashMap.put("1357", "1357");
		hashMap.put("1358", "1358");
		hashMap.put("1359", "1359");
		hashMap.put("1360", "1360");
		hashMap.put("1361", "1361");
		hashMap.put("1362", "1362");
		hashMap.put("1363", "1363");
		hashMap.put("1364", "1364");
		hashMap.put("1365", "1365");
		hashMap.put("1366", "1366");
		hashMap.put("1367", "1367");
		hashMap.put("1368", "1368");
		hashMap.put("1369", "1369");
		hashMap.put("1370", "1370");
		hashMap.put("1371", "1371");
		hashMap.put("1372", "1372");
		hashMap.put("1373", "1373");
		hashMap.put("1374", "1374");
		hashMap.put("1375", "1375");
		hashMap.put("1376", "1376");
		hashMap.put("1377", "1377");
		hashMap.put("1378", "1378");
		hashMap.put("1379", "1379");
		hashMap.put("1380", "1380");
		hashMap.put("1381", "1381");
		hashMap.put("1390", "1390");
		hashMap.put("1391", "1391");
		hashMap.put("1392", "1392");
		hashMap.put("1393", "1393");
		hashMap.put("1394", "1394");
		hashMap.put("1395", "1395");
		hashMap.put("1396", "1396");
		hashMap.put("1397", "1397");
		hashMap.put("1398", "1398");
		hashMap.put("1399", "1399");
		hashMap.put("9001", "9001");
		hashMap.put("9002", "9002");
		hashMap.put("9003", "9003");
		hashMap.put("9004", "9004");
		hashMap.put("9005", "9005");
		hashMap.put("9006", "9006");
		hashMap.put("9007", "9007");
		hashMap.put("9008", "9008");
		hashMap.put("9009", "9009");
		hashMap.put("9010", "9010");
		hashMap.put("9011", "9011");
		hashMap.put("9012", "9012");
		hashMap.put("9013", "9013");
		hashMap.put("9014", "9014");
		hashMap.put("9015", "9015");
		hashMap.put("9016", "9016");
		hashMap.put("9017", "9017");
		hashMap.put("9018", "9018");
		hashMap.put("9019", "9019");
		hashMap.put("9020", "9020");
		hashMap.put("9021", "9021");
		hashMap.put("9022", "9022");
		hashMap.put("9023", "9023");
		hashMap.put("9024", "9024");
		hashMap.put("5001", "5001");
		hashMap.put("5002", "5002");
		hashMap.put("5003", "5003");
		hashMap.put("5004", "5004");
		hashMap.put("5005", "5005");
		hashMap.put("5010", "5010");
		hashMap.put("5011", "5011");
		hashMap.put("5012", "5012");
		hashMap.put("5013", "5013");
		hashMap.put("5016", "5016");
		hashMap.put("5017", "5017");
		hashMap.put("5021", "5021");
		hashMap.put("5025", "5025");
		hashMap.put("5026", "5026");
		hashMap.put("5027", "5027");
		hashMap.put("5028", "5028");
		hashMap.put("5029", "5029");
		hashMap.put("5031", "5031");
		hashMap.put("5033", "5033");
		hashMap.put("5041", "5041");
		hashMap.put("5042", "5042");
		hashMap.put("5044", "5044");
		hashMap.put("5045", "5045");
		hashMap.put("5051", "5051");
		hashMap.put("5054", "5054");
		hashMap.put("5057", "5057");
		hashMap.put("5058", "5058");
		hashMap.put("5061", "5061");
		hashMap.put("5062", "5062");
		hashMap.put("5066", "5066");
		hashMap.put("5063", "5063");
		hashMap.put("5064", "5064");
		hashMap.put("5065", "5065");
		hashMap.put("5067", "5067");
		hashMap.put("5068", "5068");
		hashMap.put("5069", "5069");
		hashMap.put("5070", "5070");
		hashMap.put("5073", "5073");
		hashMap.put("5074", "5074");
		hashMap.put("5071", "5071");
		hashMap.put("5072", "5072");
		hashMap.put("5075", "5075");
		hashMap.put("5081", "5081");
		hashMap.put("5082", "5082");
		hashMap.put("5091", "5091");
		hashMap.put("5093", "5093");
		hashMap.put("5101", "5101");
		hashMap.put("5121", "5121");
		hashMap.put("5122", "5122");
		hashMap.put("5123", "5123");
		hashMap.put("5124", "5124");
		hashMap.put("5125", "5125");
		hashMap.put("5126", "5126");
		hashMap.put("5131", "5131");
		hashMap.put("5132", "5132");
		hashMap.put("5133", "5133");
		hashMap.put("5134", "5134");
		hashMap.put("5135", "5135");
		hashMap.put("5136", "5136");
		hashMap.put("5137", "5137");
		hashMap.put("5138", "5138");
		hashMap.put("5201", "5201");
		hashMap.put("5202", "5202");
		hashMap.put("5203", "5203");
		hashMap.put("5204", "5204");
		hashMap.put("5205", "5205");
		hashMap.put("5206", "5206");
		hashMap.put("5207", "5207");
		hashMap.put("5208", "5208");
		hashMap.put("6101", "6101");
		hashMap.put("6102", "6102");
		hashMap.put("6103", "6103");
		hashMap.put("6104", "6104");
		hashMap.put("6105", "6105");
		hashMap.put("6106", "6106");
		hashMap.put("6107", "6107");
		hashMap.put("6108", "6108");
		hashMap.put("6109", "6109");
		hashMap.put("6110", "6110");
		hashMap.put("6111", "6111");
		hashMap.put("6112", "6112");
		hashMap.put("6113", "6113");
		hashMap.put("6114", "6114");
		hashMap.put("6118", "6118");
		hashMap.put("6119", "6119");
		hashMap.put("6120", "6120");
		hashMap.put("6121", "6121");
		hashMap.put("6122", "6122");
		hashMap.put("6123", "6123");
		hashMap.put("6124", "6124");
		hashMap.put("6125", "6125");
		hashMap.put("6126", "6126");
		hashMap.put("6180", "6180");
		hashMap.put("6181", "6181");
		hashMap.put("6182", "6182");
		hashMap.put("6183", "6183");
		hashMap.put("6184", "6184");
		hashMap.put("6185", "6185");
		hashMap.put("6186", "6186");
		hashMap.put("6187", "6187");
		hashMap.put("6188", "6188");
		hashMap.put("6189", "6189");
		hashMap.put("6190", "6190");
		hashMap.put("6210", "6210");
		hashMap.put("6211", "6211");
		hashMap.put("6212", "6212");
		hashMap.put("6213", "6213");
		hashMap.put("6214", "6214");
		hashMap.put("6215", "6215");
		hashMap.put("6216", "6216");
		hashMap.put("6217", "6217");
		hashMap.put("6251", "6251");
		hashMap.put("6252", "6252");
		hashMap.put("6253", "6253");
		hashMap.put("6254", "6254");
		hashMap.put("6255", "6255");
		hashMap.put("6256", "6256");
		hashMap.put("6257", "6257");
		hashMap.put("6258", "6258");
		hashMap.put("6259", "6259");
		hashMap.put("6260", "6260");
		hashMap.put("6261", "6261");
		hashMap.put("6262", "6262");
		hashMap.put("6263", "6263");
		hashMap.put("6264", "6264");
		hashMap.put("6265", "6265");
		hashMap.put("6266", "6266");
		hashMap.put("6267", "6267");
		hashMap.put("6268", "6268");
		hashMap.put("6301", "6301");
		hashMap.put("6302", "6302");
		hashMap.put("6305", "6305");
		hashMap.put("6306", "6306");
		hashMap.put("6307", "6307");
		hashMap.put("6308", "6308");
		hashMap.put("6309", "6309");
		hashMap.put("6310", "6310");
		hashMap.put("6311", "6311");
		hashMap.put("6312", "6312");
		hashMap.put("6326", "6326");
		hashMap.put("6327", "6327");
		hashMap.put("6328", "6328");
		hashMap.put("6329", "6329");
		hashMap.put("2517", "2517");
		hashMap.put("8993", "8993");
		hashMap.put("8994", "8994");
		hashMap.put("8995", "8995");
		hashMap.put("8996", "8996");
		hashMap.put("8997", "8997");
		hashMap.put("8998", "8998");
		hashMap.put("8999", "8999");
		hashMap.put("1424", "1424");
		hashMap.put("4100", "4100");
		hashMap.put("4101", "4101");
		hashMap.put("2832", "2832");
		hashMap.put("2480", "2480");
		hashMap.put("3544", "3544");
		hashMap.put("3545", "3545");
		hashMap.put("3511", "3511");
		hashMap.put("2488", "2488");
		hashMap.put("2481", "2481");
		hashMap.put("2482", "2482");
		hashMap.put("2484", "2484");
		hashMap.put("2485", "2485");
		hashMap.put("2486", "2486");
		hashMap.put("2487", "2487");
		hashMap.put("4102", "4102");
		hashMap.put("8990", "8990");
		hashMap.put("4103", "4103");
		hashMap.put("5479", "5479");
		hashMap.put("4104", "4104");
		hashMap.put("4105", "4105");
		hashMap.put("4120", "4120");
		hashMap.put("4121", "4121");
		hashMap.put("4791", "4791");
		hashMap.put("4122", "4122");
		hashMap.put("1700", "1700");
		hashMap.put("4123", "4123");
		hashMap.put("4124", "4124");
		hashMap.put("4125", "4125");
		hashMap.put("4126", "4126");
		hashMap.put("4127", "4127");
		hashMap.put("4128", "4128");
		hashMap.put("4129", "4129");
		hashMap.put("4130", "4130");
		hashMap.put("4131", "4131");
		hashMap.put("4132", "4132");
		hashMap.put("4133", "4133");
		hashMap.put("4134", "4134");
		hashMap.put("4135", "4135");
		hashMap.put("4160", "4160");
		hashMap.put("4161", "4161");
		hashMap.put("4162", "4162");
		hashMap.put("4163", "4163");
		hashMap.put("4164", "4164");
		hashMap.put("4165", "4165");
		hashMap.put("4166", "4166");
		hashMap.put("4167", "4167");
		hashMap.put("4168", "4168");
		hashMap.put("4169", "4169");
		hashMap.put("4170", "4170");
		hashMap.put("4171", "4171");
		hashMap.put("4940", "4940");
		hashMap.put("4941", "4941");
		hashMap.put("4942", "4942");
		hashMap.put("4943", "4943");
		hashMap.put("6115", "6115");
		hashMap.put("6127", "6127");
		hashMap.put("6128", "6128");
		hashMap.put("5400", "5400");
		hashMap.put("2483", "2483");
		hashMap.put("3400", "3400");
		hashMap.put("3409", "3409");
		hashMap.put("3420", "3420");
		hashMap.put("3424", "3424");
		hashMap.put("3426", "3426");
		hashMap.put("3427", "3427");
		hashMap.put("3435", "3435");
		hashMap.put("3441", "3441");
		hashMap.put("3509", "3509");
		hashMap.put("3510", "3510");
		hashMap.put("3543", "3543");
		hashMap.put("3802", "3802");
		hashMap.put("4643", "4643");
		hashMap.put("5430", "5430");
		hashMap.put("5401", "5401");
		hashMap.put("5402", "5402");
		hashMap.put("5403", "5403");
		hashMap.put("5404", "5404");
		hashMap.put("5405", "5405");
		hashMap.put("5406", "5406");
		hashMap.put("5407", "5407");
		hashMap.put("5408", "5408");
		hashMap.put("5409", "5409");
		hashMap.put("5410", "5410");
		hashMap.put("5411", "5411");
		hashMap.put("5412", "5412");
		hashMap.put("5450", "5450");
		hashMap.put("5413", "5413");
		hashMap.put("5414", "5414");
		hashMap.put("5415", "5415");
		hashMap.put("5416", "5416");
		hashMap.put("5417", "5417");
		hashMap.put("5418", "5418");
		hashMap.put("5419", "5419");
		hashMap.put("5420", "5420");
		hashMap.put("5421", "5421");
		hashMap.put("5422", "5422");
		hashMap.put("5423", "5423");
		hashMap.put("5424", "5424");
		hashMap.put("5425", "5425");
		hashMap.put("5426", "5426");
		hashMap.put("5427", "5427");
		hashMap.put("5428", "5428");
		hashMap.put("5429", "5429");
		hashMap.put("5451", "5451");
		hashMap.put("5452", "5452");
		hashMap.put("5486", "5486");
		hashMap.put("5487", "5487");
		hashMap.put("5488", "5488");
		hashMap.put("5489", "5489");
		hashMap.put("5493", "5493");
		hashMap.put("5499", "5499");
		hashMap.put("5510", "5510");
		hashMap.put("5511", "5511");
		hashMap.put("5512", "5512");
		hashMap.put("5513", "5513");
		hashMap.put("5514", "5514");
		hashMap.put("5515", "5515");
		hashMap.put("5516", "5516");
		hashMap.put("5517", "5517");
		hashMap.put("5518", "5518");
		hashMap.put("5519", "5519");
		hashMap.put("5520", "5520");
		hashMap.put("1027", "1027");
		hashMap.put("1471", "1471");
		hashMap.put("2018", "2018");
		hashMap.put("2147", "2147");
		hashMap.put("2148", "2148");
		hashMap.put("2149", "2149");
		hashMap.put("2150", "2150");
		hashMap.put("2151", "2151");
		hashMap.put("2152", "2152");
		hashMap.put("2153", "2153");
		hashMap.put("2489", "2489");
		hashMap.put("2490", "2490");
		hashMap.put("2491", "2491");
		hashMap.put("2492", "2492");
		hashMap.put("2493", "2493");
		hashMap.put("2505", "2505");
		hashMap.put("2518", "2518");
		hashMap.put("3006", "3006");
		hashMap.put("3009", "3009");
		hashMap.put("5431", "5431");
		hashMap.put("5440", "5440");
		hashMap.put("5441", "5441");
		hashMap.put("5445", "5445");
		hashMap.put("5446", "5446");
		hashMap.put("5447", "5447");
		hashMap.put("5460", "5460");
		hashMap.put("5461", "5461");
		hashMap.put("5462", "5462");
		hashMap.put("5463", "5463");
		hashMap.put("5465", "5465");
		hashMap.put("5466", "5466");
		hashMap.put("5467", "5467");
		hashMap.put("5468", "5468");
		hashMap.put("5469", "5469");
		hashMap.put("5470", "5470");
		hashMap.put("5471", "5471");
		hashMap.put("5472", "5472");
		hashMap.put("5473", "5473");
		hashMap.put("5475", "5475");
		hashMap.put("5476", "5476");
		hashMap.put("5474", "5474");
		hashMap.put("5477", "5477");
		hashMap.put("5478", "5478");
		hashMap.put("5480", "5480");
		hashMap.put("5481", "5481");
		hashMap.put("5482", "5482");
		hashMap.put("5483", "5483");
		hashMap.put("5484", "5484");
		hashMap.put("5485", "5485");
		hashMap.put("5491", "5491");
		hashMap.put("5492", "5492");
		hashMap.put("5494", "5494");
		hashMap.put("5495", "5495");
		hashMap.put("5496", "5496");
		hashMap.put("5497", "5497");
		hashMap.put("5498", "5498");
		hashMap.put("5490", "5490");
		hashMap.put("5500", "5500");
		hashMap.put("5501", "5501");
		hashMap.put("5502", "5502");
		hashMap.put("5503", "5503");
		hashMap.put("5504", "5504");
		hashMap.put("5505", "5505");
		hashMap.put("4944", "4944");
		hashMap.put("4945", "4945");
		hashMap.put("4264", "4264");
		hashMap.put("4946", "4946");
		hashMap.put("4947", "4947");
		hashMap.put("4948", "4948");
		hashMap.put("4949", "4949");
		hashMap.put("4950", "4950");
		hashMap.put("4951", "4951");
		hashMap.put("4265", "4265");
		hashMap.put("9995", "9995");
		hashMap.put("4136", "4136");
		hashMap.put("4137", "4137");
		hashMap.put("2519", "2519");
		hashMap.put("4020", "4020");
		hashMap.put("4021", "4021");
		hashMap.put("2240", "2240");
		hashMap.put("2090", "2090");
		hashMap.put("4266", "4266");
		hashMap.put("4952", "4952");
		hashMap.put("2241", "2241");
		hashMap.put("2370", "2370");
		hashMap.put("2371", "2371");
		hashMap.put("2372", "2372");
		hashMap.put("2373", "2373");
		hashMap.put("2374", "2374");
		hashMap.put("2520", "2520");
		hashMap.put("2521", "2521");
		hashMap.put("2522", "2522");
		hashMap.put("2523", "2523");
		hashMap.put("2524", "2524");
		hashMap.put("2525", "2525");
		hashMap.put("4172", "4172");
		hashMap.put("7100", "7100");
		hashMap.put("7101", "7101");
		hashMap.put("7102", "7102");
		hashMap.put("7103", "7103");
		hashMap.put("7104", "7104");
		hashMap.put("7105", "7105");
		hashMap.put("7106", "7106");
		hashMap.put("7107", "7107");
		hashMap.put("7108", "7108");
		hashMap.put("7109", "7109");
		hashMap.put("7110", "7110");
		hashMap.put("7111", "7111");
		hashMap.put("7112", "7112");
		hashMap.put("7113", "7113");
		hashMap.put("7114", "7114");
		hashMap.put("7115", "7115");
		hashMap.put("7116", "7116");
		hashMap.put("7117", "7117");
		hashMap.put("7118", "7118");
		hashMap.put("7119", "7119");
		hashMap.put("7120", "7120");
		hashMap.put("7121", "7121");
		hashMap.put("7122", "7122");
		hashMap.put("7123", "7123");
		hashMap.put("7124", "7124");
		hashMap.put("7125", "7125");
		hashMap.put("7126", "7126");
		hashMap.put("7127", "7127");
		hashMap.put("7128", "7128");
		hashMap.put("7129", "7129");
		hashMap.put("7130", "7130");
		hashMap.put("7131", "7131");
		hashMap.put("7132", "7132");
		hashMap.put("7133", "7133");
		hashMap.put("7134", "7134");
		hashMap.put("7135", "7135");
		hashMap.put("7136", "7136");
		hashMap.put("7137", "7137");
		hashMap.put("7138", "7138");
		hashMap.put("7139", "7139");
		hashMap.put("7140", "7140");
		hashMap.put("7141", "7141");
		hashMap.put("7142", "7142");
		hashMap.put("7143", "7143");
		hashMap.put("7144", "7144");
		hashMap.put("7145", "7145");
		hashMap.put("7146", "7146");
		hashMap.put("7147", "7147");
		hashMap.put("7148", "7148");
		hashMap.put("7149", "7149");
		hashMap.put("7150", "7150");
		hashMap.put("7151", "7151");
		hashMap.put("7152", "7152");
		hashMap.put("7153", "7153");
		hashMap.put("7154", "7154");
		hashMap.put("7155", "7155");
		hashMap.put("7156", "7156");
		hashMap.put("7157", "7157");
		hashMap.put("7158", "7158");
		hashMap.put("7159", "7159");
		hashMap.put("7160", "7160");
		hashMap.put("7161", "7161");
		hashMap.put("7162", "7162");
		hashMap.put("7163", "7163");
		hashMap.put("7164", "7164");
		hashMap.put("7165", "7165");
		hashMap.put("7166", "7166");
		hashMap.put("7167", "7167");
		hashMap.put("7168", "7168");
		hashMap.put("7169", "7169");
		hashMap.put("7170", "7170");
		hashMap.put("7171", "7171");
		hashMap.put("7172", "7172");
		hashMap.put("7173", "7173");
		hashMap.put("7174", "7174");
		hashMap.put("7175", "7175");
		hashMap.put("7176", "7176");
		hashMap.put("7177", "7177");
		hashMap.put("7178", "7178");
		hashMap.put("7179", "7179");
		hashMap.put("7180", "7180");
		hashMap.put("7181", "7181");
		hashMap.put("7182", "7182");
		hashMap.put("7183", "7183");
		hashMap.put("7184", "7184");
		hashMap.put("7185", "7185");
		hashMap.put("7186", "7186");
		hashMap.put("7187", "7187");
		hashMap.put("7188", "7188");
		hashMap.put("7189", "7189");
		hashMap.put("7190", "7190");
		hashMap.put("7191", "7191");
		hashMap.put("7192", "7192");
		hashMap.put("7193", "7193");
		hashMap.put("7194", "7194");
		hashMap.put("7195", "7195");
		hashMap.put("7196", "7196");
		hashMap.put("7197", "7197");
		hashMap.put("7198", "7198");
		hashMap.put("7199", "7199");
		hashMap.put("7200", "7200");
		hashMap.put("7201", "7201");
		hashMap.put("7202", "7202");
		hashMap.put("7203", "7203");
		hashMap.put("7204", "7204");
		hashMap.put("7205", "7205");
		hashMap.put("7206", "7206");
		hashMap.put("7207", "7207");
		hashMap.put("7208", "7208");
		hashMap.put("7209", "7209");
		hashMap.put("7210", "7210");
		hashMap.put("7211", "7211");
		hashMap.put("7212", "7212");
		hashMap.put("7213", "7213");
		hashMap.put("7214", "7214");
		hashMap.put("7215", "7215");
		hashMap.put("7216", "7216");
		hashMap.put("7217", "7217");
		hashMap.put("7218", "7218");
		hashMap.put("7219", "7219");
		hashMap.put("7220", "7220");
		hashMap.put("7221", "7221");
		hashMap.put("7222", "7222");
		hashMap.put("7223", "7223");
		hashMap.put("4138", "4138");
		hashMap.put("7300", "7300");
		hashMap.put("7224", "7224");
		hashMap.put("1832", "1832");
		hashMap.put("1783", "1783");
		hashMap.put("4139", "4139");
		hashMap.put("4140", "4140");
		hashMap.put("1784", "1784");
		hashMap.put("1785", "1785");
		hashMap.put("1786", "1786");
		hashMap.put("1787", "1787");
		hashMap.put("1333", "1333");
		hashMap.put("1334", "1334");
		hashMap.put("1335", "1335");
		hashMap.put("1336", "1336");
		hashMap.put("1337", "1337");
		hashMap.put("1338", "1338");
		hashMap.put("1339", "1339");
		hashMap.put("1340", "1340");
		hashMap.put("1341", "1341");
		hashMap.put("1342", "1342");
		hashMap.put("1343", "1343");
		hashMap.put("1344", "1344");
		hashMap.put("1345", "1345");
		hashMap.put("1346", "1346");
		hashMap.put("1347", "1347");
		hashMap.put("1348", "1348");
		hashMap.put("1349", "1349");
		hashMap.put("1383", "1383");
		hashMap.put("1384", "1384");
		hashMap.put("1385", "1385");
		hashMap.put("1386", "1386");
		hashMap.put("1387", "1387");
		hashMap.put("2666", "2666");
		hashMap.put("4268", "4268");
		hashMap.put("7225", "7225");
		hashMap.put("7226", "7226");
		hashMap.put("7227", "7227");
		hashMap.put("7228", "7228");
		hashMap.put("7229", "7229");
		hashMap.put("7230", "7230");
		hashMap.put("7231", "7231");
		hashMap.put("7232", "7232");
		hashMap.put("7233", "7233");
		hashMap.put("7234", "7234");
		hashMap.put("7235", "7235");
		hashMap.put("7236", "7236");
		hashMap.put("7237", "7237");
		hashMap.put("7238", "7238");
		hashMap.put("7239", "7239");
		hashMap.put("7240", "7240");
		hashMap.put("7241", "7241");
		hashMap.put("7242", "7242");
		hashMap.put("7243", "7243");
		hashMap.put("7244", "7244");
		hashMap.put("7245", "7245");
		hashMap.put("7246", "7246");
		hashMap.put("7247", "7247");
		hashMap.put("7248", "7248");
		hashMap.put("7249", "7249");
		hashMap.put("7250", "7250");
		hashMap.put("7251", "7251");
		hashMap.put("7252", "7252");
		hashMap.put("7253", "7253");
		hashMap.put("7254", "7254");
		hashMap.put("7255", "7255");
		hashMap.put("7256", "7256");
		hashMap.put("7257", "7257");
		hashMap.put("7258", "7258");
		hashMap.put("7259", "7259");
		hashMap.put("7260", "7260");
		hashMap.put("7261", "7261");
		hashMap.put("7262", "7262");
		hashMap.put("7263", "7263");
		hashMap.put("7264", "7264");
		hashMap.put("7265", "7265");
		hashMap.put("7266", "7266");
		hashMap.put("7267", "7267");
		hashMap.put("7268", "7268");
		hashMap.put("7269", "7269");
		hashMap.put("7270", "7270");
		hashMap.put("7271", "7271");
		hashMap.put("7272", "7272");
		hashMap.put("7273", "7273");
		hashMap.put("7274", "7274");
		hashMap.put("7275", "7275");
		hashMap.put("7276", "7276");
		hashMap.put("7277", "7277");
		hashMap.put("7278", "7278");
		hashMap.put("7279", "7279");
		hashMap.put("7280", "7280");
		hashMap.put("7281", "7281");
		hashMap.put("7282", "7282");
		hashMap.put("7283", "7283");
		hashMap.put("7284", "7284");
		hashMap.put("7285", "7285");
		hashMap.put("7286", "7286");
		hashMap.put("7287", "7287");
		hashMap.put("7288", "7288");
		hashMap.put("7289", "7289");
		hashMap.put("7290", "7290");
		hashMap.put("2099", "2099");
		hashMap.put("3550", "3550");
		hashMap.put("3551", "3551");
		hashMap.put("3552", "3552");
		hashMap.put("3553", "3553");
		hashMap.put("3554", "3554");
		hashMap.put("3555", "3555");
		hashMap.put("3557", "3557");
		hashMap.put("3558", "3558");
		hashMap.put("3559", "3559");
		hashMap.put("3560", "3560");
		hashMap.put("3561", "3561");
		hashMap.put("3562", "3562");
		hashMap.put("3700", "3700");
		hashMap.put("3701", "3701");
		hashMap.put("3702", "3702");
		hashMap.put("3703", "3703");
		hashMap.put("3704", "3704");
		hashMap.put("3705", "3705");
		hashMap.put("3706", "3706");
		hashMap.put("3707", "3707");
		hashMap.put("3708", "3708");
		hashMap.put("3709", "3709");
		hashMap.put("3710", "3710");
		hashMap.put("3711", "3711");
		hashMap.put("3712", "3712");
		hashMap.put("3713", "3713");
		hashMap.put("4470", "4470");
		hashMap.put("4471", "4471");
		hashMap.put("4472", "4472");
		hashMap.put("4473", "4473");
		hashMap.put("4474", "4474");
		hashMap.put("4475", "4475");
		hashMap.put("4476", "4476");
		hashMap.put("3600", "3600");
		hashMap.put("3601", "3601");
		hashMap.put("3602", "3602");
		hashMap.put("3603", "3603");
		hashMap.put("3604", "3604");
		hashMap.put("3605", "3605");
		hashMap.put("3606", "3606");
		hashMap.put("3607", "3607");
		hashMap.put("3608", "3608");
		hashMap.put("3609", "3609");
		hashMap.put("3610", "3610");
		hashMap.put("3611", "3611");
		hashMap.put("3612", "3612");
		hashMap.put("4173", "4173");
		hashMap.put("1788", "1788");
		hashMap.put("1307", "1307");
		hashMap.put("7350", "7350");
		hashMap.put("7351", "7351");
		hashMap.put("7352", "7352");
		hashMap.put("7353", "7353");
		hashMap.put("7354", "7354");
		hashMap.put("7355", "7355");
		hashMap.put("7356", "7356");
		hashMap.put("7357", "7357");
		hashMap.put("7358", "7358");
		hashMap.put("7359", "7359");
		hashMap.put("3730", "3730");
		hashMap.put("3731", "3731");
		hashMap.put("3732", "3732");
		hashMap.put("3733", "3733");
		hashMap.put("3734", "3734");
		hashMap.put("3735", "3735");
		hashMap.put("3736", "3736");
		hashMap.put("3737", "3737");
		hashMap.put("3738", "3738");
		hashMap.put("3740", "3740");
		hashMap.put("3741", "3741");
		hashMap.put("3742", "3742");
		hashMap.put("3743", "3743");
		hashMap.put("3744", "3744");
		hashMap.put("3745", "3745");
		hashMap.put("3746", "3746");
		hashMap.put("3747", "3747");
		hashMap.put("3748", "3748");
		hashMap.put("3749", "3749");
		hashMap.put("3750", "3750");
		hashMap.put("3751", "3751");
		hashMap.put("3752", "3752");
		hashMap.put("3753", "3753");
		hashMap.put("3754", "3754");
		hashMap.put("3755", "3755");
		hashMap.put("3756", "3756");
		hashMap.put("3757", "3757");
		hashMap.put("3758", "3758");
		hashMap.put("3760", "3760");
		hashMap.put("4174", "4174");
		hashMap.put("3613", "3613");
		hashMap.put("3614", "3614");
		hashMap.put("3616", "3616");
		hashMap.put("3617", "3617");
		hashMap.put("3618", "3618");
		hashMap.put("3619", "3619");
		hashMap.put("3620", "3620");
		hashMap.put("3621", "3621");
		hashMap.put("3622", "3622");
		hashMap.put("3623", "3623");
		hashMap.put("3624", "3624");
		hashMap.put("3625", "3625");
		hashMap.put("3626", "3626");
		hashMap.put("4175", "4175");
		hashMap.put("4176", "4176");
		hashMap.put("4177", "4177");
		hashMap.put("4178", "4178");
		hashMap.put("4179", "4179");
		hashMap.put("4180", "4180");
		hashMap.put("4181", "4181");
		hashMap.put("3564", "3564");
		hashMap.put("3565", "3565");
		hashMap.put("3566", "3566");
		hashMap.put("1789", "1789");
		hashMap.put("1526", "1526");
		hashMap.put("7301", "7301");
		hashMap.put("1790", "1790");
		hashMap.put("1791", "1791");
		hashMap.put("1792", "1792");
		hashMap.put("1793", "1793");
		hashMap.put("2313", "2313");
		hashMap.put("2314", "2314");
		hashMap.put("2315", "2315");
		hashMap.put("2316", "2316");
		hashMap.put("2317", "2317");
		hashMap.put("4701", "4701");
		hashMap.put("4702", "4702");
		hashMap.put("4703", "4703");
		hashMap.put("4704", "4704");
		hashMap.put("4705", "4705");
		hashMap.put("4706", "4706");
		hashMap.put("2057", "2057");
		hashMap.put("4953", "4953");
		hashMap.put("4707", "4707");
		hashMap.put("2160", "2160");
		hashMap.put("2161", "2161");
		hashMap.put("2162", "2162");
		hashMap.put("2163", "2163");
		hashMap.put("2164", "2164");
		hashMap.put("2165", "2165");
		hashMap.put("2166", "2166");
		hashMap.put("2318", "2318");
		hashMap.put("2319", "2319");
		hashMap.put("2320", "2320");
		hashMap.put("2321", "2321");
		hashMap.put("2322", "2322");
		hashMap.put("2323", "2323");
		hashMap.put("2324", "2324");
		hashMap.put("2325", "2325");
		hashMap.put("2326", "2326");
		hashMap.put("2327", "2327");
		hashMap.put("1831", "1831");
		hashMap.put("4708", "4708");
		hashMap.put("4709", "4709");
		hashMap.put("4710", "4710");
		hashMap.put("4711", "4711");
		hashMap.put("4712", "4712");
		hashMap.put("4713", "4713");
		hashMap.put("4714", "4714");
		hashMap.put("4715", "4715");
		hashMap.put("4716", "4716");
		hashMap.put("4717", "4717");
		hashMap.put("4718", "4718");
		hashMap.put("4719", "4719");
		hashMap.put("4720", "4720");
		hashMap.put("4721", "4721");
		hashMap.put("4722", "4722");
		hashMap.put("4723", "4723");
		hashMap.put("4731", "4731");
		hashMap.put("4732", "4732");
		hashMap.put("4733", "4733");
		hashMap.put("4734", "4734");
		hashMap.put("4735", "4735");
		hashMap.put("4736", "4736");
		hashMap.put("4737", "4737");
		hashMap.put("4738", "4738");
		hashMap.put("4739", "4739");
		hashMap.put("4740", "4740");
		hashMap.put("4741", "4741");
		hashMap.put("4742", "4742");
		hashMap.put("2328", "2328");
		hashMap.put("2329", "2329");
		hashMap.put("2330", "2330");
		hashMap.put("4724", "4724");
		hashMap.put("4725", "4725");
		hashMap.put("4726", "4726");
		hashMap.put("4727", "4727");
		hashMap.put("4728", "4728");
		hashMap.put("4729", "4729");
		hashMap.put("4730", "4730");
		hashMap.put("2526", "2526");
		hashMap.put("2527", "2527");
		hashMap.put("4743", "4743");
		hashMap.put("4744", "4744");
		hashMap.put("4745", "4745");
		hashMap.put("4746", "4746");
		hashMap.put("4747", "4747");
		hashMap.put("4748", "4748");
		hashMap.put("6711", "6711");
		hashMap.put("6712", "6712");
		hashMap.put("6713", "6713");
		hashMap.put("6714", "6714");
		hashMap.put("6715", "6715");
		hashMap.put("6716", "6716");
		hashMap.put("6717", "6717");
		hashMap.put("6718", "6718");
		hashMap.put("6719", "6719");
		hashMap.put("6720", "6720");
		hashMap.put("6721", "6721");
		hashMap.put("6722", "6722");
		hashMap.put("6723", "6723");
		hashMap.put("6724", "6724");
		hashMap.put("6725", "6725");
		hashMap.put("6726", "6726");
		hashMap.put("6727", "6727");
		hashMap.put("6728", "6728");
		hashMap.put("6729", "6729");
		hashMap.put("6730", "6730");
		hashMap.put("6731", "6731");
		hashMap.put("6732", "6732");
		hashMap.put("6733", "6733");
		hashMap.put("6734", "6734");
		hashMap.put("6735", "6735");
		hashMap.put("6736", "6736");
		hashMap.put("2331", "2331");
		hashMap.put("1900", "1900");
		hashMap.put("1901", "1901");
		hashMap.put("1902", "1902");
		hashMap.put("1903", "1903");
		hashMap.put("1904", "1904");
		hashMap.put("1905", "1905");
		hashMap.put("1906", "1906");
		hashMap.put("1907", "1907");
		hashMap.put("1908", "1908");
		hashMap.put("1909", "1909");
		hashMap.put("1910", "1910");
		hashMap.put("1911", "1911");
		hashMap.put("1912", "1912");
		hashMap.put("1913", "1913");
		hashMap.put("1914", "1914");
		hashMap.put("6431", "6431");
		hashMap.put("6432", "6432");
		hashMap.put("6433", "6433");
		hashMap.put("6434", "6434");
		hashMap.put("6435", "6435");
		hashMap.put("6436", "6436");
		hashMap.put("6437", "6437");
		hashMap.put("6438", "6438");
		hashMap.put("6439", "6439");
		hashMap.put("6440", "6440");
		hashMap.put("6441", "6441");
		hashMap.put("6442", "6442");
		hashMap.put("6443", "6443");
		hashMap.put("6500", "6500");
		hashMap.put("6501", "6501");
		hashMap.put("6502", "6502");
		hashMap.put("1029", "1029");
		hashMap.put("1030", "1030");
		hashMap.put("1031", "1031");
		hashMap.put("1032", "1032");
		hashMap.put("1033", "1033");
		hashMap.put("1034", "1034");
		hashMap.put("1035", "1035");
		hashMap.put("1036", "1036");
		hashMap.put("4644", "4644");
		hashMap.put("1915", "1915");
		hashMap.put("1916", "1916");
		hashMap.put("1917", "1917");
		hashMap.put("1918", "1918");
		hashMap.put("1920", "1920");
		hashMap.put("1921", "1921");
		hashMap.put("1922", "1922");
		hashMap.put("1923", "1923");
		hashMap.put("1924", "1924");
		hashMap.put("1925", "1925");
		hashMap.put("1926", "1926");
		hashMap.put("1927", "1927");
		hashMap.put("1929", "1929");
		hashMap.put("1930", "1930");
		hashMap.put("1931", "1931");
		hashMap.put("1932", "1932");
		hashMap.put("6400", "6400");
		hashMap.put("6401", "6401");
		hashMap.put("6402", "6402");
		hashMap.put("6403", "6403");
		hashMap.put("6404", "6404");
		hashMap.put("6405", "6405");
		hashMap.put("6406", "6406");
		hashMap.put("6407", "6407");
		hashMap.put("6408", "6408");
		hashMap.put("6409", "6409");
		hashMap.put("6410", "6410");
		hashMap.put("6411", "6411");
		hashMap.put("6412", "6412");
		hashMap.put("6413", "6413");
		hashMap.put("6414", "6414");
		hashMap.put("6415", "6415");
		hashMap.put("6416", "6416");
		hashMap.put("6417", "6417");
		hashMap.put("6418", "6418");
		hashMap.put("6419", "6419");
		hashMap.put("6420", "6420");
		hashMap.put("6421", "6421");
		hashMap.put("6422", "6422");
		hashMap.put("6423", "6423");
		hashMap.put("6424", "6424");
		hashMap.put("6425", "6425");
		hashMap.put("6426", "6426");
		hashMap.put("6427", "6427");
		hashMap.put("6428", "6428");
		hashMap.put("6429", "6429");
		hashMap.put("6430", "6430");
		hashMap.put("6503", "6503");
		hashMap.put("6504", "6504");
		hashMap.put("6505", "6505");
		hashMap.put("6506", "6506");
		hashMap.put("6507", "6507");
		hashMap.put("6508", "6508");
		hashMap.put("6509", "6509");
		hashMap.put("6510", "6510");
		hashMap.put("6511", "6511");
		hashMap.put("6512", "6512");
		hashMap.put("6513", "6513");
		hashMap.put("6514", "6514");
		hashMap.put("6515", "6515");
		hashMap.put("6516", "6516");
		hashMap.put("6517", "6517");
		hashMap.put("6518", "6518");
		hashMap.put("6519", "6519");
		hashMap.put("6520", "6520");
		hashMap.put("6521", "6521");
		hashMap.put("6522", "6522");
		hashMap.put("6523", "6523");
		hashMap.put("6524", "6524");
		hashMap.put("6525", "6525");
		hashMap.put("6526", "6526");
		hashMap.put("6527", "6527");
		hashMap.put("6528", "6528");
		hashMap.put("6529", "6529");
		hashMap.put("6530", "6530");
		hashMap.put("6531", "6531");
		hashMap.put("6532", "6532");
		hashMap.put("6541", "6541");
		hashMap.put("6542", "6542");
		hashMap.put("6543", "6543");
		hashMap.put("6544", "6544");
		hashMap.put("6545", "6545");
		hashMap.put("6546", "6546");
		hashMap.put("6547", "6547");
		hashMap.put("6548", "6548");
		hashMap.put("6549", "6549");
		hashMap.put("6550", "6550");
		hashMap.put("6551", "6551");
		hashMap.put("6552", "6552");
		hashMap.put("6553", "6553");
		hashMap.put("6554", "6554");
		hashMap.put("6555", "6555");
		hashMap.put("6556", "6556");
		hashMap.put("6557", "6557");
		hashMap.put("6558", "6558");
		hashMap.put("6559", "6559");
		hashMap.put("6560", "6560");
		hashMap.put("6561", "6561");
		hashMap.put("6562", "6562");
		hashMap.put("6563", "6563");
		hashMap.put("6564", "6564");
		hashMap.put("6565", "6565");
		hashMap.put("6566", "6566");
		hashMap.put("6567", "6567");
		hashMap.put("6568", "6568");
		hashMap.put("6569", "6569");
		hashMap.put("6570", "6570");
		hashMap.put("6571", "6571");
		hashMap.put("6572", "6572");
		hashMap.put("6573", "6573");
		hashMap.put("6574", "6574");
		hashMap.put("6575", "6575");
		hashMap.put("6576", "6576");
		hashMap.put("6577", "6577");
		hashMap.put("6578", "6578");
		hashMap.put("6579", "6579");
		hashMap.put("2530", "2530");
		hashMap.put("2531", "2531");
		hashMap.put("2532", "2532");
		hashMap.put("2533", "2533");
		hashMap.put("2534", "2534");
		hashMap.put("6444", "6444");
		hashMap.put("6445", "6445");
		hashMap.put("4688", "4688");
		hashMap.put("4480", "4480");
		hashMap.put("4482", "4482");
		hashMap.put("4483", "4483");
		hashMap.put("4484", "4484");
		hashMap.put("4485", "4485");
		hashMap.put("4486", "4486");

		if (hashMap.containsKey(returnCod)) {
			ret = "1";
		}

		return ret;
	}
}
