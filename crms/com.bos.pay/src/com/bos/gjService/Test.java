package com.bos.gjService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bos.pub.GitUtil;
import com.eos.system.utility.StringUtil;

public class Test {

	
	public static void main(String[] args) {
/*		String str = "1000.00005";
		
		if(Float.parseFloat(str) < 1000.1){
			//System.out.println("12");
		}
		
		Object[] param = new Object[2];
		
		TestVo vo = new TestVo();
		vo.setId("01001");
		vo.setAge(12);
		vo.setAdd("成都");
		
		TestPojo pojo = new TestPojo();
		pojo.setNum("01");
		pojo.setTestVo(vo);
		
		param[0] = vo;
		param[1] = pojo;
		
		System.out.println(param[1].toString());*/
		
		
		/**
		 * 测试BigDecimal(小数点后N位精确)
		 */
		
/*		String str1 = "100.12345678911111111111111111111232424883298924932848923482389";
		//System.out.println(new BigDecimal(str1).compareTo(new BigDecimal(101)) < 0);
		
		float str2 = (float)101;
		String str3 = "100.9999";
		System.out.println(str2 > Float.parseFloat(str3));
		
		System.out.println(Float.parseFloat(str3));
		

		
		System.out.println(StringUtil.leftPad("2573", 6,"*"));*/
		
		//System.out.println(new BigDecimal(""));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		System.out.println(sdf.format(new Date()));
		
		
		
		
	}
	
}




















