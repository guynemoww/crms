/*******************************************************************************
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on Apr 11, 2008
 *******************************************************************************/
package com.bos.utp.dataset.organization;

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
 * </ul>
 * </p>
 *
 * @extends DataObject;
 */
public interface OrgTreeNode extends DataObject {

	public final static String QNAME = "com.bos.utp.dataset.organization.OrgTreeNode";

	public final static Type TYPE = TypeHelper.INSTANCE.getType("com.bos.utp.dataset.organization", "OrgTreeNode");
	
	String NODE_ID = "nodeId";
	String NODE_TYPE = "nodeType";
	String NODE_NAME = "nodeName";
	String ICON_CLS = "iconCls";

	public final static IObjectFactory<OrgTreeNode> FACTORY = new IObjectFactory<OrgTreeNode>() {
		public OrgTreeNode create() {
			return (OrgTreeNode) DataFactory.INSTANCE.create(TYPE);
		}
	};

	 	
	public String getNodeId();
	
	public void setNodeId(String nodeId);
	
	public String getNodeType();
	
	public void setNodeType(String nodeType);
	
	public String getNodeName();
	
	public void setNodeName(String nodeName);
	
	public String getIconCls();
	
	public void setIconCls(String iconCls);


}