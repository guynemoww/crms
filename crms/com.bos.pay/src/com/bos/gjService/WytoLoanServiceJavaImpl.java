package com.bos.gjService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.osoa.sca.annotations.Remotable;

import com.bos.bizApply.BizProcess;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.mgrcore.client.DateTools;
import commonj.sdo.DataObject;

@Bizlet("")
@Remotable
public class WytoLoanServiceJavaImpl {
	private TraceLogger log = new TraceLogger(GjtoLoanServiceImpl.class);

	public static List<String> list = new ArrayList<String>();// 对私产品
	public static List<String> list1 = new ArrayList<String>();// 担保方式
	public static List<String> list2 = new ArrayList<String>();// 还款方式
	static {
		list.add("02001004");// 个人专项机械设备按揭贷款
		list.add("02001016");// 下岗失业人员小额担保贷款
		list.add("02003012");// 个人汽车消费贷款
		list.add("02003013");// 绵商秒贷
		list.add("02003010");// 白金贷
		list.add("02003006");// 员工信用消费贷款
		list.add("02003007");// 小额信用消费贷款
		list.add("02003008");// 个人助学贷款
		list.add("02003009");// 失地农民养老保险贷款
		list.add("02005001");// 公积金委托贷款
		list.add("02005002");// 公积金委托贷款
		list.add("02001001");// 个人创业贷款
		list.add("02001002");// 个人经营性物业抵押贷款
		list.add("02001003");// 扶贫开发贷
		list.add("02001013");// 快捷贷
		list.add("02001005");// 快帮贷
		list.add("02001014");// 链链贷
		list.add("02001007");// 互助贷
		list.add("02001008");// 城乡妇女创业贷
		list.add("02002003");// 二手房按揭贷款
		list.add("02002004");// 个人商用房按揭贷款
		list.add("02002005");// 个人住房按揭贷款
		list.add("02003001");// 个人综合消费贷款
		list.add("02003002");// 公积金参缴职工专享消费贷款
		list.add("02003011");// 购房客户专享消费贷款
		list.add("02003004");// 个人代发工资账户质押消费贷款
		list.add("02003005");// 公务员个人信用消费贷款
		list.add("02001040");// 平武个人创业贷款
		list.add("02001041");// 平武快捷贷
		list.add("02001042");// 平武农担直通贷
		list.add("02001043");// 平武互助贷
		list.add("02001044");// 平武个人综合消费贷款
		list.add("02001045");// 平武小额信用消费贷款

		list1.add("01");// 信用
		list1.add("02");// 抵押
		list1.add("03");// 质押
		list1.add("04");// 保证
		list1.add("05");// 保证金

		list2.add("0100");// 等额本金
		list2.add("0200");// 等额本息
		list2.add("0300");// 阶段性等额本金
		list2.add("0400");// 阶段性等额本息
		list2.add("1100");// 按周期还息到期一次还本
		list2.add("1200");// 到期一次性还本付息
		list2.add("1300");// 按周期还息任意还本
		list2.add("1400");// 按周期还息按还本计划表还本
		//list2.add("1410");// 按还本计划表还息按还本计划表还本
		//list2.add("1500");// 利随本清
		//list2.add("1700");// 等本等息
		//list2.add("2100");// 预收息
	}

	@Bizlet("")
	public WY001Response executeWY001(WY001Request request) {
		System.out.println("网银调用INTERFACE---WY001。。。");
		WY001Response rs = new WY001Response();
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
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		WY001ResponseBody responseBody = new WY001ResponseBody();
		rs.setResponseBody(responseBody);
		try {
			// 判断是否网银的请求方系统代码---00201
			if ("00201".equals(request.getRequestHeader().getReqSysCode())) {
				// 判断交易码---WY001
				if ("WY001".equals(request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", request.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", request.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", request.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", request.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", request.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", request.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					/**
					 * 参数校验与参数获取
					 */
					// 参数校验---ECIF客户编号
					String ecifPartyNum = request.getRequestBody().getEcifPartyNum();
					if (null == ecifPartyNum || "".equals(ecifPartyNum)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{客户编号}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{客户编号}不能为空]");
						return rs;
					}
					// 查询客户信息
					DataObject tbCsmParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
					tbCsmParty.set("ecifPartyNum", ecifPartyNum);
					DatabaseUtil.expandEntityByTemplate("default", tbCsmParty, tbCsmParty);
					if (tbCsmParty.get("partyId") == null) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到客户号[" + ecifPartyNum + "]对应的客户信息]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[未查询到客户号[" + ecifPartyNum + "]对应的客户信息]");
						return rs;
					}
					
					String partyId = (String) tbCsmParty.get("partyId");
					
					// 查询客户信息---客戶管戶信息
					DataObject tbCsmManagementTeam = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmManagementTeam");
					tbCsmManagementTeam.set("partyId", partyId);
					DatabaseUtil.expandEntityByTemplate("default", tbCsmManagementTeam, tbCsmManagementTeam);
					if (tbCsmManagementTeam.get("managementTeamId") == null) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到客户号[" + ecifPartyNum + "]对应的客户管戶信息]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[未查询到客户号[" + ecifPartyNum + "]对应的客户管戶信息]");
						return rs;
					}
					
