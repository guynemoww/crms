/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.dataset.biz.impl;

import com.bos.dataset.biz.TbBizTxxxApply;
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
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getApplyDetailId <em>ApplyDetailId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getLoanid <em>Loanid</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getContractId <em>ContractId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillbatch <em>Billbatch</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillno <em>Billno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getCurrsign <em>Currsign</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillamt <em>Billamt</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillbegindate <em>Billbegindate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillenddate <em>Billenddate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBilladdtype <em>Billaddtype</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getTakeoutacname <em>Takeoutacname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getTakeoutacbankno <em>Takeoutacbankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getTakeoutacbankname <em>Takeoutacbankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getTakeoutacno <em>Takeoutacno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBenename <em>Benename</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBenebankno <em>Benebankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBenebankname <em>Benebankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBeneno <em>Beneno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillbanktype <em>Billbanktype</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillbankno <em>Billbankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillbankname <em>Billbankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getDirefrontname <em>Direfrontname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getDiscbenum <em>Discbenum</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getContimageno <em>Contimageno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getQueryresult <em>Queryresult</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getReviewresult <em>Reviewresult</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getReviewoption <em>Reviewoption</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getReviewer <em>Reviewer</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getReviewerorg <em>Reviewerorg</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getLastchanperson <em>Lastchanperson</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getLastchanbankid <em>Lastchanbankid</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getLastchandate <em>Lastchandate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBilltype <em>Billtype</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillmodel <em>Billmodel</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getAdjustnum <em>Adjustnum</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBilltrueenddate <em>Billtrueenddate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getInterate <em>Interate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getOnlinemark <em>Onlinemark</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillacno <em>Billacno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getForbidflag <em>Forbidflag</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getBillacname <em>Billacname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getCreateTime <em>CreateTime</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getUpdateTime <em>UpdateTime</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getAmountDetailId <em>AmountDetailId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getIfSameCity <em>IfSameCity</em>}</li>
 *   <li>{@link com.bos.dataset.biz.impl.TbBizTxxxApplyImpl#getSortOrder <em>SortOrder</em>}</li> 
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements TbBizTxxxApply;
 */

public class TbBizTxxxApplyImpl extends ExtendedDataObjectImpl implements TbBizTxxxApply {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_APPLYDETAILID = 0;
	public final static int INDEX_LOANID = 1;
	public final static int INDEX_CONTRACTID = 2;
	public final static int INDEX_BILLBATCH = 3;
	public final static int INDEX_BILLNO = 4;
	public final static int INDEX_CURRSIGN = 5;
	public final static int INDEX_BILLAMT = 6;
	public final static int INDEX_BILLBEGINDATE = 7;
	public final static int INDEX_BILLENDDATE = 8;
	public final static int INDEX_BILLADDTYPE = 9;
	public final static int INDEX_TAKEOUTACNAME = 10;
	public final static int INDEX_TAKEOUTACBANKNO = 11;
	public final static int INDEX_TAKEOUTACBANKNAME = 12;
	public final static int INDEX_TAKEOUTACNO = 13;
	public final static int INDEX_BENENAME = 14;
	public final static int INDEX_BENEBANKNO = 15;
	public final static int INDEX_BENEBANKNAME = 16;
	public final static int INDEX_BENENO = 17;
	public final static int INDEX_BILLBANKTYPE = 18;
	public final static int INDEX_BILLBANKNO = 19;
	public final static int INDEX_BILLBANKNAME = 20;
	public final static int INDEX_DIREFRONTNAME = 21;
	public final static int INDEX_DISCBENUM = 22;
	public final static int INDEX_CONTIMAGENO = 23;
	public final static int INDEX_QUERYRESULT = 24;
	public final static int INDEX_REVIEWRESULT = 25;
	public final static int INDEX_REVIEWOPTION = 26;
	public final static int INDEX_REVIEWER = 27;
	public final static int INDEX_REVIEWERORG = 28;
	public final static int INDEX_LASTCHANPERSON = 29;
	public final static int INDEX_LASTCHANBANKID = 30;
	public final static int INDEX_LASTCHANDATE = 31;
	public final static int INDEX_BILLTYPE = 32;
	public final static int INDEX_BILLMODEL = 33;
	public final static int INDEX_ADJUSTNUM = 34;
	public final static int INDEX_BILLTRUEENDDATE = 35;
	public final static int INDEX_INTERATE = 36;
	public final static int INDEX_ONLINEMARK = 37;
	public final static int INDEX_BILLACNO = 38;
	public final static int INDEX_FORBIDFLAG = 39;
	public final static int INDEX_BILLACNAME = 40;
	public final static int INDEX_CREATETIME = 41;
	public final static int INDEX_UPDATETIME = 42;
	public final static int INDEX_AMOUNTDETAILID = 43;
	public final static int INDEX_IFSAMECITY = 44;
	public final static int INDEX_SORTORDER = 45;
	public final static int SDO_PROPERTY_COUNT = 46;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TbBizTxxxApplyImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TbBizTxxxApplyImpl(Type type) {
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
	 * Returns the value of the '<em><b>Loanid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loanid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loanid</em>' attribute.
	 * @see #setLoanid(java.lang.String)
	 */
	public String getLoanid() {
		return DataUtil.toString(super.getByIndex(INDEX_LOANID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLoanid <em>Loanid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loanid</em>' attribute.
	 * @see #getLoanid()
	 */
	public void setLoanid(String loanid) {
		super.setByIndex(INDEX_LOANID, loanid);
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

	/**
	 * Returns the value of the '<em><b>Billbatch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billbatch</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billbatch</em>' attribute.
	 * @see #setBillbatch(java.lang.String)
	 */
	public String getBillbatch() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLBATCH, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillbatch <em>Billbatch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbatch</em>' attribute.
	 * @see #getBillbatch()
	 */
	public void setBillbatch(String billbatch) {
		super.setByIndex(INDEX_BILLBATCH, billbatch);
	}

	/**
	 * Returns the value of the '<em><b>Billno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billno</em>' attribute.
	 * @see #setBillno(java.lang.String)
	 */
	public String getBillno() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillno <em>Billno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billno</em>' attribute.
	 * @see #getBillno()
	 */
	public void setBillno(String billno) {
		super.setByIndex(INDEX_BILLNO, billno);
	}

	/**
	 * Returns the value of the '<em><b>Currsign</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Currsign</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Currsign</em>' attribute.
	 * @see #setCurrsign(java.lang.String)
	 */
	public String getCurrsign() {
		return DataUtil.toString(super.getByIndex(INDEX_CURRSIGN, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCurrsign <em>Currsign</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Currsign</em>' attribute.
	 * @see #getCurrsign()
	 */
	public void setCurrsign(String currsign) {
		super.setByIndex(INDEX_CURRSIGN, currsign);
	}

	/**
	 * Returns the value of the '<em><b>Billamt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billamt</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billamt</em>' attribute.
	 * @see #setBillamt(java.math.BigDecimal)
	 */
	public BigDecimal getBillamt() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_BILLAMT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillamt <em>Billamt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billamt</em>' attribute.
	 * @see #getBillamt()
	 */
	public void setBillamt(BigDecimal billamt) {
		super.setByIndex(INDEX_BILLAMT, billamt);
	}

	/**
	 * Returns the value of the '<em><b>Billbegindate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billbegindate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billbegindate</em>' attribute.
	 * @see #setBillbegindate(java.lang.String)
	 */
	public String getBillbegindate() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLBEGINDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillbegindate <em>Billbegindate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbegindate</em>' attribute.
	 * @see #getBillbegindate()
	 */
	public void setBillbegindate(String billbegindate) {
		super.setByIndex(INDEX_BILLBEGINDATE, billbegindate);
	}

	/**
	 * Returns the value of the '<em><b>Billenddate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billenddate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billenddate</em>' attribute.
	 * @see #setBillenddate(java.lang.String)
	 */
	public String getBillenddate() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLENDDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillenddate <em>Billenddate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billenddate</em>' attribute.
	 * @see #getBillenddate()
	 */
	public void setBillenddate(String billenddate) {
		super.setByIndex(INDEX_BILLENDDATE, billenddate);
	}

	/**
	 * Returns the value of the '<em><b>Billaddtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billaddtype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billaddtype</em>' attribute.
	 * @see #setBilladdtype(java.lang.String)
	 */
	public String getBilladdtype() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLADDTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBilladdtype <em>Billaddtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billaddtype</em>' attribute.
	 * @see #getBilladdtype()
	 */
	public void setBilladdtype(String billaddtype) {
		super.setByIndex(INDEX_BILLADDTYPE, billaddtype);
	}

	/**
	 * Returns the value of the '<em><b>Takeoutacname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Takeoutacname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Takeoutacname</em>' attribute.
	 * @see #setTakeoutacname(java.lang.String)
	 */
	public String getTakeoutacname() {
		return DataUtil.toString(super.getByIndex(INDEX_TAKEOUTACNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getTakeoutacname <em>Takeoutacname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Takeoutacname</em>' attribute.
	 * @see #getTakeoutacname()
	 */
	public void setTakeoutacname(String takeoutacname) {
		super.setByIndex(INDEX_TAKEOUTACNAME, takeoutacname);
	}

	/**
	 * Returns the value of the '<em><b>Takeoutacbankno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Takeoutacbankno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Takeoutacbankno</em>' attribute.
	 * @see #setTakeoutacbankno(java.lang.String)
	 */
	public String getTakeoutacbankno() {
		return DataUtil.toString(super.getByIndex(INDEX_TAKEOUTACBANKNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getTakeoutacbankno <em>Takeoutacbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Takeoutacbankno</em>' attribute.
	 * @see #getTakeoutacbankno()
	 */
	public void setTakeoutacbankno(String takeoutacbankno) {
		super.setByIndex(INDEX_TAKEOUTACBANKNO, takeoutacbankno);
	}

	/**
	 * Returns the value of the '<em><b>Takeoutacbankname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Takeoutacbankname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Takeoutacbankname</em>' attribute.
	 * @see #setTakeoutacbankname(java.lang.String)
	 */
	public String getTakeoutacbankname() {
		return DataUtil.toString(super.getByIndex(INDEX_TAKEOUTACBANKNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getTakeoutacbankname <em>Takeoutacbankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Takeoutacbankname</em>' attribute.
	 * @see #getTakeoutacbankname()
	 */
	public void setTakeoutacbankname(String takeoutacbankname) {
		super.setByIndex(INDEX_TAKEOUTACBANKNAME, takeoutacbankname);
	}

	/**
	 * Returns the value of the '<em><b>Takeoutacno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Takeoutacno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Takeoutacno</em>' attribute.
	 * @see #setTakeoutacno(java.lang.String)
	 */
	public String getTakeoutacno() {
		return DataUtil.toString(super.getByIndex(INDEX_TAKEOUTACNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getTakeoutacno <em>Takeoutacno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Takeoutacno</em>' attribute.
	 * @see #getTakeoutacno()
	 */
	public void setTakeoutacno(String takeoutacno) {
		super.setByIndex(INDEX_TAKEOUTACNO, takeoutacno);
	}

	/**
	 * Returns the value of the '<em><b>Benename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Benename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Benename</em>' attribute.
	 * @see #setBenename(java.lang.String)
	 */
	public String getBenename() {
		return DataUtil.toString(super.getByIndex(INDEX_BENENAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBenename <em>Benename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Benename</em>' attribute.
	 * @see #getBenename()
	 */
	public void setBenename(String benename) {
		super.setByIndex(INDEX_BENENAME, benename);
	}

	/**
	 * Returns the value of the '<em><b>Benebankno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Benebankno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Benebankno</em>' attribute.
	 * @see #setBenebankno(java.lang.String)
	 */
	public String getBenebankno() {
		return DataUtil.toString(super.getByIndex(INDEX_BENEBANKNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBenebankno <em>Benebankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Benebankno</em>' attribute.
	 * @see #getBenebankno()
	 */
	public void setBenebankno(String benebankno) {
		super.setByIndex(INDEX_BENEBANKNO, benebankno);
	}

	/**
	 * Returns the value of the '<em><b>Benebankname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Benebankname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Benebankname</em>' attribute.
	 * @see #setBenebankname(java.lang.String)
	 */
	public String getBenebankname() {
		return DataUtil.toString(super.getByIndex(INDEX_BENEBANKNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBenebankname <em>Benebankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Benebankname</em>' attribute.
	 * @see #getBenebankname()
	 */
	public void setBenebankname(String benebankname) {
		super.setByIndex(INDEX_BENEBANKNAME, benebankname);
	}

	/**
	 * Returns the value of the '<em><b>Beneno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Beneno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Beneno</em>' attribute.
	 * @see #setBeneno(java.lang.String)
	 */
	public String getBeneno() {
		return DataUtil.toString(super.getByIndex(INDEX_BENENO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBeneno <em>Beneno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Beneno</em>' attribute.
	 * @see #getBeneno()
	 */
	public void setBeneno(String beneno) {
		super.setByIndex(INDEX_BENENO, beneno);
	}

	/**
	 * Returns the value of the '<em><b>Billbanktype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billbanktype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billbanktype</em>' attribute.
	 * @see #setBillbanktype(java.lang.String)
	 */
	public String getBillbanktype() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLBANKTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillbanktype <em>Billbanktype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbanktype</em>' attribute.
	 * @see #getBillbanktype()
	 */
	public void setBillbanktype(String billbanktype) {
		super.setByIndex(INDEX_BILLBANKTYPE, billbanktype);
	}

	/**
	 * Returns the value of the '<em><b>Billbankno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billbankno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billbankno</em>' attribute.
	 * @see #setBillbankno(java.lang.String)
	 */
	public String getBillbankno() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLBANKNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillbankno <em>Billbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbankno</em>' attribute.
	 * @see #getBillbankno()
	 */
	public void setBillbankno(String billbankno) {
		super.setByIndex(INDEX_BILLBANKNO, billbankno);
	}

	/**
	 * Returns the value of the '<em><b>Billbankname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billbankname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billbankname</em>' attribute.
	 * @see #setBillbankname(java.lang.String)
	 */
	public String getBillbankname() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLBANKNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillbankname <em>Billbankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbankname</em>' attribute.
	 * @see #getBillbankname()
	 */
	public void setBillbankname(String billbankname) {
		super.setByIndex(INDEX_BILLBANKNAME, billbankname);
	}

	/**
	 * Returns the value of the '<em><b>Direfrontname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direfrontname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direfrontname</em>' attribute.
	 * @see #setDirefrontname(java.lang.String)
	 */
	public String getDirefrontname() {
		return DataUtil.toString(super.getByIndex(INDEX_DIREFRONTNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDirefrontname <em>Direfrontname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direfrontname</em>' attribute.
	 * @see #getDirefrontname()
	 */
	public void setDirefrontname(String direfrontname) {
		super.setByIndex(INDEX_DIREFRONTNAME, direfrontname);
	}

	/**
	 * Returns the value of the '<em><b>Discbenum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Discbenum</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Discbenum</em>' attribute.
	 * @see #setDiscbenum(int)
	 */
	public int getDiscbenum() {
		return DataUtil.toInt(super.getByIndex(INDEX_DISCBENUM, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDiscbenum <em>Discbenum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Discbenum</em>' attribute.
	 * @see #getDiscbenum()
	 */
	public void setDiscbenum(int discbenum) {
		super.setByIndex(INDEX_DISCBENUM, discbenum);
	}

	/**
	 * Returns the value of the '<em><b>Contimageno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contimageno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contimageno</em>' attribute.
	 * @see #setContimageno(java.lang.String)
	 */
	public String getContimageno() {
		return DataUtil.toString(super.getByIndex(INDEX_CONTIMAGENO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getContimageno <em>Contimageno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contimageno</em>' attribute.
	 * @see #getContimageno()
	 */
	public void setContimageno(String contimageno) {
		super.setByIndex(INDEX_CONTIMAGENO, contimageno);
	}

	/**
	 * Returns the value of the '<em><b>Queryresult</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Queryresult</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Queryresult</em>' attribute.
	 * @see #setQueryresult(java.lang.String)
	 */
	public String getQueryresult() {
		return DataUtil.toString(super.getByIndex(INDEX_QUERYRESULT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getQueryresult <em>Queryresult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Queryresult</em>' attribute.
	 * @see #getQueryresult()
	 */
	public void setQueryresult(String queryresult) {
		super.setByIndex(INDEX_QUERYRESULT, queryresult);
	}

	/**
	 * Returns the value of the '<em><b>Reviewresult</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reviewresult</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reviewresult</em>' attribute.
	 * @see #setReviewresult(java.lang.String)
	 */
	public String getReviewresult() {
		return DataUtil.toString(super.getByIndex(INDEX_REVIEWRESULT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getReviewresult <em>Reviewresult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reviewresult</em>' attribute.
	 * @see #getReviewresult()
	 */
	public void setReviewresult(String reviewresult) {
		super.setByIndex(INDEX_REVIEWRESULT, reviewresult);
	}

	/**
	 * Returns the value of the '<em><b>Reviewoption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reviewoption</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reviewoption</em>' attribute.
	 * @see #setReviewoption(java.lang.String)
	 */
	public String getReviewoption() {
		return DataUtil.toString(super.getByIndex(INDEX_REVIEWOPTION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getReviewoption <em>Reviewoption</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reviewoption</em>' attribute.
	 * @see #getReviewoption()
	 */
	public void setReviewoption(String reviewoption) {
		super.setByIndex(INDEX_REVIEWOPTION, reviewoption);
	}

	/**
	 * Returns the value of the '<em><b>Reviewer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reviewer</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reviewer</em>' attribute.
	 * @see #setReviewer(java.lang.String)
	 */
	public String getReviewer() {
		return DataUtil.toString(super.getByIndex(INDEX_REVIEWER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getReviewer <em>Reviewer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reviewer</em>' attribute.
	 * @see #getReviewer()
	 */
	public void setReviewer(String reviewer) {
		super.setByIndex(INDEX_REVIEWER, reviewer);
	}

	/**
	 * Returns the value of the '<em><b>Reviewerorg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reviewerorg</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reviewerorg</em>' attribute.
	 * @see #setReviewerorg(java.lang.String)
	 */
	public String getReviewerorg() {
		return DataUtil.toString(super.getByIndex(INDEX_REVIEWERORG, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getReviewerorg <em>Reviewerorg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reviewerorg</em>' attribute.
	 * @see #getReviewerorg()
	 */
	public void setReviewerorg(String reviewerorg) {
		super.setByIndex(INDEX_REVIEWERORG, reviewerorg);
	}

	/**
	 * Returns the value of the '<em><b>Lastchanperson</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lastchanperson</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lastchanperson</em>' attribute.
	 * @see #setLastchanperson(java.lang.String)
	 */
	public String getLastchanperson() {
		return DataUtil.toString(super.getByIndex(INDEX_LASTCHANPERSON, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLastchanperson <em>Lastchanperson</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastchanperson</em>' attribute.
	 * @see #getLastchanperson()
	 */
	public void setLastchanperson(String lastchanperson) {
		super.setByIndex(INDEX_LASTCHANPERSON, lastchanperson);
	}

	/**
	 * Returns the value of the '<em><b>Lastchanbankid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lastchanbankid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lastchanbankid</em>' attribute.
	 * @see #setLastchanbankid(java.lang.String)
	 */
	public String getLastchanbankid() {
		return DataUtil.toString(super.getByIndex(INDEX_LASTCHANBANKID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLastchanbankid <em>Lastchanbankid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastchanbankid</em>' attribute.
	 * @see #getLastchanbankid()
	 */
	public void setLastchanbankid(String lastchanbankid) {
		super.setByIndex(INDEX_LASTCHANBANKID, lastchanbankid);
	}

	/**
	 * Returns the value of the '<em><b>Lastchandate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lastchandate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lastchandate</em>' attribute.
	 * @see #setLastchandate(java.util.Date)
	 */
	public Date getLastchandate() {
		return DataUtil.toDate(super.getByIndex(INDEX_LASTCHANDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLastchandate <em>Lastchandate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastchandate</em>' attribute.
	 * @see #getLastchandate()
	 */
	public void setLastchandate(Date lastchandate) {
		super.setByIndex(INDEX_LASTCHANDATE, lastchandate);
	}

	/**
	 * Returns the value of the '<em><b>Billtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billtype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billtype</em>' attribute.
	 * @see #setBilltype(java.lang.String)
	 */
	public String getBilltype() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBilltype <em>Billtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billtype</em>' attribute.
	 * @see #getBilltype()
	 */
	public void setBilltype(String billtype) {
		super.setByIndex(INDEX_BILLTYPE, billtype);
	}

	/**
	 * Returns the value of the '<em><b>Billmodel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billmodel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billmodel</em>' attribute.
	 * @see #setBillmodel(java.lang.String)
	 */
	public String getBillmodel() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLMODEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillmodel <em>Billmodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billmodel</em>' attribute.
	 * @see #getBillmodel()
	 */
	public void setBillmodel(String billmodel) {
		super.setByIndex(INDEX_BILLMODEL, billmodel);
	}

	/**
	 * Returns the value of the '<em><b>Adjustnum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adjustnum</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adjustnum</em>' attribute.
	 * @see #setAdjustnum(int)
	 */
	public int getAdjustnum() {
		return DataUtil.toInt(super.getByIndex(INDEX_ADJUSTNUM, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAdjustnum <em>Adjustnum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adjustnum</em>' attribute.
	 * @see #getAdjustnum()
	 */
	public void setAdjustnum(int adjustnum) {
		super.setByIndex(INDEX_ADJUSTNUM, adjustnum);
	}

	/**
	 * Returns the value of the '<em><b>Billtrueenddate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billtrueenddate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billtrueenddate</em>' attribute.
	 * @see #setBilltrueenddate(java.util.Date)
	 */
	public Date getBilltrueenddate() {
		return DataUtil.toDate(super.getByIndex(INDEX_BILLTRUEENDDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBilltrueenddate <em>Billtrueenddate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billtrueenddate</em>' attribute.
	 * @see #getBilltrueenddate()
	 */
	public void setBilltrueenddate(Date billtrueenddate) {
		super.setByIndex(INDEX_BILLTRUEENDDATE, billtrueenddate);
	}

	/**
	 * Returns the value of the '<em><b>Interate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interate</em>' attribute.
	 * @see #setInterate(java.math.BigDecimal)
	 */
	public BigDecimal getInterate() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_INTERATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getInterate <em>Interate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interate</em>' attribute.
	 * @see #getInterate()
	 */
	public void setInterate(BigDecimal interate) {
		super.setByIndex(INDEX_INTERATE, interate);
	}

	/**
	 * Returns the value of the '<em><b>Onlinemark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onlinemark</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onlinemark</em>' attribute.
	 * @see #setOnlinemark(java.lang.String)
	 */
	public String getOnlinemark() {
		return DataUtil.toString(super.getByIndex(INDEX_ONLINEMARK, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOnlinemark <em>Onlinemark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onlinemark</em>' attribute.
	 * @see #getOnlinemark()
	 */
	public void setOnlinemark(String onlinemark) {
		super.setByIndex(INDEX_ONLINEMARK, onlinemark);
	}

	/**
	 * Returns the value of the '<em><b>Billacno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billacno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billacno</em>' attribute.
	 * @see #setBillacno(java.lang.String)
	 */
	public String getBillacno() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLACNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillacno <em>Billacno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billacno</em>' attribute.
	 * @see #getBillacno()
	 */
	public void setBillacno(String billacno) {
		super.setByIndex(INDEX_BILLACNO, billacno);
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
	 * Returns the value of the '<em><b>Billacname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Billacname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Billacname</em>' attribute.
	 * @see #setBillacname(java.lang.String)
	 */
	public String getBillacname() {
		return DataUtil.toString(super.getByIndex(INDEX_BILLACNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBillacname <em>Billacname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billacname</em>' attribute.
	 * @see #getBillacname()
	 */
	public void setBillacname(String billacname) {
		super.setByIndex(INDEX_BILLACNAME, billacname);
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
	 * Returns the value of the '<em><b>IfSameCity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IfSameCity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IfSameCity</em>' attribute.
	 * @see #setIfSameCity(java.lang.String)
	 */
	public String getIfSameCity() {
		return DataUtil.toString(super.getByIndex(INDEX_IFSAMECITY, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIfSameCity <em>IfSameCity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IfSameCity</em>' attribute.
	 * @see #getIfSameCity()
	 */
	public void setIfSameCity(String ifSameCity) {
		super.setByIndex(INDEX_IFSAMECITY, ifSameCity);
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


}