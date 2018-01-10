/**
 * 
 */
package com.bos.inter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bos.biz.MathHelper;
import com.bos.pub.socket.service.request.EsbBodyWmaRqDbtArray;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-08-07 00:18:32
 *	国际结算发往信贷接收接口
 */

@Bizlet("")
public class GjToLoan {
	public static TraceLogger logger = new TraceLogger(GjToLoan.class);

	public GjToLoan() {

	}

	/**
	 * @param obj 
	 * @param args
	 * @author 3231
	 * 02贷款放款通知
	 * 出账流程完毕后，国际业务系统将对信贷系统发送出账成功指令，以占用授信额度。
	 */
	@Bizlet("")
	public DataObject fktz(DataObject obj) {

		logger.info("------>3231------>02贷款放款通知接口------->begin!");
		String summaryNum = (String) obj.get("dbtNo");
		logger.info("------>3231------>国结返回借据编号为------>" + summaryNum);
		String bsnTp = (String) obj.get("bsnTp");
		logger.info("------>3231------>国结返业务类型为------>" + bsnTp);
		//BigDecimal summaryAmt = (BigDecimal) obj.get("dbtBal");
		BigDecimal summaryAmt = new BigDecimal((Double) obj.get("dbtBal"));
		logger.info("------>3231------>国结返回借据余额为------>" + summaryAmt);

		//如果是展期反馈
		if (bsnTp.equals("050009")) {
			DataObject dh = DataObjectUtil
					.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
			dh.set("changeNum", summaryNum);
			DatabaseUtil.expandEntityByTemplate("default", dh, dh);
			if (dh.get("changeId") == null) {
				obj.set("ReturnCode", "111111");
				obj.set("ReturnMsg", "交易失败，未查询到借据号对应借据信息");
				return obj;
			} else {
				obj.set("ReturnCode", "00000000000000");
				obj.set("ReturnMsg", "交易成功");
				return obj;
			}
		}

		//查询借据信息
		DataObject loanSummary = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanSummary");
		loanSummary.set("summaryNum", summaryNum);
		DatabaseUtil
				.expandEntityByTemplate("default", loanSummary, loanSummary);

		if (loanSummary.get("loanId") == null) {
			obj.set("ReturnCode", "111111");
			obj.set("ReturnMsg", "交易失败，未查询到借据号对应借据信息");
			return obj;
		}
		if ("06".equals(loanSummary.get("summaryStatusCd"))) {
			obj.set("ReturnCode", "111111");
			obj.set("ReturnMsg", "交易失败，借据已失效！");
			return obj;
		}
		//更新借据余额
		loanSummary.set("jjye", (BigDecimal) loanSummary.get("summaryAmt"));
		loanSummary.set("summaryStatusCd", "02");
		DatabaseUtil.updateEntity("default", loanSummary);

		//调用额度重算
		//查询出账信息
		String loanId = (String) loanSummary.get("loanId");
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		
		Map map2 = new HashMap();
		map2.put("partyId", loanInfo.get("partyId"));
		DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
		
		
		/*String loanId = (String) loanSummary.get("loanId");
		BigDecimal jjye = loanSummary.getBigDecimal("jjye");
		//logger.info("------>3231------>借据余额为------>" + jjye);
		HashMap map = new HashMap();
		//map.put("jjye", jjye);
		map.put("summaryAmt", summaryAmt);
		//String gs = "jjye-summaryAmt";
		//jjye = MathHelper.expressionBigDecimal(gs, map);
		//logger.info("------>3231------>扣减后借据余额为------>" + jjye);
		//loanSummary.set("jjye", jjye);
		//DatabaseUtil.updateEntity("default", loanSummary);

		//查询出账信息
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		String contractId = (String) loanInfo.get("contractId");
		//查询合同信息
		DataObject conInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.crt.TbConContractInfo");
		conInfo.set("contractId", contractId);
		DatabaseUtil.expandEntity("default", conInfo);
		BigDecimal conBalance = conInfo.getBigDecimal("conBalance");
		//放款金额与合同余额比较
		if (conBalance.compareTo(summaryAmt) == -1) {
			obj.set("ReturnCode", "111111");
			obj.set("ReturnMsg", "交易失败,放款金额大于合同可用余额");
			return obj;
		}
		logger.info("------>3231------>合同可用余额为------>" + conBalance);
		map.put("conBalance", conBalance);
		String gs = "conBalance-summaryAmt";
		conBalance = MathHelper.expressionBigDecimal(gs, map);
		logger.info("------>3231------>扣减后合同可用余额为------>" + conBalance);
		conInfo.set("conBalance", conBalance);
		DatabaseUtil.updateEntity("default", conInfo);

		//查询综合授信额度
		DataObject detailApprove = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
		detailApprove.set("amountDetailId", conInfo.get("amountDetailId"));
		DatabaseUtil.expandEntity("default", detailApprove);
		DataObject amountApprove = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
		amountApprove.set("amountId", detailApprove.get("amountId"));
		DatabaseUtil.expandEntity("default", amountApprove);
		DataObject bizApprove = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizApprove");
		bizApprove.set("approveId", amountApprove.get("approveId"));
		DatabaseUtil.expandEntity("default", bizApprove);
		DataObject creditLimit = DataObjectUtil
				.createDataObject("com.bos.dataset.crd.TbCrdCreditLimit");
		creditLimit.set("applyId", bizApprove.get("applyId"));
		creditLimit.set("statusCd", "03");
		DatabaseUtil
				.expandEntityByTemplate("default", creditLimit, creditLimit);
		BigDecimal availableAmt = creditLimit.getBigDecimal("availableAmt");
		logger.info("------>3231------>批复可用额度为------>" + availableAmt);
		BigDecimal hl = (BigDecimal) loanSummary.get("exchangeRate");
		gs = "availableAmt-summaryAmt*hl";
		map.put("availableAmt", availableAmt);
		map.put("hl", hl);
		availableAmt = MathHelper.expressionBigDecimal(gs, map);
		logger.info("------>3231------>批复扣减后可用额度为------>" + availableAmt);
		creditLimit.set("availableAmt", availableAmt);
		DatabaseUtil.updateEntity("default", creditLimit);
		logger.info("------>3231------>02贷款放款通知接口------->end!");*/
		obj.set("ReturnCode", "00000000000000");
		obj.set("ReturnMsg", "交易成功");
		return obj;
	}

