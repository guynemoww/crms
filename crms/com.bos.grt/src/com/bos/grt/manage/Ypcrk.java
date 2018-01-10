package com.bos.grt.manage;

import java.math.BigDecimal;

import com.bos.pub.GitUtil;
import com.bos.pub.socket.EsbSocketService;
import com.bos.pub.socket.service.request.base.EsbAppHeadRq;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.spring.TraceLogger;
import com.eos.system.annotation.Bizlet;
import com.git.easyrule.util.DateHelper;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.primeton.bfs.tp.common.exception.EOSException;
import com.sun.org.apache.bcel.internal.generic.NEW;

import commonj.sdo.DataObject;

@Bizlet("押品出入库")
public class Ypcrk {
	public static TraceLogger logger = new TraceLogger(Ypcrk.class);

	@Bizlet("押品入库")
	public String yprk(DataObject[] regCards) throws EOSException {
		try {
			for (DataObject regCard : regCards) {
				// 客户信息
				DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
				party.set("partyId", regCard.get("PARTY_ID"));
				DatabaseUtil.expandEntity("default", party);

				DataObject dbxgxx = DataObjectUtil.createDataObject("com.bos.pub.sys.HxRq02002000002BODY02");
				dbxgxx.set("txnCd", "3301");
				dbxgxx.set("rgonCd", GitUtil.getRgonCd());
				dbxgxx.set("fileInd", "0");
				dbxgxx.set("acctRgonCd", regCard.get("SAVE_ORG").toString().substring(0, 2));
				dbxgxx.set("acctBrId", regCard.get("SAVE_ORG").toString().substring(2));
				dbxgxx.set("trdTp", "B");// B为担保下柜
				dbxgxx.set("oprTp", "0");// 0-入库 1-出库
				dbxgxx.set("ccyTp", "CNY");
				dbxgxx.set("complNo", regCard.get("IN_OUT_NO"));// 下柜编号
				// 根据合同Id查合同Num
				String contractId = (String) regCard.get("CONTRACT_ID");

				DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
				tbConContractInfo.set("contractId", contractId);
				DatabaseUtil.expandEntity("default", tbConContractInfo);

				dbxgxx.set("ctrNo", (String) tbConContractInfo.get("contractNum"));
				dbxgxx.set("cstNo", (String) party.get("ecifPartyNum"));
				dbxgxx.set("cstNo", null);// ----------------------test
				dbxgxx.set("txnAmt", new BigDecimal((String) regCard.get("REG_ORG_MONEY")));// 交易金额
				dbxgxx.set("stopDt", DateHelper.formatDateYYYYMMDD(regCard.getDate("REG_DUE_DATE")));// 截止日期

				String dylx = (String) regCard.get("COLL_TYPE");
				dbxgxx.set("gryTp", "1");// 担保类型在核心为业务发生类型：首次、新增、借新还旧。。。
				if ("01".equals(dylx)) {// 抵押
					dbxgxx.set("acctCd", "612101");
				} else {// 质押
					dbxgxx.set("acctCd", "612102");
				}

				EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
				iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
				iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorehxjygy());
				iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorehxjygy());
				DataObject object = EsbSocketService.instance().socketDataObject("RQ02002000002BODY02", iEsbAppHeadRq, dbxgxx);
				String returnCode = (String) object.get("ReturnCode");
				if (!"00000000000000".equals(returnCode)) {
					return (String) object.get("ReturnMsg");
				}
			}
			return null;

		} catch (Throwable e) {
			e.printStackTrace();
			return "入库失败";
		}
	}

	@Bizlet("押品出库")
	public void ypck(Object[] regCards) throws EOSException {
		try {
			for (int i = 0; i < regCards.length; i++) {
				// 客户信息
				DataObject regCard = (DataObject) regCards[i];
				DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
				party.set("partyId", regCard.get("PARTY_ID"));
				DatabaseUtil.expandEntity("default", party);

				DataObject dbxgxx = DataObjectUtil.createDataObject("com.bos.pub.sys.HxRq02002000002BODY02");
				dbxgxx.set("txnCd", "3301");
				dbxgxx.set("rgonCd", GitUtil.getRgonCd());
				dbxgxx.set("fileInd", "0");
				dbxgxx.set("acctRgonCd", regCard.get("SAVE_ORG").toString().substring(0, 2));
				dbxgxx.set("acctBrId", regCard.get("SAVE_ORG").toString().substring(2));
				dbxgxx.set("trdTp", "B");// B为担保下柜
				dbxgxx.set("oprTp", "1");// 0-入库 1-出库
				dbxgxx.set("ccyTp", "CNY");
				dbxgxx.set("complNo", regCard.get("IN_OUT_NO"));// 下柜编号
				/*
				 * //根据合同Id查合同Num String contractId =
				 * (String)regCard.get("CONTRACT_ID");
				 * 
				 * DataObject tbConContractInfo =
				 * DataObjectUtil.createDataObject
				 * ("com.bos.dataset.crt.TbConContractInfo");
				 * tbConContractInfo.set("contractId", contractId);
				 * DatabaseUtil.expandEntity("default", tbConContractInfo);
				 */

				dbxgxx.set("ctrNo", (String) regCard.get("CONTRACT_NUM"));
				dbxgxx.set("cstNo", (String) party.get("ecifPartyNum"));
				dbxgxx.set("cstNo", null);// ----------------------test
				dbxgxx.set("txnAmt", (BigDecimal) regCard.get("REG_ORG_MONEY"));// 交易金额
				dbxgxx.set("stopDt", DateHelper.formatDateYYYYMMDD(regCard.getDate("REG_DUE_DATE")));// 截止日期

				String dylx = (String) regCard.get("COLL_TYPE");
				dbxgxx.set("gryTp", "1");// 担保类型在核心为业务发生类型：首次、新增、借新还旧。。。
				if ("01".equals(dylx)) {// 抵押
					dbxgxx.set("acctCd", "612101");
				} else {// 质押
					dbxgxx.set("acctCd", "612102");
				}

				EsbAppHeadRq iEsbAppHeadRq = new EsbAppHeadRq();
				iEsbAppHeadRq.setBranchId(GitUtil.getBranchId());
				iEsbAppHeadRq.setTranTellerNo(GitUtil.getCorehxjygy());
				iEsbAppHeadRq.setAuthrTellerNo(GitUtil.getCorehxjygy());
				DataObject object = EsbSocketService.instance().socketDataObject("RQ02002000002BODY02", iEsbAppHeadRq, dbxgxx);
				String returnCode = (String) object.get("ReturnCode");
				if (!"00000000000000".equals(returnCode)) {
					throw new EOSException((String) object.get("ReturnMsg"));
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new EOSException(e.getMessage());
		}
	}
}
