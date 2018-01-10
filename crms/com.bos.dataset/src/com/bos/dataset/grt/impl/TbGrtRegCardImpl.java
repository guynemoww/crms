/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.dataset.grt.impl;

import com.bos.dataset.grt.TbGrtRegCard;
import com.primeton.ext.data.sdo.DataUtil;
import com.primeton.ext.data.sdo.ExtendedDataObjectImpl;

import commonj.sdo.Type;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getSuretyKeyId <em>SuretyKeyId</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getSuretyId <em>SuretyId</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getCardType <em>CardType</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getCardState <em>CardState</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getRegOrgName <em>RegOrgName</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getRegOrgMoney <em>RegOrgMoney</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getCardRegDate <em>CardRegDate</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getRegDueDate <em>RegDueDate</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getSaveOrg <em>SaveOrg</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getCreateTime <em>CreateTime</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getUpdateTime <em>UpdateTime</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getRegisterCertiNo <em>RegisterCertiNo</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getUserNum <em>UserNum</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getPartyId <em>PartyId</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getInOutNo <em>InOutNo</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getOrgNum <em>OrgNum</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getMainSuretyKeyId <em>MainSuretyKeyId</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getRegEffecticeMode <em>RegEffecticeMode</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getIfEfps <em>IfEfps</em>}</li>
 *   <li>{@link com.bos.dataset.grt.impl.TbGrtRegCardImpl#getAccOrgCode <em>AccOrgCode</em>}</li>
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements TbGrtRegCard;
 */

public class TbGrtRegCardImpl extends ExtendedDataObjectImpl implements TbGrtRegCard {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int INDEX_SURETYKEYID = 0;
	public final static int INDEX_SURETYID = 1;
	public final static int INDEX_CARDTYPE = 2;
	public final static int INDEX_CARDSTATE = 3;
	public final static int INDEX_REGORGNAME = 4;
	public final static int INDEX_REGORGMONEY = 5;
	public final static int INDEX_CARDREGDATE = 6;
	public final static int INDEX_REGDUEDATE = 7;
	public final static int INDEX_SAVEORG = 8;
	public final static int INDEX_CREATETIME = 9;
	public final static int INDEX_UPDATETIME = 10;
	public final static int INDEX_REGISTERCERTINO = 11;
	public final static int INDEX_USERNUM = 12;
	public final static int INDEX_PARTYID = 13;
	public final static int INDEX_INOUTNO = 14;
	public final static int INDEX_ORGNUM = 15;
	public final static int INDEX_MAINSURETYKEYID = 16;
	public final static int INDEX_REGEFFECTICEMODE = 17;
	public final static int INDEX_IFEFPS = 18;
	public final static int INDEX_ACCORGCODE = 19;
	public final static int SDO_PROPERTY_COUNT = 20;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TbGrtRegCardImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public TbGrtRegCardImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	/**
	 * Returns the value of the '<em><b>SuretyKeyId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SuretyKeyId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SuretyKeyId</em>' attribute.
	 * @see #setSuretyKeyId(java.lang.String)
	 */
	public String getSuretyKeyId() {
		return DataUtil.toString(super.getByIndex(INDEX_SURETYKEYID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSuretyKeyId <em>SuretyKeyId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SuretyKeyId</em>' attribute.
	 * @see #getSuretyKeyId()
	 */
	public void setSuretyKeyId(String suretyKeyId) {
		super.setByIndex(INDEX_SURETYKEYID, suretyKeyId);
	}

	/**
	 * Returns the value of the '<em><b>SuretyId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SuretyId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SuretyId</em>' attribute.
	 * @see #setSuretyId(java.lang.String)
	 */
	public String getSuretyId() {
		return DataUtil.toString(super.getByIndex(INDEX_SURETYID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSuretyId <em>SuretyId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SuretyId</em>' attribute.
	 * @see #getSuretyId()
	 */
	public void setSuretyId(String suretyId) {
		super.setByIndex(INDEX_SURETYID, suretyId);
	}

	/**
	 * Returns the value of the '<em><b>CardType</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CardType</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CardType</em>' attribute.
	 * @see #setCardType(java.lang.String)
	 */
	public String getCardType() {
		return DataUtil.toString(super.getByIndex(INDEX_CARDTYPE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCardType <em>CardType</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CardType</em>' attribute.
	 * @see #getCardType()
	 */
	public void setCardType(String cardType) {
		super.setByIndex(INDEX_CARDTYPE, cardType);
	}

	/**
	 * Returns the value of the '<em><b>CardState</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CardState</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CardState</em>' attribute.
	 * @see #setCardState(java.lang.String)
	 */
	public String getCardState() {
		return DataUtil.toString(super.getByIndex(INDEX_CARDSTATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCardState <em>CardState</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CardState</em>' attribute.
	 * @see #getCardState()
	 */
	public void setCardState(String cardState) {
		super.setByIndex(INDEX_CARDSTATE, cardState);
	}

	/**
	 * Returns the value of the '<em><b>RegOrgName</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RegOrgName</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RegOrgName</em>' attribute.
	 * @see #setRegOrgName(java.lang.String)
	 */
	public String getRegOrgName() {
		return DataUtil.toString(super.getByIndex(INDEX_REGORGNAME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRegOrgName <em>RegOrgName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegOrgName</em>' attribute.
	 * @see #getRegOrgName()
	 */
	public void setRegOrgName(String regOrgName) {
		super.setByIndex(INDEX_REGORGNAME, regOrgName);
	}

	/**
	 * Returns the value of the '<em><b>RegOrgMoney</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RegOrgMoney</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RegOrgMoney</em>' attribute.
	 * @see #setRegOrgMoney(java.math.BigDecimal)
	 */
	public BigDecimal getRegOrgMoney() {
		return DataUtil.toBigDecimal(super.getByIndex(INDEX_REGORGMONEY, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRegOrgMoney <em>RegOrgMoney</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegOrgMoney</em>' attribute.
	 * @see #getRegOrgMoney()
	 */
	public void setRegOrgMoney(BigDecimal regOrgMoney) {
		super.setByIndex(INDEX_REGORGMONEY, regOrgMoney);
	}

	/**
	 * Returns the value of the '<em><b>CardRegDate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CardRegDate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CardRegDate</em>' attribute.
	 * @see #setCardRegDate(java.util.Date)
	 */
	public Date getCardRegDate() {
		return DataUtil.toDate(super.getByIndex(INDEX_CARDREGDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getCardRegDate <em>CardRegDate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CardRegDate</em>' attribute.
	 * @see #getCardRegDate()
	 */
	public void setCardRegDate(Date cardRegDate) {
		super.setByIndex(INDEX_CARDREGDATE, cardRegDate);
	}

	/**
	 * Returns the value of the '<em><b>RegDueDate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RegDueDate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RegDueDate</em>' attribute.
	 * @see #setRegDueDate(java.util.Date)
	 */
	public Date getRegDueDate() {
		return DataUtil.toDate(super.getByIndex(INDEX_REGDUEDATE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRegDueDate <em>RegDueDate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegDueDate</em>' attribute.
	 * @see #getRegDueDate()
	 */
	public void setRegDueDate(Date regDueDate) {
		super.setByIndex(INDEX_REGDUEDATE, regDueDate);
	}

	/**
	 * Returns the value of the '<em><b>SaveOrg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SaveOrg</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SaveOrg</em>' attribute.
	 * @see #setSaveOrg(java.lang.String)
	 */
	public String getSaveOrg() {
		return DataUtil.toString(super.getByIndex(INDEX_SAVEORG, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getSaveOrg <em>SaveOrg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SaveOrg</em>' attribute.
	 * @see #getSaveOrg()
	 */
	public void setSaveOrg(String saveOrg) {
		super.setByIndex(INDEX_SAVEORG, saveOrg);
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
	 * Returns the value of the '<em><b>UpdateTime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>UpdateTime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>UpdateTime</em>' attribute.
	 * @see #setUpdateTime(java.util.Date)
	 */
	public Date getUpdateTime() {
		return DataUtil.toDate(super.getByIndex(INDEX_UPDATETIME, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getUpdateTime <em>UpdateTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UpdateTime</em>' attribute.
	 * @see #getUpdateTime()
	 */
	public void setUpdateTime(Date updateTime) {
		super.setByIndex(INDEX_UPDATETIME, updateTime);
	}

	/**
	 * Returns the value of the '<em><b>RegisterCertiNo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RegisterCertiNo</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RegisterCertiNo</em>' attribute.
	 * @see #setRegisterCertiNo(java.lang.String)
	 */
	public String getRegisterCertiNo() {
		return DataUtil.toString(super.getByIndex(INDEX_REGISTERCERTINO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRegisterCertiNo <em>RegisterCertiNo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegisterCertiNo</em>' attribute.
	 * @see #getRegisterCertiNo()
	 */
	public void setRegisterCertiNo(String registerCertiNo) {
		super.setByIndex(INDEX_REGISTERCERTINO, registerCertiNo);
	}

	/**
	 * Returns the value of the '<em><b>UserNum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>UserNum</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>UserNum</em>' attribute.
	 * @see #setUserNum(java.lang.String)
	 */
	public String getUserNum() {
		return DataUtil.toString(super.getByIndex(INDEX_USERNUM, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getUserNum <em>UserNum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UserNum</em>' attribute.
	 * @see #getUserNum()
	 */
	public void setUserNum(String userNum) {
		super.setByIndex(INDEX_USERNUM, userNum);
	}

	/**
	 * Returns the value of the '<em><b>PartyId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>PartyId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>PartyId</em>' attribute.
	 * @see #setPartyId(java.lang.String)
	 */
	public String getPartyId() {
		return DataUtil.toString(super.getByIndex(INDEX_PARTYID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getPartyId <em>PartyId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PartyId</em>' attribute.
	 * @see #getPartyId()
	 */
	public void setPartyId(String partyId) {
		super.setByIndex(INDEX_PARTYID, partyId);
	}

	/**
	 * Returns the value of the '<em><b>InOutNo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>InOutNo</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>InOutNo</em>' attribute.
	 * @see #setInOutNo(java.lang.String)
	 */
	public String getInOutNo() {
		return DataUtil.toString(super.getByIndex(INDEX_INOUTNO, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getInOutNo <em>InOutNo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>InOutNo</em>' attribute.
	 * @see #getInOutNo()
	 */
	public void setInOutNo(String inOutNo) {
		super.setByIndex(INDEX_INOUTNO, inOutNo);
	}

	/**
	 * Returns the value of the '<em><b>OrgNum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>OrgNum</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>OrgNum</em>' attribute.
	 * @see #setOrgNum(java.lang.String)
	 */
	public String getOrgNum() {
		return DataUtil.toString(super.getByIndex(INDEX_ORGNUM, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getOrgNum <em>OrgNum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgNum</em>' attribute.
	 * @see #getOrgNum()
	 */
	public void setOrgNum(String orgNum) {
		super.setByIndex(INDEX_ORGNUM, orgNum);
	}

	/**
	 * Returns the value of the '<em><b>MainSuretyKeyId</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MainSuretyKeyId</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MainSuretyKeyId</em>' attribute.
	 * @see #setMainSuretyKeyId(java.lang.String)
	 */
	public String getMainSuretyKeyId() {
		return DataUtil.toString(super.getByIndex(INDEX_MAINSURETYKEYID, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getMainSuretyKeyId <em>MainSuretyKeyId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MainSuretyKeyId</em>' attribute.
	 * @see #getMainSuretyKeyId()
	 */
	public void setMainSuretyKeyId(String mainSuretyKeyId) {
		super.setByIndex(INDEX_MAINSURETYKEYID, mainSuretyKeyId);
	}

	/**
	 * Returns the value of the '<em><b>RegEffecticeMode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RegEffecticeMode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RegEffecticeMode</em>' attribute.
	 * @see #setRegEffecticeMode(java.lang.String)
	 */
	public String getRegEffecticeMode() {
		return DataUtil.toString(super.getByIndex(INDEX_REGEFFECTICEMODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getRegEffecticeMode <em>RegEffecticeMode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegEffecticeMode</em>' attribute.
	 * @see #getRegEffecticeMode()
	 */
	public void setRegEffecticeMode(String regEffecticeMode) {
		super.setByIndex(INDEX_REGEFFECTICEMODE, regEffecticeMode);
	}

	/**
	 * Returns the value of the '<em><b>IfEfps</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IfEfps</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IfEfps</em>' attribute.
	 * @see #setIfEfps(java.lang.String)
	 */
	public String getIfEfps() {
		return DataUtil.toString(super.getByIndex(INDEX_IFEFPS, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getIfEfps <em>IfEfps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IfEfps</em>' attribute.
	 * @see #getIfEfps()
	 */
	public void setIfEfps(String ifEfps) {
		super.setByIndex(INDEX_IFEFPS, ifEfps);
	}

	/**
	 * Returns the value of the '<em><b>AccOrgCode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AccOrgCode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AccOrgCode</em>' attribute.
	 * @see #setAccOrgCode(java.lang.String)
	 */
	public String getAccOrgCode() {
		return DataUtil.toString(super.getByIndex(INDEX_ACCORGCODE, true));
	}

	/**
	 * Sets the value of the '{@link com.primeton.eos.Test#getAccOrgCode <em>AccOrgCode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AccOrgCode</em>' attribute.
	 * @see #getAccOrgCode()
	 */
	public void setAccOrgCode(String accOrgCode) {
		super.setByIndex(INDEX_ACCORGCODE, accOrgCode);
	}


}