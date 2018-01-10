/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application.impl;

import com.bos.utp.framework.application.AcApplication;
import com.bos.utp.framework.application.AcFuncgroup;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncgroupImpl#getFuncgroupid <em>Funcgroupid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncgroupImpl#getFuncgroupname <em>Funcgroupname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncgroupImpl#getGrouplevel <em>Grouplevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncgroupImpl#getFuncgroupseq <em>Funcgroupseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncgroupImpl#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncgroupImpl#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncgroupImpl#getAcFuncgroup <em>AcFuncgroup</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcFuncgroupImpl#getAcApplication <em>AcApplication</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcFuncgroup;
 */

public class AcFuncgroupImpl extends ExtendedDataObjectImpl implements AcFuncgroup {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_FUNCGROUPID = 0;
	public final static int INDEX_FUNCGROUPNAME = 1;
	public final static int INDEX_GROUPLEVEL = 2;
	public final static int INDEX_FUNCGROUPSEQ = 3;
	public final static int INDEX_ISLEAF = 4;
	public final static int INDEX_SUBCOUNT = 5;
	public final static int INDEX_ACFUNCGROUP = 6;
	public final static int INDEX_ACAPPLICATION = 7;
	public final static int SDO_PROPERTY_COUNT = 8;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcFuncgroupImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcFuncgroupImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Funcgroupid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcgroupid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcgroupid</em>' attribute.
	 * @see #setFuncgroupid(int)
	 */
	public int getFuncgroupid() {
		return DataUtil.toInt(super.getByIndex(INDEX_FUNCGROUPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFuncgroupid <em>Funcgroupid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcgroupid</em>' attribute.
	 * @see #getFuncgroupid()
	 */
	public void setFuncgroupid(int funcgroupid) {
		super.setByIndex(INDEX_FUNCGROUPID, funcgroupid);
	}

	/**
	 * Returns the value of the '<em><b>Funcgroupname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcgroupname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcgroupname</em>' attribute.
	 * @see #setFuncgroupname(java.lang.String)
	 */
	public String getFuncgroupname() {
		return DataUtil.toString(super.getByIndex(INDEX_FUNCGROUPNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFuncgroupname <em>Funcgroupname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcgroupname</em>' attribute.
	 * @see #getFuncgroupname()
	 */
	public void setFuncgroupname(String funcgroupname) {
		super.setByIndex(INDEX_FUNCGROUPNAME, funcgroupname);
	}

	/**
	 * Returns the value of the '<em><b>Grouplevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grouplevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grouplevel</em>' attribute.
	 * @see #setGrouplevel(long)
	 */
	public long getGrouplevel() {
		return DataUtil.toLong(super.getByIndex(INDEX_GROUPLEVEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGrouplevel <em>Grouplevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grouplevel</em>' attribute.
	 * @see #getGrouplevel()
	 */
	public void setGrouplevel(long grouplevel) {
		super.setByIndex(INDEX_GROUPLEVEL, grouplevel);
	}

	/**
	 * Returns the value of the '<em><b>Funcgroupseq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcgroupseq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcgroupseq</em>' attribute.
	 * @see #setFuncgroupseq(java.lang.String)
	 */
	public String getFuncgroupseq() {
		return DataUtil.toString(super.getByIndex(INDEX_FUNCGROUPSEQ, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFuncgroupseq <em>Funcgroupseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcgroupseq</em>' attribute.
	 * @see #getFuncgroupseq()
	 */
	public void setFuncgroupseq(String funcgroupseq) {
		super.setByIndex(INDEX_FUNCGROUPSEQ, funcgroupseq);
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

	/**
	 * Returns the value of the '<em><b>AcApplication</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcApplication</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcApplication</em>' attribute.
	 * @see #setAcApplication(com.bos.utp.dataset.privilege.AcApplication)
	 */
	public AcApplication getAppApplication() {
		return (AcApplication) DataUtil.toDataObject(super.getByIndex(INDEX_ACAPPLICATION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAcApplication <em>AcApplication</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcApplication</em>' attribute.
	 * @see #getAcApplication()
	 */
	public void setAppApplication(AcApplication acApplication) {
		super.setByIndex(INDEX_ACAPPLICATION, acApplication);
	}


}