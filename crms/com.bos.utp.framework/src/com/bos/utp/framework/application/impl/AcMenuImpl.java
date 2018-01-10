/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application.impl;

import com.bos.utp.framework.application.AcMenu;
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
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getMenuid <em>Menuid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getMenuname <em>Menuname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getMenulabel <em>Menulabel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getMenucode <em>Menucode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getMenuaction <em>Menuaction</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getUientry <em>Uientry</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getMenulevel <em>Menulevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getRootid <em>Rootid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getDisplayorder <em>Displayorder</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getImagepath <em>Imagepath</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getExpandpath <em>Expandpath</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getMenuseq <em>Menuseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getOpenmode <em>Openmode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getAppid <em>Appid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getFunccode <em>Funccode</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.impl.AcMenuImpl#getAcMenu <em>AcMenu</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements AcMenu;
 */

public class AcMenuImpl extends ExtendedDataObjectImpl implements AcMenu {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_MENUID = 0;
	public final static int INDEX_MENUNAME = 1;
	public final static int INDEX_MENULABEL = 2;
	public final static int INDEX_MENUCODE = 3;
	public final static int INDEX_ISLEAF = 4;
	public final static int INDEX_MENUACTION = 5;
	public final static int INDEX_PARAMETER = 6;
	public final static int INDEX_UIENTRY = 7;
	public final static int INDEX_MENULEVEL = 8;
	public final static int INDEX_ROOTID = 9;
	public final static int INDEX_DISPLAYORDER = 10;
	public final static int INDEX_IMAGEPATH = 11;
	public final static int INDEX_EXPANDPATH = 12;
	public final static int INDEX_MENUSEQ = 13;
	public final static int INDEX_OPENMODE = 14;
	public final static int INDEX_SUBCOUNT = 15;
	public final static int INDEX_APPID = 16;
	public final static int INDEX_FUNCCODE = 17;
	public final static int INDEX_ACMENU = 18;
	public final static int SDO_PROPERTY_COUNT = 19;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcMenuImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public AcMenuImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

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
	public String getMenuid() {
		return DataUtil.toString(super.getByIndex(INDEX_MENUID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMenuid <em>Menuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menuid</em>' attribute.
	 * @see #getMenuid()
	 */
	public void setMenuid(String menuid) {
		super.setByIndex(INDEX_MENUID, menuid);
	}

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
	public String getMenuname() {
		return DataUtil.toString(super.getByIndex(INDEX_MENUNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMenuname <em>Menuname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menuname</em>' attribute.
	 * @see #getMenuname()
	 */
	public void setMenuname(String menuname) {
		super.setByIndex(INDEX_MENUNAME, menuname);
	}

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
	public String getMenulabel() {
		return DataUtil.toString(super.getByIndex(INDEX_MENULABEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMenulabel <em>Menulabel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menulabel</em>' attribute.
	 * @see #getMenulabel()
	 */
	public void setMenulabel(String menulabel) {
		super.setByIndex(INDEX_MENULABEL, menulabel);
	}

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
	public String getMenucode() {
		return DataUtil.toString(super.getByIndex(INDEX_MENUCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMenucode <em>Menucode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menucode</em>' attribute.
	 * @see #getMenucode()
	 */
	public void setMenucode(String menucode) {
		super.setByIndex(INDEX_MENUCODE, menucode);
	}

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
	public String getIsleaf() {
		return DataUtil.toString(super.getByIndex(INDEX_ISLEAF, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIsleaf <em>Isleaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Isleaf</em>' attribute.
	 * @see #getIsleaf()
	 */
	public void setIsleaf(String isleaf) {
		super.setByIndex(INDEX_ISLEAF, isleaf);
	}

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
	public String getMenuaction() {
		return DataUtil.toString(super.getByIndex(INDEX_MENUACTION, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMenuaction <em>Menuaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menuaction</em>' attribute.
	 * @see #getMenuaction()
	 */
	public void setMenuaction(String menuaction) {
		super.setByIndex(INDEX_MENUACTION, menuaction);
	}

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
	public String getParameter() {
		return DataUtil.toString(super.getByIndex(INDEX_PARAMETER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getParameter <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' attribute.
	 * @see #getParameter()
	 */
	public void setParameter(String parameter) {
		super.setByIndex(INDEX_PARAMETER, parameter);
	}

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
	public String getUientry() {
		return DataUtil.toString(super.getByIndex(INDEX_UIENTRY, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getUientry <em>Uientry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uientry</em>' attribute.
	 * @see #getUientry()
	 */
	public void setUientry(String uientry) {
		super.setByIndex(INDEX_UIENTRY, uientry);
	}

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
	public long getMenulevel() {
		return DataUtil.toLong(super.getByIndex(INDEX_MENULEVEL, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMenulevel <em>Menulevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menulevel</em>' attribute.
	 * @see #getMenulevel()
	 */
	public void setMenulevel(long menulevel) {
		super.setByIndex(INDEX_MENULEVEL, menulevel);
	}

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
	public String getRootid() {
		return DataUtil.toString(super.getByIndex(INDEX_ROOTID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRootid <em>Rootid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rootid</em>' attribute.
	 * @see #getRootid()
	 */
	public void setRootid(String rootid) {
		super.setByIndex(INDEX_ROOTID, rootid);
	}

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
	public long getDisplayorder() {
		return DataUtil.toLong(super.getByIndex(INDEX_DISPLAYORDER, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDisplayorder <em>Displayorder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Displayorder</em>' attribute.
	 * @see #getDisplayorder()
	 */
	public void setDisplayorder(long displayorder) {
		super.setByIndex(INDEX_DISPLAYORDER, displayorder);
	}

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
	public String getImagepath() {
		return DataUtil.toString(super.getByIndex(INDEX_IMAGEPATH, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getImagepath <em>Imagepath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imagepath</em>' attribute.
	 * @see #getImagepath()
	 */
	public void setImagepath(String imagepath) {
		super.setByIndex(INDEX_IMAGEPATH, imagepath);
	}

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
	public String getExpandpath() {
		return DataUtil.toString(super.getByIndex(INDEX_EXPANDPATH, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getExpandpath <em>Expandpath</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expandpath</em>' attribute.
	 * @see #getExpandpath()
	 */
	public void setExpandpath(String expandpath) {
		super.setByIndex(INDEX_EXPANDPATH, expandpath);
	}

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
	public String getMenuseq() {
		return DataUtil.toString(super.getByIndex(INDEX_MENUSEQ, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMenuseq <em>Menuseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menuseq</em>' attribute.
	 * @see #getMenuseq()
	 */
	public void setMenuseq(String menuseq) {
		super.setByIndex(INDEX_MENUSEQ, menuseq);
	}

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
	public String getOpenmode() {
		return DataUtil.toString(super.getByIndex(INDEX_OPENMODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOpenmode <em>Openmode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Openmode</em>' attribute.
	 * @see #getOpenmode()
	 */
	public void setOpenmode(String openmode) {
		super.setByIndex(INDEX_OPENMODE, openmode);
	}

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
	public int getSubcount() {
		return DataUtil.toInt(super.getByIndex(INDEX_SUBCOUNT, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(int subcount) {
		super.setByIndex(INDEX_SUBCOUNT, subcount);
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
	public String getFunccode() {
		return DataUtil.toString(super.getByIndex(INDEX_FUNCCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getFunccode <em>Funccode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funccode</em>' attribute.
	 * @see #getFunccode()
	 */
	public void setFunccode(String funccode) {
		super.setByIndex(INDEX_FUNCCODE, funccode);
	}

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
	public AcMenu getAcMenu() {
		return (AcMenu) DataUtil.toDataObject(super.getByIndex(INDEX_ACMENU, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAcMenu <em>AcMenu</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcMenu</em>' attribute.
	 * @see #getAcMenu()
	 */
	public void setAcMenu(AcMenu acMenu) {
		super.setByIndex(INDEX_ACMENU, acMenu);
	}


}