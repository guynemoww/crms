/**
 * 
 */
package com.bos.pub.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.RuleExecutor;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.das.entity.criteria.ExprType;
import com.eos.das.entity.criteria.OrderbyType;
import com.eos.das.entity.criteria.impl.ExprTypeImpl;
import com.eos.das.entity.criteria.impl.OrderbyTypeImpl;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.data.sdo.impl.DataObjectImpl;
import com.primeton.data.sdo.impl.types.DataObjectType;

import commonj.sdo.DataObject;
import commonj.sdo.Property;

/**
 * @author 王世春
 * @date 2013-10-17 09:49:15
 * 
 */
@Bizlet("模型相关操作")
public class ModelUtil {

	private static String toJavaName(String name) {
		if (null == name)
			return null;
		if (name.length() < 1)
			return name;
		char first = name.charAt(0);
		if (name.contains("_") == false && (first < 'A' || first > 'Z')) {
			return name;
		}
		name = name.toLowerCase();
		StringBuilder sb = new StringBuilder();
		for (int i = 0, size = name.length(); i < size; i++) {
			char ch = name.charAt(i);
			if (ch == '_') {
				sb.append(Character.toUpperCase(name.charAt(i + 1)));
				i++;
				continue;
			}
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * @param modelId
	 *            模型主键
	 * @return
	 * @author 王世春
	 */
	@Bizlet("根据模型ID查询模型指标关联的基本指标及其选择项")
	public static DataObject[] getBaseIndexAndItems(String modelId,
			HashMap<String, Object> param) {
		DataObject[] arr = new DataObject[0];
		if (StringUtils.isEmpty(modelId)) {
			return arr;
		}
		// DataObject obj = DataFactory.INSTANCE.create("com.bos.pub.model",
		// "BaseIndexAndItems");
		// obj.set("modelId", modelId);
		// arr = DatabaseUtil.queryEntitiesByTemplate("default", obj);
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("modelId", modelId);
			Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.model.model.selectBaseIndexAndItems", map);
			arr = new DataObject[tmp.length];
			for (int i = 0; i < tmp.length; i++) {
				DataObject dat = (DataObject) tmp[i];
				arr[i] = new DataObjectImpl(new DataObjectType());
				List list = dat.getInstanceProperties();
				for (int j = 0; j < list.size(); j++) {
					Property prop = (Property) list.get(j);
					char ch = prop.getName().charAt(0);
					if (prop.getName().contains("_")
							|| (ch >= 'A' && ch <= 'Z')) {
						arr[i].set(toJavaName(prop.getName()), dat.get(prop));
					} else {
						arr[i].set(prop.getName(), dat.get(prop));
					}
				}
			}
		}
		if (null == arr)
			arr = new DataObject[0];
		StringBuffer paramInds = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			DataObject index = arr[i];
			if ("1".equals(index.get("indexType"))) {// 字典表下拉框
				// obj = DataFactory.INSTANCE.create("com.bos.pub.model",
				// "TbPubIndexItem");
				// obj.set("tbPubIndexBase.indexId",
				// index.getString("indexId"));
				// index.set("items", DatabaseUtil.queryEntitiesByTemplate(
				// "default", obj));

				CriteriaType cri = CriteriaType.FACTORY.create();
				cri.set_entity("com.bos.pub.model.TbPubIndexItem");
				cri.set_expr(new ArrayList<ExprType>());
				ExprType expr = new ExprTypeImpl();
				expr.set_op("=");
				expr.set_property("tbPubIndexBase.indexId");
				expr.set_value(index.getString("indexId"));
				cri.get_expr().add(expr);// 通过ID查询，在指标生效时更新模型指标中的ID
				// expr.set_property("tbPubIndexBase.indexInd");
				// expr.set_value(index.getString("indexInd"));
				// cri.get_expr().add(expr);
				// expr = new ExprTypeImpl();
				// expr.set_op("=");
				// expr.set_property("tbPubIndexBase.indexStatus");
				// expr.set_value("1");
				// cri.get_expr().add(expr);

				cri.set_orderby(new ArrayList<OrderbyType>());
				OrderbyType ord = new OrderbyTypeImpl();
				ord.set_property("iOrder");
				ord.set_sort("asc");
				cri.get_orderby().add(ord);
				index.set("items", DatabaseUtil.queryEntitiesByCriteriaEntity(
						"default", cri));
			}
			if ("2".equals(index.get("gradeType"))) {// 比较范围得分
				// obj = DataFactory.INSTANCE.create("com.bos.pub.model",
				// "TbPubIndexRange");
				// obj.set("tbPubIndexBase.indexId",
				// index.getString("indexId"));
				// index.set("ranges", DatabaseUtil.queryEntitiesByTemplate(
				// "default", obj));

				CriteriaType cri = CriteriaType.FACTORY.create();
				cri.set_entity("com.bos.pub.model.TbPubIndexRange");
				cri.set_expr(new ArrayList<ExprType>());
				ExprType expr = new ExprTypeImpl();
				expr.set_op("=");
				expr.set_property("tbPubIndexBase.indexInd");
				expr.set_value(index.getString("indexInd"));
				cri.get_expr().add(expr);

				cri.set_orderby(new ArrayList<OrderbyType>());
				OrderbyType ord = new OrderbyTypeImpl();
				ord.set_property("rOrder");
				ord.set_sort("asc");
				cri.get_orderby().add(ord);
				index.set("ranges", DatabaseUtil.queryEntitiesByCriteriaEntity(
						"default", cri));
			}
			if ("2".equals(index.get("indexType"))
					|| "3".equals(index.get("indexType"))) {// 2-计算公式得出（可修改）;3-计算公式得出（不可修改）
				if (null != index.getString("indexParam")) {
					if (paramInds.length() > 0) {
						paramInds.append(",");
					}
					String str = index.getString("indexId");
					if (null == str)
						System.out.println(str);
					paramInds.append(index.getString("indexParam"));// 原使用aviator时需要此值，改为自行开发后不需要此值
				}
			}
		}
		if (null == param)
			param = new HashMap<String, Object>();
		if (paramInds.length() > 0) {// 原使用aviator时需要，改为自行开发后不需要
			// 获取参数
			CriteriaType cri = CriteriaType.FACTORY.create();
			cri.set_entity("com.bos.pub.model.TbPubParam");
			cri.set_expr(new ArrayList<ExprType>());
			ExprType expr = new ExprTypeImpl();
			expr.set_op("=");
			expr.set_property("paramStatus");
			expr.set_value("1");// 正常状态
			cri.get_expr().add(expr);

			expr = new ExprTypeImpl();
			expr.set_op("in");
			expr.set_property("paramInd");
			expr.set_value(paramInds.toString());
			cri.get_expr().add(expr);

			DataObject[] params = DatabaseUtil.queryEntitiesByCriteriaEntity(
					"default", cri);
			for (DataObject p : params) {
				Map<String, Object> re = new RuleExecutor().getParamValues(p,
						param);

				if (re.size() == 1 && re.containsKey(p.get("paramInd"))) {
					// 单值参数
					param.put(String.valueOf(p.get("paramInd")), re.get(p
							.get("paramInd")));
					continue;
				}
				// 多值参数
				param.putAll(re);
			}
		}

		// 根据参数计算指标值
		for (int i = 0; i < arr.length; i++) {
			DataObject index = arr[i];
			if (!"2".equals(index.get("indexType"))
					&& !"3".equals(index.get("indexType"))) {// 2-计算公式得出（可修改）;3-计算公式得出（不可修改）
				continue;
			}
			Object indexIndex = index.get("indexIndex");
			System.out.println("指标名称：" + index.get("indexInd"));
			Object value = null;
			if (null == indexIndex
					|| indexIndex.toString().trim().length() == 0) {
				//
			} else {// 原使用aviator时需要，改为自行开发后不需要
				String[] indexs = indexIndex.toString().split(",");
				for (String p : indexs) {
					if (p == null || p.trim().length() == 0)
						continue;

					p = p.trim();
					System.out.println("嵌套指标名称：" + p);
					RuleExecutor ex = new RuleExecutor();
					ex.setLevel(1);
					Object re = ex.evalIndex(p, param);
					param.put(p, re);
				}
			}

			// value = new RuleExecutor().eval(index.getString("indexExpr"),
			// param);// 原使用aviator时需要，改为自行开发后不需要
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("rid", index.getString("indexExpr"));// 指标绑定了一条规则记录，自动生效，因此可无需考虑规则的状态等情况
			try {
				value = new DecisionUtil().execRule(map, param).get("result");
				if ("3".equals(index.getString("gradeType"))) {
					String gradeExpr = index.getString("gradeExpr");
					if (null != gradeExpr && gradeExpr.length() > 0) {
						map.clear();
						map.put("rid", gradeExpr);
						param.put("指标值", value);
						Object iGrade = new DecisionUtil().execRule(map, param)
								.get("result");
						if (null != iGrade) {
							index.set("iGrade", iGrade.toString());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return arr;
			}

			System.out.println("指标执行结果：" + value);
			if (null != value) {
				index.set("iValue", value.toString());
			} else {
				index.set("iValue", "");
			}
		}

		return arr;
	}

}
