/**
 * 
 */
package com.bos.bizApply;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.eos.foundation.database.DatabaseExt;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.logging.Logger;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

/**
 * @author 提供给押品系统：根据申请ID查询当前流程岗位
 *
 */
@Remotable
public class QueryBizApplyStatus {

	protected Logger LOG = TraceLoggerFactory.getContributionTraceLogger("com.bob.bcms.comm", "log4j-contribution.xml");
	
	public String queryBizApplyStatus(String applyId){
		System.out.println("传入申请号applyId:"+applyId);
		String statusOrg = "";
		try {
			Map map = new HashMap();
			map.put("applyId", applyId);
			Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.bizApply.bizApply.getApplyStatus", map);
			//creditAmt = (BigDecimal) objs[0];
			if(objs != null && objs.length>0){
				DataObject bizDataObject = (DataObject) objs[0];//
				statusOrg = bizDataObject.getString("POST_CD");//P1001- 业务受理岗
			}
		System.out.println("通过申请号(业务主键ID)查询后得到当前岗位节点为:"+statusOrg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("通过申请号查询后得到审批状态报错！");
		}
		return statusOrg;
	}
}
