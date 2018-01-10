package com.bos.inter.CallBmsInterface;

import java.io.Serializable;

/**
 * 额度查询  请求报文体
 * @author shendl
 *
 */
public class CRMSCrdQueryReqBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6419689810230098684L;
	public String custType;//客户类型
	public String custNo;//客户编号
	public String productCd;//产品编码
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	
	public String getCustType() {
		return custType;
	}
	public String getCustNo() {
		return custNo;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	
}
