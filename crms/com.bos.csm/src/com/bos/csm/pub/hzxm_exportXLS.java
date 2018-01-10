package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class hzxm_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title={"机构名称","第三方客户名称","合作项目名称","借款人名称","合同编号","借据编号","贷款品种","币种","借据金额","借据余额","借据起期","借据止期","借据状态","逾期天数","逾期本金","正常利息","拖欠利息","罚息","经办人"};
		
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					(String)exldo[i].get("partyName2"),
					(String)exldo[i].get("itemName"),
					(String)exldo[i].get("partyName"),
					(String)exldo[i].get("contractNum"),
					(String)exldo[i].get("summaryNum"),
					GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					 mappingData.tomapping("CD000001", (String)exldo[i].get("currencyCd")) ,
					 (String)( exldo[i].get("summaryAmt")==null?"0":exldo[i].get("summaryAmt").toString()),	
					 (String)( exldo[i].get("jjye")==null?"0":exldo[i].get("jjye").toString()),
					 (String)( exldo[i].get("beginDate")==null?"":exldo[i].get("beginDate").toString()),
					 (String)( exldo[i].get("endDate")==null?"":exldo[i].get("endDate").toString()),
					 mappingData.tomapping("XD_SXYW0226", (String)exldo[i].get("summaryStatusCd")) ,
					 (String)( exldo[i].get("yqts")==null?"0":exldo[i].get("yqts").toString()),
					 (String)( exldo[i].get("jjyqbj")==null?"0":exldo[i].get("jjyqbj").toString()),		 
 					 (String)( exldo[i].get("normalItr")==null?"0":exldo[i].get("normalItr").toString()),
					 (String)( exldo[i].get("arrearItr")==null?"0":exldo[i].get("arrearItr").toString()),
					 (String)( exldo[i].get("punishItr")==null?"0":exldo[i].get("punishItr").toString()),
					 GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname")
				 
			};
		//	System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
		l.add(a);
 		}
		
		
 		return l;

	}
}
