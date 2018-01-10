/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.QueryOrgEntity;
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
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getOrgid <em>Orgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getOrgcode <em>Orgcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getOrgname <em>Orgname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getOrglevel <em>Orglevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getOrgdegree <em>Orgdegree</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getParentorgid <em>Parentorgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getOrgseq <em>Orgseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getOrgtype <em>Orgtype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getOrgaddr <em>Orgaddr</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getZipcode <em>Zipcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getManaposition <em>Manaposition</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getManagerid <em>Managerid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getOrgmanager <em>Orgmanager</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getLinkman <em>Linkman</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getLinktel <em>Linktel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getEmail <em>Email</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getWeburl <em>Weburl</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getStartdate <em>Startdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getEnddate <em>Enddate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getArea <em>Area</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getCreatetime <em>Createtime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getLastupdate <em>Lastupdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getUpdator <em>Updator</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getSortno <em>Sortno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getRemark <em>Remark</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getBuno <em>Buno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getAuditbankno <em>Auditbankno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getPaymentsysno <em>Paymentsysno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getParentorgcode <em>Parentorgcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.QueryOrgEntityImpl#getIsteam <em>Isteam</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements QueryOrgEntity;
 */

public class QueryOrgEntityImpl extends ExtendedDataObjectImpl implements QueryOrgEntity {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_ORGID = 0;
	public final static int INDEX_ORGCODE = 1;
	public final static int INDEX_ORGNAME = 2;
	public final static int INDEX_ORGLEVEL = 3;
	public final static int INDEX_ORGDEGREE = 4;
	public final static int INDEX_PARENTORGID = 5;
	public final static int INDEX_ORGSEQ = 6;
	public final static int INDEX_ORGTYPE = 7;
	public final static int INDEX_ORGADDR = 8;
	public final static int INDEX_ZIPCODE = 9;
	public final static int INDEX_MANAPOSITION = 10;
	public final static int INDEX_MANAGERID = 11;
	public final static int INDEX_ORGMANAGER = 12;
	public final static int INDEX_LINKMAN = 13;
	public final static int INDEX_LINKTEL = 14;
	public final static int INDEX_EMAIL = 15;
	public final static int INDEX_WEBURL = 16;
	public final static int INDEX_STARTDATE = 17;
	public final static int INDEX_ENDDATE = 18;
	public final static int INDEX_STATUS = 19;
	public final static int INDEX_AREA = 20;
	public final static int INDEX_CREATETIME = 21;
	public final static int INDEX_LASTUPDATE = 22;
	public final static int INDEX_UPDATOR = 23;
	public final static int INDEX_SORTNO = 24;
	public final static int INDEX_ISLEAF = 25;
	public final static int INDEX_SUBCOUNT = 26;
	public final static int INDEX_REMARK = 27;
	public final static int INDEX_BUNO = 28;
	public final static int INDEX_AUDITBANKNO = 29;
	public final static int INDEX_PAYMENTSYSNO = 30;
	public final static int INDEX_PARENTORGCODE = 31;
	public final static int INDEX_ISTEAM = 32;
	public final static int SDO_PROPERTY_COUNT = 33;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public QueryOrgEntityImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public QueryOrgEntityImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
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
	 * Returns the value of the '<em><b>Orgcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgcode</em>' attribute.
	 * @see #setOrgcode(java.lang.String)
	 */
	public String getOrgcode() {
		return DataUtil.toString(super.getByIndex(INDEX_ORGCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgcode <em>Orgcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgcode</em>' attribute.
	 * @see #getOrgcode()
	 */
	public void setOrgcode(String orgcode) {
		super.setByIndex(INDEX_ORGCODE, orgcode);
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
	 * Returns the value of the '<em><b>Orgdegree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgdegree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgdegree</em>' attribute.
	 * @see #setOrgdegree(java.lang.String)
	 */
	public String getOrgdegree() {
		return DataUtil.toString(super.getByIndex(INDEX_ORGDEGREE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgdegree <em>Orgdegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgdegree</em>' attribute.
	 * @see #getOrgdegree()
	 */
	public void setOrgdegree(String orgdegree) {
		super.setByIndex(INDEX_ORGDEGREE, orgdegree);
	}

	/**
	 * Returns the value of the '<em><b>Parentorgid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parentorgid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parentorgid</em>' attribute.
	 * @see #setParentorgid(int)
	 */
	public int getParentorgid() {
		return DataUtil.toInt(super.getByIndex(INDEX_PARENTORGID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getParentorgid <em>Parentorgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parentorgid</em>' attribute.
	 * @see #getParentorgid()
	 */
	public void setParentorgid(int parentorgid) {
		super.setByIndex(INDEX_PARENTORGID, parentorgid);
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
	 * Returns the value of the '<em><b>Orgtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgtype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgtype</em>' attribute.
	 * @see #setOrgtype(java.lang.String)
	 */
	public String getOrgtype() {
		return DataUtil.toString(super.getByIndex(INDEX_ORGTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgtype <em>Orgtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgtype</em>' attribute.
	 * @see #getOrgtype()
	 */
	public void setOrgtype(String orgtype) {
		super.setByIndex(INDEX_ORGTYPE, orgtype);
	}

	/**
	 * Returns the value of the '<em><b>Orgaddr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgaddr</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgaddr</em>' attribute.
	 * @see #setOrgaddr(java.lang.String)
	 */
	public String getOrgaddr() {
		return DataUtil.toString(super.getByIndex(INDEX_ORGADDR, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgaddr <em>Orgaddr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgaddr</em>' attribute.
	 * @see #getOrgaddr()
	 */
	public void setOrgaddr(String orgaddr) {
		super.setByIndex(INDEX_ORGADDR, orgaddr);
	}

	/**
	 * Returns the value of the '<em><b>Zipcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Zipcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Zipcode</em>' attribute.
	 * @see #setZipcode(java.lang.String)
	 */
	public String getZipcode() {
		return DataUtil.toString(super.getByIndex(INDEX_ZIPCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getZipcode <em>Zipcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zipcode</em>' attribute.
	 * @see #getZipcode()
	 */
	public void setZipcode(String zipcode) {
		super.setByIndex(INDEX_ZIPCODE, zipcode);
	}

	/**
	 * Returns the value of the '<em><b>Manaposition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manaposition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manaposition</em>' attribute.
	 * @see #setManaposition(int)
	 */
	public int getManaposition() {
		return DataUtil.toInt(super.getByIndex(INDEX_MANAPOSITION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getManaposition <em>Manaposition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manaposition</em>' attribute.
	 * @see #getManaposition()
	 */
	public void setManaposition(int manaposition) {
		super.setByIndex(INDEX_MANAPOSITION, manaposition);
	}

	/**
	 * Returns the value of the '<em><b>Managerid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managerid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Managerid</em>' attribute.
	 * @see #setManagerid(int)
	 */
	public int getManagerid() {
		return DataUtil.toInt(super.getByIndex(INDEX_MANAGERID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getManagerid <em>Managerid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managerid</em>' attribute.
	 * @see #getManagerid()
	 */
	public void setManagerid(int managerid) {
		super.setByIndex(INDEX_MANAGERID, managerid);
	}

	/**
	 * Returns the value of the '<em><b>Orgmanager</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Orgmanager</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orgmanager</em>' attribute.
	 * @see #setOrgmanager(java.lang.String)
	 */
	public String getOrgmanager() {
		return DataUtil.toString(super.getByIndex(INDEX_ORGMANAGER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgmanager <em>Orgmanager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgmanager</em>' attribute.
	 * @see #getOrgmanager()
	 */
	public void setOrgmanager(String orgmanager) {
		super.setByIndex(INDEX_ORGMANAGER, orgmanager);
	}

	/**
	 * Returns the value of the '<em><b>Linkman</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linkman</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linkman</em>' attribute.
	 * @see #setLinkman(java.lang.String)
	 */
	public String getLinkman() {
		return DataUtil.toString(super.getByIndex(INDEX_LINKMAN, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLinkman <em>Linkman</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linkman</em>' attribute.
	 * @see #getLinkman()
	 */
	public void setLinkman(String linkman) {
		super.setByIndex(INDEX_LINKMAN, linkman);
	}

	/**
	 * Returns the value of the '<em><b>Linktel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linktel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linktel</em>' attribute.
	 * @see #setLinktel(java.lang.String)
	 */
	public String getLinktel() {
		return DataUtil.toString(super.getByIndex(INDEX_LINKTEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getLinktel <em>Linktel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linktel</em>' attribute.
	 * @see #getLinktel()
	 */
	public void setLinktel(String linktel) {
		super.setByIndex(INDEX_LINKTEL, linktel);
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

	/**
	 * Returns the value of the '<em><b>Weburl</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weburl</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weburl</em>' attribute.
	 * @see #setWeburl(java.lang.String)
	 */
	public String getWeburl() {
		return DataUtil.toString(super.getByIndex(INDEX_WEBURL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getWeburl <em>Weburl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weburl</em>' attribute.
	 * @see #getWeburl()
	 */
	public void setWeburl(String weburl) {
		super.setByIndex(INDEX_WEBURL, weburl);
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
	 * Returns the value of the '<em><b>Area</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Area</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Area</em>' attribute.
	 * @see #setArea(java.lang.String)
	 */
	public String getArea() {
		return DataUtil.toString(super.getByIndex(INDEX_AREA, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getArea <em>Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Area</em>' attribute.
	 * @see #getArea()
	 */
	public void setArea(String area) {
		super.setByIndex(INDEX_AREA, area);
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
	 * @see #setUpdator(int)
	 */
	public int getUpdator() {
		return DataUtil.toInt(super.getByIndex(INDEX_UPDATOR, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getUpdator <em>Updator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Updator</em>' attribute.
	 * @see #getUpdator()
	 */
	public void setUpdator(int updator) {
		super.setByIndex(INDEX_UPDATOR, updator);
	}

	/**
	 * Returns the value of the '<em><b>Sortno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sortno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sortno</em>' attribute.
	 * @see #setSortno(long)
	 */
	public long getSortno() {
		return DataUtil.toLong(super.getByIndex(INDEX_SORTNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSortno <em>Sortno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sortno</em>' attribute.
	 * @see #getSortno()
	 */
	public void setSortno(long sortno) {
		super.setByIndex(INDEX_SORTNO, sortno);
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
	 * @see #setSubcount(int)
	 */
	public int getSubcount() {
		return DataUtil.toInt(super.getByIndex(INDEX_SUBCOUNT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(int subcount) {
		super.setByIndex(INDEX_SUBCOUNT, subcount);
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
	 * Returns the value of the '<em><b>Buno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Buno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Buno</em>' attribute.
	 * @see #setBuno(java.lang.String)
	 */
	public String getBuno() {
		return DataUtil.toString(super.getByIndex(INDEX_BUNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBuno <em>Buno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Buno</em>' attribute.
	 * @see #getBuno()
	 */
	public void setBuno(String buno) {
		super.setByIndex(INDEX_BUNO, buno);
	}

	/**
	 * Returns the value of the '<em><b>Auditbankno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auditbankno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auditbankno</em>' attribute.
	 * @see #setAuditbankno(java.lang.String)
	 */
	public String getAuditbankno() {
		return DataUtil.toString(super.getByIndex(INDEX_AUDITBANKNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAuditbankno <em>Auditbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auditbankno</em>' attribute.
	 * @see #getAuditbankno()
	 */
	public void setAuditbankno(String auditbankno) {
		super.setByIndex(INDEX_AUDITBANKNO, auditbankno);
	}

	/**
	 * Returns the value of the '<em><b>Paymentsysno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Paymentsysno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paymentsysno</em>' attribute.
	 * @see #setPaymentsysno(java.lang.String)
	 */
	public String getPaymentsysno() {
		return DataUtil.toString(super.getByIndex(INDEX_PAYMENTSYSNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPaymentsysno <em>Paymentsysno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Paymentsysno</em>' attribute.
	 * @see #getPaymentsysno()
	 */
	public void setPaymentsysno(String paymentsysno) {
		super.setByIndex(INDEX_PAYMENTSYSNO, paymentsysno);
	}

	/**
	 * Returns the value of the '<em><b>Parentorgcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parentorgcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parentorgcode</em>' attribute.
	 * @see #setParentorgcode(java.lang.String)
	 */
	public String getParentorgcode() {
		return DataUtil.toString(super.getByIndex(INDEX_PARENTORGCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getParentorgcode <em>Parentorgcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parentorgcode</em>' attribute.
	 * @see #getParentorgcode()
	 */
	public void setParentorgcode(String parentorgcode) {
		super.setByIndex(INDEX_PARENTORGCODE, parentorgcode);
	}

	/**
	 * Returns the value of the '<em><b>Isteam</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isteam</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isteam</em>' attribute.
	 * @see #setIsteam(java.lang.String)
	 */
	public String getIsteam() {
		return DataUtil.toString(super.getByIndex(INDEX_ISTEAM, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsteam <em>Isteam</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isteam</em>' attribute.
	 * @see #getIsteam()
	 */
	public void setIsteam(String isteam) {
		super.setByIndex(INDEX_ISTEAM, isteam);
	}


}