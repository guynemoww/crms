
package com.bos.pub.document.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import com.bos.batch.DateUtil;
import com.bos.utp.tools.SystemInfo;
import com.eos.foundation.data.DataContextUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * FopReport
 * @author bin.yin 2012/12/23
 */
@Bizlet("")
public class FopReport {
	/*private static Logger log = new ConsoleLogger(ConsoleLogger.LEVEL_DEBUG);*/
	// Step 1: Construct a FopFactory
	private static final FopFactory fopFactory = FopFactory.newInstance();

	/**
	 * 根据xsl模板及xml数据文件生成pdf
	 * @param xsltFile xsl模板
	 * @param xmlFile xml数据文件
	 * @return ReportData
	 * @throws Exception
	 * @author bin.yin 2012/12/25
	 */
	public static ReportData createReport(String xsltFile, String xmlFile) throws Exception {
		ReportData reportData = new ReportData();
		reportData.setContentType("application/pdf");
		
		
		String fopPath="";
    	
    	if (new File(fopPath).isFile() == false) {
			if (fopPath.startsWith(SystemInfo.FILE_SEPARATOR) == false) {
				fopPath = SystemInfo.FILE_SEPARATOR + fopPath;
			}
			fopPath = SystemInfo.APP_WAR_PATH + "document"
					+ SystemInfo.FILE_SEPARATOR + "docx/loanNotice/conf/fop.xml";
		}
    	
    	String fontPath=SystemInfo.APP_WAR_PATH + "document"
				+ SystemInfo.FILE_SEPARATOR + "docx/loanNotice/conf/fonts/";
    	
    	BufferedWriter bw = null;
    	BufferedReader br = null;
    	try{
    		br=new BufferedReader(new FileReader(new File(fopPath)));
        	String valueString = null;
        	StringBuffer sb=new StringBuffer();
            while ((valueString=br.readLine())!=null){
//    	         if(valueString.contains("<font-base>")&&valueString.contains("</font-base>")){
//    	        	 valueString="<font-base>"+fontPath+"</font-base>";
//    	        	 sb.append(valueString);
//    	         }else{
    	        	 sb.append(valueString);
//    	         }
            }
        	String string = sb.toString();
        	int startint = string.indexOf("<font-base>");
        	int endint = string.indexOf("</font-base>");
        	String substring = string.substring(startint, endint);
        	
        	String newString="<font-base>"+fontPath.replaceAll("\\\\", "/");
        	
        	string=string.replaceAll(substring, newString);
        	
            bw=new BufferedWriter(new FileWriter(new File(fopPath)));
            bw.write(string);
    	}catch(IOException e){
    		e.printStackTrace();
    		System.out.println("fop xml文件修改路径错误");
    	}finally{
    		if(null!=br){
    			br.close();
    		}
    		if(null!=bw){
    			bw.close();
    		}
    	}
    	
        
        
    	
    	String xslPath="";
    	
    	if (new File(xslPath).isFile() == false) {
			if (xslPath.startsWith(SystemInfo.FILE_SEPARATOR) == false) {
				xslPath = SystemInfo.FILE_SEPARATOR + xslPath;
			}
			xslPath = SystemInfo.APP_WAR_PATH + "document"
					+ SystemInfo.FILE_SEPARATOR + "docx/loanNotice/DKHD.xsl";
		}
    	
		fopFactory.setUserConfig(fopPath);

		// Step 2: Set up output stream.
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			// Step 3: Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

			// Step 4: Setup XSLT using identity transformer
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(new File(xslPath)));

			// Step 5: Setup input and output for XSLT transformation
			Source src = new StreamSource(new File(xmlFile));
			// Source src = new StreamSource(new StringReader(myString));

			// Step 6: Resulting SAX events (the generated FO) must be piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Step 7: Start XSLT transformation and FOP processing
			transformer.transform(src, res);

			reportData.setData(out.toByteArray());
		} catch(Exception e) {
			throw e;
		} finally {
			out.close();
		}
		return reportData;
	}

	public static ReportData createReportS(String xsltFile, String xml) throws Exception {
		ReportData reportData = new ReportData();
		reportData.setContentType("application/pdf");
		
		
		String fopPath="";
    	
    	if (new File(fopPath).isFile() == false) {
			if (fopPath.startsWith(SystemInfo.FILE_SEPARATOR) == false) {
				fopPath = SystemInfo.FILE_SEPARATOR + fopPath;
			}
			fopPath = SystemInfo.APP_WAR_PATH + "document"
					+ SystemInfo.FILE_SEPARATOR + "docx/loanNotice/conf/fop.xml";
		}
    	
    	String xslPath="";
    	
    	if (new File(xslPath).isFile() == false) {
			if (xslPath.startsWith(SystemInfo.FILE_SEPARATOR) == false) {
				xslPath = SystemInfo.FILE_SEPARATOR + xslPath;
			}
			xslPath = SystemInfo.APP_WAR_PATH + "document"
					+ SystemInfo.FILE_SEPARATOR + "docx/loanNotice/DKHD.xsl";
		}
    	
    	
		System.out.println(fopPath);
    	
		fopFactory.setUserConfig(fopPath);

		// Step 2: Set up output stream.
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			// Step 3: Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

			// Step 4: Setup XSLT using identity transformer
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(new File(xslPath)));

			// Step 5: Setup input and output for XSLT transformation
