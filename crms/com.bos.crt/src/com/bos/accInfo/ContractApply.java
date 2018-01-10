/**
 * 
 */
package com.bos.accInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.git.easyrule.util.DateHelper;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-06-29 17:54:55
 * 
 */
@Bizlet("")
public class ContractApply {

	/**
	 * @param contractId
	 * @param args
	 * @author 3231 合同调整
	 */
	@Bizlet("")
	public DataObject tzConInfo(String contractId) {
		if (null == contractId || "".equals(contractId)) {
			throw new EOSException("调整时原合同ID为空");
		}
		// 业务合同基本信息
		DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
		tbConContractInfo.set("contractId", contractId);
		DatabaseUtil.expandEntity("default", tbConContractInfo);
		// 合同利率信息
		DataObject tbConLoanrate = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConLoanrate");
		tbConLoanrate.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", tbConLoanrate, tbConLoanrate);
		// 用款计划
		DataObject tbConPayoutPlan = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConPayoutPlan");
		tbConPayoutPlan.set("contractDetailId", contractId);
		DataObject[] tbConPayoutPlans = DatabaseUtil.queryEntitiesByTemplate("default", tbConPayoutPlan);
		// 还款计划
		DataObject tbConRepayPlan = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConRepayPlan");
		tbConRepayPlan.set("contractId", contractId);
		DataObject[] tbConRepayPlans = DatabaseUtil.queryEntitiesByTemplate("default", tbConRepayPlan);
		// 账户信息
		DataObject tbConZh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConZh");
		tbConZh.set("contractId", contractId);
		DataObject[] tbConZhs = DatabaseUtil.queryEntitiesByTemplate("default", tbConZh);
		// 费用信息
		DataObject tbConFee = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConFee");
		tbConFee.set("contractDetailId", contractId);
		DataObject[] tbConFees = DatabaseUtil.queryEntitiesByTemplate("default", tbConFee);
		// 标志信息
		DataObject tbConFlagInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConFlagInfo");
		tbConFlagInfo.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", tbConFlagInfo, tbConFlagInfo);
		// 附属信息
		DataObject tbConAttachedInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConAttachedInfo");
		tbConAttachedInfo.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", tbConAttachedInfo, tbConAttachedInfo);
		// 贸易合同信息 --20151016----
		DataObject myht = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizMyhtxxApply");
		myht.set("amountDetailId", contractId);// 贸易合同信息就是存合同id
		DataObject[] myhts = DatabaseUtil.queryEntitiesByTemplate("default", myht);

		// 20160607 二期需求
		// 通知和文书送达
		DataObject noticeAddrs = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConNoticeAddrs");
		noticeAddrs.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", noticeAddrs, noticeAddrs);
		
		
		
		// 明细信息
		DataObject productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
		productInfo.set("productCd", tbConContractInfo.get("productType"));
		DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);

		String entityName = productInfo.getString("entityName");
		// com.bos.dataset.biz.TbBizLdzjApply
		// com.bos.dataset.crt.TbConLdzj
		entityName = entityName.replace("biz", "crt");
		entityName = entityName.replace("Biz", "Con");
		entityName = entityName.replace("Apply", "");
		DataObject productDetail = DataObjectUtil.createDataObject(entityName);
		productDetail.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", productDetail, productDetail);
		// 担保合同信息 com.bos.dataset.crt.TbConSubcontractRel
		DataObject conRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
		conRel.set("contractId", contractId);
		DataObject[] conRels = DatabaseUtil.queryEntitiesByTemplate("default", conRel);
		
