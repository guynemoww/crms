/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application.impl;

import com.bos.utp.framework.application.AcFuncresource;
import com.bos.utp.framework.application.AcFunction;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncresourceImpl#getResid <em>Resid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncresourceImpl#getRestype <em>Restype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncresourceImpl#getRespath <em>Respath</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncresourceImpl#getCompackname <em>Compackname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncresourceImpl#getResname <em>Resname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncresourceImpl#getAcFunction <em>AcFunction</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcFuncresource;
 */

public class AcFuncresourceImpl extends ExtendedDataObjectImpl implements AcFuncresource {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_RESID = 0;
	public final static int INDEX_RESTYPE = 1;
	public final static int INDEX_RESPATH = 2;
	public final static int INDEX_COMPACKNAME = 3;
	public final static int INDEX_RESNAME = 4;
	public final static int INDEX_ACFUNCTION = 5;
	public final static int SDO_PROPERTY_COUNT = 6;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcFuncresourceImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcFuncresourceImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Resid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resid</em>' attribute.
	 * @see #setResid(int)
	 */
	public int getResid() {
		return DataUtil.toInt(super.getByIndex(INDEX_RESID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getResid <em>Resid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resid</em>' attribute.
	 * @see #getResid()
	 */
	public void setResid(int resid) {
		super.setByIndex(INDEX_RESID, resid);
	}

	/**
	 * Returns the value of the '<em><b>Restype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Restype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Restype</em>' attribute.
	 * @see #setRestype(java.lang.String)
	 */
	public String getRestype() {
		return DataUtil.toString(super.getByIndex(INDEX_RESTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRestype <em>Restype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Restype</em>' attribute.
	 * @see #getRestype()
	 */
	public void setRestype(String restype) {
		super.setByIndex(INDEX_RESTYPE, restype);
	}

	/**
	 * Returns the value of the '<em><b>Respath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Respath</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Respath</em>' attribute.
	 * @see #setRespath(java.lang.String)
	 */
	public String getRespath() {
		return DataUtil.toString(super.getByIndex(INDEX_RESPATH, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRespath <em>Respath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Respath</em>' attribute.
	 * @see #getRespath()
	 */
	public void setRespath(String respath) {
		super.setByIndex(INDEX_RESPATH, respath);
	}

	/**
	 * Returns the value of the '<em><b>Compackname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compackname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compackname</em>' attribute.
	 * @see #setCompackname(java.lang.String)
	 */
	public String getCompackname() {
		return DataUtil.toString(super.getByIndex(INDEX_COMPACKNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCompackname <em>Compackname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compackname</em>' attribute.
	 * @see #getCompackname()
	 */
	public void setCompackname(String compackname) {
		super.setByIndex(INDEX_COMPACKNAME, compackname);
	}

	/**
	 * Returns the value of the '<em><b>Resname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resname</em>' attribute.
	 * @see #setResname(java.lang.String)
	 */
	public String getResname() {
		return DataUtil.toString(super.getByIndex(INDEX_RESNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getResname <em>Resname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resname</em>' attribute.
	 * @see #getResname()
	 */
	public void setResname(String resname) {
		super.setByIndex(INDEX_RESNAME, resname);
	}

	/**
	 * Returns the value of the '<em><b>AcFunction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcFunction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcFunction</em>' attribute.
	 * @see #setAcFunction(com.bos.utp.dataset.privilege.AcFunction)
	 */
	public AcFunction getAcFunction() {
		return (AcFunction) DataUtil.toDataObject(super.getByIndex(INDEX_ACFUNCTION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAcFunction <em>AcFunction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcFunction</em>' attribute.
	 * @see #getAcFunction()
	 */
	public void setAcFunction(AcFunction acFunction) {
		super.setByIndex(INDEX_ACFUNCTION, acFunction);
	}


}