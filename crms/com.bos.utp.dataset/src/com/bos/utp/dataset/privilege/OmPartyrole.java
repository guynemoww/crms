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
 *   <li>{@link com.bos.utp.dataset.privilege.OmPartyrole#getPartytype <em>Partytype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.OmPartyrole#getPartyid <em>Partyid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.OmPartyrole#getAcRole <em>AcRole</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OmPartyrole extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.OmPartyrole";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "OmPartyrole");

	public final static IObjectFactory<OmPartyrole> FACTORY = new IObjectFactory<OmPartyrole>() {
		public OmPartyrole create() {
			return (OmPartyrole) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Partytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partytype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partytype</em>' attribute.
	 * @see #setPartytype(java.lang.String)
	 */
	public String getPartytype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.OmPartyrole#getPartytype <em>Partytype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partytype</em>' attribute.
	 * @see #getPartytype()
	 */
	public void setPartytype(String partytype);

	/**
	 * Returns the value of the '<em><b>Partyid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partyid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partyid</em>' attribute.
	 * @see #setPartyid(int)
	 */
	public int getPartyid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.OmPartyrole#getPartyid <em>Partyid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partyid</em>' attribute.
	 * @see #getPartyid()
	 */
	public void setPartyid(int partyid);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.OmPartyrole#getAcRole <em>AcRole</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcRole</em>' attribute.
	 * @see #getAcRole()
	 */
	public void setAcRole(AcRole acRole);


}