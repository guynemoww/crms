package com.bos.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eos.spring.BeanFactory;
import com.git.easyrule.data.set.DataSetManager;
import com.git.easyrule.formula.Formula;
import com.git.easyrule.models.Condition;
import com.git.easyrule.models.MessageObj;
import com.git.easyrule.models.Property;
import com.git.easyrule.models.Rule;
import com.git.easyrule.util.DomParseUtil;
import com.git.easyrule.util.EngineConstants;
import com.git.easyrule.util.EngineUtil;
import com.git.easyrule.util.RuleException;

public class RuleServieMgr {

	private static transient Logger log = LoggerFactory.getLogger(RuleServieMgr.class);
	private static Map<String, Property> propertyMap = DomParseUtil.getPropertyMap();
	private DataSetManager dataSetManager = null;
	private BeanFactory factory = null;

	public RuleServieMgr() {

		factory = BeanFactory.newInstance();
		dataSetManager = factory.getBean("dataSetManagerImpl");

	}

	/**
	 * 
	 * @Title: runRule
	 * @Description: 检查规则
	 * @param ruleId
	 *            规则ID
	 * @param paramMap
	 *            参数集合
	 * @return
	 * @throws RuleException
	 *             设定文件
	 * @return List<MessageObj> 返回类型
	 * @throws
	 * @author GIT-Sunny
	 * @date 2012-10-25 下午10:09:21
	 * @version V1.0
	 */
	public List<MessageObj> runRule(String ruleId, Map<String, Object> paramMap) throws RuleException {
		List<MessageObj> messageList = new ArrayList<MessageObj>();
		if ("true".equals(propertyMap.get(EngineConstants.ENGINE_PROPERTY_RUNSTATUS).getValue())) {
			Rule rule = DomParseUtil.getInstance().getRuleMap().get(ruleId);
			if (rule == null) {
				throw new RuleException("The rule[" + ruleId + "] is not found!");
			}
			String resultMsg = checkRule(rule, paramMap);
			if (!EngineConstants.RESULT_SUCCESS_MSG.equals(resultMsg) && !EngineConstants.RESULT_MISS_MSG.equals(resultMsg)) {
				messageList.add(genMessageObj(rule, resultMsg));
			}
		} else if ("false".equals(propertyMap.get(EngineConstants.ENGINE_PROPERTY_RUNSTATUS).getValue())) {
			log.debug(EngineConstants.ENGINE_NAME + "规则引擎未启动！");
		} else {
			throw new RuleException(EngineConstants.ENGINE_NAME + "规则引擎运行状态配置错误，请检查！");
		}
		return messageList;
	}

