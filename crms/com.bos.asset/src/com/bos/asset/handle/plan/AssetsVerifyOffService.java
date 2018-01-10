/**
 * 
 */
package com.bos.asset.handle.plan;

import java.util.HashMap;

import com.bos.asset.AssetsUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-05-27 17:14:52
 * 
 */
@Bizlet("")
public class AssetsVerifyOffService {

	VerifyOffServiceImp verifyOffService = AssetsUtil.getService(VerifyOffServiceImp.class);

	@Bizlet("")
	public String save(DataObject data) throws Throwable {
		String msg = null;
		try {
			verifyOffService.save(data);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String actionVerifyOff(String planId, HashMap<String, Object> dataMap) {
		try {
			return (String) verifyOffService.actionVerifyOff(planId, dataMap);
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
