package com.primeton.mgrcore;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.primeton.mgrcore.F11231;

public class OXD052ResBody implements Serializable {
	private static final long serialVersionUID = 5245862029155079210L;

	private String custNo;// 客户号
	private String custAcctNo;// 客户账号
	private String custName;// 客户账户名称
	private String sysAcct;// 系统账号
	private String brcnName;// 机构名称
	private String balanceBrchName;// 调账机构名称
	private String subAcctSeri;// 子账户序号
	private String contractNo;// 合同编号
	private String currCode;// 货币代号
	private String cashFlag;// 钞汇标志
	private String prdNo;// 产品代号
	private String reletype;// 关系类型
	private String acctType;// 账户分类
	private String cateAtrb;// 分类属性
	private String vchBatNo;// 凭证批号
	private String vchKind;// 凭证种类
	private String vchSerialNo;// 凭证序号
	private String payCondiditon;// 支付条件
	private String isAllowExChg;// 是否允许通兑
	private String uswFlag;// 通存通兑标志
	private String uswAre;// 通兑范围
	private String cashExchgFlag;// 现金通兑标志
	private String transExchFlag;// 转账通兑标志
	private String openAcctDate;// 开户日期
	private String canAccDate;// 销户日期
	private String acctStat;// 账户状态
	private String intrRat;// 执行利率
	private String rangeValue;// 浮动值
	private String rateFloatRatio;// 利率浮动比例
	private String accrrestAmt;// 账户余额
	private String availableAmt;// 可用余额
	private String pendSettlementAmt;// 待结算金额
	private String acctPendSettlemenAmt;// 账户结算余额
	private String latlyBusiDate;// 最近业务日期
	private String lostFlag;// 挂失标志
	private String singleVchStat;// 单式凭证状态
	private String ynFrozen;// 是否冻结标志
	private String frzAmt;// 冻结金额
	private String ctrlFlg;// 控制标志
	private String ctrlAmt;// 控制金额
	private String interestTotAmt;// 计息累计余额（积数）
	private String lstCul;// 上期内积数
	private String thisYearTotCul;// 本年累计积数
	private String unsignRate;// 未登折数
	private String lsttotCul;// 上年累计积数
	private String latlyChgRateDate;// 最近一次换折日期
	private String lowNum;// 行数
	private String depositTerm;// 存期
	private String trsferInmode;// 转存方式
	private String transferTime;// 转存期
	private String reseOnAmt;// 预定续存金额
	private String inCustAcct;// 转入客户账号
	private String paymentInterval;// 支取间隔
	private String outAcctPro;// 外管账户性质
	private String credLimt;// 贷方累计限额
	private String chappenFlag;// 贷方发生额
	private String creditRelationFlag;// 信贷关系标志
	private String crAcct;// 贷款账号
	private String canBuyNoteNum;// 可购买支票量
	private String allowNouseRate;// 允许未用比例
	private String istogetherFlag;// 是否合一标志
	private String backupAmt;// 备用金额
	private String workTime;// 有效期
	private String ynFlag;// 是否标志
	private String addr;// 联系地址
	private String shopContactPhone;// 商户联系电话
	private String acctOpenSignNo;// 账户开户许可证核准号
	private String businessWorkDate;// 营业执照有效日期
	private String chkProjectNo;// 核准件编号
	private String acct;// 账号
	private String custAcctNo1;// 客户账号1
	private String depositType;// 存款种类
	private String acctStat1;// 账户状态1
	private String ynRecon;// 是否对账标志
	private String balanceRange;// 对账范围
	private String latlyBalanceDate;// 最近对账日期
	private String acctChkFlag;// 账户核查标志
	private String latlyAcctChkDate;// 最近账户核查日期
	private String exChkFlg;// 外汇核查标志
	private String restandTotSyn;// 余额与总账同步
	private String groupProductNo;// 组合产品代号
	private String groupAcctNo;// 组合账户号
	private String getCashFlag;// 取现标志
	private String maturityWay;// 起息方式
	private String normalAcctiscostFlag;// 正常户是否收费标志
	private String unmoveAcctIsCostFlag;// 不动户是否收费标志
	private String isValid;// 是否计息标志
	private String dateOfValue;// 起息日期
	private String matuDat;// 到期日期
	private String amt;// 金额
	private String freezeBal;// 可冻余额
	private BigInteger recNum;// f11231对象循环记录数
	private List<F11231> f11231;
	private String belongObject;// 所属对象
	private String libProType;// 负债产品类型
	private String openBrch;// 开户机构
	private String transOpponentAcct;// 交易对手账号
	private String recvAcctName;// 收款户名
	private String brchNo;// 机构号
	private String otherBankName;// 对手行行名
	private String peopleBankNo;// 人行行号
	private String acctNoCode;// 客户账号代码
	private String backup2;// 备用字段2
	private String backup1;// 备用字段1
	private String fixedPayAmt;// 固定支取金额
	private String openAcctName;// 开户行名
	private String acctShortCode3;// 账户分类代码3
	private String prdDesc;// 产品说明
	private String everyTimeGetAmt;// 每期取款金额
	private String isLinkCycleLoan;// 是否关联循环贷款
	private String loanNoteNo;// 贷款借据号
	private String grantedAmt;// 已发放金额
	private String frzGrantableAmt;// 冻结可放金额
	private String grantableAmt;// 可发放金额
	private String connectCostBatFlag;// 联机费用批量后收标志
	private String batLatterRate;// 批量后收费频率
	private String ynExchSup;// 是否外汇监管标志
	private String fixedDepositAmt;// 固定存入金额
	private String flgDefault;// 标志缺省值
	private String agnt;// 代理人
	private String certKind;// 代理人证件种类
	private String agntCertNum;// 代理人证件号码
	private String agentNation;// 代理人国籍
	private String contactPhone;// 联系人电话

