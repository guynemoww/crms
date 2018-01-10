/**
 * 
 */
package com.bos.irm.modelCalc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.internal.ObjectData;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import sun.util.logging.resources.logging;

import com.bos.irmPro.CallBackForSubmitProcess;
import com.bos.pub.DecisionUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.ibm.db2.jcc.b.s;
import com.primeton.data.sdo.impl.DataObjectImpl;
import com.primeton.data.sdo.impl.types.DataObjectType;

import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.helper.DataFactory;

/**
 * @author zhangqi
 * @date 2014-04-16 15:16:12
 *
 */

@Bizlet("模型相关操作")
public class ModelCalc {
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);

	private static String toJavaName(String name) {
		if (null == name)
			return null;
		if (name.length() < 1)
			return name;
		char first = name.charAt(0);
		if (name.contains("_") == false && (first < 'A' || first > 'Z')) {
			return name;
		}
		name = name.toLowerCase();
		StringBuilder sb = new StringBuilder();
		for (int i = 0, size = name.length(); i < size; i++) {
			char ch = name.charAt(i);
			if (ch == '_') {
				sb.append(Character.toUpperCase(name.charAt(i + 1)));
				i++;
				continue;
			}
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * @param modelId iraApplyId
	 *            模型主键
	 * @return
	 * @author zhangqi
	 */
	
	public static BigDecimal expressionBigDecimal(String expStr,Map<String, Object> params){
		Expression compiledExp = AviatorEvaluator.compile(expStr);
		BigDecimal result = (BigDecimal)compiledExp.execute(params);
		return result;
	}
	public static HashMap getFinanceValues(String r_remaks,DataObject[] arr){
 		String [] r=r_remaks.split(",");
 		HashMap map=new HashMap();
 		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				 
				
 			 if(arr[j].get("PROJECT_CD").equals(r[i])){
 				 
 				 map.put(r[i], arr[j].get("PROJECT_VALUE"));
 				 
  				 
  				break;
				 
			 }else{
				 continue;
			 }
				
			}
			
		}
		
		return map;
	}
	
	
	
	
	
	@Bizlet("财务指标计算")
	public static DataObject[] getFinanceIndex(String iraApplyId,String modelId) {
		DataObject[] arr = new DataObject[0];
		DataObject[] indexArr = new DataObject[0];
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("modelId", modelId);
		map.put("iraApplyId", iraApplyId);
		Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.irm.modelCalc.model.getFinanceIndexScore", map);
		arr = new DataObject[tmp.length];
		 if(tmp.length==0){ return null;}
		for (int i = 0; i < tmp.length; i++) {
			DataObject dat = (DataObject) tmp[i];
			arr[i] = new DataObjectImpl(new DataObjectType());
			List list = dat.getInstanceProperties();

			for (int j = 0; j < list.size(); j++) {
				Property prop = (Property) list.get(j);
				if(prop.getName().contains("_")){
					arr[i].set(prop.getName(), dat.get(prop));   
  				}
			}
//			System.out.println( arr[i]);  //取出所有的财务数据
		}

//		System.out.println( arr[0].get("CUSTOMER_TYPE_CD").toString());
		map.put("CUSTOMER_TYPE_CD", arr[0].get("CUSTOMER_TYPE_CD").toString());
		Object[] result = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.irm.modelCalc.model.getFinanceIndex", map);
		
		indexArr = new DataObject[result.length];

		for (int i = 0; i < result.length; i++) {
			DataObject dat = (DataObject) result[i];
			indexArr[i] = new DataObjectImpl(new DataObjectType());
			List list = dat.getInstanceProperties();

			for (int j = 0; j < list.size(); j++) {
				Property prop = (Property) list.get(j);
				if(prop.getName().contains("_")){
					indexArr[i].set(prop.getName(), dat.get(prop));

				}
			}	
			
//			System.out.println( indexArr[i]);
		}
		if (null == arr) {
			arr = new DataObject[0];
			indexArr = new DataObject[0];
	
		}
		
		MathContext mc = new MathContext(1, RoundingMode.HALF_DOWN);
		BigDecimal value = null;

		for (int i = 0; i < indexArr.length; i++) {
		String  R_EMARKS=	(String) indexArr[i].get("R_EMARKS");
//		System.out.println(indexArr[i]);
		HashMap a=getFinanceValues(R_EMARKS,arr);
		
		String in_logic = (String)indexArr[i].get("INDEX_CALC_LOGIC");
//		 System.out.println("!!!!"+indexArr[i].get("INDEX_CALC_LOGIC"));

		 String[] in_calc_logic =in_logic.split(";");
		 BigDecimal[] bd=new BigDecimal[in_calc_logic.length];
 		 for (int j = 0; j < in_calc_logic.length; j++) {
			 bd[j] = new BigDecimal(in_calc_logic[j]);
//			 System.out.println("!!!!"+ bd[j]);
			
 		}
		 //System.out.println("!!!!"+indexArr[i].get("INDEX_CODE"));
		 if("Y107".equals(indexArr[i].get("INDEX_CODE"))){
			String  MARKS=	(String) indexArr[i].get("R_EMARKS");
			String[] mk=MARKS.split(",");
//    System.out.println(mk[0].toString()+"!"+mk[1].toString()+"!"+mk[2].toString()+"!"+mk[3].toString());
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
			 BigDecimal av3= (BigDecimal) a.get(mk[2].toString());
			 BigDecimal av4= (BigDecimal) a.get(mk[3].toString());
			 
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 if(bv4==null || bv4.equals(new BigDecimal(0))){
				 bv4=new  BigDecimal(0.01);
			 }
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
			 params.put("av3",av3 );
			 params.put("av4",av4 );
			 params.put("bv4",bv4 );
			 params.put("w",new  BigDecimal("10000") );

			 
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+mk[2].toString()+"--->"
			 +av3.toString()+"!"+mk[3].toString()+"--->"+av4.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
//			 System.out.println(av1+"!"+av2+"!"+av3+"!"+av4+"!"+bv4+"!"+bv5) ;

			 BigDecimal sum_amt =  expressionBigDecimal("(av1-av2-av3-av4)/w", params);
			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
 				 value=expressionBigDecimal("sum_amt/bv4*INDEX_SCORE", params1);

			 }
			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
			 System.out.println(indexArr[i]);
				logger.info(iraApplyId+"Y107有形净资产:"+value.toString());


		 }
		 if("Y108".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
		 
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 BigDecimal bv6= bd[2];
//			 System.out.println(av1+"!"+av2+"!"+bv4+"!"+bv5) ;
			 
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!"+bv6.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));


			 if(av2==null || av2.equals(new BigDecimal(0))){
				 av2=new  BigDecimal(0.01);
			 }
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
			 
 
			 BigDecimal sum_amt =  expressionBigDecimal("av1/av2", params);
//			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv4)<0 ){
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else if (sum_amt.compareTo(bv5)>0){ 
				 value=new BigDecimal(0);
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
				 params1.put("v4",new BigDecimal("1"));
				 params1.put("v5",bv6);

 				 value=expressionBigDecimal("(v4-(sum_amt-bv4)/v5)*(INDEX_SCORE-v4)+v4", params1);

			 }
//			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
//			 System.out.println(indexArr[i]);
			 logger.info("Y108资产负债率:"+value.toString());

		 }
		 if("Y109".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
		 
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 BigDecimal bv6= bd[2];
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 if(av2==null||av2.equals(new BigDecimal(0))){
				 av2=new  BigDecimal(0.01, mc);
			 }
			 params.put("av2",av2 );
//			 System.out.println(av1+"!"+av2+"!"+bv4+"!"+bv5) ;
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!"+bv6.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));

			 BigDecimal sum_amt =  expressionBigDecimal("av1/av2", params);
			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
				 params1.put("v4",new BigDecimal("1"));
				 params1.put("v5",bv6);

 				 value=expressionBigDecimal("(v4-(bv4-sum_amt)/v5)*(INDEX_SCORE-v4)+v4", params1);

			 }
//			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
//			 System.out.println(indexArr[i]);

			 logger.info("Y109流动比率:"+value.toString());

		 }
		 if("Y110".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
			 BigDecimal av3= (BigDecimal) a.get(mk[2].toString());
			 BigDecimal av4= (BigDecimal) a.get(mk[3].toString());
		 
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 BigDecimal bv6= bd[2];
			 if(av4==null||av4.equals(new BigDecimal(0))){
				 av4=new  BigDecimal(0.01);
			 }
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
			 params.put("av3",av3 );
			 params.put("av4",av4 );
			 
//			 System.out.println(av1+"!"+av2+"!"+av3+"!"+av4+"!"+bv4+"!"+bv5) ;
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+mk[2].toString()+"--->"
					 +av3.toString()+"!"+mk[3].toString()+"--->"+av4.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!"+bv6.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
			 BigDecimal sum_amt =  expressionBigDecimal("(av1-av2-av3)/av4", params);
			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
				 params1.put("v4",new BigDecimal("1"));
				 params1.put("v5",bv6);

 				 value=expressionBigDecimal("(v4-(bv4-sum_amt)/v5)*(INDEX_SCORE-v4)+v4", params1);

			 }
