/**
 * 
 */
package com.bos.bps.service;

import java.util.List;

import com.eos.system.annotation.Bizlet;
import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFDefinitionQueryManager;
import com.eos.workflow.data.WFActivityDefine;
import com.eos.workflow.data.WFConnector;

/**
 * @author ljf
 * @date 2015-05-06 18:15:15
 *
 */
@Bizlet("定义查询管理类")
public class WFDefinitionQueryManagerService {

	
	
	/**
	 * 获取当前活动图元后的连线集合
	 * @param processDefID 流程定义ID
	 * @param activityID 活动定义ID
	 * @return
	 * @throws Exception
	 */
	public static List<WFConnector> queryNextTransition(long processDefID, String activityID) throws Exception{
		
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFDefinitionQueryManager definitionQueryManager = client.getDefinitionQueryManager(); 
		List<WFConnector> list = definitionQueryManager.queryNextTransition(processDefID, activityID);
		
		return list;
	}
	
	/**
	 * 获取活动定义信息
	 * @param processDefID 流程定义ID
	 * @param activityDefID 活动定义ID
	 * @return
	 * @throws Exception
	 */
	public static WFActivityDefine getActivity(long processDefID, String activityDefID) throws Exception{
		
		WFActivityDefine  act=null;
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient();
		act = client.getDefinitionQueryManager().getActivity(processDefID, activityDefID);

		return act;
	}
}
