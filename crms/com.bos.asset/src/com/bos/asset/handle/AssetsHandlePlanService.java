/**
 * 
 */
package com.bos.asset.handle;

import java.util.HashMap;
import java.util.Map;

import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-05-27 16:26:40
 * 
 */
@Bizlet("")
public class AssetsHandlePlanService {
	HandlePlanServiceImp assetHandlePlanService = new HandlePlanServiceImp();

	@Bizlet("")
	public Map<String, Object> createAssetHandlePlan(String planType, String cleanTakeType, Map<String, Object> data) {
		Map<String, Object> datas;
		try {
			datas = assetHandlePlanService.createAssetHandlePlan(planType, cleanTakeType, data);
		} catch (Throwable e) {
			datas = new HashMap<String, Object>();
			datas.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return datas;
	}

	@Bizlet("")
	public String save(DataObject data) throws Throwable {
		String msg = null;
		try {
			assetHandlePlanService.save(data);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String remove(String planId) throws Throwable {
		String msg = null;
		try {
			assetHandlePlanService.remove(planId);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

}
