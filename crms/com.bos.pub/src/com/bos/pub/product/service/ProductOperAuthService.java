package com.bos.pub.product.service;


import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.PageCond;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.BeanFactory;
import com.eos.spring.DASDaoSupport;
import com.eos.system.utility.StringUtil;
import com.primeton.spring.support.DataObjectUtil;

import commonj.sdo.DataObject;

public class ProductOperAuthService extends DASDaoSupport implements IProductOperAuthService {

	private final String tbSysOperateAuthorization="com.bos.pub.product.TbSysOperateAuthorization";
	
	public Object[] queryProductOperAuthBySql(Map<String,String> map,PageCond page) {
		
		Object[] results=DatabaseExt.queryByNamedSqlWithPage(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.product.queryProductOperAuth", page, map);
		return results;
		
	}
	
	public int countProductOperAuth(DataObject poa) {
		
		int num = getDASTemplate().countByTemplate(poa);
		return num;
	}
	
	public void addProductOperAuth(DataObject poa) {
		getDASTemplate().insertEntity(poa);
	}
	
	public void deleteProductOperAuth(DataObject poa) {
		getDASTemplate().deleteEntityCascade(poa);
	}


	public void updateProductOperAuth(DataObject poa) {
		getDASTemplate().updateEntity(poa);
	}

	public void saveProductOperAuths(DataObject[] poas) {
		for(DataObject poa : poas)
		{
			
			String orgNum = poa.getString("authOrgNum");
			String roleId = poa.getString("roleId");
			//这两个值，有一个为空，则跳过，不保存
			if(StringUtil.isNull(roleId)||StringUtil.isNull(orgNum)){
				continue;
			}
			
			
			//赋值经办机构、人员
			poa.setString("orgNum", GitUtil.getCurrentOrgCd());
			poa.setString("userNum", GitUtil.getCurrentUserId());
			//根据不同的操作，存储信息
			String state = (String)poa.get("_state");
			if ("added".equals(state)) 
			{
				//如果这两个值已经存在，则不保存
				DataObject template  = DataObjectUtil.createDataObject(tbSysOperateAuthorization);
				template.setString("authOrgNum", orgNum);
				template.setString("roleId", roleId);
				int num = countProductOperAuth(template);
				if(num>0){
					continue;
				}
				
				addProductOperAuth(poa);
			} 
			
			else if ("removed".equals(state) || "deleted".equals(state)) 
			{
				//先删除授权关系
				BeanFactory factory = BeanFactory.newInstance();
				ProductOperRelaService pors = factory.getBean("ProductOperRelaBean");
				pors.deleteProductOperRelaByAuthId(poa.getString("orgOperateAuthorizationId"));
				//再删除授权机构信息
				deleteProductOperAuth(poa);
			} 

			else if ("modified".equals(state)) //更新：_state为空，或modified
			{
				//更新时，不可更新授权机构与角色
				DataObject template  = DataObjectUtil.createDataObject(tbSysOperateAuthorization);
				template.setString("orgOperateAuthorizationId", poa.getString("orgOperateAuthorizationId"));
				template.setString("isUsed", poa.getString("isUsed"));
				updateProductOperAuth(template);
			}
		}
	}

}
