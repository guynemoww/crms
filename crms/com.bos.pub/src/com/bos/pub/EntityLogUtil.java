package com.bos.pub;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bos.utp.auth.bizlet.LogonUtil;
import com.eos.data.datacontext.DataContextManager;
import com.eos.foundation.data.DataContextUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import com.primeton.data.sdo.impl.DataObjectImpl;
import com.primeton.data.sdo.impl.types.DataObjectType;
import commonj.sdo.DataObject;

@Bizlet("根据EntityLogConfig.xml，拦截交易，记录交易中对数据库的增删改")
public class EntityLogUtil {
	private static Map<String, EntityLogTrade> config = new HashMap<String, EntityLogTrade>();

	private static EntityLogTrade paramTradeConfig = new EntityLogTrade();

	public static boolean isNeedIntercept(String tableName) {
		try {
			return isNeedIntercept(getTradeName(), tableName);
		} catch (EOSException e) {
			LogUtil.logError("判断是否需要拦截记录参数日志时出错！", e, new Object[0]);
		}
		return false;
	}

	@Bizlet("加载日志明细，并转化为可传递到前台的数组")
	public static DataObject[] getLogDetailByLogId(String logId)
			throws EOSException {
		if (StringUtils.isEmpty(logId))
			return null;

		ensureConfigLoaded();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("log_id", logId);
		map.put("logId", logId);
		Object[] logs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.log.selectLog", map);
		if (null == logs || logs.length != 1)
			return null;
		DataObject log = (DataObject) logs[0];

		Object[] arr = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.log.selectLogDetail", map);
		if (null == arr || arr.length < 1)
			return null;

		String logType = log.getString("LOG_TYPE");
		String logFunccode = log.getString("LOG_FUNCCODE");
		EntityLogTrade.Table table = null;
		List<String> primaryKeys = new ArrayList<String>();
		String[] primaryKeyCols = null;
		if ("03".equals(logType)) {// 单个参数表的单词增删改操作
			String cnt = "001";
			DataObject obj = null;
			String opType = null;
			String detailId = null;
			String tbName = null;
			List<DataObject> list = new ArrayList<DataObject>();
			Set<String> tables = new HashSet<String>();
			for (int i = 0, size = arr.length; i < size; i++) {
				DataObject col = (DataObject) arr[i];
				String TB_NAME = col.getString("TB_NAME");
				if (TB_NAME.startsWith(cnt)) {
					// 同一个表
					if (null == obj) {
						primaryKeys.clear();
						obj = new DataObjectImpl(new DataObjectType());
						opType = col.getString("OP_TYPE");
						detailId = col.getString("DETAIL_ID");
						tbName = col.getString("TB_NAME");
						primaryKeyCols = GitUtil
								.getPrimaryKeysWOUnderline(tbName.substring(4));
						tables.add(tbName.substring(4));
					}
				} else {
					// 不同的表
					cnt = TB_NAME.substring(0, 3);
					if (null != obj) {
						// primaryKeys.clear();
						list.add(obj);
						obj.setString("logOpType", opType);
						obj.setString("logDetailId", detailId);
						obj.setString("logTableName", tbName.substring(4));
						if (primaryKeys.size() > 0) {
							obj.setString("logPrimaryKey", ArrayUtils
									.toString(primaryKeys
											.toArray(new String[primaryKeys
													.size()])));
						} else {
							obj.setString("logPrimaryKey", "");
						}
						// tbName = col.getString("TB_NAME");
						// primaryKeyCols = GitUtil
						// .getPrimaryKeysWOUnderline(tbName.substring(4));
					}
					obj = new DataObjectImpl(new DataObjectType());
					opType = col.getString("OP_TYPE");
					detailId = col.getString("DETAIL_ID");
					tbName = col.getString("TB_NAME");
					tables.add(tbName.substring(4));
					primaryKeys.clear();
					primaryKeyCols = GitUtil.getPrimaryKeysWOUnderline(tbName
							.substring(4));
				}
				obj.set(col.getString("COL_NAME"), col.getString("VAL_NEW"));
				if (ArrayUtils.indexOf(primaryKeyCols, col
						.getString("COL_NAME").toUpperCase()) >= 0) {
					primaryKeys.add(col.getString("VAL_NEW"));
				}
			}
			if (null != obj) {
				list.add(obj);
				obj.setString("logOpType", opType);
				obj.setString("logDetailId", detailId);
				obj.setString("logTableName", tbName.substring(4));
				if (primaryKeys.size() > 0) {
					obj.setString("logPrimaryKey", ArrayUtils
							.toString(primaryKeys
									.toArray(new String[primaryKeys.size()])));
				} else {
					obj.setString("logPrimaryKey", "");
				}
				tables.add(tbName.substring(4));
			}
			for (String nm : tables) {
				// DataObject作为meta信息
				DataObject meta = new DataObjectImpl(new DataObjectType());
				list.add(meta);
				table = indexOf(paramTradeConfig.getTables(), nm.replaceAll(
						"_", ""));
				if (null == table)
					throw new EOSException("没有找到相关配置信息");

				meta.setString("isLogMeta", "true");
				if (table.getName().contains(".")) {
					meta.setString("name", table.getName().substring(
							table.getName().lastIndexOf('.') + 1));
				} else {
					meta.setString("name", table.getName());
				}
				meta.setString("displayName", table.getDisplayName());
				List<String[]> cols = new ArrayList<String[]>();
				meta.setList("cols", cols);
				for (int i = 0, size = table.getCols().size(); i < size; i++) {
					EntityLogTrade.Column col = table.getCols().get(i);
					cols.add(new String[] { col.getName(),
							col.getDisplayName(), col.getDictTypeId() });
				}
			}
			return list.toArray(new DataObject[list.size()]);
		} else if ("04".equals(logType)) {// 单个交易的各表的操作
			EntityLogTrade tr = getEntityLogTrade(logFunccode);
			if (null == tr)
				return null;
			String cnt = "001";
			DataObject obj = null;
			String opType = null;
			String detailId = null;
			String tbName = null;
			List<DataObject> list = new ArrayList<DataObject>();
			Set<String> tables = new HashSet<String>();
			for (int i = 0, size = arr.length; i < size; i++) {
				DataObject col = (DataObject) arr[i];
				String TB_NAME = col.getString("TB_NAME");
				if (TB_NAME.startsWith(cnt)) {
					// 同一个表
					if (null == obj) {
						primaryKeys.clear();
						obj = new DataObjectImpl(new DataObjectType());
						opType = col.getString("OP_TYPE");
						detailId = col.getString("DETAIL_ID");
						tbName = col.getString("TB_NAME");
						tables.add(tbName.substring(4));
						primaryKeyCols = GitUtil
								.getPrimaryKeysWOUnderline(tbName.substring(4));
					}
				} else {
					// 不同的表
					cnt = TB_NAME.substring(0, 3);
					if (null != obj) {
						// primaryKeys.clear();
						list.add(obj);
						obj.setString("logOpType", opType);
						obj.setString("logDetailId", detailId);
						obj.setString("logTableName", tbName.substring(4));
						if (primaryKeys.size() > 0) {
							obj.setString("logPrimaryKey", ArrayUtils
									.toString(primaryKeys
											.toArray(new String[primaryKeys
													.size()])));
						} else {
							obj.setString("logPrimaryKey", "");
						}
						// tbName = col.getString("TB_NAME");
						// primaryKeyCols = GitUtil
						// .getPrimaryKeysWOUnderline(tbName.substring(4));
					}
					obj = new DataObjectImpl(new DataObjectType());
					opType = col.getString("OP_TYPE");
					detailId = col.getString("DETAIL_ID");
					tbName = col.getString("TB_NAME");
					tables.add(tbName.substring(4));
					primaryKeys.clear();
					primaryKeyCols = GitUtil.getPrimaryKeysWOUnderline(tbName
							.substring(4));
				}
				obj.set(col.getString("COL_NAME"), col.getString("VAL_NEW"));
				if (ArrayUtils.indexOf(primaryKeyCols, col
						.getString("COL_NAME").toUpperCase()) >= 0) {
					primaryKeys.add(col.getString("VAL_NEW"));
				}
			}
			if (null != obj) {
				list.add(obj);
				obj.setString("logOpType", opType);
				obj.setString("logDetailId", detailId);
				obj.setString("logTableName", tbName.substring(4));
				if (primaryKeys.size() > 0) {
					obj.setString("logPrimaryKey", ArrayUtils
							.toString(primaryKeys
									.toArray(new String[primaryKeys.size()])));
				} else {
					obj.setString("logPrimaryKey", "");
				}
				tables.add(tbName.substring(4));
			}
			for (String nm : tables) {
				// DataObject作为meta信息
				DataObject meta = new DataObjectImpl(new DataObjectType());
				list.add(meta);
				table = indexOf(tr.getTables(), nm.replaceAll("_", ""));
				if (null == table)
					throw new EOSException("没有找到相关配置信息");

				meta.setString("isLogMeta", "true");
				if (table.getName().contains(".")) {
					meta.setString("name", table.getName().substring(
							table.getName().lastIndexOf('.') + 1));
				} else {
					meta.setString("name", table.getName());
				}
				meta.setString("displayName", table.getDisplayName());
				List<String[]> cols = new ArrayList<String[]>();
				meta.setList("cols", cols);
				for (int i = 0, size = table.getCols().size(); i < size; i++) {
					EntityLogTrade.Column col = table.getCols().get(i);
					cols.add(new String[] { col.getName(),
							col.getDisplayName(), col.getDictTypeId() });
				}
			}
			return list.toArray(new DataObject[list.size()]);
		} else {// 业务快照
			EntityLogTrade tr = getEntityLogTrade(logFunccode);
			if (null == tr)
				return null;
			String cnt = "001";
			DataObject obj = null;
			String opType = null;
			String detailId = null;
			String tbName = null;
			List<DataObject> list = new ArrayList<DataObject>();
			Set<String> tables = new HashSet<String>();
			for (int i = 0, size = arr.length; i < size; i++) {
				DataObject col = (DataObject) arr[i];
				String TB_NAME = col.getString("TB_NAME");
				if (TB_NAME.startsWith(cnt)) {
					// 同一个表
					if (null == obj) {
						primaryKeys.clear();
						obj = new DataObjectImpl(new DataObjectType());
						opType = col.getString("OP_TYPE");
						detailId = col.getString("DETAIL_ID");
						tbName = col.getString("TB_NAME");
						tables.add(tbName.substring(4));
						primaryKeyCols = GitUtil
								.getPrimaryKeysWOUnderline(tbName.substring(4));
					}
				} else {
					// 不同的表
					cnt = TB_NAME.substring(0, 3);
					if (null != obj) {
						// primaryKeys.clear();
						list.add(obj);
						obj.setString("logOpType", opType);
						obj.setString("logDetailId", detailId);
						obj.setString("logTableName", tbName.substring(4));
						if (primaryKeys.size() > 0) {
							obj.setString("logPrimaryKey", ArrayUtils
									.toString(primaryKeys
											.toArray(new String[primaryKeys
													.size()])));
						} else {
							obj.setString("logPrimaryKey", "");
						}
						// tbName = col.getString("TB_NAME");
						// primaryKeyCols = GitUtil
						// .getPrimaryKeysWOUnderline(tbName.substring(4));
					}
					obj = new DataObjectImpl(new DataObjectType());
					opType = col.getString("OP_TYPE");
					detailId = col.getString("DETAIL_ID");
					tbName = col.getString("TB_NAME");
					tables.add(tbName.substring(4));
					primaryKeys.clear();
					primaryKeyCols = GitUtil.getPrimaryKeysWOUnderline(tbName
							.substring(4));
				}
				obj.set(col.getString("COL_NAME"), col.getString("VAL_NEW"));
				if (ArrayUtils.indexOf(primaryKeyCols, col
						.getString("COL_NAME").toUpperCase()) >= 0) {
					primaryKeys.add(col.getString("VAL_NEW"));
				}
			}
			if (null != obj) {
				list.add(obj);
				obj.setString("logOpType", opType);
				obj.setString("logDetailId", detailId);
				obj.setString("logTableName", tbName.substring(4));
				if (primaryKeys.size() > 0) {
					obj.setString("logPrimaryKey", ArrayUtils
							.toString(primaryKeys
									.toArray(new String[primaryKeys.size()])));
				} else {
					obj.setString("logPrimaryKey", "");
				}
				tables.add(tbName.substring(4));
			}
			for (String nm : tables) {
				// DataObject作为meta信息
				DataObject meta = new DataObjectImpl(new DataObjectType());
				list.add(meta);
				table = indexOf(tr.getTables(), nm.replaceAll("_", ""));
				if (null == table)
					throw new EOSException("没有找到相关配置信息");

				meta.setString("isLogMeta", "true");
				if (table.getName().contains(".")) {
					meta.setString("name", table.getName().substring(
							table.getName().lastIndexOf('.') + 1));
				} else {
					meta.setString("name", table.getName());
				}
				meta.setString("displayName", table.getDisplayName());
				List<String[]> cols = new ArrayList<String[]>();
				meta.setList("cols", cols);
				for (int i = 0, size = table.getCols().size(); i < size; i++) {
					EntityLogTrade.Column col = table.getCols().get(i);
					cols.add(new String[] { col.getName(),
							col.getDisplayName(), col.getDictTypeId() });
				}
			}
			return list.toArray(new DataObject[list.size()]);
		}
	}

