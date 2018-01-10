/**
 * 
 */
package com.bos.inter.CallT24Interface;


import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.xml.bind.JAXBException;

import com.bos.mq.util.KeyGenerator;
import com.eos.system.annotation.Bizlet;
import com.ibm.mq.MQException;
import commonj.sdo.DataObject;

/**
 * @author tianchuang
 * @date 2014-07-03 14:22:31
 * 信用证10300401
 */
@Bizlet("")
public class TNbosIlcOpenHLD {
	  @Bizlet(value="")
	   public TNbosIlcOpenHLDBean sendInfoToT24(DataObject cre,DataObject loanDetail,DataObject party,DataObject temp) throws JAXBException, MQException{
		  
		  TNbosIlcOpenHLDBean cl = new TNbosIlcOpenHLDBean();
		  DecimalFormat df = new DecimalFormat("#.00");
		   cl.setOldSPRsUID(KeyGenerator.getUUID());
		   cl.setOldSPRsUID("");
		   cl.setOperateAuth("INPUT");//操作权限
			if("03".equals(loanDetail.getString("payType"))){
				   cl.setOperateAuth("REVERSE");//操作权限//冲正 
				   cl.setOldSPRsUID(temp.getString("businessNum"));
			   }
		   //cl.setTransSeqNo(KeyGenerator.getUUID());//交易流水	日期+交易序号+交易内编码(即I01这种格式的)+’.’+CHANNEL.ID+’.’+FB.ID
		   
		   cl.setTransSeqNo(loanDetail.getString("transSeqNo"));//交易流水
		   cl.setApplicantCustno(party.getString("ecifPartyNum"));//申请人客户号
		   cl.setCurrency(loanDetail.getString("currencyCd"));//信用证金额
		   if(loanDetail.getBigDecimal("loanAmt")!=null){
		   cl.setAmount(  loanDetail.getBigDecimal("loanAmt").setScale(2, BigDecimal.ROUND_HALF_UP).toString());//信用证币种
		   }
		   cl.setPercentageCrAmt(cre.getString("lcOverflowProportion"));//上浮比率
//		   cl.setPercentageDrAmt(cre.getString("lcOverflowProportion"));//下浮比率
		   //cl.setDdApprNo("");//放款核准单号
		   cl.setDdApprNo(loanDetail.getString("loanQuasiOdd"));
		   cl.setFBID("167");
		   cl.setDelimPos("Y");//上下报文隔位
		   String aa="";
		   if(cre.getString("lineNum")!=null){
			  String a=cre.getString("lineNum");
			  int b = a.indexOf(".");
			  aa=a.substring(b+1);
		   }
		   cl.setLimitReference(aa);
		  return cl;
		  
	  }
}
