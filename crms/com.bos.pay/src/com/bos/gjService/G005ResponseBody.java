package com.bos.gjService;

import java.io.Serializable;

/**
 *  国结调用信贷放/还款结果查询接口---响应对象体
 * 
 */
public class G005ResponseBody implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4410581776596665860L;
	private String JIOYRQ;// 交易日期
	private String JIOYSJ;// 交易时间
	private String JIAOYM;// 交易码
	private String YNGYJG;// 营业机构
	private String ZHNGJG;// 帐务机构号
	private String QUDAOO;// 渠道
	private String ZHNGDH;// 终端号
	private String JIO1GY;// 交易柜员
	private String SHOQGY;// 授权柜员
	private String GUIYLS;// 柜员流水号
	private String JYLSLX;// 交易流水类型
	private String QANTRQ;// 前台日期
	private String QTAILS;// 前台流水
	private String DLIYWH;// 代理业务号
	private String JYDZLX;// 交易对帐类型
	private String JIOYMC;// 交易名称
	private String BCHZBZ;// 被冲正标志
	private String YNGYLS;// 原柜员流水号
	private String ZHUJRQ;// 主机日期
	private String SHJIAN;// 时间
	private String POSZBH;// POS终端编号
	private String FUHERQ;// 复核日期
	private String FHGYLS;// 申请复核流水号
	
	private String STATUS;//交易状态

	public String getJIOYRQ() {
		return JIOYRQ;
	}

	public void setJIOYRQ(String jIOYRQ) {
		JIOYRQ = jIOYRQ;
	}

	public String getJIOYSJ() {
		return JIOYSJ;
	}

	public void setJIOYSJ(String jIOYSJ) {
		JIOYSJ = jIOYSJ;
	}

	public String getJIAOYM() {
		return JIAOYM;
	}

	public void setJIAOYM(String jIAOYM) {
		JIAOYM = jIAOYM;
	}

	public String getYNGYJG() {
		return YNGYJG;
	}

	public void setYNGYJG(String yNGYJG) {
		YNGYJG = yNGYJG;
	}

	public String getZHNGJG() {
		return ZHNGJG;
	}

	public void setZHNGJG(String zHNGJG) {
		ZHNGJG = zHNGJG;
	}


	public String getQUDAOO() {
		return QUDAOO;
	}

	public void setQUDAOO(String qUDAOO) {
		QUDAOO = qUDAOO;
	}

	public String getZHNGDH() {
		return ZHNGDH;
	}

	public void setZHNGDH(String zHNGDH) {
		ZHNGDH = zHNGDH;
	}

	public String getJIO1GY() {
		return JIO1GY;
	}

	public void setJIO1GY(String jIO1GY) {
		JIO1GY = jIO1GY;
	}

	public String getSHOQGY() {
		return SHOQGY;
	}

	public void setSHOQGY(String sHOQGY) {
		SHOQGY = sHOQGY;
	}

	public String getGUIYLS() {
		return GUIYLS;
	}

	public void setGUIYLS(String gUIYLS) {
		GUIYLS = gUIYLS;
	}

	public String getJYLSLX() {
		return JYLSLX;
	}

	public void setJYLSLX(String jYLSLX) {
		JYLSLX = jYLSLX;
	}

	public String getQANTRQ() {
		return QANTRQ;
	}

	public void setQANTRQ(String qANTRQ) {
		QANTRQ = qANTRQ;
	}

	public String getQTAILS() {
		return QTAILS;
	}

	public void setQTAILS(String qTAILS) {
		QTAILS = qTAILS;
	}

	public String getDLIYWH() {
		return DLIYWH;
	}

	public void setDLIYWH(String dLIYWH) {
		DLIYWH = dLIYWH;
	}

	public String getJYDZLX() {
		return JYDZLX;
	}

	public void setJYDZLX(String jYDZLX) {
		JYDZLX = jYDZLX;
	}

	public String getJIOYMC() {
		return JIOYMC;
	}

	public void setJIOYMC(String jIOYMC) {
		JIOYMC = jIOYMC;
	}

	public String getBCHZBZ() {
		return BCHZBZ;
	}

	public void setBCHZBZ(String bCHZBZ) {
		BCHZBZ = bCHZBZ;
	}

	public String getYNGYLS() {
		return YNGYLS;
	}

	public void setYNGYLS(String yNGYLS) {
		YNGYLS = yNGYLS;
	}

	public String getZHUJRQ() {
		return ZHUJRQ;
	}

	public void setZHUJRQ(String zHUJRQ) {
		ZHUJRQ = zHUJRQ;
	}

	public String getSHJIAN() {
		return SHJIAN;
	}

	public void setSHJIAN(String sHJIAN) {
		SHJIAN = sHJIAN;
	}

	public String getPOSZBH() {
		return POSZBH;
	}

	public void setPOSZBH(String pOSZBH) {
		POSZBH = pOSZBH;
	}

	public String getFUHERQ() {
		return FUHERQ;
	}

	public void setFUHERQ(String fUHERQ) {
		FUHERQ = fUHERQ;
	}

	public String getFHGYLS() {
		return FHGYLS;
	}

	public void setFHGYLS(String fHGYLS) {
		FHGYLS = fHGYLS;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	
}
