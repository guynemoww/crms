package com.primeton.ecds.client;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.axis2.AxisFault;
import org.apache.axis2.engine.ListenerManager;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.bos.dataset.biz.impl.TbBizPjxxApplyImpl;
import com.bos.dataset.biz.impl.TbBizTxxxApplyImpl;
import com.bos.pub.GitUtil;
import com.bos.pub.socket.util.EsbSocketConstant;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.logging.Logger;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.primeton.ecds.CommReqTranHeader;
import com.primeton.ecds.CommRequestHeader;
import com.primeton.ecds.S01001010021003ServiceStub;
import com.primeton.ecds.S01001010021003ServiceStub.FMT_CRMS_SVR_S01001010021003_IN_SUB;
import com.primeton.ecds.S01001010021004ServiceStub;
import com.primeton.ecds.S01001010021004ServiceStub.FMT_CRMS_SVR_S01001010021004_IN_SUB;
import com.primeton.ecds.S01001010021013ServiceStub;
import com.primeton.ecds.S01001010021020ServiceStub;
import com.primeton.ecds.S01001010021020ServiceStub.FMT_CRMS_SVR_S01001010021020_IN_SUB;
import com.primeton.ecds.S01001010021020ServiceStub.FMT_CRMS_SVR_S01001010021020_OUT_SUB;
import com.primeton.ecds.S01001030021010ServiceStub;
import com.primeton.ecds.S01001070021002ServiceStub;
import com.primeton.ecds.S01001110021001ServiceStub;
import com.primeton.ecds.S01001110021009ServiceStub;

import commonj.sdo.DataObject;

@Bizlet("调用票据系统接口操作类")
public class ECDSClient {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private String getUrl() {
		ListenerManager.defaultConfigurationContext = null;
		String module = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_MODULE;
		String group = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_GROUP;
		String ip = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_IP;
		String port = EsbSocketConstant.CONTRIBUTION_ESB_WEBSERVICE_PORT;
		String zip = ConfigurationUtil.getUserConfigSingleValue(module, group, ip);
		String zport = ConfigurationUtil.getUserConfigSingleValue(module, group, port);
		String url = "http://" + zip + ":" + zport;
		return url;
	}

	@Bizlet("银承签发")
	public void S01001010021001(DataObject loanInfo) throws Exception {
		Logger log = GitUtil.getLogger("ECDSClient.S01001110021001");
		S01001110021001ServiceStub.S01001110021001 request = new S01001110021001ServiceStub.S01001110021001();
		S01001110021001ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S01001110021001ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01001110021001ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S01001110021001ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01001110021001ServiceStub.FMT_CRMS_SVR_S01001110021001_IN requestBody = new S01001110021001ServiceStub.FMT_CRMS_SVR_S01001110021001_IN();
		S01001110021001ServiceStub.FMT_CRMS_SVR_S01001110021001_IN_SUB[] sub = null;

		try {
			String contractId = loanInfo.getString("contractId"); // 获取合同编号
			if (StringUtils.isEmpty(contractId)) {
				log.error("合同编号为空，请刷新页面重试");
				throw new EOSException("合同编号为空，请刷新页面重试");
			}
			DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo"); // 合同信息实体
			tbConContractInfo.setString("contractId", contractId);
			DatabaseUtil.expandEntity("default", tbConContractInfo); // 查询数据
			String amountDetailId = tbConContractInfo.getString("amountDetailId");
			if (StringUtils.isEmpty(amountDetailId)) {
				log.error("全局业务编号为空，请刷新页面重试");
				throw new EOSException("全局业务编号为空，请刷新页面重试");
			}

			HashMap<String, String> tbBizPjxxApply = new HashMap<String, String>();
			tbBizPjxxApply.put("loanId", loanInfo.getString("loanId"));
			Object[] tbBizPjxxApplys = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryLoanInfoAndPJXX", tbBizPjxxApply); // 票据明细
			if (tbBizPjxxApplys.length < 1) {
				log.error("汇票清单为空，请刷新页面重试");
				throw new EOSException("票据明细清单为空");
			}

			String orgNum = tbConContractInfo.getString("orgNum"); // 机构编号
			String userNum = tbConContractInfo.getString("userNum"); // 用户编号
			DataObject[] userInfo = GitUtil.getUserInfo(userNum);
			String userName = userInfo[0].getString("empname"); // 用户名称
			String partyId = tbConContractInfo.getString("partyId"); // 客户编号

			DataObject tbCsmParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
			tbCsmParty.setString("partyId", partyId);
			DataObject party = GitUtil.queryEntityByTemplate(tbCsmParty);
			String ecifPartyNum = party.getString("ecifPartyNum");

			// 保证金信息
			HashMap<String, String> bzjmap = new HashMap<String, String>();
			bzjmap.put("contractId", contractId);
			Object[] bzjxx = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryBzjxx", bzjmap);
			DataObject bzj = null;
			if (bzjxx.length > 0) {
				bzj = (DataObject) bzjxx[0];
			}
			// 票据种类 01-纸票;02-电票
			String pjzl = loanInfo.getString("pjzl");
			DataObject data = (DataObject) tbBizPjxxApplys[0];

			requestBody.setProtocalNo(tbConContractInfo.getString("contractNum"));// 承兑协议编号
			if ("01".equals(pjzl)) {
				requestBody.setBillClass("1");// 票据种类 1-纸票、2-电票
			} else if ("02".equals(pjzl)) {
				requestBody.setBillClass("2");// 票据种类 1-纸票、2-电票
			} else {
				throw new EOSException("票据种类错误");
			}
			requestBody.setAcptType("1"); // 签发类型 默认送1-自签

			// TODO
			requestBody.setBusiBranchNo(orgNum);// 业务发起机构(将虚拟机构对应到实体机构)
			requestBody.setAcctBranchNo(loanInfo.getString("loanOrg"));// 账务机构
			requestBody.setTransBranchNo(loanInfo.getString("loanOrg"));// 账务操作机构
			requestBody.setRemitterCustNo(ecifPartyNum); // 出票人客户号
			requestBody.setRemitterAcctNo(data.getString("CPRZH"));// 出票人账号(结算帐号)
			requestBody.setRemitter(data.getString("CPRQC"));// 出票人全称
			requestBody.setDraweebankno(loanInfo.getString("drweBnkNo"));// 付款行行号 -- 电票即承兑人开户行行号
			requestBody.setDraweebankname(loanInfo.getString("drweBnkNm")); // 付款行行名 -- 电票即承兑人开户行行名
			if ("01".equals(loanInfo.getString("pjzl"))) {
				requestBody.setDraweebankaddr(loanInfo.getString("drweBnkAdr")); // 付款行地址 纸票必输
			}
			// 一个合同下的出票日期和到期日期相同
			// TODO
			String acptdt = sdf.format(loanInfo.getDate("beginDate"));
			String duedt = sdf.format(loanInfo.getDate("endDate"));
			if("02".equals(pjzl)){
				acptdt = sdf.format(data.getDate("HPCPRQ"));
				duedt = sdf.format(data.getDate("HPDQRQ"));
			}
			requestBody.setAcptdt(acptdt); // 出票日期
			requestBody.setDuedt(duedt); // 汇票到期日
			requestBody.setCustManagerNo(userNum);// 客户经理编号
			requestBody.setCustmanager(userName);// 客户经理名称
			String bzjbl = (new BigDecimal(bzjxx != null && bzjxx.length > 0 ? bzj.getString("BZJBL") : "0").divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_EVEN)).toString();
			requestBody.setAssuranceRatio(bzjbl); // 保证金比例
			requestBody.setTotalAmt(tbConContractInfo.getString("contractAmt"));// 合同金额

			// 目前只有银行承兑汇票
			String billType = "0";
			BigDecimal bzjje = new BigDecimal(0); // 保证金
			sub = new S01001110021001ServiceStub.FMT_CRMS_SVR_S01001110021001_IN_SUB[tbBizPjxxApplys.length];
			for (int i = 0; i < tbBizPjxxApplys.length; i++) {
				sub[i] = new S01001110021001ServiceStub.FMT_CRMS_SVR_S01001110021001_IN_SUB();
				DataObject tempData = (DataObject) tbBizPjxxApplys[i];
				sub[i].setPromNoteNo(tempData.getString("SUMMARY_NUM")); // 借据编号
				sub[i].setBillId(tempData.getString("BILLID"));// 票据ID 电票必输
				if ("0".equals(billType)) {
					sub[i].setBillType("1"); // 票据类型 1-银票、2-商票
				} else if ("1".equals(billType)) {
					sub[i].setBillType("2"); // 票据类型 1-银票、2-商票
				} else {
					throw new EOSException("票据类型错误");
				}
				sub[i].setAssuranceType("2"); // 担保类型 1-普通保证金、2-票据池保证金
				sub[i].setBillMoney(tempData.getString("HPJE")); // 票据金额
				sub[i].setPayee(tempData.getString("SKRQC")); // 收款人全称
				sub[i].setPayeeAcctNo(tempData.getString("SKRZH")); // 收款人账号
				sub[i].setPayeeBankNo(tempData.getString("PAYEEBANKNO")); // 收款人开户行行号
				sub[i].setPayeeBankName(tempData.getString("PAYEEBANKNAME"));// 收款人开户行行名
				sub[i].setAcptDt(acptdt); // 出票日期
				sub[i].setDueDt(duedt); // 汇票到期日
				if ("0".equals(billType)) {
					sub[i].setAcceptorAcctNo("0"); // 承兑人账号 银票送0
					sub[i].setAcceptor(tempData.getString("ACCEPTORBANKNAME")); // 承兑人全称 银票即承兑行名称
				} else if ("1".equals(billType)) {
					sub[i].setAcceptorAcctNo(tempData.getString("BILLACNO")); // 承兑人账号
					sub[i].setAcceptor(tempData.getString("BILLACNAME")); // 承兑人全称
				}
				sub[i].setAcceptorBankNo(tempData.getString("ACCEPTORBANKNO")); // 承兑行行号
				sub[i].setAcceptorBankName(tempData.getString("ACCEPTORBANKNAME"));// 承兑行行名
				sub[i].setForbidFlag(tempData.getString("FORBIDFLAG"));// 禁止背书标记
				// 手续费 默认万分之五
				String fee = new BigDecimal(tempData.getString("HPJE")).multiply(new BigDecimal(0.0005)).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
				sub[i].setFee(fee);
				if (i == 0) {
					sub[i].setCommitFee(loanInfo.getString("comAmt"));// 首张送承诺费其他送0
				} else {
					sub[i].setCommitFee("0");// 首张送承诺费其他送0
				}
				if ("01".equals(pjzl)) {
					sub[i].setReserve1("0.28");// 工本费 (每张工本费0.28元)
				}
				if (bzjxx != null && bzjxx.length > 0) {// 有保证金信息才赋值
					sub[i].setAssuranceAccountNo(bzj.getString("MARGIN_ACCOUNT"));// 保证金账号
					sub[i].setAssuranceRatio(bzjbl);// 保证金比例
					
					//更新借据对应的保证金信息---金额和比例
					DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					loanSummary.set("summaryNum", tempData.getString("SUMMARY_NUM"));
					DatabaseUtil.expandEntityByTemplate("default", loanSummary, loanSummary);
					loanSummary.set("bzjbl", new BigDecimal(bzjbl).multiply(new BigDecimal(100)));
					
					// 最后一张票保证金用总额减
					if (i==tbBizPjxxApplys.length-1) {
						BigDecimal total_bzj = new BigDecimal(bzj.getString("BZJJE"));
						if(total_bzj.compareTo(bzjje) < 0){
							throw new EOSException("保证金错误");
						}
						sub[i].setBailSum(total_bzj.subtract(bzjje).toString());// 保证金金额
						loanSummary.set("bzjje", total_bzj.subtract(bzjje));
					}else {
						BigDecimal bzjje_ = new BigDecimal(tempData.getString("HPJE")).multiply(new BigDecimal(bzj.getString("BZJBL")))
								.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_EVEN);
						bzjje = bzjje.add(bzjje_);
						sub[i].setBailSum(bzjje_.toString());// 保证金金额
						loanSummary.set("bzjje", bzjje_);					
					}
					DatabaseUtil.saveEntity("default",loanSummary);//保存借据对应的保证金信息
				}
			}

