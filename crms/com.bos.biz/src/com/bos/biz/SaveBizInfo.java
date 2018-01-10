package com.bos.biz;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.acc.AccFinanceChange;
import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.easyrule.util.DateHelper;
import com.primeton.bfs.tp.common.exception.EOSException;
import commonj.sdo.DataObject;

public class SaveBizInfo {
	public static TraceLogger logger = new TraceLogger(SaveBizInfo.class);
	/**
	 * 将业务申请数据覆盖到业务批复
	 * */
	public void saveApplyToApprove(String applyId,String conclusion) throws Exception{
		try {
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			bizApply.set("statusType", "03");
			DatabaseUtil.updateEntity("default", bizApply);
		logger.info("------3231------>业务申请流程结束：将申请数据挪到批复------bizId="+applyId+"------->开始!");
			//创建业务申请表，创建业务申请基本信息表，创建业务申请明细信息表
			bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			DataObject amountInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
			DataObject amountDetail = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApply");
			DataObject loanRate = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApply");
			

			/**
			 *1，查询出业务申请信息，业务基本信息，业务明细信息
			 *2，将申请信息转为批复并保存，将业务基本信息转为批复并保存
			 *3，循环明细信息，查询对应利率与品种明细，转为批复并保存
			 *4，更新申请表业务状态
			 * */

			//查询业务信息
			bizApply.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApply);

			//查询业务基本信息
			amountInfo.set("applyId", applyId);
			DatabaseUtil.expandEntityByTemplate("default", amountInfo, amountInfo);

			//查询明细
			amountDetail.set("amountId", amountInfo.get("amountId"));
			DataObject[] amountDetails = DatabaseUtil.queryEntitiesByTemplate("default", amountDetail);

			//获取时间com.bos.pub.GitUtil.getBusiDate
			Date date = GitUtil.getBusiDate();
			
			
			//转化为批复信息并保存
			DataObject bizApprove = DataObjectUtil.convertDataObject(bizApply, "com.bos.dataset.biz.TbBizApprove", true);
			if("05".equals(bizApply.get("bizType"))||"06".equals(bizApply.get("bizType"))){//个人统一授信客户业务06 对公05
				bizApprove.set("becomeEffectiveMark", "09");
			}else{
				bizApprove.set("becomeEffectiveMark", "03");
			}
			bizApprove.set("createTime", date);
			bizApprove.set("validDate", date);
			bizApprove.set("approvalNum", bizApply.get("bizNum"));
			bizApprove.set("approveConclusion", conclusion);
			
			//计算到期日期
			//综合授信、集团期限1年，单笔取明细期限
			//01-年  02-半年  03-季  04-月  05-日
			String cycleUnit = null;
			int creditTerm = 0;
			String bizType = bizApply.get("bizType").toString();
			Date endDate = date;
			if(bizType.equals("02")||bizType.equals("03")||bizType.equals("05")){//综合集团
				if(amountInfo.get("creditTerm")!=null){
					creditTerm = (Integer)amountInfo.get("creditTerm");
					cycleUnit = "04";
					endDate = DateHelper.calculateDate(date, 0, creditTerm, 0);
				}
			}
			if(bizType.equals("01")||bizType.equals("04")||bizType.equals("06")){//单笔、小微
				//批复明细改为直接选止期
				endDate = amountDetails[0].getDate("endDate");
			}
			bizApprove.set("endDate", endDate);
			DatabaseUtil.updateEntity("default", amountInfo);
			DatabaseUtil.saveEntity("default", bizApprove);
			
			
			//将集团下所有集团成员授信置为生效
			if("03".equals(bizApply.get("bizType"))){
				SaveBizInfo sbi = new SaveBizInfo();
				if("2".equals(conclusion)){//不同意的情况
					//14158 统一授信业务申请复议，已完成的批复信息丢失--拒贷后成员09状态的明细不变
					//sbi.endJtForJd((String)bizApprove.get("approveId"));
				}else{
					sbi.endJt((String)bizApprove.get("approveId"));
				}
			}
			
			DataObject amountInfoApprove = DataObjectUtil.convertDataObject(amountInfo, "com.bos.dataset.biz.TbBizAmountApprove", true);
			amountInfoApprove.set("approveId", bizApprove.get("approveId"));
			DatabaseUtil.saveEntity("default", amountInfoApprove);
		logger.info("------3231------>业务申请流程结束：将申请数据挪到批复------bizId="+applyId+"------->开始挪品种明细begin!");
			for(int i =0;i<=amountDetails.length-1;i++){
		logger.info("------3231------>业务申请流程结束：将申请数据挪到批复------bizId="+applyId+"------->开始挪明细数据开始!");
				amountDetail=null;
				DataObject productApply=null;
				DataObject productApprove=null;
				loanRate=null;
				DataObject productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
				loanRate = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApply");
				amountDetail=amountDetails[i];
		logger.info("------3231------>业务申请流程结束：将申请数据挪到批复，开始挪明细数据------bizId="+applyId+"------->productType="+amountDetail.get("productType"));
				//查询利率
				loanRate.set("amountDetailId", amountDetail.get("amountDetailId"));
				DatabaseUtil.expandEntityByTemplate("default", loanRate, loanRate);
				//查询品种明细
				productInfo.set("productCd", amountDetail.get("productType"));
				DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);
				
				productApply=DataObjectUtil.createDataObject(productInfo.getString("entityName"));
				productApply.set("amountDetailId", amountDetail.get("amountDetailId"));
				DatabaseUtil.expandEntityByTemplate("default", productApply, productApply);
				
				String entity = productInfo.getString("entityName").replace("Apply", "Approve");
				productApprove = DataObjectUtil.convertDataObject(productApply, entity, true);
				
				if(null!=loanRate){
					DataObject loanRateApprove =DataObjectUtil.convertDataObject(loanRate, "com.bos.dataset.biz.TbBizAmountLoanrateApprove", true);
					DatabaseUtil.saveEntity("default", loanRateApprove);
				}
		logger.info("------3231------>业务申请流程结束：将申请数据挪到批复，开始挪明细数据------bizId="+applyId+"------->明细利率完成!");
				DatabaseUtil.saveEntity("default", productApprove);
				
				DataObject amountDetailApprove = DataObjectUtil.convertDataObject(amountDetail, "com.bos.dataset.biz.TbBizAmountDetailApprove", true);
				//提前还款是否收取违约金
				if(null==amountDetail.get("prepaymentPenalty")){
					
				}else{
				amountDetailApprove.set("prepaymentPenalty", amountDetail.get("prepaymentPenalty"));
				}
				//还款来源
				if(null==amountDetail.get("payment")){
					
				}else{
				amountDetailApprove.set("payment", amountDetail.get("payment"));
				}
				DatabaseUtil.saveEntity("default", amountDetailApprove);
			}
		logger.info("------3231------>业务申请流程结束：将申请数据挪到批复------bizId="+applyId+"------->挪品种明细数据end!");

