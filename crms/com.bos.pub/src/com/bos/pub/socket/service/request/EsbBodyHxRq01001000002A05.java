package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRq01001000002A02 
 * @Description: 01001000002通用核心记账		02中间业务简单会计分录记账
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRq01001000002A05 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String txnCd;			//交易码		String(6)	Y	3181
	private String rgonCd;			//地区代码		String(2)	Y	见绵阳银行ESB项目_JJCCBBSD_代码列表
	private String bsnTp;			//业务类型		String(10)	Y
	private String pfwDt;			//提出日期		String(8)	Y	前置日期
	private String sysTrcNo;		//系统跟踪号		String(8)	Y	前置系统跟踪号
	private String txnNum;			//交易笔数	String(8)	Y	循环体记录数
	private BigDecimal txnTotAmt;	//交易总金额		Double(20,2)Y
	private String oprInd;			//操作标志		String(4)	Y	"0-根据接口传入的TxnAmt,TxnAmt1,和借贷标志进行处理(适用现金交易和银银平台取款交易)
	private String vchrId;			//凭证编号		String(50)	Y	空
	private String ittAcctInd;		//发起方账户代号	String(18)	Y	送全账号
	private String wthdwMth;		//取款方式		String(1)	N	不检查密码送空,个人客户检查密码取1
	private String pswd;			//密码		String(30)	N
	private String sgnDt;			//签发日期		String(8)	N	如果是支票必输
	private BigDecimal drftAmt;		//汇票金额		Double(20,2)N
	private String rmk;				//备注		String(256)	N
	
	private List<EsbBodyHxRqZhArray> esbBodyHxRqZhArrays;//账号数组
	
	
	public EsbBodyHxRq01001000002A05(){
		
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

	public String getBsnTp() {
		return bsnTp;
	}

	@XmlElement(name = "BsnTp")
	public void setBsnTp(String bsnTp) {
		this.bsnTp = bsnTp;
	}

	public String getOprInd() {
		return oprInd;
	}

	@XmlElement(name = "OprInd")
	public void setOprInd(String oprInd) {
		this.oprInd = oprInd;
	}

	public String getVchrId() {
		return vchrId;
	}

	@XmlElement(name = "VchrId")
	public void setVchrId(String vchrId) {
		this.vchrId = vchrId;
	}

	public String getWthdwMth() {
		return wthdwMth;
	}

	@XmlElement(name = "WthdwMth")
	public void setWthdwMth(String wthdwMth) {
		this.wthdwMth = wthdwMth;
	}

	public String getPswd() {
		return pswd;
	}

	@XmlElement(name = "Pswd")
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getPfwDt() {
		return pfwDt;
	}

	@XmlElement(name = "PfwDt")
	public void setPfwDt(String pfwDt) {
		this.pfwDt = pfwDt;
	}

	public String getSysTrcNo() {
		return sysTrcNo;
	}

	@XmlElement(name = "SysTrcNo")
	public void setSysTrcNo(String sysTrcNo) {
		this.sysTrcNo = sysTrcNo;
	}

	public String getTxnNum() {
		return txnNum;
	}

	@XmlElement(name = "TxnNum")
	public void setTxnNum(String txnNum) {
		this.txnNum = txnNum;
	}

	public BigDecimal getTxnTotAmt() {
		return txnTotAmt;
	}

	@XmlElement(name = "TxnTotAmt")
	public void setTxnTotAmt(BigDecimal txnTotAmt) {
		this.txnTotAmt = txnTotAmt;
	}

	public String getIttAcctInd() {
		return ittAcctInd;
	}

	@XmlElement(name = "IttAcctInd")
	public void setIttAcctInd(String ittAcctInd) {
		this.ittAcctInd = ittAcctInd;
	}

	public String getSgnDt() {
		return sgnDt;
	}

	@XmlElement(name = "SgnDt")
	public void setSgnDt(String sgnDt) {
		this.sgnDt = sgnDt;
	}

	public BigDecimal getDrftAmt() {
		return drftAmt;
	}

	@XmlElement(name = "DrftAmt")
	public void setDrftAmt(BigDecimal drftAmt) {
		this.drftAmt = drftAmt;
	}

	public String getRmk() {
		return rmk;
	}

	@XmlElement(name = "Rmk")
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public List<EsbBodyHxRqZhArray> getEsbBodyHxRqZhArrays() {
		return esbBodyHxRqZhArrays;
	}
	
	@XmlElement(name = "TxnArray")
	public void setEsbBodyHxRqZhArrays(List<EsbBodyHxRqZhArray> esbBodyHxRqZhArrays) {
		this.esbBodyHxRqZhArrays = esbBodyHxRqZhArrays;
	}
	
}
