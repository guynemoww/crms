package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

@Bizlet("getdata")
public class xdzx_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title = { "机构名称", "客户名称", "证件类型", "证件号码", "中征码","行业类别", "是否农户",
				"信用等级", "客户经理" };
		List<Object> l = new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a = {
					(String) exldo[i].get("orgname"),
					(String) exldo[i].get("partyName"),
					mappingData.tomapping("CDKH0002",
							(String) exldo[i].get("certType")),
					(String) exldo[i].get("certNum"),
					(String) exldo[i].get("middelCode"),
					mappingData.tomapping("CDKH0095",
							(String) exldo[i].get("industry")),
					mappingData.tomapping("YesOrNo",
							(String) exldo[i].get("isFarmer")),
					(String) exldo[i].get("generalAdjustRatingCd"),
					 GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname")


			};
//			System.out.println(i);
			l.add(a);
		}

		return l;

	}
}
