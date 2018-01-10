package com.primeton.plus;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 计提减值结果
 * 
 * @author MJF
 * 
 */
public class PrintT1306List implements Serializable {
	private static final long serialVersionUID = 1477519837518565200L;
	private String dueNum;// 借据编号
	private String rcvDate;// 计提日期
	private String opnDep;// 机构
	private String brwName;// 借款人名称
	private BigDecimal orgPrvIpr;// 原减值准备金额(元)
	private BigDecimal iprBase;// 减值基数(元)
	private BigDecimal prop;// 减值比例(%)
	private BigDecimal nowPrvIpr;// 提取减值金额(元)
	private String currCod;// 币种
	private String busCod;// 业务别
	private String orgClsFlg;// 上月五级分类结果
	private String nowClsFlg;// 本月五级分类结果

	public PrintT1306List() {

	}

	public String getDueNum() {
		return dueNum;
	}

	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}

	public String getRcvDate() {
		return rcvDate;
	}

	public void setRcvDate(String rcvDate) {
		this.rcvDate = rcvDate;
	}

	public String getOpnDep() {
		return opnDep;
	}

	public void setOpnDep(String opnDep) {
		this.opnDep = opnDep;
	}

	public String getBrwName() {
		return brwName;
	}

	public void setBrwName(String brwName) {
		this.brwName = brwName;
	}

	public BigDecimal getOrgPrvIpr() {
		return orgPrvIpr;
	}

	public void setOrgPrvIpr(BigDecimal orgPrvIpr) {
		this.orgPrvIpr = orgPrvIpr;
	}

	public BigDecimal getIprBase() {
		return iprBase;
	}

	public void setIprBase(BigDecimal iprBase) {
		this.iprBase = iprBase;
	}

	public BigDecimal getProp() {
		return prop;
	}

	public void setProp(BigDecimal prop) {
		this.prop = prop;
	}

	public BigDecimal getNowPrvIpr() {
		return nowPrvIpr;
	}

	public void setNowPrvIpr(BigDecimal nowPrvIpr) {
		this.nowPrvIpr = nowPrvIpr;
	}

	public String getCurrCod() {
		return currCod;
	}

	public void setCurrCod(String currCod) {
		this.currCod = currCod;
	}

	public String getBusCod() {
		return busCod;
	}

	public void setBusCod(String busCod) {
		this.busCod = busCod;
	}

	public String getOrgClsFlg() {
		return orgClsFlg;
	}

	public void setOrgClsFlg(String orgClsFlg) {
		this.orgClsFlg = orgClsFlg;
	}

	public String getNowClsFlg() {
		return nowClsFlg;
	}

	public void setNowClsFlg(String nowClsFlg) {
		this.nowClsFlg = nowClsFlg;
	}

}
