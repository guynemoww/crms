<%@page import="com.bos.utp.tools.SystemInfo"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.eos.web.taglib.util.XpathUtil"%>
<%@page import="com.eos.foundation.eoscommon.LogUtil"%>
<%@page import="javax.servlet.ServletOutputStream"%>
<%@page import="java.io.*"%><%
	  //获取标签中使用的国际化资源信息
	  String fileNotExist=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_fileNotExist");
      String localFile=(String)XpathUtil.getStringValue("r",pageContext,"filePath");
      String localFileName=(String)XpathUtil.getStringValue("r",pageContext,"fileName");
      LogUtil.logInfo(">>>>download file is {0}",null ,new Object[]{localFile});
      
      if (null == localFile || localFile.contains(SystemInfo.FILE_SEPARATOR) == false) {
      		out.print("<html><script type=\"text/javascript\">alert('" 
      			+(null == localFile || localFile.length() == 0 ? "该文件尚未上传，或不存在！" : localFile)
      			+"');</script></html>");
      		return;
      }
      
	  byte[] buffer = new byte[512];
	  int size = 0; 
	  response.reset();
	  response.setContentType("application/octet-stream");
 	  response.setHeader("Content-disposition", "attachment;filename=\""+ java.net.URLEncoder.encode(localFileName,"UTF-8") + "\"");
	  ServletOutputStream os = null;
	  FileInputStream in = null;
	  try {
	     os = response.getOutputStream();
	     File downloadFile=new File(localFile);
	     if(downloadFile!=null&&downloadFile.exists()){
		     in = new FileInputStream(new File(localFile));
		     while ((size = in.read(buffer)) != -1) {
		       os.write(buffer, 0, size); 
		     }
		    out.clear();
         	out = pageContext.pushBody();
	     }else{
	         LogUtil.logInfo(">>>>download file:{0}} {1}}",null ,new Object[]{localFile,fileNotExist}); //"文件不存在！"
	     }
  	   } catch(Exception e) {
          e.printStackTrace();
       } finally {
            try {
             if(in!=null)in.close();
		     if(os!=null)os.close();
		     File file=new File(localFile);
		     if (file!=null&&file.isFile()&&file.exists()) {
		       //file.delete();
		     }

		   } catch (IOException e) {
		     e.printStackTrace();
		   }
       }
%>