package com.bos.utp.tools;

import static com.eos.system.annotation.ParamType.CONSTANT;

import java.util.Date;

import com.eos.das.entity.SequenceGenerator;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.annotation.BizletParam;
import com.eos.system.annotation.ParamType;
import com.eos.system.utility.StringUtil;
import commonj.sdo.DataObject;

/**
 * 
 * 序号生成工具
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: SequenceUtil.java,v $
 * Revision 1.6  2010/12/01 03:22:41  caisy
 * 更改编码为UTF-8
 *
 * Revision 1.5  2010/11/30 16:12:51  caisy
 * 编码改为UTF-8
 *
 * Revision 1.4  2010/11/02 02:59:40  caisy
 * Update:每日从零开始的序列优化
 *
 * Revision 1.3  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.2  2009/03/23 03:21:34  caisy
 * Update:可管理机构弹出选择界面错误
 *
 * Revision 1.1  2009/01/07 06:52:12  liuxiang
 * *** empty log message ***
 *
 * Revision 1.1  2009/01/05 02:34:56  caisy
 *
 * Revision 1.5  2008/12/17 15:17:39  wengzr
 * Update:getNextSequenceValue的条件whereString增加对like语句的支持
 *
 * Revision 1.4  2008/11/30 11:12:22  wengzr
 * Added:增加运算构件注释
 *
 * Revision 1.3  2008/11/20 09:54:29  wengzr
 * Update:修改获取最大值的字段名为max_为前缀
 *
 * Revision 1.2  2008/11/18 08:19:19  wengzr
 * 提交CVS
 *
 * Revision 1.1  2008/11/17 11:18:12  wengzr
 * 提交CVS
 *
 */
@Bizlet("序列号生成工具")
public class SequenceUtil {
     static final String DAY_SEQUENCE_KEY="_abf_day_";//序列前缀
     static final long ONEDAYSECONDS = 86400000;//一天毫秒数=24*60*60*1000 毫秒
	/**
	 * 数据库编号生成器，产生型如BT_00001
	 * 
	 * @param entityName 数据实体名
	 * @param propertyName 属性名
	 * @param pattern 匹配模式，一般作为like 查询的条件字符串, 如果传入的参数为""或null，表示不需要采用匹配模式，则产生型如000001的编码
	 * @param length  编码的长度
	 * @return 返回一个产生的编号
	 * @throws Exception
	 */
	@Bizlet(value = "数据库编号生成器，产生型如BT_00001", params = { @BizletParam(index = 0, paramAlias = "dsName", type = ParamType.CONSTANT),
			@BizletParam(index = 1, paramAlias = "entityName", type = ParamType.CONSTANT), @BizletParam(index = 2, paramAlias = "propertyName", type = ParamType.CONSTANT),
			@BizletParam(index = 3, paramAlias = "pattern", type = ParamType.CONSTANT), @BizletParam(index = 4, paramAlias = "length", type = ParamType.CONSTANT) })
	public static String getNextSequenceValue(String dsName, String entityName, String propertyName, String pattern, int length) {
		return getNextSequenceValue(dsName, entityName, propertyName, pattern, null, length);
	}

	/**
	 * 数据库编号生成器，产生型如BT_00001
	 * 
	 * @param entityName 数据实体名
	 * @param propertyName 属性名
	 * @param pattern 匹配模式，一般作为like 查询的条件字符串, 如果传入的参数为""或null，表示不需要采用匹配模式，则产生型如000001的编码
	 * @param whereString WHERE条件语句，多个条件以逗号分割，支持“like”、“=”条件操作符如field1=value1,field2=value2 或field1 like '%1'
	 * @param length  编码的长度
	 * @return 返回一个产生的编号
	 * @throws Exception
	 */
	@Bizlet(value = "数据库编号生成器，产生型如BT_00001", params = { @BizletParam(index = 0, paramAlias = "dsName", type = ParamType.CONSTANT),
			@BizletParam(index = 1, paramAlias = "entityName", type = ParamType.CONSTANT), @BizletParam(index = 2, paramAlias = "propertyName", type = ParamType.CONSTANT),
			@BizletParam(index = 3, paramAlias = "pattern", type = ParamType.CONSTANT), @BizletParam(index = 4, paramAlias = "whereString", type = ParamType.CONSTANT),
			@BizletParam(index = 5, paramAlias = "length", type = ParamType.CONSTANT) })
	public static String getNextSequenceValue(String dsName, String entityName, String propertyName, String pattern, String whereString, int length) {

		DataObject criteriaEntity = DataObjectUtil.createDataObject("com.primeton.das.criteria.criteriaType");
		criteriaEntity.set("_entity", entityName);
		criteriaEntity.set("_select/_max[1]", propertyName);

		if (StringUtil.isNullOrBlank(pattern)) {
			if (!StringUtil.isNullOrBlank(whereString)) {
				int index = 1;
				for (String singleCondition : whereString.split(",")) {

					if (singleCondition.indexOf("=") != -1) {
						String[] condString = singleCondition.split("=");
						criteriaEntity.set("_expr[" + index + "]/" + condString[0], condString[1]);
						criteriaEntity.set("_expr[" + index + "]/_op", "=");
					} else if (singleCondition.indexOf("like") != -1) {
						buildCriteriaLikeCondition(criteriaEntity, index, singleCondition);
					}
					index++;
				}
			}

		} else {
			if (pattern.length() > length) {
				throw new IllegalArgumentException("编码的产生模式:" + pattern + "的长度:" + pattern.length() + ",大于编码的长度");
			} else {
				criteriaEntity.set("_expr[1]/" + propertyName, pattern);
				criteriaEntity.set("_expr[1]/_op", "like");
				criteriaEntity.set("_expr[1]/_likeRule", "end");

				if (!StringUtil.isNullOrBlank(whereString)) {
					int index = 2;
					for (String singleCondition : whereString.split(",")) {
						if (singleCondition.indexOf("=") != -1) {
							String[] condString = singleCondition.split("=");
							criteriaEntity.set("_expr[" + index + "]/" + condString[0], condString[1]);
							criteriaEntity.set("_expr[" + index + "]/_op", "=");
						} else if (singleCondition.indexOf("like") != -1) {
							buildCriteriaLikeCondition(criteriaEntity, index, singleCondition);
						}
						index++;
					}
				}
			}
		}
		//执行数据库
		DataObject[] result = DatabaseUtil.queryEntitiesByCriteriaEntity(dsName, criteriaEntity);
		return getNextSystemNo(result, propertyName, pattern, length);
	}

