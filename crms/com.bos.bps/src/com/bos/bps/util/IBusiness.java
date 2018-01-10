package com.bos.bps.util;

import com.primeton.bfs.tp.common.exception.EOSException;

/**
 * 处理流程时，相应的处理业务信息
 * @author lijf
 *
 */
public interface IBusiness {
	
	/**
	 * 当终止流程的时候，更新业务数据
	 * @param processInstId 流程实例ID
	 * @param bizId 业务主键
	 * @throws EOSException
	 */
	public void updateBizDataWhenStopFlow(String processInstId,String bizId)throws EOSException;

}
