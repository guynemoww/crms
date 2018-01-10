package com.bos.asset.handle.plan;

import java.util.HashMap;

import com.bos.asset.AssetsUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("")
public class AssetsWriteOffService {
	private WriteOffServiceImp assetWriteOffService = AssetsUtil.getService(WriteOffServiceImp.class);

	@Bizlet("")
	public String saveWriteOff(DataObject writeOff, DataObject[] writeOffLoans) {
		String msg = null;
		try {
			assetWriteOffService.save(writeOff, writeOffLoans);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String createWriteOffLoan(String planId, HashMap<String, Object>[] dataMap) {
		String msg = null;
		try {
			assetWriteOffService.createWriteOffLoan(planId, dataMap);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String updateWriteOffLoan(String planId, DataObject[] datas) {
		String msg = null;
		try {
			assetWriteOffService.updateWriteOffLoan(planId, datas);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String removeWriteOffLoan(String planId, HashMap<String, Object>[] dataMap) {
		String msg = null;
		try {
			assetWriteOffService.removeWriteOffLoan(planId, dataMap);
		} catch (Throwable e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@Bizlet("")
	public String[] actionWriteOffLoan(String planId, HashMap<String, Object>[] dataMap) {
		try {
			return assetWriteOffService.actionWriteOffLoan(planId, dataMap);
		} catch (Throwable e) {
			e.printStackTrace();
			return new String[] { e.getMessage() };
		}
	}

}
