package com.bos.gjService;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *  国结调用信贷放/还款结果查询接口---请求对象
 * @author shendl
 *
 */
public class G005RequestBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6123326610760601461L;
	private String CXDYBZ;//查询打印标志
	private String QANTRQ;//前台日期
	private String QANTLS;//前台流水号
	private String QISHBS;//起始笔数
	private String CXUNBS;//查询笔数
	private String dueNum;//借据编号
	private BigDecimal happenAmount;//发生金额
	private String happenType;//发生类型
	
	public String getCXDYBZ() {
		return CXDYBZ;
	}
	public void setCXDYBZ(String cXDYBZ) {
		CXDYBZ = cXDYBZ;
	}
	public String getQANTRQ() {
		return QANTRQ;
	}
	public void setQANTRQ(String qANTRQ) {
		QANTRQ = qANTRQ;
	}
	public String getQANTLS() {
		return QANTLS;
	}
	public void setQANTLS(String qANTLS) {
		QANTLS = qANTLS;
	}
	public String getQISHBS() {
		return QISHBS;
	}
	public void setQISHBS(String qISHBS) {
		QISHBS = qISHBS;
	}
	public String getCXUNBS() {
		return CXUNBS;
	}
	public void setCXUNBS(String cXUNBS) {
		CXUNBS = cXUNBS;
	}
	public String getDueNum() {
		return dueNum;
	}
	public void setDueNum(String dueNum) {
		this.dueNum = dueNum;
	}
	public BigDecimal getHappenAmount() {
		return happenAmount;
	}
	public void setHappenAmount(BigDecimal happenAmount) {
		this.happenAmount = happenAmount;
	}
	public String getHappenType() {
		return happenType;
	}
	public void setHappenType(String happenType) {
		this.happenType = happenType;
	}
}
