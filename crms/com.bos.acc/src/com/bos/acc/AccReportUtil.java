
package com.bos.acc;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

/**
 * @author c_ture
 * @date 2014-12-03 15:07:42
 *
 */
@Bizlet("")
public class AccReportUtil {
	
	static	DecimalFormat df = new DecimalFormat("0.000000"); //定义数据格式，保留六位小数
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Bizlet("获取图例单元数据")
	public static List<Map<String, String>> lineCharBing(HashMap<String,Object> map){
		
		List<Map<String, String>> resultList = new ArrayList<Map<String,String>>();
		HashMap<String, Object> IndexDataMap = new HashMap<String, Object>();
		
		//查询分析明细
		Object[] analysisDetail = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.selectAnalysisDetail", map);
		if (null == analysisDetail || analysisDetail.length < 1) {
			return resultList;
		}
		for (int i = 0; i < analysisDetail.length; i++) {
			Map fmap = (Map) analysisDetail[i];
			
			String flag ="";
			switch(i){
			case 0:flag="a";
			break;
			case 1:flag="b";
			break;
			case 2:flag="c";
			break;
			}
			//查询分析指标值
			Object[] indexData = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.acc.exportExcel.selectAnalysisIndexData", fmap);
			for (int j = 0; j < indexData.length; j++) {
					Map imap = (Map) indexData[j];
				if(imap.get("INDEX_CD") == null){
					IndexDataMap.put(flag +(String) imap.get("INDEX_CD"),null);
				}else{
					IndexDataMap.put(flag + (String) imap.get("INDEX_CD"), (BigDecimal)(imap.get("INDEX_VALUE_DATA_TYPE")));
				}
			}
			
		}

//		查询分析明细
		Object[] moduleDetail = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.selectModuleDetail", map);
		
		for (int j = 0; j < moduleDetail.length; j++) {
			Map mMap = (Map) moduleDetail[j];
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("serieName", (String)mMap.get("INDEX_NAME"));//指标名称
			String firstData ="";
			String secondData ="";
			String thirdData ="";
			BigDecimal a = new BigDecimal(0.00);
			BigDecimal b = new BigDecimal(0.00);
			BigDecimal c = new BigDecimal(0.00);
			if(IndexDataMap.get("a"+mMap.get("INDEX_CODE"))!=null){
				a =(BigDecimal)IndexDataMap.get("a"+mMap.get("INDEX_CODE").toString());
//				a = a.divide(new BigDecimal(10000));
				firstData =df.format(a);
//				firstData = df.format((BigDecimal)IndexDataMap.get("a"+mMap.get("INDEX_CODE").toString()));
			}
			if(IndexDataMap.get("b"+mMap.get("INDEX_CODE"))!=null){
				b =(BigDecimal)IndexDataMap.get("b"+mMap.get("INDEX_CODE").toString());
//				b = b.divide(new BigDecimal(10000));
				secondData =df.format(b);
//				secondData = df.format((BigDecimal)IndexDataMap.get("b"+mMap.get("INDEX_CODE").toString()));
			}
			if(IndexDataMap.get("c"+mMap.get("INDEX_CODE"))!=null){
				c =(BigDecimal)IndexDataMap.get("c"+mMap.get("INDEX_CODE").toString());
//				c = c.divide(new BigDecimal(10000));
				thirdData =df.format(c);
//				thirdData = df.format((BigDecimal)IndexDataMap.get("c"+mMap.get("INDEX_CODE").toString()));
			}
			String data1= "["+firstData+","+secondData+","+thirdData+"]";
			BigDecimal MaxStr=new BigDecimal(0.00);
			if(a.abs().compareTo(b.abs())>0){
				if(a.abs().compareTo(c.abs())>0){
					MaxStr=a.abs();
				}else{
					MaxStr=c.abs();
				}

			}else{
				if(b.abs().compareTo(c.abs())>0){
					MaxStr=b.abs();
				}else{
					MaxStr=c.abs();
				}
				
			}
			map1.put("maxStr", MaxStr.toString());
			map1.put("datas", data1);
			resultList.add(map1);

	}
//		Map<String, String> map1 = new HashMap<String, String>();
//		String data1 = "[0, 1000.9, 5000.5]";
//		map1.put("serieName", "长沙2");
//		map1.put("datas", data1);
//		Map<String, String> map2 = new HashMap<String, String>();
//		String data2 = "[30.9, 40.2, 50.7]";
//		map2.put("serieName", "株洲");
//		map2.put("datas", data2);
//
//		resultList.add(map1);
//		resultList.add(map2);

		return resultList;
	}
	
