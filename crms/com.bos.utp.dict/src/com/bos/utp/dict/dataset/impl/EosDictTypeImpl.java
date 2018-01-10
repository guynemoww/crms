/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dict.dataset.impl;

import com.bos.utp.dict.dataset.EosDictType;
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
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements EosDictType;
 */

public class EosDictTypeImpl extends ExtendedDataObjectImpl implements EosDictType {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_DICTTYPEID = 0;
	public final static int INDEX_DICTTYPENAME = 1;
	public final static int INDEX_RANK = 2;
	public final static int INDEX_PARENTID = 3;
	public final static int INDEX_SEQNO = 4;
	public final static int SDO_PROPERTY_COUNT = 5;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EosDictTypeImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EosDictTypeImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>Dicttypeid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dicttypeid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dicttypeid</em>' attribute.
	 * @see #setDicttypeid(java.lang.String)
	 */
	public String getDicttypeid() {
		return DataUtil.toString(super.getByIndex(INDEX_DICTTYPEID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDicttypeid <em>Dicttypeid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dicttypeid</em>' attribute.
	 * @see #getDicttypeid()
	 */
	public void setDicttypeid(String dicttypeid) {
		super.setByIndex(INDEX_DICTTYPEID, dicttypeid);
	}

	/**
	 * Returns the value of the '<em><b>Dicttypename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dicttypename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dicttypename</em>' attribute.
	 * @see #setDicttypename(java.lang.String)
	 */
	public String getDicttypename() {
		return DataUtil.toString(super.getByIndex(INDEX_DICTTYPENAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getDicttypename <em>Dicttypename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dicttypename</em>' attribute.
	 * @see #getDicttypename()
	 */
	public void setDicttypename(String dicttypename) {
		super.setByIndex(INDEX_DICTTYPENAME, dicttypename);
	}

	/**
	 * Returns the value of the '<em><b>Rank</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rank</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rank</em>' attribute.
	 * @see #setRank(int)
	 */
	public int getRank() {
		return DataUtil.toInt(super.getByIndex(INDEX_RANK, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRank <em>Rank</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rank</em>' attribute.
	 * @see #getRank()
	 */
	public void setRank(int rank) {
		super.setByIndex(INDEX_RANK, rank);
	}

	/**
	 * Returns the value of the '<em><b>Parentid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parentid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parentid</em>' attribute.
	 * @see #setParentid(java.lang.String)
	 */
	public String getParentid() {
		return DataUtil.toString(super.getByIndex(INDEX_PARENTID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getParentid <em>Parentid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parentid</em>' attribute.
	 * @see #getParentid()
	 */
	public void setParentid(String parentid) {
		super.setByIndex(INDEX_PARENTID, parentid);
	}

	/**
	 * Returns the value of the '<em><b>Seqno</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seqno</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seqno</em>' attribute.
	 * @see #setSeqno(java.lang.String)
	 */
	public String getSeqno() {
		return DataUtil.toString(super.getByIndex(INDEX_SEQNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSeqno <em>Seqno</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seqno</em>' attribute.
	 * @see #getSeqno()
	 */
	public void setSeqno(String seqno) {
		super.setByIndex(INDEX_SEQNO, seqno);
	}


}