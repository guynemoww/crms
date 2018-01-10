package com.bos.pub;



import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;


import commonj.sdo.DataObject;
import com.eos.foundation.eoscommon.ConfigurationUtil;

public class UImageDict {

	private static final long serialVersionUID = 5388037581578269034L;


	public UImageDict() {
		super();
	}


	@Bizlet("获得影像分类")
	public String getImageTypes() throws ServletException, IOException {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?><root><function id=\"GET_DOC_RULE\"/></root>");
		String line = null;
		String xmlString = xml.toString();
				Document doc=null;
				try {
					doc = DocumentHelper.parseText(xmlString);
					Element root = doc.getRootElement();
					java.util.Iterator it = root.elementIterator("function");
					
							DataObject acApplication = DataObjectUtil.createDataObject("com.bos.dataset.pub.TbPubImageType");
							DataObject[] arr =  DatabaseUtil.queryEntitiesByTemplate(GitUtil.DEFAULT_DS_NAME,acApplication);
							System.out.println(arr.length);
							 doc = listToXml(arr);
							System.out.println(doc.asXML());
					
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				HttpClient httpClient = new HttpClient();
				//是否超时
				HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
				managerParams.setConnectionTimeout(600000);
				managerParams.setSoTimeout(600000);
				//生产地址
				//String url = "http://10.240.81.122:18123/UImageWeb/UImageDictSync";
				String url="http://"+ConfigurationUtil.getUserConfigMultiValue("CustomConfig", "Image", "DocRule").getString("DocRule")+"/UImageWeb/UImageDictSync";
				PostMethod postMethod = new PostMethod(url);
				postMethod.getParams().setContentCharset("utf-8");
				String strMsg = doc.asXML();
				postMethod.setRequestBody(strMsg);
				InputStream resis = null;
				byte[] data = null;
				try {
					int status = httpClient.executeMethod(postMethod);
					System.out.println(status);
					resis =  postMethod.getResponseBodyAsStream();
					byte[] byteBuffer = new byte[1024];	
					int length = 0;		
					while ((length = resis.read(byteBuffer)) != -1) {
						if(data == null){
							data = new byte[length];
							System.arraycopy(byteBuffer, 0, data, 0, length);
						}else{
							byte[] temp = new byte[data.length + length];
							System.arraycopy(data, 0, temp, 0, data.length);
							System.arraycopy(byteBuffer, 0, temp, data.length, length);
							data = temp;
						}
					}
					System.out.println(new String(data));
	
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new String(data);
	}
	
	public Document listToXml(DataObject[] arr){
		Document document = DocumentHelper.createDocument();
	    Element root = document.addElement("root");
	    for (int i = 0; i < arr.length; i++) {
			DataObject object = arr[i];
			
	    	root.addElement("doc_rule")
	    	.addAttribute("doc_id", object.getString("imageTypeId"))
	    	.addAttribute("doc_text", object.getString("imageTypeName"))
	    	.addAttribute("doc_rule", "N");
	    }
	    return document;
	}
	
	
	@Bizlet("获得影像张数")
	 public int getImageNum(String imageDocumentNumber){		
		String httpstr="http://"+ConfigurationUtil.getUserConfigMultiValue("CustomConfig", "Image", "DocRule").getString("DocRule")
		+"/UImageWeb/Service/Sequence/"+ConfigurationUtil.getUserConfigMultiValue("CustomConfig", "Image", "txtItemNo").getString("txtItemNo")+"/"+imageDocumentNumber;
		System.out.println("httpstr:"+httpstr);
		String  str = sendByHttp(httpstr, "", "get");
		str = str.substring(1,str.length()-1);
		String[] strs = str.split(",");
		return strs.length;
	}
	
	public static String sendByHttp(String url, String requestStr, String requestMethod) {
		try {
			HttpClient httpClient = new HttpClient();
			InputStream resis = null;
			// 是否超时
			HttpConnectionManagerParams managerParams = httpClient
					.getHttpConnectionManager().getParams();
			managerParams.setConnectionTimeout(600000);
			managerParams.setSoTimeout(600000);
			PostMethod postMethod = null;
			GetMethod getMethod = null;
			if("post".equals(requestMethod)){
				postMethod = new PostMethod(url);
				postMethod.getParams().setContentCharset("utf-8");
				postMethod.setRequestBody(requestStr);
				int status = httpClient.executeMethod(postMethod);
				if(200 != status){
					return null;
				}
				resis = postMethod.getResponseBodyAsStream();
			} else if ("get".equals(requestMethod)){
				getMethod = new GetMethod(url);
				getMethod.getParams().setContentCharset("utf-8");
				int status = httpClient.executeMethod(getMethod);
				if(200 != status){
					return null;
				}
				resis = getMethod.getResponseBodyAsStream();
			} else {
				return null;
			}
			byte[] data = null;
			byte[] byteBuffer = new byte[1024];
			int length = 0;
			while ((length = resis.read(byteBuffer)) != -1) {
				if (data == null) {
					data = new byte[length];
					System.arraycopy(byteBuffer, 0, data, 0, length);
				} else {
					byte[] temp = new byte[data.length + length];
					System.arraycopy(data, 0, temp, 0, data.length);
					System.arraycopy(byteBuffer, 0, temp, data.length, length);
					data = temp;
				}
			}
			return new String(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
