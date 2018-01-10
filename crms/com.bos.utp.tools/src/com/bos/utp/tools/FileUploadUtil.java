package com.bos.utp.tools;

import java.io.File;


import com.eos.foundation.common.utils.FileUtil;
import com.eos.runtime.core.ApplicationContext;
import com.eos.system.annotation.Bizlet;
import com.eos.system.utility.StringUtil;
import com.primeton.ext.access.http.IUploadFile;

/**
 * 
 * 文件上传工具类
 *
 * @author 蔡述尧 
 * (mailto:caisy@primeton.com)
 */
/*
 * 修改历史
 * $Log: FileUploadUtil.java,v $
 * Revision 1.4  2010/12/01 03:22:41  caisy
 * 更改编码为UTF-8
 *
 * Revision 1.3  2010/11/30 16:12:51  caisy
 * 编码改为UTF-8
 *
 * Revision 1.2  2009/03/30 08:33:29  caisy
 * 代码规范
 *
 */
@Bizlet(value="文件上传工具类")
public class FileUploadUtil {
	
	
	@Bizlet(value="移动上传文件到指定的分类目录")
	public static void moveUploadFileToCatalog(String fileCatalog,IUploadFile[] uploadFiles){
		
		if(StringUtil.isNullOrBlank(fileCatalog))
			return ;
		
		String uploadPath=ApplicationContext.getInstance().getApplicationWorkPath()+SystemInfo.FILE_SEPARATOR+"upload";
		String catalogPath=uploadPath+SystemInfo.FILE_SEPARATOR+fileCatalog;
		
		/**
		 * 洪文弟2013-07-05，按上海银行安全检查增加
		 */
		if (catalogPath.indexOf("..") > 0) {
			catalogPath = "";
		}
		File catalogDir=new File(catalogPath);
		if(!catalogDir.exists()){
			catalogDir.mkdir();
		}
		for(IUploadFile uploadFile:uploadFiles){
			File moveFile=new File(uploadFile.getFilePath());
			if(moveFile.exists()){
				
				/**
				 * 洪文弟2013-07-05，按上海银行安全检查增加
				 */
				String targetPath = catalogPath+SystemInfo.FILE_SEPARATOR+getFileName(uploadFile.getFilePath()) ;
				if (targetPath.indexOf("..") > 0) {
					targetPath = "";
				}
				File targetFile=new File(targetPath);
				if(targetFile.exists()){
					targetFile.delete();
				}
				FileUtil.moveFileToDir(uploadFile.getFilePath(), catalogPath);
			}
		}
	}
	
	@Bizlet(value="判断文件或目录是否存在")
	public static boolean existFile(String filePath){
		return new File(filePath).exists();
	}
	
	@Bizlet(value="获取分类文件路径")
	public static String getCatalogFilePath(String fileCatalog,String filePath){
		if(StringUtil.isNotNullAndBlank(fileCatalog)){
			int lastIndex=filePath.lastIndexOf(SystemInfo.FILE_SEPARATOR);
			String fileName=filePath.substring(lastIndex+1);
			return filePath.substring(0, lastIndex)+SystemInfo.FILE_SEPARATOR+fileCatalog+SystemInfo.FILE_SEPARATOR+fileName;
		}else{
			return filePath;
		}
	}
	
	private static String getFileName(String filePath){
		int lastIndex=filePath.lastIndexOf(SystemInfo.FILE_SEPARATOR);
		return filePath.substring(lastIndex+1);
	}
	
	public static void main(String[] args){
		String f="D:\\software\\primeton\\EOS6_LA2_1278\\eosserver\\working\\eos-default\\upload\\2a8181e61dd7d566011dd7f31ea20174.xls";
		String s="test";
		System.out.println(getCatalogFilePath(s,f));
	}

}
