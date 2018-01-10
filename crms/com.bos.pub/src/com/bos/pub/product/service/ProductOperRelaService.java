package com.bos.pub.product.service;

import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.DASDaoSupport;

import commonj.sdo.DataObject;

public class ProductOperRelaService extends DASDaoSupport implements IProductOperRelaService {

	public Object[] queryProductWithOperRela(Map<String,String> param) {
		
		Object [] obj = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.product.queryProductWithOperRela", param);
		return obj;
	}

	public void deleteProductOperRelaByAuthId(String authId) {

		DataObject por = DataObjectUtil.createDataObject("com.bos.pub.product.TbSysProductOperateRela");
		por.setString("orgOperateAuthorizationId", authId);
		getDASTemplate().deleteByTemplate(por);
		
	}

	public void addProductOperRela(DataObject por) {
		getDASTemplate().insertEntity(por);
	}

	public void saveProductOperRela(DataObject[] pors, String authId) {
		
		//删除已经配置的授权数据
		deleteProductOperRelaByAuthId(authId);
		
		if(null != pors && pors.length>0){
			
			//循环插入
			for (int i = 0; i < pors.length; i++) {
				DataObject dataObject = pors[i];
				DataObject por = DataObjectUtil.createDataObject("com.bos.pub.product.TbSysProductOperateRela");
				por.setString("productId",dataObject.getString("productCd"));
				por.setString("orgOperateAuthorizationId", authId);
				addProductOperRela(por);
			}
		}
	}

}
