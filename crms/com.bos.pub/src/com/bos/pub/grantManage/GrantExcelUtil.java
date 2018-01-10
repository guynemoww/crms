/**
 * 
 */
package com.bos.pub.grantManage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bos.pub.GitUtil;
import com.bos.pub.exception.ParamEmptyException;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.utility.StringUtil;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2015-07-03 15:52:37
 *
 */
@Bizlet("处理规则excel处理")
public class GrantExcelUtil {

	private final static String autch_1 ="总行审批";
	private final static String autch_2 ="分行审批";
	private final static String autch_3 ="支行审批";
	//担保方式
	private final static String guar_1 = "信用";
	private final static String guar_2 = "优质担保";
	private final static String guar_3 = "普通担保";
	private final static String guar_4 = "保证";
	private final static String guar_5 = "全部";
	//岗位编号
	private final static String P1202 = "独立审批岗";
	private final static String P1201 = "行长岗";
	private final static String P1204 = "授信审查委员会";
	
	//实体对象路径
	private final static String entiry_level="com.bos.dataset.grant.TbGrantLevelMapping";
	
	
	/**
	 * 获取excel数据，并保存，暂不支持单个机构导入
	 * @param sheetMap
	 * @return
	 * @throws ParamEmptyException
	 */
	@Bizlet("处理授权规则excel表数据")
	public static String getExcelData(Map<String, List<Map>> sheetMap) throws ParamEmptyException{
		//返回错误信息
		StringBuffer sb = new StringBuffer();
		
		Set<String> keySet = sheetMap.keySet();
		if(keySet.size()>0){
			
			//循环保存授权数据
			for (String key : keySet) {
				
				List<Map> lt = sheetMap.get(key);
				//delete grant  data  by orgid
				String orgcode = String.valueOf(lt.get(0).get("orgcode"));
				//格式化机构
				if(null != orgcode && orgcode.indexOf(".")!=-1){
					
					orgcode = orgcode.substring(0, orgcode.indexOf("."));
				}
				deleteAuthData(orgcode);
				
				if(null!=lt && lt.size()>0 ){
					
					for (int i = 0; i < lt.size(); i++) {
						Map<String,String> temp = lt.get(i);
						String rt ="";
						if(null != lt.get(i).get("orgcode") && !"".equals(lt.get(i).get("orgcode"))){
							//保存授权数据
						    rt = saveAuthData(temp,i+1,key);
						}
						if(null != rt && !"".equals(rt)){
						sb.append(rt).append("\n");					}
						}
				}else{
					sb.append("导入失败,模板数据为空！");
				}
				
				//清空list
				lt = null;
			}
		}else{
			sb.append("导入失败,模板数据为空！");
		}
		//如果没有异常，则赋导入成功
		if(null == sb.toString() || "".equals(sb.toString())){
			
			sb.append("导入成功！");
		}
		return sb.toString();
	}
	
	
	public static String saveAuthData(Map<String,String> map,int index,String sheetIndex){
		
		StringBuffer sb = new StringBuffer();
		//机构编号
		String orgcode =String.valueOf(map.get("orgcode"));
		//授信产品
		String productType=map.get("productType");
		//担保方式
		String guarType=map.get("guarType");
		//下限金额
		String  isLow =map.get("isLow");
		//上限金额
		DecimalFormat df = new DecimalFormat("0");
		String maxAmt="";
		if(null!=map.get("maxAmt")|| "".equals(map.get("maxAmt"))){
			maxAmt = String.valueOf(df.format(map.get("maxAmt")));
		}
		//权限级别
		String authLv = map.get("authLv");
		//岗位编号
		String posicode = map.get("posicode");
		//审批官级别
		String personLv = String.valueOf(map.get("personLv"));
		
		//为空校验
		if(checkVailde(orgcode,"[\\w\\W]{1,10}",false)){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，机构编号为空或输入不合法,长度必须小于等于10！");
			return sb.toString();
		}
		if(checkVailde(productType,"[0-9]{0,20}[-][\\w\\W]+",false)){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，授信品种为空或输入不合法,必须由[品种代码+'-'+品种名称]组成且品种代码长度小于等于10且为数字！");
			return sb.toString();
		}
		if(checkVailde(guarType,null,false)){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，担保类型不能为空");
			return sb.toString();
		}
		if(checkVailde(isLow,null,false)){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，是否低不能为空");
			return sb.toString();
		}
		if(checkVailde(maxAmt,"[0-9]{0,20}(\\.[0-9]{0,2})?",false)){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，权限金额为空或输入不合法,必须为数字,可以是整数或者浮点数,小位数最多两位!");
			return sb.toString();
		}
		if(checkVailde(authLv,null,false)){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，权限级别不能为空");
			return sb.toString();
		}
		if(checkVailde(posicode,null,false)){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，权限岗位不能为空");
			return sb.toString();
		}
		if(checkVailde(personLv,"[0-9]{0,2}(\\.[0-9]{0,2})?",true)){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，审批官级别为空或者输入不合法,长度必须小于等于2且为数字");
			return sb.toString();
		}
		//转译校验
		guarType = getGuarCodeByName(guarType);
		if(null == guarType){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，担保类型无法找到对应的码值");
			return sb.toString();
		}
		authLv = getAuthCodeByName(authLv);
		if(null == authLv){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，权限级别无法找到对应的码值");
			return sb.toString();
		}
		isLow = getYesOrNo(isLow);
		if(null == isLow){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，是否低无法找到对应的码值");
			return sb.toString();
		}
		posicode = getPosiCode(posicode);
		if(null == posicode){
			sb.append("工作表["+sheetIndex+"]，第"+index+"行，权限岗位无法找到对应的码值");
			return sb.toString();
		}
		
		//格式化机构
		if(null != orgcode && orgcode.indexOf(".")!=-1){
			
			orgcode = orgcode.substring(0, orgcode.indexOf("."));
		}
		
		//截取品种代码
		if(productType.indexOf("-")!=-1){
			
			productType = productType.split("-")[0];
		}
		
//		BigDecimal max_amt = new BigDecimal(0);
//		if(StringUtil.isNotNull(maxAmt)){
//			
//			max_amt =new BigDecimal(maxAmt);
//		}else{
//			
//			max_amt =new BigDecimal("999999999999");
//		}
		
		//格式化审批官级别
		if(null != personLv && personLv.indexOf(".")!=-1){
			
			personLv = personLv.substring(0, personLv.indexOf("."));
		}
		
		//创建授权层级映射实体
		DataObject level = DataObjectUtil.createDataObject(entiry_level);
		level.set("orgcode", orgcode);
		level.set("productType", productType);
		level.set("isLow", isLow);
		level.set("guarType", guarType);
		level.set("maxAmt", maxAmt);
		level.set("authLv", authLv);
		level.set("posicode", posicode);
		level.set("personLv", personLv);
		level.set("createDate",GitUtil.getBusiDate());
		DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, level);
			
