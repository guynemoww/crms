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

public class CallBackForSubmitProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根
		//提交流程  业务状态更新为02
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String outId=(String)list.get(0);
				if(null==outId||"".equals(outId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				
	logger.info("------3231------>押品出库流程提交，开始更新业务状态------outId="+outId+"------->开始!");
				DataObject out = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtOut");
				out.set("outId", outId);
				out.set("approveState", "02");
				DatabaseUtil.updateEntity("default", out);
	logger.info("------3231------>押品出库流程提交，开始更新业务状态------outId="+outId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>押品出库流程提交时修改业务状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>押品出库流程提交时修改业务状态出错！");
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
					logger.info("撤销流程时，流程返回的申请ID为空！");
					throw new EOSException("撤销流程时，流程返回的申请ID为空");
				}
				
	logger.info("------3231------>押品出库撤销流程时，开始更新业务状态------outId="+outId+"------->开始!");
				DataObject out = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtOut");
				out.set("outId", outId);
				out.set("approveState", "06");
				DatabaseUtil.updateEntity("default", out);
	logger.info("------3231------>押品出库撤销流程时，开始更新业务状态------outId="+outId+"------>结束!");
		}catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>押品出库撤销流程时修改业务状态出错！");
		} catch (Throwable e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			throw new EOSException("------3231------>押品出库撤销流程时修改业务状态出错！");
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
			map.put("bizId", outId);
			Object[] data = DatabaseExt.queryByNamedSql(
					GitUtil.DEFAULT_DS_NAME,
					"com.bos.grt.grt.getGrtOut", map);
			if (null == data || data.length == 0) {
				throw new EOSException("未查到相关出库信息");
			}else{
				DataObject dataObject = (DataObject) data[0];
				String outReason = dataObject.getString("OUT_REASON");
				String outType = dataObject.getString("OUT_TYPE");
				String date = dataObject.getString("CARD_IN_REVERT_DATE");
				if("".equals(outType) || "".equals(outReason) || outType ==null || outReason == null){
					msg = "请检查出库类型与出库原因！";
					resultMap.put("errorNum", "2");
					resultMap.put("errorCode", "2");
					resultMap.put("errorContent", msg);
					return resultMap;
				}else if("22".equals(outType) && ("".equals(date)||date==null)){
					msg = "临时出库必须输入预计归还时间！";
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
