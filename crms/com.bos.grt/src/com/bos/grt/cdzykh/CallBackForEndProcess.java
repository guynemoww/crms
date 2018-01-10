package com.bos.grt.cdzykh;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bos.batch.DealAccount;
import com.bos.bps.service.RelativeDataManagerService;
import com.bos.bps.util.IBIZProcess;
import com.bos.pub.BizNumGenerator;
import com.bos.pub.GitUtil;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.primeton.mgrcore.OXD11_CdzykhReq;
import com.primeton.plus.RepayControlInfRq;
import com.primeton.plus.RepaymentRq;
import com.primeton.plusclient.CrmsCallPlusImpl;
import com.primeton.plusclient.CrmsCallPlusProxy;
import com.primeton.spring.support.DataObjectUtil;
import com.primeton.tsl.BaseVO;
import com.primeton.workflow.api.WFServiceException;

import commonj.sdo.DataObject;

public class CallBackForEndProcess implements IBIZProcess{
	public static TraceLogger logger = new TraceLogger(CallBackForEndProcess.class);
	@Override
	public void executeAfterCreateFlow(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void executeBeforeSubmit(String processInstId, Map workitem) throws EOSException {}

	@Override
	public void executeBeforeIntegration(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void executeBeforeTerminate(String processInstId, Map workitem) throws EOSException {
		String[] xpath={"bizId"};//获取相关数据的数组
		List<Object> list;
		try {
			list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
			//获取质押扣划申请ID
			String zykhId=(String)list.get(0);
			if(null==zykhId||"".equals(zykhId)){
				logger.info("业务申请-质押扣划申请ID为空！");
				throw new EOSException("业务申请-质押扣划申请ID为空！");
			}
			//质押扣划申请信息
			DataObject tbCdZykhApply = DataObjectUtil.createDataObject("com.bos.dataset.zykh.TbCdZykhApply");
			tbCdZykhApply.set("zykhId", zykhId);
			DatabaseUtil.expandEntity("default", tbCdZykhApply);
			String partyId = tbCdZykhApply.getString("partyId");//客户的partyID
			String summaryNum = tbCdZykhApply.getString("loanSummary");//借据编号
			String suretyNo =  tbCdZykhApply.getString("suretyNo");//押品编号
			BigDecimal khAmt = tbCdZykhApply.getBigDecimal("khAmt");//扣划金额
			Date applyDate = tbCdZykhApply.getDate("applyDate");//质押扣划业务申请发起日期---用来校验是否当天完成质押扣划
			Date busiDate = GitUtil.getBusiDate();//当前业务日期
			//借据信息
			DataObject tbLoanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			tbLoanSummary.set("summaryNum", summaryNum);
			DatabaseUtil.expandEntityByTemplate("default", tbLoanSummary, tbLoanSummary);
//			BigDecimal jjye = tbLoanSummary.getBigDecimal("jjye");
			//校验质押扣划---交易必须当天完成 也就是质押扣划的申请日期
			if(!(applyDate.toString().equals(busiDate.toString()))){
				logger.info("日期非法，质押扣划应该在申请当天完成所有交易！申请日期："+applyDate+"，审批日期"+busiDate);
				throw new EOSException("日期非法，当前系统日期["+busiDate+"]！质押扣划应在申请当天["+applyDate+"]结束整个流程。如果仍需做质押扣划，请退回该流程并重新发起交易！");
			}
			//其他参数信息---包括调用核心的  调用核算的
			Map<String,String> map = new HashMap<String,String>();
			map.put("zykhId", zykhId);
			Object[] infos = DatabaseExt.queryByNamedSql("default", "com.bos.grt.cdzykh.cdzykh.getCdzykhInfo1", map);
			if(null==infos||infos.length<=0){
				logger.info("未获取到质押扣划申请ID对应的参数信息！");
				throw new EOSException("未获取到质押扣划申请ID对应的参数信息！");
			}
			//押品信息
			DataObject tbGrtMortgageBasic = DataObjectUtil.createDataObject("com.bos.dataset.grt.TbGrtMortgageBasic");
			tbGrtMortgageBasic.set("suretyNo", suretyNo);
			DatabaseUtil.expandEntityByTemplate("default", tbGrtMortgageBasic, tbGrtMortgageBasic);
			Map maps = (Map) infos[0];
			String freNum= (String) maps.get("FRENUM");//冻结编号
			String cuacNo = (String) maps.get("CUACNO");//客户账号---也是调用核算还款的还款账户
			String loanOrg = (String) maps.get("LOAN_ORG");//出账机构
			String orgNum = (String) maps.get("ORG_NUM");//经办机构
			String cdCurrencyCd = (String) maps.get("CD_CURRENCY_CD");//存单币种
			//还款的账户信息  使用存单账号   还款账户名称 使用押品所有权人名称
			String zhmcPartyId = tbGrtMortgageBasic.getString("partyId");
			String zhmc="";
			if(null==zhmcPartyId||"".equals(zhmcPartyId)){//有的押品老数据 可能没有押品所有权人  核算的还款对还款账户名称除了委托贷款 其他不是必输
				throw new EOSException("未获取到押品["+suretyNo+"]所属的客户信息！");
			}else{
				DataObject hkParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
				hkParty.setString("partyId", zhmcPartyId);
				DatabaseUtil.expandEntity("default", hkParty);
				zhmc = hkParty.getString("partyName");
			}
			/**
			//还款的账户信息---正常的产品做还款都有账户信息  如果票据的垫款  只在管理端有结算账户
			DataObject zh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
			String loanId = tbLoanSummary.getString("loanId");// 放款ID
			//出账信息
			DataObject tbLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			tbLoanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", tbLoanInfo);
			if(null == tbLoanInfo.getString("productType") || "".equals(zh.getString("productType"))){
				throw new EOSException("质押扣划流程提交结束失败，失败原因：未查询到借据号对应出账表的出账信息");
			}
			String productType = tbLoanInfo.getString("productType");//产品类型
			zh.set("loanId", loanId);//放款ID
			if(productType.indexOf("01008")>-1){//垫款放款找结算账户
				zh.set("zhlx", "2");//结算账户
			}else{
				zh.set("zhlx", "1");//还款账户
			}
			DatabaseUtil.expandEntityByTemplate("default", zh, zh);
			if(null == zh.getString("id") || "".equals(zh.getString("id"))){
				throw new EOSException("质押扣划流程提交结束失败，失败原因：未查询到借据号对应的账户信息");
			}
			if(null == zh.getString("zh") || "".equals(zh.getString("zh"))){
				throw new EOSException("质押扣划流程提交结束失败，失败原因：未查询到借据号对应的账户账号");
			}
			if(null == zh.getString("zhmc") || "".equals(zh.getString("zhmc"))){
				throw new EOSException("质押扣划流程提交结束失败，失败原因：未查询到借据号对应的账户名称");
			}
			*/
			//1---调用核算还款控制交易
			logger.info("--------------->业务申请-质押扣划，信贷调用核算还款控制T1202开始Begin------>zykhId=" + zykhId+"zh="+cuacNo+"zhmc="+zhmc);
			//质押扣划---先调用核算还款控制交易
			String flowNo1 = BizNumGenerator.getLcsStan();//交易流水
			Long flowNo = Long.valueOf(flowNo1); 
			RepayControlInfRq vo = new RepayControlInfRq();
			BaseVO bvo = new BaseVO();
			bvo.setTranCod("T1202");
			bvo.setOpr(GitUtil.getCurrentUserId());
			bvo.setAut(GitUtil.getCurrentUserId());
			bvo.setAcsMethStan(Long.valueOf(BizNumGenerator.getLcsStan()));
			bvo.setRcnStan(Long.valueOf(BizNumGenerator.getLcsStan()));
			bvo.setTrnDep(loanOrg);
			bvo.setTranFrom("47");
			bvo.setOrigFrom("11000");
			bvo.setLegPerCod("9999");
			bvo.setTranDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setAccSysDate(GitUtil.getBusiDateYYYYMMDD());
			bvo.setDepCod(GitUtil.getBranchId());
			bvo.setOpnDep(loanOrg);
			vo.setDueNum(summaryNum);//借据编号
			//vo.setTelNo(summaryNum);//通知书编号---用时间来截取一个每次都不同的字符串作为通知书编号
			Long m = System.currentTimeMillis();
			String mm = m.toString();
			vo.setTelNo(mm.substring(mm.length()-8));
			vo.setPayOrder("00");//还款顺序---默认还款序
			//还款账号
			//vo.setPayPrimAcct(zh.getString("zh"));
			vo.setPayPrimAcct(cuacNo);
			//还款账户名称
			//vo.setPayPrimName(zh.getString("zhmc"));
			vo.setPayPrimName(zhmc);
			vo.setPayOutItrFlg("1");//归还未结计利息标志
			vo.setPrinPlanFlg("0");//下发新的还本/还息计划标志
			vo.setBaseVO(bvo);
			vo.setPadUpAmt(khAmt);
			Object[] params1 = new Object[2];
			params1[0] = vo;
			//调用核算还款控制信息
			CrmsCallPlusProxy proxy1 = new CrmsCallPlusImpl();
			RepayControlInfRq rectol = proxy1.executeT1202(vo);
			if(!"00000".equals(rectol.getBaseVO().getErrCod())){
				logger.info("调用核算还款控制交易失败，失败原因："+rectol.getBaseVO().getErrMsg());
				throw new EOSException("调用核算还款控制交易失败，失败原因："+rectol.getBaseVO().getErrMsg());
			}
			logger.info("--------------->业务申请-质押扣划，信贷调用核算还款控制T1202完成End------>zykhId=" + zykhId);
			
			logger.info("--------------->业务申请-质押扣划流程提交核心数据组装开始Begin------>zykhId=" + zykhId);
			//2---核心质押扣划接口---XD11数据准备
			if(cdCurrencyCd.equals("CNY")){
				cdCurrencyCd = "01";
			}else if(cdCurrencyCd.equals("FRF")){//法国法郎
				cdCurrencyCd="250";
			}else if(cdCurrencyCd.equals("DEM")){//德国马克
				cdCurrencyCd="276";
			}else if(cdCurrencyCd.equals("HKD")){//港币
				cdCurrencyCd="13";
			}else if(cdCurrencyCd.equals("ITL")){//意大利里拉
				cdCurrencyCd="380";
			}else if(cdCurrencyCd.equals("JPY")){//日元
				cdCurrencyCd="27";
			}else if(cdCurrencyCd.equals("KRW")){//韩国元
				cdCurrencyCd="410";
			}else if(cdCurrencyCd.equals("MOP")){//澳门元
				cdCurrencyCd="81";
			}else if(cdCurrencyCd.equals("MYR")){//马来西亚币
				cdCurrencyCd="458";
			}else if(cdCurrencyCd.equals("NLG")){//荷兰盾
				cdCurrencyCd="528";
			}else if(cdCurrencyCd.equals("NZD")){//新西兰元 
				cdCurrencyCd="554";
			}else if(cdCurrencyCd.equals("AUD")){//澳洲元
				cdCurrencyCd="29";
			}else if(cdCurrencyCd.equals("NOK")){//挪威克朗
				cdCurrencyCd="578";
			}else if(cdCurrencyCd.equals("PHP")){//菲律宾比索
				cdCurrencyCd="608";
			}else if(cdCurrencyCd.equals("RUB")){//卢布
				cdCurrencyCd="643";
			}else if(cdCurrencyCd.equals("SGD")){//新加坡元
				cdCurrencyCd="32";
			}else if(cdCurrencyCd.equals("ESP")){//西班牙比塞塔
				cdCurrencyCd="724";
			}else if(cdCurrencyCd.equals("SEK")){//瑞典克朗
				cdCurrencyCd="752";
			}else if(cdCurrencyCd.equals("CHF")){//瑞士法郎
				cdCurrencyCd="15";
			}else if(cdCurrencyCd.equals("THB")){//泰国铢
				cdCurrencyCd="764";
			}else if(cdCurrencyCd.equals("GBP")){//英镑
				cdCurrencyCd="12";
			}else if(cdCurrencyCd.equals("USD")){//美元
				cdCurrencyCd="14";
			}else if(cdCurrencyCd.equals("EUR")){//欧元
				cdCurrencyCd="38";
			}else if(cdCurrencyCd.equals("ATS")){//奥地利先令
				cdCurrencyCd="040";
			}else if(cdCurrencyCd.equals("BEF")){//比利时法郎
				cdCurrencyCd="056";
			}else if(cdCurrencyCd.equals("CAD")){//加拿大元
				cdCurrencyCd="28";
			}else if(cdCurrencyCd.equals("TWD")){//新台湾币
				cdCurrencyCd="158";
			}else if(cdCurrencyCd.equals("DKK")){//丹麦克朗
				cdCurrencyCd="208";
			}else if(cdCurrencyCd.equals("FIM")){//芬兰马克
				cdCurrencyCd="246";
			}else{
				throw new EOSException("存单币种["+cdCurrencyCd+"]不被当前系统支持，请检查数据！");
			}		
			//入账账号：出账机构+9+币种+3142+00001
			String trsfInAcctNo = loanOrg+"9"+cdCurrencyCd+"0"+"3142"+"00001";//转入账号
			OXD11_CdzykhReq req = new OXD11_CdzykhReq();
			
			req.setFlowNo(flowNo.toString());//与核心质押扣划交互的交易流水
			req.setApprovalDepart(GitUtil.getCurrentOrgCd());//审批部门
			req.setApprover(GitUtil.getCurrentUserId());//审批人
			req.setCustAcctNo(cuacNo);//客户账号
			req.setDeductedAmt(khAmt.toString());//扣划金额
			req.setFrzNo(freNum);//冻结编号
			req.setSummaryCode("000233");//摘要代码
			req.setSummaryDesc("质扣");//摘要描述
			req.setTrsfInAcctNo(trsfInAcctNo);//转入账号
			req.setHxorg(loanOrg);//出账机构
			req.setHxOrgNum(orgNum);//经办机构
			logger.info("--------------->业务申请-质押扣划流程提交核心数据组装完成------>zykhId=" + zykhId+"cuacNo="+cuacNo+"freNum="+freNum+"trsfInAcctNo="+trsfInAcctNo);
			
			//调用核心的质押扣划接口---不单独调用   嵌套在与核算还款交互的过程当中  为了方便异常情况下 核算对数据的手工处理
			//CrmsMgrCallCoreProxy proxy = new CrmsMgrCallCoreImpl();
			//OXD11_CdzykhRes oxd11CdzykhRes = proxy.executeXD11(req);
			
			//String hxResCod = oxd11CdzykhRes.getResTranHeader().getHRetCode();
			//if("AAAAAAA".equals(hxResCod) ){//如果核心返回成功
				//tbCdZykhApply.setString("status", "03");//已生效---核心成功就当做生效
				//tbCdZykhApply.setString("isNoticeHx", "1");
				//tbCdZykhApply.setString("remarkHx", "交易成功");//与核心交互备注
				//tbCdZykhApply.set("hxNoticeTime", GitUtil.getBusiDate());//与核心交互时间
			//}else{
				//logger.info("调用核心质押扣划接口失败，失败原因："+oxd11CdzykhRes.getResTranHeader().getHRetMsg());
				//throw new EOSException("调用核心质押扣划接口失败，失败原因："+oxd11CdzykhRes.getResTranHeader().getHRetMsg());
			//}
			//logger.info("--------------->业务申请-质押扣划流程提交核心调用完成End------>zykhId=" + zykhId);
			
			logger.info("--------------->业务申请-质押扣划，信贷调用核算还款接口T1102以及核心质押扣划接口XD11开始Begin------>zykhId=" + zykhId);
			//2调用核算还款接口
			RepaymentRq rqs = new RepaymentRq();
			rqs.setDueNum(vo.getDueNum());
			//核算还款控制：主键约束：日期+借据编号+通知书编号  一个借据如果在某天需要多次还款  就需要控制通知书编号是变化的
			//通知书编号对核算没有意义
			//rqs.setTelNo(vo.getTelNo());
			rqs.setTelNo(mm.substring(mm.length()-8));
			rqs.setPayOutItrFlg(vo.getPayOutItrFlg());
			rqs.setProductType("zykh");
			bvo.setTranCod("T1102");
			bvo.setRcnStan(Long.valueOf(flowNo));//对账流水号---要与核心的前台流水保持一致
			vo.setBaseVO(bvo);
			rqs.setBaseVO(vo.getBaseVO());
			params1[0] = rqs;
			params1[1] = req;
			ILogicComponent logicComponent = LogicComponentFactory.create("com.primeton.tsl.TransferDataManager");
			Object[] objs = null;
			objs = logicComponent.invoke("RetPaymentEasyLcsForZykh", params1);
			RepaymentRq epaymentRq = (RepaymentRq)objs[0];
			BaseVO baseVO = epaymentRq.getBaseVO();
			String hsResCod = (String) baseVO.getErrCod();
			if (!"00000".equals(hsResCod)) {//核算还款失败
				throw new EOSException(baseVO.getErrMsg());
			}else{//核算还款成功
				tbCdZykhApply.setString("isNoticeHs", "1");
				tbCdZykhApply.setString("remarkHs", "交易成功");//与核算交互备注---成功1 失败0
				tbCdZykhApply.set("hsNoticeTime", GitUtil.getBusiDate());//与核算交互时间
				
				tbCdZykhApply.setString("isNoticeHx", "1");
				tbCdZykhApply.setString("remarkHx", "交易成功");//与核心交互备注---成功1 失败0
				tbCdZykhApply.set("hxNoticeTime", GitUtil.getBusiDate());//与核心交互时间
			}
			logger.info("--------------->业务申请-质押扣划，信贷调用核算还款接口T1102以及核心质押扣划接口XD11开始End------>zykhId=" + zykhId);
			
			//与押品交互 暂时的需求是  给押品信息表新增一个字段用来标识 已做质押扣划  同时 将扣划金额送给押品---扣划金额是累加的扣划金额
			//与押品交互  是质押扣划最后一步  前面如果成功   就表示整个交易已经在核心核算完成  这里不能抛出异常  做失败标记
			if(null==tbGrtMortgageBasic.getString("suretyId")||"".equals(tbGrtMortgageBasic.getString("suretyId"))){
				tbCdZykhApply.setString("isNoticeYp", "0");//是否通知押品成功1 失败0
				tbCdZykhApply.setString("remarkYp", "交易失败，失败原因：未获取到押品信息");//与押品交互备注
				tbCdZykhApply.set("ypNoticeTime", GitUtil.getBusiDate());//与押品交互时间
			}else{
				tbGrtMortgageBasic.set("isDoneZykh", "1");//押品是否已做质押扣划
				BigDecimal totalKhAmt = tbGrtMortgageBasic.getBigDecimal("totalKhAmt");//存单押品的已扣划金额
				if(null==totalKhAmt){
					 totalKhAmt = new BigDecimal("0");
				}
				totalKhAmt = totalKhAmt.add(khAmt);
				tbGrtMortgageBasic.set("totalKhAmt", totalKhAmt);//押品是否已做质押扣划
				DatabaseUtil.saveEntity("default", tbGrtMortgageBasic);
				tbCdZykhApply.setString("isNoticeYp", "1");//是否通知押品---成功1 失败0
				tbCdZykhApply.setString("remarkYp", "交易成功");//与押品交互备注
				tbCdZykhApply.set("ypNoticeTime", GitUtil.getBusiDate());//与押品交互时间
			}
			tbCdZykhApply.setString("hxFlowNo", flowNo.toString());//流水---与核心 核算交互的流水  核心与核算以此来完成对账
			tbCdZykhApply.setString("status", "03");//已生效
			tbCdZykhApply.set("updateTime", GitUtil.getBusiDate());
			DatabaseUtil.saveEntity("default", tbCdZykhApply);
			//更新借据---余额  状态   借据信息的同步---通过核算完成同步 
//			BigDecimal ce = jjye.subtract(khAmt);//借据余额与扣划金额的差额
//			if(ce.compareTo(new BigDecimal(0)) == 0){//已结清
//				tbLoanSummary.set("summaryStatusCd", "04");
//				tbLoanSummary.set("updateTime", GitUtil.getBusiDate());
//			}
//			tbLoanSummary.set("jjye", ce);
//			DatabaseUtil.saveEntity("default", tbLoanSummary);
			//同步借据---与核算同步借据
			DealAccount.singleSynch(summaryNum);
			// 调用额度重算
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("partyId", partyId);
			DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map1);
		} catch (WFServiceException e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		} catch (Exception e) {
			logger.info("系统发生异常，异常信息："+e.getMessage());
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}catch (Throwable e) {
			logger.info("系统发生异常，异常信息："+e.getMessage());
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}

	@Override
	public void executeBeforeUntread(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		
	}
	//否决
	@Override
	public void executeBeforeReject(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成方法存根
		logger.info("--------------->业务申请-质押扣划否决撤销------>begin！");
		String[] xpath = { "bizId" };// 获取相关数据的数组
		List<Object> list;
			try {
				list = RelativeDataManagerService.getRelativeDataBatch(Long.valueOf(processInstId), xpath);
				// 获取贷款id
				String zykhId = (String) list.get(0);
				if (null == zykhId || "".equals(zykhId)) {
					logger.info("业务申请质押扣划否决流程ID为空！");
					throw new EOSException("业务申请质押扣划否决流程ID为空！");
				}
				logger.info("--------------->业务申请-质押扣划流程否决Begin------>zykhId=" + zykhId);
				DataObject tbCdZykhApply = DataObjectUtil.createDataObject("com.bos.dataset.zykh.TbCdZykhApply");
				tbCdZykhApply.set("zykhId", zykhId);
				DatabaseUtil.expandEntity("default", tbCdZykhApply);
				Date busiDate = GitUtil.getBusiDate();//业务时间
				tbCdZykhApply.setString("status", "06");//质押扣划业务申请状态---已删除
				tbCdZykhApply.set("updateTime", busiDate);//最近更新时间
				DatabaseUtil.saveEntity("default", tbCdZykhApply);
				logger.info("--------------->业务申请-质押扣划流程否决End------>zykhId=" + zykhId);
			} catch (WFServiceException e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new EOSException(e.getMessage());
			}
	}
	
	@Override
	public void executeAfterAbort(String processInstId, Map workitem) throws EOSException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Map<String, String> executeDataCheck(String processInstId, Map workitem) throws Exception {
		return null;
	}
}
