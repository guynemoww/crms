/**
 * 
 */
package com.bos.pay;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import com.bos.conPrint.NumberToCN;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2015-10-23 18:33:18
 * 
 */
@Bizlet("")
public class ConvertDataNew {

	@Bizlet("")
	public DataObject convertPZ(DataObject object){
		// 打印日期
		String time=object.getString("time");
		time=changeDate(time);
		object.set("time", time);
		
		String BEGIN_DATE=object.getString("begDate");
		BEGIN_DATE=changeDate(BEGIN_DATE);
		// 带年月日的日期格式
		object.set("begDate", BEGIN_DATE);
		
		String END_DATE=object.getString("endDate");
		END_DATE=changeDate(END_DATE);
		object.set("endDate", END_DATE);
		
		String RCV_DATE=object.getString("rcvDate");
		RCV_DATE=changeDate(RCV_DATE);
		object.set("rcvDate", RCV_DATE);
		
		DecimalFormat df=new DecimalFormat("￥###,###.##");
		
		// 判断传入Object中需要转换的字段
		String o = object.toString();
		if (o.contains("amt")) {
			String smailAmt=df.format(object.getDouble("amt"));
			BigDecimal SUMMARY_AMT=object.getBigDecimal("amt");
			String summaryAmt=changeMoney(SUMMARY_AMT);
			/** 大写金额*/
			object.set("amt", summaryAmt);
			/** 小写金额*/
			object.set("sAmt", smailAmt);
		}else if (o.contains("padUpPrn")) {
			String smailAmt0=df.format(object.getDouble("padUpPrn"));
			BigDecimal SUMMARY_AMT0=object.getBigDecimal("padUpPrn");
			String summaryAmt0=changeMoney(SUMMARY_AMT0);
			/** 大写金额*/
			object.set("padUpPrn", summaryAmt0);
			/** 小写金额*/
			object.set("padUpPrnS", smailAmt0);
			
			String smailAmt1=df.format(object.getDouble("padUpPentPrn"));
			BigDecimal SUMMARY_AMT1=object.getBigDecimal("padUpPentPrn");
			String summaryAmt1=changeMoney(SUMMARY_AMT1);
			object.set("padUpPentPrn", summaryAmt1);
			object.set("padUpPentPrnS", smailAmt1);
			
			String smailAmt2=df.format(object.getDouble("padUpPentIcm"));
			BigDecimal SUMMARY_AMT2=object.getBigDecimal("padUpPentIcm");
			String summaryAmt2=changeMoney(SUMMARY_AMT2);
			object.set("padUpPentIcm", summaryAmt2);
			object.set("padUpPentIcmS", smailAmt2);
		}
		return  object;
	}
		
	// 日期转换 原格式yyyy-MM-dd转yyyy年MM月dd日
	@Bizlet("")
	public String changeDate(String date) {
		String[] array;
		String conDate;
		if ("" != date && null != date) {
			array = date.substring(0, 10).split("-");
			conDate = array[0] + "年" + array[1] + "月" + array[2] + "日";
			return conDate;
		}
		return null;
	}

	// 小写货币转大写汉字
	@Bizlet("")
	public String changeMoney(BigDecimal money) {
		return NumberToCN.number2CNMontrayUnit(money);
	}

}
