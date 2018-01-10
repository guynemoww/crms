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
package com.bos.utp.dict.util;

/**
 * TODO 此处填写 class 信息
 * 
 * @author luonanqin (mailto:luonq@primeton.com)
 */
public interface IAuthConstants {

	/**
	 * 将功能适配resourceType为function字符串 Comment for
	 * <code>FUNCTION_TO_RESOURCE_TYPE</code>
	 */
	String FUNCTION_TO_RESOURCE_TYPE = "function";

	/**
	 * 0代表无权限，1代表有权限 Comment for <code>FUNCTION_TO_STATES</code>
	 */
	String[] FUNCTION_TO_STATES = new String[] {
			"0", "1"
	};

	String FUNCTION_ACTION = "function_action";

	String FUNCTION_PARAN_RESOURCE_ID = "__resourceId";

	String FUNCTION_PARAM_REAOURCE_TYPE = "__resourceType";
}