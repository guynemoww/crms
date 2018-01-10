package com.bos.utp.tools.dict;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eos.access.client.ServiceContext;
//import com.eos.common.connection.ConnectionHelper;
import com.eos.das.entity.DASManager;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.IDASSession;
import com.eos.engine.component.ILogicComponent;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.server.dict.DictEntry;
import com.eos.server.dict.DictFactory;
import com.eos.server.dict.DictHelper;
import com.eos.server.dict.DictType;
import com.eos.server.dict.impl.DictEntryImpl;
//import com.eos.server.dict.impl.DictTypeImpl;
import com.eos.system.logging.Logger;
import com.primeton.ext.runtime.resource.RuntimeHelper;
import commonj.sdo.DataObject;

/**
 * TODO ABF的实现 parentId调整为 type,id的组合
 *
 */
public class ABFBusinDictFactory implements DictFactory {

	private static final Logger log = TraceLoggerFactory.getLogger(ABFBusinDictFactory.class);
	
	
	public void DataObject2Dict(DataObject dataObj, DictType dictType) {
		int level = dataObj.getInt("rank");
		String dictTypeName = dataObj.getString("dicttypename");
		dictType.setDictTypeName(dictTypeName);
		String parentId = dataObj.getString("parentid");
		dictType.setLevel(level);
		//实体对象类型确定
	    @SuppressWarnings("unchecked") List<DataObject> dataEntrys = dataObj.getList("Eosdictentrys");
		for (DataObject dataEntry : dataEntrys) {
			int entryLevel = dataEntry.getInt("rank");
			String dictId = dataEntry.getString("dictid");
			String dictName = dataEntry.getString("dictname");
			String entryParentId = dataEntry.getString("parentid");
			String filter1 = dataEntry.getString("filter1");
			String filter2 = dataEntry.getString("filter2");
			int status = dataEntry.getInt("status");
			int sortno = dataEntry.getInt("sortno");
			int rank = dataEntry.getInt("rank");
			String seqno = dataEntry.getString("seqno");

			DictEntryImpl entry = new DictEntryImpl(dictId, dictName);
			entry.setLevel(entryLevel);
			entry.setFilter1(filter1);
			entry.setFilter2(filter2);
			entry.setSeqno(seqno);
			entry.setSortno(sortno);
			entry.setStatus(status);
			entry.setRank(rank);

			if (entryParentId != null && parentId != null) {
				DictEntry parentEntry = dictType.getParent().getDictEntryById(
						entryParentId);
				if (parentEntry == null){
					log.error("DictEntry " + dictId + " load failed ! Parent dictEntry: \""
							+ entryParentId + "\" not Found!");
					continue;
				}
//					throw new RuntimeException("Parent dictEntry: \""
//							+ entryParentId + "\" not Found!");
				entry.setParent(parentEntry);
				parentEntry.addChild(entry);
			}
			dictType.addDictEntry(entry);
		}
	}

	/**
	 * ��������ҵ���ֵ�
	 * 
	 */
	public Map<String, DictType> loadAllDictTypes() throws Exception {

		List<DataObject> dictObjs = queryEntities("com.primeton.eos.webui.dict.Eosdicttype");
		Map<String, DictType> retlist = new HashMap<String, DictType>();
		Map<String, DataObject> tmpList = new HashMap<String, DataObject>();
		for (DataObject dataObj : dictObjs) {
			String dictTypeId = dataObj.getString("dicttypeid");
			tmpList.put(dictTypeId, dataObj);
		}

		for (DataObject dataObj : dictObjs) {
			String dictTypeId = dataObj.getString("dicttypeid");
			if (retlist.get(dictTypeId) != null)
				continue;
			else
				loadType(dataObj, retlist, tmpList);

			/*
			 * String dictTypeId = dataObj.getString("dicttypeid"); DictType
			 * dictType = new DictTypeImpl(dictTypeId); DataObject2Dict(dataObj,
			 * dictType); String parentId = dataObj.getString("parentid"); if
			 * (parentId != null) { //���ø�ҵ���ֵ����� DictType parent =
			 * retlist.get(parentId); dictType.setParent(parent);
			 * parent.setChild(dictType); }
			 * retlist.put(dictType.getDictTypeId(), dictType);
			 */

		}
		List<DataObject> dictentryi18n = queryEntities("com.primeton.eos.webui.dict.Eosdictentryi18n");

		for (int i = 0; i < dictentryi18n.size(); i++) {
			DataObject o = dictentryi18n.get(i);
			DictHelper.addDictEntryI18n(o.getString("locale"), o
					.getString("dicttypeid"), o.getString("dictid"), o
					.getString("dictname"));
		}

		List<DataObject> dicttypei18n = queryEntities("com.primeton.eos.webui.dict.Eosdicttypei18n");
		// ����entry,type I18n
		for (int i = 0; i < dicttypei18n.size(); i++) {
			DataObject o = dicttypei18n.get(i);
			DictHelper.addDictTypeI18n(o.getString("locale"), o
					.getString("dicttypeid"), o.getString("dicttypename"));
		}
		return retlist;
	}

