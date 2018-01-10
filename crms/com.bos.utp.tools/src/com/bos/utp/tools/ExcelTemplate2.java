package com.bos.utp.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.eos.data.xpath.XPathLocator;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.data.sdo.impl.TypeReference;
import com.primeton.data.sdo.impl.types.BooleanType;
import com.primeton.data.sdo.impl.types.DateTimeType;
import com.primeton.data.sdo.impl.types.DateType;
import com.primeton.data.sdo.impl.types.DecimalType;
import com.primeton.data.sdo.impl.types.FloatType;
import com.primeton.data.sdo.impl.types.IntType;
import com.primeton.data.sdo.impl.types.IntegerType;
import com.primeton.data.sdo.impl.types.LongType;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

/**
 * 
 * Excel模板实现类<BR>
 * 实现通过自定义Excel数据模版,将结果集填充到模版相应位置，自动创建输出到指定的文件，调用方法如下：<BR>
 * 
 * <pre>
 *                       ExcelTemplate template=new ExcelTemplate(templateFilePath,outputFilePath)
 *                       template.generate(ResultSet);//resultset为ArrayList对象,数据行以Map封装
 *                       //template.generate(titleMap,dataList)//显示主表、明细表信息
 * </pre>
 * 
 * @author 蔡述尧 caisy (mailto:caisy@primeton.com)
 */

public class ExcelTemplate2 {

	/**
	 * 模板文件名
	 */
	private String templateFile;

	/**
	 * 输出文件名
	 */
	private String outputFile;

	/**
	 * Excel模板定义的输出字段名数组
	 */
	private String[] fieldNames;

	/**
	 * 输出的起始行,默认为-1,不输出
	 */
	private int startRow = -1;

	/**
	 * 默认字体大小
	 */
	private int fontSize = 10;

	/**
	 * 默认字体
	 */
	private String fontName = "宋体";

	/**
	 * 是否设置信息标题栏边框,默认情况不设置边框
	 */
	private boolean titleCellBold = false;

	/**
	 * 是否设置空白栏边框，默认情况不设置边框
	 */
	private boolean blankCellBold = false;

	private HSSFCellStyle borderStyle = null;

	private HSSFCellStyle noneStyle = null;

	/**
	 * 关键字 &-表示模版信息内容字段 #-表示模版明细内容字段
	 */
	private final String TITLE_FLAG = "&";

	private final String CONTENT_FLAG = "#";

	private final String FIELD_AUTO_ID = "_id";

	private final String ENTRY = "com.bos.utp.dataset.dict.EosDictEntry";

	private final String TYPE = "com.bos.utp.dataset.dict.EosDictType";

	// 默认行号
	private int autoRowId = 1;

	private POIFSFileSystem fs = null;

	private HSSFWorkbook wb = null;

	private HSSFSheet sheet = null;

	// private HSSFRow sourceRow = null;

	/**
	 * 默认构造函数
	 * 
	 */
	public ExcelTemplate2() {

	}

