package com.bos.inter.CallScfInterface;

import java.util.List;
/**
 * @author chenhuan
 */
//担保合同查询

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;
import com.eos.system.annotation.Bizlet;

public class CRMSGuarConInqRs extends SuperBosfxRs{
	
	@XmlElement(name="CRMSGuarConInqRec")
	public List<CRMSGuarConInqRec>	CRMSGuarConInqRec	;

	public void setCRMSGuarConInqRec(List<CRMSGuarConInqRec> guarConInqRec) {
		CRMSGuarConInqRec = guarConInqRec;
	}
	public String toString() {
		return "CRMSGuarConInqRs [CRMSGuarConInqRec=" + CRMSGuarConInqRec+ "]";
    }
}
