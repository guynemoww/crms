package com.bos.conInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.accInfo.ContractSub;
import com.bos.biz.MathHelper;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.CommonUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.git.easyrule.util.DateHelper;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-05-12 20:01:37
 * 
 */
@Bizlet("")
public class SubProcess {

	/**
	 * @param contractId
	 * @param args
	 * @author 3231
	 */
	
	public static TraceLogger logger = new TraceLogger(SubProcess.class);
	
	@Bizlet("")
	public void submitPro(String contractId) {
		if (null == contractId || "".equals(contractId)) {
			throw new EOSException("协议合同ID为空");
		}
		String entityName = "com.bos.dataset.crt.TbConCreditInfo";
		DataObject con = DataObjectUtil.createDataObject(entityName);
		con.set("contractId", contractId);
		DatabaseUtil.expandEntity("default", con);
		if (null == con.get("partyId")) {
			entityName = "com.bos.dataset.crt.TbConContractInfo";
			con = DataObjectUtil.createDataObject(entityName);
		}
		con.set("contractId", contractId);
		con.set("conStatus", "03");
		DatabaseUtil.updateEntity("default", con);
		
		DatabaseUtil.expandEntity("default", con);
		Map<String, String> conMap = new HashMap<String, String>();
		conMap.put("contractId", contractId);
		conMap.put("entityName", entityName);
		String oldContractId = con.getString("oldContractId");
		if (null != oldContractId) {
			con.set("contractId", oldContractId);
			DatabaseUtil.expandEntity("default", con);
			con.set("conStatus", "04");
			DatabaseUtil.updateEntity("default", con);
			// 将原合同出账挂在新的合同下
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("contractId", oldContractId);
			DataObject[] loanInfos = DatabaseUtil.queryEntitiesByTemplate("default", loanInfo);
			for (int i = 0; i < loanInfos.length; i++) {
				loanInfos[i].set("contractId", contractId);
				DatabaseUtil.updateEntity("default", loanInfos[i]);
			}
			// 将原合同借据挂在新的合同下
			DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			loanSummary.set("contractId", oldContractId);
			DataObject[] loanSummarys = DatabaseUtil.queryEntitiesByTemplate("default", loanSummary);
			for (int i = 0; i < loanSummarys.length; i++) {
				loanSummarys[i].set("contractId", contractId);
				DatabaseUtil.updateEntity("default", loanSummarys[i]);
			}
		}
		// 综合授信生效时，将综合授信下的业务合同协议Id更新为综合授信协议ID
		if ("com.bos.dataset.crt.TbConCreditInfo".equals(entityName)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("creditContractId", contractId);
			Object[] xyCons = DatabaseExt.queryByNamedSql("default", "com.bos.conApply.conApply.getcreditContractId", map);
			for (int i = 0; i < xyCons.length; i++) {
				String xyContractId = (String) ((DataObject) xyCons[i]).get("CONTRACT_ID");
				String xyId = contractId;
				DataObject conInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
				conInfo.set("contractId", xyContractId);
				conInfo.set("xyId", xyId);
				DatabaseUtil.updateEntity("default", conInfo);
			}
		}
		//生效新的票据信息
		DataObject tbBizPjxxApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizPjxxApply");
		tbBizPjxxApply.set("htbh",con.getString("contractNum"));
		tbBizPjxxApply.set("amountDetailId",con.getString("amountDetailId"));
		tbBizPjxxApply.set("contractId",contractId);
		DataObject[] pjxxs = DatabaseUtil.queryEntitiesByTemplate("default", tbBizPjxxApply);
		if(null !=pjxxs && pjxxs.length>0){
			for (int j = 0; j < pjxxs.length; j++) {
				pjxxs[j].set("jlzt", "03");//生效
				DatabaseUtil.updateEntity("default", pjxxs[j]);
			}
		}
		if (null != oldContractId) {
			//失效老的票据信息
			tbBizPjxxApply.set("htbh",con.getString("contractNum"));
			tbBizPjxxApply.set("amountDetailId",con.getString("amountDetailId"));
			tbBizPjxxApply.set("contractId",oldContractId);
			DataObject[] pjxxs1 = DatabaseUtil.queryEntitiesByTemplate("default", tbBizPjxxApply);
			if(null !=pjxxs1 && pjxxs1.length>0){
				for (int k = 0; k < pjxxs1.length; k++) {
					pjxxs1[k].set("jlzt", "04");//失效
					DatabaseUtil.updateEntity("default", pjxxs1[k]);
				}
			}
		}
		// 处理担保合同,将担保合同生效，并将合同下的担保合同挂在综合授信协议下，或者业务下
		SubProcess sp = new SubProcess();
		sp.dealSubCon(conMap);
	}

