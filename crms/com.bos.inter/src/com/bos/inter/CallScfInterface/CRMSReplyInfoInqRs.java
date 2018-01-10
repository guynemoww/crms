package com.bos.inter.CallScfInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;
//批复信息查询(响应方)  SCF->CRMS
public class CRMSReplyInfoInqRs extends SuperBosfxRs{

    @XmlElement(name="CRMSReplyInfoInqRec")
	public List<CRMSReplyInfoInqRec>	CRMSReplyInfoInqRec	;
    public String toString() {
		return "CRMSReplyInfoInqRs [ CRMSReplyInfoInqRec="
				+ CRMSReplyInfoInqRec + "]";
    }
    /**
	 * @param CRMSReplyInfoInqRec 要设置的 CRMSReplyInfoInqRec
	 */
	public void setCRMSReplyInfoInqRec(List<CRMSReplyInfoInqRec> replyInfoInqRec) {
		CRMSReplyInfoInqRec = replyInfoInqRec;
	}

}
