package com.bos.pub.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bos.pub.StringUtil;

public class JspUtil {

	/**
	 * 获取含有双引号的请求数据
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getParameterHaveSign(HttpServletRequest request, String key) {
		return getStrHaveSign(request.getParameter(key));
	}

	public static String getParameterHaveSign(HttpServletRequest request, String key, String defValue) {
		return getStrHaveSign(request.getParameter(key), defValue);
	}

	public static String getParameter(HttpServletRequest request, String key, String defValue) {
		String value = getStr(request.getParameter(key));
		return value == null ? defValue : value;
	}

	@SuppressWarnings("unchecked")
	public static String parameterToStr(HttpServletRequest request, char startChar, boolean haveSign) {
		Map<String, String[]> paramMap = request.getParameterMap();
		if (paramMap.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder(paramMap.size() * 50);
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			sb.append("&").append(entry.getKey()).append("=").append(entry.getValue()[entry.getValue().length - 1]);
		}
		if (startChar != '&') {
			sb.setCharAt(0, startChar);
		}
		if (haveSign) {
			sb.insert(0, "\"").append("\"");
		}
		return sb.toString();
	}

	public static String getStr(String value) {
		return StringUtil.isNotNull(value) ? value : null;
	}

	public static String getStrHaveSign(String value) {
		return getStrHaveSign(value, null);
	}

	public static String getArray(String[] datas) {
		return getArray(datas, 0, datas.length);
	}

	public static String getArray(String[] datas, int start, int end) {
		if (datas == null || datas.length == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder(datas.length * datas[start].length());
		sb.append("[").append(getStrHaveSign(datas[start]));
		for (int i = start + 1; i < end; i++) {
			sb.append(",").append(getStrHaveSign(datas[i]));
		}
		sb.append("]");
		return sb.toString();
	}

	public static String getStrHaveSign(String value, String defValue) {
		value = getStr(value);
		defValue = getStr(defValue);
		if (value == null) {
			return defValue == null ? null : ("\"" + defValue + "\"");
		}
		return ("\"" + value + "\"");
	}
}
