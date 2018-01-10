package com.bos.gjService;

import java.io.Serializable;
/**
 * 个贷服务接口响应对象体
 * @author lenovo
 *
 */
public class WY001ResponseBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 652452734654355490L;
	private String status;//交易响应码
	private String msg;//交易响应消息
	private String bizNum;//批复编号
	private String processId;//流程编号
	private String applyId;//业务申请流水
	private String amountId;//业务申请基本信息流水
	private String applyDetailId;//业务申请明细IDtb_biz_xw_apply
	private String amountDetailId;//业务申请明细信息tb_biz_amount_detail_apply
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getBizNum() {
		return bizNum;
	}
	public void setBizNum(String bizNum) {
		this.bizNum = bizNum;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getAmountId() {
		return amountId;
	}
	public void setAmountId(String amountId) {
		this.amountId = amountId;
	}
	public String getApplyDetailId() {
		return applyDetailId;
	}
	public void setApplyDetailId(String applyDetailId) {
		this.applyDetailId = applyDetailId;
	}
	public String getAmountDetailId() {
		return amountDetailId;
	}
	public void setAmountDetailId(String amountDetailId) {
		this.amountDetailId = amountDetailId;
	}
	
	
}
