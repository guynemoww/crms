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
import java.util.Arrays;
import java.util.List;

import com.bos.utp.dataset.organization.OmEmployee;
import com.bos.utp.dataset.organization.OmOrganization;
import com.bos.utp.dataset.organization.OmPosition;
import com.bos.utp.dataset.organization.OrgTreeNode;
import com.bos.utp.tools.IconCls;

import commonj.sdo.DataObject;

/**
 * 构造机构树的辅助类
 * 
 * @author yangzhou (mailto:yangzhou@primeton.com)
 */
public class OrgTreeNodeHelper {

	/**
	 * 将机构、岗位、员工结点构造为treeNodes
	 * 
	 * @param orgs
	 * @param positions
	 * @param emps
	 * @return
	 */
	public static DataObject[] buildOrgTreeNodes(OmOrganization[] orgs,
			OmPosition[] positions, OmEmployee[] emps) {
		List<DataObject> results = new ArrayList<DataObject>();

		results.addAll(Arrays.asList(OrgTreeNodeConvertor.convert(orgs)));
		results.addAll(Arrays.asList(OrgTreeNodeConvertor.convert(positions)));
		results.addAll(Arrays.asList(OrgTreeNodeConvertor.convert(emps)));

		return results.toArray(new DataObject[results.size()]);
	}

	public static DataObject[] buildOrgTreeNodes(DataObject[] orgs,
			DataObject[] positions, DataObject[] emps) {
		List<DataObject> results = new ArrayList<DataObject>();

		results.addAll(Arrays.asList(OrgTreeNodeConvertor.convert(orgs)));
		results.addAll(Arrays.asList(OrgTreeNodeConvertor
				.convertOmPosition(positions)));
		results.addAll(Arrays.asList(OrgTreeNodeConvertor
				.convertOmEmployee(emps)));

		return results.toArray(new DataObject[results.size()]);
	}

	/**
	 * @param orgPartyList
	 * @return
	 */
	public static DataObject[] buildOrgTreeNodes(OmOrganization[] orgPartyList) {
		List<DataObject> results = new ArrayList<DataObject>();

		for (OmOrganization orgParty : orgPartyList) {
			OrgTreeNode node = OrgTreeNode.FACTORY.create();
			node.setNodeId(orgParty.getString("orgid"));
			node.setNodeType("OrgOrganization");
			node.setNodeName(orgParty.getString("orgname"));
			node.setIconCls(IconCls.ORGANIZATION);
			node.set(IOrgConstants.ORGID_PROPERTY, String.valueOf(orgParty
					.getString("orgid")));
			node.set(IOrgConstants.ORGNAME_PROPERTY, String.valueOf(orgParty
					.getString("orgname")));
			node.set("isLeaf", false);
			node.set("expanded", false);
			node.set("orglevel", orgParty.getOrglevel());
			results.add(node);
		}
		return results.toArray(new DataObject[results.size()]);
	}

	public static DataObject[] buildOrgTreeNodes(DataObject[] orgPartyList) {
		List<DataObject> results = new ArrayList<DataObject>();

		for (DataObject orgParty : orgPartyList) {
			OrgTreeNode node = OrgTreeNode.FACTORY.create();
			node.setNodeId(orgParty.getString("orgid"));
			node.setNodeType("OrgOrganization");
			node.setNodeName(orgParty.getString("orgname"));
			node.setIconCls(IconCls.ORGANIZATION);
			node.set(IOrgConstants.ORGID_PROPERTY, String.valueOf(orgParty
					.getString("orgid")));
			node.set(IOrgConstants.ORGNAME_PROPERTY, String.valueOf(orgParty
					.getString("orgname")));
			node.set("isLeaf", false);
			node.set("expanded", false);
			node.set("orglevel", orgParty.getBigDecimal("orglevel"));
			node.set("orgcode", orgParty.getString("orgcode"));
			results.add(node);
		}
		return results.toArray(new DataObject[results.size()]);
	}
	
	public static DataObject[] buildOrgTreeNodesChecked(DataObject[] orgPartyList,String orgids) {
		List<DataObject> results = new ArrayList<DataObject>();
		
		String[] ids = orgids.split(",");
		for(DataObject orgParty : orgPartyList) {
			OrgTreeNode node = OrgTreeNode.FACTORY.create();
			node.setNodeId(orgParty.getString("orgid"));
			node.setNodeType("OrgOrganization");
			node.setNodeName(orgParty.getString("orgname"));
			node.setIconCls(IconCls.ORGANIZATION);
			node.set(IOrgConstants.ORGID_PROPERTY, String.valueOf(orgParty.getString("orgid")));
			node.set(IOrgConstants.ORGNAME_PROPERTY, String.valueOf(orgParty.getString("orgname")));
			node.set("isLeaf", false);
			node.set("expanded", false);
            node.set("orglevel", orgParty.get("orglevel"));
			for(int i=0; i<ids.length; i++){
				if(ids[i].equals(orgParty.get("orgid"))){
					node.set("checked", true);					
				}
			}
			
			
			results.add(node);
		}	
		return results.toArray(new DataObject[results.size()]);
	}
}
