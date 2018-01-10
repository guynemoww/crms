/**
 * 
 */
package com.bos.pub.lst;

import com.eos.system.annotation.Bizlet;

/**
 * @author chenchuan
 * @date 2016-07-28 11:31:12
 * 
 */
@Bizlet("名单制管理公共方法类")
public class LstInfo {
	@Bizlet("根据证件名称获取证件类型代码")
	public String getCertType(String certName) {
		if (null == certName || "".equals(certName)) {
			return null;
		}
		String certType[] = certName.split("-");//将证件类型名称用“-”拆分
		if (null==certType||certType.length != 2) {
			return null;
		}
		return certType[0];
	}
	@Bizlet("根据监控状态名称获取控状态代码")
	public String getListStatus(String listStatusName) {
		if (null == listStatusName || "".equals(listStatusName)) {
			return null;
		}
		String listStatus[] = listStatusName.split("-");//将监控状态名称用“-”拆分
		if (null==listStatus||listStatus.length != 2) {
			return null;
		}
		if(!("1,2,3").contains(listStatus[0])){
			return null;
		}
		return listStatus[0];
	}
}
