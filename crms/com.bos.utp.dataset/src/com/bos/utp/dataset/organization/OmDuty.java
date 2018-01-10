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
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getDutyid <em>Dutyid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getDutycode <em>Dutycode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getDutyname <em>Dutyname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getDutyseq <em>Dutyseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getDutytype <em>Dutytype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getRemark <em>Remark</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getDutylevel <em>Dutylevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmDuty#getOmDuty <em>OmDuty</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OmDuty extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.OmDuty";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "OmDuty");

	public final static IObjectFactory<OmDuty> FACTORY = new IObjectFactory<OmDuty>() {
		public OmDuty create() {
			return (OmDuty) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Dutyid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutyid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutyid</em>' attribute.
	 * @see #setDutyid(int)
	 */
	public int getDutyid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getDutyid <em>Dutyid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutyid</em>' attribute.
	 * @see #getDutyid()
	 */
	public void setDutyid(int dutyid);

	/**
	 * Returns the value of the '<em><b>Dutycode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutycode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutycode</em>' attribute.
	 * @see #setDutycode(java.lang.String)
	 */
	public String getDutycode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getDutycode <em>Dutycode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutycode</em>' attribute.
	 * @see #getDutycode()
	 */
	public void setDutycode(String dutycode);

	/**
	 * Returns the value of the '<em><b>Dutyname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutyname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutyname</em>' attribute.
	 * @see #setDutyname(java.lang.String)
	 */
	public String getDutyname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getDutyname <em>Dutyname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutyname</em>' attribute.
	 * @see #getDutyname()
	 */
	public void setDutyname(String dutyname);

	/**
	 * Returns the value of the '<em><b>Dutyseq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutyseq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutyseq</em>' attribute.
	 * @see #setDutyseq(java.lang.String)
	 */
	public String getDutyseq();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getDutyseq <em>Dutyseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutyseq</em>' attribute.
	 * @see #getDutyseq()
	 */
	public void setDutyseq(String dutyseq);

	/**
	 * Returns the value of the '<em><b>Dutytype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutytype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutytype</em>' attribute.
	 * @see #setDutytype(java.lang.String)
	 */
	public String getDutytype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getDutytype <em>Dutytype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutytype</em>' attribute.
	 * @see #getDutytype()
	 */
	public void setDutytype(String dutytype);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getIsleaf <em>Isleaf</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getSubcount <em>Subcount</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getRemark <em>Remark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remark</em>' attribute.
	 * @see #getRemark()
	 */
	public void setRemark(String remark);

	/**
	 * Returns the value of the '<em><b>Dutylevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dutylevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dutylevel</em>' attribute.
	 * @see #setDutylevel(int)
	 */
	public int getDutylevel();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getDutylevel <em>Dutylevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dutylevel</em>' attribute.
	 * @see #getDutylevel()
	 */
	public void setDutylevel(int dutylevel);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmDuty#getOmDuty <em>OmDuty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmDuty</em>' attribute.
	 * @see #getOmDuty()
	 */
	public void setOmDuty(OmDuty omDuty);


}