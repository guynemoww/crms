package com.bos.utp.auth.taglib;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;


import com.bos.utp.tools.SystemInfo;
import com.eos.common.connection.ConnectionHelper;
import com.eos.das.entity.DASManager;
import com.eos.das.entity.IDASSession;
import com.eos.runtime.core.ApplicationContext;
import com.eos.system.exception.EOSRuntimeException;
import com.eos.system.utility.StringUtil;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.RequestUtil;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import commonj.sdo.DataObject;

/**
 * 
 * 大字段内容的输出，依赖于数据实体com.bos.utp.dataset.utility.AtFileupload<BR>
 * 如果attachment为false,则将文件直接输出到当前页面,通常为图片文件输出<BR>
 * 如果attachment为true,则以文件连接方式显示,点击文件则下载该文件.
 * 
 * @author 翁增仁
 * wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史 $Log: BlobOutputTag.java,v $
 * 修改历史 Revision 1.13  2010/12/07 03:09:46  caisy
 * 修改历史
 * 修改历史 Revision 1.12  2010/12/01 03:23:14  caisy
 * 修改历史 配置文件读取方式修改
 * 修改历史
 * 修改历史 Revision 1.11  2010/11/30 16:12:23  caisy
 * 修改历史 编码改为UTF-8
 * 修改历史
 * 修改历史 Revision 1.10  2009/03/30 05:39:39  caisy
 * 修改历史 代码规范
 * 修改历史
 * 修改历史 Revision 1.9  2009/03/16 07:38:25  caisy
 * 修改历史 Update:消除编译警告
 * 修改历史
 * 修改历史 Revision 1.8  2009/02/23 10:04:42  zhujiang
 * 修改历史 *** empty log message ***
 * 修改历史 Revision 1.7 2009/02/16 09:41:25 gengdw
 * bug修改
 * 
 * Revision 1.1 2008/11/29 12:31:09 wengzr
 * Refactor:重构BlobFieldTag->BlobOutputTag
 * 
 * Revision 1.2 2008/11/28 04:15:22 wengzr Added:增BlogFieldTag大字段输出标签
 * Refactor:重构AuthConfiguration
 * 
 * Revision 1.1 2008/11/26 13:55:53 wengzr Added:增加大字段输出标签
 * 
 */
public class BlobOutputTag extends BaseIteratorTagSupport {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -4349091383789006903L;

	// 大字段属性名
	private String blobProperty;

	// 是否以附件形式存在
	private boolean attachment;

	// 指定文件名
	private String fileName;

	private String fileNewName;

	// 指定文件类型
	private String fileType;

	// 指定文件显示路径
	private String filePath;

	private StringBuffer html;

	// 文件存取类型，1为文件系统，2为数据库系统
	private String fileSave;

	/**
	 * 是否附件
	 * 
	 * @return
	 */
	public boolean isAttachment() {
		return attachment;
	}

	public void setAttachment(boolean attachment) {
		this.attachment = attachment;
	}

	/**
	 * 大字段名称
	 * 
	 * @return
	 */
	public String getBlobProperty() {
		return blobProperty;
	}

	public void setBlobProperty(String blobField) {
		this.blobProperty = blobField;
	}

