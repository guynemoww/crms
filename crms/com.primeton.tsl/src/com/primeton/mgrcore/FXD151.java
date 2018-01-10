package com.primeton.mgrcore;

import java.io.Serializable;

public class FXD151 implements Serializable{

	private static final long serialVersionUID = -2407762414997009238L;
    private String  acctBusiKind;//账户业务种类
	private String  acctNo;//账号
	private String  acctChnName;//账户中文名
	private String  businessBrch;//营业机构
	private String  supBrch;//上级机构
	private String  prdNo;//产品编号
	private String  busiNo;//业务代号
	private String  busiClass;//业务分类
	private String  bothOpenAcctFlg;//是否对开账户
	private String  overDraftFlg;//是否允许透支
	private String  oppoAcctNo;//对方账号
	private String  openBrch;//开户机构
	private String  smIdyCd;//同业机构代号
	private String  countyBorderFlg;//境内外标志
	private String  bankBicNo;//银行BIC号码
	private String  toChkToClgSupsBal;//待对账待清算暂挂余额
	private String  isCount;//计提与否
	private String  accrualCycle;//计提周期
	private String  isInterest;//计息与否
	private String  countBalanceCycle;//计息周期
	private String  currCode;//货币代号
	private String  custNo;//客户号
	private String  depstTerm;//存期
	private String  startIntDate;//起息日期
	private String  expireDate;//到期日期
	private String  balance;//账户余额
	private String  everyDayAcmInt;//每日累计利息
	private String  acmRetnInt;//累计归还利息
	private String  rateNo;//利率编号
	private String  yearOrMonthRate;//年/月利率
	private String  rate;//利率
	private String  overDraftIntRate;//透支利率
	private String  expIntRateNo;//到期利率编号
	private String  expYrMoIntRate;//到期年月利率
	private String  expIntRate;//到期利率
	private String  structDepositFLg;//是否结构性存款
	private String  collIntWarnDays;//收息预警天数
	private String  collIntPeriod;//收息周期
	private String  collIntAcct;//收息账号
	private String  hadRcvIntTotAmt;//已收利息总额
	private String  reservesRate;//准备金比率
	private String  reservesRateNo;//准备金利率编号
	private String  reservesYMRate;//准备金年月利率
	private String  reservesRate1;//准备金利率
	private String  prvnAcrInt;//备付金应计利息
	private String  reservesAcrRate;//准备金应计利息
	private String  trsfInFncPeriod;//转入财政周期
	private String  fncDepRatePct;//财政存款比率
	private String  openAcctDate;//开户日期
	private String  openAcctTeller;//开户柜员
	private String  balaNature;//余额性质
	private String  acctSeri;//账号序号
	private String  interAccrualWay;//计息天数方式
	private String  fundAcctStat;//资金账户状态
	private String  lastDayActBal;//上日账户余额
	private String  canActTeller;//销户柜员
	private String  canActDate;//销户日期
	private String  balaDirection;//余额方向
	private String  bgnofprdBalDir;//期初余额方向
	
