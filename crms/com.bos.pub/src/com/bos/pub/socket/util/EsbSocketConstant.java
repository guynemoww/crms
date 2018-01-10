package com.bos.pub.socket.util;

import com.bos.pub.GitUtil;

public class EsbSocketConstant {
	// ESB的SOCKET服务配置begin(未用)
	public static final String CONTRIBUTION_ESB_SOCKET_MODULE = "SocketConfig";
	public static final String CONTRIBUTION_ESB_GROUP = "esb_socket_server";
	public static final String CONTRIBUTION_ESB_IP = "ip";
	public static final String CONTRIBUTION_ESB_PORT = "port";
	public static final String CONTRIBUTION_CRMS_GROUP = "crms_socket_server";
	public static final String CONTRIBUTION_CRMS_PORT = "port";
	public static final String CONTRIBUTION_APPHEAD_GROUP = "app_head";
	public static final String CONTRIBUTION_APPHEAD_SOURCESYSID = "sourceSysId";
	public static final String CONTRIBUTION_APPHEAD_RGONCD = "rgonCd";
	public static final String CONTRIBUTION_APPHEAD_BRANCHID = "branchId";
	public static final String CONTRIBUTION_APPHEAD_COREOTJYGY = "coreotjygy";
	public static final String CONTRIBUTION_APPHEAD_COREFKJYGY = "corefkjygy";
	public static final String CONTRIBUTION_APPHEAD_COREHKJYGY = "corehkjygy";
	public static final String CONTRIBUTION_APPHEAD_COREHXJYGY = "corehxjygy";
	// ESB的SOCKET服务配置end（未用）
	// ESB的WebService服务配置begin
		public static final String CONTRIBUTION_ESB_WEBSERVICE_MODULE = "EsbWebServiceConfig";
		public static final String CONTRIBUTION_ESB_WEBSERVICE_GROUP = "esb_webservice_server";
		public static final String CONTRIBUTION_ESB_WEBSERVICE_IP = "ip";
		public static final String CONTRIBUTION_ESB_WEBSERVICE_PORT = "port";
		//public static final String CONTRIBUTION_ESB_WEBSERVICE_SERVICE = "service";
		public static final String ESB_FK_IS_OPEN = "fkIsOpen";
		public static final String ESB_HK_IS_OPEN = "hkIsOpen";
	// ESB的WebService服务配置end
	// 计量的WebService服务配置begin
	public static final String CONTRIBUTION_EASYLCS_WEBSERVICE_MODULE = "WebServiceConfig";
	public static final String CONTRIBUTION_EASYLCS_WEBSERVICE_GROUP = "easylcs_webservice_server";
	public static final String CONTRIBUTION_EASYLCS_WEBSERVICE_IP = "ip";
	public static final String CONTRIBUTION_EASYLCS_WEBSERVICE_PORT = "port";
	public static final String CONTRIBUTION_EASYLCS_WEBSERVICE_SERVICE = "service";
	public static final String FK_IS_OPEN = "fkIsOpen";
	public static final String HK_IS_OPEN = "hkIsOpen";
	// 计量的WebService服务配置end
	public static final String CHARCODE_UTF8 = "UTF-8";
	public static final String RQ = "RQ";
	public static final String BODY = "BODY";

	public static final String SERVICE_CODE_SCENE = "ServiceCodeScene";

	public static final String SOCKET_SUCCESS = "00000000000000";
	public static final String SOCKET_FAILE = "99999999999999";
	public static final String XD_SOCKET_FAILE = GitUtil.getSourceSysId()
			+ "99999999";
	// DataObject中设置的返回code和msg信息
	public static final String RETURN_CODE = "ReturnCode";
	public static final String RETURN_MSG = "ReturnMsg";
	// 国结报文中用到的数组名称
	public static final String ESB_BODY_GJRQ_MRGN_ARRAYS = "esbBodyGjRqMrgnArrays";
	// 流程银行报文中用到的数组名称
	public static final String ESB_BODY_WMARQ_DBT_ARRAYS = "esbBodyWmaRqDbtArrays";
	// 核心“表外账记账”报文中用到的数组名称
	public static final String ESB_BODY_HXRQ_TSK_ARRAYS = "esbBodyHxRqTskArrays";
	// ECIF报文中用到的数组名称
	public static final String ESB_BODY_ECIFRQ_IDENTINF_ARRAYS = "esbBodyEcifRqIdentInfArrays";
	/**
	 * 公共响应体
	 */
	public static final String ESBBODYRS = "ESBBODYRS";

