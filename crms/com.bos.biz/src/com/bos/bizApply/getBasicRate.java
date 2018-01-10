package com.bos.bizApply;

import java.math.BigDecimal;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

@Bizlet("")
public class getBasicRate {
	@Bizlet("")
	public BigDecimal getBasicrate(int loanlength, String rateType) {
		BigDecimal basicrate = new BigDecimal("0");
		DataObject rate = DataObjectUtil
				.createDataObject("com.bos.dataset.sys.TbSysBasicRate");
		rate.set("status", "1");
		if (rateType.equals("1")) {//普通人民币
			if (loanlength <= 6) {
				rate.set("intRateCd", "1");
			} else if (loanlength > 6 && loanlength <= 12) {
				rate.set("intRateCd", "2");
			} else if (loanlength > 12 && loanlength <= 36) {
				rate.set("intRateCd", "3");
			} else if (loanlength > 36 && loanlength <= 60) {
				rate.set("intRateCd", "4");
			} else if (loanlength > 60) {
				rate.set("intRateCd", "5");
			}
		} else if (rateType.equals("2")) {//公积金
			if (loanlength <= 60) {
				rate.set("intRateCd", "6");
			} else if (loanlength > 60) {
				rate.set("intRateCd", "7");
			}
		}
		DatabaseUtil.expandEntityByTemplate("default", rate, rate);
		basicrate = (BigDecimal) rate.get("intRate");
		return basicrate;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}
}