	@Bizlet("加载日志明细(发生变化的部分)，并转化为可传递到前台的数组")
	public static DataObject[] getLogDetailBizByLogId(Map<String, Object> param)
			throws EOSException {
		if (null == param.get("log_id") || null == param.get("task_id"))
			return new DataObject[0];

		ensureConfigLoaded();

		Map<String, Object> map = new HashMap<String, Object>();
		if (null != param.get("log_id")
				&& "null".equals(param.get("log_id")) == false)
			map.put("log_id", param.get("log_id").toString());
		if (null != param.get("task_id")
				&& "null".equals(param.get("task_id")) == false) {
			map.put("biz_task_id", param.get("task_id").toString());
		}
		if (map.size() < 1)
			return new DataObject[0];
		Object[] logs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.log.selectLog", map);
		if (null == logs || logs.length < 1)
			return null;
		DataObject log = (DataObject) logs[0];
		map.put("log_id", log.getString("LOG_ID"));

		Object[] arr = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.log.selectLogDetailBiz", map);
		if (null == arr || arr.length < 1)
			return null;

		// String logType = log.getString("LOG_TYPE");
		String logFunccode = log.getString("LOG_FUNCCODE");
		EntityLogTrade.Table table = null;
		List<String> primaryKeys = new ArrayList<String>();
		String[] primaryKeyCols = null;
		// 03-单个参数表的单词增删改操作
		// 04-单个交易的各表的操作
		// 05-业务快照
		// if ("03".equals(logType)) {
		// return new DataObject[0];
		// } else if ("04".equals(logType)) {
		// return new DataObject[0];
		// } else {
		EntityLogTrade tr = getEntityLogTrade(logFunccode);
		if (null == tr)
			return new DataObject[0];
		String cnt = null;
		DataObject obj = null;
		String opType = null;
		String detailId = null;
		String TB_NAME = null;
		List<DataObject> list = new ArrayList<DataObject>();
		Set<String> tables = new HashSet<String>();
		for (int i = 0, size = arr.length; i < size; i++) {
			DataObject col = (DataObject) arr[i];
			TB_NAME = col.getString("TB_NAME");
			String OP_SEQ = col.getString("OP_SEQ");

			if (null == cnt)
				cnt = OP_SEQ;
			if (null == opType)
				opType = col.getString("OP_TYPE");
			if (cnt.equals(OP_SEQ) && opType.equals(col.getString("OP_TYPE"))) {
				// 同一个表
				if (null == obj) {
					primaryKeys.clear();
					obj = new DataObjectImpl(new DataObjectType());
					opType = col.getString("OP_TYPE");
					detailId = col.getString("DETAIL_ID");
					tables.add(TB_NAME);
					primaryKeyCols = GitUtil.getPrimaryKeysWOUnderline(TB_NAME);
				}
			} else {
				// 不同的表
				if (null != obj) {
					// primaryKeys.clear();
					list.add(obj);
					obj.setString("logOpType", opType);
					obj.setString("logDetailId", detailId);
					obj.setString("logTableName", TB_NAME);
					if (primaryKeys.size() > 0) {
						obj.setString("logPrimaryKey",
								ArrayUtils
										.toString(primaryKeys
												.toArray(new String[primaryKeys
														.size()])));
					} else {
						obj.setString("logPrimaryKey", "");
					}
					// tbName = col.getString("TB_NAME");
					// primaryKeyCols = GitUtil
					// .getPrimaryKeysWOUnderline(tbName.substring(4));
				}
				cnt = OP_SEQ;
				obj = new DataObjectImpl(new DataObjectType());
				opType = col.getString("OP_TYPE");
				detailId = col.getString("DETAIL_ID");
				tables.add(TB_NAME);
				primaryKeys.clear();
				primaryKeyCols = GitUtil.getPrimaryKeysWOUnderline(TB_NAME);
			}
			obj.set(col.getString("COL_NAME"), col.getString("VAL_NEW"));
			if (null != col.getString("VAL_ORI"))
				obj.set("original_value_" + col.getString("COL_NAME"), col
						.getString("VAL_ORI"));
			if (ArrayUtils.indexOf(primaryKeyCols, col.getString("COL_NAME")
					.toUpperCase()) >= 0) {
				primaryKeys.add(col.getString("VAL_NEW"));
			}
		}
		if (null != obj) {
			list.add(obj);
			obj.setString("logOpType", opType);
			obj.setString("logDetailId", detailId);
			obj.setString("logTableName", TB_NAME);
			if (primaryKeys.size() > 0) {
				obj.setString("logPrimaryKey", ArrayUtils.toString(primaryKeys
						.toArray(new String[primaryKeys.size()])));
			} else {
				obj.setString("logPrimaryKey", "");
			}
			tables.add(TB_NAME);
		}
		for (String nm : tables) {
			// DataObject作为meta信息
			DataObject meta = new DataObjectImpl(new DataObjectType());
			list.add(meta);
			table = indexOf(tr.getTables(), nm.replaceAll("_", ""));
			if (null == table)
				throw new EOSException("没有找到相关配置信息");

			meta.setString("isLogMeta", "true");
			if (table.getName().contains(".")) {
				meta.setString("name", table.getName().substring(
						table.getName().lastIndexOf('.') + 1));
			} else {
				meta.setString("name", table.getName());
			}
			meta.setString("displayName", table.getDisplayName());
			List<String[]> cols = new ArrayList<String[]>();
			meta.setList("cols", cols);
			for (int i = 0, size = table.getCols().size(); i < size; i++) {
				EntityLogTrade.Column col = table.getCols().get(i);
				cols.add(new String[] { col.getName(), col.getDisplayName(),
						col.getDictTypeId() });
			}
		}
		return list.toArray(new DataObject[list.size()]);
		// }
	}

