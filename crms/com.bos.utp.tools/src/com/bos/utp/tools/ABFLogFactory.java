package com.bos.utp.tools;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.runtime.core.ApplicationContext;
import com.eos.system.logging.LogRepository;
import com.eos.system.logging.Logger;
import com.eos.system.utility.StringUtil;

public final class ABFLogFactory {
	// 日志仓库实例
	static public String CONTRIBUTION_ABFRAME_TOOLS = "com.bos.utp.tools";

	static public String MODULE_PROJECT = "abframe-config";

	static public String GROUP_LOG = "log-config";

	static public String LOG_CONFIG_DIR = "log_config_dir";

	static public String LOG_CONFIG_POSTFIX = "log_config_postfix"; // 日志配置文件的后缀

	static ABFLogFactory factory = new ABFLogFactory();

	static HashMap<String, LogRepository> Repositorys = new HashMap<String, LogRepository>();

	static String INITPATH = null;

	static String POSTFIX = null;

	private ABFLogFactory() {
		setConfigFromEOS();
	}

	private static boolean setConfigFromEOS() {
		boolean ret = true;
		try {
			String EOSHOME = ApplicationContext.getInstance().getEOS_HOME();
			if (EOSHOME != null && StringUtil.isNotNullAndBlank(EOSHOME)) {
				// String EOSWORKING =
				// ApplicationContext.getInstance().getApplicationWorkPath();
				INITPATH = ConfigurationUtil.getContributionConfig(CONTRIBUTION_ABFRAME_TOOLS, MODULE_PROJECT, GROUP_LOG, LOG_CONFIG_DIR);
				POSTFIX = ConfigurationUtil.getContributionConfig(CONTRIBUTION_ABFRAME_TOOLS, MODULE_PROJECT, GROUP_LOG, LOG_CONFIG_POSTFIX);
				if (INITPATH == null || StringUtil.isNullOrBlank(INITPATH)) {
					INITPATH = EOSHOME + "/prolog/conf";// "prolog/logs"
				} else if (!INITPATH.startsWith("/")) {
					INITPATH = EOSHOME + "/" + INITPATH;
				}
			}
		} catch (Exception e) {
			ret = false;
			// e.printStackTrace();
		}
		return ret;
	}

	public static ABFLogFactory getInstance() {
		if (factory == null) {
			factory = new ABFLogFactory();
		}
		return factory;
	}

	public void register(String filesLoacation, String postfix) {
		INITPATH = filesLoacation;
		POSTFIX = postfix;
		registerRepository();
	}
	public void register(){		
		setConfigFromEOS();		
		registerRepository();
	}
	private void registerRepository() {		
		unRegister();
		if (com.eos.system.utility.StringUtil.isNotNullAndBlank(INITPATH)) {
			try {
				File dir = new File(INITPATH);
				if (dir.isDirectory()) {
					File[] subfiles = dir.listFiles();
					if (subfiles != null) {
						for (File file : subfiles) {
							if (file != null && file.isFile()) {
								String name = file.getName();
								if (name.endsWith(POSTFIX)) {
									String repositoryName = name.substring(0, name.length() - POSTFIX.length());
									setRepository(repositoryName, LogRepository.create(file));
								}
							}
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void unRegister() {
		for (Iterator<LogRepository> iter = Repositorys.values().iterator(); iter.hasNext();) {
			LogRepository repository = iter.next();
			if (repository != null) {
				repository.shutdown();
			}
		}
	}

	public LogRepository getRepository(String repositoryName) {
		return Repositorys.get(repositoryName);
	}

	public void setRepository(String repositoryName, LogRepository logRepository) {
		if (logRepository.getLogConfigFile() == null) {
			Repositorys.put(repositoryName, logRepository);
			return;
		}
		LogRepository repository = Repositorys.get(repositoryName);
		if (repository != null) {
			repository.configure(logRepository.getLogConfigFile());
		} else {
			repository = LogRepository.create(logRepository.getLogConfigFile());
			Repositorys.put(repositoryName, repository);
		}
	}

	public Logger getLogger(String repositoryName, Class cls) {
		LogRepository repository = Repositorys.get(repositoryName);
		if (repository != null) {
			return repository.getLogger(cls);
		} else {
			return null;
		}
	}

	public Logger getLogger(String RepositoryName, String loggerName) {
		LogRepository repository = Repositorys.get(RepositoryName);
		if (repository != null) {
			return repository.getLogger(loggerName);
		} else {
			return null;
		}
	}
}
