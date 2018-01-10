package com.bos.inter.CallBmsInterface;

import javax.xml.bind.annotation.XmlElement;

public class CRMSCreRestInsetRec {
	@XmlElement(name="DraftId")
	public String	DraftId	;       //	票据编号
	@XmlElement(name="Amount")
	public String	Amount	;        //	票面金额
	@XmlElement(name="AcceptDraftId")
	public String  AcceptDraftId; // 当天票据编号
	/**
	 * @param Amount 要设置的 Amount
	 */
	public void setAmount(String amount) {
		Amount = amount;
	}
	/**
	 * @param DraftId 要设置的 DraftId
	 */
	public void setDraftId(String draftId) {
		DraftId = draftId;
	}
	/**
	 * @param AcceptDraftId 要设置的 AcceptDraftId
	 */
	public void setAcceptDraftId(String acceptDraftId) {
		AcceptDraftId = acceptDraftId;
	}
}
