package com.bos.inter.CallBmsInterface;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 额度恢复请求报文体
 * @author shendl
 *
 */
public class CRMSCrdRegainReqBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7331463742114003067L;
	public String custNo;//客户编号
	public BigDecimal happenAmount;//扣减金额
	public String contractNum;//合同编号
	public String productCd;//产品代码
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public BigDecimal getHappenAmount() {
		return happenAmount;
	}
	public void setHappenAmount(BigDecimal happenAmount) {
		this.happenAmount = happenAmount;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	
	
}
