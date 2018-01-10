/**
 * 
 */
package com.primeton.example.excel;


import com.bos.utp.tools.SystemInfo;
import com.eos.system.annotation.Bizlet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.eos.foundation.common.io.FileUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.runtime.core.ApplicationContext;
import com.eos.system.annotation.Bizlet;
import com.eos.system.annotation.BizletParam;
import com.eos.system.annotation.ParamType;
import commonj.sdo.DataObject;
/**
*
* Excel<BR>
*
* @author 
* 
*/

@Bizlet("")
public class ExcelUtil {
	private ExcelUtil(){
		  //工具类不允许实例化
		 }

		 /**
		  * 将Excel数据导入到数据库指定的表，默认每500条数据执行一次批处理导入
		  *
		  * @param excelFile Excel文件名
		  * @param entityFullName 导入的实体全名
		  * @return
		  * @throws Exception
		  */
		 @Bizlet(
		  value="将Excel数据导入到数据库指定的表",
		  params = {
		   @BizletParam(index = 0, paramAlias = "excelFile",type=ParamType.CONSTANT),
		         @BizletParam(index = 1, paramAlias = "entityFullName",type=ParamType.CONSTANT)
		    }
		 )
		 public static int importExcel(String excelFile,String entityFullName)throws Exception{
		  ExcelTemplate template=new ExcelTemplate();
		  return template.importData(excelFile, entityFullName, 500);
		 }
		 
		 /**
		  * 将Excel数据获取
		  *
		  * @param excelFile Excel文件名
		  * @param entityFullName 导入的实体全名
		  * @return
		  * @throws Exception
		  */
		 @Bizlet(
		  value="将Excel数据导入到数据库指定的表",
		  params = {
		   @BizletParam(index = 0, paramAlias = "excelFile",type=ParamType.CONSTANT),
		         @BizletParam(index = 1, paramAlias = "entityFullName",type=ParamType.CONSTANT)
		    }
		 )
		 public static List getEntityData(String excelFile,String entityFullName)throws Exception{
		  ExcelTemplate template=new ExcelTemplate();
		  return template.entityData(excelFile, entityFullName, 500);
		 }
		 /**
		  * 将Excel数据获取
		  *
		  * @param excelFile Excel文件名
		  * @param entityFullName 导入的实体全名
		  * @return
		  * @throws Exception
		  */
		 @Bizlet(
				 value="将Excel数据导入到数据库指定的表",
				 params = {
						 @BizletParam(index = 0, paramAlias = "excelFile",type=ParamType.CONSTANT),
						 @BizletParam(index = 1, paramAlias = "entityFullName",type=ParamType.CONSTANT)
				 }
				 )
		 public static List getEntityData1(String excelFile,String entityFullName)throws Exception{
			 ExcelTemplate template=new ExcelTemplate();
			 return template.entityData1(excelFile, entityFullName, 500);
		 }
		 /**
		  * 
		  * @param empList 数据表中的数据
		  * @param entityList excel中获取的数据实体
		  * @return  成功标识 1代表成功
		  */
		 @Bizlet(
		  value="将Excel数据导入到数据库指定的表",
		  params = {
		   @BizletParam(index = 0, paramAlias = "excelFile",type=ParamType.CONSTANT),
		         @BizletParam(index = 1, paramAlias = "entityFullName",type=ParamType.CONSTANT)
		    }
		 )
		 public static int importExcelAgain(DataObject[] empList,DataObject[] entityList){
			  ExcelTemplate template=new ExcelTemplate();
			 return template.impDataAgain(empList, entityList, 500);
		 }
		 
		 
		 /**
		  * 
		  * @param empList 数据表中的数据
		  * @param entityList excel中获取的数据实体
		  * @return  成功标识 1代表成功
		  */
		 @Bizlet(
		  value="将Excel数据导入到数据库指定的表",
		  params = {
		   @BizletParam(index = 0, paramAlias = "excelFile",type=ParamType.CONSTANT),
		         @BizletParam(index = 1, paramAlias = "entityFullName",type=ParamType.CONSTANT)
		    }
		 )
		 public static int importEmpExcelAgain(DataObject[] empList,DataObject[] entityList){
			  ExcelTemplate template=new ExcelTemplate();
			 return template.impEmpDataAgain(empList, entityList, 500);
		 }
		 
