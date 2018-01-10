package com.bos.pub.product.service;

import java.util.Map;

import com.eos.foundation.PageCond;
import commonj.sdo.DataObject;


public interface IProductOperAuthService {
	
	/**
	 *
	 * @param criteriaType
	 * @param pageCond
	 * @return
	 */
	Object[] queryProductOperAuthBySql(Map<String,String> map,PageCond page);
	
	
	/**
	 * @param poa ProductOperAuth
	 * @return
	 */
	int countProductOperAuth(DataObject poa);
	
	/**
	 *
	 * @param poa ProductOperAuth
	 */
	void addProductOperAuth(DataObject poa);


	/**
	 *
	 * @param poa ProductOperAuth[]
	 */
	void updateProductOperAuth(DataObject poa);
	
	
	/**
	 *
	 * @param poas ProductOperAuth[]
	 */
	void saveProductOperAuths(DataObject[] poas);
	
	/**
	 *
	 * @param poa ProductOperAuth
	 */
	void deleteProductOperAuth(DataObject poa);
	
}
