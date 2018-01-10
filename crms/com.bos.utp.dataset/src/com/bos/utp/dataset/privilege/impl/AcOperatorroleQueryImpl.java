/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.privilege.impl;

import com.bos.utp.dataset.privilege.AcOperatorroleQuery;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleQueryImpl#getEmpname <em>Empname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleQueryImpl#getOperatorid <em>Operatorid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleQueryImpl#getRolename <em>Rolename</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleQueryImpl#getOrgname <em>Orgname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleQueryImpl#getIsmainorg <em>Ismainorg</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleQueryImpl#getOrglevel <em>Orglevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleQueryImpl#getOrgid <em>Orgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorroleQueryImpl#getRoleid <em>Roleid</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcOperatorroleQuery;
 */

public class AcOperatorroleQueryImpl extends ExtendedDataObjectImpl implements AcOperatorroleQuery {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_EMPNAME = 0;
	public final static int INDEX_OPERATORID = 1;
	public final static int INDEX_ROLENAME = 2;
	public final static int INDEX_ORGNAME = 3;
	public final static int INDEX_ISMAINORG = 4;
	public final static int INDEX_ORGLEVEL = 5;
	public final static int INDEX_ORGID = 6;
	public final static int INDEX_ROLEID = 7;
	public final static int SDO_PROPERTY_COUNT = 8;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcOperatorroleQueryImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcOperatorroleQueryImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Empname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Empname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Empname</em>' attribute.
	 * @see #setEmpname(java.lang.String)
	 */
	public String getEmpname() {
		return DataUtil.toString(super.getByIndex(INDEX_EMPNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEmpname <em>Empname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empname</em>' attribute.
	 * @see #getEmpname()
	 */
	public void setEmpname(String empname) {
		super.setByIndex(INDEX_EMPNAME, empname);
	}

	/**
	 * Returns the value of the '<em><b>Operatorid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operatorid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operatorid</em>' attribute.
	 * @see #setOperatorid(long)
	 */
	public long getOperatorid() {
		return DataUtil.toLong(super.getByIndex(INDEX_OPERATORID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOperatorid <em>Operatorid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operatorid</em>' attribute.
	 * @see #getOperatorid()
	 */
	public void setOperatorid(long operatorid) {
		super.setByIndex(INDEX_OPERATORID, operatorid);
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

	/**
	 * Returns the value of the '<em><b>Ismainorg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ismainorg</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ismainorg</em>' attribute.
	 * @see #setIsmainorg(java.lang.String)
	 */
	public String getIsmainorg() {
		return DataUtil.toString(super.getByIndex(INDEX_ISMAINORG, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsmainorg <em>Ismainorg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ismainorg</em>' attribute.
	 * @see #getIsmainorg()
	 */
	public void setIsmainorg(String ismainorg) {
		super.setByIndex(INDEX_ISMAINORG, ismainorg);
	}

	/**
	 * Returns the value of the '<em><b>Orglevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orglevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orglevel</em>' attribute.
	 * @see #setOrglevel(int)
	 */
	public int getOrglevel() {
		return DataUtil.toInt(super.getByIndex(INDEX_ORGLEVEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrglevel <em>Orglevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orglevel</em>' attribute.
	 * @see #getOrglevel()
	 */
	public void setOrglevel(int orglevel) {
		super.setByIndex(INDEX_ORGLEVEL, orglevel);
	}

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
	public int getOrgid() {
		return DataUtil.toInt(super.getByIndex(INDEX_ORGID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgid <em>Orgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgid</em>' attribute.
	 * @see #getOrgid()
	 */
	public void setOrgid(int orgid) {
		super.setByIndex(INDEX_ORGID, orgid);
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


}