					// 参数校验---客户名称
					String partyName = request.getRequestBody().getPartyName();
					if (null == partyName || "".equals(partyName)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{客户名称}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{客户名称}不能为空]");
						return rs;
					}
					// 参数校验---申请日期
					String applyDate = request.getRequestBody().getApplyDate();
					if (null == applyDate || "".equals(applyDate)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{申请日期}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{申请日期}不能为空]");
						return rs;
					}
					// 参数校验---业务性质04(对私单笔业务)
					String bizType = (null == request.getRequestBody().getBizType() || "".equals(request.getRequestBody().getBizType())) ? "04" : request.getRequestBody().getBizType();
					if (null == bizType || "".equals(bizType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{业务性质}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{业务性质}不能为空]");
						return rs;
					}
					if (!("04").equals(bizType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{业务性质}错误，个贷单笔业务代码值为：04]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{业务性质}错误，个贷单笔业务代码值为：04]");
						return rs;
					}
					// 参数校验---业务发生性质01(正常)
					String bizHappenType = (null == request.getRequestBody().getBizHappenType() || "".equals(request.getRequestBody().getBizHappenType())) ? "01" : request.getRequestBody().getBizHappenType();
					if (null == bizHappenType || "".equals(bizHappenType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{业务发生性质}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{业务发生性质}不能为空]");
						return rs;
					}
					if (!("01").equals(bizHappenType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{业务发生性质}错误，个贷单笔业务代码值为：01]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{业务发生性质}错误，个贷单笔业务代码值为：01]");
						return rs;
					}
					// 参数校验---申报模式01(常规业务)
					String applyModeType = request.getRequestBody().getApplyModeType();
					if (null == applyModeType || "".equals(applyModeType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{申报模式}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{申报模式}不能为空]");
						return rs;
					}
					if (!("01").equals(applyModeType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{申报模式}错误，个贷单笔业务代码值为：01]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{申报模式}错误，个贷单笔业务代码值为：01]");
						return rs;
					}
					// 参数校验---业务品种以及业务品种是否在个贷产品中
					String productType = request.getRequestBody().getProductType();
					if (null == productType || "".equals(productType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[业务品种不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[业务品种不能为空]");
						return rs;
					}
					if (!list.contains(productType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[业务品种代码" + productType + "错误，请验证该业务品种是否属于个贷产品]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[业务品种代码" + productType + "错误，请验证该业务品种是否属于个贷产品]");
						return rs;
					}
					// 参数校验---担保方式以及担保方式是不是在范围内
					String guarantyType = request.getRequestBody().getGuarantyType();
					if (null == guarantyType || "".equals(guarantyType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{担保方式}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{担保方式}不能为空]");
						return rs;
					}
					if (!list1.contains(guarantyType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[担保方式代码" + guarantyType + "错误，请验证该担保方式是否属于个贷产品]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[担保方式代码" + guarantyType + "错误，请验证该担保方式是否属于个贷产品]");
						return rs;
					}
					// 参数校验---申请金额
					BigDecimal detailAmt = request.getRequestBody().getDetailAmt();
					if (null == detailAmt) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{申请金额}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{申请金额}不能为空]");
						return rs;
					}
					// 参数校验---申请期限
					BigDecimal applyXwTerm = request.getRequestBody().getApplyXwTerm();
					if (null == applyXwTerm) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{申请期限}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{申请期限}不能为空]");
						return rs;
					}
					// 参数校验---币种 只能是人民币
					String currencyCd = request.getRequestBody().getCurrencyCd();
					if (null == currencyCd || "".equals(currencyCd)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{业务币种}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{业务币种}不能为空]");
						return rs;
					}
					if (!"CNY".equals(currencyCd)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{业务币种}只支持人民币]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{业务币种}只支持人民币]");
						return rs;
					}
					// 参数校验---还款方式
					String repaymentType = request.getRequestBody().getRepaymentType();
					if (null == repaymentType || "".equals(repaymentType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{还款方式}不能为空]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[参数{还款方式}不能为空]");
						return rs;
					}
					if (!list2.contains(repaymentType)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[还款方式代码" + repaymentType + "错误，请验证该还款方式是否属于个贷产品]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[还款方式代码" + repaymentType + "错误，请验证该还款方式是否属于个贷产品]");
						return rs;
					}
					
					
					String loanUse = request.getRequestBody().getLoanUse();// 贷款用途
					String payment = request.getRequestBody().getPayment();// 还款来源
					/**
					 * 开始业务处理
					 */
					// 获取业务编号
					String bizNum = BizNumGenerator.getBizNum("SEQ_BIZ_PF");
					if (null == bizNum || "".equals(bizNum)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[信贷系统生成业务编号失败]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败[信贷系统生成业务编号失败]");
						return rs;
					}
					// 业务基本信
					DataObject tbBizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					tbBizApply.set("partyId", partyId);
					tbBizApply.set("bizHappenType", bizHappenType);// 业务发生性质
					tbBizApply.set("bizNum", "PF" + bizNum);
					tbBizApply.set("statusType", "01");
					tbBizApply.set("userNum", tbCsmManagementTeam.get("userNum"));
					tbBizApply.set("orgNum", tbCsmManagementTeam.get("orgNum"));
					tbBizApply.set("applyDate", sdf.parse(applyDate));
					tbBizApply.set("productType", productType);
					tbBizApply.set("isGreenLoan", "0");
					tbBizApply.set("loanType", "0");
					tbBizApply.set("bizHappenNature", "01");
					tbBizApply.set("applyModeType", applyModeType);
					tbBizApply.set("isBankTeamLoan", "0");
					tbBizApply.set("createTime", GitUtil.getBusiDate());
					tbBizApply.set("updateTime", GitUtil.getBusiDate());
					tbBizApply.set("bizType", bizType);
					DatabaseUtil.saveEntity("default", tbBizApply);
					String applyId = tbBizApply.getString("applyId");

					// 保存申请基本信息
					DataObject tbBizAmountApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
					tbBizAmountApply.set("applyId", applyId);
					tbBizAmountApply.set("partyId", partyId);
					tbBizAmountApply.set("guarantyType", guarantyType);
					tbBizAmountApply.set("mainGuarantyType", guarantyType);
					tbBizAmountApply.set("createTime", GitUtil.getBusiDate());
					tbBizAmountApply.set("updateTime", GitUtil.getBusiDate());
					tbBizAmountApply.set("currencyCd", currencyCd);
					tbBizAmountApply.set("creditAmount", detailAmt);
					tbBizAmountApply.set("creditTerm", applyXwTerm.intValue());
					tbBizAmountApply.set("cycleUnit", "04");//默认期限单位:月
					DatabaseUtil.saveEntity("default", tbBizAmountApply);
					String amountId = tbBizAmountApply.getString("amountId");

					final String userId = tbCsmManagementTeam.getString("userNum");
					DataObject omEmployee = DataObjectUtil.createDataObject("com.bos.utp.dataset.organization.OmEmployee");
					omEmployee.set("userid", userId);
					DatabaseUtil.expandEntityByTemplate("default", omEmployee, omEmployee);
					final String userName = omEmployee.getString("empname");
					final String orgCode = tbCsmManagementTeam.getString("orgNum");
					DataObject omOrganization = DataObjectUtil.createDataObject("com.bos.utp.dataset.organization.OmOrganization");
					omOrganization.set("orgcode", orgCode);
					DatabaseUtil.expandEntityByTemplate("default", omOrganization, omOrganization);
					final String orgName = omOrganization.getString("orgname");
					final String orgLevel = omOrganization.getString("orglevel");
					
					//岗位信息
					DataObject OmPosition = DataObjectUtil.createDataObject("com.bos.utp.dataset.organization.OmPosition");
					OmPosition.set("posicode","P1001");
					DatabaseUtil.expandEntityByTemplate("default", OmPosition,OmPosition);
					DataObject OmEmpposition = DataObjectUtil.createDataObject("com.bos.utp.dataset.organization.OmEmpposition");
					OmEmpposition.set("orgid", omOrganization.get("orgid"));
					OmEmpposition.set("empid", omEmployee.get("empid"));
					OmEmpposition.set("positionid", omEmployee.get("positionid"));
					DatabaseUtil.expandEntityByTemplate("default", OmEmpposition,OmEmpposition);
					if(null==OmEmpposition.get("empposid")||"".equals(OmEmpposition.get("empposid"))){
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败["+partyName+"所属客户经理:"+userName+"没有个贷业务流程发起权限]");
						rs.getResponseBody().setStatus("11111");
						rs.getResponseBody().setMsg("交易失败["+partyName+"所属客户经理:"+userName+"没有个贷业务流程发起权限]");
						return rs;
					}
				
					
					final String positionCode = "P1001";
					// 创建流程
					BizProcess bizProcess = new BizProcess(){
						public Object[] createProcess(com.bos.bizApply.ProcessParam param) throws Throwable {

							Object[] params = {userId ,userName,orgCode,orgName,param.getTemplateName(),param.getRelaMap() , param.getModelType(),orgLevel,positionCode};
							ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.bps.service.WorkFlowService");
							// 发起业务流程
							return logicComponent.invoke("createProcessForWy", params);
						};
					};
					String processId = bizProcess.createBpsProcessThrowError((String) tbBizApply.get("applyId"), "biz");
					System.out.println("创建流程完毕：processId["+processId+"]");
					
					// 业务申请明细信息
					DataObject tbBizAmountDetailApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApply");
					tbBizAmountDetailApply.set("productType", productType);
					tbBizAmountDetailApply.set("currencyCd", currencyCd);
					tbBizAmountDetailApply.set("creditTerm", applyXwTerm.intValue());
					tbBizAmountDetailApply.set("cycleUnit", "04");//默认期限单位:月
					tbBizAmountDetailApply.set("repaymentType", repaymentType);
					tbBizAmountDetailApply.set("createTime", GitUtil.getBusiDate());
					tbBizAmountDetailApply.set("updateTime", GitUtil.getBusiDate());
					tbBizAmountDetailApply.set("amountId", amountId);
					tbBizAmountDetailApply.set("loanUse", loanUse);// 贷款用途
					tbBizAmountDetailApply.set("payment", payment);// 还款来源
					DatabaseUtil.saveEntity("default", tbBizAmountDetailApply);
					String amountDetailId = tbBizAmountDetailApply.getString("amountDetailId");

					// 小企业贷款
					DataObject tbBizXwApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXwApply");
					tbBizXwApply.set("createTime", GitUtil.getBusiDate());
					tbBizXwApply.set("updateTime", GitUtil.getBusiDate());
					tbBizXwApply.set("amountDetailId", amountDetailId);
					tbBizXwApply.set("applyXwAmt", detailAmt);
					tbBizXwApply.set("applyXwTerm", applyXwTerm.intValue());
					tbBizXwApply.set("cycleUnitXw", "04");//默认期限单位:月
					DatabaseUtil.saveEntity("default", tbBizXwApply);
					String applyDetailId = tbBizAmountDetailApply.getString("applyDetailId");
					rs.getResponseBody().setProcessId(processId);
					rs.getResponseBody().setBizNum(bizNum);
					rs.getResponseBody().setApplyId(applyId);
					rs.getResponseBody().setAmountDetailId(amountDetailId);
					rs.getResponseBody().setAmountId(amountId);
					rs.getResponseBody().setApplyDetailId(applyDetailId);
					rs.getResponseBody().setMsg("交易成功");
					rs.getResponseBody().setStatus("00000");
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
					rs.getResponseBody().setStatus("11111");
					rs.getResponseBody().setMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非网银系统不能访问此服务]");
				rs.getResponseBody().setStatus("11111");
				rs.getResponseBody().setMsg("交易失败[非网银系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResponseBody().setMsg("交易失败[" + e.getMessage() + "]");
			rs.getResponseBody().setStatus("11111");
			//rs.getResponseBody().setMsg("交易失败[信贷系统底层发生异常]");
			rs.getResponseBody().setMsg("交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WytoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			//resTranHeader.setHRetMsg("交易失败[信贷系统底层发生异常]");
			rs.getResponseBody().setMsg("交易失败[" + e.getMessage() + "]");
			rs.getResponseBody().setStatus("11111");
			//rs.getResponseBody().setMsg("交易失败[信贷系统底层发生异常]");
			rs.getResponseBody().setMsg("交易失败[" + e.getMessage() + "]");
			e.printStackTrace();
			log.info("WytoLoanServiceImpl--" + e.getMessage());
		}
		return rs;
	}
}
