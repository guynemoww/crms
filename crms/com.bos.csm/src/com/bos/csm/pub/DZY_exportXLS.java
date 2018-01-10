package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class DZY_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title={"机构名称","抵质押人名称","抵质押物类型","抵质押物编号","评估价值","权利价值","已担保金额","登记生效日期","登记到期日期","出入库状态","凭证保管地","入库经办人","管户经理"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					(String)exldo[i].get("partyName"),
					 mappingData.tomapping("XD_YWDB02", (String)exldo[i].get("sortType")) ,
					 (String)exldo[i].get("suretyNo"),
					 exldo[i].get("assessValue")==null?"0":(exldo[i].get("assessValue")).toString(),
					 (exldo[i].get("mortgageValue")==null?"0":exldo[i].get("mortgageValue")).toString(),
					 exldo[i].get("totalAmt").toString(),
					 (String)exldo[i].get("cardRegDate"),
					 (String)exldo[i].get("regDueDate"),
					 (String)exldo[i].get("mortgageStatus"),
					 GitUtil.getOrgInfo((String)exldo[i].get("saveOrg")).length==0?"":GitUtil.getOrgInfo((String)exldo[i].get("saveOrg"))[0].getString("orgname"),
					 GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname"),
					 GitUtil.getUserInfo((String)exldo[i].get("mansNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("mansNum"))[0].getString("empname")
									 		 
			};
			//System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
		l.add(a);
 		}
		
		
 		return l;

	}
}