	public int doStartTag() throws JspException {
		//TODO需确认initAtributes是否需要调用
		// initAttributes();
		DataObject dataObject = null;
		// 获取当前数据对象，如果存在迭代ID，则从当前pageContext获取数据对象,否则从Property获取数据对象
		// 获取数据对象后，从blobProperty获取输出的大字段内容
		if (getIterateId() != null)
			dataObject = (DataObject) pageContext.getAttribute(this
					.getIterateId());
		else
			dataObject = (DataObject) XpathUtil.getObjectByXpath(getRootObj(),
					getProperty());

		// 单独处理AtFileupload实体,文件可能存在server本地路径
		if ("AtFileupload".equalsIgnoreCase(dataObject.getType().getName())) {
			fileName = dataObject.getString("fileName");
			fileNewName = dataObject.getString("fileNewName");
			fileSave = dataObject.getString("fileSave");
			if ("1".equals(fileSave))// 如果是文件系统存取，则读取filepath字段
				filePath = dataObject.getString("filePath");
			fileType = dataObject.getString("fileType");
		}

		if (fileName == null){
			fileName = getDefaultFileName();
			fileNewName = fileName;
		}
		if (filePath == null)
			filePath = getDefaultFilePath() + SystemInfo.FILE_SEPARATOR
					+ fileName;
		
		/**
		 * 洪文弟2013-07-05，按上海银行安全检查增加
		 */
		if (filePath.indexOf("..") > 0) {
			filePath = "";
		}
		File file = new File(filePath);
		
		if (!file.exists()) {
			expandLobProperty(dataObject, blobProperty);
			filePath = dataObject.getString(blobProperty);
			if (filePath == null) {
				return EVAL_PAGE;
			}
		}

		if (attachment) {
			html = new StringBuffer();
			html.append("<a href=\"").append(getActionUrl()).append(
					"&attachment=true").append("\">").append(fileName).append(
					"</a>");
		} else {
			html.append("<img id=\"").append(fileName).append("\" ").append(
					"src=\"").append(getActionUrl())
					.append("&attachment=false").append("\"/>");
		}
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @return 获取默认的文件名
	 */
	private String getDefaultFileName() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetimeString = format.format(new Date());
		return datetimeString;
	}

	/**
	 * 获取默认文件路径
	 * 
	 * @return
	 */
	private String getDefaultFilePath() {
		String uploadPath = ApplicationContext.getInstance()
				.getApplicationWorkPath()
				+ SystemInfo.FILE_SEPARATOR + "upload";
		return uploadPath;
	}

	/**
	 * 获取大字段内容输出链接路径
	 * 
	 * @return
	 */
	private String getActionUrl() {
		StringBuffer action = new StringBuffer();
		try {
			action.append(RequestUtil.getContextPath(pageContext)).append(
					"/auth/blob.jsp?").append("fileType=").append(
					java.net.URLEncoder.encode(fileType, "UTF-8")).append("&")
					.append("filePath=").append(
							java.net.URLEncoder.encode(filePath, "UTF-8"))
					.append("&").append("fileName=").append(
							java.net.URLEncoder.encode(fileNewName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return action.toString();
	}

	public int doEndTag() throws JspException {
		ResponseUtil.write(pageContext, html.toString());
		release();
		return EVAL_PAGE;
	}

	@Override
	public void initAttributes() throws JspException {
		super.initAttributes();
		html = new StringBuffer();

		String fileTypeValue = XpathUtil.getStringByXpathSupport(getRootObj(),
				"fileType");
		if (StringUtil.isNotNullAndBlank(fileTypeValue))
			fileType = fileTypeValue;

		String filePathValue = XpathUtil.getStringByXpathSupport(getRootObj(),
				"filePath");
		if (StringUtil.isNotNullAndBlank(filePathValue))
			filePath = filePathValue;

		String fileNameValue = XpathUtil.getStringByXpathSupport(getRootObj(),
				"fileName");
		if (StringUtil.isNotNullAndBlank(fileNameValue))
			fileName = fileNameValue;

	}

	/**
	 * 将字节流写入到文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param bytes
	 *            字节数组
	 *
	private void streamToFile(String fileName, byte[] bytes) {
		File file = new File(fileName);
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
   */
	private Connection getConnection() {
		Connection conn = ConnectionHelper
				.getCurrentContributionConnection("default");
		return conn;
	}

	private void expandLobProperty(DataObject object, String blobField) {
		Connection conn = getConnection();
		IDASSession session = DASManager.createDasSession(conn);
		try {
			session.expandLobProperty(object, blobField);
		} finally {
			closeConnection(session, conn);
		}
	}

	/**
	 * 关闭DAS session和数据库连接。
	 * 
	 * @param session
	 *            数据访问会话对象。
	 * @param conn
	 *            数据库连接。
	 */
	protected static void closeConnection(IDASSession session, Connection conn) {
		if (session != null) {
			try {
				session.close();
			} catch (Exception e) {
				throw new EOSRuntimeException(
						"occur exception when close session", e);
			}
		}
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			throw new EOSRuntimeException(
					"occur exception when close connection", e);
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * 释放资源
	 */
	public void release() {
		// 释放资源
		super.release();
		this.attachment = false;
		this.blobProperty = null;
		this.fileName = null;
		this.filePath = null;
		this.fileType = null;
	}

}
