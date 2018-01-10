package com.bos.utp.dict;

import com.bos.utp.dict.dataset.EosDictEntry;
import com.eos.das.entity.IDASCriteria;
import com.eos.das.entity.criteria.CriteriaType;
import com.eos.foundation.PageCond;
import com.eos.spring.DASDaoSupport;
import commonj.sdo.DataObject;

/**
 * <pre>
 * Title: ������������
 * Description: �����ܵ����� 
 * </pre>
 * @author wangbing (mailto:service6@primeton.com)
 * @version 1.00.00
 * 
 */
/*
 * �޸���ʷ
 * $log$
 */
public class EosDictEntryService extends DASDaoSupport implements IEosDictEntryService{
	public void addEosDictEntry(EosDictEntry eosDictEntry){
//		getDASTemplate().getPrimaryKey(eosDictEntry);
		if(String.valueOf(eosDictEntry.getStatus()) != null && !"".equals(String.valueOf(eosDictEntry.getStatus()))){
			eosDictEntry.setStatus(1);
		}
		if(eosDictEntry.getParentid() == null){
			eosDictEntry.setRank(1);
			eosDictEntry.setSeqno("." + eosDictEntry.getEosDictType().getDicttypeid() + ".");						
		}else{
			eosDictEntry.setRank(eosDictEntry.getRank() + 1);
			eosDictEntry.setSeqno(eosDictEntry.getSeqno() + eosDictEntry.getEosDictType().getDicttypeid() + ".");
		}
		getDASTemplate().insertEntity(eosDictEntry);
	}

	public void deleteEosDictEntry(EosDictEntry[] eosDictEntries ){
		for(DataObject eosDictEntry:eosDictEntries){
			getDASTemplate().deleteEntityCascade(eosDictEntry);
		}
	}
	
	
	public void deleteEosDictEntry(EosDictEntry eosDictEntry) {
		getDASTemplate().deleteEntityCascade(eosDictEntry);
	}


	public void getEosDictEntry(EosDictEntry eosDictEntry){
		getDASTemplate().expandEntity(eosDictEntry);
	}
	
	public int countEntry(CriteriaType criteria) {
		criteria.set_entity(EosDictEntry.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteria);
		return getDASTemplate().count(dasCriteria);
	}


	public EosDictEntry[]  queryEosDictEntrys(CriteriaType criteriaType,PageCond pageCond){
		criteriaType.set_entity(EosDictEntry.QNAME);
		IDASCriteria dasCriteria = getDASTemplate().criteriaTypeToDASCriteria(criteriaType);
		return getDASTemplate().queryEntitiesByCriteriaEntityWithPage(EosDictEntry.class, dasCriteria, pageCond);
	}


    public void updateEosDictEntry(EosDictEntry eosDictEntry){
	    getDASTemplate().updateEntity(eosDictEntry);
    }
    
    public void saveEosDictEntry(EosDictEntry[] eosDictEntries){

		for(EosDictEntry eosDictEntry : eosDictEntries)
		{
			String state = (String)eosDictEntry.get("_state");
			if ("added".equals(state)) 
			{
				addEosDictEntry(eosDictEntry);
			} 
			
			else if ("removed".equals(state) || "deleted".equals(state)) 
			{
				deleteEosDictEntry(eosDictEntry);
			} 

			else if ("modified".equals(state)) //���£�_stateΪ�գ���modified
			{
				updateEosDictEntry(eosDictEntry);
			}
		}
	
	
    }
    
}

