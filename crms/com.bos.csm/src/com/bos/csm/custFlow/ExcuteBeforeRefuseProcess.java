package com.bos.csm.custFlow;

import java.util.ArrayList;
import java.util.Collection;

import com.bos.pub.GitUtil;
import com.bos.pub.exception.ParamEmptyException;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;
import commonj.sdo.DataObject;

@Bizlet("当流程撤销或者否决时，需要返回客户信息原来的数据")
public class ExcuteBeforeRefuseProcess {
	
	@Bizlet("当流程撤销或者否决时，需要返回客户信息原来的数据")
	public  static void excuteBeforeRefuseProcess(String partyId,String processId) {
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmModifyInfo");
		DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		DataObject[] dataObs;
		
		String partyTypeCd;
		try {
			dataOb.set("processId", processId);
			dataOb.set("partyId", partyId);
			dataObs = GitUtil.queryEntitiesByTemplate(dataOb);
			
			party.set("partyId",partyId);
			party = GitUtil.queryEntityByTemplate(party);
			partyTypeCd=party.getString("partyTypeCd");
			//公司客户
			if(partyTypeCd=="01"){
				recoveryCorp(dataObs);
			}
			
		} catch (ParamEmptyException e) {
			e.printStackTrace();
		}
		
	}

	private static void recoveryCorp(DataObject[] dataObs) {
		DataObject dataOb = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmModifyInfo");
		DataObject party = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmParty");
		DataObject corp = DataObjectUtil.createDataObject("com.bos.dataset.csm.TbCsmCorporation");
		Collection<String> partyInfo = new ArrayList<String>();
		String original=null;
		String value;
		Boolean isPartyChanged=false;
		Boolean isCorpChanged=false;
		for(int i=0;i<dataObs.length;i++){
			dataOb=dataObs[0];
			original = dataOb.getString("modifyColumn");
			value = dataOb.getString("originalModifyValue");
			if(original!=null){
				if(partyInfo.contains(original) ){
					party.set(original,value);
					isPartyChanged=true;
				}else{
					corp.set(original,value);
					isCorpChanged=true;
				}
			}
			
		}
		if(isPartyChanged){
			DatabaseUtil.updateEntity("default", party);
		}
		if(isCorpChanged){
			DatabaseUtil.updateEntity("default", corp);
		}
		
	}
	
	

}
