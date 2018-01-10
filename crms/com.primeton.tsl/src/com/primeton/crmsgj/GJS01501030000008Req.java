package com.primeton.crmsgj;

import java.io.Serializable;

/**
 * 编号校验接口---请求对象
 * @author shendl
 *
 */
public class GJS01501030000008Req implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8381099840236338405L;
	private String knotBusiNo;//国结业务编号
	private String knotTradeTp;//国结交易类型
	private String custNo;//客户编号
	private String proSubTp;//产品子类型
	private String bondAcct;//保证金账号
	private String bondCurr;//保证金币种
	public String getKnotBusiNo() {
		return knotBusiNo;
	}
	public void setKnotBusiNo(String knotBusiNo) {
		this.knotBusiNo = knotBusiNo;
	}
	public String getKnotTradeTp() {
		return knotTradeTp;
	}
	public void setKnotTradeTp(String knotTradeTp) {
		this.knotTradeTp = knotTradeTp;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getBondAcct() {
		return bondAcct;
	}
	public void setBondAcct(String bondAcct) {
		this.bondAcct = bondAcct;
	}
	public String getBondCurr() {
		return bondCurr;
	}
	public void setBondCurr(String bondCurr) {
		this.bondCurr = bondCurr;
	}
	public String getProSubTp() {
		return proSubTp;
	}
	public void setProSubTp(String proSubTp) {
		this.proSubTp = proSubTp;
	}
	@Override
	public String toString() {
		return "GJS01501030000008Req [knotBusiNo=" + knotBusiNo
				+ ", knotTradeTp=" + knotTradeTp + ", custNo=" + custNo
				+ ", proSubTp=" + proSubTp + ", bondAcct=" + bondAcct
				+ ", bondCurr=" + bondCurr + "]";
	}
	
}
