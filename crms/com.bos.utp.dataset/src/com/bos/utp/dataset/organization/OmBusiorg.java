/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization;

import com.eos.data.sdo.IObjectFactory;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.TypeHelper;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getBusidomain <em>Busidomain</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getBusiorgid <em>Busiorgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getOrgname <em>Orgname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getOrglevel <em>Orglevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getNodetype <em>Nodetype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getOrgcode <em>Orgcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getSeqno <em>Seqno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getManapos <em>Manapos</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getSortno <em>Sortno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getOmBusiorg <em>OmBusiorg</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmBusiorg#getOmOrganization <em>OmOrganization</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OmBusiorg extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.OmBusiorg";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "OmBusiorg");

	public final static IObjectFactory<OmBusiorg> FACTORY = new IObjectFactory<OmBusiorg>() {
		public OmBusiorg create() {
			return (OmBusiorg) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public String getBusidomain();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getBusidomain <em>Busidomain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Busidomain</em>' attribute.
	 * @see #getBusidomain()
	 */
	public void setBusidomain(String busidomain);

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
	public int getBusiorgid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getBusiorgid <em>Busiorgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Busiorgid</em>' attribute.
	 * @see #getBusiorgid()
	 */
	public void setBusiorgid(int busiorgid);

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
	public String getOrgname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getOrgname <em>Orgname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgname</em>' attribute.
	 * @see #getOrgname()
	 */
	public void setOrgname(String orgname);

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
	public long getOrglevel();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getOrglevel <em>Orglevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orglevel</em>' attribute.
	 * @see #getOrglevel()
	 */
	public void setOrglevel(long orglevel);

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
	public String getNodetype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getNodetype <em>Nodetype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nodetype</em>' attribute.
	 * @see #getNodetype()
	 */
	public void setNodetype(String nodetype);

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
	public String getOrgcode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getOrgcode <em>Orgcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgcode</em>' attribute.
	 * @see #getOrgcode()
	 */
	public void setOrgcode(String orgcode);

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
	public String getSeqno();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getSeqno <em>Seqno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seqno</em>' attribute.
	 * @see #getSeqno()
	 */
	public void setSeqno(String seqno);

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
	public int getManapos();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getManapos <em>Manapos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manapos</em>' attribute.
	 * @see #getManapos()
	 */
	public void setManapos(int manapos);

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
	public long getSortno();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getSortno <em>Sortno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sortno</em>' attribute.
	 * @see #getSortno()
	 */
	public void setSortno(long sortno);

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
	public String getIsleaf();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getIsleaf <em>Isleaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isleaf</em>' attribute.
	 * @see #getIsleaf()
	 */
	public void setIsleaf(String isleaf);

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
	public int getSubcount();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(int subcount);

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
	public OmBusiorg getOmBusiorg();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getOmBusiorg <em>OmBusiorg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmBusiorg</em>' attribute.
	 * @see #getOmBusiorg()
	 */
	public void setOmBusiorg(OmBusiorg omBusiorg);

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
	public OmOrganization getOmOrganization();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmBusiorg#getOmOrganization <em>OmOrganization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmOrganization</em>' attribute.
	 * @see #getOmOrganization()
	 */
	public void setOmOrganization(OmOrganization omOrganization);


}