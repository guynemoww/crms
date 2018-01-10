/**
 * 
 */
package com.bos.asset.transfer;

import java.util.HashMap;
import java.util.Map;

import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-07-06 10:30:13
 * 
 */
@Bizlet("")
public class AssetsTransferService {
	TransferSerciecImp serciecImp = new TransferSerciecImp();

	@Bizlet("")
	public Map<String, Object> createTransfer(String contractId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("transfer", serciecImp.createTransfer(contractId));
		} catch (Throwable e) {
			e.printStackTrace();
			map.put("msg", getMsg(e));
		}
		return map;
	}

	@Bizlet("")
	public String saveTransfer(DataObject transfer) {
		String msg = null;
		try {
			serciecImp.saveTransfer(transfer);
		} catch (Throwable e) {
			e.printStackTrace();
			msg = getMsg(e);
		}
		return msg;
	}

	private String getMsg(Throwable e) {
		String msg = e.getMessage();
		if (msg == null || msg.isEmpty()) {
			msg = "操作失败";
		}
		return msg;
	}
}
