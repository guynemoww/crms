package com.bos.conInfo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import com.bos.bizPro.CallBackForEndProcess;
import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.eos.foundation.database.DatabaseExt;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primeton.data.sdo.impl.DataObjectImpl;

import commonj.sdo.DataObject;

public class DelBizGrtRel {
	
	
	 /**
     * 删除业务申请与押品的关联关系
     * @param contractId
     */
    @Bizlet("delBizGrtRel")
    public void delBizGrtRel(String contractId){
    	
    	System.out.println("删除业务申请与押品的关联关系开始...");
    	
    	Map<String,Object> mapContractId = new HashMap<String,Object>();
    	mapContractId.put("contractId",  contractId);
    	
    	Object[] applySuretyObjs = DatabaseExt.queryByNamedSql("default","com.bos.bizApprove.bizApprove.getBizGrtRel", mapContractId);
    	
    	if( applySuretyObjs.length > 0 ){
    		
    		for(int j=0 ; j<applySuretyObjs.length; j++ ){
    			
    			Map<String,Object> map = new HashMap<String,Object>();
    			
				DataObject applySuretyObject = (DataObject) applySuretyObjs[j];
				map.put("cltNo", applySuretyObject.getString("SURETY_NO"));
				map.put("suretyId", applySuretyObject.getString("SURETY_ID"));
    			
        		map.put("applyId",  applySuretyObject.getString("APPLY_ID"));
        		
				//先删除信贷本地的业务申请和押品关系
				DatabaseExt.executeNamedSql("default", "com.bos.grt.grt.delTbBizGrtRel", map);									
				
				//调接口，同步数据
				CollServiceImplServiceServiceStub stub = null;
				try {
					stub = new CollServiceImplServiceServiceStub();
				} catch (AxisFault e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
				
				//System.out.println("押品与业务关联信息删除------applyId="+applyId+"------>开始!");
				ObjectMapper mapper = new ObjectMapper();
				map.put("trans_code", "1114");//接口交易码
				map.put("ope_flag", "delapply");//操作标志
				// Convert object to JSON string  
				String ypxxJsonStr = null;
				try {
					ypxxJsonStr = mapper.writeValueAsString(map);
				} catch (JsonProcessingException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				ser.setIn0(ypxxJsonStr);
				try {
					String flag = stub.collServiceCommInter(ser).getOut1();
					System.out.println("flag:"+flag);
				} catch (RemoteException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
        		
        	}
    			
    	}
    	
    	System.out.println("删除业务申请与押品的关联关系结束!");

    }
	
}
