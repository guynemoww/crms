package com.primeton.crmsgj;

import java.io.Serializable;
import java.util.List;

/**
 * 牌价查询接口---响应对象体
 * @author shendl
 *
 */
public class GJS01501010000010ResBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7841437695319373637L;
	private String effDate;//生效日期
	private String recNum;//记录条数
	private String transStat;//交易状态
	private String errMsg;//错误消息
	private List<GJS01501010000010ResStubBody> gjS01501030000010ResStubBodyList;
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getRecNum() {
		return recNum;
	}
	public void setRecNum(String recNum) {
		this.recNum = recNum;
	}
	public String getTransStat() {
		return transStat;
	}
	public void setTransStat(String transStat) {
		this.transStat = transStat;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public List<GJS01501010000010ResStubBody> getGjS01501030000010ResStubBodyList() {
		return gjS01501030000010ResStubBodyList;
	}
	public void setGjS01501030000010ResStubBodyList(
			List<GJS01501010000010ResStubBody> gjS01501030000010ResStubBodyList) {
		this.gjS01501030000010ResStubBodyList = gjS01501030000010ResStubBodyList;
	}
	@Override
	public String toString() {
		return "GJS01501010000010ResBody [effDate=" + effDate + ", recNum="
				+ recNum + ", transStat=" + transStat + ", errMsg=" + errMsg
				+ ", gjS01501030000010ResStubBodyList="
				+ gjS01501030000010ResStubBodyList + "]";
	}
}
