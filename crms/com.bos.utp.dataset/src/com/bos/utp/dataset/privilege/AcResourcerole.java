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
 *   <li>{@link com.bos.utp.dataset.privilege.AcResourcerole#getResourceidvalue <em>Resourceidvalue</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcResourcerole#getResourcestate <em>Resourcestate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcResourcerole#getPermissiontype <em>Permissiontype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcResourcerole#getAcResource <em>AcResource</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcResourcerole extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.AcResourcerole";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "AcResourcerole");

	public final static IObjectFactory<AcResourcerole> FACTORY = new IObjectFactory<AcResourcerole>() {
		public AcResourcerole create() {
			return (AcResourcerole) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public String getResourceidvalue();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResourcerole#getResourceidvalue <em>Resourceidvalue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourceidvalue</em>' attribute.
	 * @see #getResourceidvalue()
	 */
	public void setResourceidvalue(String resourceidvalue);

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
	public String getResourcestate();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResourcerole#getResourcestate <em>Resourcestate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourcestate</em>' attribute.
	 * @see #getResourcestate()
	 */
	public void setResourcestate(String resourcestate);

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
	public String getPermissiontype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResourcerole#getPermissiontype <em>Permissiontype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Permissiontype</em>' attribute.
	 * @see #getPermissiontype()
	 */
	public void setPermissiontype(String permissiontype);

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
	public AcResource getAcResource();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcResourcerole#getAcResource <em>AcResource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcResource</em>' attribute.
	 * @see #getAcResource()
	 */
	public void setAcResource(AcResource acResource);


}