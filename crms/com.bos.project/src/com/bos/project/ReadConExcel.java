/**
 * 
 */
package com.bos.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.eos.system.annotation.Bizlet;


/**
 * @author chenchuan
 * @date 2015-09-09 13:16:27
 * 
 */
@Bizlet("")
public class ReadConExcel {

	/**
	 * @param args
	 * @author chenchuan
	 */
	public static void main(String[] args) {
		String fileToBeRead = "C:\\users\\Administrator\\Desktop\\test.xls";
		readExcel(fileToBeRead);
		
	}
	@Bizlet("")
	public static String readExcel(String fileToBeRead) {
		int coloum = 0;
		String contractNum="";
		HSSFWorkbook workbook;
		System.out.println(fileToBeRead);
		try {
			workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
			HSSFSheet sheet = workbook.getSheet("Sheet1");
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow((short) i);
				if (null == row) {
					continue;
				} else {
					HSSFCell cell = row.getCell((short) coloum);
					if (null == cell||"".equals(cell.getStringCellValue())) {
						continue;
					} else {
							contractNum+="'"+ cell.getStringCellValue().toString()+"',";
//							int tmp = Integer.parseInt(cell.getStringCellValue());
//							cell.setCellValue(tmp + 1);
						}
				}

			}
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		contractNum=contractNum.substring(0, contractNum.length()-1);
		contractNum="("+contractNum+")";
		System.out.println(contractNum);
		return contractNum;
	}
	
	@Bizlet("")	
	public static String[] readExcelContract(String fileToBeRead) {
		int coloum = 0;
		String[] contractNum=new String[100];
		HSSFWorkbook workbook;
		System.out.println(fileToBeRead);
		try {
			workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
			HSSFSheet sheet = workbook.getSheet("Sheet1");
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow((short) i);
				if (null == row) {
					continue;
				} else {
					HSSFCell cell = row.getCell((short) coloum);
					if (null == cell||"".equals(cell.getStringCellValue())) {
						continue;
					} else {
						contractNum[i]= cell.getStringCellValue().toString();
						//	contractNum+="'"+ cell.getStringCellValue().toString()+"',";
//							int tmp = Integer.parseInt(cell.getStringCellValue());
//							cell.setCellValue(tmp + 1);
						}
				}

			}
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		

		return contractNum;
	}

}
