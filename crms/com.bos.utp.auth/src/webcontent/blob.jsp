<%@page pageEncoding="UTF-8"%>
<%@page import="javax.servlet.ServletOutputStream"%>
<%@page import="java.io.*"%>
<%@page import="com.eos.web.taglib.util.*" %><%
	  String fileNotExist=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_fileNotExist");
      
      String attachment=(String)request.getParameter("attachment");
      String fileType=java.net.URLDecoder.decode(request.getParameter("fileType"),"UTF-8");
      String fileName=java.net.URLDecoder.decode(request.getParameter("fileName"),"UTF-8");
      String filePath=java.net.URLDecoder.decode(request.getParameter("filePath"),"UTF-8");
      
      response.reset();
      if(fileType!=null&&fileType.length()>0)
         response.setContentType(fileType);
         
      if("true".equals(attachment))
 	  	  response.setHeader("Content-disposition", "attachment;filename=\""+ fileName + "\"");
      
	  byte[] buffer = new byte[512]; 
	  int size = 0; 
	  ServletOutputStream os = null;
	  FileInputStream in = null;
	  try {
	     os = response.getOutputStream();
	     File downloadFile=new File(filePath);
	     if(downloadFile!=null&&downloadFile.exists()){
		     in = new FileInputStream(new File(filePath));
		     while ((size = in.read(buffer)) != -1) { 
		       os.write(buffer, 0, size); 
		     }
		    out.clear();
         	out = pageContext.pushBody();
	     }else{
	        out.print(fileNotExist);
	     }
  	   } catch(Exception e) {
          e.printStackTrace();
       } finally {
            try {
             if(in!=null)in.close();
		     if(os!=null)os.close();

		   } catch (IOException e) {
		     e.printStackTrace();
		   }
       }
%>