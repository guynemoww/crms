package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
* @ClassName: cancelDelVo 
* @Description: 删除还款计划 
* @author GIT-dengchao
* @date 2015-6-11 下午05:23:54 
*
 */
public class CancelDelVo extends SuperBosfxRq implements Serializable{
	private static final long serialVersionUID = -6896917124251755876L;
	
	private String telNo;//通知书编号
	private String dueNum;//借据编号
	private String busDate;//营业日期
	private String delType;//删除类型
	
	public String getBusDate() {
		return busDate;
	}
	@XmlElement(name="BusDate")
	public void setBusDate(String busDate) {
		this.busDate = busDate;
	}

	public String getDelType() {
		return delType;
	}
	@XmlElement(name="DelType")
	public void setDelType(String delType) {
		this.delType = delType;
	}

	public CancelDelVo(){
		this.setBaseVO(new BaseVO());
	}
	
	public String getDueNum() {
		return dueNum;
	}

	@XmlElement(name="DueNum")
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}

	public String getTelNo() {
		return telNo;
	}

	@XmlElement(name="TelNo")
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	
	/* (非 Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "cancelDelVo [" + "dueNum=" + dueNum 
				+  ", delType="
				+ delType + ", busDate="
				+ busDate + ", telNo=" + telNo + "]";
	}

	

}
