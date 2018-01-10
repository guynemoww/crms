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

public class CRMSCustInfoInq extends BaseWorkTask implements WorkTask {
	public   Object obj;
	public CRMSCustInfoInq(Object obj) {
		this.obj = obj;
	}

	@Bizlet(value = "")
	public void execute() throws Exception {
		String partyId = ((CRMSCustInfoInqRq) obj).CustNo;
		CRMSCustInfoInqRs sccr = new CRMSCustInfoInqRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件名称
		String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
		// 逻辑流名称
		String operationName = "CRMSCustInfoInq";
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
					sccr.setCustNo(dataOb.getString("ecifId") == null ? "" : dataOb.getString("ecifId"));
					sccr.setCustomerNo(dataOb.getString("partyNum") == null ? "" : dataOb.getString("partyNum"));
					sccr.setCustomerName(dataOb.getString("partyName") == null ? "" : dataOb.getString("partyName"));
					sccr.setOrginstCode(dataOb.getString("orgnNum") == null ? "" : dataOb.getString("orgnNum"));
					sccr.setType(dataOb.getString("economicCategoriesCode") == null ? "" : dataOb.getString("economicCategoriesCode"));
					sccr.setInterSduClass(dataOb.getString("industrialTypeCd") == null ? "" : dataOb.getString("industrialTypeCd"));
					sccr.setMedSmEntFlg(dataOb.getString("enterpriseSizeCd") == null ? "" : dataOb.getString("enterpriseSizeCd"));
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
