package com.bos.inter.CallCCMSInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
 * 对于接受到的外围系统的抵押物信息进行处理
 * @author menglei
 *
 */
public class CRMSCollSyn extends BaseWorkTask implements WorkTask{
	public   Object obj; 
	
	public CRMSCollSyn(Object obj) {
		this.obj = obj;
	}
	
	@Bizlet(value = "外围系统新增或修改抵押物信息 ")
	public void execute() throws Exception {
	
		CRMSCollSynRs rs = new CRMSCollSynRs();//反馈SCF信息
		BaseMQRequest bmr = new BaseMQRequest();
		BOSFXII bf = new BOSFXII();
		
		String bizNumber = ((CRMSCollSynRq) obj).RqUID_1  ;//业务流水号
		this.rsh.setRqUID(taskBean.getTaskId());
		if (  bizNumber != null &&  !"".equals(bizNumber) && !"null".equals(bizNumber)) {
			// 调用发送历史报文逻辑开始
			byte[] data = getContexts(bizNumber);
			if (data != null) {// 只会找到历史记录，否则没记录
				//bmr.mqSendHis(data, taskBean);// 重发也不记录业务流水号
												// ，以防重发也超时，记录重复的业务流水号
				rs.setCommonRsHdr(this.success());//第一次放款申请成功。bms超时。再次发起，找到成功记录。直接返回交易成功信息
				bf.scfBosfxRs = rs;
				bmr.mqSend(bf, taskBean);
				return;
			} else {// 第一次发报文
//				转换实体
				DataObject requ = DataFactory.INSTANCE.create("com.bos.inter.CallCCMSInterface.CcmsInterface", "CRMSCollSynRq");
				requ.setString("RqUID_1", ((CRMSCollSynRq)obj).RqUID_1);
				requ.setString("PartyName", ((CRMSCollSynRq)obj).PartyName);
				requ.setString("CertificateTypeCd", ((CRMSCollSynRq)obj).CertificateTypeCd);
				requ.setString("CertificateCode", ((CRMSCollSynRq)obj).CertificateCode);
				requ.setString("ApplyNum", ((CRMSCollSynRq)obj).ApplyNum);
				requ.setString("ContractId", ((CRMSCollSynRq)obj).ContractId);
				requ.setString("SubContractId", ((CRMSCollSynRq)obj).SubContractId);
				requ.setString("RegNo", ((CRMSCollSynRq)obj).RegNo);
				requ.setString("CollateralRate", ((CRMSCollSynRq)obj).CollateralRate);
				requ.setString("Currency", ((CRMSCollSynRq)obj).Currency);
				requ.setString("SuretyAmt", ((CRMSCollSynRq)obj).SuretyAmt);
				requ.setString("SuretyTotalAmt", ((CRMSCollSynRq)obj).SuretyTotalAmt);
				
				List<DataObject> collList = new ArrayList<DataObject>();
				
				if(null != ((CRMSCollSynRq)obj).CRMSCollSynRec ){
					List<CRMSCollSynRec> colls = ((CRMSCollSynRq)obj).CRMSCollSynRec;
					for(int i=0; i<colls.size(); i++){
						DataObject cashInfo = DataFactory.INSTANCE.create("com.bos.inter.CallCCMSInterface.CcmsInterface", "CRMSCollSynRec");
						CRMSCollSynRec collRec = colls.get(i);
						cashInfo.setString("SortType", collRec.SortType);
						cashInfo.setString("OutSortType", collRec.OutSortType);
						collList.add(cashInfo);
					}
				}
				requ.setList("CRMSCollSynRec", collList);
				//逻辑流的输入参数
				String componentName = "com.bos.inter.CallCCMSInterface.CcmsMaintain";
				ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
				// 逻辑流名称
				String operationName = "CRMSCollSyn";
				//逻辑流的输入参数
				Object[] params = new Object[1];
				params[0] = requ;
				
				try {
//					调用逻辑流对接受到的数据进行处理，并且接受反馈回的结果
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
