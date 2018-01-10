package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

public class SCFCollateralCashs {
	@XmlElement(name = "applyNum")
	public String applyNum;// 放款申请号

	@XmlElement(name = "contractNum")
	public String contractNum;// 贷款合同编号

	@XmlElement(name = "sortType")
	public String sortType;// 押品类型

	@XmlElement(name = "accountNo")
	public String accountNo;// 账号

	@XmlElement(name = "accountCcy")
	public String accountCcy;// 币别

	@XmlElement(name = "suretyAmt")
	public String suretyAmt;// 本次债权金额

	@XmlElement(name = "marginType")
	public String marginType;// 保证金类型

	@XmlElement(name = "marginRation")
	public String marginRation;// 保证金比例

	@XmlElement(name = "operateType")
	public String operateType;// 操作类型 初始保证金的操作类型补充

	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public void setAccountCcy(String accountCcy) {
		this.accountCcy = accountCcy;
	}

	public void setSuretyAmt(String suretyAmt) {
		this.suretyAmt = suretyAmt;
	}

	public void setMarginRation(String marginRation) {
		this.marginRation = marginRation;
	}

	public void setMarginType(String marginType) {
		this.marginType = marginType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Override
	public String toString() {
		return "SCFCollateralCashs[applyNum=" + applyNum + ",contractNum="
				+ contractNum + ",sortType=" + sortType + ",accountNo="
				+ accountNo + ",accountCcy=" + accountCcy + ",suretyAmt="
				+ suretyAmt + ",marginType=" + marginType + ",marginRation="
				+ marginRation + ",operateType=" + operateType;
	}
}
