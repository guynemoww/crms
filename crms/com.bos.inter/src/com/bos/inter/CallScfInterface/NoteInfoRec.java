/**
 * 
 */
package com.bos.inter.CallScfInterface;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author tc
 *
 */
public class NoteInfoRec {
	@XmlElement(name="ValueDt")
	public String	ValueDt	;//	票据签发日
	@XmlElement(name="DraftId")
	public String	DraftId	;//	票据编号
	@XmlElement(name="Amount")
	public String	Amount	;//	票据金额
	@XmlElement(name="Margin")
	public String	Margin	;//	保证金分摊额
	@XmlElement(name="DueDate")
	public String	DueDate	;//	到期日
	@XmlElement(name="DraftCcy")
	public String	DraftCcy	;//	票据币种
	/**
	 * @param amount 要设置的 amount
	 */
	public void setAmount(String amount) {
		Amount = amount;
	}
	/**
	 * @param draftCcy 要设置的 draftCcy
	 */
	public void setDraftCcy(String draftCcy) {
		DraftCcy = draftCcy;
	}
	/**
	 * @param draftId 要设置的 draftId
	 */
	public void setDraftId(String draftId) {
		DraftId = draftId;
	}
	/**
	 * @param dueDate 要设置的 dueDate
	 */
	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}
	/**
	 * @param margin 要设置的 margin
	 */
	public void setMargin(String margin) {
		Margin = margin;
	}
	/**
	 * @param valueDt 要设置的 valueDt
	 */
	public void setValueDt(String valueDt) {
		ValueDt = valueDt;
	}


}
