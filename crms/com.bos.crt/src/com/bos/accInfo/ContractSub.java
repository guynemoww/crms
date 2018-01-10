/**
 * 
 */
package com.bos.accInfo;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2015-12-29 15:11:13 合同发起或调整时，操作从合同数据
 */
@Bizlet("")
public class ContractSub {

	// 借新还旧业务，发起合同时，恢复最高额从合同可用额度，恢复一般从合同下所有抵质押物的权利价值
	public void delSubcontract(String contractId) {
		if (null == contractId || "".equals(contractId)) {
			return;
		}
		// 查询主合同下所关联的所有从合同
		DataObject conRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
		conRel.set("contractId", contractId);
		DataObject[] conRels = DatabaseUtil.queryEntitiesByTemplate("default", conRel);
		// 处理业务合同信息
		for (int i = 0; i < conRels.length; i++) {
			DataObject subCon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
			subCon.set("subcontractId", conRels[i].get("subcontractId"));
			DatabaseUtil.expandEntity("default", subCon);
			// 操作业务合同下所关联的从合同信息
			if (("01".equals(subCon.get("subcontractType"))) || ("02".equals(subCon.get("subcontractType")))
					|| ("04".equals(subCon.get("subcontractType")))) {
				if ("1".equals(subCon.get("ifTopSubcon"))) {
					subCon.set("aviAmt", subCon.get("zgbjxe"));
					DatabaseUtil.updateEntity("default", subCon);
				} else {
					DataObject grtRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubGrtRel");
					grtRel.set("subcontractId", subCon.get("subcontractId"));
					DataObject[] grtRels = DatabaseUtil.queryEntitiesByTemplate("default", grtRel);
					for (int j = 0; j < grtRels.length; j++) {
						DataObject grt = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtMortgageBasic");
						grt.set("suretyId", grtRels[j].get("suretyId"));
						DatabaseUtil.expandEntity("default", grt);
						grt.set("aviAmt", grt.get("mortgageValue"));
						DatabaseUtil.updateEntity("default", grt);
					}
				}
			}
		}
	}

	// 业务合同提交时，业务合同所关联的最高额本次担保金额不能为空,返回为空的从合同编号
	public String checkMaxSub(String contractId) {

		if (null == contractId || "".equals(contractId)) {
			return null;
		}
		String msg = "";
		// 查询该合同ID下所有的从合同
		DataObject subRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
		subRel.set("contractId", contractId);
		DataObject[] subRels = DatabaseUtil.queryEntitiesByTemplate("default", subRel);
		// 判断合同下所有关联的担保合同是否合法
		for (int i = 0; i < subRels.length; i++) {
			DataObject subCon = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
			if (subRels[i].get("subcontractId") != null) {
				subCon.set("subcontractId", subRels[i].get("subcontractId"));
				DatabaseUtil.expandEntity("default", subCon);
				// 如果是最高额，并且本次担保金额为空，则将从合同编号取出。
				if (("1".equals(subCon.get("ifTopSubcon"))) && (null == subRels[i].get("suretyAmt") || "".equals(subRels[i].get("suretyAmt")))) {
					System.out.println(subCon.get("subcontractNum"));
					msg += subCon.get("subcontractNum") + ",";
				}
			}
		}
		if (null != msg && !"".equals(msg)) {
			msg = "最高额担保合同:" + msg + "本次担保金额为空";
		}
		return msg;
	}

}
