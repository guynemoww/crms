/**
 * 
 */
package com.bos.bizApply;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-08-04 23:56:12
 *	创建综合授信
 *	创建综合授信时，新的综合授信应自动包含该客户下非银团非低的所有已签合同的业务品种
 */
@Bizlet("")
public class CreditBizApply {
	public static TraceLogger logger = new TraceLogger(CreditBizApply.class);

	/**
	 * @param partyId 
	 * @param args
	 * @author 3231
	 * 为客户创建一笔新的综合授信
	 * 新的综合授信自动包括该客户下 所有非银团非低的所有已签合同的业务品种
	 */
	@Bizlet("")
	public DataObject createCreditApply(DataObject biz) {
		
		if (null == biz.get("partyId") || "".equals(biz.get("partyId"))) {
	logger.info("------3231------>发起综合授信时，partyId为空");
			return null;
		}
		String partyId = (String)biz.get("partyId");
		//获取时间com.bos.pub.GitUtil.getBusiDate
		Date date = GitUtil.getBusiDate();
		//创建基本信息
		DataObject bizApply = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizApply");
		bizApply.set("bizType", "02");
		bizApply.set("bizTypeFlag", "02");
		bizApply.set("partyId", partyId);
		DatabaseUtil.saveEntity("default", bizApply);

		//创建基本信息
		DataObject amountInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
		amountInfo.set("applyId", bizApply.get("applyId"));
		amountInfo.set("partyId", partyId);
		if(null!=biz.get("guarantyType") && !"".equals(biz.get("guarantyType"))){
			amountInfo.set("guarantyType", biz.get("guarantyType"));
		}
		DatabaseUtil.saveEntity("default", amountInfo);

		//创建明细信息//查询需要被包含的批复//综合授信明细 包括该客户下所有非银团，非低的业务品种
		HashMap map = new HashMap();
		map.put("partyId", partyId);
		Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.bizApply.bizApply.getDetailId", map);
		
		DataObject loanRateApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApprove");
		DataObject productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
		DataObject productApprove;
		DataObject productApply;
		DataObject detailApply;
		
		for(int i=0;i<objs.length;i++){
			String amountDetailId = (String) (((DataObject) objs[i]).get("AMOUNTDETAILID"));
	logger.info("------3231------>发起综合授信时，非银团，非低已签合同amountDetailId------>"+amountDetailId);
			DataObject detailApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApprove");
			detailApprove.set("amountDetailId", amountDetailId);
			DatabaseUtil.expandEntity("default", detailApprove);
			
			//查询利率--每次都要初始化
			loanRateApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountLoanrateApprove");
			loanRateApprove.set("amountDetailId", detailApprove.get("amountDetailId"));
			DatabaseUtil.expandEntityByTemplate("default", loanRateApprove, loanRateApprove);
			
			//查询品种配置
			productInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizProductInfo");
			productInfo.set("productCd", detailApprove.get("productType"));
			DatabaseUtil.expandEntityByTemplate("default", productInfo, productInfo);
			//批复品种
			String entity = productInfo.getString("entityName").replace("Approve", "Apply");
			productApprove=DataObjectUtil.createDataObject(entity);
			productApprove.set("amountDetailId", detailApprove.get("amountDetailId"));
			DatabaseUtil.expandEntityByTemplate("default", productApprove, productApprove);
			
			//转化申请并保存
			detailApply = DataObjectUtil.convertDataObject(detailApprove,"com.bos.dataset.biz.TbBizAmountDetailApply", true);
			
			//判断该分项是否存在，同品种，同币种
			DataObject detailLs= DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountDetailApply");
			detailLs.set("amountId", amountInfo.get("amountId"));
			detailLs.set("productType", detailApply.get("productType"));
			detailLs.set("currencyCd", detailApply.get("currencyCd"));
			DatabaseUtil.expandEntityByTemplate("default", detailLs, detailLs);
			if(null==detailLs.get("amountDetailId")){

				detailApply.set("amountId", amountInfo.get("amountId"));
				detailApply.set("oldDetailId", detailApply.get("amountDetailId"));
				detailApply.set("amountDetailId", null);
				detailApply.set("detailAmt", null);
				detailApply.set("rmbAmt", null);
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
			}else{
				
			}
		}
		return bizApply;

	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
