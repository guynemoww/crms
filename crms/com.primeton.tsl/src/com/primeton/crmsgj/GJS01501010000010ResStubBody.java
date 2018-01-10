package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 牌价查询接口---响应对象体
 * @author shendl
 *
 */
public class GJS01501010000010ResStubBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4837698819747086223L;
	private String currSymbol;//货币符号
	private String valCom;//计价单位
	private String huiBuy;//汇买价
	private String huiSale;//汇卖价
	private String noteBuy;//钞买价
	private String noteSale;//钞卖价
	public String getCurrSymbol() {
		return currSymbol;
	}
	public void setCurrSymbol(String currSymbol) {
		this.currSymbol = currSymbol;
	}
	public String getValCom() {
		return valCom;
	}
	public void setValCom(String valCom) {
		this.valCom = valCom;
	}
	public String getHuiBuy() {
		return huiBuy;
	}
	public void setHuiBuy(String huiBuy) {
		this.huiBuy = huiBuy;
	}
	public String getHuiSale() {
		return huiSale;
	}
	public void setHuiSale(String huiSale) {
		this.huiSale = huiSale;
	}
	public String getNoteBuy() {
		return noteBuy;
	}
	public void setNoteBuy(String noteBuy) {
		this.noteBuy = noteBuy;
	}
	public String getNoteSale() {
		return noteSale;
	}
	public void setNoteSale(String noteSale) {
		this.noteSale = noteSale;
	}
	@Override
	public String toString() {
		return "GJS01501030000010ResStubBody [currSymbol=" + currSymbol
				+ ", valCom=" + valCom + ", huiBuy=" + huiBuy + ", huiSale="
				+ huiSale + ", noteBuy=" + noteBuy + ", noteSale=" + noteSale
				+ "]";
	}
}