	/**
	 * @param obj 
	 * 01贸易融资还款
	 * 国际结算收到核心系统还款记账成功消息后向信贷发送还款信息，通知信贷系统恢复额度，对应信贷业务品种：贸易融资还款   (提货担保赔付除外)
	（来单付汇、保函付汇、提货担保赔付）
	 * */
	@Bizlet("")
	public DataObject hk(DataObject obj) {
		try {
			logger.info("------>3231------>01贸易融资还款------->begin!");
			String summaryNum = (String) obj.get("dbtNo");
			logger.info("------>3231------>国结返回借据编号为------>" + summaryNum);
			//BigDecimal PymtAmt = (BigDecimal) obj.get("PymtAmt");
			BigDecimal PymtAmt = new BigDecimal((Double) obj.get("pymtAmt"));
			logger.info("------>3231------>国结返回还款额为------>" + PymtAmt);

			//查询借据信息
			DataObject loanSummary = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			loanSummary.set("summaryNum", summaryNum);
			DatabaseUtil.expandEntityByTemplate("default", loanSummary,
					loanSummary);
			if (loanSummary.get("loanId") == null) {
				obj.set("ReturnCode", "111111");
				obj.set("ReturnMsg", "交易失败，未查询到借据号对应借据信息");
				return obj;
			}

			String loanId = (String) loanSummary.get("loanId");
			BigDecimal jjye = loanSummary.getBigDecimal("jjye");
			logger.info("------>3231------>借据余额为------>" + jjye);

			if (jjye.compareTo(PymtAmt) == -1) {
				obj.set("ReturnCode", "111111");
				obj.set("ReturnMsg", "交易失败,归还金额大于借据余额");
				return obj;
			}
			HashMap map = new HashMap();
			map.put("jjye", jjye);
			map.put("PymtAmt", PymtAmt);
			String gs = "jjye-PymtAmt";
			jjye = MathHelper.expressionBigDecimal(gs, map);
			logger.info("------>3231------>还款后借据余额为------>" + jjye);
			loanSummary.set("jjye", jjye);
			DatabaseUtil.updateEntity("default", loanSummary);

			//查询出账信息
			DataObject loanInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			
			//调用额度重算
			Map map2 = new HashMap();
			map2.put("partyId", loanInfo.get("partyId"));
			DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
			
			/*String contractId = (String) loanInfo.get("contractId");
			//查询合同信息
			DataObject conInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			conInfo.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", conInfo);
			  国结没有合同循环标志
			BigDecimal conBalance = conInfo.getBigDecimal("conBalance");
			logger.info("------>3231------>合同可用余额为------>" + conBalance);
			map.put("conBalance", conBalance);
			gs = "conBalance+PymtAmt";
			conBalance = MathHelper.expressionBigDecimal(gs, map);
			logger.info("------>3231------>还款后合同可用余额为------>" + conBalance);
			conInfo.set("conBalance", conBalance);
			DatabaseUtil.updateEntity("default", conInfo);

			//查询综合授信额度
			DataObject detailApprove = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
			detailApprove.set("amountDetailId", conInfo.get("amountDetailId"));
			DatabaseUtil.expandEntityByTemplate("default", detailApprove,
					detailApprove);
			//额度不循环不需要回滚额度
			if (detailApprove.get("cycleInd") != null
					&& !"".equals(detailApprove.get("cycleInd"))) {
				if ("1".equals(detailApprove.get("cycleInd"))) {
					logger.info("------>3231------>额度循环标志为1，额度回滚------>");
				} else {//额度循环标志为0
					obj.set("ReturnCode", "00000000000000");
					obj.set("ReturnMsg", "交易成功");
					return obj;
				}
			} else {//没有额度循环标志
				obj.set("ReturnCode", "00000000000000");
				obj.set("ReturnMsg", "交易成功");
				return obj;
			}

			DataObject amountApprove = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
			amountApprove.set("amountId", detailApprove.get("amountId"));
			DatabaseUtil.expandEntityByTemplate("default", amountApprove,
					amountApprove);
			DataObject bizApprove = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("approveId", amountApprove.get("approveId"));
			DatabaseUtil.expandEntity("default", bizApprove);
			DataObject creditLimit = DataObjectUtil
					.createDataObject("com.bos.dataset.crd.TbCrdCreditLimit");
			creditLimit.set("applyId", bizApprove.get("applyId"));
			creditLimit.set("statusCd", "03");
			DatabaseUtil.expandEntityByTemplate("default", creditLimit,
					creditLimit);
			BigDecimal availableAmt = creditLimit.getBigDecimal("availableAmt");
			logger.info("------>3231------>批复可用额度为------>" + availableAmt);
			BigDecimal hl = (BigDecimal) loanSummary.get("exchangeRate");
			gs = "availableAmt+PymtAmt*hl";
			map.put("availableAmt", availableAmt);
			map.put("hl", hl);
			availableAmt = MathHelper.expressionBigDecimal(gs, map);
			logger.info("------>3231------>还款后可用额度为------>" + availableAmt);
			creditLimit.set("availableAmt", availableAmt);
			DatabaseUtil.updateEntity("default", creditLimit);
			logger.info("------>3231------>01贸易融资还款------->end!");*/
			obj.set("ReturnCode", "00000000000000");
			obj.set("ReturnMsg", "交易成功");

		} catch (Exception e) {
			e.printStackTrace();
			obj.set("ReturnCode", "111111");
			obj.set("ReturnMsg", "交易失败,信贷后台处理失败");
		}
		return obj;
	}

