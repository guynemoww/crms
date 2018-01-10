package com.bos.pub.socket.service.request;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bos.pub.socket.service.request.base.EsbBody;

/**
 * 
 * @ClassName: EsbBodyWmaRq03002000011A02 
 * @Description: 03002000011票据信息维护		02银行承兑汇票打印撤销	
 *
 */
@XmlRootElement(name = "BODY")
public class EsbBodyWmaRq03002000011A02 extends EsbBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ctrNo;			//合同号			String(20)	Y
	private String chrgOffNo;		//出账号			String(20)	Y
	private String oprInd;			//操作标志		String(4)	Y
	private String sumNum;			//总笔数			String(9)	Y
	private List<EsbBodyWmaRqDbtArray> esbBodyWmaRqDbtArrays;//借据信息数组
	
	public EsbBodyWmaRq03002000011A02(){
		
	}

	public String getCtrNo() {
		return ctrNo;
	}

	@XmlElement(name = "CtrNo")
	public void setCtrNo(String ctrNo) {
		this.ctrNo = ctrNo;
	}

	public String getChrgOffNo() {
		return chrgOffNo;
	}

	@XmlElement(name = "ChrgOffNo")
	public void setChrgOffNo(String chrgOffNo) {
		this.chrgOffNo = chrgOffNo;
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

	public String getOprInd() {
		return oprInd;
	}
	
	@XmlElement(name = "OprInd")
	public void setOprInd(String oprInd) {
		this.oprInd = oprInd;
	}
}
