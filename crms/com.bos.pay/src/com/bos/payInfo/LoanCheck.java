package com.bos.payInfo;

import java.math.BigDecimal;
import java.util.HashMap;

import com.bos.crdPub.RiskLimit;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;

import commonj.sdo.DataObject;

public class LoanCheck {
	//出账时校验并扣减限额
	public String checkRiskLimt(String loanId){
		//查询本次出账信息
		DataObject riskLimit = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		riskLimit.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", riskLimit);
		//查询校验条件
		String orgNum = (String)riskLimit.get("orgNum");
		String productType = (String)riskLimit.get("productType");
		BigDecimal loanAmt = (BigDecimal) riskLimit.get("rmbAmt");
		HashMap map = new HashMap();
		map.put("orgNum", orgNum);
		map.put("productCd", productType);
		map.put("loanAmt", loanAmt);
		//调用限额校验
		RiskLimit rl = new RiskLimit();
		map = rl.delRiskLimit(map);
		if(null!=map.get("groupName")){
			return (String) map.get("groupName");
		}
		return null;
	}
}
