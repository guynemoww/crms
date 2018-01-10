/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application;

import com.eos.data.sdo.IObjectFactory;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.TypeHelper;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>  
 *   <li>{@link com.bos.utp.dataset.privilege.AcFunction#getFunccode <em>Funccode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFunction#getFuncname <em>Funcname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFunction#getFuncdesc <em>Funcdesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFunction#getFuncaction <em>Funcaction</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFunction#getParainfo <em>Parainfo</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFunction#getIscheck <em>Ischeck</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFunction#getFunctype <em>Functype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFunction#getIsmenu <em>Ismenu</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFunction#getAcFuncgroup <em>AcFuncgroup</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcFunction extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.AcFunction";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "AcFunction");

	public final static IObjectFactory<AcFunction> FACTORY = new IObjectFactory<AcFunction>() {
		public AcFunction create() {
			return (AcFunction) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public String getFunccode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFunction#getFunccode <em>Funccode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funccode</em>' attribute.
	 * @see #getFunccode()
	 */
	public void setFunccode(String funccode);

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
	public String getFuncname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFunction#getFuncname <em>Funcname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcname</em>' attribute.
	 * @see #getFuncname()
	 */
	public void setFuncname(String funcname);

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
	public String getFuncdesc();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFunction#getFuncdesc <em>Funcdesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcdesc</em>' attribute.
	 * @see #getFuncdesc()
	 */
	public void setFuncdesc(String funcdesc);

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
	public String getFuncaction();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFunction#getFuncaction <em>Funcaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcaction</em>' attribute.
	 * @see #getFuncaction()
	 */
	public void setFuncaction(String funcaction);

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
	public String getParainfo();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFunction#getParainfo <em>Parainfo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parainfo</em>' attribute.
	 * @see #getParainfo()
	 */
	public void setParainfo(String parainfo);

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
	public String getIscheck();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFunction#getIscheck <em>Ischeck</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ischeck</em>' attribute.
	 * @see #getIscheck()
	 */
	public void setIscheck(String ischeck);

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
	public String getFunctype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFunction#getFunctype <em>Functype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Functype</em>' attribute.
	 * @see #getFunctype()
	 */
	public void setFunctype(String functype);

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
	public String getIsmenu();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFunction#getIsmenu <em>Ismenu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ismenu</em>' attribute.
	 * @see #getIsmenu()
	 */
	public void setIsmenu(String ismenu);

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
	public AcFuncgroup getAppFuncgroup();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFunction#getAcFuncgroup <em>AcFuncgroup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcFuncgroup</em>' attribute.
	 * @see #getAcFuncgroup()
	 */
	public void setAppFuncgroup(AcFuncgroup acFuncgroup);


}