package com.bos.comm.docconverter;

import java.io.File;

/**
 * 获取文件名称
 * 
 * @author Administrator
 * 
 */
public class FileName {
	/**
	 * 获取已经完成转换的文件名称用于进行预览
	 * 
	 * @param path
	 * @return
	 */
	public String filenmae(String path) {
		String filename = "";
		if (null != path && path != "" && path.length() > 0) {
			File tempfile = new File(path.trim());
			filename = tempfile.getName();
			System.out.println("FileName:\t" + filename);
		}
		return filename;
	}
}
