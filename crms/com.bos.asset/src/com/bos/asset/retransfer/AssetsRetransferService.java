/**
 * 
 */
package com.bos.asset.retransfer;

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
public class AssetsRetransferService {
	RetransferSerciecImp serciecImp = new RetransferSerciecImp();

	@Bizlet("")
	public Map<String, Object> createRetransfer(String transferId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("retransfer", serciecImp.createRetransfer(transferId));
		} catch (Throwable e) {
			e.printStackTrace();
			map.put("msg", getMsg(e));
		}
		return map;
	}

	@Bizlet("")
	public String saveRetransfer(DataObject retransfer) {
		String msg = null;
		try {
			serciecImp.saveRetransfer(retransfer);
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
