package com.bos.inter.CallScfInterface;


import com.bos.jaxb.javabean.BOSFXII;
import com.bos.mq.rq.BaseMQRequest;
import com.bos.mq.server.BaseWorkTask;
import com.bos.mq.server.CrmsMsg;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;

@Bizlet("RequstToscf")
//放款申请撤销 CRMS->SCF
public class CRMSLoanAppRev extends BaseWorkTask implements WorkTask{

	public   Object obj;
	public CRMSLoanAppRev(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		String ContractId = ((CRMSLoanAppRevRq) obj).ContractId;
		String LoanId = ((CRMSLoanAppRevRq) obj).LoanId;
		CRMSLoanAppRevRs rs=new CRMSLoanAppRevRs();
		
		BOSFXII bf = new BOSFXII();
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
		// 逻辑流名称
		String operationName = "CRMSLoanAppRev";
		
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[2];
		params[0] =ContractId;
		params[1] =LoanId;
		
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
			bf.scfBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		}catch (Throwable e) {
			rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
			bf.scfBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
			e.printStackTrace();
			throw new EOSException(e);
		}
	
	}


}
