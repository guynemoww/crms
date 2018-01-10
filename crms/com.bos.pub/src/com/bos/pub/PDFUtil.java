/**
 * 
 */
package com.bos.pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.bos.utp.tools.SystemInfo;
import com.eos.system.annotation.Bizlet;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import commonj.sdo.DataObject;

/**
 * @author 王世春
 * @date 2013-09-12 12:28:08
 * 
 */
@Bizlet("")
public class PDFUtil {

	@Bizlet("读取pdf文档，根据传入的params中的key，替换名称为key的文本域内容为对应的value，实现pdf文档修改并输出到客户端的目的")
	public String pdfTextReplace(String pdfTemplate, DataObject params)
			throws Exception {
		if (new File(pdfTemplate).isFile() == false) {
			if (pdfTemplate.startsWith(SystemInfo.FILE_SEPARATOR) == false) {
				pdfTemplate = SystemInfo.FILE_SEPARATOR + pdfTemplate;
			}
			pdfTemplate = SystemInfo.APP_WAR_PATH + "document"
					+ SystemInfo.FILE_SEPARATOR + "pdf" + pdfTemplate;
		}
		if (new File(pdfTemplate).isFile() == false || null == params) {
			new Exception("没有传入pdf模板路径或该路径无效").printStackTrace();
			return null;
		}

		// String tempfile = SystemInfo.APP_WAR_PATH + "document"
		// + SystemInfo.FILE_SEPARATOR + "pdf" + SystemInfo.FILE_SEPARATOR
		// + "temp";
		// File out = new File(tempfile);
		// if (out.exists() == false) {
		// out.mkdirs();
		// }
		// Object userid = null;
		// try {
		// userid = DataContextUtil.get("m:userObject/userId");
		// } catch (Exception e) {
		// userid = DataContextUtil.get("s:userObject/userId");
		// }
		// if (null == userid || userid.toString().length() == 0) {
		// userid = System.currentTimeMillis();
		// }
		// tempfile = tempfile + SystemInfo.FILE_SEPARATOR + userid + ".pdf";

		// pdfTextReplace(new FileInputStream(pdfTemplate), new
		// FileOutputStream(
		// tempfile), params);
		String filename = "";
		if (StringUtils.isNotEmpty(params.getString("filename"))) {
			filename = params.getString("filename");
			if (filename.contains("/"))
				filename = filename.substring(filename.lastIndexOf("/") + 1);
			if (filename.contains("\\"))
				filename = filename.substring(filename.lastIndexOf("\\") + 1);
		} else {
			filename = GitUtil.getCurrentUserId() + ".pdf";
		}
		ServletResponse res = GitUtil.getResponse();
		res.reset();
		res.resetBuffer();
		res.setContentType("application/pdf");
		((HttpServletResponse) res).setHeader("Content-disposition",
				"attachment;filename=\""
						+ java.net.URLEncoder.encode(filename, "UTF-8") + "\"");
		OutputStream out = res.getOutputStream();
		pdfTextReplace(new FileInputStream(pdfTemplate), out, params);
		out.close();

		// return tempfile;
		return "";
	}

	public void pdfTextReplace(InputStream pdfTemplateInputStream,
			OutputStream pdfResultOutputStream, DataObject params)
			throws Exception {
		PdfReader pdf = new PdfReader(pdfTemplateInputStream);
		PdfStamper stamper = new PdfStamper(pdf, pdfResultOutputStream);
		AcroFields fields = stamper.getAcroFields();
		for (Object fieldname : fields.getFields().keySet()) {
			Object value = params.get(String.valueOf(fieldname));
			if (value == null) {
				value = "";
			}
			fields.setField(String.valueOf(fieldname), value.toString());
		}

		stamper.close();
	}

	/**
	 * @param args
	 * @author 王世春
	 */
	public static void main(String[] args) {
	}

}
