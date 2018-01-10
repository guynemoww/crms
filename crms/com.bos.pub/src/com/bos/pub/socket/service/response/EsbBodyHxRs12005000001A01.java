package com.bos.pub.socket.service.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyHxRs12005000001A01 
 * @Description: 12005000001对账文件传送		01日终对账
 * 				 12005000002批量处理文件传送	01批量账务处理
 * 				 12005000002批量处理文件传送	02批量运行结果查询
 * 				 12005000002批量账务处理		03批量入账请求
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyHxRs12005000001A01 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String repeatTms;	//重复次数		String(4)		成功返回字段
	private String tellerSeqNo;	//柜员流水号	String(8)		主机产生的柜员流水号，成功返回字段
	
	public EsbBodyHxRs12005000001A01(){
		
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
}
