/**
 * 
 */
package com.bos.conPrint.product;

import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.conPrint.ConInfoLoadUtil;
import com.bos.conPrint.IConInfoLoad;
import com.bos.csm.pub.CsmUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.TableName;
import com.bos.pub.entity.name.ConTableName;
import com.bos.pub.entity.name.CsmTableName;
import com.bos.pub.entity.name.LogTableName;
import com.bos.pub.entity.name.PubTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.example.excel.ChangeUtil;
import commonj.sdo.DataObject;

/**
 * <pre>
 * Title: 程序的中文名称
 * Description: 程序功能的描述
 * </pre>
 * 
 * @author Administrator
 * @version 1.00.00
 * 
 */

public class ConInfoLoad implements IConInfoLoad {

	public static final String REPORT_CON = "con";
	public static final String REPORT_A = "a";
	public static final String REPORT_B = "b";
	public static final String REPORT_ADD = "add";

	public static final String UNPRINT = "unprint";

	private Map<String, String> defaultMap;

	private String loadConSql;
	private String reportName;
	private String reportNameCN;

	public Map<String, Object> loadReport(Map<String, String> param) {
		Map<String, Object> reportMap = new HashMap<String, Object>();
		Map<String, Object> conMap = loadCon(param);
		if (conMap == null || conMap.isEmpty()) {
			return null;
		}
		reportMap.put(REPORT_CON, conMap);
		// 加载甲方客户信息
		reportMap.put(REPORT_A, loadParty((String) getNeedValue(conMap, "A_ID")));
		// 加载乙方客户信息
		reportMap.put(REPORT_B, loadOrg((String) getNeedValue(conMap, "B_ID")));
		// 加载地址信息
		reportMap.put(REPORT_ADD, loadAddr(conMap));
		// 加载其他数据
		loadOther(reportMap);
		// ---------编码转换-----------
		convert(reportMap);
		// 加载报表信息
		setReport(reportMap);
		setDefValue(reportMap);
		return reportMap;
	}

	@SuppressWarnings("unchecked")
	public void setReport(Map<String, Object> reportMap) {
		Map<String, Object> conMap = (Map<String, Object>) reportMap.get(REPORT_CON);
		if (conMap == null) {
			return;
		}
		String reportName = File.separator + "contract" + File.separator + getReportName(reportMap);
		reportMap.put("reportName", reportName);
		DataObject log = DataObjectUtil.createDataObject(LogTableName.TB_LOG_REPORT_PRINT);
		log.set("userNum", GitUtil.getCurrentUserId());
		log.set("printDate", new Date());
		log.set("reportName", reportName);
		log.set("contractId", getNeedValue(conMap, "CONTRACT_ID"));
		log.set("contractNum", getNeedValue(conMap, "CONTRACT_NUM"));
		DatabaseUtil.insertEntity(DBUtil.DB_NAME_DEF, log);
		reportMap.put("REPORT_NUM", log.getString("id").toUpperCase());
	}

	public void setDefValue(Map<String, Object> reportMap) {
		if (defaultMap == null || defaultMap.isEmpty()) {
			return;
		}
		for (String key : defaultMap.keySet()) {
			String defValue = defaultMap.get(key);
			if (defValue == null || defValue.isEmpty()) {
				continue;
			}
			setDefValue(reportMap, key, defValue);
		}
	}

