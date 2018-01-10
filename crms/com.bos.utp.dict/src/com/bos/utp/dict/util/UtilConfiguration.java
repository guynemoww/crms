package com.bos.utp.dict.util;

import com.bos.pub.ServiceModuleName;

/**
 * 
 * Utility构件包配置常量定义
 * 
 * @author 翁增仁 wengzr (mailto:wengzr@primeton.com)
 */
public interface UtilConfiguration {

	public static final String CONTRIBUTION_DICT_UTILS = ServiceModuleName.DICT;

	public static final String MODULE_DICT = "dict-config";

	public static final String GROUP_EXCEL = "excel-config";

	/**
	 * EXCEL模板路径
	 */
	public static final String EXCEL_TEMPLATE_PATH = "excel_template_path";

	/**
	 * 导出EXCEL最大行数
	 */
	public static final String EXCEL_EXPORT_MAXNUM = "excel_export_maxnum";

}
