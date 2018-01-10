/**
 * 
 */
package com.primeton.example.excel;

import com.bos.pub.ServiceModuleName;
import com.eos.system.annotation.Bizlet;

@Bizlet("")
public class UtilConfiguration {
	public static final String CONTRIBUTION_ABFRAME_UTILS = ServiceModuleName.PUB;
	public static final String MODULE_ABFRAME = "example-config";
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
