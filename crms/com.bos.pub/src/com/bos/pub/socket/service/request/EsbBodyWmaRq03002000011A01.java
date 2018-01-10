package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyWmaRq03002000011A01 
 * @Description: 03002000011票据信息维护		01银行承兑汇票打印发起	
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyWmaRq03002000011A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String branchId;		//机构代码		String(6)	Y
	private String intfNo;			//接口编号		String(10)	Y
	private String ctrNo;			//合同号			String(20)	Y
	private String ittTlrNo;		//起始柜员号		String(10)	Y	
	private String ittbrId;			//起始机构号		String(10)	N
	private String chrgOffNo;		//出账号			String(20)	Y
	private String drwrAcctNm;		//出票人户名		String(100)	Y
	private String drwrAcctNo;		//出票人账号		String(35)	Y
	private String drweBnkNm;		//付款行行名		String(100)	Y
	private String drweBnkNo;		//付款行行号		String(20)	Y
	private String drweBnkAdr;		//付款行地址		String(120)	Y
	private String sumNum;			//总笔数			String(9)	Y
	private List<EsbBodyWmaRqDbtArray> esbBodyWmaRqDbtArrays;//借据信息数组
	private List<EsbBodyWmaRqMrgnArray> esbBodyWmaRqMrgnArrays;//保证金数组
	
	public EsbBodyWmaRq03002000011A01(){
		
	}

	public String getBranchId() {
		return branchId;
	}

	@XmlElement(name = "BranchId")
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getIntfNo() {
		return intfNo;
	}

	@XmlElement(name = "IntfNo")
	public void setIntfNo(String intfNo) {
		this.intfNo = intfNo;
	}

	public String getCtrNo() {
		return ctrNo;
	}

	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}

	public String getIttTlrNo() {
		return ittTlrNo;
	}

	@XmlElement(name = "IttTlrNo")
	public void setIttTlrNo(String ittTlrNo) {
		this.ittTlrNo = ittTlrNo;
	}

	public String getIttbrId() {
		return ittbrId;
	}

	@XmlElement(name = "IttbrId")
	public void setIttbrId(String ittbrId) {
		this.ittbrId = ittbrId;
	}

	public String getChrgOffNo() {
		return chrgOffNo;
	}

	@XmlElement(name = "ChrgOffNo")
	public void setChrgOffNo(String chrgOffNo) {
		this.chrgOffNo = chrgOffNo;
	}

	public String getDrwrAcctNm() {
		return drwrAcctNm;
	}

	@XmlElement(name = "DrwrAcctNm")
	public void setDrwrAcctNm(String drwrAcctNm) {
		this.drwrAcctNm = drwrAcctNm;
	}

	public String getDrwrAcctNo() {
		return drwrAcctNo;
	}

	@XmlElement(name = "DrwrAcctNo")
	public void setDrwrAcctNo(String drwrAcctNo) {
		this.drwrAcctNo = drwrAcctNo;
	}

	public String getDrweBnkNm() {
		return drweBnkNm;
	}

	@XmlElement(name = "DrweBnkNm")
	public void setDrweBnkNm(String drweBnkNm) {
		this.drweBnkNm = drweBnkNm;
	}

	public String getDrweBnkNo() {
		return drweBnkNo;
	}

	@XmlElement(name = "DrweBnkNo")
	public void setDrweBnkNo(String drweBnkNo) {
		this.drweBnkNo = drweBnkNo;
	}

	public String getDrweBnkAdr() {
		return drweBnkAdr;
	}

	@XmlElement(name = "DrweBnkAdr")
	public void setDrweBnkAdr(String drweBnkAdr) {
		this.drweBnkAdr = drweBnkAdr;
	}

	public String getSumNum() {
		return sumNum;
	}

	@XmlElement(name = "SumNum")
	public void setSumNum(String sumNum) {
		this.sumNum = sumNum;
	}

	public List<EsbBodyWmaRqDbtArray> getEsbBodyWmaRqDbtArrays() {
		return esbBodyWmaRqDbtArrays;
	}

	@XmlElement(name = "DbtInfArray")
	public void setEsbBodyWmaRqDbtArrays(
			List<EsbBodyWmaRqDbtArray> esbBodyWmaRqDbtArrays) {
		this.esbBodyWmaRqDbtArrays = esbBodyWmaRqDbtArrays;
	}
	
	public List<EsbBodyWmaRqMrgnArray> getEsbBodyWmaRqMrgnArrays() {
		return esbBodyWmaRqMrgnArrays;
	}
	
	@XmlElement(name = "MrgnArray")
	public void setEsbBodyWmaRqMrgnArrays(
			List<EsbBodyWmaRqMrgnArray> esbBodyWmaRqMrgnArrays) {
		this.esbBodyWmaRqMrgnArrays = esbBodyWmaRqMrgnArrays;
	}
}