	private static void buildCriteriaLikeCondition(DataObject criteriaEntity, int index, String condition) {
		String[] condString = condition.split("like");
		String paramName = condString[0].trim();
		String paramValue = condString[1].trim();
		if (paramValue.startsWith("'%") && paramValue.endsWith("%'")) {
			String value = paramValue.substring(2, paramValue.length() - 2);
			criteriaEntity.set("_expr[" + index + "]/" + paramName, value);
			criteriaEntity.set("_expr[" + index + "]/_likeRule", "all");
		} else if (paramValue.startsWith("'%")) {
			String value = paramValue.substring(2, paramValue.length() - 1);
			criteriaEntity.set("_expr[" + index + "]/" + paramName, value);
			criteriaEntity.set("_expr[" + index + "]/_likeRule", "start");
		} else if (paramValue.endsWith("%'")) {
			String value = paramValue.substring(1, paramValue.length() - 2);
			criteriaEntity.set("_expr[" + index + "]/" + paramName, value);
			criteriaEntity.set("_expr[" + index + "]/_likeRule", "end");
		}
		criteriaEntity.set("_expr[" + index + "]/_op", "like");
	}

	/**
	 * get next system no
	 * 
	 * @param _result
	 * @param _colName
	 * @param _pattern
	 * @param _length
	 * @return
	 * @throws Exception
	 */
	private static String getNextSystemNo(DataObject[] _result, String _colName, String _pattern, int _length) {
		String current = null;
		String notNullPattern = null;
		if (_pattern == null) {
			notNullPattern = "";
		} else {
			notNullPattern = new String(_pattern);
		}

		if (_result != null && _result.length > 0) {
			current = _result[0].getString("max_" + _colName);
			if (current != null) {
				long numStr = Long.parseLong(current.substring(notNullPattern.length())) + 1;
				return notNullPattern + ChangeUtil.leftPad(new Long(numStr).toString(), (_length - notNullPattern.length()), '0');
			}
		}
		return notNullPattern + ChangeUtil.leftPad("1", (_length - notNullPattern.length()), '0');
	}

	/**
	 * 返回值为 code+date+00000x
	 * @param type 类型如org、biz
	 * @param code 类型值、123
	 * @param date 时间如20090101
	 * @param length 自增编号长度
	 * @return
	 */
	@Bizlet(value = "通过规则生成序号", params = { @BizletParam(index = 0, defaultValue = "org", type = CONSTANT, paramAlias = "类型"),
			@BizletParam(index = 1, defaultValue = "0001", type = CONSTANT, paramAlias = "类型值"),
			@BizletParam(index = 2, defaultValue = "20090101", type = CONSTANT, paramAlias = "日期"), @BizletParam(index = 3, defaultValue = "6", type = CONSTANT, paramAlias = "长度") })
	public static String getNextSequenceByTD(String type, String code, String date, int length) {
		String key = type+code+date;
		String seq =null;
		try{
		
		seq = String.valueOf(DatabaseExt.getNextSequence(key));		
		seq = code+date+com.eos.foundation.common.utils.StringUtil.leftPad(seq, "0", length);
		
		}catch(Exception e){
			seq = null;
		}
		return seq;
	}
	/**
	 * 取得当天的序列，每天序列从1开始重新计算
	 * @param length
	 * @return 定长的数字字符串
	 */
	@Bizlet(value = "每天生成序号", params = { @BizletParam(index = 0, defaultValue = "6", type = CONSTANT, paramAlias = "长度") })
	public static String getEveryDaySequence(int length) {
		Date day = new Date();	
		day.setTime(day.getTime() / ONEDAYSECONDS * ONEDAYSECONDS ) ;
		String key=DAY_SEQUENCE_KEY+ day.getTime();		
		String seq =null;
		try{	
		long iseq = SequenceGenerator.getNextSequence(key);
		if(iseq==1){//第一次获得当天的seq
//			移除昨天的key
			day.setTime(day.getTime()-ONEDAYSECONDS);
			String yestodaykey=DAY_SEQUENCE_KEY+ day.getTime();
//			删除缓存同时删除eos_unique_table中的数据
			SequenceGenerator.removeSequence(yestodaykey);
		}
		seq = String.valueOf(iseq);		
		seq =com.eos.foundation.common.utils.StringUtil.leftPad(seq, "0", length);		
		}catch(Exception e){
			seq = null;
			LogUtil.logError("生成序列出错！", e, (Object) null);
		}
		return seq;
	}
}
