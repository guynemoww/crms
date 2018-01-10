/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.privilege.impl;

import com.bos.utp.dataset.privilege.AcResource;
import com.bos.utp.dataset.privilege.AcResourcerole;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceroleImpl#getResourceidvalue <em>Resourceidvalue</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceroleImpl#getResourcestate <em>Resourcestate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceroleImpl#getPermissiontype <em>Permissiontype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceroleImpl#getAcResource <em>AcResource</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcResourcerole;
 */

public class AcResourceroleImpl extends ExtendedDataObjectImpl implements AcResourcerole {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_RESOURCEIDVALUE = 0;
	public final static int INDEX_RESOURCESTATE = 1;
	public final static int INDEX_PERMISSIONTYPE = 2;
	public final static int INDEX_ACRESOURCE = 3;
	public final static int SDO_PROPERTY_COUNT = 4;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcResourceroleImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcResourceroleImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Resourceidvalue</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resourceidvalue</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resourceidvalue</em>' attribute.
	 * @see #setResourceidvalue(java.lang.String)
	 */
	public String getResourceidvalue() {
		return DataUtil.toString(super.getByIndex(INDEX_RESOURCEIDVALUE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getResourceidvalue <em>Resourceidvalue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourceidvalue</em>' attribute.
	 * @see #getResourceidvalue()
	 */
	public void setResourceidvalue(String resourceidvalue) {
		super.setByIndex(INDEX_RESOURCEIDVALUE, resourceidvalue);
	}

	/**
	 * Returns the value of the '<em><b>Resourcestate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resourcestate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resourcestate</em>' attribute.
	 * @see #setResourcestate(java.lang.String)
	 */
	public String getResourcestate() {
		return DataUtil.toString(super.getByIndex(INDEX_RESOURCESTATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getResourcestate <em>Resourcestate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourcestate</em>' attribute.
	 * @see #getResourcestate()
	 */
	public void setResourcestate(String resourcestate) {
		super.setByIndex(INDEX_RESOURCESTATE, resourcestate);
	}

	/**
	 * Returns the value of the '<em><b>Permissiontype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissiontype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissiontype</em>' attribute.
	 * @see #setPermissiontype(java.lang.String)
	 */
	public String getPermissiontype() {
		return DataUtil.toString(super.getByIndex(INDEX_PERMISSIONTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPermissiontype <em>Permissiontype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Permissiontype</em>' attribute.
	 * @see #getPermissiontype()
	 */
	public void setPermissiontype(String permissiontype) {
		super.setByIndex(INDEX_PERMISSIONTYPE, permissiontype);
	}

	/**
	 * Returns the value of the '<em><b>AcResource</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcResource</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcResource</em>' attribute.
	 * @see #setAcResource(com.bos.utp.dataset.privilege.AcResource)
	 */
	public AcResource getAcResource() {
		return (AcResource) DataUtil.toDataObject(super.getByIndex(INDEX_ACRESOURCE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAcResource <em>AcResource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcResource</em>' attribute.
	 * @see #getAcResource()
	 */
	public void setAcResource(AcResource acResource) {
		super.setByIndex(INDEX_ACRESOURCE, acResource);
	}


}