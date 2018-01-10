package com.primeton.mgrcore;

import java.io.Serializable;
import java.util.List;

public class OXD042ResBody implements Serializable {
	private static final long serialVersionUID = 3786745209401848033L;

	private String txCount; // 笔数
	private List<F76091> f76091;// 循环对象
	private OXD042Info info;// 报表路径

	public OXD042ResBody() {

	}

	public String getTxCount() {
		return txCount;
	}

	public void setTxCount(String txCount) {
		this.txCount = txCount;
	}

	public List<F76091> getF76091() {
		return f76091;
	}

	public void setF76091(List<F76091> f76091) {
		this.f76091 = f76091;
	}

	public OXD042Info getInfo() {
		return info;
	}

	public void setInfo(OXD042Info info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "OXD042ResBody [txCount=" + txCount + ", f76091=" + f76091
				+ ", info=" + info + "]";
	}

}