	private static void ensureConfigLoaded() {
		try {
			if ((null == config || config.isEmpty())
					&& (null == paramTradeConfig || paramTradeConfig
							.getTables().isEmpty()))
				reloadConfig();
		} catch (EOSException e) {
			LogUtil.logError("重新加载日志拦截器配置时出错", e, new Object[0]);
		}
	}

	public static boolean isNeedIntercept(String name, String tableName)
			throws EOSException {
		if (null == name)
			return false;
		ensureConfigLoaded();
		if (StringUtils.isNotEmpty(tableName)) {
			if (indexOf(paramTradeConfig.getTables(), tableName) != null)
				return true;
		}

		return config.containsKey(name);
	}

	/**
	 * @return 交易名称
	 */
	private static String getTradeName() {
		String uri = (String) MDC.get("req.requestURI");
		if (null != uri) {
			if (uri.contains("/")) {
				int idx = uri.lastIndexOf('/') + 1;
				if (idx < uri.length()) {
					uri = uri.substring(idx);
				}
			}
			if (uri.length() > 0)
				return uri;
		}

		return null;
	}

	protected static final String TABLE_PRE = "tablePre";

	private static String genTablePrefix() {
		String pre = (String) MDC.get(TABLE_PRE);
		if (StringUtils.isEmpty(pre)) {
			pre = "1";
		} else {
			pre = Integer.valueOf(Integer.valueOf(pre) + 1).toString();
		}
		MDC.put(TABLE_PRE, pre);
		pre = StringUtils.leftPad(pre, 3, '0');

		return pre;
	}

