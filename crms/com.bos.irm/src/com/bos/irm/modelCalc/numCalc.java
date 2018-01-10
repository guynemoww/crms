package com.bos.irm.modelCalc;

import com.eos.system.annotation.Bizlet;

public class numCalc {
	@Bizlet("俩位小数的四舍五入")
	public static String numFormat(double a){
		String result = String.format("%.2f", a);
		System.out.println(result);
		return result;
	}
}
