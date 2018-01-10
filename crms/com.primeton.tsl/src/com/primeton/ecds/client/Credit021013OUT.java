package com.primeton.ecds.client;

import com.bos.dataset.biz.TbBizPjxxApply;
import com.bos.dataset.biz.TbBizTxxxApply;

import commonj.sdo.DataObject;

public class Credit021013OUT {

	// 分页信息
	public DataObject pageCond;

	// 银承电票信息
	public TbBizPjxxApply[] tbBizPjxxApply;

	// 贴现电票信息
	public TbBizTxxxApply[] tbBizTxxxApply;

	// 返回信息
	public String msg;

	public DataObject getPageCond() {
		return pageCond;
	}

	public void setPageCond(DataObject pageCond) {
		this.pageCond = pageCond;
	}

	public TbBizPjxxApply[] getTbBizPjxxApply() {
		return tbBizPjxxApply;
	}

	public void setTbBizPjxxApply(TbBizPjxxApply[] tbBizPjxxApply) {
		this.tbBizPjxxApply = tbBizPjxxApply;
	}

	public TbBizTxxxApply[] getTbBizTxxxApply() {
		return tbBizTxxxApply;
	}

	public void setTbBizTxxxApply(TbBizTxxxApply[] tbBizTxxxApply) {
		this.tbBizTxxxApply = tbBizTxxxApply;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
