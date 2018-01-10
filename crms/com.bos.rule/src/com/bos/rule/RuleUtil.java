/**
 * 
 */
package com.bos.rule;

import java.util.List;
import java.util.Map;

import com.eos.system.annotation.Bizlet;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.service.RuleService;
import com.git.easyrule.util.EngineConstants;
import com.git.easyrule.util.RuleException;

/**
 * @author ljf
 * @date 2015-04-23 16:41:52
 * 
 */
@Bizlet("规则工具类")
public class RuleUtil {
	private final static RuleService rs = new RuleService();

	/**
	 * 
	 * @param ruleId
	 *            规则ID
	 * @param paramMap
	 *            规则执行所需参数
	 * @return
	 * @throws RuleException
	 */
	@Bizlet("执行单一规则")
	public static String runRule(String ruleId, Map<String, ? extends Object> paramMap) throws RuleException {
		return getMsg(rs.runRule(ruleId, paramMap));
	}

	/**
	 * 执行一组规则集合
	 * 
	 * @param modelId
	 *            模型ID
	 * @param paramMap
	 *            模型中各规则执行所需参数
	 * @return
	 * @throws RuleException
	 */
	@Bizlet("执行复合规则")
	public static String runModel(String modelId, Map<String, Object> paramMap) throws RuleException {
		// 返回的错误信息拼接字符串
		return getMsg(rs.runModel(modelId, paramMap));
	}

	/**
	 * 以相同参数执行多个规则
	 * 
	 * @param paramMap
	 * @param ruleIds
	 * @throws RuleException
	 */
	public static void runRules(Map<String, ? extends Object> paramMap, String... ruleIds) throws RuleException {
		for (String ruleId : ruleIds) {
			String msg = runRule(ruleId, paramMap);
			if (msg != null) {
				throw new RuntimeException(msg);
			}
		}
	}

	/**
	 * 获取所有规则结果
	 * 
	 * @param msgList
	 * @return
	 */
	public static String getMsg(List<MessageObj> msgList) {
		if (msgList == null || msgList.isEmpty()) {
			return null;
		}
		StringBuilder sf = new StringBuilder(msgList.size() * 50);
		for (MessageObj t : msgList) {
			sf.append("[" + t.getCode() + "]:" + t.getMessageInfo()).append("\n");
		}
		return sf.length() > 0 ? sf.toString() : null;
	}

	/**
	 * 获取错误规则结果
	 * 
	 * @param msgList
	 * @return
	 */
	public static String getErrorMsg(List<MessageObj> msgList) {
		if (msgList == null || msgList.isEmpty()) {
			return null;
		}
		StringBuilder sf = new StringBuilder(msgList.size() * 50);
		for (MessageObj t : msgList) {
			if (EngineConstants.RULE_LEVEL_ERROR.equals(t.getMessageType())) {
				sf.append("[" + t.getCode() + "]:" + t.getMessageInfo()).append("\n");
			}
		}
		return sf.length() > 0 ? sf.toString() : null;
	}
}
