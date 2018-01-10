/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.privilege.impl;

import com.bos.utp.dataset.privilege.AcOperator;
import com.bos.utp.dataset.privilege.AcOperatorrole;
import com.bos.utp.dataset.privilege.AcRole;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleImpl#getAuth <em>Auth</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleImpl#getOrgid <em>Orgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleImpl#getAcRole <em>AcRole</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleImpl#getAcOperator <em>AcOperator</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcOperatorrole;
 */

public class AcOperatorroleImpl extends ExtendedDataObjectImpl implements AcOperatorrole {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_AUTH = 0;
	public final static int INDEX_ORGID = 1;
	public final static int INDEX_ACROLE = 2;
	public final static int INDEX_ACOPERATOR = 3;
	public final static int SDO_PROPERTY_COUNT = 4;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcOperatorroleImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcOperatorroleImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Auth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auth</em>' attribute.
	 * @see #setAuth(java.lang.String)
	 */
	public String getAuth() {
		return DataUtil.toString(super.getByIndex(INDEX_AUTH, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAuth <em>Auth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auth</em>' attribute.
	 * @see #getAuth()
	 */
	public void setAuth(String auth) {
		super.setByIndex(INDEX_AUTH, auth);
	}

	/**
	 * Returns the value of the '<em><b>Orgid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgid</em>' attribute.
	 * @see #setOrgid(int)
	 */
	public int getOrgid() {
		return DataUtil.toInt(super.getByIndex(INDEX_ORGID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgid <em>Orgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgid</em>' attribute.
	 * @see #getOrgid()
	 */
	public void setOrgid(int orgid) {
		super.setByIndex(INDEX_ORGID, orgid);
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

	/**
	 * Returns the value of the '<em><b>AcOperator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcOperator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcOperator</em>' attribute.
	 * @see #setAcOperator(com.bos.utp.dataset.privilege.AcOperator)
	 */
	public AcOperator getAcOperator() {
		return (AcOperator) DataUtil.toDataObject(super.getByIndex(INDEX_ACOPERATOR, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAcOperator <em>AcOperator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcOperator</em>' attribute.
	 * @see #getAcOperator()
	 */
	public void setAcOperator(AcOperator acOperator) {
		super.setByIndex(INDEX_ACOPERATOR, acOperator);
	}


}