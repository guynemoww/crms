package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyGjRq05001000002A04 
 * @Description: 05001000002保函维护	04保函修改
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyGjRq05001000002A04 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ctrNo;			//合同号
	private String dbtNo;			//借据号
	private String gntNo;			//保函号
	private String rmtInstNo;		//汇款机构号
	private String cstNo;			//客户代号
	private double afAmdtAmt;		//修改后金额
	private String newEfftDt;		//新到期日
	private double mrgnPct;			//保证金比例
	private String mrgnNum;			//保证金个数
	private List<EsbBodyGjRqMrgnArray> esbBodyGjRqMrgnArrays;//保证金数组

	public EsbBodyGjRq05001000002A04(){
		
	}

	public String getCtrNo() {
		return ctrNo;
	}

	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}

	public String getDbtNo() {
		return dbtNo;
	}

	@XmlElement(name = "DbtNo")
	public void setDbtNo(String dbtNo) {
		this.dbtNo = dbtNo;
	}

	public String getGntNo() {
		return gntNo;
	}

	@XmlElement(name = "GntNo")
	public void setGntNo(String gntNo) {
		this.gntNo = gntNo;
	}

	public String getRmtInstNo() {
		return rmtInstNo;
	}

	@XmlElement(name = "RmtInstNo")
	public void setRmtInstNo(String rmtInstNo) {
		this.rmtInstNo = rmtInstNo;
	}

	public String getCstNo() {
		return cstNo;
	}

	@XmlElement(name = "CstNo")
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public double getAfAmdtAmt() {
		return afAmdtAmt;
	}

	@XmlElement(name = "AfAmdtAmt")
	public void setAfAmdtAmt(double afAmdtAmt) {
		this.afAmdtAmt = afAmdtAmt;
	}

	public String getNewEfftDt() {
		return newEfftDt;
	}

	@XmlElement(name = "NewEfftDt")
	public void setNewEfftDt(String newEfftDt) {
		this.newEfftDt = newEfftDt;
	}

	public double getMrgnPct() {
		return mrgnPct;
	}

	@XmlElement(name = "MrgnPct")
	public void setMrgnPct(double mrgnPct) {
		this.mrgnPct = mrgnPct;
	}

	public String getMrgnNum() {
		return mrgnNum;
	}

	@XmlElement(name = "MrgnNum")
	public void setMrgnNum(String mrgnNum) {
		this.mrgnNum = mrgnNum;
	}

	public List<EsbBodyGjRqMrgnArray> getEsbBodyGjRqMrgnArrays() {
		return esbBodyGjRqMrgnArrays;
	}

	@XmlElement(name = "MrgnArray")
	public void setEsbBodyGjRqMrgnArrays(
			List<EsbBodyGjRqMrgnArray> esbBodyGjRqMrgnArrays) {
		this.esbBodyGjRqMrgnArrays = esbBodyGjRqMrgnArrays;
	}
}
