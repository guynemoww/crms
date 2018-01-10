/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.privilege;

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
 *   <li>{@link com.bos.utp.dataset.privilege.AcResource#getResourceid <em>Resourceid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcResource#getResourcename <em>Resourcename</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcResource#getResourcedesc <em>Resourcedesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcResource#getFunccode <em>Funccode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcResource#getFuncname <em>Funcname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcResource#getDisplayorder <em>Displayorder</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcResource extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.AcResource";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "AcResource");

	public final static IObjectFactory<AcResource> FACTORY = new IObjectFactory<AcResource>() {
		public AcResource create() {
			return (AcResource) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public String getResourceid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResource#getResourceid <em>Resourceid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourceid</em>' attribute.
	 * @see #getResourceid()
	 */
	public void setResourceid(String resourceid);

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
	public String getResourcename();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResource#getResourcename <em>Resourcename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourcename</em>' attribute.
	 * @see #getResourcename()
	 */
	public void setResourcename(String resourcename);

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
	public String getResourcedesc();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResource#getResourcedesc <em>Resourcedesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourcedesc</em>' attribute.
	 * @see #getResourcedesc()
	 */
	public void setResourcedesc(String resourcedesc);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResource#getFunccode <em>Funccode</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResource#getFuncname <em>Funcname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcname</em>' attribute.
	 * @see #getFuncname()
	 */
	public void setFuncname(String funcname);

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
	public int getDisplayorder();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResource#getDisplayorder <em>Displayorder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Displayorder</em>' attribute.
	 * @see #getDisplayorder()
	 */
	public void setDisplayorder(int displayorder);


}