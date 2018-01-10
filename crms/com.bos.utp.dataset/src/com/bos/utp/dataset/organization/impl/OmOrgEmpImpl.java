/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OmOrgEmp;
import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getInorgid <em>Inorgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getInorgname <em>Inorgname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getOrglevel <em>Orglevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getOrgseq <em>Orgseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getEmpid <em>Empid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getEmpcode <em>Empcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getEmpname <em>Empname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getGender <em>Gender</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getEmpstatus <em>Empstatus</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getOperatorid <em>Operatorid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getUserid <em>Userid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getOperatorname <em>Operatorname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getPositionseq <em>Positionseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getPosicode <em>Posicode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getPosiname <em>Posiname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getLastlogin <em>Lastlogin</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmOrgEmpImpl#getRolename <em>Rolename</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmOrgEmp;
 */

public class OmOrgEmpImpl extends ExtendedDataObjectImpl implements OmOrgEmp {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_INORGID = 0;
	public final static int INDEX_INORGNAME = 1;
	public final static int INDEX_ORGLEVEL = 2;
	public final static int INDEX_ORGSEQ = 3;
	public final static int INDEX_EMPID = 4;
	public final static int INDEX_EMPCODE = 5;
	public final static int INDEX_EMPNAME = 6;
	public final static int INDEX_GENDER = 7;
	public final static int INDEX_EMPSTATUS = 8;
	public final static int INDEX_OPERATORID = 9;
	public final static int INDEX_USERID = 10;
	public final static int INDEX_OPERATORNAME = 11;
	public final static int INDEX_STATUS = 12;
	public final static int INDEX_POSITIONSEQ = 13;
	public final static int INDEX_POSICODE = 14;
	public final static int INDEX_POSINAME = 15;
	public final static int INDEX_LASTLOGIN = 16;
	public final static int INDEX_ROLENAME = 17;
	public final static int SDO_PROPERTY_COUNT = 18;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmOrgEmpImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmOrgEmpImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Inorgid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inorgid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inorgid</em>' attribute.
	 * @see #setInorgid(java.lang.String)
	 */
	public String getInorgid() {
		return DataUtil.toString(super.getByIndex(INDEX_INORGID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getInorgid <em>Inorgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inorgid</em>' attribute.
	 * @see #getInorgid()
	 */
	public void setInorgid(String inorgid) {
		super.setByIndex(INDEX_INORGID, inorgid);
	}

	/**
	 * Returns the value of the '<em><b>Inorgname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inorgname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inorgname</em>' attribute.
	 * @see #setInorgname(java.lang.String)
	 */
	public String getInorgname() {
		return DataUtil.toString(super.getByIndex(INDEX_INORGNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getInorgname <em>Inorgname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inorgname</em>' attribute.
	 * @see #getInorgname()
	 */
	public void setInorgname(String inorgname) {
		super.setByIndex(INDEX_INORGNAME, inorgname);
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
	 * Returns the value of the '<em><b>Orgseq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgseq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgseq</em>' attribute.
	 * @see #setOrgseq(java.lang.String)
	 */
	public String getOrgseq() {
		return DataUtil.toString(super.getByIndex(INDEX_ORGSEQ, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgseq <em>Orgseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgseq</em>' attribute.
	 * @see #getOrgseq()
	 */
	public void setOrgseq(String orgseq) {
		super.setByIndex(INDEX_ORGSEQ, orgseq);
	}

	/**
	 * Returns the value of the '<em><b>Empid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Empid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Empid</em>' attribute.
	 * @see #setEmpid(int)
	 */
	public int getEmpid() {
		return DataUtil.toInt(super.getByIndex(INDEX_EMPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEmpid <em>Empid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empid</em>' attribute.
	 * @see #getEmpid()
	 */
	public void setEmpid(int empid) {
		super.setByIndex(INDEX_EMPID, empid);
	}

	/**
	 * Returns the value of the '<em><b>Empcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Empcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Empcode</em>' attribute.
	 * @see #setEmpcode(java.lang.String)
	 */
	public String getEmpcode() {
		return DataUtil.toString(super.getByIndex(INDEX_EMPCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEmpcode <em>Empcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empcode</em>' attribute.
	 * @see #getEmpcode()
	 */
	public void setEmpcode(String empcode) {
		super.setByIndex(INDEX_EMPCODE, empcode);
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
	 * Returns the value of the '<em><b>Gender</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gender</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gender</em>' attribute.
	 * @see #setGender(java.lang.String)
	 */
	public String getGender() {
		return DataUtil.toString(super.getByIndex(INDEX_GENDER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGender <em>Gender</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gender</em>' attribute.
	 * @see #getGender()
	 */
	public void setGender(String gender) {
		super.setByIndex(INDEX_GENDER, gender);
	}

	/**
	 * Returns the value of the '<em><b>Empstatus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Empstatus</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Empstatus</em>' attribute.
	 * @see #setEmpstatus(java.lang.String)
	 */
	public String getEmpstatus() {
		return DataUtil.toString(super.getByIndex(INDEX_EMPSTATUS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEmpstatus <em>Empstatus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empstatus</em>' attribute.
	 * @see #getEmpstatus()
	 */
	public void setEmpstatus(String empstatus) {
		super.setByIndex(INDEX_EMPSTATUS, empstatus);
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
	 * Returns the value of the '<em><b>Userid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Userid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Userid</em>' attribute.
	 * @see #setUserid(java.lang.String)
	 */
	public String getUserid() {
		return DataUtil.toString(super.getByIndex(INDEX_USERID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getUserid <em>Userid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Userid</em>' attribute.
	 * @see #getUserid()
	 */
	public void setUserid(String userid) {
		super.setByIndex(INDEX_USERID, userid);
	}

	/**
	 * Returns the value of the '<em><b>Operatorname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operatorname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operatorname</em>' attribute.
	 * @see #setOperatorname(java.lang.String)
	 */
	public String getOperatorname() {
		return DataUtil.toString(super.getByIndex(INDEX_OPERATORNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOperatorname <em>Operatorname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operatorname</em>' attribute.
	 * @see #getOperatorname()
	 */
	public void setOperatorname(String operatorname) {
		super.setByIndex(INDEX_OPERATORNAME, operatorname);
	}

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(java.lang.String)
	 */
	public String getStatus() {
		return DataUtil.toString(super.getByIndex(INDEX_STATUS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 */
	public void setStatus(String status) {
		super.setByIndex(INDEX_STATUS, status);
	}

	/**
	 * Returns the value of the '<em><b>Positionseq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Positionseq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Positionseq</em>' attribute.
	 * @see #setPositionseq(java.lang.String)
	 */
	public String getPositionseq() {
		return DataUtil.toString(super.getByIndex(INDEX_POSITIONSEQ, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPositionseq <em>Positionseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Positionseq</em>' attribute.
	 * @see #getPositionseq()
	 */
	public void setPositionseq(String positionseq) {
		super.setByIndex(INDEX_POSITIONSEQ, positionseq);
	}

	/**
	 * Returns the value of the '<em><b>Posicode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Posicode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Posicode</em>' attribute.
	 * @see #setPosicode(java.lang.String)
	 */
	public String getPosicode() {
		return DataUtil.toString(super.getByIndex(INDEX_POSICODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPosicode <em>Posicode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Posicode</em>' attribute.
	 * @see #getPosicode()
	 */
	public void setPosicode(String posicode) {
		super.setByIndex(INDEX_POSICODE, posicode);
	}

	/**
	 * Returns the value of the '<em><b>Posiname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Posiname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Posiname</em>' attribute.
	 * @see #setPosiname(java.lang.String)
	 */
	public String getPosiname() {
		return DataUtil.toString(super.getByIndex(INDEX_POSINAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPosiname <em>Posiname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Posiname</em>' attribute.
	 * @see #getPosiname()
	 */
	public void setPosiname(String posiname) {
		super.setByIndex(INDEX_POSINAME, posiname);
	}

	/**
	 * Returns the value of the '<em><b>Lastlogin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lastlogin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lastlogin</em>' attribute.
	 * @see #setLastlogin(java.util.Date)
	 */
	public Date getLastlogin() {
		return DataUtil.toDate(super.getByIndex(INDEX_LASTLOGIN, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLastlogin <em>Lastlogin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastlogin</em>' attribute.
	 * @see #getLastlogin()
	 */
	public void setLastlogin(Date lastlogin) {
		super.setByIndex(INDEX_LASTLOGIN, lastlogin);
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


}