	/**
	 * @param amountDetailId
	 *            合同发起，填充业务合同数据
	 * */
	@Bizlet("")
	public DataObject saveBizToCon(String amountDetailId) {
		if (null == amountDetailId || "".equals(amountDetailId)) {
			throw new EOSException("协议合同ID为空");
		}
		// 查询批复明细信息
		DataObject bizDetail = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
		bizDetail.set("amountDetailId", amountDetailId);
		DatabaseUtil.expandEntity("default", bizDetail);

		DataObject bizInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
		bizInfo.set("amountId", bizDetail.get("amountId"));
		DatabaseUtil.expandEntity("default", bizInfo);

		DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
		bizApprove.set("approveId", bizInfo.get("approveId"));
		DatabaseUtil.expandEntity("default", bizApprove);

		// 借新还旧时释放原贷款借据担保额度--20151229 -BUG #7971 生产环境中发起借新还旧时，之前押品未释放，担保不足值
		DataObject jxhj = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSummary");
		jxhj.set("applyId", bizApprove.get("applyId"));
		DatabaseUtil.expandEntityByTemplate("default", jxhj, jxhj);
		if (jxhj.get("summaryId") != null && !"".equals(jxhj.get("summaryId"))) {
			DataObject summaryInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			summaryInfo.set("summaryId", jxhj.get("summaryId"));
			DatabaseUtil.expandEntity("default", summaryInfo);
			if (summaryInfo.get("contractId") != null && !"".equals(summaryInfo.get("contractId"))) {
				ContractSub cs = new ContractSub();
				cs.delSubcontract(summaryInfo.get("contractId").toString());
			}
		}

		// 查询品种明细信息
		DataObject productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
		productInfo.set("productCd", bizDetail.get("productType"));
		DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);

		String entityName = productInfo.getString("entityName").replace("Apply", "Approve");
		DataObject productDetail = DataObjectUtil.createDataObject(entityName);
		productDetail.set("amountDetailId", amountDetailId);
		DatabaseUtil.expandEntityByTemplate("default", productDetail, productDetail);

		// 查询利率
		DataObject loanRate = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApprove");
		loanRate.set("amountDetailId", amountDetailId);
		DatabaseUtil.expandEntityByTemplate("default", loanRate, loanRate);

		// com.bos.dataset.biz.TbBizLdzjApprove
		// com.bos.dataset.crt.TbConLdzj
		entityName = entityName.replace("biz", "crt");
		entityName = entityName.replace("Biz", "Con");
		entityName = entityName.replace("Approve", "");

		// 如果是综合授信协议过来的业务，将协议编号保存进合同表
		DataObject creditInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConCreditInfo");
		creditInfo.set("applyId", bizApprove.get("applyId"));
		creditInfo.set("conStatus", "03");
		DatabaseUtil.expandEntityByTemplate("default", creditInfo, creditInfo);

		// 获取时间com.bos.pub.GitUtil.getBusiDate
		Date date = GitUtil.getBusiDate();

		//String conNum = BizNumGenerator.getBizNum("SEQ_CON_JK");
		//conNum = "JK" + conNum;
		String conNum = GitUtil.getSeqNo("HT", GitUtil.getCurrentOrgCd());

