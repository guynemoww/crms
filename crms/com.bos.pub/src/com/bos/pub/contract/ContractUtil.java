package com.bos.pub.contract;

import java.math.BigDecimal;

import com.bos.pub.DateStyle;
import com.bos.pub.DateUtil;
import com.bos.pub.GitUtil;
import com.bos.pub.StringUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.SysTableName;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

@Bizlet
public class ContractUtil {
	/**
	 * 人民币币种编码
	 */
	public static final String CURRENCYCD_CNY = "CNY";

	@Bizlet
	/**
	 * 根据币种获取当前人民币金额
	 * @param amt
	 * @param currency_cd
	 * @return
	 */
	public static BigDecimal getAmt_rmb(BigDecimal amt, String currency_cd) {
		if (BigDecimal.ZERO.compareTo(amt) == 0 || CURRENCYCD_CNY.equals(currency_cd)) {
			return GitUtil.getMoney(amt);
		}
		BigDecimal changeRate = getExchangeRate(currency_cd);
		amt = amt.multiply(changeRate);
		return GitUtil.getMoney(amt);
	}

	/**
	 * 根据信贷的币种码值获取当前币种汇率
	 * 
	 * @param currency_cd
	 * @return
	 */
	@Bizlet
	public static BigDecimal getExchangeRate(String currency_cd) {
		if (StringUtil.isNull(currency_cd)) {
			throw new RuntimeException("错误的币种码值[" + currency_cd + "]");
		}
		DataObject obj = EntityUtil.getEntityById(SysTableName.TB_SYS_EXCHANGE_RATE, "exchangeRateId", currency_cd, "validityInd", "1");
		String dateStr = DateUtil.DateToString(obj.getDate("discountDate"), DateStyle.YYYY_MM_DD_8L);
		String busDateStr = DateUtil.DateToString(GitUtil.getBusiDate(), DateStyle.YYYY_MM_DD_8L);
		if (!dateStr.equals(busDateStr)) {
			throw new RuntimeException("未获取到时间点[" + dateStr + "]的[" + currency_cd + "]汇率信息");
		}
		return obj.getBigDecimal("discountRateOfRmb");
	}
}
