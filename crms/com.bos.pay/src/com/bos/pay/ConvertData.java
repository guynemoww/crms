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
public class ConvertData {

	@Bizlet("")
	public DataObject convertPZ(DataObject object){
		String time=object.getString("time");
		time=changeDate(time);
		object.set("time", time);
		
		
		String BEGIN_DATE=object.getString("BEGIN_DATE");
		BEGIN_DATE=changeDate(BEGIN_DATE);
		object.set("BEGIN_DATE", BEGIN_DATE);
		
		String END_DATE=object.getString("END_DATE");
		END_DATE=changeDate(END_DATE);
		object.set("END_DATE", END_DATE);
		
		
		DecimalFormat df=new DecimalFormat("￥###,###.##");
		String smailAmt=df.format(object.getDouble("RMB_AMT"));
		
		BigDecimal SUMMARY_AMT=object.getBigDecimal("RMB_AMT");
		String summaryAmt=changeMoney(SUMMARY_AMT);
		object.set("RMB_AMT", summaryAmt);
		object.set("SMAIL_RMB_AMT", smailAmt);
		
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
