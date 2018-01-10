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

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgid <em>Orgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgcode <em>Orgcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgname <em>Orgname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrglevel <em>Orglevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgdegree <em>Orgdegree</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getParentorgid <em>Parentorgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgseq <em>Orgseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgtype <em>Orgtype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgaddr <em>Orgaddr</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getZipcode <em>Zipcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getManaposition <em>Manaposition</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getManagerid <em>Managerid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgmanager <em>Orgmanager</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getLinkman <em>Linkman</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getLinktel <em>Linktel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getEmail <em>Email</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getWeburl <em>Weburl</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getStartdate <em>Startdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getEnddate <em>Enddate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getStatus <em>Status</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getArea <em>Area</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getCreatetime <em>Createtime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getLastupdate <em>Lastupdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getUpdator <em>Updator</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getSortno <em>Sortno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getRemark <em>Remark</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getBuno <em>Buno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getAuditbankno <em>Auditbankno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getPaymentsysno <em>Paymentsysno</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getParentorgcode <em>Parentorgcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryOrgEntity#getIsteam <em>Isteam</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface QueryOrgEntity extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.QueryOrgEntity";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "QueryOrgEntity");

	public final static IObjectFactory<QueryOrgEntity> FACTORY = new IObjectFactory<QueryOrgEntity>() {
		public QueryOrgEntity create() {
			return (QueryOrgEntity) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public int getOrgid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgid <em>Orgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgid</em>' attribute.
	 * @see #getOrgid()
	 */
	public void setOrgid(int orgid);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgcode <em>Orgcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgcode</em>' attribute.
	 * @see #getOrgcode()
	 */
	public void setOrgcode(String orgcode);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgname <em>Orgname</em>}' attribute.
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
	 * @see #setOrglevel(int)
	 */
	public int getOrglevel();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrglevel <em>Orglevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orglevel</em>' attribute.
	 * @see #getOrglevel()
	 */
	public void setOrglevel(int orglevel);

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
	public String getOrgdegree();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgdegree <em>Orgdegree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgdegree</em>' attribute.
	 * @see #getOrgdegree()
	 */
	public void setOrgdegree(String orgdegree);

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
	public int getParentorgid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getParentorgid <em>Parentorgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parentorgid</em>' attribute.
	 * @see #getParentorgid()
	 */
	public void setParentorgid(int parentorgid);

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
	public String getOrgseq();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgseq <em>Orgseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgseq</em>' attribute.
	 * @see #getOrgseq()
	 */
	public void setOrgseq(String orgseq);

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
	public String getOrgtype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgtype <em>Orgtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgtype</em>' attribute.
	 * @see #getOrgtype()
	 */
	public void setOrgtype(String orgtype);

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
	public String getOrgaddr();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgaddr <em>Orgaddr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgaddr</em>' attribute.
	 * @see #getOrgaddr()
	 */
	public void setOrgaddr(String orgaddr);

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
	public String getZipcode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getZipcode <em>Zipcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Zipcode</em>' attribute.
	 * @see #getZipcode()
	 */
	public void setZipcode(String zipcode);

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
	public int getManaposition();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getManaposition <em>Manaposition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manaposition</em>' attribute.
	 * @see #getManaposition()
	 */
	public void setManaposition(int manaposition);

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
	public int getManagerid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getManagerid <em>Managerid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managerid</em>' attribute.
	 * @see #getManagerid()
	 */
	public void setManagerid(int managerid);

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
	public String getOrgmanager();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getOrgmanager <em>Orgmanager</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgmanager</em>' attribute.
	 * @see #getOrgmanager()
	 */
	public void setOrgmanager(String orgmanager);

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
	public String getLinkman();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getLinkman <em>Linkman</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linkman</em>' attribute.
	 * @see #getLinkman()
	 */
	public void setLinkman(String linkman);

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
	public String getLinktel();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getLinktel <em>Linktel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linktel</em>' attribute.
	 * @see #getLinktel()
	 */
	public void setLinktel(String linktel);

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
	public String getEmail();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getEmail <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email</em>' attribute.
	 * @see #getEmail()
	 */
	public void setEmail(String email);

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
	public String getWeburl();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getWeburl <em>Weburl</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weburl</em>' attribute.
	 * @see #getWeburl()
	 */
	public void setWeburl(String weburl);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getStartdate <em>Startdate</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getEnddate <em>Enddate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enddate</em>' attribute.
	 * @see #getEnddate()
	 */
	public void setEnddate(Date enddate);

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
	public String getStatus();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 */
	public void setStatus(String status);

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
	public String getArea();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getArea <em>Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Area</em>' attribute.
	 * @see #getArea()
	 */
	public void setArea(String area);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getCreatetime <em>Createtime</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getLastupdate <em>Lastupdate</em>}' attribute.
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
	 * @see #setUpdator(int)
	 */
	public int getUpdator();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getUpdator <em>Updator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Updator</em>' attribute.
	 * @see #getUpdator()
	 */
	public void setUpdator(int updator);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getSortno <em>Sortno</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getIsleaf <em>Isleaf</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(int subcount);

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
	public String getRemark();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getRemark <em>Remark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remark</em>' attribute.
	 * @see #getRemark()
	 */
	public void setRemark(String remark);

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
	public String getBuno();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getBuno <em>Buno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Buno</em>' attribute.
	 * @see #getBuno()
	 */
	public void setBuno(String buno);

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
	public String getAuditbankno();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getAuditbankno <em>Auditbankno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auditbankno</em>' attribute.
	 * @see #getAuditbankno()
	 */
	public void setAuditbankno(String auditbankno);

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
	public String getPaymentsysno();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getPaymentsysno <em>Paymentsysno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Paymentsysno</em>' attribute.
	 * @see #getPaymentsysno()
	 */
	public void setPaymentsysno(String paymentsysno);

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
	public String getParentorgcode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getParentorgcode <em>Parentorgcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parentorgcode</em>' attribute.
	 * @see #getParentorgcode()
	 */
	public void setParentorgcode(String parentorgcode);

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
	public String getIsteam();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryOrgEntity#getIsteam <em>Isteam</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isteam</em>' attribute.
	 * @see #getIsteam()
	 */
	public void setIsteam(String isteam);


}