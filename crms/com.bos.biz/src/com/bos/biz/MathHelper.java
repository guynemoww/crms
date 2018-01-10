package com.bos.biz;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eos.system.annotation.Bizlet;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.math.MathPowFunction;
import com.googlecode.aviator.runtime.type.AviatorFunction;
import com.googlecode.aviator.runtime.type.AviatorJavaType;

@Bizlet("MathHelper")
public class MathHelper {

	//private static Logger log = LoggerFactory.getLogger(TbSupIntrRateAdjustManager.class);
	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param v 需要四舍五入的数字
	 * @param scale 默认小数点后保留2位
	 * @return 四舍五入后的结果
	 */
	public static BigDecimal round(BigDecimal v1, String currCod) {
		if ("6".equals(currCod)) {
			BigDecimal v2 = new BigDecimal("1");
			BigDecimal v11 = v1.setScale(0, BigDecimal.ROUND_DOWN);
			return v11.divide(v2, 2, BigDecimal.ROUND_HALF_UP);
		} else {
			BigDecimal v2 = new BigDecimal("1");
			return v1.divide(v2, 2, BigDecimal.ROUND_HALF_UP);
		}
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static BigDecimal round(BigDecimal v1, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal v2 = new BigDecimal("1");
		return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/** 
	 * @Title: nullTOZero 
	 * @Description:参数为空转换为0 
	 * @param param 参数
	 * @return    设定文件 
	 * @return BigDecimal    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-23 上午10:41:51 
	 * @version V1.0
	 */
	public static BigDecimal nullTOZero(BigDecimal param) {
		if (param == null)
			return new BigDecimal("0.00");
		else
			return param;
	}

	/**
	 * 
	 * @PackageName:javacommon.util
	 * @Title: add 
	 * @Description: 提供精确的加法运算。
	 * @param iv	被加数
	 * @param vs	加数
	 * @return    设定文件 
	 * @return BigDecimal    返回类型 
	 * @throws
	 * @author GIT-Sunny
	 * @date 2015-5-15 上午09:53:51 
	 * @version V1.0
	 */
	public static BigDecimal add(BigDecimal iv, BigDecimal... vs) {
		if (iv == null) {
			iv = new BigDecimal("0.00");
		}
		for (BigDecimal v : vs) {
			if (v == null) {
				continue;
			}
			iv = iv.add(v);
		}
		return iv;
	}

	/**
	 * 
	 * @PackageName:javacommon.util
	 * @Title: sub 
	 * @Description: 提供精确的减法运算。
	 * @param iv	被减数
	 * @param vs	减数
	 * @return    设定文件 
	 * @return BigDecimal    返回类型 
	 * @throws
	 * @author GIT-Sunny
	 * @date 2015-5-15 上午09:54:38 
	 * @version V1.0
	 * @throws Exception 
	 */
	public static BigDecimal sub(BigDecimal iv, BigDecimal... vs)
			throws Exception {
		if (iv == null) {
			throw new Exception("被减数不能为空！");
		}
		for (BigDecimal v : vs) {
			if (v == null) {
				continue;
			}
			iv = iv.subtract(v);
		}
		return iv;
	}

	/** 
	 * @Title: multiply 
	 * @Description: 乘法运算 
	 * @param v1　被乘数
	 * @param v2　乘数
	 * @return    设定文件 
	 * @return BigDecimal    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-15 下午04:17:14 
	 * @version V1.0
	 */
	public static BigDecimal multiply(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(v2);
	}

	/**
	 * 
	 * @Title: expression 
	 * @Description: 根据公式计算结果 
	 * @param expStr 公式
	 * @param params 参数对象
	 * @return    设定文件 
	 * @return BigDecimal    返回类型 
	 * @throws 
	 * @author GIT-Sunny
	 * @date 2015-4-8 下午09:25:50 
	 * @version V1.0
	 */
	@Bizlet("")
	public static BigDecimal expressionBigDecimal(String expStr,
			Map<String, Object> params) {
		Expression compiledExp = AviatorEvaluator.compile(expStr);
		BigDecimal result = (BigDecimal) compiledExp.execute(params);
		return result;
	}

	public static BigDecimal powBigDecimal(BigDecimal params1, int params2) {
		AviatorFunction aviatorFunction = new MathPowFunction();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("a", params1);
		params.put("b", params2);
		BigDecimal result = (BigDecimal) aviatorFunction.call(params,
				new AviatorJavaType("a"), new AviatorJavaType("b")).getValue(
				null);
		return result;
	}

	/**根据公式返回字符串
	 * @param expStr
	 * @param params
	 * @return
	 */
	public static String expressionString(String expStr,
			Map<String, Object> params) {
		Expression compiledExp = AviatorEvaluator.compile(expStr);
		String result = String.valueOf(compiledExp.execute(params));
		return result;
	}

	/**返回表达式中的变量
	 * @param expStr
	 * @return
	 */
	public static List<String> getExpressionVariableNames(String expStr) {
		Expression compiledExp = AviatorEvaluator.compile(expStr);
		return compiledExp.getVariableNames();
	}

	/** 
	 * @Title: equationComapreToZero 
	 * @Description: 判断某个值是否等于0 
	 * @param param
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-15 下午04:09:43 
	 * @version V1.0
	 */
	public static boolean equationComapreToZero(BigDecimal param) {
		if (param == null) {
			param = new BigDecimal("0.00");
		}
		BigDecimal zero = new BigDecimal("0.00");
		if (param.compareTo(zero) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * @Title: littleComapreToZero 
	 * @Description:判断某个值是否小于0 
	 * @param param
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-15 下午04:10:19 
	 * @version V1.0
	 */
	public static boolean littleComapreToZero(BigDecimal param) {
		if (param == null) {
			param = new BigDecimal("0.00");
		}
		BigDecimal zero = new BigDecimal("0.00");
		if (param.compareTo(zero) == -1) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * @Title: greaterCompareToZero 
	 * @Description:判断某个值大于0 
	 * @param param
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-15 下午04:10:43 
	 * @version V1.0
	 */
	public static boolean greaterCompareToZero(BigDecimal param) {
		if (param == null) {
			param = new BigDecimal("0.00");
		}
		BigDecimal zero = new BigDecimal("0.00");
		if (param.compareTo(zero) == 1) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * @Title: greaterEquationCompareToZero 
	 * @Description: 判断某个值大于等于0 
	 * @param param
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-15 下午04:11:05 
	 * @version V1.0
	 */
	public static boolean greaterEquationCompareToZero(BigDecimal param) {
		if (param == null) {
			param = new BigDecimal("0.00");
		}
		BigDecimal zero = new BigDecimal("0.00");
		if (param.compareTo(zero) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * @Title: littleEquationComapreToZero 
	 * @Description:判断某个值小于等于0 
	 * @param param
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-15 下午04:11:36 
	 * @version V1.0
	 */
	public static boolean littleEquationComapreToZero(BigDecimal param) {
		if (param == null) {
			param = new BigDecimal("0.00");
		}
		BigDecimal zero = new BigDecimal("0.00");
		if (param.compareTo(zero) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * @Title: equationCompareToValue 
	 * @Description:判断参数1是否等于参数2 
	 * @param param1 参数1
	 * @param param2 参数2
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-20 上午09:03:23 
	 * @version V1.0
	 */
	public static boolean equationCompareToValue(BigDecimal param1,
			BigDecimal param2) {
		if (param1 == null) {
			param1 = new BigDecimal("0.00");
		}
		if (param2 == null) {
			param2 = new BigDecimal("0.00");
		}
		if (param1.compareTo(param2) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * @Title: greaterCompareToValue 
	 * @Description:判断参数1是否大于参数2 
	 * @param param1 参数1
	 * @param param2 参数2
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-20 上午09:05:37 
	 * @version V1.0
	 */
	public static boolean greaterCompareToValue(BigDecimal param1,
			BigDecimal param2) {
		if (param1 == null) {
			param1 = new BigDecimal("0.00");
		}
		if (param2 == null) {
			param2 = new BigDecimal("0.00");
		}
		if (param1.compareTo(param2) == 1)
			return true;
		else
			return false;
	}

	/** 
	 * @Title: littleCompareToValue 
	 * @Description: 判断参数1是否小于参数2
	 * @param param1 参数1
	 * @param param2 参数2
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-4-20 上午09:06:33 
	 * @version V1.0
	 */
	public static boolean littleCompareToValue(BigDecimal param1,
			BigDecimal param2) {
		if (param1 == null) {
			param1 = new BigDecimal("0.00");
		}
		if (param2 == null) {
			param2 = new BigDecimal("0.00");
		}
		if (param1.compareTo(param2) == -1)
			return true;
		else
			return false;
	}

	/** 
	 * @Title: sub 
	 * @Description: 两个Integer相减 
	 * @param v1 被减数
	 * @param v2 减数
	 * @return    设定文件 
	 * @return Integer    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-5-11 上午10:13:28 
	 * @version V1.0
	 */
	public static Integer sub(Integer v1, Integer v2) {
		return v1 - v2;
	}

	/** 
	 * @Title: add 
	 * @Description:两个Integer相加 
	 * @param v1 被加数
	 * @param v2 加数
	 * @return    设定文件 
	 * @return Integer    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-5-11 上午10:14:11 
	 * @version V1.0
	 */
	public static Integer add(Integer v1, Integer v2) {
		return v1 + v2;
	}

	/** 
	 * @Title: equationCompareToZero 
	 * @Description: 判断一个 Integer值等于0
	 * @param v1 参数
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-5-11 上午10:16:37 
	 * @version V1.0
	 */
	public static boolean equationCompareToZero(Integer v1) {
		if (v1 == 0)
			return true;
		else
			return false;
	}

	/** 
	 * @Title: greaterCompareToZero 
	 * @Description:判断一个 Integer值大于0
	 * @param v1
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-5-11 上午10:18:10 
	 * @version V1.0
	 */
	public static boolean greaterCompareToZero(Integer v1) {
		if (v1 > 0)
			return true;
		else
			return false;
	}

	/** 
	 * @Title: littleCompareToZero 
	 * @Description: 判断一个 Integer值小于0
	 * @param v1 参数
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-5-11 上午10:30:41 
	 * @version V1.0
	 */
	public static boolean littleCompareToZero(Integer v1) {
		if (v1 < 0)
			return true;
		else
			return false;
	}

	/** 
	 * @Title: littleEquationCompareToZero 
	 * @Description: 判断一个  Integer值小于或者等于0
	 * @param v1
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-5-11 下午03:34:17 
	 * @version V1.0
	 */
	public static boolean littleEquationCompareToZero(Integer v1) {
		if (v1 <= 0)
			return true;
		else
			return false;
	}

	/** 
	 * @Title: graterCompareToValue 
	 * @Description:v1>v2,返回true;否则,返回false 
	 * @param v1
	 * @param v2
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-5-11 下午03:37:47 
	 * @version V1.0
	 */
	public static boolean graterCompareToValue(Integer v1, Integer v2) {
		if (v1 > v2)
			return true;
		else
			return false;
	}

	/** 
	 * @Title: graterEquationCompareToValue 
	 * @Description: v1>=v2 
	 * @param v1
	 * @param v2
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-huhaijun
	 * @date 2015-5-16 下午10:57:13 
	 * @version V1.0
	 */
	public static boolean graterEquationCompareToValue(Integer v1, Integer v2) {
		if (v1 >= v2)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @Title: isInteger 
	 * @Description: 判断BigDecimal类型是否有小数部分
	 * @param param
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author GIT-gh
	 * @date 2015-6-9 下午03:07:35 
	 * @version V1.0
	 */
	public static boolean isInteger(BigDecimal param) {
		BigDecimal paramForLong = new BigDecimal(param.longValue());
		if (param.compareTo(paramForLong) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("a", new BigDecimal("100.12"));
		params.put("b", new BigDecimal("200.33"));
		params.put("c", new BigDecimal("300.11"));
		params.put("d", new BigDecimal("700.22"));
		System.out.println(expressionString("a+b*c*d", params));*/
		BigDecimal v2 = new BigDecimal("100.001");
		BigDecimal v3 = new BigDecimal("15.90");
		BigDecimal v4 = new BigDecimal("15000");
		BigDecimal temp = v2.multiply(v3).divide(v4, BigDecimal.ROUND_HALF_UP);
		System.out.println(temp + "======================");

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("v2", new BigDecimal("100.001"));
		params.put("v3", new BigDecimal("15.90"));
		params.put("v4", new BigDecimal("15000"));
		BigDecimal sum_amt = MathHelper
				.expressionBigDecimal("v2*v3/v4", params);
		System.out.println("sum_amt================" + sum_amt);

		//System.out.println(divide(new BigDecimal("1.00"),new BigDecimal("3.00")));
		//System.out.println(firstMulSubDiv(new BigDecimal("90.00"),new BigDecimal("10.00"),new BigDecimal("5.00"),new BigDecimal("2.00")));

	}

}
