package com.bos.irm.rating;

import com.eos.system.annotation.Bizlet;

@Bizlet("字符串操作")
public class StringConcatString {
	@Bizlet("俩个字符串拼接")	
	public String stringConcatString(String a,String b){
		String c = a+":"+b;
		return c;
	}	
	@Bizlet("一个字符串分解")
	public String stringToStrings(String a){
		String[] b;
		b=a.split(":");
		System.out.println(b);
		return b[0];
	}
	
	@Bizlet("字符串拼接")
	public String strToStrings(String partyname, String partynum){
		String updateInfo = "客户名称:"  +  partyname + ",客户编码:" + partynum  + "的客户，客户基础信息更新,需要重新进行评级。";
		return updateInfo;
	}

}
