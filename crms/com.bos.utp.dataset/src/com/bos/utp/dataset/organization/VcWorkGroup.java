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
 *   <li>{@link com.bos.utp.dataset.organization.VcWorkGroup#getRoleid <em>Roleid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcWorkGroup#getPartytype <em>Partytype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcWorkGroup#getPartyid <em>Partyid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcWorkGroup#getGroupid <em>Groupid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcWorkGroup#getGroupname <em>Groupname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcWorkGroup#getGrouptype <em>Grouptype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcWorkGroup#getGroupstatus <em>Groupstatus</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.VcWorkGroup#getOrgname <em>Orgname</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface VcWorkGroup extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.VcWorkGroup";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "VcWorkGroup");

	public final static IObjectFactory<VcWorkGroup> FACTORY = new IObjectFactory<VcWorkGroup>() {
		public VcWorkGroup create() {
			return (VcWorkGroup) DataFactory.INSTANCE.create(TYPE);
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcWorkGroup#getRoleid <em>Roleid</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcWorkGroup#getPartytype <em>Partytype</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcWorkGroup#getPartyid <em>Partyid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partyid</em>' attribute.
	 * @see #getPartyid()
	 */
	public void setPartyid(BigDecimal partyid);

	/**
	 * Returns the value of the '<em><b>Groupid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groupid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groupid</em>' attribute.
	 * @see #setGroupid(java.math.BigDecimal)
	 */
	public BigDecimal getGroupid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcWorkGroup#getGroupid <em>Groupid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupid</em>' attribute.
	 * @see #getGroupid()
	 */
	public void setGroupid(BigDecimal groupid);

	/**
	 * Returns the value of the '<em><b>Groupname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groupname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groupname</em>' attribute.
	 * @see #setGroupname(java.lang.String)
	 */
	public String getGroupname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcWorkGroup#getGroupname <em>Groupname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupname</em>' attribute.
	 * @see #getGroupname()
	 */
	public void setGroupname(String groupname);

	/**
	 * Returns the value of the '<em><b>Grouptype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grouptype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grouptype</em>' attribute.
	 * @see #setGrouptype(java.lang.String)
	 */
	public String getGrouptype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcWorkGroup#getGrouptype <em>Grouptype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grouptype</em>' attribute.
	 * @see #getGrouptype()
	 */
	public void setGrouptype(String grouptype);

	/**
	 * Returns the value of the '<em><b>Groupstatus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groupstatus</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groupstatus</em>' attribute.
	 * @see #setGroupstatus(java.lang.String)
	 */
	public String getGroupstatus();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcWorkGroup#getGroupstatus <em>Groupstatus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupstatus</em>' attribute.
	 * @see #getGroupstatus()
	 */
	public void setGroupstatus(String groupstatus);

	/**
	 * Returns the value of the '<em><b>Orgname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgname</em>' attribute.
	 * @see #setOrgname(java.lang.String)
	 */
	public String getOrgname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.VcWorkGroup#getOrgname <em>Orgname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgname</em>' attribute.
	 * @see #getOrgname()
	 */
	public void setOrgname(String orgname);


}