package com.bos.pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.bos.utp.tools.SystemInfo;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.access.http.UploadFile;

/**
 * @author 王世春
 */
@Bizlet("文档上传下载")
public class DocumentUtil {
	@Bizlet("文档上传")
	public static void upload(Map<String, Object> param, UploadFile file)
			throws FileNotFoundException, IOException {
		if (null == param.get("bizNum")) {
			writeMessage(GitUtil.getResponse(), "业务编号不能为空！");
			return;
		}

		if (null == param.get("docName")
				&& StringUtils.isNotEmpty(file.getClientFileName()))
			param.put("docName", file.getClientFileName());
		String surfix = "none";
		if (file.getFilePath().contains(".")) {
			surfix = file.getFilePath().substring(
					file.getFilePath().lastIndexOf(".") + 1);
			param.put("surfix", surfix);
		}
		if (surfix.equals("html") || surfix.equals("htm")
				|| surfix.equals("js") || surfix.equals("css")
				|| surfix.equals("jsp") || surfix.equals("ext")
				|| surfix.equals("ext2") || surfix.equals("flow")) {
			writeMessage(GitUtil.getResponse(), "该类型的文件不允许上传！");
			return;
		}
		if (null == param.get("docType") && file.getFilePath().contains("."))
			param.put("docType", surfix);

		upload(param, new FileInputStream(file.getFilePath()));
		try {
			// 处理完成后删除临时文件
			new File(file.getFilePath()).delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bizlet("文档上传")
	public static void upload(Map<String, Object> param, InputStream in)
			throws IOException {
		String docName = param.get("docName").toString();
		String docType = param.get("docType").toString();
		String docNote = (null == param.get("docNote")) ? "" : param.get(
				"docNote").toString();
		String bizNum = param.get("bizNum").toString();
		String bizPhase = null == param.get("bizPhase") ? "" : param.get(
				"bizPhase").toString();

		byte[] b = new byte[1024];
		int i = 0;
		FileOutputStream out = null;
		try {
			String docId = GitUtil.genUUIDString(); // 文档ID

			// 保存文件
			String path = ConfigurationUtil.getUserConfigSingleValue(
					"CustomConfig", "System", "documentPath");
			path = path.replaceAll("\\\\", "/");
			if (path.endsWith("/") == false)
				path += "/";

			String relativePath = "";
			relativePath += docType + "/" + bizNum;
			if (StringUtils.isNotEmpty(bizPhase))
				relativePath += "/" + bizPhase;
			File file = new File(path + relativePath);
			if (file.exists() == false)
				file.mkdirs();
			relativePath += "/" + docId;
			if (docName.contains(".") == false)
				relativePath += "." + param.get("surfix");
			else
				relativePath += docName.substring(docName.lastIndexOf("."));

			out = new FileOutputStream(path + relativePath);
			while ((i = in.read(b)) > 0) {
				out.write(b, 0, i);
			}

			// 记录文档上传到数据库
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("doc_id", docId);
			map.put("doc_name", docName);
			map.put("doc_type", docType);
			map.put("doc_path", relativePath);
			map.put("doc_note", docNote);
			map.put("biz_num", bizNum);
			map.put("biz_phase", bizPhase);
			map.put("create_time", GitUtil.getBusiTimestamp());
			map.put("userNum", GitUtil.getCurrentUserId());
			map.put("orgNum", GitUtil.getCurrentOrgCd());
			DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.updown.insertDoc", map);

			map.put("op_type", "上传");
			map.put("rec_id", GitUtil.genUUIDString());
			DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME,
					"com.bos.pub.updown.insertDocRecord", map);

			// 上传完成后，刷新页面或跳转到空白页面
			ServletResponse res = GitUtil.getResponse();
			res.setContentType("text/html;charset=UTF-8");
			String page = null;
			String contextPath = ((HttpServletRequest) GitUtil.getRequest())
					.getContextPath();
			if (null != param.get("redirectPage"))
				page = param.get("redirectPage").toString();
			else
				page = "/common/blank.jsp";
			if (page.startsWith("/") == false)
				page = "/" + page;

			if (page.startsWith(contextPath) == false)
				page = contextPath + page;
			((HttpServletResponse) res).sendRedirect(page);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
			}
		}
	}

