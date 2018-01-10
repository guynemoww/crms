package com.primeton.mgrcore;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class OXD012ResBody implements Serializable {
	private static final long serialVersionUID = -3556685663065149120L;
	private String hostTransDate;// 核心交易日期
	private String hostOperSeq;// 核心柜员流水
	private String hostTransCode;// 核心交易码
	private String busiCode;// 业务编号
	private String rolloutWrtOffSeq;// 转出待销账序号
	private String prmMsg;// 提示信息
	private BigInteger recNum;// 循环记录数
	private List<FXD012> fxd012;// 循环字段
	private OXD012ResInfo oxd012ResInfo;// 循环字段

	public OXD012ResBody() {

	}

	public String getHostTransDate() {
		return hostTransDate;
	}

	public void setHostTransDate(String hostTransDate) {
		this.hostTransDate = hostTransDate;
	}

	public String getHostOperSeq() {
		return hostOperSeq;
	}

	public void setHostOperSeq(String hostOperSeq) {
		this.hostOperSeq = hostOperSeq;
	}

	public String getHostTransCode() {
		return hostTransCode;
	}

	public void setHostTransCode(String hostTransCode) {
		this.hostTransCode = hostTransCode;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getRolloutWrtOffSeq() {
		return rolloutWrtOffSeq;
	}

	public void setRolloutWrtOffSeq(String rolloutWrtOffSeq) {
		this.rolloutWrtOffSeq = rolloutWrtOffSeq;
	}

	public String getPrmMsg() {
		return prmMsg;
	}

	public void setPrmMsg(String prmMsg) {
		this.prmMsg = prmMsg;
	}

	public BigInteger getRecNum() {
		return recNum;
	}

	public void setRecNum(BigInteger recNum) {
		this.recNum = recNum;
	}

	public List<FXD012> getFxd012() {
		return fxd012;
	}

	public void setFxd012(List<FXD012> fxd012) {
		this.fxd012 = fxd012;
	}

	public OXD012ResInfo getOxd012ResInfo() {
		return oxd012ResInfo;
	}

	public void setOxd012ResInfo(OXD012ResInfo oxd012ResInfo) {
		this.oxd012ResInfo = oxd012ResInfo;
	}

	@Override
	public String toString() {
		return "OXD012ResBody [hostTransDate=" + hostTransDate
				+ ", hostOperSeq=" + hostOperSeq + ", hostTransCode="
				+ hostTransCode + ", busiCode=" + busiCode
				+ ", rolloutWrtOffSeq=" + rolloutWrtOffSeq + ", prmMsg="
				+ prmMsg + ", recNum=" + recNum + ", fxd012=" + fxd012
				+ ", oxd012ResInfo=" + oxd012ResInfo + "]";
	}

}
