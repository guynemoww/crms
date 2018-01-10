/**
 * 
 */
package com.bos.csm.pub;


import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author zhouxu
 *
 */
@Bizlet("")
public class T1411Util {
	@Bizlet("业务移交T1411插入数据到中间表")
	public 	String InsertBusiSplitT1411(Map[] obj,String oldOrgNum,String newOrgNum){
		String orgNum="";
		DataObject dto= DataObjectUtil.createDataObject("com.bos.dataset.pub.TbSupSplitDue");
		try{	
		for (int i = 0; i < obj.length; i++) {
			Map map = obj[i];
			DataObject tbLoanInfo= DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			tbLoanInfo.set("loanId", map.get("LOAN_ID"));
			DatabaseUtil.expandEntity("default",tbLoanInfo);
			String loanOrg=tbLoanInfo.getString("loanOrg");//会计机构
			orgNum=tbLoanInfo.getString("orgNum");// 经办机构
			dto.set("rcvDate", GitUtil.getBusiDateYYYYMMDD());
			dto.set("legPerCod", "9999");
			dto.set("orgDep", oldOrgNum);//原开户机构
			dto.set("nowDepObj", newOrgNum);//目的机构号
			dto.set("dueNum", map.get("SUMMARY_NUM"));//借据编号
			dto.set("orgDepSpr", loanOrg);//原核算机构
			dto.set("orgDepCod", oldOrgNum);//原部门代码
			dto.set("nowDepCod",newOrgNum);//目标部门代码
			dto.set("nowDepObjSpr", loanOrg);//目的核算机构
			dto.set("flag", "");
			dto.set("rmk", "");
			DatabaseUtil.saveEntity("aplus", dto);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return orgNum;
	
	}
	
}
