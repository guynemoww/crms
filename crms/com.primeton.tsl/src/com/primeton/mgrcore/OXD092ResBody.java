package com.primeton.mgrcore;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class OXD092ResBody implements Serializable {
	private static final long serialVersionUID = -1583255556542289461L;

	private String remarkInfo;// 备注信息
	private BigInteger recNum;// 循环记录数
	private List<FXD092> fxd092;

	public OXD092ResBody() {

	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}

	public BigInteger getRecNum() {
		return recNum;
	}

	public void setRecNum(BigInteger recNum) {
		this.recNum = recNum;
	}

	public List<FXD092> getFxd092() {
		return fxd092;
	}

	public void setFxd092(List<FXD092> fxd092) {
		this.fxd092 = fxd092;
	}

	@Override
	public String toString() {
		return "OXD092ResBody [remarkInfo=" + remarkInfo + ", recNum=" + recNum
				+ ", fxd092=" + fxd092 + "]";
	}

}
