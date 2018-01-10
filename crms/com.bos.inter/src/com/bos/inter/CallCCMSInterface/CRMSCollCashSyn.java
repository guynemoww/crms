package com.bos.inter.CallCCMSInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bos.inter.CallBmsInterface.CRMSLoandAppRs;
import com.bos.inter.CallScfInterface.CRMSLoanAppRq;
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
 * 对于新增保证金的请求的处理逻辑
 * @author menglei
 *
 */
public class CRMSCollCashSyn  extends BaseWorkTask implements WorkTask{
	public   Object obj; 
	
	public CRMSCollCashSyn(Object obj) {
		this.obj = obj;
	}
	
	@Bizlet(value = "SCF系统新增保证金的处理逻辑")
	public void execute() throws Exception {
		//转换实体
		DataObject requ = DataFactory.INSTANCE.create("com.bos.inter.CallCCMSInterface.CcmsInterface", "CRMSCollCashSynRq");
		CRMSCollCashSynRs rs=new CRMSCollCashSynRs();
		BaseMQRequest bmr = new BaseMQRequest();
		BOSFXII bf = new BOSFXII();
		String RqUID_1 = ((CRMSCollCashSynRq) obj).RqUID_1;
		String bizNumber = ((CRMSCollCashSynRq) obj).RqUID_1  ;//业务流水号
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
//				 逻辑构件名称
				String componentName = "com.bos.inter.CallCCMSInterface.CcmsMaintain";
				// 逻辑流名称
				String operationName = "CRMSCollCashSyn";
				ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
				List<DataObject> cashList = new ArrayList<DataObject>();
				if(((CRMSCollCashSynRq) obj).CRMSCollCashSynRec!= null){
					List<CRMSCollCashSynRec> cashRecs = ((CRMSCollCashSynRq) obj).CRMSCollCashSynRec;
					for(int i=0; i<cashRecs.size(); i++){
						//循环接收现金等价物信息
						DataObject cashInfo = DataFactory.INSTANCE.create("com.bos.inter.CallCCMSInterface.CcmsInterface", "CRMSCollCashSynRec");
						CRMSCollCashSynRec cashRecInfo = (CRMSCollCashSynRec)cashRecs.get(i);
						cashInfo.setString("LoanId", cashRecInfo.LoanId);
						cashInfo.setString("ContractId", cashRecInfo.ContractId);
						cashInfo.setString("Type", cashRecInfo.Type);
						cashInfo.setString("AcctNo", cashRecInfo.AcctNo);
						cashInfo.setString("Currency", cashRecInfo.Currency);
						cashInfo.setString("SuretyAmt", cashRecInfo.SuretyAmt);
						cashInfo.setString("MarginType", cashRecInfo.MarginType);
						cashInfo.setString("MarginRation", cashRecInfo.MarginRation);
						cashInfo.setString("OperateType", cashRecInfo.OperateType);
						cashList.add(cashInfo);
					}
				}
				
				requ.setString("RqUID_1", RqUID_1);
				requ.setList("CRMSCollCashSynRec", cashList);
				//	 逻辑流的输入参数
				Object[] params = new Object[1];
				params[0] = requ;
				try {
					//调用逻辑流对接受到的数据进行处理，并且接受反馈回的结果
					Object[] result = logicComponent.invoke(operationName, params);//通过逻辑流存储对象
					
					Map<String,String> obj = (Map<String,String>) result[0];
					String reCode=obj.get("StatusCode");
					String reMsg=obj.get("ServerStatusCode");
					if (reCode=="000000"){
						rs.setCommonRsHdr(this.success());
						bf.scfBosfxRs = rs;
						bmr.mqSends(bf, taskBean, bizNumber);// 只有成功的情况记录业务流水号，以防找到错误的记录报文返回
					}else {
						rs.setCommonRsHdr(this.error(reCode, reMsg));
						bf.scfBosfxRs = rs;
						bmr.mqSend(bf, taskBean);
					}
					
				} catch (Throwable e) {
					rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
					bf.scfBosfxRs = rs;
					bmr.mqSend(bf, taskBean);
					e.printStackTrace();
					throw new EOSException(e);
				}

			}
		}else {
			rs.setCommonRsHdr(this.error("100000000", "业务流水号不能为空!"));
			bf.scfBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		}
		
	}
}
