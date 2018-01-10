package com.primeton.mgrcore;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 【XD01】通用记账(8661)
 */
public class OXD011_AccoutingReq implements Serializable {
	private static final long serialVersionUID = 2032593153400243983L;
	private String chargeSeq;// 记账索引号
	private String outSystemDate;// 外围系统日期
	private String busiType1;// 业务种类
	private String unitNo;// 单位编号
	private String lotNum;// 对账批号
	private String amount;// 发生额
	private String thridTransCode;// 第三方交易码
	private BigInteger recNum;// 分录数量
	private String summaryCode;// 摘要代码
	private String summaryDescription;// 摘要描述
	private String remarkInfo;// 备注信息
	private FXD011[] fxd011;// 循环字段
	private String hxorg;//核心记账机构--调用此接口必传

	public OXD011_AccoutingReq() {

	}

	public String getChargeSeq() {
		return chargeSeq;
	}

	public void setChargeSeq(String chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public String getOutSystemDate() {
		return outSystemDate;
	}

	public void setOutSystemDate(String outSystemDate) {
		this.outSystemDate = outSystemDate;
	}

	public String getBusiType1() {
		return busiType1;
	}

	public void setBusiType1(String busiType1) {
		this.busiType1 = busiType1;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getLotNum() {
		return lotNum;
	}

	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getThridTransCode() {
		return thridTransCode;
	}

	public void setThridTransCode(String thridTransCode) {
		this.thridTransCode = thridTransCode;
	}

	public BigInteger getRecNum() {
		return recNum;
	}

	public void setRecNum(BigInteger recNum) {
		this.recNum = recNum;
	}

	public String getSummaryCode() {
		return summaryCode;
	}

	public void setSummaryCode(String summaryCode) {
		this.summaryCode = summaryCode;
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

	public FXD011[] getFxd011() {
		return fxd011;
	}

	public void setFxd011(FXD011[] fxd011) {
		this.fxd011 = fxd011;
	}

	public String getHxorg() {
		return hxorg;
	}

	public void setHxorg(String hxorg) {
		this.hxorg = hxorg;
	}


}
