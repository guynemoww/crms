package com.bos.inter.CallBmsInterface;

import javax.xml.bind.annotation.XmlElement;
/**
 * ch
 */
import com.eos.system.annotation.Bizlet;
public class CRMSLoandAppRec {
	@XmlElement(name="DraftId")
	public String	DraftId	;           //	票据编号
	@XmlElement(name="Amount")  
	public String	Amount	;         //	票据金额
	@XmlElement(name="ValueDt")
	public String	ValueDt	;        //	贴现日
	@XmlElement(name="EndDt")
	public String	EndDt	;        //	到期日
	@XmlElement(name="LoanIntRt")
	public String	LoanIntRt	;   //	执行年利率
	@XmlElement(name="Acceptor")
	public String	Acceptor	;        //	承兑银行/承兑人
	@XmlElement(name="DraftType")
	public String	DraftType	;   //	票据类型
	@XmlElement(name="DraftCd")
	public String	DraftCd	;        //	票据属性
	/**
	 * @param Acceptor 要设置的 Acceptor
	 */
	public void setAcceptor(String acceptor) {
		Acceptor = acceptor;
	}
	/**
	 * @param amount 要设置的 amount
	 */
	public void setAmount(String amount) {
		Amount = amount;
	}
	/**
	 * @param DraftCd 要设置的 DraftCd
	 */
	public void setDraftCd(String draftCd) {
		DraftCd = draftCd;
	}
	/**
	 * @param DraftId 要设置的 DraftId
	 */
	public void setDraftId(String draftId) {
		DraftId = draftId;
	}
	/**
	 * @param DraftType 要设置的 DraftType
	 */
	public void setDraftType(String draftType) {
		DraftType = draftType;
	}
	/**
	 * @param EndDt 要设置的 EndDt
	 */
	public void setEndDt(String endDt) {
		EndDt = endDt;
	}
	/**
	 * @param LoanIntRt 要设置的 LoanIntRt
	 */
	public void setLoanIntRt(String loanIntRt) {
		LoanIntRt = loanIntRt;
	}
	/**
	 * @param ValueDt 要设置的 ValueDt
	 */
	public void setValueDt(String valueDt) {
		ValueDt = valueDt;
	}
	

}
