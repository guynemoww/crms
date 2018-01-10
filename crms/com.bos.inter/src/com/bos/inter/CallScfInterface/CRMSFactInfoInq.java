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

public class CRMSFactInfoInq extends BaseWorkTask implements WorkTask {
	public   Object obj;
	public CRMSFactInfoInq(Object obj) {
		this.obj = obj;
	}

	@Bizlet(value = "")
	public void execute() throws Exception {
		String partyId = ((CRMSFactInfoInqRq) obj).CustomerNo;
		CRMSFactInfoInqRs sccr = new CRMSFactInfoInqRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件名称
		String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
		// 逻辑流名称
		String operationName = "CRMSFactInfoInq";
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
					sccr.setCorporationName(dataOb.getString("CorporationName") == null ? "" : dataOb.getString("CorporationName"));
					sccr.setBizType(dataOb.getString("BizType") == null ? "" : dataOb.getString("BizType"));
					sccr.setSfCptyCountry(dataOb.getString("SfCptyCountry") == null ? "" : dataOb.getString("SfCptyCountry"));
					sccr.setEconomize(dataOb.getString("Economize") == null ? "" : dataOb.getString("Economize"));
					sccr.setAddress(dataOb.getString("Address") == null ? "" : dataOb.getString("Address"));
					sccr.setPostCode(dataOb.getString("PostCode") == null ? "" : dataOb.getString("PostCode"));
					sccr.setFax(dataOb.getString("Fax") == null ? "" : dataOb.getString("Fax"));
					sccr.setUrl(dataOb.getString("Url") == null ? "" : dataOb.getString("Url")); 
					sccr.setLinkmanName(dataOb.getString("LinkmanName") == null ? "" : dataOb.getString("LinkmanName"));
					sccr.setLinkmanEmail(dataOb.getString("LinkmanEmail") == null ? "" : dataOb.getString("LinkmanEmail"));
					sccr.setLinkmanPhone(dataOb.getString("LinkmanPhone") == null ? "" : dataOb.getString("LinkmanPhone"));
					sccr.setFci(dataOb.getString("Fci") == null ? "" : dataOb.getString("Fci"));
					sccr.setSignContract(dataOb.getString("SignContract") == null ? "" : dataOb.getString("SignContract"));
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