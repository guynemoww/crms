package com.bos.gjService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.primeton.mgrcore.client.DateTools;
import commonj.sdo.DataObject;

@Bizlet("PjtoLoanServiceJavaImpl")
public class PjtoLoanServiceJavaImpl {
	private TraceLogger log = new TraceLogger(GjtoLoanServiceImpl.class);

	@Bizlet("")
	public P001Response executeP001(P001Request p001Request) {

		System.out.println("票据调用INTERFACE---P001。。。");
		P001Response rs = new P001Response();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setVersionNo(p001Request.getRequestHeader().getVersionNo());
		responseHeader.setReqSysCode(p001Request.getRequestHeader().getReqSysCode());
		responseHeader.setReqSecCode(p001Request.getRequestHeader().getReqSecCode());
		responseHeader.setTxType(p001Request.getRequestHeader().getTxType());
		responseHeader.setTxMode(p001Request.getRequestHeader().getTxMode());
		responseHeader.setTxCode(p001Request.getRequestHeader().getReqDate());
		responseHeader.setReqTime(p001Request.getRequestHeader().getReqTime().substring(8, 14));
		responseHeader.setReqSeqNo(p001Request.getRequestHeader().getReqSeqNo());
		responseHeader.setSvrDate(GitUtil.getBusiDateYYYYMMDD());
		responseHeader.setSvrTime(DateTools.getTime20());
		responseHeader.setSvrSeqNo(DateTools.getReqSeqNo());
		responseHeader.setRecvFileName("");
		responseHeader.setTotNum(0);
		responseHeader.setCurrNum(0);
		responseHeader.setFileHMac(p001Request.getRequestHeader().getFileHMac());
		responseHeader.setHmac(p001Request.getRequestHeader().getHmac());
		rs.setResponseHeader(responseHeader);
		ResTranHeader resTranHeader = new ResTranHeader();
		rs.setResTranHeader(resTranHeader);
		P001ResponseBody responseBody = new P001ResponseBody();
		rs.setResponseBody(responseBody);

		try {
			// 判断是否票据的请求方系统代码---01501  暂时未知
			if ("01701".equals(p001Request.getRequestHeader().getReqSysCode())) {
				// 判断交易码---WY001
				if ("P001".equals(p001Request.getReqTranHeader().getHTxnCd())) {
					// 本次交易的日志记录
					DataObject outInterTransLog = DataObjectUtil.createDataObject("com.bos.dataset.sys.OutInterTransLog");
					outInterTransLog.set("localDate", p001Request.getRequestHeader().getReqDate());
					outInterTransLog.set("channelFlag", p001Request.getRequestHeader().getReqSysCode());
					outInterTransLog.set("serverCod", p001Request.getReqTranHeader().getHTxnCd());
					outInterTransLog.set("localTime", p001Request.getRequestHeader().getReqTime());
					outInterTransLog.set("orgCode", p001Request.getRequestHeader().getBrch());
					outInterTransLog.set("userCode", p001Request.getRequestHeader().getOper());
					DatabaseUtil.insertEntity("default", outInterTransLog);
					/**
					 * 参数校验与参数获取
					 */
					// 参数校验---ECIF客户编号
					String ecifPartyNum = p001Request.getRequestBody().getEcifPartyNum();
					if (null == ecifPartyNum || "".equals(ecifPartyNum)) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{客户编号}不能为空]");
						return rs;
					}
					// 查询客户信息
					DataObject tbCsmParty = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
					tbCsmParty.set("ecifPartyNum", ecifPartyNum);
					DatabaseUtil.expandEntityByTemplate("default", tbCsmParty, tbCsmParty);
					if (tbCsmParty.get("partyId") == null) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[信贷系统未查询到客户号[" + ecifPartyNum + "]对应的客户信息]");
						return rs;
					}
					String partyId = (String) tbCsmParty.get("partyId");
					// 参数校验---借据号
					if (null == p001Request.getRequestBody().getSummaryNum() || "".equals(p001Request.getRequestBody().getSummaryNum())) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[参数{借据编号}不能为空]");
						return rs;
					}
					String summaryNum = p001Request.getRequestBody().getSummaryNum();// 借据编号
					// 参数校验---恢复金额
					//if (null == p001Request.getRequestBody().getHappenAmount()) {
						//rs.getResTranHeader().setHRetCode("BBBBBBB");
						//rs.getResTranHeader().setHRetMsg("交易失败[参数{额度恢复金额}不能为空]");
						//return rs;
					//}
					//BigDecimal happenAmount = p001Request.getRequestBody().getHappenAmount();// 恢复金额
					// 查询借据信息
					DataObject loanSummary = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanSummary");
					loanSummary.set("summaryNum", summaryNum);
					DatabaseUtil.expandEntityByTemplate("default", loanSummary, loanSummary);
					if (loanSummary.get("loanId") == null) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号"+summaryNum+"对应的借据信息]");
						return rs;
					}
					String contractId = loanSummary.getString("contractId");
					DataObject contract = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
					contract.set("contractId", contractId);
					DatabaseUtil.expandEntityByTemplate("default", contract, contract);
					if (loanSummary.get("contractId") == null) {
						rs.getResTranHeader().setHRetCode("BBBBBBB");
						rs.getResTranHeader().setHRetMsg("交易失败[未查询到借据号"+summaryNum+"对应的合同信息]");
						return rs;
					}
					//更新保证金金额
					DataObject tbConSubcontractRel = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontractRel");
					tbConSubcontractRel.set("contractId", contractId);
					DatabaseUtil.expandEntityByTemplate("default", tbConSubcontractRel, tbConSubcontractRel);
					if (tbConSubcontractRel.get("conSubconId") != null) {
						String subcontractId = tbConSubcontractRel.getString("subcontractId");
						DataObject tbConSubcontract = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConSubcontract");
						tbConSubcontract.set("subcontractId", subcontractId);
						DatabaseUtil.expandEntity("default", tbConSubcontract);
						BigDecimal bzjje = tbConSubcontract.getBigDecimal("bzjje");//合同的保证金金额
						BigDecimal bzjje_ = loanSummary.getBigDecimal("bzjje");//借据对应的保证金金额
						bzjje = bzjje.subtract(null==bzjje_?BigDecimal.ZERO:bzjje_);//合同的保证金金额=合同的保证金金额-借据对应的保证金金额
						
						if(bzjje.compareTo(BigDecimal.ZERO)<=0){//如果保证金金额<=0  就是没有保证金了  都设置为0
							tbConSubcontract.set("bzjje", BigDecimal.ZERO);
							tbConSubcontract.set("bzjbl", BigDecimal.ZERO);
						}else{
							tbConSubcontract.set("bzjje", bzjje);
							//tbConSubcontract.set("bzjbl", bzjje.divide(amt.subtract(loanSummary.getBigDecimal("summaryAmt")), 4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
						}
						DatabaseUtil.saveEntity("default", tbConSubcontract);
					}
					loanSummary.set("jjye", BigDecimal.ZERO);// 借据余额
					loanSummary.set("summaryStatusCd", "09");// 借据借据状态：已撤销
					loanSummary.set("updateTime", GitUtil.getBusiDate());// 最新更新时间
					//loanSummary.set("bzjje", BigDecimal.ZERO);// 保证金金额
					//loanSummary.set("bzjbl", BigDecimal.ZERO);// 保证金比例
					DatabaseUtil.saveEntity("default", loanSummary);
					// 调用额度重算
					Map map2 = new HashMap();
					map2.put("partyId", partyId);
					DatabaseExt.executeNamedSql("default", "com.bos.conApply.conApply.updateCreditLimit", map2);
					
					rs.getResTranHeader().setHRetCode("AAAAAAA");
					rs.getResTranHeader().setHRetMsg("交易成功");
				} else {
					rs.getResTranHeader().setHRetCode("BBBBBBB");
					rs.getResTranHeader().setHRetMsg("交易失败[交易编码错误]");
				}
			} else {
				rs.getResTranHeader().setHRetCode("BBBBBBB");
				rs.getResTranHeader().setHRetMsg("交易失败[非票据系统不能访问此服务]");
			}
		} catch (Exception e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			e.printStackTrace();
			log.info("PjtoLoanServiceImpl--" + e.getMessage());
		} catch (Throwable e) {
			rs.getResTranHeader().setHRetCode("BBBBBBB");
			rs.getResTranHeader().setHRetMsg("交易失败[信贷系统底层发生异常]");
			e.printStackTrace();
			log.info("PjtoLoanServiceImpl--" + e.getMessage());
		}
		return rs;
	}
}
