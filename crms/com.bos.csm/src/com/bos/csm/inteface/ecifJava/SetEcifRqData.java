package com.bos.csm.inteface.ecifJava;

import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;



public class SetEcifRqData {
	
	 @Bizlet(value="")
	   public EcifCustRq sendCustToEcif(String certType,String certCode,String swiftNum,String ecifPartyNum) 
	 			throws EOSException{
		   EcifCustRq ecifCustRq = new EcifCustRq();
		   if(certType==null && certCode==null && swiftNum==null && ecifPartyNum==null){
			   throw new EOSException("未找到报文请求条件!");
		   }else{
			   if(ecifPartyNum!=null){
				   ecifCustRq.setCustomer(ecifPartyNum == null ? "" : ecifPartyNum);
			   }else if(swiftNum!=null){
				   ecifCustRq.setSwiftBusId(swiftNum == null ? "" : swiftNum);
			   }else{
				   ecifCustRq.setPrsnLegalType(certType== null ? "" :certType);
				   ecifCustRq.setIdCode(certCode== null ? "" :certCode.replace("-", ""));
				   
			   }
			   
		   }
		   
		   return ecifCustRq;
	 }
	 

}
