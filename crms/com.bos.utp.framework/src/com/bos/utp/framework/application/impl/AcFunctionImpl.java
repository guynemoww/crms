/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application.impl;

import com.bos.utp.framework.application.AcFuncgroup;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFunctionImpl#getFunccode <em>Funccode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFunctionImpl#getFuncname <em>Funcname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFunctionImpl#getFuncdesc <em>Funcdesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFunctionImpl#getFuncaction <em>Funcaction</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFunctionImpl#getParainfo <em>Parainfo</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFunctionImpl#getIscheck <em>Ischeck</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFunctionImpl#getFunctype <em>Functype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFunctionImpl#getIsmenu <em>Ismenu</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFunctionImpl#getAcFuncgroup <em>AcFuncgroup</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcFunction;
 */

public class AcFunctionImpl extends ExtendedDataObjectImpl implements AcFunction {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_FUNCCODE = 0;
	public final static int INDEX_FUNCNAME = 1;
	public final static int INDEX_FUNCDESC = 2;
	public final static int INDEX_FUNCACTION = 3;
	public final static int INDEX_PARAINFO = 4;
	public final static int INDEX_ISCHECK = 5;
	public final static int INDEX_FUNCTYPE = 6;
	public final static int INDEX_ISMENU = 7;
	public final static int INDEX_ACFUNCGROUP = 8;
	public final static int SDO_PROPERTY_COUNT = 9;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcFunctionImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcFunctionImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
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
	 * Returns the value of the '<em><b>Funcdesc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcdesc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcdesc</em>' attribute.
	 * @see #setFuncdesc(java.lang.String)
	 */
	public String getFuncdesc() {
		return DataUtil.toString(super.getByIndex(INDEX_FUNCDESC, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFuncdesc <em>Funcdesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcdesc</em>' attribute.
	 * @see #getFuncdesc()
	 */
	public void setFuncdesc(String funcdesc) {
		super.setByIndex(INDEX_FUNCDESC, funcdesc);
	}

	/**
	 * Returns the value of the '<em><b>Funcaction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcaction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcaction</em>' attribute.
	 * @see #setFuncaction(java.lang.String)
	 */
	public String getFuncaction() {
		return DataUtil.toString(super.getByIndex(INDEX_FUNCACTION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFuncaction <em>Funcaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcaction</em>' attribute.
	 * @see #getFuncaction()
	 */
	public void setFuncaction(String funcaction) {
		super.setByIndex(INDEX_FUNCACTION, funcaction);
	}

	/**
	 * Returns the value of the '<em><b>Parainfo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parainfo</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parainfo</em>' attribute.
	 * @see #setParainfo(java.lang.String)
	 */
	public String getParainfo() {
		return DataUtil.toString(super.getByIndex(INDEX_PARAINFO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getParainfo <em>Parainfo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parainfo</em>' attribute.
	 * @see #getParainfo()
	 */
	public void setParainfo(String parainfo) {
		super.setByIndex(INDEX_PARAINFO, parainfo);
	}

	/**
	 * Returns the value of the '<em><b>Ischeck</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ischeck</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ischeck</em>' attribute.
	 * @see #setIscheck(java.lang.String)
	 */
	public String getIscheck() {
		return DataUtil.toString(super.getByIndex(INDEX_ISCHECK, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIscheck <em>Ischeck</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ischeck</em>' attribute.
	 * @see #getIscheck()
	 */
	public void setIscheck(String ischeck) {
		super.setByIndex(INDEX_ISCHECK, ischeck);
	}

	/**
	 * Returns the value of the '<em><b>Functype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Functype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Functype</em>' attribute.
	 * @see #setFunctype(java.lang.String)
	 */
	public String getFunctype() {
		return DataUtil.toString(super.getByIndex(INDEX_FUNCTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFunctype <em>Functype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Functype</em>' attribute.
	 * @see #getFunctype()
	 */
	public void setFunctype(String functype) {
		super.setByIndex(INDEX_FUNCTYPE, functype);
	}

	/**
	 * Returns the value of the '<em><b>Ismenu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ismenu</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ismenu</em>' attribute.
	 * @see #setIsmenu(java.lang.String)
	 */
	public String getIsmenu() {
		return DataUtil.toString(super.getByIndex(INDEX_ISMENU, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsmenu <em>Ismenu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ismenu</em>' attribute.
	 * @see #getIsmenu()
	 */
	public void setIsmenu(String ismenu) {
		super.setByIndex(INDEX_ISMENU, ismenu);
	}

	/**
	 * Returns the value of the '<em><b>AcFuncgroup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcFuncgroup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcFuncgroup</em>' attribute.
	 * @see #setAcFuncgroup(com.bos.utp.dataset.privilege.AcFuncgroup)
	 */
	public AcFuncgroup getAppFuncgroup() {
		return (AcFuncgroup) DataUtil.toDataObject(super.getByIndex(INDEX_ACFUNCGROUP, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAcFuncgroup <em>AcFuncgroup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcFuncgroup</em>' attribute.
	 * @see #getAcFuncgroup()
	 */
	public void setAppFuncgroup(AcFuncgroup acFuncgroup) {
		super.setByIndex(INDEX_ACFUNCGROUP, acFuncgroup);
	}


}