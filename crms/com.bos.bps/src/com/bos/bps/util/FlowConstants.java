package com.bos.bps.util;

import java.util.HashMap;
import java.util.Map;

public class FlowConstants {

	
	
	/**
	 * bps服务器地址配置信息键值常量
	 */
	//构件包名称
	public static final String CONTRIBUTION_NAME="com.bos.bps";
	//模块名
	public static final String BPS_MODULE_NAME="bps";
	public static final String BPS_MODULE_NAME_TEMPLATE="bpsTemplate";
	//组名
	public static final String BPS_GROUP_NAME="bps_addr";
	//key值
	public static final String BPS_KEY_URL="url";
	public static final String BPS_KEY_TENANTID="tenantid";
	public static final String BPS_KEY_NAME="name";
	public static final String BPS_KEY_DESC="desc";
	//流程配置字典项代码
	public static final String BPS_DICT_ID="XD_WFCD0007";
	
	
	//页面类型
	public static final String PAGETYPE_VIEW="view";
	
	public static final String PAGETYPE_EDIT="edit"; 
	
	/**
	 * 审批结论
	 */
	//退回操作
	public static final String APPROVE_99="99";
	//再议
	public static final String APPROVE_998="998";
	//预处理退回
	public static final String APPROVE_997="997";
	//补充材料
	public static final String APPROVE_98="98";
	//拒绝操作
	public static final String APPROVE_999="999";
	//不同意
	public static final String APPROVE_2="2";
	
	/**
	 * 活动图元类型
	 */
	//结束节点
	public static final String ACTIVITYPE_FINISH="finish";
	//手工选择节点
	public static final String ACTIVITYPE_MORE="more";
	
	
	//流程实例表实体路径
	public static final String PROCESSINSTANCE_URL="com.bos.bps.dataset.bps.TbWfmProcessinstance";
	//工作项实例表实体路径
	public static final String WORKITEMINSTANCE_URL="com.bos.bps.dataset.bps.TbWfmWorkiteminstance";
	//流程模板映射表
	public static final String PROCESSINSTMAPPING_URL="com.bos.bps.dataset.bps.TbWfmProcessmapping";
	//流程节点映射表
	public static final String WORKITEMMAPPING_URL="com.bos.bps.dataset.bps.TbWfmWorkitemmapping";
	//流程参数配置表
	public static final String BUSINESSPARAMETER_URL="com.bos.bps.dataset.bps.TbWfmBusiparameter";
	//工作项参与者表
	public static final String WORKPARTICIPANT_URL="com.bos.bps.dataset.bps.TbWfmParticipant";
	
	/**
	 * 特殊模板发起
	 */
	
	//预警
	public static final String EWS_RISK_SIGN_ADD_FHDH="com.bos.bps.ews.risk_sign_add_fhdh";
	public static final String EWS_RISK_SIGN_ADD_ZHDH="com.bos.bps.ews.risk_sign_add_zhdh";
	public static final String EWS_RISK_SIGN_CLEAR_FHDH="com.bos.bps.ews.risk_sign_clear_fhdh";
	public static final String EWS_RISK_SIGN_CLEAR_ZHDH="com.bos.bps.ews.risk_sign_clear_zhdh";
	//名单制管理
	public static final String LIST_RISK_LIST_MGR_DH="com.bos.bps.list.risk_list_mgr_dh";
	//分类
	public static final String CLA_ASSET_CLASSIFY_PERSON_ZHFF="com.bos.bps.cla.asset_classify_person_zhff";
	public static final String CLA_ASSET_CLASSIFY_NO_PERSON_ZHFF="com.bos.bps.cla.asset_classify_no_person_zhff";
	public static final String CLA_ASSET_CLASSIFY_NO_PERSON_FHFF="com.bos.bps.cla.asset_classify_no_person_fhff";
	public static final String CLA_ASSET_CLASSIFY_NO_PERSON_DAY_FHFF="com.bos.bps.cla.asset_classify_no_person_day_fhff";
	//额度申请
	public static final String IRM_FINA_CUST_IRM_APPLY="com.bos.bps.irm.fina_cust_irm_apply";
	
