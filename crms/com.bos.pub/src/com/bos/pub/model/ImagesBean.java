package com.bos.pub.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ImagesBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String filePart;
	
	private ImageBean imageBean;
	
	public ImagesBean(){
		
	}

	public String getFilePart() {
		return filePart;
	}

	@XmlElement(name = "FILE_PART")
	public void setFilePart(String filePart) {
		this.filePart = filePart;
	}


	public ImageBean getImageBean() {
		return imageBean;
	}

	@XmlElement(name = "IMAGE")
	public void setImageBean(ImageBean imageBean) {
		this.imageBean = imageBean;
	}

	
}