	/**
	 * 01国际业务撤销/闭卷/取消/冲账
	 * 对应信贷业务品种：
		撤销/闭卷： 进口开证 （开证撤销确认  \开证闭卷）
		进口保函（保函/备用证撤销确认\保函/备用证闭卷）
		提货担保（提货担保闭卷）
		贸易融资发放交易，核心已经记账，不允许国结发起撤销、取消、冲账，此处通过人工处理。
	 * */
	@Bizlet("")
	public DataObject cx(DataObject obj) {

		logger.info("------>3231------>01国际业务撤销/闭卷/取消/冲账------->begin!");
		String loanNum = (String) obj.get("dbtNo");
		logger.info("------>3231------>国结返回借据编号为------>" + loanNum);
		/*BigDecimal OprInd = (BigDecimal) obj.get("oprInd");
		logger.info("------>3231------>国结返回操作标志为------>" + OprInd);*/
		//查询借据信息
		DataObject summaryInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanSummary");
		summaryInfo.set("summaryNum", loanNum);
		DatabaseUtil
				.expandEntityByTemplate("default", summaryInfo, summaryInfo);
		if (summaryInfo.get("loanId") == null) {
			obj.set("ReturnCode", "111111");
			obj.set("ReturnMsg", "交易失败，未找到借据信息");
			return obj;
		}

		if ("06".equals(summaryInfo.get("summaryStatusCd"))) {
			obj.set("ReturnCode", "111111");
			obj.set("ReturnMsg", "交易失败，借据已撤销");
			return obj;
		}

		summaryInfo.set("summaryStatusCd", "06");
		DatabaseUtil.updateEntity("default", summaryInfo);

		//查询放款信息
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", summaryInfo.get("loanId"));
		DatabaseUtil.expandEntity("default", loanInfo);
		if (loanInfo.get("loanNum") == null) {
			obj.set("ReturnCode", "111111");
			obj.set("ReturnMsg", "交易失败，未找到放款信息");
			return obj;
		}
		loanInfo.set("loanStatus", "06");
		DatabaseUtil.updateEntity("default", loanInfo);
		
		//调用额度重算
		Map map2 = new HashMap();
		map2.put("partyId", loanInfo.get("partyId"));
		DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);

