/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.dataset.pub;

import com.eos.data.sdo.IObjectFactory;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.TypeHelper;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.dataset.pub.TbPubMessage#getMsgId <em>MsgId</em>}</li>
 *   <li>{@link com.bos.dataset.pub.TbPubMessage#getCreateTime <em>CreateTime</em>}</li>
 *   <li>{@link com.bos.dataset.pub.TbPubMessage#getMobileNum <em>MobileNum</em>}</li>
 *   <li>{@link com.bos.dataset.pub.TbPubMessage#getMsgInfo <em>MsgInfo</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface TbPubMessage extends DataObject {

	public final static String QNAME = "com.bos.dataset.pub.TbPubMessage";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.dataset.pub", "TbPubMessage");

	public final static IObjectFactory<TbPubMessage> FACTORY = new IObjectFactory<TbPubMessage>() {
		public TbPubMessage create() {
			return (TbPubMessage) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public String getMsgId();

	/**
	 * Sets the value of the '{@link com.bos.dataset.pub.TbPubMessage#getMsgId <em>MsgId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MsgId</em>' attribute.
	 * @see #getMsgId()
	 */
	public void setMsgId(String msgId);

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
	public Date getCreateTime();

	/**
	 * Sets the value of the '{@link com.bos.dataset.pub.TbPubMessage#getCreateTime <em>CreateTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CreateTime</em>' attribute.
	 * @see #getCreateTime()
	 */
	public void setCreateTime(Date createTime);

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
	public String getMobileNum();

	/**
	 * Sets the value of the '{@link com.bos.dataset.pub.TbPubMessage#getMobileNum <em>MobileNum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MobileNum</em>' attribute.
	 * @see #getMobileNum()
	 */
	public void setMobileNum(String mobileNum);

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
	public String getMsgInfo();

	/**
	 * Sets the value of the '{@link com.bos.dataset.pub.TbPubMessage#getMsgInfo <em>MsgInfo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MsgInfo</em>' attribute.
	 * @see #getMsgInfo()
	 */
	public void setMsgInfo(String msgInfo);


}