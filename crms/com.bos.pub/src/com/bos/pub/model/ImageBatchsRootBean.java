package com.bos.pub.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ROOT")
public class ImageBatchsRootBean implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private List<ImageBatchsBean> batchs;
	
	public ImageBatchsRootBean(){
		
	}

	public List<ImageBatchsBean> getBatchs() {
		return batchs;
	}

	@XmlElement(name = "BATCHS")
	public void setBatchs(List<ImageBatchsBean> batchs) {
		this.batchs = batchs;
	}
}
