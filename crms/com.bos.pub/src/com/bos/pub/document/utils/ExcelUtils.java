package com.bos.pub.document.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author Sunny
 * 处理Excel文件工具类
 */
public class ExcelUtils {
	
	/**
	 * 读取Excel中的sheet
	 * @param fileIn
	 * @param clazz
	 * @return 返回sheet组成的Map
	 * @throws IOException
	 */
	public static Map<String,HSSFSheet> readDataFromExcelSheets(FileInputStream fileIn,Class clazz) throws IOException{
		Map<String,HSSFSheet> result = new HashMap<String,HSSFSheet>();
		POIFSFileSystem fs = new POIFSFileSystem(fileIn);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		int sheetsCount = wb.getNumberOfSheets();//文件中的sheet个数
		for (int i = 0; i < sheetsCount; i++) {//循环读取所有sheet页内容
			HSSFSheet sheet = wb.getSheetAt(i);//获得sheet对象
			String sheetName = sheet.getSheetName();
			result.put(sheetName, sheet);
		}
		return result;
	}
	
	/**
	 * 读取Excel文件的单个sheet
	 * @param sheet
	 * @return 返回HashMap
	 */
	public static Map<String,Object> readDataFromSheetForm(HSSFSheet sheet){
		int rowsCount = sheet.getLastRowNum();//sheet中的行数
		Map map = new HashMap();
		for (int j = 0; j < rowsCount; j++) {//循环读取每一行的信息
			HSSFRow row = sheet.getRow(j+1);//获得row对象
			if(null != row && row.getLastCellNum() !=0){
				int cellsCount = row.getLastCellNum();//每行单元格数
				for (int k = 0; k < cellsCount; k++) {//循环读取每一个单元格
					HSSFCell cell = row.getCell(k);
					if (cell != null && cell.getCellComment() != null) {
						map.put(cell.getCellComment().getString().getString(), getCellValue(cell));
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * 读取Excel文件的单个sheet
	 * @param sheet
	 * @param clazz
	 * @return 返回与clazz相同类型的对象
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ParseException
	 */
	public static Object readDataFromSheetForm(HSSFSheet sheet,Class clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, ParseException{
		Object obj = clazz.newInstance();
		int rowsCount = sheet.getLastRowNum();//sheet中的行数
		for (int j = 0; j < rowsCount; j++) {//循环读取每一行的信息
			HSSFRow row = sheet.getRow(j);//获得row对象
			int cellsCount = row.getLastCellNum();//每行单元格数
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				for (int k = 0; k < cellsCount; k++) {//循环读取每一个单元格
					HSSFCell cell = row.getCell(k);
					if (cell != null && cell.getCellComment() != null && f.getName().equalsIgnoreCase(cell.getCellComment().getString().getString())) {
						f.set(obj, getCellValueOfField(cell,f));
						break;
					}
				}
			}
		}
		return obj;
	}
	
	/**
	 * 读取excel文件返回ArrayList，内容格式为ArrayList[sheet1Map[key,value],sheet2Map[key,value],...]，此方法解析出来的数据有可能出现全部为空的情况，需要在使用解析结果时做相应的非空判断；
	 * @param fileIn
	 * @return
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ParseException 
	 * @throws InstantiationException 
	 */
	public static Map<String,Object> readDataFromExcelForm(FileInputStream fileIn,Class clazz) throws IOException, IllegalArgumentException, IllegalAccessException, ParseException, InstantiationException{
		Map<String,Object> result = new HashMap<String,Object>();
		Map map = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		fs = new POIFSFileSystem(fileIn);
		wb = new HSSFWorkbook(fs);
		int sheetsCount = wb.getNumberOfSheets();//文件中的sheet个数
		for (int i = 0; i < sheetsCount; i++) {//循环读取所有sheet页内容
			HSSFSheet sheet = wb.getSheetAt(i);//获得sheet对象
			if(clazz == null){
				map = new HashMap();
				map = readDataFromSheetForm(sheet);
				result.put(sheet.getSheetName(), map);
			}else{
				Object obj = readDataFromSheetForm(sheet,clazz);
				result.put(sheet.getSheetName(), obj);
			}
		}
		return result;
	}
	
	/**
	 * 根据模版信息解析excel文件中的数据，此方法主要针对数据列表进行解析-----此方法解析出来的数据有可能出现全部为空的情况，需要在使用解析结果时做相应的非空判断；
	 * @param fileIn	输入文件流
	 * @param clazz		需要格式化的对象，如果为null，则默认把每一行解析为map集合
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ParseException
	 */
	public static Map<String,Object> readDataFromExcelList(FileInputStream fileIn,Class clazz) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, ParseException{
		Map<String,Object> result = new HashMap<String,Object>();
		Map colMap = null;
		List dataList = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		fs = new POIFSFileSystem(fileIn);
		wb = new HSSFWorkbook(fs);
		int sheetsCount = wb.getNumberOfSheets();//文件中的sheet个数
		for (int i = 0; i < sheetsCount; i++) {//循环读取所有sheet页内容
			HSSFSheet sheet = wb.getSheetAt(i);//获得sheet对象
			dataList = new ArrayList();
			int rowsCount = sheet.getPhysicalNumberOfRows();//sheet中的行数
			colMap = new HashMap();//表格表头信息
			int headIndex = 0;
			for (int j = 0; j < rowsCount; j++) {//循环读取每一行的信息
				HSSFRow row = sheet.getRow(j);//获得row对象
				int cellsCount = row.getPhysicalNumberOfCells();//每行单元格数
				for (int k = 0; k < cellsCount; k++) {//循环读取每一个单元格
					HSSFCell cell = row.getCell(k);
					if (cell != null && cell.getCellComment() != null) {
						colMap.put(cell.getColumnIndex(),cell.getCellComment().getString().getString());
					}
				}
				if(colMap.size() > 0){
					headIndex = j;
					break;
				}
			}
			System.out.println("表头行数为【"+headIndex+"】，colMap:"+colMap);
			if(clazz == null){
				for (int j = headIndex+1; j < rowsCount; j++) {//循环读取每一行的信息
					HSSFRow row = sheet.getRow(j);//获得row对象
					int cellsCount = row.getLastCellNum();//每行单元格数
					Map dataMap = new HashMap();
					Set keySet = colMap.keySet();
					for (int k = 0; k < cellsCount; k++) {//循环读取每一个单元格
						HSSFCell cell = row.getCell(k);
						if(cell != null){
							dataMap.put(colMap.get(k), getCellValue(cell));
						}
					}
					dataList.add(dataMap);
				}
				result.put(sheet.getSheetName(), dataList);
			}else{
				for (int j = headIndex+1; j < rowsCount; j++) {//循环读取每一行的信息
					Object obj = clazz.newInstance();
					HSSFRow row = sheet.getRow(j);//获得row对象
					int cellsCount = row.getLastCellNum();//每行单元格数
					Field[] fields = clazz.getDeclaredFields();
					for (Field f : fields) {
						for (int k = 0; k < cellsCount; k++) {//循环读取每一个单元格
							HSSFCell cell = row.getCell(k);
							if (cell != null && f.getName().equalsIgnoreCase((String)colMap.get(k))) {
								f.set(obj, getCellValueOfField(cell,f));
								break;
							}
						}
					}
					dataList.add(obj);
				}
				result.put(sheet.getSheetName(), dataList);
			}
		}
		return result;
	}
	/**
	 * 根据方法获取与方法属性类型相同的值
	 * @param cell
	 * @param f
	 * @return
	 * @throws ParseException 
	 */
	private static Object getCellValueOfField(HSSFCell cell, Field f) throws ParseException {
		// TODO 自动生成方法存根
		if (f.getType() == Date.class) {
			if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				return cell.getDateCellValue();
			} else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
				String val = cell.getStringCellValue();
				if (val != null) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					return sdf.parse(cell.getStringCellValue());
				}
			}
		} else if (f.getType() == double.class) {
			if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				return cell.getNumericCellValue();
			} else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
				String val = cell.getStringCellValue();
				return Double.parseDouble(val);
			}
		} else if (f.getType() == int.class) {
			if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				return  (int) cell.getNumericCellValue();
			} else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
				String val = cell.getStringCellValue();
				if (val != null) {
					return Integer.parseInt(val);
				}else{
					return 0;
				}
			}else {
				return 0;
			}
		} else if (f.getType() == String.class) {
			return cell.getStringCellValue();
		} else {
			return cell.getStringCellValue();
		}
		return null;
	}

	
	/**
	 * 根据cell类型获取cell数据
	 * @param cell
	 * @return
	 */
	private static Object getCellValue(HSSFCell cell){
		Object obj;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			obj = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING:
			obj = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			int value = cell.getCachedFormulaResultType();
			if(HSSFCell.CELL_TYPE_STRING == value){
				obj = cell.getStringCellValue();
			}else if(HSSFCell.CELL_TYPE_NUMERIC == value){
				obj = cell.getNumericCellValue();
			}else if(HSSFCell.CELL_TYPE_BLANK == value){
				obj = null;
			}else{
				obj = cell.getStringCellValue();
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			obj = null;
			break;
		default:
			obj = cell.getStringCellValue();
			break;
		}
//		System.out.println("cell_comment:"+cell.getCellComment().getString().getString()+"----cell_type:"+cell.getCellType()+"----cell_value:"+obj+"----cell_value_type:"+obj.getClass().getName());
		return obj;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
//		String path = ExcelUtils.class.getResource("/template/execle").getPath();
		String path = "D:\\绵阳银行\\授权";
		System.out.println("模版路径："+path);
		//File file_form = new File(path+"/aa.xls");
		File file_list = new File(path+"/test.xls");
		try {
			//FileInputStream fis = new FileInputStream(file_form);
//			excelReader(fis);
//			Customer customer = new Customer();
			//System.out.println(readDataFromExcelList(fis,null));
			
			FileInputStream fis_list = new FileInputStream(file_list);
			System.out.println(readDataFromExcelList(fis_list,null));
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *获取利润表明细信息或者银行对账单汇总表
	 * @param sheet
	 * @return
	 */
	public static Map<String, Object> getExcellData(HSSFSheet sheet) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map colMap = null;
		List dataList = null;
		dataList = new ArrayList();
		int rowsCount = sheet.getPhysicalNumberOfRows();//sheet中的行数
		colMap = new HashMap();//表格表头信息
		//对账单汇总表账户信息
		Map<String,Object> billAccountMap = new HashMap<String, Object>();
		
		String sheetname = sheet.getSheetName();
		int headIndex = 0;
		for (int j = 0; j < rowsCount; j++) {//循环读取每一行的信息
			HSSFRow row = sheet.getRow(j);//获得row对象
			int cellsCount = row.getPhysicalNumberOfCells();//每行单元格数
			int cellBegin = row.getFirstCellNum();//数据表起始列
			for (int k = 0; k < cellsCount; k++) {//循环读取每一个单元格
				HSSFCell cell = row.getCell(k+cellBegin);
				/*if(cell != null && cell.getStringCellValue().endsWith("报")){
					result.put("financeType", cell.getStringCellValue());
				}else */
				if(sheetname.contains("银行对账单")){
					if(cell.getCellComment() != null){
						String cellComment = cell.getCellComment().getString().getString();
						if(cell != null && !("billKhh".equals(cellComment)||"billZhmc".equals(cellComment)||"billZh".equals(cellComment))){
							colMap.put(cell.getColumnIndex(),cell.getCellComment().getString().getString());
						}else{
							billAccountMap.put(cell.getCellComment().getString().getString(), cell.getStringCellValue());
						}
					}
				}else{
					if (cell != null && cell.getCellComment() != null) {
						colMap.put(cell.getColumnIndex(),cell.getCellComment().getString().getString());
					}
				}
			}
			if(colMap.size() > 0){
				headIndex = j;
				break;
			}
		}
		//System.out.println("表头行数为【"+headIndex+"】，colMap:"+colMap);
		for (int j = headIndex+1; j < rowsCount; j++) {//循环读取每一行的信息
			HSSFRow row = sheet.getRow(j);//获得row对象
			int cellsCount = row.getLastCellNum();//每行单元格数
			Map dataMap = new HashMap();
			Set keySet = colMap.keySet();
			for (int k = 0; k < cellsCount; k++) {//循环读取每一个单元格
				HSSFCell cell = row.getCell(k);
				if(cell != null && colMap.get(k)!=null){
					dataMap.put(colMap.get(k), getCellValue(cell));
				}
			}
			if(dataMap.keySet().size() != 0){
				dataList.add(dataMap);
			}
		}
		if(billAccountMap.keySet().size()!=0){
			dataList.add(billAccountMap);
		}
		result.put(sheet.getSheetName(), dataList);
		return result;
	}
}
