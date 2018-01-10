package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class xwyqdk_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title={"机构名称","经办人","客户编号","客户名称","业务品种","借据编号","借据金额","借据余额","借据起期","借据止期","逾期天数","逾期本金","拖欠利息","罚息","贷款账号","还款账号"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname"),
					(String)exldo[i].get("partyNum"),
					(String)exldo[i].get("partyName"),
					GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					(String)exldo[i].get("summaryNum"),								 
					 (String)exldo[i].get("summaryAmt").toString(),
					 (String)exldo[i].get("jjye").toString(),
					 (String)exldo[i].get("beginDate").toString(),
					 (String)exldo[i].get("endDate").toString(),
					 (String)exldo[i].get("yqts").toString(),
					 (String)exldo[i].get("jjyqbj").toString(),
					 (String)( exldo[i].get("arrearItr")==null?"0":exldo[i].get("arrearItr").toString()),
					 (String)(exldo[i].get("punishItr")==null?"0":exldo[i].get("punishItr").toString()),
					 (String)exldo[i].get("dkzh"),
					 (String)exldo[i].get("hkzh")
					

				 
			};
//			System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");

		l.add(a);
 		}
		
		
 		return l;

	}
}
