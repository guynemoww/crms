package com.bos.inter.CallCCMSInterface;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import com.bos.jaxb.javabean.SuperBosfxRq;

/**
 * SCF接口，押品信息同步接口 请求信息
 * 
 * @author menglei
 * 
 */
public class CRMSCollSynRq extends SuperBosfxRq {
	@XmlElement(name = "RqUID_1")
	public String RqUID_1;// SCF交易流水

	@XmlElement(name = "PartyName")
	public String PartyName;// 抵质押人名称

	@XmlElement(name = "CertificateTypeCd")
	public String CertificateTypeCd;// 抵质押人证件类型

	@XmlElement(name = "CertificateCode")
	public String CertificateCode;// 抵质押人证件号码

	@XmlElement(name = "ApplyNum")
	public String ApplyNum;// 放款申请号

	@XmlElement(name = "ContractId")
	public String ContractId;// 贷款合同编号

	@XmlElement(name = "SubContractId")
	public String SubContractId;// 担保合同编号

	@XmlElement(name = "RegNo")
	public String RegNo;// 批次号

	@XmlElement(name = "CRMSCollSynRec")
	public List<CRMSCollSynRec> CRMSCollSynRec;

	@XmlElement(name = "CollateralRate")
	public String CollateralRate;// 抵质押率

	@XmlElement(name = "Currency")
	public String Currency;// 币种

	@XmlElement(name = "SuretyAmt")
	public String SuretyAmt;// 押品的占用金额(担保金额)

	@XmlElement(name = "SuretyTotalAmt")
	public String SuretyTotalAmt;// 押品的总价值

	public void setApplyNum(String applyNum) {
		ApplyNum = applyNum;
	}

	public void setCertificateCode(String certificateCode) {
		CertificateCode = certificateCode;
	}

	public void setCertificateTypeCd(String certificateTypeCd) {
		CertificateTypeCd = certificateTypeCd;
	}

	public void setCollateralRate(String collateralRate) {
		CollateralRate = collateralRate;
	}

	public void setContractId(String contractId) {
		ContractId = contractId;
	}

	public void setCRMSCollSynRec(List<CRMSCollSynRec> collSynRec) {
		CRMSCollSynRec = collSynRec;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public void setPartyName(String partyName) {
		PartyName = partyName;
	}

	public void setRegNo(String regNo) {
		RegNo = regNo;
	}

	public void setRqUID_1(String rqUID_1) {
		RqUID_1 = rqUID_1;
	}

	public void setSubContractId(String subContractId) {
		SubContractId = subContractId;
	}

	public void setSuretyAmt(String suretyAmt) {
		SuretyAmt = suretyAmt;
	}

	public void setSuretyTotalAmt(String suretyTotalAmt) {
		SuretyTotalAmt = suretyTotalAmt;
	}

	@Override
	public String toString() {
		return "CRMSCollSynRq [RqUID_1=" + RqUID_1 + "PartyName=" + PartyName
				+ ",CertificateTypeCd=" + CertificateTypeCd
				+ ",CertificateCode=" + CertificateCode + ",ApplyNum="
				+ ApplyNum + ",ContractId=" + ContractId + ",SubContractId="
				+ SubContractId + ",RegNo=" + RegNo + ",CollateralRate="
				+ CollateralRate + ",Currency=" + Currency + ",SuretyAmt="
				+ SuretyAmt + ",SuretyTotalAmt=" + SuretyTotalAmt
				+ ",CRMSCollSynRec=" + CRMSCollSynRec;
	}

}
