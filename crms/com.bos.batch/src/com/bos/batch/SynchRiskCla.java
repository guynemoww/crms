/**
 * 
 */
package com.bos.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bos.bps.dao.ProcessInstanceDAO;
import com.bos.bps.dao.WorkItemInstanceDAO;
import com.bos.bps.service.ProcessInstManagerService;
import com.bos.bps.util.FlowUtil;
import com.bos.pub.DateStyle;
import com.bos.pub.DateUtil;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author kf_xdxt11
 * @date 2016-07-18 14:52:55
 *
 */
@Bizlet("五级减值计量同步")
public class SynchRiskCla {

	/**
	 * @param summaryNum
	 * @author kf_xdxt11
	 */
	@Bizlet("同步")
	public static void synch(String summaryNum) {
		
		HashMap<String, String> para = new HashMap<String, String>();
		para.put("summaryNum", summaryNum);
		Object[] riskClaInfos = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.synchRiskCla.queryRiskCla", para);

		if (riskClaInfos != null && riskClaInfos.length > 0) {
			for (int i = 0; i < riskClaInfos.length; i++) {
				DataObject riskClaInfo = (DataObject) riskClaInfos[i];

				Date workdate = GitUtil.getBusiDate();// 工作日期
				Date worktime = GitUtil.getBusiTimestamp();// 工作时间
				
				Map<String, Object> parameterObject = new HashMap<String, Object>();
				
				String rcv_date = DateUtil.DateToString(DateUtil.getLastDateOfMonth(workdate), DateStyle.YYYY_MM_DD_8L);

				parameterObject.put("due_num", riskClaInfo.getString("SUMMARY_NUM")); // 借据编号
				parameterObject.put("rcv_date", rcv_date); // 登记日期
				parameterObject.put("leg_per_cod", "3600");// 法人代码
				int count = DatabaseExt.countByNamedSql("sdp", "com.bos.batch.synchRiskCla.countRiskCla", parameterObject);
				if (count == 0) {
					// parameterObject.put("uuid","");
					parameterObject.put("prv_cod", riskClaInfo.getString("PRV_COD")); // 区域代码
					parameterObject.put("opn_dep", riskClaInfo.getString("OPN_DEP")); // 开户机构
					parameterObject.put("tal_dep", riskClaInfo.getString("TAL_DEP")); // 核算机构
					parameterObject.put("five_flg", riskClaInfo.getString("FIVE_FLG")); // 五级分类结果
					parameterObject.put("five_propt", riskClaInfo.getString("FIVE_PROPT")); // 减值比例
					parameterObject.put("create_time", worktime);// 创建时间
					parameterObject.put("update_time", worktime);// 更新时间
					parameterObject.put("trunc_no", 1); // 乐观锁

					DatabaseExt.executeNamedSql("sdp", "com.bos.batch.synchRiskCla.insertRiskCla", parameterObject);
				}
			}
		}
	}
	
	
	
	/**
	 * @author zxh
	 */
	@Bizlet("处理手工风险分类备案流程")
	public static void delwithHandRisk() {
		
		HashMap<String, String> para = new HashMap<String, String>();
		Object[] riskClaInfos = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.batch.synchRiskCla.queryHandRiskCla", para);
		String acApplyId = "";//申请id
		String processId = "";//实例编号
		if (riskClaInfos != null && riskClaInfos.length > 0) {
			for (int i = 0; i < riskClaInfos.length; i++) {
				
				DataObject riskClaInfo = (DataObject) riskClaInfos[i];
				acApplyId = riskClaInfo.getString("AC_APPLY_ID");
				processId = riskClaInfo.getString("PROCESS_ID");
				HashMap parameter = new HashMap();
				parameter.put("acApplyId", acApplyId);
				parameter.put("status", "3");//审批拒绝完成
				//更新审批结果
				DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateApproveResult", parameter);
				//更新审批状态
				DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateClaApplyStatus", parameter);
				parameter.put("status", "0");
				//恢复初始
				DatabaseExt.executeNamedSql("default", "com.bos.risk.startProcessLogic.updateClaDebtStatus", parameter);
				//处理流程
				try {
					Map<String, String> it = FlowUtil.getNodeConfigInfo(processId);
					// 当真正结束时，无任何下一岗信息
					WorkItemInstanceDAO.updateWorkItemInstanceByReject(it);
					// 更新流程实例
					ProcessInstanceDAO.updateProcessInstanceStatus(it);
					// 结束流程
					ProcessInstManagerService.terminateProcessInstance(processId);
				} catch (Exception e) {
				}
				
			}
		}
	}
	
	
}
