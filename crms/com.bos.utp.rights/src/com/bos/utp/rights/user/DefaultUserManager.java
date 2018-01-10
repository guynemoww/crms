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
package com.bos.utp.rights.user;

import java.util.Date;

import com.bos.utp.dataset.privilege.AcOperator;
import com.eos.system.utility.CryptoUtil;
import commonj.sdo.DataObject;

/**
 * 用户管理
 * 
 * @author shitf (mailto:shitf@primeton.com)
 */

public class DefaultUserManager {

	public final static DefaultUserManager INSTANCE = new DefaultUserManager();

	/**
	 * 设置用户属性
	 * 
	 * @param AcOperator
	 *            用户对象
	 * @return 用户对象
	 */
	public AcOperator setUserAttribute(AcOperator AcOperator) {
		try {
			// AcOperator.setPassword(encrypt(AcOperator.getPassword()));
			AcOperator.setLastlogin(new Date());
			AcOperator.setUnlocktime(new Date());
			if (AcOperator.getStartdate() == null) {
				AcOperator.setStartdate(new Date());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return AcOperator;
	}

	public DataObject setUserAttribute(DataObject AcOperator) {
		try {
			// AcOperator.setPassword(encrypt(AcOperator.getPassword()));
			AcOperator.set("lastlogin", new Date());
			AcOperator.set("unlocktime", new Date());
			if (AcOperator.get("startdate") == null) {
				AcOperator.set("startdate", new Date());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return AcOperator;
	}

	/**
	 * 把字符串加密
	 * 
	 * @param attr
	 * @return
	 */
	public String encodeString(String attr) {
		try {
			attr = encrypt(attr);
		} catch (Exception e) {
		}
		return attr;
	}

	private final static String ENCRYPT_KEY = "cap_user";

	// 加密
	private static String encrypt(String password) throws Exception {
		return CryptoUtil.encrypt(password, ENCRYPT_KEY);
	}

	// 解密
	private static String decrypt(String password) throws Exception {
		return CryptoUtil.decrypt(password, ENCRYPT_KEY);
	}
}
