package com.bos.utp.auth.skin;

import javax.servlet.http.HttpServletRequest;

import com.bos.utp.ABFConfigKey;

import com.eos.data.datacontext.IUserObject;
import com.eos.system.utility.StringUtil;

public class DefaultSkinImpl implements ISkinConvertor {

	/**
	 * 从request中获取用户的当前layout
	 */
	public String getLayout(HttpServletRequest request) {
		String layout = null;
		IUserObject user = (IUserObject) request.getSession().getAttribute(IUserObject.KEY_IN_CONTEXT);
		if (user != null) {
			layout = (String) user.getAttributes().get(ABFConfigKey.SKIN_LAYOUTATTR.getConfigValue());
		}
		return StringUtil.isNullOrBlank(layout) ? ABFConfigKey.SKIN_DEFAULT_LAYOUT.getConfigValue() : layout;
	}

	public String getStyle(HttpServletRequest request) {
		String style = null;
		IUserObject user = (IUserObject) request.getSession().getAttribute(IUserObject.KEY_IN_CONTEXT);
		if (user != null) {
			style = (String) user.getAttributes().get(ABFConfigKey.SKIN_STYLEATTR.getConfigValue());
		}
		return StringUtil.isNullOrBlank(style) ? ABFConfigKey.SKIN_DEFAULT_STYLE.getConfigValue() : style;
	}

}