	/*
	 * 12002000013客户信息开户维护 01个人客户基本信息开户
	 */
	public static final String EcifRq12002000013BODY01 = "RQ12002000013BODY01";
	/*
	 * 12002000013客户信息开户维护 02个人客户关键信息维护
	 */
	public static final String EcifRq12002000013BODY02 = "RQ12002000013BODY02";
	/*
	 * 12002000013客户信息开户维护 03个人客户基本信息维护
	 */
	public static final String EcifRq12002000013BODY03 = "RQ12002000013BODY03";
	/*
	 * 12002000013客户信息开户维护 04公司客户基本信息开户
	 */
	public static final String EcifRq12002000013BODY04 = "RQ12002000013BODY04";
	/*
	 * 12002000013客户信息开户维护 05公司客户关键信息维护
	 */
	public static final String EcifRq12002000013BODY05 = "RQ12002000013BODY05";
	/*
	 * 12002000013客户信息开户维护 06公司客户基本信息维护
	 */
	public static final String EcifRq12002000013BODY06 = "RQ12002000013BODY06";
	// -----------------------------------------------------------------------------------------------
	/*
	 * 02001000001贷款放款 01押汇融资类业务放款
	 */
	public static final String GjRq02001000001BODY01 = "RQ02001000001BODY01";
	/*
	 * 02001000003贷款信息登记 01贷款展期信息登记
	 */
	public static final String GjRq02001000003BODY01 = "RQ02001000003BODY01";
	/*
	 * 02002000001贷款业务撤销 01信贷交易取消
	 */
	public static final String GjRq02002000001BODY01 = "RQ02002000001BODY01";
	/*
	 * 05001000001信用证维护 01信用证开立
	 */
	public static final String GjRq05001000001BODY01 = "RQ05001000001BODY01";
	/*
	 * 05001000001信用证维护 02信用证修改
	 */
	public static final String GjRq05001000001BODY02 = "RQ05001000001BODY02";
	/*
	 * 05001000002保函维护 03保函开立
	 */
	public static final String GjRq05001000002BODY03 = "RQ05001000002BODY03";
	/*
	 * 05001000002保函维护 04保函修改
	 */
	public static final String GjRq05001000002BODY04 = "RQ05001000002BODY04";
	/*
	 * 07003000001汇率牌价查询 01汇率查询
	 */
	public static final String GjRq07003000001BODY01 = "RQ07003000001BODY01";
	// -----------------------------------------------------------------------------------------------
	/*
	 * 01001000002通用核心记账 02中间业务简单会计分录记账
	 */
	public static final String HxRq01001000002BODY02 = "RQ01001000002BODY02";
	/*
	 * 01001000002通用核心记账 03表外账记账
	 */
	public static final String HxRq01001000002BODY03 = "RQ01001000002BODY03";
	/*
	 * 01001000002通用核心记账 05中间业务多笔单边记账 ---受托支付
	 */
	public static final String HxRq01001000002BODY05 = "RQ01001000002BODY05";
	/*
	 * 12003000004账户信息查询 01根据账号查询账户信息
	 */
	public static final String HxRq12003000004BODY01 = "RQ12003000004BODY01";
	/*
	 * 12005000001对账文件传送 01日终对账
	 */
	public static final String HxRq12005000001BODY01 = "RQ12005000001BODY01";
	/*
	 * 12005000002批量处理文件传送 01批量账务处理
	 */
	public static final String HxRq12005000002BODY01 = "RQ12005000002BODY01";
	/*
	 * 12005000002批量处理文件传送 02批量运行结果查询
	 */
	public static final String HxRq12005000002BODY02 = "RQ12005000002BODY02";
	/*
	 * 12005000002批量账务处理 03批量入账请求
	 */
	public static final String HxRq12005000002BODY03 = "RQ12005000002BODY03";
	/*
	 * 12005000003批量账户开户 01批量机构账户开户
	 */
	public static final String HxRq12005000003BODY01 = "RQ12005000003BODY01";
	/*
	 * 12005000002批量账务处理 04批量入账结果查询
	 */
	public static final String HxRq12005000002BODY04 = "RQ12005000002BODY04";
	/*
	 * 02002000002信贷下柜登记撤销 01信贷业务下柜登记撤销
	 */
	public static final String HxRq02002000002BODY01 = "RQ02002000002BODY01";
	/*
	 * 02002000002信贷下柜登记撤销 02担保业务下柜登记撤销
	 */
	public static final String HxRq02002000002BODY02 = "RQ02002000002BODY02";
	/*
	 * 02002000002信贷下柜登记撤销 03个人授信卡登记撤销
	 */
	public static final String HxRq02002000002BODY03 = "RQ02002000002BODY03";
	// -----------------------------------------------------------------------------------------------
	/*
	 * 05001000002保函维护 04保函修改
	 */
	public static final String RzRq12002000012BODY01 = "RQ12002000012BODY01";
	/*
	 * 07003000001汇率牌价查询 01汇率查询
	 */
	public static final String RzRq12003000005BODY02 = "RQ12003000005BODY02";
	// -----------------------------------------------------------------------------------------------
	/*
	 * 02001000002贷款还款 01贸易融资还款
	 */
	public static final String XdRq02001000002BODY01 = "RQ02001000002BODY01";
	/*
	 * 02001000003贷款信息登记 02贷款放款通知
	 */
	public static final String XdRq02001000003BODY02 = "RQ02001000003BODY02";
	/*
	 * 05002000001国际结算业务维护 01国际业务撤销/闭卷/取消/冲账
	 */
	public static final String XdRq05002000001BODY01 = "RQ05002000001BODY01";
	// -------------------------------------------------------------------------------------------------
	/*
	 * 03002000011票据信息维护 01银行承兑汇票打印发起
	 */
	public static final String WmaRq03002000011BODY01 = "RQ03002000011BODY01";
	/*
	 * 03002000011票据信息维护 02银行承兑汇票打印撤销
	 */
	public static final String WmaRq03002000011BODY02 = "RQ03002000011BODY02";
	// ---------------------------20160126
	// add--------------------------------------------------------------
	/*
	 * 12003000004账户信息查询 10账户业务数据信息查询
	 */
	public static final String HxRq12003000004BODY10 = "RQ12003000004BODY10";

