/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.VcWorkGroup;
import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import java.math.BigDecimal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VcWorkGroupImpl#getRoleid <em>Roleid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VcWorkGroupImpl#getPartytype <em>Partytype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VcWorkGroupImpl#getPartyid <em>Partyid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VcWorkGroupImpl#getGroupid <em>Groupid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VcWorkGroupImpl#getGroupname <em>Groupname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VcWorkGroupImpl#getGrouptype <em>Grouptype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VcWorkGroupImpl#getGroupstatus <em>Groupstatus</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VcWorkGroupImpl#getOrgname <em>Orgname</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements VcWorkGroup;
 */

public class VcWorkGroupImpl extends ExtendedDataObjectImpl implements VcWorkGroup {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_ROLEID = 0;
	public final static int INDEX_PARTYTYPE = 1;
	public final static int INDEX_PARTYID = 2;
	public final static int INDEX_GROUPID = 3;
	public final static int INDEX_GROUPNAME = 4;
	public final static int INDEX_GROUPTYPE = 5;
	public final static int INDEX_GROUPSTATUS = 6;
	public final static int INDEX_ORGNAME = 7;
	public final static int SDO_PROPERTY_COUNT = 8;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public VcWorkGroupImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public VcWorkGroupImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

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
	public String getRoleid() {
		return DataUtil.toString(super.getByIndex(INDEX_ROLEID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRoleid <em>Roleid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Roleid</em>' attribute.
	 * @see #getRoleid()
	 */
	public void setRoleid(String roleid) {
		super.setByIndex(INDEX_ROLEID, roleid);
	}

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
	public String getPartytype() {
		return DataUtil.toString(super.getByIndex(INDEX_PARTYTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPartytype <em>Partytype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partytype</em>' attribute.
	 * @see #getPartytype()
	 */
	public void setPartytype(String partytype) {
		super.setByIndex(INDEX_PARTYTYPE, partytype);
	}

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
	public BigDecimal getPartyid() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_PARTYID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPartyid <em>Partyid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partyid</em>' attribute.
	 * @see #getPartyid()
	 */
	public void setPartyid(BigDecimal partyid) {
		super.setByIndex(INDEX_PARTYID, partyid);
	}

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
	public BigDecimal getGroupid() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_GROUPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGroupid <em>Groupid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupid</em>' attribute.
	 * @see #getGroupid()
	 */
	public void setGroupid(BigDecimal groupid) {
		super.setByIndex(INDEX_GROUPID, groupid);
	}

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
	public String getGroupname() {
		return DataUtil.toString(super.getByIndex(INDEX_GROUPNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGroupname <em>Groupname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupname</em>' attribute.
	 * @see #getGroupname()
	 */
	public void setGroupname(String groupname) {
		super.setByIndex(INDEX_GROUPNAME, groupname);
	}

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
	public String getGrouptype() {
		return DataUtil.toString(super.getByIndex(INDEX_GROUPTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGrouptype <em>Grouptype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grouptype</em>' attribute.
	 * @see #getGrouptype()
	 */
	public void setGrouptype(String grouptype) {
		super.setByIndex(INDEX_GROUPTYPE, grouptype);
	}

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
	public String getGroupstatus() {
		return DataUtil.toString(super.getByIndex(INDEX_GROUPSTATUS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGroupstatus <em>Groupstatus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupstatus</em>' attribute.
	 * @see #getGroupstatus()
	 */
	public void setGroupstatus(String groupstatus) {
		super.setByIndex(INDEX_GROUPSTATUS, groupstatus);
	}

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
	public String getOrgname() {
		return DataUtil.toString(super.getByIndex(INDEX_ORGNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgname <em>Orgname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgname</em>' attribute.
	 * @see #getOrgname()
	 */
	public void setOrgname(String orgname) {
		super.setByIndex(INDEX_ORGNAME, orgname);
	}


}