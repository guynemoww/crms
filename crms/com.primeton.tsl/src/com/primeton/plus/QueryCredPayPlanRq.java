package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

import com.primeton.tsl.SuperBosfxRq;
/**
 * 查询贷款还款计划表输入
 * @author CHENPAN
 *
 */
public class QueryCredPayPlanRq extends SuperBosfxRq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3355903078428532372L;
	private String dueNum;        //借据编号              
	private BigDecimal amt;       //放款金额              
	private String begDate;       //起始日期              
	private String endDate;       //到期日期              
	private BigDecimal norItrRate;    //正常利率              
	private String caspan;        //结息周期              
	private String itrCalRule;    //下次结息日计算规则    
	private String payDate;       //指定还款日            
	private String curPrmPayTyp;  //主还款方式            
	private String curAstPayTyp;  //子还款方式            
	private Integer stgFirstMon;   //阶段性首次还本期数    
	private String itrRateWay;    //利率依据方式          
	private String ExiFlg;        //是否为已经发放贷款标志  --是否为已经发放贷款标志 0 不存在 1存在                    
	private String prmCls;        //贷款主类别 --暂时未用
	private String astCls;        //贷款子类别 --暂时未用
	private String rltFile;     //下传文件名                 
	private String rltFileDir;  //下传文件目录               
	private Integer rltCnt;      //批量笔数                   
	private String queryresult; //json报文，不走文件的时候用 
	public QueryCredPayPlanRq() {
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public BigDecimal getNorItrRate() {
		return norItrRate;
	}
	public void setNorItrRate(BigDecimal norItrRate) {
		this.norItrRate = norItrRate;
	}
	public String getCaspan() {
		return caspan;
	}
	public void setCaspan(String caspan) {
		this.caspan = caspan;
	}
	public String getItrCalRule() {
		return itrCalRule;
	}
	public void setItrCalRule(String itrCalRule) {
		this.itrCalRule = itrCalRule;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getCurPrmPayTyp() {
		return curPrmPayTyp;
	}
	public void setCurPrmPayTyp(String curPrmPayTyp) {
		this.curPrmPayTyp = curPrmPayTyp;
	}
	public String getCurAstPayTyp() {
		return curAstPayTyp;
	}
	public void setCurAstPayTyp(String curAstPayTyp) {
		this.curAstPayTyp = curAstPayTyp;
	}
	public Integer getStgFirstMon() {
		return stgFirstMon;
	}
	public void setStgFirstMon(Integer stgFirstMon) {
		this.stgFirstMon = stgFirstMon;
	}
	public String getItrRateWay() {
		return itrRateWay;
	}
	public void setItrRateWay(String itrRateWay) {
		this.itrRateWay = itrRateWay;
	}
	public String getExiFlg() {
		return ExiFlg;
	}
	public void setExiFlg(String exiFlg) {
		ExiFlg = exiFlg;
	}
	public String getPrmCls() {
		return prmCls;
	}
	public void setPrmCls(String prmCls) {
		this.prmCls = prmCls;
	}
	public String getAstCls() {
		return astCls;
	}
	public void setAstCls(String astCls) {
		this.astCls = astCls;
	}
	public String getRltFile() {
		return rltFile;
	}
	public void setRltFile(String rltFile) {
		this.rltFile = rltFile;
	}
	public String getRltFileDir() {
		return rltFileDir;
	}
	public void setRltFileDir(String rltFileDir) {
		this.rltFileDir = rltFileDir;
	}
	public Integer getRltCnt() {
		return rltCnt;
	}
	public void setRltCnt(Integer rltCnt) {
		this.rltCnt = rltCnt;
	}
	public String getQueryresult() {
		return queryresult;
	}
	public void setQueryresult(String queryresult) {
		this.queryresult = queryresult;
	}
	
	
}
