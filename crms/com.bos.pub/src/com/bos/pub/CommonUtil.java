package com.bos.pub;

import java.util.HashMap;

import org.apache.commons.lang.ArrayUtils;

import com.eos.data.datacontext.UserObject;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.eoscommon.LogUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.bfs.tp.common.exception.EOSException;
import commonj.sdo.DataObject;

/**
 * @author 王世春
 * @date 2014-05-12 10:20:22
 * 
 */
@Bizlet("常用方法")
public class CommonUtil {

	@Bizlet("数组相加：通过浅拷贝克隆")
	public static boolean[] addAll(boolean[] array1, boolean[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	@Bizlet("数组相加：通过浅拷贝克隆")
	public static short[] addAll(short[] array1, short[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	@Bizlet("数组相加：通过浅拷贝克隆")
	public static Object[] addAll(Object[] array1, Object[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	@Bizlet("数组相加：通过浅拷贝克隆")
	public static long[] addAll(long[] array1, long[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	@Bizlet("数组相加：通过浅拷贝克隆")
	public static int[] addAll(int[] array1, int[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	@Bizlet("数组相加：通过浅拷贝克隆")
	public static float[] addAll(float[] array1, float[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	@Bizlet("数组相加：通过浅拷贝克隆")
	public static double[] addAll(double[] array1, double[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	@Bizlet("数组相加：通过浅拷贝克隆")
	public static char[] addAll(char[] array1, char[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	@Bizlet("数组相加：通过浅拷贝克隆")
	public static byte[] addAll(byte[] array1, byte[] array2) {
		return ArrayUtils.addAll(array1, array2);
	}

	@Bizlet("获取配置表值")
	public static String getDBConfigVal(String cfgGroup, String cfgKey) {
		String cfgValue = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("cfgGroup", cfgGroup);
		hm.put("cfgKey", cfgKey);
		Object[] confs = DatabaseExt.queryByNamedSql(GitUtil.DEFAULT_DS_NAME, "com.bos.pub.common.queryPubConfig", hm);
		if (confs.length == 1) {
			hm = (HashMap<String, String>) confs[0];
			cfgValue = hm.get("CFG_VALUE");
			LogUtil.logInfo(">>【" + cfgGroup + "】配置参数表【" + cfgKey + "】值为【" + cfgValue + "】！", null);
		}
		if (confs.length == 0 || cfgValue == null) {
			throw new EOSException("获取配置表值失败！【" + cfgGroup + "】【" + cfgKey + "】");
		}
		return cfgValue;
	}

	/**
	 * 判断当前登录的用户是否有某些岗位，角色之间以逗号区分
	 * 
	 * @param roles
	 * @return
	 */
	@Bizlet("判断当前登录的用户是否有某些角色，角色之间以逗号区分，有返回1，无返回0")
	public String getSessionByRoles(String positions) {
		String posiString = "";
		HashMap<String, HashMap> posiMaps = new HashMap<String, HashMap>();
		posiMaps = (HashMap) ((UserObject) GitUtil.getSession().getAttribute("userObject")).getAttributes().get("orgposimap");
		if (null == posiMaps || posiMaps.size() == 0) {
			return "0";
		}
		for(String str:posiMaps.keySet()){
			HashMap<String, String> posiMap=new HashMap<String, String>();
			posiMap=posiMaps.get(str);
			for(String str1:posiMap.keySet()){
				posiString+=str1+",";
			}
		}
		if(posiString.contains(positions)){
			return "1";
		}else{
			return "0";
		}
	}
}