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
//押品信息查询  票据->CRMS
@Bizlet("ResponseToBms")
public class CRMSCollInfoInq extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public CRMSCollInfoInq(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		String T24Id = ((CRMSCollInfoInqRq) obj).T24Id== null ? "" :((CRMSCollInfoInqRq) obj).T24Id;
		String ContractId = ((CRMSCollInfoInqRq) obj).ContractId== null ? "" :((CRMSCollInfoInqRq) obj).ContractId;
		String SubContractId = ((CRMSCollInfoInqRq) obj).SubContractId== null ? "" :((CRMSCollInfoInqRq) obj).SubContractId;
		String ApproveId = ((CRMSCollInfoInqRq) obj).ApproveId== null ? "" :((CRMSCollInfoInqRq) obj).ApproveId;
		//String ProductType = ((CRMSCollInfoInqRq) obj).ProductType== null ? "" :((CRMSCollInfoInqRq) obj).ProductType;
		//String GuarantyType = ((CRMSCollInfoInqRq) obj).GuarantyType== null ? "" :((CRMSCollInfoInqRq) obj).GuarantyType;
		CRMSCollInfoInqRs rs=new CRMSCollInfoInqRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallBmsInterface.BmsMaintain";
		// 逻辑流名称
		String operationName = "CRMSCollInfoInq";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[4];
		params[0] = T24Id;
		params[1] = ContractId;
		params[2] = SubContractId;
		params[3] = ApproveId;
		BaseMQRequest bmr = new BaseMQRequest();
		List<CRMSCollInfoInqRec> rec = new ArrayList<CRMSCollInfoInqRec>();
		try{
			Object[] result = logicComponent.invoke(operationName, params);
			this.rsh.setRqUID(taskBean.getTaskId());
			DataObject obj = (DataObject) result[0];
			String marginRate=obj.getString("MarginRate");
			String reCode=obj.getDataObject("CommonRsHdr").getString("StatusCode");
			String reMsg=obj.getDataObject("CommonRsHdr").getString("ServerStatusCode");
			//判断响应信息
				if(reCode=="000000")
				{
				ContainerAwareList temp=(ContainerAwareList) obj.getList("CRMSCollInfoInqRec");
				int size=temp.size();
				rs.setNum(size);
				for(int i=0;i<size;i++){
					CRMSCollInfoInqRec tt=new CRMSCollInfoInqRec();
					DataObject recTemp =(DataObject)temp.get(i);
					tt.setSubContractId(recTemp.getString("SubContractId"));
					tt.setSubContractNum(recTemp.getString("SubContractNum"));
					tt.setSubContractType(recTemp.getString("SubContractType"));
					tt.setGuarantyType(recTemp.getString("GuarantyType"));
					tt.setGuarantyName(recTemp.getString("GuarantyName"));
					tt.setGuarantyId(recTemp.getString("GuarantyId"));
					tt.setGuarantyAvl(FormatUtil.formatDouble(recTemp.getDouble("GuarantyAvl"),2));
					tt.setMarginAcc(recTemp.getString("MarginAcc"));
					tt.setEndDt(recTemp.getString("EndDt"));
					tt.setMarginAmt(FormatUtil.formatDouble(recTemp.getDouble("MarginAmt"),2));
					tt.setMarginAccType(recTemp.getString("MarginAccType"));
					
					rec.add(tt);
				}
				rs.setMarginRate(marginRate);
				rs.setCRMSCollInfoInqRec(rec);
				rs.setCommonRsHdr(this.success());
				
			}
			else
			{
				//返回指定响应吗
				rs.setCommonRsHdr(this.error(reCode, reMsg));
			}
			bf.bmsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
		
			
		}catch (Throwable e) {
			rs.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
			bf.bmsBosfxRs = rs;
			bmr.mqSend(bf, taskBean);
			e.printStackTrace();
			throw new EOSException(e);
		}
	
	}

}