		// 保存业务合同信息
		DataObject conDetail = DataObjectUtil.convertDataObject(productDetail, entityName, true);

		DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
		tbConContractInfo.set("amountDetailId", amountDetailId);
		tbConContractInfo.set("createTime", date);
		tbConContractInfo.set("productType", bizDetail.get("productType"));
		// tbConContractInfo.set("contractTerm", bizDetail.get("creditTerm"));
		tbConContractInfo.set("cycleUnit", bizDetail.get("cycleUnit"));
		tbConContractInfo.set("partyId", bizApprove.get("partyId"));
		tbConContractInfo.set("beginDate", bizApprove.get("validDate"));
		Date endDate = (Date) bizDetail.get("endDate");
		/*
		 * int term = (Integer) bizDetail.get("creditTerm");
		 * 
		 * String cycleUnit = (String)bizDetail.get("cycleUnit"); if
		 * (cycleUnit.equals("01")) { endDate =
		 * DateHelper.calculateDate(beginDate, term, 0, 0); } else if
		 * (cycleUnit.equals("02")) { endDate =
		 * DateHelper.calculateDate(beginDate, 0, term * 6, 0); } else if
		 * (cycleUnit.equals("03")) { endDate =
		 * DateHelper.calculateDate(beginDate, 0, term * 3, 0); } else if
		 * (cycleUnit.equals("04")) { endDate =
		 * DateHelper.calculateDate(beginDate, 0, term, 0); } else if
		 * (cycleUnit.equals("05")) { endDate =
		 * DateHelper.calculateDate(beginDate, 0, 0, term); }
		 */
		tbConContractInfo.set("endDate", endDate);
		tbConContractInfo.set("guarantyType", bizInfo.get("guarantyType"));
		tbConContractInfo.set("repaymentType", bizDetail.get("repaymentType"));
		tbConContractInfo.set("exchangeRate", bizDetail.get("exchangeRate"));
		tbConContractInfo.set("currencyCd", bizDetail.get("currencyCd"));
		tbConContractInfo.set("cycleIndCon", bizDetail.get("cycleIndCon"));
		tbConContractInfo.set("contractNum", conNum);
		tbConContractInfo.set("paperConNum", conNum);
		tbConContractInfo.set("conStatus", "01");
		tbConContractInfo.set("userNum", GitUtil.getCurrentUserId());
		tbConContractInfo.set("orgNum", GitUtil.getCurrentOrgCd());
		Date validDate = (Date) bizApprove.get("validDate");
		if(validDate != null){//valiDate有为空的情况 
			Date tkdate = DateHelper.calculateDate(validDate, 0, 0, 30);
			tbConContractInfo.set("tkDate", tkdate);
		}
		tbConContractInfo.set("holidayFlg", bizDetail.get("holidayFlg"));
		if (creditInfo.get("contractId") != null && !"".equals(creditInfo.get("contractId"))) {
			tbConContractInfo.set("xyId", creditInfo.get("contractId"));
		}
		// 小微把贷款用途保存进合同基本信息
		if (conDetail.get("loanUse") != null && !"".equals(conDetail.get("loanUse"))) {
			tbConContractInfo.set("loanUse", conDetail.get("loanUse"));
		}

		tbConContractInfo.set("firstRepayTerm", bizDetail.get("firstRepayTerm"));
		tbConContractInfo.set("internalDays",CommonUtil.getDBConfigVal("sys","sys_internalDays"));

		DatabaseUtil.saveEntity("default", tbConContractInfo);

