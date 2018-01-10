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

import java.math.BigDecimal;
import java.util.Date;

import com.bos.pub.GitUtil;
import com.bos.utp.dataset.organization.OmPosition;
import commonj.sdo.DataObject;

/**
 * 默认机构辅助类
 * 
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public class OrgHelper {

	public static final String POSITION_TYPE = "organization";

	/**
	 * 根据父机构设置子机构部分信息：level, seq, isLeaf,subcount
	 * 
	 * @param org
	 * @param parentOrg
	 */
	public static void expandOrganizationPropertyByParent(DataObject org,
			DataObject parentOrg) {
		if (org == null)
			return;
		if (parentOrg == null || parentOrg.getBigDecimal("orgid") == null) {
//			org.set("orglevel", new BigDecimal(1));
			// 不存在父机构:"."+本机构ID+".";
			org.set("orgseq", "." + org.getBigDecimal("orgid") + ".");
		} else {
			// 级别在父机构的级别上增加1
//			org.set("orglevel", new BigDecimal(parentOrg.getBigDecimal(
//					"orglevel").intValue() + 1));
			// 存在父机构:父机构顺序+本机构ID+".";
			org.set("orgseq", parentOrg.get("orgseq")
					+ org.getBigDecimal("orgid").toPlainString() + ".");
		}
		org.set("createtime", GitUtil.getBusiTimestamp());
		org.set("lastupdate", GitUtil.getBusiTimestamp());
		org.set("isleaf", IOrgConstants.IS_LEAF_YES);
		org.set("subcount", new BigDecimal(0));
		// abFream中无此字段
		// org.setTenantid(TenantManager.getCurrentTenantID());
	}

	/**
	 * 更改父机构的信息，isLeaf,subcount
	 * 
	 * @param parentOrg
	 */
	public static void expandParentOrganizationProperty(DataObject parentOrg) {
		if (parentOrg == null || parentOrg.get("orgid") == null) {
			return;
		}
		if(parentOrg.get("subcount")!=null){
		parentOrg.set("subcount", new BigDecimal(parentOrg.get("subcount")
				.toString()).add(new BigDecimal(1)));
		}
		parentOrg.set("isleaf", IOrgConstants.IS_LEAF_NO);
	}

	public static void expandPositionPropertyByParent(OmPosition position,
			OmPosition parentPosition) {
		if (parentPosition == null
				|| String.valueOf(parentPosition.getPositionid()) == null) {
			position.setPosilevel(new BigDecimal(1));
			position.setPositionseq("." + position.getPositionid() + ".");
		} else {

			BigDecimal psolev = parentPosition.getPosilevel();
			BigDecimal count = new BigDecimal(1);
			psolev = psolev.add(count);
			position.setPosilevel(psolev);
			position.setPositionseq(parentPosition.getPositionseq()
					+ position.getPositionid() + ".");
		}
		// if(parentPosition != null && parentPosition.getOmOrganization() !=
		// null){
		// position.setOmOrganization(parentPosition.getOmOrganization());
		// }
		position.setCreatetime(new Date());
		position.setLastupdate(new Date());
		position.setPositype(POSITION_TYPE);
		position.setIsleaf(IOrgConstants.IS_LEAF_YES);
		position.setSubcount(new BigDecimal(0));
		// position.setTenantid(TenantManager.getCurrentTenantID());
	}

	public static void expandParentPositionProperty(OmPosition parentPosition) {
		if (parentPosition == null
				|| String.valueOf(parentPosition.getPositionid()) == null) {
			return;
		}
		parentPosition.setIsleaf(IOrgConstants.IS_LEAF_NO);
		BigDecimal psolevs = parentPosition.getPosilevel();
		BigDecimal counts = new BigDecimal(1);
		psolevs = psolevs.add(counts);
		parentPosition.setSubcount(psolevs);
	}

	public static void expandEmployeeProperty(DataObject emp) {
		emp.set("createtime", GitUtil.getBusiTimestamp());
		emp.set("lastmodytime", GitUtil.getBusiTimestamp());
		// emp.setTenantid(TenantManager.getCurrentTenantID());
	}

}
