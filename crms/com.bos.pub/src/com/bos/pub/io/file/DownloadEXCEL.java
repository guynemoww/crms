package com.bos.pub.io.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.annotation.Bizlet;

@Bizlet("")
public class DownloadEXCEL {

	@Bizlet("")
	public String download(String filePath, Map<String, Object> excel, List<Object> listcontent) {
		if (filePath == null) {
			filePath = getFilePath();
		}
		OutputStream outputStream = null;
		try {
			HSSFWorkbook workbook = createExcel(excel, listcontent);
			outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
			outputStream.flush();// 强制输出流数据
		} catch (Throwable e) {
			e.printStackTrace();
			filePath = null;
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Throwable e) {

			}
		}
		return filePath;
	}

	private String getFilePath() {
		String path = ConfigurationUtil.getUserConfigSingleValue("CustomConfig", "System", "documentPath");
		return path + File.separatorChar + "download.xls";
	}

	private HSSFWorkbook createExcel(Map<String, Object> excel, List<Object> listcontent) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		String sheetName = (String) excel.get("sheetName");
		HSSFSheet sheet = sheetName == null ? workbook.createSheet() : workbook.createSheet(sheetName);
		for (int i = 0; i < listcontent.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			String[] a = (String[]) listcontent.get(i);
			for (int j = 0; j < a.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(a[j]);
			}
		}
		return workbook;
	}
}
