package com.bos.aft;

import com.eos.system.annotation.Bizlet;

public class StringConcatString {

	@Bizlet("俩个字符串拼接")	
	public String stringConcatString(String a,String b){
		String c = a+"（"+b+")";
		return c;
	}
}
