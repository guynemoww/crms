/**
 * 
 */
package com.bos.pub.grantManage;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.spring.support.DataObjectUtil;
import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2015-08-03 19:50:31
 *
 */
@Bizlet("小贷授权数据处理")
public class GrantMExcelUtil {

	private final static String ENTIRY_GRANT="com.bos.dataset.grant.TbGrantMappingM";
	
	@Bizlet("获取小贷数据，并保存")
	public static String getExcelData(Map<String, List<Map>> sheetMap){
		
		//返回错误信息
		StringBuffer sb = new StringBuffer();
		
		Set<String> keySet = sheetMap.keySet();
		if(keySet.size()>0){
			
			//delete grant_m  data
			deleteGrantMData();
			
			//循环保存授权数据
			for (String key : keySet) {
				List<Map> lt = sheetMap.get(key);
				if(null!=lt && lt.size()>0 ){
					
					for (int i = 0; i < lt.size(); i++) {
						Map<String,String> temp = lt.get(i);
						//保存授权数据
						String rt = saveAuthData(temp,i+1);
						if(null != rt && !"".equals(rt)){
							
							sb.append(rt).append("\n");
						}
					}
				}else{
					sb.append("导入失败,模板数据为空！");
				}
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

	private static String saveAuthData(Map<String, String> temp,int index) {
		StringBuffer sb = new StringBuffer();
		String userId = temp.get("userId");
		String maxAmt = String.valueOf(temp.get("maxAmt"));
		//为空校验
		if(null == userId || "".equals(userId)){
			sb.append("第"+index+"行，用户编号不能为空");
			return sb.toString();
		}
		if(null == maxAmt || "".equals(maxAmt)){
			sb.append("第"+index+"行，权限金额不能为空");
			return sb.toString();
		}
		//长度校验
		if(userId.length()>20){
			sb.append("第"+index+"行，用户编号长度不能大于20个字符");
			return sb.toString();
		}
		if(maxAmt.length()>20){
			sb.append("第"+index+"行，权限金额长度不能大于20个字符");
			return sb.toString();
		}
		//合法校验（权限金额必须为数字）
		if(isNumberic(maxAmt)){
			sb.append("第"+index+"行，权限金额必须为数字");
			return sb.toString();
		}
		try{
			DataObject data = DataObjectUtil.createDataObject(ENTIRY_GRANT);
			data.set("userId", userId);
			data.set("maxAmt", maxAmt);
			data.set("createDate", GitUtil.getBusiDate());
			data.set("lastUpdateDate", GitUtil.getBusiDate());
			DatabaseUtil.insertEntity(GitUtil.DEFAULT_DS_NAME, data);
		}catch(Exception e){
			e.printStackTrace();
			sb.append("第"+index+"行，保存失败！"+e.getMessage());
		}
		return sb.toString();
	}
	
	private static boolean isNumberic(String str){
		
		Pattern pattern = Pattern.compile("[0-9]+.?[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if(isNum.matches()){
			return false;
		}else{
			return true;
		}
		
	}
	
	//清除小贷中心授权信息
	private static void deleteGrantMData() {
		
		DatabaseExt.executeNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.grantManage.grant.delete_grant_m_id", null);
	}
}
