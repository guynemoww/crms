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
package com.bos.utp.tools;

import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.logging.Logger;

/**
 * 日志工厂
 * 
 * @author wuyuhou
 * 
 */
public class LoggerFactory {

	private static final String CONTIRUBTION_NAME = "com.bos.utp.tools";

	/**
	 * 取得日志实例
	 * 
	 * @param clazz 类
	 * @return 日志实例
	 */
	public static Logger getLogger(Class clazz) {
		return TraceLoggerFactory.getContributionTraceLogger(CONTIRUBTION_NAME, clazz);
	}
}