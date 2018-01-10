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

/**
 * 状态返回对象
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public class OrgResponse {
	
	//标识符，true:成功，false:失败
	private boolean flag;
	
	//提示信息
	private String message;
	
	public OrgResponse() {
		super();
	}
	
	public OrgResponse(String message) {
		super();
		this.message = message;
	}

	public OrgResponse(boolean flag) {
		super();
		this.flag = flag;
	}

	public OrgResponse(boolean flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
