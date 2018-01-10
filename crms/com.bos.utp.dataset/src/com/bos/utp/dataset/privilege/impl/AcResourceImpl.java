/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.privilege.impl;

import com.bos.utp.dataset.privilege.AcResource;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceImpl#getResourceid <em>Resourceid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceImpl#getResourcename <em>Resourcename</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceImpl#getResourcedesc <em>Resourcedesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceImpl#getFunccode <em>Funccode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceImpl#getFuncname <em>Funcname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcResourceImpl#getDisplayorder <em>Displayorder</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcResource;
 */

public class AcResourceImpl extends ExtendedDataObjectImpl implements AcResource {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_RESOURCEID = 0;
	public final static int INDEX_RESOURCENAME = 1;
	public final static int INDEX_RESOURCEDESC = 2;
	public final static int INDEX_FUNCCODE = 3;
	public final static int INDEX_FUNCNAME = 4;
	public final static int INDEX_DISPLAYORDER = 5;
	public final static int SDO_PROPERTY_COUNT = 6;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcResourceImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcResourceImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Resourceid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resourceid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resourceid</em>' attribute.
	 * @see #setResourceid(java.lang.String)
	 */
	public String getResourceid() {
		return DataUtil.toString(super.getByIndex(INDEX_RESOURCEID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getResourceid <em>Resourceid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourceid</em>' attribute.
	 * @see #getResourceid()
	 */
	public void setResourceid(String resourceid) {
		super.setByIndex(INDEX_RESOURCEID, resourceid);
	}

	/**
	 * Returns the value of the '<em><b>Resourcename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resourcename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resourcename</em>' attribute.
	 * @see #setResourcename(java.lang.String)
	 */
	public String getResourcename() {
		return DataUtil.toString(super.getByIndex(INDEX_RESOURCENAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getResourcename <em>Resourcename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourcename</em>' attribute.
	 * @see #getResourcename()
	 */
	public void setResourcename(String resourcename) {
		super.setByIndex(INDEX_RESOURCENAME, resourcename);
	}

	/**
	 * Returns the value of the '<em><b>Resourcedesc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resourcedesc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resourcedesc</em>' attribute.
	 * @see #setResourcedesc(java.lang.String)
	 */
	public String getResourcedesc() {
		return DataUtil.toString(super.getByIndex(INDEX_RESOURCEDESC, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getResourcedesc <em>Resourcedesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourcedesc</em>' attribute.
	 * @see #getResourcedesc()
	 */
	public void setResourcedesc(String resourcedesc) {
		super.setByIndex(INDEX_RESOURCEDESC, resourcedesc);
	}

	/**
	 * Returns the value of the '<em><b>Funccode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funccode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funccode</em>' attribute.
	 * @see #setFunccode(java.lang.String)
	 */
	public String getFunccode() {
		return DataUtil.toString(super.getByIndex(INDEX_FUNCCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFunccode <em>Funccode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funccode</em>' attribute.
	 * @see #getFunccode()
	 */
	public void setFunccode(String funccode) {
		super.setByIndex(INDEX_FUNCCODE, funccode);
	}

	/**
	 * Returns the value of the '<em><b>Funcname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcname</em>' attribute.
	 * @see #setFuncname(java.lang.String)
	 */
	public String getFuncname() {
		return DataUtil.toString(super.getByIndex(INDEX_FUNCNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFuncname <em>Funcname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcname</em>' attribute.
	 * @see #getFuncname()
	 */
	public void setFuncname(String funcname) {
		super.setByIndex(INDEX_FUNCNAME, funcname);
	}

	/**
	 * Returns the value of the '<em><b>Displayorder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Displayorder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Displayorder</em>' attribute.
	 * @see #setDisplayorder(int)
	 */
	public int getDisplayorder() {
		return DataUtil.toInt(super.getByIndex(INDEX_DISPLAYORDER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDisplayorder <em>Displayorder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Displayorder</em>' attribute.
	 * @see #getDisplayorder()
	 */
	public void setDisplayorder(int displayorder) {
		super.setByIndex(INDEX_DISPLAYORDER, displayorder);
	}


}