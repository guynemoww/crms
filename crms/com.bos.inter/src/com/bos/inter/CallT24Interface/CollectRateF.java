package com.bos.inter.CallT24Interface;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.eos.system.annotation.Bizlet;

public class CollectRateF {

	/**
	 * @param args
	 * 
	 */
	
	@Bizlet(value="生成银团收息频率")
	public String createCRF(String startDate,String cType){
		String collrateF="";
		String[] start=startDate.substring(0,10).split("-");
		if(startDate!=null&&!"".equals(startDate)){
			if("01".equals(cType)) {
			     if(Integer.parseInt(start[2])<21&&Integer.parseInt(start[1])<=12){
			    	 collrateF=start[0]+start[1]+"21M0121";
			     }else if(Integer.parseInt(start[2])>=21){
			    	 if(Integer.parseInt(start[1])<12){
			    	 collrateF=start[0]+String.valueOf(Integer.parseInt(start[1])+1)+"21M0121";
			    	 }else{
			    	 collrateF = String.valueOf(Integer.parseInt(start[0])+1)+"0121M0121";	 
			    	 }
			     }   
				}
			else if("02".equals(cType)){
				if(Integer.parseInt(start[1])>1&&Integer.parseInt(start[1])<=3){
					if(Integer.parseInt(start[1])>1&&Integer.parseInt(start[1])<3){
			    	 collrateF=start[0]+"0321M0321";
					}else{
					  if(Integer.parseInt(start[2])>=21){
						  collrateF=start[0]+"0621M0321";  
					  }else{
						  collrateF=start[0]+"0321M0321";
					  }	
					}
			     }else if(Integer.parseInt(start[1])>3&&Integer.parseInt(start[1])<=6){
			    	 if(Integer.parseInt(start[1])>3&&Integer.parseInt(start[1])<6){
				    	 collrateF=start[0]+"0621M0321";
						}else{
						  if(Integer.parseInt(start[2])>=21){
							  collrateF=start[0]+"0921M0321";  
						  }else{
							  collrateF=start[0]+"0621M0321";
						  }	
						}
			     }else if(Integer.parseInt(start[1])>6&&Integer.parseInt(start[1])<=9){
			    	 if(Integer.parseInt(start[1])>6&&Integer.parseInt(start[1])<9){
				    	 collrateF=start[0]+"0921M0321";
						}else{
						  if(Integer.parseInt(start[2])>=21){
							  collrateF=start[0]+"1221M0321";  
						  }else{
							  collrateF=start[0]+"0921M0321";
						  }	
						}
			     }else if(Integer.parseInt(start[1])>9&&Integer.parseInt(start[1])<=12){
			    	 if(Integer.parseInt(start[1])>9&&Integer.parseInt(start[1])<12){
				    	 collrateF=start[0]+"1221M0321";
						}else{
						  if(Integer.parseInt(start[2])>=21){
							  collrateF=String.valueOf(Integer.parseInt(start[0])+1)+"0321M0321";  
						  }else{
							  collrateF=start[0]+"1221M0321";
						  }	
						}
			     }	
			}else if("03".equals(cType)){
				if(Integer.parseInt(start[1])>1&&Integer.parseInt(start[1])<=6){
					if(Integer.parseInt(start[1])>1&&Integer.parseInt(start[1])<6){
			    	 collrateF=start[0]+"0621M0621";
					}else{
					  if(Integer.parseInt(start[2])>=21){
						  collrateF=start[0]+"1221M0621";  
					  }else{
						  collrateF=start[0]+"0621M0621";
					  }	
					}
			     }else if(Integer.parseInt(start[1])>6&&Integer.parseInt(start[1])<=12){
			    	 if(Integer.parseInt(start[1])>6&&Integer.parseInt(start[1])<12){
				    	 collrateF=start[0]+"1221M0621";
						}else{
						  if(Integer.parseInt(start[2])>21){
							  collrateF=String.valueOf(Integer.parseInt(start[0])+1)+"0621M0621";  
						  }else{
							  collrateF=start[0]+"1221M0621";
						  }	
						}
			     }
			}else if("04".equals(cType)){
			  if(Integer.parseInt(start[1])>1&&Integer.parseInt(start[1])<=12){
				  if(Integer.parseInt(start[1])>1&&Integer.parseInt(start[1])<12){
					  collrateF=start[0]+"1221M1221";
				  }else{
					  if(Integer.parseInt(start[2])<=21){
					    	 collrateF=start[0]+"1221M1221";
					     }else{
					    	 collrateF=String.valueOf(Integer.parseInt(start[0])+1)+"1221M1221";
					     } 
					  
				  }
				
			  }
			}
		
		
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
//	     Date date = new Date();   
//	     String[] nowD=sdf.format(date).split("-");
		}
		return collrateF;
	}
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		CollectRateF a = new CollectRateF();
		String aa=a.createCRF("2014-10-21", "01");
		System.out.print(aa);
	}

}
