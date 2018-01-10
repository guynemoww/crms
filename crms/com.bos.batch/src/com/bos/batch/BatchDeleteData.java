package com.bos.batch;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;

/**
  对共享盘中备份的数据进行清理（只保留7天）
*/
@Bizlet("BatchDeleteData")
public class BatchDeleteData {
    
	private static final String SNDCCMS=File.separator+"crmsshare/CRMS/data/SND/CCMS"; //CCMS卸数备份文件夹
	private static final String SNDECIR=File.separator+"crmsshare/CRMS/data/SND/ECIR"; //ECIR卸数备份文件夹
	private static final String SNDIDP=File.separator+"crmsshare/CRMS/data/SND/IDP"; //IDP卸数备份文件夹
	private static final String SNDIDPFR=File.separator+"crmsshare/CRMS/data/SND/IDPFR"; //IDP卸数备份文件夹
	
	private static final String RECCCMS=File.separator+"crmsshare/CRMS/gtp/files/REC/CCMS"; //CCMS供数文件夹
	private static final String RECECIF=File.separator+"crmsshare/CRMS/gtp/files/REC/ECIF"; //ECIF供数文件夹
	private static final String RECEDW=File.separator+"crmsshare/CRMS/gtp/files/REC/EDW"; //EDW供数文件夹
	private static final String RECSHM=File.separator+"crmsshare/CRMS/gtp/files/REC/SHM"; //SHM供数文件夹

	private static final int SAVEDAY =7;  //保存天数
	
	@Bizlet("清除文件")
	public static void delete(){
		deleteFolderData();
		deleteFileData();
	 }

	@Bizlet("清除日期文件夹数据")
	public static void deleteFolderData(){
		
		List<String> list=deleteFileList();	
		for(int i=0;i<list.size();i++){
			deleteFile(list.get(i));
		}
	 }
	
	@Bizlet("直接清除文件数据")
	public static void deleteFileData(){
		//清除卸数日志
		deleteFile("dataDrawOutCCMS",File.separator+"crmsshare/CRMS/logs");
		deleteFile("dataDrawOutECIR",File.separator+"crmsshare/CRMS/logs");
		deleteFile("dataDrawOutIDP",File.separator+"crmsshare/CRMS/logs");
		deleteFile("dataDrawOutIDPbe",File.separator+"crmsshare/CRMS/logs");
		//清除导入日志
	    deleteFile("loadData",File.separator+"crmsshare/CRMS/logs");
	   //票据系统文件
	    deleteFile("CDHP",File.separator+"crmsshare/CRMS/gtp/files/REC/BMIS");
	    deleteFile("FKXX",File.separator+"crmsshare/CRMS/gtp/files/REC/BMIS");
	   //供应链
	    deleteFile("businessinfoscf",File.separator+"crmsshare/CRMS/gtp/files/REC/SCF");
	    //T24
	    deleteFile("CORERECONDOC",File.separator+"crmsshare/CRMS/gtp/files/REC/T24");
	 }
	
	
	@Bizlet("添加删除文件夹目录")
	public static List<String> deleteFileList(){
	    List<String> list=new ArrayList<String>();
		list.add(SNDCCMS);
		list.add(SNDECIR);
		list.add(SNDIDP);
		list.add(SNDIDPFR);
		list.add(RECCCMS);
		list.add(RECECIF);
		list.add(RECEDW);
		list.add(RECSHM);
		return list;
	}
	
	@Bizlet("读取文件夹，进行删除操作")
	public static void deleteFile(String path){
		
		File file=new File(path);
		boolean flag=file.exists();
		//判断文件夹是否存在
		if(flag==true){
		//获取文件夹格式全为数字为文件夹
		  String[] files=filterFile(file.list());
	   //对文件进行排序(升序)
		  Arrays.sort(files);
		  System.out.print(files.length);
		//保留7天的文件
		if(files.length>SAVEDAY){
			//判断执行几次循环
		    int f=files.length-SAVEDAY;
		    for(int i=0;i<f;i++){
		      
		     //获取日期文件夹目录
		      String  folderPath=path+File.separator+files[i]; 
			  LogUtil.logDebug("清除文件"+folderPath, null, null);
		     
			  File folderFile=new File(folderPath);   
		      try{
			    for(int j=0;j<folderFile.list().length;j++){
   		         //获取文件夹下文件路径，进行删除
	             String sonPath=folderPath+File.separator+folderFile.list()[j];
	             LogUtil.logDebug("清除子文件"+sonPath, null, null);
	             File sonFile=new File(sonPath);
	             sonFile.delete();
		         } 
			    folderFile.delete();  //删除日期文件夹
		       }
		        catch(Exception e){
		         LogUtil.logDebug("清除文件"+folderPath+"权限不够", null, null); 
		       }
			  }
	    }
	  }
	}
	
	
	@Bizlet("直接清除文件夹下文档")
	public static void deleteFile(String fileName,String folderName){
		
		LogUtil.logDebug("开始清除文档"+fileName+";路径"+folderName, null, null); 
		File file=new File(folderName);
		boolean flag=file.exists();
		LogUtil.logDebug("文件是否存在"+flag, null, null); 
		int fileNameLength=fileName.length();
		LogUtil.logDebug("文件长度"+fileNameLength, null, null);
		List<String> list=new ArrayList<String>();
		if(flag==true){
			String[] fileNames=file.list();
			LogUtil.logDebug("文件数量"+fileNames.length, null, null);
			//获取相关删除文件
			for(int j=0;j<fileNames.length;j++){
				LogUtil.logDebug("名称查找"+fileNames[j],null,null);
				//文件名称长度，必须大于传入类型长度
				if(fileNames[j].length()>fileNameLength){
				  String ss=fileNames[j].substring(0,fileNameLength);
				  LogUtil.logDebug("名称"+ss, null, null);
				  if(fileName.equals(ss)){
					list.add(fileNames[j]);
					LogUtil.logDebug("文件在"+fileNames[j], null, null); 
			     }	
				}
			}
			String[] fileNameArray=new String[list.size()];
			
			//转换为数组
			list.toArray(fileNameArray);
			
			if(fileNameArray.length>SAVEDAY){
			//排序，升序
			Arrays.sort(fileNameArray);
			for(int i=0;i<fileNameArray.length-SAVEDAY;i++){
		     String dataFileName=folderName+File.separator+fileNameArray[i];
		     LogUtil.logDebug("删除文件"+dataFileName, null, null); 
			 try{	
				File dataFile=new File(dataFileName);
				//删除文件
				dataFile.delete();
				 LogUtil.logDebug("清除文件"+dataFileName+"成功", null, null); 
			   }catch(Exception e){
				LogUtil.logDebug("清除文件"+dataFileName+"权限不够", null, null); 	
			 }
			}
		  }
		}
	}
	
   @Bizlet("只保留含数字的数组元素")
   public static String[] filterFile(String[] files){
		boolean flag;
		List<String> list=new ArrayList<String>();
		for(int i=0;i<files.length;i++){
			 flag=isNumeric(files[i]);
			 if(flag==true){
				list.add(files[i]); 
			 } 
		}
	
		String[] stringFiles=(String[]) list.toArray(new String[list.size()]);
		return stringFiles;
	 }
	
	@Bizlet("判断字符串是否都是数字")
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		boolean flag=isNum.matches();
		if (flag==false) {
			return false;
		}
		 else
		{
		   return true;
		}
	}

	public static void main(String[] args) {
		deleteFile("E:\\crms");
	}

	
}
