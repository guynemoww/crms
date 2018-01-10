package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class xwydk_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title={"机构名称","经办人","业务品种","客户编号","客户名称","合同编号","借据编号","借据金额","借据余额","收回本金","收回利息","借据起期","借据止期","执行利率","保证人","抵押信息","质押信息","客户所处行业","分类状态"
};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname"),
					GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					(String)exldo[i].get("partyNum"),
					(String)exldo[i].get("partyName"),
					(String)exldo[i].get("contractNum"),
					(String)exldo[i].get("summaryNum"),								 
					 (String)exldo[i].get("summaryAmt").toString(),
					 (String)exldo[i].get("jjye").toString(),
					 exldo[i].get("shbj")==null?"0":(exldo[i].get("shbj")).toString(),
					 exldo[i].get("shlx")==null?"0":(exldo[i].get("shlx")).toString(),
					 (String)exldo[i].get("beginDate").toString(),
					 (String)exldo[i].get("endDate").toString(),
					 (String)exldo[i].get("yearRate").toString(),
					 (String)exldo[i].get("bzr"),
					 (String)exldo[i].get("dy"),
					 (String)exldo[i].get("zy"),
					 mappingData.tomapping("CDKH0095", (String)exldo[i].get("hyml")) ,
					 mappingData.tomapping("XD_FLCD0001", (String)exldo[i].get("fljg")) 

				 
			};
//			System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
		l.add(a);
 		}
		
		
 		return l;

	}
}
