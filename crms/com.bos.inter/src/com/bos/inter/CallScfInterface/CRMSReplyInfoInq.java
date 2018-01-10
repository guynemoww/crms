package com.bos.inter.CallScfInterface;

import java.util.ArrayList;
import java.util.List;

import com.bos.inter.CallBmsInterface.CRMSCollInfoInqRec;
import com.bos.inter.CallBmsInterface.CRMSCollInfoInqRq;
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
@Bizlet("ResponseToSCF")
public class CRMSReplyInfoInq extends BaseWorkTask implements WorkTask{
	public   Object obj;
	public CRMSReplyInfoInq(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		String CustomerNo = ((CRMSReplyInfoInqRq) obj).CustomerNo== null ? "" :((CRMSReplyInfoInqRq) obj).CustomerNo;
		String BizMode = ((CRMSReplyInfoInqRq) obj).BizMode== null ? "" :((CRMSReplyInfoInqRq) obj).BizMode;
		CRMSReplyInfoInqRs rs=new CRMSReplyInfoInqRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
		// 逻辑流名称
		String operationName = "CRMSReplyInfoInq";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		Object[] params = new Object[2];
		params[0] = CustomerNo;
		params[1] = BizMode;
		BaseMQRequest bmr = new BaseMQRequest();
		List<CRMSReplyInfoInqRec> rec = new ArrayList<CRMSReplyInfoInqRec>();
		try{
			Object[] result = logicComponent.invoke(operationName, params);
			this.rsh.setRqUID(taskBean.getTaskId());
			DataObject obj = (DataObject) result[0];
			String reCode=obj.getDataObject("CommonRsHdr").getString("StatusCode");
			String reMsg=obj.getDataObject("CommonRsHdr").getString("ServerStatusCode");
				//判断响应信息
				if(reCode=="000000")
				{
				ContainerAwareList temp=(ContainerAwareList) obj.getList("CRMSReplyInfoInqRec");
				for(int i=0;i<temp.size();i++){
					CRMSReplyInfoInqRec tt=new CRMSReplyInfoInqRec();
					DataObject recTemp =(DataObject)temp.get(i);
					tt.setCustNo(recTemp.getString("CustNo"));
					tt.setCustomerNo(recTemp.getString("CustomerNo"));
					tt.setApproveId(recTemp.getString("ApproveId"));
					tt.setApproveType(recTemp.getString("ApproveType"));
					tt.setBizMode(recTemp.getString("BizMode"));
					tt.setCurrency(recTemp.getString("Currency"));
					tt.setProductAmt(FormatUtil.formatDouble(recTemp.getDouble("ProductAmt"),2));
					tt.setDateBegin(recTemp.getString("DateBegin"));
					tt.setLimitEnd(recTemp.getString("LimitEnd"));
					tt.setProductType(recTemp.getString("ProductType"));
					rec.add(tt);
				}
				rs.setCRMSReplyInfoInqRec(rec);
				rs.setCommonRsHdr(this.success());
				}
				else
				{
					//返回指定响应吗
					rs.setCommonRsHdr(this.error(reCode, reMsg));
				}
			bf.scfBosfxRs = rs;
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
