/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OmDuty;
import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getDutyid <em>Dutyid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getDutycode <em>Dutycode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getDutyname <em>Dutyname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getDutyseq <em>Dutyseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getDutytype <em>Dutytype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getRemark <em>Remark</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getDutylevel <em>Dutylevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmDutyImpl#getOmDuty <em>OmDuty</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmDuty;
 */

public class OmDutyImpl extends ExtendedDataObjectImpl implements OmDuty {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_DUTYID = 0;
	public final static int INDEX_DUTYCODE = 1;
	public final static int INDEX_DUTYNAME = 2;
	public final static int INDEX_DUTYSEQ = 3;
	public final static int INDEX_DUTYTYPE = 4;
	public final static int INDEX_ISLEAF = 5;
	public final static int INDEX_SUBCOUNT = 6;
	public final static int INDEX_REMARK = 7;
	public final static int INDEX_DUTYLEVEL = 8;
	public final static int INDEX_OMDUTY = 9;
	public final static int SDO_PROPERTY_COUNT = 10;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmDutyImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmDutyImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Dutyid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutyid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutyid</em>' attribute.
	 * @see #setDutyid(int)
	 */
	public int getDutyid() {
		return DataUtil.toInt(super.getByIndex(INDEX_DUTYID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDutyid <em>Dutyid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutyid</em>' attribute.
	 * @see #getDutyid()
	 */
	public void setDutyid(int dutyid) {
		super.setByIndex(INDEX_DUTYID, dutyid);
	}

	/**
	 * Returns the value of the '<em><b>Dutycode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutycode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutycode</em>' attribute.
	 * @see #setDutycode(java.lang.String)
	 */
	public String getDutycode() {
		return DataUtil.toString(super.getByIndex(INDEX_DUTYCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDutycode <em>Dutycode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutycode</em>' attribute.
	 * @see #getDutycode()
	 */
	public void setDutycode(String dutycode) {
		super.setByIndex(INDEX_DUTYCODE, dutycode);
	}

	/**
	 * Returns the value of the '<em><b>Dutyname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutyname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutyname</em>' attribute.
	 * @see #setDutyname(java.lang.String)
	 */
	public String getDutyname() {
		return DataUtil.toString(super.getByIndex(INDEX_DUTYNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDutyname <em>Dutyname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutyname</em>' attribute.
	 * @see #getDutyname()
	 */
	public void setDutyname(String dutyname) {
		super.setByIndex(INDEX_DUTYNAME, dutyname);
	}

	/**
	 * Returns the value of the '<em><b>Dutyseq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutyseq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutyseq</em>' attribute.
	 * @see #setDutyseq(java.lang.String)
	 */
	public String getDutyseq() {
		return DataUtil.toString(super.getByIndex(INDEX_DUTYSEQ, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDutyseq <em>Dutyseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutyseq</em>' attribute.
	 * @see #getDutyseq()
	 */
	public void setDutyseq(String dutyseq) {
		super.setByIndex(INDEX_DUTYSEQ, dutyseq);
	}

	/**
	 * Returns the value of the '<em><b>Dutytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutytype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutytype</em>' attribute.
	 * @see #setDutytype(java.lang.String)
	 */
	public String getDutytype() {
		return DataUtil.toString(super.getByIndex(INDEX_DUTYTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDutytype <em>Dutytype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutytype</em>' attribute.
	 * @see #getDutytype()
	 */
	public void setDutytype(String dutytype) {
		super.setByIndex(INDEX_DUTYTYPE, dutytype);
	}

	/**
	 * Returns the value of the '<em><b>Isleaf</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isleaf</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isleaf</em>' attribute.
	 * @see #setIsleaf(java.lang.String)
	 */
	public String getIsleaf() {
		return DataUtil.toString(super.getByIndex(INDEX_ISLEAF, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsleaf <em>Isleaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isleaf</em>' attribute.
	 * @see #getIsleaf()
	 */
	public void setIsleaf(String isleaf) {
		super.setByIndex(INDEX_ISLEAF, isleaf);
	}

	/**
	 * Returns the value of the '<em><b>Subcount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subcount</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subcount</em>' attribute.
	 * @see #setSubcount(int)
	 */
	public int getSubcount() {
		return DataUtil.toInt(super.getByIndex(INDEX_SUBCOUNT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(int subcount) {
		super.setByIndex(INDEX_SUBCOUNT, subcount);
	}

	/**
	 * Returns the value of the '<em><b>Remark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remark</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remark</em>' attribute.
	 * @see #setRemark(java.lang.String)
	 */
	public String getRemark() {
		return DataUtil.toString(super.getByIndex(INDEX_REMARK, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRemark <em>Remark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remark</em>' attribute.
	 * @see #getRemark()
	 */
	public void setRemark(String remark) {
		super.setByIndex(INDEX_REMARK, remark);
	}

	/**
	 * Returns the value of the '<em><b>Dutylevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutylevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutylevel</em>' attribute.
	 * @see #setDutylevel(int)
	 */
	public int getDutylevel() {
		return DataUtil.toInt(super.getByIndex(INDEX_DUTYLEVEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDutylevel <em>Dutylevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutylevel</em>' attribute.
	 * @see #getDutylevel()
	 */
	public void setDutylevel(int dutylevel) {
		super.setByIndex(INDEX_DUTYLEVEL, dutylevel);
	}

	/**
	 * Returns the value of the '<em><b>OmDuty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmDuty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmDuty</em>' attribute.
	 * @see #setOmDuty(com.bos.utp.dataset.organization.OmDuty)
	 */
	public OmDuty getOmDuty() {
		return (OmDuty) DataUtil.toDataObject(super.getByIndex(INDEX_OMDUTY, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmDuty <em>OmDuty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmDuty</em>' attribute.
	 * @see #getOmDuty()
	 */
	public void setOmDuty(OmDuty omDuty) {
		super.setByIndex(INDEX_OMDUTY, omDuty);
	}


}