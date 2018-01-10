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
 *   <li>{@link com.bos.utp.dataset.privilege.AcOperatorrole#getAuth <em>Auth</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcOperatorrole#getOrgid <em>Orgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcOperatorrole#getAcRole <em>AcRole</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcOperatorrole#getAcOperator <em>AcOperator</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcOperatorrole extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.AcOperatorrole";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "AcOperatorrole");

	public final static IObjectFactory<AcOperatorrole> FACTORY = new IObjectFactory<AcOperatorrole>() {
		public AcOperatorrole create() {
			return (AcOperatorrole) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Auth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auth</em>' attribute.
	 * @see #setAuth(java.lang.String)
	 */
	public String getAuth();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcOperatorrole#getAuth <em>Auth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auth</em>' attribute.
	 * @see #getAuth()
	 */
	public void setAuth(String auth);

	/**
	 * Returns the value of the '<em><b>Orgid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgid</em>' attribute.
	 * @see #setOrgid(int)
	 */
	public int getOrgid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcOperatorrole#getOrgid <em>Orgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgid</em>' attribute.
	 * @see #getOrgid()
	 */
	public void setOrgid(int orgid);

	/**
	 * Returns the value of the '<em><b>AcRole</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcRole</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcRole</em>' attribute.
	 * @see #setAcRole(com.bos.utp.dataset.privilege.AcRole)
	 */
	public AcRole getAcRole();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcOperatorrole#getAcRole <em>AcRole</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcRole</em>' attribute.
	 * @see #getAcRole()
	 */
	public void setAcRole(AcRole acRole);

	/**
	 * Returns the value of the '<em><b>AcOperator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcOperator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcOperator</em>' attribute.
	 * @see #setAcOperator(com.bos.utp.dataset.privilege.AcOperator)
	 */
	public AcOperator getAcOperator();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcOperatorrole#getAcOperator <em>AcOperator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcOperator</em>' attribute.
	 * @see #getAcOperator()
	 */
	public void setAcOperator(AcOperator acOperator);


}