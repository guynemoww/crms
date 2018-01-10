package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRs12005000002A04 
 * @Description: 12005000002批量账务处理		04批量入账结果查询
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRs12005000002A04 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String repeatTms;	//重复次数		String(4)		前补空格	
	private String tellerSeqNo;	//柜员流水号	String(8)		主机产生的柜员流水号，成功返回字段	
	private String dealSt;		//处理状态		String(2)		"0- 初始1- 处理中2- 成功3- 失败"	
	private String rcrdNum;		//记录数		String(10)			
	private String fileNm;		//文件名		String(100)			
	
	public EsbBodyHxRs12005000002A04(){
		
	}

	public String getRepeatTms() {
		return repeatTms;
	}

	@XmlElement(name = "RepeatTms")
	public void setRepeatTms(String repeatTms) {
		this.repeatTms = repeatTms;
	}

	public String getTellerSeqNo() {
		return tellerSeqNo;
	}

	@XmlElement(name = "TellerSeqNo")
	public void setTellerSeqNo(String tellerSeqNo) {
		this.tellerSeqNo = tellerSeqNo;
	}

	public String getDealSt() {
		return dealSt;
	}

	@XmlElement(name = "DealSt")
	public void setDealSt(String dealSt) {
		this.dealSt = dealSt;
	}

	public String getRcrdNum() {
		return rcrdNum;
	}

	@XmlElement(name = "RcrdNum")
	public void setRcrdNum(String rcrdNum) {
		this.rcrdNum = rcrdNum;
	}

	public String getFileNm() {
		return fileNm;
	}

	@XmlElement(name = "FileNm")
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
}
