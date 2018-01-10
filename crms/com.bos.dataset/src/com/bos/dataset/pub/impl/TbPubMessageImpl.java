/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.dataset.pub.impl;

import com.bos.dataset.pub.TbPubMessage;
import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.dataset.pub.impl.TbPubMessageImpl#getMsgId <em>MsgId</em>}</li>
 *   <li>{@link com.bos.dataset.pub.impl.TbPubMessageImpl#getCreateTime <em>CreateTime</em>}</li>
 *   <li>{@link com.bos.dataset.pub.impl.TbPubMessageImpl#getMobileNum <em>MobileNum</em>}</li>
 *   <li>{@link com.bos.dataset.pub.impl.TbPubMessageImpl#getMsgInfo <em>MsgInfo</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements TbPubMessage;
 */

public class TbPubMessageImpl extends ExtendedDataObjectImpl implements TbPubMessage {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_MSGID = 0;
	public final static int INDEX_CREATETIME = 1;
	public final static int INDEX_MOBILENUM = 2;
	public final static int INDEX_MSGINFO = 3;
	public final static int SDO_PROPERTY_COUNT = 4;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TbPubMessageImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TbPubMessageImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>MsgId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MsgId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MsgId</em>' attribute.
	 * @see #setMsgId(java.lang.String)
	 */
	public String getMsgId() {
		return DataUtil.toString(super.getByIndex(INDEX_MSGID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMsgId <em>MsgId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MsgId</em>' attribute.
	 * @see #getMsgId()
	 */
	public void setMsgId(String msgId) {
		super.setByIndex(INDEX_MSGID, msgId);
	}

	/**
	 * Returns the value of the '<em><b>CreateTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CreateTime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CreateTime</em>' attribute.
	 * @see #setCreateTime(java.util.Date)
	 */
	public Date getCreateTime() {
		return DataUtil.toDate(super.getByIndex(INDEX_CREATETIME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCreateTime <em>CreateTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CreateTime</em>' attribute.
	 * @see #getCreateTime()
	 */
	public void setCreateTime(Date createTime) {
		super.setByIndex(INDEX_CREATETIME, createTime);
	}

	/**
	 * Returns the value of the '<em><b>MobileNum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MobileNum</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MobileNum</em>' attribute.
	 * @see #setMobileNum(java.lang.String)
	 */
	public String getMobileNum() {
		return DataUtil.toString(super.getByIndex(INDEX_MOBILENUM, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMobileNum <em>MobileNum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MobileNum</em>' attribute.
	 * @see #getMobileNum()
	 */
	public void setMobileNum(String mobileNum) {
		super.setByIndex(INDEX_MOBILENUM, mobileNum);
	}

	/**
	 * Returns the value of the '<em><b>MsgInfo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MsgInfo</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MsgInfo</em>' attribute.
	 * @see #setMsgInfo(java.lang.String)
	 */
	public String getMsgInfo() {
		return DataUtil.toString(super.getByIndex(INDEX_MSGINFO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMsgInfo <em>MsgInfo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MsgInfo</em>' attribute.
	 * @see #getMsgInfo()
	 */
	public void setMsgInfo(String msgInfo) {
		super.setByIndex(INDEX_MSGINFO, msgInfo);
	}


}