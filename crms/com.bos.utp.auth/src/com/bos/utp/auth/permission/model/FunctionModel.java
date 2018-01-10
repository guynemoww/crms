package com.bos.utp.auth.permission.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 功能模型<BR>
 *
 * @author 翁增仁 
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: FunctionModel.java,v $
 * Revision 1.4  2010/12/01 10:01:22  caisy
 * 更改编码为UTF-8
 *
 * Revision 1.3  2009/04/14 03:54:32  caisy
 * 资源权限控制
 *
 * Revision 1.2  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/05 02:34:57  caisy
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.3  2008/09/25 16:35:51  wengzr
 * Update:重构缓存模块,将CacheLoader以及CacheManager移入cache包
 * 并修改缓存模型删除模型中的名称XXXname字段
 *
 * Revision 1.2  2008/08/18 14:19:49  wengzr
 * 提交CVS
 *
 * Revision 1.1  2008/08/13 15:55:59  wengzr
 * 提交CVS
 *
 */
public class FunctionModel{

	private BaseFunction function;

	//以资源路径respath为key,以ResourceModel为value
	private Map<String ,ResourceModel> resources=new HashMap<String,ResourceModel>();

	/**
	 *
	 * @param function
	 */
	public FunctionModel(BaseFunction function){
		this.function=function;
	}

	public void setResources(Map<String, ResourceModel> resources) {
		this.resources = resources;
	}



	public Map<String, ResourceModel> getResources() {
		return resources;
	}

	/**
	 * 为功能添加资源，如果资源路径为空，则不增加
	 *
	 * @param resid 资源ID
	 * @param respath 资源路径名
	 * @param restype 资源类型
	 */
	public void addResource(String resid,String respath,String restype){

		if(respath!=null){
			ResourceModel resource=new ResourceModel();
			resource.setResId(resid.trim());
			resource.setResPath(respath.trim());
			resource.setResType(restype.trim());

			resources.put(resource.getResPath(), resource);
		}
	}

	public BaseFunction getFunction() {
		return function;
	}

	public void setFunction(BaseFunction function) {
		this.function = function;
	}


}
