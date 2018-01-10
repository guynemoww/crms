/**
 * 
 */
package com.bos.pub;


import java.io.IOException;
import java.util.Map;

import com.bos.pub.document.utils.WordUtils;
import com.eos.system.annotation.Bizlet;

import freemarker.template.TemplateException;

/**
 * @author Sunny
 * @date 2014-04-01 10:06:16
 *
 */
@Bizlet("生成Word文件")
public class WordFileWriter {

	/**
	 * @param args
	 * @author Sunny
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根

	}

	/**
	 * @return
	 * @author Sunny
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	@Bizlet("根据数据对象和模版生成word文件到服务器临时文档文件夹，并返回服务器上的文件地址")
	public static String writWordFile(Map<String,Object> dataMap,String templateName) throws IOException, TemplateException {
		// TODO 自动生成方法存根
		WordUtils wordUtils = new WordUtils();
    	return wordUtils.createWord(dataMap,templateName);
	}

}
