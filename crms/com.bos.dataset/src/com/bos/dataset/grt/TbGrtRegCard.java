/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.dataset.grt;

import com.eos.data.sdo.IObjectFactory;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.TypeHelper;

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
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getSuretyKeyId <em>SuretyKeyId</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getSuretyId <em>SuretyId</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getCardType <em>CardType</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getCardState <em>CardState</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getRegOrgName <em>RegOrgName</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getRegOrgMoney <em>RegOrgMoney</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getCardRegDate <em>CardRegDate</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getRegDueDate <em>RegDueDate</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getSaveOrg <em>SaveOrg</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getCreateTime <em>CreateTime</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getUpdateTime <em>UpdateTime</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getRegisterCertiNo <em>RegisterCertiNo</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getUserNum <em>UserNum</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getPartyId <em>PartyId</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getInOutNo <em>InOutNo</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getOrgNum <em>OrgNum</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getMainSuretyKeyId <em>MainSuretyKeyId</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getRegEffecticeMode <em>RegEffecticeMode</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getIfEfps <em>IfEfps</em>}</li>
 *   <li>{@link com.bos.dataset.grt.TbGrtRegCard#getAccOrgCode <em>AccOrgCode</em>}</li>
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface TbGrtRegCard extends DataObject {

	public final static String QNAME = "com.bos.dataset.grt.TbGrtRegCard";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.dataset.grt", "TbGrtRegCard");

	public final static IObjectFactory<TbGrtRegCard> FACTORY = new IObjectFactory<TbGrtRegCard>() {
		public TbGrtRegCard create() {
			return (TbGrtRegCard) DataFactory.INSTANCE.create(TYPE);
		}
	};

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
	public String getSuretyKeyId();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getSuretyKeyId <em>SuretyKeyId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SuretyKeyId</em>' attribute.
	 * @see #getSuretyKeyId()
	 */
	public void setSuretyKeyId(String suretyKeyId);

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
	public String getSuretyId();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getSuretyId <em>SuretyId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SuretyId</em>' attribute.
	 * @see #getSuretyId()
	 */
	public void setSuretyId(String suretyId);

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
	public String getCardType();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getCardType <em>CardType</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CardType</em>' attribute.
	 * @see #getCardType()
	 */
	public void setCardType(String cardType);

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
	public String getCardState();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getCardState <em>CardState</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CardState</em>' attribute.
	 * @see #getCardState()
	 */
	public void setCardState(String cardState);

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
	public String getRegOrgName();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getRegOrgName <em>RegOrgName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegOrgName</em>' attribute.
	 * @see #getRegOrgName()
	 */
	public void setRegOrgName(String regOrgName);

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
	public BigDecimal getRegOrgMoney();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getRegOrgMoney <em>RegOrgMoney</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegOrgMoney</em>' attribute.
	 * @see #getRegOrgMoney()
	 */
	public void setRegOrgMoney(BigDecimal regOrgMoney);

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
	public Date getCardRegDate();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getCardRegDate <em>CardRegDate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CardRegDate</em>' attribute.
	 * @see #getCardRegDate()
	 */
	public void setCardRegDate(Date cardRegDate);

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
	public Date getRegDueDate();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getRegDueDate <em>RegDueDate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegDueDate</em>' attribute.
	 * @see #getRegDueDate()
	 */
	public void setRegDueDate(Date regDueDate);

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
	public String getSaveOrg();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getSaveOrg <em>SaveOrg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SaveOrg</em>' attribute.
	 * @see #getSaveOrg()
	 */
	public void setSaveOrg(String saveOrg);

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
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getCreateTime <em>CreateTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CreateTime</em>' attribute.
	 * @see #getCreateTime()
	 */
	public void setCreateTime(Date createTime);

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
	public Date getUpdateTime();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getUpdateTime <em>UpdateTime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UpdateTime</em>' attribute.
	 * @see #getUpdateTime()
	 */
	public void setUpdateTime(Date updateTime);

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
	public String getRegisterCertiNo();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getRegisterCertiNo <em>RegisterCertiNo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegisterCertiNo</em>' attribute.
	 * @see #getRegisterCertiNo()
	 */
	public void setRegisterCertiNo(String registerCertiNo);

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
	public String getUserNum();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getUserNum <em>UserNum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UserNum</em>' attribute.
	 * @see #getUserNum()
	 */
	public void setUserNum(String userNum);

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
	public String getPartyId();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getPartyId <em>PartyId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PartyId</em>' attribute.
	 * @see #getPartyId()
	 */
	public void setPartyId(String partyId);

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
	public String getInOutNo();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getInOutNo <em>InOutNo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>InOutNo</em>' attribute.
	 * @see #getInOutNo()
	 */
	public void setInOutNo(String inOutNo);

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
	public String getOrgNum();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getOrgNum <em>OrgNum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>OrgNum</em>' attribute.
	 * @see #getOrgNum()
	 */
	public void setOrgNum(String orgNum);

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
	public String getMainSuretyKeyId();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getMainSuretyKeyId <em>MainSuretyKeyId</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MainSuretyKeyId</em>' attribute.
	 * @see #getMainSuretyKeyId()
	 */
	public void setMainSuretyKeyId(String mainSuretyKeyId);

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
	public String getRegEffecticeMode();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getRegEffecticeMode <em>RegEffecticeMode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RegEffecticeMode</em>' attribute.
	 * @see #getRegEffecticeMode()
	 */
	public void setRegEffecticeMode(String regEffecticeMode);

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
	public String getIfEfps();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getIfEfps <em>IfEfps</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IfEfps</em>' attribute.
	 * @see #getIfEfps()
	 */
	public void setIfEfps(String ifEfps);

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
	public String getAccOrgCode();

	/**
	 * Sets the value of the '{@link com.bos.dataset.grt.TbGrtRegCard#getAccOrgCode <em>AccOrgCode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AccOrgCode</em>' attribute.
	 * @see #getAccOrgCode()
	 */
	public void setAccOrgCode(String accOrgCode);


}