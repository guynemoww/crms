package com.bos.npl.bps;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.bfs.tp.common.exception.EOSException;
import commonj.sdo.DataObject;

public class SavePerformingToBook implements IBIZProcess {

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}

	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}
//流程结束回调逻辑
	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {

	}
//	流程提交，将数据生成台账
	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {

		String[] apply={"bizId"};//获取相关数据的数组
		try {
			List<Object> str = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), apply);
			String schemeId=str.get(0).toString();

			//查询待移交数据信息
			String dataType="com.bos.dataset.npl.TbNplDispostionSchemeDetail";
			DataObject schemeDetail = DataObjectUtil.createDataObject(dataType);
			schemeDetail.set("schemeId", schemeId);
			DataObject[] obj = DatabaseUtil.queryEntitiesByTemplate("default", schemeDetail);
			
			GitUtil git = new GitUtil();//营业时间
			Date date = git.getBusiDate();
			
			//将待移交数据保存到台账
			for(int i = 0;i <= obj.length-1; i++){
				String performingId = obj[i].getString("performingId");
				//查询待移交
				String dataDetailType="com.bos.dataset.npl.TbNplAssetsPerforming";
				DataObject Performing = DataObjectUtil.createDataObject(dataDetailType);
				Performing.set("performingId", performingId);
				DatabaseUtil.expandEntity("default", Performing);
				//转为台账
				String dataConverType ="com.bos.dataset.npl.TbNplAssetsBook";
				DataObject book = DataObjectUtil.convertDataObject(Performing, dataConverType, false);
				book.set("intoDate", date);
				book.set("assetsStatus", "3");
				
				DatabaseUtil.saveEntity("default", book);
			}
			
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成方法存根

	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		return null;
	}

}
