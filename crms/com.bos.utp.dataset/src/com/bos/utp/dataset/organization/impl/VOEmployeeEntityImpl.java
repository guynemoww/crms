/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.VOEmployeeEntity;
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
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getEmpid <em>Empid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getEmpcode <em>Empcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getOperatorid <em>Operatorid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getUserid <em>Userid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getEmpname <em>Empname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getRealname <em>Realname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getGender <em>Gender</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getBirthdate <em>Birthdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getEmpstatus <em>Empstatus</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getCardtype <em>Cardtype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getCardno <em>Cardno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getIndate <em>Indate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getOutdate <em>Outdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getOtel <em>Otel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getOaddress <em>Oaddress</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getOzipcode <em>Ozipcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getOemail <em>Oemail</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getFaxno <em>Faxno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getMobileno <em>Mobileno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getMsn <em>Msn</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getHtel <em>Htel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getHaddress <em>Haddress</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getHzipcode <em>Hzipcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getPemail <em>Pemail</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getParty <em>Party</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getDegree <em>Degree</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getMajor <em>Major</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getSpecialty <em>Specialty</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getWorkexp <em>Workexp</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getRegdate <em>Regdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getCreatetime <em>Createtime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getLastmodytime <em>Lastmodytime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getOrgidlist <em>Orgidlist</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getOrgid <em>Orgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getRemark <em>Remark</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getEducation <em>Education</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getLicenseno <em>Licenseno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getIntotradedate <em>Intotradedate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getRunmarkdate <em>Runmarkdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.VOEmployeeEntityImpl#getEmplevel <em>Emplevel</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements VOEmployeeEntity;
 */