		//根据合同id查询票据信息
		//查存量数据
		DataObject tbBizPjxxApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizPjxxApply");
		tbBizPjxxApply.set("htbh",tbConContractInfo.getString("contractNum"));
		tbBizPjxxApply.set("amountDetailId",tbConContractInfo.getString("amountDetailId"));
		DataObject[] pjxxs = DatabaseUtil.queryEntitiesByTemplate("default", tbBizPjxxApply);
		if(null !=pjxxs && pjxxs.length>0){
			//新数据
			tbBizPjxxApply.set("contractId",contractId);
			tbBizPjxxApply.set("htbh",tbConContractInfo.getString("contractNum"));
			tbBizPjxxApply.set("amountDetailId",tbConContractInfo.getString("amountDetailId"));
			DataObject[] pjs = DatabaseUtil.queryEntitiesByTemplate("default", tbBizPjxxApply);
			if(null !=pjs && pjs.length>0){
				pjxxs=pjs;
			}
		}
		// 新建合同信息并保存
		// 获取时间com.bos.pub.GitUtil.getBusiDate
		Date date = GitUtil.getBusiDate();
		// 业务合同基本信息
		if (null != tbConContractInfo.get("partyId")) {
			tbConContractInfo.set("contractId", null);
			tbConContractInfo.set("oldContractId", contractId);
			tbConContractInfo.set("conStatus", "01");
			tbConContractInfo.set("createTime", date);
			DatabaseUtil.insertEntity("default", tbConContractInfo);
		}
		// 新合同ID
		contractId = (String) tbConContractInfo.get("contractId");
		// 合同利率信息
		if (null != tbConLoanrate.get("loanrateId")) {
			tbConLoanrate.set("loanrateId", null);
			tbConLoanrate.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", tbConLoanrate);
		} 
		// 标志信息
		if (null != tbConFlagInfo.get("flagId")) {
			tbConFlagInfo.set("flagId", null);
			tbConFlagInfo.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", tbConFlagInfo);
		}
		// 通知书文书送达
		if (null != noticeAddrs.get("uuid")) {
			noticeAddrs.set("uuid", null);
			noticeAddrs.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", noticeAddrs);
		}
		// 合同明细下附属信息
		if (null != tbConAttachedInfo.get("attached")) {
			tbConAttachedInfo.set("attached", null);
			tbConAttachedInfo.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", tbConAttachedInfo);
		}
		// 用款计划
		for (int i = 0; i <= tbConPayoutPlans.length - 1; i++) {
			tbConPayoutPlan = tbConPayoutPlans[i];
			if (null != tbConPayoutPlan.get("payoutPlanId")) {
				tbConPayoutPlan.set("payoutPlanId", null);
				tbConPayoutPlan.set("contractDetailId", contractId);
				DatabaseUtil.insertEntity("default", tbConPayoutPlan);
			}
		}
		// 还款计划
		for (int i = 0; i <= tbConRepayPlans.length - 1; i++) {
			tbConRepayPlan = tbConRepayPlans[i];
			if (null != tbConRepayPlan.get("repayPlanId")) {
				tbConRepayPlan.set("repayPlanId", null);
				tbConRepayPlan.set("contractId", contractId);
				DatabaseUtil.insertEntity("default", tbConRepayPlan);
			}
		}
		// 账户信息
		for (int i = 0; i <= tbConZhs.length - 1; i++) {
			tbConZh = tbConZhs[i];
			if (null != tbConZh.get("id")) {
				tbConZh.set("id", null);
				tbConZh.set("contractId", contractId);
				DatabaseUtil.insertEntity("default", tbConZh);
			}
		}
		// 费用信息
		for (int i = 0; i <= tbConFees.length - 1; i++) {
			tbConFee = tbConFees[i];
			if (null != tbConFee.get("feeId")) {
				tbConFee.set("feeId", null);
				tbConFee.set("contractDetailId", contractId);
				DatabaseUtil.insertEntity("default", tbConFee);
			}
		}
		// 贸易合同信息-----20151016--
		for (int i = 0; i <= myhts.length - 1; i++) {
			myht = myhts[i];
			if (null != myht.get("htId")) {
				myht.set("htId", null);
				myht.set("amountDetailId", contractId);
				DatabaseUtil.insertEntity("default", myht);
			}
		}

