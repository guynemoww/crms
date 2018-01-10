/**   
* @Title: InitDicServlet.java 
* @Package javacommon.servlet 
* @Description: TODO(用一句话描述该文件做什么) 
* @author GIT-Sunny
* @date 2012-9-26 下午02:29:56 
* @version V1.0   
*/
package com.bos.rule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bos.utp.tools.SystemInfo;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.runtime.resource.IContributionEvent;
import com.eos.runtime.resource.IContributionListener;
import com.git.easyrule.util.DomParseUtil;
import com.git.easyrule.util.EngineConstants;

/** 
 * @ClassName: InitDicServlet 
 * @Description: 初始化规则引擎
 * @author GIT-Sunny
 * @date 2012-9-26 下午02:29:56 
 *  
 */
public class InitRulesServlet implements IContributionListener {
	private static final Logger log = LoggerFactory.getLogger(InitRulesServlet.class);
	public InitRulesServlet() {
		super();
	}
	public void load(IContributionEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
	public void loadFinished(IContributionEvent arg0) {
		log.info(EngineConstants.ENGINE_NAME+"Begin instance the rule engine...");
		
		//获取配置文件路径
		String px_url = ConfigurationUtil.getContributionConfig("com.bos.rule","Rule","Paths","url");
		String path =SystemInfo.APP_WAR_PATH+px_url;
		//设置加载路径
		DomParseUtil.setResourPath(path);
		//加载。。。
		DomParseUtil.getInstance();
		log.info(EngineConstants.ENGINE_NAME+"End instance the rule engine!");
		
	}
	public void unLoad(IContributionEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	
}