	/**
	 * 检查规则
	 * 
	 * @param rule
	 * @param paramMap
	 * @return
	 */
	private String checkRule(Rule rule, Map<String, Object> paramMap) {
		Map<String, Condition> conditions = rule.getConditions();
		if (conditions.size() > 1 && rule.getFormula() != null) {
			return EngineConstants.ENGINE_NAME + "The rule's conditions is configuration error,please check The Rule[" + rule.getId() + "-" + rule.getName() + "].";
		}
		log.debug(EngineConstants.ENGINE_NAME + "The rule's vars is :" + paramMap);
		boolean isPass = true; // 先决条件通过标志
		boolean msg = true; // 规则通过标志
		try {
			if (!conditions.isEmpty()) {
				Map<String, String> conditionRs = new HashMap<String, String>();
				for (String key : conditions.keySet()) {
					Condition condition = conditions.get(key);
					List<String> sqlList = condition.getSql();
					if (!sqlList.isEmpty()) {// 判断前置条件是否有SQL语句，如果有SQL语句，则先执行SQL语句
						for (String sql : sqlList) {
							log.debug(EngineConstants.ENGINE_NAME + "The sql's vars is :" + paramMap);
							for (String dataKey : paramMap.keySet()) {
								sql = EngineUtil.replaceParam(sql, EngineConstants.IDENTIFY_VAR + dataKey, "'" + paramMap.get(dataKey) + "'");
							}
							log.debug(EngineConstants.ENGINE_NAME + "The condition[" + condition.getId() + "] SQL is :" + sql);
							paramMap.putAll(dataSetManager.loadDataSetBySql(sql));
							log.debug(EngineConstants.ENGINE_NAME + "The condition's SQL is success![" + paramMap + "]");
						}
					}
					boolean flag = callFormulas(condition, paramMap);
					log.debug(EngineConstants.ENGINE_NAME + "The condition [" + key + "] is " + flag);
					if (rule.getFormula() == null) {
						if (!flag) {
							log.debug(EngineConstants.ENGINE_NAME + "The rule [" + rule.getId() + ":" + rule.getName() + "] is " + EngineConstants.RESULT_MISS_MSG);
							return EngineConstants.RESULT_MISS_MSG;
						}
					} else {
						conditionRs.put(key, String.valueOf(flag));
					}
				}
				if (!conditionRs.isEmpty()) {
					isPass = Boolean.parseBoolean(parseFormula(rule.getFormula(), conditionRs));
					// System.out.println("The rule ["+rule.getName()+"] conditions is "+isPass);
					log.debug(EngineConstants.ENGINE_NAME + "The rule [" + rule.getId() + ":" + rule.getName() + "] conditions is " + isPass);
				}
			}
			if (isPass) {// 先决条件检查通过
				List<String> sqlList = rule.getSql();
				if (!sqlList.isEmpty()) {// 判断规则是否有SQL语句，如果有SQL语句，则先执行SQL语句
					for (String sql : sqlList) {
						for (String dataKey : paramMap.keySet()) {
							sql = EngineUtil.replaceParam(sql, EngineConstants.IDENTIFY_VAR + dataKey, "'" + paramMap.get(dataKey) + "'");
						}
						log.debug(EngineConstants.ENGINE_NAME + "The rule[" + rule.getId() + "] SQL is :" + sql);
						log.debug(EngineConstants.ENGINE_NAME + "The dataSetManager is :" + dataSetManager);
						paramMap.putAll(dataSetManager.loadDataSetBySql(sql));
						log.debug(EngineConstants.ENGINE_NAME + "The rule's SQL is success![" + paramMap + "]");
					}
				}
				msg = callFormulas(rule, paramMap);// 公式返回值
			} else {
				log.info(EngineConstants.ENGINE_NAME + "The conditions:[" + rule.getConditions() + "] for rule:[" + rule.getId() + ":" + rule.getName() + "]  is " + EngineConstants.RESULT_MISS_MSG);
				return EngineConstants.RESULT_MISS_MSG;
			}

			if (!msg) {// 规则检查未通过
				log.info(EngineConstants.ENGINE_NAME + "break the rule:" + rule.getName());
				log.info(EngineConstants.ENGINE_NAME + "for paramMap:" + paramMap.toString());
				return replaceErrMsg(rule.getErrMsg(), paramMap);
			} else {
				log.info(EngineConstants.ENGINE_NAME + "The rule [" + rule.getName() + "] is " + EngineConstants.RESULT_SUCCESS_MSG);
				return EngineConstants.RESULT_SUCCESS_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(EngineConstants.ENGINE_NAME+"An error occurred while calling the horizontal rule. the rule name is " + rule.getName());
			// return EngineConstants.ENGINE_NAME+"An error occurred while calling the horizontal rule. the rule name is " + rule.getName();
			log.error(EngineConstants.ENGINE_NAME + "执行规则失败，规则编号[" + rule.getId() + "],规则名称[ " + rule.getName() + "]");
			return EngineConstants.ENGINE_NAME + "执行规则失败，规则编号[" + rule.getId() + "],规则名称[ " + rule.getName() + "]";
		}
	}

	/**
	 * 组装错误信息
	 * 
	 * @param rule
	 *            校验的规则
	 * @param errType
	 *            错误类型
	 * @return
	 */
	private MessageObj genMessageObj(Rule rule, String resultMsg) {
		MessageObj message = new MessageObj();
		message.setCode(rule.getId());// 规则ID
		message.setMessageType(rule.getCheckLevel());
		message.setMessageInfo(resultMsg);
		return message;
	}

	/**
	 * 调用公式
	 * 
	 * @param rule
	 *            规则
	 * @param record
	 *            记录
	 * @param paramMap
	 *            参数
	 * @return
	 */
	private boolean callFormulas(Condition condition, Map<String, Object> paramMap) {
		String[] fas = condition.getChecktype().split(EngineConstants.CHECKTYPE_SEPARATOR);

		for (int i = 0; i < fas.length; i++) {
			Formula fa = (Formula) factory.getBean(EngineConstants.CHECKTYPE_PREFIX + fas[i]);
			if (!fa.execute(condition, paramMap)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: parseFormula
	 * @Description: 计算先决条件公式
	 * @param formula
	 * @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * @author GIT-Sunny
	 * @date 2012-10-26 下午04:46:31
	 * @version V1.0
	 */
	private static String parseFormula(String formula, Map<String, String> condtionRs) {
		if (formula.indexOf("#") > -1) {
			String formula1 = formula.substring(0, formula.indexOf(")") + 1);
			String formula2 = formula1.substring(formula1.lastIndexOf("#"));
			formula = formula.replace(formula2, String.valueOf(computeFormula(formula2, condtionRs)));// 计算公式
			// System.out.println(formula);
			return parseFormula(formula, condtionRs);
		} else {
			return formula;
		}
	}

	/**
	 * 
	 * @Title: computeFormula
	 * @Description: 计算公式
	 * @param formula
	 * @param condtionRs
	 * @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @author GIT-Sunny
	 * @date 2012-10-26 下午06:01:33
	 * @version V1.0
	 */
	private static boolean computeFormula(String formula, Map<String, String> condtionRs) {
		String fun = formula.substring(1, formula.indexOf("("));
		String[] vars = formula.substring(formula.indexOf("(")).split(",");
		if (fun.equalsIgnoreCase("OR")) {
			for (int i = 0; i < vars.length; i++) {
				if (Boolean.parseBoolean(vars[i])) {
					return true;
				}
			}
		} else if (fun.equalsIgnoreCase("AND")) {
			for (int i = 0; i < vars.length; i++) {
				if (!Boolean.parseBoolean(vars[i])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 翻译错误信息中的参数
	 * 
	 * @param errMsg
	 * @param paramMap
	 * @return
	 */
	private String replaceErrMsg(String errMsg, Map<String, Object> paramMap) {
		Set<String> keySet = paramMap.keySet();
		log.debug("The error message is :" + errMsg);
		for (String key : keySet) {
			log.debug("The error message param is :" + key);
			errMsg = errMsg.replaceAll("(?i)\\[" + key + "\\]", "[" + String.valueOf(paramMap.get(key)) + "]");
		}
		errMsg = errMsg.replaceAll("[" + EngineConstants.IDENTIFY_VAR + "]", ""); // 替换变量符号
		errMsg = errMsg.replaceAll("[" + EngineConstants.IDENTIFY_FIELD + "]", ""); // 替换变量符号
		return errMsg;
	}

}
