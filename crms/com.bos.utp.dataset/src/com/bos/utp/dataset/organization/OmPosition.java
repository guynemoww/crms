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
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getPositionid <em>Positionid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getPosicode <em>Posicode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getPosiname <em>Posiname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getPosilevel <em>Posilevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getPositionseq <em>Positionseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getPositype <em>Positype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getCreatetime <em>Createtime</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getLastupdate <em>Lastupdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getUpdator <em>Updator</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getStartdate <em>Startdate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getEnddate <em>Enddate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getStatus <em>Status</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getOmOrganization <em>OmOrganization</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getOmPosition <em>OmPosition</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmPosition#getOmDuty <em>OmDuty</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OmPosition extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.OmPosition";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "OmPosition");

	public final static IObjectFactory<OmPosition> FACTORY = new IObjectFactory<OmPosition>() {
		public OmPosition create() {
			return (OmPosition) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public BigDecimal getPositionid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getPositionid <em>Positionid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Positionid</em>' attribute.
	 * @see #getPositionid()
	 */
	public void setPositionid(BigDecimal positionid);

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
	public String getPosicode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getPosicode <em>Posicode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Posicode</em>' attribute.
	 * @see #getPosicode()
	 */
	public void setPosicode(String posicode);

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
	public String getPosiname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getPosiname <em>Posiname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Posiname</em>' attribute.
	 * @see #getPosiname()
	 */
	public void setPosiname(String posiname);

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
	public BigDecimal getPosilevel();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getPosilevel <em>Posilevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Posilevel</em>' attribute.
	 * @see #getPosilevel()
	 */
	public void setPosilevel(BigDecimal posilevel);

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
	public String getPositionseq();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getPositionseq <em>Positionseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Positionseq</em>' attribute.
	 * @see #getPositionseq()
	 */
	public void setPositionseq(String positionseq);

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
	public String getPositype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getPositype <em>Positype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Positype</em>' attribute.
	 * @see #getPositype()
	 */
	public void setPositype(String positype);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getCreatetime <em>Createtime</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getLastupdate <em>Lastupdate</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getUpdator <em>Updator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Updator</em>' attribute.
	 * @see #getUpdator()
	 */
	public void setUpdator(BigDecimal updator);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getStartdate <em>Startdate</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getEnddate <em>Enddate</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 */
	public void setStatus(String status);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getIsleaf <em>Isleaf</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(BigDecimal subcount);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getOmOrganization <em>OmOrganization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmOrganization</em>' attribute.
	 * @see #getOmOrganization()
	 */
	public void setOmOrganization(OmOrganization omOrganization);

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
	public OmPosition getOmPosition();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getOmPosition <em>OmPosition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmPosition</em>' attribute.
	 * @see #getOmPosition()
	 */
	public void setOmPosition(OmPosition omPosition);

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
	public OmDuty getOmDuty();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmPosition#getOmDuty <em>OmDuty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmDuty</em>' attribute.
	 * @see #getOmDuty()
	 */
	public void setOmDuty(OmDuty omDuty);


}