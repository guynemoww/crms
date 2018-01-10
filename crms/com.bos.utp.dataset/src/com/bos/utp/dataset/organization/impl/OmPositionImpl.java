/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OmDuty;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
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
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getPositionid <em>Positionid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getPosicode <em>Posicode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getPosiname <em>Posiname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getPosilevel <em>Posilevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getPositionseq <em>Positionseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getPositype <em>Positype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getCreatetime <em>Createtime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getLastupdate <em>Lastupdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getUpdator <em>Updator</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getStartdate <em>Startdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getEnddate <em>Enddate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getOmOrganization <em>OmOrganization</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getOmPosition <em>OmPosition</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmPositionImpl#getOmDuty <em>OmDuty</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmPosition;
 */

public class OmPositionImpl extends ExtendedDataObjectImpl implements OmPosition {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_POSITIONID = 0;
	public final static int INDEX_POSICODE = 1;
	public final static int INDEX_POSINAME = 2;
	public final static int INDEX_POSILEVEL = 3;
	public final static int INDEX_POSITIONSEQ = 4;
	public final static int INDEX_POSITYPE = 5;
	public final static int INDEX_CREATETIME = 6;
	public final static int INDEX_LASTUPDATE = 7;
	public final static int INDEX_UPDATOR = 8;
	public final static int INDEX_STARTDATE = 9;
	public final static int INDEX_ENDDATE = 10;
	public final static int INDEX_STATUS = 11;
	public final static int INDEX_ISLEAF = 12;
	public final static int INDEX_SUBCOUNT = 13;
	public final static int INDEX_OMORGANIZATION = 14;
	public final static int INDEX_OMPOSITION = 15;
	public final static int INDEX_OMDUTY = 16;
	public final static int SDO_PROPERTY_COUNT = 17;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmPositionImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmPositionImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Positionid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Positionid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Positionid</em>' attribute.
	 * @see #setPositionid(java.math.BigDecimal)
	 */
	public BigDecimal getPositionid() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_POSITIONID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPositionid <em>Positionid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Positionid</em>' attribute.
	 * @see #getPositionid()
	 */
	public void setPositionid(BigDecimal positionid) {
		super.setByIndex(INDEX_POSITIONID, positionid);
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
	 * Returns the value of the '<em><b>Posilevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Posilevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Posilevel</em>' attribute.
	 * @see #setPosilevel(java.math.BigDecimal)
	 */
	public BigDecimal getPosilevel() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_POSILEVEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPosilevel <em>Posilevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Posilevel</em>' attribute.
	 * @see #getPosilevel()
	 */
	public void setPosilevel(BigDecimal posilevel) {
		super.setByIndex(INDEX_POSILEVEL, posilevel);
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
	 * Returns the value of the '<em><b>Positype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Positype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Positype</em>' attribute.
	 * @see #setPositype(java.lang.String)
	 */
	public String getPositype() {
		return DataUtil.toString(super.getByIndex(INDEX_POSITYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPositype <em>Positype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Positype</em>' attribute.
	 * @see #getPositype()
	 */
	public void setPositype(String positype) {
		super.setByIndex(INDEX_POSITYPE, positype);
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

	/**
	 * Returns the value of the '<em><b>OmPosition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmPosition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmPosition</em>' attribute.
	 * @see #setOmPosition(com.bos.utp.dataset.organization.OmPosition)
	 */
	public OmPosition getOmPosition() {
		return (OmPosition) DataUtil.toDataObject(super.getByIndex(INDEX_OMPOSITION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmPosition <em>OmPosition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmPosition</em>' attribute.
	 * @see #getOmPosition()
	 */
	public void setOmPosition(OmPosition omPosition) {
		super.setByIndex(INDEX_OMPOSITION, omPosition);
	}

	/**
	 * Returns the value of the '<em><b>OmDuty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmDuty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmDuty</em>' attribute.
	 * @see #setOmDuty(com.bos.utp.dataset.organization.OmDuty)
	 */
	public OmDuty getOmDuty() {
		return (OmDuty) DataUtil.toDataObject(super.getByIndex(INDEX_OMDUTY, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmDuty <em>OmDuty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmDuty</em>' attribute.
	 * @see #getOmDuty()
	 */
	public void setOmDuty(OmDuty omDuty) {
		super.setByIndex(INDEX_OMDUTY, omDuty);
	}


}