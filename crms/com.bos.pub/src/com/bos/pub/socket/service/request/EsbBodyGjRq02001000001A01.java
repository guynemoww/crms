package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyGjRq02001000001A01 
 * @Description: 02001000001贷款放款		01押汇融资类业务放款
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyGjRq02001000001A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ctrNo;			//合同号
	private String dbtNo;			//借据号
	private String eCIFCstNo;		//ECIF客户代号
	private String bsnTp;			//业务类型
	private String ccyTp;			//货币种类
	private double aplyAmt;			//申请金额
	private String ittDt;			//起始日
	private String expDt;			//到期日
	private double execIntRate;		//执行年利率
	private String mainGryTy;		//主要担保方式
	private String rmtInstNo;		//汇款机构号
	private String pyeAcctNo;		//收款人账号
	private double odueIntRate;		//逾期年利率
	private String doctyValDt;		//押汇起息日
	private String intSetlMth;		//结息方式
	private String bsnNo;			//业务号码
	private String lCNo;			//信用证号码
	private String doctyDcnInd;		//押汇贴现标志
	private String trdCtrNo;		//贸易合同编号
	private double trdCtrTotAmt;	//贸易合同总金额
	private double mrgnPct;			//保证金比例
	private String invNo;			//发票号
	private String crInsfncTp;		//信保融资标签
	private String gntNo;			//保函号
	private String mrgnNum;			//保证金个数
	private List<EsbBodyGjRqMrgnArray> esbBodyGjRqMrgnArrays;//保证金数组
	
	public EsbBodyGjRq02001000001A01(){
		
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

	public String geteCIFCstNo() {
		return eCIFCstNo;
	}

	@XmlElement(name = "ECIFCstNo")
	public void seteCIFCstNo(String eCIFCstNo) {
		this.eCIFCstNo = eCIFCstNo;
	}

	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getCcyTp() {
		return ccyTp;
	}

	@XmlElement(name = "CcyTp")
	public void setCcyTp(String ccyTp) {
		this.ccyTp = ccyTp;
	}

	public double getAplyAmt() {
		return aplyAmt;
	}

	@XmlElement(name = "AplyAmt")
	public void setAplyAmt(double aplyAmt) {
		this.aplyAmt = aplyAmt;
	}

	public String getIttDt() {
		return ittDt;
	}

	@XmlElement(name = "IttDt")
	public void setIttDt(String ittDt) {
		this.ittDt = ittDt;
	}

	public String getExpDt() {
		return expDt;
	}

	@XmlElement(name = "ExpDt")
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}

	public double getExecIntRate() {
		return execIntRate;
	}

	@XmlElement(name = "ExecIntRate")
	public void setExecIntRate(double execIntRate) {
		this.execIntRate = execIntRate;
	}

	public String getMainGryTy() {
		return mainGryTy;
	}

	@XmlElement(name = "MainGryTy")
	public void setMainGryTy(String mainGryTy) {
		this.mainGryTy = mainGryTy;
	}

	public String getRmtInstNo() {
		return rmtInstNo;
	}

	@XmlElement(name = "RmtInstNo")
	public void setRmtInstNo(String rmtInstNo) {
		this.rmtInstNo = rmtInstNo;
	}

	public String getPyeAcctNo() {
		return pyeAcctNo;
	}

	@XmlElement(name = "PyeAcctNo")
	public void setPyeAcctNo(String pyeAcctNo) {
		this.pyeAcctNo = pyeAcctNo;
	}

	public double getOdueIntRate() {
		return odueIntRate;
	}

	@XmlElement(name = "OdueIntRate")
	public void setOdueIntRate(double odueIntRate) {
		this.odueIntRate = odueIntRate;
	}

	public String getDoctyValDt() {
		return doctyValDt;
	}

	@XmlElement(name = "DoctyValDt")
	public void setDoctyValDt(String doctyValDt) {
		this.doctyValDt = doctyValDt;
	}

	public String getIntSetlMth() {
		return intSetlMth;
	}

	@XmlElement(name = "IntSetlMth")
	public void setIntSetlMth(String intSetlMth) {
		this.intSetlMth = intSetlMth;
	}

	public String getBsnNo() {
		return bsnNo;
	}

	@XmlElement(name = "BsnNo")
	public void setBsnNo(String bsnNo) {
		this.bsnNo = bsnNo;
	}

	public String getlCNo() {
		return lCNo;
	}

	@XmlElement(name = "LCNo")
	public void setlCNo(String lCNo) {
		this.lCNo = lCNo;
	}

	public String getDoctyDcnInd() {
		return doctyDcnInd;
	}

	@XmlElement(name = "DoctyDcnInd")
	public void setDoctyDcnInd(String doctyDcnInd) {
		this.doctyDcnInd = doctyDcnInd;
	}

	public String getTrdCtrNo() {
		return trdCtrNo;
	}

	@XmlElement(name = "TrdCtrNo")
	public void setTrdCtrNo(String trdCtrNo) {
		this.trdCtrNo = trdCtrNo;
	}

	public double getTrdCtrTotAmt() {
		return trdCtrTotAmt;
	}

	@XmlElement(name = "TrdCtrTotAmt")
	public void setTrdCtrTotAmt(double trdCtrTotAmt) {
		this.trdCtrTotAmt = trdCtrTotAmt;
	}

	public double getMrgnPct() {
		return mrgnPct;
	}

	@XmlElement(name = "MrgnPct")
	public void setMrgnPct(double mrgnPct) {
		this.mrgnPct = mrgnPct;
	}

	public String getInvNo() {
		return invNo;
	}

	@XmlElement(name = "InvNo")
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getCrInsfncTp() {
		return crInsfncTp;
	}

	@XmlElement(name = "CrInsfncTp")
	public void setCrInsfncTp(String crInsfncTp) {
		this.crInsfncTp = crInsfncTp;
	}

	public String getGntNo() {
		return gntNo;
	}

	@XmlElement(name = "GntNo")
	public void setGntNo(String gntNo) {
		this.gntNo = gntNo;
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
