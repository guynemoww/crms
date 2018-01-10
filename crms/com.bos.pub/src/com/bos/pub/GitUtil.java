/**
 * 
 */
package com.bos.pub;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.CommonUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.CsmTableName;
import com.bos.pub.exception.ParamEmptyException;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.bos.utp.tools.DBUtil;
import com.eos.common.cache.ICache;
import com.eos.common.connection.ConnectionHelper;
import com.eos.das.entity.DASManager;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.IDASSession;
import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IMapContextFactory;
import com.eos.data.datacontext.IUserObject;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataContextUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.server.dict.DictEntry;
import com.eos.server.dict.DictManager;
import com.eos.server.dict.impl.DictEntryImpl;
import com.eos.spring.DASTemplate;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;
import com.eos.system.exception.EOSRuntimeException;
import com.eos.system.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primeton.common.connection.impl.CacheUtil;
import com.primeton.das.entity.impl.hibernate.Hibernate;
import com.primeton.das.entity.impl.hibernate.id.UUIDHexGenerator;
import com.primeton.data.sdo.impl.DataObjectImpl;
import com.primeton.data.sdo.impl.types.DataObjectType;
import com.primeton.ext.access.http.HttpMapContextFactory;
import com.primeton.ext.data.datacontext.http.HttpRequestMap;
import com.primeton.ext.data.sdo.collection.ContainerAwareList;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.spring.support.DasCriteriaUtil;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.helper.DataFactory;

/**
 * @author 王世春
 * @date 2013-10-31 14:17:34
 * 
 */
@Bizlet("常用公共方法")
public class GitUtil {
	public static String DATE_FORMAT = "yyyy-MM-dd";
	public static String TIME_FORMAT = "HH:mm:ss ";

	private static final int TIME_CACHE = 20;

	public static Calendar previousDate = null; // 日期缓存

	private static long previousDateGet = -1;// 上次设置日期缓存的时间

	private static Object timeLock = new Object();
	@Deprecated
	// 请使用DBUtil。DB_NAME_DEF
	public static final String DEFAULT_DS_NAME = DBUtil.DB_NAME_DEF; // 默认数据源

