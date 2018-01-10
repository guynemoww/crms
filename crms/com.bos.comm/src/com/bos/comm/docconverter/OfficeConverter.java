/**
 * 
 */
package com.bos.comm.docconverter;

import com.eos.system.annotation.Bizlet;

/**
 * @author Sunny
 * @date 2014-06-10 18:02:55
 *
 */
@Bizlet("office文档转换工具")
public class OfficeConverter {
	/**
	 * @param path 文件路径
	 * @return
	 * @author Sunny
	 */
	@Bizlet("转换word文档")
	public static String docConverter(String path) {
		// TODO 自动生成方法存根
		DocConverter d = new DocConverter(path);
		System.out.println("path:\t" + path);
		//调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;  
		d.conver();
		String swfpath=d.getswfPath();
		//调用getswfPath()方法，打印转换后的swf文件路径  
		System.out.println("swf文件路径" + swfpath);
		//生成swf相对路径，以便传递给flexpaper播放器  
		//swfpath = saveDirectory + "upload"
				//+ swfpath.substring(swfpath.lastIndexOf("/"));
		System.out.println("swfpath" + swfpath);
		FileName fileName= new FileName();
//		String temp = fileName.filenmae(swfpath);
		return fileName.filenmae(swfpath);
	}
	
	/**
	 * @param path 文件路径
	 * @return
	 * @author Sunny
	 */
	@Bizlet("转换xml文档")
	public static String pdfConverter(String path) {
		// TODO 自动生成方法存根
		DocConverter d = new DocConverter(path);
		System.out.println("path:\t" + path);
		//调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;  
		d.converPDF();
		String swfpath=d.getswfPath();
		//调用getswfPath()方法，打印转换后的swf文件路径  
		System.out.println("swf文件路径" + swfpath);
		//生成swf相对路径，以便传递给flexpaper播放器  
		//swfpath = saveDirectory + "upload"
				//+ swfpath.substring(swfpath.lastIndexOf("/"));
		System.out.println("swfpath" + swfpath);
		FileName fileName= new FileName();
//		String temp = fileName.filenmae(swfpath);
		return fileName.filenmae(swfpath);
	}
}
