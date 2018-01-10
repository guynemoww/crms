package com.bos.asset.retransfer;

import com.bos.asset.AssetsUtil;
import com.bos.asset.AssetsUtil.ASSETS_STATUS;
import com.bos.bizApply.BizProcess;
import com.bos.pub.DictContents;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.AssetsTableName;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;

import commonj.sdo.DataObject;

public class RetransferSerciecImp {

	public DataObject createRetransfer(String transferId) {
		int num = DatabaseExt.countByNamedSql(DictContents.DB_NAME_CRMS, "com.bos.asset.retransfer.RetransferSql.retransferCreateValid", transferId);
		if (num > 0) {
			throw new RuntimeException("该移交记录存在在途的逆移交业务，无法继续申请");
		}
		DataObject tas = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_TRANSFER, "id", transferId);
		DataObject retransfer = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_RETRANSFER);
		retransfer.set("transferId", transferId);
		retransfer.set("contractId", tas.get("contractId"));
		retransfer.set("orgNum", GitUtil.getCurrentOrgCd());
		retransfer.set("userNum", GitUtil.getCurrentUserId());
		retransfer.set("updateDate", GitUtil.getBusiDate());
		retransfer.set("status", AssetsUtil.ASSETS_STATUS.START);
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
		try {
			DatabaseUtil.insertEntity(AssetsUtil.getDBName(), retransfer);
			String processId = new BizProcess().createBpsProcessThrowError(retransfer.getString("id"), "asset_retransfer");
			retransfer.set("processId", processId);
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			throw new RuntimeException(e.getMessage());
		}
		return retransfer;
	}

	public void saveRetransfer(DataObject retransfer) {
		DataObject entity = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_RETRANSFER, "id", retransfer.getString("id"));
		if (!AssetsUtil.ASSETS_STATUS.START.value().equals(entity.get("status"))) {
			throw new RuntimeException("非申请状态的业务不允许修改");
		}
		DatabaseUtil.updateEntity(AssetsUtil.getDBName(), retransfer);
	}

}
