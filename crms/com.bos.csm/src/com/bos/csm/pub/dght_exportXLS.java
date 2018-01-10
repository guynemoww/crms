package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class dght_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title={"客户类型" ,"机构名称", "客户名称" ,"综合授信协议编号", "合同编号", "合同状态" ,"贷款品种" ,"币种" ,"合同金额" ,"合同余额" ,"主担保方式" ,"起始日", "到期日", "逾期天数", "表内欠息", "表外欠息", "分类" ,"经办人" 
};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					 mappingData.tomapping("XD_KHCD0252", (String)exldo[i].get("corpCustomerTypeCd")) ,
					 GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					 (String)exldo[i].get("partyName"),
					 (String)exldo[i].get("xyContractNum"),
					 (String)exldo[i].get("contractNum"),
					 mappingData.tomapping("XD_SXCD8003", (String)exldo[i].get("conStatus")) ,
					 GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					 mappingData.tomapping("CD000001", (String)exldo[i].get("currencyCd")) ,
					 exldo[i].get("contractAmt").toString(),
					 (String)exldo[i].get("conYuE").toString(),
					 mappingData.tomapping("CDZC0005", (String)exldo[i].get("mainGuarantyType")) ,
					 (String)exldo[i].get("beginDate"),
					 (String)exldo[i].get("endDate"),
					 exldo[i].get("yqts")==null?"0":exldo[i].get("yqts").toString(),
					 exldo[i].get("dft_itr_in")==null?"0":exldo[i].get("dft_itr_in").toString(),
					 exldo[i].get("dft_itr_out")==null?"0":exldo[i].get("dft_itr_out").toString(),
					 mappingData.tomapping("XD_FLCD0001", (String)exldo[i].get("fljg")) ,
					 GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname")
				 
			};
//			System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");

		l.add(a);
 		}
		
		
 		return l;

	}
}
