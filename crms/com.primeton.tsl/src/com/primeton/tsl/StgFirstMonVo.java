package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* @ClassName: StgFirstMonVo 
* @Description: 调整阶段性贷款首次还本期数
* @author GIT-ABC
* @date 2015-6-4 上午09:13:32 
*
 */
public class StgFirstMonVo extends SuperBosfxRq implements Serializable{

	private BaseVO baseVO;
	private String dueNum;//借据编号
	private String telNo;//通知书编号
	private int stgFirstMon;//首次还本期数
	
	
	public StgFirstMonVo(){
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


	


	public int getStgFirstMon() {
		return stgFirstMon;
	}


	public void setStgFirstMon(int stgFirstMon) {
		this.stgFirstMon = stgFirstMon;
	}


	public String getTelNo() {
		return telNo;
	}


	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}


	@Override
	public String toString() {
		return "StgFirstMonVo [baseVO=" + baseVO + ", dueNum=" + dueNum
				+ ", stgFirstMon=" + stgFirstMon + ", telNo=" + telNo + "]";
	}


}
