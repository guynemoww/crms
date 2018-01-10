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
package com.bos.utp.framework;

import com.bos.utp.framework.application.AcFuncresource;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;


/**
 * TODO 此处填写 class 信息
 *
 * @author fangwl (mailto:fangwl@primeton.com)
 */
public interface IAppFuncresourceService{

	/**
	 *
	 * @param appFuncresource AppFuncresource
	 */
	void addAppFuncresource(AcFuncresource appFuncresource);

	/**
	 *
	 * @param appFuncresources AppFuncresource[]
	 */
	void deleteAppFuncresource(AcFuncresource[] appFuncresources);

	/**
	 *
	 * @param appFuncresource AppFuncresource[]
	 */
	void getAppFuncresource(AcFuncresource appFuncresource);

	/**
	 *
	 * @param criteria CriteriaType
	 * @param page PageCond
	 * @return AppFuncresource[]
	 */
	AcFuncresource[] queryAppFuncresources(CriteriaType criteriaType,
			PageCond pageCond);

	/**
	 *
	 * @param appFuncresource AppFuncresource[]
	 */
	void updateAppFuncresource(AcFuncresource appFuncresource);

}
