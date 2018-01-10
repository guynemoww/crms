/**
 * 
 */
package com.bos.bps.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eos.system.annotation.Bizlet;
import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFRelativeDataManager;
import com.eos.workflow.omservice.WFParticipant;
import com.primeton.workflow.api.WFServiceException;

/**
 * @author ljf
 * @date 2015-04-13 11:56:46
 *
 */
@Bizlet("相关数据域操作类")
public class RelativeDataManagerService {

	
	/**
	 * 获取相关数据域信息
	 * @param processId
	 * @param xpath
	 * @throws WFServiceException
	 */
	@Bizlet("获取相关数据域信息")
	public static List<Object> getRelativeDataBatch(long processInstID,String [] xpath) throws WFServiceException{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFRelativeDataManager relativeDataManager = client.getRelativeDataManager(); 
		List<Object> list = relativeDataManager.getRelativeDataBatch(processInstID, xpath);
		return list;
	}
	
	/**
	 * 设置相关数据域信息
	 * @param processInstID
	 * @param map
	 * @throws WFServiceException
	 */
	@Bizlet("设置相关数据域信息")
	public static void setRelativeDataBatch(long processInstID,Map<String,Object> map) throws WFServiceException{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFRelativeDataManager relativeDataManager = client.getRelativeDataManager(); 
		relativeDataManager.setRelativeDataBatch(processInstID, map); 
		
	}
	
	/**
	 * 批量设置相关数据中指定路径下的参与人
	 * @param processInstID
	 * @param xpath
	 * @return
	 * @throws Exception
	 */
	@Bizlet("批量设置相关数据中指定路径下的参与人")
	public static void setRelativeDataForParticipant(long processInstID,String xpath,WFParticipant[] bpsParticipant) throws Exception{
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFRelativeDataManager relativeDataManager = client.getRelativeDataManager(); 
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(xpath, bpsParticipant);
		relativeDataManager.setRelativeDataBatch(processInstID, map);
		
	}
	
}
