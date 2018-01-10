package com.bos.utp.tools;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IDataContext;
import com.eos.system.annotation.Bizlet;
import com.eos.system.annotation.BizletParam;
import com.eos.system.annotation.ParamType;
import commonj.sdo.DataObject;
import commonj.sdo.Property;

/**
 *
 * SDO数据对象扩展工具
 *
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: DataObjectExt.java,v $
 * Revision 1.4  2010/12/01 03:22:41  caisy
 * 更改编码为UTF-8
 *
 * Revision 1.3  2010/11/30 16:12:51  caisy
 * 编码改为UTF-8
 *
 * Revision 1.2  2009/03/30 05:39:38  caisy
 * 代码规范
 *
 * Revision 1.1  2009/01/07 06:52:12  liuxiang
 * *** empty log message ***
 *
 * Revision 1.1  2009/01/05 02:34:56  caisy
 *
 * Revision 1.7  2008/11/27 06:04:20  wengzr
 * Update:修复appendObject对Object[]转换的异常
 *
 * Revision 1.6  2008/11/26 13:55:05  wengzr
 * Update:修改appendObject方法,增加可变长的参数
 *
 * Revision 1.5  2008/11/26 09:16:21  wengzr
 * Added:添加cleanProperties清除对象的所有属性值
 *
 * Revision 1.4  2008/11/25 09:41:08  wengzr
 * Update:增加注释
 *
 * Revision 1.3  2008/11/24 10:47:48  wengzr
 * Added:增加unSetProperties运算构件方法
 *
 * Revision 1.2  2008/11/24 07:26:11  wengzr
 * Added:增加unSetProperties运算构件方法
 *
 * Revision 1.1  2008/11/17 11:18:12  wengzr
 * 提交CVS
 *
 * Revision 1.1  2008/11/12 14:35:23  wengzr
 * Update:修改DataObjectExtUtil->为DataObjectExt
 *
 * Revision 1.1  2008/10/07 09:25:49  wengzr
 * *** empty log message ***
 *
 * Revision 1.2  2008/09/11 09:59:07  wengzr
 * Update:修改获取操作员的所有角色实现,增加运算逻辑appendObject和getUniqueObjects
 *
 * Revision 1.1  2008/09/10 09:03:30  wengzr
 * Update:重构PermissionUtil,抽取数据对象到DataObjectExtUtil
 *
 */
@Bizlet("数据对象扩展运算逻辑")
public class DataObjectExt {

	private DataObjectExt(){
		//工具类不能实例化
	}
	
	/**
	 * 将变长的源数组内容添加到目标Xpath数组
	 * @param targetXpath 目标数组的xpath
	 * @param sourceXpath 变长的源数组Xpath
	 */
	@Bizlet(
		value="将变长的源数组内容添加到目标Xpath数组",
		params = {
			@BizletParam(index = 0, type = ParamType.CONSTANT,paramAlias="targetXpath"),
			@BizletParam(index=1,type=ParamType.CONSTANT,paramAlias="strings")
		}
	)
	public static void appendObject(String targetXpath,String...sourceXpath){
		for(String xpath:sourceXpath){
			appendObject(targetXpath,xpath);
		}
	}

	/**
	 * 将源数组添加到目标Xpath数组
	 * @param targetXpath 目标数组的xpath名称
	 * @param dataObjects 源对象数组
	 */
	private static void appendObject(String targetXpath,String sourceXpath){
		
		IDataContext context=DataContextManager.current().getDefaultContext();
		Object[] sourceObjects=convertObjectArray(context.get(sourceXpath));
		if(sourceObjects==null||sourceObjects.length==0)
			return ;

		Object[] targetObjects=convertObjectArray(context.get(targetXpath));
		if(targetObjects==null||targetObjects.length==0){
			targetObjects=new DataObject[sourceObjects.length];
			System.arraycopy(sourceObjects, 0, targetObjects, 0, sourceObjects.length);
		}else{
			//临时交换变量
			Object[] temp=new Object[targetObjects.length];
			System.arraycopy(targetObjects, 0, temp, 0, targetObjects.length);

			targetObjects=new Object[targetObjects.length+sourceObjects.length];
			System.arraycopy(temp, 0, targetObjects, 0, temp.length);
			System.arraycopy(sourceObjects, 0, targetObjects, temp.length, sourceObjects.length);
		}
		context.set(targetXpath, targetObjects);
	}

	
	private static Object[] convertObjectArray(Object object){
		Object[] target=null;
		if(object instanceof Collection){
			Collection<?> collection=(Collection)object;
			target=(Object[])collection.toArray(new Object[collection.size()]);
		}else if(object instanceof Object[]){
			target=(Object[])object;
		}
		return target;
	}

	/**
	 * 获取指定不重复属性值的数组
	 * @param xpath 目标数组的xpath
	 * @param propertyName 指定不重复的属性值
	 * @return 返回不重复属性值的对象数组
	 */
	@Bizlet(
		value="获取指定不重复属性值的数组",
		params = {
			@BizletParam(index = 0, type = ParamType.CONSTANT,paramAlias="xpath"),
			@BizletParam(index = 1, type = ParamType.CONSTANT,paramAlias="propertyName") 
		}
	)
	@SuppressWarnings("unchecked")
	public static void getUniqueObjects(String xpath,final String propertyName){
		IDataContext context=DataContextManager.current().getDefaultContext();
		
		Object[] target=convertObjectArray(context.get(xpath));

		if( target!=null && target.length>0) {

			List targetList=Arrays.asList(target);
			java.util.Collections.sort(targetList, new Comparator() {
				public int compare(final Object o1, final Object o2){
					final DataObject m1 = (DataObject)o1;
					final DataObject m2 = (DataObject)o2;
					if(m1.get(propertyName)!=null&&m2.get(propertyName)!=null){
						final String order1=m1.get(propertyName).toString();
						final String order2=m2.get(propertyName).toString();
						return order1.compareTo(order2);
					}
					return 0;

				}
			});

			Object value=null;
			List<DataObject> result=new ArrayList<DataObject>();
			for(Iterator it = targetList.iterator(); it.hasNext();) {
				DataObject obj=(DataObject)it.next();
				if(obj.get(propertyName)!=null&&obj.get(propertyName).equals(value)){
					//it.remove()不能使用，从Arrays.asList的数组是不可修改的!!!
				}else{
					result.add(obj);
					value=obj.get(propertyName);
				}
			}

			context.set(xpath,  (Object[])result.toArray(new Object[result.size()]));
		}



	}

