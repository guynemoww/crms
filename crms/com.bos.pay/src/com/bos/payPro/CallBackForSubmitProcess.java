package com.bos.payPro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.inter.LoanToLcs;
import com.bos.pub.GitUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

public class CallBackForSubmitProcess implements IBIZProcess {
	
	public static TraceLogger logger = new TraceLogger(CallBackForSubmitProcess.class);
	
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		try {
			List<Object> list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String loanId=(String)list.get(0);
				if(null==loanId||"".equals(loanId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
					
	logger.info("------>3231------>出账流程提交,修改出账状态------bizId="+loanId+"------->begin!");
				DataObject loanInfo = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanInfo");
				loanInfo.set("loanId", loanId);
				loanInfo.set("loanStatus", "02");
				DatabaseUtil.updateEntity("default", loanInfo);
	logger.info("------>3231------>出账流程提交,修改出账状态------bizId="+loanId+"------->end!");
			
	logger.info("------>3231------出账流程提交,与计量系统第一次交互------bizId="+loanId+"------>begin!");
				DatabaseUtil.expandEntity("default", loanInfo);
				String productType = (String)loanInfo.get("productType"); 
	logger.info("------>3231------贷种------productType="+productType+"------>begin!");		
				if("01007007".equals(productType)||"01007008".equals(productType)||
				   "01007002".equals(productType)||"01008001".equals(productType)||"01008002".equals(productType)||
				   "01009".equals(productType.substring(0, 5))||"01010001".equals(productType)||
				   "01011001".equals(productType)||"01012001".equals(productType)||"01004001".equals(productType)||
				   "01006001".equals(productType)||"01006002".equals(productType)||"01007009".equals(productType)||"01007010".equals(productType)||
				   "01007012".equals(productType)||"01007013".equals(productType)||"01007014".equals(productType)
				   ||"01006010".equals(productType)|| "01009010".equals(productType)||"01008010".equals(productType)  //村镇银行贴现产品
						){
					
				}else{
					try {
						LoanToLcs ll = new LoanToLcs();
						ll.loanToLcs1(loanId);
					} catch (Exception e) {
						e.printStackTrace();
						throw new EOSException(e.getMessage());
					}
					
				}
				
	logger.info("------>3231------出账流程提交,与计量系统第一次交互------bizId="+loanId+"------>end!");
			//throw new EOSException(obj.toString());
		}catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {

		// TODO 自动生成的方法存根
		String[] xpath={"bizId"};//获取相关数据的数组
		List<Object> list;
			try {
				list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取贷款id
				String loanId=(String)list.get(0);
				if(null==loanId||"".equals(loanId)){
					logger.info("出账撤销时，返回bizID为空！");
					throw new EOSException("出账撤销时，返回bizID为空！");
				}
		logger.info("------3231------>合同流程撤销，开始业务状态为06------bizId="+loanId+"------->begin!");
			DataObject loanInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			loanInfo.set("loanStatus", "06");
			DatabaseUtil.updateEntity("default", loanInfo);
		logger.info("------3231------>合同流程撤销，开始业务状态为06------bizId="+loanId+"------->end!");
			} catch (WFServiceException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		Map<String, String> resultMap = new HashMap<String, String>();
		String[] xpath={"bizId"};//获取相关数据的数组
			List<Object> list;
			try {
				list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				//获取贷款id
				String loanId=(String)list.get(0);
				if(null==loanId||"".equals(loanId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				DataObject loanInfo = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanInfo");
				loanInfo.set("loanId", loanId);
				DatabaseUtil.expandEntity("default", loanInfo);
				//如果页面没有保存，报错
				if(loanInfo.get("updateTime")==null){
					resultMap.put("errorNum", "2");
			    	resultMap.put("errorCode", "2");
			    	resultMap.put("errorContent", "请先保存放款信息");
			    	return resultMap;
				}
				
				RuleService rs = new RuleService();
				Map paramMap = new HashMap();
				paramMap.put("loanId", loanId);
				paramMap.put("contractId", loanInfo.get("contractId"));
				
//				List<MessageObj> msgList = rs.runRule("RLON_0031", paramMap);
//				String msg = convertMsg(msgList);
//				if (!"true".equals(msg)) {
//					msg = msg.replace("#当前业务日期#", GitUtil.getBusiDateYYYYMMDD());
//					resultMap.put("errorNum", "2");
//					resultMap.put("errorCode", "2");
//					resultMap.put("errorContent", msg);
//					return resultMap;
//				}
//				//合同循环业务期限不得超过一年  ----需求变更去掉改校验
//				
//				List<MessageObj> msgList = rs.runRule("RLON_0003", paramMap);
//		        String msg = convertMsg(msgList);
//		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
//		        	resultMap.put("errorNum", "2");
//		        	resultMap.put("errorCode", "2");
//		        	resultMap.put("errorContent", msg);
//		        	return resultMap;
//		        }
				
		        //小微未保存贴息信息
				List<MessageObj> msgList = rs.runRule("RLON_0005", paramMap);
				String msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		        //小微贴息止期大于贷款止期
		        msgList = rs.runRule("RLON_0006", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		        //小微贴息止期大于超出贴息期限
		        msgList = rs.runRule("RLON_0007", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		        /*//除了垫款外其他贷款账户开户机构和出账机构要一致----去掉校验
		        msgList = rs.runRule("RLON_0009", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }*/
		        //还本计划表校验
		        String repayType = "1200";//没有还款方式的默认1200
		        if(loanInfo.get("repayType")!=null && !"".equals(loanInfo.get("repayType"))){
		        	repayType = (String)loanInfo.get("repayType");
		        }
		        if("1400".equals(repayType)||"1410".equals(repayType)) {
		        	//还本总金额校验
		        	msgList = rs.runRule("RLON_0015", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
			        //最后一期止期与借据止期
			        msgList = rs.runRule("RLON_0016", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
			      //还款计划不同期次还款日期不能一样
			        msgList = rs.runRule("RCON_0085", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
			        //第一期止期与借据起期
			        msgList = rs.runRule("RLON_0025", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
		        }
		        //借新还旧金额大于关联借据余额---借新还旧业务申请后还了款的情况
		        msgList = rs.runRule("RLON_0026", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		        //13828 合作项目额度只有10万元，却放了30万元，请按需求增加控制 
		        msgList = rs.runRule("RLON_0027", paramMap);
		        msg = convertMsg(msgList);
		        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
		        	resultMap.put("errorNum", "2");
		        	resultMap.put("errorCode", "2");
		        	resultMap.put("errorContent", msg);
		        	return resultMap;
		        }
		        
		logger.info("------>3231------出账流程提交前，向计量中间表插入数据，并校验数据准确性------bizId="+loanId+"------>begin!");
				String productType = (String)loanInfo.get("productType"); 
		logger.info("------>3231-----贷种------productType="+productType+"------>begin!");
				//银承校验票据信息 20160304----pc
				if("01008001".equals(productType)||"01008002".equals(productType)||"01008010".equals(productType)){
					//导入的数据完整性校验
					msgList = rs.runRule("RLON_0014", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
					//汇票张数校验
					msgList = rs.runRule("RLON_0011", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
			        //汇票金额校验
			        msgList = rs.runRule("RLON_0013", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
			        //字符长度校验---------------------
			        //收款人全称--50
			        msgList = rs.runRule("RLON_0019", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
			        //收款人账号---32
			        msgList = rs.runRule("RLON_0020", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
			        //收款人开户银行---60
			        msgList = rs.runRule("RLON_0021", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
				}
				//受托支付校验
				String trusToPayFlg = (String)loanInfo.get("trusToPayFlg"); 
				Map<String,String> map = new HashMap<String, String>();
				map.put("contractId", (String)loanInfo.get("contractId"));
				Object[] stzf = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.LoanSubject.getLoanStzfxx", map);
				/*20160901需求变更 3、如果业务批复中未关联合作项目额度信息，但在合同明细中选择了受托支付，
					可直接在出账时录入“受托支付”信息，但不校验该模块必输。*/
				if("1".equals(trusToPayFlg)&& stzf.length !=0){
					//受托支付信息未录
					msgList = rs.runRule("RLON_0023", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
				}
				
				//所有的产品只要合同签约时选择了受托支付，则放款时必须输入受托支付账号
				//若放款时选择了非本行账户，则不需要委托账号必输
				if("1".equals(trusToPayFlg)){
					Map<String,String> accountMap = new HashMap<String,String>();
					accountMap.put("loanId", loanId);
					Object[] ownAccount = DatabaseExt.queryByNamedSql("default", "com.bos.payInfo.LoanSubject.getLoanOwnAccount", accountMap);
					if(ownAccount.length == 0){
						resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", "请输入第三方结算账号");
			        	return resultMap;
					}
				}
				if("1".equals(trusToPayFlg)){
					//受托支付金额与放款金额比较
			        msgList = rs.runRule("RLON_0024", paramMap);
			        msg = convertMsg(msgList);
			        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
			        	resultMap.put("errorNum", "2");
			        	resultMap.put("errorCode", "2");
			        	resultMap.put("errorContent", msg);
			        	return resultMap;
			        }
				}
				if("01007007".equals(productType)||"01007008".equals(productType)||"01007002".equals(productType)||
				   "01008001".equals(productType)||"01008002".equals(productType)||"01009".equals(productType.substring(0, 5))||
				   "01010001".equals(productType)||"01011001".equals(productType)||
				   "01012001".equals(productType)||"01004001".equals(productType)||"01006001".equals(productType)||"01006002".equals(productType)||
				   "01007009".equals(productType)||"01007010".equals(productType)||"01007012".equals(productType)||
				   "01007013".equals(productType)||"01007014".equals(productType)
				   ||"01006010".equals(productType)|| "01008010".equals(productType)  || "01009010".equals(productType) //村镇银行贴现产品
				   ){
					
				}else{
					LoanToLcs ll = new LoanToLcs();
					ll.dataInsertCheck(loanId);
				}
		logger.info("------>3231------出账流程提交前，向计量中间表插入数据，并校验数据准确性------bizId="+loanId+"------>end!");
		
		
				resultMap.put("errorNum", "1");
		    	resultMap.put("errorCode", "");
		    	resultMap.put("errorContent", "");
			} catch (WFServiceException e) {
				e.printStackTrace();
				resultMap.put("errorNum", "2");
		    	resultMap.put("errorCode", "2");
		    	resultMap.put("errorContent", e.getMessage());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				resultMap.put("errorNum", "2");
		    	resultMap.put("errorCode", "2");
		    	resultMap.put("errorContent", e.getMessage());
			} catch (Throwable e) {
				e.printStackTrace();
				resultMap.put("errorNum", "2");
		    	resultMap.put("errorCode", "2");
		    	resultMap.put("errorContent", e.getMessage());
			}
		return resultMap;
	}
	private String convertMsg(List<MessageObj> msgList){
        StringBuffer sf = new StringBuffer();
        if(msgList != null && !msgList.isEmpty()){
            for (int i = 0; i < msgList.size(); i++) {
                MessageObj t = msgList.get(i);
                if(EngineConstants.RULE_LEVEL_ERROR.equals(t.getMessageType())){
                    sf.append("[(" + (i+1) + "):" + t.getCode() + "," + t.getMessageInfo() + "]");
                }
            }
        }
        if (sf.length() > 0) {
            return sf.toString();
        }
        return "true";
    }

}
