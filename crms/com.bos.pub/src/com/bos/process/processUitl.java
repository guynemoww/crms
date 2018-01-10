package com.bos.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.bps.util.IBusiness;
import com.eos.foundation.database.DatabaseExt;
import com.primeton.bfs.tp.common.exception.EOSException;

public class processUitl implements IBIZProcess,IBusiness{

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
	}

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		
	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		
	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		
	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		
	}
	//流程结束后执行方法
	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		String [] xpath= {"bizId"};
		try {
			List<Object> abs=RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			Map<String,String> map=new HashMap<String, String>();
			map.put("rid",abs.get(0).toString());
			map.put("status1","1");
			map.put("status2","2");
			
			DatabaseExt.executeNamedSql("default", "com.bos.pub.decision.updateRuleStatusSetRid", map);
			DatabaseExt.executeNamedSql("default", "com.bos.pub.decision.updateRuleStatusSetRidGetRind", map);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		
	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		return null;
	}
	//系统管理员撤销后执行操作
	public void updateBizDataWhenStopFlow(String processInstId, String bizId) throws EOSException {
		// TODO 自动生成方法存根
		Map<String,String> map=new HashMap<String, String>();
		map.put("rid",bizId);
		map.put("status2","2");
		DatabaseExt.executeNamedSql("default", "com.bos.pub.decision.updateRuleStatusSetRid", map);
	}

}
