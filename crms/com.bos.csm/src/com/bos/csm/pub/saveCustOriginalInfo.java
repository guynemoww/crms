package com.bos.csm.pub;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.bos.pub.GitUtil;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

@Bizlet("保存被修改客户的原始数据")
public class saveCustOriginalInfo  {

	@Bizlet("保存被修改客户的原始数据")
	public String saveChangeCust(HashMap oldData, HashMap changeMap,String partyId,String processId) {
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmModifyInfo");
		Set<String> keySet =  changeMap.keySet();
		Integer maintainType = 3 ;
		Collection<String> imptInfo = new ArrayList<String>();//存储重要信息
		Iterator<String> itor =  keySet.iterator();
		//循环插入修改过的客户字段和原值
		while(itor.hasNext()){// 存在下一个值
			String key = itor.next();// 当前key值
			dataOb.set("modifyId", GitUtil.genUUIDString());
			if(key.equals("partyName")){
				dataOb.set("modifyEntity","com.bos.dataset.csm.TbCsmParty");
			}else if(key.equals("certificateCode")){
				dataOb.set("modifyEntity","com.bos.dataset.csm.TbCsmCertificateInfo");
			}else{
				dataOb.set("modifyEntity","com.bos.dataset.csm.TbCsmCorporation");
			}
			dataOb.set("modifyColumn",key);
			dataOb.set("newModifyValue",changeMap.get(key));
			dataOb.set("originalModifyValue",oldData.get(key));
			dataOb.set("partyId", partyId);
			dataOb.set("processId", processId);
			DatabaseUtil.insertEntity("default", dataOb);
		}
		if(keySet.containsAll(imptInfo)){
			//集合中含有重要信息的修改
			maintainType= 2;
		}
		return maintainType.toString();

	} 

	
}
