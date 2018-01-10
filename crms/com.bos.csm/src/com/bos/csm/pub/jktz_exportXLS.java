package com.bos.csm.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.bos.pub.mappingData;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("getdata")
public class jktz_exportXLS {

	@Bizlet("")
	public List<Object> getData(Map[] exldo) {
		String[] title={"机构名称","客户名称","业务品种","合同编号","借据编号","币种","借据金额","借据余额","借据起期","借据止期","逾期天数","逾期本金","正常利息","拖欠利息","罚息","还款账号","经办人"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					(String)exldo[i].get("partyName"),
					//GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					
					GitUtil.getProductFlag(exldo[i].get("productType").toString()).length()==1?
					(String)exldo[i].get("productType"):
					GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					
					(String)exldo[i].get("contractNum"),
					(String)exldo[i].get("summaryNum"),
					mappingData.tomapping("CD000001", (String)exldo[i].get("summaryCurrencyCd")) , 
					(String)(exldo[i].get("summaryAmt")==null?"0":exldo[i].get("summaryAmt").toString()),
					(String)(exldo[i].get("jjye")==null?"0":exldo[i].get("jjye").toString()),
					(String)exldo[i].get("beginDate"),
					(String)exldo[i].get("endDate"),
					(String)(exldo[i].get("yqts")==null?"0":exldo[i].get("yqts").toString()),
					(String)(exldo[i].get("jjyqbj")==null?"0":exldo[i].get("jjyqbj").toString()),
					(String)(exldo[i].get("normalItr")==null?"0":exldo[i].get("normalItr").toString()),
					(String)(exldo[i].get("arrearItr")==null?"0":exldo[i].get("arrearItr").toString()),
					(String)(exldo[i].get("punishItr")==null?"0":exldo[i].get("punishItr").toString()),
					(String)exldo[i].get("zh"),
					GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname") 
			};
		//	System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
		l.add(a);
 		}
 		return l;
	}
	
	@Bizlet("")
	public List<Object> getData1(Map[] exldo) {
		String[] title={"机构名称","客户名称","业务品种","合同编号","借据编号","币种","借据金额","借据余额","借据起期","借据止期","分类","到期天数","经办人"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					(String)exldo[i].get("partyName"),
					//GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					
					GitUtil.getProductFlag(exldo[i].get("productType").toString()).length()==1?
					(String)exldo[i].get("productType"):
					GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					
					(String)exldo[i].get("contractNum"),
					(String)exldo[i].get("summaryNum"),
					mappingData.tomapping("CD000001", (String)exldo[i].get("summaryCurrencyCd")) , 
					(String)(exldo[i].get("summaryAmt")==null?"0":exldo[i].get("summaryAmt").toString()),
					(String)(exldo[i].get("jjye")==null?"0":exldo[i].get("jjye").toString()),
					(String)exldo[i].get("beginDate"),
					(String)exldo[i].get("endDate"),
					mappingData.tomapping("XD_FLCD0001", (String)exldo[i].get("fljg")) , 
					(String)(exldo[i].get("dqts")==null?"0":exldo[i].get("dqts").toString()),
					GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname")		 
			};
		//	System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
		l.add(a);
 		}	
 		return l;
	}
	
	@Bizlet("")
	public List<Object> getData2(Map[] exldo) {
		String[] title={"机构名称","客户名称","业务品种","合同编号","借据编号","币种","借据金额","借据余额","借据起期","借据止期","分类","已发放天数","经办人"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					(String)exldo[i].get("partyName"),
					//GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					
					GitUtil.getProductFlag(exldo[i].get("productType").toString()).length()==1?
					(String)exldo[i].get("productType"):
					GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					
					(String)exldo[i].get("contractNum"),
					(String)exldo[i].get("summaryNum"),
					mappingData.tomapping("CD000001", (String)exldo[i].get("summaryCurrencyCd")) , 
					(String)(exldo[i].get("summaryAmt")==null?"0":exldo[i].get("summaryAmt").toString()),
					(String)(exldo[i].get("jjye")==null?"0":exldo[i].get("jjye").toString()),
					(String)exldo[i].get("beginDate"),
					(String)exldo[i].get("endDate"),
					mappingData.tomapping("XD_FLCD0001", (String)exldo[i].get("fljg")) , 
					(String)(exldo[i].get("fkts")==null?"0":exldo[i].get("fkts").toString()),
					GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname")	 
			};
		//	System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
		l.add(a);
 		}	
 		return l;
	}
	
	@Bizlet("")
	public List<Object> getData3(Map[] exldo) {
		String[] title={"机构名称","客户名称","业务品种","合同编号","借据编号","币种","借据金额","借据余额","借据起期","借据止期","分类","收回金额","收回日期"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					(String)exldo[i].get("partyName"),
					//GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					
					GitUtil.getProductFlag(exldo[i].get("productType").toString()).length()==1?
					(String)exldo[i].get("productType"):
					GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
							
					(String)exldo[i].get("contractNum"),
					(String)exldo[i].get("summaryNum"),
					mappingData.tomapping("CD000001", (String)exldo[i].get("summaryCurrencyCd")) , 
					(String)(exldo[i].get("summaryAmt")==null?"0":exldo[i].get("summaryAmt").toString()),
					(String)(exldo[i].get("jjye")==null?"0":exldo[i].get("jjye").toString()),
					(String)exldo[i].get("beginDate"),
					(String)exldo[i].get("endDate"),
					mappingData.tomapping("XD_FLCD0001", (String)exldo[i].get("fljg")) , 
					(String)(exldo[i].get("padUpPrn")==null?"0":exldo[i].get("padUpPrn").toString()),
					(String)(exldo[i].get("rcvDate")==null?"0":exldo[i].get("rcvDate").toString())
					//GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname")				 
			};
		//	System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
		l.add(a);
 		}	
 		return l;
	}
	
	@Bizlet("")
	public List<Object> getData4(Map[] exldo) {
		String[] title={"机构名称","客户名称","业务品种","合同编号","借据编号","币种","借据金额","借据余额","借据起期","借据止期","分类","逾期天数","正常利息","拖欠利息","罚息"};
		List<Object> l =new ArrayList<Object>();
		l.add(title);
		for (int i = 0; i < exldo.length; i++) {
			String[] a={
					GitUtil.getOrgInfo((String)exldo[i].get("orgNum"))[0].getString("orgname"),
					(String)exldo[i].get("partyName"),
					//GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					
					GitUtil.getProductFlag(exldo[i].get("productType").toString()).length()==1?
					(String)exldo[i].get("productType"):
					GitUtil.getProductInfo((String)exldo[i].get("productType"))[0].getString("productName"),
					
					(String)exldo[i].get("contractNum"),
					(String)exldo[i].get("summaryNum"),
					mappingData.tomapping("CD000001", (String)exldo[i].get("summaryCurrencyCd")) , 
					(String)(exldo[i].get("summaryAmt")==null?"0":exldo[i].get("summaryAmt").toString()),
					(String)(exldo[i].get("jjye")==null?"0":exldo[i].get("jjye").toString()),
					(String)exldo[i].get("beginDate"),
					(String)exldo[i].get("endDate"),
					mappingData.tomapping("XD_FLCD0001", (String)exldo[i].get("fljg")) , 
					(String)(exldo[i].get("yqts")==null?"0":exldo[i].get("yqts").toString()),
					(String)(exldo[i].get("normalItr")==null?"0":exldo[i].get("normalItr").toString()),
					(String)(exldo[i].get("arrearItr")==null?"0":exldo[i].get("arrearItr").toString()),
					(String)(exldo[i].get("punishItr")==null?"0":exldo[i].get("punishItr").toString())
					//GitUtil.getUserInfo((String)exldo[i].get("userNum")).length==0?"":GitUtil.getUserInfo((String)exldo[i].get("userNum"))[0].getString("empname")				 
			};
		//	System.out.println(i+"----"+(String)exldo[i].get("partyName")+"------>");
		l.add(a);
 		}
 		return l;
	}

}
