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
package com.bos.utp.org;

import java.util.List;

import com.bos.utp.dataset.organization.OmEmployee;


/**
 * 人员授权服务接口
 * 
 * @author liuzn (mailto:liuzn@primeton.com)
 */
public interface IEmployeeAuthService {

	/**
	 * 人员数组转人员列表
	 * 
	 * @param orgEmployees
	 *            人员数组
	 * @return 人员列表
	 */
	List<OmEmployee> arrayToList(OmEmployee[] orgEmployees);

	/**
	 * 匹配人员的授权信息
	 * 
	 * @param orgEmployeeList
	 *            人员列表
	 * @param roleId
	 *            授权的角色
	 * @return 包含授权信息的人员列表
	 */
	List<OmEmployee> getEmployeeAuth(List<OmEmployee> orgEmployeeList,
			String roleId);

}
