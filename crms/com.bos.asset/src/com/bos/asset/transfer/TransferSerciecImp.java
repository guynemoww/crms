package com.bos.asset.transfer;

import com.bos.asset.AssetsUtil;
import com.bos.bizApply.BizProcess;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.ConTableName;
import com.bos.pub.entity.name.AssetsTableName;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;

import commonj.sdo.DataObject;

public class TransferSerciecImp {

	public DataObject createTransfer(String contractId) {
		int num = DatabaseExt.countByNamedSql(DictContents.DB_NAME_CRMS, "com.bos.asset.transfer.TransferSql.transferCreateValid", contractId);
		if (num > 0) {
			throw new RuntimeException("该合同存在在途的移交业务，或者已经移交，无法继续申请");
		}
		DataObject contract = EntityUtil.getEntityById(ConTableName.TB_CON_CONTRACT_INFO, "contractId", contractId);
		DataObject transfer = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_TRANSFER);
		transfer.set("transferNum", GitUtil.getSeqNo(AssetsUtil.TRANSFER_SEQ_NAME, GitUtil.getCurrentOrgCd()));
		transfer.set("contractId", contract.get("contractId"));
		transfer.set("conOrgNum", contract.get("orgNum"));
		transfer.set("conUserNum", contract.get("userNum"));
		transfer.set("orgNum", GitUtil.getCurrentOrgCd());
		transfer.set("userNum", GitUtil.getCurrentUserId());
		transfer.set("updateDate", GitUtil.getBusiDate());
		transfer.set("status", AssetsUtil.ASSETS_STATUS.START);
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
		try {
			DatabaseUtil.insertEntity(AssetsUtil.getDBName(), transfer);
			String processId = new BizProcess().createBpsProcessThrowError(transfer.getString("id"), "asset_transfer");
			transfer.set("processId", processId);
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			throw new RuntimeException(e.getMessage());
		}
		return transfer;
	}

	public void saveTransfer(DataObject transfer) {
		DataObject entity = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_TRANSFER, "id", transfer.getString("id"));
		if (!AssetsUtil.ASSETS_STATUS.START.value().equals(entity.get("status"))) {
			throw new RuntimeException("非申请状态的业务不允许修改");
		}
		DatabaseUtil.updateEntity(AssetsUtil.getDBName(), transfer);
	}

}
