package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
* @ClassName: TranMsgVO 
* @Description: 账务类公共报文
* @author GIT-ABC
* @date 2015-5-11 下午05:46:44 
*
 */
public class TranMsgVO implements Serializable {
	private String cTranCod;		// 交易代码(填3181)
	private String cPrvCod;			// 地区代号(机构表添加此字段)
	private String cOpnDep;			// 机构代号
	private String cOpr;			// 交易柜员(放款虚拟柜员和还款虚拟柜员 管理另外建立 对账类型chk 与柜员一致)
	private String cTemCod;			// 终端代号(不用填)
	private String cBusTyp;			// 业务类型(最少1个 最好2个与管理分开)
	private String cOprFlg;			// 操作标志
	private String cBrwLgo;			// 借贷标志
	private String cCahFlg;			// 现转标志(填1)
	private String cTckCod;			// 凭证代号(不用填)	
	private String cAcct;			// 账号代号
	private BigDecimal cAmt;		// 交易金额(放款或者还款)
	private BigDecimal cAmt1;		// 交易金额1(不用填)
	private BigDecimal cAmt2;		// 交易金额2(不用填)
	private String cOthAcct;		// 对方账号代号(必须填)
	private String cBrifCod;		// 摘要代号(与事件码作映射)
	private String cCahPrjCod;		// 现金项目代号(不用填)
	private String cDrwTyp;			// 取款方式(不用填)
	private String cPwd;			// 密码(不用填)
	private String cTranDate;		// 提出日期(交易日期)
	private int cFlwCod;			// 系统跟踪号(对账流水号)

	public TranMsgVO(){
	}

	public String getcTranCod() {
		return cTranCod;
	}

	@XmlElement(name = "CTranCod")
	public void setcTranCod(String cTranCod) {
		this.cTranCod = cTranCod;
	}

	public String getcPrvCod() {
		return cPrvCod;
	}

	@XmlElement(name = "CPrvCod")
	public void setcPrvCod(String cPrvCod) {
		this.cPrvCod = cPrvCod;
	}

	public String getcOpnDep() {
		return cOpnDep;
	}

	@XmlElement(name = "COpnDep")
	public void setcOpnDep(String cOpnDep) {
		this.cOpnDep = cOpnDep;
	}

	public String getcOpr() {
		return cOpr;
	}

	@XmlElement(name = "COpr")
	public void setcOpr(String cOpr) {
		this.cOpr = cOpr;
	}

	public String getcTemCod() {
		return cTemCod;
	}

	@XmlElement(name = "CTemCod")
	public void setcTemCod(String cTemCod) {
		this.cTemCod = cTemCod;
	}

	public String getcBusTyp() {
		return cBusTyp;
	}

	@XmlElement(name = "CBusTyp")
	public void setcBusTyp(String cBusTyp) {
		this.cBusTyp = cBusTyp;
	}

	public String getcOprFlg() {
		return cOprFlg;
	}

	@XmlElement(name = "COprFlg")
	public void setcOprFlg(String cOprFlg) {
		this.cOprFlg = cOprFlg;
	}

	public String getcBrwLgo() {
		return cBrwLgo;
	}

	@XmlElement(name = "CBrwLgo")
	public void setcBrwLgo(String cBrwLgo) {
		this.cBrwLgo = cBrwLgo;
	}

	public String getcCahFlg() {
		return cCahFlg;
	}

	@XmlElement(name = "CCahFlg")
	public void setcCahFlg(String cCahFlg) {
		this.cCahFlg = cCahFlg;
	}

	public String getcTckCod() {
		return cTckCod;
	}

	@XmlElement(name = "CTckCod")
	public void setcTckCod(String cTckCod) {
		this.cTckCod = cTckCod;
	}

	public String getcAcct() {
		return cAcct;
	}

	@XmlElement(name = "CAcct")
	public void setcAcct(String cAcct) {
		this.cAcct = cAcct;
	}

	public BigDecimal getcAmt() {
		return cAmt;
	}

	@XmlElement(name = "CAmt")
	public void setcAmt(BigDecimal cAmt) {
		this.cAmt = cAmt;
	}

	public BigDecimal getcAmt1() {
		return cAmt1;
	}

	@XmlElement(name = "CAmt1")
	public void setcAmt1(BigDecimal cAmt1) {
		this.cAmt1 = cAmt1;
	}

	public BigDecimal getcAmt2() {
		return cAmt2;
	}

	@XmlElement(name = "CAmt2")
	public void setcAmt2(BigDecimal cAmt2) {
		this.cAmt2 = cAmt2;
	}

	public String getcOthAcct() {
		return cOthAcct;
	}

	@XmlElement(name = "COthAcct")
	public void setcOthAcct(String cOthAcct) {
		this.cOthAcct = cOthAcct;
	}

	public String getcBrifCod() {
		return cBrifCod;
	}

	@XmlElement(name = "CBrifCod")
	public void setcBrifCod(String cBrifCod) {
		this.cBrifCod = cBrifCod;
	}

	public String getcCahPrjCod() {
		return cCahPrjCod;
	}

	@XmlElement(name = "CCahPrjCod")
	public void setcCahPrjCod(String cCahPrjCod) {
		this.cCahPrjCod = cCahPrjCod;
	}

	public String getcDrwTyp() {
		return cDrwTyp;
	}

	@XmlElement(name = "CDrwTyp")
	public void setcDrwTyp(String cDrwTyp) {
		this.cDrwTyp = cDrwTyp;
	}

	public String getcPwd() {
		return cPwd;
	}

	@XmlElement(name = "CPwd")
	public void setcPwd(String cPwd) {
		this.cPwd = cPwd;
	}

	public String getcTranDate() {
		return cTranDate;
	}

	@XmlElement(name = "CTranDate")
	public void setcTranDate(String cTranDate) {
		this.cTranDate = cTranDate;
	}

	public int getcFlwCod() {
		return cFlwCod;
	}

	@XmlElement(name = "CFlwCod")
	public void setcFlwCod(int cFlwCod) {
		this.cFlwCod = cFlwCod;
	}

	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "TranMsgVO [cAcct=" + cAcct + ", cAmt=" + cAmt + ", cAmt1="
				+ cAmt1 + ", cAmt2=" + cAmt2 + ", cBrifCod=" + cBrifCod
				+ ", cBrwLgo=" + cBrwLgo + ", cBusTyp=" + cBusTyp
				+ ", cCahFlg=" + cCahFlg + ", cCahPrjCod=" + cCahPrjCod
				+ ", cDrwTyp=" + cDrwTyp + ", cFlwCod=" + cFlwCod
				+ ", cOpnDep=" + cOpnDep + ", cOpr=" + cOpr + ", cOprFlg="
				+ cOprFlg + ", cOthAcct=" + cOthAcct + ", cPrvCod=" + cPrvCod
				+ ", cPwd=" + cPwd + ", cTckCod=" + cTckCod + ", cTemCod="
				+ cTemCod + ", cTranCod=" + cTranCod + ", cTranDate="
				+ cTranDate + "]";
	}
}
