/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.dataset.biz.impl;

import com.bos.dataset.biz.TbBizPjxxApply;
import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getApplyDetailId <em>ApplyDetailId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getCreateTime <em>CreateTime</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getUpdateTime <em>UpdateTime</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getAmountDetailId <em>AmountDetailId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getCprqc <em>Cprqc</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getCprzh <em>Cprzh</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getSkrqc <em>Skrqc</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getSkrzh <em>Skrzh</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getSkrkhh <em>Skrkhh</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getPjhm <em>Pjhm</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getCurrencyCd <em>CurrencyCd</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getHpje <em>Hpje</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getHpxs <em>Hpxs</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getHpcprq <em>Hpcprq</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getHpdqrq <em>Hpdqrq</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getDfdz <em>Dfdz</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getHtbh <em>Htbh</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getJlzt <em>Jlzt</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getRemitterbankname <em>Remitterbankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getRemitterbankno <em>Remitterbankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getPayeebankname <em>Payeebankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getPayeebankno <em>Payeebankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getAcceptorbankname <em>Acceptorbankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getAcceptorbankno <em>Acceptorbankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getBillid <em>Billid</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getForbidflag <em>Forbidflag</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getSortOrder <em>SortOrder</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizPjxxApplyImpl#getContractId <em>ContractId</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements TbBizPjxxApply;
 */