		return sb.toString();
	}
	
	/**
	 * 根据机构编号，删除已经导入的授权规则
	 * @param orgcode
	 */
	public static void deleteAuthData(String orgcode){
		
		//清除授权层级映射表
		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.grantManage.grant.delete_level_id", orgcode);
	}
	
	
	/**
	 * 校验字段合法性
	 * @param str
	 * @param pattern
	 * @param errInfo
	 * @return
	 */
	private static boolean checkVailde(String str,String patt,boolean isNull){
		
		if(!isNull){//允许为空
			
			if(null == str || "".equals(str)){
				
				return true;
			}
			if(null !=patt){
				Pattern pattern = Pattern.compile(patt);
				Matcher isNum = pattern.matcher(str);
				if(isNum.matches()){
					return false;
				}else{
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	/**
	 * 根据权限名称，转换成对应的码值
	 * @param authName
	 * @return
	 */
	public static String getAuthCodeByName(String authName){
		
		String authCode = null;
		if(null != authName && "" != authName){
			
			if(autch_3.equals(authName)){
				
				authCode = "3";
			}else if(autch_2.equals(authName)){
				
				authCode = "2";
			}else if(autch_1.equals(authName)){
				
				authCode = "1";
			}
		}
		return authCode;
	}
	
	/**
	 * 根据担保方式名称，转换成对应的码值
	 * @param guarName
	 * @return
	 */
	public static String getGuarCodeByName(String guarName){
		String guarCode=null;
		if(null != guarName && "" != guarName){
			if(guar_1.equals(guarName)){
				
				guarCode = "1";
			}else if(guar_2.equals(guarName)){
				
				guarCode = "2";
			}else if(guar_3.equals(guarName)){
				
				guarCode = "3";
			}else if(guar_4.equals(guarName)){
				
				guarCode = "4";
			}else if(guar_5.equals(guarName)){

				guarCode = "5";
			}
			
		}
		return guarCode;
	}
	
	public static String getPosiCode(String posiname){
		String posicode = null;
		if(P1202.equals(posiname)){
			
			posicode = "P1202";
		}else if(P1201.equals(posiname)){
			
			posicode = "P1201";
		}else if(P1204.equals(posiname)){
			
			posicode = "P1204";
		}
		return posicode;
	}
	
	/**
	 * 转译是否标志
	 * @param ynName
	 * @return
	 */
	public static String getYesOrNo(String ynName){
		
		String yes = null;
		if("是".equals(ynName)){
			
			yes = "1";
		}else{
			
			yes = "0";
		}
		return yes;
	}
}
