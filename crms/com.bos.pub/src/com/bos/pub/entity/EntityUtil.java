package com.bos.pub.entity;

import java.util.ArrayList;
import java.util.List;

import com.bos.utp.tools.DBUtil;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.das.entity.criteria.ExprType;
import com.eos.das.entity.criteria.OrderbyType;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import commonj.sdo.DataObject;

public class EntityUtil {

	public static DataObject getEntityById(String entityName, String... param) {
		return getEntityById(entityName, true, param);
	}

	public static DataObject getEntityById(String entityName, boolean throwError, String... param) {
		return getEntityById(DBUtil.DB_NAME_DEF, entityName, throwError, param);
	}

	/**
	 * 根据实体主键查询数据
	 * 
	 * @param dbName
	 *            数据库名称
	 * @param entityName
	 *            实体名称
	 * @param throwError
	 *            是否抛错
	 * @param param
	 *            实体参数
	 * @return
	 */
	public static DataObject getEntityById(String dbName, String entityName, boolean throwError, String... param) {
		if (param.length % 2 != 0) {
			throw new IllegalArgumentException("实体查询参数为键值对，长度应该是偶数");
		}
		DataObject obj = DataObjectUtil.createDataObject(entityName);
		for (int i = 0; i < param.length - 1; i = i + 2) {
			obj.set(param[i], param[i + 1]);
		}
		int size = DatabaseUtil.expandEntity(dbName, obj);
		if (size != 1) {
			if (throwError) {
				throw new RuntimeException("没有获取到必须的数据实体[" + entityName + "]");
			}
			obj = null;
		}
		return obj;
	}

	public static DataObject searchEntity(String entityName, String... keys) {
		return searchEntity(DBUtil.DB_NAME_DEF, entityName, keys, null);
	}

	public static DataObject searchEntity(String entityName, String[] keys, String[] orderbys) {
		return searchEntity(DBUtil.DB_NAME_DEF, entityName, keys, orderbys);
	}

	/**
	 * 根据传入参数以及排序规则查询首条数据
	 * 
	 * @param dbName
	 *            数据库名称
	 * @param entityName
	 *            实体对象名称
	 * @param params
	 *            数据参数
	 * @param orderbys
	 *            排序规则
	 * @return
	 */
	public static DataObject searchEntity(String dbName, String entityName, String[] params, String[] orderbys) {
		CriteriaType criteriaType = getCriteriaType(entityName, params, orderbys);
		DataObject obj = DataObjectUtil.createDataObject(entityName);
		if (DatabaseUtil.expandEntityByCriteriaEntity(DBUtil.DB_NAME_DEF, criteriaType, obj) == 0) {
			obj = null;
		}
		return obj;
	}

	public static DataObject[] searchEntitys(String entityName, String... params) {
		return searchEntitys(DBUtil.DB_NAME_DEF, entityName, params, null);
	}

	public static DataObject[] searchEntitys(String entityName, String[] params, String[] orderbys) {
		return searchEntitys(DBUtil.DB_NAME_DEF, entityName, params, orderbys);
	}

	/**
	 * 根据传入参数以及排序规则查询相应数据
	 * 
	 * @param dbName
	 * @param entityName
	 * @param params
	 * @param orderbys
	 * @return
	 */
	public static DataObject[] searchEntitys(String dbName, String entityName, String[] params, String[] orderbys) {
		CriteriaType criteriaType = getCriteriaType(entityName, params, orderbys);
		return DatabaseUtil.queryEntitiesByCriteriaEntity(dbName, criteriaType);
	}

	/**
	 * 根据传入参数组装查询条件
	 * 
	 * @param entityName
	 * @param params
	 * @param orderbys
	 * @return
	 */
	public static CriteriaType getCriteriaType(String entityName, String[] params, String[] orderbys) {
		if (params.length % 2 != 0) {
			throw new IllegalArgumentException("实体查询参数为键值对，长度应该是偶数");
		}
		CriteriaType criteriaType = CriteriaType.FACTORY.create();
		criteriaType.set_entity(entityName);
		List<ExprType> exprList = new ArrayList<ExprType>();
		for (int i = 0; i < params.length - 1; i = i + 2) {
			ExprType exprType = ExprType.FACTORY.create();
			exprType.set(params[i], params[i + 1]);
			exprList.add(exprType);
		}
		criteriaType.set_expr(exprList);
		List<OrderbyType> orderbyList = new ArrayList<OrderbyType>();
		if (orderbys != null && orderbys.length != 0) {
			for (int i = 0; i < orderbys.length - 1; i++) {
				if (orderbys[i] == null || orderbys[i].isEmpty()) {
					continue;
				}
				OrderbyType orderby = OrderbyType.FACTORY.create();
				orderby.set_property(orderbys[i]);
				String sort = orderbys[i + 1] == null ? "asc" : orderbys[i + 1].toLowerCase();
				if ("desc".equals(sort) || "asc".equals(sort)) {
					orderby.set_sort(sort);
					i++;
				}
				orderbyList.add(orderby);
			}

		}
		criteriaType.set_orderby(orderbyList);
		return criteriaType;
	}

	/**
	 * 根据条件验证当前数据是否存在，[只支持 where a=b and b=c]模式
	 * 
	 * @param entityName
	 * @param params
	 * @return
	 */
	public static boolean exists(String entityName, String... params) {
		return exists(DBUtil.DB_NAME_DEF, entityName, params);
	}

	public static boolean exists(String dbName, String entityName, String[] params) {
		CriteriaType criteriaType = getCriteriaType(entityName, params, null);
		int num = DatabaseUtil.count(dbName, criteriaType);
		return num > 0;
	}
}
