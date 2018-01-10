package com.primeton.tsl;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

/** 
* @ClassName: DueNumInfoVo 
* @Description:借据信息
* @author GIT-kf_xdxt29
* @date 2016-5-24 上午11:13:14 
*  
*/
public class DueNumInfoVo implements Serializable{ 
	private static final long serialVersionUID = 1L;
	
	private String rcvDate;//接收日期
	private String dueNum;//借据编号

	public String getDueNum() {
		return dueNum;
	}
	
	public String getRcvDate() {
		return rcvDate;
	}
	
	@XmlElement(name="RcvDate")
	public void setRcvDate(String rcvDate) {
		this.rcvDate = rcvDate;
	}
	
	@XmlElement(name = "DueNum")
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	
	@Override
	public String toString() {
		return "DueNumInfoVo [DueNum="+ dueNum+",RcvDate"+rcvDate+"]";
	}
}