	private static Connection getConnection() {
		Connection conn = null;
	//TODO	conn = ConnectionHelper.getConnection("BusinessDict");
		return conn;
	}

	private static Object smartExecute(Callback callback) {
		Connection conn = getConnection();
		IDASSession session = DASManager.createDasSession(conn);
		try {
			Object result = callback.execute(session);
			return result;
		} catch (Exception e) {
			// try {
			// conn.rollback();
			// } catch (SQLException e1) {
			// throw new RuntimeException(e1);
			// }
			// �׳�try����쳣
			throw new RuntimeException(e);
		} finally {
			session.close();
			try {
				conn.close();
			} catch (Exception e) {
				// TODO:
			}
		}
	}

	private interface Callback {
		public Object execute(IDASSession session);
	}

	/**
	 * ��ݲ�ѯ������ѯ����б�
	 * 
	 * @param dsName
	 *            ���Դ���
	 * @param criteria
	 *            ��ѯ����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DataObject> queryEntities(final String criteria) {

		if (RuntimeHelper.getServerMode().equals(RuntimeHelper.ServerMode.APP)) {
			// ΪApp��

			return (List<DataObject>) smartExecute(new Callback() {
				IDASCriteria ct = DASManager.createCriteria(criteria);

				public Object execute(IDASSession session) {
					if (criteria
							.equals("com.primeton.eos.webui.dict.Eosdicttype")) {
						ct.addAssociation("Eosdictentrys");
						ct.asc("Eosdictentrys.sortno");
					}

					return session.query(ct);
				}
			});
		} else {
			// ΪWeb��
			/*
			 * MBeanServerConnection mbs = MBeanServerFactory.getMBeanServer(
			 * JmxServiceUrlHelper.buildServiceUrl(null, RuntimeHelper
			 * .getServerIp(), new Integer(RuntimeHelper
			 * .getServerPort()).intValue(), null), null, null,
			 * EOSBusinDictDataLoader.class.getClassLoader(), null);
			 * 
			 * EOSBusinDictDataLoaderMBean proxy = MBeanServerAccessor
			 * .newProxyInstance(mbs, ObjectNameFactory.buildObjectName(
			 * ObjectNameFactory.MANAGEMENT_TYPE, RuntimeHelper
			 * .getServerAppName(), "EOSBusinDictDataLoaderMBean"),
			 * EOSBusinDictDataLoaderMBean.class);
			 * proxy.getDictDataFromDatabase(criteria); return
			 * proxy.getDictDataFromDatabase(criteria);
			 */
			try {
				ILogicComponent comp = new ServiceContext()
						.locateBizLogic("com.eos.server.dict.impl.dictLoader");// �߼��������

				Object[] rets = comp.invoke("getEosDictData", new Object[0]);
				DataObject[] result = (DataObject[]) rets[0];

				List re = new ArrayList<DataObject>();
				for (int i = 0; i < result.length; i++)
					re.add(result[i]);
				return re;
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

	}

	/**
	 * ����dicttype,���dicttypeδ����,���ȼ��ظ�dicttype
	 * 
	 * 
	 */
	private void loadType(DataObject dictObj, Map<String, DictType> dictMap,
			Map<String, DataObject> tmpList) {
//
//		String dictTypeId = dictObj.getString("dicttypeid");
//
//		DictType dictType = new DictTypeImpl(dictTypeId);
//
//		String parentId = dictObj.getString("parentid");
//		if (parentId != null) { // ���ø�ҵ���ֵ�����
//			DictType parent = dictMap.get(parentId);
//			if (parent == null) {
//				if (tmpList.get(parentId) == null){
//					log.error("DictEntry " + dictTypeId + " load failed ! Parent dictEntry: \""
//							+ parentId + "\" not Found!");
//					return;
////					throw new RuntimeException("Parent dictType: \"" + parentId
////							+ "\" not Found!");
//				}
//				loadType(tmpList.get(parentId), dictMap, tmpList);
//				parent = dictMap.get(parentId);
//			}
//			dictType.setParent(parent);
//			parent.setChild(dictType);
//		}
//		DataObject2Dict(dictObj, dictType);
//		dictMap.put(dictType.getDictTypeId(), dictType);

	}

}
