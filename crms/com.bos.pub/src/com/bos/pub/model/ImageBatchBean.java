package com.bos.pub.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class ImageBatchBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String queryTime;
	private String batchId;
	private String busiSerialNo;
	
	public ImageBatchBean(){
		
	}

	public String getQueryTime() {
		return queryTime;
	}

	@XmlAttribute(name="QUERY_TIME")
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getBatchId() {
		return batchId;
	}

	@XmlAttribute(name="BATCH_ID")
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBusiSerialNo() {
		return busiSerialNo;
	}

	@XmlAttribute(name="BUSI_SERIAL_NO")
	public void setBusiSerialNo(String busiSerialNo) {
		this.busiSerialNo = busiSerialNo;
	}
}
