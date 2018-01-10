/**
 * 
 */
package com.bos.bps.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bos.bps.util.CommonUtil;
import com.bos.bps.vo.WorkItemWithPageCondVO;
import com.bos.pub.GitUtil;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.das.entity.criteria.ExprType;
import com.eos.das.entity.criteria.OrderbyType;
import com.eos.data.datacontext.IUserObject;
import com.eos.foundation.PageCond;
import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;
import com.eos.system.utility.StringUtil;
import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFWorklistQueryManager;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

/**
 * @author ljf
 * @date 2015-04-13 11:54:30
 *
 */
@Bizlet("工作列表查询管理类")
public class WorkListQueryManagerService {

	
	
	/**
	 * 查询待办，代理，代办，协办工作项列表
	 * @param personID 指定人员ID
	 * @param permission 执行权限 ALL全部，PUBLIC公共，PRIVATE个人
	 * @param activityDefId 岗位ID
	 * @param scope 任务来源 ALL全部，SELF自己，AGENT代理，DELEG代办，HELP协办
	 * @param cusName 客户名称
	 * @param page 翻页对象
	 * @param module 业务模块标志（首页传入）
	 * @return
	 * @throws WFServiceException 
	 * @throws Exception
	 */
	@Bizlet("查询待办，代理，代办，协办工作项列表")
	public  WorkItemWithPageCondVO queryWorkingItemList(String personID,String activityDefId,String module,String cusName,String startTime,String endTime,String permission,String scope,PageCond page) throws WFServiceException{
		WorkItemWithPageCondVO itemVO = new WorkItemWithPageCondVO();
		
		
		//获取转换后的翻页对象
		//com.primeton.workflow.api.PageCond wsPageCond = turnPageCondToWSPageCond(page);
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFWorklistQueryManager worklistQueryManager = client.getWorklistQueryManager();
		//增加查询条件
		CriteriaType criteria = CriteriaType.FACTORY.create();
		criteria.set_entity("com.eos.workflow.data.WFWorkItem");
		
		List<ExprType> exprs = new ArrayList<ExprType>();
		
		if(StringUtil.isNotNull(module)){
			
			ExprType expr1 = ExprType.FACTORY.create();
			expr1.set("processDefName", module);
			expr1.set_op("like");
			expr1.set_likeRule("all");
			
			exprs.add(expr1);
		}
		if(StringUtil.isNotNull(startTime)){
			
			ExprType expr2 = ExprType.FACTORY.create();
			expr2.set("createTime", startTime);
			expr2.set_pattern("yyyy-MM-dd");
			expr2.set_op(">=");
			
			exprs.add(expr2);
		}
		if(StringUtil.isNotNull(endTime)){
			
			ExprType expr3 = ExprType.FACTORY.create();
			expr3.set("createTime", endTime);
			expr3.set_pattern("yyyy-MM-dd");
			expr3.set_op("<=");
			
			exprs.add(expr3);
		}
		
		//获取流程实例集合
		IUserObject user = CommonUtil.getIUserObject();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgcode", user.getAttributes().get("orgcode"));
		if(StringUtil.isNotNull(cusName)){
			map.put("cusName", cusName);
		}
		Object [] arrs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.bps.dataset.bpmNamingSql.queryPressIds", map);
		if(null!=arrs&&arrs.length>0){
			Map<String,String>  temp =  (Map<String,String>)arrs[0];
			String processId = temp.get("PROCESSID");
			ExprType expr4 = ExprType.FACTORY.create();
			expr4.set("processInstID", processId==null?"0":processId);
			expr4.set_op("in");
			
			exprs.add(expr4);
		}
		criteria.set_expr(exprs);
		//创建时间降序排序
		List<OrderbyType> ordrs = new ArrayList<OrderbyType>();
		OrderbyType  ordr = OrderbyType.FACTORY.create();
		ordr.set_property("createTime");
		ordr.set_sort("desc");
		ordrs.add(ordr);
		criteria.set_orderby(ordrs);
		
		//List<WFWorkItem> entrustWorkItems = worklistQueryManager.queryPersonWorkItems(personID, permission, scope, wsPageCond);
		DataObject[]  entrustWorkItems = worklistQueryManager.queryPersonWorkItems4SDO(personID, permission, scope, criteria, page);
		//List<WFWorkItem> entrustWorkItems = worklistQueryManager.queryPersonWorkItemsCriteria(personID, permission,scope, workItem, wsPageCond);
		
		//存放从我的工作进入的查询数据
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		if(null != entrustWorkItems && entrustWorkItems.length>0){
			
			for (int j = 0; j < entrustWorkItems.length; j++) {
				Map<String, Object> temp = organizeWorkItemData(entrustWorkItems[j]);
				list.add(temp);
			}
		}
		itemVO.setList(list);
		itemVO.setCount(page.getCount());
		return itemVO;
	}
	
	
	/***
	 * 组织工作项数据
	 * @param item
	 * @return
	 * @throws WFServiceException 
	 * @throws Exception
	 */
	public static Map<String, Object> organizeWorkItemData(DataObject item) throws WFServiceException{
		Map<String,Object> temp = new HashMap<String,Object>();
		temp.put("processInstId", String.valueOf(item.getLong("processInstID")));
		//流程实例名称：如 测试流程-测试客户
		temp.put("processInstName",item.getString("processInstName"));
		temp.put("processDefId", String.valueOf(item.getLong("processInstID")));
		//流程定义名称：如 com.bos.bps.csm.custMaintain
		String processDefName = item.getString("processDefName");
		temp.put("processDefName",processDefName);
		
		String[] flowTypeCds = processDefName.split("\\.");
		int len=flowTypeCds.length-2;
		String flowTypeCd=flowTypeCds[len];
		//业务类型
		temp.put("flowTypeCd",flowTypeCd);
		//活动定义ID：如 R2001
		temp.put("activityDefId", item.getString("activityDefID"));
		temp.put("activityInstId", String.valueOf(item.getLong("activityInstID")));
		//活动实例名称：如 分行管户岗
		temp.put("activityInstName", item.getString("activityInstName"));
		temp.put("createTime", item.getString("createTime"));
		temp.put("startTime", item.getString("startTime"));
		temp.put("workItemId", String.valueOf(item.getLong("workItemID")));
		temp.put("workItemName", item.getString("workItemName"));//与活动实例名称一样
		/**
		 * 4  WAITING_RECEIVE  待领取
		 * 8  SUSPENDED  挂起 
		 * 10  RUNNING  运行 
		 * 12  COMPLETED  完成
		 * 13  TERMINATED  终止 
		 */
		temp.put("currentState", String.valueOf(item.getLong("currentState")));
		
		//查询相关数据 approveType:为单独给业务申请模块使用
		String[] xpath ={"wfCreateOrgCode","wfCreateOrgName","wfCreateUserId","wfCreateUserName","cusName","bizId","bizType","approveType","wfOtherOrgCode","isWfOtherOrg","wfReadStatus","isConfirmPer","wfBackOperPositionId","custId","authAmt"};
		//实例化相关数据域服务对象
		List<Object> list = RelativeDataManagerService.getRelativeDataBatch(item.getLong("processInstID"), xpath);
		
		
		temp.put("wfCreateOrgCode",list.get(0));
		temp.put("wfCreateOrgName",list.get(1));
		temp.put("wfCreateUserId",list.get(2));
		temp.put("wfCreateUserName",list.get(3));
		temp.put("cusName",list.get(4));
		temp.put("bizId",list.get(5));
		temp.put("bizType", list.get(6));
		temp.put("approveType", list.get(7));
		temp.put("wfOtherOrgCode", list.get(8));
		temp.put("isWfOtherOrg", list.get(9));
		if(null==list.get(10) || "".equals(list.get(10))){
			temp.put("wfReadStatus", "1");
		}else{
			
			temp.put("wfReadStatus", list.get(10));
		}
		temp.put("isConfirmPer", list.get(11));
		temp.put("wfBackOperPositionId", list.get(12));
		temp.put("custId", list.get(13));
		temp.put("authAmt", list.get(14));
		
		return temp;
	}
	
	
	