	@Bizlet("初始化营业日期缓存")
	public static void initBusiDate() {
		if (previousDate == null) {
			synchronized (timeLock) {
				if (previousDate == null) {
					// DataObject[] arr =(DataObject[])DatabaseExt.queryByNamedSql(DEFAULT_DS_NAME, "com.bos.pub.common.selectSystemDate", null);
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					try {
						conn = DBUtil.getConnection();
						ps = conn.prepareStatement("select operating_date from tb_pub_date");
						rs = ps.executeQuery();
						while (rs.next()) {
							java.util.Date date = rs.getDate(1);
							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							previousDate = cal;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DBUtil.closeAll(conn, ps, rs);
					}

					if (previousDate == null) {
						previousDate = Calendar.getInstance();
					}
				}
				String cacheName = "CacheForDict";
				ICache cache = CacheUtil.createCache(cacheName);
				cache.put("bizDate", previousDate);
			}// synchronized
		}

	}

	@Bizlet("获取法人标识")
	public static String getLegorg() {
		String legorg = "";
		// 获取session里的机构级别串
		IUserObject user = CommonUtil.getIUserObject();
		Map<String, Object> attmap = user.getAttributes();
		legorg = (String) attmap.get("legorg");
		return legorg;
	}

	@Bizlet("获取法人标识")
	public static String getLegorgByParty(String partyId) {
		DataObject party = EntityUtil.getEntityById(CsmTableName.TB_CSM_PARTY, "partyId", partyId);
		return party.getString("legOrg");
	}

	@Bizlet("更新营业日期")
	public static void updateBusiDate() {
		synchronized (timeLock) {
			DataObject o = DataFactory.INSTANCE.create("com.bos.pub.meta", "TbPubDate");
			DataObject[] arr = DatabaseUtil.queryEntitiesByTemplate(DEFAULT_DS_NAME, o);
			if (null != arr && arr.length > 0) {
				// 从数据库获取营业日期
				java.util.Date date = arr[0].getDate("operatingDate");
				// java.util.Date date = arr[0].getDate("busiDate");
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				previousDate = cal;
			} else {
				previousDate = Calendar.getInstance();
			}
			String cacheName = "CacheForDict";
			ICache<String, Calendar> cache = CacheUtil.createCache(cacheName);
			cache.put("bizDate", previousDate);
		}
	}

	@Bizlet("获取业务时间")
	public static Timestamp getBusiTimestamp() {
		String cacheName = "CacheForDict";
		ICache<String, Calendar> cache = CacheUtil.createCache(cacheName);
		if (previousDate == null) {
			initBusiDate();
		}
		Calendar cal = cache.get("bizDate");
		Calendar now = Calendar.getInstance();
		now.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		now.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		now.set(Calendar.DATE, cal.get(Calendar.DATE));

		return new Timestamp(now.getTimeInMillis());
	}

	@Bizlet("获取业务时间")
	public static String getBusiDate1DayBeforStr() {
		java.util.Date dateBusi = new Date(getBusiTimestamp().getTime());
		java.util.Date dateBusi1DayBefore = new Date(getBusiTimestamp().getTime());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateBusi);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		dateBusi1DayBefore = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dateBusi1DayBefore);
	}

	@Bizlet("获取当前时间戳")
	public static Timestamp currDateTime() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	@Bizlet("获取业务日期字符串yyyy-MM-dd")
	public static String getBusiDateStr() {
		return new SimpleDateFormat("yyyy-MM-dd").format(getBusiTimestamp());
	}

	/**
	 * @param datestr
	 *            yyyy-M-dd格式的字符串
	 * @return
	 * @author 王世春
	 */
	@Bizlet("字符串转换为日期")
	public static java.util.Date toDate(String datestr) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(datestr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param datestr
	 *            根据日期格式的字符串，转换成日期
	 * @return
	 * @author 王世春
	 */
	@Bizlet("字符串转换为日期")
	public static java.util.Date toDate(String datestr, String style) {
		try {
			return new SimpleDateFormat(style).parse(datestr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return
	 */
	@Bizlet("获取业务时间")
	public static java.util.Date getBusiDate() {
		return new Date(getBusiTimestamp().getTime());
	}

	@Bizlet("获取数组个数")
	public static int getDataObjectSize(Object[] array) {
		return array.length;
	}

	@Bizlet("获取系统时间YYYYMMDD格式业务日期")
	public static String getSysDateYYYYMMDD() {
		Object[] summaryInfo = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.querySysDate", "");
		Map map = (Map) summaryInfo[0];
		String perdate = new SimpleDateFormat("yyyy-MM-dd").format(map.get("OPERATING_DATE"));
		return perdate;
	}

	@Bizlet("获取YYYYMMDD格式业务日期")
	public static String getBusiDateYYYYMMDD() {
		return getBusiDateStr().replaceAll("-", "");
	}

	@Bizlet("获取HHmmSS格式业务时间")
	public static String getBusiTimeStr() {
		return String.valueOf(getBusiTimestamp().getHours()) + String.valueOf(getBusiTimestamp().getMinutes()) + String.valueOf(getBusiTimestamp().getSeconds());
	}

	@Bizlet("获取日志处理类")
	public static Logger getLogger(String className) {
		return TraceLoggerFactory.getLogger(className);
	}

	@Bizlet("获取日志处理类")
	public static Logger getLogger(Class cls) {
		return TraceLoggerFactory.getLogger(cls);
	}

	private static Map<String, String[]> primaryKeysMap = new ConcurrentHashMap<String, String[]>();

	private static ReentrantLock lock = new ReentrantLock(true);

	/**
	 * @param tableName
	 *            全部大写，有下划线的
	 * @return
	 */
	public static String[] getPrimaryKeys(String tableName) {
		if (StringUtils.isEmpty(tableName))
			return new String[0];

		if (primaryKeysMap.containsKey(tableName) == false) {
			lock.lock();
			try {
				if (primaryKeysMap.containsKey(tableName) == false) {// 双重检查锁定
					String names = "";
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("TABNAME", tableName);
					Object[] arr = DatabaseExt.queryByNamedSql(DEFAULT_DS_NAME, "com.bos.pub.common.selectPrimaryKeyCols", map);
					if (null != arr && arr.length == 1)
						names = ((DataObject) arr[0]).getString("COLS");
					if (null == names)
						names = "";
					String[] keys = names.split(",");
					primaryKeysMap.put(tableName, keys);
				}
			} finally {
				lock.unlock();
			}
		}

		return primaryKeysMap.get(tableName);
	}

	/**
	 * @param tableName
	 *            全部大写，有下划线的
	 * @return 主键字段中的下划线替换后返回
	 */
	public static String[] getPrimaryKeysWOUnderline(String tableName) {
		String[] arr = getPrimaryKeys(tableName);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].replaceAll("_", "");
		}
		return arr;
	}

	/**
	 * @param orgids
	 *            机构ID
	 * @return
	 */
	@Bizlet("根据逗号分隔的机构ID查询机构信息")
	public static DataObject[] getOrgInfo(String orgids) {
		if (null == orgids || orgids.replaceAll(",", "").length() == 0)
			return new DataObject[0];

		HashMap<String, Object> org = new HashMap<String, Object>();
		if (null != orgids && orgids.replaceAll("[0-9]", "").replaceAll(",", "").length() == 0) {
			orgids = "'" + orgids.replaceAll("'", "").replaceAll(",", "','") + "'";
			org.put("orgid", orgids);
			org.put("orgcode", orgids);
		} else {
			orgids = "'" + orgids.replaceAll("'", "").replaceAll(",", "','") + "'";
			org.put("orgid", "0");
			org.put("orgcode", orgids);
		}

		Object[] res = DatabaseExt.queryByNamedSql(DEFAULT_DS_NAME, "com.bos.pub.organizationtree.queryOrg", org);
		DataObject[] ts = new DataObject[res.length];
		try {
			for (int i = 0; i < res.length; i++) {
				ts[i] = new DataObjectImpl(new DataObjectType());
				copyTo((DataObject) res[i], ts[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new DataObject[0];
		}

		return ts;
	}

	/**
	 * @param orgids
	 *            出账机构ID
	 * @return
	 */
	@Bizlet("根据逗号分隔的机构ID查询出账机构信息")
	public static DataObject[] getBatchOrgInfo(String orgids) {
		if (null == orgids || orgids.replaceAll(",", "").length() == 0)
			return new DataObject[0];

		HashMap<String, Object> org = new HashMap<String, Object>();
		if (null != orgids && orgids.replaceAll("[0-9]", "").replaceAll(",", "").length() == 0) {
			orgids = "'" + orgids.replaceAll("'", "").replaceAll(",", "','") + "'";
			org.put("orgcode", orgids);
		} else {
			orgids = "'" + orgids.replaceAll("'", "").replaceAll(",", "','") + "'";
			org.put("orgcode", orgids);
		}

		Object[] res = DatabaseExt.queryByNamedSql(DEFAULT_DS_NAME, "com.bos.pub.organizationtree.queryBatchOrg", org);
		DataObject[] ts = new DataObject[res.length];
		try {
			for (int i = 0; i < res.length; i++) {
				ts[i] = new DataObjectImpl(new DataObjectType());
				copyTo((DataObject) res[i], ts[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new DataObject[0];
		}

		return ts;
	}

	@Bizlet("查询会计机构")
	public static DataObject[] getAcctOrgInfo(String orgIds) {
		String[] ids;
		if (orgIds == null || (ids = orgIds.split(",")).length == 0) {
			return new DataObject[0];
		}
		String orgStr = StringUtil.joinStr(ids, "','", true);
		if (orgStr.isEmpty()) {
			return new DataObject[0];
		}
		orgStr = "'" + orgStr + "'";
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgIds", orgStr);
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.pub.organizationtree.queryAcctOrg", map);
		if (datas == null || datas.length == 0) {
			return new DataObject[0];
		}
		DataObject[] objs = new DataObject[datas.length];
		for (int i = 0; i < datas.length; i++) {
			objs[i] = (DataObject) datas[i];
		}
		return objs;
	}

	/**
	 * @param userids
	 * @return
	 */
	@Bizlet("根据逗号分隔的用户ID查询用户信息")
	public static DataObject[] getUserInfo(String userids) {
		if (null == userids || userids.replaceAll(",", "").length() == 0)
			return new DataObject[0];

		HashMap<String, Object> org = new HashMap<String, Object>();
		if (null != userids && userids.replaceAll("[0-9]", "").replaceAll(",", "").length() == 0) {
			userids = "'" + userids.replaceAll("'", "").replaceAll(",", "','") + "'";
			org.put("empid", userids);
			org.put("userid", userids);
		} else {
			userids = "'" + userids.replaceAll("'", "").replaceAll(",", "','") + "'";
			org.put("empid", "'0'");
			org.put("userid", userids);
		}

		Object[] res = DatabaseExt.queryByNamedSql(DEFAULT_DS_NAME, "com.bos.pub.organizationtree.queryEmployee", org);
		DataObject[] ts = new DataObject[res.length];
		try {
			for (int i = 0; i < res.length; i++) {
				ts[i] = new DataObjectImpl(new DataObjectType());
				copyTo((DataObject) res[i], ts[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new DataObject[0];
		}

		return ts;
	}

	/**
	 * @param products
	 * @return
	 */
	@Bizlet("根据逗号分隔的授信品种ID查询授信品种信息")
	public static DataObject[] getProductInfo(String products) {
		if (null == products || products.replaceAll(",", "").length() == 0)
			return new DataObject[0];

		HashMap<String, Object> product = new HashMap<String, Object>();
		product.put("productId", products);
		product.put("productCd", products);

		Object[] res = DatabaseExt.queryByNamedSql(DEFAULT_DS_NAME, "com.bos.pub.product.queryProduct", product);
		DataObject[] ts = new DataObject[res.length];
		try {
			for (int i = 0; i < res.length; i++) {
				ts[i] = new DataObjectImpl(new DataObjectType());
				copyTo((DataObject) res[i], ts[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new DataObject[0];
		}

		return ts;
	}

	/**
	 * @param products
	 * @return
	 */
	@Bizlet("根据授信品种判断是否不存在")
	public static String getProductFlag(String products) {
		if (null == products || products.replaceAll(",", "").length() == 0)
			return "0";

		HashMap<String, Object> product = new HashMap<String, Object>();
		product.put("productId", products);
		product.put("productCd", products);

		Object[] res = DatabaseExt.queryByNamedSql(DEFAULT_DS_NAME, "com.bos.pub.product.queryProduct", product);

		if (res.length == 0) {
			return "0";
		} else {
			return "111";
		}

	}

	@Bizlet("获取当前机构编号")
	public static String getCurrentOrgCd() {
		String id = DataContextUtil.getString("m:userObject/attributes/orgcode");
		return id;
	}

	@Bizlet("获取当前机构ID")
	public static String getCurrentOrgId() {
		String id = DataContextUtil.getString("m:userObject/userOrgId");
		return id;
	}

	@Bizlet("获取当前用户ID")
	public static String getCurrentUserId() {
		String id = DataContextUtil.getString("m:userObject/userId");
		// String id = DataContextUtil
		// .getString("m:userObject/attributes/operatorid");
		return id;
	}

	@Bizlet("获取当前用户岗位编号")
	public static String getCurrentPositionCode() {
		String id = DataContextUtil.getString("m:userObject/attributes/posicode");
		return id;
	}

	@Bizlet("获取当前用户岗位名称")
	public static String getCurrentPositionName() {
		String id = DataContextUtil.getString("m:userObject/attributes/posiname");
		return id;
	}

	@Bizlet("根据模板查询列表")
	public static DataObject[] queryEntitiesByTemplate(DataObject param) throws ParamEmptyException {
		DataObject[] arr = new DataObject[0];
		if (null == param)
			return arr;

		if (isEmpty(param)) {
			throw new ParamEmptyException("queryEntitiesByTemplate中，查询模板的属性不能全部为空");
		}
		arr = DatabaseUtil.queryEntitiesByTemplate("default", param);

		return arr;
	}

	@Bizlet("根据模板查询列表，并返回第一条记录")
	public static DataObject queryEntityByTemplate(DataObject param) throws ParamEmptyException {
		DataObject[] arr = queryEntitiesByTemplate(param);
		if (null != arr && arr.length > 0)
			return arr[0];

		return null;
	}

	@Bizlet("根据模板查询分页列表")
	public static DataObject[] queryEntitiesByTemplateWithPage(DataObject param, DataObject page) throws ParamEmptyException {
		DataObject[] arr = new DataObject[0];
		if (null == param)
			return arr;

		// 分页查询应该是可以全表查询的，所以不需要判断模板是否为空
		// if (isEmpty(param)) {
		// throw new ParamEmptyException(
		// "queryEntitiesByTemplateWithPage中，查询模板的属性不能全部为空");
		// }
		arr = DatabaseExt.queryEntitiesByTemplateWithPage("default", param, page);

		return arr;
	}

	@Bizlet("判断dataObject是否为空")
	public static boolean isEmpty(DataObject param) {
		List list = param.getInstanceProperties();
		boolean flag = true;
		for (Object obj : list) {
			if (obj instanceof Property == false)
				continue;

			if (param.isSet((Property) obj) && false == "_entity".equals(((Property) obj).getName())) {
				Object value = param.get((Property) obj);
				if (null != value) {
					if (value instanceof DataObject) {
						if (isEmpty((DataObject) value) == false) {
							// 该属性为引用属性，且该值不为空
							flag = false;
							break;
						} else {
							continue;
						}
					} else {
						// 该属性的值不为空
						flag = false;
						break;
					}
				} else {
					// 该属性的值为空
					continue;
				}
			}
		}
		return flag;
	}

	@Bizlet("根据模板template/criteria查询分页列表")
	public static DataObject[] commonQueryWithPage(DataObject param, DataObject page) throws ParamEmptyException {
		DataObject[] arr = new DataObject[0];
		if (null == param)
			return arr;

		if (isEmpty(param)) {
			throw new ParamEmptyException("commonQueryWithPage中，查询模板的属性不能全部为空");
		}
		arr = DatabaseExt.commonQueryWithPage("default", param, page);

		return arr;
	}

	@Bizlet("根据模板criteria查询分页列表")
	public static DataObject[] queryEntitiesByCriteriaEntityWithPage(DataObject param, DataObject page) throws ParamEmptyException {
		DataObject[] arr = new DataObject[0];
		if (null == param)
			return arr;

		if (isEmpty(param)) {
			throw new ParamEmptyException("queryEntitiesByCriteriaEntityWithPage中，查询模板的属性不能全部为空");
		}
		arr = DatabaseExt.queryEntitiesByCriteriaEntityWithPage("default", param, page);

		return arr;
	}

	@Bizlet("根据模板criteria查询分页列表")
	public static DataObject[] queryEntitiesByCriteriaEntity(DataObject param) throws ParamEmptyException {
		DataObject[] arr = new DataObject[0];
		if (null == param)
			return arr;

		if (isEmpty(param)) {
			throw new ParamEmptyException("queryEntitiesByCriteriaEntity中，查询模板的属性不能全部为空");
		}
		arr = DatabaseUtil.queryEntitiesByCriteriaEntity("default", param);

		return arr;
	}

	@Bizlet("根据模板template删除数据")
	public static void deleteEntityCascade(DataObject param) throws ParamEmptyException {
		if (null == param)
			return;

		if (isEmpty(param)) {
			throw new ParamEmptyException("deleteEntityCascade中，模板的属性不能全部为空");
		}
		DatabaseExt.deleteEntityCascade("default", param);
	}

	@Bizlet("根据模板criteria删除数据")
	public static void deleteByCriteriaEntity(DataObject param) throws ParamEmptyException {
		if (null == param)
			return;

		if (isEmpty(param)) {
			throw new ParamEmptyException("deleteByCriteriaEntity中，模板的属性不能全部为空");
		}
		DatabaseUtil.deleteByCriteriaEntity("default", param);
	}

	@Bizlet("根据模板template删除数据")
	public static void deleteByTemplate(DataObject param) throws ParamEmptyException {
		if (null == param)
			return;

		if (isEmpty(param)) {
			throw new ParamEmptyException("deleteByTemplate中，模板的属性不能全部为空");
		}
		DatabaseUtil.deleteByTemplate("default", param);
	}

	@Bizlet("根据类型获取业务字典项列表")
	public static List<DictEntry> getDictEntryList(String dicttypeid) {
		return DictManager.getDictEntries(dicttypeid);
	}

	@Bizlet("根据类型获取业务字典项数组")
	public static DataObject[] getDictEntryArray(String dicttypeid) {
		List<DictEntry> dictEntrties = DictManager.getDictEntries(dicttypeid);
		if (dictEntrties != null || dictEntrties.size() > 0) {
			DataObject[] results = new DataObject[dictEntrties.size()];
			for (int i = 0; i < dictEntrties.size(); ++i) {
				DictEntryImpl entry = (DictEntryImpl) dictEntrties.get(i);
				DataObject dictObj = DataObjectUtil.createDataObject("com.eos.foundation.DictEntry");
				dictObj.set("dictID", entry.getDictId());
				dictObj.set("dictName", entry.getDictName());
				dictObj.set("status", entry.getStatus());
				dictObj.set("sortNo", entry.getSortno());
				dictObj.set("parentid", entry.getFilter1());// 存为上级ID
				results[i] = dictObj;
			}
			return results;
		}
		return new DataObject[0];
	}

	@Bizlet("根据类型、业务字典项代码获取名称")
	public static String getDictName(String dictTypeId, String dictId) {
		return BusinessDictUtil.getDictName(dictTypeId, dictId);
	}

	@Bizlet("获取业务字典，当字典项数量超过maxCount时，只返回第一级；parentid有值时，只返回上级为parentid的字典项")
	public static DataObject[] getDictEntrys(String dicttypeid, String parentid, int maxCount) {
		if (maxCount < 1)
			maxCount = Integer.MAX_VALUE;

		List<DictEntry> dictEntrties = DictManager.getDictEntries(dicttypeid);
		if (dictEntrties != null || dictEntrties.size() > 0) {
			List<DataObject> list = new ArrayList<DataObject>();
			for (int i = 0, len = dictEntrties.size(); i < len; ++i) {
				DictEntryImpl entry = (DictEntryImpl) dictEntrties.get(i);
				if (StringUtils.isNotEmpty(parentid)) {
					if (entry.getFilter1() == null || !parentid.equals(entry.getFilter1())) {
						// parentid有值时，只返回上级为parentid的字典项
						continue;
					}
				} else if (len > maxCount) {
					// 当字典项数量超过maxCount时，只返回第一级
					if (entry.getFilter1() != null && !"null".equals(entry.getFilter1()))
						continue;
				}

				DataObject dictObj = DataObjectUtil.createDataObject("com.eos.foundation.DictEntry");
				dictObj.set("dictID", entry.getDictId());
				dictObj.set("dictName", entry.getDictName());
				dictObj.set("status", entry.getStatus());
				dictObj.set("sortNo", entry.getSortno());
				dictObj.set("parentid", entry.getFilter1());// 存为上级ID
				list.add(dictObj);
			}
			return list.toArray(new DataObject[list.size()]);
		}
		return new DataObject[0];
	}

	public static void closeConnection(IDASSession session, Connection conn) {
		if (session != null)
			try {
				session.close();
			} catch (Throwable e) {
				LogUtil.logI18NWarn("foundation_database0013", e, new Object[0]);
			}
		try {
			if ((conn != null) && (!(conn.isClosed())))
				conn.close();
		} catch (Throwable e) {
			LogUtil.logI18NWarn("foundation_database0013", e, new Object[0]);
		}
	}

	private static Map<String, String[]> entityIdPropertyNamesMap = new ConcurrentHashMap<String, String[]>();

	@SuppressWarnings("deprecation")
	public static String[] getIdPropertyNames(String entityName) {
		Connection conn = null;
		IDASSession session = null;
		String[] ids = new String[0];
		try {
			if (entityIdPropertyNamesMap.containsKey(entityName))
				return entityIdPropertyNamesMap.get(entityName);

			conn = ConnectionHelper.getConnection();
			session = DASManager.createDasSession(conn);
			ids = DatabaseExt.getIdPropertyNames(session, entityName);
			if (null != ids && ids.length > 0)
				entityIdPropertyNamesMap.put(entityName, ids);
		} finally {
			closeConnection(session, conn);
		}

		return ids;
	}

	public static String[] getIdPropertyNames(DataObject entity) {
		return getIdPropertyNames(DataObjectUtil.getEntityName(entity));
	}

	private static AtomicInteger seqno = new AtomicInteger();

	@Bizlet("手动生成UUID")
	public static String genUUIDString() {
		try {
			if (MDC.get("req.serverPort") != null) {
				StringBuilder sb = new StringBuilder(32);
				sb.append(new SimpleDateFormat("yyMMddHHmmss").format(getBusiDate()));// 日期时间：12位
				if (null != MDC.get("req.remoteAddr")) {// 客户端IP：8位
					String remoteAddr = MDC.get("req.remoteAddr").toString();
					Pattern ipp = Pattern.compile("^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$");
					Matcher matcher = ipp.matcher(remoteAddr);
					if (matcher.matches()) {
						sb.append(StringUtils.leftPad(Integer.toHexString(Integer.valueOf(matcher.group(1))), 2, '0'));
						sb.append(StringUtils.leftPad(Integer.toHexString(Integer.valueOf(matcher.group(2))), 2, '0'));
						sb.append(StringUtils.leftPad(Integer.toHexString(Integer.valueOf(matcher.group(3))), 2, '0'));
						sb.append(StringUtils.leftPad(Integer.toHexString(Integer.valueOf(matcher.group(4))), 2, '0'));
					} else {
						sb.append(StringUtils.leftPad(getCurrentOrgCd(), 8, '0'));
					}
				} else {
					sb.append(StringUtils.leftPad(getCurrentOrgCd(), 8, '0'));
				}
				Integer serverPort = Integer.valueOf(MDC.get("req.serverPort").toString());
				String serverIp = null;
				byte[] arr = InetAddress.getLocalHost().getAddress();
				if (null != arr && arr.length == 4) {
					serverIp = Integer.toHexString(arr[3] & 0xff);
					sb.append(StringUtils.leftPad(serverIp, 2, '0')); // 服务器IP：2位
					sb.append(StringUtils.leftPad(serverPort.toString(), 5, '0')); // 服务器端口：5位
				} else {
					sb.append(StringUtils.leftPad(getCurrentUserId(), 7, '0'));
				}

				// 加顺序号
				if (sb.length() == 27) {
					final int max = 0xFFFFF;
					int seq = seqno.get();
					if (seq < 1 || seq > max - 2000) {
						synchronized (GitUtil.class) {
							if (seq < 1 || seq > max - 2000) {
								seqno.set(1);
							}
						}
					}
					seq = seqno.getAndIncrement();
					sb.append(StringUtils.leftPad(Integer.toHexString(seq), 5, '0'));
					if (sb.length() == 32)
						return sb.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Properties props = new Properties();
		props.setProperty("separator", "");
		UUIDHexGenerator gen = new UUIDHexGenerator();
		gen.configure(Hibernate.STRING, props, null);
		Object id = gen.generate(null, null);
		if (null != id)
			return id.toString();

		return UUID.randomUUID().toString().replaceAll("-", "");// 不会发生！
	}

	/**
	 * @return 当前的request对象
	 */
	public static ServletRequest getRequest() {
		IMapContextFactory fact = DataContextManager.current().getMapContextFactory();
		if (null == fact || fact instanceof HttpMapContextFactory == false)
			return Log4jInterceptor.getRequest();

		HttpMapContextFactory http = (HttpMapContextFactory) fact;
		return ((HttpRequestMap) http.getRequestMap()).getRequest();
	}

	/**
	 * @return 当前的response对象
	 */
	public static ServletResponse getResponse() {
		IMapContextFactory fact = DataContextManager.current().getMapContextFactory();
		if (null == fact || fact instanceof Log4jInterceptor.MyHttpMapContextFactory == false) {
			return Log4jInterceptor.getResponse();
		}

		Log4jInterceptor.MyHttpMapContextFactory http = (Log4jInterceptor.MyHttpMapContextFactory) fact;
		return http.getResponse();
	}

	/**
	 * @return 当前的session对象
	 */
	public static HttpSession getSession() {
		IMapContextFactory fact = DataContextManager.current().getMapContextFactory();
		if (null == fact || fact instanceof HttpMapContextFactory == false)
			return Log4jInterceptor.getSession();

		HttpMapContextFactory http = (HttpMapContextFactory) fact;
		HttpServletRequest req = (HttpServletRequest) ((HttpRequestMap) http.getRequestMap()).getRequest();
		return req.getSession();
	}

	public static DataObject[] queryEntitiesByCriteriaEntity(DASTemplate das, IDASCriteria dasCriteria) throws EOSRuntimeException {
		DataObject[] dataObjects = DasCriteriaUtil.queryEntitiesByCriteriaEntity(das.getDataSource(), dasCriteria);
		return dataObjects;
	}

	public static DataObject[] queryByNamedSql(DASTemplate das, String nameSqlId, Object parameterObject) throws EOSRuntimeException {
		Object[] res = com.primeton.spring.support.DatabaseExt.queryByNamedSql(das.getDataSource(), nameSqlId, parameterObject);
		DataObject[] ts = new DataObject[res.length];
		try {
			for (int i = 0; i < res.length; i++) {
				ts[i] = new DataObjectImpl(new DataObjectType());
				copyTo((DataObject) res[i], ts[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new DataObject[0];
		}
		return ts;
	}

	public static <T> T[] queryEntitiesByCriteriaEntity(DASTemplate das, Class<T> componentType, IDASCriteria dasCriteria) throws EOSRuntimeException {
		DataObject[] dataObjects = DasCriteriaUtil.queryEntitiesByCriteriaEntity(das.getDataSource(), dasCriteria);
		T[] ts = (T[]) Array.newInstance(componentType, dataObjects.length);
		try {
			for (int i = 0; i < dataObjects.length; i++) {
				if (dataObjects[i].getClass() == componentType) {
					ts[i] = (T) dataObjects[i];
				} else {
					ts[i] = componentType.newInstance();
					copyTo((DataObject) dataObjects[i], ts[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return (T[]) Array.newInstance(componentType, 0);
		}
		return ts;
	}

	public static <T> T[] queryByNamedSql(DASTemplate das, Class<T> componentType, String nameSqlId, Object parameterObject) throws EOSRuntimeException {
		Object[] res = com.primeton.spring.support.DatabaseExt.queryByNamedSql(das.getDataSource(), nameSqlId, parameterObject);
		T[] ts = (T[]) Array.newInstance(componentType, res.length);
		try {
			for (int i = 0; i < res.length; i++) {
				if (res[i].getClass() == componentType) {
					ts[i] = (T) res[i];
				} else {
					ts[i] = componentType.newInstance();
					copyTo((DataObject) res[i], ts[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return (T[]) Array.newInstance(componentType, 0);
		}
		return ts;
	}

	public static void copyTo(Map from, Object to) {
		if (null == from || null == to)
			return;
		Iterator iterator = from.keySet().iterator();
		try {
			while (iterator.hasNext()) {
				String key = String.valueOf(iterator.next());
				BeanUtils.setProperty(to, key, from.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyTo(DataObject from, Object to) {
		if (null == from || null == to)
			return;
		List list = from.getInstanceProperties();
		try {
			for (int i = 0, len = list.size(); i < len; i++) {
				String key = ((Property) list.get(i)).getName();
				Object object = from.get(key);
				if (object != null) {
					if (object instanceof ContainerAwareList) {
						ContainerAwareList cal = (ContainerAwareList) object;
						List elist = new ArrayList();
						for (int j = 0; j < cal.size(); j++) {
							elist.add(cal.get(j));
						}
						BeanUtils.setProperty(to, key, elist);
					} else if (object instanceof Object[]) {
						Object[] o = (Object[]) object;
						List elist = new ArrayList();
						for (int j = 0; j < o.length; j++) {
							elist.add(o[j]);
						}
						BeanUtils.setProperty(to, key, elist);
					} else {
						BeanUtils.setProperty(to, key, object);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyTo(Object from, Object to) {
		DataObject obj1 = (DataObject) from;
		DataObject obj2 = (DataObject) to;
		List list = obj1.getInstanceProperties();
		for (int i = 0, len = list.size(); i < len; i++) {
			String key = ((Property) list.get(i)).getName();
			obj2.set(key, obj1.get(key));
		}
	}

	public static void copyTo(DataObject obj1, DataObject obj2) {
		List list = obj1.getInstanceProperties();
		for (int i = 0, len = list.size(); i < len; i++) {
			String key = ((Property) list.get(i)).getName();
			obj2.set(key, obj1.get(key));
		}
	}

	public static void copyTo(Object[] obj1, DataObject[] obj2) {
		for (int i = 0, len = obj1.length; i < len; i++) {
			copyTo((DataObject) obj1[i], obj2[i]);
		}
	}

	@Bizlet("截取字符串")
	public static String[] splitString(String temp) {
		String stringTemp[] = temp.split(",");
		return stringTemp;
	}

	@Bizlet("字符串转换数组")
	public static String stringToArray(String temp[]) {
		String stringTemp = null;
		for (int i = 0; i < temp.length; i++) {
			stringTemp = temp[i] + ",";

		}
		return stringTemp;
	}

	@Bizlet("获取影像文档编号")
	public static String getImageNumber(DataObject ob) {
		int num = 0;
		int line = DatabaseUtil.countByTemplate(GitUtil.DEFAULT_DS_NAME, ob);
		if (line == 0) {
			num = 1;
		} else {
			Object[] image = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.image.selectImageNum", ob);
			Map obj = (Map) image[0];
			int s = Integer.valueOf(obj.get("NUM").toString());
			num = s + 1;
		}

		String res = StringUtils.leftPad(String.valueOf(num), 3, '0');
		return res;
	}

	/**
	 * 默认区域
	 * 
	 * @return
	 */
	@Bizlet("默认区域")
	public static String getRgonCd() {
		String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_APPHEAD_GROUP;
		String rgonCd = EsbSocketConstant.CONTRIBUTION_APPHEAD_RGONCD;

		String vrgonCd = ConfigurationUtil.getUserConfigSingleValue(module, group, rgonCd);

		return vrgonCd;
	}

	/**
	 * 默认机构
	 * 
	 * @return
	 */
	@Bizlet("默认机构")
	public static String getBranchId() {
		String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_APPHEAD_GROUP;
		String branchId = EsbSocketConstant.CONTRIBUTION_APPHEAD_BRANCHID;

		String vbranchId = ConfigurationUtil.getUserConfigSingleValue(module, group, branchId);

		return vbranchId;
	}

	/**
	 * 默认柜员
	 * 
	 * @return
	 */
	@Bizlet("默认柜员")
	public static String getCoreotjygy() {
		String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_APPHEAD_GROUP;
		String coreotjygy = EsbSocketConstant.CONTRIBUTION_APPHEAD_COREOTJYGY;

		String otjygy = ConfigurationUtil.getUserConfigSingleValue(module, group, coreotjygy);

		return otjygy;
	}

	/**
	 * 放款柜员
	 * 
	 * @return
	 */
	@Bizlet("放款柜员")
	public static String getCorefkjygy() {
		String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_APPHEAD_GROUP;
		String corefkjygy = EsbSocketConstant.CONTRIBUTION_APPHEAD_COREFKJYGY;

		String fkjygy = ConfigurationUtil.getUserConfigSingleValue(module, group, corefkjygy);

		return fkjygy;
	}

	/**
	 * 还款柜员
	 * 
	 * @return
	 */
	@Bizlet("还款柜员")
	public static String getCorehkjygy() {
		String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_APPHEAD_GROUP;
		String corehkjygy = EsbSocketConstant.CONTRIBUTION_APPHEAD_COREHKJYGY;

		String hkjygy = ConfigurationUtil.getUserConfigSingleValue(module, group, corehkjygy);

		return hkjygy;
	}

	/**
	 * 信贷管理与核心日间其他交易柜员
	 * 
	 * @return
	 */
	@Bizlet("信贷管理与核心日间其他交易柜员")
	public static String getCorehxjygy() {
		String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_APPHEAD_GROUP;
		String corehxjygy = EsbSocketConstant.CONTRIBUTION_APPHEAD_COREHXJYGY;

		String hxjygy = ConfigurationUtil.getUserConfigSingleValue(module, group, corehxjygy);

		return hxjygy;
	}

	/**
	 * @return
	 */
	@Bizlet("ESB分配系统ID")
	public static String getSourceSysId() {
		String retSourceSysId = "";

		String module = EsbSocketConstant.CONTRIBUTION_ESB_SOCKET_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_APPHEAD_GROUP;
		String sourceSysId = EsbSocketConstant.CONTRIBUTION_APPHEAD_SOURCESYSID;

		retSourceSysId = ConfigurationUtil.getUserConfigSingleValue(module, group, sourceSysId);

		return retSourceSysId;
	}

	/**
	 * @return
	 */
	@Bizlet("放款冲正开关")
	public static String getFkIsOpen() {
		String retFkIsOpen = "";

		String module = EsbSocketConstant.CONTRIBUTION_EASYLCS_WEBSERVICE_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_EASYLCS_WEBSERVICE_GROUP;
		String fkIsOpen = EsbSocketConstant.FK_IS_OPEN;

		retFkIsOpen = ConfigurationUtil.getUserConfigSingleValue(module, group, fkIsOpen);

		return retFkIsOpen;
	}

	/**
	 * @return
	 */
	@Bizlet("还款异常登记开关")
	public static String getHkIsOpen() {
		String retHkIsOpen = "0";

		String module = EsbSocketConstant.CONTRIBUTION_EASYLCS_WEBSERVICE_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_EASYLCS_WEBSERVICE_GROUP;
		String hkIsOpen = EsbSocketConstant.HK_IS_OPEN;

		retHkIsOpen = ConfigurationUtil.getUserConfigSingleValue(module, group, hkIsOpen);
		if (retHkIsOpen == null || "".equals(retHkIsOpen)) {
			retHkIsOpen = "0";
		}
		return retHkIsOpen;
	}

	/**
	 * 格式化数据，将科学计数法形式的数字，格式成正常显示的数字
	 * 
	 * @param pattern
	 * @param val
	 * @return
	 */
	public static String formatData(String pattern, Object val) {
		String value = "";
		if (null == val || "".equals(val)) {
			value = new DecimalFormat(pattern).format(0);
		} else {
			value = new DecimalFormat(pattern).format(val);
		}
		return value;
	}

	/**
	 * add by shangmf 格式化数据，将map转换为json
	 * 
	 * @param pattern
	 * @param val
	 * @return
	 */
	@Bizlet("将map转换为json")
	public static String mapToJson(Map map) {

		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return jsonStr;
	}

	// add by shangmf
	@Bizlet("将int转换为String")
	public static String intToString(int value) {

		String valueStr = String.valueOf(value);

		return valueStr;
	}

	/**
	 * @return
	 */
	// add by shangmf
	@Bizlet("取webservice配置信息")
	public static String getWebServiceConfig() {

		String module = "CollWebServiceConfig";
		String group = "coll_webservice_server";
		String ip = "ip";
		String port = "port";
		String wservice = "service";

		String zip = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
		String zport = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
		String zservice = ConfigurationUtil.getUserConfigSingleValue(module, group, wservice);

		String url = "http://" + zip + ":" + zport + zservice;
		return url;
	}

	@Bizlet("根据传进来的对象转换为逗号分隔的字符串")
	public static String arrayToStrSplit(java.lang.Object listObj[]) {

		String returnStr = "";

		for (Object obj : listObj) {

			Map<String, String> map = (Map<String, String>) obj;
			returnStr += map.get("surety_no") + ",";

		}

		returnStr = returnStr.substring(0, returnStr.length() - 1);

		return returnStr;

	}

	/**
	 * 生成序列编号 两位编号+4位机构号+8位时间+6位顺序号
	 * 
	 * @param sqeName
	 *            序列编号 合同HT/借据JJ/不良处置CZ/担保合同DB
	 * @param orgNum
	 *            机构编号
	 * @return
	 */
	@Bizlet("生成序列编号")
	public static String getSeqNo(String sqeName, String orgNum) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("SEQNAME", "SEQ_" + sqeName + "_NO.NEXTVAL");
		Object[] res = DatabaseExt.queryByNamedSql(DEFAULT_DS_NAME, "com.bos.pub.common.selectSeqByName", map);
		DataObject data = (DataObject) res[0];
		StringBuilder sb = new StringBuilder(sqeName + orgNum);
		sb.append(getBusiDateStr().replaceAll("-", ""));
		StringBuilder seqno = new StringBuilder(data.getString("SEQNO"));
		while (seqno.length() < 6) {
			seqno.insert(0, '0');
		}
		if (seqno.length() > 6) {
			seqno.substring(seqno.length() - 6);
		}
		sb.append(seqno);

		return sb.toString();

	}

	/**
	 * 获取变更表里当日变更信息
	 */
	@Bizlet("获取变更表里当日变更信息")
	public DataObject getChangeInfo(String summaryId) {
		String currTime = getSysDateYYYYMMDD();
		Map map = new HashMap();
		String beginDate = currTime + " 00:00:00";
		String endDate = currTime + " 23:59:59";
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("summaryId", summaryId);
		Object[] changeInfo = DatabaseExt.queryByNamedSql("default", "com.bos.pay.LoanSummary.getChageInfo", map);
		DataObject info = null;
		if (changeInfo.length > 0) {
			info = (DataObject) changeInfo[0];
		}
		// String changeId = info.getString("CHANGE_ID");
		// String loanChangeType = info.getString("LOAN_CHANGE_TYPE");
		return info;
	}

	@Bizlet("获取当前机构的虚拟机构柜员")
	public static String getNumOrg(String orgCode) throws EOSException {
		Map maps = new HashMap();
		maps.put("orgNum", orgCode);
		Object[] summaryInfo = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryEmpOrgInfo", maps);
		String[] orgEmps = null;
		if (summaryInfo.length > 0) {
			Map map = (Map) summaryInfo[0];
			String orgEmp = (String) map.get("ORGEMP");
			BigDecimal times = (BigDecimal) map.get("TIMES");
			orgEmps = orgEmp.split(",");
			int tims = Integer.valueOf(times.toString());
			return orgEmps[tims - 1];
		} else {
			throw new EOSException("当前机构无对应的虚拟柜员。");
		}
	}

	@Bizlet("获取当前机构的会计机构")
	public static String getAccOrg(String orgCode) throws EOSException {
		Map maps = new HashMap();
		maps.put("status", "1");
		maps.put("oprOrgNo", orgCode);
		Object[] orgAccRel = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryOrgAccInfo", maps);
		String[] orgEmps = null;
		if (orgAccRel.length > 0) {
			Map map = (Map) orgAccRel[0];
			String orgEmp = (String) map.get("ACC_ORG_NO");
			return orgEmp;
		} else {
			throw new EOSException("当前机构无对应的虚拟柜员。");
		}
	}

	@Bizlet("修改当前机构操作虚拟机构柜员的次数")
	public static void getNumTimes(String orgCode) throws EOSException {
		try {
			Map maps = new HashMap();
			maps.put("orgNum", orgCode);
			Object[] summaryInfo = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryEmpOrgInfo", maps);
			Map map = (Map) summaryInfo[0];
			BigDecimal times = (BigDecimal) map.get("TIMES");
			maps.put("times", times.toString());
			DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.updateEmpOrgInfo", maps);
		} catch (Exception e) {
			throw new EOSException("修改虚拟机构柜员次数失败。");
		}
	}

	@Bizlet("保存出账信息时将信息保存到流程里")
	public void setRelativeInfo(String processId, String loanId) throws EOSException {
		try {
			Map map = new HashMap();
			map.put("loanId", loanId);
			Object[] relativeInfo = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryRelativeInfo", map);
			Map maps = (Map) relativeInfo[0];
			String applyModeType = (String) maps.get("APPLYMODETYPE");
			String guarantyType = (String) maps.get("GUARANTYTYPE");
			String productType = (String) maps.get("PRODUCTTYPE");
			BigDecimal loanAmt = (BigDecimal) maps.get("LOANAMT");
			BigDecimal exchangeRate = (BigDecimal) maps.get("EXCHANGERATE");
			BigDecimal mouney = loanAmt.multiply(exchangeRate);
			Map<String, Object> relaDatas = new HashMap<String, Object>();
			if ("02".equals(applyModeType)) {
				relaDatas.put("isLow", "1");// 1是低风险
			} else {
				relaDatas.put("isLow", "0");// 0否低风险
			}
			relaDatas.put("guarType", guarantyType);// 担保方式
			relaDatas.put("authAmt", mouney.toString());// 申请额度
			relaDatas.put("productType", productType);// 产品
			RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processId), relaDatas);
		} catch (Exception e) {
			throw new EOSException("保存流程信息失败!");
		}
	}

	@Bizlet("根据发起人所在机构取是否绵阳区域标志")
	public static String getOrgArea(String orgNum) {
		Map maps = new HashMap();
		maps.put("orgNum", orgNum);
		Object[] orgArea = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.grt.bsCiValue.getOraAreaSql.queryOrgArea", maps);
		Map map = (Map) orgArea[0];
		String orgAreaStr = (String) map.get("AREA");
		return orgAreaStr;

	}

	@Bizlet("根据机构编号获取上级机构层级")
	public static String getParentLevel(String orgNum) {
		Map maps = new HashMap();
		maps.put("orgNum", orgNum);
		Object[] orgLevel = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.grt.bsCiValue.getOraAreaSql.queryOrgParentLevel", maps);
		String parentlv = "";
		Map map = new HashMap();
		if (orgLevel != null) {
			if (orgLevel.length == 1) {
				map = (Map) orgLevel[0];
			} else {
				map = (Map) orgLevel[1];
			}
			parentlv = map.get("ORGLEVEL") + "";
		}

		return parentlv;
	}

	@Bizlet("获取借据id")
	public void getSummaryId(String loanId) {
		Map map = new HashMap();
		map.put("loanId", loanId);
		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.updateSummaryStaus", map);
	}

	@Bizlet("保存委托贷款基金账号")
	public String saveEntrust(String loanId) throws EOSException {
		String msg = "";
		try {
			Map map = new HashMap();
			map.put("loanId", loanId);
			DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			String productTypes = loanInfo.getString("productType");
			String loanOrg = loanInfo.getString("loanOrg");
			if ("02005001".equals(productTypes)) {// 公积金委托贷款
				Object[] entAccs1 = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForHx.queryAccId", map);
				if (entAccs1.length > 0) {
					DataObject entacc = (DataObject) entAccs1[0];
					String accId1 = entacc.getString("ACCID");
					DataObject orgAccount = DataObjectUtil.createDataObject("com.bos.utp.dataset.position.OrgEntrustAccount");
					orgAccount.set("orgCode", loanOrg);
					int orglength = DatabaseUtil.expandEntity("default", orgAccount);
					String entrustAcc = orgAccount.getString("entrustAcc");
					String entrustReturnPrincipalAcc = orgAccount.getString("entrustReturnPrincipalAcc");
					String entrustReturnInterestAcc = orgAccount.getString("entrustReturnInterestAcc");
					String entrustReturnAcc = orgAccount.getString("entrustReturnAcc");
					if (orglength == 1) {
						Map accoutMap = new HashMap();
						accoutMap.put("entrustAcc", entrustAcc);
						accoutMap.put("entrustReturnPrincipalAcc", entrustReturnPrincipalAcc);
						accoutMap.put("entrustReturnInterestAcc", entrustReturnInterestAcc);
						accoutMap.put("entrustReturnAcc", entrustReturnAcc);
						accoutMap.put("accId", accId1);
						DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.payInfo.queryForHx.updateEntAcc", accoutMap);
					} else {
						msg = "未查询到公积金委托账号信息,请系统管理员添加公积金账号信息!";
					}
				} else {
					msg = "该公积金委托贷款信息有误!";
				}
			}
			Object[] entAccs = (Object[]) DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.queryForHx.queryEntAcc", map);
			if (entAccs.length > 0) {
				for (int i = 0; i < entAccs.length; i++) {
					DataObject entacc = (DataObject) entAccs[i];
					String productType = entacc.getString("TYPE");// 产品类型
					String org = entacc.getString("ORG");// 放款机构
					String currType = entacc.getString("CURRTYPE");// 币种
					String accId = entacc.getString("ACCID");
					// 币种转换
					if ("CNY".equals(currType)) {
						currType = "01";
					} else if ("GBP".equals(currType)) {// 英镑
						currType = "12";
					} else if ("HKD".equals(currType)) {// 港币
						currType = "13";
					} else if ("USD".equals(currType)) {// 美元
						currType = "14";
					} else if ("CHF".equals(currType)) {// 瑞士法郎
						currType = "15";
					} else if ("JPY".equals(currType)) {// 日元
						currType = "27";
					} else if ("CAD".equals(currType)) {// 加拿大元
						currType = "28";
					} else if ("AUD".equals(currType)) {// 澳洲元
						currType = "29";
					} else if ("SGD".equals(currType)) {// 新加坡元
						currType = "32";
					} else if ("EUR".equals(currType)) {// 欧元
						currType = "38";
					} else if ("MOP".equals(currType)) {// 澳门元
						currType = "81";
					} else {
						msg = "不支持的币种";
					}
					String subject = "";// 委托贷款基金账号业务代号
					String subject1 = "";// 委托贷款收息账号业务代号
					if ("02005001".equals(productType)) {// 公积金委托贷款
						subject = "3134";
						subject1 = "3069";
					} else if ("02005002".equals(productType) || "02005010".equals(productType)) {// 个人委托贷款
						subject = "3133";
						subject1 = "3068";
					} else if ("01013001".equals(productType) || "01013010".equals(productType)) {// 单位委托贷款
						subject = "3132";
						subject1 = "3067";
					} else {
						msg = "不支持的产品类型";
					}
					String acountNo = org + "9" + currType + "0" + subject + "00001";
					String acountNo1 = org + "9" + currType + "0" + subject1 + "00001";// 20170911将 9和0改为8和6,20171123将8和6改为9和0
					if (acountNo.length() != 17) {
						msg = "委托基金账号错误，请检查后再操作";
					}
					if (acountNo1.length() != 17) {
						msg = "委托贷款收息账号错误，请检查后再操作";
					}
					Map mapm = new HashMap();
					mapm.put("accId", accId);
					mapm.put("entrustLoanAcc", acountNo);
					mapm.put("entrustReturnAcc", acountNo1);
					DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.payInfo.queryForHx.updaEntAcc", mapm);// 委托贷款基金账号和委托贷款收息账号
				}
			}
		} catch (Exception e) {
			msg = "保存委托贷款账户信息失败:" + e.getMessage();
		}
		return msg;
	}

	// add by shangmf:多法人改造，传入userId和orgcode
	@Bizlet("根据orgid取OrgCode")
	public static String getOrgCode() {

		IUserObject user = CommonUtil.getIUserObject();

		String orgId = user.getUserOrgId();

		// 逻辑构件名称 :com.bos.csm.callback.moveBusiness
		String componentName = "com.bos.aft.util";
		// 逻辑流名称
		String operationName = "getOrgCode";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		// 逻辑流的输入参数
		Object[] params = new Object[1];
		params[0] = orgId;
		Object[] orgCode = null;
		try {
			orgCode = logicComponent.invoke(operationName, params);
		} catch (Throwable e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String orgCodeStr = (String) orgCode[0];
		return orgCodeStr;
	}

	@Bizlet("生成委托贷款收息账号")
	public String accountNum(String orgNum) throws EOSException {
		String account = orgNum + "9" + "01" + "0" + "3069" + "00001";// 01-人民币
		if (account.length() != 17) {
			throw new EOSException("生成委托贷款收息账号失败!");
		}
		return account;
	}

	/**
	 * 按期限单位计算起始日期加到期限，得到到期日期
	 * 
	 * @param qxdw
	 *            期限单位（Y 年,J 季,M 月,D 天）
	 * @param qx
	 *            期限
	 * @param rq
	 *            起始日期 yyyy-mm-dd
	 * @return 到期日期 yyyy-mm-dd
	 * @throws EOSException
	 */
	@Bizlet("日期函数加法，返回日期")
	public static String addDate(String qxdw, int qx, String rq) {
		Calendar cl = Calendar.getInstance();
		cl.set(Integer.parseInt(rq.substring(0, 4)), (Integer.parseInt(rq.substring(5, 7)) - 1), Integer.parseInt(rq.substring(8, 10)));
		if ("Y".equals(qxdw)) {
			cl.add(Calendar.YEAR, qx);
		} else if ("J".equals(qxdw)) {
			cl.add(Calendar.MONTH, qx * 3);
		} else if ("M".equals(qxdw)) {
			cl.add(Calendar.MONTH, qx);
		} else if ("D".equals(qxdw)) {
			cl.add(Calendar.DAY_OF_MONTH, qx);
		} else {
			return "";
		}
		return cl.get(Calendar.YEAR) + "-" + (cl.get(Calendar.MONTH) < 9 ? "0" : "") + (cl.get(Calendar.MONTH) + 1) + "-" + (cl.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "") + cl.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 按期限单位计算两日期间期限
	 * 
	 * @param qxdw
	 *            期限单位（Y 年,M 月,D 天）
	 * @param qsrq
	 *            起始日期 yyyy-mm-dd
	 * @param dqrq
	 *            到期日期 yyyy-mm-dd
	 * @return 期限
	 * @throws Exception
	 * @throws EOSException
	 */
	@Bizlet("两日期函数计算期限，返回期限")
	public static int retDateTerm(String qxdw, String qsrq, String dqrq) throws Exception {
		int a = 0;
		if ("Y".equals(qxdw)) {
			a = accountDateYear(qsrq, dqrq);
		} else if ("M".equals(qxdw)) {
			a = accountDateMoth(qsrq, dqrq);
		} else if ("D".equals(qxdw)) {
			a = (int) getDistDates(qsrq, dqrq);
		}
		return a;
	}

	/**
	 * 两日期间计算天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public static long getDistDates(String start, String end) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date startDate = sdf.parse(start);
		java.util.Date endDate = sdf.parse(end);
		return getDistDates(startDate, endDate);
	}

	/**
	 * 两日期间计算天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public static long getDistDates(java.util.Date startDate, java.util.Date endDate) {
		long totalDate = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		long timestart = calendar.getTimeInMillis();
		calendar.setTime(endDate);
		long timeend = calendar.getTimeInMillis();
		totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);
		return totalDate;
	}

	/**
	 * 日期差值计算 月
	 * 
	 * @param qsrq
	 *            起始日期 YYYY-MM-DD
	 * @param bjrq
	 *            到期日期 YYYY-MM-DD
	 * @return
	 * @throws Exception
	 */
	public static int accountDateMoth(String qsrq, String bjrq) throws Exception {
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		// String d1 = df.parse(qsrq).toString();
		// String d2 = df.parse(bjrq).toString();
		String[] s1 = qsrq.split("-");
		String[] s2 = bjrq.split("-");
		int year1 = Integer.parseInt(s1[0]);
		int year2 = Integer.parseInt(s2[0]);
		if (year2 - year1 < 0) {
			throw new Exception("输入的终止日期年份小于起始日期的年份");
		}
		int a = 12 * (year2 - year1);
		int month1 = Integer.parseInt(s1[1]);
		int month2 = Integer.parseInt(s2[1]);
		int b = month2 - month1;
		int day1 = Integer.parseInt(s1[2]);
		int day2 = Integer.parseInt(s2[2]);
		int c = a + b;
		if (day2 - day1 > 0) {
			c = a + b + 1;
		}
		return c;

	}

	/**
	 * 日期差值计算 年
	 * 
	 * @param qsrq
	 *            起始日期 YYYY-MM-DD
	 * @param bjrq
	 *            到期日期 YYYY-MM-DD
	 * @return
	 * @throws Exception
	 */
	public static int accountDateYear(String qsrq, String bjrq) throws Exception {
		double a = accountDateMoth(qsrq, bjrq);
		double c = a / 12;
		return (int) Math.ceil(c);
	}

	// public static void main(String[] args)throws Exception{
	// //计算两日起间月
	// System.out.println("============"+accountDateMoth("2017-02-01","2018-03-01"));
	// System.out.println("============"+accountDateYear("2017-02-01","2018-02-02"));
	// System.out.println("============"+addDate("J", 2, "2017-02-01"));
	// System.out.println("============"+getDistDates("2017-02-01", "2017-03-01"));
	// }

	// add by shangmf:增加取会计机构:出入库记表外账时使用
	@Bizlet("获取当前机构的会计机构")
	public static String getAccOrgOffBalance(String orgCode) throws EOSException {
		Map maps = new HashMap();
		maps.put("status", "1");
		maps.put("oprOrgNo", orgCode);
		Object[] orgAccRel = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryOrgAccInfoOffBalance", maps);
		String[] orgEmps = null;
		if (orgAccRel.length > 0) {
			Map map = (Map) orgAccRel[0];
			String orgEmp = (String) map.get("ACC_ORG_NO");
			return orgEmp;
		} else {
			throw new EOSException("当前机构无对应的会计机构。");
		}
	}

	// add by shangmf:增加取会计机构:出入库记表外账时使用
	@Bizlet("获取当前机构的虚拟机构柜员")
	public static String getNumOrgOffBalance(String orgCode) throws EOSException {
		Map maps = new HashMap();
		maps.put("orgNum", orgCode);
		Object[] summaryInfo = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.queryEmpOrgInfoOffBalance", maps);
		String[] orgEmps = null;
		if (summaryInfo.length > 0) {
			Map map = (Map) summaryInfo[0];
			String orgEmp = (String) map.get("ORGEMP");
			BigDecimal times = (BigDecimal) map.get("TIMES");
			orgEmps = orgEmp.split(",");
			int tims = Integer.valueOf(times.toString());
			return orgEmps[tims - 1];
		} else {
			throw new EOSException("当前机构无对应的虚拟柜员。");
		}
	}

	@Bizlet("获取金额数据样式")
	public static BigDecimal getMoney(BigDecimal money) {
		if (money == null) {
			return BigDecimal.ZERO.setScale(2);
		}
		return money.setScale(2);
	}

	public static BigDecimal getMoney(String money) {
		if (money == null || (money = money.trim()).isEmpty()) {
			return BigDecimal.ZERO.setScale(2);
		}
		money = money.replace(",", "");
		if (money.isEmpty()) {
			return BigDecimal.ZERO.setScale(2);
		}
		BigDecimal b = new BigDecimal(money);
		return b.setScale(2);
	}

	@Bizlet("获取是否是借新还旧业务")
	public static String getNewAndOld(String loanId) throws EOSException {
		Map maps = new HashMap();
		maps.put("loanId", loanId);
		Object[] objs1 = DatabaseExt.queryByNamedSql("default", "com.bos.pay.LoanSummary.getIsJxhj", maps);
		DataObject isJxhj = (DataObject) objs1[0];
		String bizHappenType = isJxhj.getString("BIZ_HAPPEN_TYPE");
		String agriculLoans = isJxhj.getString("AGRICUL_LOANS");
		return bizHappenType + ":" + agriculLoans;
	}

	@Bizlet("获取最大的基准利率")
	public static String getMaxBaseRate() throws EOSException {
		Object[] objs1 = DatabaseExt.queryByNamedSql("default", "com.bos.pay.LoanSummary.getMaxBaseRate", "");
		DataObject isJxhj = (DataObject) objs1[0];
		String maxbaseRate = isJxhj.getString("MAXRATE");
		return maxbaseRate;
	}

	@Bizlet("根据日期获取基准利率")
	public static String getbettweenBaseRate(String contractId) throws EOSException {
		Map maps = new HashMap();
		maps.put("contractId", contractId);
		Object[] objs1 = DatabaseExt.queryByNamedSql("default", "com.bos.pay.LoanSummary.getbettweenBaseRate", maps);
		DataObject isJxhj = (DataObject) objs1[0];
		String maxbaseRate = isJxhj.getString("MAXRATE");
		return maxbaseRate;
	}

	public static boolean contain(String key, String[] keys) {
		if (keys == null || keys.length == 0) {
			return false;
		}
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null && keys[i].equals(key)) {
				return true;
			}
		}
		return false;
	}

	@Bizlet("担保金额汇总")
	public static String getSumSuarity(String dyAmt, String zyAmt, String bzrAmt, String bzjAmt) throws EOSException {
		String[] dyAmt1 = dyAmt.split(",");
		String dyAmt2 = "";
		for (int i = 0; i < dyAmt1.length; i++) {
			dyAmt2 = dyAmt2 + dyAmt1[i];
		}
		String[] zyAmt1 = zyAmt.split(",");
		String zyAmt2 = "";
		for (int i = 0; i < zyAmt1.length; i++) {
			zyAmt2 = zyAmt2 + zyAmt1[i];
		}
		String[] bzrAmt1 = bzrAmt.split(",");
		String bzrAmt2 = "";
		for (int i = 0; i < bzrAmt1.length; i++) {
			bzrAmt2 = bzrAmt2 + bzrAmt1[i];
		}
		String[] bzjAmt1 = bzjAmt.split(",");
		String bzjAmt2 = "";
		for (int i = 0; i < bzjAmt1.length; i++) {
			bzjAmt2 = bzjAmt2 + bzjAmt1[i];
		}
		String sum = new BigDecimal(dyAmt2).add(new BigDecimal(zyAmt2)).add(new BigDecimal(bzrAmt2)).add(new BigDecimal(bzjAmt2)).toString();
		String str1 = sum.substring(0, sum.indexOf("."));
		String aft = sum.substring(sum.indexOf("."));
		str1 = new StringBuilder(str1).reverse().toString();
		String str2 = "";
		for (int i = 0; i < str1.length(); i++) {
			if (i * 3 + 3 > str1.length()) {
				str2 += str1.substring(i * 3, str1.length());
				break;
			}
			str2 += str1.substring(i * 3, i * 3 + 3) + ",";
		}
		if (str2.endsWith(",")) {
			str2 = str2.substring(0, str2.length() - 1);
		}
		return new StringBuilder(str2).reverse().toString() + aft;

	}
}
