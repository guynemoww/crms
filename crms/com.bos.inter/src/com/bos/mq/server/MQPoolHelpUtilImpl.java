package com.bos.mq.server;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.bos.inter.CallBmsInterface.CRMSCollInfoInq;
import com.bos.inter.CallBmsInterface.CRMSCollInfoInqRq;
import com.bos.inter.CallBmsInterface.CRMSContrListInq;
import com.bos.inter.CallBmsInterface.CRMSContrListInqRq;
import com.bos.inter.CallBmsInterface.CRMSCreRestInset;
import com.bos.inter.CallBmsInterface.CRMSCreRestInsetRq;
import com.bos.inter.CallBmsInterface.CRMSLoanBackNot;
import com.bos.inter.CallBmsInterface.CRMSLoanBackNotRq;
import com.bos.inter.CallBmsInterface.CRMSLoandApp;
import com.bos.inter.CallBmsInterface.CRMSLoandAppRq;
import com.bos.inter.CallCAMSInterface.CRMSLoanStaInq;
import com.bos.inter.CallCAMSInterface.CRMSLoanStaInqRq;
import com.bos.inter.CallCAMSInterface.CRMSTStLdStdAaa;
import com.bos.inter.CallCAMSInterface.CRMSTStLdStdAaaRq;
import com.bos.inter.CallCCMSInterface.CRMSCollCashSyn;
import com.bos.inter.CallCCMSInterface.CRMSCollCashSynRq;
import com.bos.inter.CallCCMSInterface.CRMSCollInBackRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollInRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollOutRigRq;
import com.bos.inter.CallCCMSInterface.CRMSCollSyn;
import com.bos.inter.CallCCMSInterface.CRMSCollSynRq;
import com.bos.inter.CallCRMInterface.CRMSCreEncKey;
import com.bos.inter.CallCRMInterface.CRMSCreEncKeyRq;
import com.bos.inter.CallScfInterface.CRMSCountInfoInq;
import com.bos.inter.CallScfInterface.CRMSCountInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSCustInfoInq;
import com.bos.inter.CallScfInterface.CRMSCustInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSFactInfoInq;
import com.bos.inter.CallScfInterface.CRMSFactInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSGuarConInq;
import com.bos.inter.CallScfInterface.CRMSGuarConInqRq;
import com.bos.inter.CallScfInterface.CRMSInsuComInfoInq;
import com.bos.inter.CallScfInterface.CRMSInsuComInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSLoanApp;
import com.bos.inter.CallScfInterface.CRMSLoanAppCancel;
import com.bos.inter.CallScfInterface.CRMSLoanAppCancelRq;
import com.bos.inter.CallScfInterface.CRMSLoanAppRev;
import com.bos.inter.CallScfInterface.CRMSLoanAppRevRq;
import com.bos.inter.CallScfInterface.CRMSLoanAppRq;
import com.bos.inter.CallScfInterface.CRMSLoanBack;
import com.bos.inter.CallScfInterface.CRMSLoanBackRq;
import com.bos.inter.CallScfInterface.CRMSLoanBalInq;
import com.bos.inter.CallScfInterface.CRMSLoanBalInqRq;
import com.bos.inter.CallScfInterface.CRMSRegulInfoInq;
import com.bos.inter.CallScfInterface.CRMSRegulInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSReplyInfoInq;
import com.bos.inter.CallScfInterface.CRMSReplyInfoInqRq;
import com.bos.inter.CallScfInterface.CRMSWareInfoInq;
import com.bos.inter.CallScfInterface.CRMSWareInfoInqRq;
import com.bos.inter.CallT24Interface.TDpAcctAllInq;
import com.bos.inter.CallT24Interface.TDpAcctAllInqRq;
import com.bos.inter.CallT24Interface.TExCurrencyAllInq;
import com.bos.inter.CallT24Interface.TExCurrencyAllInqRq;
import com.bos.jaxb.JAXBUtil;
import com.bos.jaxb.javabean.BOSFXII;
import com.bos.jaxb.javabean.CommonRqHdr;
import com.bos.mq.client.BaseMQ;
import com.bos.mq.client.MQbean;
import com.bos.mq.client.Receiver;
import com.eos.spring.TraceLogger;
import com.git.easyetl.threadpool.common.PoolHelpUtil;
import com.git.easyetl.threadpool.common.TaskBean;
import com.git.easyetl.threadpool.task.WorkTask;
import com.ibm.mq.MQException;

