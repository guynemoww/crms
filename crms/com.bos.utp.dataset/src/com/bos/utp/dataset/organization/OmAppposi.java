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
 *   <li>{@link com.bos.utp.dataset.organization.OmAppposi#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.organization.OmAppposi#getOmPosition <em>OmPosition</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OmAppposi extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.OmAppposi";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "OmAppposi");

	public final static IObjectFactory<OmAppposi> FACTORY = new IObjectFactory<OmAppposi>() {
		public OmAppposi create() {
			return (OmAppposi) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmAppposi#getAppid <em>Appid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appid</em>' attribute.
	 * @see #getAppid()
	 */
	public void setAppid(int appid);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.organization.OmAppposi#getOmPosition <em>OmPosition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OmPosition</em>' attribute.
	 * @see #getOmPosition()
	 */
	public void setOmPosition(OmPosition omPosition);


}