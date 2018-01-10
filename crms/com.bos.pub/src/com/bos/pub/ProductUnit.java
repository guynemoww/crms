/**
 * 
 */
package com.bos.pub;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author lujinbin
 * @date 2014-04-10 20:40:59
 *
 */
@Bizlet("授信产品参数校验类")
public class ProductUnit {
	public static void main(String[] args) {
	}
	
	/**
	 * 
	 * @param tempOne 申请的参数
	 * @param tempTwo 数据库中的数据
	 * @return 1:可以申请 2：不可以申请
	 */
	@Bizlet("比较担保方式是否符合")
	public static int compareWarrant(String[] tempOne,String[] tempTwo){
		int res = 0;
		List  listTempOne=new ArrayList();
		List  listTempTwo=new ArrayList();
		listTempOne= Arrays.asList(tempOne);//申请的参数
		listTempTwo= Arrays.asList(tempTwo);//数据库中的数据
		boolean flag = false;
		for(int i=0;i<listTempOne.size();i++){
			flag=listTempTwo.contains(listTempOne.get(i));//申请的参数是否存在数据表中
			if(flag==true){//如果存在
				res=1;
			}else{//不存在
				res=2;
			}
		}
		return res;
	}
	@Bizlet("截取字符串")
	public static String[] splitString(String temp) {
		String stringTemp[]  = null;
		String stringTempOne[]=new String[1];
		if(temp.indexOf(",")==-1){
			stringTempOne[0]=temp;
			return stringTempOne;
		}else{
			stringTemp=temp.split(",");
		}
		return stringTemp;
	}
	@Bizlet("字符串转换数组")
	public static String stringToArrays(Object temp[]) {
		String stringTemp = "";
		
		for (int i = 0; i < temp.length; i++) {
			DataObject ob=(DataObject) temp[i];
			stringTemp += ob.getString("ORGCODE")+ ",";

		}
		return stringTemp;
	}
}
