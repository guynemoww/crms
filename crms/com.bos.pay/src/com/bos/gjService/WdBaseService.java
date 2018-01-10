package com.bos.gjService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.exception.EOSRuntimeException;
import com.bos.payInfo.LoanSubject;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;

import commonj.sdo.DataObject;

/**
 * 网贷 个贷保存申请、批复的数据
 * @author ww
 *
 */
public class WdBaseService {
	private TraceLogger log = new TraceLogger(WdBaseService.class);
	/**
	 * @param mapApp 
	 * 1、保存申请、批复的数据
	 */
	public HashMap<String, String> saveApply(Map mapApp) {
		HashMap<String, String> map = new HashMap<String, String>();
		String applyId = (String) mapApp.get("applyId");//业务申请ID
		String ecifPartyNum = (String) mapApp.get("ecifPartyNum");//客户ecif编号
		String productType = (String) mapApp.get("productType");//申请产品代码
		String applyXwTerm = (String) mapApp.get("applyXwTerm");//申请期限 最大12个月
		String creditAmount = (String) mapApp.get("creditAmount");//申请金额
		String guarantyType = (String) mapApp.get("guarantyType");//担保方式
		String rate = (String) mapApp.get("rate");//年利率
		String repaymentType = (String) mapApp.get("repaymentType");//还款方式
		String payment = (String) mapApp.get("payment");//还款来源
		String orgNum = (String) mapApp.get("orgNum");//机构
		String userNum = (String) mapApp.get("userNum");//员工编号
		String loanUse = (String) mapApp.get("loanUse");//贷款用途

		try {
			//String applyId = getUuid();//tbBizApply业务申请表的主键
			String bizNum = BizNumGenerator.getBizNum("SEQ_BIZ_PF");//批复编号
			//1保存业务申请表信息
			HashMap<String, String> mapBizApply = new HashMap<String, String>();
			mapBizApply.put("applyId", applyId);//主键
			mapBizApply.put("bizNum", "PF" + bizNum);//批复编号 
			mapBizApply.put("orgNum", orgNum);//机构
			mapBizApply.put("userNum", userNum);//用户
			mapBizApply.put("productType", productType);//产品代码
			mapBizApply.put("ecifPartNum", ecifPartyNum);//客户ecif编号
			//业务申请
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbBizApply", mapBizApply);

			//2保存业务申请基本信息
			HashMap<String, String> mapAmountApply = new HashMap<String, String>();
			mapAmountApply.put("applyId", applyId);//取tbBizApply表的值
			mapAmountApply.put("creditAmount", creditAmount);//申请额度
			mapAmountApply.put("guarantyType", guarantyType);//担保方式

			//业务申请基本
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbBizAmountApply",
					mapAmountApply);

			//3业务申请明细信息表
			HashMap<String, String> mapAmountDetail = new HashMap<String, String>();
			mapAmountDetail.put("repaymentType", repaymentType);//还款方式??
			mapAmountDetail.put("payment", payment);//还款来源??
			mapAmountDetail.put("applyXwTerm", applyXwTerm);//申请期限
			mapAmountDetail.put("loanUse", loanUse);//贷款用途
			mapAmountDetail.put("applyId", applyId);//业务申请ID
			//保存详细信息
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbBizAmountDetailApply",
					mapAmountDetail);

			//4小企业贷款
			HashMap<String, String> mapXwApply = new HashMap<String, String>();
			mapXwApply.put("applyXwTerm", applyXwTerm);//申请期限
			mapXwApply.put("applyId", applyId);//业务申请ID
			//保存小微
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbBizXwApply", mapXwApply);

