package com.bos.pub;

import java.util.List;

import com.bos.bps.service.RelativeDataManagerService;
import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

public class EosUtil {
	/**
	 * 根据流程编号到eos中查询对应的申请信息编号
	 * 
	 * @param processInstId
	 * @return
	 */
	public static String getBizId(String processInstId) {
		String[] xpath = { "bizId" };
		List<Object> list;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("根据流程编号[" + processInstId + "]获取业务信息失败");
		}
		String bizId;
		if (list.isEmpty() || (bizId = (String) list.get(0)) == null || bizId.isEmpty()) {
			throw new RuntimeException("根据流程编号[" + processInstId + "]获取业务信息失败");
		}
		return bizId;
	}

	/**
	 * 创建一个空对象
	 * 
	 * @return
	 */
	public static DataObject createDataObject() {
		return DataFactory.INSTANCE.create("commonj.sdo", "DataObject");
	}
}
