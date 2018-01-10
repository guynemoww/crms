/**
 * 
 */
package com.bos.inter.CallBmsInterface;


import java.util.ArrayList;
import java.util.List;

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
 * @author shenglong
 * @date 2014-06-09 15:07:18
 *
 */
@Bizlet("ResponseToBms")
public class CRMSContrListInq extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public CRMSContrListInq(Object obj) {
		this.obj = obj;
	}
	/**
	 * @author shenglong
	 * 
	 */
	@Bizlet("")
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		BaseMQRequest bmr = new BaseMQRequest();
//		
//		//调用发送历史报文逻辑开始。。。。。
//		String rqUID = ((CRMSContrListInqRq) obj).CommonRqHdr.rqUID;
//		byte[] data = getContext(rqUID);
//		if(data != null){
//			bmr.mqSendHis(data, taskBean);
//			return;
//		}
//		//调用发送历史报文逻辑结束。。。。
//		
		String custNo = ((CRMSContrListInqRq) obj).CustNo== null ? "" :((CRMSContrListInqRq) obj).CustNo;
		String approveId = ((CRMSContrListInqRq) obj).ApproveId== null ? "" :((CRMSContrListInqRq) obj).ApproveId;
		String contractId = ((CRMSContrListInqRq) obj).ContractId== null ? "" :((CRMSContrListInqRq) obj).ContractId;
		CRMSContrListInqRs rs = new CRMSContrListInqRs();
		BOSFXII bf = new BOSFXII();
		
		// 逻辑构件名称
		String componentName = "com.bos.inter.CallBmsInterface.BmsMaintain";
		// 逻辑流名称
		String operationName = "CRMSContrListInq";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[3];
		params[0] = custNo;
		params[1] = approveId;
		params[2] = contractId;
		List<CRMSContrListInqRec> rec = new ArrayList<CRMSContrListInqRec>();
		try {
			Object[] result = logicComponent.invoke(operationName, params);
			System.out.println("1111"+result);
			this.rsh.setRqUID(taskBean.getTaskId());
			DataObject obj = (DataObject) result[0];
			String reCode=obj.getDataObject("CommonRsHdr").getString("StatusCode");
			String reMsg=obj.getDataObject("CommonRsHdr").getString("ServerStatusCode");
			//判断响应信息
				if(reCode=="000000")
				{
				ContainerAwareList temp=(ContainerAwareList) obj.getList("CRMSContrListInqRec");
				int size=temp.size();
				rs.setNum(size);
				for(int i=0;i<size;i++)
				{
					CRMSContrListInqRec tt=new CRMSContrListInqRec();
					DataObject recTemp =(DataObject)temp.get(i);
					tt.setContractId(recTemp.getString("ContractId"));
					tt.setContractType(recTemp.getString("ContractType"));
					tt.setContractTerm(recTemp.getString("ContractTerm"));
					tt.setCcy(recTemp.getString("Ccy"));
					tt.setContractAmt(FormatUtil.formatDouble(recTemp.getDouble("ContractAmt"),2));
					tt.setType(recTemp.getString("CycleType"));
					tt.setMode(recTemp.getString("Mode"));
					tt.setPartyName(recTemp.getString("PartyName"));
					tt.setT24Id(recTemp.getString("T24Id"));
					tt.setContractAvl(FormatUtil.formatDouble(recTemp.getDouble("ContractAvl"),2));
					tt.setContractBal(FormatUtil.formatDouble(recTemp.getDouble("ContractBal"),2));
					tt.setContractRate(recTemp.getString("ContractRate"));
					tt.setStartDate(recTemp.getString("StartDate"));
					tt.setEndDate(recTemp.getString("EndDate"));
					tt.setFlag(recTemp.getString("Flag"));
					tt.setAcctNo(recTemp.getString("AcctNo"));
					tt.setPayeeAccNo(recTemp.getString("PayeeAccNo"));
					tt.setName1(recTemp.getString("Name1"));
					rec.add(tt);
				}
				rs.setCRMSContrListInqRec(rec);
				rs.setCommonRsHdr(this.success());
			}else
			{
				//返回指定响应吗
				rs.setCommonRsHdr(this.error(reCode, reMsg));
			}
			bf.bmsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		} catch (Throwable e) {
			rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
			bf.bmsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
			e.printStackTrace();
			throw new EOSException(e);
		}
	}

}
