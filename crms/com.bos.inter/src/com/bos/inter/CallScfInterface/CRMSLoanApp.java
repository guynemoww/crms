package com.bos.inter.CallScfInterface;

import java.math.BigDecimal;

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
 * @author ch
 * 
 */
// scf放款申请 SCF->CRMS
@Bizlet("ResponseToScf")
public class CRMSLoanApp extends BaseWorkTask implements WorkTask {
	public   Object obj;

	public CRMSLoanApp(Object obj) {
		this.obj = obj;
	}

	public void execute() throws Exception {
		// TODO 自动生成方法存根
		DataObject requ = DataFactory.INSTANCE
				.create("com.bos.inter.CallScfInterface.ScfInterfacce",
						"CRMSLoanAppRq");
		requ.setString("CustomerNo", ((CRMSLoanAppRq) obj).CustomerNo);
		requ.setString("ContractId", ((CRMSLoanAppRq) obj).ContractId);
		requ.setString("SubContractId", ((CRMSLoanAppRq) obj).SubContractId);
		requ.setBigDecimal("Amt", new BigDecimal(String
				.valueOf(((CRMSLoanAppRq) obj).Amt)));
		requ.setString("Currency", ((CRMSLoanAppRq) obj).Currency);
		requ.setString("ApplyDt", ((CRMSLoanAppRq) obj).ApplyDt);
		requ.setString("EndDt", ((CRMSLoanAppRq) obj).EndDt);
		requ.setString("LoanId", ((CRMSLoanAppRq) obj).LoanId);
		String bizNumber = ((CRMSLoanAppRq) obj).RqUID_1  ;
		BaseMQRequest bmr = new BaseMQRequest();
		CRMSLoanAppRs rs = new CRMSLoanAppRs();
		BOSFXII bf = new BOSFXII();
		this.rsh.setRqUID(taskBean.getTaskId());
		if (  bizNumber != null &&  !"".equals(bizNumber) && !"null".equals(bizNumber)) {
			// 调用发送历史报文逻辑开始
			byte[] data = getContexts(bizNumber);
			if (data != null) {// 找到历史记录
				//bmr.mqSendHis(data, taskBean);// 重发也不记录业务流水号
												// ，以防重发也超时，记录重复的业务流水号
				rs.setCommonRsHdr(this.success());//第一次放款申请成功。bms超时。再次发起，找到成功记录。直接返回交易成功信息
				bf.scfBosfxRs = rs;
				bmr.mqSend(bf, taskBean);
				return;
			} else {// 第一次发报文
				// 逻辑构件路径
				String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
				// 逻辑流名称
				String operationName = "CRMSLoanApp";
				ILogicComponent logicComponent = LogicComponentFactory
						.create(componentName);
				Object[] params = new Object[1];
				params[0] = requ;
				try {
					Object[] result = logicComponent.invoke(operationName,
							params);
					
					DataObject obj = (DataObject) result[0];
					String reCode = obj.getDataObject("CommonRsHdr").getString(
							"StatusCode");
					String reMsg = obj.getDataObject("CommonRsHdr").getString(
							"ServerStatusCode");
					// 判断响应信息
					if (reCode == "000000") {
						rs.setCommonRsHdr(this.success());
						bf.scfBosfxRs = rs;
						bmr.mqSends(bf, taskBean, bizNumber);// 只有成功的情况记录业务流水号，以防找到错误的记录报文返回
					} else {
						// 返回指定响应吗
						rs.setCommonRsHdr(this.error(reCode, reMsg));
						bf.scfBosfxRs = rs;
						bmr.mqSend(bf, taskBean);
					}
				} catch (Throwable e) {
					rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(),
							CrmsMsg._EXCEPTION_MSG.value()));
					bf.scfBosfxRs = rs;
					bmr.mqSend(bf, taskBean);
					e.printStackTrace();
					throw new EOSException(e);
				}

			}

		} else {
			rs.setCommonRsHdr(this.error("100000000", "业务流水号不能为空!"));
			bf.scfBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		}

	}
}
