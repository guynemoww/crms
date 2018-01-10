package com.bos.inter.CallT24Interface;
/**
 * @author chenhuan
 */
import javax.xml.bind.annotation.XmlElement;

import com.bos.jaxb.javabean.SuperBosfxRq;
//账户信息查询
public class TDpAcctAllInqRq extends SuperBosfxRq{
	@XmlElement(name="MediumType")
	public String	MediumType	;         		    //	介质账户类型
    @XmlElement(name="AcctNo")
	public String	AcctNo	;    		           //	介质账号
    @XmlElement(name="Currency")
	public String Currency;                      //币种
    @XmlElement(name="FcyType")
	public String	FcyType	;         		 //	外币钞汇标志
    @XmlElement(name="DEPProd")
	public String DEPProd	;    		    //	存款子产品类型
    @XmlElement(name="Pwd")
	public String Pwd;                    //账户密码
    public String toString() {
    
    	return "TDpAcctAllInqRq [MediumType=" + MediumType + ", AcctNo="
	   + AcctNo + ", Currency=" + Currency + ", FcyType="
	   + FcyType + ",DEPProd=" + DEPProd + ", Pwd="
	    + Pwd + "]";
}
    /**
	 * @param AcctNo 要设置的 AcctNo
	 */
	public void setAcctNo(String acctNo) {
		AcctNo = acctNo;
	}
	 /**
	 * @param Currency 要设置的 Currency
	 */
	
	public void setCurrency(String currency) {
		Currency = currency;
	}
	 /**
	 * @param DEPProd 要设置的 DEPProd
	 */
	public void setDEPProd(String DEPProd) {
		DEPProd = DEPProd;
	}
	 /**
	 * @param FcyType 要设置的 FcyType
	 */
	public void setFcyType(String fcyType) {
		FcyType = fcyType;
	}
	 /**
	 * @param MediumType 要设置的 MediumType
	 */
	public void setMediumType(String mediumType) {
		MediumType = mediumType;
	}
	 /**
	 * @param Pwd 要设置的 Pwd
	 */
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
  
}
