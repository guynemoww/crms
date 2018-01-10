package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

@Bizlet("getdata")
public class zjtz_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title = { "机构名称", "客户名称", "证件类型", "证件号码", "支付日期","币种", "支付金额",
				"支付对象", "贷款用途","是否符合审批或约定用途" };
		List<Object> l = new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a = {
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					(String) exldo[i].get("partyName"),
					mappingData.tomapping("CDKH0002",
							(String) exldo[i].get("certType")),
					(String) exldo[i].get("certNum"),
					(String)( exldo[i].get("payTime")==null?"":exldo[i].get("payTime").toString()),
					mappingData.tomapping("CD000001",
							(String) exldo[i].get("summaryCurrencyCd")),
					(String)exldo[i].get("applyAmount").toString(),
					(String) exldo[i].get("payObject"),
					(String) exldo[i].get("payUse"),
					mappingData.tomapping("YesOrNo",
							(String) exldo[i].get("isFitDeal"))
			};
//			System.out.println(i);
			l.add(a);
		}

		return l;

	}
}
