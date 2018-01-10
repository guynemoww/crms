package com.bos.utp.dataset;

import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;
/**
 * 主页工具类
 * @author 邵岩
 * @since 2013-6-1
 */
@Bizlet
public class MainPageUtil {
	/**
	 * 拆分session中的部门编号序列 生成in查询用字符串
	 * @param code 部门编号序列
	 * @return 以分号分开的部门编码
	 */
	@Bizlet
	public String orgQueryString(String code){
		if(code!=null&&!code.equals("")){
			code=code.substring(1,code.length()-1);
			code=code.replace(".",",");
			String[] temp=code.split(",");
			StringBuffer str=new StringBuffer();
			String s="";
			for(int i=0;i<temp.length;i++){
				s=temp[i];
				if(s!=null&&!s.equals("")){
					if(i!=0)
						str.append(",");
					str.append("'"+s+"'");
				}
			}
			return str.toString();	
		}
		return null;
	}
	
	
	/**
	 * 拼装登录跳转主页url
	 * @author wangbing
	 * @param 
	 * @return 
	 */
	@Bizlet
	public String togetherMainUrl(String url, String myStyle){
		String rsUrl = "";
		if(myStyle != null && !myStyle.equals("")){
			rsUrl = url + myStyle + "/index.jsp";
		}else{
			rsUrl = url + "default/index.jsp";
		}
		return rsUrl;
	}
	
	/**
	 * 拆分风格样式值
	 * @author wangbing
	 * @param 
	 * @return 
	 */
	@Bizlet
	public String[] splitTypeAndStyle(DataObject oper){
		String menuType = oper.getString("menutype");
		String split = ",";//布局与样式存放在ac_operator的menutype字段，以","号分割
		String[] menuTypes = new String[2];
		if(menuType != null && ! menuType.equals("")){
			if(menuType.contains(split)){
				menuTypes = menuType.split(split);
			}else{
				menuTypes[0] = menuType;
			}
		}
		return menuTypes;
	}
	
	/**
	 * 布局与样式合并
	 * @author wangbing
	 * @param 
	 * @return 
	 */
	@Bizlet
	public String togetherTypeAndStyle(String menutype, String pageStyle){
		return menutype + "," + pageStyle;
	}
	
}