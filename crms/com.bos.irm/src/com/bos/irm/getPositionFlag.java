package com.bos.irm;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Set;

import com.eos.system.annotation.Bizlet;

public class getPositionFlag {
	

	/**
	 * @return
	 */
	@Bizlet("判断是否客户经理")
	public static String getPosFlag(String pos) {
		if("P1001".equals(pos) || "P2001".equals(pos) || "P3001".equals(pos) || "P4001".equals(pos) || "P5001".equals(pos) ){
			return "1";
		}
		return "2";
	}


	/**
	 * @return
	 */
	@Bizlet("小数百分比转换")
	public static String getAvgPdAChange(BigDecimal  pd) {
		pd = pd.multiply(new BigDecimal(100));
		DecimalFormat df = new DecimalFormat("#0.00");
		String tt = df.format(pd);
		String avgPd = tt + "%";
		System.out.println(avgPd);
		return avgPd;
	}
	
	
	/**
	 * 评级再审定岗 return 2 ，其他 0
	 * @param orgMap
	 * @param orgNum
	 * @return
	 */
	@Bizlet("获取当前用户岗位是否评级再审定岗")
	public  static String GetCurIsReAudPosFlg(HashMap orgMap,String orgNum){
		String 	roleCd = "0";
		//获取岗位的map
		HashMap roleMap =  (HashMap) orgMap.get("org"+orgNum);
		Set keys = roleMap.keySet();
		if(keys.contains("P1069")){
			//只存在评级审核岗
			roleCd="2";
		}else{
			roleCd = "0";
		}
		return roleCd;
	}
	/**
	 * 如果是客户经理 return 1 ， 评级审核岗 return 2 ，其他 0
	 * @param orgMap
	 * @param orgNum
	 * @return
	 */
	@Bizlet("获取当前用户岗位标识")
	public  static String GetCurrentUserPositionFlg(HashMap orgMap,String orgNum){
		String 	roleCd = "0";
		//获取岗位的map
		HashMap roleMap =  (HashMap) orgMap.get("org"+orgNum);
		Set keys = roleMap.keySet();
//		如果是总行的话，获取总行的权限
		if("10001".equals(orgNum)){
			if(keys.contains("P1083")){
				roleCd ="4";//违约管理岗
			}else if(keys.contains("P1047")){
				//只存在评级审核岗
				roleCd="2";
			}else{
				roleCd = "0";
			}
		}else{//非总行
			if(keys.contains("P1001")){
				if(keys.contains("P1047")){
					//既存在客户经理岗也存在评级审核岗
					roleCd="2";
				}else if(keys.contains("P1046")){//既存在客户经理岗也存在评级发起岗
					roleCd ="3";
				}else{
					//只存在总行客户经理岗
					roleCd="1";
				}
			}else if(keys.contains("P1047")){
				//只存在评级审核岗
				roleCd="2";
			}else{
				roleCd = "0";
			}
		}
		return roleCd;
	}
	/**
	 * 
	 * @param orgMap
	 * @param orgNum
	 * @return
	 */
	@Bizlet("获取当前用户岗位标识")
	public  static String GetCurrentPositionFlg(HashMap orgMap,String orgNum,String orglevel){
		String 	roleCd = "0";
		//获取岗位的map
		HashMap roleMap =  (HashMap) orgMap.get("org"+orgNum);
		Set keys = roleMap.keySet();
//		如果是总行的话，获取总行的权限(机构级别)
		if("1".equals(orglevel)){
			if(keys.contains("P1083")){
				if(keys.contains("P1001")){
					if(keys.contains("P1046") || keys.contains("P1082")){//既存在客户经理岗也存在评级发起岗或违约发起岗
						roleCd ="41";
					}
				}else if(keys.contains("P1046")|| keys.contains("P1082")){
					roleCd ="41";
				}else{
					roleCd ="4";//违约管理岗
				}
			}else{
				if(keys.contains("P1001")){
					if(keys.contains("P1046") || keys.contains("P1082")){//既存在客户经理岗也存在评级发起岗或违约发起岗
						roleCd ="3";
					}
				}else if(keys.contains("P1046")|| keys.contains("P1082")){
					roleCd ="3";
				}else{
					roleCd ="1";
				}
			}
		}else{//非总行
			if(keys.contains("P1083")){
				if(keys.contains("P1001")){
					if(keys.contains("P1046") || keys.contains("P1082")){//既存在客户经理岗也存在评级发起岗或违约发起岗
						roleCd ="3";
					}
				}else if(keys.contains("P1046")|| keys.contains("P1082")){
					roleCd ="3";
				}else{
					roleCd ="1";
				}
			}else{
				if(keys.contains("P1001")){
					if(keys.contains("P1046") || keys.contains("P1082")){//既存在客户经理岗也存在评级发起岗或违约发起岗
						roleCd ="3";
					}
				}else if(keys.contains("P1046")|| keys.contains("P1082")){
					roleCd ="3";
				}else{
					roleCd ="1";
				}
			}
		}
		return roleCd;
	}
	@Bizlet("是否包含评级发起岗")
	public  static String GetPositionFlg(HashMap orgMap,String orgNum){
		String 	roleCd = "0";
		//获取岗位的map
		HashMap roleMap =  (HashMap) orgMap.get("org"+orgNum);
		Set keys = roleMap.keySet();
		if(keys.contains("P1046")){
			roleCd="1";
		}
		return roleCd;
	}
	
	
	/**
	 * 将流程中的节点代码转换为岗位代码， 节点信息代码第二位代表机构级别 将第二位换成 1 ；就转换为当前流程中的岗位；
	 * @param orgMap
	 * @param orgNum
	 * @return
	 */
	@Bizlet("获取流程中的岗位代码")
	public  static String ReplacePosNum2(String pos){
		if(null != pos && !"".equals(pos)){
			if(pos.indexOf("P_")!=-1){
				pos = pos.substring(pos.indexOf("P_")+2,pos.length());
			}
			if(pos.lastIndexOf("_")!=-1){
				pos = pos.substring(0,pos.lastIndexOf("_"));
			}
		}
		String s1=pos.substring(0,1); 
		String s2=pos.substring(2,pos.length());
		String posCd = s1 + "1" + s2;
		System.out.println("posCd=" + posCd);
		System.out.println("mima===========");
		return posCd ;
	}
	
	public static void main(String[] args) { 
		String str="P$005"; 
		String s=""; 
		try { 
		s = replace(str,2,"Q"); 
		} catch (Throwable e) { 
		e.printStackTrace(); 
		} 
		System.out.println(s); 
	} 
	
	public static String replace(String str,int n,String newChar) throws Throwable{ 
		String s1=""; 
		String s2=""; 
		try{ 
		s1=str.substring(0,n-1); 
		s2=str.substring(n,str.length()); 
		}catch(Exception ex){ 
		throw new Throwable("替换的位数大于字符串的位数"); 
		} 
		return s1+newChar+s2; 
	} 

}
