package com.bos.payback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.plus.LoanCancelControlRq;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.tsl.BaseVO;

import commonj.sdo.DataObject;

public class CallBackForSubmitProcess implements IBIZProcess{
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String loanId=(String)list.get(0);
				if(null==loanId||"".equals(loanId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				DataObject loanInfo = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanInfo");
				loanInfo.set("loanId", loanId);
				DatabaseUtil.expandEntity("default", loanInfo);
				String summaryNum = loanInfo.getString("summaryNum");//借据编号
				String telNo = loanInfo.getString("loanNum");//通知书编号
				//String loanorg = loanInfo.getString("orgNum");//开户机构
				String loanorg = loanInfo.getString("loanOrg");//出账机构
				Map<String,String> map = new HashMap<String,String>();
				map.put("summaryNum", summaryNum);
				Object[] summaryInfo = DatabaseExt.queryByNamedSql(
						GitUtil.DEFAULT_DS_NAME, "com.bos.pay.LoanSummary.querySummaryInfo", map);
				Map maps = (Map) summaryInfo[0];
				String trnOrg = (String) maps.get("ORG_NUM");
				String rcnStan = (String) maps.get("RCN_STAN");
				//调用核算撤销控制信息接口
				LoanCancelControlRq rq = new LoanCancelControlRq();
				BaseVO bvo = new BaseVO();
				rq.setTelNo(telNo);
				rq.setDueNum(summaryNum);
				rq.setRevStan(Long.valueOf(rcnStan));
				bvo.setTranCod("T1210");
				bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
				bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
				bvo.setOpnDep(loanorg);
				bvo.setTrnDep(loanorg);
				bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
				bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
				bvo.setOpr(GitUtil.getCurrentUserId());
				bvo.setOrigFrom("11000");
				bvo.setLegPerCod("9999");
				bvo.setTranFrom("47");
				bvo.setOrigStan(Long.valueOf(rcnStan));
				rq.setBaseVO(bvo);
				Object[] params1 = new Object[1];
				params1[0] = rq;
				ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.pay.LoanSummary");
				Object[] objs = null;
				objs = logicComponent.invoke("allBussBackoutCheck", params1);
				DataObject vo1 = (DataObject)objs[0];
				BaseVO baseVO = (BaseVO)vo1.get("baseVO");
				String returnCode = (String)baseVO.getErrCod();
				if(!"00000".equals(returnCode)){
					throw new EOSException(baseVO.getErrMsg());
				}
				logger.info("------>放款流程撤销提交后,修改状态------bizId="+loanId+"------->begin!");
				map.put("backCd", "08");//审批中
				DatabaseExt.executeNamedSql("default", "com.bos.pay.LoanSummary.updateStatus", map);
				logger.info("------>修改借据撤销状态结束----->");
				} catch (Exception e) {
					e.printStackTrace();
					throw new EOSException(e.getMessage());
				} catch (Throwable e) {
					e.printStackTrace();
					throw new EOSException("流程提交更新业务状态出错！");
				}
	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String loanId=(String)list.get(0);
				if(null==loanId||"".equals(loanId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				DataObject loanInfo = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanInfo");
				loanInfo.set("loanId", loanId);
				DatabaseUtil.expandEntity("default", loanInfo);
				String summaryNum = loanInfo.getString("summaryNum");//借据编号
				Map<String,String> map = new HashMap<String,String>();
				map.put("summaryNum", summaryNum);
				map.put("backCd", "02");//正常
				DatabaseExt.executeNamedSql("default", "com.bos.pay.LoanSummary.updateStatus", map);
			} catch (Exception e) {
				
			}
	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String loanId=(String)list.get(0);
				if(null==loanId||"".equals(loanId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				DataObject loanInfo = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanInfo");
				loanInfo.set("loanId", loanId);
				DatabaseUtil.expandEntity("default", loanInfo);
				String summaryNum = loanInfo.getString("summaryNum");//借据编号
				Map<String,String> map = new HashMap<String,String>();
				map.put("summaryNum", summaryNum);
				map.put("backCd", "02");//正常
				DatabaseExt.executeNamedSql("default", "com.bos.pay.LoanSummary.updateStatus", map);
			} catch (Exception e) {
				
			}

	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		Map<String, String> resultMap = new HashMap<String, String>();
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String summaryId=(String)list.get(0);
				if(null==summaryId||"".equals(summaryId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
					
	logger.info("------>放款流程撤销提交,数据检查-----bizId="+summaryId+"------->begin!");
				DataObject tbLoanSummary = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanSummary");
				tbLoanSummary.set("summaryId", summaryId);
				DatabaseUtil.expandEntity("default", tbLoanSummary);
				String summaryStatusCd = tbLoanSummary.getString("summaryStatusCd");
				String backCd = tbLoanSummary.getString("backCd");
				if("02".equals(summaryStatusCd)){//02--正常
					if("08".equals(backCd)){
						resultMap.put("errorNum", "2");
				    	resultMap.put("errorCode", "2");
				    	resultMap.put("errorContent", "当前借据状态处于审批中，不允许重复提交！");
					}
				}else if("09".equals(summaryStatusCd)){
					resultMap.put("errorNum", "2");
			    	resultMap.put("errorCode", "2");
			    	resultMap.put("errorContent", "当前借据已完成撤销，不能进行重复撤销操作！");
				}else{
					resultMap.put("errorNum", "1");
			    	resultMap.put("errorCode", "");
			    	resultMap.put("errorContent", "");
				}
				} catch (Exception e) {
					e.printStackTrace();
					throw new EOSException(e.getMessage());
				} catch (Throwable e) {
					e.printStackTrace();
					throw new EOSException("流程提交更新业务状态出错！");
				}
		return resultMap;
	}

}
