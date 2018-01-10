/**
 * 
 */
package com.bos.inter.CallBmsInterface;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;
import com.eos.system.annotation.Bizlet;

/**
 * @author shenglong
 * @date 2014-06-09 15:11:41
 *
 */
public class CRMSContrListInqRs extends SuperBosfxRs{
	@XmlElement(name="CRMSContrListInqRec")
	public List<CRMSContrListInqRec>	CRMSContrListInqRec	;   //合同信息清单
	@XmlElement(name="Num")
	public int	Num	;   //子记录数量
	 public String toString() {
			return "CRMSContrListInqRs [Num=" + Num  + ", CRMSContrListInqRec="
					+ CRMSContrListInqRec + "]";
	    }

	public void setNum(int num) {
		Num = num;
	}

	public void setCRMSContrListInqRec(List<CRMSContrListInqRec> contrListInqRec) {
		CRMSContrListInqRec = contrListInqRec;
	}
	
}
