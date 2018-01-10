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
 * 机构常量定义
 * @author yangyong (mailto:yangyong@primeton.com)
 */
public interface IOrgConstants {
	
	/** 叶子节点 */
	String IS_LEAF_YES = "1";
	
	/** 不是叶子节点 */
	String IS_LEAF_NO = "2";
	
	/** 机构ID标识 */
	String ORGID_PROPERTY = "orgid";
	
	/** 机构名称标识 */
	String ORGNAME_PROPERTY = "orgname";
	
	/** 岗位ID标识 */
	String POSITIONID_PROPERTY = "positionid";
	
	/** 员工ID标识 */
	String EMPID_PROPERTY = "empid";
	
	/** 员工代码 */
	String EMPCODE_PROPERTY = "empcode";
	
	String EMP_EMAIL_PROPERTY = "oemail";

	/** 岗位关联标识 */
	String POSITION_REF_PROPERTY = "orgPosition";

	/** 员工关联标识 */
	String EMP_REF_PROPERTY = "omEmployee";

	/** 机构关联标识 */
	String ORG_REF_PROPERTY = "omOrganization";
	
	/** 主要的 */
	String IS_MAIN_YES = "1";
	
	/** 次要的 */
	String IS_MAIN_NO = "2";

	/** 操作员ID（用户ID） */
	String OPERATORID = "operatorid";
	
}
