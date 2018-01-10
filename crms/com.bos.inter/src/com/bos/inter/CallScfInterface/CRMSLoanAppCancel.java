package com.bos.inter.CallScfInterface;


import com.bos.inter.CallBmsInterface.CRMSContrListInqRq;
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
 * 
 * @author chenhuan
 *
 */
//放款申请取消 SCF->CRMS
@Bizlet("ResponseToSCF")
public class CRMSLoanAppCancel extends BaseWorkTask implements WorkTask{

	public   Object obj;
	public CRMSLoanAppCancel(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		String ContractId = ((CRMSLoanAppCancelRq) obj).ContractId== null ? "" :((CRMSLoanAppCancelRq) obj).ContractId;
		String CrmsLoanId = ((CRMSLoanAppCancelRq) obj).CrmsLoanId== null ? "" :((CRMSLoanAppCancelRq) obj).CrmsLoanId;
		String LoanId = ((CRMSLoanAppCancelRq) obj).LoanId== null ? "" :((CRMSLoanAppCancelRq) obj).LoanId;
		String AcceptNo = ((CRMSLoanAppCancelRq) obj).AcceptNo== null ? "" :((CRMSLoanAppCancelRq) obj).AcceptNo;
		CRMSLoanAppCancelRs rs=new CRMSLoanAppCancelRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
		// 逻辑流名称
		String operationName = "CRMSLoanAppCancel";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[4];
		params[0] =ContractId;
		params[1] = CrmsLoanId;
		params[2] = LoanId;
		params[3] = AcceptNo;
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
			else {
			 //返回指定响应吗
				rs.setCommonRsHdr(this.error(reCode, reMsg));
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
