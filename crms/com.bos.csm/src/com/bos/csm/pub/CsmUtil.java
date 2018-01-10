package com.bos.csm.pub;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;

import commonj.sdo.DataObject;

public class CsmUtil {

	/**
	 * 流程结束后需要操作
	 * 
	 * @param partyId
	 *            客户ID
	 * @param processId
	 *            流程ID
	 * 
	 */
	public static void setValueForBps(String partyId, String processId, TraceLogger logger) {

		// 实例-流程记录
		DataObject mod = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmModifyInfo");
		mod.set("partyId", partyId);
		mod.set("processId", processId);
		// 查询流程记录
		DataObject[] mods = DatabaseUtil.queryEntitiesByTemplate("default", mod);

		for (int i = 0; i < mods.length; i++) {
			// 记录所需字段
			String comName = mods[i].getString("modifyColumn");
			String newValue = mods[i].getString("newModifyValue");
			String entityName = mods[i].getString("modifyEntity");

			logger.info("修改字段名称：" + comName + "新值：" + newValue + " 实体名称:" + entityName);

			DataObject dataObj = DataObjectUtil.createDataObject(entityName);

			dataObj.set(comName, newValue);
			dataObj.set("partyId", partyId);
			// 更新实体
			// 组织机构代码特殊校验
			if (comName.equals("certificateCode")) {
				dataObj.set("certificateTypeCd", "20001");
				DatabaseUtil.updateEntityByTemplate("default", dataObj, dataObj);
				return;
			}

			DatabaseUtil.updateEntity("default", dataObj);
			logger.info(comName + "更新完");
		}

	}

	/**
	 * 根据客户类型判断是否为对私客户
	 * 
	 * @param partyType
	 * @return
	 */
	public static boolean isNatural(String partyType) {
		return "02".equals(partyType);
	}

	/**
	 * 根据客户类型判断是否为对公客户
	 * 
	 * @param partyType
	 * @return
	 */
	public static boolean isCorporation(String partyType) {
		return !isNatural(partyType);
	}

	public static String getCorpAccReportType(String corpType) {
		String[][] types = { { "def", "007,008,009,011" }, { "1", "002,006" }, { "3", "019" }, { "2", "002,004,012,013,014" }, { "5", "019" }, { "6", "002,004,006,008" } };
		if (corpType == null || corpType.isEmpty()) {
			return types[0][1];
		}
		for (int i = 1; i < types.length; i++) {
			if (corpType.equals(types[i][0])) {
				return types[i][1];
			}
		}
		return types[0][1];
	}
}
