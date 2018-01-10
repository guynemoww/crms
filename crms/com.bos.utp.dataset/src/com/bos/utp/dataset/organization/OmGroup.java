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
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getGroupid <em>Groupid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getGrouplevel <em>Grouplevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getGroupname <em>Groupname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getGroupdesc <em>Groupdesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getGrouptype <em>Grouptype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getGroupseq <em>Groupseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getStartdate <em>Startdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getEnddate <em>Enddate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getGroupstatus <em>Groupstatus</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getManager <em>Manager</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getCreatetime <em>Createtime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getLastupdate <em>Lastupdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getUpdator <em>Updator</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getOmGroup <em>OmGroup</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmGroup#getOmOrganization <em>OmOrganization</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OmGroup extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.OmGroup";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "OmGroup");

	public final static IObjectFactory<OmGroup> FACTORY = new IObjectFactory<OmGroup>() {
		public OmGroup create() {
			return (OmGroup) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public BigDecimal getGroupid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getGroupid <em>Groupid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupid</em>' attribute.
	 * @see #getGroupid()
	 */
	public void setGroupid(BigDecimal groupid);

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
	public int getGrouplevel();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getGrouplevel <em>Grouplevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grouplevel</em>' attribute.
	 * @see #getGrouplevel()
	 */
	public void setGrouplevel(int grouplevel);

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
	public String getGroupname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getGroupname <em>Groupname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupname</em>' attribute.
	 * @see #getGroupname()
	 */
	public void setGroupname(String groupname);

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
	public String getGroupdesc();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getGroupdesc <em>Groupdesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupdesc</em>' attribute.
	 * @see #getGroupdesc()
	 */
	public void setGroupdesc(String groupdesc);

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
	public String getGrouptype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getGrouptype <em>Grouptype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grouptype</em>' attribute.
	 * @see #getGrouptype()
	 */
	public void setGrouptype(String grouptype);

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
	public String getGroupseq();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getGroupseq <em>Groupseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupseq</em>' attribute.
	 * @see #getGroupseq()
	 */
	public void setGroupseq(String groupseq);

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
	public Date getStartdate();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getStartdate <em>Startdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Startdate</em>' attribute.
	 * @see #getStartdate()
	 */
	public void setStartdate(Date startdate);

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
	public Date getEnddate();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getEnddate <em>Enddate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enddate</em>' attribute.
	 * @see #getEnddate()
	 */
	public void setEnddate(Date enddate);

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
	public String getGroupstatus();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getGroupstatus <em>Groupstatus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groupstatus</em>' attribute.
	 * @see #getGroupstatus()
	 */
	public void setGroupstatus(String groupstatus);

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
	public String getManager();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getManager <em>Manager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager</em>' attribute.
	 * @see #getManager()
	 */
	public void setManager(String manager);

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
	public Date getCreatetime();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getCreatetime <em>Createtime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Createtime</em>' attribute.
	 * @see #getCreatetime()
	 */
	public void setCreatetime(Date createtime);

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
	public Date getLastupdate();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getLastupdate <em>Lastupdate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastupdate</em>' attribute.
	 * @see #getLastupdate()
	 */
	public void setLastupdate(Date lastupdate);

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
	public BigDecimal getUpdator();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getUpdator <em>Updator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Updator</em>' attribute.
	 * @see #getUpdator()
	 */
	public void setUpdator(BigDecimal updator);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getIsleaf <em>Isleaf</em>}' attribute.
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
	 * @see #setSubcount(java.math.BigDecimal)
	 */
	public BigDecimal getSubcount();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(BigDecimal subcount);

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
	public OmGroup getOmGroup();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getOmGroup <em>OmGroup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmGroup</em>' attribute.
	 * @see #getOmGroup()
	 */
	public void setOmGroup(OmGroup omGroup);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmGroup#getOmOrganization <em>OmOrganization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmOrganization</em>' attribute.
	 * @see #getOmOrganization()
	 */
	public void setOmOrganization(OmOrganization omOrganization);


}