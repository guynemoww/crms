package com.primeton.p2p;

import java.util.Map;

import com.bos.gjService.ZX001Request;
import com.bos.gjService.ZX001Response;

import commonj.sdo.DataObject;

public interface Ip2pCredit {
	/**
	 * 网贷查询征信查询
	 * @return
	 */
	public void p2pCreditReport(String applyId ,String ecifPartyNum,String partyId) throws Exception;
	/**
	 * 调征信接口之前向中间表插入数据
	 * @return
	 */
	public DataObject saveApplyMiddle(DataObject object) throws Exception;
	/**
	 * 网贷查询征信查询 定时器
	 * @return
	 */
	public void p2pCreditReportEsq() throws Exception;
	/**
	 *网贷调取CRMS服务
	 * @return
	 */
	public ZX001Response crmsTop2pCreditReport(ZX001Request request) throws Exception;
}
