/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OmPartyRoleView;
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
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPartyRoleViewImpl#getRoleid <em>Roleid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPartyRoleViewImpl#getPartytype <em>Partytype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPartyRoleViewImpl#getPartyid <em>Partyid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPartyRoleViewImpl#getRolename <em>Rolename</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPartyRoleViewImpl#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPartyRoleViewImpl#getAppname <em>Appname</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmPartyRoleView;
 */

public class OmPartyRoleViewImpl extends ExtendedDataObjectImpl implements OmPartyRoleView {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_ROLEID = 0;
	public final static int INDEX_PARTYTYPE = 1;
	public final static int INDEX_PARTYID = 2;
	public final static int INDEX_ROLENAME = 3;
	public final static int INDEX_APPID = 4;
	public final static int INDEX_APPNAME = 5;
	public final static int SDO_PROPERTY_COUNT = 6;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmPartyRoleViewImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmPartyRoleViewImpl(Type type) {
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
	 * Returns the value of the '<em><b>Rolename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rolename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rolename</em>' attribute.
	 * @see #setRolename(java.lang.String)
	 */
	public String getRolename() {
		return DataUtil.toString(super.getByIndex(INDEX_ROLENAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRolename <em>Rolename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rolename</em>' attribute.
	 * @see #getRolename()
	 */
	public void setRolename(String rolename) {
		super.setByIndex(INDEX_ROLENAME, rolename);
	}

	/**
	 * Returns the value of the '<em><b>Appid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Appid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Appid</em>' attribute.
	 * @see #setAppid(java.math.BigDecimal)
	 */
	public BigDecimal getAppid() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_APPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAppid <em>Appid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appid</em>' attribute.
	 * @see #getAppid()
	 */
	public void setAppid(BigDecimal appid) {
		super.setByIndex(INDEX_APPID, appid);
	}

	/**
	 * Returns the value of the '<em><b>Appname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Appname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Appname</em>' attribute.
	 * @see #setAppname(java.lang.String)
	 */
	public String getAppname() {
		return DataUtil.toString(super.getByIndex(INDEX_APPNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAppname <em>Appname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appname</em>' attribute.
	 * @see #getAppname()
	 */
	public void setAppname(String appname) {
		super.setByIndex(INDEX_APPNAME, appname);
	}


}