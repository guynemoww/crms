package com.bos.pub.product.service;

import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;

import commonj.sdo.DataObject;

public interface IProductParamService {
	
	/**
	 * 查询产品控制参数集合
	 * @param criteria
	 * @param page
	 * @return
	 */
	 DataObject[] queryProductPara(CriteriaType criteria,PageCond page);
	
	 /**
	 * 根据产品ID,机构，查询其配置的控制参数
	 * @param productId 产品ID
	 * @param paraType 参数类型
	 * @param paraColumn 参数字段
	 * @return
	 */
	 Object[] queryParamByProductId(String productId,String paraType,String paraColumn);
	 
	/**
	 * delete ProductPara data by pId
	 * @param pId 主键
	 */
	void deleteProductPara(DataObject pp);
	
	/**
	 * add ProductPara data 
	 * @param pp ProductPara
	 */
	void addProductPara(DataObject pp);
	
	/**
	 * update ProductPara data 
	 * @param pp ProductPara
	 */
	void updateProductPara(DataObject pp);

	/**
	 * get one ProductPara data by pId
	 * @param pp
	 */
	DataObject getProductPara(DataObject pp);
}