		// 明细信息
		if (null != productDetail.get("applyDetailId")) {
			productDetail.set("applyDetailId", null);
			productDetail.set("updateTime", null);
			productDetail.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", productDetail);
		}
		//票据信息
		if(null !=pjxxs && pjxxs.length!=0){
			for (int i = 0; i < pjxxs.length; i++) {
				 tbBizPjxxApply = pjxxs[i];
				 tbBizPjxxApply.set("applyDetailId",null);
				 tbBizPjxxApply.set("jlzt","01");//未提交
				 tbBizPjxxApply.set("contractId",contractId);//调整备份
				 DatabaseUtil.insertEntity("default",  tbBizPjxxApply);
			}
		}
		
		for (int i = 0; i < conRels.length; i++) {
			conRel = conRels[i];
			if(conRel.get("subcontractId") == null){
				continue;
			}
			// 担保合同信息
			DataObject conSubcon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
			conSubcon.set("subcontractId", conRel.get("subcontractId"));
			DatabaseUtil.expandEntity("default", conSubcon);
			//备份担保合同
			conSubcon.set("subcontractId", null);
			conSubcon.set("subcontractStatus", "01");
			DatabaseUtil.insertEntity("default", conSubcon);
			//新担保合同Id
			String subcontractId = (String) conSubcon.get("subcontractId");
			
			// 保证金协议下附属信息
			if ("03".equals(conSubcon.getString("subcontractType"))) {
				tbConAttachedInfo.set("attached", null);
				tbConAttachedInfo.set("contractId", subcontractId);
				DatabaseUtil.insertEntity("default", tbConAttachedInfo);
			}
			//备份担保合同与押品关系信息表
			DataObject conSubgrt = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");
			conSubgrt.set("subcontractId", conRel.get("subcontractId"));
			DataObject[] conSubgrts = DatabaseUtil.queryEntitiesByTemplate("default", conSubgrt);
			if(null != conSubgrts && conSubgrts.length != 0){
				for (int j = 0; j < conSubgrts.length; j++) {
					conSubgrt = conSubgrts[j];
					//备份新的关系
					conSubgrt.set("relationId", null);
					conSubgrt.set("subcontractId", subcontractId);
					DatabaseUtil.insertEntity("default", conSubgrt);
				}
			}		
			
			//备份担保合同与批复关系信息表com.bos.dataset.biz.TbBizSubcontractRel
			DataObject bizSubRel= DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSubcontractRel");
			bizSubRel.set("subcontractId", conRel.getString("subcontractId"));
			DatabaseUtil.expandEntityByTemplate("default", bizSubRel,bizSubRel);
			bizSubRel.set("partyId", bizSubRel.getString("partyId"));
			DataObject[] bizSubRels = DatabaseUtil.queryEntitiesByTemplate("default", bizSubRel);
			
			for (int j = 0; j < bizSubRels.length; j++) {
				DataObject bizSubConRel = bizSubRels[j];
				bizSubConRel.set("relationId", null);
				bizSubConRel.set("createTime", date);
				bizSubConRel.set("subcontractId", subcontractId);//合同调整提交至流程最后一步修改为新的担保合同Id，如果撤销则修改为原担保合同Id				
				DatabaseUtil.insertEntity("default", bizSubConRel); 
			}
			
			//备份担保合同信息与贷款合同信息关系表
			conRel.set("conSubconId", null);
			conRel.set("contractId", contractId);
			conRel.set("subcontractId", subcontractId);
			conRel.set("operationType", "01");
			DatabaseUtil.insertEntity("default", conRel);
		}
		
