package com.bos.risk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bsh.StringUtil;

import com.eos.system.annotation.Bizlet;

public class RiskSortUtil {
	
	@Bizlet("将Map添加到List中")
	public List<HashMap<String, Object>> addDataForList(List<HashMap<String, Object>> mapList,
			HashMap<String, Object> map){
		mapList.add(map);
		return mapList;
	}
	
	@SuppressWarnings("unchecked")
	@Bizlet("将list添加到List中")
	public List addList(List list,List addList){
		addList.add(list);
		return addList;
	}
	
	@Bizlet("将list中数据清空")
	public List clearList(List list){
		list.clear();
		return list;
	}
	
	@Bizlet("将list添加到List中")
	public static List compareToLowG2Result(List list){
		int maxIndex = 0;
		int maxNo = 0;
	/*	if(list.size() > 0){
			List maxG2ByLoan = (List)list.get(0);  //第一个债项所有结果
			if(maxG2ByLoan.size() > 0){
				Map maxG2Map = (Map)maxG2ByLoan.get(maxG2ByLoan.size()-1); //G2A
				Map maxG2Result = (Map)maxG2Map.get("result");
				List maxG2Grade = (List)maxG2Result.get("grades");
				Map maxDictMap = (Map)maxG2Grade.get(maxG2Grade.size()-1);
				maxNo = Integer.valueOf(maxDictMap.get("SORTNO").toString());
			}
		}*/
		for (int i = 0; i < list.size(); i++) {
			List nextG2ByLoan = (List)list.get(i);  //下一个债项所有结果
			if(nextG2ByLoan.size() > 0){
				Map nextG2Map = (Map)nextG2ByLoan.get(nextG2ByLoan.size()-1); //G2A
				Map nextG2Result = (Map)nextG2Map.get("result");
				if((null == nextG2Result.get("errorMessage") || "".equals(nextG2Result.get("errorMessage").toString()))
						&& null != nextG2Result.get("errorMessage2")){
					List nextG2Grade = (List)nextG2Result.get("grades");
					Map nextDictMap = (Map)nextG2Grade.get(nextG2Grade.size()-1);
					int nextNo = Integer.valueOf(nextDictMap.get("SORTNO").toString());
					if(nextNo > maxNo){
						maxNo = nextNo;
						maxIndex = i;
					}
				}
			}
		}
		return (List)list.get(maxIndex);
	}
	
	@Bizlet("处理编号")
	public static String getClaNumber(String num,int endLength){
		int length = num.length();
		int add = endLength - length;
		String left="";
		for(int i=0; i<add; i++){
			left = left+"0";
		}
		return left+num;
	}
	
	/**
	 * 
	 * @param prefix前缀
	 * @param acApplyNum现有编号（包括前缀）
	 * @param len编号长度
	 * @return
	 */
	@Bizlet("处理分类申请编号")
	public static String getApplyNumber(String prefix,String acApplyNum,int len){
		String busiDate = com.bos.pub.GitUtil.getBusiDateYYYYMMDD();
		if(null == acApplyNum || "".equals(acApplyNum)){
			//重新生成编号
			acApplyNum = prefix + busiDate + "000001";
		}else{
			//在原有编号上加一
			String num = String.valueOf(Integer.valueOf(acApplyNum.substring(12))+1);
			int numLen = num.length();
			int add = len - numLen;//补充字符数量
			String left = "";
			for(int i=0; i < add; i++){
				left = left + "0";
			}
			acApplyNum = prefix + busiDate + left + num;
		}
		return acApplyNum;
	}
	
	@Bizlet("")
	public String[] trainform(String name) {
		String res[];
		String result = name;
		res = result.split(",");

		return res;
	}
	
	@Bizlet("分类结果导入失败详情")
	/**
	 * flag: 1-不是当前客户权限的借据 2-分类结果输入错误 3-本月清单无此借据
	 */
	public Map<String, List<String>> importExcel_fail(int flag,Map<String, List<String>> failDetail, String loan_num) {
		//分类结果导入失败详情
		List<String> fail_noPower; //没有导入权限
		List<String> fail_noResult; //没有该结果
		List<String> fail_noClassify; //不在本月分类清单中
		//数据初始化
		if(failDetail.isEmpty()){
			fail_noPower = new ArrayList<String>();
			fail_noResult = new ArrayList<String>();
			fail_noClassify = new ArrayList<String>();
			failDetail.put("noPower", fail_noPower);
			failDetail.put("noResult", fail_noResult);
			failDetail.put("noClassify", fail_noClassify);
		} 
		
		if(flag == 1){ //无该借据的导入权限
			fail_noPower = failDetail.get("noPower");
			fail_noPower.add(loan_num);
			failDetail.put("noPower", fail_noPower);
		}else if(flag == 2){ //无导入结果
			fail_noResult = failDetail.get("noResult");
			fail_noResult.add(loan_num);
			failDetail.put("noResult", fail_noResult);
		}else if( flag == 3){ //本月清单无该借据
			fail_noClassify = failDetail.get("noClassify");
			fail_noClassify.add(loan_num);
			failDetail.put("noClassify", fail_noClassify);
		}
		return failDetail;
	}
}
