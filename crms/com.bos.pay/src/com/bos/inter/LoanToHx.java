package com.bos.inter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.biz.MathHelper;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.EsbSocketService;
import com.bos.pub.socket.service.request.EsbBodyWmaRqDbtArray;
import com.bos.pub.socket.service.request.base.EsbAppHeadRq;
import com.bos.pub.socket.util.EsbSocketUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.git.easyrule.util.DateHelper;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.mgrcore.FXD011;
import com.primeton.mgrcore.OXD011_AccoutingReq;
import com.primeton.mgrcore.OXD012_AccoutingRes;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;

import commonj.sdo.DataObject;

@Bizlet("")
public class LoanToHx {
	public static TraceLogger logger = new TraceLogger(LoanToHx.class);

	/**
	 * 
	 * @Title: cdhp 
	 * @Description: 银承放款与核心交互接口 
	 * @param loanInfo
	 * @throws EOSException    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author GIT-LPC
	 * @date 2015年8月25日 下午8:55:09 
	 * @version V1.0
	 */
	public void cdhp(DataObject loanInfo) throws EOSException {
		try {
			//客户信息
			DataObject party = DataObjectUtil
					.createDataObject("com.bos.dataset.csm.TbCsmParty");
			party.set("partyId", loanInfo.get("partyId"));
			DatabaseUtil.expandEntity("default", party);

			//对公客户表
			DataObject corpparty = DataObjectUtil
					.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
			corpparty.set("partyId", loanInfo.get("partyId"));
			DatabaseUtil.expandEntity("default", corpparty);

			//合同信息
			DataObject conInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			conInfo.set("contractId", loanInfo.get("contractId"));
			DatabaseUtil.expandEntity("default", conInfo);
			
			//账户信息
			DataObject loanZh = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanZh");
			loanZh.set("loanId", loanInfo.get("loanId"));
			loanZh.set("zhlx", "2");
			DatabaseUtil.expandEntityByTemplate("default", loanZh, loanZh);
			if (loanZh.get("zh") == null) {
				throw new EOSException("未查询到结算账户");
			}
			
			//纸票与电票走不同渠道
			String pjzl = "01";//01-纸票 02-电票     默认纸票
			if(loanInfo.get("pjzl")== null ||"".equals(loanInfo.get("pjzl"))){
				pjzl = "01";
			}
			pjzl =  loanInfo.get("pjzl").toString();
			
			if("01".equals(pjzl)){//纸票走流程银行
				DataObject cdhp = DataObjectUtil
						.createDataObject("com.bos.pub.sys.WmaRq03002000011BODY01");

				//cdhp.set("branchId", (String) loanInfo.get("orgNum"));
				cdhp.set("intfNo", "WMA2012TO999020");
				cdhp.set("ctrNo", conInfo.get("contractNum"));
				cdhp.set("ittTlrNo", loanInfo.get("userNum"));
				cdhp.set("ittbrId", loanInfo.get("loanOrg"));
				cdhp.set("chrgOffNo", loanInfo.get("loanNum"));
				cdhp.set("drwrAcctNm", loanZh.get("zhmc"));
				cdhp.set("drwrAcctNo", loanZh.get("zh"));
				cdhp.set("sumNum", loanInfo.get("hpzs"));

				EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
				iEsbAppHeadRq.setBranchId(loanInfo.get("orgNum").toString());
				iEsbAppHeadRq.setTranTellerNo(loanInfo.get("userNum").toString());
				/*iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());*/

				String loanId = loanInfo.getString("loanId");
				DataObject hpamt = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanHpAmt");
				hpamt.set("loanId", loanId);
				DataObject[] hpamts = DatabaseUtil.queryEntitiesByTemplate(
						"default", hpamt);
				if (null == hpamts || 0 == hpamts.length) {
					throw new EOSException("未查询到票据信息！");
				}

				cdhp.set("drweBnkNm", loanInfo.get("drweBnkNm"));
				cdhp.set("drweBnkNo", loanInfo.get("drweBnkNo"));
				cdhp.set("drweBnkAdr", loanInfo.get("drweBnkAdr"));
				//借据数组赋值
				Object[] arrs = new Object[(Integer) loanInfo.get("hpzs")];
				for (int i = 0; i < hpamts.length; i++) {
					hpamt = hpamts[i];
					EsbBodyWmaRqDbtArray arr = new EsbBodyWmaRqDbtArray();
					arr.setDbtNo(hpamt.get("summaryNum").toString());
					arr.setDrftExpDt(DateHelper.formatDateYYYYMMDD((Date) hpamt
							.get("drftExpDt")));
					arr.setIssuAmt(((BigDecimal) hpamt.get("loanAmt"))
							.doubleValue());
					arr.setIssuDt(DateHelper.formatDateYYYYMMDD((Date) hpamt
							.get("issuDt")));
					arr.setPyeAcctNm(hpamt.get("pyeAcctNm").toString());
					arr.setPyeAcctNo(hpamt.get("pyeAcctNo").toString());
					arr.setPyeOpenAcctBnkNm(hpamt.get("pyeOpenAcctBnkNm")
							.toString());
					arrs[i] = arr;
				}
				EsbSocketUtil.setDataObject(cdhp, "esbBodyWmaRqDbtArrays", arrs);

				//保证金信息
				Map map2 = new HashMap();
				map2.put("contractId",loanInfo.get("contractId"));
				Object[] bzjxx = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryBzjxx", map2);
				if (bzjxx != null && bzjxx.length>0) {//有保证金信息才赋值
					EsbSocketUtil.setDataObject(cdhp, "esbBodyWmaRqMrgnArrays", bzjxx);
				}
				
				DataObject object = EsbSocketService.instance().socketDataObject(
						"RQ03002000011BODY01", iEsbAppHeadRq, cdhp);
				String returnCode = (String) object.get("ReturnCode");
				if (!"00000000000000".equals(returnCode)) {
					throw new EOSException((String) object.get("ReturnMsg"));
				}
			}else{//电票直接到核心下柜
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("loanId", (String) loanInfo.get("loanId"));
				Object[] qxry = (Object[]) DatabaseExt.queryByNamedSql("default",
						"com.bos.payInfo.queryForHx.queryMonthDay", map);

				DataObject cdhp = DataObjectUtil
						.createDataObject("com.bos.pub.sys.HxRq02002000002BODY01");
				cdhp.set("fileInd", "0");
				cdhp.set("acctRgonCd", loanInfo.get("loanOrg").toString()
						.substring(0, 2));
				cdhp.set("acctBrId", loanInfo.get("loanOrg").toString()
						.substring(2));
				cdhp.set("trdTp", "A");
				cdhp.set("oprTp", "0");
				cdhp.set("ctrNo", conInfo.get("contractNum"));
				cdhp.set("cstNo", (String) party.get("ecifPartyNum"));
				cdhp.set("cstNo", null);/////////////////////////////////测试用
				cdhp.set("txnCd", "3301");
				cdhp.set("acctCd", "624101");//科目代码
				cdhp.set("txnTp", "1");//交易类型
				cdhp.set("ccyTp", loanInfo.get("currencyCd"));
				cdhp.set("ctrAmt", conInfo.get("contractAmt"));
				cdhp.set("stopDt",
						DateHelper.formatDateYYYYMMDD(loanInfo.getDate("endDate")));//截止日期
				cdhp.set("ctrIttDt", DateHelper.formatDateYYYYMMDD(loanInfo
						.getDate("beginDate")));
				cdhp.set("ctrExpDt",
						DateHelper.formatDateYYYYMMDD(loanInfo.getDate("endDate")));
				cdhp.set("intRatCd", "");//利率代号
				cdhp.set("ratEfftDt", "18991231");
				cdhp.set("intRatMd", "");//利率方式
				cdhp.set("fltIntRat", new BigDecimal("0"));//浮动利率
				cdhp.set("moRat", new BigDecimal("0"));//浮动利率
				cdhp.set("intPnyRat", new BigDecimal("0"));//罚息率
				cdhp.set("intInd", "");//计息标志
				cdhp.set("intPrd", "");//计息周期

				if (qxry != null && qxry.length != 0) {
					DataObject monthandday = (DataObject) qxry[0];
					cdhp.set("moTrm", monthandday.get("A"));//期限月
					cdhp.set("dayTrm", monthandday.get("B"));//期限日
				}

				if (corpparty.get("orgRegisterCd") != null) {
					cdhp.set("entpCd", corpparty.get("orgRegisterCd"));//企业代号-组织机构代码
				} else {
					cdhp.set("entpCd", "");//企业代号-组织机构代码
				}
				cdhp.set("blgAcctNo", loanZh.get("zh"));//结算账号
				//cdhp.set("blgAcctNo","727010100100012615");//结算账号-------------------测试用-------------
				cdhp.set("rgonCd", GitUtil.getRgonCd());//区域代码

				String loanId = loanInfo.getString("loanId");
				DataObject summaryInfo = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanSummary");
				summaryInfo.set("loanId", loanId);
				DataObject[] summaryInfos = DatabaseUtil.queryEntitiesByTemplate(
						"default", summaryInfo);
				for (int i = 0; i < summaryInfos.length; i++) {
					summaryInfo = summaryInfos[i];
					cdhp.set("txnAmt", (BigDecimal) summaryInfo.get("summaryAmt"));

					cdhp.set("complNo", summaryInfo.get("summaryNum"));//下柜编号
					EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
					iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
					iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
					iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
					DataObject object = EsbSocketService.instance()
							.socketDataObject("RQ02002000002BODY01", iEsbAppHeadRq,
									cdhp);
					String returnCode = (String) object.get("ReturnCode");
					if (!"00000000000000".equals(returnCode)) {
						throw new EOSException((String) object.get("ReturnMsg"));
					}
				}
			}
			
			/*//期限月、日
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("loanId", (String) loanInfo.get("loanId"));
			Object[] qxry = (Object[]) DatabaseExt.queryByNamedSql("default",
					"com.bos.payInfo.queryForHx.queryMonthDay", map);

			DataObject cdhp = DataObjectUtil
					.createDataObject("com.bos.pub.sys.HxRq02002000002BODY01");
			cdhp.set("fileInd", "0");
			cdhp.set("acctRgonCd", (String) loanInfo.get("loanOrg"));
			cdhp.set("acctBrId",
					loanInfo.get("loanOrg").toString().substring(0, 2));
			cdhp.set("trdTp", "A");
			cdhp.set("oprTp", "0");
			cdhp.set("ctrNo", conInfo.get("contractNum"));
			cdhp.set("cstNo", (String) party.get("ecifPartyNum"));
			cdhp.set("cstNo", null);/////////////////////////////////测试用
			cdhp.set("txnCd", "3301");
			cdhp.set("acctCd", "624101");//科目代码
			cdhp.set("txnTp", "1");//交易类型
			cdhp.set("ccyTp", loanInfo.get("currencyCd"));
			cdhp.set("ctrAmt", conInfo.get("contractAmt"));
			cdhp.set("stopDt",
					DateHelper.formatDateYYYYMMDD(loanInfo.getDate("endDate")));//截止日期
			cdhp.set("ctrIttDt", DateHelper.formatDateYYYYMMDD(loanInfo
					.getDate("beginDate")));
			cdhp.set("ctrExpDt",
					DateHelper.formatDateYYYYMMDD(loanInfo.getDate("endDate")));
			cdhp.set("intRatCd", "");//利率代号
			cdhp.set("ratEfftDt", "18991231");
			cdhp.set("intRatMd", "");//利率方式
			cdhp.set("fltIntRat", new BigDecimal("0"));//浮动利率
			cdhp.set("moRat", new BigDecimal("0"));//浮动利率
			cdhp.set("intPnyRat", new BigDecimal("0"));//罚息率
			cdhp.set("intInd", "");//计息标志
			cdhp.set("intPrd", "");//计息周期

			if (qxry != null && qxry.length != 0) {
				DataObject monthandday = (DataObject) qxry[0];
				cdhp.set("moTrm", monthandday.get("A"));//期限月
				cdhp.set("dayTrm", monthandday.get("B"));//期限日
			}

			if (corpparty.get("orgRegisterCd") != null) {
				cdhp.set("entpCd", corpparty.get("orgRegisterCd"));//企业代号-组织机构代码
			} else {
				cdhp.set("entpCd", "");//企业代号-组织机构代码
			}
			cdhp.set("blgAcctNo", loanZh.get("zh"));//结算账号
			//cdhp.set("blgAcctNo","727010100100012615");//结算账号-------------------测试用-------------
			cdhp.set("rgonCd", GitUtil.getRgonCd());//区域代码

			String loanId = loanInfo.getString("loanId");
			DataObject summaryInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summaryInfo.set("loanId", loanId);
			DataObject[] summaryInfos = DatabaseUtil.queryEntitiesByTemplate(
					"default", summaryInfo);
			for (int i = 0; i < summaryInfos.length; i++) {
				summaryInfo = summaryInfos[i];
				cdhp.set("txnAmt", (BigDecimal) summaryInfo.get("summaryAmt"));

				cdhp.set("complNo", summaryInfo.get("summaryNum"));//下柜编号
				EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
				iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
				iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
				iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
				DataObject object = EsbSocketService.instance()
						.socketDataObject("RQ02002000002BODY01", iEsbAppHeadRq,
								cdhp);
				String returnCode = (String) object.get("ReturnCode");
				if (!"00000000000000".equals(returnCode)) {
					throw new EOSException((String) object.get("ReturnMsg"));
				}
			}*/

		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: gnbl 
	 * @Description: 国内保理放款与核心交互接口  
	 * @param loanInfo
	 * @throws EOSException    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author GIT-LPC
	 * @date 2015年8月25日 下午10:00:14 
	 * @version V1.0
	 */
	public void gnbl(DataObject loanInfo) throws EOSException {
		try {
			//客户信息
			DataObject party = DataObjectUtil
					.createDataObject("com.bos.dataset.csm.TbCsmParty");
			party.set("partyId", loanInfo.get("partyId"));
			DatabaseUtil.expandEntity("default", party);

			//对公客户表
			DataObject corpparty = DataObjectUtil
					.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
			corpparty.set("partyId", loanInfo.get("partyId"));
			DatabaseUtil.expandEntity("default", corpparty);
			//合同信息
			DataObject conInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			conInfo.set("contractId", loanInfo.get("contractId"));
			DatabaseUtil.expandEntity("default", conInfo);

			//账户信息
			DataObject loanZh = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanZh");
			loanZh.set("loanId", loanInfo.get("loanId"));
			loanZh.set("zhlx", "2");
			DatabaseUtil.expandEntityByTemplate("default", loanZh, loanZh);
			if (loanZh.get("zh") == null) {
				throw new EOSException("未查询到结算账户");
			}
			//期限月、日
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("loanId", (String) loanInfo.get("loanId"));
			Object[] qxry = (Object[]) DatabaseExt.queryByNamedSql("default",
					"com.bos.payInfo.queryForHx.queryMonthDay", map);

			DataObject cdhp = DataObjectUtil
					.createDataObject("com.bos.pub.sys.HxRq02002000002BODY01");
			cdhp.set("fileInd", "0");
			cdhp.set("acctRgonCd", loanInfo.get("loanOrg").toString()
					.substring(0, 2));
			cdhp.set("acctBrId", loanInfo.get("loanOrg").toString()
					.substring(2));
			cdhp.set("trdTp", "A");
			cdhp.set("oprTp", "0");
			cdhp.set("complNo", loanInfo.get("loanNum"));//下柜编号
			cdhp.set("ctrNo", conInfo.get("contractNum"));
			cdhp.set("cstNo", (String) party.get("ecifPartyNum"));
			cdhp.set("cstNo", null);/////////////////////////////////测试用
			cdhp.set("txnCd", "3301");
			if ("01009001".equals(conInfo.get("productType"))) {//国内融资性保函
				cdhp.set("acctCd", "626101");//科目代码
			} else if ("01009002".equals(conInfo.get("productType")) || "01009010".equals(conInfo.get("productType"))) {//国内非融资性保函
				//cdhp.set("acctCd", "626102");//科目代码
			} else if ("01004001".equals(conInfo.get("productType"))) {//国内保理
				cdhp.set("acctCd", "626102");//科目代码
			} else if ("01011001".equals(conInfo.get("productType"))
					|| "01012001".equals(conInfo.get("productType"))) {//信贷证明、项目贷款承诺函
				cdhp.set("acctCd", "613501");//科目代码
			} else if ("01006001".equals(conInfo.get("productType"))
					||"01006002".equals(conInfo.get("productType"))
					||"01006010".equals(conInfo.get("productType")) //村镇银行贴现产品
					) {//贴现
				cdhp.set("acctCd", "123110");//科目代码
			}
			cdhp.set("txnTp", "1");//交易类型
			cdhp.set("ccyTp", loanInfo.get("currencyCd"));
			cdhp.set("ctrAmt", conInfo.get("contractAmt"));
			cdhp.set("stopDt",
					DateHelper.formatDateYYYYMMDD(loanInfo.getDate("endDate")));//截止日期
			cdhp.set("ctrIttDt", DateHelper.formatDateYYYYMMDD(loanInfo
					.getDate("beginDate")));
			cdhp.set("ctrExpDt",
					DateHelper.formatDateYYYYMMDD(loanInfo.getDate("endDate")));
			cdhp.set("intRatCd", "");//利率代号
			cdhp.set("ratEfftDt", "18991231");
			cdhp.set("intRatMd", "");//利率方式
			cdhp.set("fltIntRat", new BigDecimal("0"));//浮动利率
			cdhp.set("moRat", new BigDecimal("0"));//浮动利率
			cdhp.set("intPnyRat", new BigDecimal("0"));//罚息率
			cdhp.set("intInd", "");//计息标志
			cdhp.set("intPrd", new BigDecimal("0"));//计息周期
			if (qxry != null && qxry.length != 0) {
				DataObject monthandday = (DataObject) qxry[0];
				cdhp.set("moTrm", monthandday.get("A"));//期限月
				cdhp.set("dayTrm", monthandday.get("B"));//期限日
			}
			if (corpparty.get("orgRegisterCd") != null) {
				cdhp.set("entpCd", corpparty.get("orgRegisterCd"));//企业代号-组织机构代码
			} else {
				cdhp.set("entpCd", "");//企业代号-组织机构代码
			}
			cdhp.set("rgonCd", GitUtil.getRgonCd());//区域代码
			cdhp.set("blgAcctNo", loanZh.get("zh"));//结算账号
			//cdhp.set("blgAcctNo","727010100100012615");//结算账号-------------------测试用-------------

			cdhp.set("txnAmt", (BigDecimal) loanInfo.get("loanAmt"));//交易金额

			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
			DataObject object = EsbSocketService.instance().socketDataObject(
					"RQ02002000002BODY01", iEsbAppHeadRq, cdhp);
			String returnCode = (String) object.get("ReturnCode");
			if (!"00000000000000".equals(returnCode)) {
				throw new EOSException((String) object.get("ReturnMsg"));
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: entrustMoneyTrans 
	 * @Description: 委托资金扣划
	 * @param loanInfo    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author GIT-LPC
	 * @date 2015年9月21日 下午7:54:21 
	 * @version V1.0
	 */
	public String entrustMoneyTrans(DataObject loanInfo) {
		String resultString = "1";
		try {
//			DataObject wtzjkh = DataObjectUtil
//					.createDataObject("com.bos.pub.sys.HxRq01001000002BODY02");
//			wtzjkh.set("txnCd", "3181");
//			wtzjkh.set("rgonCd", GitUtil.getRgonCd());//区域代码
//			wtzjkh.set("bsnTp", "93");//业务类型
//			wtzjkh.set("oprInd", "0");//操作标志
//			wtzjkh.set("crDbFlg", "0");//借贷标志
//			wtzjkh.set("cashTrfFlg", "1");//现转标志
			//账户信息
			Map map = new HashMap();
			map.put("loanId", loanInfo.get("loanId"));
			Object[] entAccs = (Object[]) DatabaseExt.queryByNamedSql(
					"default", "com.bos.payInfo.queryForHx.queryEntAcc", map);
			DataObject entacc = (DataObject) entAccs[0];
//			wtzjkh.set("acctInd", entacc.get("CZH"));//账户代号
//			wtzjkh.set("pyeAcctNo", entacc.get("RZH"));//对方账户代号
//
//			wtzjkh.set("txnAmt", loanInfo.getBigDecimal("loanAmt"));//交易金额
//			wtzjkh.set("txnAmt1", new BigDecimal("0"));//手续费
//			wtzjkh.set("smyCd", "127");//摘要代码
//			wtzjkh.set("pfwDt", GitUtil.getBusiDateYYYYMMDD());//前置日期
//			wtzjkh.set("sysTrcNo", BizNumGenerator.genSysTrcNo());//系统跟踪号
//			EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
//			iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
//			iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
//			iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
//			DataObject object = EsbSocketService.instance().socketDataObject(
//					"RQ01001000002BODY02", iEsbAppHeadRq, wtzjkh);
//			String returnCode = (String) object.get("ReturnCode");
//			if (!"00000000000000".equals(returnCode)) {
//				resultString = (String) object.get("ReturnMsg");
//				return "委托资金扣划：" + resultString;
//			}
			String currencyCd = loanInfo.getString("currencyCd");//货币
			CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
			OXD011_AccoutingReq req = new OXD011_AccoutingReq();
			req.setHxorg(loanInfo.getString("loanOrg"));
			req.setAmount(loanInfo.getString("loanAmt"));
			req.setRecNum(new BigInteger("2"));
			FXD011[] msg = new FXD011[2];
			FXD011 msgenty = new FXD011();
			FXD011 msgenty1 = new FXD011();
			req.setChargeSeq(String.valueOf(BizNumGenerator.getLcsStan()));
			req.setOutSystemDate(GitUtil.getBusiDateYYYYMMDD());
			req.setRecNum(new BigInteger("2"));
			//req.setRemarkInfo("J");//类型
			req.setSummaryCode("B00149");//摘要代码
			req.setSummaryDescription("委托贷款");//摘要描述
			msgenty.setDealType("0");
			msgenty.setDrCrFlag("0");//借贷标志--0-借/收  1-贷/付
			msgenty.setAcct(entacc.getString("CZH"));
			if("CNY".equals(currencyCd)){
				msgenty.setCurrCode("01");
				msgenty.setCashFlag("0");
			}else{
				if(currencyCd.equals("HKD")){//港币
					msgenty.setCurrCode("13");
				}else if(currencyCd.equals("JPY")){//日元
					msgenty.setCurrCode("27");
				}else if(currencyCd.equals("MOP")){//澳门元
					msgenty.setCurrCode("81");
				}else if(currencyCd.equals("AUD")){//澳洲元
					msgenty.setCurrCode("29");
				}else if(currencyCd.equals("SGD")){//新加坡元
					msgenty.setCurrCode("32");
				}else if(currencyCd.equals("CHF")){//瑞士法郎
					msgenty.setCurrCode("15");
				}else if(currencyCd.equals("GBP")){//英镑
					msgenty.setCurrCode("12");
				}else if(currencyCd.equals("USD")){//美元
					msgenty.setCurrCode("14");
				}else if(currencyCd.equals("EUR")){//欧元
					msgenty.setCurrCode("38");
				}else if(currencyCd.equals("CAD")){//加拿大元
					msgenty.setCurrCode("28");
				}else{
					throw new EOSException("不支持的币种");
				}
				msgenty.setCashFlag("1");
			}
			msgenty.setTransAmt(loanInfo.getString("loanAmt"));
			msgenty.setAcctFromGo("0");
			msgenty.setPwdKind("00");
			msgenty.setSignPassFlag("1");
			msgenty.setVertLastboxSignFlag("0");
			msgenty.setFeePayType("0");
			msg[0]=msgenty;
			msgenty1.setDealType("0");
			msgenty1.setDrCrFlag("1");//借贷标志--0-借/收  1-贷/付
			msgenty1.setAcct(entacc.getString("RZH"));
			if("CNY".equals(currencyCd)){
				msgenty1.setCurrCode("01");
				msgenty1.setCashFlag("0");
			}else{
				if(currencyCd.equals("HKD")){//港币
					msgenty1.setCurrCode("13");
				}else if(currencyCd.equals("JPY")){//日元
					msgenty1.setCurrCode("27");
				}else if(currencyCd.equals("MOP")){//澳门元
					msgenty1.setCurrCode("81");
				}else if(currencyCd.equals("AUD")){//澳洲元
					msgenty1.setCurrCode("29");
				}else if(currencyCd.equals("SGD")){//新加坡元
					msgenty1.setCurrCode("32");
				}else if(currencyCd.equals("CHF")){//瑞士法郎
					msgenty1.setCurrCode("15");
				}else if(currencyCd.equals("GBP")){//英镑
					msgenty1.setCurrCode("12");
				}else if(currencyCd.equals("USD")){//美元
					msgenty1.setCurrCode("14");
				}else if(currencyCd.equals("EUR")){//欧元
					msgenty1.setCurrCode("38");
				}else if(currencyCd.equals("CAD")){//加拿大元
					msgenty1.setCurrCode("28");
				}else{
					throw new EOSException("不支持的币种");
				}
				msgenty1.setCashFlag("1");
			}
			msgenty1.setTransAmt(loanInfo.getString("loanAmt"));
			msgenty1.setAcctFromGo("0");
			msgenty1.setPwdKind("00");
			msgenty1.setSignPassFlag("1");
			msgenty1.setVertLastboxSignFlag("0");
			msgenty1.setFeePayType("0");
			msg[1]=msgenty1;
			req.setFxd011(msg);
			OXD012_AccoutingRes rs = proxy.executeXD01(req);
			if(!"AAAAAAA".equals(rs.getResTranHeader().getHRetCode())){
				throw new EOSException(rs.getResTranHeader().getHRetMsg());
			}
			//如果成功，向记录表插入数据
			Date date = GitUtil.getBusiDate();
			DataObject wdrecord = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanEntrTransRecord");
			wdrecord.set("createTime", date);
			wdrecord.set("updateTime", date);
			wdrecord.set("loanId", loanInfo.get("loanId"));
			wdrecord.set("outAcc", entacc.get("CZH"));
			wdrecord.set("inAcc", entacc.get("RZH"));
			wdrecord.set("status", "02");
			//DatabaseUtil.saveEntity("default", wdrecord);
			Object[] params1 = new Object[1];
			params1[0] = wdrecord;
			ILogicComponent logicComponent = LogicComponentFactory
					.create("com.bos.payInfo.PayInfo");
			Object[] objs = null;
			objs = logicComponent.invoke("saveEntrRecord", params1, "suspend");
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().length() > 100) {
				resultString = e.getMessage().substring(0, 100);
			} else {
				resultString = e.getMessage();
			}
		} catch (Throwable e) {
			e.printStackTrace();
			if (e.getMessage().length() > 100) {
				resultString = e.getMessage().substring(0, 100);
			} else {
				resultString = e.getMessage();
			}
		}
		return resultString;
	}

	/**
	 * 
	 * @Title: entrustMoneyTransBack 
	 * @Description: 委托资金扣划回滚
	 * @param loanInfo    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author GIT-LPC
	 * @date 2015年9月21日 下午7:54:21 
	 * @version V1.0
	 */
	public String entrustMoneyTransBack(DataObject loanInfo) {
		String resultString = "1";
		try {
			//账户信息
			Map map = new HashMap();
			map.put("loanId", loanInfo.get("loanId"));
			Object[] entAccs = (Object[]) DatabaseExt.queryByNamedSql(
					"default", "com.bos.payInfo.queryForHx.queryEntAcc", map);
			DataObject entacc = (DataObject) entAccs[0];
			String currencyCd = loanInfo.getString("currencyCd");//货币
			CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
			OXD011_AccoutingReq req = new OXD011_AccoutingReq();
			req.setHxorg(loanInfo.getString("loanOrg"));
			req.setAmount(loanInfo.getString("loanAmt"));
			req.setRecNum(new BigInteger("2"));
			FXD011[] msg = new FXD011[2];
			FXD011 msgenty = new FXD011();
			FXD011 msgenty1 = new FXD011();
			req.setChargeSeq(String.valueOf(BizNumGenerator.getLcsStan()));
			req.setOutSystemDate(GitUtil.getBusiDateYYYYMMDD());
			req.setRecNum(new BigInteger("2"));
			req.setSummaryCode("B00150");//摘要代码
			req.setSummaryDescription("委托贷款撤销");//摘要描述
			msgenty.setDealType("0");
			msgenty.setDrCrFlag("1");//借贷标志--0-借/收  1-贷/付
			msgenty.setAcct(entacc.getString("CZH"));
			if("CNY".equals(currencyCd)){
				msgenty.setCurrCode("01");
				msgenty.setCashFlag("0");
			}else{
				if(currencyCd.equals("HKD")){//港币
					msgenty.setCurrCode("13");
				}else if(currencyCd.equals("JPY")){//日元
					msgenty.setCurrCode("27");
				}else if(currencyCd.equals("MOP")){//澳门元
					msgenty.setCurrCode("81");
				}else if(currencyCd.equals("AUD")){//澳洲元
					msgenty.setCurrCode("29");
				}else if(currencyCd.equals("SGD")){//新加坡元
					msgenty.setCurrCode("32");
				}else if(currencyCd.equals("CHF")){//瑞士法郎
					msgenty.setCurrCode("15");
				}else if(currencyCd.equals("GBP")){//英镑
					msgenty.setCurrCode("12");
				}else if(currencyCd.equals("USD")){//美元
					msgenty.setCurrCode("14");
				}else if(currencyCd.equals("EUR")){//欧元
					msgenty.setCurrCode("38");
				}else if(currencyCd.equals("CAD")){//加拿大元
					msgenty.setCurrCode("28");
				}else{
					throw new EOSException("不支持的币种");
				}
				msgenty.setCashFlag("1");
			}
			msgenty.setTransAmt(loanInfo.getString("loanAmt"));
			msgenty.setAcctFromGo("0");
			msgenty.setPwdKind("00");
			msgenty.setSignPassFlag("1");
			msgenty.setVertLastboxSignFlag("0");
			msgenty.setFeePayType("0");
			msg[0]=msgenty;
			msgenty1.setDealType("0");
			msgenty1.setDrCrFlag("0");//借贷标志--0-借/收  1-贷/付
			msgenty1.setAcct(entacc.getString("RZH"));
			if("CNY".equals(currencyCd)){
				msgenty1.setCurrCode("01");
				msgenty1.setCashFlag("0");
			}else{
				if(currencyCd.equals("HKD")){//港币
					msgenty1.setCurrCode("13");
				}else if(currencyCd.equals("JPY")){//日元
					msgenty1.setCurrCode("27");
				}else if(currencyCd.equals("MOP")){//澳门元
					msgenty1.setCurrCode("81");
				}else if(currencyCd.equals("AUD")){//澳洲元
					msgenty1.setCurrCode("29");
				}else if(currencyCd.equals("SGD")){//新加坡元
					msgenty1.setCurrCode("32");
				}else if(currencyCd.equals("CHF")){//瑞士法郎
					msgenty1.setCurrCode("15");
				}else if(currencyCd.equals("GBP")){//英镑
					msgenty1.setCurrCode("12");
				}else if(currencyCd.equals("USD")){//美元
					msgenty1.setCurrCode("14");
				}else if(currencyCd.equals("EUR")){//欧元
					msgenty1.setCurrCode("38");
				}else if(currencyCd.equals("CAD")){//加拿大元
					msgenty1.setCurrCode("28");
				}else{
					throw new EOSException("不支持的币种");
				}
				msgenty1.setCashFlag("1");
			}
			msgenty1.setTransAmt(loanInfo.getString("loanAmt"));
			msgenty1.setAcctFromGo("0");
			msgenty1.setPwdKind("00");
			msgenty1.setSignPassFlag("1");
			msgenty1.setVertLastboxSignFlag("0");
			msgenty1.setFeePayType("0");
			msg[1]=msgenty1;
			req.setFxd011(msg);
			OXD012_AccoutingRes rs = proxy.executeXD01(req);
			if(!"AAAAAAA".equals(rs.getResTranHeader().getHRetCode())){
				throw new EOSException(rs.getResTranHeader().getHRetMsg());
			}
			//如果成功，向记录表插入数据
			Date date = GitUtil.getBusiDate();
			DataObject wdrecord = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanEntrTransRecord");
			wdrecord.set("createTime", date);
			wdrecord.set("updateTime", date);
			wdrecord.set("loanId", loanInfo.get("loanId"));
			wdrecord.set("outAcc", entacc.get("RZH"));
			wdrecord.set("inAcc", entacc.get("CZH"));
			wdrecord.set("status", "02");
			//---做成逻辑流，以免交易失败回滚
			//DatabaseUtil.saveEntity("default", wdrecord);
			Object[] params1 = new Object[1];
			params1[0] = wdrecord;
			ILogicComponent logicComponent = LogicComponentFactory
					.create("com.bos.payInfo.PayInfo");
			Object[] objs = null;
			objs = logicComponent.invoke("saveEntrRecord", params1, "suspend");
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().length() > 100) {
				resultString = e.getMessage().substring(0, 100);
			} else {
				resultString = e.getMessage();
			}
			//如果失败，向记录表插入数据
			//账户信息
			Map map = new HashMap();
			map.put("loanId", loanInfo.get("loanId"));
			Object[] entAccs = (Object[]) DatabaseExt.queryByNamedSql(
					"default", "com.bos.payInfo.queryForHx.queryEntAcc", map);
			DataObject entacc = (DataObject) entAccs[0];
			Date date = GitUtil.getBusiDate();
			DataObject wdrecord = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanEntrTransRecord");
			wdrecord.set("createTime", date);
			wdrecord.set("updateTime", date);
			wdrecord.set("loanId", loanInfo.get("loanId"));
			wdrecord.set("outAcc", entacc.get("RZH"));
			wdrecord.set("inAcc", entacc.get("CZH"));
			wdrecord.set("status", "01");
			//DatabaseUtil.saveEntity("default", wdrecord);
			Object[] params1 = new Object[1];
			params1[0] = wdrecord;
			ILogicComponent logicComponent = LogicComponentFactory
					.create("com.bos.payInfo.PayInfo");
			Object[] objs = null;
			try {
				objs = logicComponent.invoke("saveEntrRecord", params1,
						"suspend");
			} catch (Throwable e1) {
				logger.info("---------保存委托扣划回滚信息失败------");
				e1.printStackTrace();
				resultString = "保存委托扣划回滚信息失败";
			}
		} catch (Throwable e) {
			e.printStackTrace();
			resultString = "保存委托扣划信息失败";
		}
		return resultString;
	}

	/**
	 * 
	 * @param partyId 
	 * @param loanInfo 
	 * @Title: cdhpcx 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param loanId 
	 * @throws EOSException    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author GIT-LPC
	 * @date 2015年11月12日 下午4:57:29 
	 * @version V1.0
	 */
	@Bizlet("")
	public String cdhpcx(String loanId, String partyId) throws EOSException {
		String retString = "撤销成功";
		try {
			//出账信息
			DataObject loanInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);

			//客户信息
			DataObject party = DataObjectUtil
					.createDataObject("com.bos.dataset.csm.TbCsmParty");
			party.set("partyId", partyId);
			DatabaseUtil.expandEntity("default", party);

			//对公客户表
			DataObject corpparty = DataObjectUtil
					.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
			corpparty.set("partyId", partyId);
			DatabaseUtil.expandEntity("default", corpparty);

			//合同信息
			DataObject conInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			conInfo.set("contractId", loanInfo.get("contractId"));
			DatabaseUtil.expandEntity("default", conInfo);

			//账户信息
			DataObject loanZh = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanZh");
			loanZh.set("loanId", loanId);
			loanZh.set("zhlx", "2");
			DatabaseUtil.expandEntityByTemplate("default", loanZh, loanZh);
			if (loanZh.get("zh") == null) {
				throw new EOSException("未查询到结算账户");
			}
			//期限月、日
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("loanId", loanId);
			Object[] qxry = (Object[]) DatabaseExt.queryByNamedSql("default",
					"com.bos.payInfo.queryForHx.queryMonthDay", map);

			DataObject cdhp = DataObjectUtil
					.createDataObject("com.bos.pub.sys.HxRq02002000002BODY01");
			cdhp.set("fileInd", "0");
			cdhp.set("acctRgonCd", (String) loanInfo.get("loanOrg"));
			cdhp.set("acctBrId",
					loanInfo.get("loanOrg").toString().substring(0, 2));
			cdhp.set("trdTp", "A");
			cdhp.set("oprTp", "1");//0-下柜  1-下柜撤销
			cdhp.set("ctrNo", conInfo.get("contractNum"));
			cdhp.set("cstNo", (String) party.get("ecifPartyNum"));
			cdhp.set("cstNo", null);/////////////////////////////////测试用--不用送此字段
			cdhp.set("txnCd", "3301");

			if ("01009001".equals(conInfo.get("productType"))) {//国内融资性保函
				cdhp.set("acctCd", "626101");//科目代码
			} else if ("01009002".equals(conInfo.get("productType")) || "01009010".equals(conInfo.get("productType"))) {//国内非融资性保函
				//cdhp.set("acctCd", "626102");//科目代码
			} else if ("01004001".equals(conInfo.get("productType"))) {//国内保理
				cdhp.set("acctCd", "626102");//科目代码
			} else if ("01011001".equals(conInfo.get("productType"))
					|| "01012001".equals(conInfo.get("productType"))) {//信贷证明、项目贷款承诺函
				cdhp.set("acctCd", "613501");//科目代码
			} else if ("01006001".equals(conInfo.get("productType"))
					||"01006002".equals(conInfo.get("productType"))
					||"01006010".equals(conInfo.get("productType")) //村镇银行贴现产品
					) {//贴现
				cdhp.set("acctCd", "123110");//科目代码
			} else if ("01008001".equals(conInfo.get("productType"))||"01008002".equals(conInfo.get("productType"))||"01008010".equals(conInfo.get("productType"))) {//银承
				cdhp.set("acctCd", "624101");//科目代码
			}
			cdhp.set("txnTp", "1");//交易类型
			cdhp.set("ccyTp", loanInfo.get("currencyCd"));
			cdhp.set("ctrAmt", conInfo.get("contractAmt"));
			cdhp.set("stopDt",
					DateHelper.formatDateYYYYMMDD(loanInfo.getDate("endDate")));//截止日期
			cdhp.set("ctrIttDt", DateHelper.formatDateYYYYMMDD(loanInfo
					.getDate("beginDate")));
			cdhp.set("ctrExpDt",
					DateHelper.formatDateYYYYMMDD(loanInfo.getDate("endDate")));
			cdhp.set("intRatCd", "");//利率代号
			cdhp.set("ratEfftDt", "18991231");
			cdhp.set("intRatMd", "");//利率方式
			cdhp.set("fltIntRat", new BigDecimal("0"));//浮动利率
			cdhp.set("moRat", new BigDecimal("0"));//浮动利率
			cdhp.set("intPnyRat", new BigDecimal("0"));//罚息率
			cdhp.set("intInd", "");//计息标志
			cdhp.set("intPrd", "");//计息周期

			if (qxry != null && qxry.length != 0) {
				DataObject monthandday = (DataObject) qxry[0];
				cdhp.set("moTrm", monthandday.get("A"));//期限月
				cdhp.set("dayTrm", monthandday.get("B"));//期限日
			}

			if (corpparty.get("orgRegisterCd") != null) {
				cdhp.set("entpCd", corpparty.get("orgRegisterCd"));//企业代号-组织机构代码
			} else {
				cdhp.set("entpCd", "");//企业代号-组织机构代码
			}
			cdhp.set("blgAcctNo", loanZh.get("zh"));//结算账号
			//cdhp.set("blgAcctNo","727010100100012615");//结算账号-------------------测试用-------------
			cdhp.set("rgonCd", GitUtil.getRgonCd());//区域代码

			DataObject summaryInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summaryInfo.set("loanId", loanId);
			DataObject[] summaryInfos = DatabaseUtil.queryEntitiesByTemplate(
					"default", summaryInfo);
			for (int i = 0; i < summaryInfos.length; i++) {
				summaryInfo = summaryInfos[i];
				cdhp.set("txnAmt", (BigDecimal) summaryInfo.get("summaryAmt"));

				cdhp.set("complNo", summaryInfo.get("summaryNum"));//下柜编号
				EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
				iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
				iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorefkjygy());
				iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorefkjygy());
				DataObject object = EsbSocketService.instance()
						.socketDataObject("RQ02002000002BODY01", iEsbAppHeadRq,
								cdhp);
				String returnCode = (String) object.get("ReturnCode");
				if (!"00000000000000".equals(returnCode)) {
					throw new EOSException((String) object.get("ReturnMsg"));
				}
				//成功后将借据及放款信息置为失效
				summaryInfo.set("summaryStatusCd", "06");
				summaryInfo.set("jjye", new BigDecimal("0"));
				DatabaseUtil.updateEntity("default", summaryInfo);
				
				//银承才有
				if ("01008001".equals(conInfo.get("productType"))||"01008002".equals(conInfo.get("productType"))) {//银承
					//票据信息状态更改从0改成1
					DataObject hpamt = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanHpAmt");
					hpamt.set("summaryNum", summaryInfo.get("summaryNum"));
					DatabaseUtil.expandEntityByTemplate("default", hpamt, hpamt);
					if(null == hpamt.get("moneyUseId")){
						throw new EOSException("交易失败，未找到票据信息");
					}
					hpamt.set("billState", "1");
					DatabaseUtil.updateEntity("default", hpamt);
				}
			}
			loanInfo.set("loanStatus", "06");
			DatabaseUtil.updateEntity("default", loanInfo);

			//回滚额度
			rollbackLimit(conInfo, loanInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("撤销失败");
		}
		return retString;
	}

	public void rollbackLimit(DataObject conInfo, DataObject loanInfo) {
		try {
			//回滚合同可用额度
			BigDecimal conBalance = conInfo.getBigDecimal("conBalance");
			BigDecimal pymtAmt = loanInfo.getBigDecimal("loanAmt");
			logger.info("------>3231------>回滚前合同可用余额为------>" + conBalance);
			Map map = new HashMap();
			map.put("conBalance", conBalance);
			map.put("PymtAmt", pymtAmt);
			String gs = "conBalance+PymtAmt";
			conBalance = MathHelper.expressionBigDecimal(gs, map);
			logger.info("------>3231------>回滚后合同可用余额为------>" + conBalance);
			conInfo.set("conBalance", conBalance);
			DatabaseUtil.updateEntity("default", conInfo);

			logger.info("------>3231------>回滚额度-----start->");
			Map map2 = new HashMap();
			map2.put("partyId", loanInfo.get("partyId"));
			DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
			/*
			//查询综合授信额度
			DataObject detailApprove = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
			detailApprove.set("amountDetailId", conInfo.get("amountDetailId"));
			DatabaseUtil.expandEntityByTemplate("default", detailApprove,
					detailApprove);
			logger.info("------>3231------>查询TbBizAmountDetailApprove-----end->");

			DataObject amountApprove = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
			amountApprove.set("amountId", detailApprove.get("amountId"));
			DatabaseUtil.expandEntityByTemplate("default", amountApprove,
					amountApprove);
			logger.info("------>3231------>查询TbBizAmountApprove-----end->");

			DataObject bizApprove = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("approveId", amountApprove.get("approveId"));
			DatabaseUtil.expandEntity("default", bizApprove);
			logger.info("------>3231------>查询TbBizApprove-----end->");

			DataObject creditLimit = DataObjectUtil
					.createDataObject("com.bos.dataset.crd.TbCrdCreditLimit");
			creditLimit.set("applyId", bizApprove.get("applyId"));
			creditLimit.set("statusCd", "03");
			DatabaseUtil.expandEntityByTemplate("default", creditLimit,
					creditLimit);
			BigDecimal availableAmt = creditLimit.getBigDecimal("availableAmt");
			logger.info("------>3231------>批复可用额度为------>" + availableAmt);
			BigDecimal hl = (BigDecimal) loanInfo.get("exchangeRate");
			gs = "availableAmt+PymtAmt*hl";
			map.put("availableAmt", availableAmt);
			map.put("hl", hl);
			availableAmt = MathHelper.expressionBigDecimal(gs, map);
			logger.info("------>3231------>回滚后可用额度为------>" + availableAmt);
			creditLimit.set("availableAmt", availableAmt);
			DatabaseUtil.updateEntity("default", creditLimit);*/

			logger.info("------>3231------>回滚额度-----end->");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("回滚额度失败！");
		}

	}

	//银承汇票导出excel
	@Bizlet("")
	public List<Object> getData(Object[] exldo) {
		String[] title = {  "收款人全称", "收款人账号",
				"收款人开户银行", "出票金额" };
		List<Object> l = new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			DataObject exlsing = (DataObject)exldo[i];
			String[] a = { 
					(String) exlsing.get("pyeAcctNm"),
					(String) exlsing.get("pyeAcctNo"),
					(String) exlsing.get("pyeOpenAcctBnkNm"),
					exlsing.get("loanAmt").toString() };
			// System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
			l.add(a);
		}
		return l;

	}

	public static void main(String[] args) {
		//

	}

}
