package com.bos.pay;

import java.util.HashMap;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.exception.EOSException;

import commonj.sdo.DataObject;

@Bizlet("")
public class LoanInsertPJ {

	@Bizlet("票据业务出账时插入票据信息")
	public void insertXXXX(String amountDetailId, String productType, DataObject tbLoanInfo) throws EOSException {
		// 银承
		if ("01008001".equals(productType) || "01008002".equals(productType) || "01008010".equals(productType)) {
			insertPJXX(amountDetailId, tbLoanInfo);
		}
		// 贴现
		if ("01006001".equals(productType) || "01006002".equals(productType) || "01006010".equals(productType)) {
			insertTXXX(amountDetailId, tbLoanInfo);
		}
	}

	/**
	 * 银承出账时插入银承票据信息
	 */
	private void insertPJXX(String amountDetailId, DataObject tbLoanInfo) throws EOSException {
		// 查询合同信息
		DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo");
		tbConContractInfo.setString("contractId", tbLoanInfo.getString("contractId"));
		DatabaseUtil.expandEntity("default", tbConContractInfo);
		// 查询票据明细 明细ID+合同编号
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("amountDetailId", amountDetailId);
		map.put("contractNum", tbConContractInfo.getString("contractNum"));
		Object[] pjxx = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryPJXX", map); // 票据明细
		if (pjxx == null || pjxx.length < 1) {
			throw new EOSException("票据明细清单为空");
		}else{
			map.put("contractId", tbLoanInfo.getString("contractId"));
			Object[] pjxx1 = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryPJXX", map); // 票据明细
			if(null!=pjxx1 && pjxx1.length>0){
				pjxx=pjxx1;
			}
		}
		DataObject tempPj = (DataObject) pjxx[0];
		String pjzl = tempPj.getString("HPXS"); // 票据种类

		HashMap<String, String> mapOth = new HashMap<String, String>();
		mapOth.put("orgCode", GitUtil.getCurrentOrgCd());
		if ("01".equals(pjzl)) {
			mapOth.put("type", "2"); // 银承纸票
		} else if ("02".equals(pjzl)) {
			mapOth.put("type", "3"); // 银承电票
		} else {
			throw new EOSException("票据类型错误");
		}
		Object[] other = DatabaseExt.queryByNamedSql("default", "com.bos.comm.pub.orgRel.selectOrgRel", mapOth);
		if (other.length < 1) {
			throw new EOSException("未配置入账机构，请联系管理员");
		}
		DataObject dataOth = (DataObject) other[0];

		// 纸票更新承兑行,出票行
		if ("01".equals(pjzl)) {
			HashMap<String, String> map1 = new HashMap<String, String>();
			map1.put("amountDetailId", amountDetailId);
			map1.put("contractNum", tbConContractInfo.getString("contractNum"));
			map1.put("acceptorbankno", dataOth.getString("BANKNO"));
			map1.put("acceptorbankname", dataOth.getString("KEHYWM"));
			map1.put("remitterbankno", dataOth.getString("BANKNO"));
			map1.put("remitterbankname", dataOth.getString("KEHYWM"));
			DatabaseExt.executeNamedSql("default", "com.bos.pay.loanHp.updateAcceptorbank", map1);
		}

		// 插入发放明细
		DataObject[] tbLoanHpAmt = new DataObject[pjxx.length];
		for (int i = 0; i < pjxx.length; i++) {
			DataObject data = (DataObject) pjxx[i];
			tbLoanHpAmt[i] = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanHpAmt");
			String summaryNum = GitUtil.getSeqNo("JJ", GitUtil.getCurrentOrgCd()); // 借据号
			tbLoanHpAmt[i].set("applyDetailId", data.getString("APPLY_DETAIL_ID"));// 票据明细ID
			tbLoanHpAmt[i].set("loanId", tbLoanInfo.getString("loanId"));// 出账id
			tbLoanHpAmt[i].set("loanAmt", data.getString("HPJE"));// 出账金额
			tbLoanHpAmt[i].set("pyeAcctNm", data.getString("SKRQC"));// 收款人户名
			tbLoanHpAmt[i].set("pyeAcctNo", data.getString("SKRZH"));// 收款人账号
			tbLoanHpAmt[i].set("pyeOpenAcctBnkNm", data.getString("PAYEEBANKNAME"));// 收款人开户行行名
			tbLoanHpAmt[i].set("drftExpDt", data.getString("HPDQRQ"));// 汇票到期日期
			tbLoanHpAmt[i].set("issuDt", data.getString("HPCPRQ"));// 出票日期
			tbLoanHpAmt[i].set("billNo", data.getString("PJHM"));// 票据号
			tbLoanHpAmt[i].set("billState", "0");// 票据状态(0-正常；1-删除)'
			tbLoanHpAmt[i].set("summaryNum", summaryNum);// 借据号
			
			tbLoanHpAmt[i].set("sortOrder", data.getString("SORT_ORDER"));// 排序序号
			
			if (other.length > 0) { // 目前版本中只应该有一个配置的入账机构
				tbLoanHpAmt[i].set("drweBnkNm", dataOth.getString("KEHYWM"));// 付款行全称
				tbLoanHpAmt[i].set("drweBnkNo", dataOth.getString("BANKNO"));// 付款行行号
				tbLoanHpAmt[i].set("drweBnkAdr", dataOth.getString("ORGADDR"));// 付款行地址
			}
		}
		DatabaseUtil.insertEntityBatch("default", tbLoanHpAmt);

		// 更新发放信息
		tbLoanInfo.setDate("endDate", tbConContractInfo.getDate("endDate"));// 到期日期
		tbLoanInfo.set("loanAmt", tbConContractInfo.getString("rmbAmt"));// 出账金额
		tbLoanInfo.set("rmbAmt", tbConContractInfo.getString("rmbAmt"));// 合同金额*汇率 目前默认人民币汇率1
		tbLoanInfo.set("pjzl", pjzl);// 票据种类
		tbLoanInfo.set("hpzs", pjxx.length);// 汇票张数
		tbLoanInfo.set("loanOrg", dataOth.getString("ACC_ORG_NO"));// 入账机构
		tbLoanInfo.set("comAmt", null);
		tbLoanInfo.set("drweBnkNm", dataOth.getString("KEHYWM"));
		tbLoanInfo.set("drweBnkNo", dataOth.getString("BANKNO"));
		tbLoanInfo.set("drweBnkAdr", dataOth.getString("ORGADDR"));
		DatabaseUtil.updateEntity("default", tbLoanInfo);
	}