	/**
	 * 计算各模块待办事项个数
	 * @param personID
	 * @param activityDefId 岗位ID
	 * @param permission
	 * @param scope
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@Bizlet("计算各模块待办事项个数")
	public  List<Map<String,Object>> getWorkingListCount(String personID,String activityDefId,String permission,String scope,PageCond page) throws Exception{
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		IBPSServiceClient client = BPSServiceClientFactory.getDefaultClient(); 
		IWFWorklistQueryManager worklistQueryManager = client.getWorklistQueryManager();
		//增加查询条件
		CriteriaType criteria = CriteriaType.FACTORY.create();
		criteria.set_entity("com.eos.workflow.data.WFWorkItem");
		
		List<ExprType> exprs = new ArrayList<ExprType>();
		
		//获取流程实例集合
		IUserObject user = CommonUtil.getIUserObject();
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("orgcode", user.getAttributes().get("orgcode"));
		Object [] arrs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.bps.dataset.bpmNamingSql.queryPressIds", para);
		if(null!=arrs&&StringUtil.isNotNull(arrs.toString())){
			Map<String,String>  temp =  (Map<String,String>)arrs[0];
			String processId = temp.get("PROCESSID");
			ExprType expr1 = ExprType.FACTORY.create();
			expr1.set("processInstID", processId==null?"0":processId);
			expr1.set_op("in");
			
			exprs.add(expr1);
		}
		criteria.set_expr(exprs);
		
		DataObject[]  entrustWorkItems = worklistQueryManager.queryPersonWorkItems4SDO(personID, permission, scope, criteria, null);
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		for (int i = 0; i < entrustWorkItems.length; i++) {
			DataObject item =entrustWorkItems[i];
			//流程定义名称：如 com.bos.bps.csm.custMaintain
			String proDefName = item.getString("processDefName");
			//根据流程定义名称，获取业务模块标志
			String[] flowTypeCds = proDefName.split("\\.");
			int len=flowTypeCds.length-2;
			String flowTypeCd=flowTypeCds[len];
			if(map.size()!=0&&map.containsKey(flowTypeCd)){
				int map_k_count=(int)map.get(flowTypeCd)+1;
				map.put(flowTypeCd, map_k_count);
			}
			else{
				map.put(flowTypeCd,1);
			}
		}
		Set<String> keys = map.keySet();
		for (String key : keys) {
			int cnt = map.get(key);
			 Map<String,Object> temp = new HashMap<String,Object>();
			 temp.put("flowTypeCd", key);
			 temp.put("cnt", cnt);
			 list.add(temp);
		}
					
		return list;
	}
	
	
	
	/**
	 * 转换翻页对象
	 * @param page
	 * @return
	 */
	public static com.primeton.workflow.api.PageCond turnPageCondToWSPageCond(PageCond page){
		
		com.primeton.workflow.api.PageCond ws = new  com.primeton.workflow.api.PageCond();
		ws.setBegin(page.getBegin());
		ws.setCount(page.getCount());
		ws.setCurrentPage(page.getCurrentPage());
		ws.setIsCount(page.getIsCount());
		ws.setFirst(page.getIsFirst());
		ws.setLast(page.getIsLast());
		ws.setIsCount(page.getIsCount());
		ws.setLength(page.getLength());
		ws.setTotalPage(page.getTotalPage());
		return ws;
	}
}
