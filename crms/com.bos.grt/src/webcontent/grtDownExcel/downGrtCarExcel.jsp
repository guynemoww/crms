<%@page pageEncoding="UTF-8"%>
<%@page import="javax.servlet.ServletOutputStream"%>
<%@page import="java.io.*"%>
<%@page import="com.eos.web.taglib.util.*" %><%
   //获取标签中使用的国际化资源信息
   String fileNotExist=com.eos.foundation.eoscommon.ResourcesMessageUtil.getI18nResourceMessage("l_fileNotExist");
      Object root= com.eos.web.taglib.util.XpathUtil.getDataContextRoot("request", pageContext);
      String localFile=(String)XpathUtil.getObjectByXpath(root,"downloadFile");
   
      System.out.println(">>>>download file is "+localFile);
   byte[] buffer = new byte[512];
   int size = 0;
   response.reset();
   response.setContentType("application/vnd.ms-excel");
    //response.setHeader("Content-disposition", "attachment;filename=\""+ java.net.URLEncoder.encode(localFile,"UTF-8") + "\"");
    response.setHeader("Content-disposition", "attachment;filename=\""+ java.net.URLEncoder.encode("GrtCarTemp.xls","UTF-8") + "\"");
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
         out.print(fileNotExist); //"文件不存在！"
      }
      } catch(Exception e) {
          e.printStackTrace();
       } finally {
            try {
             if(in!=null)in.close();
       if(os!=null)os.close();
       File file=new File(localFile);
       if (file!=null&&file.isFile()&&file.exists()) {
       //   file.delete();
       }
     } catch (IOException e) {
       e.printStackTrace();
     }
       }
%>
