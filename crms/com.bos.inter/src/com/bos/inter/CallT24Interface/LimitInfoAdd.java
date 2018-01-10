/**
 * 
 */
package com.bos.inter.CallT24Interface;

import java.math.BigDecimal;

import javax.xml.bind.JAXBException;

import com.bos.pub.GitUtil;
import com.eos.system.annotation.Bizlet;
import com.ibm.mq.MQException;
import commonj.sdo.DataObject;

/**
 * @author tianchuang
 * @date 2014-05-13 16:20:14 额度信息建立 法人账户透支(101004) 进口信用证(10300401)
 */
@Bizlet("")
public class LimitInfoAdd {
	@Bizlet(value = "")
	public LimitInfoBean sendtoT24(DataObject bizJkxyzs) throws JAXBException, MQException {
		LimitInfoBean cl = new LimitInfoBean();
		cl.setCustomer(bizJkxyzs.getString("customerId"));// 客户号
		cl.setLimitCode("0006100");// 额度代码
		cl.setCurrency(bizJkxyzs.getString("limitCurrency"));
		cl.setAmt((bizJkxyzs.getBigDecimal("txnAmt").setScale(2, BigDecimal.ROUND_HALF_UP)).toString());// 金额
		// cl.setValueDate((loanDetail.getString("startIntrestDate").replace("-", "")).substring(0, 8));//起息日 
		cl.setBeginDt((bizJkxyzs.getString("approvalDate").replace("-", "")).substring(0, 8));// 审批日
		cl.setEndDt((bizJkxyzs.getString("expiryDate").replace("-", "")).substring(0, 8));// 到期日EXPIRY_DATE
		cl.setProposalDt((bizJkxyzs.getString("proposalDate").replace("-", "")).substring(0, 8));// 额度申请提交日
		cl.setAvailable("Y");// 是否可用额度
		cl.setReviewFrequency("D");// 审核频率
		cl.setFBID("167");// 业务类型
		cl.setTxnType("CRMS001");// 本地交易类型
		return cl;
	}
}
