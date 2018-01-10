/**
 * 
 */
package com.bos.bizApply;

import java.util.HashMap;
import java.util.Map;

import com.bos.biz.SaveBizInfo;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-04-27 21:03:11
 *
 */
@Bizlet("")
public class ApplyInfoAndProcess {
	public static TraceLogger logger = new TraceLogger(ApplyInfoAndProcess.class);

	/**
	 * @param args
	 * @author 3231
	 */
	public void delProcess(String applyId) {

		if (null == applyId || "" == applyId) {
			logger.info("流程返回的申请ID为空！");
			return;
		}

		logger.info("撤销流程，删除业务数据------bizId=" + applyId + "------->开始!");

		DataObject bizApply = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizApply");
		DataObject amountInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
		DataObject amountDetail = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApply");
		DataObject loanRate = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApply");
		DataObject productInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
		//查询业务信息
		DatabaseUtil.expandEntity("default", bizApply);
		//流程未提交
		if ("01".equals(bizApply.get("statusType"))) {
			//查询业务基本信息
			amountInfo.set("applyId", applyId);
			DatabaseUtil.expandEntityByTemplate("default", amountInfo,
					amountInfo);
			//查询明细
			if (null != amountInfo.getString("amountId")) {
				amountDetail.set("amountId", amountInfo.get("amountId"));
				DataObject[] amountDetails = DatabaseUtil
						.queryEntitiesByTemplate("default", amountDetail);
				//查询品种明细
				for (int i = 0; i <= amountDetails.length; i++) {
					amountDetail = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApply");
					DataObject productApply = null;
					loanRate = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApply");
					productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
					amountDetail = amountDetails[i];
					//查询利率
					loanRate.set("amountDetailId",
							amountDetail.get("amountDetailId"));
					DatabaseUtil.expandEntityByTemplate("default", loanRate,
							loanRate);
					//查询品种明细
					productInfo.set("productCd",
							amountDetail.get("productType"));
					DatabaseUtil.expandEntityByTemplate("default", productInfo,
							productInfo);
					productApply = DataObjectUtil.createDataObject(productInfo
							.getString("entityName"));
					productApply.set("amountDetailId",
							amountDetail.get("amountDetailId"));
					DatabaseUtil.expandEntityByTemplate("default",
							productApply, productApply);

					//删除
					if (null != productApply.getString("applyDetailId")) {
						DatabaseUtil.deleteEntity("default", productApply);
					}
					if (null != loanRate.getString("loanrateId")) {
						DatabaseUtil.deleteEntity("default", loanRate);
					}
					if (null != amountDetail.getString("amountDetailId")) {
						DatabaseUtil.deleteEntity("default", amountDetail);
					}
				}
				DatabaseUtil.deleteEntity("default", amountInfo);
			}

			DatabaseUtil.deleteEntity("default", bizApply);
			logger.info("撤销流程，物理删除业务数据------bizId=" + applyId + "------->结束!");
			return;
		}
		bizApply.set("statusType", "04");
		DatabaseUtil.updateEntity("default", bizApply);
		logger.info("撤销流程，逻辑删除业务数据------bizId=" + applyId + "------->结束!");
		return;

	}

	@Bizlet("")
	public DataObject saveApvToApl(String approveId) throws Throwable {
		if (null == approveId || "" == approveId) {
			logger.info("批复ID为空！");
			return null;
		}
		SaveBizInfo sb = new SaveBizInfo();
		DataObject bizApply;
		DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
		bizApprove.set("approveId", approveId);
		DatabaseUtil.expandEntity("default", bizApprove);
		if("03".equals(bizApprove.get("bizType"))){
			bizApply = sb.tzJt(approveId);
		}else{
			bizApply = sb.saveApproveToApply(approveId);
		}
		if (null == bizApply) {
			logger.info("批复挪往申请时失败，申请信息为空！");
			return null;
		}
		return bizApply;
	}

