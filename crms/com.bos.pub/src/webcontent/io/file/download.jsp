<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<%@page import="com.bos.pub.io.IoUtil"%>
<%@page import="java.net.URLEncoder"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.bos.pub.web.JspUtil"%>
<%@page import="com.eos.foundation.eoscommon.LogUtil"%>
<%@page import="javax.servlet.ServletOutputStream"%>
<%@page import="com.eos.foundation.eoscommon.ConfigurationUtil"%>
<%@page import="java.io.*"%>
<%
	String filePath = JspUtil.getParameter(request, "downloadFile", null);//过滤前后空格
	if (filePath == null) {
		filePath = (String) request.getAttribute("downloadFile");
		if (filePath == null) {
			String path = ConfigurationUtil.getUserConfigSingleValue("CustomConfig", "System", "documentPath");
			filePath = path + File.separatorChar + "download.xls";
		}
	}
	File file = new File(filePath);
	if (!file.exists()) {
		LogUtil.logInfo(">>>>download file:{0}} {1}}", null, new Object[] { filePath, com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_fileNotExist") }); //"文件不存在！"
		filePath = filePath.replace("\\", "\\\\");
		out.print("<html><script type=\"text/javascript\">alert('未找到需要下载的文件[" + filePath + "]" + "');</script></html>");
		return;
	}
	String deleteFile = JspUtil.getParameter(request, "deleteFile", null);//过滤前后空格
	if (deleteFile == null) {
		deleteFile = (String) request.getAttribute("deleteFile");
	}
	boolean delete = "true".equals(deleteFile);
	System.out.println("****** download file[" + filePath + "][" + file.getName() + "][" + (delete ? "delete" : "") + "] ******");
	//
	response.reset();
	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
	//
	IoUtil.download(response.getOutputStream(), file, delete);
	out = pageContext.pushBody();
%>