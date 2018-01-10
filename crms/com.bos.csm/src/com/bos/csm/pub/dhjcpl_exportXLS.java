package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class dhjcpl_exportXLS {

	@Bizlet("")
	public List<Object> getData(DataObject[] exldo) {
		String[] title={"客户名称","所属机构","客户类型","信用等级","分类","是否异地","预警级别","监控名单类型","检查频率(变更前)","检查频率(变更后)"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					exldo[i].getString("partyName"),
					GitUtil.getUserInfo((String)exldo[i].get("orgNum")).length==0?"":GitUtil.getOrgInfo((String) exldo[i].get("orgNum"))[0].getString("orgname"),
 					mappingData.tomapping("XD_KHCD1001",exldo[i].getString("customerTypeCd")),
					exldo[i].getString("creditRatingCd"),
					mappingData.tomapping("XD_FLCD0001",exldo[i].getString("classificationResultCd")),
					mappingData.tomapping("CDGY0001",exldo[i].getString("isDifferentPlace")),
					mappingData.tomapping("XD_YJJB0001",exldo[i].getString("warningLevelCd")),
					mappingData.tomapping("XD_JKMD0001",exldo[i].getString("listStatus")),
					mappingData.tomapping("XD_DHCD0001",exldo[i].getString("approveRate")),
					mappingData.tomapping("XD_DHCD0001",exldo[i].getString("setRate"))

			
				 
			};
//			System.out.print(i+"----"+(String)exldo[i].get("partyName")+"------>");
//			System.out.println(GitUtil.getUserInfo((String)exldo[i].get("userNum")));
		l.add(a);
 
		}
		
		
 		return l;

	}
}
