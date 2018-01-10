package com.bos.grt.custFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.primeton.bfs.tp.common.exception.EOSException;

import commonj.sdo.DataObject;

/**
 * 借出归还
 * @author ZhuYongLun
 *
 */
public class CollateralOutInCust implements IBIZProcess{
	/**
	 * @Title: executeAfterAbort
	 * @Description: TODO(用于撤销流程后业务逻辑)
	 * @param processInstId 流程实例ID号
	 * @return void     返回类型
	 * @throws
	 */
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		//撤销流程，examineState=null
		//bizStatus:01--审核中  02--已审核  03--被否决(临时)  状态代码待定
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtRanklaidupmain");
		try {
			//获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("laidUpId", list.get(0).toString());
				dataOb.set("examineState","");
				DatabaseUtil.saveEntity("default", dataOb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
	}
	
	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
			
	}
	
	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		
	}
	
	/**
	 * 
	 * @Title: executeBeforeReject
	 * @Description: TODO(用于审批否决前业务逻辑)---流程中途被否决结束
	 * @param processInstId 流程实例ID号
	 * @return void     返回类型
	 * @throws
	 */
	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		//流程进行时，examineState=审核中
		//01--审核中  02--已审核  03--被否决(临时)  状态代码待定
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtRanklaidupmain");
		try {
			//获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("laidUpId", list.get(0).toString());
				dataOb.set("examineState","03");
				DatabaseUtil.saveEntity("default", dataOb);
				//将修改的状态传回流程中
				relaDatas.put("bizStatus", "03"); 
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
	}
	
	/**
	 * 
	 * @Title: executeBeforeSubmit
	 * @Description: TODO(用于审批同意流程提交前业务逻辑)--流程下一节点同意，往下一节点走的情况
	 * @param  processInstId 流程实例ID号
	 * @return void     返回类型
	 * @throws
	 */
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {
		//流程进行时，examineState=审核中
		//01--审核中  02--已审核  03--被否决(临时)  状态代码待定
		String[] xpath={"bizId","bizStatus"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtRanklaidupmain");
		try {
			//获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//将从业务流程中获取的数据 设置到数据实体中，方便更新数据库。
				dataOb.set("laidUpId", list.get(0).toString());
				dataOb.set("examineState","01");
				DatabaseUtil.saveEntity("default", dataOb);
				//将修改的状态传回流程中
				relaDatas.put("bizStatus", "01"); 
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
	}
	
	/**
	 * @Title: executeAfterAbort
	 * @Description: TODO(用于普通流程结束前业务逻辑)
	 * @param processInstId 流程实例ID号
	 * @return void     返回类型
	 * @throws
	 */
	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
//		流程结束时，examineState=已审核
		//bizStatus:01--审核中  02--已审核  03--被否决(临时)  状态代码待定
		String[] xpath={"bizId","bizStatus","regId","mailerNum","saveReg"};//获取相关数据的数组
		HashMap<String, Object>  relaDatas = new HashMap<String, Object>();
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtRanklaidupmain");
		try {
			//获取流程中的业务数据
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				String laidUpId = list.get(0).toString();
				String mailerNum = list.get(3).toString();
				String saveReg = list.get(4).toString();
				//com.bos.pub.GitUtil.getBusiDate()
				Object updateTime = com.bos.pub.GitUtil.getBusiDate();
				HashMap<String, Object> laidUpStr = new HashMap<String, Object>();
				laidUpStr.put("laidUpId", laidUpId);
				laidUpStr.put("mailerNum", mailerNum);
				laidUpStr.put("saveReg", saveReg);
				laidUpStr.put("updateTime", updateTime);
				Object[] objects = DatabaseExt.queryByNamedSql(
						"default",
						"com.bos.grt.grt.selectRegIdByLaidUpId",
						laidUpStr);
				if(objects.length>0){
					dataOb.set("laidUpId",list.get(0).toString());
					dataOb.set("examineState","02");
					DatabaseUtil.saveEntity("default", dataOb);
					//修改登记信息中的登记状态
					DatabaseExt.executeNamedSql("default", "com.bos.grt.grt.updateRegcardinfoByLaidUpIdIn",laidUpStr);
				}
				//将修改的状态传回流程中
				relaDatas.put("bizStatus", "02"); 
				RelativeDataManagerService.setRelativeDataBatch(Long.valueOf(processInstId), relaDatas);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e);
		}
	}
	
	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		
	}
	
	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws EOSException {
		return null;
	}
}