		// 汇票贴现要把所有票据的编号拼起来，逗号隔开--金额加起来
		if ("01006001".equals(tbConContractInfo.get("productType"))||"01006002".equals(tbConContractInfo.get("productType"))
				||"01006010".equals(tbConContractInfo.get("productType")) //村镇银行贴现产品
				) {
			String hpbh = "";
			BigDecimal hpamt = new BigDecimal("0");
			DataObject hp = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizPjxxApply");
			hp.set("amountDetailId", amountDetailId);
			DataObject[] hps = DatabaseUtil.queryEntitiesByTemplate("default", hp);
			if (hps != null) {
				for (int i = 0; i < hps.length; i++) {
					if (i == 0) {
						hpbh = (String) hps[i].get("pjhm");
					} else {
						hpbh = hpbh + "," + hps[i].get("pjhm");
					}
					// 计算金额总和
					Map<String, Object> params1 = new HashMap<String, Object>();
					params1.put("hpamt", hpamt);
					params1.put("hpje", (BigDecimal) hps[i].get("hpje"));
					hpamt = MathHelper.expressionBigDecimal("hpamt+hpje", params1);
				}
			}
			conDetail.set("hpTotalAmt", hpamt);
			conDetail.set("hpbh", hpbh);
			conDetail.set("txqq", date);
		}
		conDetail.set("contractId", tbConContractInfo.get("contractId"));
		conDetail.set("applyDetailId", null);
		conDetail.set("updateTime", null);
		conDetail.set("createTime", date);
		DatabaseUtil.saveEntity("default", conDetail);

		DataObject conLoanrate = DataObjectUtil.convertDataObject(loanRate, "com.bos.dataset.crt.TbConLoanrate", true);
		conLoanrate.set("contractId", tbConContractInfo.get("contractId"));
		conLoanrate.set("loanrateId", null);
		DatabaseUtil.saveEntity("default", conLoanrate);

