package com.bos.jaxb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

import com.bos.inter.CallT24Interface.AmountRec;
import com.bos.inter.CallT24Interface.TStLdStdAaaRq;
import com.bos.jaxb.javabean.BOSFXII;
import com.bos.jaxb.javabean.CommonRqHdr;
import com.bos.pub.socket.util.EsbSocketConstant;

/**
 * marshal对象和unmarshal对象都是由JAXBContext创建.所以一开始需要初始化JAXBContext.
 * 
 * @author Sunny
 */
public class JAXBUtil {
	/**
	 * 生成xml文件的二进制数据
	 * 
	 * @param obj
	 *            对象
	 */
	public static byte[] marshal(Object obj) throws JAXBException {
		JAXBContext context = JAXBCache.instance().getJAXBContext(
				obj.getClass());
		Marshaller m = context.createMarshaller();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//		m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.bankofshanghai.com/BOSFX/2010/08");
		m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//		m.setProperty("standalone", "");
		m.marshal(obj, outputStream);
		byte[] result = outputStream.toByteArray();
		return result;
	}
	
	/**
	 * 生成xml文件到指定目录
	 * @param obj
	 * @param file	需要生成的文件
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void marshal(Object obj,File file) throws IOException{
		JAXBContext context;
		FileOutputStream out = null;
		try {
			context = JAXBCache.instance().getJAXBContext(
					obj.getClass());
			Marshaller m = context.createMarshaller();
			out = new FileOutputStream(file);
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//格式化输出
			m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.bankofshanghai.com/BOSFX/2010/08");//指定SCHEMA_LOCATION
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");//设置字符集
			m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);//取消自动生成头文件
//			m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, Boolean.TRUE);
			m.marshal(obj, out);
			out.close();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	/**
	 * @param data
	 *            xml 字节数组
	 * @param classe
	 *            类
	 * @return jaxb生成xml的java 类对象
	 * @throws UnsupportedEncodingException 
	 */
	public static Object unmarshal(byte[] data, Class<?> classe)
			throws JAXBException, UnsupportedEncodingException {
		JAXBContext context = JAXBCache.instance().getJAXBContext(classe);
		Unmarshaller m = context.createUnmarshaller();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		Object obj = m.unmarshal(inputStream);
		return obj;
	}

	/**
	 * @param in
	 *            xml stream
	 * @param classe
	 *            类
	 * @return jaxb生成xml的java 类对象
	 */
	public static Object unmarshal(InputStream in, Class<?> classe)
			throws JAXBException, IOException {
		JAXBContext context = JAXBCache.instance().getJAXBContext(classe);
		byte[] data = IOUtils.toByteArray(in);
		Unmarshaller m = context.createUnmarshaller();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
		Object obj = m.unmarshal(inputStream);
		return obj;
	}

	public static Object unmarshal(String socketMessage, Class<?> classe)
    {
		Object ret = null;
    	try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(socketMessage.getBytes(EsbSocketConstant.CHARCODE_UTF8));

			ret = unmarshal(inputStream, classe);
		} catch (JAXBException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    	return ret;
    }


	public static void main(String[] args) throws JAXBException, UnsupportedEncodingException {
		List<AmountRec> list = new ArrayList<AmountRec>();
		TStLdStdAaaRq tStLdStdAaaRq = new TStLdStdAaaRq();
		tStLdStdAaaRq.setAloneMergeInd("中文");
//		cl.setAmountRec(list);
		BOSFXII bf = new BOSFXII();
		bf.t24BosfxRq = tStLdStdAaaRq;
		String marshalToStr = marshalToStr(bf,true);
		byte[] b = JAXBUtil.marshal(bf);
		try {
			System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+new String(b,"UTF-8"));
			BOSFXII newBfc = (BOSFXII) JAXBUtil.unmarshal(b, BOSFXII.class);
			System.out.println(newBfc.toString());
		} catch (UnsupportedEncodingException ex) {
			// TODO 自动生成 catch 块
			ex.printStackTrace();
		}
	}
	
	/**
	 * @param Object
	 * @return jaxb生成xml的java String字符串
	 * @throws UnsupportedEncodingException 
	 */
	public static String marshalToStr(Object obj, boolean bl) throws JAXBException, UnsupportedEncodingException {
		String result = "";
		JAXBContext context = JAXBCache.instance().getJAXBContext(obj.getClass());
		Marshaller m = context.createMarshaller();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_ENCODING, EsbSocketConstant.CHARCODE_UTF8);
		m.setProperty(Marshaller.JAXB_FRAGMENT, bl);
		m.marshal(obj, outputStream);
		byte[] bytes = outputStream.toByteArray();
		result = new String(bytes,EsbSocketConstant.CHARCODE_UTF8);
		return result;
	}
}