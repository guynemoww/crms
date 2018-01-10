package com.primeton.tsl;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/** 
* @ClassName: ConSecuDueNumVo 
* @Description:合同资产证券化借据编号信息用途: typeNo=A00---资产证券化检查
* @author GIT-kf_xdxt29
* @date 2016-5-24 上午11:09:24 
*  
*/
public class ConSecuDueNumVo extends SuperBosfxRq implements Serializable{ 
	private static final long serialVersionUID = 1L;
	private String rcvDate;//接收日期
	private String conSecuNum;//合同编号或者资产证券化编号
	private String typeNo;//类型编号
	private List<DueNumInfoVo> dueNumInfoVoList;//合同下或资产证券化下的借据信息
	
	public ConSecuDueNumVo(){
		this.setBaseVO(new BaseVO());
	}
	
	public String getRcvDate() {
		return rcvDate;
	}
	
	@XmlElement(name="RcvDate")
	public void setRcvDate(String rcvDate) {
		this.rcvDate = rcvDate;
	}
	
	public String getConSecuNum() {
		return conSecuNum;
	}
	
	@XmlElement(name = "ConSecuNum")
	public void setConSecuNum(String conSecuNum) {
		this.conSecuNum = conSecuNum;
	}
	
	public String getTypeNo() {
		return typeNo;
	}
	
	@XmlElement(name = "TypeNo")
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	
	
	public List<DueNumInfoVo> getDueNumInfoVoList() {
		return dueNumInfoVoList;
	}
	
	@XmlElement(name="DueNumInfoVoList")
	public void setDueNumInfoVoList(List<DueNumInfoVo> dueNumInfoVoList) {
		this.dueNumInfoVoList = dueNumInfoVoList;
	}

	@Override
	public String toString() {
		return "ConSecuDueNumVo[RcvDate="+rcvDate+",ConSecuNum="+conSecuNum+",TypeNo="+typeNo+",DueNumInfoVoList="+dueNumInfoVoList+"]";
	}
	
}
