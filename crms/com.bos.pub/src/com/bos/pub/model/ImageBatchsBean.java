package com.bos.pub.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ImageBatchsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String count;
	
	private ImageBatchBean batchBean;
	
	public ImageBatchsBean(){
		
	}

	public String getCount() {
		return count;
	}

	@XmlAttribute(name = "COUNT")
	public void setCount(String count) {
		this.count = count;
	}

	public ImageBatchBean getBatchBean() {
		return batchBean;
	}

	@XmlElement(name = "BATCH")
	public void setBatchBean(ImageBatchBean batchBean) {
		this.batchBean = batchBean;
	}
}
