package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class xwhk_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		//String[] title={"机构名称","经办人","客户编号","客户名称","电话号码","合同编号","借据编号","还款日期","还贷金额","还贷本金","还贷利息","还贷期数","还款账号","五级分类状况"};
		String[] title={"机构名称","客户名称","证件类型","证件号码","合同编号","借据编号","还款日期","还款期次","应还本金","应还利息","应还本息合计","还款账号","联系方式","经办人"};
		
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),

					(String)exldo[i].get("partyName"),
					mappingData.tomapping("CDKH0002", (String)exldo[i].get("certType")) ,
					(String)exldo[i].get("certNum"),
					
					(String)exldo[i].get("contractNum"),
					(String)exldo[i].get("summaryNum"),	
					
					 (String)( exldo[i].get("endDate")==null?"":exldo[i].get("endDate").toString()),
					 (String)( exldo[i].get("qc")==null?"":exldo[i].get("qc").toString()),

					 (String)( exldo[i].get("bj")==null?"0":exldo[i].get("bj").toString()),
					 (String)( exldo[i].get("lx")==null?"0":exldo[i].get("lx").toString()),
					 (String)( exldo[i].get("amt")==null?"0":exldo[i].get("amt").toString()),
					 
					 (String)exldo[i].get("hkzh"),
					 
					 (String)exldo[i].get("phoneNumber"),
					 GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname")

				 
			};
			System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
		l.add(a);
 		}
		
		
 		return l;

	}
}
