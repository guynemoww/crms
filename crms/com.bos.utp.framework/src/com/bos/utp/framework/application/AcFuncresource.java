/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application;

import java.math.BigDecimal;

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
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncresource#getResid <em>Resid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncresource#getRestype <em>Restype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncresource#getRespath <em>Respath</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncresource#getCompackname <em>Compackname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncresource#getResname <em>Resname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncresource#getAcFunction <em>AcFunction</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcFuncresource extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.AcFuncresource";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "AcFuncresource");

	public final static IObjectFactory<AcFuncresource> FACTORY = new IObjectFactory<AcFuncresource>() {
		public AcFuncresource create() {
			return (AcFuncresource) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public int getResid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncresource#getResid <em>Resid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resid</em>' attribute.
	 * @see #getResid()
	 */
	public void setResid(int resid);

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
	public String getRestype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncresource#getRestype <em>Restype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Restype</em>' attribute.
	 * @see #getRestype()
	 */
	public void setRestype(String restype);

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
	public String getRespath();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncresource#getRespath <em>Respath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Respath</em>' attribute.
	 * @see #getRespath()
	 */
	public void setRespath(String respath);

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
	public String getCompackname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncresource#getCompackname <em>Compackname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compackname</em>' attribute.
	 * @see #getCompackname()
	 */
	public void setCompackname(String compackname);

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
	public String getResname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncresource#getResname <em>Resname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resname</em>' attribute.
	 * @see #getResname()
	 */
	public void setResname(String resname);

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
	public AcFunction getAcFunction();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncresource#getAcFunction <em>AcFunction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcFunction</em>' attribute.
	 * @see #getAcFunction()
	 */
	public void setAcFunction(AcFunction acFunction);


}