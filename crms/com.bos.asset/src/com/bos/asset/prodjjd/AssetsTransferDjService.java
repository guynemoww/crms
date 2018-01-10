package com.bos.asset.prodjjd;

import java.util.HashMap;
import java.util.Map;

import com.eos.system.annotation.Bizlet;

@Bizlet("")
public class AssetsTransferDjService {

	TransferDjSerciecImp serciecImp = new TransferDjSerciecImp();

	@Bizlet("")
	public Map<String, Object> createTransfer(String amountId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("transfer", serciecImp.createTransfer(amountId));
		} catch (Throwable e) {
			e.printStackTrace();
			map.put("msg", getMsg(e));
		}
		return map;
	}
	
	private String getMsg(Throwable e) {
		String msg = e.getMessage();
		if (msg == null || msg.isEmpty()) {
			msg = "操作失败";
		}
		return msg;
	}
}