	@SuppressWarnings("unchecked")
	private void setDefValue(Map<String, Object> dataMap, String key, Object defValue) {
		int index = key.indexOf(".");
		Object obj;
		if (index > 0) {
			String k = key.substring(0, index);
			obj = dataMap.get(k);
			if (obj == null) {
				obj = new HashMap<String, Object>();
				dataMap.put(k, obj);
			} else if (!(obj instanceof Map)) {
				return;
			}
			key = key.substring(index + 1);
			setDefValue((Map<String, Object>) obj, key, defValue);
		} else {
			Object value = dataMap.get(key);
			if (value != null) {
				return;
			} else if (value instanceof Collection) {
				for (Map<String, Object> v : (Collection<Map<String, Object>>) value) {
					if (((Map<String, Object>) v).get(key) == null) {
						((Map<String, Object>) v).put(key, defValue);
					}
				}
			} else {
				dataMap.put(key, defValue);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> loadCon(Map<String, String> param) {
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, loadConSql, param);
		Map<String, Object> conMap = datas == null || datas.length == 0 ? null : (Map<String, Object>) datas[0];
		return conMap;
	}

	public void loadOther(Map<String, Object> reportMap) {

	}

	public Map<String, Object> loadParty(String partyId) {
		DataObject party = EntityUtil.searchEntity(CsmTableName.TB_CSM_PARTY, "partyId", partyId);
		Map<String, Object> partyMap = new HashMap<String, Object>();
		if (null != party) {
			String partyType = party.getString("partyTypeCd");
			partyMap.put("PARTY_ID", partyId);
			partyMap.put("PARTY_TYPE", partyType);
			partyMap.put("PARTY_NAME", party.getString("partyName"));
			// 设置甲方基础信息
			if (CsmUtil.isNatural(partyType)) {
				setCsmNatural(partyId, partyMap);
			} else {
				setCsmCorp(partyId, partyMap);
			}
		}
		return partyMap;
	}

	/**
	 * 设置甲方对公基础信息
	 * 
	 * @param data
	 * @param partyId
	 */
	protected void setCsmCorp(String partyId, Map<String, Object> corpMap) {
		DataObject corp = EntityUtil.getEntityById(CsmTableName.TB_CSM_CORPORATION, "partyId", partyId);
		if (corp != null) {
			corpMap.put("LEGAL_NAME", corp.getString("legalName"));
			getPartyAdders(partyId, corpMap);
		}
	}

	protected void getPartyAdders(String partyId, Map<String, Object> partyMap) {
		DataObject attached = EntityUtil.searchEntity(CsmTableName.TB_CSM_ATTACHED_INFO, "partyId", partyId);
		if (attached != null) {
			partyMap.put("PHONE", attached.getString("accountContactsPhone"));
			partyMap.put("TELEPHONE", attached.getString("telephone"));
			partyMap.put("EMAIL", attached.getString("email"));
			partyMap.put("FAX", attached.getString("fax"));
			partyMap.put("ZIP_NUM", attached.getString("zipNum"));
			partyMap.put("ADDRESS", attached.getString("addressValue"));
		}
	}

	/**
	 * 设置乙方对私信息
	 * 
	 * @param data
	 * @param partyId
	 */
	protected void setCsmNatural(String partyId, Map<String, Object> natuMap) {
		DataObject natural = EntityUtil.searchEntity(CsmTableName.TB_CSM_NATURAL_PERSON, "partyId", partyId);
		if (null != natural) {
			natuMap.put("CERT_TYPE", natural.getString("certType"));
			natuMap.put("CERT_NUM", natural.getString("certNum"));
			natuMap.put("PHONE", natural.getString("phoneNumber"));
			natuMap.put("ADDRESS", natural.getString("familyAddress"));
		}
	}

	public Map<String, Object> loadOrg(String orgcode) {
		Map<String, Object> orgMap = new HashMap<String, Object>();
		DataObject org = EntityUtil.searchEntity(TableName.OM_ORGANIZATION, "orgcode", orgcode);
		if (org == null) {
			return orgMap;
		}
		String temp = org.getString("orgSeal");
		if (temp == null || temp.isEmpty()) {
			temp = org.getString("orgname");
		}
		// 法人名称与机构公章是相同的
		orgMap.put("ORG_NAME", temp);
		orgMap.put("LEGAL_NAME", temp);
		orgMap.put("ZIP_NUM", org.get("zipcode"));
		orgMap.put("PHONE", org.get("linktel"));
		temp = org.getString("district");
		if (temp != null && !(temp = temp.trim()).isEmpty()) {
			DataObject address = EntityUtil.searchEntity(PubTableName.TB_PUB_DISTRICT_ECIF, "dictid", temp);
			temp = address != null ? address.getString("dictname") : "";
		} else {
			temp = "";
		}
		if (org.get("streetAddress") != null) {
			temp += org.get("streetAddress");
		}
		orgMap.put("ADDRESS", temp);
		return orgMap;
	}

	protected Map<String, Object> loadAddr(Map<String, Object> conMap) {
		String contractId = getNeedValue(conMap, "CONTRACT_ID");
		DataObject obj = EntityUtil.searchEntity(ConTableName.TB_CON_NOTICE_ADDRS, "contractId", contractId);
		Map<String, Object> addMap = new HashMap<String, Object>();
		if (obj == null) {
			return addMap;
		}
		addMap.put("A_SEND_ADDR", obj.get("aSendAddr")); // 甲方送达地址
		addMap.put("A_POSTCODE", obj.get("aPostcode")); // 甲方邮编
		addMap.put("A_RECEIVER", obj.get("aReceiver")); // 甲方收件人
		addMap.put("A_PHONE", obj.get("aPhone")); // 甲方电话
		addMap.put("A_EMAIL", obj.get("aEmail")); // 甲方电子邮箱
		addMap.put("B_SEND_ADDR", obj.get("bSendAddr")); // 乙方送达地址
		addMap.put("B_POSTCODE", obj.get("bPostcode")); // 乙方邮编
		addMap.put("B_RECEIVER", obj.get("bReceiver")); // 乙方收件人
		addMap.put("B_PHONE", obj.get("bPhone")); // 乙方电话
		addMap.put("B_EMAIL", obj.get("bEmail")); // 乙方电子邮箱
		return addMap;
	}

	public String getReportName(Map<String, Object> reportMap) {
		return getReportName();
	}

	@SuppressWarnings("unchecked")
	public void convert(Map<String, Object> reportMap) {
		convertCon((Map<String, Object>) reportMap.get(REPORT_CON));
		ConInfoLoadUtil.convertData((Map<String, Object>) reportMap.get(REPORT_A));
		ConInfoLoadUtil.convertData((Map<String, Object>) reportMap.get(REPORT_B));
	}

	public void convertCon(Map<String, Object> conMap) {
		BigDecimal num = (BigDecimal) conMap.get("TOTAL_COUNT");
		if (num != null) {
			conMap.put("TOTAL_COUNT_CN", ChangeUtil.getBigNum(num.longValue()));
		}
		// 时间数据转换
		ConInfoLoadUtil.setDateFormat(conMap, new String[] { "BEGIN_DATE" }, new String[] { "END_DATE" });
	}

	@SuppressWarnings("unchecked")
	public static <T> T getNeedValue(Map<String, ? extends Object> param, String key) {
		Object value = param.get(key);
		if (value == null) {
			throw new RuntimeException("所需的报表必要信息缺失[" + key + "]");
		}
		return (T) value;
	}

	public String getLoadConSql() {
		return loadConSql;
	}

	public void setLoadConSql(String loadConSql) {
		this.loadConSql = loadConSql;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Map<String, String> getDefaultMap() {
		return defaultMap;
	}

	public void setDefaultMap(Map<String, String> defaultMap) {
		this.defaultMap = defaultMap;
	}

	public String getReportNameCN(String... params) {
		return reportNameCN;
	}

	public void setReportNameCN(String reportNameCN) {
		this.reportNameCN = reportNameCN;
	}

}
