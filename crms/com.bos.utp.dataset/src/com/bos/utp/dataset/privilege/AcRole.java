/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.privilege;

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
 *   <li>{@link com.bos.utp.dataset.privilege.AcRole#getRoleid <em>Roleid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcRole#getRolename <em>Rolename</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcRole#getRoletype <em>Roletype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcRole#getRoledesc <em>Roledesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcRole#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcRole#getInitflag <em>Initflag</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcRole extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.AcRole";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "AcRole");

	public final static IObjectFactory<AcRole> FACTORY = new IObjectFactory<AcRole>() {
		public AcRole create() {
			return (AcRole) DataFactory.INSTANCE.create(TYPE);
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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcRole#getRoleid <em>Roleid</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcRole#getRolename <em>Rolename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rolename</em>' attribute.
	 * @see #getRolename()
	 */
	public void setRolename(String rolename);

	/**
	 * Returns the value of the '<em><b>Roletype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roletype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roletype</em>' attribute.
	 * @see #setRoletype(java.lang.String)
	 */
	public String getRoletype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcRole#getRoletype <em>Roletype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Roletype</em>' attribute.
	 * @see #getRoletype()
	 */
	public void setRoletype(String roletype);

	/**
	 * Returns the value of the '<em><b>Roledesc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roledesc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roledesc</em>' attribute.
	 * @see #setRoledesc(java.lang.String)
	 */
	public String getRoledesc();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcRole#getRoledesc <em>Roledesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Roledesc</em>' attribute.
	 * @see #getRoledesc()
	 */
	public void setRoledesc(String roledesc);

	/**
	 * Returns the value of the '<em><b>Appid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Appid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Appid</em>' attribute.
	 * @see #setAppid(int)
	 */
	public int getAppid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcRole#getAppid <em>Appid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appid</em>' attribute.
	 * @see #getAppid()
	 */
	public void setAppid(int appid);

	/**
	 * Returns the value of the '<em><b>Initflag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initflag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initflag</em>' attribute.
	 * @see #setInitflag(java.lang.String)
	 */
	public String getInitflag();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcRole#getInitflag <em>Initflag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initflag</em>' attribute.
	 * @see #getInitflag()
	 */
	public void setInitflag(String initflag);


}