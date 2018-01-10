/*
 * Copyright 2013 Primeton.
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
package com.bos.utp.rights.user;

import java.util.Date;

import com.bos.utp.dataset.privilege.AcOperator;
import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.spring.DASDaoSupport;

import org.apache.commons.lang.StringUtils;

import commonj.sdo.DataObject;

/**
 * 用户服务类
 * 
 * @author shitf (mailto:shitf@primeton.com)
 */
public class CapUserService extends DASDaoSupport implements ICapUserService {

	public void addCapUser(AcOperator capUser) {
		DefaultUserManager.INSTANCE.setUserAttribute(capUser);
		getDASTemplate().getPrimaryKey(capUser);
		getDASTemplate().insertEntity(capUser);
	}

	public void addCapUser(DataObject capUser) {
		DefaultUserManager.INSTANCE.setUserAttribute(capUser);
		getDASTemplate().getPrimaryKey(capUser);
		getDASTemplate().insertEntity(capUser);
	}

	public void deleteCapUser(AcOperator[] capUsers) {
		for (DataObject capUser : capUsers) {
			getDASTemplate().deleteEntityCascade(capUser);
			// CapPartyauth partyauth = CapPartyauth.FACTORY.create();
			// partyauth.setPartyId(capUser.getString("userId"));
			// partyauth.setPartyType(IConstants.USER_PARTY_TYPE_ID);
			// partyauth.setTenantId(TenantManager.getCurrentTenantID());
			// partyauthService.deleteCapPartyauthByTemplate(partyauth);
		}
	}

	public void getCapUser(AcOperator capUser) {
		getDASTemplate().expandEntity(capUser);
	}

	public AcOperator[] queryCapUsers(CriteriaType criteria, PageCond page) {
		criteria.set_entity(AcOperator.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(
				criteria);
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(
				AcOperator.class, dasCriteria, page);
	}

	public void updateCapUser(AcOperator capUser) {
		if (capUser.getStartdate() == null) {
			capUser.setStartdate(new Date());
		}
		getDASTemplate().updateEntity(capUser);
	}

	public void updateCapUser(DataObject capUser) {
		if (capUser.get("startdate") == null) {
			capUser.set("startdate", new Date());
		}
		getDASTemplate().updateEntity(capUser);
	}

	public int countCapUser(CriteriaType criteria) {
		criteria.set_entity(AcOperator.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(
				criteria);
		return getDASTemplate().count(dasCriteria);
	}

	public Boolean checkUser(String userId) {
		AcOperator capUser = AcOperator.FACTORY.create();
		capUser.setUserid(userId);
		AcOperator[] capUsers = getDASTemplate().queryEntitiesByTemplate(
				AcOperator.class, capUser);
		if (capUsers.length > 0)
			return true;
		return false;
	}

	public void getCapUserByTemplate(AcOperator template, AcOperator capUser) {
		getDASTemplate().expandEntityByTemplate(template, capUser);
	}

	public void deleteCapUserByTemplate(AcOperator capUser) {
		getDASTemplate().deleteByTemplate(capUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 */
	public AcOperator getCapUserByUserId(String userId) {
		if (!StringUtils.isBlank(userId)) {
			IDASCriteria dasCriteria = getDASTemplate().createCriteria(
					AcOperator.QNAME);
			dasCriteria.add(ExpressionHelper.eq("userid", userId));
			AcOperator[] users = getDASTemplate()
					.queryEntitiesByCriteriaEntity(AcOperator.class,
							dasCriteria);
			return users.length > 0 ? users[0] : null;
		}
		return null;
	}

	public boolean validateUserId(AcOperator user) {
		IDASCriteria dasCriteria = getDASTemplate().createCriteria(
				AcOperator.QNAME);
		dasCriteria.add(ExpressionHelper.eq("userid", user.getUserid()));
		AcOperator[] users = getDASTemplate().queryEntitiesByCriteriaEntity(
				AcOperator.class, dasCriteria);
		if (users == null || users.length == 0) {
			return true;
		}
		if (String.valueOf(user.getUserid()) != null
				&& !"".equals(String.valueOf(user.getUserid()))
				&& users[0].getOperatorid() == user.getOperatorid()) {// 修改的情况，只能为1个
			return users.length == 1;
		} else {// 新增,必定没有
			return users.length == 0;
		}
	}

	public void updatePasswords(AcOperator[] capUsers) {
		for (AcOperator capUser : capUsers) {
			String password = DefaultUserManager.INSTANCE
					.encodeString("000000");
			capUser.setPassword(password);
		}
		getDASTemplate().updateEntityBatch(capUsers);
	}

	public boolean authentication(String userId, String password) {
		AcOperator capUser = AcOperator.FACTORY.create();
		capUser.setUserid(userId);
		capUser.setPassword(password);
		AcOperator[] capUsers = getDASTemplate().queryEntitiesByTemplate(
				AcOperator.class, capUser);
		if (capUsers.length > 0)
			return true;
		return false;
	}

	public String encodePassword(String password) {
		return DefaultUserManager.INSTANCE.encodeString(password);
	}

}
