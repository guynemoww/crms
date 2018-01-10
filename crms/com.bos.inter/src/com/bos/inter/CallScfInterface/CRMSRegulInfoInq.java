package com.bos.inter.CallScfInterface;

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

public class CRMSRegulInfoInq extends BaseWorkTask implements WorkTask {
	public   Object obj;
	public CRMSRegulInfoInq(Object obj) {
		this.obj = obj;
	}

	@Bizlet(value = "")
	public void execute() throws Exception {
		String partyId = ((CRMSRegulInfoInqRq) obj).CustomerNo;
		CRMSRegulInfoInqRs sccr = new CRMSRegulInfoInqRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件名称
		String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
		// 逻辑流名称
		String operationName = "CRMSRegulInfoInq";
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		// 逻辑流的输入参数
		Object[] params = new Object[1];
		params[0] = partyId;
		BaseMQRequest bmr = new BaseMQRequest();
		try {
			Object[] result = logicComponent.invoke(operationName, params);
			this.rsh.setRqUID(taskBean.getTaskId());
			if (result != null && result.length > 0) {
				DataObject dataOb = (DataObject) result[0];
				String code = (String)result[1] ;
				String msg = (String)result[2] ;
				if("000000".equals(code)){
					sccr.setCustomerNo(dataOb.getString("CustomerNo") == null ? "" : dataOb.getString("CustomerNo"));
					sccr.setCorporationNum(dataOb.getString("CorporationNum") == null ? "" : dataOb.getString("CorporationNum"));
					sccr.setCompLevel(dataOb.getString("CompLevel") == null ? "" : dataOb.getString("CompLevel"));
					sccr.setSuperOrgenName(dataOb.getString("SuperOrgenName") == null ? "" : dataOb.getString("SuperOrgenName"));
					sccr.setHeadCompNo(dataOb.getString("HeadCompNo") == null ? "" : dataOb.getString("HeadCompNo"));
					sccr.setHeadCompName(dataOb.getString("HeadCompName") == null ? "" : dataOb.getString("HeadCompName"));
					sccr.setAddress(dataOb.getString("Address") == null ? "" : dataOb.getString("Address"));
					sccr.setProductType(dataOb.getString("ProductType") == null ? "" : dataOb.getString("ProductType"));
					sccr.setPostCode(dataOb.getString("PostCode") == null ? "" : dataOb.getString("PostCode"));
					sccr.setFax(dataOb.getString("Fax") == null ? "" : dataOb.getString("Fax"));
					sccr.setLinkmanName(dataOb.getString("LinkmanName") == null ? "" : dataOb.getString("LinkmanName"));
					sccr.setUrl(dataOb.getString("Url") == null ? "" : dataOb.getString("Url"));
					sccr.setLinkmanEmail(dataOb.getString("LinkmanEmail") == null ? "" : dataOb.getString("LinkmanEmail"));
					sccr.setLinkmanPhone(dataOb.getString("LinkmanPhone") == null ? "" : dataOb.getString("LinkmanPhone"));
					sccr.setSignContract(dataOb.getString("SignContract") == null ? "" : dataOb.getString("SignContract"));
					sccr.setContactNo(dataOb.getString("ContactNo") == null ? "" : dataOb.getString("ContactNo"));
					sccr.setStartDt(dataOb.getString("StartDt") == null ? "" : dataOb.getString("StartDt"));
					sccr.setEndDate(dataOb.getString("EndDate") == null ? "" : dataOb.getString("EndDate"));
					sccr.setMemo(dataOb.getString("Memo") == null ? "" : dataOb.getString("Memo"));
								
					sccr.setCommonRsHdr(this.success());
				}else{
					sccr.setCommonRsHdr(this.error(code,msg));
					
				}
			} else {
				sccr.setCommonRsHdr(this.error("100001", "客户信息不存在"));
			}
			bf.scfBosfxRs = sccr;
			bmr.mqSend(bf, taskBean);
		} catch (Throwable e) {
			sccr.setCommonRsHdr(this.error(CrmsMsg._EXCEPTION.value(), CrmsMsg._EXCEPTION_MSG.value()));
			bf.scfBosfxRs = sccr;
			bmr.mqSend(bf, taskBean);
			e.printStackTrace();
			throw new EOSException(e);
		}
	}
}
