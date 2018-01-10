package com.bos.pub.document.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bos.pub.GitUtil;
import com.bos.utp.tools.SystemInfo;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
  

@Bizlet("")
public class WordUtils {  
      
    private static Configuration configuration = null;  

    private static DataObject config = ConfigurationUtil
			.getUserConfigMultiValue("CustomConfig", "System", "templatePath",
					"CustomConfig", "System", "docTempPath");
      
    public WordUtils(){  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");
    }
    
    public static void main(String[] args) { 
    	
    	Scanner sc = new Scanner(System.in); 
    	System.out.println("输入红包总金额:"); 
    	System.out.println("输入长整型："+sc.nextDouble());  
    	
        /*try {
        	WordUtils wordUtils = new WordUtils();
        	System.out.println(wordUtils.createWord(getData(),"simple"));
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}  */
    }  
    @Bizlet("")
    public String createWord(Map<String,Object> dataMap,String templateName) throws IOException, TemplateException{
//      configuration.setClassForTemplateLoading(new WordUtils().getClass(), "/template/word");  //FTL文件所存在的位置
//    	String templatePath = "D:\\Workspaces\\EASYLOAN6.5\\document-lib\\src\\template\\word";
//    	String docTempPath = "d:\\home";
    	String templatePath="";
    	
    	if (new File(templatePath).isFile() == false) {
			if (templatePath.startsWith(SystemInfo.FILE_SEPARATOR) == false) {
				templatePath = SystemInfo.FILE_SEPARATOR + templatePath;
			}
			templatePath = SystemInfo.APP_WAR_PATH + "document"
					+ SystemInfo.FILE_SEPARATOR + "docx/loanNotice/" + templatePath;
		}
		
    	
    	String docTempPath = SystemInfo.APP_WAR_PATH + "document"
				+ SystemInfo.FILE_SEPARATOR + "docx/temp/";
    	
    	File templateFile = new File(templatePath);
    	
    	configuration.setDirectoryForTemplateLoading(templateFile);
        Template t = configuration.getTemplate(templateName+".xml");
        String outFilePath = docTempPath+"\\"+GitUtil.genUUIDString()+".doc";
        File outFile = new File(outFilePath);  
        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
        
        DataObject[] db=(DataObject[]) dataMap.get("obj");
        
        Map<String, Object> printMap = new HashMap<String, Object>();
        
        printMap.put("time", dataMap.get("time"));
        printMap.put("userName", dataMap.get("userName"));
        
        
        //构造打印数据
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(); 
        for(int i=0;i<db.length;i++){
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("SUMMARY_NUM", db[i].getString("SUMMARY_NUM"));
        	map.put("PARTY_NAME", db[i].getString("PARTY_NAME"));
        	map.put("ZH", db[i].getString("ZH"));
        	map.put("ZHMC", db[i].getString("ZHMC"));
        	map.put("YEAR_RATE", db[i].getString("YEAR_RATE"));
        	map.put("CHBJ", db[i].getString("CHBJ"));
        	map.put("CHLX", db[i].getString("CHLX"));
        	map.put("CHFX", db[i].getString("CHFX"));
            list.add(map);
        }
        
        //ts表示需要遍历的集合名
        printMap.put("ts", list);
        try {
			t.process(printMap, out);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			if(null!=out){
				out.close();
			}
		}
        
        
        //将doc转换成docx
        
        
        return outFilePath;
    }
  
   /* private static Map<String,Object> getData() {  
    	Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("xingming", "小红");
        dataMap.put("dianhua", "18888888888");
		dataMap.put("nianling", "18");
		dataMap.put("xingbie", "美女"); 
          
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            Map<String,Object> map = new HashMap<String,Object>();  
            map.put("name", "王五-"+i);
    		map.put("tel", "8886666-"+i);
    		map.put("age", "18-"+i);
    		map.put("sex", "男-"+i);
            list.add(map);
        }  
        
        List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            Map<String,Object> map = new HashMap<String,Object>();  
            map.put("name", "张三-"+i);
    		map.put("tel", "8886666-"+i);
    		map.put("age", "18-"+i);
    		map.put("sex", "男-"+i);
            list.add(map);
        }  
          
        dataMap.put("list", list); 
        
        dataMap.put("list1", list1);  
        return dataMap;
    }  */
}  
