package com.bos.inter.CallBmsInterface;

import java.math.BigDecimal;
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
import com.primeton.ext.engine.component.LogicComponentFactory;
import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

//票据放款申请  SCF->CRMS
@Bizlet("ResponseToBMS")
public class CRMSLoandApp extends BaseWorkTask implements WorkTask {
	public   Object obj;

	public CRMSLoandApp(Object obj) {
		this.obj = obj;
	}

	public void execute() throws Exception {
		// TODO 自动生成方法存根
		DataObject requ = DataFactory.INSTANCE
				.create("com.bos.inter.CallBmsInterface.BmsInterface",
						"CRMSLoandAppRq");
		requ.setString("ContractId", ((CRMSLoandAppRq) obj).ContractId);
		requ.setString("AcceptNo", ((CRMSLoandAppRq) obj).AcceptNo);
		requ.setString("LoanId", ((CRMSLoandAppRq) obj).LoanId);
		requ.setBigDecimal("LoanAmt", new BigDecimal(String
				.valueOf(((CRMSLoandAppRq) obj).LoanAmt)));
		requ.setString("Currency", ((CRMSLoandAppRq) obj).Currency);
		requ.setString("ApplyDt", ((CRMSLoandAppRq) obj).ApplyDt);
		requ.setString("EndDt", ((CRMSLoandAppRq) obj).EndDt);
		requ.setString("ApplyId", ((CRMSLoandAppRq) obj).ApplyId);
		requ.setString("ApplyName", ((CRMSLoandAppRq) obj).ApplyName);
		requ.setString("ApplyOrg", ((CRMSLoandAppRq) obj).ApplyOrg);
		requ.setString("BizType", ((CRMSLoandAppRq) obj).BizType);
		String bizNumber = ((CRMSLoandAppRq) obj).RqUID_1;
		List<DataObject> draftList = new ArrayList<DataObject>();
		List<DataObject> collList = new ArrayList<DataObject>();
		CRMSLoandAppRs rs = new CRMSLoandAppRs();
		BaseMQRequest bmr = new BaseMQRequest();
		BOSFXII bf = new BOSFXII();
		this.rsh.setRqUID(taskBean.getTaskId());
		if (  bizNumber != null &&  !"".equals(bizNumber) && !"null".equals(bizNumber)) {
			// 调用发送历史报文逻辑开始
			byte[] data = getContexts(bizNumber);
			if (data != null) {// 找到历史记录
				//bmr.mqSendHis(data, taskBean);// 重发也不记录业务流水号
				// ，以防重发也超时，记录重复的业务流水号
				rs.setCommonRsHdr(this.success());//第一次放款申请成功。bms超时。再次发起，找到成功记录。直接返回交易成功信息
				bf.bmsBosfxRs = rs;
				bmr.mqSend(bf, taskBean);
				return;
			} else {// 第一次发报文
				if (((CRMSLoandAppRq) obj).CRMSLoandAppRec != null) {
					List<CRMSLoandAppRec> draftRec = ((CRMSLoandAppRq) obj).CRMSLoandAppRec;
					// 接收发票信息
					for (int i = 0; i < draftRec.size(); i++) {
						DataObject noteInfo = DataFactory.INSTANCE.create(
								"com.bos.inter.CallBmsInterface.BmsInterface",
								"CRMSLoandAppRec");
						CRMSLoandAppRec temp = (CRMSLoandAppRec) draftRec
								.get(i);
						noteInfo.setString("DraftId", temp.DraftId);
						noteInfo.setString("Amount", temp.Amount);
						noteInfo.setString("ValueDt", temp.ValueDt);
						noteInfo.setString("EndDt", temp.EndDt);
						noteInfo.setString("LoanIntRt", temp.LoanIntRt);
						noteInfo.setString("Acceptor", temp.Acceptor);
						noteInfo.setString("DraftType", temp.DraftType);
						noteInfo.setString("DraftCd", temp.DraftCd);
						draftList.add((DataObject) noteInfo);
					}
					requ.setList("CRMSLoandAppRec", draftList);
				}
				if (((CRMSLoandAppRq) obj).CRMSLoandRec != null) {
					List<CRMSLoandRec> collRec = ((CRMSLoandAppRq) obj).CRMSLoandRec;
					// 接收现金等价物信息
					for (int i = 0; i < collRec.size(); i++) {
						DataObject noteInfo = DataFactory.INSTANCE.create(
								"com.bos.inter.CallBmsInterface.BmsInterface",
								"CRMSLoandRec");
						CRMSLoandRec temp = (CRMSLoandRec) collRec.get(i);
						noteInfo.setString("AcctNo", temp.AcctNo);
						noteInfo.setString("CurrType", temp.CurrType);
						noteInfo.setString("Amt", temp.Amt);
						collList.add((DataObject) noteInfo);
					}
					requ.setList("CRMSLoandRec", collList);
				}

				// 逻辑构件路径
				String componentName = "com.bos.inter.CallBmsInterface.BmsMaintain";
				// 逻辑流名称
				String operationName = "CRMSLoandApp";
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
						bf.bmsBosfxRs = rs;
						bmr.mqSends(bf, taskBean, bizNumber);// 只有成功的情况记录业务流水号，以防找到错误的记录报文返回
					} else {
						// 返回指定响应吗
						rs.setCommonRsHdr(this.error(reCode, reMsg));
						bf.bmsBosfxRs = rs;
						bmr.mqSend(bf, taskBean);
					}
					
				} catch (Throwable e) {
					rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(),
							CrmsMsg._EXCEPTION_MSG.value()));
					bf.bmsBosfxRs = rs;
					bmr.mqSend(bf, taskBean);
					e.printStackTrace();
					throw new EOSException(e);
				}

			}
		}else {
			rs.setCommonRsHdr(this.error("100000000", "业务流水号不能为空!"));
			bf.bmsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		}

	}

}
