/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization.impl;

import com.bos.utp.dataset.organization.OrgTreeNode;
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
 * </ul>
 * </p>
 *
 * @extends ExtendedDataObjectImpl;
 *
 * @implements OrgTreeNode;
 */

public class OrgTreeNodeImpl extends ExtendedDataObjectImpl implements OrgTreeNode {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	public final static int SDO_PROPERTY_COUNT = 0;

	public final static int EXTENDED_PROPERTY_COUNT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OrgTreeNodeImpl() {
		this(TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public OrgTreeNodeImpl(Type type) {
		super(type);
	}

	protected void validate() {
		validateType(TYPE);
	}

	public String getIconCls() {
		return DataUtil.toString(super.get(ICON_CLS));
	}

	public String getNodeId() {
		return DataUtil.toString(super.get(NODE_ID));
	}

	public String getNodeName() {
		return DataUtil.toString(super.get(NODE_NAME));
	}

	public String getNodeType() {
		return DataUtil.toString(super.get(NODE_TYPE));
	}

	public void setIconCls(String iconCls) {
		super.set(ICON_CLS, iconCls);
	}

	public void setNodeId(String nodeId) {
		super.set(NODE_ID, nodeId);		
	}

	public void setNodeName(String nodeName) {
		super.set(NODE_NAME, nodeName);		
	}

	public void setNodeType(String nodeType) {
		super.set(NODE_TYPE, nodeType);		
	}


}