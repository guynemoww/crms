package com.bos.gjService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.batch.DealAccount;
import com.bos.crd.valid.RiskLimitValid;
import com.bos.inter.InterScheduler;
import com.bos.inter.LoanToLcs;
import com.bos.payInfo.LoanInfo;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.git.easyrule.util.RuleException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.mgrcore.IXD15AccountInfo;
import com.primeton.mgrcore.OXD051_AccInfoQryReq;
import com.primeton.mgrcore.OXD052_AccInfoQryRes;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.mgrcore.client.CrmsMgrCallCoreProxy;
import com.primeton.mgrcore.client.DateTools;
import com.primeton.p2p.P2pCreditImpl;
import com.primeton.plus.CrePayQueryRq;
import com.primeton.plus.RepayAccChangeRq;
import com.primeton.plus.RepayControlInfRq;
import com.primeton.plus.RepaymentRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;

import commonj.sdo.DataObject;

/**
 * 网贷调用信贷的Java实现方法
 * 
 * @author lenovo
 * 
 */
@Bizlet("WdToLoanServiceJavaImpl")
public class WdtoLoanServiceJavaImpl {
	private TraceLogger log = new TraceLogger(WdtoLoanServiceJavaImpl.class);
	private BigDecimal minAmt = new BigDecimal(1000);// 最小放款金额
	private String months = "3";// 客户征信拒绝受理在3个月之内
	private String PRODUCT_MSMD_CD = "02003013";// 绵商秒贷产品代码
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	/**
	 * @param d001Request
	 *            白名单信息查询接口
	 * @param rs
	 * @param rsInfo
	 *            查询该白名单客户的具体信息
	 */

