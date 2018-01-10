package com.bos.pub.product.service;

import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.DASDaoSupport;
import com.eos.system.utility.StringUtil;

import commonj.sdo.DataObject;

public class ProductParamService extends DASDaoSupport implements IProductParamService {

	public DataObject[] queryProductPara(CriteriaType criteria, PageCond page) {
		
		DataObject[] data = DatabaseExt.queryEntitiesByCriteriaEntityWithPage(GitUtil.DEFAULT_DS_NAME, criteria, page);
		return data;
	}

	public void deleteProductPara(DataObject pp) {
		
		getDASTemplate().deleteEntity(pp);
	}

	public void addProductPara(DataObject pp) {
		getDASTemplate().insertEntity(pp);
	}

	public void updateProductPara(DataObject pp) {
		getDASTemplate().updateEntity(pp);
	}

	public DataObject getProductPara(DataObject pp) {
		
		 getDASTemplate().expandEntity(pp);
		 return pp;
	}

	public Object[] queryParamByProductId(String productId,String paraType,String paraColumn) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("productId", productId);
		map.put("orgcode", GitUtil.getCurrentOrgCd());
		map.put("paraType", paraType);
		if(StringUtil.isNotNull(paraColumn)){
			map.put("paraColumn", paraColumn);
		}
		
		Object[] obj = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.product.queryParamOfProduct", map);
		return obj;
	}

}
