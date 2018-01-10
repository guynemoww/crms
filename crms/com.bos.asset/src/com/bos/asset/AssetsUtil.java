package com.bos.asset;

import java.util.HashMap;
import java.util.Map;

import com.bos.asset.handle.IAssetHandlePlanService;
import com.bos.asset.handle.plan.CleanTakeEntrustServiceImp;
import com.bos.asset.handle.plan.CleanTakeLawServiceImp;
import com.bos.asset.handle.plan.CleanTakeMoneyServiceImp;
import com.bos.asset.handle.plan.VerifyOffServiceImp;
import com.bos.asset.handle.plan.WriteOffServiceImp;
import com.bos.pub.entity.name.AssetsTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.primeton.mgrcore.client.CrmsMgrCallCoreImpl;
import com.primeton.mgrcore.client.CrmsMgrCallCoreProxy;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.tsl.BaseVO;
import commonj.sdo.DataObject;

public class AssetsUtil {

	public static final Map<String, IAssetHandlePlanService> serviceMap;
	public static final Map<Class<?>, Object> interfaceMap;
	public static final Map<String, String[]> planTypeMap;

	public static final String PLAN_SEQ_NAME = "CZ";
	public static final String TRANSFER_SEQ_NAME = "CZ";

	public static final String FLOW_TRANSFER_TAB = "asset_transfer";

	public static enum ASSETS_STATUS {
		START("10"), AUDIT("20"), PASS("30"), REJECT("40");
		private String key;

		ASSETS_STATUS(String key) {
			this.key = key;
		}

		public static ASSETS_STATUS get(String key) {
			ASSETS_STATUS[] temps = values();
			for (ASSETS_STATUS s : temps) {
				if (s.value().equals(key)) {
					return s;
				}
			}
			throw new IllegalArgumentException("错误的处置方案.状态:[" + key + "]");
		}

		public String toString() {
			return key;
		}

		public String value() {
			return key;
		}
	}

	public static enum INTERFACE_CODE {
		T1106, T1206, T1207, T1107, T1410, B1102
	}

	public static enum INTERFACE_STATUS {
		UNACTION("10"), SUCCESS("20"), FAIL("30");
		private String key;

		INTERFACE_STATUS(String key) {
			this.key = key;
		}

		public static INTERFACE_STATUS get(String key) {
			INTERFACE_STATUS[] temps = values();
			for (INTERFACE_STATUS s : temps) {
				if (s.value().equals(key)) {
					return s;
				}
			}
			throw new IllegalArgumentException("错误的接口通讯.状态[" + key + "]");
		}

		public String toString() {
			return key;
		}

		public String value() {
			return key;
		}
	}

	static {
		serviceMap = new HashMap<String, IAssetHandlePlanService>();
		IAssetHandlePlanService servcice = new CleanTakeMoneyServiceImp();
		serviceMap.put("10_10", servcice);
		serviceMap.put(servcice.getServiceName(), servcice);
		//
		servcice = new CleanTakeLawServiceImp();
		serviceMap.put("10_20", servcice);
		serviceMap.put(servcice.getServiceName(), servcice);
		//
		servcice = new CleanTakeEntrustServiceImp();
		serviceMap.put("10_30", servcice);
		serviceMap.put(servcice.getServiceName(), servcice);
		//
		servcice = new WriteOffServiceImp();
		serviceMap.put("20", servcice);
		serviceMap.put(servcice.getServiceName(), servcice);
		//
		servcice = new VerifyOffServiceImp();
		serviceMap.put("30", servcice);
		serviceMap.put(servcice.getServiceName(), servcice);

		// ------------------------------
		interfaceMap = new HashMap<Class<?>, Object>();
		interfaceMap.put(CrmsCallPlusProxy.class, new CrmsCallPlusImpl());
		interfaceMap.put(CrmsMgrCallCoreProxy.class, new CrmsMgrCallCoreImpl());

		// ------------------------------
		planTypeMap = new HashMap<String, String[]>();
		planTypeMap.put("10_10", new String[] { AssetsTableName.TB_ASSET_CLEAN_TAKE_MONEY });
		planTypeMap.put("10_20", new String[] { AssetsTableName.TB_ASSET_CLEAN_TAKE_LAW, AssetsTableName.TB_ASSET_CLEAN_TAKE_LAW_CON });
		planTypeMap.put("10_30", new String[] { AssetsTableName.TB_ASSET_CLEAN_TAKE_ENTRUST, AssetsTableName.TB_ASSET_CLEAN_TAKE_ENT_CON });
		planTypeMap.put("20", new String[] { AssetsTableName.TB_ASSET_WRITE_OFF, AssetsTableName.TB_ASSET_WRITE_OFF_LOAN });
		planTypeMap.put("30", new String[] { AssetsTableName.TB_ASSET_VERIFY_OFF });
	}

