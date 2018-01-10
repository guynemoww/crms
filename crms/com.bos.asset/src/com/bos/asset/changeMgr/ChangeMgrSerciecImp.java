package com.bos.asset.changeMgr;

import com.bos.asset.AssetsUtil;
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

public class ChangeMgrSerciecImp {

	public DataObject createChangeMgr(String transferId) {
		int num = DatabaseExt.countByNamedSql(DictContents.DB_NAME_CRMS, "com.bos.asset.changeMgr.ChangeMgrSql.changeMgrCreateValid", transferId);
		if (num > 0) {
			throw new RuntimeException("该移交记录存在在途的管户变更业务，无法继续申请");
		}
		DataObject tas = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_TRANSFER, "id", transferId);
		DataObject changeMgr = DataObjectUtil.createDataObject(AssetsTableName.TB_ASSET_CHANGE_MGR);
		changeMgr.set("transferId", transferId);
		changeMgr.set("orgNum", GitUtil.getCurrentOrgCd());
		changeMgr.set("userNum", GitUtil.getCurrentUserId());
		changeMgr.set("oldOrgNum", tas.get("tasOrgNum"));
		changeMgr.set("oldUserNum", tas.get("tasUserNum"));
		changeMgr.set("status", AssetsUtil.ASSETS_STATUS.START);
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
		try {
			DatabaseUtil.insertEntity(AssetsUtil.getDBName(), changeMgr);
			String processId = new BizProcess().createBpsProcessThrowError(changeMgr.getString("id"), "asset_change_mgr");
			changeMgr.set("processId", processId);
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			throw new RuntimeException(e.getMessage());
		}
		return changeMgr;
	}

	public void saveChangeMgr(DataObject changeMgr) {
		DataObject entity = EntityUtil.getEntityById(AssetsTableName.TB_ASSET_CHANGE_MGR, "id", changeMgr.getString("id"));
		if (!AssetsUtil.ASSETS_STATUS.START.value().equals(entity.get("status"))) {
			throw new RuntimeException("非申请状态的业务不允许修改");
		}
		DatabaseUtil.updateEntity(AssetsUtil.getDBName(), changeMgr);
	}

}
