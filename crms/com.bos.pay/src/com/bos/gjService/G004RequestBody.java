package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 国结调用信贷业务通知接口---请求对象体
 *
 */
public class G004RequestBody implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5382967519614261014L;
	private String dueNum;//借据编号
	private String happenDate;//发生日期
	private BigDecimal happenAmount;//发生金额
	private String status;//状态
	private String noticeType;//通知类型
	private String ywhm;//业务号码
	private String dkOrNot;//是否垫款
	private BigDecimal dkje;//垫款金额
	
	public String getYwhm() {
		return ywhm;
	}
	public void setYwhm(String ywhm) {
		this.ywhm = ywhm;
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	
	public String getHappenDate() {
		return happenDate;
	}
	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getHappenAmount() {
		return happenAmount;
	}
	public void setHappenAmount(BigDecimal happenAmount) {
		this.happenAmount = happenAmount;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getDkOrNot() {
		return dkOrNot;
	}
	public void setDkOrNot(String dkOrNot) {
		this.dkOrNot = dkOrNot;
	}
	public BigDecimal getDkje() {
		return dkje;
	}
	public void setDkje(BigDecimal dkje) {
		this.dkje = dkje;
	}
	
}
