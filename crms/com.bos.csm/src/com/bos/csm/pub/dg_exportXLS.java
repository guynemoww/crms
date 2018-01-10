package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class dg_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title={"机构名称","客户名称","客户类型","注册登记号码","组织机构代码","中征码","行业类型","企业规模","信用等级","第三方客户类型","管户经理"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
				 (String) exldo[i].get("orgname"),
				 (String)exldo[i].get("partyName"),
				 mappingData.tomapping("XD_KHCD0252", (String)exldo[i].get("corpCustomerTypeCd")) ,
				 (String)exldo[i].get("registrCd"),
				 (String)exldo[i].get("orgRegisterCd"),
				 (String)exldo[i].get("middelCode"),
				 mappingData.tomapping("CDKH0095", (String)exldo[i].get("industrialTypeCd")) ,
				 mappingData.tomapping("CDKH0027", (String)exldo[i].get("enterpriseScaleGx")) ,
				 (String)exldo[i].get("generalAdjustRatingCd"),
				 mappingData.tomapping("XD_KHCD7001", (String)exldo[i].get("thirdCustTypeCd")) ,
				
				 GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname")
			
				 
			};
//			System.out.print(i+"----"+(String)exldo[i].get("partyName")+"------>");
//			System.out.println(GitUtil.getUserInfo((String)exldo[i].get("userNum")));
		l.add(a);
 
		}
		
		
 		return l;

	}
}
