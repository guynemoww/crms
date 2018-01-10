/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application;

import java.util.Date;

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
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getAppcode <em>Appcode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getAppname <em>Appname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getApptype <em>Apptype</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getIsopen <em>Isopen</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getOpendate <em>Opendate</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getUrl <em>Url</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getAppdesc <em>Appdesc</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getMaintenance <em>Maintenance</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getManarole <em>Manarole</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getDemo <em>Demo</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getIniwp <em>Iniwp</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getIntaskcenter <em>Intaskcenter</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getIpaddr <em>Ipaddr</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcApplication#getIpport <em>Ipport</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcApplication extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.AcApplication";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "AcApplication");

	public final static IObjectFactory<AcApplication> FACTORY = new IObjectFactory<AcApplication>() {
		public AcApplication create() {
			return (AcApplication) DataFactory.INSTANCE.create(TYPE);
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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getAppid <em>Appid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appid</em>' attribute.
	 * @see #getAppid()
	 */
	public void setAppid(int appid);

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
	public String getAppcode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getAppcode <em>Appcode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appcode</em>' attribute.
	 * @see #getAppcode()
	 */
	public void setAppcode(String appcode);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getAppname <em>Appname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appname</em>' attribute.
	 * @see #getAppname()
	 */
	public void setAppname(String appname);

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
	public String getApptype();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getApptype <em>Apptype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Apptype</em>' attribute.
	 * @see #getApptype()
	 */
	public void setApptype(String apptype);

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
	public String getIsopen();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getIsopen <em>Isopen</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isopen</em>' attribute.
	 * @see #getIsopen()
	 */
	public void setIsopen(String isopen);

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
	public Date getOpendate();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getOpendate <em>Opendate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opendate</em>' attribute.
	 * @see #getOpendate()
	 */
	public void setOpendate(Date opendate);

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
	public String getUrl();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 */
	public void setUrl(String url);

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
	public String getAppdesc();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getAppdesc <em>Appdesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appdesc</em>' attribute.
	 * @see #getAppdesc()
	 */
	public void setAppdesc(String appdesc);

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
	public int getMaintenance();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getMaintenance <em>Maintenance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maintenance</em>' attribute.
	 * @see #getMaintenance()
	 */
	public void setMaintenance(int maintenance);

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
	public String getManarole();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getManarole <em>Manarole</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manarole</em>' attribute.
	 * @see #getManarole()
	 */
	public void setManarole(String manarole);

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
	public String getDemo();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getDemo <em>Demo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Demo</em>' attribute.
	 * @see #getDemo()
	 */
	public void setDemo(String demo);

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
	public String getIniwp();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getIniwp <em>Iniwp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iniwp</em>' attribute.
	 * @see #getIniwp()
	 */
	public void setIniwp(String iniwp);

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
	public String getIntaskcenter();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getIntaskcenter <em>Intaskcenter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intaskcenter</em>' attribute.
	 * @see #getIntaskcenter()
	 */
	public void setIntaskcenter(String intaskcenter);

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
	public String getIpaddr();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getIpaddr <em>Ipaddr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ipaddr</em>' attribute.
	 * @see #getIpaddr()
	 */
	public void setIpaddr(String ipaddr);

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
	public String getIpport();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcApplication#getIpport <em>Ipport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ipport</em>' attribute.
	 * @see #getIpport()
	 */
	public void setIpport(String ipport);


}