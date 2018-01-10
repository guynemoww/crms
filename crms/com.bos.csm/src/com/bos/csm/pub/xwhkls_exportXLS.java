package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class xwhkls_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		// String[] title={"机构名称","经办人","客户编号","客户名称","合同编号","借据编号","还款日期","还贷金额","还贷本金","还贷利息","五级分类状况"};
		String[] title = { "机构名称", "客户名称", "证件类型", "证件号码", "合同编号", "借据编号", "还款日期", "归还本金", "归还利息", "归还罚息", "归还本息合计", "经办人" };
		List<Object> dataList = new ArrayList<Object>();
		dataList.add(title);
		for (int i = 0; i < exldo.length; i++) {
			DataObject[] datas = GitUtil.getUserInfo((String) exldo[i].get("userNum"));
			String empName = datas != null && datas.length > 0 ? datas[0].getString("empname") : (String) exldo[i].get("userNum");
			String[] a = { GitUtil.getOrgInfo((String) exldo[i].get("orgNum"))[0].getString("orgname"),//
					(String) exldo[i].get("partyName"),//
					mappingData.tomapping("CDKH0002", (String) exldo[i].get("certType")),//
					(String) exldo[i].get("certNum"),//
					(String) exldo[i].get("contractNum"),//
					(String) exldo[i].get("summaryNum"), //
					(String) (exldo[i].get("rcvDate") == null ? "" : exldo[i].get("rcvDate").toString()),//
					(String) (exldo[i].get("hbje") == null ? "0" : exldo[i].get("hbje").toString()),//
					(String) (exldo[i].get("sum1") == null ? "0" : exldo[i].get("sum1").toString()),//
					(String) (exldo[i].get("sum2") == null ? "0" : exldo[i].get("sum2").toString()),//
					(String) (exldo[i].get("sum3") == null ? "0" : exldo[i].get("sum3").toString()),//
					empName };
			dataList.add(a);
		}
		return dataList;
	}
}
