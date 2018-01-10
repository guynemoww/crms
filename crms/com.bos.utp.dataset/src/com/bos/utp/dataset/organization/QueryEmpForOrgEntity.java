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
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getEmpid <em>Empid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getEmpcode <em>Empcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getOperatorid <em>Operatorid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getEmpname <em>Empname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getGender <em>Gender</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getEmpstatus <em>Empstatus</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getInorgid <em>Inorgid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getInorgname <em>Inorgname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getOrglevel <em>Orglevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getOrgcode <em>Orgcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getStatus <em>Status</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getLastlogin <em>Lastlogin</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getRolename <em>Rolename</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface QueryEmpForOrgEntity extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.QueryEmpForOrgEntity";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "QueryEmpForOrgEntity");

	public final static IObjectFactory<QueryEmpForOrgEntity> FACTORY = new IObjectFactory<QueryEmpForOrgEntity>() {
		public QueryEmpForOrgEntity create() {
			return (QueryEmpForOrgEntity) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public int getEmpid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getEmpid <em>Empid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empid</em>' attribute.
	 * @see #getEmpid()
	 */
	public void setEmpid(int empid);

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
	public String getEmpcode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getEmpcode <em>Empcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empcode</em>' attribute.
	 * @see #getEmpcode()
	 */
	public void setEmpcode(String empcode);

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
	public long getOperatorid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getOperatorid <em>Operatorid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operatorid</em>' attribute.
	 * @see #getOperatorid()
	 */
	public void setOperatorid(long operatorid);

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
	public String getEmpname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getEmpname <em>Empname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empname</em>' attribute.
	 * @see #getEmpname()
	 */
	public void setEmpname(String empname);

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
	public String getGender();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getGender <em>Gender</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gender</em>' attribute.
	 * @see #getGender()
	 */
	public void setGender(String gender);

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
	public String getEmpstatus();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getEmpstatus <em>Empstatus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empstatus</em>' attribute.
	 * @see #getEmpstatus()
	 */
	public void setEmpstatus(String empstatus);

	/**
	 * Returns the value of the '<em><b>Inorgid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inorgid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inorgid</em>' attribute.
	 * @see #setInorgid(int)
	 */
	public int getInorgid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getInorgid <em>Inorgid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inorgid</em>' attribute.
	 * @see #getInorgid()
	 */
	public void setInorgid(int inorgid);

	/**
	 * Returns the value of the '<em><b>Inorgname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inorgname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inorgname</em>' attribute.
	 * @see #setInorgname(java.lang.String)
	 */
	public String getInorgname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getInorgname <em>Inorgname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inorgname</em>' attribute.
	 * @see #getInorgname()
	 */
	public void setInorgname(String inorgname);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getOrglevel <em>Orglevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orglevel</em>' attribute.
	 * @see #getOrglevel()
	 */
	public void setOrglevel(int orglevel);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getOrgcode <em>Orgcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orgcode</em>' attribute.
	 * @see #getOrgcode()
	 */
	public void setOrgcode(String orgcode);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 */
	public void setStatus(String status);

	/**
	 * Returns the value of the '<em><b>Lastlogin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lastlogin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lastlogin</em>' attribute.
	 * @see #setLastlogin(java.util.Date)
	 */
	public Date getLastlogin();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getLastlogin <em>Lastlogin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lastlogin</em>' attribute.
	 * @see #getLastlogin()
	 */
	public void setLastlogin(Date lastlogin);

	/**
	 * Returns the value of the '<em><b>Rolename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rolename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rolename</em>' attribute.
	 * @see #setRolename(java.lang.String)
	 */
	public String getRolename();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.QueryEmpForOrgEntity#getRolename <em>Rolename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rolename</em>' attribute.
	 * @see #getRolename()
	 */
	public void setRolename(String rolename);


}