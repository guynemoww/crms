package com.primeton.mgrcore;

import java.io.Serializable;

public class F11231 implements Serializable {
	private static final long serialVersionUID = 4008767821651239490L;

	private String rataKind;// 费率种类
	private String rataNme;// 费率名称
	private String ynFlag1;// 是否标志

	public F11231() {

	}

	public String getRataKind() {
		return rataKind;
	}

	public void setRataKind(String rataKind) {
		this.rataKind = rataKind;
	}

	public String getRataNme() {
		return rataNme;
	}

	public void setRataNme(String rataNme) {
		this.rataNme = rataNme;
	}

	public String getYnFlag1() {
		return ynFlag1;
	}

	public void setYnFlag1(String ynFlag1) {
		this.ynFlag1 = ynFlag1;
	}

	@Override
	public String toString() {
		return "F11231 [rataKind=" + rataKind + ", rataNme=" + rataNme
				+ ", ynFlag1=" + ynFlag1 + "]";
	}

}
