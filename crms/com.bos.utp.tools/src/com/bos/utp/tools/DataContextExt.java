package com.bos.utp.tools;

import static com.eos.system.annotation.ParamType.CONSTANT;

import java.util.List;

import com.bos.pub.exception.ParamEmptyException;
import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.IDataContext;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.annotation.BizletParam;
import commonj.sdo.DataObject;
import commonj.sdo.Property;

/**
 * 数据上下文的相关函数
 * 1、根据属性名获取数据对象数组中的属性值列表
 * 
 * @author 蔡述尧
 * @date 2009-02-21 19:27:16
 */
/*
 * 修改历史
 * $Log: DataContextExt.java,v $
 * Revision 1.3  2010/12/01 03:22:41  caisy
 * 更改编码为UTF-8
 *
 * Revision 1.2  2010/11/30 16:12:51  caisy
 * 编码改为UTF-8
 *
 * Revision 1.1  2009/02/22 19:39:53  caisy
 * 分级授权
 * 
 */
@Bizlet("数据上下文扩展类")
public class DataContextExt {

	/**
	 * @param list 数组xapth
	 * @param propertyName 属性名
	 * @return
	 * @author 蔡述尧
	 */
	@Bizlet(value = "抽取对象数组的属性数组", params = { @BizletParam(index = 0, defaultValue = "list"), @BizletParam(index = 1, defaultValue = "property", type = CONSTANT) })
	public static String[] getPropertyValues(String list, String propertyName) {
		if (null == list)  return null ;

		IDataContext ctx=DataContextManager.current().getDefaultContext();

		DataObject[] dataObjects=(DataObject[])ctx.get(list);
		return getPropertyValues(dataObjects, propertyName) ;
	}

	/**
	 * @param list 数据对象数组
	 * @param propertyName 属性名
	 * @return
	 */
	@Bizlet(value = "抽取对象数组的属性数组", params = { @BizletParam(index = 0, defaultValue = "list"), @BizletParam(index = 1, defaultValue = "property", type = CONSTANT) })
	public static String[] getPropertyValues(DataObject[] list, String propertyName) {
		if(null==list ||list.length==0){
			return null;
		}
		String [] ret = new String [list.length];
		for(int i=0;i<list.length;i++){
			ret[i] = list[i].getString(propertyName);
		}
		return ret;
	}
	@Bizlet("判断dataObject是否为空")
	public static boolean isEmpty(DataObject param) {
		List list = param.getInstanceProperties();
		boolean flag = true;
		for (Object obj : list) {
			if (obj instanceof Property == false)
				continue;

			if (param.isSet((Property) obj)
					&& false == "_entity".equals(((Property) obj).getName())) {
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

	@Bizlet("根据模板查询列表")
	public static DataObject[] queryEntitiesByTemplate(DataObject param)
			throws ParamEmptyException {
		DataObject[] arr = new DataObject[0];
		if (null == param)
			return arr;

		if (isEmpty(param)) {
			throw new ParamEmptyException(
					"queryEntitiesByTemplate中，查询模板的属性不能全部为空");
		}
		arr = DatabaseUtil.queryEntitiesByTemplate("default", param);

		return arr;
	}

}
