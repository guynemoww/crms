/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application;

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
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getMenuid <em>Menuid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getMenuname <em>Menuname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getMenulabel <em>Menulabel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getMenucode <em>Menucode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getMenuaction <em>Menuaction</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getParameter <em>Parameter</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getUientry <em>Uientry</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getMenulevel <em>Menulevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getRootid <em>Rootid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getDisplayorder <em>Displayorder</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getImagepath <em>Imagepath</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getExpandpath <em>Expandpath</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getMenuseq <em>Menuseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getOpenmode <em>Openmode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getFunccode <em>Funccode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcMenu#getAcMenu <em>AcMenu</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcMenu extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.AcMenu";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "AcMenu");

	public final static IObjectFactory<AcMenu> FACTORY = new IObjectFactory<AcMenu>() {
		public AcMenu create() {
			return (AcMenu) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Menuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menuid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menuid</em>' attribute.
	 * @see #setMenuid(java.lang.String)
	 */
	public String getMenuid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getMenuid <em>Menuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menuid</em>' attribute.
	 * @see #getMenuid()
	 */
	public void setMenuid(String menuid);

	/**
	 * Returns the value of the '<em><b>Menuname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menuname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menuname</em>' attribute.
	 * @see #setMenuname(java.lang.String)
	 */
	public String getMenuname();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getMenuname <em>Menuname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menuname</em>' attribute.
	 * @see #getMenuname()
	 */
	public void setMenuname(String menuname);

	/**
	 * Returns the value of the '<em><b>Menulabel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menulabel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menulabel</em>' attribute.
	 * @see #setMenulabel(java.lang.String)
	 */
	public String getMenulabel();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getMenulabel <em>Menulabel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menulabel</em>' attribute.
	 * @see #getMenulabel()
	 */
	public void setMenulabel(String menulabel);

	/**
	 * Returns the value of the '<em><b>Menucode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menucode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menucode</em>' attribute.
	 * @see #setMenucode(java.lang.String)
	 */
	public String getMenucode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getMenucode <em>Menucode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menucode</em>' attribute.
	 * @see #getMenucode()
	 */
	public void setMenucode(String menucode);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getIsleaf <em>Isleaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isleaf</em>' attribute.
	 * @see #getIsleaf()
	 */
	public void setIsleaf(String isleaf);

	/**
	 * Returns the value of the '<em><b>Menuaction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menuaction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menuaction</em>' attribute.
	 * @see #setMenuaction(java.lang.String)
	 */
	public String getMenuaction();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getMenuaction <em>Menuaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menuaction</em>' attribute.
	 * @see #getMenuaction()
	 */
	public void setMenuaction(String menuaction);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' attribute.
	 * @see #setParameter(java.lang.String)
	 */
	public String getParameter();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getParameter <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' attribute.
	 * @see #getParameter()
	 */
	public void setParameter(String parameter);

	/**
	 * Returns the value of the '<em><b>Uientry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uientry</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uientry</em>' attribute.
	 * @see #setUientry(java.lang.String)
	 */
	public String getUientry();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getUientry <em>Uientry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uientry</em>' attribute.
	 * @see #getUientry()
	 */
	public void setUientry(String uientry);

	/**
	 * Returns the value of the '<em><b>Menulevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menulevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menulevel</em>' attribute.
	 * @see #setMenulevel(long)
	 */
	public long getMenulevel();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getMenulevel <em>Menulevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menulevel</em>' attribute.
	 * @see #getMenulevel()
	 */
	public void setMenulevel(long menulevel);

	/**
	 * Returns the value of the '<em><b>Rootid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rootid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rootid</em>' attribute.
	 * @see #setRootid(java.lang.String)
	 */
	public String getRootid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getRootid <em>Rootid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rootid</em>' attribute.
	 * @see #getRootid()
	 */
	public void setRootid(String rootid);

	/**
	 * Returns the value of the '<em><b>Displayorder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Displayorder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Displayorder</em>' attribute.
	 * @see #setDisplayorder(long)
	 */
	public long getDisplayorder();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getDisplayorder <em>Displayorder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Displayorder</em>' attribute.
	 * @see #getDisplayorder()
	 */
	public void setDisplayorder(long displayorder);

	/**
	 * Returns the value of the '<em><b>Imagepath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imagepath</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imagepath</em>' attribute.
	 * @see #setImagepath(java.lang.String)
	 */
	public String getImagepath();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getImagepath <em>Imagepath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imagepath</em>' attribute.
	 * @see #getImagepath()
	 */
	public void setImagepath(String imagepath);

	/**
	 * Returns the value of the '<em><b>Expandpath</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expandpath</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expandpath</em>' attribute.
	 * @see #setExpandpath(java.lang.String)
	 */
	public String getExpandpath();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getExpandpath <em>Expandpath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expandpath</em>' attribute.
	 * @see #getExpandpath()
	 */
	public void setExpandpath(String expandpath);

	/**
	 * Returns the value of the '<em><b>Menuseq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menuseq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menuseq</em>' attribute.
	 * @see #setMenuseq(java.lang.String)
	 */
	public String getMenuseq();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getMenuseq <em>Menuseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menuseq</em>' attribute.
	 * @see #getMenuseq()
	 */
	public void setMenuseq(String menuseq);

	/**
	 * Returns the value of the '<em><b>Openmode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Openmode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Openmode</em>' attribute.
	 * @see #setOpenmode(java.lang.String)
	 */
	public String getOpenmode();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getOpenmode <em>Openmode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Openmode</em>' attribute.
	 * @see #getOpenmode()
	 */
	public void setOpenmode(String openmode);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(int subcount);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getAppid <em>Appid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appid</em>' attribute.
	 * @see #getAppid()
	 */
	public void setAppid(int appid);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getFunccode <em>Funccode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funccode</em>' attribute.
	 * @see #getFunccode()
	 */
	public void setFunccode(String funccode);

	/**
	 * Returns the value of the '<em><b>AcMenu</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcMenu</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcMenu</em>' attribute.
	 * @see #setAcMenu(com.bos.utp.dataset.privilege.AcMenu)
	 */
	public AcMenu getAcMenu();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcMenu#getAcMenu <em>AcMenu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcMenu</em>' attribute.
	 * @see #getAcMenu()
	 */
	public void setAcMenu(AcMenu acMenu);


}