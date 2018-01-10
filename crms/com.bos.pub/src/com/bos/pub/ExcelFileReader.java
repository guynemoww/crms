/**
 * 
 */
package com.bos.pub;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.bos.pub.document.utils.ExcelUtils;
import com.bos.pub.exception.ParamEmptyException;
import com.eos.system.annotation.Bizlet;

/**
 * @author Sunny
 * @date 2014-03-31 15:02:12
 *
 */
@Bizlet("读取Excel文件")
public class ExcelFileReader {

	/**
	 * @param args
	 * @author Sunny
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根

	}

	/**
	 * @param filePath
	 * @param fileName
	 * @return
	 * @author Sunny
	 * @throws InstantiationException 
	 * @throws ParseException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
	@Bizlet("读取指定路径下的Excel文件，返回HashMap")
	public static Map readExcelFile(String filePath, String fileName) throws IllegalArgumentException, IOException, IllegalAccessException, ParseException, InstantiationException {
		// TODO 自动生成方法存根
		if(filePath == null || "".equals(filePath)){
			throw new FileNotFoundException("文件路径["+filePath+"]不能为空，请检查！");
		}
		File file = null;
		if(fileName == null || "".equals(fileName)){
			file = new File(filePath+"/"+fileName);
		}else{
			file = new File(filePath+"/"+fileName);
		}
		FileInputStream fis = new FileInputStream(file);
		return ExcelUtils.readDataFromExcelForm(fis, null);
	}
	
	/**
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 * @throws InstantiationException
	 */
	@Bizlet("读取指定路径下的Excel文件，返回sheet组成的HashMap")
	public static Map<String,HSSFSheet> readExcelSheets(String filePath,String fileName) throws IllegalArgumentException, IOException, IllegalAccessException, ParseException, InstantiationException {
		if(filePath == null || "".equals(filePath)){
			throw new FileNotFoundException("文件路径["+filePath+"]不能为空，请检查！");
		}
		File file = null;
		if(fileName == null || "".equals(fileName)){
			file = new File(filePath);
		}else{
			file = new File(filePath+"/"+fileName);
		}
		FileInputStream fis = new FileInputStream(file);
		return ExcelUtils.readDataFromExcelSheets(fis, null);
	}
	
	/**
	 * 根据sheet解析出每个Sheet的HashMap
	 * @param sheet
	 * @return
	 * @throws ParamEmptyException
	 */
	@Bizlet("根据传入的sheet对象解析出HashMap")
	public static Map<String,Object> readSheetForm(HSSFSheet sheet) throws ParamEmptyException{
		if(sheet == null){
			throw new ParamEmptyException("需要解析的sheet对象不能为空，请检查！");
		}
		return ExcelUtils.readDataFromSheetForm(sheet);
	}
	
	/**
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 * @throws InstantiationException
	 */
	@Bizlet("读取指定路径下的Excel文件，返回sheet组成的HashMap")
	public static Map<String,Object> readExcelList(String filePath,String fileName) throws IllegalArgumentException, IOException, IllegalAccessException, ParseException, InstantiationException {
		if(filePath == null || "".equals(filePath)){
			throw new FileNotFoundException("文件路径["+filePath+"]不能为空，请检查！");
		}
		File file = null;
		if(fileName == null || "".equals(fileName)){
			file = new File(filePath);
		}else{
			file = new File(filePath+"/"+fileName);
		}
		FileInputStream fis = new FileInputStream(file);
		return ExcelUtils.readDataFromExcelList(fis, null);
	}

}
