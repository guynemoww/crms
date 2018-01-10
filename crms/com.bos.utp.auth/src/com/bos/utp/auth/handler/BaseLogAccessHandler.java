package com.bos.utp.auth.handler;

import com.bos.utp.ABFConfigKey;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IUserObject;
import com.eos.engine.core.IHandler;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.runtime.core.ApplicationContext;
import com.eos.runtime.metadata.IApplicationMetaData;
import com.eos.runtime.metadata.IBizMetaData;
import com.eos.runtime.metadata.IContributionMetaData;
import com.eos.runtime.metadata.IPageFlowMetaData; 
import com.eos.runtime.metadata.MetaDataHelper;
import com.primeton.ext.engine.core.IRuntimeContext;

/**
 * 
 * 日志处理基本实现<BR>
 * 
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史 $Log: BaseLogAccessHandler.java,v $
 * 修改历史 Revision 1.11  2010/12/07 03:09:46  caisy
 * 修改历史 配置读取方式修改
 * 修改历史
 * 修改历史 Revision 1.10  2010/12/01 03:23:14  caisy
 * 修改历史 配置文件读取方式修改
 * 修改历史
 * 修改历史 Revision 1.9  2010/11/30 16:12:23  caisy
 * 修改历史 编码改为UTF-8
 * 修改历史
 * 修改历史 Revision 1.8  2010/09/28 07:46:11  caisy
 * 修改历史 删除无用代码
 * 修改历史
 * 修改历史 Revision 1.7  2009/04/27 13:22:59  wengzr
 * 修改历史 Update:重构日志处理方法
 * 修改历史
 * 修改历史 Revision 1.6  2009/03/30 05:39:38  caisy
 * 修改历史 代码规范
 * 修改历史
 * 修改历史 Revision 1.5  2009/02/26 06:01:24  zhujiang
 * 修改历史 *** empty log message ***
 * 修改历史
 * 修改历史 Revision 1.4  2009/02/25 10:06:07  gengdw
 * 修改历史 *** empty log message ***
 * 修改历史
 * 修改历史 Revision 1.3  2009/02/25 09:55:53  xusl
 * 修改历史 *** empty log message ***
 * 修改历史
 * 修改历史 Revision 1.2  2009/02/24 09:30:09  zhujiang
 * 修改历史 *** empty log message ***
 * 修改历史 Revision 1.1 2009/01/05 02:34:57
 * caisy 二期初始版本
 * 
 * Revision 1.4 2008/11/28 04:15:22 wengzr Added:增BlogFieldTag大字段输出标签
 * Refactor:重构AuthConfiguration
 * 
 * Revision 1.3 2008/11/25 06:23:10 wengzr Update:修改业务日志实现方式
 * Update:修改业务日志表和日志配置表,增加日志类型字段
 * 
 * Revision 1.2 2008/11/24 07:28:15 wengzr Update:重构PermissionUtil->CacheUtil
 * Update:修改业务日志
 * 
 * Revision 1.1 2008/10/07 09:25:49 wengzr *** empty log message ***
 * 
 * Revision 1.1 2008/10/05 22:07:50 wengzr Update:修复NPE错误
 * 
 * Revision 1.2 2008/10/05 14:45:07 wengzr Added:增加注释
 * 
 * Revision 1.1 2008/10/05 14:11:25 wengzr Added:增加业务日志功能
 * 
 */
public abstract class BaseLogAccessHandler implements IHandler {

	/**
	 * 将指定的业务逻辑名称转换为Contribution可识别的资源路径
	 * @param bizName
	 * @return
	 */
	private String getResourceName(String bizName) {
		String resourceName = bizName.substring(0, bizName.lastIndexOf("."));
		if(bizName.endsWith(".biz")){
			resourceName = resourceName.replace('.', '/')+ bizName.substring(bizName.lastIndexOf("."));
		} else if (bizName.endsWith(".flow") || bizName.contains(".flow?")) {
			resourceName = resourceName.replace('.', '/')+ bizName.substring(bizName.lastIndexOf("."));
		}
		return resourceName;
	}

	/**
	 * 获取指定应用Application的元数据信息
	 * @param appName 应用名称
	 * @return IApplicationMetaData 实例
	 */
	private IApplicationMetaData getCurrentApplicatonMetaData(String appName) {
		IApplicationMetaData[] applicationMetadata = MetaDataHelper.getApplicationMetaDatas(null);

		for (int i = 0; i < applicationMetadata.length; i++) {
			IApplicationMetaData data = applicationMetadata[i];
			if (appName.equals(data.getName())) {
				return data;

			}
		}
		return null;
	}

	/**
	 * 获取日志存储提供者实例
	 * 
	 * @return LogStorageProvider实例
	 */
	protected LogStorageProvider getLogStorageProvider() {
		try {
			String providerClassName =ABFConfigKey.BUSINESS_LOG_PROVIDER.getConfigValue();
			if (providerClassName != null) 
				return (LogStorageProvider) Class.forName(providerClassName).newInstance();
			
		} catch (Exception e) {
			LogUtil.logError("“日志存储提供者实例未设置正确”" , e, (Object)null);
		}
		return null;
	}

	
	/**
	 * 处理业务日志
	 * 
	 * @param context
	 * @param t
	 */
	protected int processLog(IRuntimeContext context,LogConfig logBean, String interceptType, Throwable t) {
		
		if(logBean==null||!logBean.getLogevent().equals(interceptType))
			return RET_OK;
		
		String bizName = context.getName();
		String contributionName = DataContextManager.current().getContributionMetaData().getName();

		IUserObject userObject = null;

		if (bizName.endsWith(".biz")) {

			IApplicationMetaData currentApplication = this.getCurrentApplicatonMetaData(ApplicationContext.getInstance().getAppName());

			IContributionMetaData contributionMetaData = currentApplication.getContributionMetaData(contributionName);
			userObject = DataContextManager.current().getMUODataContext().getUserObject();
			IBizMetaData bizMetaData = contributionMetaData.getBizMetaData(this.getResourceName(bizName));
			if (bizMetaData != null) {
				LogicSignature logicSignature = new LogicSignature();
				logicSignature.setParameterNames(bizMetaData.getParameterNames());
				logicSignature.setReturnNames(bizMetaData.getReturnNames());
				logicSignature.setLogicName(bizName);
				logicSignature.setLogicLabel(context.getProcessName());
				LogStorageProvider provider = getLogStorageProvider();
				if (provider != null)
					provider.log(userObject, logBean, context, logicSignature,t);
			}
		}

		else if (bizName.indexOf(".flow")!=-1) {
			userObject = (IUserObject)DataContextManager.current().getSessionCtx().get(IUserObject.KEY_IN_CONTEXT);
			//IPageFlowMetaData bizMetaData = contributionMetaData.getPageFlowMetaData(this.getResourceName(bizName));
			//if (bizMetaData != null) {
				LogicSignature logicSignature = new LogicSignature();
				//logicSignature.setParameterNames(bizMetaData.getActionNames());
				//logicSignature.setReturnNames(bizMetaData.getActionNames());
				logicSignature.setLogicName(bizName);
				logicSignature.setLogicLabel(context.getProcessName());
				LogStorageProvider provider = getLogStorageProvider();
				if (provider != null)
					provider.log(userObject, logBean, context, logicSignature,t);
			//}
		}
		return RET_OK;

	}
}