			CommRequestHeader commRequestHeader = new CommRequestHeader();
			CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
			initESBRequestHeader(commRequestHeader, commReqTranHeader, "S01001110021001", orgNum, userNum);
			BeanUtils.copyProperties(requestHeader, commRequestHeader);
			BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
			requestBody.setArray(sub);
			request.setRequestHeader(requestHeader);
			request.setReqTranHeader(reqTranHeader);
			request.setRequestBody(requestBody);
		} catch (EOSException e) {
			log.error("数据错误:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据错误:" + e.getMessage());
			throw new EOSException("数据错误:" + e.getMessage());
		}

		/**
		 * 发送报文
		 */
		S01001110021001ServiceStub.S01001110021001Response response = null;
		try {
			String zservice = "/WebService/CRMS_SVR/S01001110021001";
			String url = getUrl() + zservice;
			S01001110021001ServiceStub client = new S01001110021001ServiceStub(url);
			response = client.S01001110021001(request);
		} catch (AxisFault e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		}

		if (!response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
			log.error("调用接口失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("调用接口失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
		}

	}
	
	@Bizlet("追加保证金")
	public void S01001030021010(String loanId ,String contractNum,BigDecimal bzjje,BigDecimal bzjbl,String bzjzh,BigDecimal sumBzjje) throws Exception {
		Logger log = GitUtil.getLogger("ECDSClient.S01001030021010");
		/**
		 * 报文实体
		 */
		S01001030021010ServiceStub.S01001030021010 request = new S01001030021010ServiceStub.S01001030021010();
		S01001030021010ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S01001030021010ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01001030021010ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S01001030021010ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01001030021010ServiceStub.FMT_CRMS_SVR_S01001030021010_IN requestBody = new S01001030021010ServiceStub.FMT_CRMS_SVR_S01001030021010_IN();
		S01001030021010ServiceStub.FMT_CRMS_SVR_S01001030021010_IN_SUB[] sub = null;
		Object[] tbBizPjxxApplys = null;
		try {
			if (StringUtils.isEmpty(loanId)) {
				log.error("合同编号为空，请刷新页面重试");
				throw new EOSException("合同编号为空，请刷新页面重试");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据错误:" + e.getMessage());
			throw e;
		}
			
			DataObject tbLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			tbLoanInfo.set("loanId",loanId);
			DatabaseUtil.expandEntityByTemplate("default", tbLoanInfo,tbLoanInfo);
			String pjzl ="";
			if(null!=tbLoanInfo ){
					// 票据种类 01-纸票/02-电票
					pjzl=tbLoanInfo.getString("pjzl");
					 //查询票据明细数据
					HashMap<String, String> tbBizPjxxApply = new HashMap<String, String>();
					tbBizPjxxApply.put("loanId", tbLoanInfo.getString("loanId"));
					tbBizPjxxApplys = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryHPInfo", tbBizPjxxApply); // 票据明细
					sub = new S01001030021010ServiceStub.FMT_CRMS_SVR_S01001030021010_IN_SUB[tbBizPjxxApplys.length];
					if (tbBizPjxxApplys.length < 1) {
						log.error("汇票清单为空，请刷新页面重试");
						throw new EOSException("汇票清单为空，请刷新页面重试");
					}
					BigDecimal amt =new BigDecimal(0);//baozhen

					
					for (int j = 0; j < tbBizPjxxApplys.length; j++) {
						DataObject temp = (DataObject) tbBizPjxxApplys[j];
						sub[j] = new S01001030021010ServiceStub.FMT_CRMS_SVR_S01001030021010_IN_SUB();
						sub[j].setPromNoteNo(temp.getString("SUMMARY_NUM")); // 借据编号 (唯一编号,传主键)
						
						//更新借据对应的保证金信息---金额和比例
						DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
						loanSummary.set("summaryNum", temp.getString("SUMMARY_NUM"));
						DatabaseUtil.expandEntityByTemplate("default", loanSummary, loanSummary);
						loanSummary.set("bzjbl", bzjbl.multiply(new BigDecimal(100)));

						if(j==(tbBizPjxxApplys.length-1)){//最后一张票
							//sub[j].setBailSum(sumBzjje.subtract(amt).toString());
							sub[j].setBailSum(bzjje.subtract(amt).toString());//送給票據的保證金---追加差額分配
							BigDecimal b = new BigDecimal(temp.getString("BZJJE"));//追加前借據保證金金額
							
							loanSummary.set("bzjje", b.add(bzjje.subtract(amt)));//借據的保證金金額---總額分配
						}else{
							BigDecimal a = BigDecimal.ZERO;
							//sub[j].setBailSum((bzjje.divide(new BigDecimal(tbBizPjxxApplys.length),2,BigDecimal.ROUND_HALF_EVEN)).toString());//追加金额
							//loanSummary.set("bzjje", (bzjje.divide(new BigDecimal(tbBizPjxxApplys.length),2,BigDecimal.ROUND_HALF_EVEN)).toString());
							//amt=amt.add(bzjje.divide(new BigDecimal(tbBizPjxxApplys.length),2,BigDecimal.ROUND_HALF_EVEN));
							//a = new BigDecimal(temp.getString("BZJJE"));
							a = new BigDecimal(temp.getString("SUMMARY_AMT"));//借據金額
							a = a.multiply(bzjbl).setScale(2,BigDecimal.ROUND_HALF_EVEN);//追加后借據的保證金金額
							//a = a.multiply(bzjje).divide(sumBzjje.subtract(bzjje),2,BigDecimal.ROUND_HALF_EVEN);
							BigDecimal b = new BigDecimal(temp.getString("BZJJE"));//追加前借據保證金金額
							a = a.subtract(b);
							sub[j].setBailSum(a.toString());//送給票據的保證金---追加差額分配
							loanSummary.set("bzjje", a.add(b));//借據的保證金金額---總額分配
							amt=amt.add(a);
						}
						DatabaseUtil.saveEntity("default", loanSummary);
						
						if("02".equals(pjzl)){//BILLNO 电票号码
							sub[j].setBillNo(temp.getString("BILL_NO"));
						}
					}
				/**
				 * 组装报文
				 */
				requestBody.setProtocalNo(contractNum);// 承兑协议编号
				requestBody.setBillClass(pjzl.substring(1));// 票据种类 1-纸票、2-电票
				requestBody.setBusiType("1");//1、承兑签发2、商票保贴
				requestBody.setAssuranceRatio(bzjbl.toString());
				requestBody.setReserve1(bzjzh);//保证金账号
				requestBody.setReserve5(bzjje.toString());//追加金额
				
				CommRequestHeader commRequestHeader = new CommRequestHeader();
				CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
				initESBRequestHeader(commRequestHeader, commReqTranHeader, "S01001030021010", "", "");
				BeanUtils.copyProperties(requestHeader, commRequestHeader);
				BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
				requestBody.setArray(sub);
				request.setRequestHeader(requestHeader);
				request.setReqTranHeader(reqTranHeader);
				request.setRequestBody(requestBody);
		
				S01001030021010ServiceStub.S01001030021010Response response = null;
				try {
					String zservice = "/WebService/CRMS_SVR/S01001030021010";
					String url = getUrl() + zservice;
					S01001030021010ServiceStub client = new S01001030021010ServiceStub(url);
					System.out.println("ESB流水号：" + requestHeader.getReqSeqNo());
					response = client.S01001030021010(request);
					String code=response.getResTranHeader().getHRetCode();
					String msg=response.getResTranHeader().getHRetMsg();
				} catch (AxisFault e) {
					e.printStackTrace();
					log.error("发送报文错误:" + e.getMessage());
					log.error("ESB流水号：" + requestHeader.getReqSeqNo());
					throw new EOSException("发送报文错误:" + e.getMessage());
				} catch (RemoteException e) {
					e.printStackTrace();
					log.error("发送报文错误:" + e.getMessage());
					log.error("ESB流水号：" + requestHeader.getReqSeqNo());
					throw new EOSException("发送报文错误:" + e.getMessage());
				}
		
				if (!response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
					log.error("调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
					throw new EOSException("调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
				}
			}else{
				throw new EOSException("未查到放款信息，不能做补足保证金业务！");
			}
		return;
	}

	@Bizlet("银承撤销")
	public void S01001010021002(String loanId) throws Exception {
		Logger log = GitUtil.getLogger("ECDSClient.S01001070021002");

		/**
		 * 报文实体
		 */
		S01001070021002ServiceStub.S01001070021002 request = new S01001070021002ServiceStub.S01001070021002();
		S01001070021002ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S01001070021002ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01001070021002ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S01001070021002ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01001070021002ServiceStub.FMT_CRMS_SVR_S01001070021002_IN requestBody = new S01001070021002ServiceStub.FMT_CRMS_SVR_S01001070021002_IN();
		S01001070021002ServiceStub.FMT_CRMS_SVR_S01001070021002_IN_SUB[] sub = null;
		Object[] tbBizPjxxApplys = null;
		try {
			// 查询放款信息
			DataObject tbLoanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
			tbLoanInfo.set("loanId", loanId);
			DatabaseUtil.expandEntity("default", tbLoanInfo); // 查询数据
			String contractId = tbLoanInfo.getString("contractId");
			if (StringUtils.isEmpty(contractId)) {
				log.error("合同编号为空，请刷新页面重试");
				throw new EOSException("合同编号为空，请刷新页面重试");
			}
			DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo"); // 合同信息实体
			tbConContractInfo.setString("contractId", contractId);
			DatabaseUtil.expandEntity("default", tbConContractInfo); // 查询数据
			String amountDetailId = tbConContractInfo.getString("amountDetailId");
			if (StringUtils.isEmpty(amountDetailId)) {
				log.error("全局业务编号为空，请刷新页面重试");
				throw new EOSException("全局业务编号为空，请刷新页面重试");
			}

			// * 查询票据明细数据
			HashMap<String, String> tbBizPjxxApply = new HashMap<String, String>();
			tbBizPjxxApply.put("loanId", loanId);
			tbBizPjxxApplys = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryLoanInfoAndPJXX", tbBizPjxxApply); // 票据明细
			if (tbBizPjxxApplys.length < 1) {
				log.error("汇票清单为空，请刷新页面重试");
				throw new EOSException("汇票清单为空，请刷新页面重试");
			}

			/**
			 * 组装报文
			 */
			requestBody.setProtocalNo(tbConContractInfo.getString("contractNum"));// 承兑协议编号
			// 票据种类 01-纸票/02-电票
			String pjzl = tbLoanInfo.getString("pjzl");
			requestBody.setBillClass(pjzl.substring(1));// 票据种类 1-纸票、2-电票
			requestBody.setCancelType("1");// 撤销方式 1：批次撤销 2:明细撤销

			sub = new S01001070021002ServiceStub.FMT_CRMS_SVR_S01001070021002_IN_SUB[tbBizPjxxApplys.length];
			for (int i = 0; i < tbBizPjxxApplys.length; i++) {
				sub[i] = new S01001070021002ServiceStub.FMT_CRMS_SVR_S01001070021002_IN_SUB();
				DataObject tempData = (DataObject) tbBizPjxxApplys[i];
				sub[i].setPromNoteNo(tempData.getString("SUMMARY_NUM")); // 借据编号 (唯一编号,传主键)
			}

			// 更新汇票状态
			updateState(loanId, tbBizPjxxApplys);

			CommRequestHeader commRequestHeader = new CommRequestHeader();
			CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
			initESBRequestHeader(commRequestHeader, commReqTranHeader, "S01001070021002", "", "");
			BeanUtils.copyProperties(requestHeader, commRequestHeader);
			BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
			requestBody.setArray(sub);
			request.setRequestHeader(requestHeader);
			request.setReqTranHeader(reqTranHeader);
			request.setRequestBody(requestBody);
		} catch (EOSException e) {
			log.error("数据错误:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据错误:" + e.getMessage());
			throw new EOSException("数据错误:" + e.getMessage());
		}

		S01001070021002ServiceStub.S01001070021002Response response = null;
		try {
			String zservice = "/WebService/CRMS_SVR/S01001070021002";
			String url = getUrl() + zservice;
			S01001070021002ServiceStub client = new S01001070021002ServiceStub(url);
			response = client.S01001070021002(request);
		} catch (AxisFault e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		}

		if (!response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
			log.error("调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
		}

		return;
	}

	@Bizlet("接收贴现审批信息")
	public void S01001010021003(DataObject loanInfo) throws Exception {
		Logger log = GitUtil.getLogger("ECDSClient.S01001010021003");
		S01001010021003ServiceStub.S01001010021003 request = new S01001010021003ServiceStub.S01001010021003();
		S01001010021003ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S01001010021003ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01001010021003ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S01001010021003ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01001010021003ServiceStub.FMT_CRMS_SVR_S01001010021003_IN requestBody = new S01001010021003ServiceStub.FMT_CRMS_SVR_S01001010021003_IN();
		S01001010021003ServiceStub.FMT_CRMS_SVR_S01001010021003_IN_SUB[] sub = null;

		String contractId = loanInfo.getString("contractId");
		DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
		tbConContractInfo.setString("contractId", contractId);
		DatabaseUtil.expandEntity("default", tbConContractInfo);

		DataObject tbCsmParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		tbCsmParty.setString("partyId", tbConContractInfo.getString("partyId"));
		DatabaseUtil.expandEntity("default", tbCsmParty);

		HashMap<String, String> tbBizPjxxApply = new HashMap<String, String>();
		tbBizPjxxApply.put("loanId", loanInfo.getString("loanId"));
		Object[] tbBizTxxxApplys = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryLoanInfoAndTXXX", tbBizPjxxApply); // 票据明细
		if (tbBizTxxxApplys.length < 1) {
			log.error("汇票清单为空，请刷新页面重试");
			throw new EOSException("汇票清单为空，请刷新页面重试");
		}

		DataObject loanZh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
		loanZh.set("loanId", loanInfo.getString("loanId"));
		loanZh.set("zhlx", "0"); // 放款账户 XD_SXCD1208
		DatabaseUtil.expandEntityByTemplate("default", loanZh, loanZh);

		DataObject data = (DataObject) tbBizTxxxApplys[0];

		String orgNum = tbConContractInfo.getString("orgNum");
		String userNum = tbConContractInfo.getString("userNum"); // 用户编号
		DataObject[] userInfo = GitUtil.getUserInfo(userNum);
		String userName = userInfo[0].getString("empname"); // 用户名称

		CommRequestHeader commRequestHeader = new CommRequestHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S01001010021003", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);

		// TODO
		requestBody.setBusiLaunBrch(orgNum);// 业务发起机构
		requestBody.setAccoutBrch(loanInfo.getString("loanOrg"));// 账务机构
		requestBody.setAccountOperBrch(loanInfo.getString("loanOrg"));// 账务操作机构
		requestBody.setContractNo(tbConContractInfo.getString("contractNum"));// 合同号
		requestBody.setAgentFlag("0");// 是否代理 0：非代理；1：代理--默认0
		requestBody.setDiscType("1");// 1:买断式；2：赎回式 －－默认1
		requestBody.setDiscDt(GitUtil.getBusiDateYYYYMMDD());// yyyymmdd格式日期 当前日期
		if ("01".equals(data.getString("BILLMODEL"))) {
			requestBody.setBillClass("1");// 票据种类 1:纸质；2：电子
		} else if ("02".equals(data.getString("BILLMODEL"))) {
			requestBody.setBillClass("2");// 票据种类 1:纸质；2：电子
		} else {
			throw new EOSException("票据种类错误");
		}
		if ("0".equals(data.getString("BILLTYPE"))) {
			requestBody.setBillType("1");// 票据类型 1：银票；2：商票
		} else if ("1".equals(data.getString("BILLTYPE"))) {
			requestBody.setBillType("2");// 票据类型 1：银票；2：商票
		} else {
			throw new EOSException("票据类型错误");
		}
		requestBody.setRate(data.getString("INTERATE"));// 贴现利率 年利率为：6.1000%，服务调用时应当送值为：6.1000
		requestBody.setRateType("360");// 贴现利率类型 360：年利率；30：月利率 －－默认年利率360
		requestBody.setPayType("2");// 付息方式
		requestBody.setCustNo(tbCsmParty.getString("ecifPartyNum"));// 客户号
		requestBody.setCustName(tbCsmParty.getString("partyName"));// 客户名称
		requestBody.setCustAcctNo(loanZh.getString("zh").trim());// 客户账号
		requestBody.setBuyPayRate("0");// 买方付息比例
		requestBody.setThirdPayRate("0");// 第三方付息比例
		requestBody.setMagrNo(userNum);// 客户经理编号
		requestBody.setMagrName(userName);// 客户经理编号
		requestBody.setMagrBranchNo(orgNum);// 客户经理机构号
		requestBody.setIfTc("0");// 是否先贴后查 0：非先贴后查；1：先贴后查 默认：0
		requestBody.setBranchNo(loanInfo.getString("loanOrg"));// 机构号
		requestBody.setUserNo(userNum);// 操作员号
		requestBody.setIsRedeem("0");// 是否赎回 0:非赎回式；1：赎回式 默认：0
		requestBody.setIsInstore("0");// 是否入库 1：入库；0：不入库 默认：0
		if ("02".equals(data.getString("BILLMODEL"))) {
			requestBody.setOnlineMark(data.getString("ONLINEMARK"));// 1：线上清算；0：非线上清算
		}
		sub = new FMT_CRMS_SVR_S01001010021003_IN_SUB[tbBizTxxxApplys.length];
		for (int i = 0; i < tbBizTxxxApplys.length; i++) {
			sub[i] = new FMT_CRMS_SVR_S01001010021003_IN_SUB();
			DataObject tempData = (DataObject) tbBizTxxxApplys[i];
			sub[i].setPromNoteNo(tempData.getString("SUMMARY_NUM"));// 借据号
			sub[i].setBillNo(tempData.getString("BILLNO"));// 票据号码
			sub[i].setBillMoney(tempData.getString("BILLAMT"));// 票据金额
			sub[i].setRemitterCustNo(tempData.getString("TAKEOUTACNO"));// 出票人客户号 (可能非我行客户,传出票人账号)
			sub[i].setRemitterAcctNo(tempData.getString("TAKEOUTACNO"));// 出票人账号
			sub[i].setRemitter(tempData.getString("TAKEOUTACNAME"));// 出票人全称
			sub[i].setRemitterBankNo(tempData.getString("TAKEOUTACBANKNO").trim());// 出票人开户行行号
			sub[i].setRemitterBankName(tempData.getString("TAKEOUTACBANKNAME"));// 出票人开户行行名
			sub[i].setDraweeBankNo(tempData.getString("BILLBANKNO"));// 付款行行号
			sub[i].setDraweeBankName(tempData.getString("BILLBANKNAME"));// 付款行行名
			sub[i].setPayeeAcctNo(tempData.getString("BENENO"));// 收款人账号
			sub[i].setPayee(tempData.getString("BENENAME"));// 收款人全称
			sub[i].setAcptDt(sdf.format(tempData.getDate("BILLBEGINDATE")));// 出票日期
			sub[i].setDueDt(sdf.format(tempData.getDate("BILLENDDATE")));// 汇票到期日
			if ("0".equals(data.getString("BILLTYPE"))) {
				sub[i].setAcceptorAcctNo("0"); // 承兑人账号 银票填0
				sub[i].setAcceptor(tempData.getString("BILLBANKNAME"));// 承兑人全称
			} else if ("1".equals(data.getString("BILLTYPE"))) {
				sub[i].setAcceptorAcctNo(tempData.getString("BILLACNO"));// 承兑人账号
				sub[i].setAcceptor(tempData.getString("BILLACNAME"));// 承兑人全称
			} else {
				throw new EOSException("票据类型错误");
			}
			String billBankNo = tempData.getString("BILLBANKNO");
			sub[i].setAcceptorBankNo(billBankNo);// 承兑人开户行行号
			sub[i].setAcceptorBankName(tempData.getString("BILLBANKNAME"));// 承兑人开户行行号
			sub[i].setIsAccp(isAccp(billBankNo, log));// 是否我行承兑
			String billAddType = tempData.getString("BILLADDTYPE");
			if ("1".equals(billAddType)) {
				sub[i].setIfSameCity("1");// 是否同城 1：同城 0：异地
			} else if ("2".equals(billAddType)) {
				sub[i].setIfSameCity("0");// 是否同城 1：同城 0：异地
			}
			sub[i].setForbidFlag(tempData.getString("FORBIDFLAG"));// 禁止背书标记
			sub[i].setDelayDays(tempData.getString("ADJUSTNUM"));// 顺延天数
			if ("01".equals(tempData.getString("BILLMODEL"))) {
				sub[i].setInqRepFlag("0");// 是否已查询查复 0:未进行查询查复
				sub[i].setInqRepType("1");// 1：报文；2：实地；3：系统内
			}
			sub[i].setInterestFalg("0");// 票据系统是否需要重新计算利息
		}
		requestBody.setArray(sub);
		request.setRequestHeader(requestHeader);
		request.setReqTranHeader(reqTranHeader);
		request.setRequestBody(requestBody);

		/**
		 * 发送报文
		 */
		S01001010021003ServiceStub.S01001010021003Response response = null;
		try {
			String zservice = "/WebService/CRMS_SVR/S01001010021003";
			String url = getUrl() + zservice;
			S01001010021003ServiceStub client = new S01001010021003ServiceStub(url);
			response = client.S01001010021003(request);
		} catch (AxisFault e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		}

		if (!response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
			log.error("调用接口失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("调用接口失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
		}

	}

	@Bizlet("进行贴现申请撤销")
	public void S01001010021004(String summaryNum) throws Exception {
		Logger log = GitUtil.getLogger("ECDSClient.S01001010021004");

		S01001010021004ServiceStub.S01001010021004 request = new S01001010021004ServiceStub.S01001010021004();
		S01001010021004ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S01001010021004ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01001010021004ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S01001010021004ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01001010021004ServiceStub.FMT_CRMS_SVR_S01001010021004_IN requestBody = new S01001010021004ServiceStub.FMT_CRMS_SVR_S01001010021004_IN();

		DataObject summary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
		summary.set("summaryNum", summaryNum);
		DatabaseUtil.expandEntityByTemplate("default", summary, summary);
		String contractId = summary.getString("contractId");
		String loanId = summary.getString("loanId");

		DataObject tbLoanZh = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanZh");
		tbLoanZh.set("loanId", loanId);
		tbLoanZh.set("zhlx", "0");// 放款账户 XD_SXCD1208
		DatabaseUtil.expandEntityByTemplate("default", tbLoanZh, tbLoanZh);

		DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
		tbConContractInfo.setString("contractId", contractId);
		DatabaseUtil.expandEntity("default", tbConContractInfo);

		// * 查询票据明细数据
		Object[] tbBizTxxxApplys = null;
		HashMap<String, String> tbBizPjxxApply = new HashMap<String, String>();
		tbBizPjxxApply.put("loanId", loanId);
		tbBizTxxxApplys = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryLoanInfoAndTXXX", tbBizPjxxApply); // 票据明细
		if (tbBizTxxxApplys.length < 1) {
			log.error("汇票清单为空，请刷新页面重试");
			throw new EOSException("汇票清单为空，请刷新页面重试");
		}

		FMT_CRMS_SVR_S01001010021004_IN_SUB[] subs = new FMT_CRMS_SVR_S01001010021004_IN_SUB[tbBizTxxxApplys.length];
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S01001010021004", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		requestBody.setContractNo(tbConContractInfo.getString("contractNum"));// 合同号
		DataObject data = (DataObject) tbBizTxxxApplys[0];
		if ("01".equals(data.getString("BILLMODEL"))) {
			requestBody.setBillClass("1");// 票据种类 1:纸质；2：电子
		} else if ("02".equals(data.getString("BILLMODEL"))) {
			requestBody.setBillClass("2");// 票据种类 1:纸质；2：电子
		} else {
			throw new EOSException("票据种类错误");
		}
		requestBody.setCustAcctNo(tbLoanZh.getString("zh"));// 客户账号
		requestBody.setCancelType("1");// 撤销方式
		for (int i = 0; i < tbBizTxxxApplys.length; i++) {
			FMT_CRMS_SVR_S01001010021004_IN_SUB sub = new FMT_CRMS_SVR_S01001010021004_IN_SUB();
			DataObject tempData = (DataObject) tbBizTxxxApplys[i];
			sub.setPromNoteNo(tempData.getString("SUMMARY_NUM"));// 借据号
			subs[i] = sub;
		}
		requestBody.setArray(subs);
		request.setRequestHeader(requestHeader);
		request.setReqTranHeader(reqTranHeader);
		request.setRequestBody(requestBody);

		// 更新汇票状态
		updateState(loanId, tbBizTxxxApplys);

		S01001010021004ServiceStub.S01001010021004Response response = null;
		try {
			String zservice = "/WebService/CRMS_SVR/S01001010021004";
			String url = getUrl() + zservice;
			S01001010021004ServiceStub client = new S01001010021004ServiceStub(url);
			response = client.S01001010021004(request);
		} catch (AxisFault e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		}

		if (!response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
			log.error("调用接口失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("调用接口失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
		}
	}

	@Bizlet("电票拒绝")
	public void S01001110021009(String[] billnos, String busiType) throws Exception {
		Logger log = GitUtil.getLogger("ECDSClient.S01001110021009");
		S01001110021009ServiceStub.S01001110021009 request = new S01001110021009ServiceStub.S01001110021009();
		S01001110021009ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S01001110021009ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01001110021009ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S01001110021009ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01001110021009ServiceStub.FMT_CRMS_SVR_S01001110021009_IN requestBody = new S01001110021009ServiceStub.FMT_CRMS_SVR_S01001110021009_IN();

		for (int i = 0; i < billnos.length; i++) {
			requestBody.setBillNo(billnos[i]);// 票号
			requestBody.setBusiType(busiType);// 业务类型 1：承兑签发 2：贴现 3：质押

			// 添加ESB报文头
			CommRequestHeader commRequestHeader = new CommRequestHeader();
			CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
			initESBRequestHeader(commRequestHeader, commReqTranHeader, "S01001110021009", "", "");
			BeanUtils.copyProperties(requestHeader, commRequestHeader);
			BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);

			request.setRequestHeader(requestHeader);
			request.setReqTranHeader(reqTranHeader);
			request.setRequestBody(requestBody);

			S01001110021009ServiceStub.S01001110021009Response response = null;
			try {
				String zservice = "/WebService/CRMS_SVR/S01001110021009";
				String url = getUrl() + zservice;
				S01001110021009ServiceStub client = new S01001110021009ServiceStub(url);
				response = client.S01001110021009(request);
			} catch (AxisFault e) {
				e.printStackTrace();
				log.error("发送报文错误:" + e.getMessage());
				log.error("ESB流水号：" + requestHeader.getReqSeqNo());
				if ("java.net.SocketTimeoutException".equals(e.getCause().getClass().getName())) {
					throw new EOSException("发送报文错误: 链接超时");
				}
				throw new EOSException("发送报文错误:" + e.getMessage());
			} catch (RemoteException e) {
				e.printStackTrace();
				log.error("发送报文错误:" + e.getMessage());
				log.error("ESB流水号：" + requestHeader.getReqSeqNo());
				throw new EOSException("发送报文错误:" + e.getMessage());
			}

			if (!response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
				log.error("调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
				throw new EOSException("调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
			}
		}

	}

	@Bizlet("查询电票信息")
	public Credit021013OUT S01001010021013(Credit021013IN requestIn) throws Exception {
		Logger log = GitUtil.getLogger("ECDSClient.S01001010021013");

		/**
		 * 报文实体
		 */
		S01001010021013ServiceStub.S01001010021013 request = new S01001010021013ServiceStub.S01001010021013();
		S01001010021013ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S01001010021013ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01001010021013ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S01001010021013ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01001010021013ServiceStub.FMT_CRMS_SVR_S01001010021013_IN requestBody = new S01001010021013ServiceStub.FMT_CRMS_SVR_S01001010021013_IN();

		// 分页信息
		DataObject pageCond = requestIn.getPageCond();
		int begin = pageCond.getInt("begin");
		int length = pageCond.getInt("length");
		int currentPage = (begin + length) / length;

		try {
			// 添加报文体
			// 其他查询条件未添加
			requestBody.setBusiType(requestIn.getBusiType()); // 业务类型 :1-承兑签发2-贴现3-质押
			requestBody.setCustNo(requestIn.getCustNo());// 客户编号
			requestBody.setAoAcctNo(requestIn.getAoAcctNo());// 入账账号
			requestBody.setToBankNo(requestIn.getToBankNo());// 业务接收方行号 --该字段限制特定区域只能做该区域的业务
			requestBody.setAcptDt(requestIn.getAcptDt());// 出票日期

			requestBody.setCurrentPage(String.valueOf(currentPage));
			requestBody.setPageSize(pageCond.getString("length"));

			// 添加ESB报文头
			CommRequestHeader commRequestHeader = new CommRequestHeader();
			CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
			initESBRequestHeader(commRequestHeader, commReqTranHeader, "S01001010021013", "", "");
			BeanUtils.copyProperties(requestHeader, commRequestHeader);
			BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);

			request.setRequestHeader(requestHeader);
			request.setReqTranHeader(reqTranHeader);
			request.setRequestBody(requestBody);
		} catch (EOSException e) {
			log.error("数据错误:" + e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("数据错误:" + e.getMessage());
			throw new EOSException("数据错误:" + e.getMessage());
		}

		/**
		 * 发送报文
		 */
		S01001010021013ServiceStub.S01001010021013Response response = null;
		try {
			String zservice = "/WebService/CRMS_SVR/S01001010021013";
			String url = getUrl() + zservice;
			S01001010021013ServiceStub client = new S01001010021013ServiceStub(url);
			response = client.S01001010021013(request);
		} catch (AxisFault e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		}

		if (!response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
			log.error("调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
		}

		// 更新分页信息
		pageCond.setInt("currentPage", 0);
		pageCond.set("count", response.getResponseBody().getTotalRows());
		pageCond.set("totalPage", response.getResponseBody().getTotalPages());

		return setCredit021013OUT(response, pageCond, requestIn);

	}

	/**
	 * 组装查询风险票(021013)返回值
	 * @param response
	 * @param pageCond
	 * @param requestIn
	 * @return
	 */
	private Credit021013OUT setCredit021013OUT(S01001010021013ServiceStub.S01001010021013Response response, DataObject pageCond, Credit021013IN requestIn) {
		S01001010021013ServiceStub.FMT_CRMS_SVR_S01001010021013_OUT_SUB[] array = response.getResponseBody().getArray();
		int total = Integer.valueOf(response.getResponseBody().getTotalRows());
		TbBizPjxxApplyImpl[] tbBizPjxxApply = null;
		TbBizTxxxApplyImpl[] tbBizTxxxApply = null;
		if (total == 0) {
			tbBizPjxxApply = new TbBizPjxxApplyImpl[0];
			tbBizTxxxApply = new TbBizTxxxApplyImpl[0];
		} else {
			tbBizPjxxApply = new TbBizPjxxApplyImpl[array.length];
			tbBizTxxxApply = new TbBizTxxxApplyImpl[array.length];
		}

		// 银承
		if ("1".equals(requestIn.getBusiType()) && total > 0) {
			for (int i = 0; i < array.length; i++) {
				tbBizPjxxApply[i] = new TbBizPjxxApplyImpl();
				if (StringUtils.isEmpty(array[i].getBillNo())) {
					continue;
				}
				tbBizPjxxApply[i].setPjhm(array[i].getBillNo());// 汇票号码
				tbBizPjxxApply[i].setHpje(new BigDecimal(array[i].getBillMoney()));// 汇票金额
				// dataSet中这两个字段设置为String
				StringBuilder acptDt = new StringBuilder(array[i].getAcptDt());
				StringBuilder dueDt = new StringBuilder(array[i].getDueDt());
				tbBizPjxxApply[i].setHpcprq(acptDt.insert(6, "-").insert(4, "-").toString());// 出票日期
				tbBizPjxxApply[i].setHpdqrq(dueDt.insert(6, "-").insert(4, "-").toString());// 到期日期
				tbBizPjxxApply[i].setCprqc(array[i].getRemitter()); // 出票人全称
				tbBizPjxxApply[i].setCprzh(array[i].getRemitterAcctNo());// 出票人账号
				tbBizPjxxApply[i].setRemitterbankname(array[i].getRemitterBankName());// 出票行行名
				tbBizPjxxApply[i].setRemitterbankno(array[i].getRemitterBankNo().trim());// 出票行行号
				tbBizPjxxApply[i].setSkrqc(array[i].getPayee());// 收款人名称
				tbBizPjxxApply[i].setSkrzh(array[i].getPayeeAcctNo());// 收款人账号
				tbBizPjxxApply[i].setPayeebankname(array[i].getPayeeBankName());// 收款人开户行行名
				tbBizPjxxApply[i].setPayeebankno(array[i].getPayeeBankNo());// 收款人开户行行号
				tbBizPjxxApply[i].setAcceptorbankname(array[i].getAcceptorBankName());// 承兑行行名
				tbBizPjxxApply[i].setAcceptorbankno(array[i].getAcceptorBankNo());// 承兑行行号
				tbBizPjxxApply[i].setBillid(Long.parseLong(array[i].getBillId()));// 电票ID
				tbBizPjxxApply[i].setForbidflag(array[i].getForbidFlag());// 禁止背书标记 (银承使用)
				tbBizPjxxApply[i].setAmountDetailId(requestIn.getAmountDetailId());
				tbBizPjxxApply[i].setCurrencyCd("CNY");// 默认人民币
				tbBizPjxxApply[i].setHpxs("02");// 默认电票
				tbBizPjxxApply[i].setUpdateTime(GitUtil.getBusiDate());// 更新时间
				tbBizPjxxApply[i].setCreateTime(GitUtil.getBusiDate());// 创建时间
				tbBizPjxxApply[i].setHtbh(requestIn.getContractNum());// 合同编号
			}
		}
		// 贴现
		if ("2".equals(requestIn.getBusiType()) && total > 0) {
			for (int i = 0; i < array.length; i++) {
				tbBizTxxxApply[i] = new TbBizTxxxApplyImpl();
				if (StringUtils.isEmpty(array[i].getBillNo())) {
					continue;
				}
				tbBizTxxxApply[i].setBillno(array[i].getBillNo());// 汇票号码
				tbBizTxxxApply[i].setBillamt(new BigDecimal(array[i].getBillMoney()));// 汇票金额
				StringBuilder acptDt = new StringBuilder(array[i].getAcptDt());
				StringBuilder dueDt = new StringBuilder(array[i].getDueDt());
				tbBizTxxxApply[i].setBillbegindate(acptDt.insert(6, "-").insert(4, "-").toString());// 出票日期
				tbBizTxxxApply[i].setBillenddate(dueDt.insert(6, "-").insert(4, "-").toString());// 到期日期
				tbBizTxxxApply[i].setInterate(new BigDecimal(array[i].getRate()));// 贴现利率
				tbBizTxxxApply[i].setTakeoutacname(array[i].getRemitter());// 出票人全称
				tbBizTxxxApply[i].setTakeoutacbankno(array[i].getRemitterBankNo().trim());// 出票人开户行账号
				tbBizTxxxApply[i].setTakeoutacno(array[i].getRemitterAcctNo());// 出票人开户行行号
				tbBizTxxxApply[i].setTakeoutacbankname(array[i].getRemitterBankName());// 出票人开户行名称
				tbBizTxxxApply[i].setBenename(array[i].getPayee());// 收款人全称
				tbBizTxxxApply[i].setBeneno(array[i].getPayeeAcctNo());// 收款人开户行账号
				tbBizTxxxApply[i].setBenebankno(array[i].getPayeeBankNo());// 收款人开户行行号
				tbBizTxxxApply[i].setBenebankname(array[i].getPayeeBankName());// 收款人开户行名称
				tbBizTxxxApply[i].setBillacname(array[i].getAcceptor());// 承兑人全称
				tbBizTxxxApply[i].setBillacno(array[i].getAcceptorAcctNo());// 承兑人账号
				tbBizTxxxApply[i].setBillbankname(array[i].getAcceptorBankName());// 承兑行名称
				tbBizTxxxApply[i].setBillbankno(array[i].getAcceptorBankNo());// 承兑行行号
				// 票据 1:银票;2:商票
				// 信贷 0:银行承兑汇票;1:商业承兑汇票
				if ("1".equals(array[i].getBillType())) {
					tbBizTxxxApply[i].setBilltype("0"); // 汇票类型
				} else if ("2".equals(array[i].getBillType())) {
					tbBizTxxxApply[i].setBilltype("1"); // 汇票类型
				}
				// 票据 1:纸质汇票;2:电子汇票
				// 信贷 01:纸质汇票;02:电子汇票
				tbBizTxxxApply[i].setBillmodel("02"); // 汇票模式 电子汇票
				tbBizTxxxApply[i].setOnlinemark(array[i].getOnlineMark());// 线上清算标识
				tbBizTxxxApply[i].setForbidflag(array[i].getTransForbidFlag());// 禁止背书标记(贴现使用)
				tbBizTxxxApply[i].setAmountDetailId(requestIn.getAmountDetailId());
				tbBizTxxxApply[i].setContractId(requestIn.getContractId());// 合同编号
				tbBizTxxxApply[i].setCurrsign("CNY"); // 默认人民币

				tbBizTxxxApply[i].set("aoAcctNo", array[i].getAoAcctNo());
			}
		}

		Credit021013OUT responseOut = new Credit021013OUT();
		responseOut.setPageCond(pageCond);
		responseOut.setTbBizPjxxApply(tbBizPjxxApply);
		responseOut.setTbBizTxxxApply(tbBizTxxxApply);

		return responseOut;
	}

	@Bizlet("查询风险票")
	public String S01001010021020(String billno) throws Exception {
		Logger log = GitUtil.getLogger("ECDSClient.S01001010021020");

		S01001010021020ServiceStub.S01001010021020 request = new S01001010021020ServiceStub.S01001010021020();
		S01001010021020ServiceStub.FMT_SOAP_UTF8_RequestHeader requestHeader = new S01001010021020ServiceStub.FMT_SOAP_UTF8_RequestHeader();
		S01001010021020ServiceStub.FMT_SOAP_UTF8_ReqTranHeader reqTranHeader = new S01001010021020ServiceStub.FMT_SOAP_UTF8_ReqTranHeader();
		S01001010021020ServiceStub.FMT_CRMS_SVR_S01001010021020_IN requestBody = new S01001010021020ServiceStub.FMT_CRMS_SVR_S01001010021020_IN();

		FMT_CRMS_SVR_S01001010021020_IN_SUB[] subs = new FMT_CRMS_SVR_S01001010021020_IN_SUB[1];
		CommRequestHeader commRequestHeader = new CommRequestHeader();
		CommReqTranHeader commReqTranHeader = new CommReqTranHeader();
		initESBRequestHeader(commRequestHeader, commReqTranHeader, "S01001010021020", GitUtil.getCurrentOrgCd(), GitUtil.getCurrentPositionCode());
		BeanUtils.copyProperties(requestHeader, commRequestHeader);
		BeanUtils.copyProperties(reqTranHeader, commReqTranHeader);
		subs[0] = new FMT_CRMS_SVR_S01001010021020_IN_SUB();

		subs[0].setBillNo(billno); // 汇票号码

		requestBody.setArray(subs);
		request.setRequestHeader(requestHeader);
		request.setReqTranHeader(reqTranHeader);
		request.setRequestBody(requestBody);

		/**
		 * 发送报文
		 */
		S01001010021020ServiceStub.S01001010021020Response response = null;
		try {
			String zservice = "/WebService/CRMS_SVR/S01001010021020";
			String url = getUrl() + zservice;
			S01001010021020ServiceStub client = new S01001010021020ServiceStub(url);
			response = client.S01001010021020(request);
		} catch (AxisFault e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			log.error("发送报文错误:" + e.getMessage());
			log.error("ESB流水号：" + requestHeader.getReqSeqNo());
			throw new EOSException("发送报文错误:" + e.getMessage());
		}

		if (!response.getResTranHeader().getHRetCode().equals("AAAAAAA")) {
			log.error("调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo());
			return "调用失败，错误码：" + response.getResTranHeader().getHRetCode() + " 错误信息:" + response.getResTranHeader().getHRetMsg() + " ESB流水号：" + requestHeader.getReqSeqNo();
		}

		FMT_CRMS_SVR_S01001010021020_OUT_SUB[] retuList = response.getResponseBody().getArray();
		if (retuList == null || retuList.length < 1) {
			return "该票为风险票";
		}
		return "正常";
	}

	/**
	 * 更新撤销汇票状态
	 * @param loanId
	 * @param applys
	 */
	private void updateState(String loanId, Object[] applys) {
		DataObject loanInfo = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanInfo");
		loanInfo.set("loanId", loanId);
		DatabaseUtil.expandEntity("default", loanInfo);
		loanInfo.set("loanStatus", "04");
		DatabaseUtil.updateEntity("default", loanInfo);
		for (int k = 0; k < applys.length; k++) {
			DataObject hpsummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
			DataObject hpamt = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanHpAmt");
			DataObject tempData = (DataObject) applys[k];
			String summaryNum = tempData.getString("SUMMARY_NUM"); // 借据编号 (唯一编号,传主键)
			hpsummary.set("summaryNum", summaryNum);
			DatabaseUtil.expandEntityByTemplate("default", hpsummary, hpsummary);
			hpsummary.set("summaryStatusCd", "09");
			hpsummary.set("backCd", "09");
			DatabaseUtil.updateEntity("default", hpsummary);
			hpamt.set("summaryNum", summaryNum);
			DatabaseUtil.expandEntityByTemplate("default", hpamt, hpamt);
			hpamt.set("billState", "1");
			DatabaseUtil.updateEntity("default", hpamt);
		}
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("partyId", loanInfo.getString("partyId"));
		DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
	}

	/**
	 * 是否我行承兑
	 * @param FBHHHH
	 * @return
	 */
	private String isAccp(String FBHHHH, Logger log) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("FBHHHH", FBHHHH);
		Object[] FBHQSH = DatabaseExt.queryByNamedSql("default", "com.primeton.ecds.client.ecds.queryFBHQSH", map); // 票据明细
		if (FBHQSH.length < 1) {
			log.error("承兑行行号错误");
			throw new EOSException("承兑行行号错误");
		}
		DataObject data = (DataObject) FBHQSH[0];
		if ("313659000016".equals(data.getString("FBHQSH"))) {
			return "1"; // 1：我行承兑
		} else {
			return "0"; // 0:非我行承兑
		}
	}

	private void initESBRequestHeader(CommRequestHeader requestHeader, CommReqTranHeader reqTranHeader, String serviceCode, String orgNum, String userNum) {
		requestHeader.setVersionNo("ESB0001-0001"); // 版本号
		requestHeader.setReqSysCode("01201"); // 请求方系统代码
		requestHeader.setReqSecCode(""); // 安全节点号
		requestHeader.setTxType("RQ"); // RQ
		requestHeader.setTxMode("0"); // 0-正常 1-冲销2-冲正 3-重发
		requestHeader.setTxCode(serviceCode); // soapenv服务码
		requestHeader.setReqDate(GitUtil.getBusiDateYYYYMMDD()); // 业务日期
		requestHeader.setReqTime(GitUtil.getBusiDateYYYYMMDD() + GitUtil.getBusiTimeStr()); // 机器时间戳
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = df.format(new Date());
		String reqSeqNo = time + UUID.randomUUID().toString().replace("-", "").substring(10, 18);
		requestHeader.setReqSeqNo(reqSeqNo); // 请求方交易流水号
		requestHeader.setChanlNo("13"); // 渠道号（字符）
		requestHeader.setBrch(orgNum); // 机构编号
		if (StringUtils.isEmpty(orgNum)) {
			requestHeader.setBrch(GitUtil.getCurrentOrgCd()); // 机构编号
		}
		requestHeader.setTermNo(""); // 终端号
		requestHeader.setOper(userNum); // 柜员
		if (StringUtils.isEmpty(userNum)) {
			requestHeader.setOper(GitUtil.getCurrentPositionCode()); // 柜员
		}
		requestHeader.setSendFileName(""); // 发送文件名
		requestHeader.setBeginRec(""); // 开始记录数
		requestHeader.setMaxRec(1); // 一次查询最大记录数
		requestHeader.setFileHMac(""); // 文件摘要
		requestHeader.setHMac(""); // 报文摘要

		reqTranHeader.setHPinSeed("");// PIN种子
		reqTranHeader.setHOriChnl("");// 源渠道
		reqTranHeader.setHSecFlag("0");// 安全标志
		reqTranHeader.setHPwdFlag("0");// 加密标志
		reqTranHeader.setHCombFlag("0");// 组合标志
		reqTranHeader.setHSvcInfo("zuhejy_01");// 服务信息
		reqTranHeader.setHSecInfoVerNo("");// 安全信息版本号
		reqTranHeader.setHSysChnl("");// 渠道号
		reqTranHeader.setHLegaObj("9999");// 责任承担者
		reqTranHeader.setHMsgRefNo("");// 消息参考号
		reqTranHeader.setHTermNo("");// 终端号
		reqTranHeader.setHCityCd("");// 城市代码
		reqTranHeader.setHBrchNo(orgNum);// 发送方机构ID
		if (StringUtils.isEmpty(orgNum)) {
			reqTranHeader.setHBrchNo(GitUtil.getCurrentOrgCd());// 发送方机构ID
		}
		reqTranHeader.setHUserID(userNum);// 服务请求者
		if (StringUtils.isEmpty(userNum)) {
			reqTranHeader.setHUserID(GitUtil.getCurrentPositionCode());// 服务请求者
		}
		reqTranHeader.setHTxnCd("");// 交易代码
		reqTranHeader.setHTxnMod("");// 交易模式
		reqTranHeader.setHReserveLen("");// 保留数量字段
		reqTranHeader.setHSenderSvcCd("");// 发起端服务码
		reqTranHeader.setHSenderSeq("");// 发起端流水
		reqTranHeader.setHSenderDate("");// 发起端日期
		reqTranHeader.setHAuthUserID("");// 授权服务请求者
		reqTranHeader.setHAuthVerfInfo("");// 授权验证信息
		reqTranHeader.setHAuthFlag("");// 授权标志
		reqTranHeader.setHRefSeq("");// 关联流水
		reqTranHeader.setHAuthSeri("");// 授权序号
		reqTranHeader.setHHostSeq("");// 核心流水号
		reqTranHeader.setHRefDt("");// 关联日期
		reqTranHeader.setHSvcVer("");// 服务版本号
		reqTranHeader.setHreserveMsg("");// 保留信息字段
	}
}
