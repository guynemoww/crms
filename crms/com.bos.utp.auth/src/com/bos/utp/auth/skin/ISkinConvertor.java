package com.bos.utp.auth.skin;

import javax.servlet.http.HttpServletRequest;

import com.bos.utp.auth.bizlet.SkinUtil;

public interface ISkinConvertor {
	public static final ISkinConvertor INSTANCE=SkinUtil.initISkinConvertor();
	/**
	 * 获取 layout 
	 * @param request  
	 * @return  layout
	 */
      public String getLayout(HttpServletRequest request);
      
      /**
       * 获取 style 
       * @param request
       * @return style
       */
      public String getStyle(HttpServletRequest request);
      
}
