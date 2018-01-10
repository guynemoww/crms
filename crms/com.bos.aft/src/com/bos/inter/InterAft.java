package com.bos.inter;

import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

public class InterAft {

	public void interChose(DataObject change, DataObject tbLoanInfo, String type) throws EOSException{
		String changeType = (String)change.get("loanChangeType");
		String currencyCd = tbLoanInfo.getString("currencyCd");
		String productType = tbLoanInfo.getString("productType");
		String repayType = tbLoanInfo.getString("repayType");
		if("12".equals(changeType)) {
			try {
				InterToGJ inter = new InterToGJ();
				inter.aftToGJxyz(change);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
		}else if("13".equals(changeType)) {
			try {
				InterToGJ inter = new InterToGJ();
				inter.aftToGJbh(change);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
		}else if("04".equals(changeType) || "02".equals(changeType) 
				|| "03".equals(changeType) || "09".equals(changeType) || "16".equals(changeType)|| "17".equals(changeType)) {
			InterToJL inter = new InterToJL();
			inter.aftToLcs1(change);
		}else if("06".equals(changeType) || "19".equals(changeType)) {
			if(!"CNY".equals(currencyCd) && "01007".equals(productType.substring(0, 5))) {
				try {
					InterToGJ inter = new InterToGJ();
					inter.aftToGJzq(change);
				} catch (Throwable e) {
					e.printStackTrace();
					throw new EOSException(e.getMessage());
				}
			}else {
				String termChangeWay = change.getString("termChangeWay");
				if(("03".equals(termChangeWay) || "04".equals(termChangeWay)) 
						&& ("1100".equals(repayType) || "1200".equals(repayType) || "1300".equals(repayType) || "1400".equals(repayType) || "1410".equals(repayType) || "1500".equals(repayType))) {//展期：非分期
					InterToJL inter = new InterToJL();
					inter.aftTo1414(change,tbLoanInfo, type);
				}else {
					InterToJL inter = new InterToJL();
					inter.aftToLcs1(change);
				}
			}
		}else if("01".equals(changeType) || "08".equals(changeType)) {
			InterToJL inter = new InterToJL();
			inter.insertJL(change);
		}else if("10".equals(changeType)) {
			try {
				InterToJL inter = new InterToJL();
				inter.inter10(change);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
		}else if("11".equals(changeType) || "14".equals(changeType) || "15".equals(changeType) || "18".equals(changeType)) {
			InterToJL inter = new InterToJL();
			inter.aftToLcs2(change);
		}
		
		/*DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		String productType = (String)loanInfo.get("productType");
		if("01007007".equals(productType)){
			try {
				LoanToGj lt = new LoanToGj();
				lt.jkxyz(loanInfo);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
		}else if("01007008".equals(productType)){
			try {
				LoanToGj lt = new LoanToGj();
				lt.jkbh(loanInfo);
			} catch (Throwable e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
		}else{
			
				LoanToLcs ll = new LoanToLcs();
				ll.loanToLcs2(loanId);
		}*/
	}
	
}
