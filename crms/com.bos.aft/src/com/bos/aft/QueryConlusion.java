package com.bos.aft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.util.FlowConstants;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;

@Bizlet("")
public class QueryConlusion {
	public List<DataObject> results=new ArrayList<DataObject>();
	public int flag=1;
	
	 public DataObject getConclusions(String processId,String nextPostNum){
	    	
			//获取流程中的业务数据
	    	
		 			Object[] result = null;
					// 逻辑构件名称             
					String componentName = "com.bos.aft.dailyInspect";
					// 逻辑流名称 
					String operationName = "queryFlowConclusion";
					ILogicComponent logicComponent = LogicComponentFactory
					.create(componentName);
	                //逻辑流的输入参数
					Object[] params = new Object[2];
					params[0] = processId;
					
					//params[0] ="{'param':{'pfId'"+pfId+"}}";
					params[1]=nextPostNum;
					try {
						result= logicComponent.invoke(operationName, params);
						if(null!=result){
							return (DataObject)result[0];
						}else{
							return null;
						}
					} catch (Throwable e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
					return null;
			}
	 
	 public List<DataObject> recursion(String processId,String nextPostNum){
		if(flag==0||processId==null){
			return results;
		} else{
			DataObject result = DataObjectUtil.createDataObject(FlowConstants.WORKITEMINSTANCE_URL);
			result=this.getConclusions(processId, nextPostNum);
			if(result==null){
				flag=0;
			}else{
				nextPostNum=result.getString("postCd");
				results.add(result);
			}
			return recursion(processId,nextPostNum);
		}
	 }
	 @Bizlet("获取当前岗位之前所有岗位的检查意见")
	 public Map<String,Object> getFlowConclusions(String processId,String nextPostNum){
		 List<DataObject> queryList=new ArrayList<DataObject>();
		 queryList=this.recursion(processId, nextPostNum);
		 List<String> concluList=new ArrayList<String>();
		 HashMap<String,Object> concluMap=new HashMap<String,Object>();
		 if(null!=queryList&&queryList.size()>0){
			 for(int i=queryList.size()-1;i>=0;i--){
				 concluList.add(queryList.get(i).getString("opinion"));
			 }
		 }
		 if(null!=concluList&&concluList.size()>0){
			 for(int i=0;i<concluList.size();i++){
				 concluMap.put("opinion"+i, concluList.get(i));
			 }
		 }
		 return concluMap;
	 }
}
