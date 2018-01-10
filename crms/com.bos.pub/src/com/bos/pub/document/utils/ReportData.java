/**
 * 
 */
package com.bos.pub.document.utils;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author bin.yin 2012/12/22
 * @version 1.0
 */
public class ReportData implements Serializable {
	private static final long serialVersionUID = -2722248902864797698L;

	private byte[] mbData = null;
	private String msContentType = null;

	public byte[] getData() {
		return mbData;
	}
	public void setData(byte[] pData) {
		mbData = pData;
	}
	public String getContentType() {
		return msContentType;
	}
	public void setContentType(String pContentType) {
		msContentType = pContentType;
	}
}