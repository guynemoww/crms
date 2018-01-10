package com.bos.grtPro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

public class CallBackForSubmitInProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitInProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}
	//用于创建流程成功后更新业务表数据 审批状态 XD_KHCD0230
	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		String[] xpath={"bizId"};//获取相关数据的数组
		try{
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("流程返回的业务ID为空！");
					throw new EOSException("流程返回的业务ID为空");
				}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bizId", outId);
			map.put("state", "02");
			map.put("user", GitUtil.getCurrentUserId()); //--
			map.put("org", GitUtil.getCurrentOrgId()); //--
			logger.info("------------>押品入库流程提交，开始更新业务状态------outId="+outId+"------->开始!");
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateGrtIn", map);
			logger.info("------------>押品入库流程提交，开始更新业务状态------outId="+outId+"------>结束!");
		}catch(Exception e){
			e.printStackTrace();
			throw new EOSException("------------>押品入库流程提交时修改业务状态出错！");
		}
	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		//撤销流程  业务状态更新为06
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("撤销流程时，流程返回的业务ID为空！");
					throw new EOSException("撤销流程时，流程返回的业务ID为空");
				}
			logger.info("------------>押品入库撤销流程时，开始更新业务状态------outId="+outId+"------->开始!");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bizId", outId);
			map.put("state", "06");
			map.put("user", GitUtil.getCurrentUserId()); //--
			map.put("org", GitUtil.getCurrentOrgId()); //--
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateGrtIn", map);
			
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("id", outId);
			Object[] data = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.grt.grt.selectCardIds", map1);
			if (null == data || data.length == 0) {
				throw new EOSException("未查到相关权证信息");
			}
			DataObject dataObject = (DataObject) data[0];
			String suretyKeyId = dataObject.getString("SURETY_KEY_ID");
			Map<String,Object> hmap = new HashMap<String,Object>();
			hmap.put("suretyKeyId", suretyKeyId);
			hmap.put("state", "03");//XD_YWDB0139
			DatabaseExt.executeNamedSql("default", 
					"com.bos.grt.grt.updateRegCard", hmap);
			
			//恢复押品的状态--将押品状态 是已入库的变成01
			map.put("mortgageStatus", "01");
			DatabaseExt.executeNamedSql("default", 
			"com.bos.grt.grt.updateMortgageStatus", map);//将已入库的状态恢复
			
	logger.info("------------>押品入库撤销流程时，开始更新业务状态------outId="+outId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------------>押品入库撤销流程时修改业务状态出错！");
		} 
	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		String[] xpath={"bizId"};//获取相关数据的数组
		HashMap resultMap = new HashMap();
		String msg = "";
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String outId=(String)list.get(0);
			HashMap map = new HashMap();
			map.put("inId", outId);
			Object[] data = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.grt.grt.getGrtIn", map);
			if (null == data || data.length == 0) {
				throw new EOSException("未查到相关入库信息");
			}else{
				DataObject dataObject = (DataObject) data[0];
				String inReason = dataObject.getString("IN_REASON");
				String inType = dataObject.getString("IN_TYPE");
				if("".equals(inType) || "".equals(inReason) || inType ==null || inReason == null){
					msg = "请先输入入库类型与入库原因！";
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("------------>押品入库校验出错！");
		}
		return null;
	}

}
