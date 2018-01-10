package com.bos.grtPro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

public class CallBackForSubmitValueProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitValueProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	//用于创建流程成功后更新业务表数据 审批状态 XD_SXCD8003
		public void executeBeforeSubmit(String processInstId, Map workitem)
				throws EOSException {
			// TODO 自动生成的方法存根
			String[] xpath={"bizId"};//获取相关数据的数组
			try{
				List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取贷款id
				String outId=(String)list.get(0);
					if(null==outId||"".equals(outId)){
						logger.info("流程返回的业务ID为空！");
						throw new EOSException("流程返回的业务ID为空");
					}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("bizId", outId);//押品编号
				map.put("state", "02");
				map.put("user", GitUtil.getCurrentUserId()); //--
				map.put("org", GitUtil.getCurrentOrgId()); //--
				logger.info("------------>押品价值审核流程提交，开始更新业务状态------outId="+outId+"------->开始!");
				DatabaseExt.executeNamedSql("default", 
						"com.bos.grt.grt.insertValueApprove", map);
				logger.info("------------>押品价值审核流程提交，开始更新业务状态------outId="+outId+"------>结束!");
			}catch(Exception e){
				e.printStackTrace();
				throw new EOSException("------------>押品价值审核流程提交时修改业务状态出错！");
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
		// TODO 自动生成的方法存根

	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		//撤销流程  业务状态更新为06
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("撤销流程时，流程返回的业务ID为空！");
					throw new EOSException("撤销流程时，流程返回的业务ID为空");
				}
			Map<String,Object> map = new HashMap<String,Object>();
			//modi by shangmf:20171106:修改参数inId->bizId,避免价值审核提交，退回后再撤销，后续无法再进行价值审核
			//删除：map.put("inId", outId);
			map.put("bizId", outId);
			map.put("state", "06");
			map.put("user", GitUtil.getCurrentUserId()); //--
			map.put("org", GitUtil.getCurrentOrgId()); //--
	logger.info("------------>押品价值审核撤销流程时，开始更新业务状态------outId="+outId+"------->开始!");
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateValueApprove", map);
	logger.info("------------>押品价值审核撤销流程时，开始更新业务状态------outId="+outId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------------>押品出库撤销流程时修改业务状态出错！");
		} 
	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

}
