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
 * @date 2017-05-27 14:41:32
 * 
 */
@Bizlet("")
public class AssetsCleanTakeService {
	CleanTakeEntrustServiceImp entrustService = AssetsUtil.getService(CleanTakeEntrustServiceImp.class);
	CleanTakeMoneyServiceImp moneyService = AssetsUtil.getService(CleanTakeMoneyServiceImp.class);
	CleanTakeLawServiceImp lawService = AssetsUtil.getService(CleanTakeLawServiceImp.class);

	@Bizlet("")
	public String saveCleanTakeEntrust(DataObject cleanTakEntrust, DataObject[] cleanTakEntrustCon) {
		String msg = null;
		try {
			entrustService.save(cleanTakEntrust, cleanTakEntrustCon);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String saveCleanTakeMoney(DataObject data) {
		String msg = null;
		try {
			moneyService.save(data);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String saveCleanTakeLaw(DataObject cleanTakLaw, DataObject[] cleanTakLawCon) {
		String msg = null;
		try {
			lawService.save(cleanTakLaw, cleanTakLawCon);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String createCleanTakLawCon(String planId, HashMap<String, Object>[] dataMap) throws Throwable {
		String msg = null;
		try {
			lawService.createCleanTakLawCon(planId, dataMap);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String createCleanTakeEntrustCon(String planId, HashMap<String, Object>[] dataMap) throws Throwable {
		String msg = null;
		try {
			entrustService.createCleanTakeEntrustCon(planId, dataMap);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String removeCleanTakLawCon(String planId, HashMap<String, Object>[] dataMap) throws Throwable {
		String msg = null;
		try {
			lawService.removeCleanTakLawCon(planId, dataMap);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String removeCleanTakeEntCon(String planId, HashMap<String, Object>[] dataMap) throws Throwable {
		String msg = null;
		try {
			entrustService.removeCleanTakeEntrustCon(planId, dataMap);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}
}