public class VOEmployeeEntityImpl extends ExtendedDataObjectImpl implements VOEmployeeEntity {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_EMPID = 0;
	public final static int INDEX_EMPCODE = 1;
	public final static int INDEX_OPERATORID = 2;
	public final static int INDEX_USERID = 3;
	public final static int INDEX_EMPNAME = 4;
	public final static int INDEX_REALNAME = 5;
	public final static int INDEX_GENDER = 6;
	public final static int INDEX_BIRTHDATE = 7;
	public final static int INDEX_POSITION = 8;
	public final static int INDEX_EMPSTATUS = 9;
	public final static int INDEX_CARDTYPE = 10;
	public final static int INDEX_CARDNO = 11;
	public final static int INDEX_INDATE = 12;
	public final static int INDEX_OUTDATE = 13;
	public final static int INDEX_OTEL = 14;
	public final static int INDEX_OADDRESS = 15;
	public final static int INDEX_OZIPCODE = 16;
	public final static int INDEX_OEMAIL = 17;
	public final static int INDEX_FAXNO = 18;
	public final static int INDEX_MOBILENO = 19;
	public final static int INDEX_MSN = 20;
	public final static int INDEX_HTEL = 21;
	public final static int INDEX_HADDRESS = 22;
	public final static int INDEX_HZIPCODE = 23;
	public final static int INDEX_PEMAIL = 24;
	public final static int INDEX_PARTY = 25;
	public final static int INDEX_DEGREE = 26;
	public final static int INDEX_MAJOR = 27;
	public final static int INDEX_SPECIALTY = 28;
	public final static int INDEX_WORKEXP = 29;
	public final static int INDEX_REGDATE = 30;
	public final static int INDEX_CREATETIME = 31;
	public final static int INDEX_LASTMODYTIME = 32;
	public final static int INDEX_ORGIDLIST = 33;
	public final static int INDEX_ORGID = 34;
	public final static int INDEX_REMARK = 35;
	public final static int INDEX_EDUCATION = 36;
	public final static int INDEX_LICENSENO = 37;
	public final static int INDEX_INTOTRADEDATE = 38;
	public final static int INDEX_RUNMARKDATE = 39;
	public final static int INDEX_EMPLEVEL = 40;
	public final static int SDO_PROPERTY_COUNT = 41;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public VOEmployeeEntityImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public VOEmployeeEntityImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
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
	 * Returns the value of the '<em><b>Realname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realname</em>' attribute.
	 * @see #setRealname(java.lang.String)
	 */
	public String getRealname() {
		return DataUtil.toString(super.getByIndex(INDEX_REALNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRealname <em>Realname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Realname</em>' attribute.
	 * @see #getRealname()
	 */
	public void setRealname(String realname) {
		super.setByIndex(INDEX_REALNAME, realname);
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
	 * Returns the value of the '<em><b>Birthdate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Birthdate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Birthdate</em>' attribute.
	 * @see #setBirthdate(java.util.Date)
	 */
	public Date getBirthdate() {
		return DataUtil.toDate(super.getByIndex(INDEX_BIRTHDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBirthdate <em>Birthdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Birthdate</em>' attribute.
	 * @see #getBirthdate()
	 */
	public void setBirthdate(Date birthdate) {
		super.setByIndex(INDEX_BIRTHDATE, birthdate);
	}

	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see #setPosition(int)
	 */
	public int getPosition() {
		return DataUtil.toInt(super.getByIndex(INDEX_POSITION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 */
	public void setPosition(int position) {
		super.setByIndex(INDEX_POSITION, position);
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
	 * Returns the value of the '<em><b>Cardtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cardtype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cardtype</em>' attribute.
	 * @see #setCardtype(java.lang.String)
	 */
	public String getCardtype() {
		return DataUtil.toString(super.getByIndex(INDEX_CARDTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCardtype <em>Cardtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cardtype</em>' attribute.
	 * @see #getCardtype()
	 */
	public void setCardtype(String cardtype) {
		super.setByIndex(INDEX_CARDTYPE, cardtype);
	}

	/**
	 * Returns the value of the '<em><b>Cardno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cardno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cardno</em>' attribute.
	 * @see #setCardno(java.lang.String)
	 */
	public String getCardno() {
		return DataUtil.toString(super.getByIndex(INDEX_CARDNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCardno <em>Cardno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cardno</em>' attribute.
	 * @see #getCardno()
	 */
	public void setCardno(String cardno) {
		super.setByIndex(INDEX_CARDNO, cardno);
	}

	/**
	 * Returns the value of the '<em><b>Indate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indate</em>' attribute.
	 * @see #setIndate(java.util.Date)
	 */
	public Date getIndate() {
		return DataUtil.toDate(super.getByIndex(INDEX_INDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIndate <em>Indate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indate</em>' attribute.
	 * @see #getIndate()
	 */
	public void setIndate(Date indate) {
		super.setByIndex(INDEX_INDATE, indate);
	}

	/**
	 * Returns the value of the '<em><b>Outdate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outdate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outdate</em>' attribute.
	 * @see #setOutdate(java.util.Date)
	 */
	public Date getOutdate() {
		return DataUtil.toDate(super.getByIndex(INDEX_OUTDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOutdate <em>Outdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outdate</em>' attribute.
	 * @see #getOutdate()
	 */
	public void setOutdate(Date outdate) {
		super.setByIndex(INDEX_OUTDATE, outdate);
	}

	/**
	 * Returns the value of the '<em><b>Otel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Otel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Otel</em>' attribute.
	 * @see #setOtel(java.lang.String)
	 */
	public String getOtel() {
		return DataUtil.toString(super.getByIndex(INDEX_OTEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOtel <em>Otel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Otel</em>' attribute.
	 * @see #getOtel()
	 */
	public void setOtel(String otel) {
		super.setByIndex(INDEX_OTEL, otel);
	}

	/**
	 * Returns the value of the '<em><b>Oaddress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Oaddress</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Oaddress</em>' attribute.
	 * @see #setOaddress(java.lang.String)
	 */
	public String getOaddress() {
		return DataUtil.toString(super.getByIndex(INDEX_OADDRESS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOaddress <em>Oaddress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Oaddress</em>' attribute.
	 * @see #getOaddress()
	 */
	public void setOaddress(String oaddress) {
		super.setByIndex(INDEX_OADDRESS, oaddress);
	}

	/**
	 * Returns the value of the '<em><b>Ozipcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ozipcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ozipcode</em>' attribute.
	 * @see #setOzipcode(java.lang.String)
	 */
	public String getOzipcode() {
		return DataUtil.toString(super.getByIndex(INDEX_OZIPCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOzipcode <em>Ozipcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ozipcode</em>' attribute.
	 * @see #getOzipcode()
	 */
	public void setOzipcode(String ozipcode) {
		super.setByIndex(INDEX_OZIPCODE, ozipcode);
	}

	/**
	 * Returns the value of the '<em><b>Oemail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Oemail</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Oemail</em>' attribute.
	 * @see #setOemail(java.lang.String)
	 */
	public String getOemail() {
		return DataUtil.toString(super.getByIndex(INDEX_OEMAIL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOemail <em>Oemail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Oemail</em>' attribute.
	 * @see #getOemail()
	 */
	public void setOemail(String oemail) {
		super.setByIndex(INDEX_OEMAIL, oemail);
	}

	/**
	 * Returns the value of the '<em><b>Faxno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Faxno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Faxno</em>' attribute.
	 * @see #setFaxno(java.lang.String)
	 */
	public String getFaxno() {
		return DataUtil.toString(super.getByIndex(INDEX_FAXNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFaxno <em>Faxno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Faxno</em>' attribute.
	 * @see #getFaxno()
	 */
	public void setFaxno(String faxno) {
		super.setByIndex(INDEX_FAXNO, faxno);
	}

	/**
	 * Returns the value of the '<em><b>Mobileno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mobileno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mobileno</em>' attribute.
	 * @see #setMobileno(java.lang.String)
	 */
	public String getMobileno() {
		return DataUtil.toString(super.getByIndex(INDEX_MOBILENO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMobileno <em>Mobileno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mobileno</em>' attribute.
	 * @see #getMobileno()
	 */
	public void setMobileno(String mobileno) {
		super.setByIndex(INDEX_MOBILENO, mobileno);
	}

	/**
	 * Returns the value of the '<em><b>Msn</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Msn</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Msn</em>' attribute.
	 * @see #setMsn(java.lang.String)
	 */
	public String getMsn() {
		return DataUtil.toString(super.getByIndex(INDEX_MSN, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMsn <em>Msn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Msn</em>' attribute.
	 * @see #getMsn()
	 */
	public void setMsn(String msn) {
		super.setByIndex(INDEX_MSN, msn);
	}

	/**
	 * Returns the value of the '<em><b>Htel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Htel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Htel</em>' attribute.
	 * @see #setHtel(java.lang.String)
	 */
	public String getHtel() {
		return DataUtil.toString(super.getByIndex(INDEX_HTEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getHtel <em>Htel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Htel</em>' attribute.
	 * @see #getHtel()
	 */
	public void setHtel(String htel) {
		super.setByIndex(INDEX_HTEL, htel);
	}

	/**
	 * Returns the value of the '<em><b>Haddress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Haddress</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Haddress</em>' attribute.
	 * @see #setHaddress(java.lang.String)
	 */
	public String getHaddress() {
		return DataUtil.toString(super.getByIndex(INDEX_HADDRESS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getHaddress <em>Haddress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Haddress</em>' attribute.
	 * @see #getHaddress()
	 */
	public void setHaddress(String haddress) {
		super.setByIndex(INDEX_HADDRESS, haddress);
	}

	/**
	 * Returns the value of the '<em><b>Hzipcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hzipcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hzipcode</em>' attribute.
	 * @see #setHzipcode(java.lang.String)
	 */
	public String getHzipcode() {
		return DataUtil.toString(super.getByIndex(INDEX_HZIPCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getHzipcode <em>Hzipcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hzipcode</em>' attribute.
	 * @see #getHzipcode()
	 */
	public void setHzipcode(String hzipcode) {
		super.setByIndex(INDEX_HZIPCODE, hzipcode);
	}

	/**
	 * Returns the value of the '<em><b>Pemail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pemail</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pemail</em>' attribute.
	 * @see #setPemail(java.lang.String)
	 */
	public String getPemail() {
		return DataUtil.toString(super.getByIndex(INDEX_PEMAIL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPemail <em>Pemail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pemail</em>' attribute.
	 * @see #getPemail()
	 */
	public void setPemail(String pemail) {
		super.setByIndex(INDEX_PEMAIL, pemail);
	}

	/**
	 * Returns the value of the '<em><b>Party</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Party</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Party</em>' attribute.
	 * @see #setParty(java.lang.String)
	 */
	public String getParty() {
		return DataUtil.toString(super.getByIndex(INDEX_PARTY, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getParty <em>Party</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Party</em>' attribute.
	 * @see #getParty()
	 */
	public void setParty(String party) {
		super.setByIndex(INDEX_PARTY, party);
	}

	/**
	 * Returns the value of the '<em><b>Degree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Degree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Degree</em>' attribute.
	 * @see #setDegree(java.lang.String)
	 */
	public String getDegree() {
		return DataUtil.toString(super.getByIndex(INDEX_DEGREE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDegree <em>Degree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Degree</em>' attribute.
	 * @see #getDegree()
	 */
	public void setDegree(String degree) {
		super.setByIndex(INDEX_DEGREE, degree);
	}

	/**
	 * Returns the value of the '<em><b>Major</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Major</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Major</em>' attribute.
	 * @see #setMajor(int)
	 */
	public int getMajor() {
		return DataUtil.toInt(super.getByIndex(INDEX_MAJOR, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMajor <em>Major</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Major</em>' attribute.
	 * @see #getMajor()
	 */
	public void setMajor(int major) {
		super.setByIndex(INDEX_MAJOR, major);
	}

	/**
	 * Returns the value of the '<em><b>Specialty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specialty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specialty</em>' attribute.
	 * @see #setSpecialty(java.lang.String)
	 */
	public String getSpecialty() {
		return DataUtil.toString(super.getByIndex(INDEX_SPECIALTY, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSpecialty <em>Specialty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specialty</em>' attribute.
	 * @see #getSpecialty()
	 */
	public void setSpecialty(String specialty) {
		super.setByIndex(INDEX_SPECIALTY, specialty);
	}

	/**
	 * Returns the value of the '<em><b>Workexp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workexp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workexp</em>' attribute.
	 * @see #setWorkexp(java.lang.String)
	 */
	public String getWorkexp() {
		return DataUtil.toString(super.getByIndex(INDEX_WORKEXP, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getWorkexp <em>Workexp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workexp</em>' attribute.
	 * @see #getWorkexp()
	 */
	public void setWorkexp(String workexp) {
		super.setByIndex(INDEX_WORKEXP, workexp);
	}

	/**
	 * Returns the value of the '<em><b>Regdate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regdate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regdate</em>' attribute.
	 * @see #setRegdate(java.util.Date)
	 */
	public Date getRegdate() {
		return DataUtil.toDate(super.getByIndex(INDEX_REGDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRegdate <em>Regdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Regdate</em>' attribute.
	 * @see #getRegdate()
	 */
	public void setRegdate(Date regdate) {
		super.setByIndex(INDEX_REGDATE, regdate);
	}

	/**
	 * Returns the value of the '<em><b>Createtime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Createtime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Createtime</em>' attribute.
	 * @see #setCreatetime(java.util.Date)
	 */
	public Date getCreatetime() {
		return DataUtil.toDate(super.getByIndex(INDEX_CREATETIME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCreatetime <em>Createtime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Createtime</em>' attribute.
	 * @see #getCreatetime()
	 */
	public void setCreatetime(Date createtime) {
		super.setByIndex(INDEX_CREATETIME, createtime);
	}

	/**
	 * Returns the value of the '<em><b>Lastmodytime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lastmodytime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lastmodytime</em>' attribute.
	 * @see #setLastmodytime(java.util.Date)
	 */
	public Date getLastmodytime() {
		return DataUtil.toDate(super.getByIndex(INDEX_LASTMODYTIME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLastmodytime <em>Lastmodytime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastmodytime</em>' attribute.
	 * @see #getLastmodytime()
	 */
	public void setLastmodytime(Date lastmodytime) {
		super.setByIndex(INDEX_LASTMODYTIME, lastmodytime);
	}

	/**
	 * Returns the value of the '<em><b>Orgidlist</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgidlist</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgidlist</em>' attribute.
	 * @see #setOrgidlist(java.lang.String)
	 */
	public String getOrgidlist() {
		return DataUtil.toString(super.getByIndex(INDEX_ORGIDLIST, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgidlist <em>Orgidlist</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgidlist</em>' attribute.
	 * @see #getOrgidlist()
	 */
	public void setOrgidlist(String orgidlist) {
		super.setByIndex(INDEX_ORGIDLIST, orgidlist);
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
	 * Returns the value of the '<em><b>Remark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remark</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remark</em>' attribute.
	 * @see #setRemark(java.lang.String)
	 */
	public String getRemark() {
		return DataUtil.toString(super.getByIndex(INDEX_REMARK, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRemark <em>Remark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remark</em>' attribute.
	 * @see #getRemark()
	 */
	public void setRemark(String remark) {
		super.setByIndex(INDEX_REMARK, remark);
	}

	/**
	 * Returns the value of the '<em><b>Education</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Education</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Education</em>' attribute.
	 * @see #setEducation(java.lang.String)
	 */
	public String getEducation() {
		return DataUtil.toString(super.getByIndex(INDEX_EDUCATION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEducation <em>Education</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Education</em>' attribute.
	 * @see #getEducation()
	 */
	public void setEducation(String education) {
		super.setByIndex(INDEX_EDUCATION, education);
	}

	/**
	 * Returns the value of the '<em><b>Licenseno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Licenseno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Licenseno</em>' attribute.
	 * @see #setLicenseno(java.lang.String)
	 */
	public String getLicenseno() {
		return DataUtil.toString(super.getByIndex(INDEX_LICENSENO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLicenseno <em>Licenseno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Licenseno</em>' attribute.
	 * @see #getLicenseno()
	 */
	public void setLicenseno(String licenseno) {
		super.setByIndex(INDEX_LICENSENO, licenseno);
	}

	/**
	 * Returns the value of the '<em><b>Intotradedate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intotradedate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intotradedate</em>' attribute.
	 * @see #setIntotradedate(java.util.Date)
	 */
	public Date getIntotradedate() {
		return DataUtil.toDate(super.getByIndex(INDEX_INTOTRADEDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIntotradedate <em>Intotradedate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intotradedate</em>' attribute.
	 * @see #getIntotradedate()
	 */
	public void setIntotradedate(Date intotradedate) {
		super.setByIndex(INDEX_INTOTRADEDATE, intotradedate);
	}

	/**
	 * Returns the value of the '<em><b>Runmarkdate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Runmarkdate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Runmarkdate</em>' attribute.
	 * @see #setRunmarkdate(java.util.Date)
	 */
	public Date getRunmarkdate() {
		return DataUtil.toDate(super.getByIndex(INDEX_RUNMARKDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRunmarkdate <em>Runmarkdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Runmarkdate</em>' attribute.
	 * @see #getRunmarkdate()
	 */
	public void setRunmarkdate(Date runmarkdate) {
		super.setByIndex(INDEX_RUNMARKDATE, runmarkdate);
	}

	/**
	 * Returns the value of the '<em><b>Emplevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Emplevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Emplevel</em>' attribute.
	 * @see #setEmplevel(java.lang.String)
	 */
	public String getEmplevel() {
		return DataUtil.toString(super.getByIndex(INDEX_EMPLEVEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getEmplevel <em>Emplevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Emplevel</em>' attribute.
	 * @see #getEmplevel()
	 */
	public void setEmplevel(String emplevel) {
		super.setByIndex(INDEX_EMPLEVEL, emplevel);
	}


}