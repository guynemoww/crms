/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OmBusiorg;
import com.bos.utp.dataset.organization.OmOrganization;
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
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getBusidomain <em>Busidomain</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getBusiorgid <em>Busiorgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getOrgname <em>Orgname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getOrglevel <em>Orglevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getNodetype <em>Nodetype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getOrgcode <em>Orgcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getSeqno <em>Seqno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getManapos <em>Manapos</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getSortno <em>Sortno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getOmBusiorg <em>OmBusiorg</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.impl.OmBusiorgImpl#getOmOrganization <em>OmOrganization</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OmBusiorg;
 */

public class OmBusiorgImpl extends ExtendedDataObjectImpl implements OmBusiorg {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_BUSIDOMAIN = 0;
	public final static int INDEX_BUSIORGID = 1;
	public final static int INDEX_ORGNAME = 2;
	public final static int INDEX_ORGLEVEL = 3;
	public final static int INDEX_NODETYPE = 4;
	public final static int INDEX_ORGCODE = 5;
	public final static int INDEX_SEQNO = 6;
	public final static int INDEX_MANAPOS = 7;
	public final static int INDEX_SORTNO = 8;
	public final static int INDEX_ISLEAF = 9;
	public final static int INDEX_SUBCOUNT = 10;
	public final static int INDEX_OMBUSIORG = 11;
	public final static int INDEX_OMORGANIZATION = 12;
	public final static int SDO_PROPERTY_COUNT = 13;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmBusiorgImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OmBusiorgImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Busidomain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Busidomain</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Busidomain</em>' attribute.
	 * @see #setBusidomain(java.lang.String)
	 */
	public String getBusidomain() {
		return DataUtil.toString(super.getByIndex(INDEX_BUSIDOMAIN, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBusidomain <em>Busidomain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Busidomain</em>' attribute.
	 * @see #getBusidomain()
	 */
	public void setBusidomain(String busidomain) {
		super.setByIndex(INDEX_BUSIDOMAIN, busidomain);
	}

	/**
	 * Returns the value of the '<em><b>Busiorgid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Busiorgid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Busiorgid</em>' attribute.
	 * @see #setBusiorgid(int)
	 */
	public int getBusiorgid() {
		return DataUtil.toInt(super.getByIndex(INDEX_BUSIORGID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getBusiorgid <em>Busiorgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Busiorgid</em>' attribute.
	 * @see #getBusiorgid()
	 */
	public void setBusiorgid(int busiorgid) {
		super.setByIndex(INDEX_BUSIORGID, busiorgid);
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
	 * @see #setOrglevel(long)
	 */
	public long getOrglevel() {
		return DataUtil.toLong(super.getByIndex(INDEX_ORGLEVEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrglevel <em>Orglevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orglevel</em>' attribute.
	 * @see #getOrglevel()
	 */
	public void setOrglevel(long orglevel) {
		super.setByIndex(INDEX_ORGLEVEL, orglevel);
	}

	/**
	 * Returns the value of the '<em><b>Nodetype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodetype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodetype</em>' attribute.
	 * @see #setNodetype(java.lang.String)
	 */
	public String getNodetype() {
		return DataUtil.toString(super.getByIndex(INDEX_NODETYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getNodetype <em>Nodetype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nodetype</em>' attribute.
	 * @see #getNodetype()
	 */
	public void setNodetype(String nodetype) {
		super.setByIndex(INDEX_NODETYPE, nodetype);
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
	 * Returns the value of the '<em><b>Seqno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seqno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seqno</em>' attribute.
	 * @see #setSeqno(java.lang.String)
	 */
	public String getSeqno() {
		return DataUtil.toString(super.getByIndex(INDEX_SEQNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSeqno <em>Seqno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seqno</em>' attribute.
	 * @see #getSeqno()
	 */
	public void setSeqno(String seqno) {
		super.setByIndex(INDEX_SEQNO, seqno);
	}

	/**
	 * Returns the value of the '<em><b>Manapos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manapos</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manapos</em>' attribute.
	 * @see #setManapos(int)
	 */
	public int getManapos() {
		return DataUtil.toInt(super.getByIndex(INDEX_MANAPOS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getManapos <em>Manapos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manapos</em>' attribute.
	 * @see #getManapos()
	 */
	public void setManapos(int manapos) {
		super.setByIndex(INDEX_MANAPOS, manapos);
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
	 * Returns the value of the '<em><b>OmBusiorg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OmBusiorg</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OmBusiorg</em>' attribute.
	 * @see #setOmBusiorg(com.bos.utp.dataset.organization.OmBusiorg)
	 */
	public OmBusiorg getOmBusiorg() {
		return (OmBusiorg) DataUtil.toDataObject(super.getByIndex(INDEX_OMBUSIORG, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOmBusiorg <em>OmBusiorg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmBusiorg</em>' attribute.
	 * @see #getOmBusiorg()
	 */
	public void setOmBusiorg(OmBusiorg omBusiorg) {
		super.setByIndex(INDEX_OMBUSIORG, omBusiorg);
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