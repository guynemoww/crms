package com.bos.utp;

import com.eos.infra.config.Configuration.Module;

/**
 * 
 * 配置文件中的模块
 * 
 * @author charles (mailto:caisy@primeton.com)
 */
/*
 * Modify history $Log: ABFModule.java,v $
 * Modify history Revision 1.1  2010/12/07 03:09:46  caisy
 * Modify history
 */
public enum ABFModule {
	/** 数据源配置模块 * */
	AUTH_DATASOURCE(ABFContribution.Auth, "DataSource"),
	/** ABFrame 配置模块* */
	AUTH_ABFRAME(ABFContribution.Auth, "abframe-config");
	
	private final ABFContribution contribution;

	private final String name;

	ABFModule(ABFContribution contribution, String name) {
		this.contribution = contribution;
		this.name = name;
	}

	@Override
	public String toString() {
		return contribution.toString() + ":" + name;
	}

	public ABFContribution getContribution() {
		return contribution;
	}

	public String getName() {
		return name;
	}

	/**
	 *获取模块配置信息
	 * @return
	 */
	public Module getModuleConfig() {
		return contribution.getConfig().getModule(name);
	}
}
