package com.bos.pub.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ROOT")
public class ImageRootBean implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private List<ImagesBean> images;
	
	public ImageRootBean(){
		
	}

	public List<ImagesBean> getImages() {
		return images;
	}

	@XmlElement(name = "IMAGES")
	public void setImages(List<ImagesBean> images) {
		this.images = images;
	}
}
