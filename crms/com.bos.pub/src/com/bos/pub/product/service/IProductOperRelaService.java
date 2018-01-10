package com.bos.pub.product.service;

import java.util.Map;

import commonj.sdo.DataObject;

public interface IProductOperRelaService {
	
	
	/**
	 * 
	 * @param authId
	 * @return pors
	 */
	Object[] queryProductWithOperRela(Map<String,String> param);
	
	/**
	 * delete ProductOperRela data by authId
	 * @param authId
	 */
	void deleteProductOperRelaByAuthId(String authId);
	
	/**
	 * 
	 * @param por ProductOperRela
	 */
	void addProductOperRela(DataObject por);
	
	/**
	 * 
	 * @param pors ProductOperRela
	 * @param authId String
	 */
	void saveProductOperRela(DataObject[] pors,String authId);

	
}
