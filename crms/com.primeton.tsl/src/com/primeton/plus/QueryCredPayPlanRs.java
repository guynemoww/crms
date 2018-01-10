package com.primeton.plus;

import java.io.Serializable;

import com.primeton.tsl.SuperBosfxRs;
/**
 * 查询贷款还款计划表输出
 * @author CHENPAN
 *
 */
public class QueryCredPayPlanRs extends SuperBosfxRs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6487673751454828918L;
	private String rltFile;     //下传文件名                 
	private String rltFileDir;  //下传文件目录               
	private Integer rltCnt;      //批量笔数                   
	private String queryresult; //json报文，不走文件的时候用 
	
	public QueryCredPayPlanRs() {
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
