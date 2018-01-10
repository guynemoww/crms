package com.bos.print;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.pub.DateUtil;
import com.bos.pub.EosUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.LoanTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet
public class PrintDataManage {
	@SuppressWarnings("unchecked")
	@Bizlet
	public DataObject T1303(DataObject[] datas, String summaryId, String begDate, String endDate) {
		DataObject obj = EosUtil.createDataObject();
		if (datas == null || datas.length == 0) {
			return obj;
		}
		DataObject summary = EntityUtil.getEntityById(LoanTableName.TB_LOAN_SUMMARY, "summaryId", summaryId);
		if (summary == null) {
			return obj;
		}
		Date beginD = DateUtil.StringToDate(begDate);
		Date endD = DateUtil.StringToDate(endDate);
		BigDecimal sumBjAmt = BigDecimal.ZERO;
		BigDecimal sumLxAmt = BigDecimal.ZERO;
		List<DataObject> dataList = new ArrayList<DataObject>();
		List<DataObject> dataList1 = new ArrayList<DataObject>();
		Map<String,String> map1 = new HashMap<String, String>();
		for (DataObject d : datas) {
			Date rcvDate = getDate(d.getString("rcvDate"));
			if (rcvDate == null || rcvDate.before(beginD) || rcvDate.after(endD)) {
				continue;
			}
			BigDecimal bjAmt = sumAmt(d, "padUpPrn", "padUpOftPrn");
			d.set("padUpPrn", GitUtil.getMoney(bjAmt));
			sumBjAmt = sumBjAmt.add(bjAmt);
			BigDecimal lxAmt = sumAmt(d, "padUpNorItrIn", "padUpDftItrIn", "padUpPnsItrIn", "padUpCpdItrIn", "padUpNorItrOut", "padUpDftItrOut", "padUpPnsItrOut", "padUpCpdItrOut", "padUpOftItr");
			sumLxAmt = sumLxAmt.add(lxAmt);
			d.set("padUpItr", lxAmt);
			String payPrimAcct = d.getString("payPrimAcct");
			if(map1.size()<1){
				map1.put(d.getString("payPrimAcct"), d.getString("payPrimName"));
			}else{
				for(Map.Entry<String,String> entry: map1.entrySet()){
					if(!d.getString("payPrimAcct").equals(entry.getKey())){
						map1.put(d.getString("payPrimAcct"), d.getString("payPrimName"));
					}
				}				
			}
			if ((payPrimAcct == null || (payPrimAcct = payPrimAcct.trim()).isEmpty()) && bjAmt.compareTo(BigDecimal.ZERO) == 0 && lxAmt.compareTo(BigDecimal.ZERO) == 0) {
				continue;
			}
			dataList.add(d);
		}
		
		for(Map.Entry<String,String> entry: map1.entrySet()){
			DataObject dataObj = DataObjectUtil.createDataObject("com.bos.dataset.pub.PrintT1303Entry");
			dataObj.set("payPrimAcct", entry.getKey());
			dataObj.set("payPrimName", entry.getValue());
			dataList1.add(dataObj);
		}
		if (dataList.size() > 0) {
			obj.set("info", dataList);
			obj.set("info1", dataList1);
			obj.set("printBeginDate", begDate);
			obj.set("printEndDate", endDate);
			obj.set("jjye", summary.get("jjye"));
			obj.set("beginDate", getDateStr(summary.get("beginDate")));
			obj.set("endDate", getDateStr(summary.get("endDate")));
			obj.set("sumPadUpItr", GitUtil.getMoney(sumLxAmt));
			obj.set("sumPadUpPrn", GitUtil.getMoney(sumBjAmt));
			obj.set("sumAmt", GitUtil.getMoney(sumLxAmt.add(sumBjAmt)));
			DataObject d = dataList.get(0);
			obj.set("dueNum", d.get("dueNum"));
			obj.set("brwName", d.get("brwName"));
			String temp = d.getString("opnDep");
			obj.set("opnDep", getAccOrgName(temp));
			Map<String, String> param = new HashMap<String, String>();
			param.put("dueNum", d.getString("dueNum"));
			Object[] objs = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_APLUS, "com.bos.utp.rights.funds.queryCurrSybjlx", param);
			if (objs != null && objs.length == 1) {
				Map<String, Object> map = (Map<String, Object>) objs[0];
				obj.set("jjye", GitUtil.getMoney((BigDecimal) map.get("BJ")));
				obj.set("tq_bj", GitUtil.getMoney((BigDecimal) map.get("TQ_BJ")));
				obj.set("tq_lx", GitUtil.getMoney((BigDecimal) map.get("TQ_LX")));
			}
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	private String getAccOrgName(String orgNum) {
		Object[] datas = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.pay.LoanSummary.getAccOrgName", orgNum);
		return datas == null || datas.length == 0 ? null : ((Map<String, String>) datas[0]).get("ORG_NAME");
	}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private Date getDate(String date) {
		if (date == null || date.isEmpty()) {
			return null;
		}
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getDateStr(Object date) {
		if (date == null) {
			return null;
		}
		return sdf.format(date);
	}

	private BigDecimal sumAmt(DataObject d, String... names) {
		BigDecimal amt = BigDecimal.ZERO;
		for (String m : names) {
			BigDecimal value = d.getBigDecimal(m);
			if (value == null) {
				continue;
			}
			amt = amt.add(GitUtil.getMoney(value));
		}
		return GitUtil.getMoney(amt);
	}
}
