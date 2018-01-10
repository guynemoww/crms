package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* @ClassName: ChgLengthVo 
* @Description: 期限变更
* @author GIT-ABC
* @date 2015-6-4 上午09:13:32 
*
 */
public class ChgLengthVo extends SuperBosfxRq implements Serializable{

	private BaseVO baseVO;
	private String dueNum;//借据编号
	private String telNo;//通知书编号
	private String endDate;//贷款新到期日
	private String busCod;//贷款新业务别
	private String discEndDate;//新贴息止期
	
	
	public ChgLengthVo(){
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
	 * @return endDate 
	 */
	public String getEndDate() {
		return endDate;
	}


	/** 
	 * @param endDate 要设置的 endDate 
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	/** 
	 * @return busCod 
	 */
	public String getBusCod() {
		return busCod;
	}


	/** 
	 * @param busCod 要设置的 busCod 
	 */
	public void setBusCod(String busCod) {
		this.busCod = busCod;
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
		return "ChgLengthVo [baseVO=" + baseVO + ", busCod=" + busCod
				+ ", discEndDate=" + discEndDate + ", dueNum=" + dueNum
				+ ", endDate=" + endDate + ", telNo=" + telNo + "]";
	}

}