//			Source src = new StreamSource(new File(xmlFile));
			 Source src = new StreamSource(new StringReader(xml));

			// Step 6: Resulting SAX events (the generated FO) must be piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Step 7: Start XSLT transformation and FOP processing
			transformer.transform(src, res);

			reportData.setData(out.toByteArray());
		} catch(Exception e) {
			throw e;
		} finally {
			out.close();
		}
		return reportData;
	}

	/**
	 * 根据xsl模板及xml字节数组生成pdf
	 * @param xsltFile xsl模板
	 * @param bXmlData xml字节数组 eg. StringBuffer buf = new StringBuffer(); buf.getBytes("UTF-8");
	 * @return ReportData
	 * @throws Exception
	 * @author bin.yin 2012/12/25
	 */
	public static ReportData createReport(String xsltFile, byte[] bXmlData) throws Exception {
		ReportData reportData = new ReportData();
		try {
			// convert xml bytes to a temp file
			File xmlFile = File.createTempFile("FOP", ".tmp");
			FileOutputStream fos = new FileOutputStream(xmlFile);
			fos.write(bXmlData);
			fos.close();
			
			reportData = createReport(xsltFile, xmlFile.getAbsolutePath());
			// delete temp file
			xmlFile.delete();
		} catch (Exception e) {
			throw e;
		}
		return reportData;
	}

	@Bizlet("")
	public String xmlCovertpdf(Map<String,Object> dataMap,String templateName){
		DataObject[] db=(DataObject[]) dataMap.get("obj");
		String userName=(String) dataMap.get("userName");
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buf.append("<ItemListReport>");
			for(int i=0;i<db.length;i++){
				buf.append("		<Table>");
				buf.append("				<Rq>日期："+db[i].getString("RCV_DATE")+"</Rq>");
				buf.append("				<Jjbh>"+db[i].getString("SUMMARY_NUM")+"</Jjbh>");
				buf.append("				<Dkrmc>"+db[i].getString("PARTY_NAME")+"</Dkrmc>");
				buf.append("				<Hkzh>"+db[i].getString("ZH")+"</Hkzh>");
				buf.append("				<Hkhm>"+db[i].getString("ZHMC")+"</Hkhm>");
				buf.append("				<Ll>"+db[i].getString("YEAR_RATE")+"</Ll>");
				buf.append("				<Chbj>"+db[i].getString("rmbAmt")+"</Chbj>");
				buf.append("				<Chlx>"+db[i].getString("CHLX")+"</Chlx>");
				buf.append("				<Chfx>"+db[i].getString("CHFX")+"</Chfx>");
				buf.append("				<PrintDate>2012-12-12</PrintDate>");
				buf.append("				<Jb>"+userName+"</Jb>");
				buf.append("		</Table>");
			}
			buf.append("</ItemListReport>");
			
			Object userid = null;
			try {
				userid = DataContextUtil.get("s:userObject/userId");
			} catch (Exception e) {
				userid = DataContextUtil.get("m:userObject/userId");
			}
			if (null == userid || userid.toString().length() == 0) {
				userid = System.currentTimeMillis();
			}
			
			if (templateName.contains("/"))
				templateName = templateName.substring(templateName.lastIndexOf("/") + 1);
			if (templateName.contains("\\"))
				templateName = templateName.substring(templateName.lastIndexOf("\\") + 1);
			if (templateName.endsWith(".pdf") == false) {
				templateName += ".pdf";
			}
			
			String formatTime = DateUtil.formatTime();
			
			
			templateName = SystemInfo.APP_WAR_PATH + "document"
					+ SystemInfo.FILE_SEPARATOR + "docx/temp/"+formatTime +templateName;
			
			ReportData data = FopReport.createReport(templateName, buf.toString().getBytes("UTF-8"));
			FileOutputStream fos = new FileOutputStream(templateName);
			fos.write(data.getData());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return templateName;
		
	}

}