			//5申请明细利率
			HashMap<String,Object> mapLoanRateApply = new HashMap<String,Object>();
			mapLoanRateApply.put("yearRate", rate);//年利率
			mapLoanRateApply.put("applyId", applyId);//业务申请ID
			//保存利率
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbBizAmountLoanRateApply",
					mapLoanRateApply);

			//返回批复编号和ecif客户号
			map.put("applyId", applyId);
			map.put("bizNum", "PF"+bizNum);
			map.put("ecifPartyNum", ecifPartyNum);

			log.info("----基础服务----保存申请信息成功applyId: " + applyId 
					+ "||批复编号:"+bizNum + "||ecif客户编号: " + ecifPartyNum);
		} catch (EOSRuntimeException e) {
			e.printStackTrace();
		}
		return map;
	}

	//2、保存个贷批复表信息
	public void saveApprove(Map mapApprove) {
		String applyId = (String) mapApprove.get("applyId");
		try {
			//1批复信息表
			Map<String, String> mapBizApprove = new HashMap<String, String>();
			mapBizApprove.put("applyId", applyId);
			DatabaseExt
					.executeNamedSql("default",
							"com.bos.pay.wdBaseService.saveTbBizApprove",
							mapBizApprove);

			//2批复基本信息
			Map<String, String> mapAmountApprove = new HashMap<String, String>();
			mapAmountApprove.put("applyId", applyId);
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbBizAmountApprove",
					mapAmountApprove);

			//3批复详细信息
			Map<String, String> mapAmountDetailApprove = new HashMap<String, String>();
			mapAmountDetailApprove.put("applyId", applyId);
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbBizAmountDetailApprove",
					mapAmountDetailApprove);

			//4批复详细信息
			Map<String, String> mapXwApprove = new HashMap<String, String>();
			mapXwApprove.put("applyId", applyId);
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbBizxwApprove",
					mapXwApprove);

			//5批复详细信息
			Map<String, String> mapLoanRateApprove = new HashMap<String, String>();
			mapLoanRateApprove.put("applyId", applyId);
			//
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbBizLoanrateApprove",
					mapLoanRateApprove);
			
			log.info("----基础服务----保存批复信息成功");
		} catch (EOSRuntimeException e) {
			e.printStackTrace();
		}
	}

	//3、保存合同信息
	public String saveContract(Map mapCon) {
		String orgNum = (String) mapCon.get("orgNum");
		String applyId = (String) mapCon.get("applyId");
		String contractNum = GitUtil.getSeqNo("HT", orgNum);//生成合同编号
		String payWay = (String) mapCon.get("payWay");//资金支付方式
		String signPlace = (String) mapCon.get("signPlace");//签约地点
		String loanTurn = (String) mapCon.get("loanTurn");//行业投向
		String zhbs = (String) mapCon.get("zhbs");//账户标识
		//String zhlx = (String) mapCon.get("zhlx");//账户类型
		String kzbs = (String) mapCon.get("kzbs");//卡折标识
		String zhmc = (String) mapCon.get("zhmc");//账户名称
		String zh = (String) mapCon.get("zh");//账号
		String zhkhjg = (String) mapCon.get("zhkhjg");//账户开户机构
		String accStatus = (String) mapCon.get("accStatus");//账户状态

		String firstZhmc = (String) mapCon.get("firstZhmc");//第一还款账号名称
		String firstZhzh = (String) mapCon.get("firstZhzh");//第一还款账号
		String firstZhkhjg = (String) mapCon.get("firstZhkhjg");//第一还款账户开户机构
		String firstZhstatus = (String) mapCon.get("firstZhstatus");//第一还款账户状态
		
		String secondZhmc = (String) mapCon.get("secondZhmc");//第二还款账号名称
		String secondZhzh = (String) mapCon.get("secondZhzh");//第二还款账号
		String secondZhkhjg = (String) mapCon.get("secondZhkhjg");//第二还款账户开户机构
		String secondZhstatus = (String) mapCon.get("secondZhstatus");//第二还款账户状态
		String thirdZhmc = (String) mapCon.get("thirdZhmc");//第三还款账号名称
		String thirdZhzh = (String) mapCon.get("thirdZhzh");//第三还款账号名称
		String thirdZhkhjg = (String) mapCon.get("thirdZhkhjg");//第三还款账户开户机构
		String thirdZhstatus = (String) mapCon.get("thirdZhstatus");//第三还款账户状态

		try {
			//1主合同信息表
			HashMap<String, String> mapContract = new HashMap<String, String>();
			mapContract.put("contractNum", contractNum);//合同编号
			mapContract.put("applyId", applyId);
			mapContract.put("payWay", payWay);//资金支付方式
			mapContract.put("signPlace", signPlace);//签约地点
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbConContractInfo",
					mapContract);

			//2主合同业务标志信息
			HashMap<String, String> mapConFlag = new HashMap<String, String>();
			mapConFlag.put("applyId", applyId);
			mapConFlag.put("loanTurn", loanTurn);
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbConFlagInfo", mapConFlag);

			//3主合同利率信息
			HashMap<String, String> mapLoanRate = new HashMap<String, String>();
			mapLoanRate.put("applyId", applyId);
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbConLoanRate", mapLoanRate);

			//4合同通知书信息
			HashMap<String, String> mapNotice = new HashMap<String, String>();
			mapNotice.put("applyId", applyId);
			DatabaseExt
					.executeNamedSql("default",
							"com.bos.pay.wdBaseService.saveTbConNoticeAddrs",
							mapNotice);

			//5主合同附属信息
			HashMap<String, String> mapAttachInfo = new HashMap<String, String>();
			mapAttachInfo.put("applyId", applyId);
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbConAttachedInfo",
					mapAttachInfo);

			//6合同账户信息
			//放款账户信息
			HashMap<String, String> mapConZh = new HashMap<String, String>();
			mapConZh.put("applyId", applyId);
			mapConZh.put("zhbs", zhbs);//账户标识
			mapConZh.put("zhlx", "0");//账户类型
			mapConZh.put("kzbs", kzbs);//卡折标识
			mapConZh.put("zhmc", zhmc);//账户名称
			mapConZh.put("zh", zh);//账号
			mapConZh.put("zhkhjg", zhkhjg);//账户开户机构
			mapConZh.put("accStatus", accStatus);//账户状态
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbConZh", mapConZh);

			//第一还款账户
			HashMap<String, String> mapConZh1 = new HashMap<String, String>();
			mapConZh1.put("applyId", applyId);
			mapConZh1.put("zhbs", zhbs);//账户标识
			mapConZh1.put("zhlx", "1");//账户类型
			mapConZh1.put("kzbs", kzbs);//卡折标识
			mapConZh1.put("zhmc", firstZhmc);//账户名称
			mapConZh1.put("zh", firstZhzh);//账号
			mapConZh1.put("zhkhjg", firstZhkhjg);//账户开户机构
			mapConZh1.put("accStatus", firstZhstatus);//账户状态
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbConZh", mapConZh1);

			//第二还款账户
			HashMap<String, String> mapConZh2 = new HashMap<String, String>();
			if (secondZhmc != null  && secondZhzh !=null) {
				mapConZh2.put("applyId", applyId);
				mapConZh2.put("zhbs", zhbs);//账户标识
				mapConZh2.put("zhlx", "6");//账户类型
				mapConZh2.put("kzbs", kzbs);//卡折标识
				mapConZh2.put("zhmc", secondZhmc);//账户名称
				mapConZh2.put("zh", secondZhzh);//账号
				mapConZh2.put("zhkhjg", secondZhkhjg);//账户开户机构
				mapConZh2.put("accStatus", secondZhstatus);//账户状态
				DatabaseExt.executeNamedSql("default",
						"com.bos.pay.wdBaseService.saveTbConZh", mapConZh2);
			}

			//第三还款账户
			HashMap<String, String> mapConZh3 = new HashMap<String, String>();
			if (thirdZhmc != null && thirdZhzh != null) {
				mapConZh3.put("applyId", applyId);
				mapConZh3.put("zhbs", zhbs);//账户标识
				mapConZh3.put("zhlx", "7");//账户类型
				mapConZh3.put("kzbs", kzbs);//卡折标识
				mapConZh3.put("zhmc", thirdZhmc);//账户名称
				mapConZh3.put("zh", thirdZhzh);//账号
				mapConZh3.put("zhkhjg", thirdZhkhjg);//账户开户机构
				mapConZh3.put("accStatus", thirdZhstatus);//账户状态
				DatabaseExt.executeNamedSql("default",
						"com.bos.pay.wdBaseService.saveTbConZh", mapConZh3);
			}

			log.info("----基础服务----保存合同信息成功，合同编号: "+ contractNum);
		} catch (EOSRuntimeException e) {
			e.printStackTrace();
		}
		return contractNum;//合同编号
	}

	//4、保存放款信息
	public HashMap<String, String> saveLoan(Map mapLoan) {
		HashMap<String, String> map = new HashMap<String, String>();
		String orgNum = (String) mapLoan.get("orgNum");//经办机构
		String contractNum = (String) mapLoan.get("contractNum");//合同编号
		String loanAmt = (String) mapLoan.get("loanAmt");//出账金额
		Date beginDate = (Date) mapLoan.get("beginDate");//出账金额
		String loanTerm = (String) mapLoan.get("loanTerm");//放款期限
		String loanId = getUuid();
		try {
			//1保存放款信息表
			String payNum = GitUtil.getSeqNo("JJ", orgNum);
			Map<String, Object> mapLoanInfo = new HashMap<String, Object>();
			mapLoanInfo.put("loanId", loanId);//tbLoanInfo的loanId
			mapLoanInfo.put("payNum", payNum);//出账编号
			mapLoanInfo.put("loanAmt", loanAmt);//出账金额
			mapLoanInfo.put("beginDate", beginDate);//放款日期
			mapLoanInfo.put("loanTerm", loanTerm);//放款期限
			mapLoanInfo.put("contractNum", contractNum);//合同编号

			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbLoanInfo", mapLoanInfo);

			//1.1更新放款信息表
			HashMap<String, String> mapLoanSub = new HashMap<String, String>();
			//获取业务别
			String loanSubject = new LoanSubject().getLoanSubject(loanId);
			mapLoanSub.put("loanId", loanId);
			mapLoanSub.put("loanSubject", loanSubject);
			//更新放款信息表
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.updateTbLoanInfo", mapLoanSub);

			//2保存账户信息
			HashMap<String, String> mapLoanZh = new HashMap<String, String>();
			mapLoanZh.put("loanId", loanId);
			mapLoanZh.put("contractNum", contractNum);//合同编号
			//放款账户表
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbLoanZh", mapLoanZh);

			//3保存放款利率信息
			HashMap<String, String> mapLoanRate = new HashMap<String, String>();
			mapLoanRate.put("loanId", loanId);
			String intRateCd = "1";
			if(Integer.parseInt(loanTerm) >6){//如果放款期限小于6个月利率代码为1,7-12利率代码为2
				intRateCd = "2";
			}
			mapLoanRate.put("intRateCd", intRateCd);
			//放款利率表
			DatabaseExt.executeNamedSql("default",
							"com.bos.pay.wdBaseService.saveTbLoanLoanRate",
							mapLoanRate);

			//4保存小企业贷款放款表信息
			HashMap<String, String> mapLoanXw = new HashMap<String, String>();
			mapLoanXw.put("loanId", loanId);
			//小企业贷款放款表
			DatabaseExt.executeNamedSql("default",
					"com.bos.pay.wdBaseService.saveTbLoanXw", mapLoanXw);

			map.put("loanId", loanId);
			map.put("loanNum", payNum);//放款编号
			log.info("----基础服务----保存放款信息成功loanId: " + loanId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	//保存业务申请中间表信息
	/*申请状态  01受理；02查询征信成功；
	 * 03查询征信失败；
	 * 04拒接受理；05合同生效；
	 * 06合同失效；07合同撤销；*/
	public static void saveApplyMiddle(DataObject object) {
		long errorNum = 0;//失败次数
		//Date sucessTime=GitUtil.getBusiDate();//征信成功时间
		object.set("errorNum", errorNum);//失败次数(查询征信失败的次数)
		object.set("applyStatus", "01");//业务受理
		object.set("creatTime", new Date());
		object.set("updateTime", new Date());
		//object.set("errorMsg", errorMsg);
		//object.set("updateTime", updateTime);
		DatabaseUtil.insertEntity("default", object);//向中间表插入数据
		
	}

	//随机生成一个32位长度的uuid
	public static String getUuid() {
		return GitUtil.genUUIDString();//调用系统的公用方法
	}
	/*	//测试用
	 public static void main(String[] args) {
	 //		String str = "20171123";
	 //		System.out.println(str.substring(0,4)+"-"+str.substring(4,6)+"-"+str.substring(6,8));
	
	 System.out.println(GitUtil.getBusiDate());
	 }*/

}
