/**
 * 
 */
package com.bos.asset.changeMgr;

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
public class AssetsChangeMgrService {
	ChangeMgrSerciecImp serciecImp = new ChangeMgrSerciecImp();

	@Bizlet("")
	public Map<String, Object> createChangeMgr(String transferId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("changeMgr", serciecImp.createChangeMgr(transferId));
		} catch (Throwable e) {
			e.printStackTrace();
			map.put("msg", getMsg(e));
		}
		return map;
	}

	@Bizlet("")
	public String saveChangeMgr(DataObject changeMgr) {
		String msg = null;
		try {
			serciecImp.saveChangeMgr(changeMgr);
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
