package com.primeton.mgrcore;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class OXD012ResInfo implements Serializable {
	private static final long serialVersionUID = 2727693460958741759L;
	private String transName;// 交易名称
	private String custNo;// 客户账号
	private String acct;// 账号
	private String custChnName;// 客户中文名
	private String feeCny;// 收费币种
	private String costPayFlg;// 费用收付标志
	private String totFee;// 合计费用
	private String cshOrTran;// 现转标志
	private String summaryDescription;// 摘要描述
	private String remarkInfo;// 备注信息
	private BigInteger infoNum;// 循环记录数
	private List<OXD012ResInfoRec> oxd012ResInfoRec;

	public OXD012ResInfo() {

	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAcct() {
		return acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	public String getCustChnName() {
		return custChnName;
	}

	public void setCustChnName(String custChnName) {
		this.custChnName = custChnName;
	}

	public String getFeeCny() {
		return feeCny;
	}

	public void setFeeCny(String feeCny) {
		this.feeCny = feeCny;
	}

	public String getCostPayFlg() {
		return costPayFlg;
	}

	public void setCostPayFlg(String costPayFlg) {
		this.costPayFlg = costPayFlg;
	}

	public String getTotFee() {
		return totFee;
	}

	public void setTotFee(String totFee) {
		this.totFee = totFee;
	}

	public String getCshOrTran() {
		return cshOrTran;
	}

	public void setCshOrTran(String cshOrTran) {
		this.cshOrTran = cshOrTran;
	}

	public String getSummaryDescription() {
		return summaryDescription;
	}

	public void setSummaryDescription(String summaryDescription) {
		this.summaryDescription = summaryDescription;
	}

	public String getRemarkInfo() {
		return remarkInfo;
	}

	public void setRemarkInfo(String remarkInfo) {
		this.remarkInfo = remarkInfo;
	}

	public BigInteger getInfoNum() {
		return infoNum;
	}

	public void setInfoNum(BigInteger infoNum) {
		this.infoNum = infoNum;
	}

	public List<OXD012ResInfoRec> getOxd012ResInfoRec() {
		return oxd012ResInfoRec;
	}

	public void setOxd012ResInfoRec(List<OXD012ResInfoRec> oxd012ResInfoRec) {
		this.oxd012ResInfoRec = oxd012ResInfoRec;
	}

	@Override
	public String toString() {
		return "OXD012ResInfo [transName=" + transName + ", custNo=" + custNo
				+ ", acct=" + acct + ", custChnName=" + custChnName
				+ ", feeCny=" + feeCny + ", costPayFlg=" + costPayFlg
				+ ", totFee=" + totFee + ", cshOrTran=" + cshOrTran
				+ ", summaryDescription=" + summaryDescription
				+ ", remarkInfo=" + remarkInfo + ", infoNum=" + infoNum
				+ ", oxd012ResInfoRec=" + oxd012ResInfoRec + "]";
	}

}
