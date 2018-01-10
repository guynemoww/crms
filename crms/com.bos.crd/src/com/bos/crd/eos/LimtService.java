/**
 * 
 */
package com.bos.crd.eos;

import java.util.Map;

import com.bos.crd.LimitServiceImp;
import com.bos.crd.valid.ChargeLimitValid;
import com.bos.crd.valid.LimitValid;
import com.bos.crd.valid.RiskLimitValid;
import com.bos.pub.DictContents;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-06-15 17:26:57
 * 
 */
@Bizlet
public class LimtService {

	private LimitServiceImp service = new LimitServiceImp();

	private LimitValid chargeValid = new ChargeLimitValid();
	private LimitValid riskValid = new RiskLimitValid();

	@Bizlet
	public String chargeLimitValid(Map<String, Object> param) {
		try {
			chargeValid.valid(param);
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage() == null ? "操作失败" : e.getMessage();
		}
		return null;
	}

	@Bizlet
	public String riskLimitValidByApplyId(String applyId) {
		try {
			Object[] datas = DatabaseExt.queryByNamedSql(DictContents.DB_NAME_CRMS, "com.bos.crd.LimitService.getApplyLimitInfo", applyId);
			for (Object o : datas) {
				Map<String, Object> param = (Map<String, Object>) o;
				for (String key : param.keySet()) {
					if (param.get(key) == null) {
						// 没有数据的字段不检查
						param.put(key, "unsight");
					}
				}
				String msg = riskLimitValid(param);
				if (msg != null) {
					return msg;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage() == null ? "操作失败" : e.getMessage();
		}
		return null;
	}

	@Bizlet
	public String riskLimitValid(Map<String, Object> param) {
		try {
			riskValid.valid(param);
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage() == null ? "操作失败" : e.getMessage();
		}
		return null;
	}

	@Bizlet
	public String saveChargeLimit(DataObject riskLimit) {
		try {
			service.saveChargeLimit(riskLimit);
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage() == null ? "操作失败" : e.getMessage();
		}
		return null;
	}

	@Bizlet
	public String saveRiskLimit(DataObject riskLimit) {
		try {
			service.saveRiskLimit(riskLimit);
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage() == null ? "操作失败" : e.getMessage();
		}
		return null;
	}

	@Bizlet
	public String saveRiskGroup(DataObject group, DataObject[] details) {
		try {
			service.saveRiskGroup(group, details);
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage() == null ? "操作失败" : e.getMessage();
		}
		return null;
	}

	@Bizlet
	public String removeRiskGroup(String groupId) {
		try {
			service.removeRiskGroup(groupId);
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage() == null ? "操作失败" : e.getMessage();
		}
		return null;
	}

	@Bizlet
	public String removeRiskGroupDetail(String[] detailIds) {
		try {
			service.removeRiskGroupDetail(detailIds);
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage() == null ? "操作失败" : e.getMessage();
		}
		return null;
	}

	@Bizlet
	public String removeRiskLimit(String limitId) {
		try {
			service.removeRiskLimit(limitId);
		} catch (Throwable e) {
			e.printStackTrace();
			return e.getMessage() == null ? "操作失败" : e.getMessage();
		}
		return null;
	}
}