	/**
	 * 规则使用常量
	 */
	//授权专用标识
	public static final String BPS_RULEID_SQ="ruleSQ";
	
	
	//授权规则参数
	public static final String [] RULE_SQ = {"biz_amt","cust_num","cust_name","cust_region","cust_ty_type","cust_finance_type",
		"cust_finance_num","cust_is_national","cust_eval","cust_amt2","cust_amt","biz_is_low_risk","biz_product_cd","biz_type",
		"biz_happen_cd","biz_term","biz_org","biz_guaranty","biz_main_granty","biz_use_cd","biz_is_extend","ori_term","is_cycle",
		"ori_borrow_type","ori_borrow_amt","gu_type","gu_is_valid","gu_is_finance","amt_2","loan_pre_appr_org","cust_is_group","cust_size",
		"ruleresult_orglevel"};
	//存放规则的集合
	public static  Map<String,Object> ruleMap;
	
	//存放注销业务回调类路径
	private static Map<String,String> busMap = new HashMap<String,String>();
	
	/**
	 * 获取注销业务回调类路径集合
	 * @return
	 */
	public static Map getBusMap(){
		
		//贷后检查
		busMap.put("aft", "com.bos.aft.RevokeFlow");
		//担保客户贷后检查流程
		busMap.put("guar_loan_check_small_business", "com.bos.aft.warrantRevokeFlow");
		//业务申请
		busMap.put("biz", "com.bos.biz.pro.delProInfoByApplyId");
		//绿色通道
		busMap.put("green", "com.bos.biz.pro.delProInfoByApplyId");
		
		//客户管理
		busMap.put("black_list_into", "com.bos.csm.custStop.BlackListInfo");//黑名单转入转出
		busMap.put("custMaintain", "com.bos.csm.custStop.CustMaintain");//客户信息维护
		busMap.put("group_cust_change", "com.bos.csm.custStop.GroupCustChange");//集团客户变更
		busMap.put("group_cust_identify", "com.bos.csm.custStop.GroupCustIdentify");//集团客户认定
		busMap.put("host_right_change_diff", "com.bos.csm.custStop.HostRightChange");//集团客户主权变更-同行不同机构
		busMap.put("host_right_change_same", "com.bos.csm.custStop.HostRightChange");//集团客户主权变更-跨机构
		busMap.put("small_enterprise_identify", "com.bos.csm.custStop.SmallEnterpriseIdentify");//小企业认定流程
		//内部评级
		busMap.put("cre", "com.bos.irm.rating.ModifyRatingCutFlow");
		busMap.put("default_determination", "com.bos.irm.rating.ModifyDefaultWhenStopFlow");//违约认定、重生
		//预警
		busMap.put("ews", "com.bos.ews.RevokeFlow");
		//放款支付
		busMap.put("pay", "com.bos.pay.processEnd");
		//合同签约
		busMap.put("crt", "com.bos.crt.ConDel");
		//授权规则
		busMap.put("rule", "com.bos.process.processUitl");
		//资产保全
		//busMap.put("npl", "");
		//担保管理
		busMap.put("grt", "com.bos.grt.custFlow.StopCollateralCust");
		
		return busMap;
		
	}
	/**
	 * 初始化规则参数map
	 *
	 */
	public static void initRuleMap(){
		ruleMap =new HashMap<String, Object>();
		ruleMap.put("ruleSQ", RULE_SQ);
		
	}
	
	/**
	 * 根据规则Id，获取规则所需参数数组对象
	 * @param ruleId
	 * @return
	 */
	public static String [] getRuleParameterArrays(String ruleId){
		
	  String [] ruleKeys = (String[])ruleMap.get(ruleId);
	  
	  return ruleKeys;
	}
}
