package com.bos.utp.dict.util;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.bos.utp.tools.SystemInfo;
import com.eos.foundation.common.io.FileUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.runtime.core.ApplicationContext;
import com.eos.system.annotation.Bizlet;
import com.eos.system.utility.StringUtil;
import commonj.sdo.DataObject;

/**
 * TODO fill class info here
 *
 * @author 蔡述尧
 * @date 2010-04-09 19:44:39
 */
/*
 * Modify history
 * $Log: DictUtil.java,v $
 * Revision 1.1  2010/12/26 22:23:04  caisy
 * 业务字典导入导出使用统一模板
 *
 * Revision 1.1.2.1  2010/04/28 10:23:45  caisy
 * 业务字典导入
 * 
 */
@Bizlet("字典导入导出工具")
public class DictUtil {

	/**
	 * @author 蔡述尧
	 * 
	 */
	@Bizlet("")
	public static String dictExport(DataObject[] dictdata,DataObject exportInfo) throws Exception{
		String templateFilename = "DictTemplate";
		return dictExport(dictdata,exportInfo, templateFilename);
   }
	/**
	 * @author 蔡述尧
	 * 
	 */
	public static String dictExport(DataObject[] dictdata,DataObject exportInfo,String templateFilename) throws Exception{
			ExcelTemplate2 template=getTemplateFile(templateFilename,generateOutputExcelFileName(templateFilename));
	        template.initialize();
	        if(template.getStartRow()==-1)
	        	return null ;
	        
	        //先填充标题
	        if(exportInfo!=null)
	        	template.generateTitleDatas(exportInfo);
	        //生成数据内容
	            template.generateDictDatas(Arrays.asList(dictdata));
	            template.writeToFile();
			return template.getOutputFile();
		
	}

	/**
	 * @author 蔡述尧
	 * @throws Exception 
	 * 
	 */
	@Bizlet("")
	public static int dictImport(String excelFile) throws Exception {
		String templateFilename = "DictTemplate";
		ExcelTemplate2 template=getImportTemplateFile(templateFilename);
		template.initialize();
		return template.importDictData(excelFile, 10);
	}
	/**
	 * 生成EXCEL输出文件，默认带时间戳
	 * @param templateFilename 文件名
	 * @return
	 */
	private static String generateOutputExcelFileName(String templateFilename){
		String filename=templateFilename;
		if(templateFilename.endsWith(".xls")){
			filename=templateFilename.substring(0,templateFilename.length()-4);
		}
		if (filename.indexOf("/") < 0) {
			String tempDir = SystemInfo.APP_WAR_PATH + "document/xlsx/temp/";
			File file = new File(tempDir);
			if (!file.exists()) {
				// 创建临时目录
				try {
					FileUtil.mkDir(tempDir);
				} catch (Exception e) {
				}
			}
			filename = tempDir + filename;
		}

		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String datetimeString=format.format(new Date());

		filename=filename+"_"+datetimeString+".xls";
		return filename;
	}
	
	/**
	 * 生成导入的模板
	 * @param templateName
	 * @param inoutfile
	 * @return
	 * @throws Exception
	 */
	private static ExcelTemplate2 getImportTemplateFile(String templateName) throws Exception {
		return getTemplateFile(templateName,null);
	}
	/**
	 * 生成模板
	 * @param templateName
	 * @param inoutfile
	 * @return
	 * @throws Exception
	 */
	private static ExcelTemplate2 getTemplateFile(String templateName,String inoutfile) throws Exception {
		if(templateName.indexOf(".xls")==-1){
			templateName+=".xls";
		}

		//临时路径是服务器当前war下面的excel-config目录
//		String templateDir=ApplicationContext.getInstance().getWarRealPath()+ConfigurationUtil.getContributionConfig(UtilConfiguration.CONTRIBUTION_DICT_UTILS,
//				UtilConfiguration.MODULE_DICT,
//				UtilConfiguration.GROUP_EXCEL,
//				UtilConfiguration.EXCEL_TEMPLATE_PATH);
		String templateDir=SystemInfo.APP_WAR_PATH+"document/xlsx/";
		
		if(!templateDir.endsWith("/")){
			templateDir+="/";
		}
		String tempDir=templateDir+"temp/";
		File file=new File(tempDir);
		if(!file.exists()){
			//创建临时目录
			FileUtil.mkDir(tempDir);
			//file.createNewFile();
		}
		String templateFile=templateDir+templateName;
		if(StringUtil.isNullOrBlank(inoutfile)){
		    inoutfile=tempDir+generateOutputExcelFileName(templateName);
		}
		return new ExcelTemplate2(templateFile,inoutfile);
	}
}
