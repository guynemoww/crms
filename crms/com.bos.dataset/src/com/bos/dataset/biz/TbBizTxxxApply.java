/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.dataset.biz;

import com.eos.data.sdo.IObjectFactory;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.TypeHelper;

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
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getApplyDetailId <em>ApplyDetailId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getLoanid <em>Loanid</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getContractId <em>ContractId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbatch <em>Billbatch</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillno <em>Billno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getCurrsign <em>Currsign</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillamt <em>Billamt</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbegindate <em>Billbegindate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillenddate <em>Billenddate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBilladdtype <em>Billaddtype</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getTakeoutacname <em>Takeoutacname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getTakeoutacbankno <em>Takeoutacbankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getTakeoutacbankname <em>Takeoutacbankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getTakeoutacno <em>Takeoutacno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBenename <em>Benename</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBenebankno <em>Benebankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBenebankname <em>Benebankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBeneno <em>Beneno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbanktype <em>Billbanktype</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbankno <em>Billbankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbankname <em>Billbankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getDirefrontname <em>Direfrontname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getDiscbenum <em>Discbenum</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getContimageno <em>Contimageno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getQueryresult <em>Queryresult</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getReviewresult <em>Reviewresult</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getReviewoption <em>Reviewoption</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getReviewer <em>Reviewer</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getReviewerorg <em>Reviewerorg</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getLastchanperson <em>Lastchanperson</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getLastchanbankid <em>Lastchanbankid</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getLastchandate <em>Lastchandate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBilltype <em>Billtype</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillmodel <em>Billmodel</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getAdjustnum <em>Adjustnum</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBilltrueenddate <em>Billtrueenddate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getInterate <em>Interate</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getOnlinemark <em>Onlinemark</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillacno <em>Billacno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getForbidflag <em>Forbidflag</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getBillacname <em>Billacname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getCreateTime <em>CreateTime</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getUpdateTime <em>UpdateTime</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getAmountDetailId <em>AmountDetailId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getIfSameCity <em>IfSameCity</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizTxxxApply#getSortOrder <em>SortOrder</em>}</li> 
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface TbBizTxxxApply extends DataObject {

	public final static String QNAME = "com.bos.dataset.biz.TbBizTxxxApply";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.dataset.biz", "TbBizTxxxApply");

	public final static IObjectFactory<TbBizTxxxApply> FACTORY = new IObjectFactory<TbBizTxxxApply>() {
		public TbBizTxxxApply create() {
			return (TbBizTxxxApply) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public String getApplyDetailId();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getApplyDetailId <em>ApplyDetailId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ApplyDetailId</em>' attribute.
	 * @see #getApplyDetailId()
	 */
	public void setApplyDetailId(String applyDetailId);

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
	public String getLoanid();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getLoanid <em>Loanid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loanid</em>' attribute.
	 * @see #getLoanid()
	 */
	public void setLoanid(String loanid);

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
	public String getContractId();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getContractId <em>ContractId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ContractId</em>' attribute.
	 * @see #getContractId()
	 */
	public void setContractId(String contractId);

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
	public String getBillbatch();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbatch <em>Billbatch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbatch</em>' attribute.
	 * @see #getBillbatch()
	 */
	public void setBillbatch(String billbatch);

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
	public String getBillno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillno <em>Billno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billno</em>' attribute.
	 * @see #getBillno()
	 */
	public void setBillno(String billno);

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
	public String getCurrsign();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getCurrsign <em>Currsign</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Currsign</em>' attribute.
	 * @see #getCurrsign()
	 */
	public void setCurrsign(String currsign);

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
	public BigDecimal getBillamt();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillamt <em>Billamt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billamt</em>' attribute.
	 * @see #getBillamt()
	 */
	public void setBillamt(BigDecimal billamt);

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
	public String getBillbegindate();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbegindate <em>Billbegindate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbegindate</em>' attribute.
	 * @see #getBillbegindate()
	 */
	public void setBillbegindate(String billbegindate);

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
	public String getBillenddate();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillenddate <em>Billenddate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billenddate</em>' attribute.
	 * @see #getBillenddate()
	 */
	public void setBillenddate(String billenddate);

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
	public String getBilladdtype();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBilladdtype <em>Billaddtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billaddtype</em>' attribute.
	 * @see #getBilladdtype()
	 */
	public void setBilladdtype(String billaddtype);

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
	public String getTakeoutacname();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getTakeoutacname <em>Takeoutacname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Takeoutacname</em>' attribute.
	 * @see #getTakeoutacname()
	 */
	public void setTakeoutacname(String takeoutacname);

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
	public String getTakeoutacbankno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getTakeoutacbankno <em>Takeoutacbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Takeoutacbankno</em>' attribute.
	 * @see #getTakeoutacbankno()
	 */
	public void setTakeoutacbankno(String takeoutacbankno);

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
	public String getTakeoutacbankname();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getTakeoutacbankname <em>Takeoutacbankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Takeoutacbankname</em>' attribute.
	 * @see #getTakeoutacbankname()
	 */
	public void setTakeoutacbankname(String takeoutacbankname);

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
	public String getTakeoutacno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getTakeoutacno <em>Takeoutacno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Takeoutacno</em>' attribute.
	 * @see #getTakeoutacno()
	 */
	public void setTakeoutacno(String takeoutacno);

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
	public String getBenename();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBenename <em>Benename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Benename</em>' attribute.
	 * @see #getBenename()
	 */
	public void setBenename(String benename);

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
	public String getBenebankno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBenebankno <em>Benebankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Benebankno</em>' attribute.
	 * @see #getBenebankno()
	 */
	public void setBenebankno(String benebankno);

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
	public String getBenebankname();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBenebankname <em>Benebankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Benebankname</em>' attribute.
	 * @see #getBenebankname()
	 */
	public void setBenebankname(String benebankname);

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
	public String getBeneno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBeneno <em>Beneno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Beneno</em>' attribute.
	 * @see #getBeneno()
	 */
	public void setBeneno(String beneno);

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
	public String getBillbanktype();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbanktype <em>Billbanktype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbanktype</em>' attribute.
	 * @see #getBillbanktype()
	 */
	public void setBillbanktype(String billbanktype);

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
	public String getBillbankno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbankno <em>Billbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbankno</em>' attribute.
	 * @see #getBillbankno()
	 */
	public void setBillbankno(String billbankno);

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
	public String getBillbankname();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillbankname <em>Billbankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billbankname</em>' attribute.
	 * @see #getBillbankname()
	 */
	public void setBillbankname(String billbankname);

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
	public String getDirefrontname();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getDirefrontname <em>Direfrontname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direfrontname</em>' attribute.
	 * @see #getDirefrontname()
	 */
	public void setDirefrontname(String direfrontname);

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
	public int getDiscbenum();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getDiscbenum <em>Discbenum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Discbenum</em>' attribute.
	 * @see #getDiscbenum()
	 */
	public void setDiscbenum(int discbenum);

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
	public String getContimageno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getContimageno <em>Contimageno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contimageno</em>' attribute.
	 * @see #getContimageno()
	 */
	public void setContimageno(String contimageno);

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
	public String getQueryresult();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getQueryresult <em>Queryresult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Queryresult</em>' attribute.
	 * @see #getQueryresult()
	 */
	public void setQueryresult(String queryresult);

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
	public String getReviewresult();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getReviewresult <em>Reviewresult</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reviewresult</em>' attribute.
	 * @see #getReviewresult()
	 */
	public void setReviewresult(String reviewresult);

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
	public String getReviewoption();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getReviewoption <em>Reviewoption</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reviewoption</em>' attribute.
	 * @see #getReviewoption()
	 */
	public void setReviewoption(String reviewoption);

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
	public String getReviewer();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getReviewer <em>Reviewer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reviewer</em>' attribute.
	 * @see #getReviewer()
	 */
	public void setReviewer(String reviewer);

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
	public String getReviewerorg();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getReviewerorg <em>Reviewerorg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reviewerorg</em>' attribute.
	 * @see #getReviewerorg()
	 */
	public void setReviewerorg(String reviewerorg);

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
	public String getLastchanperson();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getLastchanperson <em>Lastchanperson</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastchanperson</em>' attribute.
	 * @see #getLastchanperson()
	 */
	public void setLastchanperson(String lastchanperson);

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
	public String getLastchanbankid();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getLastchanbankid <em>Lastchanbankid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastchanbankid</em>' attribute.
	 * @see #getLastchanbankid()
	 */
	public void setLastchanbankid(String lastchanbankid);

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
	public Date getLastchandate();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getLastchandate <em>Lastchandate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastchandate</em>' attribute.
	 * @see #getLastchandate()
	 */
	public void setLastchandate(Date lastchandate);

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
	public String getBilltype();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBilltype <em>Billtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billtype</em>' attribute.
	 * @see #getBilltype()
	 */
	public void setBilltype(String billtype);

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
	public String getBillmodel();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillmodel <em>Billmodel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billmodel</em>' attribute.
	 * @see #getBillmodel()
	 */
	public void setBillmodel(String billmodel);

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
	public int getAdjustnum();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getAdjustnum <em>Adjustnum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adjustnum</em>' attribute.
	 * @see #getAdjustnum()
	 */
	public void setAdjustnum(int adjustnum);

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
	public Date getBilltrueenddate();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBilltrueenddate <em>Billtrueenddate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billtrueenddate</em>' attribute.
	 * @see #getBilltrueenddate()
	 */
	public void setBilltrueenddate(Date billtrueenddate);

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
	public BigDecimal getInterate();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getInterate <em>Interate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interate</em>' attribute.
	 * @see #getInterate()
	 */
	public void setInterate(BigDecimal interate);

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
	public String getOnlinemark();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getOnlinemark <em>Onlinemark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onlinemark</em>' attribute.
	 * @see #getOnlinemark()
	 */
	public void setOnlinemark(String onlinemark);

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
	public String getBillacno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillacno <em>Billacno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billacno</em>' attribute.
	 * @see #getBillacno()
	 */
	public void setBillacno(String billacno);

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
	public String getForbidflag();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getForbidflag <em>Forbidflag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forbidflag</em>' attribute.
	 * @see #getForbidflag()
	 */
	public void setForbidflag(String forbidflag);

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
	public String getBillacname();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getBillacname <em>Billacname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billacname</em>' attribute.
	 * @see #getBillacname()
	 */
	public void setBillacname(String billacname);

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
	public Date getCreateTime();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getCreateTime <em>CreateTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CreateTime</em>' attribute.
	 * @see #getCreateTime()
	 */
	public void setCreateTime(Date createTime);

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
	public Date getUpdateTime();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getUpdateTime <em>UpdateTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UpdateTime</em>' attribute.
	 * @see #getUpdateTime()
	 */
	public void setUpdateTime(Date updateTime);

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
	public String getAmountDetailId();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getAmountDetailId <em>AmountDetailId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AmountDetailId</em>' attribute.
	 * @see #getAmountDetailId()
	 */
	public void setAmountDetailId(String amountDetailId);

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
	public String getIfSameCity();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getIfSameCity <em>IfSameCity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IfSameCity</em>' attribute.
	 * @see #getIfSameCity()
	 */
	public void setIfSameCity(String ifSameCity);

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
	public long getSortOrder();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizTxxxApply#getSortOrder <em>SortOrder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SortOrder</em>' attribute.
	 * @see #getSortOrder()
	 */
	public void setSortOrder(long sortOrder);


}