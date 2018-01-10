package com.bos.pub;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.eos.foundation.database.DatabaseExt;
import com.eos.server.dict.DictFactory;
import com.eos.server.dict.DictType;
import com.eos.server.dict.impl.DictEntryImpl;
import com.eos.server.dict.impl.DictTypeImpl;
import com.eos.system.logging.Logger;

/**
 * 
 * @author 王世春
 * @time 2014-02-25
 * @description 业务字典自定义加载，用于替换原来的com.eos.server.dict.impl.EOSBusinDictFactory
 */
public class CustomCacheFactory implements DictFactory {
	private static final Logger log = GitUtil
			.getLogger(CustomCacheFactory.class);

	public Map<String, DictType> loadAllDictTypes() throws Exception {
		Map<String, DictType> retlist = new HashMap<String, DictType>();

		try {
			log.info("开始加载业务字典类型");
			Object[] types = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.cache.select_dict_types", new HashMap());
			if (null == types || types.length < 1) {
				log.info("未找到任何业务字典类型");
				return retlist;
			}

			log.info("加载业务字典类型:" + types.length + " 条记录");
			for (int i = 0; i < types.length; i++) {
				Map map = (Map) types[i];
				String dicttypeid = String.valueOf(map.get("DICTTYPEID"));
				String dicttypename = String.valueOf(map.get("DICTTYPENAME"));
				retlist.put(dicttypeid, new DictTypeImpl(dicttypeid,
						dicttypename));
			}

			log.info("开始加载业务字典项");
			Object[] entrys = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.cache.select_dict_entrys", new HashMap());
			if (null == entrys || entrys.length < 1) {
				log.info("未找到任何业务字典项");
				return retlist;
			}

			log.info("加载业务字典项:" + entrys.length + " 条记录");
			DictTypeImpl type = null;
			for (int i = 0; i < entrys.length; i++) {
				Map map = (Map) entrys[i];
				String dicttypeid = String.valueOf(map.get("DICTTYPEID"));
				String dictid = String.valueOf(map.get("DICTID"));
				String dictname = String.valueOf(map.get("DICTNAME"));
				String status = String.valueOf(map.get("STATUS"));
				String sortno = String.valueOf(map.get("SORTNO"));
				String parentid = String.valueOf(map.get("PARENTID"));

				if (null == type
						|| type.getDictTypeId().equals(dicttypeid) == false) {
					type = (DictTypeImpl) retlist.get(dicttypeid);
				}
				if (null == type)
					continue;
				DictEntryImpl ent = new DictEntryImpl(dictid, dictname);
				if (status != null && status.length() > 0
						&& "null".equals(status) == false) {
					ent.setStatus(new BigDecimal(status).intValue());
				} else {
					ent.setStatus(1);// 默认为启用状态
				}
				if (sortno != null && sortno.length() > 0
						&& "null".equals(sortno) == false) {
					ent.setSortno(new BigDecimal(sortno).intValue());
				}
				ent.setFilter1(parentid); // 存为上级ID
				type.getDictEntries().add(ent);
			}
		} catch (Exception e) {
			log.error("加载业务字典时出错", e);
		}
		return retlist;
	}

	// class DictEntryImpl extends com.eos.server.dict.impl.DictEntryImpl {
	// public DictEntryImpl() {
	// this(null, null);
	// }
	//
	// public DictEntryImpl(String dictId, String dictName) {
	// super(dictId, dictName);
	// }
	// }
}
