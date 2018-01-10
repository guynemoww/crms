package com.bos.inter.CallCRMInterface;

//单点登录获取url加密key
import com.bos.jaxb.javabean.BOSFXII;
import com.bos.mq.rq.BaseMQRequest;
import com.bos.mq.server.BaseWorkTask;
import com.bos.mq.server.CrmsMsg;
import com.eos.engine.component.ILogicComponent;
import com.git.easyetl.threadpool.task.WorkTask;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;
public class CRMSCreEncKey  extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public CRMSCreEncKey(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		BaseMQRequest bmr = new BaseMQRequest();
		String ChannelId = ((CRMSCreEncKeyRq) obj).ChannelId== null ? "" :((CRMSCreEncKeyRq) obj).ChannelId;
		String UserId = ((CRMSCreEncKeyRq) obj).UserId== null ? "" :((CRMSCreEncKeyRq) obj).UserId;
		
		CRMSCreEncKeyRs rs = new CRMSCreEncKeyRs();
		BOSFXII bf = new BOSFXII();
		//逻辑构件名称
		String componentName = "com.bos.inter.CallCRMInterface.CRMInterface";
		// 逻辑流名称
		String operationName = "CRMSCreEncKey";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[2];
		params[0] = ChannelId;
		params[1] = UserId;
		try {
			Object[] result = logicComponent.invoke(operationName, params);
			this.rsh.setRqUID(taskBean.getTaskId());
			DataObject obj = (DataObject) result[0];
			String reCode=obj.getDataObject("CommonRsHdr").getString("StatusCode");
			String reMsg=obj.getDataObject("CommonRsHdr").getString("ServerStatusCode");
			//判断响应信息
			if(reCode=="000000")
			{
				rs.setKeyName(obj.getString("KeyName"));
				rs.setCommonRsHdr(this.success());
				
			}else
			{
				//返回指定响应吗
				rs.setCommonRsHdr(this.error(reCode, reMsg));
			}
			bf.crmBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		} catch (Throwable e) {
			rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
			bf.crmBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
			e.printStackTrace();
			throw new EOSException(e);
		}
	}


}
