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

import java.math.BigDecimal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.utp.dataset.organization.VcDuty#getRoleid <em>Roleid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcDuty#getPartytype <em>Partytype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcDuty#getPartyid <em>Partyid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcDuty#getDutyname <em>Dutyname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcDuty#getDutyid <em>Dutyid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcDuty#getDutycode <em>Dutycode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcDuty#getDutytype <em>Dutytype</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface VcDuty extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.VcDuty";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "VcDuty");

	public final static IObjectFactory<VcDuty> FACTORY = new IObjectFactory<VcDuty>() {
		public VcDuty create() {
			return (VcDuty) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Roleid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roleid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roleid</em>' attribute.
	 * @see #setRoleid(java.lang.String)
	 */
	public String getRoleid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcDuty#getRoleid <em>Roleid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Roleid</em>' attribute.
	 * @see #getRoleid()
	 */
	public void setRoleid(String roleid);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcDuty#getPartytype <em>Partytype</em>}' attribute.
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
	 * @see #setPartyid(java.math.BigDecimal)
	 */
	public BigDecimal getPartyid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcDuty#getPartyid <em>Partyid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partyid</em>' attribute.
	 * @see #getPartyid()
	 */
	public void setPartyid(BigDecimal partyid);

	/**
	 * Returns the value of the '<em><b>Dutyname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutyname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutyname</em>' attribute.
	 * @see #setDutyname(java.lang.String)
	 */
	public String getDutyname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcDuty#getDutyname <em>Dutyname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutyname</em>' attribute.
	 * @see #getDutyname()
	 */
	public void setDutyname(String dutyname);

	/**
	 * Returns the value of the '<em><b>Dutyid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutyid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutyid</em>' attribute.
	 * @see #setDutyid(java.math.BigDecimal)
	 */
	public BigDecimal getDutyid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcDuty#getDutyid <em>Dutyid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutyid</em>' attribute.
	 * @see #getDutyid()
	 */
	public void setDutyid(BigDecimal dutyid);

	/**
	 * Returns the value of the '<em><b>Dutycode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutycode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutycode</em>' attribute.
	 * @see #setDutycode(java.lang.String)
	 */
	public String getDutycode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcDuty#getDutycode <em>Dutycode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutycode</em>' attribute.
	 * @see #getDutycode()
	 */
	public void setDutycode(String dutycode);

	/**
	 * Returns the value of the '<em><b>Dutytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutytype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutytype</em>' attribute.
	 * @see #setDutytype(java.lang.String)
	 */
	public String getDutytype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcDuty#getDutytype <em>Dutytype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutytype</em>' attribute.
	 * @see #getDutytype()
	 */
	public void setDutytype(String dutytype);


}