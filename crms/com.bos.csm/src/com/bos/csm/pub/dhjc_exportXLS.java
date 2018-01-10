package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class dhjc_exportXLS {

	@Bizlet("")
	public List<Object> getData(DataObject[] exldo) {
		String[] title={"所属机构" ,"客户名称", "证件类型" ,"证件号码", "客户类型", "借据/合同编号" ,"预警级别" ,"检查类型" ,"检查截至日期" ,"经办人","完成情况" };
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
 			String[] a={
					 GitUtil.getOrgInfo(exldo[i].getString("ORG_NUM"))[0].getString("orgname"),
					 exldo[i].getString("PARTY_NAME"),
					 mappingData.tomapping("CDKH0002", exldo[i].getString("CERT_TYPE")) ,
					 exldo[i].getString("CERT_NUM"),
					 mappingData.tomapping("XD_KHCD1001", exldo[i].getString("PARTY_TYPE_CD")) ,
					 exldo[i].getString("RE_NUM"),
					 exldo[i].getString("WARNING_LEVEL_CD"),
					 mappingData.tomapping("XD_DHCD0015", exldo[i].getString("REMIND")) ,
					 exldo[i].getString("LAST_DATE").substring(0, 10),
					 GitUtil.getUserInfo((String)exldo[i].get("USER_NUM")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("USER_NUM"))[0].getString("empname"),
					 mappingData.tomapping("YP_YJCD0002", exldo[i].getString("REMIND_STATUS")) ,


			};
 
		l.add(a);
 		}
		
		
 		return l;

	}
}
