package com.bos.riskPro;

import java.util.Map;

import com.bos.bps.util.IBIZProcess;
import com.primeton.bfs.tp.common.exception.EOSException;

/**
 * 流程结束
 * 
 * @author CHJ
 * 
 */
public class CallBackForEndProcess implements IBIZProcess {

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		new CallBackForSubmitProcess().executeAfterCreateFlow(processInstId, workitem);
	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		new CallBackForSubmitProcess().executeBeforeSubmit(processInstId, workitem);

	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		new CallBackForSubmitProcess().executeBeforeIntegration(processInstId, workitem);

	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		new CallBackForSubmitProcess().executeBeforeTerminate(processInstId, workitem);

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		new CallBackForSubmitProcess().executeBeforeUntread(processInstId, workitem);

	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		new CallBackForSubmitProcess().executeBeforeReject(processInstId, workitem);

	}

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		new CallBackForSubmitProcess().executeAfterAbort(processInstId, workitem);
		
	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		return new CallBackForSubmitProcess().executeDataCheck(processInstId, workitem);
	}

}
