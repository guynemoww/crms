/**
 * 
 */
package com.bos.whitePro;

import java.util.HashMap;

import com.bos.bizApply.IProcessAction;
import com.bos.bizApply.ProcessParam;
import com.bos.bizApply.ProcessType;
import com.bos.pub.entity.EntityUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

/**
 * @author 3231
 * @date 2016-05-24 10:26:44
 * @category 业务申请-一般客户(支行行长): single_biz_apply_brach <br>
 *           业务申请-一般客户: single_biz_apply_manager <br>
 *           业务申请-集团客户: group_cust_biz_apply <br>
 *           业务申请-集团成员: biz_apply_member <br>
 *           出账管理-出账申请: payBiz <br>
 *           合同管理-合同签订: crt_sign <br>
 *           合同管理-合同调整 : contract_tz <br>
 *           合同管理-担保合同调整: contract_grt_tz <br>
 *           合同管理-补充保证金: contract_bcbzj_tz <br>
 *           客户管理-联保小组认定: guar_group_zd <br>
 *           客户管理-集团认定: group_cust_rd <br>
 *           押品管理-押品出库: grt_out <br>
 *           移交管理-业务移交: cust_business_mov <br>
 *           移交管理-客户共享: cust_share <br>
 *           移交管理-客户移交: cust_mov <br>
 *           评级管理-客户评级: simple_cust_grade <br>
 *           贷后变更-利息、手续费调整(客户经理): rate_adjust_manager <br>
 *           贷后变更-利息、手续费调整(支行行长): rate_adjust_brach <br>
 *           贷后变更-方结期(一支客户经理): pay_week_manager <br>
 *           贷后变更-方结期(支行行长): pay_week_brach <br>
 *           贷后变更-约定还款日(客户经理): pay_day_manager <br>
 *           贷后变更-约定还款日(支行行长): pay_day_brach <br>
 *           贷后变更-账号变更(还-指-委)、第三方代偿(客户经理): summary_tz_manager <br>
 *           贷后变更-账号变更(还-指-委)、第三方代偿(支行行长): summary_tz_brach <br>
 *           贷后检查-日常检查: aft_day_check <br>
 *           贷后检查-重点，授信到期检查: aft_important_check <br>
 *           贷后检查-首次检查: aft_first_check <br>
 *           额度管理-客户额度解冻: cust_irm_jd_mccb <br>
 *           额度管理-第三方客户担保额度申请: third_cust_grante_apply <br>
 *           额度管理-第三方客户项目额度申请(支行): third_cust_project_brach <br>
 *           额度管理-第三方客户项目额度申请: third_cust_project_apply <br>
 *           分类-小企业自然人(支行): asset_classify_person_zhff <br>
 *           分类-小企业自然人: asset_classify_person_mgr <br>
 *           分类-非小企业自然人(分行): asset_classify_no_person_fhff <br>
 *           分类-非小企业自然人(支行): asset_classify_no_person_zhff <br>
 *           分类-非小企业自然人: asset_classify_no_person_mgr <br>
 *           分类-非小企业自然人日常分类(分行): asset_classify_no_day_fhff <br>
 *           分类-非小企业自然人日常分类: asset_classify_no_day_mgr <br>
 *           名单管理-加入退出申请(支行贷后跟踪岗): risk_list_dh <br>
 *           名单管理-加入退出申请: risk_list_mgr <br>
 *           预警-预警新增(分行): risk_sign_add_fhdh <br>
 *           预警-预警新增(支行): risk_sign_add_zhdh <br>
 *           预警-预警新增: risk_sign_add_mgr
 */
@Bizlet("")
public class WhiteProcess {
	public static TraceLogger logger = new TraceLogger(WhiteProcess.class);

	public final static String MSG_KEY = "errCode";
	public final static String MSG_TEXT = "errDesc";
	public final static String PROCESS_ID = "processId";

	/**
	 * 以map封装错误信息
	 * 
	 * @param bizId
	 * @param type
	 * @return
	 */

	@Bizlet("")
	public HashMap<String, Object> createBpsProcess(String bizId, String type) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String processId = createBpsProcessThrowError(bizId, type);
			resultMap.put(PROCESS_ID, processId);
		} catch (Throwable e) {
			e.printStackTrace();
			String msg = e.getMessage();
			if (msg == null || (msg = msg.trim()).isEmpty()) {
				msg = "流程创建失败[" + type + "]";
			}
			resultMap.put(MSG_KEY, "BPS_E0001");
			resultMap.put(MSG_TEXT, msg);
		}
		return resultMap;
	}

	/**
	 * 以抛错形式返回错误信息,并强行阻止后续代码执行
	 * 
	 * @param bizId
	 * @param type
	 * @return
	 * @throws Throwable
	 */
	@Bizlet("")
	public String createBpsProcessThrowError(String bizId, String type) throws Throwable {

		if (bizId == null || type == null || (bizId = bizId.trim()).isEmpty() || (type = type.trim()).isEmpty()) {
			logger.info("------3231>------>流程发起时------>bizId为空");
			throw new IllegalArgumentException("需要创建流程的业务编号错误");
		}
		logger.info("------3231>------>流程发起时------>bizId=" + bizId);
		logger.info("------3231>------>流程发起时------>type=" + type);
		IProcessAction processAction = ProcessType.get(type).getAction();
		if (processAction == null) {
			throw new IllegalArgumentException("错误的流程类型，尚未配置相关流程信息!");
		}
		ProcessParam param = processAction.action(bizId);
		if (param.getTemplateName() == null) {
			throw new IllegalArgumentException("没有获取到流程模版名称");
		}
/*		if (null == param.getPartyId() || param.getPartyId().isEmpty()) {
			logger.info("------3231>------>流程发起时------>不存在的partyId");
			throw new IllegalArgumentException("没有获取到正确的客户信息");
		}*/
		if (param.getModelType() == null) {
			param.setModelType(type);
		}
		// 获取流程中的客户信息
		setPartyInfoToRelaMap(param);
		// 发起业务流程
		Object[] objs = param.isBatch() ? createProcessByBatch(param) : createProcess(param);
		if ((String) objs[1] != null || (String) objs[2] != null) {
			throw new RuntimeException((String) objs[1] + "->" + objs[2]);
		}
		return (String) objs[0];
	}

	public Object[] createProcess(ProcessParam param) throws Throwable {
		Object[] params = { param.getTemplateName(), param.getModelType(), param.getRelaMap() };
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.bps.service.WorkFlowService");
		// 发起业务流程
		return logicComponent.invoke("createProcess", params);
	}

	private Object[] createProcessByBatch(ProcessParam param) throws Throwable {
		Object[] params = new Object[7];
		params[0] = param.getUserCode();
		params[1] = param.getUserName();
		params[2] = param.getOrgName();
		params[3] = param.getOrgCode();
		params[4] = param.getTemplateName();
		params[5] = param.getModelType();
		params[6] = param.getRelaMap();
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.bps.service.WorkFlowService");
		return logicComponent.invoke("createProcessByBatch", params);
	}

	private void setPartyInfoToRelaMap(ProcessParam param) {
		if (param.isAbsenceParty()) {
			return;
		}
		//DataObject party = EntityUtil.getEntityById("com.bos.dataset.csm.TbCsmParty", "partyId", param.getPartyId());
		param.putRelaMap("cusName", "白名单");
		param.putRelaMap("custId", "");
	}
}
