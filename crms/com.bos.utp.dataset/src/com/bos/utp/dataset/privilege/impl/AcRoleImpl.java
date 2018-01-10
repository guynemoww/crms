/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.privilege.impl;

import com.bos.utp.dataset.privilege.AcRole;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcRoleImpl#getRoleid <em>Roleid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcRoleImpl#getRolename <em>Rolename</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcRoleImpl#getRoletype <em>Roletype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcRoleImpl#getRoledesc <em>Roledesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcRoleImpl#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcRoleImpl#getInitflag <em>Initflag</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcRole;
 */

public class AcRoleImpl extends ExtendedDataObjectImpl implements AcRole {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_ROLEID = 0;
	public final static int INDEX_ROLENAME = 1;
	public final static int INDEX_ROLETYPE = 2;
	public final static int INDEX_ROLEDESC = 3;
	public final static int INDEX_APPID = 4;
	public final static int INDEX_INITFLAG = 5;
	public final static int SDO_PROPERTY_COUNT = 6;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcRoleImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcRoleImpl(Type type) {
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
	 * Returns the value of the '<em><b>Roletype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roletype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roletype</em>' attribute.
	 * @see #setRoletype(java.lang.String)
	 */
	public String getRoletype() {
		return DataUtil.toString(super.getByIndex(INDEX_ROLETYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRoletype <em>Roletype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Roletype</em>' attribute.
	 * @see #getRoletype()
	 */
	public void setRoletype(String roletype) {
		super.setByIndex(INDEX_ROLETYPE, roletype);
	}

	/**
	 * Returns the value of the '<em><b>Roledesc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roledesc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roledesc</em>' attribute.
	 * @see #setRoledesc(java.lang.String)
	 */
	public String getRoledesc() {
		return DataUtil.toString(super.getByIndex(INDEX_ROLEDESC, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRoledesc <em>Roledesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Roledesc</em>' attribute.
	 * @see #getRoledesc()
	 */
	public void setRoledesc(String roledesc) {
		super.setByIndex(INDEX_ROLEDESC, roledesc);
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
	 * @see #setAppid(int)
	 */
	public int getAppid() {
		return DataUtil.toInt(super.getByIndex(INDEX_APPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAppid <em>Appid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appid</em>' attribute.
	 * @see #getAppid()
	 */
	public void setAppid(int appid) {
		super.setByIndex(INDEX_APPID, appid);
	}

	/**
	 * Returns the value of the '<em><b>Initflag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initflag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initflag</em>' attribute.
	 * @see #setInitflag(java.lang.String)
	 */
	public String getInitflag() {
		return DataUtil.toString(super.getByIndex(INDEX_INITFLAG, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getInitflag <em>Initflag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initflag</em>' attribute.
	 * @see #getInitflag()
	 */
	public void setInitflag(String initflag) {
		super.setByIndex(INDEX_INITFLAG, initflag);
	}


}