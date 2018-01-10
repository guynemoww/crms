package com.bos.inter.CallBmsInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRs;
import com.eos.system.annotation.Bizlet;
//押品信息查询 （响应）票据->CRMS				

public class CRMSCollInfoInqRs extends SuperBosfxRs{
	@XmlElement(name="Num")
	public int	Num	;         		//	子记录数量
    @XmlElement(name="MarginRate")
	public String	MarginRate	;    		//	保证金比例
	@XmlElement(name="CRMSCollInfoInqRec")
	public List<CRMSCollInfoInqRec>	 CRMSCollInfoInqRec	;  
	public String toString() {
	return "CRMSCollInfoInqRs [CRMSCollInfoInqRec="+CRMSCollInfoInqRec+",Num="+Num+",MarginRate="+MarginRate+"]";
		}
	 /**
	 * @param CRMSCollInfoInqRec 要设置的 CRMSCollInfoInqRec
	 */
	public void setCRMSCollInfoInqRec(List<CRMSCollInfoInqRec> collInfoInqRec) {
		CRMSCollInfoInqRec = collInfoInqRec;
	}
	 /**
	 * @param Num 要设置的 Num
	 */
	public void setNum(int num) {
		Num = num;
	}
	 /**
	 * @param MarginRate 要设置的 MarginRate
	 */
	public void setMarginRate(String marginRate) {
		MarginRate = marginRate;
	}
   
}