	/**
	 * 向当前DataContext中的数组对象或者list对象添加DataObject对象<BR>
	 * 如果对应的PropertyName已经存在，则不添加;如果propertyName为null则不检查对象的属性是否重复
	 * @param xpath DataObject数组或者list所在xpath。
	 * @param dataObject 需要添加的DataObject对象。
	 * @param propertyName 检查DataObject对象实体名的属性值是否已经存在数组中。
	 */
	@SuppressWarnings("unchecked")
	@Bizlet(
		value="添加不重复的数据对象到目标Xpath数组",
		params = {
			@BizletParam(index = 0, type = ParamType.CONSTANT,paramAlias="xpath"),
			@BizletParam(index=1,type=ParamType.VARIABLE,paramAlias="dataObject"),
			@BizletParam(index = 2, type = ParamType.CONSTANT,paramAlias="propertyName") 
		}
	)
	public static void appendObject(String xpath, DataObject dataObject,String propertyName) {

		if (null == dataObject)
			return ;

		IDataContext ctx=DataContextManager.current().getDefaultContext();

		DataObject[] target=null;

		DataObject[] dataObjects=(DataObject[])ctx.get(xpath);

		if(propertyName!=null&&isDuplicateProperty(dataObjects,dataObject,propertyName)){
			return ;
		}

		int length = (dataObjects == null) ? 0 : Array.getLength(dataObjects);

		target = new DataObject[length + 1];
		for (int i = 0; i <length; i++)
			target[i] = (DataObject) Array.get(dataObjects, i);

		target[length] = dataObject;
		ctx.set(xpath, target);
	}

	/**
	 * 判断目标数组中的对象是否存在与源对象中相同的属性值
	 * @param targets 目标数组
	 * @param source 源对象
	 * @param propertyName 属性名
	 * @return 存在返回true,不存在返回false
	 */
	private static boolean isDuplicateProperty(DataObject[] targets,DataObject source,String propertyName){
		if(source==null||source.get(propertyName)==null)
			return false;

		if(targets!=null&&targets.length>0){
			for(int i=0;i<targets.length;i++){
				if(source.get(propertyName).equals(targets[i].get(propertyName))){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 不设置数据对象指定的属性值
	 * @param dataobject DataObject数据对象
	 * @param properties 属性名称动态数组
	 */
	@Bizlet(
		value="不设置数据对象指定的属性",
		params = {
			@BizletParam(index = 0, type = ParamType.VARIABLE,paramAlias="dataObject"),
			@BizletParam(index=1,type=ParamType.VARIABLE,paramAlias="properties")
		}
	)
	public static void unSetProperties(DataObject dataobject, String... properties){
		for(String propertyName:properties){
			dataobject.unset(propertyName);
		}
	}
	
	/**
	 * 不设置数据对象指定的属性,如果当前xpath的对象为集合或对象数组,则有相同属性的全部不设置属性值
	 * @param xpath 数据对象的xpath
	 * @param properties 属性名称动态数组
	 */
	@Bizlet(
		value="不设置数据对象指定的属性",
		params = {
			@BizletParam(index = 0, type = ParamType.CONSTANT,paramAlias="xpath"),
			@BizletParam(index=1,type=ParamType.VARIABLE,paramAlias="properties")
		}
	)
	public static void unSetProperties(String xpath,String... properties){
		IDataContext context=DataContextManager.current().getDefaultContext();
		Object obj=context.get(xpath);
		if(obj instanceof java.util.Collection){
			java.util.Collection<?> collection=(java.util.Collection)obj;
			context.set(xpath, unSetProperties((DataObject[])collection.toArray(new DataObject[collection.size()]),properties));
		}
		else if(obj instanceof DataObject[]){
			DataObject[] dataObjects=(DataObject[])obj;
			context.set(xpath, unSetProperties(dataObjects,properties));
		}else if(obj instanceof DataObject){
			DataObject dataObject=(DataObject)obj;
			for(String propertyName:properties)
				dataObject.unset(propertyName);
			context.set(xpath, obj);
		}
		
	}
	
	/**
	 * 清除数据对象的所有属性值
	 * @param dataObject DataObject对象
	 */
	@Bizlet(
		value="清除数据对象的所有属性值",
		params = {
			@BizletParam(index = 0, type = ParamType.VARIABLE,paramAlias="dataObject"),
		}
	)
	public static void cleanProperties(DataObject dataObject){
		Iterator properties=dataObject.getType().getProperties().iterator();
		while(properties.hasNext()){
			Property property=(Property)properties.next();
			dataObject.unset(property);
		}

	}
	
	
	private static DataObject[] unSetProperties(DataObject[] obj,String... properties){
		DataObject[] dataObjects=(DataObject[])obj;
		List<DataObject> result=new ArrayList<DataObject>();
		for(DataObject array:dataObjects){
			for(String propertyName:properties){
				array.unset(propertyName);
			}
			result.add(array);
		}
		return (DataObject[])result.toArray(new DataObject[result.size()]);
	}
}