		 @Bizlet("上传文件")
		 public void uploadFile(String upFile_path){
				String templateDir=SystemInfo.APP_WAR_PATH+"document/xlsx/otherFile";
			 FileOutputStream fos;
			try {
				fos = new FileOutputStream(templateDir + "\\"
							+upFile_path);
//				 fos.write(); // 写入
				fos.flush();// 释放
				fos.close(); // 关闭
			} catch (FileNotFoundException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} // 创建输出流
 catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
				
		 }
		 /**
		  * 将指定的对象数组exportObjects导出到指定模板的Excel文件
		  *
		  * @param exportObjects 待导出的对象数组
		  * @param exportInfo  模板文件的其他附加信息(非结果集内容)
		  * @param templateFilename 模板文件名(不带扩展名),对应到在user-config.xml配置路径下的模板文件
		  * @return 返回生成的Excel文件下载路径
		  * @throws Exception
		  */
		 @Bizlet(
		  value="将指定的对象数组导出到指定模板的Excel文件",
		  params = {
		   @BizletParam(index = 0, paramAlias = "exportObjects",type=ParamType.VARIABLE),
		         @BizletParam(index = 1, paramAlias = "exportInfo",type=ParamType.VARIABLE),
		         @BizletParam(index = 2, paramAlias = "templateFilename",type=ParamType.CONSTANT)
		     }
		 )
		 public static String exportExcel(DataObject[] exportObjects,DataObject exportInfo,String templateFilename) throws Exception{
		  return exportExcel(exportObjects,exportInfo,templateFilename,false,false);
		 }
		 /**
		  * 分页将对象数组导出到指定的模板Excel文件,注意：此时模板文件必需包含Excel表格的分页符
		  * @param exportObjects 待导出的对象数组
		  * @param exportInfo  模板文件的其他附加信息(非结果集内容)
		  * @param templateFilename 模板文件名(不带扩展名),对应到在user-config.xml配置路径下的模板文件
		  * @return 返回生成的Excel文件下载路径
		  * @throws Exception
		  */
		 @Bizlet(
		  value="分页将对象数组导出到指定的模板Excel文件",
		  params = {
		   @BizletParam(index = 0, paramAlias = "exportObjects",type=ParamType.VARIABLE),
		         @BizletParam(index = 1, paramAlias = "exportInfo",type=ParamType.VARIABLE),
		         @BizletParam(index = 2, paramAlias = "templateFilename",type=ParamType.CONSTANT)
		     }
		 )
		 public static String exportExcelWithPagnation(DataObject[] exportObjects,DataObject exportInfo,String templateFilename)
		throws Exception{
		  return exportExcel(exportObjects,exportInfo,templateFilename,true,false);
		 }
		 /**
		  * 分工作表将对象数组导出到指定的模板Excel文件，默认情况下输出工作表最大行:20000
		  * @param exportObjects 待导出的对象数组
		  * @param exportInfo  模板文件的其他附加信息(非结果集内容)
		  * @param templateFilename 模板文件名(不带扩展名),对应到在user-config.xml配置路径下的模板文件
		  * @return 返回生成的Excel文件下载路径
		  * @throws Exception
		  */
		 @Bizlet(
		  value="分工作表将对象数组导出到指定的模板Excel文件",
		  params = {
		   @BizletParam(index = 0, paramAlias = "exportObjects",type=ParamType.VARIABLE),
		         @BizletParam(index = 1, paramAlias = "exportInfo",type=ParamType.VARIABLE),
		         @BizletParam(index = 2, paramAlias = "templateFilename",type=ParamType.CONSTANT)
		     }
		 )
		 public static String exportExcelWithSheet(DataObject[] exportObjects,DataObject exportInfo,String templateFilename)
		throws Exception{
		  return exportExcel(exportObjects,exportInfo,templateFilename,false,true);
		 }
		 /**
		  * 导出Excel文件,根据指定路径下的模板生成输出的Excel文件
		  *
		  * @param exportObjects 待导出的对象数组
		  * @param exportInfo 模板文件的其他附加信息(非结果集内容)
		  * @param templateFilename 模板文件名(不带扩展名),对应到在user-config.xml配置路径下的模板文件
		  * @param autoPagination 是否分页
		  * @param autoSheet 是否分工作表
		  * @return 返回生成的Excel文件下载路径
		  * @throws Exception
		  */
		 private static String exportExcel(DataObject[] exportObjects,DataObject exportInfo,String templateFilename,
		boolean autoPagination,boolean autoSheet) throws Exception{
		  String filename=templateFilename;
		  if(filename.indexOf(".xls")==-1){
		   filename+=".xls";
		  }
		  //临时路径是服务器当前war下面的excel-config目录
		  String templateDir=ApplicationContext.getInstance().getWarRealPath()+ConfigurationUtil.getContributionConfig
		(UtilConfiguration.CONTRIBUTION_ABFRAME_UTILS,
		    UtilConfiguration.MODULE_ABFRAME,
		    UtilConfiguration.GROUP_EXCEL,
		    UtilConfiguration.EXCEL_TEMPLATE_PATH);
		 // System.out.println("路径="+templateDir);
		  String excelExportMaxnum=ConfigurationUtil.getContributionConfig(UtilConfiguration.CONTRIBUTION_ABFRAME_UTILS,
		    UtilConfiguration.MODULE_ABFRAME,
		    UtilConfiguration.GROUP_EXCEL,
		    UtilConfiguration.EXCEL_EXPORT_MAXNUM);
		  
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
		  String templateFile=templateDir+filename;
		  String outputFile=tempDir+generateOutputExcelFile(filename);
		  ExcelTemplate template=new ExcelTemplate(templateFile,outputFile);
		  template.setAutoPagination(autoPagination);
		  template.setAutoSheet(autoSheet);
		  int excelExportMaxnumInt = 0;
		  try{
		   excelExportMaxnumInt = Integer.parseInt(excelExportMaxnum);
		  }catch (Exception e){
		   e.printStackTrace();
		  }
		  template.setMaxRow(excelExportMaxnumInt);
		  template.generate(Arrays.asList(exportObjects),exportInfo);
		  return outputFile;
		 }
		 /**
		  * 生成EXCEL输出文件，默认带时间戳
		  * @param templateFilename 文件名
		  * @return
		  */
		 private static String generateOutputExcelFile(String templateFilename){
		  String filename=templateFilename;
		  if(templateFilename.endsWith(".xls")){
		   filename=templateFilename.substring(0,templateFilename.length()-4);
		  }
		  SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		  String datetimeString=format.format(new Date());
		  filename=filename+"_"+datetimeString+".xls";
		  return filename;
		 }

	}
