package com.bos.inter.CallScfInterface;

import com.bos.jaxb.javabean.BOSFXII;
import com.bos.mq.server.BaseWorkTask;
import com.eos.system.annotation.Bizlet;
import com.git.easyetl.threadpool.task.WorkTask;
@Bizlet("RequstToscf")
public class CRMSLoanBack extends BaseWorkTask implements WorkTask{

	public   Object obj;
	public CRMSLoanBack(Object obj) {
		this.obj = obj;
	}
	public void execute() throws Exception {
		// TODO 自动生成方法存根
		CRMSReplyInfoInqRs rs=new CRMSReplyInfoInqRs();
		BOSFXII bf = new BOSFXII();
		// 逻辑构件路径
		String componentName = "com.bos.inter.CallScfInterface.ScfMaintain";
		// 逻辑流名称
		String operationName = "CRMSLoanApp";
		
	}

}
