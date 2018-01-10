/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmEmpposition;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
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
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmEmppositionImpl#getIsmain <em>Ismain</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmEmppositionImpl#getEmpposid <em>Empposid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmEmppositionImpl#getOmEmployee <em>OmEmployee</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmEmppositionImpl#getOmOrganization <em>OmOrganization</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmEmppositionImpl#getOmPosition <em>OmPosition</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmEmpposition;
 */

public class OmEmppositionImpl extends ExtendedDataObjectImpl implements OmEmpposition {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_ISMAIN = 0;
	public final static int INDEX_EMPPOSID = 1;
	public final static int INDEX_OMEMPLOYEE = 2;
	public final static int INDEX_OMORGANIZATION = 3;
	public final static int INDEX_OMPOSITION = 4;
	public final static int SDO_PROPERTY_COUNT = 5;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmEmppositionImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmEmppositionImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

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
	public String getIsmain() {
		return DataUtil.toString(super.getByIndex(INDEX_ISMAIN, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsmain <em>Ismain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ismain</em>' attribute.
	 * @see #getIsmain()
	 */
	public void setIsmain(String ismain) {
		super.setByIndex(INDEX_ISMAIN, ismain);
	}

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
	public String getEmpposid() {
		return DataUtil.toString(super.getByIndex(INDEX_EMPPOSID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEmpposid <em>Empposid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empposid</em>' attribute.
	 * @see #getEmpposid()
	 */
	public void setEmpposid(String empposid) {
		super.setByIndex(INDEX_EMPPOSID, empposid);
	}

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
	public OmEmployee getOmEmployee() {
		return (OmEmployee) DataUtil.toDataObject(super.getByIndex(INDEX_OMEMPLOYEE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmEmployee <em>OmEmployee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmEmployee</em>' attribute.
	 * @see #getOmEmployee()
	 */
	public void setOmEmployee(OmEmployee omEmployee) {
		super.setByIndex(INDEX_OMEMPLOYEE, omEmployee);
	}

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
	public OmOrganization getOmOrganization() {
		return (OmOrganization) DataUtil.toDataObject(super.getByIndex(INDEX_OMORGANIZATION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmOrganization <em>OmOrganization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmOrganization</em>' attribute.
	 * @see #getOmOrganization()
	 */
	public void setOmOrganization(OmOrganization omOrganization) {
		super.setByIndex(INDEX_OMORGANIZATION, omOrganization);
	}

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
	public OmPosition getOmPosition() {
		return (OmPosition) DataUtil.toDataObject(super.getByIndex(INDEX_OMPOSITION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmPosition <em>OmPosition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmPosition</em>' attribute.
	 * @see #getOmPosition()
	 */
	public void setOmPosition(OmPosition omPosition) {
		super.setByIndex(INDEX_OMPOSITION, omPosition);
	}


}