package com.primeton.p2p;

import java.io.Serializable;

/**
 * 
* @ClassName:征信接口返回值
* @Description: 
* @author GIT-ABC
* @date 2015-6-4 上午09:13:32 
*
 */
public class P2pEntry{
	private String busiNo;//业务编号
	private String imageNo;//影像编号
	private String curOverNodk;//贷款当前逾期期数
	private String curreOveAmountdk;//贷款当前逾期金额
	private String higtNoOfOvePerdsdk;//贷款历史最高连续逾期期数
	private String cumuOverNoOfHstrydk;//贷款历史累计逾期次数
	private String curOverNodjk;//贷记卡当前逾期期数
	private String curreOveAmountdjk;//贷记卡当前逾期金额
	private String higtNoOfOvePerdsdjk;//贷记卡历史最高连续逾期期数
	private String cumuOverNoOfHstrydjk;//贷记卡历史累计逾期次数
	private String curOverNozdjk;//准贷记卡当前透支月数
	private String curreOveAmountzdjk;//准贷记卡当前透支金额
	private String higtNoOfOvePerdszdjk;//准贷记卡历史最长透支月数
	private String cumuOverNoOfHstryzdjk;//准贷记卡历史透支月份数
	private String orgNumdk;//最近一月内贷款审批查询机构数
	private String orgNumxyk;//最近一月内信用卡审批查询机构数
	private String queNumdk;//最近一月内贷款审批查询次数
	private String queNumxyk;//最近一月内信用卡审批查询次数
	private String fiveLevClass;//五级分类
	
	
	private String dkspcs3;//最近3个月查询原因为贷款审批的次数
	private String xykspcs3;//最近3个月查询原因为信用卡审批的次数
	private String jydks;//经营性贷款的笔数
	private String pjyhk6;//最近6个月平均应还款金额
	private String ifdwdbye;//是否有对外担保余额
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getImageNo() {
		return imageNo;
	}
	public void setImageNo(String imageNo) {
		this.imageNo = imageNo;
	}
	public String getCurOverNodk() {
		return curOverNodk;
	}
	public void setCurOverNodk(String curOverNodk) {
		this.curOverNodk = curOverNodk;
	}
	public String getCurreOveAmountdk() {
		return curreOveAmountdk;
	}
	public void setCurreOveAmountdk(String curreOveAmountdk) {
		this.curreOveAmountdk = curreOveAmountdk;
	}
	public String getHigtNoOfOvePerdsdk() {
		return higtNoOfOvePerdsdk;
	}
	public void setHigtNoOfOvePerdsdk(String higtNoOfOvePerdsdk) {
		this.higtNoOfOvePerdsdk = higtNoOfOvePerdsdk;
	}
	public String getCumuOverNoOfHstrydk() {
		return cumuOverNoOfHstrydk;
	}
	public void setCumuOverNoOfHstrydk(String cumuOverNoOfHstrydk) {
		this.cumuOverNoOfHstrydk = cumuOverNoOfHstrydk;
	}
	public String getCurOverNodjk() {
		return curOverNodjk;
	}
	public void setCurOverNodjk(String curOverNodjk) {
		this.curOverNodjk = curOverNodjk;
	}
	public String getCurreOveAmountdjk() {
		return curreOveAmountdjk;
	}
	public void setCurreOveAmountdjk(String curreOveAmountdjk) {
		this.curreOveAmountdjk = curreOveAmountdjk;
	}
	public String getHigtNoOfOvePerdsdjk() {
		return higtNoOfOvePerdsdjk;
	}
	public void setHigtNoOfOvePerdsdjk(String higtNoOfOvePerdsdjk) {
		this.higtNoOfOvePerdsdjk = higtNoOfOvePerdsdjk;
	}
	public String getCumuOverNoOfHstrydjk() {
		return cumuOverNoOfHstrydjk;
	}
	public void setCumuOverNoOfHstrydjk(String cumuOverNoOfHstrydjk) {
		this.cumuOverNoOfHstrydjk = cumuOverNoOfHstrydjk;
	}
	public String getCurOverNozdjk() {
		return curOverNozdjk;
	}
	public void setCurOverNozdjk(String curOverNozdjk) {
		this.curOverNozdjk = curOverNozdjk;
	}
	public String getCurreOveAmountzdjk() {
		return curreOveAmountzdjk;
	}
	public void setCurreOveAmountzdjk(String curreOveAmountzdjk) {
		this.curreOveAmountzdjk = curreOveAmountzdjk;
	}
	public String getHigtNoOfOvePerdszdjk() {
		return higtNoOfOvePerdszdjk;
	}
	public void setHigtNoOfOvePerdszdjk(String higtNoOfOvePerdszdjk) {
		this.higtNoOfOvePerdszdjk = higtNoOfOvePerdszdjk;
	}
	public String getCumuOverNoOfHstryzdjk() {
		return cumuOverNoOfHstryzdjk;
	}
	public void setCumuOverNoOfHstryzdjk(String cumuOverNoOfHstryzdjk) {
		this.cumuOverNoOfHstryzdjk = cumuOverNoOfHstryzdjk;
	}
	public String getOrgNumdk() {
		return orgNumdk;
	}
	public void setOrgNumdk(String orgNumdk) {
		this.orgNumdk = orgNumdk;
	}
	public String getOrgNumxyk() {
		return orgNumxyk;
	}
	public void setOrgNumxyk(String orgNumxyk) {
		this.orgNumxyk = orgNumxyk;
	}
	public String getQueNumdk() {
		return queNumdk;
	}
	public void setQueNumdk(String queNumdk) {
		this.queNumdk = queNumdk;
	}
	public String getQueNumxyk() {
		return queNumxyk;
	}
	public void setQueNumxyk(String queNumxyk) {
		this.queNumxyk = queNumxyk;
	}
	public String getFiveLevClass() {
		return fiveLevClass;
	}
	public void setFiveLevClass(String fiveLevClass) {
		this.fiveLevClass = fiveLevClass;
	}
	public String getDkspcs3() {
		return dkspcs3;
	}
	public void setDkspcs3(String dkspcs3) {
		this.dkspcs3 = dkspcs3;
	}
	public String getXykspcs3() {
		return xykspcs3;
	}
	public void setXykspcs3(String xykspcs3) {
		this.xykspcs3 = xykspcs3;
	}
	public String getJydks() {
		return jydks;
	}
	public void setJydks(String jydks) {
		this.jydks = jydks;
	}
	public String getPjyhk6() {
		return pjyhk6;
	}
	public void setPjyhk6(String pjyhk6) {
		this.pjyhk6 = pjyhk6;
	}
	public String getIfdwdbye() {
		return ifdwdbye;
	}
	public void setIfdwdbye(String ifdwdbye) {
		this.ifdwdbye = ifdwdbye;
	}
	
	

}
