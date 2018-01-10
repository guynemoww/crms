package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 国结业务表外业务放款状态查询---请求对象
 * @author shendl
 *
 */
public class GJS01501010000011Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3450642255958531420L;
	private String debitNo;//借据号
	private String prdCode;//产品代码
	private String proSubTp;//产品子类型
	public String getDebitNo() {
		return debitNo;
	}
	public void setDebitNo(String debitNo) {
		this.debitNo = debitNo;
	}
	public String getPrdCode() {
		return prdCode;
	}
	public void setPrdCode(String prdCode) {
		this.prdCode = prdCode;
	}
	public String getProSubTp() {
		return proSubTp;
	}
	public void setProSubTp(String proSubTp) {
		this.proSubTp = proSubTp;
	}
	@Override
	public String toString() {
		return "GJS01501030000011Req [debitNo=" + debitNo + ", prdCode="
				+ prdCode + ", proSubTp=" + proSubTp + "]";
	}
}
