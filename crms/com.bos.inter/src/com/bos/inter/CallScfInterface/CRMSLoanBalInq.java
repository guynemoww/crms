package com.bos.inter.CallScfInterface;
import com.bos.jaxb.javabean.BOSFXII;
import com.bos.mq.rq.BaseMQRequest;
import com.bos.mq.server.BaseWorkTask;
import com.bos.mq.server.CrmsMsg;
import com.bos.mq.util.FormatUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;
//放款余额查询 SCF->CRMS
@Bizlet("requestFromScf")
public class CRMSLoanBalInq extends BaseWorkTask implements WorkTask{

	public   Object obj;
	public CRMSLoanBalInq(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		String ContractId = ((CRMSLoanBalInqRq) obj).ContractId;
		String LoanApproveNum = ((CRMSLoanBalInqRq) obj).LoanApproveNum;
		String BizMode = ((CRMSLoanBalInqRq) obj).BizMode;
		CRMSLoanBalInqRs rs=new CRMSLoanBalInqRs();
		BOSFXII bf = new BOSFXII();
		//loan_quasi_odd
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
		// 逻辑流名称
		String operationName = "CRMSLoanBalInq";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[3];
		params[0] = ContractId;
		params[1] = LoanApproveNum;
		params[2] = BizMode;
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
				rs.setLoanSummaryId(obj.getString("LoanSummaryId"));
				rs.setOverDueFlag(obj.getString("OverDueFlag")== null ? "" : obj.getString("OverDueFlag"));
				rs.setCurrency(obj.getString("Currency"));
				rs.setLoanAmt(FormatUtil.formatDouble(obj.getDouble("LoanAmt"),2));
				rs.setLoanBal(FormatUtil.formatDouble(obj.getDouble("LoanBal"),2));
				rs.setMarginBal(FormatUtil.formatDouble(obj.getDouble("MarginBal"),2));
				rs.setIsStData(obj.getString("IsStData")== null ? "" : obj.getString("IsStData"));
				rs.setCommonRsHdr(this.success());
				}
				else {
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
