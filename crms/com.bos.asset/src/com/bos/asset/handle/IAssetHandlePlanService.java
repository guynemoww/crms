package com.bos.asset.handle;

import java.util.Map;

public interface IAssetHandlePlanService {

	void createValidate(Map<String, Object> data);

	void create(Map<String, Object> param) throws Throwable;

	Object submitToInterface(String planId, Map<String, Object> param);

	String getDBName();

	String getServiceName();

	boolean inFlowPath();

	void processSubmitValid(String planId) throws Throwable;

}
