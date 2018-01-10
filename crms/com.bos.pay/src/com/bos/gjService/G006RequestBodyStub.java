package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

public class G006RequestBodyStub implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4042418709491397346L;
	private String currencyCd;//币种
	private BigDecimal disRateOfRmb;//对人民币折算率
	private BigDecimal actualExchangeRate;//真实汇率
	private String discountDate;//折算日期
	private String discountUnit;//折算单位名称
	private String validityInd;//有效标志
	private String unitCurrencyCd;//单位货币币种
	private String valuationCurrencyCd;//计价货币币种
	private BigDecimal buyingPrice;//现汇买入价
	private BigDecimal sellingPrice;//现汇卖出价
	public String getCurrencyCd() {
		return currencyCd;
	}
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}
	public BigDecimal getDisRateOfRmb() {
		return disRateOfRmb;
	}
	public void setDisRateOfRmb(BigDecimal disRateOfRmb) {
		this.disRateOfRmb = disRateOfRmb;
	}
	public BigDecimal getActualExchangeRate() {
		return actualExchangeRate;
	}
	public void setActualExchangeRate(BigDecimal actualExchangeRate) {
		this.actualExchangeRate = actualExchangeRate;
	}
	
	public String getDiscountDate() {
		return discountDate;
	}
	public void setDiscountDate(String discountDate) {
		this.discountDate = discountDate;
	}
	public String getDiscountUnit() {
		return discountUnit;
	}
	public void setDiscountUnit(String discountUnit) {
		this.discountUnit = discountUnit;
	}
	public String getValidityInd() {
		return validityInd;
	}
	public void setValidityInd(String validityInd) {
		this.validityInd = validityInd;
	}
	public String getUnitCurrencyCd() {
		return unitCurrencyCd;
	}
	public void setUnitCurrencyCd(String unitCurrencyCd) {
		this.unitCurrencyCd = unitCurrencyCd;
	}
	public String getValuationCurrencyCd() {
		return valuationCurrencyCd;
	}
	public void setValuationCurrencyCd(String valuationCurrencyCd) {
		this.valuationCurrencyCd = valuationCurrencyCd;
	}
	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	
	
}