	public static void editValidate(String dbName, DataObject obj) {
		editValidate(dbName, obj, "id");
	}

	public static void editValidate(String dbName, DataObject obj, String fieldName) {
		String planId = obj.getString(fieldName);
		if (planId == null || (planId = planId.trim()).isEmpty()) {
			throw new RuntimeException("没有获取到[处置方案.编号]");
		}
		editValidate(dbName, planId);
	}

	@SuppressWarnings("unchecked")
	public static ASSETS_STATUS getPlanStatus(String dbName, String planId) {
		Object[] objs = DatabaseExt.queryByNamedSql(dbName, "com.bos.asset.handle.HandleSql.editHandleValid", planId);
		if (objs == null || objs.length != 1) {
			throw new RuntimeException("错误的处置方案.编号:[" + planId + "]");
		}
		Map<String, Object> map = (Map<String, Object>) objs[0];
		return ASSETS_STATUS.get((String) map.get("STATUS"));
	}

	public static void editValidate(String dbName, String planId) {
		ASSETS_STATUS status = getPlanStatus(dbName, planId);
		if (ASSETS_STATUS.START != status) {
			String statusName = BusinessDictUtil.getDictName("XD_ASSET002", status.value());
			if (statusName == null) {
				throw new RuntimeException("错误的数据<状态>:[" + planId + "]");
			} else {
				throw new RuntimeException("该数据当前状态为[" + statusName + "]，无法继续操作");
			}
		}
	}

	public static void paramValidate(Map<String, Object> map, String... fieldNames) {
		for (String field : fieldNames) {
			Object value = map.get(field);
			if (value == null) {

			} else if (value instanceof String && ((String) value).trim().isEmpty()) {

			} else if (value instanceof Number && ((Number) value).doubleValue() == 0) {

			} else {
				continue;//
			}
			throw new RuntimeException("mast param is null[" + field + "]");
		}
	}

	public static BaseVO createBaseVO(INTERFACE_CODE tranCod, String orgNum) {
		return createBaseVO(tranCod, orgNum, null);
	}

	public static CrmsCallPlusProxy getAplusInterface() {
		CrmsCallPlusProxy entity = AssetsUtil.getInterface(CrmsCallPlusProxy.class);
		if (entity == null) {
			throw new RuntimeException("配置信息错误：CrmsCallPlusProxy接口没有配置");
		}
		return entity;
	}

	public static CrmsMgrCallCoreProxy getCoreInterface() {
		CrmsMgrCallCoreProxy entity = AssetsUtil.getInterface(CrmsMgrCallCoreProxy.class);
		if (entity == null) {
			throw new RuntimeException("配置信息错误：CrmsCallPlusProxy接口没有配置");
		}
		return entity;
	}

	public static BaseVO createBaseVO(INTERFACE_CODE tranCod, String orgNum, Long rcnStan) {
		return CrmsCallPlusImpl.createAplusBaseVO(tranCod.toString(), orgNum, rcnStan);
	}

	public static IAssetHandlePlanService getService(String planType, String cleanTakeType) {
		String key = planType;
		if (cleanTakeType != null) {
			key += "_" + cleanTakeType;
		}
		return serviceMap.get(key);
	}

	public static String[] getPlanEntity(String planType, String cleanTakeType) {
		String key = planType;
		if (cleanTakeType != null) {
			key += "_" + cleanTakeType;
		}
		return planTypeMap.get(key);
	}

	@SuppressWarnings("unchecked")
	public static <T extends IAssetHandlePlanService> T getService(Class<T> clazz) {
		return (T) serviceMap.get(clazz.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInterface(Class<T> clazz) {
		return (T) interfaceMap.get(clazz);
	}

	public static String getDBName() {
		return DBUtil.DB_NAME_DEF;
	}
}
