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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.utp.dataset.organization.AcRolefuncView#getRoleid <em>Roleid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.AcRolefuncView#getRolename <em>Rolename</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.AcRolefuncView#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.AcRolefuncView#getAppname <em>Appname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.AcRolefuncView#getFuncgroupid <em>Funcgroupid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.AcRolefuncView#getFuncgroupname <em>Funcgroupname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.AcRolefuncView#getFunccode <em>Funccode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.AcRolefuncView#getFuncname <em>Funcname</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcRolefuncView extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.AcRolefuncView";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "AcRolefuncView");

	public final static IObjectFactory<AcRolefuncView> FACTORY = new IObjectFactory<AcRolefuncView>() {
		public AcRolefuncView create() {
			return (AcRolefuncView) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Roleid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roleid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roleid</em>' attribute.
	 * @see #setRoleid(java.lang.String)
	 */
	public String getRoleid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.AcRolefuncView#getRoleid <em>Roleid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Roleid</em>' attribute.
	 * @see #getRoleid()
	 */
	public void setRoleid(String roleid);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.AcRolefuncView#getRolename <em>Rolename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rolename</em>' attribute.
	 * @see #getRolename()
	 */
	public void setRolename(String rolename);

	/**
	 * Returns the value of the '<em><b>Appid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Appid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Appid</em>' attribute.
	 * @see #setAppid(java.math.BigDecimal)
	 */
	public BigDecimal getAppid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.AcRolefuncView#getAppid <em>Appid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appid</em>' attribute.
	 * @see #getAppid()
	 */
	public void setAppid(BigDecimal appid);

	/**
	 * Returns the value of the '<em><b>Appname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Appname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Appname</em>' attribute.
	 * @see #setAppname(java.lang.String)
	 */
	public String getAppname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.AcRolefuncView#getAppname <em>Appname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appname</em>' attribute.
	 * @see #getAppname()
	 */
	public void setAppname(String appname);

	/**
	 * Returns the value of the '<em><b>Funcgroupid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcgroupid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcgroupid</em>' attribute.
	 * @see #setFuncgroupid(java.math.BigDecimal)
	 */
	public BigDecimal getFuncgroupid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.AcRolefuncView#getFuncgroupid <em>Funcgroupid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcgroupid</em>' attribute.
	 * @see #getFuncgroupid()
	 */
	public void setFuncgroupid(BigDecimal funcgroupid);

	/**
	 * Returns the value of the '<em><b>Funcgroupname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcgroupname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcgroupname</em>' attribute.
	 * @see #setFuncgroupname(java.lang.String)
	 */
	public String getFuncgroupname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.AcRolefuncView#getFuncgroupname <em>Funcgroupname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcgroupname</em>' attribute.
	 * @see #getFuncgroupname()
	 */
	public void setFuncgroupname(String funcgroupname);

	/**
	 * Returns the value of the '<em><b>Funccode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funccode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funccode</em>' attribute.
	 * @see #setFunccode(java.lang.String)
	 */
	public String getFunccode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.AcRolefuncView#getFunccode <em>Funccode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funccode</em>' attribute.
	 * @see #getFunccode()
	 */
	public void setFunccode(String funccode);

	/**
	 * Returns the value of the '<em><b>Funcname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcname</em>' attribute.
	 * @see #setFuncname(java.lang.String)
	 */
	public String getFuncname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.AcRolefuncView#getFuncname <em>Funcname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcname</em>' attribute.
	 * @see #getFuncname()
	 */
	public void setFuncname(String funcname);


}