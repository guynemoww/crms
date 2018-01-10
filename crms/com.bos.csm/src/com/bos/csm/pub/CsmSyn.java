package com.bos.csm.pub;

import java.util.Map;

import com.bos.bps.util.CommonUtil;
import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.eos.system.annotation.Bizlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.eos.data.datacontext.IUserObject;
import com.eos.engine.component.ILogicComponent;

@Bizlet
public class CsmSyn {

	/**
	 * 将客户信息同步至押品系统
	 * @param map
	 * @return
	 */
	@Bizlet
	public String csmSynForColl(Map ypxxMap){
		String flag = "";
		try{ 
			//调接口，同步数据
			CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
			CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
			
			ObjectMapper mapper = new ObjectMapper();
			ypxxMap.put("trans_code","1101");//客户信息同步接口交易码 
			ypxxMap.put("ope_flag","0");
			//add by shangmf:多法人改造，传入userId和orgcode
			IUserObject user = CommonUtil.getIUserObject();
			String userid = user.getUserId();
			String orgId = user.getUserOrgId();
			
			// 逻辑构件名称 :com.bos.csm.callback.moveBusiness           
			String componentName = "com.bos.aft.util";
			// 逻辑流名称 
			String operationName = "getOrgCode";
			ILogicComponent logicComponent = LogicComponentFactory
			.create(componentName);
			//逻辑流的输入参数
			Object[] params = new Object[1];
			params[0] = orgId;
			Object[] orgCode = null;
			try {
				orgCode = logicComponent.invoke(operationName, params);
			} catch (Throwable e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			String orgCodeStr = (String) orgCode[0];
			ypxxMap.put("inputuserid", userid);
			ypxxMap.put("inputorgcode", orgCodeStr);
			
			// Convert object to JSON string  
			String ypxxJsonStr = null;
			ypxxJsonStr = mapper.writeValueAsString(ypxxMap);
			ser.setIn0(ypxxJsonStr);
			flag = stub.collServiceCommInter(ser).getOut1();
		}catch(Exception e){
			e.printStackTrace();
			flag = "fail";
		}
		return flag;
	}
}