		return tbConContractInfo;
	}

	/**
	 * 根据起期、期限、期限单位计算止期
	 * 
	 * @param beginDate
	 * @param term
	 * @param cycleUnit
	 * @return
	 */
	@Bizlet("")
	public Date getEndDate(Date beginDate, int term, String cycleUnit) {
		Date endDate = beginDate;
		if (cycleUnit.equals("01")) {
			endDate = DateHelper.calculateDate(beginDate, term, 0, 0);
		} else if (cycleUnit.equals("02")) {
			endDate = DateHelper.calculateDate(beginDate, 0, term * 6, 0);
		} else if (cycleUnit.equals("03")) {
			endDate = DateHelper.calculateDate(beginDate, 0, term * 3, 0);
		} else if (cycleUnit.equals("04")) {
			endDate = DateHelper.calculateDate(beginDate, 0, term, 0);
		} else if (cycleUnit.equals("05")) {
			endDate = DateHelper.calculateDate(beginDate, 0, 0, term);
		}
		return endDate;
	}

	@Bizlet("")
	public DataObject tzCreditInfo(String contractId) {
		if (null == contractId || "".equals(contractId)) {
			throw new EOSException("调整时原合同ID为空");
		}
		// 授信协议基本信息
		DataObject tbConCreditInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConCreditInfo");
		tbConCreditInfo.set("contractId", contractId);
		DatabaseUtil.expandEntity("default", tbConCreditInfo);

		// 账户信息
		DataObject tbConZh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConZh");
		tbConZh.set("contractId", contractId);
		DataObject[] tbConZhs = DatabaseUtil.queryEntitiesByTemplate("default", tbConZh);

		// 附属信息
		DataObject tbConAttachedInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConAttachedInfo");
		tbConAttachedInfo.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", tbConAttachedInfo, tbConAttachedInfo);

		// 担保合同信息 com.bos.dataset.crt.TbConSubcontractRel
		DataObject conRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
		conRel.set("contractId", contractId);
		DataObject[] conRels = DatabaseUtil.queryEntitiesByTemplate("default", conRel);

		// 新建合同信息并保存
		// 获取时间com.bos.pub.GitUtil.getBusiDate
		Date date = GitUtil.getBusiDate();
		// 业务合同基本信息
		if (null != tbConCreditInfo.get("partyId")) {
			tbConCreditInfo.set("contractId", null);
			tbConCreditInfo.set("oldContractId", contractId);
			tbConCreditInfo.set("conStatus", "01");
			tbConCreditInfo.set("createTime", date);
			DatabaseUtil.insertEntity("default", tbConCreditInfo);
		}
		// 新合同ID
		contractId = (String) tbConCreditInfo.get("contractId");

		// 附属信息
		if (null != tbConAttachedInfo.get("attached")) {
			tbConAttachedInfo.set("attached", null);
			tbConAttachedInfo.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", tbConAttachedInfo);
		}

		// 账户信息
		for (int i = 0; i <= tbConZhs.length - 1; i++) {
			tbConZh = tbConZhs[i];
			if (null != tbConZh.get("id")) {
				tbConZh.set("id", null);
				tbConZh.set("contractId", contractId);
				DatabaseUtil.insertEntity("default", tbConZh);
			}
		}

		// 担保合同信息
		for (int i = 0; i < conRels.length; i++) {
			// 判断担保合同是否生效
			DataObject subCon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
			subCon.set("subcontractId", conRels[i].get("subcontractId"));
			DatabaseUtil.expandEntity("default", subCon);
			if ("03".equals(subCon.get("subcontractStatus"))) {
				conRel = conRels[i];
				conRel.set("conSubconId", null);
				conRel.set("contractId", contractId);
				conRel.set("operationType", "01");
				DatabaseUtil.insertEntity("default", conRel);
			}
		}
		return tbConCreditInfo;
	}

	@Bizlet("")
	public String aftDeleteRepayPlan(String contractId) {
		String result = "1";
		try {
			Map argMap = new HashMap();
			argMap.put("contractId", contractId);
			DataObject repayPlan = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConRepayPlan");
			// repayPlan.set("contractId", contractId);
			Object[] repayPlans = DatabaseExt.queryByNamedSql("default", "com.bos.conApply.conApply.getHkjhsByConId", argMap);
			for (int i = 0; i < repayPlans.length; i++) {
				repayPlan = (DataObject) repayPlans[i];
				repayPlan.set("periodsNumber", new BigDecimal(i + 1));
				DatabaseUtil.updateEntity("default", repayPlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "2";
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

	/**
	 * @param contractId
	 * @param args
	 * @author 3231 合同调整
	 */
	@Bizlet("")
	public DataObject tzSupplybzjInfo(String contractId) {
		if (null == contractId || "".equals(contractId)) {
			throw new EOSException("调整时原合同ID为空");
		}
		// 业务合同基本信息
		DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
		tbConContractInfo.set("contractId", contractId);
		DatabaseUtil.expandEntity("default", tbConContractInfo);
		// 合同利率信息
		DataObject tbConLoanrate = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConLoanrate");
		tbConLoanrate.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", tbConLoanrate, tbConLoanrate);
		// 用款计划
		DataObject tbConPayoutPlan = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConPayoutPlan");
		tbConPayoutPlan.set("contractDetailId", contractId);
		DataObject[] tbConPayoutPlans = DatabaseUtil.queryEntitiesByTemplate("default", tbConPayoutPlan);
		// 还款计划
		DataObject tbConRepayPlan = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConRepayPlan");
		tbConRepayPlan.set("contractId", contractId);
		DataObject[] tbConRepayPlans = DatabaseUtil.queryEntitiesByTemplate("default", tbConRepayPlan);
		// 账户信息
		DataObject tbConZh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConZh");
		tbConZh.set("contractId", contractId);
		DataObject[] tbConZhs = DatabaseUtil.queryEntitiesByTemplate("default", tbConZh);
		// 费用信息
		DataObject tbConFee = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConFee");
		tbConFee.set("contractDetailId", contractId);
		DataObject[] tbConFees = DatabaseUtil.queryEntitiesByTemplate("default", tbConFee);
		// 标志信息
		DataObject tbConFlagInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConFlagInfo");
		tbConFlagInfo.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", tbConFlagInfo, tbConFlagInfo);
		// 附属信息
		DataObject tbConAttachedInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConAttachedInfo");
		tbConAttachedInfo.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", tbConAttachedInfo, tbConAttachedInfo);
		// 贸易合同信息 --20151016----
		DataObject myht = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizMyhtxxApply");
		myht.set("amountDetailId", contractId);// 贸易合同信息就是存合同id
		DataObject[] myhts = DatabaseUtil.queryEntitiesByTemplate("default", myht);

		// 20160607 二期需求
		// 通知和文书送达
		DataObject noticeAddrs = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConNoticeAddrs");
		noticeAddrs.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", noticeAddrs, noticeAddrs);

		// 明细信息
		DataObject productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
		productInfo.set("productCd", tbConContractInfo.get("productType"));
		DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);

		String entityName = productInfo.getString("entityName");
		// com.bos.dataset.biz.TbBizLdzjApply
		// com.bos.dataset.crt.TbConLdzj
		entityName = entityName.replace("biz", "crt");
		entityName = entityName.replace("Biz", "Con");
		entityName = entityName.replace("Apply", "");
		DataObject productDetail = DataObjectUtil.createDataObject(entityName);
		productDetail.set("contractId", contractId);
		DatabaseUtil.expandEntityByTemplate("default", productDetail, productDetail);
		// 担保合同信息 com.bos.dataset.crt.TbConSubcontractRel
		DataObject conRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
		conRel.set("contractId", contractId);
		DataObject[] conRels = DatabaseUtil.queryEntitiesByTemplate("default", conRel);

		// 新建合同信息并保存
		// 获取时间com.bos.pub.GitUtil.getBusiDate
		Date date = GitUtil.getBusiDate();
		// 业务合同基本信息
		if (null != tbConContractInfo.get("partyId")) {
			tbConContractInfo.set("contractId", null);
			tbConContractInfo.set("oldContractId", contractId);
			tbConContractInfo.set("conStatus", "01");
			tbConContractInfo.set("createTime", date);
			DatabaseUtil.insertEntity("default", tbConContractInfo);
		}
		// 新合同ID
		contractId = (String) tbConContractInfo.get("contractId");
		// 合同利率信息
		if (null != tbConLoanrate.get("loanrateId")) {
			tbConLoanrate.set("loanrateId", null);
			tbConLoanrate.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", tbConLoanrate);
		}
		// 标志信息
		if (null != tbConFlagInfo.get("flagId")) {
			tbConFlagInfo.set("flagId", null);
			tbConFlagInfo.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", tbConFlagInfo);
		}
		// 通知书文书送达
		if (null != noticeAddrs.get("uuid")) {
			noticeAddrs.set("uuid", null);
			noticeAddrs.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", noticeAddrs);
		}
		// 附属信息
		if (null != tbConAttachedInfo.get("attached")) {
			tbConAttachedInfo.set("attached", null);
			tbConAttachedInfo.set("contractId", contractId);
			DatabaseUtil.insertEntity("default", tbConAttachedInfo);
		}
		// 用款计划
		for (int i = 0; i <= tbConPayoutPlans.length - 1; i++) {
			tbConPayoutPlan = tbConPayoutPlans[i];
			if (null != tbConPayoutPlan.get("payoutPlanId")) {
				tbConPayoutPlan.set("payoutPlanId", null);
				tbConPayoutPlan.set("contractDetailId", contractId);
				DatabaseUtil.insertEntity("default", tbConPayoutPlan);
			}
		}
		// 还款计划
		for (int i = 0; i <= tbConRepayPlans.length - 1; i++) {
			tbConRepayPlan = tbConRepayPlans[i];
			if (null != tbConRepayPlan.get("repayPlanId")) {
				tbConRepayPlan.set("repayPlanId", null);
				tbConRepayPlan.set("contractId", contractId);
				DatabaseUtil.insertEntity("default", tbConRepayPlan);
			}
		}
		// 账户信息
		for (int i = 0; i <= tbConZhs.length - 1; i++) {
			tbConZh = tbConZhs[i];
			if (null != tbConZh.get("id")) {
				tbConZh.set("id", null);
				tbConZh.set("contractId", contractId);
				DatabaseUtil.insertEntity("default", tbConZh);
			}
		}
		// 费用信息
		for (int i = 0; i <= tbConFees.length - 1; i++) {
			tbConFee = tbConFees[i];
			if (null != tbConFee.get("feeId")) {
				tbConFee.set("feeId", null);
				tbConFee.set("contractDetailId", contractId);
				DatabaseUtil.insertEntity("default", tbConFee);
			}
		}
		// 贸易合同信息-----20151016--
		for (int i = 0; i <= myhts.length - 1; i++) {
			myht = myhts[i];
			if (null != myht.get("htId")) {
				myht.set("htId", null);
				myht.set("amountDetailId", contractId);
				DatabaseUtil.insertEntity("default", myht);
			}
		}

		// 明细信息
		if (null != productDetail.get("applyDetailId")) {
			productDetail.set("applyDetailId", null);
			productDetail.set("contractId", contractId);
			productDetail.set("updateTime", GitUtil.currDateTime());
			DatabaseUtil.insertEntity("default", productDetail);
		}
		

		for (int i = 0; i < conRels.length; i++) {
			conRel = conRels[i];
			// 担保合同信息
			DataObject conSubcon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
			conSubcon.set("subcontractId", conRel.get("subcontractId"));
			DatabaseUtil.expandEntity("default", conSubcon);
			//备份担保合同
			conSubcon.set("subcontractId", null);
			conSubcon.set("subcontractStatus", "01");
			DatabaseUtil.insertEntity("default", conSubcon);
			//新担保合同Id
			String subcontractId = (String) conSubcon.get("subcontractId");
			
			//备份担保合同与押品关系信息表
			DataObject conSubgrt = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");
			conSubgrt.set("subcontractId", conRel.get("subcontractId"));
			DataObject[] conSubgrts = DatabaseUtil.queryEntitiesByTemplate("default", conSubgrt);
			if(null != conSubgrts && conSubgrts.length != 0){
				for (int j = 0; j < conSubgrts.length; j++) {
					conSubgrt = conSubgrts[j];
					//备份新的关系
					conSubgrt.set("relationId", null);
					conSubgrt.set("subcontractId", subcontractId);
					DatabaseUtil.insertEntity("default", conSubgrt);
				}
			}		
			
			//备份担保合同与批复关系信息表com.bos.dataset.biz.TbBizSubcontractRel
			DataObject bizSubRel= DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSubcontractRel");
			bizSubRel.set("subcontractId", conRel.getString("subcontractId"));
			DatabaseUtil.expandEntityByTemplate("default", bizSubRel,bizSubRel);
			bizSubRel.set("partyId", bizSubRel.getString("partyId"));
			DataObject[] bizSubRels = DatabaseUtil.queryEntitiesByTemplate("default", bizSubRel);
			
			for (int j = 0; j < bizSubRels.length; j++) {
				DataObject bizSubConRel = bizSubRels[j];
				bizSubConRel.set("relationId", null);
				bizSubConRel.set("createTime", date);
				bizSubConRel.set("subcontractId", subcontractId);//合同调整提交至流程最后一步修改为新的担保合同Id，如果撤销则修改为原担保合同Id				
				DatabaseUtil.insertEntity("default", bizSubConRel); 
			}
			
			//备份担保合同信息与贷款合同信息关系表
			conRel.set("conSubconId", null);
			conRel.set("contractId", contractId);
			conRel.set("subcontractId", subcontractId);
			conRel.set("operationType", "01");
			DatabaseUtil.insertEntity("default", conRel);
		}
		
			// DataObject subcon =
			// DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
			// subcon.set("subcontractId", conRels[i].get("subcontractId"));
			// subcon.set("subcontractStatus", "03");
			// DatabaseUtil.expandEntity("default", subcon);
			// if(null != subcon.get("subcontractId")){
			// DataObject subconT =
			// DataObjectUtil.convertDataObject(subcon,"com.bos.dataset.crt.TbConSubcontractT",
			// true);
			// subconT.set("status", "00");
			// subconT.set("id", GitUtil.genUUIDString());
			// DatabaseUtil.insertEntity("default", subconT);
			// if("03".equals(subconT.getString("subcontractType"))){
			//
			// DataObject conSubGrtRel =
			// DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");
			// conSubGrtRel.set("subcontractId", subcon.get("subcontractId"));
			// DataObject[] conSubGrtRels =
			// DatabaseUtil.queryEntitiesByTemplate("default", conSubGrtRel);
			// DataObject tbConSubTmp =
			// DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubTmp");
			// for(int j = 0; j < conSubGrtRels.length; j++){
			// tbConSubTmp.set("subcontractId",
			// conSubGrtRels[j].get("subcontractId"));
			// tbConSubTmp.set("conSuretyId", conSubGrtRels[j].get("suretyId"));
			// tbConSubTmp.set("createTime",
			// conSubGrtRels[j].get("createTime"));
			// tbConSubTmp.set("updateTime",
			// conSubGrtRels[j].get("updateTime"));
			// DatabaseUtil.insertEntity("default", tbConSubTmp);
			// DataObject tbGrtMargin =
			// DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtMargin");
			// tbGrtMargin.set("suretyId", conSubGrtRels[j].get("suretyId"));
			// DatabaseUtil.expandEntityByTemplate("default", tbGrtMargin,
			// tbGrtMargin);
			// DataObject tbGrtMarginT =
			// DataObjectUtil.convertDataObject(tbGrtMargin,"com.bos.dataset.crt.TbGrtMarginT",
			// true);
			// if(null != tbGrtMarginT.get("suretyKeyId")){
			// DatabaseUtil.insertEntity("default", tbGrtMarginT);
			// }
			// }
			// }
			//
			// }
		return tbConContractInfo;
	}

}
