/**
 * 
 */
package com.bos.csm.corp;

import com.bos.bizApply.BizProcess;
import com.bos.bps.util.FlowUtil.FLOW_STATUS;
import com.bos.pub.GitUtil;
import com.bos.pub.entity.EntityUtil;
import com.bos.pub.entity.name.CsmTableName;
import com.bos.utp.tools.DBUtil;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-06-24 09:39:09
 * 
 */
@Bizlet("CorpService")
public class CorpService {

	@Bizlet("")
	public DataObject createIdentifyScale(String partyId) {
		try {
			return _createIdentifyScale(partyId);
		} catch (Throwable e) {
			e.printStackTrace();
			String msg = e.getMessage();
			DataObject obj = DataObjectUtil.createDataObject(CsmTableName.TB_CSM_CORP_SCALE_IDENTIFY);
			obj.set("msg", msg == null ? "操作失败" : msg);
			return obj;
		}
	}

	@Bizlet("")
	public void saveIdentifyScale(DataObject scale) {
		DatabaseUtil.updateEntity(DBUtil.DB_NAME_DEF, scale);
	}

	private DataObject _createIdentifyScale(String partyId) {
		Object[] obj = DatabaseExt.queryByNamedSql(DBUtil.DB_NAME_DEF, "com.bos.csm.corporation.corporation.corpScaleValidExist", partyId);
		if (obj != null && obj.length > 0 && 0 != (Long) obj[0]) {
			throw new RuntimeException("该客户有在途的企业规模认定流程，无法继续操作");
		}
		DataObject scale = DataObjectUtil.createDataObject(CsmTableName.TB_CSM_CORP_SCALE_IDENTIFY);
		DataObject corp = EntityUtil.getEntityById(CsmTableName.TB_CSM_CORPORATION, "partyId", partyId);
		scale.set("oldScaleCode", corp.getString("bankScaleIdentify"));
		scale.set("partyId", partyId);
		scale.set("orgNum", GitUtil.getCurrentOrgCd());
		scale.set("userNum", GitUtil.getCurrentUserId());
		scale.set("createDate", GitUtil.getBusiDate());
		scale.set("status", FLOW_STATUS.APPLY.value());
		ITransactionManager tm = TransactionManagerFactory.getTransactionManager();
		tm.begin(ITransactionDefinition.PROPAGATION_REQUIRED);
		String msg = null;
		try {
			DatabaseUtil.insertEntity(DBUtil.DB_NAME_DEF, scale);
			String processId = new BizProcess().createBpsProcessThrowError(scale.getString("id"), "crm_corp_scale_identify");
			scale.set("processId", processId);
			tm.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tm.rollback();
			scale.set("msg", (msg = e.getMessage()) == null ? "操作失败" : msg);
		}
		return scale;
	}
}
