package com.bos.crtPro;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.biz.SaveBizInfo;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.conInfo.SubProcess;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;
/**
 * 回调逻辑：结束流程，更新业务表数据
 *  01-未提交;
	02-审批中;
	03-结束;
	04-已删除
 * 
 * */
public class CallBackForEndProcessDbjg implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndProcessDbjg.class);
	
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	/**
	* 
	* @Title: executeBeforeSubmit
	* @Description: TODO(用于审批同意流程提交前业务逻辑)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	*/
	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	/**
	 * 同意
	 * */
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		String[] xpath={"bizId"};//获取相关数据的数组
		String conclusion = (String) workitem.get("conclusion");//结论
		try {
			
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String contractId=(String)list.get(0);
			if(null==contractId||"".equals(contractId)){
				logger.info("------3231------>专业担保机构合作协议申请ID为空！");
				throw new EOSException("------3231------>专业担保机构合作协议ID为空！");
			}
			DataObject con  = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGuarantOrgInfo");
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			if(con.get("partyId")==null || "".equals(con.get("partyId"))){
				throw new EOSException("------3231------>专业担保机构合作协议party_ID为空！");
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("partyId", (String)con.get("partyId"));
			//将原生效的置为失效
			DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateDbxyByPartyId", map);
			
			con.set("statusCd", "03");
			Date date = GitUtil.getBusiDate();
			con.set("updateTime", date);
			DatabaseUtil.updateEntity("default", con);
			
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("专业担保机构合作协议同意流程更新业务状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("专业担保机构合作协议同意流程更新业务状态出错");
		}
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	/**
	 * 否决
	 * */
	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		//流程否决   业务状态更新为06
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String contractId=(String)list.get(0);
			if(null==contractId||"".equals(contractId)){
				logger.info("专业担保机构合作协议流程否决时，bizID为空！");
				throw new EOSException("专业担保机构合作协议否决时，bizID为空！");
			}
			
			//将主表状态置为已删除
			DataObject con  = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGuarantOrgInfo");
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			con.set("contractId", contractId);
			con.set("statusCd", "06");
			Date date = GitUtil.getBusiDate();
			con.set("updateTime", date);
			DatabaseUtil.updateEntity("default", con);
			
			//删除账户信息
			DataObject zh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConZh");
			zh.set("contractId", contractId);
			DataObject[] tbConZhs = DatabaseUtil.queryEntitiesByTemplate("default", zh);
			if(tbConZhs==null || tbConZhs.length==0){
				//无账户信息
			}else{
				DatabaseUtil.deleteEntityBatch("default", tbConZhs);
			}
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>专业担保机构合作协议否决状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>专业担保机构合作协议否决状态出错！");
		}
	}
	/**
	 * 撤销
	 * */
	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		//撤销流程  业务状态更新为06
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String contractId=(String)list.get(0);
			if(null==contractId||"".equals(contractId)){
				logger.info("专业担保机构合作协议流程否决时，bizID为空！");
				throw new EOSException("专业担保机构合作协议否决时，bizID为空！");
			}
			
			//将主表状态置为已删除
			DataObject con  = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConGuarantOrgInfo");
			con.set("contractId", contractId);
			DatabaseUtil.expandEntity("default", con);
			con.set("contractId", contractId);
			con.set("statusCd", "06");
			Date date = GitUtil.getBusiDate();
			con.set("updateTime", date);
			DatabaseUtil.updateEntity("default", con);
			
			//删除账户信息
			DataObject zh = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConZh");
			zh.set("contractId", contractId);
			DataObject[] tbConZhs = DatabaseUtil.queryEntitiesByTemplate("default", zh);
			if(tbConZhs==null || tbConZhs.length==0){
				//无账户信息
			}else{
				DatabaseUtil.deleteEntityBatch("default", tbConZhs);
			}
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>专业担保机构合作协议流程提交状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>专业担保机构合作协议流程提交更新业务状态出错！");
		}
	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("errorNum", "1");
    	resultMap.put("errorCode", "");
    	resultMap.put("errorContent", "");
		return resultMap;
	}
}