	/**
	 * 构造器
	 * 
	 * @param templateFile
	 *            模版文件
	 * @param outputFile
	 *            输出文件
	 */
	public ExcelTemplate2(String template, String output) {
		this.templateFile = template;
		this.outputFile = output;
		try {
			this.setFs(templateFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * excel导入模板构造器
	 * 
	 * @param template
	 */
	public ExcelTemplate2(String template) {
		this.templateFile = template;
		try {
			this.setFs(templateFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return Returns the outputFile.
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * @param outputFile
	 *            The outputFile to set.
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	/**
	 * @return Returns the templateFile.
	 */
	public String getTemplateFile() {
		return templateFile;
	}

	/**
	 * @param templateFile
	 *            The templateFile to set.
	 */
	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	public POIFSFileSystem getFs() {
		return fs;
	}

	/**
	 * 设置fs后会使用 new HSSFWorkbook(fs)初始化 workbook
	 * 
	 * @param fs
	 * @throws Exception
	 */
	public void setFs(POIFSFileSystem fs) throws Exception {
		this.fs = fs;
		setWb(new HSSFWorkbook(fs));
	}

	public void setFs(String fs) throws Exception {
		setFs(new POIFSFileSystem(new FileInputStream(fs)));
		setWb(new HSSFWorkbook(getFs()));
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
		borderStyle = getBorderStyle(wb);
		noneStyle = getNoneStyle(wb);
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public HSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * 设置标题栏是否需要边框
	 * 
	 * @param b
	 */
	public void setTitleCellBold(boolean titleCellBold) {
		this.titleCellBold = titleCellBold;
	}

	/**
	 * 设置空白行是否需要显示边框
	 * 
	 * @param blankCellBold
	 */
	public void setBlankCellBold(boolean blankCellBold) {
		this.blankCellBold = blankCellBold;
	}

	/**
	 * 设置字体大小，默认10号字体
	 * 
	 * @param size
	 */
	public void setFontSize(int size) {
		this.fontSize = size;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	/**
	 * 初始化工作模版，获取模版配置起始行(start)以及对应字段填充位置(fieldNames) 调用此方法前需要设置fs 和 sheet 如果
	 * sheet为空则取 设置sheet=wb.getSheetAt(0)
	 * 
	 * @param sheet
	 */
	public boolean initialize() {
		if (sheet == null) {
			setSheet(wb.getSheetAt(0));
		}
		return initialize(sheet);

	}

	/**
	 * 初始化工作模版，获取模版配置起始行(start)以及对应字段填充位置(fieldNames)
	 * 
	 * @param sheet
	 */
	private boolean initialize(HSSFSheet sheet) {
		boolean setStart = false;
		try {
			if (sheet != null) {
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					HSSFRow row = sheet.getRow(r);

					if (row != null) {
						int cells = row.getPhysicalNumberOfCells();
						for (short c = 0; c < cells; c++) {
							HSSFCell cell = row.getCell(c);
							if (cell != null) {
								String value = null;
								if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									value = "" + cell.getNumericCellValue();
								} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
									value = "" + cell.getBooleanCellValue();
								} else {
									value = cell.getRichStringCellValue().getString();
								}
								if (value != null && !"".equals(value)) {
									value = value.trim();
									// 内容数据
									if (value.startsWith(CONTENT_FLAG)) {
										if (!setStart) {
											this.startRow = r;// 设置内容填充起始行，从字段设置行的下一行开始
											this.fieldNames = new String[cells];
											setStart = true;
										}
										this.fieldNames[c] = value.substring(1);// 初始化内容字段
									}

								}

							}

						}
					}
				}
			}
		} catch (Exception e) {
			setStart = false;
		}
		return setStart;
	}

	/**
	 * 生成填充模版标题数据
	 * 
	 * @param titleMap
	 * @param wb
	 * @param sheet
	 * @throws Exception
	 */
	public void generateTitleDatas(DataObject exportInfo) throws Exception {
		HSSFSheet sheet = getSheet();
		int rows = sheet.getPhysicalNumberOfRows();

		for (int r = 0; r < rows; r++) {
			HSSFRow row = sheet.getRow(r);
			if (row != null) {

				int cells = row.getPhysicalNumberOfCells();
				for (short c = 0; c < cells; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							String value = cell.getRichStringCellValue().getString();
							if (value != null) {
								value = value.trim();
								if (value.startsWith(TITLE_FLAG)) {
									value = value.substring(1);
									// 获取对应的值，支持XPATH取值
									Object obj = XPathLocator.newInstance().getValue(exportInfo, value);
									String content = obj + "";

									// String
									// content=exportInfo.getString(value);
									if (content == null)
										content = "";
									// 重建Cell，填充标题值
									cell = row.createCell((short) c);
									cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									cell.setCellValue(new HSSFRichTextString(content));

									if (!titleCellBold) {
										cell.setCellStyle(noneStyle);
									} else {
										cell.setCellStyle(borderStyle);
									}
								}
							}
						}
					}

				}
			}
		}
	}

	/**
	 * 将指定的对象数组resulset输出到指定的Excel位置
	 * 
	 * @param resultset
	 *            List<DataObject>对象数组
	 * @param wb
	 *            HSSFWorkbook
	 * @param sheet
	 *            HSSFSheet
	 */
	public void generateContentDatas(List<DataObject> resultset) {
		HSSFSheet sheet = getSheet();

		for (Iterator it = resultset.iterator(); it.hasNext(); autoRowId++) {
			DataObject content = (DataObject) it.next();
			startRow++;
			HSSFRow row = sheet.createRow(startRow);
			genOneLine(row, content);
			if (it.hasNext()) {
				shiftDown(sheet, startRow, sheet.getLastRowNum(), 1);
			}

		}

	}

	/**
	 * 将指定的对象数组resulset输出到指定的Excel位置
	 * 
	 * @param resultset
	 *            List<DataObject>对象数组
	 * @param wb
	 *            HSSFWorkbook
	 * @param sheet
	 *            HSSFSheet
	 */
	public void generateDictDatas(List<DataObject> resultset) {
		HSSFSheet sheet = getSheet();

		for (Iterator it = resultset.iterator(); it.hasNext(); autoRowId++) {
			DataObject content = (DataObject) it.next();
			HSSFRow row = sheet.createRow(startRow);
//			String dictid = (String) XPathLocator.newInstance().getValue(content, "dictid");
//			if (dictid != null) {
//				content.set("dicttypeid", content.get("eosDictType/dicttypeid"));
//				content.set("dicttypename", content.get("eosDictType/dicttypename"));
//			}
			genOneLine(row, content);
			if (it.hasNext()) {
				shiftDown(sheet, startRow, sheet.getLastRowNum(), 1);
			}
			startRow++;
		}

	}

	/**
	 * 生成一行excel数据
	 * 
	 * @param row
	 * @param content
	 */
	private void genOneLine(HSSFRow row, DataObject content) {
		for (int i = 0; i < fieldNames.length; i++) {
			// 输出自动生成的行号
			if (fieldNames[i] != null && fieldNames[i].equals(FIELD_AUTO_ID)) {
				HSSFCell cell = row.createCell((short) i);
				cell.setCellStyle(borderStyle);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(autoRowId);
				continue;
			}

			if (fieldNames[i] != null) {
				HSSFCell cell = row.createCell((short) i);
				cell.setCellStyle(borderStyle);
				if (content != null) {
					// 字段名支持xpath取值
					Object value = XPathLocator.newInstance().getValue(content, fieldNames[i]);

					// Object value=content.get(fieldNames[i]);
					if (value != null) {
						if (value instanceof Double || value instanceof BigDecimal) {
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.parseDouble(value.toString()));
						} else {
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(new HSSFRichTextString(value.toString()));
						}
					} else {
						cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
					}

				} else {

					cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
					if (!blankCellBold) {
						cell.setCellStyle(noneStyle);
					} else {
						cell.setCellStyle(borderStyle);
					}
				}
			}
		}
	}

	/**
	 * 将结果集填充到Excel模版,resultset必须是以Map封装行
	 * 
	 * @param
	 * @param resultset
	 *            数据内容
	 * @throws Exception
	 */
	public void generate(List<DataObject> resultset) throws Exception {
		this.generate(resultset, null);

	}

	/**
	 * 将结果集填充到Excel模版,resultset必须是以Map封装行
	 * 
	 * @param titleMap
	 *            标题信息
	 * @param resultset
	 *            结果集
	 * @throws Exception
	 */
	public void generate(List<DataObject> resultset, DataObject exportInfo) throws Exception {
		// 如果无法初始化，退出
		if (!initialize())
			return;

		// 先填充标题
		if (exportInfo != null)
			generateTitleDatas(exportInfo);
		// 生成数据内容
		generateContentDatas(resultset);
		writeToFile();

	}

	public void writeToFile() throws Exception {
		writeToFile(outputFile);
	}

	public void writeToFile(String outputFile) throws Exception {
		FileOutputStream fileOut = new FileOutputStream(outputFile);
		getWb().write(fileOut);
		fileOut.close();
	}

	private HSSFCellStyle getBorderStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) fontSize);
		font.setFontName(fontName);
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return style;
	}

	private HSSFCellStyle getNoneStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) fontSize);
		font.setFontName(fontName);
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
		style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		style.setBorderRight(HSSFCellStyle.BORDER_NONE);
		style.setBorderTop(HSSFCellStyle.BORDER_NONE);
		return style;
	}

	/**
	 * 向下平推表格，并复制格式与内容
	 * 
	 * @param thisrow：当前行号
	 * @param lastrow：最后行号
	 * @param shiftcount：平推量
	 */
	private void shiftDown(HSSFSheet sheet, int thisrow, int lastrow, int shiftcount) {
		sheet.shiftRows(thisrow, lastrow, shiftcount);

		for (int z = 0; z < shiftcount; z++) {
			HSSFRow row = sheet.getRow(thisrow);
			HSSFRow oldrow = sheet.getRow(thisrow + shiftcount);
			// 将各行的行高复制
			oldrow.setHeight(row.getHeight());
			// 将各个单元格的格式复制
			for (short i = 0; i <= oldrow.getPhysicalNumberOfCells(); i++) {

				HSSFCell cell = row.createCell(i);
				HSSFCell oldcell = oldrow.getCell(i);

				if (oldcell != null) {
					switch (oldcell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(oldcell.getRichStringCellValue());
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(oldcell.getNumericCellValue());
						break;
					default:
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(oldcell.getRichStringCellValue());

					}
					cell.setCellStyle(oldcell.getCellStyle());
				}
			}

			// 将有列跨越的复制
			Vector regs = findRegion(sheet, oldrow);
			if (regs.size() != 0) {
				for (int i = 0; i < regs.size(); i++) {
					Region reg = (Region) regs.get(i);
					reg.setRowFrom(row.getRowNum());
					reg.setRowTo(row.getRowNum());
					sheet.addMergedRegion(reg);
				}
			}
			thisrow++;
		}
	}

	/**
	 * 查找所有的合并单元格
	 * 
	 * @param oldrow
	 * @return
	 */
	private Vector findRegion(HSSFSheet sheet, HSSFRow oldrow) {
		Vector<Region> regs = new Vector<Region>();
		int num = sheet.getNumMergedRegions();
		int curRowid = oldrow.getRowNum();
		for (int i = 0; i < num; i++) {
			Region reg = sheet.getMergedRegionAt(i);
			if (reg.getRowFrom() == reg.getRowTo() && reg.getRowFrom() == curRowid) {
				regs.add(reg);
			}
		}
		return regs;
	}

	/**
	 * 将目标Excel文件的内容导入到默认数据源数据表
	 * 
	 * @param targetFile
	 *            Excel文件路径
	 * @param entityName
	 *            SDO数据实体全名
	 * @return 返回1 导入成功
	 * 
	 * @throws Exception
	 */
	public int importData(String excelFile, String entityName, int submitCount) throws Exception {
		return importData(excelFile, "default", entityName, submitCount);
	}

	/**
	 * 将目标Excel文件的内容导入到指定数据源的数据表
	 * 
	 * @param targetFile
	 *            Excel文件路径
	 * @param entityName
	 *            SDO数据实体全名
	 * @return 返回1 导入成功
	 * 
	 * @throws Exception
	 */
	public int importData(String excelFile, String dsname, String entityName, int submitCount) throws Exception {
		for (int sheetCount = 0; sheetCount < wb.getNumberOfSheets(); sheetCount++) {
			HSSFSheet sheet = wb.getSheetAt(sheetCount);
			int rows = sheet.getPhysicalNumberOfRows();
			setSheet(sheet);
			// 如果无法初始化，跳过当前sheet
			if (!initialize())
				continue;

			List<DataObject> dataObjects = new ArrayList<DataObject>();

			// 从以#开头的的字段名行开始

			for (int rowCount = startRow + 1; rowCount < rows; rowCount++) {
				HSSFRow sourceRow = sheet.getRow(rowCount);
				DataObject importEntity = getOneLine(sourceRow, entityName);

				if (importEntity != null) {
					// 达到批量提交的数量，进行持久化
					if (dataObjects.size() == submitCount) {
						DatabaseUtil.insertEntityBatch(dsname, dataObjects.toArray(new DataObject[dataObjects.size()]));
						dataObjects.clear();
					}

					dataObjects.add(importEntity);
				}
			}
			// 如果dataObjects中还有数据，进行持久化。
			if (dataObjects.size() > 0) {
				DatabaseUtil.insertEntityBatch(dsname, dataObjects.toArray(new DataObject[dataObjects.size()]));
			}
		}

		return 1;
	}

	/**
	 * 将目标Excel文件的内容导入到默认数据源数据表
	 * 
	 * @param targetFile
	 *            Excel文件路径
	 * @param entityName
	 *            SDO数据实体全名
	 * @return 返回1 导入成功
	 * 
	 * @throws Exception
	 */
	public int importDictData(String excelFile, int submitCount) throws Exception {
		return importDictData(excelFile, "default", submitCount);
	}

	/**
	 * 将目标Excel文件的内容导入到指定数据源的数据表
	 * 
	 * @param targetFile
	 *            Excel文件路径
	 * @param entityName
	 *            SDO数据实体全名
	 * @return 返回1 导入成功
	 * 
	 * @throws Exception
	 */
	public int importDictData(String excelFile, String dsname, int submitCount) throws Exception {
		int ret = 0;
		HSSFWorkbook source = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelFile)));

		for (int sheetCount = 0; sheetCount < wb.getNumberOfSheets(); sheetCount++) {
			// 设置模板
			HSSFSheet sheet = wb.getSheetAt(sheetCount);
			setSheet(sheet);
			// 如果无法初始化，跳过当前sheet
			if (!initialize())
				continue;
			// 设置输入文件信息
			HSSFSheet srcsheet = source.getSheetAt(sheetCount);
			int rows = srcsheet.getPhysicalNumberOfRows();			

		    List<DataObject> typelist = new ArrayList<DataObject>();
		    Set<String> typeset=new HashSet<String>();		    
			List<DataObject> entrylist = new ArrayList<DataObject>();
			Set<String>entryset=new HashSet<String>();
			// 从以#开头的的字段名行开始
			int cellno = getDictCellNo(this.fieldNames);
			for (int rowCount = startRow; rowCount < rows; rowCount++) {
				DataObject[] objs = getDictLine(srcsheet.getRow(rowCount), cellno);
				if(objs.length>0){
					String dicttype = objs[0].getString("dicttypeid");					
					if (!typeset.contains(dicttype)) {
						typelist.add(objs[0]);
						typeset.add(dicttype);
					}
					if (objs.length>1){
						String dictid = dicttype+objs[1].getString("dictid");					
						if(!entryset.contains(dictid)) {				
							entrylist.add(objs[1]);
							entryset.add(dictid);
						}
					}
				}
				// 如果数据批量操作上限执行数据库提交
				if ((entrylist.size()+typelist.size()) == submitCount) {
					ret += saveData(dsname, typelist);
					ret += saveData(dsname, entrylist);
				}
			}
			// 如果dataObjects中还有数据，进行持久化。
			if ((entrylist.size()+typelist.size())>0) {
				ret += saveData(dsname, typelist);
				ret += saveData(dsname, entrylist);
			}
		}
		return ret;
	}

	/**
	 * 批量保存数据
	 * 
	 * @param dsname
	 * @param list
	 * @return
	 */
	private int saveData(String dsname, List<DataObject> list) {
		int ret = list.size();
		try {
			DatabaseUtil.insertEntityBatch(dsname, list.toArray(new DataObject[ret]));
			list.clear();
		} catch (Exception e) {
			ret = 0;
		}
		return ret;
	}

	/**
	 * 返回dictid所在列的序号 -1表示不存在
	 * 
	 * @param fields
	 * @return
	 */
	private int getDictCellNo(String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			if ("dictid".equals(fields[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取字典记录
	 * 
	 * @param sourceRow
	 * @param cellno
	 * @return
	 */
	private DataObject[] getDictLine(HSSFRow sourceRow, int cellno) {
		if (sourceRow != null) {
			DataObject importType = DataObjectUtil.createDataObject(TYPE);
			DataObject importEntity = DataObjectUtil.createDataObject(ENTRY);
			// 以下构造导入的实体对象，并根据Excel单元格的内容填充实体属性值
			for (int cellCount = 0; cellCount < fieldNames.length; cellCount++) {
				String propertyName = fieldNames[cellCount];
				HSSFCell cell = sourceRow.getCell((short) cellCount);

				setDataValue(importType, propertyName, cell);
				if ("dicttypeid".equals(propertyName)) {
					propertyName = "eosDictType/dicttypeid";
				}
				setDataValue(importEntity, propertyName, cell);
			}
			return new DataObject[] { importType, importEntity };
		} else {
			return new DataObject[0];
		}
	}

	private DataObject getOneLine(HSSFRow sourceRow, String entityName) {

		DataObject importEntity = DataObjectUtil.createDataObject(entityName);
		// 判断某一行是否允许插入，当该行的所有列cell均为BLANK时不插入数据库
		boolean allowInsert = false;

		// 以下构造导入的实体对象，并根据Excel单元格的内容填充实体属性值
		for (int cellCount = 0; cellCount < fieldNames.length; cellCount++) {

			String propertyName = fieldNames[cellCount];
			HSSFCell cell = sourceRow.getCell((short) cellCount);
			allowInsert = setDataValue(importEntity, propertyName, cell) && allowInsert;
		}
		return allowInsert ? importEntity : null;
	}

	/**
	 * 设置一个属性值
	 * 
	 * @param importEntity
	 * @param propertyName
	 * @param cell
	 * @return
	 */
	private boolean setDataValue(DataObject importEntity, String propertyName, HSSFCell cell) {
		if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			return false;
		} else {

			String value = null;

			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = dateFormat.format((cell.getDateCellValue()));

				} else {
					value = String.valueOf((long) cell.getNumericCellValue());
				}
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
				value = cell.getBooleanCellValue() + "";
			} else {
				value = cell.getRichStringCellValue().getString();
			}

			String[] names = propertyName.split("\\/");
			TypeReference typeReference = null;
			if (names.length == 2) {
				Property property = importEntity.getType().getProperty(names[0]);
				// 如果没有这个属性，直接返回。
				if (property == null) {
					return false;
				}
				typeReference = (TypeReference) property.getType().getProperty(names[1]).getType();
			} else if (names.length == 1) {
				Property property = importEntity.getType().getProperty(propertyName);
				if (property == null) {
					return false;
				}
				typeReference = (TypeReference) property.getType();
			} else {
				return false;
			}

			Type propertyType = typeReference.getActualType();

			if (propertyType instanceof IntType || propertyType instanceof IntegerType) {
				// 防止可能出现的Excel表格读取自动加.号
				if (value.indexOf(".") != -1)
					value = value.substring(0, value.indexOf("."));
				importEntity.set(propertyName, ChangeUtil.toInteger(value));

			} else if (propertyType instanceof BooleanType) {
				importEntity.set(propertyName, ChangeUtil.toBoolean(Boolean.valueOf(value)));

			} else if (propertyType instanceof FloatType) {
				importEntity.set(propertyName, ChangeUtil.toFloat(value));

			} else if (propertyType instanceof LongType) {
				if (value.indexOf(".") != -1)
					value = value.substring(0, value.indexOf("."));

				importEntity.set(propertyName, ChangeUtil.toLong(value));

			} else if (propertyType instanceof DecimalType) {
				importEntity.set(propertyName, ChangeUtil.toBigDecimal(value));

			} else if (propertyType instanceof DateType) {
				importEntity.set(propertyName, ChangeUtil.changeToDBDate(value));

			} else if (propertyType instanceof DateTimeType) {
				importEntity.set(propertyName, ChangeUtil.toTimestamp(value));

			} else {
				importEntity.set(propertyName, value);
			}
		}

		return true;
	}

	/**
	 * 单元测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ExcelTemplate2 et = new ExcelTemplate2();
		// et.setFs();

	}

}
