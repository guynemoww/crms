package com.bos.bizPro;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.biz.SaveBizInfo;
import com.bos.bizApply.GroupInfo;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;
/**
 * 回调逻辑：结束流程，更新业务表数据
 *  01-未提交;
	02-审批中;
	03-结束;
	04-已删除
 * 
 * */
public class CallBackForEndProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);
	
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	/**
	* 
	* @Title: executeBeforeSubmit
	* @Description: TODO(用于审批同意流程提交前业务逻辑)
	* @param  processInstId 流程实例ID号
	* @return void     返回类型
	* @throws
	*/
	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	/**
	 * 同意方法
	 * */
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		String[] xpath={"bizId"};//获取相关数据的数组
		String conclusion = (String) workitem.get("conclusion");//结论
		try {
			
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String applyId=(String)list.get(0);
			if(null==applyId||"".equals(applyId)){
				logger.info("业务申请流程结束ID为空！");
				throw new EOSException("业务申请流程结束ID为空");
			}
			
		logger.info("业务申请流程结束，回掉逻辑开始------bizId="+applyId+"------->开始!");
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApply);
			String partyId = bizApply.getString("partyId");
			String bizType = bizApply.getString("bizType");
			//查询品种明细金额
			HashMap map = new HashMap();
			map.put("applyId", applyId);
			map.put("partyId", partyId);
			BigDecimal creditAmt = new BigDecimal("0"); 
			if("03".equals(bizType)){
				GroupInfo gi = new GroupInfo();
				creditAmt = gi.getGroupCredit(partyId, "1");
			}else {
				Object[] objs = DatabaseExt.queryByNamedSql("default", "com.bos.bizInfo.bizInfo.getAmountAmt", map);
				creditAmt = (BigDecimal) objs[0];
			}
			
			//查询基本信息
			DataObject bizInfo = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizAmountApply");
			bizInfo.set("applyId", applyId);
			DatabaseUtil.expandEntityByTemplate("default", bizInfo,bizInfo);
			
			bizInfo.set("creditAmount",creditAmt);
			DatabaseUtil.updateEntity("default", bizInfo);
			
			//迁移数据
			SaveBizInfo sbi = new SaveBizInfo();
			sbi.saveApplyToApprove(applyId,conclusion);
			
			//综合授信批复调整
			if("02".equals(bizApply.getString("bizType")) && "04".equals(bizApply.getString("bizHappenType"))){
				int count =0;
				//查询综合授信协议
				DataObject creditInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConCreditInfo");
				DataObject conRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
				creditInfo.set("applyId", applyId);
				creditInfo.set("conStatus", "03");
				DatabaseUtil.expandEntityByTemplate("default",creditInfo, creditInfo);
				if(null != creditInfo.get("contractId")){
					conRel.set("contractId", creditInfo.get("contractId"));
					count = DatabaseUtil.countByTemplate("default", conRel);
				}
				if(count==0){
					//不是抵押、质押担保的综合授信批复调整后需要再次自动创建授信协议
					Object[] result = null;
					ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.conApply.conApply");
					// 逻辑流的输入参数
					Object[] params = new Object[1];
					params[0] = applyId;
					result = logicComponent.invoke("createSxxy", params);
					if (result != null && result.length > 0) {
						if (null != result[0] && !"".equals(result[0].toString())) {
							throw new EOSException(result[0].toString());
						}
					}
				}
				
			}
			//额度重算-----补足保证金----
			
			Map map2 = new HashMap();
			map2.put("partyId", bizApply.get("partyId"));
			DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
			
			//统一授信客户需要重新计算成员的额度
			if("03".equals(bizApply.getString("bizType"))){
				Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.bizInfo.bizInfo.getAllMember", map);
				for(int i=0;i<objs.length;i++){
					String memberid = (String)objs[i];
					map2.put("partyId", memberid);
					DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
				}
			}
			
		logger.info("业务申请流程结束，回掉逻辑结束------bizId="+applyId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	/**
	 * 否决
	 * */
	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		String[] xpath={"bizId"};//获取相关数据的数组
		String conclusion = (String) workitem.get("conclusion");//结论
		try {
			
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String applyId=(String)list.get(0);
			if(null==applyId||"".equals(applyId)){
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}
			
		logger.info("业务申请流程结束，回掉逻辑开始,否决------bizId="+applyId+"------->开始!");	
			DataObject bizApprove = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApprove");
			bizApprove.set("applyId", applyId);
			DatabaseUtil.expandEntityByTemplate("default", bizApprove, bizApprove);
			if(null != bizApprove.get("approveConclusion")){
				if(!(bizApprove.get("approveConclusion")).equals(conclusion)){
					if("2".equals(bizApprove.get("conclusion"))){
						//修改结论
						bizApprove.set("approveConclusion", conclusion);
					}
					if("3".equals(bizApprove.get("conclusion"))&&(!"2".equals(bizApprove.get("approveConclusion")))){
						//修改结论
						bizApprove.set("approveConclusion", conclusion);
					}
					DatabaseUtil.updateEntity("default", bizApprove);
				}
			}else{
				//迁移数据
				SaveBizInfo sbi = new SaveBizInfo();
				sbi.saveApplyToApprove(applyId,conclusion);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("applyId",  applyId);

			//查询抵质押的押品编号。如果存在押品编号，则与押品交互，删除业务押品关系
			Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.bizApprove.bizApprove.getSXMortgageBasic", map);
			if(objs.length>0){
				
				//modi by shangmf:业务申请可能关联多笔押品，需要循环，且需要删除信贷本身的关联关系tb_biz_grt_rel
				for(int i=0 ; i<objs.length; i++ ){
					
					//DataObject object = (DataObject) objs[0];
					DataObject object = (DataObject) objs[i];
					map.put("cltNo", object.getString("SURETY_NO"));
					map.put("suretyId", object.getString("SURETY_ID"));
					//先删除信贷本地的业务申请和押品关系
					DatabaseExt.executeNamedSql("default", "com.bos.grt.grt.delTbBizGrtRel", map);									
					
					//调接口，同步数据
					CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
					CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
					
					logger.info("押品与业务关联信息删除------applyId="+applyId+"------>开始!");
					ObjectMapper mapper = new ObjectMapper();
					map.put("trans_code", "1114");//接口交易码
					map.put("ope_flag", "delapply");//操作标志
					// Convert object to JSON string  
					String ypxxJsonStr = null;
					ypxxJsonStr = mapper.writeValueAsString(map);
					ser.setIn0(ypxxJsonStr);
					String flag = stub.collServiceCommInter(ser).getOut1();
					
				}
					
				
				logger.info("押品与业务关联信息删除------applyId="+applyId+"------>结束!");
			}
			
			logger.info("------3231------>删除贴现票据信息------>开始！ bizId=" + applyId);
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApply);
			String productType = bizApply.getString("productType");
			
			if ("01006001".equals(productType) || "01006002".equals(productType) || "01006010".equals(productType)) {
			 Object[] objs1 = DatabaseExt.queryByNamedSql("default", "com.bos.bizApply.bizApply.getCountByApplyId", map);
				if (objs1.length == 0) {//objs1.length == 0允许删除
					Object[] objs2 = DatabaseExt.queryByNamedSql("default", "com.bos.bizApply.bizApply.getAmountDetailId", map);
					if (objs2.length >0 ) {
						for (int i = 0; i < objs2.length; i++) {
							 String amountDetailId = (String) objs2[i];
							 DataObject tbBizTxxxApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizTxxxApply");
							 tbBizTxxxApply.set("amountDetailId", amountDetailId);
							 DataObject[] tbBizTxxxApplys = DatabaseUtil.queryEntitiesByTemplate("default", tbBizTxxxApply);
							 if(tbBizTxxxApplys.length>0){
								 DatabaseUtil.deleteEntityBatch("default", tbBizTxxxApplys);
							 }
						}
					}
				}
			}
			 logger.info("------3231------>删除贴现票据信息------>结束！");
			
			
		logger.info("业务申请流程结束，回掉逻辑结束,否决------bizId="+applyId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}
	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		logger.info("------3231------>业务申请撤销流程------>begin！");
			String[] xpath={"bizId"};//获取相关数据的数组
			List<Object> list;
			try {
				list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取贷款id
				String applyId=(String)list.get(0);
					if(null==applyId||"".equals(applyId)){
						logger.info("业务申请撤销流程ID为空！");
						throw new EOSException("业务申请撤销流程ID为空！");
					}
		logger.info("------3231------>业务申请撤销流程------>bizId="+applyId);
				DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
				bizApply.set("applyId", applyId);
				bizApply.set("oldApplyId",null);
				bizApply.set("statusType", "06");
				DatabaseUtil.updateEntity("default", bizApply);
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("applyId",  applyId);

				//查询抵质押的押品编号。如果存在押品编号，则与押品交互，删除业务押品关系
				Object[] objs = DatabaseExt.queryByNamedSql("default","com.bos.bizApprove.bizApprove.getSXMortgageBasic", map);
				if(objs.length>0){
					
					//modi by shangmf:业务申请可能关联多笔押品，需要循环，且需要删除信贷本身的关联关系tb_biz_grt_rel
					for(int i=0 ; i<objs.length; i++ ){
						
						//DataObject object = (DataObject) objs[0];
						DataObject object = (DataObject) objs[i];
						map.put("cltNo", object.getString("SURETY_NO"));
						map.put("suretyId", object.getString("SURETY_ID"));
						//先删除信贷本地的业务申请和押品关系
						DatabaseExt.executeNamedSql("default", "com.bos.grt.grt.delTbBizGrtRel", map);									
						
						//调接口，同步数据
						CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
						CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
						
						logger.info("押品与业务关联信息删除------applyId="+applyId+"------>开始!");
						ObjectMapper mapper = new ObjectMapper();
						map.put("trans_code", "1114");//接口交易码
						map.put("ope_flag", "delapply");//操作标志
						// Convert object to JSON string  
						String ypxxJsonStr = null;
						ypxxJsonStr = mapper.writeValueAsString(map);
						ser.setIn0(ypxxJsonStr);
						String flag = stub.collServiceCommInter(ser).getOut1();
						
					}
						
					
					logger.info("押品与业务关联信息删除------applyId="+applyId+"------>结束!");
				}
		logger.info("------3231------>业务申请撤销流程------>end！");		
			} catch (WFServiceException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		try {
			String[] xpath={"bizId"};//获取相关数据的数组
			String conclusion = (String) workitem.get("conclusion");//结论
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			String applyId=(String)list.get(0);
			HashMap resultMap = new HashMap();
			//不同意、退回、补充材料不往下走
			if(("99").equals(conclusion)||"2".equals(conclusion)||"98".equals(conclusion)){
	        	return null;
			}
			
			//查询是否委托贷款
			DataObject bizApply = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizApply");
			bizApply.set("applyId", applyId);
			DatabaseUtil.expandEntity("default", bizApply);
			String partyId = (String)bizApply.get("partyId");
			//规则校验
			RuleService rs = new RuleService();
			Map paramMap = new HashMap();
			paramMap.put("applyId", applyId);		
			paramMap.put("partyId", partyId);		
			//小贷校验审批条件
			if(("04".equals(bizApply.get("bizType")))&&("3".equals(conclusion))){
				List<MessageObj> msgList = rs.runRule("RBIZ_0033", paramMap);
		        String msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
			}
			
			//集团成员流动资金校验
			List<MessageObj> msgList = rs.runRule("RBIZ_0021", paramMap);
			String msg = convertMsg(msgList);
			if(!"true".equals(msg)){	//校验不成功返回校验失败结果
				resultMap.put("errorNum", "2");
				resultMap.put("errorCode", "2");
				resultMap.put("errorContent", msg);
				return resultMap;
			}
			//集团成员校验
			if("05".equals(bizApply.get("bizType"))||"06".equals(bizApply.get("bizType"))){
				//基本信息保存校验
				msgList = rs.runRule("RBIZ_0003", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		      //明细信息保存校验
		        msgList = rs.runRule("RBIZ_0004", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		        //明细信息有金额为0的分项校验
		        msgList = rs.runRule("RBIZ_0013", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		      //监管报送
		        msgList = rs.runRule("RBIZ_0005", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		      //如果担保方式有保证必须输入保证信息
		        msgList = rs.runRule("RBIZ_0006", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		        //如果担保方式有抵押必须输入抵押信息
		        msgList = rs.runRule("RBIZ_0007", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		        //如果担保方式有质押必须输入质押信息
		        msgList = rs.runRule("RBIZ_0008", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		        //抵质押物信息必须录完整
		        msgList = rs.runRule("RBIZ_0014", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		      //有保证信息必须勾选保证
	   	    	msgList = rs.runRule("RBIZ_0028", paramMap);
	   	    	msg = convertMsg(msgList);
	   	    	if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	   	    		resultMap.put("errorNum", "2");
	   	    		resultMap.put("errorCode", "2");
	   	    		resultMap.put("errorContent", msg);
	   	    		return resultMap;
	   	    	}
	   	    	//有抵押信息必须勾选抵押
	   	    	msgList = rs.runRule("RBIZ_0029", paramMap);
	   	    	msg = convertMsg(msgList);
	   	    	if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	   	    		resultMap.put("errorNum", "2");
	   	    		resultMap.put("errorCode", "2");
	   	    		resultMap.put("errorContent", msg);
	   	    		return resultMap;
	   	    	}
	   	    	//有质押信息必须勾选质押
	   	    	msgList = rs.runRule("RBIZ_0030", paramMap);
	   	    	msg = convertMsg(msgList);
	   	    	if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	   	    		resultMap.put("errorNum", "2");
	   	    		resultMap.put("errorCode", "2");
	   	    		resultMap.put("errorContent", msg);
	   	    		return resultMap;
	   	    	}
	   	    	//有保证金信息必须勾选保证金
	   	    	msgList = rs.runRule("RBIZ_0031", paramMap);
	   	    	msg = convertMsg(msgList);
	   	    	if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	   	    		resultMap.put("errorNum", "2");
	   	    		resultMap.put("errorCode", "2");
	   	    		resultMap.put("errorContent", msg);
	   	    		return resultMap;
	   	    	}
	   	    	//委托贷款必须录入委托人账户信息
		        if(null!=bizApply.get("productType")&&!"".equals(bizApply.get("productType"))){
		        	String productType = (String)bizApply.get("productType");
		        	if("01013010".equals(productType)||"02005010".equals(productType) || "01013001".equals(productType)||"02005001".equals(productType)||"02005002".equals(productType)||"02005003".equals(productType)){
		        		msgList = rs.runRule("RBIZ_0045", paramMap);
		    	        msg = convertMsg(msgList);
		    	        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		    	        	resultMap.put("errorNum", "2");
		    	        	resultMap.put("errorCode", "2");
		    	        	resultMap.put("errorContent", msg);
		    	        	return resultMap;
		    	        }
		        	}
		        }
		        
		    	// 校验该业务下是否存在未价值审核的押品
				msgList = rs.runRule("RBIZ_0067", paramMap);
				msg = convertMsg(msgList);
				if (!"true".equals(msg)) { // 校验不成功返回校验失败结果
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
				
		        CollServiceImplServiceServiceStub stub = new CollServiceImplServiceServiceStub();
				CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();
				CollServiceImplServiceServiceStub.CollServiceCommInter serQuery = new CollServiceImplServiceServiceStub.CollServiceCommInter();
				ObjectMapper mapper = new ObjectMapper();
				//增加校验。如果业务申请下面存在 暂存 的押品，业务申请不允许通过，通过申请号查询
				String cltStsCdMsg = "该业务申请下存在'暂存'状态的押品，不允许提交!";
				logger.info("------------>调用押品系统查询押品校验接口1115------>开始!");
				Map ypxxMap = new HashMap();
				ypxxMap.put("applyId", applyId);//申请号
				ypxxMap.put("ope_flag","0001");
				ypxxMap.put("trans_code","1115");//押品校验查询接口交易码
				// Convert object to JSON string  
				String ypxxJsonStr = null;
				ypxxJsonStr = mapper.writeValueAsString(ypxxMap);
				System.out.println("押品校验查询接口入参："+ypxxJsonStr);
				ser.setIn0(ypxxJsonStr);
				logger.info("------------>applyId---ypxxJsonStr="+ypxxJsonStr+"---->结束!");
				String queryStr = stub.collServiceCommInter(ser).getOut1();	
				logger.info("------------>applyId结束------queryStr="+queryStr+"------>结束!");
				Map strmap = mapper.readValue(queryStr,HashMap.class);
				logger.info("信贷查回的押品校验详情:"+queryStr);
				if("true".equals(strmap.get("flag"))){
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", cltStsCdMsg);
					return resultMap;
				}
				
		        //贴息必输校验--20160526 lpc
		        msgList = rs.runRule("RBIZ_0020", paramMap);
	   	    	msg = convertMsg(msgList);
	   	    	if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	   	    		resultMap.put("errorNum", "2");
	   	    		resultMap.put("errorCode", "2");
	   	    		resultMap.put("errorContent", msg);
	   	    		return resultMap;
	   	    	}
	   	    	//贴息方式必须一样
	   	    	msgList = rs.runRule("RBIZ_0054", paramMap);
	   	    	msg = convertMsg(msgList);
	   	    	if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	   	    		resultMap.put("errorNum", "2");
	   	    		resultMap.put("errorCode", "2");
	   	    		resultMap.put("errorContent", msg);
	   	    		return resultMap;
	   	    	}
	   	    	//贴息比例之和不超过100%
	   	    	msgList = rs.runRule("RBIZ_0055", paramMap);
	   	    	msg = convertMsg(msgList);
	   	    	if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	   	    		resultMap.put("errorNum", "2");
	   	    		resultMap.put("errorCode", "2");
	   	    		resultMap.put("errorContent", msg);
	   	    		return resultMap;
	   	    	}
		        
	   	    	//按固定金额贴息：只能支持一个贴息主体
	   	    	msgList = rs.runRule("RBIZ_0056", paramMap);
	   	    	msg = convertMsg(msgList);
	   	    	if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	   	    		resultMap.put("errorNum", "2");
	   	    		resultMap.put("errorCode", "2");
	   	    		resultMap.put("errorContent", msg);
	   	    		return resultMap;
	   	    	}
	   	    	//调整后的批复金额不能大于原批复已用--借新还旧调整bizHappenType还是06 所以用bizApply里的oldapply判断
		        if(null != bizApply.get("oldApplyId") && !"".equals(bizApply.get("oldApplyId"))){
		        	msgList = rs.runRule("RBIZ_0064", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
		        }
		        //借新还旧申请额度不得大于借据余额
		        String bizHappenType = (String)bizApply.get("bizHappenType");//04调整   06借新还旧
		        if("06".equals(bizHappenType)){
		        	msgList = rs.runRule("RBIZ_0034", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
		        }
	   	    	//家庭财务信息校验   根据程钰要求--经营性不校验
		        if(null!=bizApply.get("productType")&&!"".equals(bizApply.get("productType"))){
		        	String productType = (String)bizApply.get("productType");
		        	if(!productType.startsWith("01")&&!productType.startsWith("020050")&&
		        			!productType.startsWith("020010")&&!productType.startsWith("03")){
		        		msgList = rs.runRule("RBIZ_0058", paramMap);
		    	        msg = convertMsg(msgList);
		    	        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		    	        	resultMap.put("errorNum", "2");
		    	        	resultMap.put("errorCode", "2");
		    	        	resultMap.put("errorContent", msg);
		    	        	return resultMap;
		    	        }
		        	}
		        }
		      //协办客户经理添加校验
	   	    	msgList = rs.runRule("RBIZ_0025", paramMap);
	   	    	msg = convertMsg(msgList);
	   	    	if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	   	    		resultMap.put("errorNum", "2");
	   	    		resultMap.put("errorCode", "2");
	   	    		resultMap.put("errorContent", msg);
	   	    		return resultMap;
	   	    	}
			}
			
			//校验监管限额
			if("2".equals(conclusion)){
		logger.info("------3231------>审批结论为不同意时不校验监管限额------>begin！");
			}else if("01013001".equals(bizApply.get("productType"))|| "01013010".equals(bizApply.get("productType"))||"02005010".equals(bizApply.get("productType"))||
					"02005002".equals(bizApply.get("productType"))||"02005003".equals(bizApply.get("productType"))){
		logger.info("------3231------>委托贷款不校验监管限额------>begin！");
			}else{
				//校验监管限额
			logger.info("------3231------>业务申请流程结束，校验监管限额------>begin！");
				ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.crd.CreditLimit");
				Object[] params = new Object[1];
				params[0] = applyId;
				Object[] objs=logicComponent.invoke("checkJgLimit", params);
				Object obj=  objs[0];
			logger.info("监管限额校验结果为："+obj.toString());
				if(!"1".equals(obj)){
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", obj.toString());
		        	return resultMap;
				}
			}		
		} catch (WFServiceException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	private String convertMsg(List<MessageObj> msgList){
        StringBuffer sf = new StringBuffer();
        if(msgList != null && !msgList.isEmpty()){
            for (int i = 0; i < msgList.size(); i++) {
                MessageObj t = msgList.get(i);
                if(EngineConstants.RULE_LEVEL_ERROR.equals(t.getMessageType())){
                    sf.append("[(" + (i+1) + "):" + t.getCode() + "," + t.getMessageInfo() + "]");
                }
            }
        }
        if (sf.length() > 0) {
            return sf.toString();
        }
        return "true";
    }
}
