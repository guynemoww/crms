package com.bos.irm;

import java.math.BigDecimal;

public class test {
	public static void main(String[] args) {
		BigDecimal b;
	 BigDecimal a =new BigDecimal("10.64");
	b = a.setScale(0,BigDecimal.ROUND_HALF_UP);
	 System.out.println(b);
	 
		 
	}
}
