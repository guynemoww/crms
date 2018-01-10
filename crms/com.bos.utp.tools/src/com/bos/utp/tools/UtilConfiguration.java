package com.bos.utp.tools;

import com.bos.pub.ServiceModuleName;

/**
 * 
 * Utility构件包配置常量定义
 * 
 * @author 翁增仁 wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史 $Log: UtilConfiguration.java,v $ Revision 1.5 2010/12/01 03:22:41 caisy 更改编码为UTF-8
 * 
 * Revision 1.4 2010/11/30 16:12:51 caisy 编码改为UTF-8
 * 
 * Revision 1.3 2009/03/30 05:39:38 caisy 代码规范
 * 
 * Revision 1.2 2009/01/07 07:04:14 xusl *** empty log message ***
 * 
 * Revision 1.1 2009/01/07 06:52:12 liuxiang *** empty log message ***
 * 
 * Revision 1.1 2009/01/05 02:34:56 caisy
 * 
 * Revision 1.2 2008/12/01 09:05:17 wengzr Update:增加EXCEL_EXPORT_MAXNUM配置
 * 
 * Revision 1.1 2008/11/28 04:03:02 wengzr Added:增加系统信息工具类SystemInfo Refactor:将Excel工具类从customize移入到utils
 * 
 * Revision 1.1 2008/11/12 14:41:55 wengzr Added:utility构件包名称修改为com.bos.utp.tools
 * 
 * Revision 1.1 2008/10/07 09:25:48 wengzr *** empty log message ***
 * 
 * Revision 1.1 2008/09/17 09:38:31 wengzr Update:重构常量定义
 */
public interface UtilConfiguration {

	public static final String CONTRIBUTION_ABFRAME_UTILS = ServiceModuleName.TOOLS;

	public static final String MODULE_ABFRAME = "abframe-config";

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
