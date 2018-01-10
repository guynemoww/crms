package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 票据额度恢复---请求对象体
 * @author lenovo
 *
 */
public class P001RequestBody implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5020009079542487494L;
	private String summaryNum;//借据编号
	private String ecifPartyNum;//客户编号
	private String tyPartyNum;//同业客户编号
	private BigDecimal happenAmount;//额度恢复金额(人民币)
	private String productCd;//产品代码
	public String getSummaryNum() {
		return summaryNum;
	}
	public void setSummaryNum(String summaryNum) {
		this.summaryNum = summaryNum;
	}
	public String getEcifPartyNum() {
		return ecifPartyNum;
	}
	public void setEcifPartyNum(String ecifPartyNum) {
		this.ecifPartyNum = ecifPartyNum;
	}
	public String getTyPartyNum() {
		return tyPartyNum;
	}
	public void setTyPartyNum(String tyPartyNum) {
		this.tyPartyNum = tyPartyNum;
	}
	public BigDecimal getHappenAmount() {
		return happenAmount;
	}
	public void setHappenAmount(BigDecimal happenAmount) {
		this.happenAmount = happenAmount;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	
	
}