public class MQPoolHelpUtilImpl extends BaseMQ implements PoolHelpUtil {
	static TraceLogger log = new TraceLogger(MQPoolHelpUtilImpl.class);
	public List<WorkTask> getTaskList() {
		List<WorkTask> taskList = new ArrayList<WorkTask>();
		// TODO 自动生成方法存根
		BOSFXII rebf = new BOSFXII();
		MQbean mqbean = getMQParameter();
		Receiver receiver = new Receiver();
		try {
			log.info("作为服务端响应开始: MQbean relationId = " + mqbean.getRelationid());
//			while(true){
				List<MQbean> reList = receiver.receiveAll(mqbean);
				for (MQbean qbean : reList) {
					TaskBean taskBean = new TaskBean();
					WorkTask task = null;
					byte[] redata = qbean.getContext();
					
					rebf = (BOSFXII)JAXBUtil.unmarshal(redata, BOSFXII.class);
					if(rebf.scfBosfxRq != null){
						log.info("消息类型 ：" + rebf.scfBosfxRq);
					}
					if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSCustInfoInqRq){
						//授信客户
						task = new CRMSCustInfoInq(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSCustInfoInqRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSRegulInfoInqRq){
						//监管机构
						task = new CRMSRegulInfoInq(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSRegulInfoInqRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSInsuComInfoInqRq){
						//保险公司
						task = new CRMSInsuComInfoInq(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSInsuComInfoInqRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSWareInfoInqRq){
						//仓库信息
						task = new CRMSWareInfoInq(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSWareInfoInqRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSFactInfoInqRq){
						//保理商
						task = new CRMSFactInfoInq(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSFactInfoInqRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSCountInfoInqRq){
						//交易对手
						task = new CRMSCountInfoInq(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSCountInfoInqRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSReplyInfoInqRq){
						//批复信息查询 
						task = new CRMSReplyInfoInq(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSReplyInfoInqRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					
					else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSGuarConInqRq){
						//担保合同查询
						task = new CRMSGuarConInq(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSGuarConInqRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSLoanAppRq){
						//放款申请
						task = new CRMSLoanApp(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSLoanAppRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSCollSynRq){
						//押品同步
						task = new CRMSCollSyn(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSCollSynRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSCollCashSynRq){
						//现金等价物同步
						task = new CRMSCollCashSyn(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSCollCashSynRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSLoanBackRq){
						//放款回退通知
						task = new CRMSLoanBack(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSLoanBackRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSLoanAppCancelRq){
						//放款申请取消
						task = new CRMSLoanAppCancel(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSLoanAppCancelRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSLoanAppRevRq){
						//放款申请撤销
						task = new CRMSLoanAppRev(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSLoanAppRevRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.scfBosfxRq != null && rebf.scfBosfxRq instanceof CRMSLoanBalInqRq){
						//放款余额查询
						task = new CRMSLoanBalInq(rebf.scfBosfxRq);
						taskBean.setTaskId(((CRMSLoanBalInqRq)rebf.scfBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("SCF");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					//票据
					else if(rebf.bmsBosfxRq != null && rebf.bmsBosfxRq instanceof CRMSContrListInqRq){
						//合同清单查询
						task = new CRMSContrListInq(rebf.bmsBosfxRq);
						taskBean.setTaskId(((CRMSContrListInqRq)rebf.bmsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("BMS");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.bmsBosfxRq != null && rebf.bmsBosfxRq instanceof CRMSCollInfoInqRq){
						//押品信息查询
						task = new CRMSCollInfoInq(rebf.bmsBosfxRq);
						taskBean.setTaskId(((CRMSCollInfoInqRq)rebf.bmsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("BMS");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.bmsBosfxRq != null && rebf.bmsBosfxRq instanceof CRMSLoandAppRq){
						//放款申请
						task = new CRMSLoandApp(rebf.bmsBosfxRq);
						taskBean.setTaskId(((CRMSLoandAppRq)rebf.bmsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("BMS");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.bmsBosfxRq != null && rebf.bmsBosfxRq instanceof CRMSLoanBackNotRq){
						//放款回退通知
						task = new CRMSLoanBackNot(rebf.bmsBosfxRq);
						taskBean.setTaskId(((CRMSLoanBackNotRq)rebf.bmsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("BMS");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}else if(rebf.bmsBosfxRq != null && rebf.bmsBosfxRq instanceof CRMSCreRestInsetRq){
						//额度恢复接口
						task = new CRMSCreRestInset(rebf.bmsBosfxRq);
						taskBean.setTaskId(((CRMSCreRestInsetRq)rebf.bmsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("BMS");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					//现金管理平台
					else if(rebf.camsBosfxRq!= null && rebf.camsBosfxRq instanceof CRMSTStLdStdAaaRq){
						//一般贷款发放
						task = new CRMSTStLdStdAaa(rebf.camsBosfxRq);
						taskBean.setTaskId(((CRMSTStLdStdAaaRq)rebf.camsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("CAMS");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.camsBosfxRq!= null && rebf.camsBosfxRq instanceof CRMSLoanStaInqRq){
						//放款状态查询
						task = new CRMSLoanStaInq(rebf.camsBosfxRq);
						taskBean.setTaskId(((CRMSLoanStaInqRq)rebf.camsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("CAMS");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					//t24
					else if(rebf.t24BosfxRq!= null && rebf.t24BosfxRq instanceof TDpAcctAllInqRq){
						//账户信息查询
						task = new TDpAcctAllInq(rebf.t24BosfxRq);
						taskBean.setTaskId(((TDpAcctAllInqRq)rebf.t24BosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("T24");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					else if(rebf.t24BosfxRq!= null && rebf.t24BosfxRq instanceof TExCurrencyAllInqRq){
						//币种汇率
						task = new TExCurrencyAllInq(rebf.t24BosfxRq);
						taskBean.setTaskId(((TExCurrencyAllInqRq)rebf.t24BosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("T24");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
				   // else if(rebf.t24BosfxRq != null && rebf.t24BosfxRq instanceof TStLdStatusAaaRq){
						//授信状态通知
						//task = new ResponseToT24(rebf.t24BosfxRq);
						//taskBean.setTaskId(((TStLdStatusAaaRq)rebf.t24BosfxRq).CommonRqHdr.rqUID);
						//taskBean.setUuid(qbean.getRelationid());
						//task.setTaskBean(taskBean);
						//task.setTaskThreadKey("DEFAULT");
					//}
				//else if(rebf.t24BosfxRq != null && rebf.t24BosfxRq instanceof TStLdRpmamtAaaRq){
						//提前还款
						//task = new AdvanceRepayFromT24(rebf.t24BosfxRq);
						//taskBean.setTaskId(((TStLdRpmamtAaaRq)rebf.t24BosfxRq).CommonRqHdr.rqUID);
						//taskBean.setUuid(qbean.getRelationid());
						//task.setTaskBean(taskBean);
						//task.setTaskThreadKey("DEFAULT");
					//}
				else if(rebf.ccmsBosfxRq != null && rebf.ccmsBosfxRq instanceof CRMSCollInRigRq){
						//押品入库
						//task = new AdvanceRepayFromT24(rebf.ccmsBosfxRq);
						//taskBean.setTaskId(((TStLdRpmamtAaaRq)rebf.ccmsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}else if(rebf.ccmsBosfxRq != null && rebf.ccmsBosfxRq instanceof CRMSCollOutRigRq){
						//押品出库
						//task = new AdvanceRepayFromT24(rebf.ccmsBosfxRq);
						//taskBean.setTaskId(((TStLdRpmamtAaaRq)rebf.ccmsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}else if(rebf.ccmsBosfxRq != null && rebf.ccmsBosfxRq instanceof CRMSCollInBackRigRq){
						//押品出入库撤销
						//task = new AdvanceRepayFromT24(rebf.ccmsBosfxRq);
						//taskBean.setTaskId(((TStLdRpmamtAaaRq)rebf.ccmsBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}else if(rebf.crmBosfxRq != null && rebf.crmBosfxRq instanceof CRMSCreEncKeyRq){
						//crms单点登录
						task = new CRMSCreEncKey(rebf.crmBosfxRq);
						taskBean.setTaskId(((CRMSCreEncKeyRq)rebf.crmBosfxRq).CommonRqHdr.rqUID);
						taskBean.setUuid(qbean.getRelationid());
						taskBean.setTaskType("CRM");
						task.setTaskBean(taskBean);
						task.setTaskThreadKey("DEFAULT");
					}
					
					else{
						System.out.println("该交易未定义！");
					}
					if(task != null){
						taskList.add(task);
					}
				}
//			}
		} catch (MQException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return taskList;
	}

	public void updateTask(TaskBean arg0) {
		// TODO 自动生成方法存根
	}
	
	
    private CommonRqHdr getRqHdr(BOSFXII bosfxii) throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException{
    	Field[] fields = bosfxii.getClass().getDeclaredFields();
    	for(Field field:fields){   
    		if(!"serialVersionUID".equals(field.getName())){
	    		field.setAccessible(true); // 设置些属性是可以访问的
	    		Object o = field.get(bosfxii);
	    		if(o != null){
	    			return (CommonRqHdr)o.getClass().getField("CommonRqHdr").get(o);
	    		}
    		}
    	}
    	return null;
    }
}