	@Bizlet("获取财报分析日期字符串")
	public static String getDeadlineStr(HashMap<String,Object> map){
		
		String DeadlineStr = "";
		//查询分析明细
		Object[] analysisDetail = DatabaseExt.queryByNamedSql(
				GitUtil.DEFAULT_DS_NAME,
				"com.bos.acc.exportExcel.selectAnalysisDetail", map);
		for (int i = 0; i < analysisDetail.length; i++) {
			Map fmap = (Map) analysisDetail[i];
			if(i==0){
			DeadlineStr= "'"+simpleDateFormat.format((Date)fmap.get("FINANCE_DEADLINE"))+"'";
			}else{
				DeadlineStr= DeadlineStr+","+"'"+simpleDateFormat.format((Date)fmap.get("FINANCE_DEADLINE"))+"'";
			}
		}
		DeadlineStr ="["+DeadlineStr+"]";
		//DeadlineStr ="['2012-12-31', '2013-12-31', '2014-12-31']";
		return DeadlineStr;
	}
	
	@Bizlet("获取财报分析指标最大值/5字符串")
	public static String getMaxDivideStr(List<Map<String, String>> resultList){
		
		String MaxDivideStr = "";
		BigDecimal max = new BigDecimal(0.00);
		for(int i =0;i<resultList.size();i++){
			Map map = (Map)resultList.get(i);
			BigDecimal maxStr =new BigDecimal((String)map.get("maxStr"));
			
			if(i==0){
				max =maxStr;
			}else{
				if(max.compareTo(maxStr)<0){
					max=maxStr;
				}
			}
		}
		
		MaxDivideStr = max.divide(new BigDecimal(5),0).toString();
		//DeadlineStr ="['2012-12-31', '2013-12-31', '2014-12-31']";
		return MaxDivideStr;
	}

	/**
	 * @param args
	 * @author 曾薛海
	 */
	public static void main(String[] args) {
		String str = "LEG_PER_COD, DEP_COD, TRN_DEP, OPN_DEP, ORIG_FROM, PROD_COD, CRD_CENT, CERT_TYPE, CERT_NUM, CUS_NO, CON_NO, TEL_NO, DUE_NUM, DUE_NUM_UN, DUE_TYPE, BRW_NAME, AMT, BEG_DATE, END_DATE, BEG_ITR_DATE, PRE_PAY_ITR_DATE, CLS_FLG, CURR_COD, PRM_CLS, AST_CLS, BUS_COD, PRM_PAY_TYP, AST_PAY_TYP, CUR_PRM_PAY_TYP, CUR_AST_PAY_TYP,FIX_RATE_FLAG, ITR_RATE_WAY, NOR_ITR_RATE, DEL_ITR_RATE, CPD_ITR_RATE, REL_ITR_RATE, CUS_PAY_PLAN_TYPE, CASPAN, CAL_DAYS, PAY_DATE, DIST_DAYS, ITR_CAL_RULE, AFTER_CASPAN, AFTER_PAY_DATE, DISC_FLAG, ITR_FRE_FLG, ITR_FRE_CYL, FREE_PROC_TYPE, CEAS_DISC_FLAG, END_DISC_FLAG, HLD_FLG, HLD_PRN_WAY, HLD_ITR_WAY, GRA_PERD_FLG, GRA_PRD_TYP, GRA_PRN_DAYS, GRA_ITR_DAYS, GRA_PRD_PRN_WAY, GRA_PRD_ITR_WAY, CAL_ITR_FLAG, ACR_ITR_FLG, ACR_ITR_TYP, CEAS_IMP_FLAG, ITR_SETTLE_TYPE, SETT_PNS_TYPE, IPR_PVS_BASE_RULE, ITR_CHG_FLG, PAY_ORDER, BAT_FLG, BAT_TYPE, STUD_PERD, CLEAR_FLG, TRUS_TO_PAY_FLG, STOP_PAY_FLG, BY_NEW_OLD_FLG, AST_DUE_NUM, FEE_FLAG, OFT_PRN_ITR_TYP, DEAL_FLG";
		str = str.toLowerCase();
		String[] strs=str.split(",");
		String[] cls = null;
		String jgstrs = "";
		String jgstr = "";
		for(String cl:strs){
			cls = cl.trim().split("_");
			String recl = "";
			String zhcl = "";
			for(int i=0;i<cls.length;i++ ){
				recl = cls[i];
				if(i > 0){
					zhcl = zhcl + recl.substring(0, 1).toUpperCase()+recl.substring(1, recl.length());
				}else{
					zhcl = recl;
				}
			}
			 
			jgstr = jgstr + ","+ zhcl;
			
		}
		jgstrs = jgstr.substring(1, jgstr.length());
		
		
		System.out.println(jgstrs);

	}

}
