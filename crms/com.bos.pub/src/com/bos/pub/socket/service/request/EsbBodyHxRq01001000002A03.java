package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq01001000002A03 
 * @Description: 01001000002通用核心记账		03表外账记账
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq01001000002A03 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;			//交易码		String(6)	Y
	private String rgonCd;			//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String qryRcrdNum;		//查询记录数	String(8)	Y
	private String mdlTxnDt;		//中间交易日期	String(8)	Y	YYYYMMDD
	private String recptPymtInd;	//收付标志		String(2)	Y
	private String acctCd;			//科目代码		String(6)	Y
	private String cstNm;			//客户名称		String(50)	N
	private String relAcctNo;		//关联账号		String(35)	N
	private String trdTp;			//交易种类		String(10)	Y
	private String complNo;			//下柜编号		String(14)	Y
	private String ctrNo;			//合同号		String(20)	Y
	private String cstNo;			//客户代号		String(10)	Y
	private List<EsbBodyHxRqTskArray> esbBodyHxRqTskArrays;//任务数组
	
	public EsbBodyHxRq01001000002A03(){
		
	}

	public String getTxnCd() {
		return txnCd;
	}

	@XmlElement(name = "TxnCd")
	public void setTxnCd(String txnCd) {
		this.txnCd = txnCd;
	}

	public String getRgonCd() {
		return rgonCd;
	}

	@XmlElement(name = "RgonCd")
	public void setRgonCd(String rgonCd) {
		this.rgonCd = rgonCd;
	}

	public String getQryRcrdNum() {
		return qryRcrdNum;
	}

	@XmlElement(name = "QryRcrdNum")
	public void setQryRcrdNum(String qryRcrdNum) {
		this.qryRcrdNum = qryRcrdNum;
	}

	public String getMdlTxnDt() {
		return mdlTxnDt;
	}

	@XmlElement(name = "MdlTxnDt")
	public void setMdlTxnDt(String mdlTxnDt) {
		this.mdlTxnDt = mdlTxnDt;
	}

	public String getRecptPymtInd() {
		return recptPymtInd;
	}

	@XmlElement(name = "RecptPymtInd")
	public void setRecptPymtInd(String recptPymtInd) {
		this.recptPymtInd = recptPymtInd;
	}

	public String getAcctCd() {
		return acctCd;
	}

	@XmlElement(name = "AcctCd")
	public void setAcctCd(String acctCd) {
		this.acctCd = acctCd;
	}

	public String getCstNm() {
		return cstNm;
	}

	@XmlElement(name = "CstNm")
	public void setCstNm(String cstNm) {
		this.cstNm = cstNm;
	}

	public String getRelAcctNo() {
		return relAcctNo;
	}

	@XmlElement(name = "RelAcctNo")
	public void setRelAcctNo(String relAcctNo) {
		this.relAcctNo = relAcctNo;
	}

	public String getTrdTp() {
		return trdTp;
	}

	@XmlElement(name = "TrdTp")
	public void setTrdTp(String trdTp) {
		this.trdTp = trdTp;
	}

	public String getComplNo() {
		return complNo;
	}

	@XmlElement(name = "ComplNo")
	public void setComplNo(String complNo) {
		this.complNo = complNo;
	}

	public String getCtrNo() {
		return ctrNo;
	}

	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}

	public String getCstNo() {
		return cstNo;
	}

	@XmlElement(name = "CstNo")
	public void setCstNo(String cstNo) {
		this.cstNo = cstNo;
	}

	public List<EsbBodyHxRqTskArray> getEsbBodyHxRqTskArrays() {
		return esbBodyHxRqTskArrays;
	}

	@XmlElement(name = "TskArray")
	public void setEsbBodyHxRqTskArrays(
			List<EsbBodyHxRqTskArray> esbBodyHxRqTskArrays) {
		this.esbBodyHxRqTskArrays = esbBodyHxRqTskArrays;
	}
}
