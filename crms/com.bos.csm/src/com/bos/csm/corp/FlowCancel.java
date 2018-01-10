package com.bos.csm.corp;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.workflow.commons.utility.StringUtil;

import commonj.sdo.DataObject;

@Bizlet("撤销流程")
public class FlowCancel {
	/**
	 * 类型转换
	 * 
	 * @param dataObject
	 * @param array
	 */
	@Bizlet("撤销流程")
	public static String flowCancel(DataObject data) throws EOSException {
		String msg=null;
		if (null == data.getString("FLAG") || "".equals(data.getString("FLAG")) || null == data.getString("BIZID")
				|| "".equals(data.getString("BIZID"))) {
			msg="业务编号或业务类型为空";
			return msg;
		}
		String bizType = data.getString("FLAG");
		String bizId = data.getString("BIZID");
		if ("评级申请".equals(bizType)) {
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.irm.TbIrmInternalRatingApply");
			con.set("iraApplyId", bizId);
			con.set("ratingState", "06");
			DatabaseUtil.updateEntity("default", con);
		} else if ("客户额度".equals(bizType)) {
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdPartyLimit");
			con.set("limitId", bizId);
			con.set("statusCd", "06");
			DatabaseUtil.updateEntity("default", con);
		} else if ("额度申请".equals(bizType)) {
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.crd.TbCrdThirdPartyLimit");
			con.set("limitId", bizId);
			con.set("statusCd", "06");
			DatabaseUtil.updateEntity("default", con);
		} else if ("业务申请".equals(bizType)) {
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			con.set("applyId", bizId);
			con.set("statusType", "06");
			DatabaseUtil.updateEntity("default", con);
		} else if ("合同申请".equals(bizType)) {
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
			con.set("contractId", bizId);
			con.set("conStatus", "06");
			DatabaseUtil.updateEntity("default", con);
		} else if ("合同协议".equals(bizType)) {
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConCreditInfo");
			con.set("contractId", bizId);
			con.set("conStatus", "06");
			DatabaseUtil.updateEntity("default", con);
		} else if ("出账申请".equals(bizType)) {
			DataObject con = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			con.set("loanId", bizId);
			con.set("loanStatus", "06");
			DatabaseUtil.updateEntity("default", con);
		}
		// else if ("贷后变更".equals(bizType)) {
		// DataObject con =
		// DataObjectUtil.createDataObject("com.bos.dataset.con_cha.TbConLoanChange");
		// con.set("changeId", bizId);
		// con.set("conStatus", "06");
		// DatabaseUtil.updateEntity("default", con);
		// }

		return msg;
	}
}