			bizApply.set("applyId", applyId);
			if("05".equals(bizApply.get("bizType"))||"06".equals(bizApply.get("bizType"))){
				bizApply.set("statusType", "09");	
			}else{
				bizApply.set("statusType", "03");
			}
			DatabaseUtil.updateEntity("default", bizApply);
			
			SaveBizInfo sbi = new SaveBizInfo();
			//原业务申请信息置为失效
			if((null != bizApply.get("oldApplyId"))&&(!"2".equals(conclusion))&&(!"05".equals(bizApply.get("bizType")))&&(!"06".equals(bizApply.get("bizType")))){
				//将原业务置为失效
				sbi.updateOldApv04((String)bizApply.get("applyId"));
				//原业务下合同关联到新的批复下
				sbi.saveOldConToNew((String)bizApply.get("applyId"));
			}
			//如果是综合授信，并且审批结论为同意   将该客户下的所有非银团非低的业务置为失效  （除该笔业务）
			if((("02".equals(bizApply.get("bizType"))))&&(!"2".equals(conclusion))){
				
				//将客户下其他非低非银团的业务置为失效
				sbi.updateOtherApv04((String)bizApply.get("applyId"));
				//原业务下合同关联到新的批复下
				sbi.saveOldConToNew((String)bizApply.get("applyId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程业务处理失败");
		}
		if (null == applyId || "".equals(applyId)) {
			throw new EOSException("流程返回的申请ID为空");
		}
	}
	
	
	
