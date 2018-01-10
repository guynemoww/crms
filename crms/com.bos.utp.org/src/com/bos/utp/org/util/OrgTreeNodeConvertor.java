/*
 * Copyright 2013 Primeton Technologies Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bos.utp.org.util;

import java.util.ArrayList;
import java.util.List;

import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.dataset.organization.OrgTreeNode;
import com.bos.utp.dataset.organization.QueryEmpForOrgEntity;
import com.bos.utp.dataset.organization.QueryEmplyoeeEntity;
import com.bos.utp.tools.IconCls;
import com.eos.foundation.data.DataObjectUtil;
import commonj.sdo.DataObject;

/**
 * 树节点转换器，将机构，岗位，员工转换成OrgTreeNode对象
 * 
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public class OrgTreeNodeConvertor {

	public static final String NODE_ID = "nodeId";

	public static final String NODE_TYPE = "nodeType";

	public static final String NODE_NAME = "nodeName";

	public static final String ICON_CLS = "iconCls";

	public static OrgTreeNode[] convert(OmOrganization[] orgs) {
		if (orgs == null || orgs.length == 0)
			return new OrgTreeNode[0];
		OrgTreeNode[] nodes = new OrgTreeNode[orgs.length];
		for (int i = 0; i < orgs.length; i++) {
			nodes[i] = convert(orgs[i]);
		}
		return nodes;
	}

	public static OrgTreeNode[] convert(DataObject[] orgs) {
		if (orgs == null || orgs.length == 0)
			return new OrgTreeNode[0];
		OrgTreeNode[] nodes = new OrgTreeNode[orgs.length];
		for (int i = 0; i < orgs.length; i++) {
			nodes[i] = convert(orgs[i]);
		}
		return nodes;
	}

	public static OrgTreeNode[] convert(OmPosition[] positions) {
		if (positions == null || positions.length == 0)
			return new OrgTreeNode[0];
		OrgTreeNode[] nodes = new OrgTreeNode[positions.length];
		for (int i = 0; i < positions.length; i++) {
			nodes[i] = convert(positions[i]);
		}
		return nodes;
	}

	public static OrgTreeNode[] convertOmPosition(DataObject[] positions) {
		if (positions == null || positions.length == 0)
			return new OrgTreeNode[0];
		OrgTreeNode[] nodes = new OrgTreeNode[positions.length];
		for (int i = 0; i < positions.length; i++) {
			nodes[i] = convertOmPosition(positions[i]);
		}
		return nodes;
	}

	public static OrgTreeNode[] convert(OmEmployee[] emps) {
		if (emps == null || emps.length == 0)
			return new OrgTreeNode[0];
		OrgTreeNode[] nodes = new OrgTreeNode[emps.length];
		for (int i = 0; i < emps.length; i++) {
			nodes[i] = convert(emps[i]);
		}
		return nodes;
	}

	public static OrgTreeNode[] convertOmEmployee(DataObject[] emps) {
		if (emps == null || emps.length == 0)
			return new OrgTreeNode[0];
		OrgTreeNode[] nodes = new OrgTreeNode[emps.length];
		for (int i = 0; i < emps.length; i++) {
			nodes[i] = convertOmEmployee(emps[i]);
		}
		return nodes;
	}

	public static OrgTreeNode[] convert(QueryEmplyoeeEntity[] emps) {
		if (emps == null || emps.length == 0)
			return new OrgTreeNode[0];
		OrgTreeNode[] nodes = new OrgTreeNode[emps.length];
		for (int i = 0; i < emps.length; i++) {
			nodes[i] = convert(emps[i]);
		}
		return nodes;
	}

	public static OrgTreeNode convert(DataObject org) {
		OrgTreeNode node = (OrgTreeNode) DataObjectUtil.convertDataObject(org,
				OrgTreeNode.QNAME, true);
		node.setNodeId(org.getString("orgid"));
		node.setNodeType("OrgOrganization");
		node.setNodeName(org.getString("orgname"));
		node.setIconCls(IconCls.ORGANIZATION);
		return node;
	}

	public static OrgTreeNode convert(OmOrganization org) {
		OrgTreeNode node = (OrgTreeNode) DataObjectUtil.convertDataObject(org,
				OrgTreeNode.QNAME, true);
		node.setNodeId(String.valueOf(org.getOrgid()));
		node.setNodeType("OrgOrganization");
		node.setNodeName(org.getOrgname());
		node.setIconCls(IconCls.ORGANIZATION);
		return node;
	}

	public static OrgTreeNode convert(OmPosition position) {
		OrgTreeNode node = (OrgTreeNode) DataObjectUtil.convertDataObject(
				position, OrgTreeNode.QNAME, true);
		node.setNodeId(String.valueOf(position.getPositionid()));
		node.setNodeType(OrgPositionNodeType);
		node.setNodeName(position.getPosiname());
		node.setIconCls(IconCls.POSITION);
		return node;
	}

	public static OrgTreeNode convertOmPosition(DataObject position) {
		OrgTreeNode node = (OrgTreeNode) DataObjectUtil.convertDataObject(
				position, OrgTreeNode.QNAME, true);
		node.setNodeId(String.valueOf(position.getInt("positionid")));
		node.setNodeType(OrgPositionNodeType);
		node.setNodeName(position.getString("posiname"));
		node.setIconCls(IconCls.POSITION);
		return node;
	}

	private static final String OrgPositionNodeType = "OrgPosition";

	private static final String OmEmployeeNodeType = "OrgEmployee";

	public static OrgTreeNode convert(OmEmployee emp) {
		OrgTreeNode node = (OrgTreeNode) DataObjectUtil.convertDataObject(emp,
				OrgTreeNode.QNAME, true);
		node.setNodeId(String.valueOf(emp.getEmpid()));
		node.setNodeType(OmEmployeeNodeType);
		node.setNodeName(emp.getEmpname());
		node.setIconCls(IconCls.EMPLOYEE);
		return node;
	}

	public static OrgTreeNode convertOmEmployee(DataObject emp) {
		OrgTreeNode node = (OrgTreeNode) DataObjectUtil.convertDataObject(emp,
				OrgTreeNode.QNAME, true);
		node.setNodeId(String.valueOf(emp.getBigDecimal("empid")));
		node.setNodeType(OmEmployeeNodeType);
		node.setNodeName(emp.getString("empname"));
		node.setIconCls(IconCls.EMPLOYEE);
		return node;
	}

	public static OrgTreeNode convert(QueryEmpForOrgEntity emp) {
		OrgTreeNode node = (OrgTreeNode) DataObjectUtil.convertDataObject(emp,
				OrgTreeNode.QNAME, true);
		node.setNodeId(String.valueOf(emp.getEmpid()));
		node.setNodeType(OmEmployeeNodeType);
		node.setNodeName(emp.getEmpname());
		node.setIconCls(IconCls.EMPLOYEE);
		return node;
	}

	public static OrgTreeNode[] convertOrgs(OmOrganization[] orgs) {
		if (orgs == null || orgs.length == 0)
			return new OrgTreeNode[0];
		List<OrgTreeNode> nodelist = new ArrayList<OrgTreeNode>();
		for (int i = 0; i < orgs.length; i++) {
			OrgTreeNode node = OrgTreeNode.FACTORY.create();
			node.setNodeId(String.valueOf(orgs[i].getOrgid()));
			node.setNodeName(orgs[i].getOrgname());
			node.setIconCls(IconCls.ORGANIZATION);
			node.set("parentId", orgs[i].getOmOrganization() == null ? null
					: orgs[i].getOmOrganization().getOrgid());
			nodelist.add(node);
		}
		return nodelist.toArray(new OrgTreeNode[] {});
	}

	// public static OrgTreeNode[] convertEmps(QueryEmpOperator[] orgs){
	// if(orgs == null || orgs.length == 0) return new OrgTreeNode[0];
	// List<OrgTreeNode> nodelist =new ArrayList<OrgTreeNode>();
	// for(int i=0;i<orgs.length;i++){
	// OrgTreeNode node = OrgTreeNode.FACTORY.create();
	// node.setNodeId(orgs[i].getString("empid"));
	// node.setNodeType(OmEmployee.NODE_TYPE);
	// node.setNodeName(orgs[1].getString("empname"));
	// node.setIconCls(IconCls.EMPLOYEE);
	// nodelist.add(node);
	// }
	// return nodelist.toArray(new OrgTreeNode[]{});
	// }

	public static OrgTreeNode[] convertEmps(QueryEmpForOrgEntity[] emps) {
		if (emps == null || emps.length == 0)
			return new OrgTreeNode[0];
		OrgTreeNode[] nodes = new OrgTreeNode[emps.length];
		for (int i = 0; i < emps.length; i++) {
			OrgTreeNode node = (OrgTreeNode) DataObjectUtil.convertDataObject(
					emps[i], OrgTreeNode.QNAME, true);
			node.setNodeId(String.valueOf(emps[i].getEmpid()));
			node.setNodeType(OmEmployeeNodeType);
			node.setNodeName(emps[i].getEmpname());
			node.setIconCls(IconCls.EMPLOYEE);
			nodes[i] = node;
		}
		return nodes;
	}

	public static OrgTreeNode[] convertEmps2(OmEmployee[] emps) {
		if (emps == null || emps.length == 0)
			return new OrgTreeNode[0];
		OrgTreeNode[] nodes = new OrgTreeNode[emps.length];
		for (int i = 0; i < emps.length; i++) {
			OrgTreeNode node = (OrgTreeNode) DataObjectUtil.convertDataObject(
					emps[i], OrgTreeNode.QNAME, true);
			node.setNodeId(String.valueOf(emps[i].getEmpid()));
			node.setNodeType(OmEmployeeNodeType);
			node.setNodeName(emps[i].getEmpname());
			node.setIconCls(IconCls.EMPLOYEE);
			nodes[i] = node;
		}
		return nodes;
	}

}
