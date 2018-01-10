package com.bos.csm.custStop;

import java.util.HashMap;
import java.util.List;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBusiness;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.bfs.tp.common.exception.EOSException;
import commonj.sdo.DataObject;
/**
 * 终止流程
 * 实现流程接口，处理业务数据
 * 如果为流程中，状态删除
 * 如果未发起流程，直接删除业务数据
 * 主办权变更
 * */
public class HostRightChange implements IBusiness {

	public void updateBizDataWhenStopFlow(String processInstId, String bizId)
			throws EOSException {
		// TODO 自动生成方法存根
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		
		try {
//			获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("partyId", list.get(0).toString());
				dataOb.set("examineState","03");
				DatabaseUtil.updateEntity("default", dataOb);
				//将修改的状态传回流程中
				relaDatas.put("bizStatus", "03"); 
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
		
	}
}
