/**
 * 
 */
package com.bos.inter.CallT24Interface;


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

/**
 * @author lujinbin
 * @date 2014-09-06 11:51:47
 *
 */
@Bizlet("币种汇率")
public class TExCurrencyAllInq extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public TExCurrencyAllInq(Object obj) {
		this.obj = obj;
	}	
public void execute() throws Exception {
	// TODO 自动生成方法存根
	 String ccyId= ((TExCurrencyAllInqRq)obj).CcyId ;
	 String allFlag=((TExCurrencyAllInqRq)obj).AllFlag;
	TExCurrencyAllInqRs rs=new TExCurrencyAllInqRs();
	BOSFXII bf = new BOSFXII();
	// 逻辑构件路径
	String componentName = "com.bos.inter.CallT24Interface.T24Maintain";
	// 逻辑流名称
	String operationName = "TExCurrencyAllInq";
	ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
	Object[] params = new Object[2];
	params[0] =ccyId;
	params[1] =allFlag;
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