		obj.set("ReturnCode", "00000000000000");
		obj.set("ReturnMsg", "交易成功");
		return obj;

	}

	/**
	 * 
	 * @Title: yccx 
	 * @Description: 流程银行--银承撤销
	 * @param obj 
	 * @return    设定文件 
	 * @return DataObject    返回类型 
	 * @throws 
	 * @author GIT-LPC
	 * @date 2015年12月25日 上午10:54:13 
	 * @version V1.0
	 */
	@Bizlet("")
	public DataObject yccx(DataObject obj) {
		logger.info("------>流程银行------>银承撤销------->begin!");
		String contractNum = (String) obj.get("ctrNo");
		logger.info("------>流程银行------>发过来的合同号为------>" + contractNum);
		List<EsbBodyWmaRqDbtArray> esbBodyWmaRqDbtArrays = (List<EsbBodyWmaRqDbtArray>) obj
				.get("esbBodyWmaRqDbtArrays");
		Iterator<EsbBodyWmaRqDbtArray> jjs = esbBodyWmaRqDbtArrays.iterator();
		String partyId = "11";
		while (jjs.hasNext()) {
			String summaryNum = jjs.next().getDbtNo();
			if(null == summaryNum || "".equals(summaryNum)){
				obj.set("ReturnCode", "111111");
				obj.set("ReturnMsg", "交易失败，借据号为空");
				return obj;
			}
			//失效借据
			//查询借据信息
			DataObject summaryInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summaryInfo.set("summaryNum", summaryNum);
			DatabaseUtil.expandEntityByTemplate("default", summaryInfo,
					summaryInfo);
			if (summaryInfo.get("loanId") == null) {
				obj.set("ReturnCode", "111111");
				obj.set("ReturnMsg", "交易失败，未找到借据的信息");
				return obj;
			}

			if ("06".equals(summaryInfo.get("summaryStatusCd"))) {
				obj.set("ReturnCode", "111111");
				obj.set("ReturnMsg", "交易失败，借据已撤销");
				return obj;
			}

			summaryInfo.set("summaryStatusCd", "06");
			summaryInfo.set("jjye", new BigDecimal("0"));
			DatabaseUtil.updateEntity("default", summaryInfo);
			
			//票据信息状态更改从0改成1
			DataObject hpamt = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanHpAmt");
			hpamt.set("summaryNum", summaryNum);
			DatabaseUtil.expandEntityByTemplate("default", hpamt, hpamt);
			if(null == hpamt.get("moneyUseId")){
				obj.set("ReturnCode", "111111");
				obj.set("ReturnMsg", "交易失败，未找到票据信息");
				return obj;
			}
			hpamt.set("billState", "1");
			DatabaseUtil.updateEntity("default", hpamt);

			String contractId = (String) summaryInfo.get("contractId");
			//查询合同信息
			DataObject conInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			conInfo.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", conInfo);
			
			partyId = conInfo.getString("partyId");
			/*//回滚合同可用
			Map map = new HashMap();
			BigDecimal conBalance = conInfo.getBigDecimal("conBalance");
			logger.info("------>3231------>合同可用余额为------>" + conBalance);
			map.put("conBalance", conBalance);
			map.put("summaryAmt", summaryInfo.getBigDecimal("summaryAmt"));
			String gs = "conBalance+summaryAmt";
			conBalance = MathHelper.expressionBigDecimal(gs, map);
			logger.info("------>3231------>还款后合同可用余额为------>" + conBalance);
			conInfo.set("conBalance", conBalance);
			
			//合同余额con_yu_e
			Map<String,String> paraMap = new HashMap<String, String>();
			paraMap.put("contractId", contractId);
			Object[] conyues = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryConYuE", paraMap);
			DataObject conyue = (DataObject)conyues[0];
			conInfo.set("conYuE", conyue.get("CONYUE"));
			
			DatabaseUtil.updateEntity("default", conInfo);

			//滚回批复额度
			DataObject detailApprove = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
			detailApprove.set("amountDetailId", conInfo.get("amountDetailId"));
			DatabaseUtil.expandEntityByTemplate("default", detailApprove,
					detailApprove);

			DataObject amountApprove = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
			amountApprove.set("amountId", detailApprove.get("amountId"));
			DatabaseUtil.expandEntityByTemplate("default", amountApprove,
					amountApprove);
			DataObject bizApprove = DataObjectUtil
					.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("approveId", amountApprove.get("approveId"));
			DatabaseUtil.expandEntity("default", bizApprove);
			DataObject creditLimit = DataObjectUtil
					.createDataObject("com.bos.dataset.crd.TbCrdCreditLimit");
			creditLimit.set("applyId", bizApprove.get("applyId"));
			creditLimit.set("statusCd", "03");
			DatabaseUtil.expandEntityByTemplate("default", creditLimit,
					creditLimit);
			BigDecimal availableAmt = creditLimit.getBigDecimal("availableAmt");
			logger.info("------>3231------>批复可用额度为------>" + availableAmt);
			BigDecimal hl = (BigDecimal) summaryInfo.get("exchangeRate");
			gs = "availableAmt+summaryAmt*hl";
			map.put("availableAmt", availableAmt);
			map.put("hl", hl);
			availableAmt = MathHelper.expressionBigDecimal(gs, map);
			logger.info("------>3231------>还款后可用额度为------>" + availableAmt);
			creditLimit.set("availableAmt", availableAmt);
			DatabaseUtil.updateEntity("default", creditLimit);*/
		}

		//调用额度重算
		Map map2 = new HashMap();
		map2.put("partyId", partyId);
		DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
		logger.info("------>流程银行------>银承撤销------->end!");
		obj.set("ReturnCode", "00000000000000");
		obj.set("ReturnMsg", "交易成功");
		return obj;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