	// ---------------------------20160223
	// add--------------------------------------------------------------
	/*
	 * 02003000003 客户信贷信息查询  01客户授信信息查询				
	 */
	public static final String MtmqRs02003000003BODY01 = "RS02003000003BODY01";
	public static final String MtmqRq02003000003BODY01 = "RQ02003000003BODY01";
	// ---------------------------2016/02/24
	// add--------------------------------------------------------------
	/*
	 * 12002000013客户信息开户维护 09对私/微贷客户信息维护				
	 */
	public static final String MtmqRs12002000013BODY09 = "RS12002000013BODY09";
	public static final String MtmqRq12002000013BODY09 = "RQ12002000013BODY09";
	/*
	 * 12002000013客户信息开户维护 10对公客户信息维护				
	 */
	public static final String MtmqRs12002000013BODY10 = "RS12002000013BODY10";
	public static final String MtmqRq12002000013BODY10 = "RQ12002000013BODY10";
	/*
	 * 12002000013客户信息开户维护 13对公客户财报信息维护
	 */
	public static final String MtmqRs12002000013BODY13 = "RS12002000013BODY13";
	public static final String MtmqRq12002000013BODY13 = "RQ12002000013BODY13";
	
	/*
	 * 02003000004信贷信息查询     01信贷资料目录树查询
	 */
	public static final String MtmqRs02003000004BODY01 = "RS02003000004BODY01";
	public static final String MtmqRq02003000004BODY01 = "RQ02003000004BODY01";
	
	
	/**
	 * 02003000004信贷信息查询     02项目信息查询				
	 */
	public static final String MtmqRs02003000004BODY02 = "RS02003000004BODY02";
	public static final String MtmqRq02003000004BODY02 = "RQ02003000004BODY02";
	
	
	/**
	 * 02003000004信贷信息查询     03押品信息查询				
	 */
	public static final String MtmqRs02003000004BODY03 = "RS02003000004BODY03";
	public static final String MtmqRq02003000004BODY03 = "RQ02003000004BODY03";
	/**
	 * 02002000004信贷信息维护     01项目信息建立				
	 */
	public static final String MtmqRs02002000004BODY01 = "RS02002000004BODY01";
	public static final String MtmqRq02002000004BODY01 = "RQ02002000004BODY01";
	
	/**
	 * 02002000004信贷信息维护     02押品信息建立				
	 */
	public static final String MtmqRs02002000004BODY02 = "RS02002000004BODY02";
	public static final String MtmqRq02002000004BODY02 = "RQ02002000004BODY02";
	/**
	 * 02002000003移动信贷公共管理     01业务预申请			
	 */
	public static final String MtmqRs02002000003BODY01 = "RS02002000003BODY01";
	public static final String MtmqRq02002000003BODY01 = "RQ02002000003BODY01";
	/**
	 * 02002000003移动信贷公共管理     02影像补录			
	 */
	public static final String MtmqRs02002000003BODY02 = "RS02002000003BODY02";
	public static final String MtmqRq02002000003BODY02 = "RQ02002000003BODY02";
	
	/**
	 * 02002000003移动信贷公共管理     03催收登记信息提交	
	 */
	public static final String MtmqRs02002000003BODY03 = "RS02002000003BODY03";
	public static final String MtmqRq02002000003BODY03 = "RQ02002000003BODY03";
	

	/**
	 * 02003000004信贷信息查询	  06日常检查信息列表查询	  
	 */
	public static final String MtmqRs02003000004BODY06 = "RS02003000004BODY06";
	public static final String MtmqRq02003000004BODY06 = "RQ02003000004BODY06";
	
	/**
	 * 02003000004信贷信息查询       07逾期借据信息查询 
	 */
	public static final String MtmqRs02003000004BODY07 = "RS02003000004BODY07";
	public static final String MtmqRq02003000004BODY07 = "RQ02003000004BODY07";

	/**
	 * 
	 * 02003000004信贷信息查询 		05合同信息查询
	 * 
	 */
	public static final String MtmqRs02003000004BODY05 = "RS02003000004BODY05";
	public static final String MtmqRq02003000004BODY05 = "RQ02003000004BODY05";
	
	/**
	 * 
	 * 02003000004信贷信息查询 04业务申请查询
	 * 
	 */
	public static final String MtmqRs02003000004BODY04 = "RS02003000004BODY04";
	public static final String MtmqRq02003000004BODY04 = "RQ02003000004BODY04";
	

}							                              
