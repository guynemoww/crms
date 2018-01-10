package com.bos.inter.CallBmsInterface;

import java.util.ArrayList;
import java.util.List;

import com.bos.jaxb.javabean.BOSFXII;
import com.bos.mq.rq.BaseMQRequest;
import com.bos.mq.server.BaseWorkTask;
import com.bos.mq.server.CrmsMsg;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.data.sdo.collection.ContainerAwareList;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
//票据额度恢复  票据->CRMS
@Bizlet("ResponseToBms")
public class CRMSCreRestInset extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public CRMSCreRestInset(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		DataObject requ = DataFactory.INSTANCE.create("com.bos.inter.CallBmsInterface.BmsInterface", "CRMSCreRestInsetRq");
		requ.setString("ContractId",((CRMSCreRestInsetRq)obj).ContractId);
		requ.setString("TrnType",((CRMSCreRestInsetRq)obj).TrnType);
		List<DataObject> myData = new ArrayList<DataObject>();
		List<CRMSCreRestInsetRec> draftRec =((CRMSCreRestInsetRq)obj).CRMSCreRestInsetRec;
		for(int i=0;i<draftRec.size();i++)
		{
			DataObject noteInfo = DataFactory.INSTANCE.create("com.bos.inter.CallBmsInterface.BmsInterface", "CRMSCreRestInsetRec");
			CRMSCreRestInsetRec temp=(CRMSCreRestInsetRec) draftRec.get(i);
			noteInfo.setString("DraftId",temp.DraftId);
			noteInfo.setString("Amount",temp.Amount);
			noteInfo.setString("AcceptDraftId",temp.AcceptDraftId);
			myData.add((DataObject)noteInfo);
		}
		requ.setList("CRMSCreRestInsetRec", myData);
		CRMSCreRestInsetRs rs=new CRMSCreRestInsetRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallBmsInterface.BmsMaintain";
		// 逻辑流名称
		String operationName = "CRMSCreRestInset";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[1];
		params[0] =requ;
		BaseMQRequest bmr = new BaseMQRequest();
		try{
			Object[] result = logicComponent.invoke(operationName, params);
			this.rsh.setRqUID(taskBean.getTaskId());
			DataObject obj = (DataObject) result[0];
			String reCode=obj.getDataObject("CommonRsHdr").getString("StatusCode");
			String reMsg=obj.getDataObject("CommonRsHdr").getString("ServerStatusCode");
			//判断响应信息
				if(reCode=="000000")
				{
				rs.setCommonRsHdr(this.success());
				}
				else
				{
					//返回指定响应吗
					rs.setCommonRsHdr(this.error(reCode, reMsg));
				}
			bf.bmsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		
			
		}catch (Throwable e) {
			rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
			bf.bmsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
			e.printStackTrace();
			throw new EOSException(e);
		}
	
	}

}
