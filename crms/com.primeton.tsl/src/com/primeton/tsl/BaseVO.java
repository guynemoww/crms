package com.primeton.tsl;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
* @ClassName: BaseVO 
* @Description: 计量系统用于交易的业务实体超类
* @author GIT-Sunny
* @date 2015-3-31 下午07:41:03 
*
 */
public class BaseVO implements Serializable {
//	private String trnCod;		// 交易码
//	private String opr;			// 操作员
//	private String aut;			// 授权员
//	private int acsMethStan;	// 接入系统流水----接入系统传入
//	private int supStan;		// 流水号----计量系统生成
//	private int rcnStan;		// 对账流水号	
//	private String legPerCod;	// 法人代码
//	private String opnDep;		// 开户机构
//	private String rlsDep;		// 交易机构
//	private String tranFrom;	// 业务渠道来源
//	private String sndDate;		// 发起系统营业日期
//	private String busDate;		// 营业日期
//	private String trnTimes;	// 交易次数标志----计量系统修改后下传
//	private String toCoreSys;	// 交易是否转发核心系统标志----计量系统修改后下传
//	private String outFile;		//上传文件名
//	private String outFilePath;	//上传文件路径
//	private String inFile;		//下传文件名
//	private String inFilePath;	//下传文件路径
//	private String rpsCod;		//响应码
//	private String rpsMsg;		//响应信息
//	private int recSeq;         //分录顺序号
//	private String branchId; 	// 机构代码 String 6 可选
	
	
	public BaseVO(){
	}

//	public String getTrnCod() {
//		return trnCod;
//	}
//
//	@XmlElement(name="TrnCod")
//	public void setTrnCod(String trnCod) {
//		this.trnCod = trnCod;
//	}
//
//	public String getOpr() {
//		return opr;
//	}
//
//	@XmlElement(name="Opr")
//	public void setOpr(String opr) {
//		this.opr = opr;
//	}
//
//	public String getAut() {
//		return aut;
//	}
//
//	@XmlElement(name="Aut")
//	public void setAut(String aut) {
//		this.aut = aut;
//	}
//
//	public int getAcsMethStan() {
//		return acsMethStan;
//	}
//
//	@XmlElement(name="AcsMethStan")
//	public void setAcsMethStan(int acsMethStan) {
//		this.acsMethStan = acsMethStan;
//	}
//
//	public int getSupStan() {
//		return supStan;
//	}
//
//	@XmlElement(name="SupStan")
//	public void setSupStan(int supStan) {
//		this.supStan = supStan;
//	}
//
//	public int getRcnStan() {
//		return rcnStan;
//	}
//
//	@XmlElement(name="RcnStan")
//	public void setRcnStan(int rcnStan) {
//		this.rcnStan = rcnStan;
//	}
//
//	public String getLegPerCod() {
//		return legPerCod;
//	}
//
//	@XmlElement(name="LegPerCod")
//	public void setLegPerCod(String legPerCod) {
//		this.legPerCod = legPerCod;
//	}
//
//	public String getOpnDep() {
//		return opnDep;
//	}
//
//	@XmlElement(name="OpnDep")
//	public void setOpnDep(String opnDep) {
//		this.opnDep = opnDep;
//	}
//
//	public String getRlsDep() {
//		return rlsDep;
//	}
//
//	@XmlElement(name="RlsDep")
//	public void setRlsDep(String rlsDep) {
//		this.rlsDep = rlsDep;
//	}
//
//	public String getTranFrom() {
//		return tranFrom;
//	}
//
//	@XmlElement(name="TranFrom")
//	public void setTranFrom(String tranFrom) {
//		this.tranFrom = tranFrom;
//	}
//
//	public String getSndDate() {
//		return sndDate;
//	}
//
//	@XmlElement(name="SndDate")
//	public void setSndDate(String sndDate) {
//		this.sndDate = sndDate;
//	}
//
//	public String getBusDate() {
//		return busDate;
//	}
//
//	@XmlElement(name="BusDate")
//	public void setBusDate(String busDate) {
//		this.busDate = busDate;
//	}
//
//	public String getTrnTimes() {
//		return trnTimes;
//	}
//
//	@XmlElement(name="TrnTimes")
//	public void setTrnTimes(String trnTimes) {
//		this.trnTimes = trnTimes;
//	}
//
//	public String getToCoreSys() {
//		return toCoreSys;
//	}
//
//	@XmlElement(name="ToCoreSys")
//	public void setToCoreSys(String toCoreSys) {
//		this.toCoreSys = toCoreSys;
//	}
//
//	public String getOutFile() {
//		return outFile;
//	}
//
//	@XmlElement(name="OutFile")
//	public void setOutFile(String outFile) {
//		this.outFile = outFile;
//	}
//
//	public String getOutFilePath() {
//		return outFilePath;
//	}
//
//	@XmlElement(name="OutFilePath")
//	public void setOutFilePath(String outFilePath) {
//		this.outFilePath = outFilePath;
//	}
//
//	public String getInFile() {
//		return inFile;
//	}
//
//	@XmlElement(name="InFile")
//	public void setInFile(String inFile) {
//		this.inFile = inFile;
//	}
//
//	public String getInFilePath() {
//		return inFilePath;
//	}
//
//	@XmlElement(name="InFilePath")
//	public void setInFilePath(String inFilePath) {
//		this.inFilePath = inFilePath;
//	}
//
//	public String getRpsCod() {
//		return rpsCod;
//	}
//
//	@XmlElement(name="RpsCod")
//	public void setRpsCod(String rpsCod) {
//		this.rpsCod = rpsCod;
//	}
//
//	public String getRpsMsg() {
//		return rpsMsg;
//	}
//
//	@XmlElement(name="RpsMsg")
//	public void setRpsMsg(String rpsMsg) {
//		this.rpsMsg = rpsMsg;
//	}
//
//	public int getRecSeq() {
//		return recSeq;
//	}
//
//	@XmlElement(name="RecSeq")
//	public void setRecSeq(int recSeq) {
//		this.recSeq = recSeq;
//	}
//
//	public String getBranchId() {
//		return branchId;
//	}
//
//	@XmlElement(name="BranchId")
//	public void setBranchId(String branchId) {
//		this.branchId = branchId;
//	}
//
//	/* (非 Javadoc) 
//	* <p>Title: toString</p> 
//	* <p>Description: </p> 
//	* @return 
//	* @see java.lang.Object#toString() 
//	*/
//	@Override
//	public String toString() {
//		return "BaseVO [acsMethStan=" + acsMethStan + ", aut=" + aut
//				+ ", busDate=" + busDate + ", inFile=" + inFile
//				+ ", inFilePath=" + inFilePath + ", legPerCod=" + legPerCod
//				+ ", opnDep=" + opnDep + ", opr=" + opr + ", outFile="
//				+ outFile + ", outFilePath=" + outFilePath + ", rcnStan="
//				+ rcnStan + ", rlsDep=" + rlsDep + ", rpsCod=" + rpsCod
//				+ ", rpsMsg=" + rpsMsg + ", sndDate=" + sndDate + ", supStan="
//				+ supStan + ", toCoreSys=" + toCoreSys + ", tranFrom="
//				+ tranFrom + ", trnCod=" + trnCod + ", trnTimes=" + trnTimes
//				+ "]";
//	}
	private String tranCod;    //交易码                                                       
	private String legPerCod;  //法人代码                                                     
	private String opnDep;     //开户机构                                                     
	private String trnDep;     //交易机构                                                     
	private String depCod;     //部门代码                                                     
	private String origFrom;   //交易发起渠道                                                 
	private String tranFrom;   //交易接收渠道（A+外围系统）                                   
	private String tranDate;   //A+营业日期                                                   
	private String accSysDate; //交易对手营业日期                                             
	private Long acsMethStan;  //接入系统流水----接入系统传入                                 
	private Long supStan;      //流水号----A+生成                                             
	private Long rcnStan;      //对账流水号                                                   
	private Long origStan;     //原对账流水号                                                 
	private String tranTimes;  //交易次数标志----A+修改后下传                                 
	private String toCoreSys;  //交易是否转发核心系统标志----A+系统修改后下传 1=转发负债类系统
	private String reqFile;    //上传文件名                                                   
	private String reqFileDir; //上传文件路径                                                 
	private Long reqCnt;       //上传文件笔数                                                 
	private String rltFile;    //下传文件名                                                   
	private String rltFileDir; //下传文件路径                                                 
	private Long rltCnt;       //下传文件笔数                                                 
	private String opr;        //操作员                                                       
	private String aut;        //授权员                                                       
	private String errCod;     //响应码                                                       
	private String errMsg;     //响应信息
	public String getTranCod() {
		return tranCod;
	}
	public void setTranCod(String tranCod) {
		this.tranCod = tranCod;
	}
	public String getLegPerCod() {
		return legPerCod;
	}
	public void setLegPerCod(String legPerCod) {
		this.legPerCod = legPerCod;
	}
	public String getOpnDep() {
		return opnDep;
	}
	public void setOpnDep(String opnDep) {
		this.opnDep = opnDep;
	}
	public String getTrnDep() {
		return trnDep;
	}
	public void setTrnDep(String trnDep) {
		this.trnDep = trnDep;
	}
	public String getDepCod() {
		return depCod;
	}
	public void setDepCod(String depCod) {
		this.depCod = depCod;
	}
	public String getOrigFrom() {
		return origFrom;
	}
	public void setOrigFrom(String origFrom) {
		this.origFrom = origFrom;
	}
	public String getTranFrom() {
		return tranFrom;
	}
	public void setTranFrom(String tranFrom) {
		this.tranFrom = tranFrom;
	}
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getAccSysDate() {
		return accSysDate;
	}
	public void setAccSysDate(String accSysDate) {
		this.accSysDate = accSysDate;
	}
	public Long getAcsMethStan() {
		return acsMethStan;
	}
	public void setAcsMethStan(Long acsMethStan) {
		this.acsMethStan = acsMethStan;
	}
	public Long getSupStan() {
		return supStan;
	}
	public void setSupStan(Long supStan) {
		this.supStan = supStan;
	}
	public Long getRcnStan() {
		return rcnStan;
	}
	public void setRcnStan(Long rcnStan) {
		this.rcnStan = rcnStan;
	}
	public Long getOrigStan() {
		return origStan;
	}
	public void setOrigStan(Long origStan) {
		this.origStan = origStan;
	}
	public String getTranTimes() {
		return tranTimes;
	}
	public void setTranTimes(String tranTimes) {
		this.tranTimes = tranTimes;
	}
	public String getToCoreSys() {
		return toCoreSys;
	}
	public void setToCoreSys(String toCoreSys) {
		this.toCoreSys = toCoreSys;
	}
	public String getReqFile() {
		return reqFile;
	}
	public void setReqFile(String reqFile) {
		this.reqFile = reqFile;
	}
	public String getReqFileDir() {
		return reqFileDir;
	}
	public void setReqFileDir(String reqFileDir) {
		this.reqFileDir = reqFileDir;
	}
	public Long getReqCnt() {
		return reqCnt;
	}
	public void setReqCnt(Long reqCnt) {
		this.reqCnt = reqCnt;
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
	public Long getRltCnt() {
		return rltCnt;
	}
	public void setRltCnt(Long rltCnt) {
		this.rltCnt = rltCnt;
	}
	public String getOpr() {
		return opr;
	}
	public void setOpr(String opr) {
		this.opr = opr;
	}
	public String getAut() {
		return aut;
	}
	public void setAut(String aut) {
		this.aut = aut;
	}
	public String getErrCod() {
		return errCod;
	}
	public void setErrCod(String errCod) {
		this.errCod = errCod;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	@Override
	public String toString() {
		return "BaseVO [tranCod=" + tranCod + ", legPerCod=" + legPerCod
				+ ", opnDep=" + opnDep + ", trnDep=" + trnDep + ", depCod="
				+ depCod + ", origFrom=" + origFrom + ", tranFrom=" + tranFrom
				+ ", tranDate=" + tranDate + ", accSysDate=" + accSysDate
				+ ", acsMethStan=" + acsMethStan + ", supStan=" + supStan
				+ ", rcnStan=" + rcnStan + ", origStan=" + origStan
				+ ", tranTimes=" + tranTimes + ", toCoreSys=" + toCoreSys
				+ ", reqFile=" + reqFile + ", reqFileDir=" + reqFileDir
				+ ", reqCnt=" + reqCnt + ", rltFile=" + rltFile
				+ ", rltFileDir=" + rltFileDir + ", rltCnt=" + rltCnt
				+ ", opr=" + opr + ", aut=" + aut + ", errCod=" + errCod
				+ ", errMsg=" + errMsg + "]";
	}
	
	
}
