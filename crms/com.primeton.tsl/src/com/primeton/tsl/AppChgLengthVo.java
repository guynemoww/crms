package com.primeton.tsl;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* @ClassName: AppChgLengthVo 
* @Description: 还款方式变更
* @author GIT-ABC
* @date 2015-6-4 上午09:13:32 
*
 */
public class AppChgLengthVo extends SuperBosfxRq implements Serializable{

	private BaseVO baseVO;
	private String dueNum;//借据编号
	private String telNo;//通知书编号
	private String busCod;//贷款业务别
	private String begDate;//展期起始日期
	private String endDate;//展期到期日期
	private BigDecimal norItrRate;//展期后正常利率
	private BigDecimal delItrRate;//展期后罚息利率
	private String discEndDate;//贴息止期
	private String sts;//下发还本计划标志
	
	public AppChgLengthVo(){
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
	 * @return begDate 
	 */
	public String getBegDate() {
		return begDate;
	}

	/** 
	 * @param begDate 要设置的 begDate 
	 */
	public void setBegDate(String begDate) {
		this.begDate = begDate;
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
	 * @return norItrRate 
	 */
	public BigDecimal getNorItrRate() {
		return norItrRate;
	}

	/** 
	 * @param norItrRate 要设置的 norItrRate 
	 */
	public void setNorItrRate(BigDecimal norItrRate) {
		this.norItrRate = norItrRate;
	}

	/** 
	 * @return delItrRate 
	 */
	public BigDecimal getDelItrRate() {
		return delItrRate;
	}

	/** 
	 * @param delItrRate 要设置的 delItrRate 
	 */
	public void setDelItrRate(BigDecimal delItrRate) {
		this.delItrRate = delItrRate;
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

	/** 
	 * @return sts 
	 */
	public String getSts() {
		return sts;
	}

	/** 
	 * @param sts 要设置的 sts 
	 */
	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	@Override
	public String toString() {
		return "AppChgLengthVo [baseVO=" + baseVO + ", begDate=" + begDate
				+ ", busCod=" + busCod + ", delItrRate=" + delItrRate
				+ ", discEndDate=" + discEndDate + ", dueNum=" + dueNum
				+ ", endDate=" + endDate + ", norItrRate=" + norItrRate
				+ ", sts=" + sts + ", telNo=" + telNo + "]";
	}

	
}
