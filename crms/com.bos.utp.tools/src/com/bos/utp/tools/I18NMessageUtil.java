package com.bos.utp.tools;


import java.text.MessageFormat;

import com.eos.foundation.eoscommon.ResourcesMessageUtil;
import com.eos.system.annotation.Bizlet;

/**
 * 国际化信息相关方法
 *
 * @author 蔡述尧
 * @date 2009-04-09 09:40:10
 */
/*
 * 修改历史
 * $Log: I18NMessageUtil.java,v $
 * Revision 1.2  2010/11/30 16:12:51  caisy
 * ��ı���ΪUTF-8
 *
 * Revision 1.1  2009/04/14 08:59:50  caisy
 * Update：国际化翻译工具类，支持通过java获取国际化带参数的消息
 * 
 */
@Bizlet("国际化信息相关方法")
public class I18NMessageUtil {

	/**
	 * @param key
	 * @param param
	 * @return
	 * @author 蔡述尧
	 */
	@Bizlet("获取带参数的国际化信息")
	public static String getI18NResourceMessage(String key, Object[] param) {
		  String title = ResourcesMessageUtil.getI18nResourceMessage(key);
		  if(title!=null){
		    return  MessageFormat.format(title,param);
		  }else{
			  return null;
		  }
		
	}

}
