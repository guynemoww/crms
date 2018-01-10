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
 *   <li>{@link com.bos.utp.dataset.organization.OmEmpposition#getIsmain <em>Ismain</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmEmpposition#getEmpposid <em>Empposid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmEmpposition#getOmEmployee <em>OmEmployee</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmEmpposition#getOmOrganization <em>OmOrganization</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmEmpposition#getOmPosition <em>OmPosition</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OmEmpposition extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.OmEmpposition";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "OmEmpposition");

	public final static IObjectFactory<OmEmpposition> FACTORY = new IObjectFactory<OmEmpposition>() {
		public OmEmpposition create() {
			return (OmEmpposition) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Ismain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ismain</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ismain</em>' attribute.
	 * @see #setIsmain(java.lang.String)
	 */
	public String getIsmain();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmEmpposition#getIsmain <em>Ismain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ismain</em>' attribute.
	 * @see #getIsmain()
	 */
	public void setIsmain(String ismain);

	/**
	 * Returns the value of the '<em><b>Empposid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Empposid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Empposid</em>' attribute.
	 * @see #setEmpposid(java.lang.String)
	 */
	public String getEmpposid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmEmpposition#getEmpposid <em>Empposid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empposid</em>' attribute.
	 * @see #getEmpposid()
	 */
	public void setEmpposid(String empposid);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmEmpposition#getOmEmployee <em>OmEmployee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmEmployee</em>' attribute.
	 * @see #getOmEmployee()
	 */
	public void setOmEmployee(OmEmployee omEmployee);

	/**
	 * Returns the value of the '<em><b>OmOrganization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmOrganization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmOrganization</em>' attribute.
	 * @see #setOmOrganization(com.bos.utp.dataset.organization.OmOrganization)
	 */
	public OmOrganization getOmOrganization();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmEmpposition#getOmOrganization <em>OmOrganization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmOrganization</em>' attribute.
	 * @see #getOmOrganization()
	 */
	public void setOmOrganization(OmOrganization omOrganization);

	/**
	 * Returns the value of the '<em><b>OmPosition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmPosition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmPosition</em>' attribute.
	 * @see #setOmPosition(com.bos.utp.dataset.organization.OmPosition)
	 */
	public OmPosition getOmPosition();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmEmpposition#getOmPosition <em>OmPosition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmPosition</em>' attribute.
	 * @see #getOmPosition()
	 */
	public void setOmPosition(OmPosition omPosition);


}