	/**
	 * 将业务批复数据覆盖到业务申请	 用于	除调整外   方法
	 * 综合授信调整时，将批复信息挪到业务申请
	 * 生成一条新的批复信息
	 * @throws Throwable 
	 * */
	public DataObject saveApproveToApply(String approveId) throws Throwable {
		try {
			if (null == approveId || "".equals(approveId)) {
				throw new EOSException("批复ID为空");
			}
			//批复信息
			DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("approveId", approveId);
			DatabaseUtil.expandEntity("default", bizApprove);
			
			//获取时间com.bos.pub.GitUtil.getBusiDate
			Date date = GitUtil.getBusiDate();
			
			//批复信息转化为申请信息并保存
			DataObject bizApply = DataObjectUtil.convertDataObject(bizApprove, "com.bos.dataset.biz.TbBizApply", true);
			bizApply.set("createTime", date);
			bizApply.set("oldApplyId", bizApprove.get("applyId"));
			bizApply.set("applyId", null);
			DatabaseUtil.insertEntity("default", bizApply);
			
			
			//基本信息
			DataObject amountApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
			amountApprove.set("approveId", approveId);
			DatabaseUtil.expandEntityByTemplate("default", amountApprove, amountApprove);
			DataObject amountApply = DataObjectUtil.convertDataObject(amountApprove, "com.bos.dataset.biz.TbBizAmountApply", true);
			
			String oldAmountId = amountApply.get("amountId").toString();
			amountApply.set("applyId", bizApply.get("applyId"));
			amountApply.set("creditAmount", new BigDecimal(0));
			amountApply.set("amountId", null);
			DatabaseUtil.insertEntity("default", amountApply);
			
			//监管保送  tb_biz_yesorno_apply
			DataObject yesOrNo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizYesornoApply");
			yesOrNo.set("applyId", bizApprove.get("applyId"));
			DatabaseUtil.expandEntityByTemplate("default", yesOrNo, yesOrNo);
			yesOrNo.set("yesornoId",null);
			yesOrNo.set("applyId",bizApply.get("applyId"));
			DatabaseUtil.insertEntity("default", yesOrNo);
			
			//担保评价
			DataObject bizGrtRel = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizGrtRel");
			bizGrtRel.set("applyId", bizApprove.get("applyId"));
			DataObject[] bizGrtRels = DatabaseUtil.queryEntitiesByTemplate("default", bizGrtRel);
			for(int i = 0;i<=bizGrtRels.length-1;i++){
				bizGrtRels[i].set("applyId", bizApply.get("applyId"));
				bizGrtRels[i].set("relationId", null);
				bizGrtRels[i].set("createTime", date);
				bizGrtRels[i].set("updateTime", date);
				DatabaseUtil.insertEntity("default", bizGrtRels[i]);
			}
			
			//最高额担保com.bos.dataset.biz.TbBizGrtMaxloanconApply
			DataObject maxGrt = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizGrtMaxloanconApply");
			maxGrt.set("applyId", bizApprove.get("applyId"));
			DataObject[] maxGrts = DatabaseUtil.queryEntitiesByTemplate("default", maxGrt);
			for(int i = 0;i<=maxGrts.length-1;i++){
				maxGrts[i].set("applyId", bizApply.get("applyId"));
				maxGrts[i].set("relationId", null);
				maxGrts[i].set("createTime", date);
				maxGrts[i].set("updateTime", date);
				DatabaseUtil.insertEntity("default", maxGrts[i]);
			}
			
			//银团	tb_biz_bank_struct_apply
			DataObject bankStruct = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizBankStructApply");
			bankStruct.set("applyId", bizApprove.get("applyId"));
			DatabaseUtil.expandEntityByTemplate("default", bankStruct, bankStruct);

			if(null != bankStruct.get("syndicatedStructId")){
				//银团成员	tb_biz_bank_member_apply
				DataObject bankMember = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizBankMemberApply");
				bankMember.set("syndicatedStructId", bankStruct.get("syndicatedStructId"));
				DataObject[] bankMembers = DatabaseUtil.queryEntitiesByTemplate("default", bankMember);
				
				bankStruct.set("syndicatedStructId",null);
				bankStruct.set("applyId",bizApply.get("applyId"));
				DatabaseUtil.insertEntity("default", bankStruct);
				
				for(int i =0;i<=bankMembers.length-1;i++){
					bankMember = null;
					bankMember = bankMembers[i];
					bankMember.set("syndicatedMemberId", null);
					bankMember.set("syndicatedStructId", bankStruct.get("syndicatedStructId"));
					DatabaseUtil.insertEntity("default", bankMember);
				}
			}
			/*//贴息	tb_biz_tx  com.bos.dataset.biz.TbBizTx-----二期贴息信息改为列表形式
			DataObject tx = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizTx");
			tx.set("applyId", bizApprove.get("applyId"));
			DatabaseUtil.expandEntityByTemplate("default", tx, tx);
			System.out.println(tx.get("txzt1"));
			if(tx.get("txzt1")!=null&&!"".equals(tx.get("txzt1"))){
				tx.set("txId", null);
				tx.set("applyId", bizApply.get("applyId"));
				DatabaseUtil.insertEntity("default", tx);
			}*/
			//贴息
			DataObject tx = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizTx");
			tx.set("applyId", bizApprove.get("applyId"));
			DataObject[] txs = DatabaseUtil.queryEntitiesByTemplate("default",tx);
			if(null != txs && txs.length!=0){
				for(int i = 0; i<txs.length;i++){
					tx = txs[i];
					tx.set("applyId", bizApply.get("applyId"));
					tx.set("txId", null);
					DatabaseUtil.insertEntity("default", tx);
				}
			}
			//项目额度
			DataObject xm = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXmxxApply");
			xm.set("amountDetailId", bizApprove.get("applyId"));
			DatabaseUtil.expandEntityByTemplate("default", xm, xm);
			if(xm.get("projectId")!=null&&!"".equals(tx.get("projectId"))){
				xm.set("relateId", null);
				xm.set("amountDetailId", bizApply.get("applyId"));
				DatabaseUtil.insertEntity("default", xm);
			}
			
			//自然人贷款--家庭财务信息
			if("04".equals(bizApply.get("bizType"))||"06".equals(bizApply.get("bizType"))){
				String productType = (String) bizApply.get("productType");
				//消费--存在单独的表中
				if(productType.indexOf("020020")!=-1||productType.indexOf("020030")!=-1||productType.indexOf("020040")!=-1){
					DataObject cw = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizFamilyFinance");
					cw.set("applyId", bizApprove.get("applyId"));
					DatabaseUtil.expandEntityByTemplate("default", cw, cw);
					if(cw.get("partyId")!=null&&!"".equals(cw.get("partyId"))){
						cw.set("financeId", null);
						cw.set("applyId", bizApply.get("applyId"));
						DatabaseUtil.insertEntity("default", cw);
					}
				}else{//经营
					AccFinanceChange.copyFinancesByBizId((String)bizApprove.get("applyId"), (String)bizApply.get("applyId"));
				}
			}
			
			//借新还旧信息 
			DataObject jxhj = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSummary");
			jxhj.set("applyId", bizApprove.get("applyId"));
			DatabaseUtil.expandEntityByTemplate("default", jxhj, jxhj);
			if(jxhj.get("summaryId")!=null&&!"".equals(jxhj.get("summaryId"))){
				jxhj.set("id", null);
				jxhj.set("applyId", bizApply.get("applyId"));
				DatabaseUtil.insertEntity("default", jxhj);
			}
			//批复明细信息
			DataObject detailApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
			detailApprove.set("amountId", oldAmountId);
			DataObject[] detailApproves = DatabaseUtil.queryEntitiesByTemplate("default", detailApprove);
			
			
			DataObject productApprove;
			DataObject productApply;
			DataObject detailApply;
			
			//循环批复明细信息
			for(int i =0;i<=detailApproves.length-1;i++){
				//detailApprove=null;
				//detailApprove=detailApproves[i];
				DataObject loanRateApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApprove");
				DataObject productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
				//查询利率
				loanRateApprove.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DatabaseUtil.expandEntityByTemplate("default", loanRateApprove, loanRateApprove);
				
				//查询品种配置
				productInfo.set("productCd", detailApproves[i].get("productType"));
				DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);
				//批复品种
				String entity = productInfo.getString("entityName").replace("Approve", "Apply");
				productApprove=DataObjectUtil.createDataObject(entity);
				productApprove.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DatabaseUtil.expandEntityByTemplate("default", productApprove, productApprove);
				
				//转化申请并保存
				detailApply = DataObjectUtil.convertDataObject(detailApproves[i],"com.bos.dataset.biz.TbBizAmountDetailApply", true);
				detailApply.set("amountId", amountApply.get("amountId"));
				detailApply.set("amountDetailId", null);
				detailApply.set("oldDetailId",detailApproves[i].get("amountDetailId"));
				detailApply.set("oldApplyId",bizApprove.get("applyId"));
				DatabaseUtil.insertEntity("default", detailApply);
				
				productApply = DataObjectUtil.convertDataObject(productApprove, productInfo.getString("entityName"), true);
				productApply.set("amountDetailId",detailApply.get("amountDetailId"));
				productApply.set("applyDetailId",null);
				DatabaseUtil.insertEntity("default", productApply);
				
				if(null!=loanRateApprove){
				DataObject loanRateApply =DataObjectUtil.convertDataObject(loanRateApprove, "com.bos.dataset.biz.TbBizAmountLoanrateApply", true);
				loanRateApply.set("amountDetailId",detailApply.get("amountDetailId"));
				loanRateApply.set("loanrateId", null);
				DatabaseUtil.insertEntity("default", loanRateApply);
				}
				
				//小微还款计划
				DataObject hkjh = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXwHkjh");
				hkjh.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DataObject[] hkjhs = DatabaseUtil.queryEntitiesByTemplate("default", hkjh);
				if(hkjhs.length!=0){
					DataObject[] resultObjects = new DataObject[hkjhs.length];
					for(int k=0;k<hkjhs.length;k++){
						hkjh = hkjhs[k];
						hkjh.set("amountDetailId", detailApply.get("amountDetailId"));
						hkjh.set("uuid", null);
						resultObjects[k] = hkjh;
					}
					DatabaseUtil.saveEntities("default", resultObjects);
				}
				//国内保理费率信息
				DataObject fl = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConFee");
				fl.set("contractDetailId", detailApproves[i].get("amountDetailId"));//此处存的就是amountDetailId
				DataObject[] fls = DatabaseUtil.queryEntitiesByTemplate("default", fl);
				if(fls!=null && fls.length!=0){
					DataObject[] resultObjects = new DataObject[fls.length];
					for(int k=0;k<fls.length;k++){
						fl = fls[k];
						fl.set("contractDetailId", detailApply.get("amountDetailId"));
						hkjh.set("feeId", null);
						resultObjects[k] = fl;
					}
					DatabaseUtil.saveEntities("default", resultObjects);
				}
				
				//汇票贴现票据信息
				DataObject pjxx = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizPjxxApply");
				pjxx.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DataObject[] pjs = DatabaseUtil.queryEntitiesByTemplate("default", pjxx);
				if(pjs!=null && pjs.length!=0){
					DataObject[] resultObjects = new DataObject[pjs.length];
					for(int k=0;k<pjs.length;k++){
						pjxx = pjs[k];
						pjxx.set("amountDetailId", detailApply.get("amountDetailId"));
						pjxx.set("applyDetailId", null);
						resultObjects[k] = pjxx;
					}
					DatabaseUtil.saveEntities("default", resultObjects);
				}
				//固贷项目信息
				DataObject xmxx = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXmxxApply");
				xmxx.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DataObject[] xmxxs = DatabaseUtil.queryEntitiesByTemplate("default", xmxx);
				if(xmxxs!=null && xmxxs.length!=0){
					DataObject[] resultObjects = new DataObject[xmxxs.length];
					for(int k=0;k<xmxxs.length;k++){
						xmxx = xmxxs[k];
						xmxx.set("amountDetailId", detailApply.get("amountDetailId"));
						xmxx.set("relateId", null);
						resultObjects[k] = xmxx;
					}
					DatabaseUtil.saveEntities("default", resultObjects);
				}
			}
			return bizApply;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("复制业务数据异常");
		}
	}
	/**
	 * 将业务批复数据覆盖到业务申请	 用于调整
	 * 综合授信调整时，将所有非低，非银团的批复信息挪到业务申请
	 * 生成一条新的批复信息
	 * @throws Throwable 
	 * */
	public DataObject saveApproveToApplyTZ(String approveId) throws Throwable {
		try {
			if (null == approveId || "".equals(approveId)) {
				throw new EOSException("批复ID为空");
			}
			//批复信息
			DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("approveId", approveId);
			DatabaseUtil.expandEntity("default", bizApprove);
			
			//获取时间com.bos.pub.GitUtil.getBusiDate
			Date date = GitUtil.getBusiDate();
			
			//批复信息转化为申请信息并保存
			DataObject bizApply = DataObjectUtil.convertDataObject(bizApprove, "com.bos.dataset.biz.TbBizApply", true);
			bizApply.set("createTime", date);
			bizApply.set("oldApplyId", bizApprove.get("applyId"));
			bizApply.set("applyId", null);
			bizApply.set("userNum", GitUtil.getCurrentUserId());
			bizApply.set("orgNum", GitUtil.getCurrentOrgCd());
			DatabaseUtil.insertEntity("default", bizApply);
			
			//测算信息
			DataObject tbSysFlowTest = DataObjectUtil.createDataObject("com.bos.pub.sys.TbSysFlowTest");
			tbSysFlowTest.set("applyId",bizApprove.get("applyId"));
			DataObject[] tbSysFlowTestList = DatabaseUtil.queryEntitiesByTemplate("default", tbSysFlowTest);
			if(tbSysFlowTestList!=null&&tbSysFlowTestList.length>0){
				for(DataObject tbSysFlowTest_:tbSysFlowTestList){
					tbSysFlowTest_.set("testFlowNo", "");
					tbSysFlowTest_.set("applyId", bizApply.get("applyId"));
				}
			}
			DatabaseUtil.insertEntityBatch("default", tbSysFlowTestList);
			
			//基本信息
			DataObject amountApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
			amountApprove.set("approveId", approveId);
			DatabaseUtil.expandEntityByTemplate("default", amountApprove, amountApprove);
			DataObject amountApply = DataObjectUtil.convertDataObject(amountApprove, "com.bos.dataset.biz.TbBizAmountApply", true);
			
			String oldAmountId = amountApply.get("amountId").toString();
			amountApply.set("applyId", bizApply.get("applyId"));
			amountApply.set("creditAmount", new BigDecimal(0));
			amountApply.set("amountId", null);
			DatabaseUtil.insertEntity("default", amountApply);
			
			//监管报送  tb_biz_yesorno_apply
			DataObject yesOrNo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizYesornoApply");
			yesOrNo.set("applyId", bizApprove.get("applyId"));
			DatabaseUtil.expandEntityByTemplate("default", yesOrNo, yesOrNo);
			yesOrNo.set("yesornoId",null);
			yesOrNo.set("applyId",bizApply.get("applyId"));
			DatabaseUtil.insertEntity("default", yesOrNo);
					
			//担保评价
			DataObject bizGrtRel = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizGrtRel");
			bizGrtRel.set("applyId", bizApprove.get("applyId"));
			DataObject[] bizGrtRels = DatabaseUtil.queryEntitiesByTemplate("default", bizGrtRel);
			for(int i = 0;i<=bizGrtRels.length-1;i++){
				bizGrtRels[i].set("applyId", bizApply.get("applyId"));
				bizGrtRels[i].set("relationId", null);
				bizGrtRels[i].set("createTime", date);
				bizGrtRels[i].set("updateTime", date);
				DatabaseUtil.insertEntity("default", bizGrtRels[i]);
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("suretyId",  bizGrtRels[i].get("suretyId"));
				//调押品接口。同步押品与业务关联关系
				Object[] data = DatabaseExt.queryByNamedSql("default", 
						"com.bos.grt.grt.basicQuery", map);
				if(data != null && data.length>0){
					DataObject dataObject = (DataObject) data[0];
					map.put("cltNo",dataObject.getString("SURETY_NO"));
					//调接口，同步数据
					CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
					CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();

					ObjectMapper mapper = new ObjectMapper();
					map.put("applyId",  bizApply.get("applyId"));
					map.put("trans_code", "1114");//接口交易码
					map.put("ope_flag", "apply");//操作标志
					// Convert object to JSON string  
					String ypxxJsonStr = null;
					ypxxJsonStr = mapper.writeValueAsString(map);
					ser.setIn0(ypxxJsonStr);
					String flag = stub.collServiceCommInter(ser).getOut1();
				}
			}
			
			//最高额担保com.bos.dataset.biz.TbBizGrtMaxloanconApply
			DataObject maxGrt = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizGrtMaxloanconApply");
			maxGrt.set("applyId", bizApprove.get("applyId"));
			DataObject[] maxGrts = DatabaseUtil.queryEntitiesByTemplate("default", maxGrt);
			for(int i = 0;i<=maxGrts.length-1;i++){
				maxGrts[i].set("applyId", bizApply.get("applyId"));
				maxGrts[i].set("relationId", null);
				maxGrts[i].set("createTime", date);
				maxGrts[i].set("updateTime", date);
				DatabaseUtil.insertEntity("default", maxGrts[i]);
			}
			
			//银团	tb_biz_bank_struct_apply
			DataObject bankStruct = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizBankStructApply");
			bankStruct.set("applyId", bizApprove.get("applyId"));
			DatabaseUtil.expandEntityByTemplate("default", bankStruct, bankStruct);

			if(null != bankStruct.get("syndicatedStructId")){
				//银团成员	tb_biz_bank_member_apply
				DataObject bankMember = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizBankMemberApply");
				bankMember.set("syndicatedStructId", bankStruct.get("syndicatedStructId"));
				DataObject[] bankMembers = DatabaseUtil.queryEntitiesByTemplate("default", bankMember);
				
				bankStruct.set("syndicatedStructId",null);
				bankStruct.set("applyId",bizApply.get("applyId"));
				DatabaseUtil.insertEntity("default", bankStruct);
				
				for(int i =0;i<=bankMembers.length-1;i++){
					bankMember = null;
					bankMember = bankMembers[i];
					bankMember.set("syndicatedMemberId", null);
					bankMember.set("syndicatedStructId", bankStruct.get("syndicatedStructId"));
					DatabaseUtil.insertEntity("default", bankMember);
				}
			}
			//贴息
			DataObject tx = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizTx");
			tx.set("applyId", bizApprove.get("applyId"));
			DataObject[] txs = DatabaseUtil.queryEntitiesByTemplate("default",tx);
			if(null != txs && txs.length!=0){
				for(int i = 0; i<txs.length;i++){
					tx = txs[i];
					tx.set("applyId", bizApply.get("applyId"));
					tx.set("txId", null);
					DatabaseUtil.insertEntity("default", tx);
				}
			}
			//项目额度
			DataObject xm = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXmxxApply");
			xm.set("amountDetailId", bizApprove.get("applyId"));
			DatabaseUtil.expandEntityByTemplate("default", xm, xm);
			if(xm.get("projectId")!=null&&!"".equals(tx.get("projectId"))){
				xm.set("relateId", null);
				xm.set("amountDetailId", bizApply.get("applyId"));
				DatabaseUtil.insertEntity("default", xm);
			}
			
			//自然人贷款--家庭财务信息
			if("04".equals(bizApply.get("bizType"))||"06".equals(bizApply.get("bizType"))){
				String productType = (String) bizApply.get("productType");
				//消费--存在单独的表中
				if(productType.indexOf("020020")!=-1||productType.indexOf("020030")!=-1||productType.indexOf("020040")!=-1){
					DataObject cw = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizFamilyFinance");
					cw.set("applyId", bizApprove.get("applyId"));
					DatabaseUtil.expandEntityByTemplate("default", cw, cw);
					if(cw.get("partyId")!=null&&!"".equals(cw.get("partyId"))){
						cw.set("financeId", null);
						cw.set("applyId", bizApply.get("applyId"));
						DatabaseUtil.insertEntity("default", cw);
					}
				}else{//经营
					AccFinanceChange.copyFinancesByBizId((String)bizApprove.get("applyId"), (String)bizApply.get("applyId"));
				}
			}
			
			//借新还旧信息 
			DataObject jxhj = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSummary");
			jxhj.set("applyId", bizApprove.get("applyId"));
			DatabaseUtil.expandEntityByTemplate("default", jxhj, jxhj);
			if(jxhj.get("summaryId")!=null&&!"".equals(jxhj.get("summaryId"))){
				jxhj.set("id", null);
				jxhj.set("applyId", bizApply.get("applyId"));
				DatabaseUtil.insertEntity("default", jxhj);
			}		
			//批复明细信息
			DataObject detailApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
			detailApprove.set("amountId", oldAmountId);
			DataObject[] detailApproves = DatabaseUtil.queryEntitiesByTemplate("default", detailApprove);
			
			DataObject productApprove;
			DataObject productApply;
			DataObject detailApply;
			
			//循环批复明细信息
			for(int i =0;i<=detailApproves.length-1;i++){
				detailApprove=null;
				//detailApprove=detailApproves[i];
				DataObject loanRateApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApprove");
				DataObject productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
				//查询利率
				loanRateApprove.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DatabaseUtil.expandEntityByTemplate("default", loanRateApprove, loanRateApprove);
				
				//查询品种配置
				productInfo.set("productCd", detailApproves[i].get("productType"));
				DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);
				//批复品种
				String entity = productInfo.getString("entityName").replace("Approve", "Apply");
				productApprove=DataObjectUtil.createDataObject(entity);
				productApprove.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DatabaseUtil.expandEntityByTemplate("default", productApprove, productApprove);
				
				//转化申请并保存
				detailApply = DataObjectUtil.convertDataObject(detailApproves[i],"com.bos.dataset.biz.TbBizAmountDetailApply", true);
				detailApply.set("amountId", amountApply.get("amountId"));
				detailApply.set("amountDetailId", null);
				detailApply.set("oldDetailId",detailApproves[i].get("amountDetailId"));
				detailApply.set("oldApplyId",bizApprove.get("applyId"));
				DatabaseUtil.insertEntity("default", detailApply);
				
				productApply = DataObjectUtil.convertDataObject(productApprove, productInfo.getString("entityName"), true);
				productApply.set("amountDetailId",detailApply.get("amountDetailId"));
				productApply.set("applyDetailId",null);
				DatabaseUtil.insertEntity("default", productApply);
				
				if(null!=loanRateApprove){
				DataObject loanRateApply =DataObjectUtil.convertDataObject(loanRateApprove, "com.bos.dataset.biz.TbBizAmountLoanrateApply", true);
				loanRateApply.set("amountDetailId",detailApply.get("amountDetailId"));
				loanRateApply.set("loanrateId", null);
				DatabaseUtil.insertEntity("default", loanRateApply);
				}
				
				//小微还款计划
				DataObject hkjh = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXwHkjh");
				hkjh.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DataObject[] hkjhs = DatabaseUtil.queryEntitiesByTemplate("default", hkjh);
				if(hkjhs.length!=0){
					DataObject[] resultObjects = new DataObject[hkjhs.length];
					for(int k=0;k<hkjhs.length;k++){
						hkjh = hkjhs[k];
						hkjh.set("amountDetailId", detailApply.get("amountDetailId"));
						hkjh.set("uuid", null);
						resultObjects[k] = hkjh;
					}
					DatabaseUtil.saveEntities("default", resultObjects);
				}
				//国内保理费率信息
				DataObject fl = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConFee");
				fl.set("contractDetailId", detailApproves[i].get("amountDetailId"));//此处存的就是amountDetailId
				DataObject[] fls = DatabaseUtil.queryEntitiesByTemplate("default", fl);
				if(fls.length!=0){
					DataObject[] resultObjects = new DataObject[fls.length];
					for(int k=0;k<fls.length;k++){
						fl = fls[k];
						fl.set("contractDetailId", detailApply.get("amountDetailId"));
						hkjh.set("feeId", null);
						resultObjects[k] = fl;
					}
					DatabaseUtil.saveEntities("default", resultObjects);
				}
				//汇票贴现票据信息
				DataObject pjxx = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizPjxxApply");
				pjxx.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DataObject[] pjs = DatabaseUtil.queryEntitiesByTemplate("default", pjxx);
				if(pjs!=null && pjs.length!=0){
					DataObject[] resultObjects = new DataObject[pjs.length];
					for(int k=0;k<pjs.length;k++){
						pjxx = pjs[k];
						pjxx.set("amountDetailId", detailApply.get("amountDetailId"));
						pjxx.set("applyDetailId", null);
						resultObjects[k] = pjxx;
					}
					DatabaseUtil.saveEntities("default", resultObjects);
				}
				
				//贴现票据信息
				DataObject txxx = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizTxxxApply");
				txxx.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DataObject[] txxxs = DatabaseUtil.queryEntitiesByTemplate("default", txxx);
				if(txxxs!=null && txxxs.length!=0){
					DataObject[] resultObjects = new DataObject[txxxs.length];
					for(int k=0;k<txxxs.length;k++){
						txxx = txxxs[k];
						txxx.set("amountDetailId", detailApply.get("amountDetailId"));
						txxx.set("applyDetailId", null);
						resultObjects[k] = txxx;
					}
					DatabaseUtil.saveEntities("default", resultObjects);
				}
				
				//固贷项目信息
				DataObject xmxx = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizXmxxApply");
				xmxx.set("amountDetailId", detailApproves[i].get("amountDetailId"));
				DataObject[] xmxxs = DatabaseUtil.queryEntitiesByTemplate("default", xmxx);
				if(xmxxs!=null && xmxxs.length!=0){
					DataObject[] resultObjects = new DataObject[xmxxs.length];
					for(int k=0;k<xmxxs.length;k++){
						xmxx = xmxxs[k];
						xmxx.set("amountDetailId", detailApply.get("amountDetailId"));
						xmxx.set("relateId", null);
						resultObjects[k] = xmxx;
					}
					DatabaseUtil.saveEntities("default", resultObjects);
				}
			}
			//综合授信明细 包括该客户下所有非银团，非低的业务品种
			if("02".equals(bizApply.getString("bizType"))){
				//创建明细信息//查询需要被包含的批复//综合授信明细 包括该客户下所有非银团，非低的业务品种
				HashMap map = new HashMap();
				map.put("partyId", bizApply.get("partyId"));
				Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.bizApply.getDetailId", map);
				
				DataObject loanRateApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApprove");
				DataObject productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
				
				for(int i=0;i<objs.length;i++){
					String amountDetailId = (String) (((DataObject) objs[i]).get("AMOUNTDETAILID"));
			logger.info("------3231------>发起综合授信时，非银团，非低已签合同amountDetailId------>"+amountDetailId);
					detailApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
					detailApprove.set("amountDetailId", amountDetailId);
					DatabaseUtil.expandEntity("default", detailApprove);
					
					//查询利率
					loanRateApprove.set("amountDetailId", detailApprove.get("amountDetailId"));
					DatabaseUtil.expandEntityByTemplate("default", loanRateApprove, loanRateApprove);
					
					//查询品种配置
					productInfo.set("productCd", detailApprove.get("productType"));
					DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);
					//批复品种
					String entity = productInfo.getString("entityName").replace("Approve", "Apply");
					productApprove=DataObjectUtil.createDataObject(entity);
					productApprove.set("amountDetailId", detailApprove.get("amountDetailId"));
					DatabaseUtil.expandEntityByTemplate("default", productApprove, productApprove);
					
					//转化申请并保存
					detailApply = DataObjectUtil.convertDataObject(detailApprove,"com.bos.dataset.biz.TbBizAmountDetailApply", true);
					detailApply.set("amountId", amountApply.get("amountId"));
					detailApply.set("oldDetailId", detailApply.get("amountDetailId"));
					detailApply.set("amountDetailId", null);
					detailApply.set("detailAmt", null);
					DatabaseUtil.insertEntity("default", detailApply);
					
					productApply = DataObjectUtil.convertDataObject(productApprove, productInfo.getString("entityName"), true);
					productApply.set("amountDetailId",detailApply.get("amountDetailId"));
					productApply.set("applyDetailId",null);
					DatabaseUtil.insertEntity("default", productApply);
					
					if(null!=loanRateApprove){
						DataObject loanRateApply =DataObjectUtil.convertDataObject(loanRateApprove, "com.bos.dataset.biz.TbBizAmountLoanrateApply", true);
						loanRateApply.set("amountDetailId",detailApply.get("amountDetailId"));
						loanRateApply.set("loanrateId", null);
						DatabaseUtil.insertEntity("default", loanRateApply);
					}
				}
				
			}
			return bizApply;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("复制业务数据异常");
		}
	}
	/**
	 * 集团综合授信时，结束该集团下的所有子流程
	 * 将该集团下所有成员的成员综合授信置为生效
	 * */
	public void endJt(String approveId){
		try {
			DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("approveId", approveId);
			DatabaseUtil.expandEntity("default", bizApprove);
			SaveBizInfo sbi = new SaveBizInfo();
			if("03".equals(bizApprove.get("bizType"))){
				DataObject partyGroup = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
				partyGroup.set("groupPartyId", bizApprove.get("partyId"));
				partyGroup.set("status", bizApprove.get("03"));
				DataObject[] objs = DatabaseUtil.queryEntitiesByTemplate("default", partyGroup);
				for(int i=0;i<objs.length;i++){
					DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
					obj.set("partyId", (String)objs[i].get("corporationPartyId"));
					obj.set("bizType", "05");
					obj.set("becomeEffectiveMark", "09");
					DatabaseUtil.expandEntityByTemplate("default", obj, obj);
					if(null != obj.get("approveId")){
						//批复置为生效
						obj.set("becomeEffectiveMark", "03");
						DatabaseUtil.updateEntity("default", obj);
						
						//原批复置为失效
						sbi.updateOldApv04(obj.getString("applyId"));
						//客户下非低非银团的业务置为失效
						sbi.updateOtherApv04(obj.getString("applyId"));
						//原批复下的合同挪到新的批复下
						sbi.saveOldConToNew(obj.getString("applyId"));
					}
				}
				//20160719  增加自然人成员的处理逻辑
				for(int i=0;i<objs.length;i++){
					DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
					obj.set("partyId", (String)objs[i].get("corporationPartyId"));
					obj.set("bizType", "06");
					obj.set("becomeEffectiveMark", "09");
					DataObject[] apps = DatabaseUtil.queryEntitiesByTemplate("default", obj);
					if(null != apps && apps.length!=0){
						for(int j=0;j<apps.length ; j++){
							DataObject objt = apps[j];
							//批复置为生效
							objt.set("becomeEffectiveMark", "03");
							DatabaseUtil.updateEntity("default", objt);
							
							//原批复置为失效
							sbi.updateOldApv04(objt.getString("applyId"));
							//原批复下的合同挪到新的批复下
							sbi.saveOldConToNew(objt.getString("applyId"));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			throw new EOSException("结束该集团下的所有子流程发生异常");
		}
	}
	/**
	 * 集团审批拒绝调用的处理逻辑
	 * @param approveId
	 */
	public void endJtForJd(String approveId){
		try {
			DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("approveId", approveId);
			DatabaseUtil.expandEntity("default", bizApprove);
			SaveBizInfo sbi = new SaveBizInfo();
			if("03".equals(bizApprove.get("bizType"))){
				DataObject partyGroup = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmGroupMember");
				partyGroup.set("groupPartyId", bizApprove.get("partyId"));
				partyGroup.set("status", bizApprove.get("03"));
				DataObject[] objs = DatabaseUtil.queryEntitiesByTemplate("default", partyGroup);
				for(int i=0;i<objs.length;i++){
					DataObject obj = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
					obj.set("partyId", (String)objs[i].get("corporationPartyId"));
					//obj.set("bizType", "05");--对公和个人成员的拒绝处理方式一样
					obj.set("becomeEffectiveMark", "09");
					DataObject[] apps = DatabaseUtil.queryEntitiesByTemplate("default", obj);
					if(null != apps && apps.length!=0){
						for(int j=0;j<apps.length ; j++){
							DataObject objt = apps[j];
							//批复置为生效
							objt.set("becomeEffectiveMark", "03");
							objt.set("approveConclusion", "2");
							DatabaseUtil.updateEntity("default", objt);
							
						}
					}
					//业务申请改为03
					DataObject obj1 = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
					obj1.set("partyId", (String)objs[i].get("corporationPartyId"));
					//obj.set("bizType", "05");--对公和个人成员的拒绝处理方式一样
					obj1.set("statusType", "09");
					DataObject[] apps1 = DatabaseUtil.queryEntitiesByTemplate("default", obj1);
					if(null != apps1 && apps1.length!=0){
						for(int j=0;j<apps1.length ; j++){
							DataObject objt = apps1[j];
							//批复置为生效
							objt.set("statusType", "03");
							DatabaseUtil.updateEntity("default", objt);
							
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			throw new EOSException("结束该集团下的所有子流程发生异常--forjd");
		}
	}
	
	/**
	 * 将原批复下的合同关联到新的业务批复下
	 * 参数：新业务申请Id
	 * */
	public void saveOldConToNew(String applyId){
		try {
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApply);
			DataObject amountInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
			amountInfo.set("applyId", applyId);
			DatabaseUtil.expandEntityByTemplate("default", amountInfo, amountInfo);
			DataObject amountDetail = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApply");
			amountDetail.set("amountId", amountInfo.get("amountId"));
			DataObject[] amountDetails = DatabaseUtil.queryEntitiesByTemplate("default", amountDetail);
			for(int i = 0;i<amountDetails.length;i++){
				DataObject conInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
				if(amountDetails[i].get("oldDetailId")==null || "".equals(amountDetails[i].get("oldDetailId"))){
					conInfo.set("amountDetailId", "00000000000000");//调整时新增的明细没有oldDetailId
				}else{
					conInfo.set("amountDetailId", amountDetails[i].get("oldDetailId"));
				}
				DataObject[] conInfos = DatabaseUtil.queryEntitiesByTemplate("default", conInfo);
				for(int j =0;j<conInfos.length;j++){
					conInfos[j].set("amountDetailId",amountDetails[i].get("amountDetailId"));
					DatabaseUtil.updateEntity("default", conInfos[j]);
				}
			}
			//更新综合授信协议
			DataObject creditInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConCreditInfo");
			if(bizApply.get("oldApplyId")!=null &&!"".equals(bizApply.get("oldApplyId"))){
				creditInfo.set("applyId", bizApply.get("oldApplyId"));
				creditInfo.set("conStatus", "03");
				DatabaseUtil.expandEntityByTemplate("default",creditInfo, creditInfo);
				if(null != creditInfo.get("contractId")){
					creditInfo.set("applyId", bizApply.get("applyId"));
					DatabaseUtil.updateEntity("default", creditInfo);
				}
			}
			//将被包入的单笔申请下的合同，更新到新的综合授信下
			if("01".equals(bizApply.get("bizHappenType"))){
				if("02".equals(bizApply.get("bizType")) ||"05".equals(bizApply.get("bizType"))){
					for(int i = 0;i<amountDetails.length;i++){
						String amountDetailId = (String)amountDetails[i].get("amountDetailId");
						String partyId = (String)bizApply.get("partyId");
						//查询该客户下被包含的该品种该币种的所有合同
						HashMap map = new HashMap();
						map.put("amountDetailId", amountDetailId);
						map.put("partyId", partyId);
						Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.bizApply.getContractId", map);
						for(int j =0;j<objs.length;j++){
							String contractId = (String)((DataObject)objs[j]).get("CONTRACT_ID");
							if(null == contractId){}else{
								DataObject conInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
								conInfo.set("contractId", contractId);
								conInfo.set("xyId", "PC000000");
								conInfo.set("amountDetailId", amountDetailId);
								DatabaseUtil.updateEntity("default", conInfo);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("将原批复下的合同关联到新的业务批复下");
		}
	}
	/**
	 * 将原批复信息置为失效
	 * 传入新的applyId
	 * */
	public void updateOldApv04(String newApplyId){
		DataObject newBizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
		newBizApply.set("applyId", newApplyId);
		DatabaseUtil.expandEntity("default", newBizApply);
		if(null == newBizApply.get("statusType") || "".equals(newBizApply.get("statusType"))){
			throw new EOSException("未查到业务申请信息--newApplyId="+newApplyId);
		}
		newBizApply.set("statusType", "03");
		DatabaseUtil.updateEntity("default", newBizApply);
		if(null != newBizApply.get("oldApplyId")&&!"".equals(newBizApply.get("oldApplyId"))){
			String oldApplyId = newBizApply.getString("oldApplyId");
			DataObject oldBizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
			oldBizApprove.set("applyId", oldApplyId);
			DatabaseUtil.expandEntityByTemplate("default", oldBizApprove, oldBizApprove);
			if(null == oldBizApprove.get("approveId")){
				return;
			}
			oldBizApprove.set("becomeEffectiveMark", "04");
			DatabaseUtil.updateEntity("default", oldBizApprove);
		}
	}
	/**
	 * 在综合授信业务申请审批流程 结束时
	如果是综合授信，并且审批结论为同意   将该客户下的所有非银团非低的业务置为失效  （除该笔业务）
	*/
	public void updateOtherApv04(String newApplyId){
		
		DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
		bizApprove.set("applyId", newApplyId);
		DatabaseUtil.expandEntityByTemplate("default", bizApprove, bizApprove);
		
		if((("02".equals(bizApprove.get("bizType")))||("05".equals(bizApprove.get("bizType"))))&&(!"2".equals(bizApprove.get("conclusion")))){
			HashMap map =new HashMap();
			map.put("applyId", newApplyId);
			Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.bizApply.saveApproveStatus04", map);
			for(int i=0;i<objs.length;i++){
				String approveId = (String)((DataObject)objs[i]).get("APPROVE_ID");
				DataObject approve04 = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
				if(null != approveId){
					approve04.set("approveId", approveId);
					approve04.set("becomeEffectiveMark", "04");
					DatabaseUtil.updateEntity("default", approve04);
				}
			}
		}
	}
	
	/**
	 * 在综合授信业务申请提交时 
	如果是综合授信，并且审批结论为同意   将该客户下的所有非银团非低的业务置为失效  （除该笔业务）
	*/
	public void updateOtherApvBeforSubmit(String newApplyId,String partyId){
		//从申请表里判断是否为综合授信业务(批复表要在流程走完后才会将申请表的数据迁移过去)
		DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
		bizApply.set("applyId", newApplyId);
		DatabaseUtil.expandEntityByTemplate("default", bizApply, bizApply);
		
		if(("02".equals(bizApply.get("bizType")))||("05".equals(bizApply.get("bizType")))){
			HashMap map =new HashMap();
			map.put("applyId", newApplyId);
			map.put("partyId", partyId);
			Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.bizApply.saveApproveStatusBeforSubmit", map);
			for(int i=0;i<objs.length;i++){
				String approveId = (String)((DataObject)objs[i]).get("APPROVE_ID");
				DataObject approve04 = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
				if(null != approveId){
					approve04.set("approveId", approveId);
					approve04.set("becomeEffectiveMark", "04");
					DatabaseUtil.updateEntity("default", approve04);
				}
			}
		}
	}
	
	/**
	 * 集团 调整/复议
	 * 集团调整只需要迁移 主表及基本信息表 数据
	 * */
	public DataObject tzJt(String approveId){
		DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
		bizApprove.set("approveId", approveId);
		DatabaseUtil.expandEntity("default", bizApprove);

		//基本信息
		DataObject amountApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
		amountApprove.set("approveId", approveId);
		DatabaseUtil.expandEntityByTemplate("default", amountApprove, amountApprove);
		
		//获取时间com.bos.pub.GitUtil.getBusiDate
		Date date = GitUtil.getBusiDate();
		
		//批复信息转化为申请信息并保存
		DataObject bizApply = DataObjectUtil.convertDataObject(bizApprove, "com.bos.dataset.biz.TbBizApply", true);
		bizApply.set("createTime", date);
		bizApply.set("oldApplyId", bizApprove.get("applyId"));
		bizApply.set("applyId", null);
		DatabaseUtil.insertEntity("default", bizApply);
		
		DataObject amountApply = DataObjectUtil.convertDataObject(amountApprove, "com.bos.dataset.biz.TbBizAmountApply", true);
		amountApply.set("applyId", bizApply.get("applyId"));
		DatabaseUtil.insertEntity("default", amountApply);
		
		return bizApply;
		

	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
