package com.bos.utp;

import com.eos.common.config.ConfigurationFactory;
import com.eos.infra.config.Configuration;

/**
 * 
 * 构件包配置
 *
 * @author charles (mailto:caisy@primeton.com)
 */
/*
 * Modify history
 * $Log: ABFContribution.java,v $
 * Revision 1.1  2010/12/07 03:09:46  caisy
 *
 */
public enum ABFContribution {
	/** 认证 构件包配置  **/
	Auth("com.bos.utp.auth"),
	/** 机构管理 构件包配置  **/
	Org("com.bos.utp.org"), 
	/** 权限管理 构件包配置  **/
	Rights("com.bos.utp.rights"), 
	/** 工具 构件包配置  **/
	Tools("com.bos.utp.tools");
	private String name;

	ABFContribution(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * 返回构件包配置信息
	 * 
	 * @return 配置信息
	 */
	public Configuration getConfig() {
		return ConfigurationFactory.getContributionConfiguration(name);
	}

	@Override
	public String toString() {
		return name;
	}
}