	public OXD052ResBody() {

	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getCustAcctNo() {
		return custAcctNo;
	}

	public void setCustAcctNo(String custAcctNo) {
		this.custAcctNo = custAcctNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getSysAcct() {
		return sysAcct;
	}

	public void setSysAcct(String sysAcct) {
		this.sysAcct = sysAcct;
	}

	public String getBrcnName() {
		return brcnName;
	}

	public void setBrcnName(String brcnName) {
		this.brcnName = brcnName;
	}

	public String getBalanceBrchName() {
		return balanceBrchName;
	}

	public void setBalanceBrchName(String balanceBrchName) {
		this.balanceBrchName = balanceBrchName;
	}

	public String getSubAcctSeri() {
		return subAcctSeri;
	}

	public void setSubAcctSeri(String subAcctSeri) {
		this.subAcctSeri = subAcctSeri;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getCashFlag() {
		return cashFlag;
	}

	public void setCashFlag(String cashFlag) {
		this.cashFlag = cashFlag;
	}

	public String getPrdNo() {
		return prdNo;
	}

	public void setPrdNo(String prdNo) {
		this.prdNo = prdNo;
	}

	public String getReletype() {
		return reletype;
	}

	public void setReletype(String reletype) {
		this.reletype = reletype;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getCateAtrb() {
		return cateAtrb;
	}

	public void setCateAtrb(String cateAtrb) {
		this.cateAtrb = cateAtrb;
	}

	public String getVchBatNo() {
		return vchBatNo;
	}

	public void setVchBatNo(String vchBatNo) {
		this.vchBatNo = vchBatNo;
	}

	public String getVchKind() {
		return vchKind;
	}

	public void setVchKind(String vchKind) {
		this.vchKind = vchKind;
	}

	public String getVchSerialNo() {
		return vchSerialNo;
	}

	public void setVchSerialNo(String vchSerialNo) {
		this.vchSerialNo = vchSerialNo;
	}

	public String getPayCondiditon() {
		return payCondiditon;
	}

	public void setPayCondiditon(String payCondiditon) {
		this.payCondiditon = payCondiditon;
	}

	public String getIsAllowExChg() {
		return isAllowExChg;
	}

	public void setIsAllowExChg(String isAllowExChg) {
		this.isAllowExChg = isAllowExChg;
	}

	public String getUswFlag() {
		return uswFlag;
	}

	public void setUswFlag(String uswFlag) {
		this.uswFlag = uswFlag;
	}

	public String getUswAre() {
		return uswAre;
	}

	public void setUswAre(String uswAre) {
		this.uswAre = uswAre;
	}

	public String getCashExchgFlag() {
		return cashExchgFlag;
	}

	public void setCashExchgFlag(String cashExchgFlag) {
		this.cashExchgFlag = cashExchgFlag;
	}

	public String getTransExchFlag() {
		return transExchFlag;
	}

	public void setTransExchFlag(String transExchFlag) {
		this.transExchFlag = transExchFlag;
	}

	public String getOpenAcctDate() {
		return openAcctDate;
	}

	public void setOpenAcctDate(String openAcctDate) {
		this.openAcctDate = openAcctDate;
	}

	public String getCanAccDate() {
		return canAccDate;
	}

	public void setCanAccDate(String canAccDate) {
		this.canAccDate = canAccDate;
	}

	public String getAcctStat() {
		return acctStat;
	}

	public void setAcctStat(String acctStat) {
		this.acctStat = acctStat;
	}

	public String getIntrRat() {
		return intrRat;
	}

	public void setIntrRat(String intrRat) {
		this.intrRat = intrRat;
	}

	public String getRangeValue() {
		return rangeValue;
	}

	public void setRangeValue(String rangeValue) {
		this.rangeValue = rangeValue;
	}

	public String getRateFloatRatio() {
		return rateFloatRatio;
	}

	public void setRateFloatRatio(String rateFloatRatio) {
		this.rateFloatRatio = rateFloatRatio;
	}

	public String getAccrrestAmt() {
		return accrrestAmt;
	}

	public void setAccrrestAmt(String accrrestAmt) {
		this.accrrestAmt = accrrestAmt;
	}

	public String getAvailableAmt() {
		return availableAmt;
	}

	public void setAvailableAmt(String availableAmt) {
		this.availableAmt = availableAmt;
	}

	public String getPendSettlementAmt() {
		return pendSettlementAmt;
	}

	public void setPendSettlementAmt(String pendSettlementAmt) {
		this.pendSettlementAmt = pendSettlementAmt;
	}

	public String getAcctPendSettlemenAmt() {
		return acctPendSettlemenAmt;
	}

	public void setAcctPendSettlemenAmt(String acctPendSettlemenAmt) {
		this.acctPendSettlemenAmt = acctPendSettlemenAmt;
	}

	public String getLatlyBusiDate() {
		return latlyBusiDate;
	}

	public void setLatlyBusiDate(String latlyBusiDate) {
		this.latlyBusiDate = latlyBusiDate;
	}

	public String getLostFlag() {
		return lostFlag;
	}

	public void setLostFlag(String lostFlag) {
		this.lostFlag = lostFlag;
	}

	public String getSingleVchStat() {
		return singleVchStat;
	}

	public void setSingleVchStat(String singleVchStat) {
		this.singleVchStat = singleVchStat;
	}

	public String getYnFrozen() {
		return ynFrozen;
	}

	public void setYnFrozen(String ynFrozen) {
		this.ynFrozen = ynFrozen;
	}

	public String getFrzAmt() {
		return frzAmt;
	}

	public void setFrzAmt(String frzAmt) {
		this.frzAmt = frzAmt;
	}

	public String getCtrlFlg() {
		return ctrlFlg;
	}

	public void setCtrlFlg(String ctrlFlg) {
		this.ctrlFlg = ctrlFlg;
	}

	public String getCtrlAmt() {
		return ctrlAmt;
	}

	public void setCtrlAmt(String ctrlAmt) {
		this.ctrlAmt = ctrlAmt;
	}

	public String getInterestTotAmt() {
		return interestTotAmt;
	}

	public void setInterestTotAmt(String interestTotAmt) {
		this.interestTotAmt = interestTotAmt;
	}

	public String getLstCul() {
		return lstCul;
	}

	public void setLstCul(String lstCul) {
		this.lstCul = lstCul;
	}

	public String getThisYearTotCul() {
		return thisYearTotCul;
	}

	public void setThisYearTotCul(String thisYearTotCul) {
		this.thisYearTotCul = thisYearTotCul;
	}

	public String getUnsignRate() {
		return unsignRate;
	}

	public void setUnsignRate(String unsignRate) {
		this.unsignRate = unsignRate;
	}

	public String getLsttotCul() {
		return lsttotCul;
	}

	public void setLsttotCul(String lsttotCul) {
		this.lsttotCul = lsttotCul;
	}

	public String getLatlyChgRateDate() {
		return latlyChgRateDate;
	}

	public void setLatlyChgRateDate(String latlyChgRateDate) {
		this.latlyChgRateDate = latlyChgRateDate;
	}

	public String getLowNum() {
		return lowNum;
	}

	public void setLowNum(String lowNum) {
		this.lowNum = lowNum;
	}

	public String getDepositTerm() {
		return depositTerm;
	}

	public void setDepositTerm(String depositTerm) {
		this.depositTerm = depositTerm;
	}

	public String getTrsferInmode() {
		return trsferInmode;
	}

	public void setTrsferInmode(String trsferInmode) {
		this.trsferInmode = trsferInmode;
	}

	public String getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(String transferTime) {
		this.transferTime = transferTime;
	}

	public String getReseOnAmt() {
		return reseOnAmt;
	}

	public void setReseOnAmt(String reseOnAmt) {
		this.reseOnAmt = reseOnAmt;
	}

	public String getInCustAcct() {
		return inCustAcct;
	}

	public void setInCustAcct(String inCustAcct) {
		this.inCustAcct = inCustAcct;
	}

	public String getPaymentInterval() {
		return paymentInterval;
	}

	public void setPaymentInterval(String paymentInterval) {
		this.paymentInterval = paymentInterval;
	}

	public String getOutAcctPro() {
		return outAcctPro;
	}

	public void setOutAcctPro(String outAcctPro) {
		this.outAcctPro = outAcctPro;
	}

	public String getCredLimt() {
		return credLimt;
	}

	public void setCredLimt(String credLimt) {
		this.credLimt = credLimt;
	}

	public String getChappenFlag() {
		return chappenFlag;
	}

	public void setChappenFlag(String chappenFlag) {
		this.chappenFlag = chappenFlag;
	}

	public String getCreditRelationFlag() {
		return creditRelationFlag;
	}

	public void setCreditRelationFlag(String creditRelationFlag) {
		this.creditRelationFlag = creditRelationFlag;
	}

	public String getCrAcct() {
		return crAcct;
	}

	public void setCrAcct(String crAcct) {
		this.crAcct = crAcct;
	}

	public String getCanBuyNoteNum() {
		return canBuyNoteNum;
	}

	public void setCanBuyNoteNum(String canBuyNoteNum) {
		this.canBuyNoteNum = canBuyNoteNum;
	}

	public String getAllowNouseRate() {
		return allowNouseRate;
	}

	public void setAllowNouseRate(String allowNouseRate) {
		this.allowNouseRate = allowNouseRate;
	}

	public String getIstogetherFlag() {
		return istogetherFlag;
	}

	public void setIstogetherFlag(String istogetherFlag) {
		this.istogetherFlag = istogetherFlag;
	}

	public String getBackupAmt() {
		return backupAmt;
	}

	public void setBackupAmt(String backupAmt) {
		this.backupAmt = backupAmt;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getYnFlag() {
		return ynFlag;
	}

	public void setYnFlag(String ynFlag) {
		this.ynFlag = ynFlag;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getShopContactPhone() {
		return shopContactPhone;
	}

	public void setShopContactPhone(String shopContactPhone) {
		this.shopContactPhone = shopContactPhone;
	}

	public String getAcctOpenSignNo() {
		return acctOpenSignNo;
	}

	public void setAcctOpenSignNo(String acctOpenSignNo) {
		this.acctOpenSignNo = acctOpenSignNo;
	}

	public String getBusinessWorkDate() {
		return businessWorkDate;
	}

	public void setBusinessWorkDate(String businessWorkDate) {
		this.businessWorkDate = businessWorkDate;
	}

	public String getChkProjectNo() {
		return chkProjectNo;
	}

	public void setChkProjectNo(String chkProjectNo) {
		this.chkProjectNo = chkProjectNo;
	}

	public String getAcct() {
		return acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}

	public String getCustAcctNo1() {
		return custAcctNo1;
	}

	public void setCustAcctNo1(String custAcctNo1) {
		this.custAcctNo1 = custAcctNo1;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public String getAcctStat1() {
		return acctStat1;
	}

	public void setAcctStat1(String acctStat1) {
		this.acctStat1 = acctStat1;
	}

	public String getYnRecon() {
		return ynRecon;
	}

	public void setYnRecon(String ynRecon) {
		this.ynRecon = ynRecon;
	}

	public String getBalanceRange() {
		return balanceRange;
	}

	public void setBalanceRange(String balanceRange) {
		this.balanceRange = balanceRange;
	}

	public String getLatlyBalanceDate() {
		return latlyBalanceDate;
	}

	public void setLatlyBalanceDate(String latlyBalanceDate) {
		this.latlyBalanceDate = latlyBalanceDate;
	}

	public String getAcctChkFlag() {
		return acctChkFlag;
	}

	public void setAcctChkFlag(String acctChkFlag) {
		this.acctChkFlag = acctChkFlag;
	}

	public String getLatlyAcctChkDate() {
		return latlyAcctChkDate;
	}

	public void setLatlyAcctChkDate(String latlyAcctChkDate) {
		this.latlyAcctChkDate = latlyAcctChkDate;
	}

	public String getExChkFlg() {
		return exChkFlg;
	}

	public void setExChkFlg(String exChkFlg) {
		this.exChkFlg = exChkFlg;
	}

	public String getRestandTotSyn() {
		return restandTotSyn;
	}

	public void setRestandTotSyn(String restandTotSyn) {
		this.restandTotSyn = restandTotSyn;
	}

	public String getGroupProductNo() {
		return groupProductNo;
	}

	public void setGroupProductNo(String groupProductNo) {
		this.groupProductNo = groupProductNo;
	}

	public String getGroupAcctNo() {
		return groupAcctNo;
	}

	public void setGroupAcctNo(String groupAcctNo) {
		this.groupAcctNo = groupAcctNo;
	}

	public String getGetCashFlag() {
		return getCashFlag;
	}

	public void setGetCashFlag(String getCashFlag) {
		this.getCashFlag = getCashFlag;
	}

	public String getMaturityWay() {
		return maturityWay;
	}

	public void setMaturityWay(String maturityWay) {
		this.maturityWay = maturityWay;
	}

	public String getNormalAcctiscostFlag() {
		return normalAcctiscostFlag;
	}

	public void setNormalAcctiscostFlag(String normalAcctiscostFlag) {
		this.normalAcctiscostFlag = normalAcctiscostFlag;
	}

	public String getUnmoveAcctIsCostFlag() {
		return unmoveAcctIsCostFlag;
	}

	public void setUnmoveAcctIsCostFlag(String unmoveAcctIsCostFlag) {
		this.unmoveAcctIsCostFlag = unmoveAcctIsCostFlag;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getDateOfValue() {
		return dateOfValue;
	}

	public void setDateOfValue(String dateOfValue) {
		this.dateOfValue = dateOfValue;
	}

	public String getMatuDat() {
		return matuDat;
	}

	public void setMatuDat(String matuDat) {
		this.matuDat = matuDat;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getFreezeBal() {
		return freezeBal;
	}

	public void setFreezeBal(String freezeBal) {
		this.freezeBal = freezeBal;
	}

	public BigInteger getRecNum() {
		return recNum;
	}

	public void setRecNum(BigInteger recNum) {
		this.recNum = recNum;
	}

	public List<F11231> getF11231() {
		return f11231;
	}

	public void setF11231(List<F11231> f11231) {
		this.f11231 = f11231;
	}

	public String getBelongObject() {
		return belongObject;
	}

	public void setBelongObject(String belongObject) {
		this.belongObject = belongObject;
	}

	public String getLibProType() {
		return libProType;
	}

	public void setLibProType(String libProType) {
		this.libProType = libProType;
	}

	public String getOpenBrch() {
		return openBrch;
	}

	public void setOpenBrch(String openBrch) {
		this.openBrch = openBrch;
	}

	public String getTransOpponentAcct() {
		return transOpponentAcct;
	}

	public void setTransOpponentAcct(String transOpponentAcct) {
		this.transOpponentAcct = transOpponentAcct;
	}

	public String getRecvAcctName() {
		return recvAcctName;
	}

	public void setRecvAcctName(String recvAcctName) {
		this.recvAcctName = recvAcctName;
	}

	public String getBrchNo() {
		return brchNo;
	}

	public void setBrchNo(String brchNo) {
		this.brchNo = brchNo;
	}

	public String getOtherBankName() {
		return otherBankName;
	}

	public void setOtherBankName(String otherBankName) {
		this.otherBankName = otherBankName;
	}

	public String getPeopleBankNo() {
		return peopleBankNo;
	}

	public void setPeopleBankNo(String peopleBankNo) {
		this.peopleBankNo = peopleBankNo;
	}

	public String getAcctNoCode() {
		return acctNoCode;
	}

	public void setAcctNoCode(String acctNoCode) {
		this.acctNoCode = acctNoCode;
	}

	public String getBackup2() {
		return backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getFixedPayAmt() {
		return fixedPayAmt;
	}

	public void setFixedPayAmt(String fixedPayAmt) {
		this.fixedPayAmt = fixedPayAmt;
	}

	public String getOpenAcctName() {
		return openAcctName;
	}

	public void setOpenAcctName(String openAcctName) {
		this.openAcctName = openAcctName;
	}

	public String getAcctShortCode3() {
		return acctShortCode3;
	}

	public void setAcctShortCode3(String acctShortCode3) {
		this.acctShortCode3 = acctShortCode3;
	}

	public String getPrdDesc() {
		return prdDesc;
	}

	public void setPrdDesc(String prdDesc) {
		this.prdDesc = prdDesc;
	}

	public String getEveryTimeGetAmt() {
		return everyTimeGetAmt;
	}

	public void setEveryTimeGetAmt(String everyTimeGetAmt) {
		this.everyTimeGetAmt = everyTimeGetAmt;
	}

	public String getIsLinkCycleLoan() {
		return isLinkCycleLoan;
	}

	public void setIsLinkCycleLoan(String isLinkCycleLoan) {
		this.isLinkCycleLoan = isLinkCycleLoan;
	}

	public String getLoanNoteNo() {
		return loanNoteNo;
	}

	public void setLoanNoteNo(String loanNoteNo) {
		this.loanNoteNo = loanNoteNo;
	}

	public String getGrantedAmt() {
		return grantedAmt;
	}

	public void setGrantedAmt(String grantedAmt) {
		this.grantedAmt = grantedAmt;
	}

	public String getFrzGrantableAmt() {
		return frzGrantableAmt;
	}

	public void setFrzGrantableAmt(String frzGrantableAmt) {
		this.frzGrantableAmt = frzGrantableAmt;
	}

	public String getGrantableAmt() {
		return grantableAmt;
	}

	public void setGrantableAmt(String grantableAmt) {
		this.grantableAmt = grantableAmt;
	}

	public String getConnectCostBatFlag() {
		return connectCostBatFlag;
	}

	public void setConnectCostBatFlag(String connectCostBatFlag) {
		this.connectCostBatFlag = connectCostBatFlag;
	}

	public String getBatLatterRate() {
		return batLatterRate;
	}

	public void setBatLatterRate(String batLatterRate) {
		this.batLatterRate = batLatterRate;
	}

	public String getYnExchSup() {
		return ynExchSup;
	}

	public void setYnExchSup(String ynExchSup) {
		this.ynExchSup = ynExchSup;
	}

	public String getFixedDepositAmt() {
		return fixedDepositAmt;
	}

	public void setFixedDepositAmt(String fixedDepositAmt) {
		this.fixedDepositAmt = fixedDepositAmt;
	}

	public String getFlgDefault() {
		return flgDefault;
	}

	public void setFlgDefault(String flgDefault) {
		this.flgDefault = flgDefault;
	}

	public String getAgnt() {
		return agnt;
	}

	public void setAgnt(String agnt) {
		this.agnt = agnt;
	}

	public String getCertKind() {
		return certKind;
	}

	public void setCertKind(String certKind) {
		this.certKind = certKind;
	}

	public String getAgntCertNum() {
		return agntCertNum;
	}

	public void setAgntCertNum(String agntCertNum) {
		this.agntCertNum = agntCertNum;
	}

	public String getAgentNation() {
		return agentNation;
	}

	public void setAgentNation(String agentNation) {
		this.agentNation = agentNation;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Override
	public String toString() {
		return "OXD052ResBody [custNo=" + custNo + ", custAcctNo=" + custAcctNo
				+ ", custName=" + custName + ", sysAcct=" + sysAcct
				+ ", brcnName=" + brcnName + ", balanceBrchName="
				+ balanceBrchName + ", subAcctSeri=" + subAcctSeri
				+ ", contractNo=" + contractNo + ", currCode=" + currCode
				+ ", cashFlag=" + cashFlag + ", prdNo=" + prdNo + ", reletype="
				+ reletype + ", acctType=" + acctType + ", cateAtrb="
				+ cateAtrb + ", vchBatNo=" + vchBatNo + ", vchKind=" + vchKind
				+ ", vchSerialNo=" + vchSerialNo + ", payCondiditon="
				+ payCondiditon + ", isAllowExChg=" + isAllowExChg
				+ ", uswFlag=" + uswFlag + ", uswAre=" + uswAre
				+ ", cashExchgFlag=" + cashExchgFlag + ", transExchFlag="
				+ transExchFlag + ", openAcctDate=" + openAcctDate
				+ ", canAccDate=" + canAccDate + ", acctStat=" + acctStat
				+ ", intrRat=" + intrRat + ", rangeValue=" + rangeValue
				+ ", rateFloatRatio=" + rateFloatRatio + ", accrrestAmt="
				+ accrrestAmt + ", availableAmt=" + availableAmt
				+ ", pendSettlementAmt=" + pendSettlementAmt
				+ ", acctPendSettlemenAmt=" + acctPendSettlemenAmt
				+ ", latlyBusiDate=" + latlyBusiDate + ", lostFlag=" + lostFlag
				+ ", singleVchStat=" + singleVchStat + ", ynFrozen=" + ynFrozen
				+ ", frzAmt=" + frzAmt + ", ctrlFlg=" + ctrlFlg + ", ctrlAmt="
				+ ctrlAmt + ", interestTotAmt=" + interestTotAmt + ", lstCul="
				+ lstCul + ", thisYearTotCul=" + thisYearTotCul
				+ ", unsignRate=" + unsignRate + ", lsttotCul=" + lsttotCul
				+ ", latlyChgRateDate=" + latlyChgRateDate + ", lowNum="
				+ lowNum + ", depositTerm=" + depositTerm + ", trsferInmode="
				+ trsferInmode + ", transferTime=" + transferTime
				+ ", reseOnAmt=" + reseOnAmt + ", inCustAcct=" + inCustAcct
				+ ", paymentInterval=" + paymentInterval + ", outAcctPro="
				+ outAcctPro + ", credLimt=" + credLimt + ", chappenFlag="
				+ chappenFlag + ", creditRelationFlag=" + creditRelationFlag
				+ ", crAcct=" + crAcct + ", canBuyNoteNum=" + canBuyNoteNum
				+ ", allowNouseRate=" + allowNouseRate + ", istogetherFlag="
				+ istogetherFlag + ", backupAmt=" + backupAmt + ", workTime="
				+ workTime + ", ynFlag=" + ynFlag + ", addr=" + addr
				+ ", shopContactPhone=" + shopContactPhone
				+ ", acctOpenSignNo=" + acctOpenSignNo + ", businessWorkDate="
				+ businessWorkDate + ", chkProjectNo=" + chkProjectNo
				+ ", acct=" + acct + ", custAcctNo1=" + custAcctNo1
				+ ", depositType=" + depositType + ", acctStat1=" + acctStat1
				+ ", ynRecon=" + ynRecon + ", balanceRange=" + balanceRange
				+ ", latlyBalanceDate=" + latlyBalanceDate + ", acctChkFlag="
				+ acctChkFlag + ", latlyAcctChkDate=" + latlyAcctChkDate
				+ ", exChkFlg=" + exChkFlg + ", restandTotSyn=" + restandTotSyn
				+ ", groupProductNo=" + groupProductNo + ", groupAcctNo="
				+ groupAcctNo + ", getCashFlag=" + getCashFlag
				+ ", maturityWay=" + maturityWay + ", normalAcctiscostFlag="
				+ normalAcctiscostFlag + ", unmoveAcctIsCostFlag="
				+ unmoveAcctIsCostFlag + ", isValid=" + isValid
				+ ", dateOfValue=" + dateOfValue + ", matuDat=" + matuDat
				+ ", amt=" + amt + ", freezeBal=" + freezeBal + ", recNum="
				+ recNum + ", f11231=" + f11231 + ", belongObject="
				+ belongObject + ", libProType=" + libProType + ", openBrch="
				+ openBrch + ", transOpponentAcct=" + transOpponentAcct
				+ ", recvAcctName=" + recvAcctName + ", brchNo=" + brchNo
				+ ", otherBankName=" + otherBankName + ", peopleBankNo="
				+ peopleBankNo + ", acctNoCode=" + acctNoCode + ", backup2="
				+ backup2 + ", backup1=" + backup1 + ", fixedPayAmt="
				+ fixedPayAmt + ", openAcctName=" + openAcctName
				+ ", acctShortCode3=" + acctShortCode3 + ", prdDesc=" + prdDesc
				+ ", everyTimeGetAmt=" + everyTimeGetAmt + ", isLinkCycleLoan="
				+ isLinkCycleLoan + ", loanNoteNo=" + loanNoteNo
				+ ", grantedAmt=" + grantedAmt + ", frzGrantableAmt="
				+ frzGrantableAmt + ", grantableAmt=" + grantableAmt
				+ ", connectCostBatFlag=" + connectCostBatFlag
				+ ", batLatterRate=" + batLatterRate + ", ynExchSup="
				+ ynExchSup + ", fixedDepositAmt=" + fixedDepositAmt
				+ ", flgDefault=" + flgDefault + ", agnt=" + agnt
				+ ", certKind=" + certKind + ", agntCertNum=" + agntCertNum
				+ ", agentNation=" + agentNation + ", contactPhone="
				+ contactPhone + "]";
	}

}