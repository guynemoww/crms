package com.bos.utp.tools;


import java.util.ArrayList;
import java.util.List;

import com.eos.runtime.metadata.IApplicationMetaData;
import com.eos.runtime.metadata.IContributionMetaData;
import com.eos.runtime.metadata.MetaDataHelper;
import com.eos.system.annotation.Bizlet;

/**
 * EOS元数据工具类
 *
 * @author 蔡述尧
 * @date 2009-04-17 13:38:06
 */
/*
 * 修改历史
 * $Log: MetaDataUtil.java,v $
 * Revision 1.3  2010/12/01 03:22:41  caisy
 * 更改编码为UTF-8
 *
 * Revision 1.2  2010/11/30 16:12:51  caisy
 * 编码改为UTF-8
 *
 * Revision 1.1  2009/04/22 10:11:33  caisy
 * EOS元数据功能
 * 
 */
@Bizlet("EOS元数据工具类")
public class MetaDataUtil {

	/**
	 * @param appName
	 * @return
	 * @author 蔡述尧
	 * 获取应用的构件包列表，如果系统中不存在对应应用名的应用则返回当前应用的构件包列表
	 */
	@Bizlet("获取应用的构件包列表")
	public static IContributionMetaData[] getContributions(String appName) {		
		return MetaDataHelper.getContributionMetaDatas(getIApplication(appName));		
	}
	@Bizlet("获取应用的构件包列表")
	public static IContributionMetaData[] getContributions(String appName,String conName) {	
		List<IContributionMetaData> list = new ArrayList<IContributionMetaData>();
		IContributionMetaData[] ar = getContributions(appName);
		if(ar!=null){
			for(IContributionMetaData con:ar){
				if(con.getName().matches(conName)){
					list.add(con);
				}
			}
		}
		return list.toArray(new IContributionMetaData[list.size()]);	
	}
	@Bizlet("获取应用的构件包列表")
	public static IContributionMetaData getContribution(String appName,String conName) {		
		IContributionMetaData[] ar = getContributions(appName);
		if(ar!=null){
			for(IContributionMetaData con:ar){
				if(con.getName().equalsIgnoreCase(conName)){
					return con;
				}
			}
			return null;
		}
		return null;
	}
	//根据名称返回当前服务器下的应用元数据接口，如果未找到返回null
	private static IApplicationMetaData getIApplication(String appName){
		IApplicationMetaData[] applications = MetaDataHelper.getApplicationMetaDatas(null);
		for (IApplicationMetaData application : applications) {
		     if (application.getName().equals(appName)) {
			    return application;			  
		     }
		}
		return null;
	}	

}
