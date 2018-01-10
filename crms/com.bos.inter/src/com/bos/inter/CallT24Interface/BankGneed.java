package com.bos.inter.CallT24Interface;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

public class BankGneed {
	@Bizlet(value="生成银团放款序列号前端")
	public String orderDate() {
		DataObject requ = DataObjectUtil.createDataObject("com.bos.pub.meta.TbPubDate");
		
			DataObject tt[]= DatabaseUtil.queryEntitiesByTemplate("default", requ);
			DataObject temps=tt[0];
			String temp=temps.getString("operatingDate");
			if(temp!=null&&!"".equals(temp)){
				temp = temp.substring(0,10);
			}
			Date date = new Date();
			int dateSum = 0;
			String d="";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = temp;
			String yy=dateStr.substring(2, 4);
			int year = Integer.valueOf(dateStr.substring(0, 4));
			int month = Integer.valueOf(dateStr.substring(5, 7));
			int day = Integer.valueOf(dateStr.substring(8, 10));
			System.out.println(year+"="+month+"="+day);
			for (int i = 1; i < month; i++) {
				switch (i) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					dateSum += 31;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					dateSum += 30;
					break;
				case 2:
					if (((year % 4 == 0) & (year % 100 != 0)) | (year % 400 == 0))
						dateSum += 29;
					else
						dateSum += 28;
				}
			}
	        dateSum = dateSum + day;
	        int a=String.valueOf(dateSum).length();
	        System.out.println("a="+a);
	        if(a==1){
					d="00"+ String.valueOf(dateSum);
			}else if(a==2){
					d="0"+ String.valueOf(dateSum);
			}else{
				d=String.valueOf(dateSum);
			}
	      	return "SL"+yy+d;
	}
	@Bizlet(value="生成银团合同序列号")
	public String BankSqNum(int num){
		String num1=String.valueOf(num);
		String bb=StringUtils.leftPad(num1, 5, '0');
		String aa=orderDate();
		String bankSqNum=aa+bb;
		return bankSqNum;	
	}
	
	@Bizlet(value="生成银团放款序列号")
	public String BankSqNumPay(int num,String aa){
		String num1=String.valueOf(num);
		String bb=StringUtils.leftPad(num1, 4, '0');
//		String aa=orderDate();
		String bankSqNum=aa+"001"+bb;
		return bankSqNum;	
	}
	@Bizlet(value="截取银团接口返回信息")
	public String subStringOver(List<OverRideRec> a,String overRide) throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException{
		
		String relust = "flag";
		if(null!=a){
			for(int i=0; i<a.size();i++){
				OverRideRec bb= a.get(i);
				 
				String str=getFeild(bb, overRide);
				int b = str.indexOf("*");
				String temp= str.substring(b+1,b+4);
				System.out.println(temp);
				System.out.print(temp);
				if(!("100".equals(temp)||"101".equals(temp)||"102".equals(temp))){
					relust = relust+str;
				}
			}
			if(!"flag".equals(relust.trim())){
				for(int i=0; i<a.size();i++){
					OverRideRec bb= a.get(i);
					String str=getFeild(bb, overRide);
					relust = relust+str;
				}
			}
		}
		return relust;
	}

	@Bizlet("")
	public static String getFeild(Object obj, String feildName)
			throws ClassNotFoundException, SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		if(null==obj){
			return null;
		}else{
		 Class ownerClass = obj.getClass();
		 Field field = ownerClass.getField(feildName);//.getDeclaredField(feildName);
//		 field.setAccessible(true);
		 Object str = field.get(obj);
		 if(null==str){
			 return null;
		 }else{
			 return (String)str;}
		}
	}
	public static void main(String arg[]){
		BankGneed a = new BankGneed();
        System.out.print(a.orderDate());
	}
	@Bizlet("")
	public String[] over(List<OverRideRec> a,String overRide) throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException{
		String[] b = new String[a.size()];
		if(null!=a){
			for(int i=0; i<a.size();i++){
				OverRideRec bb= a.get(i);
				String str=bb.overRide;
				b[i]=str;
				}
			}
		return b;
	}
	
	public String test(){
		String aa="1123213213*123232";
		int b=aa.indexOf("*");
		String d=aa.substring(b+1);
		return d;
		
	}
	
}
