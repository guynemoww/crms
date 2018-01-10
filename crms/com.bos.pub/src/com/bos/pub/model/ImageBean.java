package com.bos.pub.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class ImageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fileFormat;//影像格式JPG、BMP、PNG、TIF
	private String busiFileType;//业务文件类型
	private String fileNo;//文件号
	private String filePath;//文件路径
	private String url ;//文件全路径
	private String optionType;//操作类型
	private String nodeId;//节点ID
	private String batchId;//批次号
	
	
	public ImageBean(){
		
	}
	
	public String getOptionType() {
		return optionType;
	}

	@XmlAttribute(name="OPTION_TYPE")
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public String getNodeId() {
		return nodeId;
	}

	@XmlAttribute(name="NODE_ID")
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getBatchId() {
		return batchId;
	}

	@XmlAttribute(name="BATCH_ID")
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getFileFormat() {
		return fileFormat;
	}
	@XmlAttribute(name="FILE_FORMAT")
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	
	public String getBusiFileType() {
		return busiFileType;
	}
	@XmlAttribute(name="BUSI_FILE_TYPE")
	public void setBusiFileType(String busiFileType) {
		this.busiFileType = busiFileType;
	}

	
	public String getFileNo() {
		return fileNo;
	}
	@XmlAttribute(name="FILE_NO")
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	

	public String getFilePath() {
		return filePath;
	}
	@XmlAttribute(name="FILE_PATH")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
	public String getUrl() {
		return url;
	}
	@XmlAttribute(name="URL")
	public void setUrl(String url) {
		this.url = url;
	}
	
}
