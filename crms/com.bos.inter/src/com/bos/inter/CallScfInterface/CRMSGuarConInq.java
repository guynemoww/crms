package com.bos.inter.CallScfInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//担保合同查询  SCF->CRMS/ 票据->CRMS
@Bizlet("ResponseToSCF")
public class CRMSGuarConInq extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public CRMSGuarConInq(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		String CustomerNo = ((CRMSGuarConInqRq) obj).CustomerNo== null ? "" :((CRMSGuarConInqRq) obj).CustomerNo;
		String CustNo = ((CRMSGuarConInqRq) obj).CustNo== null ? "" :((CRMSGuarConInqRq) obj).CustNo;
		String ApproveId = ((CRMSGuarConInqRq) obj).ApproveId== null ? "" :((CRMSGuarConInqRq) obj).ApproveId;
		String ProductType = ((CRMSGuarConInqRq) obj).ProductType== null ? "" :((CRMSGuarConInqRq) obj).ProductType;
		String ContractId = ((CRMSGuarConInqRq) obj).ContractId== null ? "" :((CRMSGuarConInqRq) obj).ContractId;
		CRMSGuarConInqRs rs=new CRMSGuarConInqRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
		// 逻辑流名称
		String operationName = "CRMSGuarConInq";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[5];
		params[0] = CustomerNo;
		params[1] = CustNo;
		params[2] = ApproveId;
		params[3] = ProductType;
		params[4] = ContractId;
		BaseMQRequest bmr = new BaseMQRequest();
		List<CRMSGuarConInqRec> rec = new ArrayList<CRMSGuarConInqRec>();
		try{
			Object[] result = logicComponent.invoke(operationName, params);
			this.rsh.setRqUID(taskBean.getTaskId());
				DataObject obj = (DataObject) result[0];
				Map<String,String> objs = (Map<String,String>) result[1];
				String reCode=objs.get("StatusCode");
				String reMsg=objs.get("ServerStatusCode");
				//判断响应信息
				if(reCode=="000000")
				{
				ContainerAwareList temp=(ContainerAwareList) obj.getList("CRMSGuarConInqRec");
				for(int i=0;i<temp.size();i++){
					CRMSGuarConInqRec tt=new CRMSGuarConInqRec();
					DataObject recTemp =(DataObject)temp.get(i);
					tt.setAmt(recTemp.getString("Amt"));
					tt.setContractId(recTemp.getString("ContractId"));
					tt.setContractNo(recTemp.getString("ContractNo"));
					tt.setCurrency(recTemp.getString("Currency"));
					tt.setDateBegin(recTemp.getString("DateBegin"));
					tt.setEndDate(recTemp.getString("EndDate"));
					tt.setPeopleName(recTemp.getString("PeopleName"));
					tt.setPeopleNo(recTemp.getString("PeopleNo"));
					tt.setSubContractId(recTemp.getString("SubContractId"));
					tt.setSubContractManualNum(recTemp.getString("SubContractManualNum"));
					tt.setGuaranteeRate(recTemp.getString("GuaranteeRate"));
					rec.add(tt);
				}
				rs.setCRMSGuarConInqRec(rec);
				rs.setCommonRsHdr(this.success());
			}
			else{
				//返回指定响应吗
				rs.setCommonRsHdr(this.error(reCode, reMsg));
			}
			bf.scfBosfxRs=rs;
			bmr.mqSend(bf, taskBean);
		}catch(Throwable e){
			rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
			bf.scfBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
			e.printStackTrace();
			throw new EOSException(e);	
		}
		
	}

}
