package com.bos.utp.auth.bizlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bos.utp.ABFConfigKey;
import com.bos.utp.auth.skin.DefaultSkinImpl;
import com.bos.utp.auth.skin.ISkinConvertor;

import com.eos.data.datacontext.IUserObject;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.utility.StringUtil;

@Bizlet("")
public class SkinUtil {
	/**
	 * 根据skin_convertor配置值初始化实例 如果未定义则使用默认实现DefaultSkinImpl
	 * 
	 * @return
	 */
	public static ISkinConvertor initISkinConvertor() {
		ISkinConvertor service = null;
		try {
			String clazz = ABFConfigKey.SKIN_CONVERTOR.getConfigValue();
			if (StringUtil.isNotNullAndBlank(clazz)) {
				Class serviceClass = Class.forName(clazz);
				service = (ISkinConvertor) serviceClass.newInstance();
			} else {
				service = new DefaultSkinImpl();
			}
		} catch (Exception e) {
			service = new DefaultSkinImpl();
			LogUtil.logError("init Static  ISkinConvertor instance faild,use com.bos.utp.auth.DefaultSkinImpl ", e, (Object) null);
		}
		return service;
	}

	/**
	 * 获取当前用户的layout值 获取顺序为 1、parameter 2、外部接口-session 3、内部session
	 * userObject/attribues/layout
	 * @param request 
	 * @return skin 如果 未设置layout返回"default"
	 */
	@Bizlet("")
	public static String getLayout(HttpServletRequest request) {
		return ISkinConvertor.INSTANCE.getLayout(request);
	}

	/**
	 * 获取当前用户的style值
	 * 
	 * @param request 
	 * @return skin 如果 未设置style返回"default"
	 */
	@Bizlet("")
	public static String getStyle(HttpServletRequest request) {
		return ISkinConvertor.INSTANCE.getStyle(request);
	}

	/**
	 * 重定向到不同skin下页面
	 * 
	 * @param request 
	 * @param response 
	 * @param uri menu.jsp
	 * @throws IOException
	 * @throws ServletException
	 */
	@Bizlet("")
	public static void redirectBySkin(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException, IOException {
		request.getRequestDispatcher(getLayoutFileForTag(uri, request)).forward(request, response);
	}

	/**
	 * 返回当前style下的文件
	 * @param request 
	 * @param uri 即使uri包含/,也是当前style目录下开始
	 */
	@Bizlet("")
	public static String getStyleFileForTag(String uri, HttpServletRequest request) {
		return concat(ABFConfigKey.SKIN_STYLE_LOC.getConfigValue(), getStyle(request), uri);
	}

	/**
	 *   返回当前style下的文件
	 *   包含contextpath的url
	 * @param request 
	 * @param uri 即使uri包含/,也是当前style目录下开始
	 */

	@Bizlet("")
	public static String getStyleFile(String uri, HttpServletRequest request) {
		return concat(request.getContextPath(), getStyleFileForTag(uri, request));
	}
	/**
	 *   返回当前style下
	 *   包含contextpath的url
	 * @param request 
	 */
	public static String getStylePath(HttpServletRequest request) {
		return concat(request.getContextPath(), getStyleFileForTag(request));
	}
	
	public static String getStyleFileForTag(HttpServletRequest request) {
		return concat(ABFConfigKey.SKIN_STYLE_LOC.getConfigValue(), getStyle(request));
	}

	/**
	 * 返回当前layout下的uri
	 * 不包含contextPath
	 * @param request 
	 * @param uri 即使uri包含/,也是当前layout目录下开始
	 */
	@Bizlet("")
	public static String getLayoutFileForTag(String uri, HttpServletRequest request) {
		return concat(ABFConfigKey.SKIN_LAYOUT_LOC.getConfigValue(), getLayout(request), uri);
	}

	/**
	 * 返回当前layout下的uri
	 * 包含contextpath的url
	 * @param request 
	 * @param uri 即使uri包含/,也是当前layout目录下开始
	 */

	@Bizlet("")
	public static String getLayoutFile(String uri, HttpServletRequest request) {
		return concat(request.getContextPath(), getLayoutFileForTag(uri, request));
	}

	/**
	 * 返回当前layout下的对应style中的uri
	 * 不包含contextpath
	 * @param request 
	 * @param uri 即使uri包含/,也是当前style目录下开始
	 */
	@Bizlet("")
	public static String getLayoutStyleFileForTag(String uri, HttpServletRequest request) {
		return concat(ABFConfigKey.SKIN_LAYOUT_LOC.getConfigValue(), getLayout(request), ABFConfigKey.SKIN_LAYOUTSTYLE_LOC.getConfigValue(), getStyle(request), uri);
	}

	/**
	 * 返回当前layout下的对应style中的uri
	 * 包含contextpath的url 
	 * @param request 
	 * @param uri 即使uri包含/,也是当前style目录下开始
	 */

	@Bizlet("")
	public static String getLayoutStyleFile(String uri, HttpServletRequest request) {
		return concat(request.getContextPath(), getLayoutStyleFileForTag(uri, request));
	}

	/**
	 * 把子字符串用“/”连接起来
	 * <pre>
	 * 如果字符串是“”或者null则不连接 如果字符串是"/"也不连接 concat("","a","/',"/b") =
	 * concat("/","a","/',"/b")
	 * </pre>
	 * @param urls
	 * @return
	 */
	private static final String SLASH = "/";

	private static String concat(String... urls) {
		StringBuilder sb = new StringBuilder();
		for (String url : urls) {
			if (StringUtil.isNotNullAndBlank(url) && !SLASH.equals(url)) {
				if (url.startsWith(SLASH)) {
					sb.append(url);
				} else {
					sb.append(SLASH).append(url);
				}
			}
		}
		return sb.toString();
	}

	@Bizlet("")
	public static void setLayout(String layout, IUserObject user) {
		user.getAttributes().put(ABFConfigKey.SKIN_LAYOUTATTR.getConfigValue(), layout);
	}

	@Bizlet("")
	public static void setStyle(String style, IUserObject user) {
		user.getAttributes().put(ABFConfigKey.SKIN_STYLEATTR.getConfigValue(), style);
	}

	@Bizlet("")
	public static void seLayoutStyle(String layoutstyle, IUserObject user) {
		String[] tmp = layoutstyle.split(",");
		setLayout(tmp[0], user);
		setStyle(tmp[1], user);
	}

	public static void main(String[] args) {
		System.out.println(concat("", "/a", "/b"));
	}
}