	@Bizlet("")
	public D001Response executeD001(D001Request d001Request) {
		System.out.println("----["+sdf.format(new Date())+"]调用网贷wd001接口开始----");
		D001Response rs = new D001Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(d001Request.getRequestHeader()
				.getVersionNo());
		responseHeader.setReqSysCode(d001Request.getRequestHeader()
				.getReqSysCode());
		responseHeader.setReqSecCode(d001Request.getRequestHeader()
				.getReqSecCode());
		responseHeader.setTxType(d001Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(d001Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(d001Request.getRequestHeader().getReqDate());
		if (d001Request.getRequestHeader().getReqTime() != null
				&& !d001Request.getRequestHeader().getReqTime().equals("")) {
			responseHeader.setReqTime(d001Request.getRequestHeader()
					.getReqTime().substring(8, 14));
		}
		responseHeader
				.setReqSeqNo(d001Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(d001Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(d001Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		D001ResponseBody d001ResponseBody = new D001ResponseBody();
		rs.setResponseBody(d001ResponseBody);

		try {
			if ("00201".equals(d001Request.getRequestHeader().getReqSysCode())) {// 请求方系统代码
				if ("WD001".equals(d001Request.getReqTranHeader().getHTxnCd())) {// 交易码
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", d001Request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", d001Request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", d001Request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", d001Request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", d001Request
							.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", d001Request
							.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);

					// ecif客户编号
					String ecifPartyNum = d001Request.getRequestBody()
							.getEcifPartyNum();
					if ("".equals(ecifPartyNum) || null == ecifPartyNum) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "客户编号" + "}不能为空]");
						return rs;
					}
					// 证件号码
					String cerNum = d001Request.getRequestBody().getCerNum();
					if ("".equals(cerNum) || null == cerNum) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "证件号码" + "}不能为空]");
						return rs;
					}
					log.info("----wd001查询白名单额度信息开始----");
					// 白名单信息查询
					Map<String, String> mapWhiteInfo = new HashMap<String, String>();
					mapWhiteInfo.put("ecifPartyNum", ecifPartyNum);
					mapWhiteInfo.put("cerNum", cerNum);
					Object[] whiteInfos = DatabaseExt.queryByNamedSql(
							"default",
							"com.bos.pay.wdBaseService.getWhiteCustomerInfo",
							mapWhiteInfo);
					if (whiteInfos.length != 0 && whiteInfos != null) {
						DataObject whiteInfo = (DataObject) whiteInfos[0];
						d001ResponseBody.setRate(whiteInfo
								.getBigDecimal("RATE"));// 利率
						d001ResponseBody.setTotalLimit(whiteInfo
								.getBigDecimal("TOTALLIMIT"));// 客户网贷总额度
						d001ResponseBody.setUsedLimit(whiteInfo
								.getBigDecimal("USEDLIMIT"));// 已用额度
						d001ResponseBody.setUserId(whiteInfo
								.getString("USERID"));
						d001ResponseBody.setOrgCode(whiteInfo
								.getString("ORGCODE"));
						d001ResponseBody.setAviLimit(whiteInfo.getBigDecimal(
								"TOTALLIMIT").subtract(
								whiteInfo.getBigDecimal("USEDLIMIT")));// 可用额度
						d001ResponseBody.setEcifPartyNum(whiteInfo
								.getString("ECIFPARTYNUM"));
						d001ResponseBody.setCusName(whiteInfo
								.getString("CUSNAME"));
						d001ResponseBody.setCerNum(whiteInfo
								.getString("CERNUM"));
						d001ResponseBody.setCerType(whiteInfo
								.getString("CERTYPE"));// 证件类型

						rs.getResTranHeader().setHRetCode("AAAAAAA");
						rs.getResTranHeader().setHRetMsg("交易成功");
					} else {
						rs.getResTranHeader().setHRetCode("AAAAAAA");
						rs.getResTranHeader().setHRetMsg("交易成功[未查询到白名单客户信息]");
					}

					log.info("----wd001查询白名单额度信息结束----");

				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			log.info("WdtoLoanServiceJavaImpl--" + e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceJavaImpl--" + e.getMessage());
		}

		return rs;
	}

	/**
	 * @author ww
	 * @param wd002d002Request
	 * @return 网贷平台调用此接口 将网银传来的值保存到申请中间表 并向网贷返回业务正在受理的信息
	 */

	@Bizlet("")
	public D002Response executeD002(D002Request d002Request) {
		System.out.println("----调用wd002接口开始----");
		D002Response rs = new D002Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(d002Request.getRequestHeader()
				.getVersionNo());
		responseHeader.setReqSysCode(d002Request.getRequestHeader()
				.getReqSysCode());
		responseHeader.setReqSecCode(d002Request.getRequestHeader()
				.getReqSecCode());
		responseHeader.setTxType(d002Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(d002Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(d002Request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(d002Request.getRequestHeader().getReqTime()
				.substring(8, 14));
		responseHeader
				.setReqSeqNo(d002Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader
				.setFileHMac(d002Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(d002Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		D002ResponseBody responseBody = new D002ResponseBody();
		rs.setResponseBody(responseBody);

		try {
			if ("00201".equals(d002Request.getRequestHeader().getReqSysCode())) {// 请求方系统代码
				if ("WD002".equals(d002Request.getReqTranHeader().getHTxnCd())) {// 交易码
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", d002Request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", d002Request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", d002Request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", d002Request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", d002Request
							.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", d002Request
							.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);

					// 业务申请中间表
					DataObject applyMiddle = DataObjectUtil
							.createDataObject("com.bos.dataset.biz.TbBizApplyMiddle");
					// 业务申请利率applyDate
					String rate;
					String orgNum;
					String userNum;
					String partyId;

					// 1客户编号ecif号
					String ecifPartyNum = d002Request.getRequestBody()
							.getEcifPartyNum();
					if ("".equals(ecifPartyNum) || null == ecifPartyNum) {
						responseHeader(rs, "客户编号");
						return rs;
					} else {// 如果ecif编号不为空，则在白名单表中查询该客户
						Map<String, String> mapWhiteCus = new HashMap<String, String>();
						mapWhiteCus.put("ecifPartyNum", ecifPartyNum);
						Object[] whiteCustomers = DatabaseExt.queryByNamedSql(
								"default",
								"com.bos.pay.wdBaseService.queryWhiteCustomer",
								mapWhiteCus);
						// 白名单客户状态为02的表示客户信息有效
						if (whiteCustomers == null
								|| whiteCustomers.length == 0) {
							rs.getResTranHeader().setHRetCode("BBBBBBB");
							rs.getResTranHeader().setHRetMsg("白名单客户信息正在维护中");
							return rs;
						} else {// 白名单表里有该客户信息
							Map<String, String> mapMiddle = new HashMap<String, String>();
							mapMiddle.put("ecifPartyNum", ecifPartyNum);
							Object[] middleInfos = DatabaseExt
									.queryByNamedSql(
											"default",
											"com.bos.pay.wdBaseService.queryApplyMiddle",
											mapMiddle);
							// 该客户名下有正在处理的业务
							if (middleInfos != null && middleInfos.length != 0) {
								rs.getResTranHeader().setHRetCode("BBBBBBB");
								rs.getResTranHeader()
										.setHRetMsg("客户已经有正在受理的业务");
								return rs;// ToDo测试的时候不判断
							}
							// 查询中间表客户征信信息是否有拒绝受理的情况
							HashMap<String, String> mapCredit = new HashMap<String, String>();
							mapCredit.put("ecifPartyNum", ecifPartyNum);
							mapCredit.put("months", months);// 拒绝受理在几个月之内
							Object[] countCredits = DatabaseExt
									.queryByNamedSql(
											"default",
											"com.bos.pay.wdBaseService.queryApplyMiddleCredit",
											mapCredit);
							// 当记录大于0时，存在征信拒绝受理且在3个月内的情况
							if (countCredits != null && countCredits.length > 0) {
								DataObject countCredit = (DataObject) countCredits[0];
								if (countCredit.getInt("COUNTCREDIT") > 0) {
									rs.getResTranHeader()
											.setHRetCode("BBBBBBB");
									rs.getResTranHeader().setHRetMsg(
											"客户征信信息拒绝受理");
									return rs;
								}
							}

						}
						DataObject whiteCustomer = (DataObject) whiteCustomers[0];
						// 从白名单中获取利率值
						rate = whiteCustomer.getString("rate");
						orgNum = whiteCustomer.getString("manageOrg");
						userNum = whiteCustomer.getString("manageUser");
						partyId = whiteCustomer.getString("partyId");
						// 查询白名单，给中间表赋值
						applyMiddle.set("ecifPartyNum", ecifPartyNum);
						applyMiddle.set("orgNum", orgNum);// 经办机构
						applyMiddle.set("userNum", userNum);// 经办人
						applyMiddle.set("phoneNum",
								whiteCustomer.getString("phoneNum"));// 业务申请人手机号
					}

					// 2渠道来源
					String source = d002Request.getRequestBody().getSource();
					if ("".equals(source) || null == source) {
						responseHeader(rs, "渠道来源");
						return rs;
					}
					// 3产品类型
					String productType = d002Request.getRequestBody()
							.getProductType();
					if ("".equals(productType) || null == productType) {
						responseHeader(rs, "产品类型");
						return rs;
					}
					/**
					 * 经办人、经办机构需要从其它表里查询
					 */
					/*
					 * //4经办人 String userNum =
					 * d002Request.getRequestBody().getUserNum(); if
					 * ("".equals(userNum) || null == userNum) {
					 * 
					 * //return rs; } //5经办机构 String orgNum =
					 * d002Request.getRequestBody().getOrgNum(); if
					 * ("".equals(orgNum) || null == orgNum) {
					 * 
					 * //return rs; }
					 */
					// 6担保方式（一般默认为01信用）
					String guarantyType = d002Request.getRequestBody()
							.getGuarantyType();
					if ("".equals(guarantyType) || null == guarantyType) {
						responseHeader(rs, "担保方式");
						return rs;
					}
					// 7业务金额
					// String amt = d002Request.getRequestBody().getAmt();
					/*
					 * if ("".equals(amt) || null == amt) { responseHeader(rs,
					 * "业务金额"); return rs; }
					 */
					// 8贷款用途
					String loanUse = d002Request.getRequestBody().getLoanUse();
					if ("".equals(loanUse) || null == loanUse) {
						responseHeader(rs, "贷款用途");
						return rs;
					}
					// 9还款方式
					String repaymentType = d002Request.getRequestBody()
							.getRepaymentType();
					if ("".equals(repaymentType) || null == repaymentType) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{还款方式}不能为空]");
						return rs;
					}
					// 10还款来源
					String payment = d002Request.getRequestBody().getPayment();
					if ("".equals(payment) || null == payment) {
						responseHeader(rs, "还款来源");
						return rs;
					}
					// 11申请期限
					String applyXwTerm = d002Request.getRequestBody()
							.getApplyXwTerm();
					if ("".equals(applyXwTerm) || null == applyXwTerm) {
						responseHeader(rs, "申请期限");
						return rs;
					}
					// 12申请利率
					/*
					 * String applyRate =
					 * d002Request.getRequestBody().getApplyRate(); if
					 * ("".equals(applyRate) || null == applyRate) {
					 * //responseHeader(rs,"申请利率"); applyMiddle.set("applyRate",
					 * rate); } else { applyMiddle.set("applyRate", applyRate);
					 * }
					 */
					// 13申请日期
					String applyDate = d002Request.getRequestBody()
							.getApplyDate();
					if (applyDate.length() != 8) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "申请日期" + "}有误]");
						return rs;
					}
					// 14签约地点
					String signPlace = d002Request.getRequestBody()
							.getSignPlace();
					if ("".equals(signPlace) || null == signPlace) {
						responseHeader(rs, "签约地点");
						return rs;
					}
					// 15行业投向
					String loanTurn = d002Request.getRequestBody()
							.getLoanTurn();
					if ("".equals(loanTurn) || null == loanTurn) {
						responseHeader(rs, "行业投向");
						return rs;
					}
					// 19资金支付方式
					String payWay = d002Request.getRequestBody().getPayway();
					if ("".equals(payWay) || null == payWay) {
						responseHeader(rs, "资金支付方式");
						return rs;
					}
					// 20提前还款是否收违约金
					String prepaymentPenalty = d002Request.getRequestBody()
							.getPrepaymentPenalty();
					if ("".equals(prepaymentPenalty)
							|| null == prepaymentPenalty) {
						responseHeader(rs, "提前还款是否收违约金");
						return rs;
					}
					// 21提前还款补偿率(%)
					String wybcbl = d002Request.getRequestBody().getWybcbl();
					if ("".equals(wybcbl) || null == wybcbl) {
						// responseHeader(rs, "提前还款补偿率(%)");
						// return rs;
					}
					// 30放款账户名称
					String zhmc = d002Request.getRequestBody().getZhmc();
					if ("".equals(zhmc) || null == zhmc) {
						responseHeader(rs, "放款账户名称");
						return rs;
					}
					// 31放款账户账号
					String zhzh = d002Request.getRequestBody().getZhzh();
					if ("".equals(zhzh) || null == zhzh) {
						responseHeader(rs, "放款账户账号");
						return rs;
					}

					//调用核心校验账户信息(网贷放款和第一还款账户相同，因此只需要校验一个)
					Object[] params = new Object[3];
					String acctInd = zhzh;//放款账号
					String currCode = "01";//币种 01人民币
					String cashFlag = "0";//0钞户 1汇户
					params[0] = acctInd;//账号
					params[1] = currCode;//
					params[2] = cashFlag;//
					Object[] accInfo = new Object[3];

					//查询核心客户账号
					ILogicComponent logicComponet = LogicComponentFactory.create("com.bos.accInfo.accInfo");
					accInfo = logicComponet.invoke("queryAcc1", params);
					//核心查询失败
					if (!"AAAAAAA".equals(accInfo[2])) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[" + zhzh + "]账号不存在或已销户");
						return rs;
					}
					// 核心返回的账户信息
					OXD052_AccInfoQryRes accRes = (OXD052_AccInfoQryRes) accInfo[0];
					String accName = accRes.getOxd052ResBody().getCustName();// 账户名称
					if (!zhmc.equals(accName)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[" + zhmc + "]账户名称输入有误");
						return rs;
					}

					// 32开户行
					String zhkhjg = d002Request.getRequestBody().getZhkhjg();
					if ("".equals(zhkhjg) || null == zhkhjg) {
						responseHeader(rs, "开户行");
						return rs;
					}
					// 33账户状态
					String zhzt = d002Request.getRequestBody().getZhzt();
					if ("".equals(zhzt) || null == zhzt) {
						responseHeader(rs, "账户状态");
						return rs;
					}
					// 34第一还款账户名称
					String firstPayBackZhmc = d002Request.getRequestBody().getFirstPayBackZhmc();
					if ("".equals(firstPayBackZhmc) || null == firstPayBackZhmc) {
						responseHeader(rs, "第一还款账户名称");
						return rs;
					}
					// 35第一还款账户账号
					String firstPayBackZhzh = d002Request.getRequestBody()
							.getFirstPayBackZhzh();
					if ("".equals(firstPayBackZhzh) || null == firstPayBackZhzh) {
						responseHeader(rs, "第一还款账户账号");
						return rs;
					}
					// 36第一还款账户开户行
					String firstPayBackZhkhjg = d002Request.getRequestBody().getFirstPayBackZhkhjg();
					if ("".equals(firstPayBackZhkhjg) || null == firstPayBackZhkhjg) {
						responseHeader(rs, "第一还款账户开户行");
						return rs;
					}
					// 37第一还款账户状态
					String firstPayBackZhstatus = d002Request.getRequestBody()
							.getFirstPayBackZhstatus();
					if ("".equals(firstPayBackZhstatus) || null == firstPayBackZhstatus) {
						responseHeader(rs, "第一还款账户状态");
						return rs;
					}
					// 38第二还款账户名称
					String secondPayBackZhmc = d002Request.getRequestBody()
							.getSecondPayBackZhmc();
					if ("".equals(secondPayBackZhmc)
							|| null == secondPayBackZhmc) {
						//responseHeader(rs, "第二还款账户名称");
					}
					// 39第二还款账户账号
					String secondPayBackZhzh = d002Request.getRequestBody()
							.getSecondPayBackZhzh();
					if ("".equals(secondPayBackZhzh)
							|| null == secondPayBackZhzh) {
						//responseHeader(rs, "第二还款账户账号");
					}
					// 40第二还款账户开户行
					String secondPayBackZhkhjg = d002Request.getRequestBody()
							.getZhkhjg();
					if ("".equals(secondPayBackZhkhjg)
							|| null == secondPayBackZhkhjg) {
						//responseHeader(rs, "第二还款账户开户行");
					}
					// 41第二还款账户状态
					String secondPayBackZhstatus = d002Request.getRequestBody()
							.getSecondPayBackZhstatus();
					if ("".equals(secondPayBackZhstatus)
							|| null == secondPayBackZhstatus) {
						//responseHeader(rs, "第二还款账户状态");
					}
					// 42第三还款账户名称
					String thirdPayBackZhmc = d002Request.getRequestBody()
							.getThirdPayBackZhmc();
					if ("".equals(thirdPayBackZhmc) || null == thirdPayBackZhmc) {
						//responseHeader(rs, "第三还款账户名称");
					}
					// 43第三还款账户账号
					String thirdPayBackZhzh = d002Request.getRequestBody()
							.getThirdPayBackZhzh();
					if ("".equals(thirdPayBackZhzh) || null == thirdPayBackZhzh) {
						//responseHeader(rs, "第三还款账户账号");
					}
					// 44第三还款账户开户行
					String thirdPayBackZhkhjg = d002Request.getRequestBody()
							.getThirdPayBackZhkhjg();
					if ("".equals(thirdPayBackZhkhjg)
							|| null == thirdPayBackZhkhjg) {
						//responseHeader(rs, "第三还款账户开户行");
					}
					// 45第三还款账户状态
					String thirdPayBackZhstatus = d002Request.getRequestBody().getThirdPayBackZhstatus();
					if ("".equals(thirdPayBackZhstatus) || null == thirdPayBackZhstatus) {
						//responseHeader(rs, "第三还款账户状态");
					}

					log.info("----wd002保存业务申请中间表信息开始----");
					// 中间表主键、业务申请ID
					String middleId = WdBaseService.getUuid();
					String applyId = WdBaseService.getUuid();

					applyMiddle.set("middleId", middleId);// 主键
					applyMiddle.set("applyId", applyId);// 本次业务申请ID
					applyMiddle.set("partyId", partyId);// 客户parytId
					// 从网银传来的参数
					applyMiddle.set("source", source);// 渠道来源
					applyMiddle.set("productType", productType);// 产品代码
					// applyMiddle.set("guarantyType", guarantyType);//
					// applyMiddle.set("amt", amt);//申请金额
					applyMiddle.set("loanUse", loanUse);// 贷款用途
					applyMiddle.set("repaymentType", repaymentType);//
					applyMiddle.set("payment", payment);//
					applyMiddle.set("applyXwTerm", applyXwTerm);//
					applyMiddle.set("applyDate", new SimpleDateFormat(
							"yyyy/MM/dd").parse(applyDate.substring(0, 4) + "/"
							+ applyDate.substring(4, 6) + "/"
							+ applyDate.substring(6, 8)));// 申请日期
					// applyMiddle.set("signPlace", signPlace);//签约地点 中间表无此字段
					applyMiddle.set("loanTurn", loanTurn);// 行业投向
					// applyMiddle.set("payWay", payWay);//资金支付方式中间表无此字段
					// applyMiddle.set("prepaymentPenalty",
					// prepaymentPenalty);//
					applyMiddle.set("wybcbl", wybcbl);//
					applyMiddle.set("zhmc", zhmc);//
					applyMiddle.set("zhzh", zhzh);//
					applyMiddle.set("zhkhjg", zhkhjg);//
					// applyMiddle.set("zhzt", zhzt);//账户状态 中间表无此字段
					/* 第一还款账户信息 */
					applyMiddle.set("firstZhMc", firstPayBackZhmc);//
					applyMiddle.set("firstZhZh", firstPayBackZhzh);//
					applyMiddle.set("firstZhKhjg", firstPayBackZhkhjg);//
					applyMiddle.set("firstZhStatus", firstPayBackZhstatus);//
					// 第二还款账户信息
					applyMiddle.set("secondZhMc", secondPayBackZhmc);//
					applyMiddle.set("secondZhZh", secondPayBackZhzh);//
					applyMiddle.set("secondZhKhjg", secondPayBackZhkhjg);//
					applyMiddle.set("secondZhStatus", secondPayBackZhstatus);//
					// 第三还款账户信息
					applyMiddle.set("thirdZhMc", thirdPayBackZhmc);//
					applyMiddle.set("thirdZhZh", thirdPayBackZhzh);//
					applyMiddle.set("thirdZhKhjg", thirdPayBackZhkhjg);//
					applyMiddle.set("thirdZhtStatus", thirdPayBackZhstatus);//

					// 保存中间表信息
					WdBaseService.saveApplyMiddle(applyMiddle);

					responseBody.setEcifPartyNum(ecifPartyNum);
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");

					log.info("----wd002网贷保存业务申请中间表信息结束----");

					// 调用征信服务
					P2pCreditImpl p2p = new P2pCreditImpl();
					p2p.p2pCreditReport(applyId, ecifPartyNum, partyId);

				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			log.info("WdtoLoanServiceJavaImpl--" + e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceJavaImpl--" + e.getMessage());
		}

		return rs;
	}

	@Bizlet("网银放款接口 ")
	public D003Response executeWYPayPro(D003Request request) {
		System.out.println("----调用网贷wd003接口开始----");
		D003Response response = new D003Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(request.getRequestHeader().getVersionNo());
		responseHeader.setReqSysCode(request.getRequestHeader().getReqSysCode());
		responseHeader.setReqSecCode(request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(request.getRequestHeader().getTxType());
		responseHeader.setTxMode(request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(request.getRequestHeader().getReqTime().substring(8, 14));
		responseHeader.setReqSeqNo(request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(request.getRequestHeader().getHmac());
		response.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		D003ResponseBody responseBody = new D003ResponseBody();
		response.setResTranHeader(resTranHeader);
		response.setResponseBody(responseBody);
		try {
			// 判断是否网银的请求方系统代码---00201
			if ("00201".equals(request.getRequestHeader().getReqSysCode())) {
				// 判断交易码
				if ("WD003".equals(request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", request.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", request.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", request.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", request.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", request.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", request.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);

					// 合同编号
					String contractNum = request.getRequestBody()
							.getContractNum();
					if ("".equals(contractNum) || null == contractNum) {
						response.getResTranHeader().setHRetCode("BBBBBBB");
						response.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "合同编号" + "}不能为空]");
						return response;
					}
					// 放款金额
					String loanAmt = request.getRequestBody().getLoanAmt();
					if ("".equals(loanAmt) || null == loanAmt) {
						response.getResTranHeader().setHRetCode("BBBBBBB");
						response.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "放款金额" + "}不能为空]");
						return response;
					}
					// 放款期限(单元月)
					String loanTerm = request.getRequestBody().getLoanTerm();
					if ("".equals(loanTerm) || null == loanTerm) {
						response.getResTranHeader().setHRetCode("BBBBBBB");
						response.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "放款期限" + "}不能为空]");
						return response;
					}

					// 根据合同编号查询合同信息
					HashMap<String, String> mapCon = new HashMap<String, String>();
					mapCon.put("contractNum", contractNum);// 合同编号
					Object[] contractInfos = DatabaseExt.queryByNamedSql(
							"default", "com.bos.pay.netquery.getContractInfo",
							mapCon);
					DataObject contractInfo = (DataObject) contractInfos[0];
					// 合同里存的机构号
					String orgNum = contractInfo.getString("ORGNUM");// 机构编号

					// 判断客户名下是否有未结清的利息或者逾期贷款 如果有则不允许放款
					HashMap<String, String> mapSum = new HashMap<String, String>();
					mapSum.put("contractNum", contractNum);
					Object[] summarys = DatabaseExt
							.queryByNamedSql(
									"default",
									"com.bos.pay.netquery.queryCustomerSummary",
									mapSum);
					if (summarys != null && summarys.length > 0) {
						response.getResTranHeader().setHRetCode("BBBBBBB");
						response.getResTranHeader().setHRetMsg(
								"交易失败[客户存在未结清的利息或逾期贷款]");
						return response;
					}

					// 单次提款金额不得低于最低放款金额
					if (new BigDecimal(loanAmt).compareTo(minAmt) < 0) {
						response.getResTranHeader().setHRetCode("BBBBBBB");
						response.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "放款金额" + "}不能小于1000元]");
						return response;
					}
					// 校验本次放款金额 是否超过本机构风险限额
					try {
						Map<String, Object> mapLimit = new HashMap<String, Object>();
						mapLimit.put("ORG_NUM", orgNum);// 放款机构
						mapLimit.put("PRODUCT_TYPE", PRODUCT_MSMD_CD);// 绵商秒贷
						mapLimit.put("AMT", loanAmt);// 放款金额
						new RiskLimitValid().valid(mapLimit);
					} catch (Exception e) {
						log.info("----wd003放款金额校验[失败]----本机构可用放款金额不足");
						response.getResTranHeader().setHRetCode("BBBBBBB");
						response.getResTranHeader().setHRetMsg(
								"交易失败[本机构可用放款金额不足]");
						return response;
					}

					log.info("wd003调用基础服务保存放款信息开始----");
					// 调用网贷基础服务 保存放款信息
					Map<String, Object> mapLoan = new HashMap<String, Object>();
					mapLoan.put("contractNum", contractNum);// contractNum
					mapLoan.put("loanAmt", loanAmt);// 放款金额
					mapLoan.put("loanTerm", loanTerm);// 放款期限
					mapLoan.put("orgNum", orgNum);// 机构编号
					mapLoan.put("beginDate", GitUtil.getBusiDate());// 开始日期
					// 放款
					HashMap<String, String> map = new WdBaseService()
							.saveLoan(mapLoan);

					String loanId = map.get("loanId");// 出账ID
					String loanNum = map.get("loanNum");// 放款编号
					log.info("----wd003调用基础服务保存放款信息结束----");
					// 校验放款表信息
					try {
						// 校验额度是否足够
						RuleService rs = new RuleService();
						Map<String, String> paramMap = new HashMap<String, String>();
						paramMap.put("loanId", loanId);

						List<MessageObj> msgList = rs.runRule("RLON_0017",
								paramMap);
						String msg = convertMsg(msgList);
						if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
							response.getResTranHeader().setHRetCode("BBBBBBB");
							response.getResTranHeader().setHRetMsg("合同可用额度不足");
							return response;
						}
						msgList = rs.runRule("RLON_0018", paramMap);
						msg = convertMsg(msgList);
						if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
							response.getResTranHeader().setHRetCode("BBBBBBB");
							response.getResTranHeader()
									.setHRetMsg("批复明细可用额度不足");
							return response;
						}
					} catch (RuleException e1) {
						e1.printStackTrace();
						throw new EOSException(e1.getMessage());
					}

					log.info("----wd003调用管理向计量中间表插入数据并检查----");
					// 放款数据检查
					LoanToLcs lcs = new LoanToLcs();
					lcs.dataInsertCheck(loanId);

					lcs.loanToLcs1(loanId);
					DataObject loanInfo = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanInfo");
					loanInfo.set("loanId", loanId);
					DatabaseUtil.expandEntity("default", loanInfo);
					LoanInfo li = new LoanInfo();
					li.createSummary(loanInfo);

					// 重算额度
					String partyId = loanInfo.getString("partyId");
					Map<String, String> map2 = new HashMap<String, String>();
					map2.put("partyId", partyId);
					DatabaseExt
							.executeNamedSql(
									"default",
									"com.bos.conApply.conApply.updateCreditLimit",
									map2);
					log.info("网贷wd003放款流程结束，调用接口------loanId=" + loanId
							+ "------->begin!");

					InterScheduler is = new InterScheduler();
					try {
						is.interChose(loanInfo);
					} catch (Exception e) {
						e.printStackTrace();
						throw new EOSException(e.getMessage());
					}

					response.getResTranHeader().setHRetCode("AAAAAAA");
					response.getResTranHeader().setHRetMsg("交易成功[放款成功]");
					response.getResponseBody().setLoanNum(loanNum);// 出账编号
					response.getResponseBody().setSummaryNum(loanNum);// 借据编号
				} else {
					response.getResTranHeader().setHRetCode("BBBBBBB");
					response.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				response.getResTranHeader().setHRetCode("BBBBBBB");
				response.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		}
		return response;
	}

	@Bizlet("网银还款接口 ")
	public D004Response executeWYAftPro(D004Request request) {
		System.out.println("----调用网贷wd004接口开始----");
		D004Response response = new D004Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(request.getRequestHeader().getVersionNo());
		responseHeader
				.setReqSysCode(request.getRequestHeader().getReqSysCode());
		responseHeader
				.setReqSecCode(request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(request.getRequestHeader().getTxType());
		responseHeader.setTxMode(request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(request.getRequestHeader().getReqTime()
				.substring(8, 14));
		responseHeader.setReqSeqNo(request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(request.getRequestHeader().getHmac());
		response.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		D004ResponseBody responseBody = new D004ResponseBody();
		response.setResTranHeader(resTranHeader);
		response.setResponseBody(responseBody);
		try {
			// 判断是否网银的请求方系统代码---00201
			if ("00201".equals(request.getRequestHeader().getReqSysCode())) {
				// 判断交易码
				if ("WD004".equals(request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", request.getRequestHeader()
							.getBrch());
					outInterTransLog.set("userCode", request.getRequestHeader()
							.getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					// 查询放款信息
					Map<String, String> maps = new HashMap<String, String>();
					maps.put("summaryNum", request.getRequestBody().getDueNum());
					Object[] objs1 = DatabaseExt.queryByNamedSql("default",
							"com.bos.pay.netquery.queryLoanInfo", maps);
					DataObject isJxhj = (DataObject) objs1[0];
					String loanOrg = isJxhj.getString("LOAN_ORG");// 出账金额
					String currencyCd = isJxhj.getString("CURRENCY_CD");// 出账币种
					String acctInd = isJxhj.getString("ZH");// 还款账号
					String zhmc = isJxhj.getString("ZHMC");// 账户名称
					String productType = isJxhj.getString("PRODUCT_TYPE");// 产品类型
					String opr = isJxhj.getString("USER_NUM");// 操作员
					Long lcsStan = Long.valueOf(BizNumGenerator.getLcsStan());// 交易流水号
					String partyId = isJxhj.getString("PARTY_ID");
					/**
					 * 调用核心账户查询接口
					 */
					CrmsMgrCallCoreProxy proxy = new CrmsMgrCallCoreImpl();
					if ("8".equals(acctInd.substring(4, 5))
							|| "9".equals(acctInd.substring(4, 5))) {
						IXD15AccountInfo req = new IXD15AccountInfo();
						req.setAcctNo(acctInd);
						req.setOrgNum(loanOrg);
						if ("CNY".equals(currencyCd)) {// 人民币
							req.setCurrCode("01");
						} else if ("GBP".equals(currencyCd)) {// 英镑
							req.setCurrCode("12");
						} else if ("HKD".equals(currencyCd)) {// 港币
							req.setCurrCode("13");
						} else if ("USD".equals(currencyCd)) {// 美元
							req.setCurrCode("14");
						} else if ("CHF".equals(currencyCd)) {// 瑞士法郎
							req.setCurrCode("15");
						} else if ("JPY".equals(currencyCd)) {// 日元
							req.setCurrCode("27");
						} else if ("CAD".equals(currencyCd)) {// 加拿大元
							req.setCurrCode("28");
						} else if ("AUD".equals(currencyCd)) {// 澳洲元
							req.setCurrCode("29");
						} else if ("SGD".equals(currencyCd)) {// 新加坡元
							req.setCurrCode("32");
						} else if ("EUR".equals(currencyCd)) {// 欧元
							req.setCurrCode("38");
						} else if ("MOP".equals(currencyCd)) {// 澳门元
							req.setCurrCode("81");
						}
						proxy.executeXD15(req);

					} else {
						OXD051_AccInfoQryReq req = new OXD051_AccInfoQryReq();
						req.setOrgNum(loanOrg);
						req.setQryType("1");
						req.setCustAcctNo(acctInd);
						if ("CNY".equals(currencyCd)) {// 人民币
							req.setCurrCode("01");
							req.setCashFlag("0");
						} else if ("GBP".equals(currencyCd)) {// 英镑
							req.setCurrCode("12");
							req.setCashFlag("1");
						} else if ("HKD".equals(currencyCd)) {// 港币
							req.setCurrCode("13");
							req.setCashFlag("1");
						} else if ("USD".equals(currencyCd)) {// 美元
							req.setCurrCode("14");
							req.setCashFlag("1");
						} else if ("CHF".equals(currencyCd)) {// 瑞士法郎
							req.setCurrCode("15");
							req.setCashFlag("1");
						} else if ("JPY".equals(currencyCd)) {// 日元
							req.setCurrCode("27");
							req.setCashFlag("1");
						} else if ("CAD".equals(currencyCd)) {// 加拿大元
							req.setCurrCode("28");
							req.setCashFlag("1");
						} else if ("AUD".equals(currencyCd)) {// 澳洲元
							req.setCurrCode("29");
							req.setCashFlag("1");
						} else if ("SGD".equals(currencyCd)) {// 新加坡元
							req.setCurrCode("32");
							req.setCashFlag("1");
						} else if ("EUR".equals(currencyCd)) {// 欧元
							req.setCurrCode("38");
							req.setCashFlag("1");
						} else if ("MOP".equals(currencyCd)) {// 澳门元
							req.setCurrCode("81");
							req.setCashFlag("1");
						}
						req.setQryPwd("1");
						OXD052_AccInfoQryRes rs = proxy.executeXD05(req);
						BigDecimal avalibAmt = new BigDecimal(rs
								.getOxd052ResBody().getAvailableAmt());
						BigDecimal zeAmt = new BigDecimal(request
								.getRequestBody().getPadUpAmt());
						if (zeAmt.doubleValue() > avalibAmt.doubleValue()) {
							response.getResTranHeader().setHRetCode("BBBBBBB");
							response.getResTranHeader().setHRetMsg(
									"还款金额大于账户可用余额！");
							return response;
						}
					}
					// 还款控制信息
					RepayControlInfRq vo = new RepayControlInfRq();
					BaseVO bvo = new BaseVO();
					bvo.setTranCod("T1202");
					bvo.setOpr(GitUtil.getCurrentUserId());
					bvo.setAut(GitUtil.getCurrentUserId());
					bvo.setAcsMethStan(Long.valueOf(BizNumGenerator
							.getLcsStan()));
					bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
					bvo.setTrnDep(loanOrg);
					bvo.setTranFrom("47");
					bvo.setOrigFrom("11000");
					bvo.setLegPerCod("9999");
					bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
					bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
					bvo.setDepCod(GitUtil.getBranchId());
					bvo.setOpnDep(loanOrg);
					vo.setDueNum(request.getRequestBody().getDueNum());// 借据编号
					vo.setTelNo(request.getRequestBody().getDueNum());// 通知书编号
					vo.setPayOrder(request.getRequestBody().getPayOrder());// 还款顺序
					// 还款账号
					vo.setPayPrimAcct(acctInd);
					vo.setPayPrimName(zhmc);
					vo.setPayOutItrFlg("1");// 归还未结计利息标志--后续修改
					vo.setPrinPlanFlg("0");// 下发新的还本计划标志
					vo.setPadUpAmt(new BigDecimal(request.getRequestBody()
							.getPadUpAmt()));// 还款金额
					vo.setBaseVO(bvo);
					Object[] params1 = new Object[1];
					params1[0] = vo;
					// 调用核算还款控制信息
					CrmsCallPlusProxy proxy1 = new CrmsCallPlusImpl();
					RepayControlInfRq rectol = proxy1.executeT1202(vo);
					if ("00000".equals(rectol.getBaseVO().getErrCod())) {
						RepaymentRq rqs = new RepaymentRq();
						rqs.setDueNum(vo.getDueNum());
						rqs.setTelNo(vo.getTelNo());
						rqs.setPayOutItrFlg(vo.getPayOutItrFlg());
						rqs.setProductType(productType);
						BaseVO bvo1 = vo.getBaseVO();
						bvo1.setTranCod("T1102");
						bvo1.setTranTimes("1");
						bvo1.setToCoreSys("0");
						bvo1.setAcsMethStan(Long.valueOf(BizNumGenerator
								.getLcsStan()));
						bvo1.setRcnStan(lcsStan);
						bvo1.setOpr(opr);
						rqs.setBaseVO(bvo1);
						params1[0] = rqs;
						ILogicComponent logicComponent = LogicComponentFactory
								.create("com.primeton.tsl.TransferDataManager");
						Object[] objs = null;
						objs = logicComponent.invoke("retPaymentEasyLcs",
								params1);
						RepaymentRq epaymentRq = (RepaymentRq) objs[0];
						BaseVO baseVO = epaymentRq.getBaseVO();
						String returnCode = (String) baseVO.getErrCod();
						if (!"00000".equals(returnCode)) {
							response.getResTranHeader().setHRetCode("BBBBBBB");
							response.getResTranHeader().setHRetMsg(
									baseVO.getErrMsg());
							return response;
						}
						DealAccount dealAccount = new DealAccount();
						dealAccount.singleSynch(request.getRequestBody()
								.getDueNum());
						// 重算额度
						Map<String, String> map2 = new HashMap<String, String>();
						map2.put("partyId", partyId);
						DatabaseExt.executeNamedSql("default",
								"com.bos.conApply.conApply.updateCreditLimit",
								map2);
						response.getResponseBody().setPayPrimAcct(
								epaymentRq.getPayPrimAcct());
						response.getResponseBody().setPayPrimName(
								epaymentRq.getPayPrimName());
						response.getResponseBody().setPadUpAmt(
								epaymentRq.getPadUpAmt().toString());
						response.getResponseBody().setPayOrder(
								epaymentRq.getPayOrder());
						response.getResponseBody().setRcvPrn(
								epaymentRq.getRcvPrn().toString());
						response.getResponseBody().setRcvNorItrIn(
								epaymentRq.getRcvNorItrIn().toString());
						response.getResponseBody().setRcvDftItrIn(
								epaymentRq.getRcvDftItrIn().toString());
						response.getResponseBody().setRcvPnsItrIn(
								epaymentRq.getRcvPnsItrIn().toString());
						response.getResponseBody().setPadUpPrn(
								epaymentRq.getPadUpPrn().toString());
						response.getResponseBody().setPadUpNorItrIn(
								epaymentRq.getPadUpNorItrIn().toString());
						response.getResponseBody().setPadUpDftItrIn(
								epaymentRq.getPadUpDftItrIn().toString());
						response.getResponseBody().setPadUpPnsItrIn(
								epaymentRq.getPadUpPnsItrIn().toString());
						response.getResponseBody().setPadUpPentIcm(
								epaymentRq.getPadUpPentIcm().toString());
						response.getResponseBody().setDueNum(
								epaymentRq.getDueNum());
						response.getResponseBody().setTelNo(
								epaymentRq.getTelNo());
						response.getResponseBody().setConNo(
								epaymentRq.getConNo());
						response.getResponseBody().setBegDate(
								epaymentRq.getBegDate());
						response.getResponseBody().setEndDate(
								epaymentRq.getEndDate());
						response.getResponseBody().setBrwName(
								epaymentRq.getBrwName());
						response.getResponseBody().setSts(epaymentRq.getSts());
					} else {
						response.getResTranHeader().setHRetCode("BBBBBBB");
						response.getResTranHeader().setHRetMsg(
								rectol.getBaseVO().getErrMsg());
						return response;
					}
					response.getResTranHeader().setHRetCode("AAAAAAA");
					response.getResTranHeader().setHRetMsg("交易成功[还款成功]");
				} else {
					response.getResTranHeader().setHRetCode("BBBBBBB");
					response.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				response.getResTranHeader().setHRetCode("BBBBBBB");
				response.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		}
		return response;
	}

	@Bizlet("查询额度信息 ")
	public D005Response executeCredInfo(D005Request request) {
		System.out.println("----调用wd005接口开始----");
		D005Response response = new D005Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(request.getRequestHeader().getVersionNo());
		responseHeader
				.setReqSysCode(request.getRequestHeader().getReqSysCode());
		responseHeader
				.setReqSecCode(request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(request.getRequestHeader().getTxType());
		responseHeader.setTxMode(request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(request.getRequestHeader().getReqTime()
				.substring(8, 14));
		responseHeader.setReqSeqNo(request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(request.getRequestHeader().getHmac());
		response.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		List<ConList> responseBody = new ArrayList<ConList>();

		response.setResTranHeader(resTranHeader);
		ConList responseBody1 = null;
		try {
			// 判断是否网银的请求方系统代码---00201
			if ("00201".equals(request.getRequestHeader().getReqSysCode())) {
				// 判断交易码
				if ("WD005".equals(request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", request.getRequestHeader()
							.getBrch());
					outInterTransLog.set("userCode", request.getRequestHeader()
							.getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);

					// 查询借据信息
					Map<String, String> maps = new HashMap<String, String>();
					maps.put("ecifPartyNum", request.getRequestBody()
							.getEcifPartyNum());
					Object[] objs1 = DatabaseExt.queryByNamedSql("default",
							"com.bos.pay.netquery.queryCredInfo", maps);
					if (objs1.length > 0) {
						for (int i = 0; i < objs1.length; i++) {
							responseBody1 = new ConList();
							DataObject isJxhj = (DataObject) objs1[i];
							responseBody1.setEcifPartyNum(isJxhj
									.getString("ECIFPARTYNUM"));
							responseBody1.setContractNum(isJxhj
									.getString("CONTRACTNUM"));
							responseBody1.setCustName(isJxhj
									.getString("CUSTNAME"));
							responseBody1.setTerm(isJxhj.getString("TERM"));
							responseBody1.setConStartDate(isJxhj
									.getString("CONSTARTDATE"));
							responseBody1.setConEndDate(isJxhj
									.getString("CONENDDATE"));
							responseBody1.setRepaymentType(isJxhj
									.getString("REPAYMENTTYPE"));
							responseBody1.setConStatus(isJxhj
									.getString("CONSTATUS"));
							responseBody1.setRate(isJxhj.getString("RATE"));// 利率
							responseBody1.setTotalLimit(isJxhj
									.getString("TOTALLIMIT"));
							responseBody1.setConUsedLimit(isJxhj
									.getString("CONUSEDLIMIT"));
							responseBody1.setAviLimit(isJxhj
									.getString("AVILIMIT"));
							responseBody1.setPayAccNo(isJxhj
									.getString("PAYACCNO"));// 还款账号
							responseBody.add(responseBody1);
						}
						response.setResponseBody(responseBody);
						response.getResTranHeader().setHRetCode("AAAAAAA");
						response.getResTranHeader().setHRetMsg("交易成功");
					} else {
						response.getResTranHeader().setHRetCode("AAAAAAA");
						response.getResTranHeader()
								.setHRetMsg("交易失败[未查询到额度信息]");
					}

				} else {
					response.getResTranHeader().setHRetCode("BBBBBBB");
					response.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				response.getResTranHeader().setHRetCode("BBBBBBB");
				response.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		}
		return response;
	}

	@Bizlet("贷款本息查询 ")
	public D006Response executeNorDftInfo(D006Request request) {
		System.out.println("----调用网贷wd006接口开始----");
		D006Response response = new D006Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(request.getRequestHeader().getVersionNo());
		responseHeader
				.setReqSysCode(request.getRequestHeader().getReqSysCode());
		responseHeader
				.setReqSecCode(request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(request.getRequestHeader().getTxType());
		responseHeader.setTxMode(request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(request.getRequestHeader().getReqTime()
				.substring(8, 14));
		responseHeader.setReqSeqNo(request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(request.getRequestHeader().getHmac());
		response.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		D006ResponseBody responseBody = new D006ResponseBody();
		response.setResTranHeader(resTranHeader);
		response.setResponseBody(responseBody);
		try {
			// 判断是否网银的请求方系统代码---00201
			if ("00201".equals(request.getRequestHeader().getReqSysCode())) {
				// 判断交易码
				if ("WD006".equals(request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", request.getRequestHeader()
							.getBrch());
					outInterTransLog.set("userCode", request.getRequestHeader()
							.getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					// 查询借据信息
					Map<String, String> maps = new HashMap<String, String>();
					maps.put("dueNum", request.getRequestBody().getDueNum());
					Object[] objs1 = DatabaseExt.queryByNamedSql("default",
							"com.bos.pay.netquery.queryLoanOrg", maps);
					DataObject obj = (DataObject) objs1[0];
					CrePayQueryRq crePayQueryRq = new CrePayQueryRq();
					CrmsCallPlusProxy proxy = new CrmsCallPlusImpl();
					BaseVO bvo = new BaseVO();
					bvo.setTranCod("T1410");
					bvo.setOpr(GitUtil.getCurrentUserId());
					bvo.setAut(GitUtil.getCurrentUserId());
					bvo.setAcsMethStan(Long.valueOf(BizNumGenerator
							.getLcsStan()));
					bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
					bvo.setTrnDep(obj.getString("LOAN_ORG"));
					bvo.setTranFrom("47");
					bvo.setOrigFrom("11000");
					bvo.setLegPerCod("9999");
					bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
					bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
					bvo.setDepCod(GitUtil.getBranchId());
					bvo.setOpnDep(obj.getString("LOAN_ORG"));
					crePayQueryRq.setBaseVO(bvo);
					crePayQueryRq.setDueNum(request.getRequestBody()
							.getDueNum());
					CrePayQueryRq hresponse = proxy.executeT1410(crePayQueryRq);
					if ("00000".equals(hresponse.getBaseVO().getErrCod())) {
						response.getResTranHeader().setHRetCode("AAAAAAA");
						response.getResTranHeader().setHRetMsg("交易成功");
						response.getResponseBody().setRcvPrn(
								hresponse.getRcvPrn().toString());
						response.getResponseBody().setResNor(
								hresponse.getResNor().toString());
						response.getResponseBody().setDftPrnBal(
								hresponse.getDftPrnBal().toString());
						response.getResponseBody().setRcvNorItrIn(
								hresponse.getRcvNorItrIn().toString());
						response.getResponseBody().setRcvDftItrIn(
								hresponse.getRcvDftItrIn().toString());
						response.getResponseBody().setRcvPnsItrIn(
								hresponse.getRcvPnsItrIn().toString());
						response.getResponseBody().setRcvCpdItrIn(
								hresponse.getRcvCpdItrIn().toString());
						response.getResponseBody().setAdjOtdPns(
								hresponse.getAdjOtdPns().toString());
						response.getResponseBody().setAdjOtdCpd(
								hresponse.getAdjOtdCpd().toString());
						response.getResponseBody().setCurrPrjPrn(
								hresponse.getCurrPrjPrn().toString());
						response.getResponseBody().setCurrPrjItr(
								hresponse.getCurrPrjItr().toString());
						response.getResponseBody().setPadUpAmt(
								hresponse.getPadUpAmt().toString());
						response.getResponseBody().setTotPrnItr(
								hresponse.getTotPrnItr().toString());
						response.getResponseBody().setDevaSts(
								hresponse.getDevaSts());
					} else {
						response.getResTranHeader().setHRetCode("BBBBBBB");
						response.getResTranHeader().setHRetMsg(
								hresponse.getBaseVO().getErrMsg());
					}
				} else {
					response.getResTranHeader().setHRetCode("BBBBBBB");
					response.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				response.getResTranHeader().setHRetCode("BBBBBBB");
				response.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		}
		return response;
	}

	@Bizlet("放款流水查询 ")
	public D007Response executePayProInfo(D007Request request) {
		System.out.println("----调用网贷wd007接口开始----");
		D007Response response = new D007Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(request.getRequestHeader().getVersionNo());
		responseHeader
				.setReqSysCode(request.getRequestHeader().getReqSysCode());
		responseHeader
				.setReqSecCode(request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(request.getRequestHeader().getTxType());
		responseHeader.setTxMode(request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(request.getRequestHeader().getReqTime()
				.substring(8, 14));
		responseHeader.setReqSeqNo(request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(request.getRequestHeader().getHmac());
		response.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		List<LoanList> responseBody = new ArrayList<LoanList>();
		LoanList responseBody1 = null;
		response.setResTranHeader(resTranHeader);
		try {
			// 判断是否网银的请求方系统代码---00201
			if ("00201".equals(request.getRequestHeader().getReqSysCode())) {
				// 判断交易码
				if ("WD007".equals(request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", request.getRequestHeader()
							.getBrch());
					outInterTransLog.set("userCode", request.getRequestHeader()
							.getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					// 查询借据信息
					Map<String, String> maps = new HashMap<String, String>();
					maps.put("dueNum", request.getRequestBody().getDueNum());
					// maps.put("begDate",
					// request.getRequestBody().getBegDate());
					// maps.put("endDate",
					// request.getRequestBody().getEndDate());
					Object[] objs1 = DatabaseExt.queryByNamedSql("default",
							"com.bos.pay.netquery.queryPayProInfo", maps);
					if (objs1.length > 0) {
						for (int i = 0; i < objs1.length; i++) {
							DataObject obj = (DataObject) objs1[i];
							responseBody1 = new LoanList();
							responseBody1.setLoanOrg(obj.getString("LOAN_ORG"));
							responseBody1.setDueNum(obj
									.getString("SUMMARY_NUM"));
							responseBody1.setCustName(obj
									.getString("PARTY_NAME"));
							responseBody1.setLoanAmt(obj.getString("LOAN_AMT"));
							responseBody1.setLoanBeginDate(obj
									.getString("BEGIN_DATE").substring(0, 10)
									.replace("-", "").trim());
							responseBody1.setLoanEndDate(obj
									.getString("END_DATE").substring(0, 10)
									.replace("-", "").trim());
							responseBody1.setNormalRate(obj
									.getString("YEAR_RATE"));
							responseBody1.setOverRate(obj
									.getString("OVER_RATE"));
							responseBody1.setPaybackWay(obj
									.getString("REPAY_TYPE"));
							responseBody.add(responseBody1);
						}
						response.setResponseBody(responseBody);
						response.getResTranHeader().setHRetCode("AAAAAAA");
						response.getResTranHeader().setHRetMsg("交易成功");
					} else {
						response.getResTranHeader().setHRetCode("AAAAAAA");
						response.getResTranHeader().setHRetMsg(
								"交易失败[未查询到放款流水信息]");
					}
				} else {
					response.getResTranHeader().setHRetCode("BBBBBBB");
					response.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				response.getResTranHeader().setHRetCode("BBBBBBB");
				response.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		}
		return response;
	}

	@Bizlet("还款流水查询 ")
	public D008Response executeAftProInfo(D008Request request) {
		System.out.println("----调用网贷wd008接口开始----");
		D008Response rs = new D008Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(request.getRequestHeader().getVersionNo());
		responseHeader
				.setReqSysCode(request.getRequestHeader().getReqSysCode());
		responseHeader
				.setReqSecCode(request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(request.getRequestHeader().getTxType());
		responseHeader.setTxMode(request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(request.getRequestHeader().getReqTime()
				.substring(8, 14));
		responseHeader.setReqSeqNo(request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		List<RepayLoanList> responseBody = new ArrayList<RepayLoanList>();
		;

		try {
			// 判断是否网银的请求方系统代码---00201
			if ("00201".equals(request.getRequestHeader().getReqSysCode())) {
				// 判断交易码
				if ("WD008".equals(request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", request.getRequestHeader()
							.getBrch());
					outInterTransLog.set("userCode", request.getRequestHeader()
							.getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);

					// 借据编号
					String dueNum = request.getRequestBody().getDueNum();
					if ("".equals(dueNum) || null == dueNum) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "借据编号" + "}不能为空]");
						return rs;
					}
					// 起始日期
					String begDate = request.getRequestBody().getBegDate();
					if ("".equals(begDate) || null == begDate) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "起始日期" + "}不能为空]");
						return rs;
					}
					// 终止日期
					String endDate = request.getRequestBody().getEndDate();
					if ("".equals(endDate) || null == endDate) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "终止日期" + "}不能为空]");
						return rs;
					}
					// 查询参数
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("dueNum", dueNum);
					map.put("begDate", begDate);
					map.put("endDate", endDate);

					Object[] rePayLoanInfos = DatabaseExt.queryByNamedSql(
							"aplus",
							"com.bos.pay.netquery.queryRepayLoanInList", map);

					if (rePayLoanInfos.length > 0) {
						for (int i = 0; i < rePayLoanInfos.length; i++) {
							DataObject info = (DataObject) rePayLoanInfos[i];
							RepayLoanList rePayInfo = new RepayLoanList();
							rePayInfo.setRepaymentDate(info
									.getString("REPAYMENTDATE"));// 还款日期
							rePayInfo.setOrgNum(info.getString("ORGNUM"));// 机构
							rePayInfo.setDueNum(info.getString("DUENUM"));//
							rePayInfo.setCustName(info.getString("CUSTNAME"));// 客户名称
							rePayInfo.setRepaymentAccount(info
									.getString("REPAYMENTACCOUNT"));// 还款账号
							rePayInfo.setRepaymentAccountName(info
									.getString("REPAYMENTACCOUNTNAME"));// 还款账号名称
							rePayInfo.setRepaymentType(info
									.getString("REPAYMENTTYPE"));// 还款类型
							rePayInfo.setLoanBeginDate(info
									.getString("LOANBEGINDATE"));// 贷款起期
							rePayInfo.setLoanEndDate(info
									.getString("LOANENDDATE"));// 贷款止期
							rePayInfo.setRealCapitalAmt(info
									.getBigDecimal("REALCAPITALAMT"));// 实收本金金额
							rePayInfo.setAdvanceCapitalAmt(info
									.getBigDecimal("ADVANCECAPITALAMT"));// 提前还本金额
							rePayInfo.setRealBnNorInterest(info
									.getBigDecimal("REALBNNORINTEREST"));// 实收表内正常利息金额
							rePayInfo.setRealBnDelInterest(info
									.getBigDecimal("REALBNDELINTEREST"));// 实收表内拖欠利息金额
							rePayInfo.setRealBnOverInterest(info
									.getBigDecimal("REALBNOVERINTEREST"));// 实收表内罚息
							rePayInfo.setRealBnComInterest(info
									.getBigDecimal("REALBNCOMINTEREST"));// 实收表内复利
							rePayInfo.setRealBwNorInterest(info
									.getBigDecimal("REALBWNORINTEREST"));// 实收表外正常利息金额
							rePayInfo.setRealBwDelInterest(info
									.getBigDecimal("REALBWDELINTEREST"));// 实收表外拖欠利息金额
							rePayInfo.setRealBwOverInterest(info
									.getBigDecimal("REALBWOVERINTEREST"));// 实收表外罚息
							rePayInfo.setRealBwComInterest(info
									.getBigDecimal("REALBWCOMINTEREST"));// 实收表外复利
							rePayInfo.setPenaltyForAdv(info
									.getBigDecimal("PENALTYFORADV"));// 提前还款违约金
							rePayInfo.setVerificationAmt(info
									.getBigDecimal("VERIFICATIONAMT"));// 核销收回本金
							rePayInfo.setVerificationInterest(info
									.getBigDecimal("VERIFICATIONINTEREST"));// 核销收回利息
							rePayInfo.setVerificationYjInterest(info
									.getBigDecimal("VERIFICATIONYJINTEREST"));// 核销收回应计利息
							responseBody.add(rePayInfo);// 添加到集合
						}
					}

					rs.setResponseBody(responseBody);
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");

				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		}
		return rs;
	}

	@Bizlet("查询借据信息 ")
	public D009Response executeSummaryInfo(D009Request request) {
		System.out.println("----调用网贷wd009接口开始----");
		D009Response response = new D009Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(request.getRequestHeader().getVersionNo());
		responseHeader
				.setReqSysCode(request.getRequestHeader().getReqSysCode());
		responseHeader
				.setReqSecCode(request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(request.getRequestHeader().getTxType());
		responseHeader.setTxMode(request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(request.getRequestHeader().getReqTime()
				.substring(8, 14));
		responseHeader.setReqSeqNo(request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(request.getRequestHeader().getHmac());
		response.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		List<SummaryList> responseBody = new ArrayList<SummaryList>();
		response.setResTranHeader(resTranHeader);
		SummaryList responseBody1 = null;
		try {
			// 判断是否网银的请求方系统代码---00201
			if ("00201".equals(request.getRequestHeader().getReqSysCode())) {
				// 判断交易码
				if ("WD009".equals(request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", request.getRequestHeader()
							.getBrch());
					outInterTransLog.set("userCode", request.getRequestHeader()
							.getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);

					// 查询借据信息
					Map<String, String> maps = new HashMap<String, String>();
					if ((null != request.getRequestBody().getContractNum() && request
							.getRequestBody().getContractNum().length() > 15)
							|| (null != request.getRequestBody()
									.getEcifPartyNum() && request
									.getRequestBody().getEcifPartyNum()
									.length() > 11)) {
						maps.put("contractNum", request.getRequestBody()
								.getContractNum());
						maps.put("ecifPartyNum", request.getRequestBody()
								.getEcifPartyNum());

						Object[] objs1 = DatabaseExt.queryByNamedSql("default",
								"com.bos.pay.netquery.querySummaryInfo", maps);
						for (int i = 0; i < objs1.length; i++) {
							responseBody1 = new SummaryList();
							DataObject isJxhj = (DataObject) objs1[i];
							String loanId = isJxhj.getString("LOAN_ID");
							responseBody1.setEcifPartyNum(isJxhj
									.getString("ECIF_PARTY_NUM"));
							responseBody1.setContractNum(isJxhj
									.getString("CONTRACT_NUM"));
							responseBody1.setCustName(isJxhj
									.getString("PARTY_NAME"));
							responseBody1.setBizNum(isJxhj
									.getString("SUMMARY_NUM"));
							responseBody1.setLoanAmt(isJxhj
									.getString("LOAN_AMT"));
							responseBody1.setLoanBalance(isJxhj
									.getString("JJYE"));
							responseBody1.setLoanStartDate(isJxhj.getString(
									"BEGIN_DATE").substring(0, 10));
							responseBody1.setLoanEndDate(isJxhj.getString(
									"END_DATE").substring(0, 10));
							responseBody1.setLoanStatus(isJxhj
									.getString("SUMMARY_STATUS_CD"));
							Object[] objs2 = DatabaseExt.queryByNamedSql(
									"default",
									"com.bos.pay.netquery.queryAccountInfo",
									loanId);
							DataObject isJxhj2 = (DataObject) objs2[0];
							String zh = isJxhj2.getString("ZH");
							String[] zhs = zh.split(",");
							if (zhs.length > 3) {
								response.getResTranHeader().setHRetCode(
										"BBBBBBB");
								response.getResTranHeader().setHRetMsg(
										"系统错误，请联系系统管理员");
								response.getResponseBody().add(responseBody1);
								return response;
							} else {
								if (zhs.length == 3) {
									responseBody1.setFirstPayBackZhmc(zhs[0]);
									responseBody1.setSecondPayBackZhmc(zhs[1]);
									responseBody1.setThirdPayBackZhzh(zhs[2]);
								} else if (zhs.length == 2) {
									responseBody1.setFirstPayBackZhmc(zhs[0]);
									responseBody1.setSecondPayBackZhmc(zhs[1]);
								} else if (zhs.length == 1) {
									responseBody1.setFirstPayBackZhmc(zhs[0]);
								}
							}
							responseBody.add(responseBody1);
						}
						response.setResponseBody(responseBody);
						response.getResTranHeader().setHRetCode("AAAAAAA");
						response.getResTranHeader().setHRetMsg("交易成功");
					} else {
						response.getResTranHeader().setHRetCode("BBBBBBB");
						response.getResTranHeader().setHRetMsg(
								"交易失败[请输入合同编号或客户编号]");
					}
				} else {
					response.getResTranHeader().setHRetCode("BBBBBBB");
					response.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				response.getResTranHeader().setHRetCode("BBBBBBB");
				response.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			response.getResTranHeader().setHRetCode("BBBBBBB");
			response.getResTranHeader().setHRetMsg(
					"交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceImpl--" + e.getMessage());
		}
		return response;
	}

	/**
	 * 客户申请状态查询
	 * 
	 * @param d010Request
	 * @return 查询该客户下某种申请状态的信息
	 */

	@Bizlet("")
	public D010Response executeD010(D010Request d010Request) {
		System.out.println("----调用网贷wd010接口开始----");
		D010Response rs = new D010Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(d010Request.getRequestHeader()
				.getVersionNo());
		responseHeader.setReqSysCode(d010Request.getRequestHeader()
				.getReqSysCode());
		responseHeader.setReqSecCode(d010Request.getRequestHeader()
				.getReqSecCode());
		responseHeader.setTxType(d010Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(d010Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(d010Request.getRequestHeader().getReqDate());
		if (d010Request.getRequestHeader().getReqTime() != null
				&& !d010Request.getRequestHeader().getReqTime().equals("")) {
			responseHeader.setReqTime(d010Request.getRequestHeader()
					.getReqTime().substring(8, 14));
		}
		responseHeader
				.setReqSeqNo(d010Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader
				.setFileHMac(d010Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(d010Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		List<BizInfoList> responseBody = new ArrayList<BizInfoList>();

		try {
			if ("00201".equals(d010Request.getRequestHeader().getReqSysCode())) {// 请求方系统代码
				if ("WD010".equals(d010Request.getReqTranHeader().getHTxnCd())) {// 交易码
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", d010Request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", d010Request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", d010Request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", d010Request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", d010Request
							.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", d010Request
							.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);

					// ecif客户编号
					String ecifPartyNum = d010Request.getRequestBody()
							.getEcifPartyNum();// ecif客户编号
					if ("".equals(ecifPartyNum) || null == ecifPartyNum) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "ecif客户编号" + "}不能为空]");
						return rs;
					}

					// applyStatus申请状态(可以为空)
					String applyStatus = d010Request.getRequestBody()
							.getApplyStatus();// applyStatus申请状态

					Map<String, String> map = new HashMap<String, String>();
					log.info("----wd010查询申请状态信息开始----");
					map.put("ecifPartyNum", ecifPartyNum);// 客户编号
					map.put("applyStatus", applyStatus);// 申请状态
					Object[] whiteInfos = DatabaseExt
							.queryByNamedSql(
									"default",
									"com.bos.pay.wdBaseService.queryWhiteApplyStatusInfo",
									map);
					if (whiteInfos != null && whiteInfos.length != 0) {
						for (int i = 0; i < whiteInfos.length; i++) {
							DataObject info = (DataObject) whiteInfos[i];
							BizInfoList bizInfoList = new BizInfoList();
							bizInfoList.setEcifPartyNum(info
									.getString("ECIFPARTYNUM"));
							bizInfoList.setApplyId(info.getString("APPLYID"));
							bizInfoList.setUserName(info.getString("USERNAME"));
							bizInfoList.setOrgName(info.getString("ORGNAME"));
							bizInfoList.setApplyDate(info
									.getString("APPLYDATE"));
							bizInfoList.setApplyRate(info
									.getBigDecimal("APPLYRATE"));
							bizInfoList.setApplyXwTerm(info
									.getString("APPLYXWTERM"));
							bizInfoList.setApplyAmt(info
									.getBigDecimal("APPLYAMT"));
							bizInfoList.setRepaymentType(info
									.getString("REPAYMENTTYPE"));
							bizInfoList.setZhzh(info.getString("ZHZH"));
							bizInfoList.setFirstPayBackZhzh(info
									.getString("FIRSTPAYBACKZHZH"));
							bizInfoList.setSecondPayBackZhzh(info
									.getString("SECONDPAYBACKZHZH"));
							bizInfoList.setThirdPayBackZhzh(info
									.getString("THIRDPAYBACKZHZH"));
							bizInfoList.setPhoneNum(info.getString("PHONENUM"));
							bizInfoList.setLoanUse(info.getString("LOANUSE"));
							bizInfoList.setPayment(info.getString("PAYMENT"));
							bizInfoList.setApplyStatus(info
									.getString("APPLYSTATUS"));
							bizInfoList.setErrorMsg(info.getString("ERRORMSG"));
							responseBody.add(bizInfoList);
						}
					}
					rs.setResponseBody(responseBody);
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");

					log.info("----wd010查询申请状态信息结束----");
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			log.info("WdtoLoanServiceJavaImpl--" + e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceJavaImpl--" + e.getMessage());
		}

		return rs;
	}

	/**
	 * 
	 * @param request
	 * @return wd011还款账号变更接口
	 */
	@Bizlet("")
	public D011Response executeD011(D011Request request) {
		log.info("----调用网贷wd011接口开始----");
		D011Response rs = new D011Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(request.getRequestHeader().getVersionNo());
		responseHeader
				.setReqSysCode(request.getRequestHeader().getReqSysCode());
		responseHeader
				.setReqSecCode(request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(request.getRequestHeader().getTxType());
		responseHeader.setTxMode(request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(request.getRequestHeader().getReqDate());
		if (request.getRequestHeader().getReqTime() != null
				&& !request.getRequestHeader().getReqTime().equals("")) {
			responseHeader.setReqTime(request.getRequestHeader().getReqTime()
					.substring(8, 14));
		}
		responseHeader.setReqSeqNo(request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		D011ResponseBody responseBody = new D011ResponseBody();

		try {
			if ("00201".equals(request.getRequestHeader().getReqSysCode())) {// 请求方系统代码
				if ("WD011".equals(request.getReqTranHeader().getHTxnCd())) {// 交易码
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil
							.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", request
							.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", request
							.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", request
							.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", request
							.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", request.getRequestHeader()
							.getBrch());
					outInterTransLog.set("userCode", request.getRequestHeader()
							.getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);

					// 借据编号
					String summaryNum = request.getRequestBody()
							.getSummaryNum();
					if ("".equals(summaryNum) || summaryNum == null) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "借据编号" + "}不能为空]");
						return rs;
					}
					// 新第一还款账号
					String payPrimAcct = request.getRequestBody()
							.getPayPrimAcct();
					if ("".equals(payPrimAcct) || payPrimAcct == null) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "新第一还款账号" + "}不能为空]");
						return rs;
					}
					// 新第一还款账号名称
					String payPrimName = request.getRequestBody()
							.getPayPrimName();
					if ("".equals(payPrimName) || payPrimName == null) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[参数{" + "新第一还款账号名称" + "}不能为空]");
						return rs;
					}

					// 调用核心校验账户信息(网贷放款和第一还款账户相同，因此只需要校验这一个)
					Object[] params = new Object[3];
					String acctInd = payPrimAcct;// 新第一还款账号
					String currCode = "01";// 币种人民币
					String cashFlag = "0";// 0钞户 1汇户
					params[0] = acctInd;// 账号
					params[1] = currCode;//
					params[2] = cashFlag;//
					Object[] accInfo = new Object[3];

					ILogicComponent logicComponet = LogicComponentFactory
							.create("com.bos.accInfo.accInfo");
					accInfo = logicComponet.invoke("queryAcc1", params);
					// 核心查询失败
					if (!"AAAAAAA".equals(accInfo[2])) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[" + payPrimAcct + "]账号不存在或已销户");
						return rs;
					}
					// 核心返回的账户信息
					OXD052_AccInfoQryRes accRes = (OXD052_AccInfoQryRes) accInfo[0];
					String accName = accRes.getOxd052ResBody().getCustName();// 账户名称
					if (!payPrimName.equals(accName)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg(
								"交易失败[" + payPrimName + "]账户名称输入有误");
						return rs;
					}

					log.info("----wd011还款账号变更开始----");

					String bus_date = GitUtil.getBusiDateYYYYMMDD();// 当前营业日期
					// 借据
					DataObject summary = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					summary.set("summaryNum", summaryNum);
					DatabaseUtil.expandEntityByTemplate("default", summary,
							summary);
					// 放款
					DataObject loan = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanInfo");
					loan.set("loanId", summary.getString("loanId"));
					DatabaseUtil.expandEntityByTemplate("default", loan, loan);
					// 放款账户
					DataObject zh = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanZh");
					zh.set("loanId", loan.getString("loanId"));
					zh.set("zhlx", "1");
					DatabaseUtil.expandEntityByTemplate("default", zh, zh);

					Object[] params1 = new Object[2];
					params1[0] = "MA1_1401";

					RepayAccChangeRq vo = new RepayAccChangeRq();
					BaseVO bvo = new BaseVO();
					bvo.setTranCod("T1402");// 交易代码
					bvo.setOpr(GitUtil.getCurrentUserId());// 操作员
					bvo.setAut(GitUtil.getCurrentUserId());// 授权员
					bvo.setAcsMethStan(Long.valueOf(BizNumGenerator
							.getLcsStan()));// 接入系统流水号
					bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));// 对账流水号
					bvo.setTrnDep(loan.getString("loanOrg"));// 交易机构，会校验
					bvo.setTranFrom("47");
					bvo.setTranTimes("1");// 交易次数标志 1次交易后填2，二次交易后填3
					bvo.setToCoreSys("0");// 交易是否转发核心系统标志 0=不转发；1=向核心系统转发
					bvo.setOpnDep(loan.getString("loanOrg"));// 贷款开户机构
					bvo.setAccSysDate(bus_date);// 营业日期 检查该机构在机构表中是否存在
					bvo.setTranDate(bus_date);
					bvo.setOrigFrom("11000");
					bvo.setLegPerCod("9999");
					vo.setDueNum(summary.getString("summaryNum"));// //借据编号
					vo.setPayPrimAcct(payPrimAcct);// 还款账号
					vo.setPayPrimName(payPrimName);// 还款账户名称
					vo.setBaseVO(bvo);
					params1[1] = vo;
					ILogicComponent logicComponent = LogicComponentFactory
							.create("com.primeton.tsl.TransferDataManager");
					Object[] objs = null;
					objs = logicComponent.invoke("newDataInsertCheck", params1);
					DataObject vo1 = (DataObject) objs[0];
					BaseVO baseVO = (BaseVO) vo1.get("baseVO");
					String returnCode = (String) baseVO.getErrCod();
					// 还款账号变更不成功抛出异常
					if (!"00000".equals(returnCode)) {
						throw new EOSException(baseVO.getErrMsg());
					}

					// 核算账号变更成功 更新放款账户信息表的账号
					DataObject template = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanZh");
					DataObject loanZh = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanZh");
					template.set("loanId", loan.getString("loanId"));// 放款账号loanId
					template.set("zhlx", "1");// 账户类型(第一还款账号)
					loanZh.set("zh", payPrimAcct);// 更新第一还款账号
					DatabaseUtil.updateEntityByTemplate("default", loanZh,
							template);

					rs.setResponseBody(responseBody);
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");

					log.info("----wd011还款账号变更结束----");
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			log.info("WdtoLoanServiceJavaImpl--" + e.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WdtoLoanServiceJavaImpl--" + e.getMessage());
		}

		return rs;

	}

	private String convertMsg(List<MessageObj> msgList) {
		StringBuffer sf = new StringBuffer();
		if (msgList != null && !msgList.isEmpty()) {
			for (int i = 0; i < msgList.size(); i++) {
				MessageObj t = msgList.get(i);
				if (EngineConstants.RULE_LEVEL_ERROR.equals(t.getMessageType())) {
					sf.append("[(" + (i + 1) + "):" + t.getCode() + ","
							+ t.getMessageInfo() + "]");
				}
			}
		}
		if (sf.length() > 0) {
			return sf.toString();
		}
		return "true";
	}

	// 返回的头文件信息
	private void responseHeader(D002Response rs, String rsInfo) {
		rs.getResTranHeader().setHRetCode("BBBBBBB");
		rs.getResTranHeader().setHRetMsg("交易失败[参数{" + rsInfo + "}不能为空]");
	}
}