//			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
//			 System.out.println(indexArr[i]);
			 logger.info("Y110速动比率:"+value.toString());


		 }
		 if("Y111".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
 
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 BigDecimal bv6= bd[2];
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!"+bv6.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
			 
			 if(av2==null ||av2.equals(new BigDecimal(0))){
				 av2=new  BigDecimal(0.01);
			 }
			 params.put("av2",av2 );
//			 System.out.println(av1+"!"+av2+"!"+bv4+"!"+bv5) ;
			 BigDecimal sum_amt =  expressionBigDecimal("(av1+av2)/av2", params);
//			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
				 params1.put("v4",new BigDecimal("1"));
				 params1.put("v5",bv6);

 				 value=expressionBigDecimal("(v4-(bv4-sum_amt)/v5)*(INDEX_SCORE-v4)+v4", params1);

			 }
			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
			 System.out.println(indexArr[i]);

			 logger.info("Y111利息保障倍数:"+value.toString());
		 }
		 if("Y112".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= getY112Score(iraApplyId);
			 BigDecimal bv3= bd[0];
			 BigDecimal bv4= bd[1];
//			 System.out.println(av1+"@"+av2+"@"+bv3+"@"+bv4);
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+av2.toString()+"!"+bv3.toString()+"!"+bv4.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
				
			 if(av2==null||av2.equals(new BigDecimal(0))){
				 av2=new  BigDecimal(0.01);
			 }
			 System.out.println(av1+"@"+av2+"@"+bv3+"@"+bv4);
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
			 BigDecimal sum_amt =  expressionBigDecimal("av1/av2", params);
			 
			 
			 if (sum_amt.compareTo(bv3)>0 ){
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else if (sum_amt.compareTo(bv4)<0){ 
				 value=new BigDecimal(0);
			 }else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv3", bv3);
				 
  				 value=expressionBigDecimal("sum_amt/bv3*INDEX_SCORE", params1); 
				 
			 }

			 
			 indexArr[i].set("iValue", value.toString());
			 logger.info("Y112净资产与年末贷款余额比例:"+value.toString());
		 }
		 if("Y113".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
  
			 BigDecimal bv4= bd[0];
 			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
 			 
			 
			 System.out.println(av1+"!"+bv4) ;
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+bv4.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
				
			  
			 if (av1==null ||av1.equals(new BigDecimal(0))){
 				 value=new BigDecimal(0);
			 }
			 
			 else if (av1.compareTo(bv4)>0 ){
				  

				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");

			 }else {
				 
				 value=new BigDecimal(0);
			 }
			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
			 System.out.println(indexArr[i]);

			 logger.info("Y113经营活动产生的净现金流量:"+value.toString());

		 }
		 if("Y114".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= getY114Score(iraApplyId);
 
			 if(av2==null||av2.equals(new BigDecimal(0))){
				 av2=new  BigDecimal(0.01);
			 }
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
			 
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!平均资产总额:"+av2.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!"+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));

			 
			 
			 System.out.println(av1+"!"+av2+"!"+bv4+"!"+bv5) ;

			 BigDecimal sum_amt =  expressionBigDecimal("av1/av2", params);
			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
			 

 				 value=expressionBigDecimal("sum_amt/bv4*INDEX_SCORE", params1);

			 }
			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
			 System.out.println(indexArr[i]);

			 logger.info("Y114总资产利润率:"+value.toString());

		 }
		 if("Y115".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
			 if(av2==null||av2.equals(new BigDecimal(0))){
				 av2=new  BigDecimal(0.01);
			 }
 
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
			 
//			 System.out.println(av1+"!"+av2+"!"+bv4+"!"+bv5) ;
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
				
			 BigDecimal sum_amt =  expressionBigDecimal("av1/av2", params);
			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
				  

 				 value=expressionBigDecimal("sum_amt/bv4*INDEX_SCORE", params1);

			 }
			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
			 System.out.println(indexArr[i]);

			 logger.info("Y115销售利润率:"+value.toString());

		 }
		 if("Y116".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
			 BigDecimal av3= (BigDecimal) a.get(mk[2].toString());

 
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 if(av1==null||av1.equals(new BigDecimal(0))){
				 av1=new  BigDecimal(0.01);
			 }
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
			 params.put("av3",av3 );
			 
			 
//			 System.out.println(av1+"!"+av2+"!"+av3+"!"+bv4+"!"+bv5) ;
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+mk[2].toString()+"--->"+av3.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
				

			 BigDecimal sum_amt =  expressionBigDecimal("(av1-av2-av3)/av1", params);
			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
				  

 				 value=expressionBigDecimal("sum_amt/bv4*INDEX_SCORE", params1);

			 }
			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
			 System.out.println(indexArr[i]);

			 logger.info("Y116主营业务利润率:"+value.toString());

		 }
		 if("Y117".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
 
 
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 BigDecimal bv6= bd[2];
			 if(av2==null||av2.equals(new BigDecimal(0)) ){
				 av2=new  BigDecimal(0.01);
			 }
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
 			 
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!"+bv6.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
				
//			 System.out.println(av1+"!"+av2+"!"+bv4+"!"+bv5) ;

			 BigDecimal sum_amt =  expressionBigDecimal("av1/av2", params);
			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
				 params1.put("v4",new BigDecimal("1"));
				 params1.put("v5",bv6);

 				 value=expressionBigDecimal("(v4-(bv4-sum_amt)/v5)*(INDEX_SCORE-v4)+v4", params1);

			 }
			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
			 System.out.println(indexArr[i]);

			 logger.info("Y117主营收入现金率:"+value.toString());

		 }
		 if("Y118".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
			 BigDecimal av3= (BigDecimal) a.get(mk[2].toString());

 
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 if(av2==null||av2.equals(new BigDecimal(0))){
				 av2=new  BigDecimal(0.01);
			 }
			 if(av3==null||av3.equals(new BigDecimal(0))){
				 av3=new  BigDecimal(0.01);
			 }
			 
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
			 params.put("av3",av3 );
			 
//			 System.out.println(av1+"!"+av2+"!"+av3+"!"+bv4+"!"+bv5) ;
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+mk[2].toString()+"--->"+av3.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
				
			 BigDecimal sum_amt =  expressionBigDecimal("av1/(av2+av3)", params);
			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
				 

 				 value=expressionBigDecimal("sum_amt/bv4*INDEX_SCORE", params1);

			 }
			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
			 System.out.println(indexArr[i]);

			 logger.info("Y118应收账款和应收票据周转次数:"+value.toString());

		 }
		 if("Y119".equals(indexArr[i].get("INDEX_CODE"))){
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av1= (BigDecimal) a.get(mk[0].toString());
			 BigDecimal av2= (BigDecimal) a.get(mk[1].toString());
 
			 if(av2==null||av2.equals(new BigDecimal(0))){
				 av2=new  BigDecimal(0.01);
			 }
			 BigDecimal bv4= bd[0];
			 BigDecimal bv5= bd[1];
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("av1",av1 );
			 params.put("av2",av2 );
 			 
			 logger.info(mk[0].toString()+"--->"+av1.toString()+"!"+mk[1].toString()+"--->"+av2.toString()+"!"+bv4.toString()+"!"+bv5.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
				
//			 System.out.println(av1+"!"+av2+"!"+bv4+"!"+bv5) ;

			 BigDecimal sum_amt =  expressionBigDecimal("av1/av2", params);
			 System.out.println(sum_amt);
			 if (sum_amt.compareTo(bv5)<0 ){
				 value=new BigDecimal(0);
			 }
			 else if (sum_amt.compareTo(bv4)>0){ 
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
			 }
			 else{
				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv4", bv4);
				 

 				 value=expressionBigDecimal("sum_amt/bv4*INDEX_SCORE", params1);

			 }
			 System.out.println(value);
			 indexArr[i].set("iValue", value.toString());
			 System.out.println(indexArr[i]);

			 logger.info("Y119存货周转次数:"+value.toString());

		 }
		 if("Y122".equals(indexArr[i].get("INDEX_CODE"))){
			 BigDecimal bv0= bd[0];
			 BigDecimal bv1= bd[1];
			 BigDecimal bv2= bd[2];
			 BigDecimal bv3= bd[3];
			 BigDecimal av1= getY122Score(iraApplyId);
//			 System.out.println(bv0+"@"+bv1+"@"+bv2+"@"+bv3+"@"+av1);
			 
			 logger.info(bv0.toString()+"!"+bv1.toString()+"!"+bv2.toString()+"!"+bv3.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));
				
			 value=new BigDecimal(0);
 			 if(av1.compareTo(bv0)==0){
				 value=bv0;
			 }
			    if(av1.compareTo(bv1)==0){
				 value=bv1;
			 }
			   if(av1.compareTo(bv2)==0){
				 value=bv2;
			 }
			   if(av1.compareTo(bv3)==0){
				 value=bv3;
			 }   
			  
			 
			 indexArr[i].set("iValue", value.toString());
			 logger.info("Y122近三年利润情况:"+value.toString());

		 }
		 if("Y123".equals(indexArr[i].get("INDEX_CODE"))){
			 BigDecimal bv0= bd[0];
			 BigDecimal bv1= bd[1];
			 BigDecimal bv2= bd[2];
			 BigDecimal av1= getY123Score(iraApplyId);
			 logger.info(bv0.toString()+"!"+bv1.toString()+"!"+bv2.toString()+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));

			 value=new BigDecimal(0);
			 if(av1.compareTo(bv0)>0){
				 value=new BigDecimal(3);
			 }
			 if(av1.compareTo(bv1)>0 && av1.compareTo(bv0)<0){
				 value=new BigDecimal(2);
			 }
			 if(av1.compareTo(bv2)>0 && av1.compareTo(bv1)<0){
				 value=new BigDecimal(1);
			 }
			 indexArr[i].set("iValue", value.toString());
			 logger.info("Y123销售增长率:"+value.toString());

		 }
		 if("Y125".equals(indexArr[i].get("INDEX_CODE"))){
			 BigDecimal bv0= bd[0];
			 BigDecimal bv1= bd[1];
			 BigDecimal bv2= bd[2];
 			 BigDecimal av1= getY125Score(iraApplyId);
 			 
			 logger.info(bv0.toString()+"!"+bv1.toString()+"!"+bv2.toString()+"!担保金额："+av1+"!满分值为:"+indexArr[i].get("INDEX_SCORE"));

			 value=new BigDecimal(0);
			 String  MARKS=	(String) indexArr[i].get("R_EMARKS");
				String[] mk=MARKS.split(",");
			 BigDecimal av2= (BigDecimal) a.get(mk[0].toString());
//			 System.out.println("Y125!!"+bv0+"!!"+bv1+"!!"+av1+"!!"+av2);
			 if(av1==null || av1.compareTo(new BigDecimal(0)  )==0 ){
				 value=(BigDecimal) indexArr[i].get("INDEX_SCORE");
				 
			 }else  if( av2==null ||av2.compareTo(new BigDecimal(0))==0  ){
				 value=new BigDecimal(0);
				 
			 }else {
				 HashMap<String, Object> params = new HashMap<String, Object>();
				 params.put("av1",av1 );
				 params.put("av2",av2);
				 BigDecimal sum_amt =  expressionBigDecimal("av1/av2", params);
				 if(sum_amt.compareTo(bv0)==-1){
					 value=new BigDecimal(5);
					 }
				 else if(sum_amt.compareTo(bv1)==1){
					 value=new BigDecimal(0);
					 }else{

				 HashMap<String, Object> params1 = new HashMap<String, Object>();
				 params1.put("sum_amt",sum_amt );
				 params1.put("INDEX_SCORE",indexArr[i].get("INDEX_SCORE") );
				 params1.put("bv0", bv0);
				 params1.put("v4",new BigDecimal("1"));
				 params1.put("v5",bv2);
				 

 				 value=expressionBigDecimal("(v4-(sum_amt-bv0)/v5)*(INDEX_SCORE-v4)+v4", params1);
					 }
			 }
			 
			 
			 
			 
			 
			 indexArr[i].set("iValue", value.toString());
			 logger.info("Y125对外担保比例:"+value.toString());

			
		 }
		}
		 
 		
		
		return indexArr;
	}
	@Bizlet("非财务指标计算")
	public static  DataObject[] getNonFinanceIndexScore(String iraApplyId){
		DataObject[] arr = new DataObject[0];
		if (StringUtils.isEmpty(iraApplyId)) {
			return arr;
		}
		{
			Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("iraApplyId", iraApplyId);
			Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
					"com.bos.irm.modelCalc.model.getNonFinanceIndexScore", map);
			arr = new DataObject[tmp.length];
			for (int i = 0; i < tmp.length; i++) {
				DataObject dat = (DataObject) tmp[i];
				arr[i] = new DataObjectImpl(new DataObjectType());
				List list = dat.getInstanceProperties();
				System.out.println("!!!!"+dat);
				for (int j = 0; j < list.size(); j++) {
					Property prop = (Property) list.get(j);
					char ch = prop.getName().charAt(0);
					if (prop.getName().contains("_")
							|| (ch >= 'A' && ch <= 'Z')) {
						arr[i].set(toJavaName(prop.getName()), dat.get(prop));
					} else {
						arr[i].set(prop.getName(), dat.get(prop));
					}
				}
			}
		}
		if (null == arr)
			arr = new DataObject[0];
	
		return arr;
	}

	@Bizlet("模型得分对应信用等级显示")
	public static String getModelCreditRating(String modelId,BigDecimal score ){
		BigDecimal score1=score.setScale(0,BigDecimal.ROUND_HALF_UP);
		String creditRatingCd = null;
		DataObject[] arr = new DataObject[0];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("modelId", modelId);
		Object[] tmp = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,"com.bos.irm.modelCalc.model.getModelScale", map);
		arr = new DataObject[tmp.length];
		for (int i = 0; i < tmp.length; i++) {
			DataObject dat = (DataObject) tmp[i];
			arr[i] = new DataObjectImpl(new DataObjectType());
			List list = dat.getInstanceProperties();
			
			for (int j = 0; j < list.size(); j++) {
				Property prop = (Property) list.get(j);
				char ch = prop.getName().charAt(0);
				if (prop.getName().contains("_") || (ch >= 'A' && ch <= 'Z')) {
					arr[i].set(toJavaName(prop.getName()), dat.get(prop));

				} else {
					arr[i].set(prop.getName(), dat.get(prop));

				}
			}
			if (null == arr) {
				arr = new DataObject[0];
			}
		}
	
		for (int i = 0; i < arr.length; i++) {
			
			DataObject modelScale = arr[i];

			
			if((score1.compareTo(modelScale.getBigDecimal("minValue"))==1||score1.compareTo(modelScale.getBigDecimal("minValue"))==0)&&(score1.compareTo(modelScale.getBigDecimal("maxValue"))==-1||score1.compareTo(modelScale.getBigDecimal("maxValue"))==0)){
				creditRatingCd =	modelScale.getString("creditRatingCd");
			
			}
		}
		return creditRatingCd;
		
	}
	@Bizlet("模型得分对应信用等级显示xg")
	public static String getModelCreditRating_xg(String modelId,DataObject[] nonFinanceIndexs,BigDecimal indexScore ,String creditRatingCd ){
			String creditRatingCd1=creditRatingCd;
			BigDecimal score1=indexScore.setScale(0,BigDecimal.ROUND_HALF_UP);
 			System.out.println(modelId+";"+indexScore+";"+creditRatingCd);
 			for (int i = 0; i < nonFinanceIndexs.length; i++) {
 	 			System.out.println( nonFinanceIndexs[i].get("indexCode"));

			if("Y135".equals(nonFinanceIndexs[i].get("indexCode"))&& nonFinanceIndexs[i].getBigDecimal("indexScore").compareTo(new BigDecimal("0"))==0
					&& score1.compareTo(new BigDecimal("57"))==1){
				
				creditRatingCd1="B";
				break;
				
			}else {
				continue;
			}
 			}

			
			return creditRatingCd1;
	}
	
	public static  BigDecimal getY112Score(String iraApplyId ){
		DataObject[] arr = new DataObject[0];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("iraApplyId", iraApplyId);
		Object[] tmpjjye = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.irm.modelCalc.model.getjjye", map);
		arr=new DataObject[tmpjjye.length];
		for (int i = 0; i < tmpjjye.length; i++) {
			DataObject dat = (DataObject) tmpjjye[i];
			arr[i] = new DataObjectImpl(new DataObjectType());
			List list = dat.getInstanceProperties();
			for (int j = 0; j < list.size(); j++) {
				Property prop = (Property) list.get(j);
				arr[i].set(prop.getName(), dat.get(prop));
				System.out.println(arr[i]);
			}
		
		}
 		
		BigDecimal a= arr[0].getBigDecimal("JJYE");
		if(a==null||a.equals(new BigDecimal(0))){ a=new BigDecimal(0);}
		return  a;
	}
	
	public static  BigDecimal getY122Score(String iraApplyId ){
		DataObject[] arr = new DataObject[0];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("iraApplyId", iraApplyId);
		Object[] tmpjlr = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.irm.modelCalc.model.getjlr", map);
		arr=new DataObject[tmpjlr.length];
		if(tmpjlr.length<3){
			BigDecimal s=new BigDecimal(0);
			return s;
		}else{
		for (int i = 0; i < tmpjlr.length; i++) {
			DataObject dat = (DataObject) tmpjlr[i];
			arr[i] = new DataObjectImpl(new DataObjectType());
			List list = dat.getInstanceProperties();
			for (int j = 0; j < list.size(); j++) {
				Property prop = (Property) list.get(j);
				arr[i].set(prop.getName(), dat.get(prop));
				System.out.println(arr[i]);
			}
		
		}
		BigDecimal a=new BigDecimal(0);
		 if(arr[0].getBigDecimal("PROJECT_VALUE").compareTo(arr[1].getBigDecimal("PROJECT_VALUE"))>0 && arr[1].getBigDecimal("PROJECT_VALUE").compareTo(arr[2].getBigDecimal("PROJECT_VALUE"))>0){
			 a=new BigDecimal(3);
		 }
		 if (arr[0].getBigDecimal("PROJECT_VALUE").compareTo(arr[1].getBigDecimal("PROJECT_VALUE"))<0 && arr[1].getBigDecimal("PROJECT_VALUE").compareTo(arr[2].getBigDecimal("PROJECT_VALUE"))<0){
			 a=new BigDecimal(0);
		 }
		 if (arr[0].getBigDecimal("PROJECT_VALUE").compareTo(arr[1].getBigDecimal("PROJECT_VALUE"))>0 && arr[1].getBigDecimal("PROJECT_VALUE").compareTo(arr[2].getBigDecimal("PROJECT_VALUE"))<0 && arr[0].getBigDecimal("PROJECT_VALUE").compareTo(arr[2].getBigDecimal("PROJECT_VALUE"))>0){
			 a=new BigDecimal(2);
		 }
		 if (arr[0].getBigDecimal("PROJECT_VALUE").compareTo(arr[1].getBigDecimal("PROJECT_VALUE"))>0 && arr[1].getBigDecimal("PROJECT_VALUE").compareTo(arr[2].getBigDecimal("PROJECT_VALUE"))<0 && arr[0].getBigDecimal("PROJECT_VALUE").compareTo(arr[2].getBigDecimal("PROJECT_VALUE"))<0){
			 a=new BigDecimal(1);
		 }
		 
		return  a;
		}
	}
	
	public static  BigDecimal getY123Score(String iraApplyId ){
		DataObject[] arr = new DataObject[0];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("iraApplyId", iraApplyId);
		Object[] tmpyysr = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.irm.modelCalc.model.getyysr", map);
 		
		arr=new DataObject[tmpyysr.length];
		if(tmpyysr.length<2){
			BigDecimal s=new BigDecimal(0);
			return s;
		}else {
		for (int i = 0; i < tmpyysr.length; i++) {
			DataObject dat = (DataObject) tmpyysr[i];
			arr[i] = new DataObjectImpl(new DataObjectType());
			List list = dat.getInstanceProperties();
			for (int j = 0; j < list.size(); j++) {
				Property prop = (Property) list.get(j);
				arr[i].set(prop.getName(), dat.get(prop));
				System.out.println(arr[i]);
			}
		
		}
		 HashMap<String, Object> params = new HashMap<String, Object>();
		 BigDecimal av1=arr[0].getBigDecimal("PROJECT_VALUE");
		 BigDecimal av2=arr[1].getBigDecimal("PROJECT_VALUE");
//		 System.out.println(av1+"@"+av2);
		 logger.info("getY123Score"+av1.toString()+"!"+av2.toString());

		 if(av2==null || av2.equals(new BigDecimal(0))){
			 av2=new BigDecimal(0.01);
		 }
		 params.put("av1",av1);
		 params.put("av2",av2);
			 
 
		 BigDecimal sum_amt =  expressionBigDecimal("(av1-av2)/av2", params);
		 System.out.println(sum_amt);
 		return  sum_amt;
		}
	}
	
	
	
	
	public static  BigDecimal getY114Score(String iraApplyId ){
		DataObject[] arr = new DataObject[0];
		BigDecimal a= new BigDecimal(0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("iraApplyId", iraApplyId);
		Object[] tmpyysr = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.irm.modelCalc.model.getyysr1", map);
 		
		arr=new DataObject[tmpyysr.length];
		if(tmpyysr.length==1){
			for (int i = 0; i < tmpyysr.length; i++) {
				DataObject dat = (DataObject) tmpyysr[i];
				arr[i] = new DataObjectImpl(new DataObjectType());
				List list = dat.getInstanceProperties();
				for (int j = 0; j < list.size(); j++) {
					Property prop = (Property) list.get(j);
					arr[i].set(prop.getName(), dat.get(prop));
					System.out.println(arr[i]);
				}
			
			}
			 a=arr[0].getBigDecimal("PROJECT_VALUE");
			 
		}
		if(tmpyysr.length>1) {
		for (int i = 0; i < tmpyysr.length; i++) {
			DataObject dat = (DataObject) tmpyysr[i];
			arr[i] = new DataObjectImpl(new DataObjectType());
			List list = dat.getInstanceProperties();
			for (int j = 0; j < list.size(); j++) {
				Property prop = (Property) list.get(j);
				arr[i].set(prop.getName(), dat.get(prop));
				System.out.println(arr[i]);
			}
		
		}
		 HashMap<String, Object> params = new HashMap<String, Object>();
		 BigDecimal av1=arr[0].getBigDecimal("PROJECT_VALUE");
		 BigDecimal av2=arr[1].getBigDecimal("PROJECT_VALUE");
//		 System.out.println(av1+"@"+av2);
		 logger.info("getY114Score:"+av1.toString()+"!"+av2.toString());

		 
		 params.put("av1",av1);
		 params.put("av2",av2);
		 params.put("av3",new BigDecimal(2));
 
		 BigDecimal sum_amt =  expressionBigDecimal("(av1+av2)/av3", params);
		 System.out.println(sum_amt);
 		a=  sum_amt;
		}
		return   a;
	}
	
	
	public static  BigDecimal getY125Score(String iraApplyId ){
		DataObject[] arr = new DataObject[0];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("iraApplyId", iraApplyId);
		Object[] tmpyysr = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME,
				"com.bos.irm.modelCalc.model.getdbje", map);
		arr=new DataObject[tmpyysr.length];
		if(tmpyysr.length==0){
			return new BigDecimal(0);
		}
 		for (int i = 0; i < tmpyysr.length; i++) {
			DataObject dat = (DataObject) tmpyysr[i];
			arr[i] = new DataObjectImpl(new DataObjectType());
			List list = dat.getInstanceProperties();
			for (int j = 0; j < list.size(); j++) {
				Property prop = (Property) list.get(j);
				arr[i].set(prop.getName(), dat.get(prop));
				System.out.println(arr[i]);
			}
		
		}
		
 		 BigDecimal sum_amt=arr[0].getBigDecimal("PROJECT_VALUE");
		  
		 System.out.println(sum_amt);
 		return  sum_amt;
	}
	
	
	
	
	
	@Bizlet("模型选择规则")
	public static HashMap<String, Object> execModelRule(HashMap<String, Object> param) throws Exception {
		 

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rind", "模型选择规则");
		DecisionUtil du = new DecisionUtil();
		return du.execRule(map, param);
	}
	
	@Bizlet("专业贷款标识规则")
	public static HashMap<String, Object> execLoanRule(HashMap<String, Object> param) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rind", "专业贷款标识");
		DecisionUtil du = new DecisionUtil();
		return du.execRule(map, param);
	}
	
	@Bizlet("评级认定权限")
	public static HashMap<String, Object> execPermissionRule(HashMap<String, Object> param) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rind", "评级认定权限");
		DecisionUtil du = new DecisionUtil();
		return du.execRule(map, param);
	}

	
	
	

}
