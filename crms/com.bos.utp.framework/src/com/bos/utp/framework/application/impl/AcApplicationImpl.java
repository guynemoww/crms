/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application.impl;

import java.util.Date;

import com.bos.utp.framework.application.AcApplication;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getAppcode <em>Appcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getAppname <em>Appname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getApptype <em>Apptype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getIsopen <em>Isopen</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getOpendate <em>Opendate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getAppdesc <em>Appdesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getMaintenance <em>Maintenance</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getManarole <em>Manarole</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getDemo <em>Demo</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getIniwp <em>Iniwp</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getIntaskcenter <em>Intaskcenter</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getIpaddr <em>Ipaddr</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcApplicationImpl#getIpport <em>Ipport</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcApplication;
 */

public class AcApplicationImpl extends ExtendedDataObjectImpl implements AcApplication {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_APPID = 0;
	public final static int INDEX_APPCODE = 1;
	public final static int INDEX_APPNAME = 2;
	public final static int INDEX_APPTYPE = 3;
	public final static int INDEX_ISOPEN = 4;
	public final static int INDEX_OPENDATE = 5;
	public final static int INDEX_URL = 6;
	public final static int INDEX_APPDESC = 7;
	public final static int INDEX_MAINTENANCE = 8;
	public final static int INDEX_MANAROLE = 9;
	public final static int INDEX_DEMO = 10;
	public final static int INDEX_INIWP = 11;
	public final static int INDEX_INTASKCENTER = 12;
	public final static int INDEX_IPADDR = 13;
	public final static int INDEX_IPPORT = 14;
	public final static int SDO_PROPERTY_COUNT = 15;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcApplicationImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcApplicationImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

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
	public int getAppid() {
		return DataUtil.toInt(super.getByIndex(INDEX_APPID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAppid <em>Appid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appid</em>' attribute.
	 * @see #getAppid()
	 */
	public void setAppid(int appid) {
		super.setByIndex(INDEX_APPID, appid);
	}

	/**
	 * Returns the value of the '<em><b>Appcode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Appcode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Appcode</em>' attribute.
	 * @see #setAppcode(java.lang.String)
	 */
	public String getAppcode() {
		return DataUtil.toString(super.getByIndex(INDEX_APPCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAppcode <em>Appcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appcode</em>' attribute.
	 * @see #getAppcode()
	 */
	public void setAppcode(String appcode) {
		super.setByIndex(INDEX_APPCODE, appcode);
	}

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
	public String getAppname() {
		return DataUtil.toString(super.getByIndex(INDEX_APPNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAppname <em>Appname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appname</em>' attribute.
	 * @see #getAppname()
	 */
	public void setAppname(String appname) {
		super.setByIndex(INDEX_APPNAME, appname);
	}

	/**
	 * Returns the value of the '<em><b>Apptype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Apptype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Apptype</em>' attribute.
	 * @see #setApptype(java.lang.String)
	 */
	public String getApptype() {
		return DataUtil.toString(super.getByIndex(INDEX_APPTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getApptype <em>Apptype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Apptype</em>' attribute.
	 * @see #getApptype()
	 */
	public void setApptype(String apptype) {
		super.setByIndex(INDEX_APPTYPE, apptype);
	}

	/**
	 * Returns the value of the '<em><b>Isopen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isopen</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isopen</em>' attribute.
	 * @see #setIsopen(java.lang.String)
	 */
	public String getIsopen() {
		return DataUtil.toString(super.getByIndex(INDEX_ISOPEN, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsopen <em>Isopen</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isopen</em>' attribute.
	 * @see #getIsopen()
	 */
	public void setIsopen(String isopen) {
		super.setByIndex(INDEX_ISOPEN, isopen);
	}

	/**
	 * Returns the value of the '<em><b>Opendate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Opendate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Opendate</em>' attribute.
	 * @see #setOpendate(java.util.Date)
	 */
	public Date getOpendate() {
		return DataUtil.toDate(super.getByIndex(INDEX_OPENDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOpendate <em>Opendate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opendate</em>' attribute.
	 * @see #getOpendate()
	 */
	public void setOpendate(Date opendate) {
		super.setByIndex(INDEX_OPENDATE, opendate);
	}

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(java.lang.String)
	 */
	public String getUrl() {
		return DataUtil.toString(super.getByIndex(INDEX_URL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 */
	public void setUrl(String url) {
		super.setByIndex(INDEX_URL, url);
	}

	/**
	 * Returns the value of the '<em><b>Appdesc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Appdesc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Appdesc</em>' attribute.
	 * @see #setAppdesc(java.lang.String)
	 */
	public String getAppdesc() {
		return DataUtil.toString(super.getByIndex(INDEX_APPDESC, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAppdesc <em>Appdesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appdesc</em>' attribute.
	 * @see #getAppdesc()
	 */
	public void setAppdesc(String appdesc) {
		super.setByIndex(INDEX_APPDESC, appdesc);
	}

	/**
	 * Returns the value of the '<em><b>Maintenance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maintenance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maintenance</em>' attribute.
	 * @see #setMaintenance(int)
	 */
	public int getMaintenance() {
		return DataUtil.toInt(super.getByIndex(INDEX_MAINTENANCE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMaintenance <em>Maintenance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maintenance</em>' attribute.
	 * @see #getMaintenance()
	 */
	public void setMaintenance(int maintenance) {
		super.setByIndex(INDEX_MAINTENANCE, maintenance);
	}

	/**
	 * Returns the value of the '<em><b>Manarole</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manarole</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manarole</em>' attribute.
	 * @see #setManarole(java.lang.String)
	 */
	public String getManarole() {
		return DataUtil.toString(super.getByIndex(INDEX_MANAROLE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getManarole <em>Manarole</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manarole</em>' attribute.
	 * @see #getManarole()
	 */
	public void setManarole(String manarole) {
		super.setByIndex(INDEX_MANAROLE, manarole);
	}

	/**
	 * Returns the value of the '<em><b>Demo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Demo</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Demo</em>' attribute.
	 * @see #setDemo(java.lang.String)
	 */
	public String getDemo() {
		return DataUtil.toString(super.getByIndex(INDEX_DEMO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDemo <em>Demo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Demo</em>' attribute.
	 * @see #getDemo()
	 */
	public void setDemo(String demo) {
		super.setByIndex(INDEX_DEMO, demo);
	}

	/**
	 * Returns the value of the '<em><b>Iniwp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iniwp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iniwp</em>' attribute.
	 * @see #setIniwp(java.lang.String)
	 */
	public String getIniwp() {
		return DataUtil.toString(super.getByIndex(INDEX_INIWP, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIniwp <em>Iniwp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iniwp</em>' attribute.
	 * @see #getIniwp()
	 */
	public void setIniwp(String iniwp) {
		super.setByIndex(INDEX_INIWP, iniwp);
	}

	/**
	 * Returns the value of the '<em><b>Intaskcenter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intaskcenter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intaskcenter</em>' attribute.
	 * @see #setIntaskcenter(java.lang.String)
	 */
	public String getIntaskcenter() {
		return DataUtil.toString(super.getByIndex(INDEX_INTASKCENTER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIntaskcenter <em>Intaskcenter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intaskcenter</em>' attribute.
	 * @see #getIntaskcenter()
	 */
	public void setIntaskcenter(String intaskcenter) {
		super.setByIndex(INDEX_INTASKCENTER, intaskcenter);
	}

	/**
	 * Returns the value of the '<em><b>Ipaddr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ipaddr</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ipaddr</em>' attribute.
	 * @see #setIpaddr(java.lang.String)
	 */
	public String getIpaddr() {
		return DataUtil.toString(super.getByIndex(INDEX_IPADDR, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIpaddr <em>Ipaddr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ipaddr</em>' attribute.
	 * @see #getIpaddr()
	 */
	public void setIpaddr(String ipaddr) {
		super.setByIndex(INDEX_IPADDR, ipaddr);
	}

	/**
	 * Returns the value of the '<em><b>Ipport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ipport</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ipport</em>' attribute.
	 * @see #setIpport(java.lang.String)
	 */
	public String getIpport() {
		return DataUtil.toString(super.getByIndex(INDEX_IPPORT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIpport <em>Ipport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ipport</em>' attribute.
	 * @see #getIpport()
	 */
	public void setIpport(String ipport) {
		super.setByIndex(INDEX_IPPORT, ipport);
	}


}