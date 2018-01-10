/**
 * 
 */
package com.bos.inter.CallCAMSInterface;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.inter.CallScfInterface.CRMSReplyInfoInqRec;
import com.bos.inter.CallScfInterface.CRMSReplyInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSReplyInfoInqRs;
import com.bos.jaxb.javabean.BOSFXII;
import com.bos.mq.rq.BaseMQRequest;
import com.bos.mq.server.BaseWorkTask;
import com.bos.mq.server.CrmsMsg;
import com.bos.mq.util.FormatUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.data.sdo.collection.ContainerAwareList;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;

/**
 * @author lujinbin
 * @date 2014-09-03 10:09:12
 *
 */

public class CRMSLoanStaInq  extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public CRMSLoanStaInq(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		// TODO 自动生成方法存根
//		 TODO 自动生成方法存根
		String LoanId = ((CRMSLoanStaInqRq) obj).LoanId== null ? null :((CRMSLoanStaInqRq) obj).LoanId;
		String LdNum = ((CRMSLoanStaInqRq) obj).LdNum== null ? null :((CRMSLoanStaInqRq) obj).LdNum;
		String LoanApproveNum = ((CRMSLoanStaInqRq) obj).LoanApproveNum== null ? null :((CRMSLoanStaInqRq) obj).LoanApproveNum;
		String SpName = ((CRMSLoanStaInqRq) obj).SpName== null ? null :((CRMSLoanStaInqRq) obj).SpName;
		CRMSLoanStaInqRs rs=new CRMSLoanStaInqRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallCAMSInterface.CamsMaintain";
		// 逻辑流名称
		String operationName = "CRMSLoanStaInq";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[4];
		params[0] = LdNum;
		params[1] = LoanId;
		params[2] = LoanApproveNum;
		params[3] = SpName;
		BaseMQRequest bmr = new BaseMQRequest();
		try{
			Object[] result = logicComponent.invoke(operationName, params);
			this.rsh.setRqUID(taskBean.getTaskId());
			DataObject obj = (DataObject) result[0];
			Map<String,String> objs = (Map<String,String>) result[1];
			String reCode=objs.get("StatusCode");
			String reMsg=objs.get("ServerStatusCode");
			if (reCode=="000000"){
			rs.setLdNum(obj.getString("LdNum"));
			rs.setLoanApproveNum(obj.getString("LoanApproveNum"));
			rs.setLoanId(obj.getString("LoanId"));
			rs.setTransState(obj.getString("TransState"));
				rs.setCommonRsHdr(this.success());
			}else {
				rs.setCommonRsHdr(this.error(reCode, reMsg));
			}
			 
			bf.camsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		}catch(Throwable e){
			rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
			bf.camsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
			e.printStackTrace();
			throw new EOSException(e);
			
		}
	}

 
}