	private static void writeMessage(ServletResponse res, String msg) {
		try {
			res.reset();
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter wr = res.getWriter();
			wr.write("<html><body><script>alert('");
			wr.write(msg);
			wr.write("');</script></body></html>");
			res.flushBuffer();
		} catch (IOException e) {
		}
	}

	@Bizlet("文档下载")
	public static void download(Map<String, Object> param) throws IOException {
		String resCode = "success";
		String resMsg = "下载成功";
		String docName = null;
		File file = null;
		Object[] docs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.pub.updown.selectDoc", param);
		if (docs != null && docs.length >= 1) {
			Map doc = (Map) docs[0];
			String docId = doc.get("DOC_ID").toString();
			docName = doc.get("DOC_NAME").toString();
			String docPath = "";
			if (null != doc.get("DOC_PATH"))
				docPath = doc.get("DOC_PATH").toString();

			if (docPath.length() >= 1 && docId.length() >= 1 && docName.length() >= 1) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("doc_id", docId);
				map.put("op_type", "下载");
				map.put("rec_id", GitUtil.genUUIDString());
				map.put("create_time", GitUtil.getBusiTimestamp());
				map.put("userNum", GitUtil.getCurrentUserId());
				map.put("orgNum", GitUtil.getCurrentOrgCd());
				
				DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.updown.insertDocRecord", map);

				String path = ConfigurationUtil.getUserConfigSingleValue("CustomConfig", "System", "documentPath");
				path = path.replaceAll("\\\\", "/");
				if (path.endsWith("/") == false)
					path += "/";
				file = new File(path + docPath);
				if (file.exists()) {
					if (docName.contains(".") == false && docPath.contains("."))
						docName += docPath.substring(docPath.lastIndexOf("."));
				}else{
					resCode = "failed";
					resMsg = "文档未上传！";
				}
			}else{
				resCode = "failed";
				resMsg = "文档不存在，或信息不完整";
			}
		}else{
			resCode = "failed";
			resMsg = "文档不存在";
		}
		
		ServletResponse res = GitUtil.getResponse();
		HttpServletResponse httpRes = (HttpServletResponse) res;
		httpRes.reset();
		httpRes.setContentType("application/octet-stream");
		httpRes.setHeader("Content-disposition",
				"attachment;filename=\""+ java.net.URLEncoder.encode(docName, "UTF-8") + "\"");
		OutputStream out = httpRes.getOutputStream();
		if(resCode.equals("success")){
			FileInputStream in = new FileInputStream(file);
			int i = 0;
			byte[] b = new byte[1024];
			while ((i = in.read(b)) > 0) {
				out.write(b, 0, i);
			}
			in.close();
		}else{
			String resScript = "<html><body><script>alert('"+resMsg+"');</script></body></html>";
			out.write(resScript.getBytes("UTF-8"));
		}
		if (out != null)
			out.close();
		httpRes.flushBuffer();
	}


	@Bizlet("判断是否已上传某类文档")
	public static boolean hasUpload(Map<String, Object> param) {
		return false;
	}
	
	@Bizlet("获取文件上传目录")
	public static String getFilePath(){
		
		String path = ConfigurationUtil.getUserConfigSingleValue(
				"CustomConfig", "System", "documentPath");
		path = path.replaceAll("\\\\", "/");
		if (path.endsWith("/") == false){
			
			path += "/";
		}
		if(null != path && path.startsWith("/")){
			
			return SystemInfo.APP_WAR_PATH+path.substring(1) ;
		}else{
			
			return SystemInfo.APP_WAR_PATH+path ;
		}
	}
}


