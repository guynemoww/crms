package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 业务拆分申请及机构拆分申请输出
 * @author CHENPAN
 *
 */
public class BusiOrgSplitRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 827297252441203691L;
	private String legPerCod;          //法人代码   
	private String exiFlg;             //拆分类型
	private String exdBegDate;        //拆分申请日期
	public BusiOrgSplitRs() {
	}
	public String getLegPerCod() {
		return legPerCod;
	}
	public void setLegPerCod(String legPerCod) {
		this.legPerCod = legPerCod;
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
