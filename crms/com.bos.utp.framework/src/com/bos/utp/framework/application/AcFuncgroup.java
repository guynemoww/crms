/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.framework.application;

import java.math.BigDecimal;

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
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncgroup#getFuncgroupid <em>Funcgroupid</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncgroup#getFuncgroupname <em>Funcgroupname</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncgroup#getGrouplevel <em>Grouplevel</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncgroup#getFuncgroupseq <em>Funcgroupseq</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncgroup#getIsleaf <em>Isleaf</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncgroup#getSubcount <em>Subcount</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncgroup#getAcFuncgroup <em>AcFuncgroup</em>}</li>
 *   <li>{@link com.bos.utp.dataset.privilege.AcFuncgroup#getAcApplication <em>AcApplication</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface AcFuncgroup extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.privilege.AcFuncgroup";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.privilege", "AcFuncgroup");

	public final static IObjectFactory<AcFuncgroup> FACTORY = new IObjectFactory<AcFuncgroup>() {
		public AcFuncgroup create() {
			return (AcFuncgroup) DataFactory.INSTANCE.create(TYPE);
		}
	};

	/**
	 * Returns the value of the '<em><b>Funcgroupid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcgroupid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcgroupid</em>' attribute.
	 * @see #setFuncgroupid(int)
	 */
	public int getFuncgroupid();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncgroup#getFuncgroupid <em>Funcgroupid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcgroupid</em>' attribute.
	 * @see #getFuncgroupid()
	 */
	public void setFuncgroupid(int funcgroupid);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncgroup#getFuncgroupname <em>Funcgroupname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcgroupname</em>' attribute.
	 * @see #getFuncgroupname()
	 */
	public void setFuncgroupname(String funcgroupname);

	/**
	 * Returns the value of the '<em><b>Grouplevel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grouplevel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grouplevel</em>' attribute.
	 * @see #setGrouplevel(long)
	 */
	public long getGrouplevel();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncgroup#getGrouplevel <em>Grouplevel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grouplevel</em>' attribute.
	 * @see #getGrouplevel()
	 */
	public void setGrouplevel(long grouplevel);

	/**
	 * Returns the value of the '<em><b>Funcgroupseq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Funcgroupseq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Funcgroupseq</em>' attribute.
	 * @see #setFuncgroupseq(java.lang.String)
	 */
	public String getFuncgroupseq();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncgroup#getFuncgroupseq <em>Funcgroupseq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Funcgroupseq</em>' attribute.
	 * @see #getFuncgroupseq()
	 */
	public void setFuncgroupseq(String funcgroupseq);

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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncgroup#getIsleaf <em>Isleaf</em>}' attribute.
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
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncgroup#getSubcount <em>Subcount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subcount</em>' attribute.
	 * @see #getSubcount()
	 */
	public void setSubcount(int subcount);

	/**
	 * Returns the value of the '<em><b>AcFuncgroup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcFuncgroup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcFuncgroup</em>' attribute.
	 * @see #setAcFuncgroup(com.bos.utp.dataset.privilege.AcFuncgroup)
	 */
	public AcFuncgroup getAppFuncgroup();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncgroup#getAcFuncgroup <em>AcFuncgroup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcFuncgroup</em>' attribute.
	 * @see #getAcFuncgroup()
	 */
	public void setAppFuncgroup(AcFuncgroup acFuncgroup);

	/**
	 * Returns the value of the '<em><b>AcApplication</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AcApplication</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AcApplication</em>' attribute.
	 * @see #setAcApplication(com.bos.utp.dataset.privilege.AcApplication)
	 */
	public AcApplication getAppApplication();

	/**
	 * Sets the value of the '{@link com.bos.utp.dataset.privilege.AcFuncgroup#getAcApplication <em>AcApplication</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AcApplication</em>' attribute.
	 * @see #getAcApplication()
	 */
	public void setAppApplication(AcApplication acApplication);


}