public class TbBizPjxxApplyImpl extends ExtendedDataObjectImpl implements TbBizPjxxApply {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_APPLYDETAILID = 0;
	public final static int INDEX_CREATETIME = 1;
	public final static int INDEX_UPDATETIME = 2;
	public final static int INDEX_AMOUNTDETAILID = 3;
	public final static int INDEX_CPRQC = 4;
	public final static int INDEX_CPRZH = 5;
	public final static int INDEX_SKRQC = 6;
	public final static int INDEX_SKRZH = 7;
	public final static int INDEX_SKRKHH = 8;
	public final static int INDEX_PJHM = 9;
	public final static int INDEX_CURRENCYCD = 10;
	public final static int INDEX_HPJE = 11;
	public final static int INDEX_HPXS = 12;
	public final static int INDEX_HPCPRQ = 13;
	public final static int INDEX_HPDQRQ = 14;
	public final static int INDEX_DFDZ = 15;
	public final static int INDEX_HTBH = 16;
	public final static int INDEX_JLZT = 17;
	public final static int INDEX_REMITTERBANKNAME = 18;
	public final static int INDEX_REMITTERBANKNO = 19;
	public final static int INDEX_PAYEEBANKNAME = 20;
	public final static int INDEX_PAYEEBANKNO = 21;
	public final static int INDEX_ACCEPTORBANKNAME = 22;
	public final static int INDEX_ACCEPTORBANKNO = 23;
	public final static int INDEX_BILLID = 24;
	public final static int INDEX_FORBIDFLAG = 25;
	public final static int INDEX_SORTORDER = 26;
	public final static int INDEX_CONTRACTID = 27;
	public final static int SDO_PROPERTY_COUNT = 28;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TbBizPjxxApplyImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TbBizPjxxApplyImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>ApplyDetailId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ApplyDetailId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ApplyDetailId</em>' attribute.
	 * @see #setApplyDetailId(java.lang.String)
	 */
	public String getApplyDetailId() {
		return DataUtil.toString(super.getByIndex(INDEX_APPLYDETAILID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getApplyDetailId <em>ApplyDetailId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ApplyDetailId</em>' attribute.
	 * @see #getApplyDetailId()
	 */
	public void setApplyDetailId(String applyDetailId) {
		super.setByIndex(INDEX_APPLYDETAILID, applyDetailId);
	}

	/**
	 * Returns the value of the '<em><b>CreateTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CreateTime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CreateTime</em>' attribute.
	 * @see #setCreateTime(java.util.Date)
	 */
	public Date getCreateTime() {
		return DataUtil.toDate(super.getByIndex(INDEX_CREATETIME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCreateTime <em>CreateTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CreateTime</em>' attribute.
	 * @see #getCreateTime()
	 */
	public void setCreateTime(Date createTime) {
		super.setByIndex(INDEX_CREATETIME, createTime);
	}

	/**
	 * Returns the value of the '<em><b>UpdateTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>UpdateTime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>UpdateTime</em>' attribute.
	 * @see #setUpdateTime(java.util.Date)
	 */
	public Date getUpdateTime() {
		return DataUtil.toDate(super.getByIndex(INDEX_UPDATETIME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getUpdateTime <em>UpdateTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UpdateTime</em>' attribute.
	 * @see #getUpdateTime()
	 */
	public void setUpdateTime(Date updateTime) {
		super.setByIndex(INDEX_UPDATETIME, updateTime);
	}

	/**
	 * Returns the value of the '<em><b>AmountDetailId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AmountDetailId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AmountDetailId</em>' attribute.
	 * @see #setAmountDetailId(java.lang.String)
	 */
	public String getAmountDetailId() {
		return DataUtil.toString(super.getByIndex(INDEX_AMOUNTDETAILID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAmountDetailId <em>AmountDetailId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AmountDetailId</em>' attribute.
	 * @see #getAmountDetailId()
	 */
	public void setAmountDetailId(String amountDetailId) {
		super.setByIndex(INDEX_AMOUNTDETAILID, amountDetailId);
	}

	/**
	 * Returns the value of the '<em><b>Cprqc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cprqc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cprqc</em>' attribute.
	 * @see #setCprqc(java.lang.String)
	 */
	public String getCprqc() {
		return DataUtil.toString(super.getByIndex(INDEX_CPRQC, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCprqc <em>Cprqc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cprqc</em>' attribute.
	 * @see #getCprqc()
	 */
	public void setCprqc(String cprqc) {
		super.setByIndex(INDEX_CPRQC, cprqc);
	}

	/**
	 * Returns the value of the '<em><b>Cprzh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cprzh</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cprzh</em>' attribute.
	 * @see #setCprzh(java.lang.String)
	 */
	public String getCprzh() {
		return DataUtil.toString(super.getByIndex(INDEX_CPRZH, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCprzh <em>Cprzh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cprzh</em>' attribute.
	 * @see #getCprzh()
	 */
	public void setCprzh(String cprzh) {
		super.setByIndex(INDEX_CPRZH, cprzh);
	}

	/**
	 * Returns the value of the '<em><b>Skrqc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skrqc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skrqc</em>' attribute.
	 * @see #setSkrqc(java.lang.String)
	 */
	public String getSkrqc() {
		return DataUtil.toString(super.getByIndex(INDEX_SKRQC, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSkrqc <em>Skrqc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skrqc</em>' attribute.
	 * @see #getSkrqc()
	 */
	public void setSkrqc(String skrqc) {
		super.setByIndex(INDEX_SKRQC, skrqc);
	}

	/**
	 * Returns the value of the '<em><b>Skrzh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skrzh</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skrzh</em>' attribute.
	 * @see #setSkrzh(java.lang.String)
	 */
	public String getSkrzh() {
		return DataUtil.toString(super.getByIndex(INDEX_SKRZH, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSkrzh <em>Skrzh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skrzh</em>' attribute.
	 * @see #getSkrzh()
	 */
	public void setSkrzh(String skrzh) {
		super.setByIndex(INDEX_SKRZH, skrzh);
	}

	/**
	 * Returns the value of the '<em><b>Skrkhh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skrkhh</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skrkhh</em>' attribute.
	 * @see #setSkrkhh(java.lang.String)
	 */
	public String getSkrkhh() {
		return DataUtil.toString(super.getByIndex(INDEX_SKRKHH, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSkrkhh <em>Skrkhh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skrkhh</em>' attribute.
	 * @see #getSkrkhh()
	 */
	public void setSkrkhh(String skrkhh) {
		super.setByIndex(INDEX_SKRKHH, skrkhh);
	}

	/**
	 * Returns the value of the '<em><b>Pjhm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pjhm</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pjhm</em>' attribute.
	 * @see #setPjhm(java.lang.String)
	 */
	public String getPjhm() {
		return DataUtil.toString(super.getByIndex(INDEX_PJHM, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPjhm <em>Pjhm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pjhm</em>' attribute.
	 * @see #getPjhm()
	 */
	public void setPjhm(String pjhm) {
		super.setByIndex(INDEX_PJHM, pjhm);
	}

	/**
	 * Returns the value of the '<em><b>CurrencyCd</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CurrencyCd</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CurrencyCd</em>' attribute.
	 * @see #setCurrencyCd(java.lang.String)
	 */
	public String getCurrencyCd() {
		return DataUtil.toString(super.getByIndex(INDEX_CURRENCYCD, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCurrencyCd <em>CurrencyCd</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CurrencyCd</em>' attribute.
	 * @see #getCurrencyCd()
	 */
	public void setCurrencyCd(String currencyCd) {
		super.setByIndex(INDEX_CURRENCYCD, currencyCd);
	}

	/**
	 * Returns the value of the '<em><b>Hpje</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hpje</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hpje</em>' attribute.
	 * @see #setHpje(java.math.BigDecimal)
	 */
	public BigDecimal getHpje() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_HPJE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getHpje <em>Hpje</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hpje</em>' attribute.
	 * @see #getHpje()
	 */
	public void setHpje(BigDecimal hpje) {
		super.setByIndex(INDEX_HPJE, hpje);
	}

	/**
	 * Returns the value of the '<em><b>Hpxs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hpxs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hpxs</em>' attribute.
	 * @see #setHpxs(java.lang.String)
	 */
	public String getHpxs() {
		return DataUtil.toString(super.getByIndex(INDEX_HPXS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getHpxs <em>Hpxs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hpxs</em>' attribute.
	 * @see #getHpxs()
	 */
	public void setHpxs(String hpxs) {
		super.setByIndex(INDEX_HPXS, hpxs);
	}

	/**
	 * Returns the value of the '<em><b>Hpcprq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hpcprq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hpcprq</em>' attribute.
	 * @see #setHpcprq(java.lang.String)
	 */
	public String getHpcprq() {
		return DataUtil.toString(super.getByIndex(INDEX_HPCPRQ, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getHpcprq <em>Hpcprq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hpcprq</em>' attribute.
	 * @see #getHpcprq()
	 */
	public void setHpcprq(String hpcprq) {
		super.setByIndex(INDEX_HPCPRQ, hpcprq);
	}

	/**
	 * Returns the value of the '<em><b>Hpdqrq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hpdqrq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hpdqrq</em>' attribute.
	 * @see #setHpdqrq(java.lang.String)
	 */
	public String getHpdqrq() {
		return DataUtil.toString(super.getByIndex(INDEX_HPDQRQ, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getHpdqrq <em>Hpdqrq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hpdqrq</em>' attribute.
	 * @see #getHpdqrq()
	 */
	public void setHpdqrq(String hpdqrq) {
		super.setByIndex(INDEX_HPDQRQ, hpdqrq);
	}

	/**
	 * Returns the value of the '<em><b>Dfdz</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dfdz</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dfdz</em>' attribute.
	 * @see #setDfdz(java.lang.String)
	 */
	public String getDfdz() {
		return DataUtil.toString(super.getByIndex(INDEX_DFDZ, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDfdz <em>Dfdz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dfdz</em>' attribute.
	 * @see #getDfdz()
	 */
	public void setDfdz(String dfdz) {
		super.setByIndex(INDEX_DFDZ, dfdz);
	}

	/**
	 * Returns the value of the '<em><b>Htbh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Htbh</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Htbh</em>' attribute.
	 * @see #setHtbh(java.lang.String)
	 */
	public String getHtbh() {
		return DataUtil.toString(super.getByIndex(INDEX_HTBH, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getHtbh <em>Htbh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Htbh</em>' attribute.
	 * @see #getHtbh()
	 */
	public void setHtbh(String htbh) {
		super.setByIndex(INDEX_HTBH, htbh);
	}

	/**
	 * Returns the value of the '<em><b>Jlzt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jlzt</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jlzt</em>' attribute.
	 * @see #setJlzt(java.lang.String)
	 */
	public String getJlzt() {
		return DataUtil.toString(super.getByIndex(INDEX_JLZT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getJlzt <em>Jlzt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jlzt</em>' attribute.
	 * @see #getJlzt()
	 */
	public void setJlzt(String jlzt) {
		super.setByIndex(INDEX_JLZT, jlzt);
	}

	/**
	 * Returns the value of the '<em><b>Remitterbankname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remitterbankname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remitterbankname</em>' attribute.
	 * @see #setRemitterbankname(java.lang.String)
	 */
	public String getRemitterbankname() {
		return DataUtil.toString(super.getByIndex(INDEX_REMITTERBANKNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRemitterbankname <em>Remitterbankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remitterbankname</em>' attribute.
	 * @see #getRemitterbankname()
	 */
	public void setRemitterbankname(String remitterbankname) {
		super.setByIndex(INDEX_REMITTERBANKNAME, remitterbankname);
	}

	/**
	 * Returns the value of the '<em><b>Remitterbankno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remitterbankno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remitterbankno</em>' attribute.
	 * @see #setRemitterbankno(java.lang.String)
	 */
	public String getRemitterbankno() {
		return DataUtil.toString(super.getByIndex(INDEX_REMITTERBANKNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRemitterbankno <em>Remitterbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remitterbankno</em>' attribute.
	 * @see #getRemitterbankno()
	 */
	public void setRemitterbankno(String remitterbankno) {
		super.setByIndex(INDEX_REMITTERBANKNO, remitterbankno);
	}

	/**
	 * Returns the value of the '<em><b>Payeebankname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Payeebankname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Payeebankname</em>' attribute.
	 * @see #setPayeebankname(java.lang.String)
	 */
	public String getPayeebankname() {
		return DataUtil.toString(super.getByIndex(INDEX_PAYEEBANKNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPayeebankname <em>Payeebankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Payeebankname</em>' attribute.
	 * @see #getPayeebankname()
	 */
	public void setPayeebankname(String payeebankname) {
		super.setByIndex(INDEX_PAYEEBANKNAME, payeebankname);
	}

	/**
	 * Returns the value of the '<em><b>Payeebankno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Payeebankno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Payeebankno</em>' attribute.
	 * @see #setPayeebankno(java.lang.String)
	 */
	public String getPayeebankno() {
		return DataUtil.toString(super.getByIndex(INDEX_PAYEEBANKNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPayeebankno <em>Payeebankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Payeebankno</em>' attribute.
	 * @see #getPayeebankno()
	 */
	public void setPayeebankno(String payeebankno) {
		super.setByIndex(INDEX_PAYEEBANKNO, payeebankno);
	}

	/**
	 * Returns the value of the '<em><b>Acceptorbankname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Acceptorbankname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Acceptorbankname</em>' attribute.
	 * @see #setAcceptorbankname(java.lang.String)
	 */
	public String getAcceptorbankname() {
		return DataUtil.toString(super.getByIndex(INDEX_ACCEPTORBANKNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAcceptorbankname <em>Acceptorbankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Acceptorbankname</em>' attribute.
	 * @see #getAcceptorbankname()
	 */
	public void setAcceptorbankname(String acceptorbankname) {
		super.setByIndex(INDEX_ACCEPTORBANKNAME, acceptorbankname);
	}

	/**
	 * Returns the value of the '<em><b>Acceptorbankno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Acceptorbankno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Acceptorbankno</em>' attribute.
	 * @see #setAcceptorbankno(java.lang.String)
	 */
	public String getAcceptorbankno() {
		return DataUtil.toString(super.getByIndex(INDEX_ACCEPTORBANKNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAcceptorbankno <em>Acceptorbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Acceptorbankno</em>' attribute.
	 * @see #getAcceptorbankno()
	 */
	public void setAcceptorbankno(String acceptorbankno) {
		super.setByIndex(INDEX_ACCEPTORBANKNO, acceptorbankno);
	}

	/**
	 * Returns the value of the '<em><b>Billid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billid</em>' attribute.
	 * @see #setBillid(long)
	 */
	public long getBillid() {
		return DataUtil.toLong(super.getByIndex(INDEX_BILLID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillid <em>Billid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billid</em>' attribute.
	 * @see #getBillid()
	 */
	public void setBillid(long billid) {
		super.setByIndex(INDEX_BILLID, billid);
	}

	/**
	 * Returns the value of the '<em><b>Forbidflag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forbidflag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forbidflag</em>' attribute.
	 * @see #setForbidflag(java.lang.String)
	 */
	public String getForbidflag() {
		return DataUtil.toString(super.getByIndex(INDEX_FORBIDFLAG, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getForbidflag <em>Forbidflag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forbidflag</em>' attribute.
	 * @see #getForbidflag()
	 */
	public void setForbidflag(String forbidflag) {
		super.setByIndex(INDEX_FORBIDFLAG, forbidflag);
	}

	/**
	 * Returns the value of the '<em><b>SortOrder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SortOrder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SortOrder</em>' attribute.
	 * @see #setSortOrder(long)
	 */
	public long getSortOrder() {
		return DataUtil.toLong(super.getByIndex(INDEX_SORTORDER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSortOrder <em>SortOrder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SortOrder</em>' attribute.
	 * @see #getSortOrder()
	 */
	public void setSortOrder(long sortOrder) {
		super.setByIndex(INDEX_SORTORDER, sortOrder);
	}

	/**
	 * Returns the value of the '<em><b>ContractId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ContractId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ContractId</em>' attribute.
	 * @see #setContractId(java.lang.String)
	 */
	public String getContractId() {
		return DataUtil.toString(super.getByIndex(INDEX_CONTRACTID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getContractId <em>ContractId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ContractId</em>' attribute.
	 * @see #getContractId()
	 */
	public void setContractId(String contractId) {
		super.setByIndex(INDEX_CONTRACTID, contractId);
	}


}