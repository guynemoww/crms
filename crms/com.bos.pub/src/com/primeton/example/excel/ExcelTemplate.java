/**
 * 
 */
package com.primeton.example.excel;


import com.bos.pub.GitUtil;
import com.eos.spring.DASDaoSupport;
import com.eos.system.annotation.Bizlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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

import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.data.xpath.XPathLocator;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.ibm.db2.jcc.b.p;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.data.sdo.impl.PropertyImpl;
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
import commonj.sdo.Type;



@Bizlet("")
public class ExcelTemplate extends DASDaoSupport{
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
	 private int startRow=-1;
	 private int tempStartRowNum=-1;
	 /**
	  * 默认字体大小
	  */
	 private int fontSize=10;
	 /**
	  * 默认字体
	  */
	 private String fontName="宋体";
	 /**
	  * 是否设置信息标题栏边框,默认情况不设置边框
	  */
	 private boolean titleCellBold=false;
	 /**
	  * 是否设置空白栏边框，默认情况不设置边框
	  */
	 private boolean blankCellBold=false;
	 /**
	  * 是否自动分工作薄
	  */
	 private boolean autoSheet=false;
	 /**
	  * 是否自动分页
	  */
	 private boolean autoPagination=false;
	 /**
	  * 分页行数
	  */
	 private int maxrow=-1;
	 /**
	  * 是否有公式
	  */
	 private boolean hasFormula=false;
	 /**
	  * 关键字
	  * &-表示模版信息内容字段
	  * #-表示模版明细内容字段
	  * formula-表示模版函数关键字
	  * ~-表示Cell当前行，当包含":"时，表示当前行减1
	  */
	 private final String TITLE_FLAG="&";
	 private final String CONTENT_FLAG="#";
	 private final String FORMULA_FLAG="formula";
	 private final String UNLIMIT_FLAG="~";
	 private final String FIELD_AUTO_ID="_id";
	 /**
	  * 公式计算操作符号
	  */
	 private final String[] OP_FLAG=new String[]{"+","-","*","/","%",":"};
	 /**
	  * 默认构造函数
	  *
	  */
	 public ExcelTemplate(){
	 }
	 /**
	  * 构造器
	  * @param templateFile 模版文件
	  * @param outputFile 输出文件
	  */
	 public ExcelTemplate(String templateFile,String outputFile){
	  this.templateFile=templateFile;
	  this.outputFile=outputFile;
	 }
	 /**
	  * 设置模版文件是否包含Excel公式
	  * @param hasFormula
	  */
	 public void setIncludeFormula(boolean hasFormula){
	  this.hasFormula=hasFormula;
	 }
	 /**
	  * 设置标题栏是否需要边框
	  * @param b
	  */
	 public void setTitleCellBold(boolean titleCellBold){
	  this.titleCellBold=titleCellBold;
	 }
	 /**
	  * 设置空白行是否需要显示边框
	  * @param blankCellBold
	  */
	 public void setBlankCellBold(boolean blankCellBold){
	  this.blankCellBold=blankCellBold;
	 }
	 /**
	  * 设置是否分工作薄
	  * @param b
	  */
	 public void setAutoSheet(boolean autoSheet){
	  this.autoSheet=autoSheet;
	  this.autoPagination=(autoSheet?false:autoPagination);
	 }
	 /**
	  * 是否自动分页
	  * @param autoPagination
	  */
	 public void setAutoPagination(boolean autoPagination){
	  this.autoPagination=autoPagination;
	  this.autoSheet=(autoPagination?false:autoSheet);
	 }
	 /**
	  * 设置分页最大行
	  * @param maxrow
	  */
	 public void setMaxRow(int maxrow){
	  this.maxrow=maxrow;
	 }
	 /**
	  * 设置字体大小，默认10号字体
	  * @param size
	  */
	 public void setFontSize(int size){
	  this.fontSize=size;
	 }
	 public void setFontName(String fontName){
	  this.fontName=fontName;
	 }
	 /**
	  * 初始化工作模版，获取模版配置起始行(start)以及对应字段填充位置(fieldNames)
	  * @param sheet
	  */
	 private void initialize(HSSFSheet sheet){
	        boolean setStart=false;
	        int rows  = sheet.getPhysicalNumberOfRows();
	        for (int r = 0; r < rows; r++){
	            HSSFRow row   = sheet.getRow(r);
	            if (row != null) {
	            	//System.out.println("row is not null");
	                int cells = row.getPhysicalNumberOfCells();
	                //System.out.println("列="+cells);
	              //  System.out.println("cells="+cells);
	                for(short c = 0; c < cells; c++){
	                 HSSFCell cell  = row.getCell(c);
	                 if(cell!=null)
	                 {
	                  String value=null;
	                  if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
	                   value=""+cell.getNumericCellValue();
	                  }else if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
	                   value=""+cell.getBooleanCellValue();
	                  }else{
	                   value=cell.getRichStringCellValue().getString();
	                  }
	                     if(value!=null&&!"".equals(value))
	                     {
	                      value=value.trim();
	                      //内容数据
	                      if(value.startsWith(CONTENT_FLAG)){
	                    	 // System.out.println("#开头");
	                          if(!setStart){
	                           this.startRow=r;//设置内容填充起始行
	                           this.fieldNames=new String[cells];
	                           setStart=true;
	                          }
	                        //  System.out.println("字段名："+value.substring(1));
	                          this.fieldNames[c]=value.substring(1);//初始化内容字段
	                      }
	                     }
	                 }
	                }
	            }
	        }
	 }
	 /**
	  * 计算公式,默认范围从0行到工作薄结尾
	  * @param wb
	  * @param sheet
	  */
	 private void calcFormula(HSSFWorkbook wb,HSSFSheet sheet){
	  this.calcFormula(wb,sheet,0,sheet.getPhysicalNumberOfRows());
	 }
	 /**
	  * 计算公式函数,范围从开始行(start_row)到结束行(end_row)
	  * @param wb HSSFWorkbook
	  * @param sheet HSSFSHeet
	  * @param start_rang
	  * @param end_rang
	  */
	 private void calcFormula(HSSFWorkbook wb,HSSFSheet sheet,int start_rang,int end_rang){
	        //int rows  = sheet.getPhysicalNumberOfRows();
	  HSSFCellStyle borderStyle=this.getBorderStyle(wb);
	  HSSFCellStyle noneStyle=this.getNoneStyle(wb);  
	        for (int r = start_rang; r < end_rang; r++){
	            HSSFRow row   = sheet.getRow(r);
	            if (row != null) {
	                int cells = row.getPhysicalNumberOfCells();
	                for(short c = 0; c < cells; c++){
	                 HSSFCell cell  = row.getCell(c);
	                 if(cell!=null){
	                  if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
	                         String value=cell.getRichStringCellValue().getString();
	                         if(value!=null){
	                          value=value.trim().toLowerCase();
	                          if(value.startsWith(FORMULA_FLAG))
	                          {
	                           int index=value.indexOf("=");
	                           String formula=value.substring(index+1);
	                           //判断函数是否包含以#开头,如果是以#开头表示必须显示边框，
	                           String flag=formula.substring(0,1);
	                           boolean showBold=false;
	                           if(flag.equals(CONTENT_FLAG)){
	                            formula=formula.substring(1);
	                            showBold=true;
	                           }
	                             //如果包含':'符号则统计公式不包含当前行,否则会引发公式循环引用错误.
	                           if(formula.indexOf(":")!=-1){
	                            formula=formula.replaceAll(UNLIMIT_FLAG,r+"").toUpperCase();
	                           }else{
	                            formula=formula.replaceAll(UNLIMIT_FLAG,(r+1)+"").toUpperCase();
	                           }
	                           //判断公式对应的Cell内容是否为blank,
	                           //如果公式对应的CELL内容为空，则设置为""
	                           int rightIndex=formula.indexOf(")");
	                           int leftIndex=formula.indexOf("(");
	                           String content=formula.substring(leftIndex+1,rightIndex);
	                           int opIndex=this.getOpIndex(content);
	                           String startPos=content.substring(0,opIndex);
	                           String endPos=content.substring(opIndex+1);
	                           int start_col=this.getColumnIndex(startPos.charAt(0));
	                        int start_row=Integer.parseInt(startPos.substring(1));
	                        int end_col=this.getColumnIndex(endPos.charAt(0));
	                        int end_row=Integer.parseInt(endPos.substring(1));
	                        HSSFCell startC=sheet.getRow(start_row-1).getCell((short)start_col);
	                        HSSFCell endC=sheet.getRow(end_row-1).getCell((short)end_col);
	                        //判断公式开始Cell与结束cell内容是否无效
	                        //当为均为无效的cell值，并且当前公式不包含":"，则设置公式框内容为""，
	                        //包含":" 则设置为计算公式
	                        if(invalidCellValue(startC)&&invalidCellValue(endC)){
	                         if(formula.indexOf(":")==-1){
	                          cell.setCellValue( new HSSFRichTextString(""));
	                         }else{
	                                cell=row.createCell((short)c);
	                                cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
	                                cell.setCellFormula(formula);
	                         }
	                        }else{
	                            //重建Cell
	                            cell=row.createCell((short)c);
	                            cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
	                            cell.setCellFormula(formula);
	                        }
	                           if(showBold){
	                            cell.setCellStyle(borderStyle);
	                           }else{
	                            cell.setCellStyle(noneStyle);
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
	  * 设置公式文本框为空白栏，当统计开始行减1==startRowNum时
	  * @param cell
	  * @param startRowNum
	  */
	 private void setFormulaBlankCell(HSSFCell cell,int startRowNum){
	     if (cell != null) {
	   if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
	    String value = cell.getRichStringCellValue().getString();
	    if (value != null) {
	     value = value.trim().toLowerCase();
	     if (value.startsWith(FORMULA_FLAG)) {
	      int index = value.indexOf("=");
	      String formula = value.substring(index + 1);
	      String flag = formula.substring(0, 1);
	      if (flag.equals(CONTENT_FLAG))formula = formula.substring(1);
	      if (formula.indexOf(":") != -1) {
	       int rightIndex = formula.indexOf(")");
	       int leftIndex = formula.indexOf("(");
	       String content = formula.substring(leftIndex + 1,rightIndex).toUpperCase();
	       int opIndex = this.getOpIndex(content);
	       String startPos = content.substring(0, opIndex);
	       String colValue = startPos.substring(1,opIndex);
	       if(Integer.parseInt(colValue)-1==startRowNum)
	        cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
	      }
	     }
	    }
	   }
	  }
	 
	 }
	 /**
	  * 生成填充模版标题数据
	  *
	  * @param titleMap
	  * @param wb
	  * @param sheet
	  * @throws Exception
	  */
	 private void generateTitleDatas(DataObject exportInfo,HSSFWorkbook wb,HSSFSheet sheet)throws Exception{
	        int rows  = sheet.getPhysicalNumberOfRows();
	        HSSFCellStyle borderStyle=this.getBorderStyle(wb);
	        HSSFCellStyle noneStyle=this.getNoneStyle(wb);       
	        for (int r = 0; r < rows; r++){
	            HSSFRow row   = sheet.getRow(r);
	            if (row != null) {
	                int cells =row.getPhysicalNumberOfCells();
	                for(short c = 0; c < cells; c++){
	                 HSSFCell cell  = row.getCell(c);
	                 if(cell!=null){
	                  if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
	                         String value=cell.getRichStringCellValue().getString();
	                         if(value!=null){
	                          value=value.trim();
	                          if(value.startsWith(TITLE_FLAG)){
	                           value=value.substring(1);
	                           //获取对应的值，支持XPATH取值
	                           Object obj=XPathLocator.newInstance().getValue(exportInfo, value);
	                           String content=obj+"";
	                           //String content=exportInfo.getString(value);
	                           if(content==null)content="";
	                           //重建Cell，填充标题值
	                           cell=row.createCell((short)c);                           
	                           cell.setCellType(HSSFCell.CELL_TYPE_STRING);                           
	                           cell.setCellValue( new HSSFRichTextString(content));
	                           if(!titleCellBold){
	                            cell.setCellStyle(noneStyle);
	                           }else{
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
	  * @param resultset List<DataObject>对象数组
	  * @param wb HSSFWorkbook
	  * @param sheet HSSFSheet
	  */
	 private void generateContentDatas(List<DataObject> resultset,HSSFWorkbook wb,HSSFSheet sheet){
	  HSSFCellStyle borderStyle=this.getBorderStyle(wb);
	  HSSFCellStyle noneStyle=this.getNoneStyle(wb);
	  //默认行号
	  int autoRowId=1;
	        for(Iterator it=resultset.iterator();it.hasNext();autoRowId++){
	         DataObject content=(DataObject)it.next();
	         HSSFRow sourceRow=sheet.getRow(startRow);
	         HSSFRow row=sheet.createRow(startRow++);
	         for(int i=0;i<sourceRow.getPhysicalNumberOfCells();i++){
	          //输出自动生成的行号
	          if(fieldNames[i]!=null&&fieldNames[i].equals(FIELD_AUTO_ID)){
	             HSSFCell cell=row.createCell((short)i);           
	           cell.setCellStyle(borderStyle);
	           cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setCellValue(autoRowId);
	        continue;
	          }
	          if(fieldNames[i]!=null){
	           HSSFCell cell=row.createCell((short)i);           
	           cell.setCellStyle(borderStyle);
	           if(content!=null){
	            //字段名支持xpath取值
	            Object value=XPathLocator.newInstance().getValue(content, fieldNames[i]);
	               //Object value=content.get(fieldNames[i]);
	               if(value!=null){
	                if(value instanceof Double|| value instanceof BigDecimal){
	                 cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	                 cell.setCellValue(Double.parseDouble(value.toString()));
	                }else{
	                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	                 cell.setCellValue(new HSSFRichTextString(value.toString()));
	                }
	               }else{
	                cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
	               }
	           }else{
	            cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
	            if(!blankCellBold){
	             cell.setCellStyle(noneStyle);
	            }else{
	             cell.setCellStyle(borderStyle);
	            }
	           }
	          }else{
	        HSSFCell sourceCell=sourceRow.getCell((short)i);
	        if(sourceCell!=null&&
	          sourceCell.getCellType()==HSSFCell.CELL_TYPE_STRING&&
	          sourceCell.getRichStringCellValue().getString()!=null&&
	          sourceCell.getRichStringCellValue().getString().toLowerCase().startsWith(FORMULA_FLAG)){
	         HSSFCell cell=row.createCell((short)i);
	         cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	         cell.setCellValue(sourceCell.getRichStringCellValue());
	        }
	       }
	         }
	         if(it.hasNext()){
	          //向下平推一行
	          //sheet.shiftRows(startRow-1,sheet.getLastRowNum(),1);
	          shiftDown(sheet,startRow-1, sheet.getLastRowNum(), 1);
	         }
	        }
	 }
	 /**
	  * 将结果集填充到Excel模版,resultset必须是以Map封装行
	  * @param
	  * @param resultset 数据内容
	  * @throws Exception
	  */
	 public void generate(List<DataObject> resultset)throws Exception{
	  this.generate(resultset,null);
	 }
	 /**
	  * 将结果集填充到Excel模版,resultset必须是以Map封装行
	  * @param titleMap 标题信息
	  * @param resultset 结果集
	  * @throws Exception
	  */
	 public void generate(List<DataObject> resultset,DataObject exportInfo)throws Exception{
	        POIFSFileSystem fs =new POIFSFileSystem(new FileInputStream(templateFile));
	        HSSFWorkbook wb = new HSSFWorkbook(fs);
	        HSSFSheet sheet = wb.getSheetAt(0);
	        initialize(sheet);
	        if(startRow==-1)
	         return ;

	        if(this.autoPagination){
	         this.generatePagination(wb,sheet,resultset,exportInfo);
	        }
	        else if(this.autoSheet){
	         generatePaginationSheet(wb,sheet,resultset,exportInfo);
	        }
	        else{
	            //先填充标题
	            if(exportInfo!=null)
	             this.generateTitleDatas(exportInfo,wb,sheet);
	            //生成数据内容
	            this.generateContentDatas(resultset,wb,sheet);
	            if(hasFormula){
	             this.calcFormula(wb,sheet);
	            }
	        }
	        FileOutputStream fileOut = new FileOutputStream(outputFile);
	        wb.write(fileOut);
	        fileOut.close();
	 }
	 /**
	  * EXCEL分页
	  * 必须在EXCEL模版的最后一行插入EXCEL分页符!
	  * @param wb HSSFWorkbook
	  * @param sourceSheet HSSFSheet
	  * @param resultset 填充数据集
	  * @param titleMap 信息栏内容
	  * @throws Exception
	  */
	 private void generatePagination(HSSFWorkbook wb,HSSFSheet sourceSheet,List<DataObject> resultset,DataObject exportInfo)
	throws Exception{
	     int startPosition=startRow;
	     tempStartRowNum=startRow;
	     int count=resultset.size() / maxrow;
	     int num=resultset.size() % maxrow;
	     int rows=sourceSheet.getPhysicalNumberOfRows();
	     System.out.println("rows="+rows);
	     if(num>0){
	      count=count+1;
	      num=maxrow-num;
	      //不足指定的maxrow，添加空行
	      for(int i=0;i<num;i++){
	       resultset.add(null);
	      }
	     }
	     //删除最后一行的分页符
	     try{
	      sourceSheet.removeRowBreak(rows-1);
	     }catch(NullPointerException npe){
	      throw new Exception("指定的EXCEL模版文件["+this.templateFile+"] 未插入分页符");
	     }
	     //超过1页则插入分页符
	     for(int i=1;i<count;i++){
	      //设置分页符
	      sourceSheet.setRowBreak(i*rows-1);
	      this.copyRows(sourceSheet,sourceSheet,0,rows,i*rows+1);
	     }
	     if(exportInfo!=null)
	      this.generateTitleDatas(exportInfo,wb,sourceSheet);
	     int current_page=0;
	     while(current_page<count){
	      List<DataObject> newList=resultset.subList(current_page*maxrow,maxrow*(current_page+1));
	      this.generateContentDatas(newList,wb,sourceSheet);
	      current_page++;
	      //计算下一行的数据填充起始位置
	      startRow=current_page*rows+maxrow+startPosition;
	     }
	        if(hasFormula)
	         this.calcFormula(wb,sourceSheet);
	 }

	 /**
	  * 生成分页的工作薄模版
	  * @param wb HSSFWorkbook
	  * @param sourceSheet HSSFSheet
	  * @param resultset 填充数据集
	  * @param titleMap 信息(标题)栏内容
	  */
	 private void generatePaginationSheet(HSSFWorkbook wb,HSSFSheet sourceSheet,List<DataObject> resultset,DataObject exportInfo)
	throws Exception{
	     int startPosition=startRow;
	     int count=resultset.size() / maxrow;
	     int num=resultset.size() % maxrow;
	     if(num>0){
	      count=count+1;
	      num=maxrow-num;
	      //不足指定的maxrow，添加空行
	      for(int i=0;i<num;i++){
	       resultset.add(null);
	      }
	     }
	     for(int i=1;i<count;i++){
	      HSSFSheet newsheet=wb.createSheet("Page "+i);
	      this.copyRows(sourceSheet,newsheet,0,sourceSheet.getLastRowNum(),0);
	     }
	     if(count>1){
	      for(int i=0;i<wb.getNumberOfSheets();i++){
	       startRow=startPosition;
	       List<DataObject> newList=resultset.subList(i*maxrow,maxrow*(i+1));
	       HSSFSheet sheet=wb.getSheetAt(i);
	             //先填充标题
	             if(exportInfo!=null)
	              this.generateTitleDatas(exportInfo,wb,sheet);
	                this.generateContentDatas(newList,wb,sheet);
	                if(hasFormula)
	                 this.calcFormula(wb,sheet);
	      }
	     }else{
	   HSSFSheet sheet=wb.getSheetAt(0);
	         if(exportInfo!=null)
	          this.generateTitleDatas(exportInfo,wb,sheet);
	            this.generateContentDatas(resultset,wb,sheet);
	            if(hasFormula)
	             this.calcFormula(wb,sheet);
	     }
	 }
	 private HSSFCellStyle getBorderStyle(HSSFWorkbook wb){
	        HSSFCellStyle style = wb.createCellStyle();
	        HSSFFont font=wb.createFont();
	        font.setFontHeightInPoints((short)fontSize);
	        font.setFontName(fontName);
	        style.setFont(font);
	        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        return style;
	 }
	 private HSSFCellStyle getNoneStyle(HSSFWorkbook wb){
	        HSSFCellStyle style = wb.createCellStyle();
	        HSSFFont font=wb.createFont();
	        font.setFontHeightInPoints((short)fontSize);
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
	     * @param thisrow：当前行号
	     * @param lastrow：最后行号
	     * @param shiftcount：平推量
	     */
	    private void shiftDown(HSSFSheet sheet,int thisrow, int lastrow, int shiftcount) {
	        sheet.shiftRows(thisrow, lastrow, shiftcount);
	        for (int z = 0; z < shiftcount; z++) {
	            HSSFRow row = sheet.getRow(thisrow);
	            HSSFRow oldrow = sheet.getRow(thisrow + shiftcount);
	            //将各行的行高复制
	            oldrow.setHeight(row.getHeight());
	            //将各个单元格的格式复制
	            for (short i = 0; i <= oldrow.getPhysicalNumberOfCells(); i++) {
	                HSSFCell cell = row.createCell(i);
	                HSSFCell oldcell = oldrow.getCell(i);
	                if (oldcell != null) {                   
	                    switch(oldcell.getCellType()){
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
	            //将有列跨越的复制
	            Vector regs = findRegion(sheet,oldrow);
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
	     * @param oldrow
	     * @return
	     */
	    private Vector findRegion(HSSFSheet sheet ,HSSFRow oldrow) {
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
	     * 复制EXCEL行到指定的工作薄上
	     * ××如果是分页显示，需要增加一个判断：当复制行包含公式forumla=sum(G7:G~)字样时候，必须修改其实行G7为相应的新行。
	     * @param sourceSheet  原工作薄
	     * @param targetSheet 目标工作薄
	     * @param pStartRow 复制起始行
	     * @param pEndRow 复制终止行
	     * @param pPosition 插入位置
	     */
	    private void copyRows(HSSFSheet sourceSheet, HSSFSheet targetSheet,int pStartRow, int pEndRow, int pPosition) {
	  HSSFRow sourceRow = null;
	  HSSFRow targetRow = null;
	  HSSFCell sourceCell = null;
	  HSSFCell targetCell = null;
	  Region region = null;
	  int cType;
	  int i;
	  short j;
	  int targetRowFrom;
	  int targetRowTo;
	  if ((pStartRow == -1) || (pEndRow == -1)) {
	   return;
	  }
	  // 拷贝合并的单元格
	  for (i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
	   region = sourceSheet.getMergedRegionAt(i);
	   if ((region.getRowFrom() >= pStartRow)&& (region.getRowTo() <= pEndRow)) {
	    targetRowFrom = region.getRowFrom() - pStartRow + pPosition;
	    targetRowTo = region.getRowTo() - pStartRow + pPosition;
	    region.setRowFrom(targetRowFrom);
	    region.setRowTo(targetRowTo);
	    targetSheet.addMergedRegion(region);
	   }
	  }
	  // 设置列宽
	  for (i = pStartRow; i <= pEndRow; i++) {
	   sourceRow = sourceSheet.getRow(i);
	   if (sourceRow != null) {
	    for (j = sourceRow.getFirstCellNum(); j < sourceRow.getLastCellNum(); j++) {
	     targetSheet.setColumnWidth(j, sourceSheet.getColumnWidth(j));
	    }
	    break;
	   }
	  }
	  // 拷贝行并填充数据
	  for (; i <= pEndRow; i++) {
	   sourceRow = sourceSheet.getRow(i);
	   if (sourceRow == null) {
	    continue;
	   }
	   targetRow = targetSheet.createRow(i - pStartRow + pPosition);
	   targetRow.setHeight(sourceRow.getHeight());
	   for (j = sourceRow.getFirstCellNum(); j < sourceRow.getLastCellNum(); j++) {
	    sourceCell = sourceRow.getCell(j);
	    if (sourceCell == null) {
	     continue;
	    }
	    targetCell = targetRow.createCell(j);    
	    targetCell.setCellStyle(sourceCell.getCellStyle());
	    cType = sourceCell.getCellType();
	    targetCell.setCellType(cType);
	    switch (cType) {
	    case HSSFCell.CELL_TYPE_BOOLEAN:
	     targetCell.setCellValue(sourceCell.getBooleanCellValue());
	     break;
	    case HSSFCell.CELL_TYPE_ERROR:
	     targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
	     break;
	    case HSSFCell.CELL_TYPE_FORMULA:
	     targetCell.setCellFormula(parseFormula(sourceCell.getCellFormula()));
	     break;
	    case HSSFCell.CELL_TYPE_NUMERIC:
	     targetCell.setCellValue(sourceCell.getNumericCellValue());
	     break;
	    case HSSFCell.CELL_TYPE_STRING:
	     targetCell.setCellValue(sourceCell.getRichStringCellValue());
	     break;
	    }
	    if(this.autoPagination){
	     this.setFormulaBlankCell(sourceCell,tempStartRowNum);
	    }
	   }
	  }
	 }
	    private String parseFormula(String pPOIFormula) {
	  final String cstReplaceString = "ATTR(semiVolatile)"; //$NON-NLS-1$
	  StringBuffer result = null;
	  int index;
	  result = new StringBuffer();
	  index = pPOIFormula.indexOf(cstReplaceString);
	  if (index >= 0) {
	   result.append(pPOIFormula.substring(0, index));
	   result.append(pPOIFormula.substring(index+ cstReplaceString.length()));
	  } else {
	   result.append(pPOIFormula);
	  }
	  return result.toString();
	 }
	 
	    /**
	  * 将列的索引换算成ABCD字母，这个方法要在插入公式时用到
	  * @param colIndex 列索引。
	  * @return ABCD字母。
	  */
	    /*
	    private String getColLetter(int colIndex){
	     String ch = "";
	        if (colIndex  < 26)
	            ch = "" + (char)((colIndex) + 65);
	        else
	           ch = "" + (char)((colIndex) / 26 + 65 - 1) + (char)((colIndex) % 26 + 65);
	        return ch;
	    }
	    */
	    private int getColumnIndex(char c){
	     int i=c;
	     return i-65;
	    }
	    private int getOpIndex(String s){
	     for(int i=0;i<OP_FLAG.length;i++){
	      int index=s.indexOf(OP_FLAG[i]);
	      if(index!=-1){
	       return index;
	      }
	     }
	     return -1;
	    }
	    /**
	     * 判断是否无效Cell
	     * @param cell
	     * @return
	     */
	    private boolean invalidCellValue(HSSFCell cell){
	     if(cell.getCellType()==HSSFCell.CELL_TYPE_BLANK){
	      return true;
	     }
	     else if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
	      if(cell.getRichStringCellValue().getString()==null||cell.getRichStringCellValue().getString().equals("")){
	       return true;
	      }
	     }
	     else if(cell.getCellType()==HSSFCell.CELL_TYPE_ERROR){
	      return true;
	     }
	     return false;
	    }
	    /**
	     * 将目标Excel文件的内容导入到数据表
	     * @param targetFile Excel文件路径
	     * @param entityName SDO数据实体全名
	     * @return 返回1 导入成功
	     *
	     * @throws Exception
	     */
	    public int importData(String targetFile,String entityName,int submitCount)throws Exception{
	        POIFSFileSystem fs =new POIFSFileSystem(new FileInputStream(targetFile));
	        HSSFWorkbook wb = new HSSFWorkbook(fs);
	        List<DataObject> dataObjects=new ArrayList<DataObject>();
	        for(int sheetCount=0;sheetCount<wb.getNumberOfSheets();sheetCount++){
	         HSSFSheet sheet = wb.getSheetAt(sheetCount);
	         int rows  = sheet.getPhysicalNumberOfRows();
	            initialize(sheet);
	            if(startRow==-1)
	             continue;
	           
	            //第一行为#字段名
	            //第二行为字段标题，因此内容读取从startRow+2
	            for(int rowCount=startRow+2;rowCount<rows;rowCount++){
	             HSSFRow sourceRow=sheet.getRow(rowCount);
	            //  System.out.println("entityName="+entityName);
	             DataObject importEntity=DataObjectUtil.createDataObject(entityName);
	             //判断某一行是否允许插入，当该行的所有列cell均为空白时不插入数据库
	             boolean allowInsert=false;
	             //以下构造导入的实体对象，并根据Excel单元格的内容填充实体属性值
	             for(int cellCount=0;cellCount<fieldNames.length;cellCount++){
	              String propertyName=fieldNames[cellCount];
	              HSSFCell cell=sourceRow.getCell((short)cellCount);
	              if(cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
	               continue;
	              allowInsert=true;
	              String value=null;
	              if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
	                if (HSSFDateUtil.isCellDateFormatted(cell)){
	                             SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                             value= dateFormat.format((cell.getDateCellValue()));
	                         
	                          }else{ 
	                             value=String.valueOf((long) cell.getNumericCellValue()); 
	                          }
	              }else if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
	               value=cell.getBooleanCellValue()+"";
	              }else{
	               value=cell.getRichStringCellValue().getString();
	              }
	              TypeReference typeReference=(TypeReference)importEntity.getType().getProperty(propertyName).getType();
	              Type propertyType=typeReference.getActualType();
	              if(propertyType instanceof IntType||propertyType instanceof IntegerType){
	               //防止可能出现的Excel表格读取自动加.号
	                 if(value.indexOf(".")!=-1)
	                value=value.substring(0,value.indexOf("."));
	               importEntity.set(fieldNames[cellCount],ChangeUtil.toInteger(value));
	              }
	              else if(propertyType instanceof BooleanType){
	               importEntity.set(fieldNames[cellCount],ChangeUtil.toBoolean(Boolean.valueOf(value)));
	              }
	              else if(propertyType instanceof FloatType){
	               importEntity.set(fieldNames[cellCount],ChangeUtil.toFloat(value));
	              }
	              else if(propertyType instanceof LongType){
	               if(value.indexOf(".")!=-1)
	                value=value.substring(0,value.indexOf("."));
	               importEntity.set(fieldNames[cellCount],ChangeUtil.toLong(value));
	              }
	              else if(propertyType instanceof DecimalType){
	               importEntity.set(fieldNames[cellCount],ChangeUtil.toBigDecimal(value));
	              }
	              else if(propertyType instanceof DateType){
	               importEntity.set(fieldNames[cellCount],ChangeUtil.changeToDBDate(value));
	              }
	              else if(propertyType instanceof DateTimeType){
	               importEntity.set(fieldNames[cellCount],ChangeUtil.toTimestamp(value));
	              }
	              else{
	               importEntity.set(fieldNames[cellCount], value);
	              }
	             }

	             if(dataObjects.size()<submitCount){
	              if(allowInsert)
	               dataObjects.add(importEntity);
	            
	             }else{
	              if(dataObjects.size()>0){
	               DatabaseUtil.insertEntityBatch("default", dataObjects.toArray(new DataObject[dataObjects.size()]));
	               dataObjects.clear();
	              }
	             }
	             if(rowCount==rows-1){
	              if(dataObjects.size()>0)
	               DatabaseUtil.insertEntityBatch("default", dataObjects.toArray(new DataObject[dataObjects.size()]));
	             }

	            }
	        }
	        System.out.println("zhi="+dataObjects.size());
	        return 1;
	    }
	    /**
	     *  再次导入数据
	     * @param empList 数据表中的数据
		  * @param entityList excel中获取的数据实体
		  * @param sum 数据条数的限制
		  * @return   成功标识 1代表成功
	     * @return
	     */
	    	public int impDataAgain(DataObject[] empList,DataObject[] entityList,int sum){
	    		 List<DataObject> newData=new ArrayList<DataObject>();
	    		 List<DataObject> oldData=new ArrayList<DataObject>();
	    		//定义数组
	    		 BigDecimal[] tempId=new BigDecimal[empList.length];
	    		 
	    		  boolean flag=true;
	    		  //得到数据表中的empid放入数组
	    		  for(int i=0;i<empList.length;i++){
	    			  tempId[i]=(BigDecimal) empList[i].get("orgid");
	    		  }
	    		  //empid数组放入集合
	    		 List tempList=	Arrays.asList(tempId);
	    		 //循环excel中的数据 ，得到orgid
	    		for (int i = 0; i < entityList.length; i++) {
	    			//看excel中的orgid是否已存在数据表中
	    		flag=tempList.contains(entityList[i].get("orgid"));
	    		 if(flag==false){
	    			 newData.add(entityList[i]);
	    		 }else{
	    			 oldData.add(entityList[i]);
	    		 }
				}
	    		//新增数据
	    		if(newData.size()==0){
	    		}else{
	    			try {
	    				 DatabaseUtil.insertEntityBatch(GitUtil.DEFAULT_DS_NAME, newData.toArray(new DataObject[newData.size()]));
					} catch (Exception e) {
					}
	    			
	    		}
	    		//修改数据
	    		if(oldData.size()==0){
	    		}else{
	    			try {
	    				System.out.println("==xiugai");
	    				 DatabaseUtil.updateEntityBatch(GitUtil.DEFAULT_DS_NAME,  oldData.toArray(new DataObject[oldData.size()]));
					} catch (Exception e) {
					}
	    		}
	    	System.out.println("new="+newData.size()+"=old="+oldData.size());
	    		
	    	return 1;
	    }
	    	/**
		     *  再次导入数据
		     * @param empList 数据表中的数据
			  * @param entityList excel中获取的数据实体
			  * @param sum 数据条数的限制
			  * @return   成功标识 1代表成功
		     * @return
		     */
		    	public int impEmpDataAgain(DataObject[] empList,DataObject[] entityList,int sum){
		    		 List<DataObject> newData=new ArrayList<DataObject>();
		    		 List<DataObject> oldData=new ArrayList<DataObject>();
		    		//定义数组
		    		 BigDecimal[] tempId=new BigDecimal[empList.length];
		    		 
		    		  boolean flag=true;
		    		  //得到数据表中的empid放入数组
		    		  for(int i=0;i<empList.length;i++){
		    			  tempId[i]=(BigDecimal) empList[i].get("empid");
		    		  }
		    		  //empid数组放入集合
		    		 List tempList=	Arrays.asList(tempId);
		    		 //循环excel中的数据 ，得到orgid
		    		for (int i = 0; i < entityList.length; i++) {
		    			//看excel中的orgid是否已存在数据表中
		    		flag=tempList.contains(entityList[i].get("empid"));
		    		 if(flag==false){
		    			 newData.add(entityList[i]);
		    		 }else{
		    			 oldData.add(entityList[i]);
		    		 }
					}
		    		//新增数据
		    		if(newData.size()==0){
		    		}else{
		    			try {
		    				 DatabaseUtil.insertEntityBatch(GitUtil.DEFAULT_DS_NAME, newData.toArray(new DataObject[newData.size()]));
						} catch (Exception e) {
						}
		    			
		    		}
		    		//修改数据
		    		if(oldData.size()==0){
		    		}else{
		    			try {
		    				System.out.println("==xiugai");
		    				 DatabaseUtil.updateEntityBatch(GitUtil.DEFAULT_DS_NAME,  oldData.toArray(new DataObject[oldData.size()]));
						} catch (Exception e) {
						}
		    		}
		    	System.out.println("new="+newData.size()+"=old="+oldData.size());
		    		
		    	return 1;
		    }
	    /**
	     * 将目标Excel文件的内容获得
	     * @param targetFile Excel文件路径
	     * @param entityName SDO数据实体全名
	     * @return 对象实体数据
	     *
	     * @throws Exception
	     */
	    public List entityData(String targetFile,String entityName,int submitCount)throws Exception{
	    	 POIFSFileSystem fs =new POIFSFileSystem(new FileInputStream(targetFile));
		        HSSFWorkbook wb = new HSSFWorkbook(fs);
		        List<DataObject> dataObjects=new ArrayList<DataObject>();
		        System.out.println("工作表个数=="+wb.getNumberOfSheets());
		        for(int sheetCount=0;sheetCount<wb.getNumberOfSheets();sheetCount++){
		         HSSFSheet sheet = wb.getSheetAt(sheetCount);
		         
		         int rows  = sheet.getPhysicalNumberOfRows();
		         System.out.println("rows="+rows);
		            initialize(sheet);
		            if(startRow==-1)
		             continue;
		           
		            //第一行为#字段名
		            //第二行为字段标题，因此内容读取从startRow+2
		            for(int rowCount=startRow+2;rowCount<rows;rowCount++){
		             HSSFRow sourceRow=sheet.getRow(rowCount);
		             //System.out.println("entityName="+entityName);
		             DataObject importEntity=DataObjectUtil.createDataObject(entityName);
		             //判断某一行是否允许插入，当该行的所有列cell均为空白时不插入数据库
		             boolean allowInsert=false;
		             //以下构造导入的实体对象，并根据Excel单元格的内容填充实体属性值
		             System.out.println("fieldNames.length="+fieldNames.length);
		             for(int cellCount=0;cellCount<fieldNames.length;cellCount++){
		              String propertyName=fieldNames[cellCount];
		              if(sourceRow !=null){
		              HSSFCell cell=sourceRow.getCell((short)cellCount);
		              if(cell == null)
		            	  continue;
		              if(cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
		               continue;
		              allowInsert=true;
		              String value=null;
		              if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
		                if (HSSFDateUtil.isCellDateFormatted(cell)){
		                             SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		                             value= dateFormat.format((cell.getDateCellValue()));
		                         
		                          }else{ 
		                             value=String.valueOf(cell.getNumericCellValue()); 
		                          }
		              }else if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
		               value=cell.getBooleanCellValue()+"";
		              }else{
		               value=cell.getRichStringCellValue().getString();
		              }
		              TypeReference typeReference=(TypeReference)importEntity.getType().getProperty(propertyName).getType();
		              Type propertyType=typeReference.getActualType();
		              if(propertyType instanceof IntType||propertyType instanceof IntegerType){
		               //防止可能出现的Excel表格读取自动加.号
		                 if(value.indexOf(".")!=-1)
		                value=value.substring(0,value.indexOf("."));
		               importEntity.set(fieldNames[cellCount],ChangeUtil.toInteger(value));
		              }
		              else if(propertyType instanceof BooleanType){
		               importEntity.set(fieldNames[cellCount],ChangeUtil.toBoolean(Boolean.valueOf(value)));
		              }
		              else if(propertyType instanceof FloatType){
		               importEntity.set(fieldNames[cellCount],ChangeUtil.toFloat(value));
		              }
		              else if(propertyType instanceof LongType){
		               if(value.indexOf(".")!=-1)
		                value=value.substring(0,value.indexOf("."));
		               importEntity.set(fieldNames[cellCount],ChangeUtil.toLong(value));
		              }
		              else if(propertyType instanceof DecimalType){
		               importEntity.set(fieldNames[cellCount],ChangeUtil.toBigDecimal(value));
		              }
		              else if(propertyType instanceof DateType){
		               importEntity.set(fieldNames[cellCount],ChangeUtil.changeToDBDate(value));
		              }
		              else if(propertyType instanceof DateTimeType){
		               importEntity.set(fieldNames[cellCount],ChangeUtil.toTimestamp(value));
		              }
		              else{
		               importEntity.set(fieldNames[cellCount], value);
		              }
		             }else{
		            	 allowInsert=false;
		             }
		             }
		             if(allowInsert)
			               dataObjects.add(importEntity);
			             }
		        }
//		        CriteriaType criteria = null;
//		        System.out.println("ExampleExcelEmp.QNAME="+ExampleExcelEmp.QNAME);
//		        criteria.set_entity(ExampleExcelEmp.QNAME);
//				IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
//				ExampleExcelEmp[] empList = getDASTemplate().queryEntitiesByCriteriaEntity(ExampleExcelEmp.class,dasCriteria);
//		    
//		        System.out.println("empList="+empList.length);
//		        for(DataObject empLists: empList){
//		        	System.out.println("canshuzhi="+empLists.get("empid"));
//		        }
		       // System.out.println("data="+dataObjects.size());
	        return dataObjects;
	    }
	    /**
	     * 将目标Excel文件的内容获得
	     * @param targetFile Excel文件路径
	     * @param entityName SDO数据实体全名
	     * @return 对象实体数据
	     *
	     * @throws Exception
	     */
	    public List entityData1(String targetFile,String entityName,int submitCount)throws Exception{
	    	POIFSFileSystem fs =new POIFSFileSystem(new FileInputStream(targetFile));
	    	HSSFWorkbook wb = new HSSFWorkbook(fs);
	    	List<DataObject> dataObjects=new ArrayList<DataObject>();
	    	//System.out.println("=="+wb.getNumberOfSheets());
	    	for(int sheetCount=0;sheetCount<wb.getNumberOfSheets();sheetCount++){
	    		HSSFSheet sheet = wb.getSheetAt(sheetCount);
	    		int rows  = sheet.getPhysicalNumberOfRows();
	    		// System.out.println("rows="+rows);
	    		initialize(sheet);
	    		if(startRow==-1)
	    			throw new EOSException("请使用正确导入模板！");
	    		
	    		//第一行为#字段名
	    		//第二行为字段标题，因此内容读取从startRow+2
	    		for(int rowCount=startRow+2;rowCount<rows;rowCount++){
	    			HSSFRow sourceRow=sheet.getRow(rowCount);
	    			//System.out.println("entityName="+entityName);
	    			DataObject importEntity=DataObjectUtil.createDataObject(entityName);
	    			//判断某一行是否允许插入，当该行的所有列cell均为空白时不插入数据库
	    			boolean allowInsert=false;
	    			//以下构造导入的实体对象，并根据Excel单元格的内容填充实体属性值
	    			System.out.println("fieldNames.length="+fieldNames.length);
	    			for(int cellCount=0;cellCount<fieldNames.length;cellCount++){
	    				String propertyName=fieldNames[cellCount];
	    				HSSFCell cell=sourceRow.getCell(cellCount);
	    				if(cell == null)
	    					continue;
	    				if(cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
	    					continue;
	    				allowInsert=true;
	    				String value=null;
	    				if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
	    					if (HSSFDateUtil.isCellDateFormatted(cell)){
	    						SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    						value= dateFormat.format((cell.getDateCellValue()));
	    						
	    					}else{ 
	    						if("pyeAcctNo".equals(propertyName)){
	    							value=String.valueOf((long)cell.getNumericCellValue());
	    						}else{
		    						value=String.valueOf((double)cell.getNumericCellValue()); 
	    						}
	    					}
	    				}else if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
	    					value=cell.getBooleanCellValue()+"";
	    				}else{
	    					value=cell.getRichStringCellValue().getString();
	    				}
	    				if("pyeAcctNo".equals(propertyName)){
	    					if(!value.matches("^\\d+$")){
	    						throw new EOSException("收款人账号必须是数字");
	    					}
	    				}
	    				if("loanAmt".equals(propertyName)){
	    					if(!value.matches("^(([1-9]\\d+)|0)(\\.\\d{1,2})?$")){
	    						throw new EOSException("出票金额必须是数字");
	    					}
	    				}
	    				TypeReference typeReference=(TypeReference)importEntity.getType().getProperty(propertyName).getType();
	    				Type propertyType=typeReference.getActualType();
	    				if(propertyType instanceof IntType||propertyType instanceof IntegerType){
	    					//防止可能出现的Excel表格读取自动加.号
	    					if(value.indexOf(".")!=-1)
	    						value=value.substring(0,value.indexOf("."));
	    					importEntity.set(fieldNames[cellCount],ChangeUtil.toInteger(value));
	    				}
	    				else if(propertyType instanceof BooleanType){
	    					importEntity.set(fieldNames[cellCount],ChangeUtil.toBoolean(Boolean.valueOf(value)));
	    				}
	    				else if(propertyType instanceof FloatType){
	    					importEntity.set(fieldNames[cellCount],ChangeUtil.toFloat(value));
	    				}
	    				else if(propertyType instanceof LongType){
	    					if(value.indexOf(".")!=-1)
	    						value=value.substring(0,value.indexOf("."));
	    					importEntity.set(fieldNames[cellCount],ChangeUtil.toLong(value));
	    				}
	    				else if(propertyType instanceof DecimalType){
	    					importEntity.set(fieldNames[cellCount],ChangeUtil.toBigDecimal(value));
	    				}
	    				else if(propertyType instanceof DateType){
	    					importEntity.set(fieldNames[cellCount],ChangeUtil.changeToDBDate(value));
	    				}
	    				else if(propertyType instanceof DateTimeType){
	    					importEntity.set(fieldNames[cellCount],ChangeUtil.toTimestamp(value));
	    				}
	    				else{
	    					//账号是一串数字会自动加上.0
	    					if(value.indexOf(".")!=-1)
	    						value=value.substring(0,value.indexOf("."));
	    					importEntity.set(fieldNames[cellCount], value);
	    				}
	    			}
	    			
	    			if(allowInsert)
	    				dataObjects.add(importEntity);
	    		}
	    	}
//		        CriteriaType criteria = null;
//		        System.out.println("ExampleExcelEmp.QNAME="+ExampleExcelEmp.QNAME);
//		        criteria.set_entity(ExampleExcelEmp.QNAME);
//				IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
//				ExampleExcelEmp[] empList = getDASTemplate().queryEntitiesByCriteriaEntity(ExampleExcelEmp.class,dasCriteria);
//		    
//		        System.out.println("empList="+empList.length);
//		        for(DataObject empLists: empList){
//		        	System.out.println("canshuzhi="+empLists.get("empid"));
//		        }
	    	// System.out.println("data="+dataObjects.size());
	    	return dataObjects;
	    }
    
	    
	  
	    
	    
	    
	    /**
	     * 如果模板文件是否存在
	     * @param filename 模板文件名
	     * @return 文件存在返回true,否则false
	     * @throws IOException
	     */
	    protected boolean isExistTemplate(String templateFile)throws IOException{
	     File file=new File(templateFile);
	     return file.exists();
	    }

	    /**
	     * 预初始化模板文件<BR>
	     * 当用户指定的模板文件不存在时，将自动生成指定的模板文件，并第一行设置为要导出的字段列
	     * @param templateFile 模板文件名
	     * @param dataObject 数据实体对象
	     * @throws Exception
	     */
	    public void prepareInitializeTemplate(String templateFile,DataObject dataObject) throws Exception{
	      HSSFWorkbook wb = new HSSFWorkbook();
	      FileOutputStream fileOut = new FileOutputStream(templateFile);
	      HSSFSheet sheet = wb.createSheet("new sheet");
	      //设置模板的第一行为输出字段定义列
	      HSSFRow row = sheet.createRow((short)0);
	      Object[] properties=dataObject.getType().getDeclaredProperties().toArray();
	      for(int i=0;i<properties.length;i++){
	       PropertyImpl property=(PropertyImpl)properties[i];
	       HSSFCell cell = row.createCell((short)i);
	       HSSFRichTextString text=new HSSFRichTextString("#"+property.getName());
	       cell.setCellValue(text);
	      }
	      wb.write(fileOut);
	      fileOut.close();
	    }

	
	}
