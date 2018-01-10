package com.bos.payPro;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.biz.MathHelper;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.inter.InterScheduler;
import com.bos.inter.LoanToHx;
import com.bos.payInfo.LoanCheck;
import com.bos.payInfo.LoanInfo;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.git.easyrule.util.RuleException;
import com.ibm.db2.jcc.c.r;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.tsl.BaseVO;
import com.primeton.tsl.CancelDelVo;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

public class CallBackForEndProcess implements IBIZProcess {

	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);
	
	public void executeAfterCreateFlow(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeSubmit(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeIntegration(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public void executeBeforeTerminate(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		
		List<Object> list = null;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
		} catch (WFServiceException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
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
		String productType = (String)loanInfo.get("productType"); 
		
		logger.info("------>3231------>放款流程结束，向借据表插入数据------loanId="+loanId+"------->begin!");				
		LoanInfo li = new LoanInfo();
		li.createSummary(loanInfo);
		logger.info("------>3231------>放款流程结束，向借据表插入数据------loanId="+loanId+"------->end!");	
		
		logger.info("------>3231------>放款流程提交,额度足额判断------bizId="+loanId+"------->begin!");
		try {
			//校验额度是否足够
			RuleService rs = new RuleService();
			Map<String,String> paramMap = new HashMap<String, String>();
			paramMap.put("loanId", loanId);
			
			List<MessageObj> msgList = rs.runRule("RLON_0017", paramMap);
			String msg = convertMsg(msgList);
	        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	        	throw new EOSException("合同可用额度不足");
	        }
	        msgList = rs.runRule("RLON_0018", paramMap);
	        msg = convertMsg(msgList);
	        if(!"true".equals(msg)){	//校验不成功返回校验失败结果
	        	throw new EOSException("批复明细可用额度不足");
	        }
		} catch (RuleException e1) {
			e1.printStackTrace();
			throw new EOSException("执行额度校验规则失败");
		}
        
	logger.info("------>3231------>放款流程提交,额度足额判断------bizId="+loanId+"------->end!");
	
	logger.info("------>3231------>放款流程结束，更新借据状态------loanId="+loanId+"------->begin!");
	/*//更新借据状态
	if("01008001".equals(productType)||"01008002".equals(productType)){//银行承兑汇票
		DataObject summaryInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanSummary");
		summaryInfo.set("loanId", loanId);
		DataObject[] summaryInfos = DatabaseUtil.queryEntitiesByTemplate("default", summaryInfo);
		DataObject[] cdhps = new DataObject[summaryInfos.length];
		for(int i=0;i<summaryInfos.length;i++){
			DataObject summaryInfotp = summaryInfos[i];
			summaryInfotp.set("summaryStatusCd", "02");
			summaryInfotp.set("jjye", (BigDecimal)summaryInfotp.get("summaryAmt"));
			cdhps[i] = summaryInfotp;
		}
		DatabaseUtil.updateEntityBatch("default", cdhps);
	}else if("01007001".equals(productType)||"01007002".equals(productType)||"01007003".equals(productType)||
			"01007004".equals(productType)||"01007005".equals(productType)||"01007006".equals(productType)||
			"01007007".equals(productType)||"01007008".equals(productType)){//借据余额和状态反馈后更新
		DataObject summaryInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanSummary");
		summaryInfo.set("loanId", loanId);
		DatabaseUtil.expandEntityByTemplate("default", summaryInfo,summaryInfo);
		summaryInfo.set("summaryStatusCd", "02");
		DatabaseUtil.updateEntity("default", summaryInfo);
	}else{
		DataObject summaryInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanSummary");
		summaryInfo.set("loanId", loanId);
		DatabaseUtil.expandEntityByTemplate("default", summaryInfo,summaryInfo);
		summaryInfo.set("summaryStatusCd", "02");
		summaryInfo.set("jjye", (BigDecimal)summaryInfo.get("summaryAmt"));
		DatabaseUtil.updateEntity("default", summaryInfo);
	}*/
	
	logger.info("------>3231------>放款流程结束，更新借据状态------loanId="+loanId+"------->end!");
	
	//额度扣减----国结在反馈后才扣减额度
	if("01007001".equals(productType)||"01007002".equals(productType)||"01007003".equals(productType)||
			"01007004".equals(productType)||"01007005".equals(productType)||"01007006".equals(productType)||
			"01007007".equals(productType)||"01007008".equals(productType)
			||"01007009".equals(productType)||"01007010".equals(productType)||"01007011".equals(productType)
			||"01007012".equals(productType)||"01007013".equals(productType)||"01007014".equals(productType)){
		logger.info("------>3231------>放款流程提交,扣减合同额度--国结在反馈后才扣减额度");
	}else{
		//借新还旧在出账前会再调一次，所以此处不用调
		Map<String, String> map = new HashMap<String, String>();
		map.put("loanId", loanId);
		Object[] applyIds = DatabaseExt.queryByNamedSql("default", "com.bos.pay.LoanSummary.getApplyIdByLoanId", map);
		if(null ==applyIds || applyIds.length==0){
			throw new EOSException("-------------未查询到applyId------------------");
		}
		String applyId = (String)((DataObject)applyIds[0]).get("APPLY_ID");
		DataObject jxhj = DataObjectUtil.createDataObject("com.bos.dataset.biz.TbBizSummary");
		jxhj.set("applyId", applyId);
		DatabaseUtil.expandEntityByTemplate("default", jxhj, jxhj);
		if(jxhj.get("summaryId")==null||"".equals(jxhj.get("summaryId"))){//非借新还旧
			String partyId = loanInfo.getString("partyId");
			Map<String,String> map2 = new HashMap<String,String>();
			map2.put("partyId", partyId);
			DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
		}
	}
	
	if("01013001".equals(productType)||"02005001".equals(productType)||
			"01013010".equals(productType)||"02005010".equals(productType)||
			"02005002".equals(productType)||"02005003".equals(productType)){
		logger.info("------>3231------>如果是委贷必须先做委托资金扣划------loanId="+loanId+"------->begin!");	
		try {
			LoanToHx lh = new LoanToHx();
			String resultString  = lh.entrustMoneyTrans(loanInfo);
			if(!"1".equals(resultString)){
				throw new EOSException(resultString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
		
		logger.info("------>3231------>如果是委贷必须先做委托资金扣划------loanId="+loanId+"------->end!");	
	}
	
	logger.info("------>3231------>放款流程结束，调用接口------loanId="+loanId+"------->begin!");		
		
		InterScheduler is= new InterScheduler();
		try {
			is.interChose(loanInfo);
		} catch (Exception e) {
			e.printStackTrace();
			if("01013001".equals(productType)||"02005001".equals(productType)||
					"01013010".equals(productType)||"02005010".equals(productType)||
					"02005002".equals(productType)||"02005003".equals(productType)){
				//委托扣划回滚
				LoanToHx lh = new LoanToHx();
				String resultString  =lh.entrustMoneyTransBack(loanInfo);
				if(!"1".equals(resultString)){
					logger.info("-----------委托资金扣划回滚失败-----------------------"+loanId+"失败原因"+resultString);
				}
			}
			throw new EOSException(e.getMessage());
		}
	logger.info("------>3231------>放款流程结束，调用接口------loanId="+loanId+"------->end!");	
	
	//throw new EOSException("呵呵");
	}

	public void executeBeforeUntread(String processInstId, Map workitem)
			throws EOSException {
		//到计量的业务发交易撤销，删除中间表数据
		String[] xpath={"bizId"};//获取相关数据的数组
		List<Object> list = null;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取贷款id
			String loanId=(String)list.get(0);
			DataObject loanInfo = DataObjectUtil
					.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			loanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", loanInfo);
			String productType = (String)loanInfo.get("productType");
			
			if(productType.startsWith("01007")||"01004001".equals(productType)|| "01006001".equals(productType)||"01006002".equals(productType)||
					"01009".equals(productType.substring(0, 5))|| "01010001".equals(productType)||
					"01011001".equals(productType)||"01012001".equals(productType)|| "01008001".equals(productType)||
					"01008002".equals(productType)
					|| "01006010".equals(productType)|| "01008010".equals(productType) ||"01009010".equals(productType) //村镇银行贴现产品
					){
				//国结和到核心的出账不用撤销
			}else{
//				String dueNum = loanInfo.getString("loanNum");
//				Object[] params1 = new Object[2];
//				params1[0] = "MDB_2002";
//
//				CancelDelVo vo = new CancelDelVo();
//				vo.getBaseVO().setTranCod("MDB_2002");
//				vo.getBaseVO().setTranDate(GitUtil.getBusiDateYYYYMMDD());
//				vo.getBaseVO().setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
//				vo.setBusDate(GitUtil.getBusiDateYYYYMMDD());
//				vo.setDelType("07");
//				vo.setDueNum(dueNum);
//				vo.setTelNo(dueNum);
//
//				params1[1] = vo;
//				ILogicComponent logicComponent = LogicComponentFactory
//						.create("com.primeton.tsl.TransferDataManager");
//				Object[] objs = logicComponent
//						.invoke("newDataInsertCheck", params1);
//				DataObject vo1 = (DataObject) objs[0];
//				BaseVO baseVO = (BaseVO)vo1.get("baseVO");
//				String returnCode = (String)baseVO.getErrCod();
//				if(!"200".equals(returnCode)){
//					throw new EOSException(baseVO.getErrMsg());
//				}
				
			}
		} catch (WFServiceException e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

	public void executeBeforeReject(String processInstId, Map workitem)
			throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		
		List<Object> list = null;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
		} catch (WFServiceException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//获取贷款id
		String loanId=(String)list.get(0);
		if(null==loanId||"".equals(loanId)){
			logger.info("流程返回的申请ID为空！");
			throw new EOSException("流程返回的申请ID为空");
		}
		//更新借据状态
		DataObject loanInfo = DataObjectUtil
				.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		loanInfo.set("loanStatus", "06");
		DatabaseUtil.updateEntity("default", loanInfo);
	}

	public void executeAfterAbort(String processInstId, Map workitem)
			throws EOSException {
		// TODO 自动生成的方法存根

	}

	public Map<String, String> executeDataCheck(String processInstId,
			Map workitem) throws EOSException {
		// 校验并扣减限额
		String[] xpath={"bizId"};//获取相关数据的数组
		String conclusion = (String) workitem.get("conclusion");//结论
		List<Object> list;
		try {
			if("1".equals(conclusion)){
				list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				String loanId=(String)list.get(0);
				HashMap map = new HashMap();
		logger.info("------3231------>放款流程结束------>loanId="+loanId+"校验限额------begin");
				if(null==loanId||"".equals(loanId)){
					logger.info("流程返回的申请ID为空！");
					throw new EOSException("流程返回的申请ID为空");
				}
				LoanCheck lc = new LoanCheck();
				if(null != lc.checkRiskLimt(loanId) && "" !=lc.checkRiskLimt(loanId)){
					map.put("errorNum", "2");
					map.put("errorCode", "2");
					map.put("errorContent","限额组（"+lc.checkRiskLimt(loanId)+"）可用额度不足！");
					return map;
				}
		logger.info("------3231------>放款流程结束------>loanId="+loanId+"校验限额------end");
		
				DataObject loanInfo = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanInfo");
				loanInfo.set("loanId", loanId);
				DatabaseUtil.expandEntity("default", loanInfo);
				String productType = (String)loanInfo.get("productType"); 
				
				/**账号有效性校验-余额大于等于0
				DataObject zh = DataObjectUtil
						.createDataObject("com.bos.dataset.pay.TbLoanZh");
				zh.set("loanId", loanId);
				DataObject[] zhs = DatabaseUtil.queryEntitiesByTemplate("default", zh);
				if(null!=zhs && zhs.length!=0){
					for(DataObject tmpzh : zhs){//所有账户校验
						try {
							String acctInd = (String)tmpzh.get("zh");
							String returnString  = queryAcc(acctInd,"CNY","1");
							if(!"1".equals(returnString)){
								map.put("errorNum", "2");
								map.put("errorCode", "2");
								map.put("errorContent",returnString);
								return map;
							}
						} catch (Exception e) {
							e.printStackTrace();
							map.put("errorNum", "2");
							map.put("errorCode", "2");
							map.put("errorContent","查询账号信息出错");
							return map;
						}
					}
				}**/
		
				//委托账号币种校验
				if("01013001".equals(productType)||"02005001".equals(productType)||
						"01013010".equals(productType)||"02005010".equals(productType)||
						"02005002".equals(productType)||"02005003".equals(productType)){
					
					DataObject wtdkinfo = DataObjectUtil
							.createDataObject("com.bos.dataset.pay.TbLoanWtdk");
					if("01013001".equals(productType) || "01013010".equals(productType)){//对公
						//先查出委托人id
						wtdkinfo = DataObjectUtil
								.createDataObject("com.bos.dataset.pay.TbLoanWtdk");
						wtdkinfo.set("loanId", loanId);
						DatabaseUtil.expandEntityByTemplate("default", wtdkinfo , wtdkinfo);
					}else{//个人
						DataObject contract = DataObjectUtil
								.createDataObject("com.bos.dataset.crt.TbConContractInfo");
						contract.set("contractId", (String)loanInfo.get("contractId"));
						DatabaseUtil.expandEntity("default", contract);
						
						//先查出委托人id
						wtdkinfo = DataObjectUtil
								.createDataObject("com.bos.dataset.biz.TbBizXwApply");
						wtdkinfo.set("amountDetailId", (String)contract.get("amountDetailId"));
						DatabaseUtil.expandEntityByTemplate("default", wtdkinfo , wtdkinfo);
					}
					
					
					//根据委托人id查找委托方账号信息
					if(wtdkinfo.get("wtxmId")!=null){
						DataObject wtzh = DataObjectUtil
								.createDataObject("com.bos.dataset.csm.TbCsmEntrustAccount");
						wtzh.set("accId", wtdkinfo.get("wtxmId"));
						DatabaseUtil.expandEntity("default", wtzh);
						if(wtzh.get("entrustAcc")!=null){//委托存款户
							String retString = queryAcc((String)wtzh.get("entrustAcc"),(String)loanInfo.get("currencyCd"),"2");
							if(!"1".equals(retString)){
								map.put("errorNum", "2");
								map.put("errorCode", "2");
								map.put("errorContent",retString);
								return map;
							}
						}
						if(wtzh.get("entrustLoanAcc")!=null){//委托基金号
							String retString = queryAcc((String)wtzh.get("entrustLoanAcc"),(String)loanInfo.get("currencyCd"),"2");
							if(!"1".equals(retString)){
								map.put("errorNum", "2");
								map.put("errorCode", "2");
								map.put("errorContent",retString);
								return map;
							}
						}
						if(wtzh.get("entrustReturnAcc")!=null){//委托贷款收息号
							String retString = queryAcc((String)wtzh.get("entrustReturnAcc"),(String)loanInfo.get("currencyCd"),"2");
							if(!"1".equals(retString)){
								map.put("errorNum", "2");
								map.put("errorCode", "2");
								map.put("errorContent",retString);
								return map;
							}
						}
						if(wtzh.get("entrustReturnPrincipalAcc")!=null){//委托人收本户
							String retString = queryAcc((String)wtzh.get("entrustReturnPrincipalAcc"),(String)loanInfo.get("currencyCd"),"2");
							if(!"1".equals(retString)){
								map.put("errorNum", "2");
								map.put("errorCode", "2");
								map.put("errorContent",retString);
								return map;
							}
						}
						if(wtzh.get("entrustReturnInterestAcc")!=null){//委托人收息户
							String retString = queryAcc((String)wtzh.get("entrustReturnInterestAcc"),(String)loanInfo.get("currencyCd"),"2");
							if(!"1".equals(retString)){
								map.put("errorNum", "2");
								map.put("errorCode", "2");
								map.put("errorContent",retString);
								return map;
							}
						}
					}
				}
			}
		} catch (WFServiceException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	* @Title: queryAcc 
	* @Description: 调用查账户信息接口 
	* @param acctInd,conCurrencyCd,flag-为2表示是委托方账号要校验币种
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author GIT-LPC
	* @date 2015年10月14日 下午8:54:08 
	* @version V1.0
	 */
	public String queryAcc(String acctInd,String conCurrencyCd,String flag){
		String returnsString = "1";
		/**
		Object[] params1 = new Object[1];
		params1[0] = acctInd;
		ILogicComponent logicComponent = LogicComponentFactory.create("com.bos.accInfo.accInfo");
		Object[] objs = new Object[2];
		try {
			objs = logicComponent.invoke("queryAccNew", params1);
			String retString = (String)objs[0];//第一个返回值
			if(!"查询成功".equals(retString)){
				returnsString = "查询账号信息出错:"+acctInd;
			}else{//接口查询成功
				//余额必须大于等于0
				DataObject accinfObject = (DataObject)objs[1];//第二个返回值
				BigDecimal avlBal = new BigDecimal((Double) accinfObject.get("avlBal"));
				if(avlBal.compareTo(new BigDecimal("0"))==-1){
					returnsString = "账号"+acctInd+"可用余额小于0";
				}
				//委托账号币种校验
				if("2".equals(flag)){
					String accqueryccy = (String)accinfObject.get("ccyTp");
					if(!conCurrencyCd.equals(accqueryccy)){
						returnsString = "委托方账号"+acctInd+"币种与合同币种不一致";
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			returnsString = "查询账户信息失败";
		}**/
		return returnsString;
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
