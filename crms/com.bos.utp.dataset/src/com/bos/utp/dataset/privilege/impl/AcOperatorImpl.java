/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.privilege.impl;

import com.bos.utp.dataset.privilege.AcOperator;
import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getOperatorid <em>Operatorid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getUserid <em>Userid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getInvaldate <em>Invaldate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getOperatorname <em>Operatorname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getAuthmode <em>Authmode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getUnlocktime <em>Unlocktime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getMenutype <em>Menutype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getLastlogin <em>Lastlogin</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getErrcount <em>Errcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getStartdate <em>Startdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getEnddate <em>Enddate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getValidtime <em>Validtime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getMaccode <em>Maccode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getIpaddress <em>Ipaddress</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcOperatorImpl#getEmail <em>Email</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcOperator;
 */

public class AcOperatorImpl extends ExtendedDataObjectImpl implements AcOperator {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_OPERATORID = 0;
	public final static int INDEX_USERID = 1;
	public final static int INDEX_PASSWORD = 2;
	public final static int INDEX_INVALDATE = 3;
	public final static int INDEX_OPERATORNAME = 4;
	public final static int INDEX_AUTHMODE = 5;
	public final static int INDEX_STATUS = 6;
	public final static int INDEX_UNLOCKTIME = 7;
	public final static int INDEX_MENUTYPE = 8;
	public final static int INDEX_LASTLOGIN = 9;
	public final static int INDEX_ERRCOUNT = 10;
	public final static int INDEX_STARTDATE = 11;
	public final static int INDEX_ENDDATE = 12;
	public final static int INDEX_VALIDTIME = 13;
	public final static int INDEX_MACCODE = 14;
	public final static int INDEX_IPADDRESS = 15;
	public final static int INDEX_EMAIL = 16;
	public final static int SDO_PROPERTY_COUNT = 17;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcOperatorImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcOperatorImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
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
	 * @see #setOperatorid(java.math.BigDecimal)
	 */
	public BigDecimal getOperatorid() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_OPERATORID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOperatorid <em>Operatorid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operatorid</em>' attribute.
	 * @see #getOperatorid()
	 */
	public void setOperatorid(BigDecimal operatorid) {
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
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(java.lang.String)
	 */
	public String getPassword() {
		return DataUtil.toString(super.getByIndex(INDEX_PASSWORD, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 */
	public void setPassword(String password) {
		super.setByIndex(INDEX_PASSWORD, password);
	}

	/**
	 * Returns the value of the '<em><b>Invaldate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invaldate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invaldate</em>' attribute.
	 * @see #setInvaldate(java.util.Date)
	 */
	public Date getInvaldate() {
		return DataUtil.toDate(super.getByIndex(INDEX_INVALDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getInvaldate <em>Invaldate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invaldate</em>' attribute.
	 * @see #getInvaldate()
	 */
	public void setInvaldate(Date invaldate) {
		super.setByIndex(INDEX_INVALDATE, invaldate);
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
	 * Returns the value of the '<em><b>Authmode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authmode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authmode</em>' attribute.
	 * @see #setAuthmode(java.lang.String)
	 */
	public String getAuthmode() {
		return DataUtil.toString(super.getByIndex(INDEX_AUTHMODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAuthmode <em>Authmode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authmode</em>' attribute.
	 * @see #getAuthmode()
	 */
	public void setAuthmode(String authmode) {
		super.setByIndex(INDEX_AUTHMODE, authmode);
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
	 * Returns the value of the '<em><b>Unlocktime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unlocktime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unlocktime</em>' attribute.
	 * @see #setUnlocktime(java.util.Date)
	 */
	public Date getUnlocktime() {
		return DataUtil.toDate(super.getByIndex(INDEX_UNLOCKTIME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getUnlocktime <em>Unlocktime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unlocktime</em>' attribute.
	 * @see #getUnlocktime()
	 */
	public void setUnlocktime(Date unlocktime) {
		super.setByIndex(INDEX_UNLOCKTIME, unlocktime);
	}

	/**
	 * Returns the value of the '<em><b>Menutype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menutype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menutype</em>' attribute.
	 * @see #setMenutype(java.lang.String)
	 */
	public String getMenutype() {
		return DataUtil.toString(super.getByIndex(INDEX_MENUTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMenutype <em>Menutype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menutype</em>' attribute.
	 * @see #getMenutype()
	 */
	public void setMenutype(String menutype) {
		super.setByIndex(INDEX_MENUTYPE, menutype);
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
	 * Returns the value of the '<em><b>Errcount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Errcount</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Errcount</em>' attribute.
	 * @see #setErrcount(java.math.BigDecimal)
	 */
	public BigDecimal getErrcount() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_ERRCOUNT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getErrcount <em>Errcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Errcount</em>' attribute.
	 * @see #getErrcount()
	 */
	public void setErrcount(BigDecimal errcount) {
		super.setByIndex(INDEX_ERRCOUNT, errcount);
	}

	/**
	 * Returns the value of the '<em><b>Startdate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Startdate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Startdate</em>' attribute.
	 * @see #setStartdate(java.util.Date)
	 */
	public Date getStartdate() {
		return DataUtil.toDate(super.getByIndex(INDEX_STARTDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getStartdate <em>Startdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Startdate</em>' attribute.
	 * @see #getStartdate()
	 */
	public void setStartdate(Date startdate) {
		super.setByIndex(INDEX_STARTDATE, startdate);
	}

	/**
	 * Returns the value of the '<em><b>Enddate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enddate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enddate</em>' attribute.
	 * @see #setEnddate(java.util.Date)
	 */
	public Date getEnddate() {
		return DataUtil.toDate(super.getByIndex(INDEX_ENDDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEnddate <em>Enddate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enddate</em>' attribute.
	 * @see #getEnddate()
	 */
	public void setEnddate(Date enddate) {
		super.setByIndex(INDEX_ENDDATE, enddate);
	}

	/**
	 * Returns the value of the '<em><b>Validtime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validtime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Validtime</em>' attribute.
	 * @see #setValidtime(java.lang.String)
	 */
	public String getValidtime() {
		return DataUtil.toString(super.getByIndex(INDEX_VALIDTIME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getValidtime <em>Validtime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validtime</em>' attribute.
	 * @see #getValidtime()
	 */
	public void setValidtime(String validtime) {
		super.setByIndex(INDEX_VALIDTIME, validtime);
	}

	/**
	 * Returns the value of the '<em><b>Maccode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maccode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maccode</em>' attribute.
	 * @see #setMaccode(java.lang.String)
	 */
	public String getMaccode() {
		return DataUtil.toString(super.getByIndex(INDEX_MACCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMaccode <em>Maccode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maccode</em>' attribute.
	 * @see #getMaccode()
	 */
	public void setMaccode(String maccode) {
		super.setByIndex(INDEX_MACCODE, maccode);
	}

	/**
	 * Returns the value of the '<em><b>Ipaddress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ipaddress</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ipaddress</em>' attribute.
	 * @see #setIpaddress(java.lang.String)
	 */
	public String getIpaddress() {
		return DataUtil.toString(super.getByIndex(INDEX_IPADDRESS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIpaddress <em>Ipaddress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ipaddress</em>' attribute.
	 * @see #getIpaddress()
	 */
	public void setIpaddress(String ipaddress) {
		super.setByIndex(INDEX_IPADDRESS, ipaddress);
	}

	/**
	 * Returns the value of the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Email</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Email</em>' attribute.
	 * @see #setEmail(java.lang.String)
	 */
	public String getEmail() {
		return DataUtil.toString(super.getByIndex(INDEX_EMAIL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEmail <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email</em>' attribute.
	 * @see #getEmail()
	 */
	public void setEmail(String email) {
		super.setByIndex(INDEX_EMAIL, email);
	}


}