	/**
	 * 记录交易日志
	 * 
	 * @param opType
	 *            操作类型：insert-增加；update-修改；delete-删除
	 */
	static void log(String opType, DataObject entity, Serializable primaryKey,
			String[] propertyNames, Object[] values) {
		if (!"insert".equals(opType) && !"update".equals(opType)
				&& !"delete".equals(opType))
			return;
		ensureConfigLoaded();

		String tablename = entity.getType().getName();
		// tablename = tablename.toLowerCase();
		if (isNeedIntercept(tablename) == false)
			return;

		EntityLogTrade.Table table = indexOf(paramTradeConfig.getTables(),
				tablename);
		if (null != table) {
			// 处理参数日志：增删改都记录
			String op = "insert".equals(opType) ? "新增：" : "update"
					.equals(opType) ? "修改：" : "删除：";
			logTrade("03", table.getName(), op + table.getDisplayName());

			// String tablename =
			// DatabaseExt.getTableName(GitUtil.DEFAULT_DS_NAME,
			// entity.getType().getURI() + "." + entity.getType().getName());
			// if (null == tablename)
			// return;
			String id = (String) MDC.get("req.id");
			List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
			tablename = toSqlColumnName(tablename);
			tablename = genTablePrefix() + "_" + tablename;

			String[] ids = GitUtil.getIdPropertyNames(entity);
			if (null != ids) {
				for (int i = 0, size = ids.length; i < size; i++) {
					// 将主键值插入
					String val = getValue(entity.get(ids[i]));
					// logTradeDetail(id, tablename, ids[i], val, opType);
					Map<String, Object> detailMap = new HashMap<String, Object>();
					detailMap.put("LOG_ID", id);
					detailMap.put("DETAIL_ID", GitUtil.genUUIDString());// 主键
					detailMap.put("TB_NAME", tablename);
					detailMap.put("COL_NAME", ids[i]);
					detailMap.put("OP_TYPE", opType);
					detailMap.put("VAL_NEW", val);
					detailList.add(detailMap);
				}
			}
			for (int i = 0, size = table.getCols().size(); i < size; i++) {
				EntityLogTrade.Column col = table.getCols().get(i);
				int idx = ArrayUtils.indexOf(propertyNames, col.getName());
				if (idx < 0)
					continue;
				String val = getValue(values[idx]);
				// logTradeDetail(id, tablename, col.getName(), val, opType);
				Map<String, Object> detailMap = new HashMap<String, Object>();
				detailMap.put("LOG_ID", id);
				detailMap.put("DETAIL_ID", GitUtil.genUUIDString());// 主键
				detailMap.put("TB_NAME", tablename);
				detailMap.put("COL_NAME", col.getName());
				detailMap.put("OP_TYPE", opType);
				detailMap.put("VAL_NEW", val);
				detailList.add(detailMap);
			}
			DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.log.insertLogDetail", detailList
							.toArray(new HashMap[detailList.size()]));
		} else {
			// 按交易记录：只记录指定交易的指定表
			EntityLogTrade tr = getEntityLogTrade(getTradeName());
			if (null == tr)
				return;

			logTrade("04", tr.getName(), tr.getDisplayName());
			String id = (String) MDC.get("req.id");
			table = indexOf(tr.getTables(), tablename);
			if (null == table)
				return;
			tablename = toSqlColumnName(tablename);
			tablename = genTablePrefix() + "_" + tablename;
			List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
			String[] ids = GitUtil.getIdPropertyNames(entity);
			if (null != ids) {
				for (int i = 0, size = ids.length; i < size; i++) {
					// 将主键值插入
					String val = getValue(entity.get(ids[i]));
					// logTradeDetail(id, tablename, ids[i], val, opType);
					Map<String, Object> detailMap = new HashMap<String, Object>();
					detailMap.put("LOG_ID", id);
					detailMap.put("DETAIL_ID", GitUtil.genUUIDString());// 主键
					detailMap.put("TB_NAME", tablename);
					detailMap.put("COL_NAME", ids[i]);
					detailMap.put("OP_TYPE", opType);
					detailMap.put("VAL_NEW", val);
					detailList.add(detailMap);
				}
			}
			for (int i = 0, size = table.getCols().size(); i < size; i++) {
				EntityLogTrade.Column col = table.getCols().get(i);
				int idx = ArrayUtils.indexOf(propertyNames, col.getName());
				if (idx < 0)
					continue;
				String val = getValue(values[idx]);
				// logTradeDetail(id, tablename, col.getName(), val, opType);
				Map<String, Object> detailMap = new HashMap<String, Object>();
				detailMap.put("LOG_ID", id);
				detailMap.put("DETAIL_ID", GitUtil.genUUIDString());// 主键
				detailMap.put("TB_NAME", tablename);
				detailMap.put("COL_NAME", col.getName());
				detailMap.put("OP_TYPE", opType);
				detailMap.put("VAL_NEW", val);
				detailList.add(detailMap);
			}
			DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.log.insertLogDetail", detailList
							.toArray(new HashMap[detailList.size()]));
		}
	}

	/**
	 * @param snapshotName
	 *            快照配置名称，对应XML配置中的name属性（必填）
	 * @param bizNum
	 *            业务编号（必填）
	 * @param bizType
	 *            业务类型（必填）
	 * @param taskId
	 *            流程节点ID（必填）
	 * @param taskName
	 *            流程节点名称（必填）
	 * @param csvTableName
	 *            逗号分隔的表名称，大写加下划线
	 */
	@Bizlet("记录快照日志")
	public static void snapshot(String snapshotName, String bizNum,
			String bizType, String taskId, String taskName, String csvTableName)
			throws EOSException {
		if (StringUtils.isEmpty(snapshotName) || StringUtils.isEmpty(bizNum)
				|| StringUtils.isEmpty(bizType) || StringUtils.isEmpty(taskId)
				|| StringUtils.isEmpty(taskName)) {
			throw new EOSException("记录快照必须提供：快照名称、业务编号、业务类型、流程节点ID、流程节点名称，缺一不可");
		}
		if (null == csvTableName || csvTableName.equals("null"))
			csvTableName = "";
		csvTableName = "," + csvTableName + ",";

		// 记录快照交易日志
		String id = (String) MDC.get("req.id");
		if (StringUtils.isEmpty(id)) {
			id = UUID.randomUUID().toString().replaceAll("-", "");
			MDC.put("req.id", id);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LOG_ID", id);
		map.put("LOG_IP", MDC.get("req.remoteAddr"));
		map.put("LOG_FUNCCODE", snapshotName);
		map.put("LOG_DESC", taskName);
		map.put("LOG_TYPE", "05"); // 05-业务快照
		map.put("BIZ_NUM", bizNum);
		map.put("BIZ_TYPE", bizType);
		map.put("BIZ_TASK_ID", taskId);
		map.put("USER_NUM", GitUtil.getCurrentUserId());
		map.put("ORG_NUM", GitUtil.getCurrentOrgId());
		map.put("CREATE_TIME", GitUtil.getBusiTimestamp());

		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.log.insertSnapshot", map);

		// 记录快照
		EntityLogTrade tr = getEntityLogTrade(snapshotName);
		if (null == tr)
			return;

		for (int i = 0, size = tr.getTables().size(); i < size; i++) {
			EntityLogTrade.Table table = tr.getTables().get(i);
			String tableName = table.getName();
			if (tableName.contains(".")) {
				tableName = tableName.substring(tableName.lastIndexOf('.') + 1);
			}
			tableName = toSqlColumnName(tableName);
			if (csvTableName.length() > 3
					&& csvTableName.contains(tableName + ",") == false)
				continue;
			// tableName = genTablePrefix() + "_" + tableName;

			String sql = table.getSql();
			if (StringUtils.isEmpty(sql))
				continue;
			sql = sql.replaceAll("#bizNum#", "'" + bizNum + "'");
			sql = sql.replaceAll("#taskId#", "'" + taskId + "'");
			if (sql.contains("#")) {
				throw new EOSException("记录快照提供的sql中，只能有bizNum、taskId作为参数");
			}
			Map<String, String> parammap = new HashMap<String, String>();
			parammap.put("sql", sql);
			Object[] arr = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.log.common_select", parammap);
			if (null == arr || arr.length < 1)
				continue;

			List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
			for (int j = 0, sizej = arr.length; j < sizej; j++) {
				DataObject obj = (DataObject) arr[j];
				String tableName2 = genTablePrefix() + "_" + tableName;
				for (int k = 0, sizek = table.getCols().size(); k < sizek; k++) {
					EntityLogTrade.Column col = table.getCols().get(k);
					Map<String, Object> detailMap = new HashMap<String, Object>();
					detailMap.put("LOG_ID", id);
					detailMap.put("DETAIL_ID", GitUtil.genUUIDString());// 主键
					detailMap.put("TB_NAME", tableName2);
					detailMap.put("COL_NAME", col.getName());
					detailMap.put("OP_TYPE", "snapshot");
					detailMap.put("VAL_NEW", getValue(obj
							.get(toSqlColumnName(col.getName()))));
					detailList.add(detailMap);

				}
			}
			DatabaseExt.executeNamedSqlBatch(GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.log.insertLogDetail", detailList
							.toArray(new HashMap[detailList.size()]));
		}

		// 插入差异部分
		map.clear();
		map.put("log_id", id);
		map.put("logId", id);
		map.put("biz_task_id", taskId);
		Object[] logs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.log.selectLogPreByLogId", map);
		if (null == logs || logs.length != 1)
			return;
		DataObject log = (DataObject) logs[0];
		map.put("log_id_pre", log.getString("LOG_ID"));
		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.log.insertLogDetailBiz", map);
	}

	private static String toSqlColumnName(String name) {
		if (null == name)
			return null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0, size = name.length(); i < size; i++) {
			char ch = name.charAt(i);
			if (Character.isUpperCase(ch) && i > 0)
				sb.append("_");
			sb.append(ch);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 新增交易日志，这里之所以为public，是给签到、签退交易使用
	 * 
	 * @param logType
	 *            日志类型：01-签到；02-签退；03-配置类交易；04-指定交易特定表；05-业务快照；
	 * @param logFunccode
	 *            系统功能编号：对应XML配置中的trade的name，logType为03时才有；
	 * @param logDesc
	 *            描述：日志的备注信息
	 */
	@Bizlet("新增交易日志")
	public static void logTrade(String logType, String logFunccode,
			String logDesc) {
		if (Boolean.TRUE == (Boolean) MDC.get("req.tradeLogged"))
			return;
		Map<String, Object> map = new HashMap<String, Object>();
		String id = (String) MDC.get("req.id");
		if (StringUtils.isEmpty(id)) {
			id = UUID.randomUUID().toString().replaceAll("-", "");
		}
		String ip = (String) MDC.get("req.remoteAddr");
		if (StringUtils.isEmpty(ip)) {
			// IMUODataContext muo = DataContextManager.current()
			// .getMUODataContext();
			// if (muo != null) {
			// IUserObject userobject = muo.getUserObject();
			// if (userobject != null) {
			// ip = userobject.getUserRemoteIP();
			// }
			// }
			if (null != DataContextManager.current().getMUODataContext()) {
				ip = DataContextUtil.getString("m:userObject/userRemoteIP");
			} else {
				ip = "0";
			}
		}
		map.put("LOG_ID", id);
		map.put("LOG_IP", ip);
		map.put("LOG_FUNCCODE", logFunccode);
		map.put("LOG_DESC", logDesc);
		map.put("LOG_TYPE", logType);
		if (null != DataContextManager.current().getMUODataContext()) {
			map.put("USER_NUM", GitUtil.getCurrentUserId());
			
			String org = GitUtil.getCurrentOrgId();
			org = org == null ? "" : org;
			if (org.contains(",")) {
				map.put("ORG_NUM", org.substring(0, org.indexOf(",")));
			} else {
				map.put("ORG_NUM", org);
			}
		} else {
			map.put("USER_NUM", MDC.get(LogonUtil.USER_ID));
			map.put("ORG_NUM", MDC.get(LogonUtil.USER_ORG_ID));
		}
		map.put("CREATE_TIME", GitUtil.currDateTime());

		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.pub.log.insertLog", map);
		MDC.put("req.tradeLogged", Boolean.TRUE);// 用以标记当前交易是否已记录日志，只有此方法中使用
	}

	/**
	 * @param LOG_ID
	 *            日志编号，外键（和log4j中的ID一致）
	 * @param TB_NAME
	 *            物理表名
	 * @param COL_NAME
	 *            字段名
	 * @param VAL_NEW
	 *            新值
	 * @param OP_TYPE
	 *            操作类型：insert-增加；update-修改；delete-删除
	 */
	// private static void logTradeDetail(String LOG_ID, String TB_NAME,
	// String COL_NAME, String VAL_NEW, String OP_TYPE) {
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("LOG_ID", LOG_ID);
	// map.put("TB_NAME", TB_NAME);
	// map.put("COL_NAME", COL_NAME);
	// map.put("OP_TYPE", OP_TYPE);
	// map.put("VAL_NEW", VAL_NEW);
	//
	// DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME,
	// "com.bos.pub.log.insertLogDetail", map);
	// }
	private static String getValue(Object obj) {
		return String.valueOf(obj);
	}

	private static EntityLogTrade.Table indexOf(
			List<EntityLogTrade.Table> tables, String tablename) {
		if (null == tables || null == tablename)
			return null;

		EntityLogTrade.Table tab = null;
		for (int i = 0, size = tables.size(); i < size; i++) {
			tab = tables.get(i);
			if (tab.getName().toLowerCase().endsWith(tablename.toLowerCase())) {
				return tab;
			}
		}
		return null;
	}

	/**
	 * @param name
	 *            交易名称
	 * @return 交易配置信息
	 */
	public static EntityLogTrade getEntityLogTrade(String name) {
		ensureConfigLoaded();

		return config.get(name);
	}

	@Bizlet("重新加载配置文件EntityLogConfig.xml")
	public synchronized static void reloadConfig() throws EOSException {
		try {
			InputStream is = EntityLogUtil.class
					.getResourceAsStream("EntityLogConfig.xml");
			if (null == is || is.available() < 1) {
				return;
			}
			Map<String, EntityLogTrade> cfg = new HashMap<String, EntityLogTrade>();
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			// trades的直接子元素中，table表示需记录参数日志，trade表示需按交易记录日志，snapshot表示记录业务快照
			List trades = doc.getRootElement().elements("trade");
			List trades2 = doc.getRootElement().elements("table");
			List snapshots = doc.getRootElement().elements("snapshot");
			// if ((null == trades || trades.size() < 1)
			// && (null == trades2 || trades2.size() < 1)) {
			// LogUtil.logWarn("加载实体CRUD日志配置EntityLogConfig.xml时，发现没有需要处理的交易",
			// null, new Object[0]);
			// return;
			// }
			if (null == trades)
				trades = new ArrayList();
			if (null == trades2)
				trades2 = new ArrayList();
			if (null == snapshots)
				snapshots = new ArrayList();

			EntityLogTrade paramTrade = new EntityLogTrade();
			for (int i = 0, size = trades2.size(); i < size; i++) {
				// 处理需记录参数日志的内容
				Element table = (Element) trades2.get(i);
				EntityLogTrade.Table tb = paramTrade.createTable();
				paramTrade.getTables().add(tb);
				tb.setName(table.attributeValue("name"));
				tb.setDisplayName(table.attributeValue("displayName"));

				List cols = table.elements("col");
				if (null == cols || cols.size() < 1)
					continue;
				for (int k = 0, colsize = cols.size(); k < colsize; k++) {
					// 处理交易所需拦截的表的列
					Element col = (Element) cols.get(k);
					EntityLogTrade.Column tc = tb.createColumn();
					tb.getCols().add(tc);
					tc.setName(col.attributeValue("name"));
					tc.setDisplayName(col.attributeValue("displayName"));
					tc.setDictTypeId(col.attributeValue("dictTypeId"));
				}

			}
			paramTradeConfig = paramTrade;

			for (int i = 0, size = trades.size(); i < size; i++) {
				// 处理各交易所需拦截记录的内容
				Element trade = (Element) trades.get(i);
				String name = trade.attributeValue("name");
				String displayName = trade.attributeValue("displayName");
				EntityLogTrade tr = new EntityLogTrade();
				tr.setName(name);
				tr.setDisplayName(displayName);
				cfg.put(name, tr);

				List tables = trade.elements("table");
				if (null == tables || tables.size() < 1)
					continue;
				for (int j = 0, tablesize = tables.size(); j < tablesize; j++) {
					// 处理交易所需拦截的表
					Element table = (Element) tables.get(j);
					EntityLogTrade.Table tb = tr.createTable();
					tr.getTables().add(tb);
					tb.setName(table.attributeValue("name"));
					tb.setDisplayName(table.attributeValue("displayName"));

					List cols = table.elements("col");
					if (null == cols || cols.size() < 1)
						continue;
					for (int k = 0, colsize = cols.size(); k < colsize; k++) {
						// 处理交易所需拦截的表的列
						Element col = (Element) cols.get(k);
						EntityLogTrade.Column tc = tb.createColumn();
						tb.getCols().add(tc);
						tc.setName(col.attributeValue("name"));
						tc.setDisplayName(col.attributeValue("displayName"));
						tc.setDictTypeId(col.attributeValue("dictTypeId"));
					}
				}
			}

			for (int i = 0, size = snapshots.size(); i < size; i++) {
				// 处理快照所需记录的内容
				Element snapshot = (Element) snapshots.get(i);
				String name = snapshot.attributeValue("name");
				String displayName = snapshot.attributeValue("displayName");
				EntityLogTrade tr = new EntityLogTrade();
				tr.setName(name);
				tr.setDisplayName(displayName);
				cfg.put(name, tr);

				List tables = snapshot.elements("table");
				if (null == tables || tables.size() < 1)
					continue;
				for (int j = 0, tablesize = tables.size(); j < tablesize; j++) {
					// 处理所需拦截的表
					Element table = (Element) tables.get(j);
					EntityLogTrade.Table tb = tr.createTable();
					tr.getTables().add(tb);
					tb.setName(table.attributeValue("name"));
					tb.setDisplayName(table.attributeValue("displayName"));
					if (tb.getName().contains(".") == false) {
						throw new EOSException("快照配置中表的名称应包括URI部分和name部分！");
					}
					String sql = table.attributeValue("sql");
					if (StringUtils.isEmpty(sql)) {
						sql = table.elementText("sql");
					}
					if (StringUtils.isEmpty(sql)) {
						throw new EOSException("快照配置中表应有sql属性或子元素！");
					}
					tb.setSql(sql);

					List cols = table.elements("col");
					if (null == cols || cols.size() < 1)
						continue;
					for (int k = 0, colsize = cols.size(); k < colsize; k++) {
						// 处理所需拦截的表的列
						Element col = (Element) cols.get(k);
						EntityLogTrade.Column tc = tb.createColumn();
						tb.getCols().add(tc);
						tc.setName(col.attributeValue("name"));
						tc.setDisplayName(col.attributeValue("displayName"));
						tc.setDictTypeId(col.attributeValue("dictTypeId"));
					}
				}
			}

			if (cfg.isEmpty() == false)
				config = cfg;
		} catch (IOException e) {
			LogUtil.logError("加载实体CRUD日志配置EntityLogConfig.xml时出错", e,
					new Object[0]);
		} catch (DocumentException e) {
			LogUtil.logError("加载实体CRUD日志配置EntityLogConfig.xml时出错（格式错误）", e,
					new Object[0]);
		}
	}
}
