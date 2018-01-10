/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.privilege.impl;

import com.bos.utp.dataset.privilege.AcRole;
import com.bos.utp.dataset.privilege.OmPartyrole;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.OmPartyroleImpl#getPartytype <em>Partytype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.OmPartyroleImpl#getPartyid <em>Partyid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.OmPartyroleImpl#getAcRole <em>AcRole</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmPartyrole;
 */

public class OmPartyroleImpl extends ExtendedDataObjectImpl implements OmPartyrole {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_PARTYTYPE = 0;
	public final static int INDEX_PARTYID = 1;
	public final static int INDEX_ACROLE = 2;
	public final static int SDO_PROPERTY_COUNT = 3;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmPartyroleImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmPartyroleImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Partytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partytype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partytype</em>' attribute.
	 * @see #setPartytype(java.lang.String)
	 */
	public String getPartytype() {
		return DataUtil.toString(super.getByIndex(INDEX_PARTYTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPartytype <em>Partytype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partytype</em>' attribute.
	 * @see #getPartytype()
	 */
	public void setPartytype(String partytype) {
		super.setByIndex(INDEX_PARTYTYPE, partytype);
	}

	/**
	 * Returns the value of the '<em><b>Partyid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partyid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partyid</em>' attribute.
	 * @see #setPartyid(int)
	 */
	public int getPartyid() {
		return DataUtil.toInt(super.getByIndex(INDEX_PARTYID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPartyid <em>Partyid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partyid</em>' attribute.
	 * @see #getPartyid()
	 */
	public void setPartyid(int partyid) {
		super.setByIndex(INDEX_PARTYID, partyid);
	}

	/**
	 * Returns the value of the '<em><b>AcRole</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcRole</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcRole</em>' attribute.
	 * @see #setAcRole(com.bos.utp.dataset.privilege.AcRole)
	 */
	public AcRole getAcRole() {
		return (AcRole) DataUtil.toDataObject(super.getByIndex(INDEX_ACROLE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAcRole <em>AcRole</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcRole</em>' attribute.
	 * @see #getAcRole()
	 */
	public void setAcRole(AcRole acRole) {
		super.setByIndex(INDEX_ACROLE, acRole);
	}


}