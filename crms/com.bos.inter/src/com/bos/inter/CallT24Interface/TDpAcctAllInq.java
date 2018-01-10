package com.bos.inter.CallT24Interface;
/**
 * @author chenhuan
 */
import com.bos.jaxb.javabean.BOSFXII;
import com.bos.mq.rq.BaseMQRequest;
import com.bos.mq.server.BaseWorkTask;
import com.bos.mq.server.CrmsMsg;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
@Bizlet("ResponseToscfcrms")
public class TDpAcctAllInq  extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public TDpAcctAllInq(Object obj) {
		this.obj = obj;
	}	
public void execute() throws Exception {
	// TODO 自动生成方法存根
	DataObject requ = DataFactory.INSTANCE.create("com.bos.inter.CallT24Interface", "TDpAcctAllInqRq");
	requ.setString("MediumType",((TDpAcctAllInqRq)obj).MediumType);
	requ.setString("AcctNo",((TDpAcctAllInqRq)obj).AcctNo);
	requ.setString("Currency",((TDpAcctAllInqRq)obj).Currency==null?null:((TDpAcctAllInqRq)obj).Currency);
	requ.setString("FcyType",((TDpAcctAllInqRq)obj).FcyType==null?null:((TDpAcctAllInqRq)obj).FcyType);
	requ.setString("DEPProd",((TDpAcctAllInqRq)obj).DEPProd==null?null:((TDpAcctAllInqRq)obj).DEPProd);
	requ.setString("Pwd",((TDpAcctAllInqRq)obj).Pwd==null?null:((TDpAcctAllInqRq)obj).Pwd);
	TDpAcctAllInqRs rs=new TDpAcctAllInqRs();
	BOSFXII bf = new BOSFXII();
	// 逻辑构件路径
	String componentName = "com.bos.inter.CallT24Interface.T24Maintain";
	// 逻辑流名称
	String operationName = "TDpAcctAllInq";
	ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
	Object[] params = new Object[1];
	params[0] =requ;
	BaseMQRequest bmr = new BaseMQRequest();
	try{
		Object[] result = logicComponent.invoke(operationName, params);
		this.rsh.setRqUID(taskBean.getTaskId());
		if (result != null && result.length > 0){
			rs.setCommonRsHdr(this.success());
			
		}
		 else {
				rs.setCommonRsHdr(this.error(CrmsMsg._SCF_ISNULL.value(), CrmsMsg._SCF_ISNULL_MSG.value()));
			}
		bf.t24BosfxRs = rs;
		bmr.mqSend(bf, taskBean);
	}catch (Throwable e) {
		rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
		bf.t24BosfxRs = rs;
		bmr.mqSend(bf, taskBean);
		e.printStackTrace();
		throw new EOSException(e);
	}
}

}
