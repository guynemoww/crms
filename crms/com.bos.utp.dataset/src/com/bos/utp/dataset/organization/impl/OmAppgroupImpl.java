/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OmAppgroup;
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
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmAppgroupImpl#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmAppgroupImpl#getOmGroup <em>OmGroup</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmAppgroup;
 */

public class OmAppgroupImpl extends ExtendedDataObjectImpl implements OmAppgroup {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_APPID = 0;
	public final static int INDEX_OMGROUP = 1;
	public final static int SDO_PROPERTY_COUNT = 2;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmAppgroupImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmAppgroupImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Appid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Appid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Appid</em>' attribute.
	 * @see #setAppid(int)
	 */
	public int getAppid() {
		return DataUtil.toInt(super.getByIndex(INDEX_APPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAppid <em>Appid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appid</em>' attribute.
	 * @see #getAppid()
	 */
	public void setAppid(int appid) {
		super.setByIndex(INDEX_APPID, appid);
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


}