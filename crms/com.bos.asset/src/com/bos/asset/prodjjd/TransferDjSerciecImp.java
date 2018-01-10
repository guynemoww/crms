package com.bos.asset.prodjjd;

import com.bos.bizApply.BizProcess;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;

public class TransferDjSerciecImp {

	public String createTransfer(String amountId) {
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
		String processId;
		try {
			processId = new BizProcess().createBpsProcessThrowError(amountId, "asset_transfer");
			tm.commit();
		} catch (Throwable e) {
			tm.rollback();
			throw new RuntimeException(e.getMessage());
		}
		return processId;
	}
}
