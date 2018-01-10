package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.bos.pub.mappingDataFlag;
import com.eos.system.annotation.Bizlet;

@Bizlet("getdata")
public class jdzx_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title = { "机构名称", "客户名称", "证件类型", "证件号码", "业务编号","申请日期", "币种",
				"申请金额", "申请期限","贷款用途","拒绝日期","拒绝原因","经办人" };
		List<Object> l = new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a = {
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					(String) exldo[i].get("partyName"),
					mappingData.tomapping("CDKH0002",
							(String) exldo[i].get("certType")),
					(String) exldo[i].get("certNum"),
					(String) exldo[i].get("bizNum"),
					(String)( exldo[i].get("applyDate")==null?"":exldo[i].get("applyDate").toString()),
					mappingData.tomapping("CD000001",
							(String) exldo[i].get("currencyCd")),
					(String)( exldo[i].get("creditAmount")==null?"":exldo[i].get("creditAmount").toString()),
					(String)( exldo[i].get("creditTerm")==null?"":exldo[i].get("creditTerm").toString()),
					(String) exldo[i].get("loanUse"),
					(String)( exldo[i].get("validDate")==null?"":exldo[i].get("validDate").toString()),
					
					mappingDataFlag.tomapping("XD_YWDB0141",(String) exldo[i].get("opinion")).length()==1?
							(String)( exldo[i].get("opinion")):
								mappingData.tomapping("XD_YWDB0141",(String) exldo[i].get("opinion")),
						
					/*
					mappingData.tomapping("XD_YWDB0141",
							(String) exldo[i].get("opinion")),*/
					GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname"),
			};
//			System.out.println(i);
			l.add(a);
		}

		return l;

	}
}
