package com.bos.ewsPro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.spring.support.DataObjectUtil;
import commonj.sdo.DataObject;

public class CallBackForEndProcess implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);

	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		new CallBackForSubmitProcess().executeBeforeTerminate(processInstId, workitem);

	}

	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {

	}

	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {

		String[] xpath = { "bizId" };// 获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			// 获取主键id
			String applyId = (String) list.get(0);
			if (null == applyId || "".equals(applyId)) {
				logger.info("流程返回的申请ID为空！");
				throw new EOSException("流程返回的申请ID为空");
			}

			logger.info("流程提交，开始更新业务状态------bizId=" + applyId + "------->开始!");

			HashMap map = new HashMap();
			map.put("applyId", applyId);

			// 关闭时不删除，修改预警信号状态
			DatabaseExt.queryByNamedSql("default", "com.bos.ews.queryWarnBizInfo.deleteSignalClose", map);
			// 新增时删除预警信号
			DatabaseExt.queryByNamedSql("default", "com.bos.ews.queryWarnBizInfo.deleteSignalOpen", map);

			// 删除预警变更申请
			DataObject adjust = DataObjectUtil.createDataObject("com.bos.dataset.ews.TbRewLevelAdjust");
			adjust.set("levelAdjustId", applyId);
			DatabaseUtil.expandEntity("default", adjust);
			String partyId = "";
			if (null != adjust) {
				if (null != adjust.get("partyId") && !"".equals((String) adjust.get("partyId"))) {
					logger.info("partyId--->" + adjust.get("partyId"));
					partyId = (String) adjust.get("partyId");
				}
				DatabaseUtil.deleteEntity("default", adjust);
			}
			logger.info("partyId--->" + partyId);

			logger.info("流程提交，开始更新业务状态------bizId=" + applyId + "------>结束!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException("流程提交更新业务状态出错！");
		}

	}

	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根

	}

	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		return null;
	}

}