	/**
	 * 贴现出账时插入贴现票据信息
	 */
	private void insertTXXX(String amountDetailId, DataObject tbLoanInfo) throws EOSException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("amountDetailId", amountDetailId);
		map.put("contractId", tbLoanInfo.getString("contractId"));
		Object[] txxx = DatabaseExt.queryByNamedSql("default", "com.bos.pay.loanHp.queryTXXX", map); // 贴现明细
		String pjzl = ""; // 票据种类

		DataObject[] tbLoanHpAmt = new DataObject[txxx.length];
		for (int i = 0; i < txxx.length; i++) {
			DataObject data = (DataObject) txxx[i];
			pjzl = data.getString("BILLMODEL");

			tbLoanHpAmt[i] = DataObjectUtil.createDataObject("com.bos.dataset.pay.TbLoanHpAmt");
			String summaryNum = GitUtil.getSeqNo("JJ", GitUtil.getCurrentOrgCd()); // 借据号

			tbLoanHpAmt[i].set("applyDetailId", data.getString("APPLY_DETAIL_ID"));// 票据明细ID
			tbLoanHpAmt[i].set("loanId", tbLoanInfo.getString("loanId"));// 出账id
			tbLoanHpAmt[i].set("loanAmt", data.getString("BILLAMT"));// 出账金额
			tbLoanHpAmt[i].set("pyeAcctNm", data.getString("BENENAME"));// 收款人户名
			tbLoanHpAmt[i].set("pyeAcctNo", data.getString("BENENO"));// 收款人账号
			tbLoanHpAmt[i].set("pyeOpenAcctBnkNm", data.getString("BENEBANKNAME"));// 收款人开户行行名
			tbLoanHpAmt[i].set("drftExpDt", data.getString("BILLENDDATE"));// 汇票到期日期
			tbLoanHpAmt[i].set("issuDt", data.getString("BILLBEGINDATE"));// 出票日期
			tbLoanHpAmt[i].set("billNo", data.getString("BILLNO"));// 票据号
			tbLoanHpAmt[i].set("billState", "0");// 票据状态(0-正常；1-删除)'
			tbLoanHpAmt[i].set("summaryNum", summaryNum);// 借据号
			tbLoanHpAmt[i].set("drweBnkNm", null);// 付款行全称
			tbLoanHpAmt[i].set("drweBnkNo", null);// 付款行行号
			tbLoanHpAmt[i].set("drweBnkAdr", null);// 付款行地址
			
			tbLoanHpAmt[i].set("sortOrder", data.getString("SORT_ORDER"));// 排序序号

		}
		DatabaseUtil.insertEntityBatch("default", tbLoanHpAmt);

		DataObject tbConContractInfo = DataObjectUtil.createDataObject("com.bos.dataset.crt.TbConContractInfo"); // 合同信息实体
		tbConContractInfo.setString("contractId", tbLoanInfo.getString("contractId"));
		DatabaseUtil.expandEntity("default", tbConContractInfo); // 查询数据

		tbLoanInfo.setDate("beginDate", GitUtil.getBusiDate());// 当前日期
		tbLoanInfo.setDate("endDate", tbConContractInfo.getDate("endDate"));// 到期日期
		tbLoanInfo.set("loanAmt", tbConContractInfo.getString("rmbAmt"));// 出账金额
		tbLoanInfo.set("rmbAmt", tbConContractInfo.getString("rmbAmt"));// 合同金额*汇率 目前默认人民币汇率1
		tbLoanInfo.set("pjzl", pjzl);// 票据种类
		tbLoanInfo.set("hpzs", txxx.length);// 汇票张数
		DatabaseUtil.updateEntity("default", tbLoanInfo);
	}
}