	@Bizlet("")
	public DataObject saveApvToAplTZ(String approveId) throws Throwable {
		if (null == approveId || "" == approveId) {
			logger.info("批复ID为空！");
			return null;
		}
		SaveBizInfo sb = new SaveBizInfo();
		DataObject bizApply;
		DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
		bizApprove.set("approveId", approveId);
		DatabaseUtil.expandEntity("default", bizApprove);
		if("03".equals(bizApprove.get("bizType"))){
			bizApply = sb.tzJt(approveId);
		}else{
			bizApply = sb.saveApproveToApplyTZ(approveId);
		}
		if (null == bizApply) {
			logger.info("批复挪往申请时失败，申请信息为空！");
			return null;
		}
		return bizApply;
	}

	@Bizlet("批复失效")
	public String pfsx(String approveId) throws Throwable {
		String result = "";
		if (null == approveId || "" == approveId) {
			logger.info("批复ID为空！");
			result =  "批复ID为空！";
			return result;
		}
		try {
			DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("approveId", approveId);
			DatabaseUtil.expandEntity("default", bizApprove);
			//将批复置为失效
			bizApprove.set("becomeEffectiveMark", "08");
			DatabaseUtil.updateEntity("default", bizApprove);
			//将批复下的合同置为失效
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("approveId", approveId);
			DatabaseExt.executeNamedSql("default", "com.bos.bizApply.bizApply.updateContractByApproveId", map1);
			//重算额度
			Map<String,String> map2 = new HashMap<String,String>();
			map2.put("partyId", (String)bizApprove.get("partyId"));
			DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
		} catch (Exception e) {
			e.printStackTrace();
			result = "批复失效处理失败！";
		}
		
		return result;
	}
	@Bizlet("根据产品获取评级类型")
	public String getRatingTypeByProduct(String productType){
		//默认对公3
		String ratingType = "3";
		if(productType.startsWith("03") || productType.startsWith("02001")|| productType.startsWith("02005002")|| productType.startsWith("02005010")){//经营性--委贷取经营性
			ratingType = "2";
		}else if(productType.startsWith("02002")||productType.startsWith("02003")||productType.startsWith("02004")||
				productType.startsWith("02005001") || productType.startsWith("02005003")){//消费 --bug13402 公积金显示消费
			ratingType = "1";
		}
		
		return ratingType;
	}
	@Bizlet("已完成状态批复发起调整")
	public String groupMemberTz09(DataObject groupBizInfo){
		String result = "";
		try {
			//先删除批复
			String applyId = groupBizInfo.getString("APPLY_ID");
			DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("applyId", applyId);
			bizApprove.set("becomeEffectiveMark", "09");
			DatabaseUtil.expandEntityByTemplate("default", bizApprove, bizApprove);
			if(bizApprove.get("approveId")==null){
				return "未找到已完成状态批复";
			}
			DatabaseUtil.deleteEntity("default", bizApprove);
			//删除基本信息
			DataObject bizAmountApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApprove");
			bizAmountApprove.set("approveId", bizApprove.getString("approveId"));
			DatabaseUtil.expandEntityByTemplate("default", bizAmountApprove, bizAmountApprove);
			if(bizAmountApprove.get("amountId")==null){
				return "未找到已完成状态申请基本信息表";
			}
			DatabaseUtil.deleteEntity("default", bizAmountApprove);
			//删除明细
			DataObject bizAmountDetailApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
			bizAmountDetailApprove.set("amountId", bizAmountApprove.get("amountId"));
			DataObject[] details = DatabaseUtil.queryEntitiesByTemplate("default", bizAmountDetailApprove);
			if (details == null || details.length==0) {
				return "未找到已完成状态明细信息";
			}
			DatabaseUtil.deleteEntityBatch("default", details);
			
			//将申请信息状态改为01
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			bizApply.set("statusType", "01");
			DatabaseUtil.updateEntity("default", bizApply);
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "发起调整失败";
		}
		
		return result;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
