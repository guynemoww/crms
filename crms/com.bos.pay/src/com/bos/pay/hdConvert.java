/**
 * 
 */
package com.bos.pay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2016-01-05 11:10:41
 *
 */
@Bizlet("")
public class hdConvert {
	@Bizlet("")
	public Object[] convertHD(DataObject[] loanInfos,DataObject page,String startDate,String endDate){
		StringBuilder sb=new StringBuilder();
		Map map=new HashMap();
		for(int i=0;i<loanInfos.length;i++){
			String summaryNum=loanInfos[i].getString("SUMMARY_NUM");
			if(i==loanInfos.length-1){
				sb.append("'").append(summaryNum).append("'");
			}else{
				sb.append("'").append(summaryNum).append("'").append(",");
			}
		}
		
		if(sb.length()==0){
			return null;
		}
		//查询计量的数据
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("summaryNum", sb.toString());
		
		//查询计量的数据
		Object[] objs = DatabaseExt.queryByNamedSqlWithPage("sdp", "com.bos.pay.LoanSummary.queryJLList", page, map);
		if(objs.length>0){
			
			//遍历计量查出的所有数据
			for(int i=0;i<objs.length;i++){
				DataObject con = (DataObject) objs[i];
				String dueNum=con.getString("DUE_NUM");		//计量查出的借据编号
				
				for(int j=0;j<loanInfos.length;j++){		//遍历管理的数据
					String summaryNum=loanInfos[j].getString("SUMMARY_NUM");
					if(dueNum.trim().equals(summaryNum.trim())){
						con.set("PARTY_NAME", loanInfos[j].getString("PARTY_NAME"));
						con.set("ZH", loanInfos[j].getString("ZH"));
						con.set("ZHMC", loanInfos[j].getString("ZHMC"));
						con.set("YEAR_RATE", loanInfos[j].getString("YEAR_RATE"));
						con.set("SUMMARY_NUM", loanInfos[j].getString("SUMMARY_NUM"));
					}
				}
			}
		}else{
			return null;
		}
		
		return objs;
	}
	
	@Bizlet("")
	public Object[] queryJL_HKLS(String summarynum){
		Map map=new HashMap();
		
		map.put("summarynum", summarynum);
		Object[] objs = DatabaseExt.queryByNamedSql("sdp", "com.bos.pay.LoanSummary.queryJLHKLSList",map);
		DataObject jjls=null;
		DataObject jjls_next=null;

		
 		for (int i = 0; i < objs.length; i++) {
 			jjls = (DataObject) objs[i];
 			if(!(jjls.getInt("XH")==0)){
 			jjls.setBigDecimal("WHBJ", jjls.getBigDecimal("RCV_PRN").subtract(jjls.getBigDecimal("PAD_PRN")));
 			}
 			if(i< (objs.length-1)){
 				jjls_next=(DataObject) objs[i+1];
				if(!(jjls.getInt("XH")==0) && jjls.getInt("XH")==jjls_next.getInt("XH")){
					if(jjls.getBigDecimal("RCV_PRN").compareTo(jjls.getBigDecimal("PAD_PRN"))==1
					||	jjls.getBigDecimal("RCV_ITR").compareTo(jjls.getBigDecimal("PAD_ITR"))==1
					||	jjls.getBigDecimal("RCV_PNS_ITR").compareTo(jjls.getBigDecimal("PNS_ITR"))==1	
							){
						jjls_next.setBigDecimal("RCV_PRN", jjls.getBigDecimal("RCV_PRN").subtract(jjls.getBigDecimal("PAD_PRN")));
						jjls_next.setBigDecimal("RCV_ITR", jjls.getBigDecimal("RCV_ITR").subtract(jjls.getBigDecimal("PAD_ITR")));
						jjls_next.setBigDecimal("RCV_PNS_ITR", jjls.getBigDecimal("RCV_PNS_ITR").subtract(jjls.getBigDecimal("PNS_ITR")));
					}
 				}
 			}
 		}
 		List<Object> list=new ArrayList<Object>();
 		BigDecimal PAD_PRN=new BigDecimal("0.00");
 		BigDecimal PAD_ITR=new BigDecimal("0.00");
 		BigDecimal PNS_ITR=new BigDecimal("0.00");

 		for (int j = 0; j < objs.length; j++) {
 			jjls=(DataObject) objs[j];
 			PAD_PRN=PAD_PRN.add(jjls.getBigDecimal("PAD_PRN"));
 			PAD_ITR=PAD_ITR.add(jjls.getBigDecimal("PAD_ITR"));
 			PNS_ITR=PNS_ITR.add(jjls.getBigDecimal("PNS_ITR"));

 			
 			list.add(objs[j]);
 			
		}
		DataObject objs_HJ = DataObjectUtil.createDataObject("com.bos.pub.meta.TbPubDate");
		objs_HJ.setString("XH", "合计");
		 
		objs_HJ.setBigDecimal("PAD_PRN", PAD_PRN);
		objs_HJ.setBigDecimal("PAD_ITR", PAD_ITR);
		objs_HJ.setBigDecimal("PNS_ITR", PNS_ITR);
 

		list.add(objs_HJ);
		Object[] objs1=list.toArray();
 		
		return objs1;
		
	}
}
