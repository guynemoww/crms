/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization;

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
 *   <li>{@link com.bos.utp.dataset.organization.OmEmpgroup#getOmGroup <em>OmGroup</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmEmpgroup#getOmEmployee <em>OmEmployee</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OmEmpgroup extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.OmEmpgroup";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "OmEmpgroup");

	public final static IObjectFactory<OmEmpgroup> FACTORY = new IObjectFactory<OmEmpgroup>() {
		public OmEmpgroup create() {
			return (OmEmpgroup) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>OmGroup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmGroup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmGroup</em>' attribute.
	 * @see #setOmGroup(com.bos.utp.dataset.organization.OmGroup)
	 */
	public OmGroup getOmGroup();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmEmpgroup#getOmGroup <em>OmGroup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmGroup</em>' attribute.
	 * @see #getOmGroup()
	 */
	public void setOmGroup(OmGroup omGroup);

	/**
	 * Returns the value of the '<em><b>OmEmployee</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmEmployee</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmEmployee</em>' attribute.
	 * @see #setOmEmployee(com.bos.utp.dataset.organization.OmEmployee)
	 */
	public OmEmployee getOmEmployee();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmEmpgroup#getOmEmployee <em>OmEmployee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmEmployee</em>' attribute.
	 * @see #getOmEmployee()
	 */
	public void setOmEmployee(OmEmployee omEmployee);


}