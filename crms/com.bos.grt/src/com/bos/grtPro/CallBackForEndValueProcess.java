package com.bos.grtPro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.engine.ListenerManager;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.grt.collService.CollServiceImplServiceServiceStub;
import com.bos.pub.GitUtil;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.transaction.ITransactionManager;
import com.eos.common.transaction.TransactionManagerFactory;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.spring.TraceLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primeton.bfs.tp.common.exception.EOSException;
import commonj.sdo.DataObject;

public class CallBackForEndValueProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForEndValueProcess.class);

	private static  CollServiceImplServiceServiceStub stub = null;
	//获取IP
	private String getUrl(){
		ListenerManager.defaultConfigurationContext = null;
		String module = "CollWebServiceConfig";
		String group = "coll_webservice_server";
		String ip = "ip";
		String port = "port";
		String wservice = "service";
		String zip = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
		String zport = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
		String zservice = ConfigurationUtil.getUserConfigSingleValue(module, group, wservice);
		String url = "http://" + zip + ":" + zport + zservice;
		System.out.println(url);
		return url;
	}
	
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	
	//出库流程结束时执行的逻辑
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		//提交流程  业务状态更新为03  XD_SXCD8003
		
		/*--价值审核步骤
		 * ----1.更新业务审批状态
		 * ----2.调用价值信息同步接口。将数据传入押品系统
		--*/
		String[] xpath={"bizId"};//获取相关数据的数组
		ITransactionManager transactionManager = TransactionManagerFactory.getTransactionManager();

		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			transactionManager.begin(ITransactionDefinition.PROPAGATION_REQUIRED);

			//获取押品id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("流程返回的业务ID为空！");
					throw new EOSException("流程返回的业务ID为空");
				}
			logger.info("------------>押品价值审核流程结束，开始更新业务状态------outId="+outId+"------->开始!");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bizId", outId);
			map.put("state", "03");
			map.put("user", GitUtil.getCurrentUserId()); //--
			map.put("org", GitUtil.getCurrentOrgId()); //--
			
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateValueApprove", map);
			
			double value = 0.0;
			//查询评估价值
			Object[] basicData = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.grt.grt.getGrtBasic", map);
			if (null == basicData || basicData.length == 0) {
				logger.info("-----未查到相关押品信息------>bizId=" + outId);
			}
			DataObject basicDataObject = (DataObject) basicData[0];
			value = Double.parseDouble(basicDataObject.getString("ASSESS_VALUE"));
			String  mybankaffirmvalue = basicDataObject.getString("MYBANK_AFFIRM_VALUE");//我行确认价值。如果此值为0，则为贷前。不为0 ，则为贷后
			double mortamt = basicDataObject.getDouble("MORTGAGA_AMT");//最新权利价值
			double mortvalue = basicDataObject.getDouble("MORTGAGE_VALUE");//权利价值
			//更新押品表中评估价值为我行认定价值
			Map<String,Object> tmap = new HashMap<String,Object>();
			tmap.put("mortgagaValue", value);
			tmap.put("suretyNo", basicDataObject.getString("SURETY_NO"));
			if("0".equals(mybankaffirmvalue)){
				
			}else{
				tmap.put("mortamt", mortamt);
			}
			
			map.put("cltNo", basicDataObject.getString("SURETY_NO"));
			map.put("status", "03");
			Object[] outs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, 
					"com.bos.grt.grt.getSubForSuretyno", map);
//			double amt = 0.0;
			if(outs.length > 0){
//				amt =((DataObject)outs[0]).getDouble("AVI_AMT");
//				ttmap.put("amt",  amtt);
				
				Map ttmap = new HashMap();
				//如果押品下面存在担保，则需要更新TB_CON_SUB_GRT_REL surety_amt  tb_con_subcontract_rel surety_amt
				for(int j = 0;j<outs.length;j++){
					DataObject ob = (DataObject)outs[j];
					ttmap.put("num",  ob.getString("SUBCONTRACT_NUM"));
					ttmap.put("suretyId",  ob.getString("SURETY_ID"));
				    double amtt = ob.getDouble("SURETY_AMT");
					//如果新的权利价值比 担保确认金额小，则将担保确认金额更新为新的权利价值，如果比确认金额大。则不做操作
				    if(mortamt - amtt < 0.0){
				    	ttmap.put("amt",  mortamt);
				    	DatabaseExt.executeNamedSql("default", 
				    			"com.bos.grt.grt.updateSuretyAmt", ttmap);	
				    }
				    double amttt = ob.getDouble("SS_AMT");
					//如果新的权利价值比 担保确认金额小，则将担保确认金额更新为新的权利价值，如果比确认金额大。则不做操作
				    if(mortamt - amttt < 0.0){
				    	ttmap.put("amt",  mortamt);
				    	DatabaseExt.executeNamedSql("default", 
				    			"com.bos.grt.grt.updateSuretyAmtRel", ttmap);	
				    }
				}
			}else{
				if("0".equals(mybankaffirmvalue)){
				}else{
					tmap.put("aviamt", mortamt);//如果押品下面没有担保，更新押品信息表中可用金额，如果下面有担保，则不更新 20171114
				}
			}
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateMortgagaValue", tmap);	
			
			//调接口，同步数据
			stub = new CollServiceImplServiceServiceStub(getUrl());
			CollServiceImplServiceServiceStub.CollServiceCommInter ser = new CollServiceImplServiceServiceStub.CollServiceCommInter();

			ObjectMapper mapper = new ObjectMapper();
			Map ypxxMap = new HashMap();
			ypxxMap.put("clt_no",outId);
			ypxxMap.put("beforeevalvalue",value);
			if("0".equals(mybankaffirmvalue)){
				ypxxMap.put("scene_id","0");//贷前
			}else{
				ypxxMap.put("mortamt",mortamt);
				ypxxMap.put("scene_id","1");//重估
			}
			ypxxMap.put("trans_code","1103");//价值同步接口交易码
			// Convert object to JSON string  
			String ypxxJsonStr = null;
			ypxxJsonStr = mapper.writeValueAsString(ypxxMap);
			ser.setIn0(ypxxJsonStr);
			String flag = stub.collServiceCommInter(ser).getOut1();
			logger.info("------------>押品价值审核流程结束，开始更新业务状态------outId="+outId+"------>结束!");
			transactionManager.commit();

		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			if (transactionManager != null) {
				transactionManager.rollback();
			}
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			if (transactionManager != null) {
				transactionManager.rollback();
			}
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		//提交流程  业务状态更新为06  XD_SXCD8003
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("流程返回的业务ID为空！");
					throw new EOSException("流程返回的业务ID为空");
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("bizId", outId);
				map.put("state", "06");
				map.put("user", GitUtil.getCurrentUserId()); //--
				map.put("org", GitUtil.getCurrentOrgId()); //--
				logger.info("------------>押品价值审核流程拒绝，开始更新业务状态------outId="+outId+"------->开始!");
				DatabaseExt.executeNamedSql("default", 
						"com.bos.grt.grt.updateValueApprove", map);
				logger.info("------------>押品价值审核流程拒绝，开始更新业务状态------outId="+outId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------------>押品价值审核流程拒绝失败!");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------------>押品价值审核流程拒绝失败!");
		}
	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

}
