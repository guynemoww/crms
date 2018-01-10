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
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getApplyDetailId <em>ApplyDetailId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getCreateTime <em>CreateTime</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getUpdateTime <em>UpdateTime</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getAmountDetailId <em>AmountDetailId</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getCprqc <em>Cprqc</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getCprzh <em>Cprzh</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getSkrqc <em>Skrqc</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getSkrzh <em>Skrzh</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getSkrkhh <em>Skrkhh</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getPjhm <em>Pjhm</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getCurrencyCd <em>CurrencyCd</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getHpje <em>Hpje</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getHpxs <em>Hpxs</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getHpcprq <em>Hpcprq</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getHpdqrq <em>Hpdqrq</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getDfdz <em>Dfdz</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getHtbh <em>Htbh</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getJlzt <em>Jlzt</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getRemitterbankname <em>Remitterbankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getRemitterbankno <em>Remitterbankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getPayeebankname <em>Payeebankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getPayeebankno <em>Payeebankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getAcceptorbankname <em>Acceptorbankname</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getAcceptorbankno <em>Acceptorbankno</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getBillid <em>Billid</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getForbidflag <em>Forbidflag</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getSortOrder <em>SortOrder</em>}</li>
 *   <li>{@link com.bos.dataset.biz.TbBizPjxxApply#getContractId <em>ContractId</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface TbBizPjxxApply extends DataObject {

	public final static String QNAME = "com.bos.dataset.biz.TbBizPjxxApply";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.dataset.biz", "TbBizPjxxApply");

	public final static IObjectFactory<TbBizPjxxApply> FACTORY = new IObjectFactory<TbBizPjxxApply>() {
		public TbBizPjxxApply create() {
			return (TbBizPjxxApply) DataFactory.INSTANCE.create(TYPE);
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
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getApplyDetailId <em>ApplyDetailId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ApplyDetailId</em>' attribute.
	 * @see #getApplyDetailId()
	 */
	public void setApplyDetailId(String applyDetailId);

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
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getCreateTime <em>CreateTime</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getUpdateTime <em>UpdateTime</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getAmountDetailId <em>AmountDetailId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AmountDetailId</em>' attribute.
	 * @see #getAmountDetailId()
	 */
	public void setAmountDetailId(String amountDetailId);

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
	public String getCprqc();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getCprqc <em>Cprqc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cprqc</em>' attribute.
	 * @see #getCprqc()
	 */
	public void setCprqc(String cprqc);

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
	public String getCprzh();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getCprzh <em>Cprzh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cprzh</em>' attribute.
	 * @see #getCprzh()
	 */
	public void setCprzh(String cprzh);

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
	public String getSkrqc();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getSkrqc <em>Skrqc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skrqc</em>' attribute.
	 * @see #getSkrqc()
	 */
	public void setSkrqc(String skrqc);

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
	public String getSkrzh();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getSkrzh <em>Skrzh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skrzh</em>' attribute.
	 * @see #getSkrzh()
	 */
	public void setSkrzh(String skrzh);

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
	public String getSkrkhh();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getSkrkhh <em>Skrkhh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skrkhh</em>' attribute.
	 * @see #getSkrkhh()
	 */
	public void setSkrkhh(String skrkhh);

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
	public String getPjhm();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getPjhm <em>Pjhm</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pjhm</em>' attribute.
	 * @see #getPjhm()
	 */
	public void setPjhm(String pjhm);

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
	public String getCurrencyCd();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getCurrencyCd <em>CurrencyCd</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CurrencyCd</em>' attribute.
	 * @see #getCurrencyCd()
	 */
	public void setCurrencyCd(String currencyCd);

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
	public BigDecimal getHpje();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getHpje <em>Hpje</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hpje</em>' attribute.
	 * @see #getHpje()
	 */
	public void setHpje(BigDecimal hpje);

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
	public String getHpxs();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getHpxs <em>Hpxs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hpxs</em>' attribute.
	 * @see #getHpxs()
	 */
	public void setHpxs(String hpxs);

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
	public String getHpcprq();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getHpcprq <em>Hpcprq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hpcprq</em>' attribute.
	 * @see #getHpcprq()
	 */
	public void setHpcprq(String hpcprq);

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
	public String getHpdqrq();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getHpdqrq <em>Hpdqrq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hpdqrq</em>' attribute.
	 * @see #getHpdqrq()
	 */
	public void setHpdqrq(String hpdqrq);

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
	public String getDfdz();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getDfdz <em>Dfdz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dfdz</em>' attribute.
	 * @see #getDfdz()
	 */
	public void setDfdz(String dfdz);

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
	public String getHtbh();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getHtbh <em>Htbh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Htbh</em>' attribute.
	 * @see #getHtbh()
	 */
	public void setHtbh(String htbh);

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
	public String getJlzt();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getJlzt <em>Jlzt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jlzt</em>' attribute.
	 * @see #getJlzt()
	 */
	public void setJlzt(String jlzt);

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
	public String getRemitterbankname();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getRemitterbankname <em>Remitterbankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remitterbankname</em>' attribute.
	 * @see #getRemitterbankname()
	 */
	public void setRemitterbankname(String remitterbankname);

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
	public String getRemitterbankno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getRemitterbankno <em>Remitterbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remitterbankno</em>' attribute.
	 * @see #getRemitterbankno()
	 */
	public void setRemitterbankno(String remitterbankno);

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
	public String getPayeebankname();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getPayeebankname <em>Payeebankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Payeebankname</em>' attribute.
	 * @see #getPayeebankname()
	 */
	public void setPayeebankname(String payeebankname);

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
	public String getPayeebankno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getPayeebankno <em>Payeebankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Payeebankno</em>' attribute.
	 * @see #getPayeebankno()
	 */
	public void setPayeebankno(String payeebankno);

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
	public String getAcceptorbankname();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getAcceptorbankname <em>Acceptorbankname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Acceptorbankname</em>' attribute.
	 * @see #getAcceptorbankname()
	 */
	public void setAcceptorbankname(String acceptorbankname);

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
	public String getAcceptorbankno();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getAcceptorbankno <em>Acceptorbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Acceptorbankno</em>' attribute.
	 * @see #getAcceptorbankno()
	 */
	public void setAcceptorbankno(String acceptorbankno);

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
	public long getBillid();

	/**
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getBillid <em>Billid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Billid</em>' attribute.
	 * @see #getBillid()
	 */
	public void setBillid(long billid);

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
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getForbidflag <em>Forbidflag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forbidflag</em>' attribute.
	 * @see #getForbidflag()
	 */
	public void setForbidflag(String forbidflag);

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
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getSortOrder <em>SortOrder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SortOrder</em>' attribute.
	 * @see #getSortOrder()
	 */
	public void setSortOrder(long sortOrder);

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
	 * Sets the value of the '{@link com.bos.dataset.biz.TbBizPjxxApply#getContractId <em>ContractId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ContractId</em>' attribute.
	 * @see #getContractId()
	 */
	public void setContractId(String contractId);


}