package com.bos.inter.CallCCMSInterface;

import javax.xml.bind.annotation.XmlElement;

public class CRMSCollCashSynRec {

	@XmlElement(name="LoanId")
	public String LoanId;//放款申请号
	
	@XmlElement(name="ContractId")
	public String ContractId;//贷款合同编号
	
	@XmlElement(name="Type")
	public String Type;//押品类型
	
	@XmlElement(name="AcctNo")
	public String AcctNo;//账号
	
	@XmlElement(name="Currency")
	public String Currency;//币别
	
	@XmlElement(name="SuretyAmt")
	public String SuretyAmt;//本次债权金额
	
	@XmlElement(name="MarginType")
	public String MarginType;//保证金类型
	
	@XmlElement(name="MarginRation")
	public String MarginRation;//保证金比例
	
	@XmlElement(name="OperateType")
	public String OperateType;//操作类型 初始保证金的操作类型补充
	
	
}
