package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* @ClassName: ChgDiscEndVo 
* @Description: 调整贴息到期日
* @author GIT-ABC
* @date 2015-6-4 上午09:13:32 
*
 */
public class ChgDiscEndVo extends SuperBosfxRq implements Serializable{

	private BaseVO baseVO;
	private String dueNum;//借据编号
	private String telNo;//通知书编号
	private String discEndDate;//贴息到期日
	
	
	public ChgDiscEndVo(){
		this.baseVO = new BaseVO();
	}


	/** 
	 * @return baseVO 
	 */
	public BaseVO getBaseVO() {
		return baseVO;
	}


	/** 
	 * @param baseVO 要设置的 baseVO 
	 */
	public void setBaseVO(BaseVO baseVO) {
		this.baseVO = baseVO;
	}


	/** 
	 * @return dueNum 
	 */
	public String getDueNum() {
		return dueNum;
	}


	/** 
	 * @param dueNum 要设置的 dueNum 
	 */
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}


	/** 
	 * @return discEndDate 
	 */
	public String getDiscEndDate() {
		return discEndDate;
	}


	/** 
	 * @param discEndDate 要设置的 discEndDate 
	 */
	public void setDiscEndDate(String discEndDate) {
		this.discEndDate = discEndDate;
	}


	public String getTelNo() {
		return telNo;
	}


	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}


	@Override
	public String toString() {
		return "ChgDiscEndVo [baseVO=" + baseVO + ", discEndDate="
				+ discEndDate + ", dueNum=" + dueNum + ", telNo=" + telNo + "]";
	}



}
