package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 业务拆分申请及机构拆分申请输入
 * @author CHENPAN
 *
 */
public class BusiOrgSplitRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -655609719233229033L;
	private String exiFlg;             //拆分类型
	private String exdBegDate;        //拆分申请日期
	public BusiOrgSplitRq() {
	}
	public String getExiFlg() {
		return exiFlg;
	}
	public void setExiFlg(String exiFlg) {
		this.exiFlg = exiFlg;
	}
	public String getExdBegDate() {
		return exdBegDate;
	}
	public void setExdBegDate(String exdBegDate) {
		this.exdBegDate = exdBegDate;
	}
	
}