		return tbConContractInfo;

	}

	// 将合同下的担保合同生效
	public void dealSubCon(Map<String, String> conMap) {
		DataObject con = DataObjectUtil.createDataObject(conMap.get("entityName"));
		con.set("contractId", conMap.get("contractId"));
		DatabaseUtil.expandEntity("default", con);
		// 查合同下的所有担保合同信息
		DataObject conRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
		conRel.set("contractId", con.getString("contractId"));
		DataObject[] conRels = DatabaseUtil.queryEntitiesByTemplate("default", conRel);
		
		// 查询合同下业务ID
		DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
		DataObject amountInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
		DataObject amountDetail = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");

		// System.out.println(com.eos.foundation.data.DataObjectUtil.getEntityName(con));

		if ("com.bos.dataset.crt.TbConContractInfo".equals(DataObjectUtil.getEntityName(con))) {
			amountDetail.set("amountDetailId", con.getString("amountDetailId"));
			DatabaseUtil.expandEntity("default", amountDetail);

			amountInfo.set("amountId", amountDetail.getString("amountId"));
			DatabaseUtil.expandEntity("default", amountInfo);

			bizApprove.set("approveId", amountInfo.getString("approveId"));
			DatabaseUtil.expandEntity("default", bizApprove);
		}

		for (int i = 0; i < conRels.length; i++) {
			// 将合同下的担保合同生效
			DataObject subCon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
			if (conRels[i].get("subcontractId") == null) {
				continue;
			}
			String subcontractId = ((DataObject) conRels[i]).getString("subcontractId");
			subCon.set("subcontractId", subcontractId);
			subCon.set("subcontractStatus", "03");
			DatabaseUtil.updateEntity("default", subCon);
			DatabaseUtil.expandEntity("default", subCon);

			if ("1".equals(subCon.getString("ifTopSubcon"))) {
				if (null != con.getString("xyId") && !"".equals(con.getString("xyId"))) {// 综合授信协议项下合同

					conRel.set("contractId", con.getString("xyId"));
					conRel.set("subcontractId", conRels[i].getString("subcontractId"));

					if (DatabaseUtil.countByTemplate("default", conRel) == 0) {// 如果担保合同不存在于该协议下，就将担保挂在合同对应的协议下
						conRels[i].set("conSubconId", null);
						conRels[i].set("contractId", con.getString("xyId"));
						conRels[i].set("updateTime", GitUtil.getBusiDate());
						conRels[i].set("createTime", GitUtil.getBusiDate());
						conRels[i].set("suretyAmt", null);
						DatabaseUtil.insertEntity("default", conRels[i]);
					}
				} else {// 将单笔业务合同下的担保合同挂着他对应 的业务下

					DataObject subBiz = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizGrtMaxloanconApply");
					subBiz.set("approveId", bizApprove.getString("approveId"));
					subBiz.set("applyId", bizApprove.getString("applyId"));
					subBiz.set("subcontractId", subCon.getString("subcontractId"));
					if (DatabaseUtil.countByTemplate("default", subBiz) == 0) {// 如果担保合同不存在于该业务下，将担保合同挂在业务下
						subBiz.set("maxloanconId", null);
						subBiz.set("approveId", bizApprove.getString("approveId"));
						subBiz.set("applyId", bizApprove.getString("applyId"));
						subBiz.set("subcontractId", subCon.getString("subcontractId"));
						subBiz.set("reType", subCon.getString("subcontractType"));
						subBiz.set("updateTime", GitUtil.getBusiDate());
						subBiz.set("createTime", GitUtil.getBusiDate());
						DatabaseUtil.insertEntity("default", subBiz);
					}
				}

			}

		}
		
		if(con.getString("oldContractId")!= null){
			// 业务老合同基本信息
			DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			tbConContractInfo.set("contractId", con.getString("oldContractId"));
			DatabaseUtil.expandEntityByTemplate("default", tbConContractInfo,tbConContractInfo);
			// 查老合同下的所有担保合同信息
			DataObject conRel1 = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
			conRel1.set("contractId", tbConContractInfo.getString("contractId"));
			DataObject[] conRels1 = DatabaseUtil.queryEntitiesByTemplate("default", conRel1);
			for (int i = 0; i < conRels1.length; i++) {
			// 将老合同下的担保合同失效
			DataObject subCon1 = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
				if (conRels1[i].get("subcontractId") != null) {
					
					//add by shangmf:20171018：删除担保合同和押品关联关系
					String grtRel_entityName="com.bos.dataset.crt.TbConSubGrtRel";
					DataObject grtRel_Con = DataObjectUtil.createDataObject(grtRel_entityName);
					//先查出关系relationId，然后删除
					grtRel_Con.set("subcontractId", ((DataObject) conRels1[i]).getString("subcontractId"));
					DataObject[] congrtRels = DatabaseUtil.queryEntitiesByTemplate("default", grtRel_Con);
					for(int j=0;j<congrtRels.length;j++){
						DataObject grtRel_Con_new = DataObjectUtil.createDataObject(grtRel_entityName);
						String relaid= ((DataObject) congrtRels[j]).getString("relationId");
						if(!"".equals(relaid) && relaid != null){
							grtRel_Con_new.set("relationId", relaid);
							DatabaseUtil.deleteEntity("default", grtRel_Con_new);										
						}
					}
					
					String subcontractId = ((DataObject) conRels1[i]).getString("subcontractId");
					subCon1.set("subcontractId", subcontractId);
					subCon1.set("subcontractStatus", "04");
					DatabaseUtil.updateEntity("default", subCon1);
					
					DatabaseUtil.expandEntity("default", subCon1);
					if ("1".equals(subCon1.getString("ifTopSubcon"))) {
						if (null != con.getString("xyId") && !"".equals(con.getString("xyId"))) {// 综合授信协议项下合同
							conRel1.set("contractId", con.getString("xyId"));
							conRel1.set("subcontractId", conRels[i].getString("subcontractId"));
							
							DatabaseUtil.deleteEntity("default", conRel1);
						} else {// 将单笔业务合同下的担保合同挂着他对应 的业务下
							DataObject subBiz1 = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizGrtMaxloanconApply");
							subBiz1.set("approveId", bizApprove.getString("approveId"));
							subBiz1.set("applyId", bizApprove.getString("applyId"));
							subBiz1.set("subcontractId", subCon1.getString("subcontractId"));
							DatabaseUtil.deleteEntity("default", subBiz1);
						}
					}
				}
			}
		}
		
		
		//modi by shangmf:20171110:处理新的最高额担保合同,挂接到其他用到此最高额担保合同的合同
		logger.info("处理新的最高额担保合同,挂接到其他用到此最高额担保合同的合同开始...");
		DataObject conRel_old = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
		conRel_old.set("contractId", con.getString("oldContractId"));
		logger.info("老合同id,oldContractId:"+con.getString("oldContractId"));
		logger.info("新合同id,contractId:"+con.getString("contractId"));
		
		if( con.getString("oldContractId")!= null ){
			
			DataObject[] conRels_old = DatabaseUtil.queryEntitiesByTemplate("default", conRel_old);
			for (int i = 0; i < conRels_old.length; i++) {
				
				logger.info("第:"+i+"次循环开始");
				//根据subcontractid查询是否为最高额担保合同
				String subcontractId_oldId = ((DataObject) conRels_old[i]).getString("subcontractId");
				DataObject subCon_old_obj = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");					
				subCon_old_obj.set("subcontractId", subcontractId_oldId);
				DatabaseUtil.expandEntity("default", subCon_old_obj);		
				String ifTopSubcon = ((DataObject) subCon_old_obj).getString("ifTopSubcon");
				
				//根据subcontractid查询subcontractNum		
				String subcontractNum_old = ((DataObject) subCon_old_obj).getString("subcontractNum");
				
				if ("1".equals(ifTopSubcon)) {
					
					//老担保合同id
					String subcontractId = ((DataObject) conRels_old[i]).getString("subcontractId");
				
					//根据老担保合同id找到相关联的列表
					DataObject conRel_old_cyc = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
					conRel_old_cyc.set("subcontractId", subcontractId);
					DataObject[] conRels_old_cyc = DatabaseUtil.queryEntitiesByTemplate("default", conRel_old_cyc);								
					
					//查询新的担保合同id:根据新的contractid查询新的担保合同id
					String subcontractId_new_final = null;	//最终的对应的担保合同id
					
					DataObject conRel_new = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
					conRel_new.set("contractId", con.getString("contractId"));
					DataObject[] conRels_new = DatabaseUtil.queryEntitiesByTemplate("default", conRel_new);
					for (int j = 0; j < conRels_new.length; j++) {
						
						String subcontractId_new = ((DataObject) conRels_new[j]).getString("subcontractId");
						DataObject subCon_new = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");					
						subCon_new.set("subcontractId", subcontractId_new);
						DatabaseUtil.expandEntity("default", subCon_new);
						
						String subcontractNum_new = ((DataObject) subCon_new).getString("subcontractNum");
						if( subcontractNum_new.equals(subcontractNum_old)){
							
							subcontractId_new_final = subcontractId_new;
							
						}
					}
					
					//将老担保合同id赋为新的担保合同id
					for (int k = 0; k < conRels_old_cyc.length; k++) {
						conRels_old_cyc[k].set("subcontractId", subcontractId_new_final);
						conRels_old_cyc[k].set("updateTime", GitUtil.getBusiTimestamp());
						conRels_old_cyc[k].set("subcontractIdBackups", subcontractId_oldId);
						
						logger.info("修改合同与担保合同记录,"+"担保合同号:"+subcontractNum_old+",关系id:"+conRels_old_cyc[k].get("conSubconId")+",新担保合同id:"+subcontractId_new_final+",旧担保合同id:"+subcontractId_oldId);
						
						DatabaseUtil.updateEntity("default", conRels_old_cyc[k]);
					}
					
				}
				
				logger.info("第:"+i+"次循环结束!");
			}
			
		}
		logger.info("处理新的最高额担保合同,挂接到其他用到此最高额担保合同的合同结束!");

	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