	public FXD151() {
	}
	public String getAcctBusiKind() {
		return acctBusiKind;
	}
	public void setAcctBusiKind(String acctBusiKind) {
		this.acctBusiKind = acctBusiKind;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getAcctChnName() {
		return acctChnName;
	}
	public void setAcctChnName(String acctChnName) {
		this.acctChnName = acctChnName;
	}
	public String getBusinessBrch() {
		return businessBrch;
	}
	public void setBusinessBrch(String businessBrch) {
		this.businessBrch = businessBrch;
	}
	public String getSupBrch() {
		return supBrch;
	}
	public void setSupBrch(String supBrch) {
		this.supBrch = supBrch;
	}
	public String getPrdNo() {
		return prdNo;
	}
	public void setPrdNo(String prdNo) {
		this.prdNo = prdNo;
	}
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getBusiClass() {
		return busiClass;
	}
	public void setBusiClass(String busiClass) {
		this.busiClass = busiClass;
	}
	public String getBothOpenAcctFlg() {
		return bothOpenAcctFlg;
	}
	public void setBothOpenAcctFlg(String bothOpenAcctFlg) {
		this.bothOpenAcctFlg = bothOpenAcctFlg;
	}
	public String getOverDraftFlg() {
		return overDraftFlg;
	}
	public void setOverDraftFlg(String overDraftFlg) {
		this.overDraftFlg = overDraftFlg;
	}
	public String getOppoAcctNo() {
		return oppoAcctNo;
	}
	public void setOppoAcctNo(String oppoAcctNo) {
		this.oppoAcctNo = oppoAcctNo;
	}
	public String getOpenBrch() {
		return openBrch;
	}
	public void setOpenBrch(String openBrch) {
		this.openBrch = openBrch;
	}
	public String getSmIdyCd() {
		return smIdyCd;
	}
	public void setSmIdyCd(String smIdyCd) {
		this.smIdyCd = smIdyCd;
	}
	public String getCountyBorderFlg() {
		return countyBorderFlg;
	}
	public void setCountyBorderFlg(String countyBorderFlg) {
		this.countyBorderFlg = countyBorderFlg;
	}
	public String getBankBicNo() {
		return bankBicNo;
	}
	public void setBankBicNo(String bankBicNo) {
		this.bankBicNo = bankBicNo;
	}
	public String getToChkToClgSupsBal() {
		return toChkToClgSupsBal;
	}
	public void setToChkToClgSupsBal(String toChkToClgSupsBal) {
		this.toChkToClgSupsBal = toChkToClgSupsBal;
	}
	public String getIsCount() {
		return isCount;
	}
	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}
	public String getAccrualCycle() {
		return accrualCycle;
	}
	public void setAccrualCycle(String accrualCycle) {
		this.accrualCycle = accrualCycle;
	}
	public String getIsInterest() {
		return isInterest;
	}
	public void setIsInterest(String isInterest) {
		this.isInterest = isInterest;
	}
	public String getCountBalanceCycle() {
		return countBalanceCycle;
	}
	public void setCountBalanceCycle(String countBalanceCycle) {
		this.countBalanceCycle = countBalanceCycle;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getDepstTerm() {
		return depstTerm;
	}
	public void setDepstTerm(String depstTerm) {
		this.depstTerm = depstTerm;
	}
	public String getStartIntDate() {
		return startIntDate;
	}
	public void setStartIntDate(String startIntDate) {
		this.startIntDate = startIntDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getEveryDayAcmInt() {
		return everyDayAcmInt;
	}
	public void setEveryDayAcmInt(String everyDayAcmInt) {
		this.everyDayAcmInt = everyDayAcmInt;
	}
	public String getAcmRetnInt() {
		return acmRetnInt;
	}
	public void setAcmRetnInt(String acmRetnInt) {
		this.acmRetnInt = acmRetnInt;
	}
	public String getRateNo() {
		return rateNo;
	}
	public void setRateNo(String rateNo) {
		this.rateNo = rateNo;
	}
	public String getYearOrMonthRate() {
		return yearOrMonthRate;
	}
	public void setYearOrMonthRate(String yearOrMonthRate) {
		this.yearOrMonthRate = yearOrMonthRate;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getOverDraftIntRate() {
		return overDraftIntRate;
	}
	public void setOverDraftIntRate(String overDraftIntRate) {
		this.overDraftIntRate = overDraftIntRate;
	}
	public String getExpIntRateNo() {
		return expIntRateNo;
	}
	public void setExpIntRateNo(String expIntRateNo) {
		this.expIntRateNo = expIntRateNo;
	}
	public String getExpYrMoIntRate() {
		return expYrMoIntRate;
	}
	public void setExpYrMoIntRate(String expYrMoIntRate) {
		this.expYrMoIntRate = expYrMoIntRate;
	}
	public String getExpIntRate() {
		return expIntRate;
	}
	public void setExpIntRate(String expIntRate) {
		this.expIntRate = expIntRate;
	}
	public String getStructDepositFLg() {
		return structDepositFLg;
	}
	public void setStructDepositFLg(String structDepositFLg) {
		this.structDepositFLg = structDepositFLg;
	}
	public String getCollIntWarnDays() {
		return collIntWarnDays;
	}
	public void setCollIntWarnDays(String collIntWarnDays) {
		this.collIntWarnDays = collIntWarnDays;
	}
	public String getCollIntPeriod() {
		return collIntPeriod;
	}
	public void setCollIntPeriod(String collIntPeriod) {
		this.collIntPeriod = collIntPeriod;
	}
	public String getCollIntAcct() {
		return collIntAcct;
	}
	public void setCollIntAcct(String collIntAcct) {
		this.collIntAcct = collIntAcct;
	}
	public String getHadRcvIntTotAmt() {
		return hadRcvIntTotAmt;
	}
	public void setHadRcvIntTotAmt(String hadRcvIntTotAmt) {
		this.hadRcvIntTotAmt = hadRcvIntTotAmt;
	}
	public String getReservesRate() {
		return reservesRate;
	}
	public void setReservesRate(String reservesRate) {
		this.reservesRate = reservesRate;
	}
	public String getReservesRateNo() {
		return reservesRateNo;
	}
	public void setReservesRateNo(String reservesRateNo) {
		this.reservesRateNo = reservesRateNo;
	}
	public String getReservesYMRate() {
		return reservesYMRate;
	}
	public void setReservesYMRate(String reservesYMRate) {
		this.reservesYMRate = reservesYMRate;
	}
	public String getReservesRate1() {
		return reservesRate1;
	}
	public void setReservesRate1(String reservesRate1) {
		this.reservesRate1 = reservesRate1;
	}
	public String getPrvnAcrInt() {
		return prvnAcrInt;
	}
	public void setPrvnAcrInt(String prvnAcrInt) {
		this.prvnAcrInt = prvnAcrInt;
	}
	public String getReservesAcrRate() {
		return reservesAcrRate;
	}
	public void setReservesAcrRate(String reservesAcrRate) {
		this.reservesAcrRate = reservesAcrRate;
	}
	public String getTrsfInFncPeriod() {
		return trsfInFncPeriod;
	}
	public void setTrsfInFncPeriod(String trsfInFncPeriod) {
		this.trsfInFncPeriod = trsfInFncPeriod;
	}
	public String getFncDepRatePct() {
		return fncDepRatePct;
	}
	public void setFncDepRatePct(String fncDepRatePct) {
		this.fncDepRatePct = fncDepRatePct;
	}
	public String getOpenAcctDate() {
		return openAcctDate;
	}
	public void setOpenAcctDate(String openAcctDate) {
		this.openAcctDate = openAcctDate;
	}
	public String getOpenAcctTeller() {
		return openAcctTeller;
	}
	public void setOpenAcctTeller(String openAcctTeller) {
		this.openAcctTeller = openAcctTeller;
	}
	public String getBalaNature() {
		return balaNature;
	}
	public void setBalaNature(String balaNature) {
		this.balaNature = balaNature;
	}
	public String getAcctSeri() {
		return acctSeri;
	}
	public void setAcctSeri(String acctSeri) {
		this.acctSeri = acctSeri;
	}
	public String getInterAccrualWay() {
		return interAccrualWay;
	}
	public void setInterAccrualWay(String interAccrualWay) {
		this.interAccrualWay = interAccrualWay;
	}
	public String getFundAcctStat() {
		return fundAcctStat;
	}
	public void setFundAcctStat(String fundAcctStat) {
		this.fundAcctStat = fundAcctStat;
	}
	public String getLastDayActBal() {
		return lastDayActBal;
	}
	public void setLastDayActBal(String lastDayActBal) {
		this.lastDayActBal = lastDayActBal;
	}
	public String getCanActTeller() {
		return canActTeller;
	}
	public void setCanActTeller(String canActTeller) {
		this.canActTeller = canActTeller;
	}
	public String getCanActDate() {
		return canActDate;
	}
	public void setCanActDate(String canActDate) {
		this.canActDate = canActDate;
	}
	public String getBalaDirection() {
		return balaDirection;
	}
	public void setBalaDirection(String balaDirection) {
		this.balaDirection = balaDirection;
	}
	public String getBgnofprdBalDir() {
		return bgnofprdBalDir;
	}
	public void setBgnofprdBalDir(String bgnofprdBalDir) {
		this.bgnofprdBalDir = bgnofprdBalDir;
	}
}
