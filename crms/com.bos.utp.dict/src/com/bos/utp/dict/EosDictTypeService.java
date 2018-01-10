package com.bos.utp.dict;

import com.bos.utp.dict.dataset.EosDictEntry;
import com.bos.utp.dict.dataset.EosDictType;
import com.bos.utp.dict.util.DictResponse;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.foundation.eoscommon.BusinessDictUtil;
import com.eos.spring.DASDaoSupport;
import commonj.sdo.DataObject;

public class EosDictTypeService extends DASDaoSupport implements IEosDictTypeService{
	public void addEosDictType(DataObject eosDictType){
//		getDASTemplate().getPrimaryKey(eosDictType);
		if(eosDictType.get("parentid") == null){
			eosDictType.set("rank", 1);
			eosDictType.set("seqno", "." + eosDictType.get("dicttypeid") + ".");						
		}else{
			eosDictType.setInt("rank", eosDictType.getInt("rank") + 1);
			eosDictType.set("seqno", eosDictType.getString("seqno") + eosDictType.get("dicttypeid") + ".");
		}
		getDASTemplate().insertEntity(eosDictType);
	}

	public void deleteEosDictType(DataObject[] eosDictTypes ){
		for(DataObject eosDictType:eosDictTypes){
			getDASTemplate().deleteEntityCascade(eosDictType);
		}
	}
	
	public void deleteEosDictType(DataObject eosDictType, DataObject criteriaType) {
		String dicttypeid = eosDictType.get("dicttypeid").toString();
		criteriaType.set("_entity", EosDictEntry.QNAME);
		criteriaType.set("_expr[1]/eosDictType.dicttypeid", dicttypeid);
		criteriaType.set("_expr[1]/_op", "=");
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria((CriteriaType)criteriaType);
		EosDictEntry[] entry = getDASTemplate().queryEntitiesByCriteriaEntity(EosDictEntry.class, dasCriteria);
		for(DataObject eosEntryType:entry){
			getDASTemplate().deleteEntityCascade(eosEntryType);
		}
		getDASTemplate().deleteEntityCascade(eosDictType);
	}

	public void getEosDictType(DataObject eosDictType){
		getDASTemplate().expandEntity(eosDictType);
	}
	
	public int countTDictTypes(CriteriaType criteria) {
		criteria.set_entity(EosDictType.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
		
	}


	public EosDictType[]  queryEosDictTypes(CriteriaType criteriaType,DataObject pageCond){
		criteriaType.set_entity(EosDictType.QNAME);
//		if (sortFilder !=null && sortOrder != null) {
//			criteria.set("_orderby[1]/_property", sortFilder);
//			criteria.set("_orderby[1]/_sort", sortOrder);
//		}
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(EosDictType.class, dasCriteria, pageCond);
	}


    public void updateEosDictType(DataObject eosDictType){
	    getDASTemplate().updateEntity(eosDictType);
    }
    
    public DictResponse reloadDictType() {
		BusinessDictUtil.reLoad();
		return new DictResponse(true, "刷新成功");
	}

	public void saveEosDictType(DataObject[] eosDictTypes, DataObject criteria) {

		for(DataObject eosDictType : eosDictTypes)
		{
			String state = (String)eosDictType.get("_state");
			if ("added".equals(state)) 
			{
				addEosDictType(eosDictType);
			} 
			
			else if ("removed".equals(state) || "deleted".equals(state)) 
			{
				deleteEosDictType(eosDictType,criteria);
			} 

			else if ("modified".equals(state)) 
			{
				updateEosDictType(eosDictType);
			}
		}
	
	}
    
}

