package com.primeton.mgrcore;

import java.io.Serializable;

public class OXD06ResBody implements Serializable {
	private static final long serialVersionUID = 561556979917306298L;
	private String remarkInfo;// 备注信息

	public OXD06ResBody() {

	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}

	@Override
	public String toString() {
		return "OXD06ResBody [remarkInfo=" + remarkInfo + "]";
	}

}
