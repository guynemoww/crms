/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OmEmpgroup;
import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmGroup;
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
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmEmpgroupImpl#getOmGroup <em>OmGroup</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmEmpgroupImpl#getOmEmployee <em>OmEmployee</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmEmpgroup;
 */

public class OmEmpgroupImpl extends ExtendedDataObjectImpl implements OmEmpgroup {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_OMGROUP = 0;
	public final static int INDEX_OMEMPLOYEE = 1;
	public final static int SDO_PROPERTY_COUNT = 2;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmEmpgroupImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmEmpgroupImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>OmGroup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmGroup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmGroup</em>' attribute.
	 * @see #setOmGroup(com.bos.utp.dataset.organization.OmGroup)
	 */
	public OmGroup getOmGroup() {
		return (OmGroup) DataUtil.toDataObject(super.getByIndex(INDEX_OMGROUP, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmGroup <em>OmGroup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmGroup</em>' attribute.
	 * @see #getOmGroup()
	 */
	public void setOmGroup(OmGroup omGroup) {
		super.setByIndex(INDEX_OMGROUP, omGroup);
	}

	/**
	 * Returns the value of the '<em><b>OmEmployee</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmEmployee</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmEmployee</em>' attribute.
	 * @see #setOmEmployee(com.bos.utp.dataset.organization.OmEmployee)
	 */
	public OmEmployee getOmEmployee() {
		return (OmEmployee) DataUtil.toDataObject(super.getByIndex(INDEX_OMEMPLOYEE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmEmployee <em>OmEmployee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmEmployee</em>' attribute.
	 * @see #getOmEmployee()
	 */
	public void setOmEmployee(OmEmployee omEmployee) {
		super.setByIndex(INDEX_OMEMPLOYEE, omEmployee);
	}


}