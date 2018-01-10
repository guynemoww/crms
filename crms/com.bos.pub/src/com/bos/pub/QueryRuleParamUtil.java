package com.bos.pub;

import java.util.HashMap;
import java.util.Map;

import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

public class QueryRuleParamUtil {

	/**
	 * 
	 * @param str
	 *            需要执行替换的字符串
	 * @param regex
	 *            被替换的参数
	 * @param replacement
	 *            替换的参数
	 * @return
	 */
	@Bizlet("字符串替换")
	public String replaceAll(String str, String regex, String replacement) {
		return str.replaceAll(regex, replacement);
	}

	/**
	 * 
	 * @param str
	 *            需要执行分割的字符串
	 * @param regex
	 *            分割参数
	 * @return
	 */
	@Bizlet("分割串替换")
	public String[] splitString(String str, String regex) {

		return str.split(regex);
	}

	/**
	 * 
	 * @param str
	 *            需要执行分割的授权规则
	 * @param regex
	 *            分割参数
	 * @return
	 */
	@Bizlet("分割授权规则")
	public String[] splitRu(String str, String regex) {

		return str.split(regex);
	}

	/**
	 * 
	 * @param str
	 *            需要执行分割的授权规则结果
	 * @param regex
	 *            分割参数
	 * @return
	 */
	@Bizlet("分割授权规则结果")
	public String splitLog(String str, String regex) {
		int i = str.lastIndexOf(regex);
		String strorg = str.substring(i);
		int j = str.lastIndexOf("orgname=");
		String strorgt = str.substring(j + 8);
		return strorgt.split(",")[0];
	}

	/**
	 * 
	 * @param paramStr
	 *            参数字符串
	 * @param obj
	 *            存有中文名的参数集合
	 * @return
	 */
	@Bizlet("把授权参数英文换成中文")
	public String idConversionName(String paramStr, Object[] obj) {
		// HaString [] params=paramStr.split(" ");shMap<String,String>[]
		// map=(HashMap<String,String>[]) obj;
		String[] pa = paramStr.split("rule");
		String[] params = pa[0].split(" ");
		String str = "";
		for (int i = 0; i < obj.length; i++) {
			Map<String, String> map = (Map<String, String>) obj[i];
			for (int y = 0; y < params.length; y++) {
				String param = params[y].split("=")[0];
				String id = map.get("ID");
				String name = map.get("NAME");
				String dictType = map.get("DICTTYPE");
				if (param.equals(id)) {
					String par = params[y];
					String[] pars = par.split("=");
					str += pars[0].replaceAll(id, name) + "=";
					if (dictType != null && dictType != "" && pars.length == 2) {
						// System.out.println(GitUtil.getDictName(dictType,pars[1]));
						if (dictType.equals("product")) {
							String[] pp = pars[1].split(",");
							for (int j = 0; j < pp.length; j++) {
								DataObject[] texts = GitUtil
										.getProductInfo(pp[j]);
								if (texts.length == 1) {
									str += pp[j].replaceAll(pp[j], texts[0]
											.get("productName").toString())+"-";
								} else {
									str += " ";
								}
							}
							str += " ";
						} else if (dictType.equals("org")) {
							DataObject[] texts = GitUtil.getOrgInfo(pars[1]);
							if (texts.length == 1) {
								str += pars[1].replaceAll(pars[1], texts[0]
										.get("orgname").toString())
										+ " ";
							} else {
								str += " ";
							}
						} else {
							str += pars[1].replaceAll(pars[1], GitUtil
									.getDictName(dictType, pars[1]))
									+ " ";
						}
					} else if (pars.length == 2) {
						str += pars[1] + " ";
					} else {
						str += " ";
					}
				}
			}
		}
		if (pa.length > 1) {
			String st = pa[1].toString();
			str += "rule" + st;
		}
		str = str.replaceAll("=null ", "=空值 ");
		str = str.replaceAll("= ", "=空值 ");
		str = str.replaceAll("=false ", "=否 ");
		str = str.replaceAll("=true ", "=是 ");

		return str;
	}
}
