/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OmGroup;
import com.bos.utp.dataset.organization.OmOrganization;
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
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getGroupid <em>Groupid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getGrouplevel <em>Grouplevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getGroupname <em>Groupname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getGroupdesc <em>Groupdesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getGrouptype <em>Grouptype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getGroupseq <em>Groupseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getStartdate <em>Startdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getEnddate <em>Enddate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getGroupstatus <em>Groupstatus</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getCreatetime <em>Createtime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getLastupdate <em>Lastupdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getUpdator <em>Updator</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getOmGroup <em>OmGroup</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmGroupImpl#getOmOrganization <em>OmOrganization</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmGroup;
 */

public class OmGroupImpl extends ExtendedDataObjectImpl implements OmGroup {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_GROUPID = 0;
	public final static int INDEX_GROUPLEVEL = 1;
	public final static int INDEX_GROUPNAME = 2;
	public final static int INDEX_GROUPDESC = 3;
	public final static int INDEX_GROUPTYPE = 4;
	public final static int INDEX_GROUPSEQ = 5;
	public final static int INDEX_STARTDATE = 6;
	public final static int INDEX_ENDDATE = 7;
	public final static int INDEX_GROUPSTATUS = 8;
	public final static int INDEX_MANAGER = 9;
	public final static int INDEX_CREATETIME = 10;
	public final static int INDEX_LASTUPDATE = 11;
	public final static int INDEX_UPDATOR = 12;
	public final static int INDEX_ISLEAF = 13;
	public final static int INDEX_SUBCOUNT = 14;
	public final static int INDEX_OMGROUP = 15;
	public final static int INDEX_OMORGANIZATION = 16;
	public final static int SDO_PROPERTY_COUNT = 17;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmGroupImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmGroupImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
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
	 * Returns the value of the '<em><b>Grouplevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grouplevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grouplevel</em>' attribute.
	 * @see #setGrouplevel(int)
	 */
	public int getGrouplevel() {
		return DataUtil.toInt(super.getByIndex(INDEX_GROUPLEVEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGrouplevel <em>Grouplevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grouplevel</em>' attribute.
	 * @see #getGrouplevel()
	 */
	public void setGrouplevel(int grouplevel) {
		super.setByIndex(INDEX_GROUPLEVEL, grouplevel);
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
	 * Returns the value of the '<em><b>Groupdesc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groupdesc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groupdesc</em>' attribute.
	 * @see #setGroupdesc(java.lang.String)
	 */
	public String getGroupdesc() {
		return DataUtil.toString(super.getByIndex(INDEX_GROUPDESC, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGroupdesc <em>Groupdesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupdesc</em>' attribute.
	 * @see #getGroupdesc()
	 */
	public void setGroupdesc(String groupdesc) {
		super.setByIndex(INDEX_GROUPDESC, groupdesc);
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
	 * Returns the value of the '<em><b>Groupseq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groupseq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groupseq</em>' attribute.
	 * @see #setGroupseq(java.lang.String)
	 */
	public String getGroupseq() {
		return DataUtil.toString(super.getByIndex(INDEX_GROUPSEQ, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getGroupseq <em>Groupseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupseq</em>' attribute.
	 * @see #getGroupseq()
	 */
	public void setGroupseq(String groupseq) {
		super.setByIndex(INDEX_GROUPSEQ, groupseq);
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
	 * Returns the value of the '<em><b>Manager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manager</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manager</em>' attribute.
	 * @see #setManager(java.lang.String)
	 */
	public String getManager() {
		return DataUtil.toString(super.getByIndex(INDEX_MANAGER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getManager <em>Manager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager</em>' attribute.
	 * @see #getManager()
	 */
	public void setManager(String manager) {
		super.setByIndex(INDEX_MANAGER, manager);
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
	 * Returns the value of the '<em><b>Lastupdate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lastupdate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lastupdate</em>' attribute.
	 * @see #setLastupdate(java.util.Date)
	 */
	public Date getLastupdate() {
		return DataUtil.toDate(super.getByIndex(INDEX_LASTUPDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLastupdate <em>Lastupdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastupdate</em>' attribute.
	 * @see #getLastupdate()
	 */
	public void setLastupdate(Date lastupdate) {
		super.setByIndex(INDEX_LASTUPDATE, lastupdate);
	}

	/**
	 * Returns the value of the '<em><b>Updator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Updator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Updator</em>' attribute.
	 * @see #setUpdator(java.math.BigDecimal)
	 */
	public BigDecimal getUpdator() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_UPDATOR, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getUpdator <em>Updator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Updator</em>' attribute.
	 * @see #getUpdator()
	 */
	public void setUpdator(BigDecimal updator) {
		super.setByIndex(INDEX_UPDATOR, updator);
	}

	/**
	 * Returns the value of the '<em><b>Isleaf</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isleaf</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isleaf</em>' attribute.
	 * @see #setIsleaf(java.lang.String)
	 */
	public String getIsleaf() {
		return DataUtil.toString(super.getByIndex(INDEX_ISLEAF, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsleaf <em>Isleaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isleaf</em>' attribute.
	 * @see #getIsleaf()
	 */
	public void setIsleaf(String isleaf) {
		super.setByIndex(INDEX_ISLEAF, isleaf);
	}

	/**
	 * Returns the value of the '<em><b>Subcount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subcount</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subcount</em>' attribute.
	 * @see #setSubcount(java.math.BigDecimal)
	 */
	public BigDecimal getSubcount() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_SUBCOUNT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(BigDecimal subcount) {
		super.setByIndex(INDEX_SUBCOUNT, subcount);
	}

	/**
	 * Returns the value of the '<em><b>OmGroup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmGroup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmGroup</em>' attribute.
	 * @see #setOmGroup(com.bos.utp.dataset.organization.OmGroup)
	 */
	public OmGroup getOmGroup() {
		return (OmGroup) DataUtil.toDataObject(super.getByIndex(INDEX_OMGROUP, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmGroup <em>OmGroup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmGroup</em>' attribute.
	 * @see #getOmGroup()
	 */
	public void setOmGroup(OmGroup omGroup) {
		super.setByIndex(INDEX_OMGROUP, omGroup);
	}

	/**
	 * Returns the value of the '<em><b>OmOrganization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmOrganization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmOrganization</em>' attribute.
	 * @see #setOmOrganization(com.bos.utp.dataset.organization.OmOrganization)
	 */
	public OmOrganization getOmOrganization() {
		return (OmOrganization) DataUtil.toDataObject(super.getByIndex(INDEX_OMORGANIZATION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmOrganization <em>OmOrganization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmOrganization</em>' attribute.
	 * @see #getOmOrganization()
	 */
	public void setOmOrganization(OmOrganization omOrganization) {
		super.setByIndex(INDEX_OMORGANIZATION, omOrganization);
	}


}